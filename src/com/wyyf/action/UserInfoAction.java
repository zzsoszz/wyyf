package com.wyyf.action;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Null;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.power.bean.Ae_t_sysuser;
import com.power.bean.Ag_t_file;
import com.wyyf.bean.Ba_t_Labour;
import com.wyyf.bean.Bb_t_ShopUser;

/**
 * 用于显示用户资料 以及 修改密码
 */
@Scope(value = "prototype")
@Controller("UserInfoAction")
@RequestMapping(value="/wyyf/info")
public class UserInfoAction extends BaseAjaxAction{
	
	/***
	 * 进入用户自己的信息维护界面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="initUser")
	public String initUser(){
		StringBuffer sb=new StringBuffer("select a.d_code TREEID,'0' open,a.d_parent TREEPID,a.d_name TREENAME from aa26 a  "); 
		String json=JsonUtils.getJsonDataFromCollection(baseBiz.queryForList(sb.toString()));
		model.addAttribute("groupTree", json);
		String userid=getcuttSysuserID();
		if(org.springframework.util.StringUtils.hasText(userid)){
			StringBuffer sb1=new StringBuffer("select a.ae_st_lockstate,a.ae_st_type,a.ae_st_tell,a.ae_st_id,a.ae_st_name,a.ae_nm_age,a.ae_st_sex,a.username,")
			.append("a.ae_st_nickName,a.ae_st_shsheng,a.ae_st_shshi,a.ae_st_shxian,a.ae_st_address,a.ae_st_intro,b.ba_st_id,b.ba_st_type,b.ba_st_team_intro,b.ba_st_price,")
			.append("c.bb_st_id,c.bb_st_area,c.bb_st_phone1,c.bb_st_phone2,c.bb_st_shopinfo,(select ag_st_url from ag_t_file where ag_st_objid = a.ae_st_id and ag_st_mark='userfile' limit 0,1) ag_st_url")
			.append(" from ae_t_sysuser a left join ba_t_labour b on a.ae_st_id = b.ba_st_userid left join bb_t_shopuser c on a.ae_st_id = c.bb_st_userid  ")
			.append(" where a.ae_st_id=? ");
			Map<String, Object> map=baseBiz.queryForMap(sb1.toString(), new Object[]{userid});
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
			model.addAttribute("jsonData", jsonData);
//			this.createAjax(response, jsonData);
		}
		
		return "wyyf/info/intoUserinfo";
	}
	/**
	 * 新增或更新系统用户
	 * @param response
	 * @param sysfunction
	 */
	@RequestMapping(value="userupd")
	public void userupd(Ae_t_sysuser ae_t_sysuser,Ba_t_Labour labour,Bb_t_ShopUser shopUser,Ag_t_file oldFile,MultipartHttpServletRequest multipartRequest){
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
//			Encoder e=new MD5Encoder();//使用security3 的MD5加密技术
			//创建批量保存事务对象
			List<BizTransUtil> list=new ArrayList<BizTransUtil>();
			String id = ae_t_sysuser.getAe_st_id();
			//更新
			if (org.springframework.util.StringUtils.hasText(id)) {
				//构建数据--修改
				Map<String,Object>  map=new LinkedCaseInsensitiveMap<Object>();
				map.put("ae_st_id", id);//ID
//				if(StringUtils.hasText(ae_t_sysuser.getPassword())){
//					map.put("password", e.encrypt(ae_t_sysuser.getPassword(), ae_t_sysuser.getUsername()));//MD5加密
//				}
				map.put("ae_st_updUserId", getcuttSysuserID());
				map.put("ae_dt_updDate", getcuttDate());
//				map.put("ae_st_lockstate", ae_t_sysuser.getAe_st_lockstate());//审核通过/不通过状态修改
				map.put("ae_st_name", ae_t_sysuser.getAe_st_name());//姓名
				if (ae_t_sysuser.getAe_st_tell().length()>11 || ae_t_sysuser.getAe_st_tell().length()<11) {
					jsonData = "{success:false,msg:\"请正确填写您的电话号码!\"}";
				}
				else {
				map.put("ae_st_tell", ae_t_sysuser.getAe_st_tell());//电话
				map.put("ae_st_sex", ae_t_sysuser.getAe_st_sex());//性别
				map.put("ae_nm_age", ae_t_sysuser.getAe_nm_age());//年龄 
				map.put("ae_st_address", ae_t_sysuser.getAe_st_address());//详细地址
				map.put("ae_st_intro", ae_t_sysuser.getAe_st_intro());//简介
				map.put("ae_st_shsheng", ae_t_sysuser.getAe_st_shsheng());//省
				map.put("ae_st_shshi", ae_t_sysuser.getAe_st_shshi());//市
				map.put("ae_st_shxian", ae_t_sysuser.getAe_st_shxian());//县/区
//				map.put("ae_nm_zjnum", ae_t_sysuser.getAe_nm_zjnum());//资金余额
				map.put("ae_st_nickName", ae_t_sysuser.getAe_st_nickName());//用户昵称
				list.add(new BizTransUtil(map,Ae_t_sysuser.class,CommonUtil.UPDATE));
				
				//用户表子表修改
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
					if (!labour.getBa_st_price().matches("\\d+")) {
						jsonData = "{success:false,msg:\"收费报价不为空并且只能输入数字!\"}";
					}
					else {
					sfmap.put("ba_st_price", labour.getBa_st_price());  
					System.out.println(labour.getBa_st_price());
					sfmap.put("ba_st_team_intro", labour.getBa_st_team_intro());
					list.add(new BizTransUtil(sfmap,Ba_t_Labour.class,CommonUtil.UPDATE));
			
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
			} else{
				jsonData = "{success:false,msg:\"操作异常!!!!\"}";
			}
			}
			}
			}
			}
		}  catch (Exception e) {
			jsonData = "{success:false,msg:\"操作异常，"+StringUtils.replaceBlank(e.getMessage())+"!\"}";
		}
		this.createAjax(response,jsonData);
	}
	
	/***
	 * 进入超级管理员修改密码界面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="initAdminPW")
	public String initAdminPW(){
		return "wyyf/info/initAdminPW";
	}
	/***
	 * 进入超级管理员账户信息界面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="initAdminUser")
	public String initAdminUser(Model model){
		Map<String, Object> map = baseBiz.queryForMap("select ae_st_id, DATE_FORMAT(ae_dt_lastlogontime,'%Y-%m-%d %h:%m:%s') ae_dt_lastlogontime ," +
				"ae_st_description,ae_st_lastlogonIp,ae_st_nickName from ae_t_sysuser where ae_st_id=?",new Object[]{getcuttSysuserID()});
		model.addAttribute("map", map);
		return "wyyf/info/adminManager";
	}
	/**
	 * 修改密码
	 * @param response
	 * @param model
	 * @param oldPsw
	 * @param newPsw
	 */
	@RequestMapping(value="updatePsw")
	public void updatePsw(HttpServletResponse response,Model model, String oldPsw,String newPsw){
		String json = "{'success':'false','msg':'修改失败!'}";
		try {
			Ae_t_sysuser sysuser = getcuttSysuser();
			String userName = sysuser.getUsername();
			String id = sysuser.getAe_st_id();
			//获取数据库真是密码
			String oldPassword = (String) baseBiz.queryForMap("select password from ae_t_sysuser where ae_st_id=?", new Object[]{id}).get("password");
			Encoder md5 = new MD5Encoder();
			oldPsw = md5.encrypt(oldPsw, userName);
			//输入的原密码为空/原密码错误
			if (!org.springframework.util.StringUtils.hasText(oldPsw) || (org.springframework.util.StringUtils.hasText(oldPsw) && !oldPassword.equals(oldPsw))) {
				json = "{'success':'false','msg':'修改失败,原密码错误!'}";
			} else if (org.springframework.util.StringUtils.hasText(newPsw)) {
				baseBiz.executeTRANS("update ae_t_sysuser set password=? where ae_st_id=?",new Object[] { md5.encrypt(newPsw, userName),id});
				json = "{'success':'true','msg':'修改成功!'}";
			}
		} catch (Exception e) {
		}
		this.createAjax(response, json);
	}
	
}
