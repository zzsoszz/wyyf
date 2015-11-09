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
import com.lys.utils.BizTransUtil;
import com.lys.utils.CommonUtil;
import com.lys.utils.JsonUtils;
import com.lys.utils.StringUtils;
import com.lys.utils.pagination.PageBean;
import com.power.bean.Ac_t_sysrole;
import com.power.bean.Ad_t_sysrolefunction;
import com.power.bean.Af_t_sysuserrole;
import com.power.utils.PageColumn;

@Scope(value = "prototype")
@Controller("SysroleAction")
@RequestMapping(value="/system/sysrole")
public class SysroleAction extends BaseAjaxAction{
	/***
	 * 进入角色主界面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="initSysrole")
	public String initSysrole(){
		StringBuffer sb=new StringBuffer("select a.aa_st_id TREEID,a.aa_nm_openorclose as \"open\",a.aa_st_parent TREEPID,a.aa_st_title TREENAME from  aa_t_sysfunction a order by a.aa_nm_orderno asc");
		String json=JsonUtils.getJsonDataFromCollection(baseBiz.queryForList(sb.toString()));
		model.addAttribute("functionTree", json);
		return "system/sysrole/sysroleList";
	}
	/**
	 * 查询角色列表——分页---此处每次都要进行列设置， 这种方式需要在 1.7进行改进
	 * @param response
	 * @param sysfunction
	 */
	@RequestMapping(value="sysroleList")
	public String sysroleFind( Ac_t_sysrole ac_t_sysrole,String ISSHOWCOLUMS,Integer pageIndex,Integer pageSize){
		StringBuffer sb=new StringBuffer("select a.ac_st_id,a.ac_st_code,"+StringUtils.trunTypeByString("a.ac_dt_addDate")+" ac_dt_addDate,(select ae_st_nickName from ae_t_sysuser where ae_st_id=a.ac_st_addUserId) ae_st_nickName,a.ac_st_name,a.ac_st_type "); 
		StringBuffer sbcount=new StringBuffer("select count(a.ac_st_id) ");
		StringBuffer sbcommon=new StringBuffer(" from ac_t_sysrole a where 1=1 ");
		List<String> params=new ArrayList<String>();
		//角色名
		if(org.springframework.util.StringUtils.hasText(ac_t_sysrole.getAc_st_name())){
			sbcommon.append(" and a.ac_st_name like ? ");
			params.add("%"+ac_t_sysrole.getAc_st_name()+"%");
		}
		//角色标识=角色码值
		if(org.springframework.util.StringUtils.hasText(ac_t_sysrole.getAc_st_code())){
			sbcommon.append(" and a.ac_st_code like ? ");
			params.add(ac_t_sysrole.getAc_st_code()+"%");
		}
		//角色类型
		if(org.springframework.util.StringUtils.hasText(ac_t_sysrole.getAc_st_type())){
			sbcommon.append(" and a.ac_st_type = ? ");
			params.add(ac_t_sysrole.getAc_st_type());
		}
		sbcount.append(sbcommon);
		sb.append(sbcommon).append(" order by a.ac_dt_addDate desc ");
		//分页数据、分页配置 查询
		PageBean pages=baseBiz.getPages(sb.toString(),sbcount.toString(),pageIndex, pageSize,params.toArray());
		//列设置
		List<PageColumn> columnList=new ArrayList<PageColumn>();
		columnList.add(new PageColumn("复选框", "ac_st_id").setIscheckbox(true).setWidth("30"));//复选框
		columnList.add(new PageColumn("角色名", "ac_st_name"));
		columnList.add(new PageColumn("角色标识", "ac_st_code"));
		columnList.add(new PageColumn("类型", "ac_st_type").setCode("JSLXZD").setIsshowcode(true));
		columnList.add(new PageColumn("创建人", "ae_st_nickName"));
		columnList.add(new PageColumn("创建时间", "ac_dt_addDate"));
		//操作按钮
		StringBuffer btns=new StringBuffer("<button data-toggle='modal' onclick='editRole(this);' class='btn btn-sm blue' ><i class='fa fa-pencil-square-o'></i>编辑</button> ");
		btns.append("<button data-toggle='modal' onclick='delRole(this);' class='btn btn-sm red' ><i class='fa fa-trash-o'></i>删除</button> ");
		columnList.add(new PageColumn("操作", btns.toString()).setIsoperation(true));
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
	 * 新增或更新角色
	 * @param response
	 * @param sysfunction
	 */
	@RequestMapping(value="sysroleAdd")
	public void sysroleAdd( Ac_t_sysrole ac_t_sysrole,String powerids){
		String jsonData="";
		try {
			if(ac_t_sysrole!=null){			
				//创建批量保存事务对象
				List<BizTransUtil> list=new ArrayList<BizTransUtil>();
				//更新
				if (org.springframework.util.StringUtils.hasText(ac_t_sysrole.getAc_st_id())) {
					//构建数据--修改
					Map<String,Object>  map=new LinkedCaseInsensitiveMap<Object>();
					map.put("ac_st_id", ac_t_sysrole.getAc_st_id());//ID
					map.put("ac_st_name", ac_t_sysrole.getAc_st_name());
					map.put("ac_st_code", ac_t_sysrole.getAc_st_code());
					map.put("ac_st_describe", ac_t_sysrole.getAc_st_describe());
					map.put("ac_st_type", ac_t_sysrole.getAc_st_type());
					map.put("ac_dt_updDate",getcuttDate());
					map.put("ac_st_updUserId",  getcuttSysuserID());
					list.add(new BizTransUtil(map,Ac_t_sysrole.class,CommonUtil.UPDATE));
					//构建数据--删除该角色所有权限
					Map<String,Object> maprf=new LinkedCaseInsensitiveMap<Object>();
					maprf.put("ac_st_id", ac_t_sysrole.getAc_st_id());
					list.add(new BizTransUtil(maprf,Ad_t_sysrolefunction.class,CommonUtil.DELETE));
				}
				//新增
				else {
					//构建数据--新增
					ac_t_sysrole.setAc_st_id(StringUtils.getUUID32());
					ac_t_sysrole.setAc_st_addUserId(getcuttSysuserID());
					ac_t_sysrole.setAc_dt_addDate(new Date());
					list.add(new BizTransUtil(ac_t_sysrole));
				}
				//保存权限
				if(org.springframework.util.StringUtils.hasText(powerids)){
					List<Ad_t_sysrolefunction> listrf=new ArrayList<Ad_t_sysrolefunction>();
					String[] poweridarry=powerids.split(",");
					for(String powerid:poweridarry){//保存这个角色的权限
						Ad_t_sysrolefunction rf=new Ad_t_sysrolefunction(true);
						rf.setAc_st_id(ac_t_sysrole.getAc_st_id());
						rf.setAa_st_id(powerid);
						listrf.add(rf);
					}
					list.add(new BizTransUtil(listrf,Ad_t_sysrolefunction.class));
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
			jsonData = "{success:false,msg:\"操作异常，"+e.getMessage()+"!\"}";
			e.printStackTrace();
		}
		this.createAjax(response,jsonData);
	}
	
	/**
	 * 删除角色
	 * @param response
	 * @param sysfunction
	 */
	@RequestMapping(value="sysroleDelete")
	public void sysroleDelete( String deleteData){
		String jsonData="";
		try {
			if(org.springframework.util.StringUtils.hasText(deleteData)){
				//创建批量保存事务对象
				List<BizTransUtil> list=new ArrayList<BizTransUtil>();
				list.add(new BizTransUtil(deleteData, Ac_t_sysrole.class));
				//构建数据--删除该角色所有权限
				Map<String,Object>  maprf=new LinkedCaseInsensitiveMap<Object>();
				maprf.put("ac_st_id", deleteData);
				list.add(new BizTransUtil(maprf,Ad_t_sysrolefunction.class,CommonUtil.DELETE));
				//删除用户与该角色的关系
				Map<String,Object>  mapur=new LinkedCaseInsensitiveMap<Object>();
				mapur.put("ac_st_id", deleteData);
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
	 * 查询单个角色信息
	 * @param response
	 * @param roleid
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="findRoleById")
	public void findRoleById( String roleid){
		if(org.springframework.util.StringUtils.hasText(roleid)){
			StringBuffer sb=new StringBuffer(" select a.ac_st_id as \"ac_st_id\",a.ac_st_code as \"ac_st_code\","+StringUtils.trunTypeByString("a.ac_dt_addDate")+"  as \"ac_dt_addDate\" ")
			.append(" ,(select ae_st_nickName from ae_t_sysuser where ae_st_id=a.ac_st_addUserId) as \"ae_st_nickName\",a.ac_st_name as \"ac_st_name\",a.ac_st_type as \"ac_st_type\" ")
			.append("   ,a.ac_st_describe as \"ac_st_describe\" ")
			.append(" from ac_t_sysrole a  ")
			.append(" where a.ac_st_id=? ")
			.append(" order by a.ac_dt_addDate desc ");
			Map<String, Object> map=baseBiz.queryForMap(sb.toString(), new Object[]{roleid});
			List list=baseBiz.queryForList("select aa_st_id from ad_t_sysrolefunction where ac_st_id=?",new Object[]{roleid}, String.class);
			map.put("powerids", StringUtils.toStringBySpilt(list));
			String jsonData = "{\"success\":true,result:["+JsonUtils.getJsonData(map)+"]}";
			this.createAjax(response, jsonData);
		}
	}
	
}



