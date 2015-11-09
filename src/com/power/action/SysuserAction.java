package com.power.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lys.mvc.action.BaseAjaxAction;
import com.lys.security.encryption.Encoder;
import com.lys.security.encryption.impl.MD5Encoder;
import com.lys.utils.BizTransUtil;
import com.lys.utils.CommonUtil;
import com.lys.utils.JsonUtils;
import com.lys.utils.StringUtils;
import com.lys.utils.pagination.PageBean;
import com.power.bean.Ae_t_sysuser;
import com.power.bean.Af_t_sysuserrole;
import com.power.utils.PageColumn;

@Scope(value = "prototype")
@Controller("SysuserAction")
@RequestMapping(value="/system/sysuser")
public class SysuserAction extends BaseAjaxAction{
	/***
	 * 进入角色主界面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="initSysuser")
	public String initSysuser(){
		StringBuffer sb=new StringBuffer("select a.ac_st_id as TREEID, '1' as \"open\", '0' as TREEPID,ac_st_name TREENAME from ac_t_sysrole a order by a.ac_dt_addDate asc");
		String json=JsonUtils.getJsonDataFromCollection(baseBiz.queryForList(sb.toString()));
		model.addAttribute("roleTree", json);
		StringBuffer sb2=new StringBuffer("  select ac_st_id ab_st_value,ac_st_name ab_st_name from ac_t_sysrole  ");
		String json2=JsonUtils.getJsonDataFromCollection(baseBiz.queryForList(sb2.toString()));
		model.addAttribute("roleSelect", json2);
		return "system/sysuser/sysuserList";
	}
	/**
	 * 查询系统用户列表——分页---此处每次都要进行列设置， 这种方式需要在 1.7进行改进
	 * @param response
	 * @param sysfunction
	 */
	@RequestMapping(value="sysuserList")
	public String sysuserList(Ae_t_sysuser ae_t_sysuser,String isonline,String ISSHOWCOLUMS,Integer pageIndex,Integer pageSize){
		StringBuffer sb=new StringBuffer("select * "); 
		StringBuffer sbcount=new StringBuffer("select count(a.ae_st_id) ");
		StringBuffer sbcommon=new StringBuffer("  from ( select ae_st_id, ae_st_nickName,username ,(case when (select count(aj_st_id) from aj_t_userlogin where aj_st_objid=ae_st_id and aj_st_status='1' )>0 then '在线' else '离线' end) isonline,ae_st_userkind ,"+StringUtils.trunTypeByString("ae_dt_lastlogontime")+" ae_dt_lastlogontime,ae_dt_addDate from ae_t_sysuser) a where 1=1 ");
		List<String> params=new ArrayList<String>();
		//昵称
		if(org.springframework.util.StringUtils.hasText(ae_t_sysuser.getAe_st_nickName())){
			sbcommon.append(" and a.ae_st_nickName like ? ");
			params.add("%"+ae_t_sysuser.getAe_st_nickName()+"%");
		}
		//帐号
		if(org.springframework.util.StringUtils.hasText(ae_t_sysuser.getUsername())){
			sbcommon.append(" and a.username like ? ");
			params.add(ae_t_sysuser.getUsername()+"%");
		}
		//类型
		if(org.springframework.util.StringUtils.hasText(ae_t_sysuser.getAe_st_userkind())){
			sbcommon.append(" and a.ae_st_userkind = ? ");
			params.add(ae_t_sysuser.getAe_st_userkind());
		}
		//角色类型
		String roletype=request.getParameter("roletype");
		if(org.springframework.util.StringUtils.hasText(roletype)){
			sbcommon.append(" and a.ae_st_id in (select ae_st_id from af_t_sysuserrole where ac_st_id=? ) ");
			params.add(roletype);
		}
		//是否在线
		if(org.springframework.util.StringUtils.hasText(isonline)){
			sbcommon.append(" and a.isonline = ? ");
			params.add(isonline);
		}
		sbcount.append(sbcommon);
		sb.append(sbcommon).append(" order by a.ae_dt_addDate desc,a.username asc ");
		//分页数据、分页配置 查询
		PageBean pages=baseBiz.getPages(sb.toString(),sbcount.toString(),pageIndex, pageSize,params.toArray());
		//列设置
		List<PageColumn> columnList=new ArrayList<PageColumn>();
		columnList.add(new PageColumn("复选框", "ae_st_id").setIscheckbox(true).setWidth("30"));//复选框
		//columnList.add(new PageColumn("ID", "ae_st_id").setHiddle(true));
		columnList.add(new PageColumn("帐号", "username"));
		columnList.add(new PageColumn("昵称", "ae_st_nickName"));
//		columnList.add(new PageColumn("类型", "ae_st_userkind").setCode("YHLXZD").setIsshowcode(true));
		columnList.add(new PageColumn("上次登录", "ae_dt_lastlogontime"));
		columnList.add(new PageColumn("上线状态", "isonline"));
		//操作按钮
		StringBuffer btns=new StringBuffer("<button data-toggle='modal' onclick='editUser(this);' class='btn btn-sm blue' ><i class='fa fa-pencil-square-o'></i>编辑</button> ");
		btns.append("<button data-toggle='modal' onclick='delUser(this);' class='btn btn-sm red' ><i class='fa fa-trash-o'></i>删除</button> ");
		Map<String, String> map=new HashMap<String, String>();
		map.put("离线",btns.toString()+"<button data-toggle='modal' disabled class='btn btn-sm default' ><i class='fa fa-arrow-down'></i>强制下线</button> ");
		btns.append("<button data-toggle='modal' onclick='downline(this);' class='btn btn-sm yellow' ><i class='fa fa-arrow-down'></i>强制下线</button> ");
		map.put("在线",btns.toString());
		
		columnList.add(new PageColumn("操作",null).setCases("isonline").setWhen(map).setIscasewhen(true).setIsoperation(true));
		//列显示/隐藏配置
		if(org.springframework.util.StringUtils.hasText(ISSHOWCOLUMS)){
			String[] ss=ISSHOWCOLUMS.split(",");
			Map<String, String> showcolumnMap=new HashMap<String, String>();
			for(String s:ss){
				showcolumnMap.put(s,"yes");
			}
			model.addAttribute("showcolumnMap", showcolumnMap);
		}
		model.addAttribute("pageBeanObj", pages);//分页对象
		model.addAttribute("columnList", columnList);//控制列设置的集合
		return "system/common/commonList";
	}

	/**
	 * 新增或更新系统用户
	 * @param response
	 * @param sysfunction
	 */
	@RequestMapping(value="sysuserAdd")
	public void sysuserAdd(Ae_t_sysuser ae_t_sysuser,String roleids){
		String jsonData="";
		try {
			if(ae_t_sysuser!=null){
				if(!org.springframework.util.StringUtils.hasText(ae_t_sysuser.getUsername())){
					throw new RuntimeException("帐号不能为空");
				}
				Encoder e=new MD5Encoder();//使用security3 的MD5加密技术
				//创建批量保存事务对象
				List<BizTransUtil> list=new ArrayList<BizTransUtil>();
				//更新
				if (org.springframework.util.StringUtils.hasText(ae_t_sysuser.getAe_st_id())) {
					//构建数据--修改
					Map<String,Object>  map=new LinkedCaseInsensitiveMap<Object>();
					map.put("ae_st_id", ae_t_sysuser.getAe_st_id());//ID
					map.put("username", ae_t_sysuser.getUsername());
					if(org.springframework.util.StringUtils.hasText(ae_t_sysuser.getPassword())){
						map.put("password", e.encrypt(ae_t_sysuser.getPassword(), ae_t_sysuser.getUsername()));//MD5加密
					}
					map.put("ae_st_userkind", ae_t_sysuser.getAe_st_userkind());
					map.put("ae_st_nickName", ae_t_sysuser.getAe_st_nickName());
					map.put("ae_st_description", ae_t_sysuser.getAe_st_description());
					map.put("ae_st_updUserId", getcuttSysuserID());
					map.put("ae_dt_updDate", getcuttDate());
					list.add(new BizTransUtil(map,Ae_t_sysuser.class,CommonUtil.UPDATE));
					//构建数据--删除该系统用户所有角色
					Map<String,Object>  maprf=new LinkedCaseInsensitiveMap<Object>();
					maprf.put("ae_st_id", ae_t_sysuser.getAe_st_id());
					list.add(new BizTransUtil(maprf,Af_t_sysuserrole.class,CommonUtil.DELETE));
				}
				//新增
				else {
					if(!org.springframework.util.StringUtils.hasText(ae_t_sysuser.getPassword())){
						throw new RuntimeException("密码不能为空");
					}
					//构建数据--新增
					ae_t_sysuser.setAe_st_id(StringUtils.getUUID32());
					ae_t_sysuser.setAe_st_addUserId(getcuttSysuserID());
					ae_t_sysuser.setAe_dt_addDate(getcuttDate());
					ae_t_sysuser.setPassword(e.encrypt(ae_t_sysuser.getPassword(), ae_t_sysuser.getUsername()));
					ae_t_sysuser.setAe_st_lockstate("1");//账户锁定状态：1正常，2账户停用3.注销
					list.add(new BizTransUtil(ae_t_sysuser));
				}
				//保存角色
				if(org.springframework.util.StringUtils.hasText(roleids)){
					List<Af_t_sysuserrole> listrf=new ArrayList<Af_t_sysuserrole>();
					String[] poweridarry=roleids.split(",");
					for(String powerid:poweridarry){//保存这个系统用户的角色
						Af_t_sysuserrole rf=new Af_t_sysuserrole(true);
						rf.setAe_st_id(ae_t_sysuser.getAe_st_id());
						rf.setAc_st_id(powerid);
						listrf.add(rf);
					}
					list.add(new BizTransUtil(listrf,Af_t_sysuserrole.class));
				}
				//执行操作
				if (baseBiz.executesTRANS(list)) {
					jsonData = "{success:true,msg:\"操作成功!\"}";
				} else {
					jsonData = "{success:false,msg:\"操作异常!\"}";
				}
			}else{
				jsonData = "{success:false,msg:\"操作失败!\"}";
			}
		}  catch (Exception e) {
			jsonData = "{success:false,msg:\"操作异常，"+StringUtils.replaceBlank(e.getMessage())+"!\"}";
			e.printStackTrace();
		}
		this.createAjax(response,jsonData);
	}
	/**
	 * 删除系统用户
	 * @param response
	 * @param sysfunction
	 */
	@RequestMapping(value="sysuserDelete")
	public void sysuserDelete(String deleteData){
		String jsonData="";
		try {
			if(org.springframework.util.StringUtils.hasText(deleteData)){
				//创建批量保存事务对象
				List<BizTransUtil> list=new ArrayList<BizTransUtil>();
				list.add(new BizTransUtil(deleteData, Ae_t_sysuser.class));
				//构建数据--删除该系统用户所有角色
				Map<String,Object>  maprf=new LinkedCaseInsensitiveMap<Object>();
				maprf.put("ae_st_id", deleteData);
				list.add(new BizTransUtil(maprf,Af_t_sysuserrole.class,CommonUtil.DELETE));
				if(baseBiz.executesTRANS(list)){
					jsonData = "{success:true,msg:\"删除成功!\"}";
				}else{
					jsonData = "{success:false,msg:\"删除失败!\"}";
				}
			}else{
				jsonData = "{success:false,msg:\"删除失败!\"}";
			}
		} catch (Exception e) {
			jsonData = "{success:false,msg:\"删除异常，"+e.getMessage()+"!\"}";
			e.printStackTrace();
		}
	
		this.createAjax(response, jsonData);
	}
	/**
	 * 强制下线系统用户
	 * @param id 用户ID 
	 * @param tpye=2 代表踢出所有在线用户,不包括自己
	 */
	@RequestMapping(value="sysuserdownline")
	public void sysuserdownline(String id,String tpye){
		String jsonData="";
		try {
			//踢出某个用户
			if(org.springframework.util.StringUtils.hasText(id)){
				//踢出用户
				Map<String,String>  maprf=new LinkedCaseInsensitiveMap<String>();
				maprf.put(id,"1");
				this.outSessionsbyId(maprf);
				//修改用户在线记录
				Integer num= baseBiz.executeTRANS("update aj_t_userlogin set aj_dt_logouttime=?,aj_st_status='2' where aj_st_objid=? and aj_st_objname='ae_t_sysuser' ", new Date(),id);
				if(num>0){
					jsonData = "{success:true,msg:\"强制下线成功!\"}";
				}else{
					jsonData = "{success:false,msg:\"强制下线失败!\"}";
				}
			}
			//踢出所有在线用户,不包括自己
			else if("2".equals(tpye)){
				//踢出用户
				this.outSessionsAll();
				//修改用户在线记录
				baseBiz.executeTRANS("update aj_t_userlogin set aj_dt_logouttime=?,aj_st_status='2' where aj_st_objid!=? and aj_st_objname='ae_t_sysuser' ", new Date(),getcuttSysuserID());
				jsonData = "{success:true,msg:\"强制下线成功!\"}";
			}
			else{
				jsonData = "{success:false,msg:\"强制下线失败!\"}";
			}
		} catch (Exception e) {
			jsonData = "{success:false,msg:\"操作异常，"+e.getMessage()+"!\"}";
			e.printStackTrace();
		}
		this.createAjax(response, jsonData);
	}
	/**
	 * 查询单个系统用户信息
	 * @param response
	 * @param roleid
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="findUserById")
	public void findUserById(String userid){
		if(org.springframework.util.StringUtils.hasText(userid)){	
			StringBuffer sb=new StringBuffer(" select a.ae_st_id as \"ae_st_id\",a.username  as \"username\" ")
			.append(" ,a.ae_st_userkind  as \"ae_st_userkind\",a.ae_st_nickName as \"ae_st_nickName\",a.ae_st_description as \"ae_st_description\" ")
			.append(" from ae_t_sysuser a  ")
			.append(" where a.ae_st_id=? ")
			.append(" order by a.ae_dt_addDate desc ");
			Map<String, Object> map=baseBiz.queryForMap(sb.toString(), new Object[]{userid});
			List list=baseBiz.queryForList("select ac_st_id from af_t_sysuserrole where ae_st_id=?",new Object[]{userid}, String.class);
			map.put("roleids", StringUtils.toStringBySpilt(list));
			String jsonData = "{\"success\":true,result:["+JsonUtils.getJsonData(map)+"]}";
			this.createAjax(response, jsonData);
		}
	}
}



