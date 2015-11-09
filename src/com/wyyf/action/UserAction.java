package com.wyyf.action;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.lys.mvc.action.BaseAjaxAction;
import com.lys.security.encryption.Encoder;
import com.lys.security.encryption.impl.MD5Encoder;
import com.lys.utils.BizTransUtil;
import com.lys.utils.CommonUtil;
import com.lys.utils.JsonUtils;
import com.lys.utils.StringUtils;
import com.lys.utils.UploadFile;
import com.lys.utils.pagination.PageBean;
import com.power.bean.Ae_t_sysuser;
import com.power.bean.Af_t_sysuserrole;
import com.power.bean.Ag_t_file;
import com.power.utils.PageColumn;
import com.wyyf.bean.Ba_t_Labour;
import com.wyyf.bean.Bb_t_ShopUser;
import com.wyyf.bean.Bm_t_userMoneyLog;
import com.wyyf.bean.Bq_t_UsefulAddress;

/***
 * 会员管理
 * @author Administrator
 *
 */
@Scope(value = "prototype")
@Controller("UserAction")
@RequestMapping(value="/wyyf/user")
public class UserAction extends BaseAjaxAction{
	/***
	 * 进入用户主界面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="initUser")
	public String initUser(){
		StringBuffer sb=new StringBuffer("select a.d_code TREEID,'0' open,a.d_parent TREEPID,a.d_name TREENAME from aa26 a  "); 
		String json=JsonUtils.getJsonDataFromCollection(baseBiz.queryForList(sb.toString()));
		model.addAttribute("groupTree", json);
		
		return "wyyf/user/userList";
	}
	/**
	 * 查询系统用户列表——分页---此处每次都要进行列设置， 这种方式需要在 1.7进行改进
	 * @param response
	 * @param sysfunction
	 */
	@RequestMapping(value="userList")
	public String userList(Ae_t_sysuser ae_t_sysuser,String isonline,String ISSHOWCOLUMS,Integer pageIndex,Integer pageSize){
		StringBuffer sb=new StringBuffer("select a.ae_st_id,a.ae_st_name,a.ae_st_sex,ae_st_lockstate,ae_st_type,a.username,a.ae_st_tell,"+StringUtils.trunTypeByString("a.ae_dt_addDate")+" ae_dt_addDate  "); 
		StringBuffer sbcount=new StringBuffer("select count(a.ae_st_id) ");
		StringBuffer sbcommon=new StringBuffer("  from ae_t_sysuser a where a.ae_st_lockstate!='3' and a.ae_st_userkind='3' ");
		List<String> params=new ArrayList<String>(); 
		//姓名
		if(org.springframework.util.StringUtils.hasText(ae_t_sysuser.getAe_st_nickName())){
			sbcommon.append(" and a.ae_st_nickName like ? ");
			params.add("%"+ae_t_sysuser.getAe_st_nickName()+"%");
		}
		//姓名
				if(org.springframework.util.StringUtils.hasText(ae_t_sysuser.getAe_st_name())){
					sbcommon.append(" and a.ae_st_name like ? ");
					params.add("%"+ae_t_sysuser.getAe_st_name()+"%");
				}
		//帐号
		if(org.springframework.util.StringUtils.hasText(ae_t_sysuser.getUsername())){
			sbcommon.append(" and a.username like ? ");
			params.add("%"+ae_t_sysuser.getUsername()+"%");
		}
//		//状态查询
//		if(StringUtils.hasText(ae_t_sysuser.getAe_st_isjh())){
//			sbcommon.append(" and a.ae_st_isjh = ? ");
//			params.add(ae_t_sysuser.getAe_st_isjh());
//		}
		sbcount.append(sbcommon);
		sb.append(sbcommon).append(" order by a.ae_dt_addDate desc ");
		//分页数据、分页配置 查询
		PageBean pages=baseBiz.getPages(sb.toString(),sbcount.toString(),pageIndex, pageSize,params.toArray());
		//列设置
		List<PageColumn> columnList=new ArrayList<PageColumn>();
		columnList.add(new PageColumn("序号", "ae_st_id").setIsnum(true).setWidth("30"));//序号
		columnList.add(new PageColumn("姓名", "ae_st_name"));
		columnList.add(new PageColumn("帐号", "username"));
		columnList.add(new PageColumn("性别", "ae_st_sex").setCode("SEX").setIsshowcode(true));
		columnList.add(new PageColumn("电话", "ae_st_tell"));
		columnList.add(new PageColumn("状态", "ae_st_lockstate").setCode("ZHZT").setIsshowcode(true));
		columnList.add(new PageColumn("注册时间", "ae_dt_addDate"));
		//操作按钮
		StringBuffer btns1=new StringBuffer("");
		btns1.append("<button data-toggle='modal' onclick='czUser(this);' class='btn btn-sm green' ><i class='fa fa-trash-o'></i>充值</button> ");
		btns1.append("<button data-toggle='modal' onclick='editUser(this);' class='btn btn-sm blue' ><i class='fa fa-pencil-square-o'></i>编辑</button> ");
		btns1.append("<button data-toggle='modal' onclick='zxUser(this);' class='btn btn-sm red' ><i class='fa fa-trash-o'></i>注销</button> ");
		columnList.add(new PageColumn("操作",btns1.toString()).setCases("ae_st_isjh").setIsoperation(true));
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
	@RequestMapping(value="userAdd")
	public void userAdd(Ae_t_sysuser ae_t_sysuser,Ba_t_Labour labour,Bb_t_ShopUser shopUser,Ag_t_file oldFile,MultipartHttpServletRequest multipartRequest){
		String jsonData="";
		try {
			if(!org.springframework.util.StringUtils.hasText(ae_t_sysuser.getUsername())){
				throw new RuntimeException("帐号不能为空");
			}
			//选择的地区
			String groupids3=request.getParameter("groupids3");
			if(org.springframework.util.StringUtils.hasText(groupids3)){
				String[] groups=groupids3.split(",");
				for(int b=groups.length,i=b-1;i>=0;i--){
					if(i==b-1){
						ae_t_sysuser.setAe_st_shsheng(groups[i]);
					}else if(i==b-2){
						ae_t_sysuser.setAe_st_shshi(groups[i]);
					}else if(i==b-3){
						ae_t_sysuser.setAe_st_shxian(groups[i]);
					}
				}
			}
			Encoder e=new MD5Encoder();//使用security3 的MD5加密技术
			//创建批量保存事务对象
			List<BizTransUtil> list=new ArrayList<BizTransUtil>();
			String id = ae_t_sysuser.getAe_st_id();
			//更新
			if (org.springframework.util.StringUtils.hasText(id)) {
				//构建数据--修改
				Map<String,Object>  map=new LinkedCaseInsensitiveMap<Object>();
				map.put("ae_st_id", id);//ID
				if(org.springframework.util.StringUtils.hasText(ae_t_sysuser.getPassword())){
					map.put("password", e.encrypt(ae_t_sysuser.getPassword(), ae_t_sysuser.getUsername()));//MD5加密
				}	
				map.put("ae_st_updUserId", getcuttSysuserID());
				map.put("ae_dt_updDate", getcuttDate());
				map.put("ae_st_lockstate", ae_t_sysuser.getAe_st_lockstate());//审核通过/不通过状态修改
				map.put("ae_st_name", ae_t_sysuser.getAe_st_name());//姓名
				map.put("ae_st_tell", ae_t_sysuser.getAe_st_tell());//电话
				map.put("ae_st_sex", ae_t_sysuser.getAe_st_sex());//性别
				map.put("ae_nm_age", ae_t_sysuser.getAe_nm_age());//年龄 
				map.put("ae_st_address", ae_t_sysuser.getAe_st_address());//详细地址
				map.put("ae_st_intro", ae_t_sysuser.getAe_st_intro());//简介
				map.put("ae_st_shsheng", ae_t_sysuser.getAe_st_shsheng());//省
				map.put("ae_st_shshi", ae_t_sysuser.getAe_st_shshi());//市
				map.put("ae_st_shxian", ae_t_sysuser.getAe_st_shxian());//县/区
				map.put("ae_nm_zjnum", ae_t_sysuser.getAe_nm_zjnum());//资金余额
				map.put("ae_st_nickName", ae_t_sysuser.getAe_st_nickName());//用户昵称
				
				list.add(new BizTransUtil(map,Ae_t_sysuser.class,CommonUtil.UPDATE));
				
				String type = ae_t_sysuser.getAe_st_type();
				if("3".equals(type)){
					Map<String,Object>  sjmap=new LinkedCaseInsensitiveMap<Object>();
					sjmap.put("bb_st_id", shopUser.getBb_st_id());
					sjmap.put("bb_st_area", shopUser.getBb_st_area());
					sjmap.put("bb_st_phone1", shopUser.getBb_st_phone1());
					sjmap.put("bb_st_phone2", shopUser.getBb_st_phone2());
					sjmap.put("bb_st_shopinfo", shopUser.getBb_st_shopinfo());
					list.add(new BizTransUtil(sjmap,Bb_t_ShopUser.class,CommonUtil.UPDATE));
				}else if("4".equals(type)||"5".equals(type)||"6".equals(type)){
					Map<String,Object>  sfmap=new LinkedCaseInsensitiveMap<Object>();
					sfmap.put("ba_st_id", labour.getBa_st_id());
					sfmap.put("ba_st_type", labour.getBa_st_type());
					sfmap.put("ba_st_price", labour.getBa_st_price());
					sfmap.put("ba_st_team_intro", labour.getBa_st_team_intro());
					list.add(new BizTransUtil(sfmap,Ba_t_Labour.class,CommonUtil.UPDATE));
				}
			}
			//新增
			else {
				//构建数据--新增 
				String aeid = StringUtils.getUUID32();
				ae_t_sysuser.setAe_st_id(aeid);
				ae_t_sysuser.setAe_st_userkind("3");//用户类型
				ae_t_sysuser.setAe_st_addUserId(getcuttSysuserID());
				ae_t_sysuser.setAe_dt_addDate(getcuttDate());
				ae_t_sysuser.setPassword(e.encrypt(ae_t_sysuser.getPassword(), ae_t_sysuser.getUsername()));
				ae_t_sysuser.setAe_st_lockstate("1");//账户锁定状态：1正常，2账户停用3.注销
				
				String usertype = ae_t_sysuser.getAe_st_type();
				if("3".equals(usertype)){
					//添加商家附加信息
					String sjid = StringUtils.getUUID32();  //商家id
					ae_t_sysuser.setAe_st_objid(sjid);
					ae_t_sysuser.setAe_st_objtype("bb_t_shopuser");
					shopUser.setBb_st_id(sjid);
					shopUser.setBb_st_userid(aeid);
					shopUser.setBb_st_addUserId(getcuttSysuserID());
					shopUser.setBb_dt_addDate(getcuttDate());
					list.add(new BizTransUtil(shopUser));
				}else if("4".equals(usertype)||"5".equals(usertype)||"6".equals(usertype)){
					//添加师傅附加信息
					String sfid = StringUtils.getUUID32(); //师傅id
					ae_t_sysuser.setAe_st_objid(sfid);
					ae_t_sysuser.setAe_st_objtype("ba_t_labour");
					labour.setBa_st_id(sfid);
					labour.setBa_st_userid(aeid);
					labour.setBa_st_addUserId(getcuttSysuserID());
					labour.setBa_dt_addDate(getcuttDate());
					list.add(new BizTransUtil(labour));
				}
				list.add(new BizTransUtil(ae_t_sysuser));
				
				
				String type = ae_t_sysuser.getAe_st_type();
				String roleid = (String) baseBiz.queryForObject("select ac_st_id from ac_t_sysrole where ac_st_code = ?",
						new Object[]{"2".equals(type)?"WZGLY":("3".equals(type)?"SJ":("4".equals(type)||"5".equals(type)||"6".equals(type)?"SF":"HY"))},String.class);
				Af_t_sysuserrole rf=new Af_t_sysuserrole(true);
				rf.setAe_st_id(aeid);
				rf.setAc_st_id(roleid);
				list.add(new BizTransUtil(rf));
			}
			
			//新增图片/删除图片
			MultipartFile mf = multipartRequest.getFile("myfile");
			String delfileurl = "";
			if(mf!=null && mf.getSize()>0){
				//如果是修改，且文件表不为空，则删除相关文件表数据，和清除文件
				if (org.springframework.util.StringUtils.hasText(id)){
					list.add(new BizTransUtil("delete from ag_t_file where ag_st_objid=?", new Object[]{id},CommonUtil.DELETE));//删除本条数据
					delfileurl=oldFile.getAg_st_url();
				}
				Ag_t_file file=new Ag_t_file(true);
				//上传文件的相对路径
				String uploadPath=UploadFile.uploadInputStream(mf.getInputStream(), mf.getOriginalFilename(), "images/business/user");
				BufferedImage buff = ImageIO.read(mf.getInputStream());
				file.setAg_nm_height(buff.getHeight());//文件属性高
				file.setAg_nm_width(buff.getWidth());//文件属性宽
				file.setAg_nm_size(mf.getSize());//文件属性大小
				file.setAg_st_format(mf.getContentType());//文件格式
				file.setAg_dt_addDate(getcuttDate());//创建时间
				file.setAg_st_addUserId(getcuttSysuserID());//创建人员ID  
				file.setAg_st_objid(ae_t_sysuser.getAe_st_id());//文件归属ID
				file.setAg_st_objtype("ae_t_sysuser");//文件对应的对象
				file.setAg_st_type("1");//文件类型-图片
				file.setAg_st_mark("userfile");//自定义文件标识
				file.setAg_st_url(uploadPath);////文件 的存储地址
				list.add(new BizTransUtil(file));
			}
			//执行操作
			if (baseBiz.executesTRANS(list)) {
				if(org.springframework.util.StringUtils.hasText(delfileurl)){
					UploadFile.delpic(delfileurl);//删除原文件
				}
				jsonData = "{success:true,msg:\"操作成功!\"}";
			} else {
				jsonData = "{success:false,msg:\"操作异常!\"}";
			}
		}  catch (Exception e) {
			jsonData = "{success:false,msg:\"操作异常，"+StringUtils.replaceBlank(e.getMessage())+"!\"}";
		}
		this.createAjax(response,jsonData);
	}
	/**
	 * 用户注销
	 */
	@RequestMapping(value="userzx")
	public void userzx(String ae_st_id){
		String jsonData="";
		try {
			//创建批量保存事务对象
			List<BizTransUtil> list=new ArrayList<BizTransUtil>();
			//更新
			if (org.springframework.util.StringUtils.hasText(ae_st_id)) {
				//构建数据--修改
				Map<String,Object>  map=new LinkedCaseInsensitiveMap<Object>();
				map.put("ae_st_id", ae_st_id);//ID
				map.put("ae_st_lockstate", "3");//账户锁定状态：1正常，3.注销
				map.put("ae_st_logoffreason", "系统管理员注销");
				list.add(new BizTransUtil(map,Ae_t_sysuser.class,CommonUtil.UPDATE));
			}
			//执行操作
			if (baseBiz.executesTRANS(list)) {
				jsonData = "{success:true,msg:\"操作成功!\"}";
			} else {
				jsonData = "{success:false,msg:\"操作异常!\"}";
			}
		}  catch (Exception e) {
			jsonData = "{success:false,msg:\"操作异常，"+StringUtils.replaceBlank(e.getMessage())+"!\"}";
		}
		this.createAjax(response,jsonData);
	}
	/**
	 * 用户充值或者扣款
	 * @param response
	 * @param sysfunction
	 */
	@RequestMapping(value="czuser")
	public void czuser(Ae_t_sysuser ae_t_sysuser,String czkc){
		String jsonData="";
		try {
			//更新
			if (org.springframework.util.StringUtils.hasText(ae_t_sysuser.getAe_st_id())&&org.springframework.util.StringUtils.hasText(czkc)) {
				//创建批量保存事务对象
				List<BizTransUtil> list=new ArrayList<BizTransUtil>();
				//账目记录表
				Bm_t_userMoneyLog bm=new Bm_t_userMoneyLog(true);
				bm.setBm_st_jsuserid(ae_t_sysuser.getAe_st_id());;//接收用户ID【FK】--被处理的用户
//				bb.setBm_st_fsuserid(BusinessComUtil.DeaufltAdminID);//发送用户ID【FK】--操作人
				bm.setBm_st_type("1");//交易类型 （1=资金类型、2=佣金类型、3=积分类型）
				bm.setBm_st_addUserId(getcuttSysuserID());//创建人员ID  
				bm.setBm_dt_addDate(getcuttDate());//创建时间
				bm.setBm_st_remark(ae_t_sysuser.getAe_st_remark());//获取备注
				String sql="";
				//充值
				if("1".equals(czkc)){
					sql="update ae_t_sysuser set ae_nm_zjnum=ae_nm_zjnum+? where ae_st_id=? ";
					bm.setBm_nm_money(+ae_t_sysuser.getAe_nm_zjnum());//本次交易金额
					bm.setBm_st_operation("1");//操作类型 （1=充值、2=扣款、3=提现、4=二级返佣、5=三级返佣）
				}
				//扣款
				else if("2".equals(czkc)){
					sql="update ae_t_sysuser set ae_nm_zjnum=ae_nm_zjnum-? where ae_st_id=? ";
					bm.setBm_nm_money(-ae_t_sysuser.getAe_nm_zjnum());//本次交易金额
					bm.setBm_st_operation("2");//操作类型 （1=充值、2=扣款、3=提现、4=二级返佣、5=三级返佣）
				}
				list.add(new BizTransUtil(sql, new Object[]{ae_t_sysuser.getAe_nm_zjnum(),ae_t_sysuser.getAe_st_id()}, CommonUtil.UPDATE));//执行
				list.add(new BizTransUtil(bm));
				if (baseBiz.executesTRANS(list)) {
					jsonData = "{success:true,msg:\"操作成功!\"}";
				} else {
					jsonData = "{success:false,msg:\"操作异常!\"}";
				}
			}
		}  catch (Exception e) {
			jsonData = "{success:false,msg:\"操作异常，"+StringUtils.replaceBlank(e.getMessage())+"!\"}";
		}
		this.createAjax(response,jsonData);
	}
	 
	/**
	 * 查询单个系统用户信息
	 * @param response
	 * @param roleid
	 */
	@RequestMapping(value="findUserById")
	public void findUserById(String userid){
		if(org.springframework.util.StringUtils.hasText(userid)){
			StringBuffer sb=new StringBuffer("select a.ae_st_lockstate,a.ae_st_type,a.ae_st_tell,a.ae_st_id,a.ae_st_name,a.ae_nm_age,a.ae_st_sex,a.username,")
			.append("a.ae_st_nickName,a.ae_st_shsheng,a.ae_st_shshi,a.ae_st_shxian,a.ae_st_address,a.ae_st_intro,b.ba_st_id,b.ba_st_type,b.ba_st_team_intro,b.ba_st_price,")
			.append("c.bb_st_id,c.bb_st_area,c.bb_st_phone1,c.bb_st_phone2,c.bb_st_shopinfo,(select ag_st_url from ag_t_file where ag_st_objid = a.ae_st_id and ag_st_mark='userfile' limit 0,1) ag_st_url")
			.append(" from ae_t_sysuser a left join ba_t_labour b on a.ae_st_id = b.ba_st_userid left join bb_t_shopuser c on a.ae_st_id = c.bb_st_userid  ")
			.append(" where a.ae_st_id=? ");
			Map<String, Object> map=baseBiz.queryForMap(sb.toString(), new Object[]{userid});
			 String ae_st_shsheng=StringUtils.toStringByObject(map.get("ae_st_shsheng"));//省
			 String ae_st_shshi=StringUtils.toStringByObject(map.get("ae_st_shshi"));//市
			 String ae_st_shxian=StringUtils.toStringByObject(map.get("ae_st_shxian"));//县/区
			if(org.springframework.util.StringUtils.hasText(ae_st_shxian)){
				map.put("groupsid", ae_st_shxian);
			}else if(org.springframework.util.StringUtils.hasText(ae_st_shshi)){
				map.put("groupsid", ae_st_shshi);
			}else if(org.springframework.util.StringUtils.hasText(ae_st_shsheng)){
				map.put("groupsid", ae_st_shsheng);
			}
			String jsonData = "{\"success\":true,result:["+JsonUtils.getJsonData(map)+"]}";
			this.createAjax(response, jsonData);
		}
	}
	
	/**
	 * 查询系统用户交易列表— 
	 * @param response
	 * @param sysfunction
	 */
	@RequestMapping(value="usermoneylogList")
	public String usermoneylogList(Bm_t_userMoneyLog bb ,String ISSHOWCOLUMS,Integer pageIndex,Integer pageSize){
		StringBuffer sb=new StringBuffer("select a.bm_st_remark,a.bm_st_id,a.bm_st_type,(select ae_st_name from ae_t_sysuser where ae_st_id=a.bm_st_adduserid) ae_st_name,a.bm_nm_money,a.bm_st_operation,"+StringUtils.trunTypeByString("a.bm_dt_addDate")+" bm_dt_addDate  "); 
		StringBuffer sbcount=new StringBuffer("select count(a.bm_st_id) ");
		StringBuffer sbcommon=new StringBuffer("  from bm_t_usermoneylog a where 1=1 ");
		List<String> params=new ArrayList<String>();
		//接收用户ID--必传，否则不查询
		if(org.springframework.util.StringUtils.hasText(bb.getBm_st_jsuserid())){
			sbcommon.append(" and a.bm_st_jsuserid = ? ");
			params.add(bb.getBm_st_jsuserid());
		}else{
			sbcommon.append(" and a.bm_st_jsuserid ='nocx' ");//不查询
		}
		sbcount.append(sbcommon);
		sb.append(sbcommon).append(" order by a.bm_dt_addDate desc ");
		//分页数据、分页配置 查询
		PageBean pages=baseBiz.getPages(sb.toString(),sbcount.toString(),pageIndex, pageSize,params.toArray());
		//列设置
		List<PageColumn> columnList=new ArrayList<PageColumn>();
		columnList.add(new PageColumn("序号", "ae_st_id").setIsnum(true).setWidth("30"));//序号
		columnList.add(new PageColumn("交易类型", "bm_st_type").setCode("JYLX").setIsshowcode(true));
		columnList.add(new PageColumn("操作人", "ae_st_name"));
		columnList.add(new PageColumn("操作类型", "bm_st_operation").setCode("CZLX").setIsshowcode(true));
		columnList.add(new PageColumn("操作金额", "bm_nm_money"));
		columnList.add(new PageColumn("操作时间", "bm_dt_addDate"));
		columnList.add(new PageColumn("备注", "bm_st_remark").setLength(10));
		//列显示/隐藏配置
		if(org.springframework.util.StringUtils.hasText(ISSHOWCOLUMS)){
			String[] ss=ISSHOWCOLUMS.split(",");
			Map<String, String> showcolumnMap=new HashMap<String, String>();
			for(String s:ss){
				showcolumnMap.put(s,"yes");
			}
			model.addAttribute("showcolumnMap", showcolumnMap);
		}
		model.addAttribute("listMark", "money");
		model.addAttribute("pageBeanObj", pages);//分页对象
		model.addAttribute("columnList", columnList);//控制列设置的集合
		return "system/common/commonList";
	}
	

	/**
	 * 查询系统用户收货地址列表--主要针对会员 
	 * @param response
	 * @param sysfunction
	 */
	@RequestMapping(value="useraddressList")
	public String useraddressList(Bq_t_UsefulAddress bq, String ISSHOWCOLUMS,Integer pageIndex,Integer pageSize){
		StringBuffer sb=new StringBuffer("select bq_st_id,bq_st_ismr,bq_st_name,bq_st_phone,CONCAT(bq_st_sheng,bq_st_shi,bq_st_xian,bq_st_adress) address  "); 
		StringBuffer sbcount=new StringBuffer("select count(bq_st_id) ");
		StringBuffer sbcommon=new StringBuffer("  from bq_t_usefuladdress where 1=1 ");
		List<String> params=new ArrayList<String>();
		//接收用户ID--必传，否则不查询
		if(org.springframework.util.StringUtils.hasText(bq.getBq_st_memberid())){
			sbcommon.append(" and bq_st_memberid = ? ");
			params.add(bq.getBq_st_memberid());
		}else{
			sbcommon.append(" and bq_st_memberid ='nocx' ");//不查询
		}
		sbcount.append(sbcommon);
		sb.append(sbcommon).append(" order by bq_dt_addDate desc ");
		//分页数据、分页配置 查询
		PageBean pages=baseBiz.getPages(sb.toString(),sbcount.toString(),pageIndex, pageSize,params.toArray());
		//列设置
		List<PageColumn> columnList=new ArrayList<PageColumn>();
		columnList.add(new PageColumn("序号", "bq_st_id").setIsnum(true).setWidth("30"));//序号
		columnList.add(new PageColumn("是否默认", "bq_st_ismr").setCode("YESNO").setIsshowcode(true));
		columnList.add(new PageColumn("收货人", "bq_st_name"));
		columnList.add(new PageColumn("收货电话", "bq_st_phone") );
		columnList.add(new PageColumn("收货地址", "address"));
		//列显示/隐藏配置
		if(org.springframework.util.StringUtils.hasText(ISSHOWCOLUMS)){
			String[] ss=ISSHOWCOLUMS.split(",");
			Map<String, String> showcolumnMap=new HashMap<String, String>();
			for(String s:ss){
				showcolumnMap.put(s,"yes");
			}
			model.addAttribute("showcolumnMap", showcolumnMap);
		}
		model.addAttribute("listMark", "address");
		model.addAttribute("pageBeanObj", pages);//分页对象
		model.addAttribute("columnList", columnList);//控制列设置的集合
		return "system/common/commonList";
	}
}



