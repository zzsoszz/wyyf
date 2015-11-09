package com.power.action;

import java.util.List;
import java.util.Map;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lys.mvc.action.BaseAjaxAction;
import com.power.bean.Ae_t_sysuser;

@Scope(value = "prototype")
@Controller("AdminAction")
@RequestMapping(value="/system/admin")
public class AdminAction extends BaseAjaxAction{
	
	/***
	 * 进入登录界面
	 * @param req
	 * @return
	 */
	@RequestMapping
	public String initLogin(){
//		return "system/admin/login2";//不一样的登录界面
		return "system/admin/login";//
	}
	
	/***
	 * 进入主页面 W8
	 * @param model
	 * @return
	 */
	@RequestMapping(value="initIndex")
	public String initIndex(){
		Ae_t_sysuser ae_t_sysuser=getcuttSysuser();
		model.addAttribute("username", ae_t_sysuser.getUsername());
		return "system/admin/index";
	}
	
	
	
	/**
	 * 进入后台主界面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="initMain")
	public String initMain(String functionid1){
		Ae_t_sysuser ae_t_sysuser=getcuttSysuser();
		model.addAttribute("username", ae_t_sysuser.getUsername());
		StringBuffer sb=new StringBuffer("select a.aa_st_id,a.aa_st_parent,a.aa_st_title,a.aa_nm_orderno,a.aa_st_url,a.aa_st_image,a.aa_st_mark,a.aa_st_active from aa_t_sysfunction a where 1=1");
		//限制当前登录人只能看见自己拥有的权限
		sb.append(" and (a.aa_st_id in (select distinct c.aa_st_id from af_t_sysuserrole b ,ad_t_sysrolefunction c where b.ac_st_id=c.ac_st_id and b.ae_st_id='"+ae_t_sysuser.getAe_st_id()+"') ");
		sb.append(" or a.aa_st_id in ( select aa_st_id from aa_t_sysfunction where aa_st_active='0' ) ) ");
		
		StringBuffer sb1=new StringBuffer(sb).append(" and a.aa_st_type='1' order by a.aa_nm_orderno asc ");
		List<Map<String, Object>> list1=baseBiz.queryForList(sb1.toString());//1级菜单
		if(list1!=null&&list1.size()>0){
			model.addAttribute("CDLIST1", list1);//将1级菜单放入request
			if(!org.springframework.util.StringUtils.hasText(functionid1)){
				//默认查询当前登录人拥有的权限中的第一个权限ID
				StringBuffer sbYI=new StringBuffer("select d.aa_st_id from aa_t_sysfunction d ");
				sbYI.append(" where 1=1 ")
					.append("   and aa_st_id in ( select distinct c.aa_st_id from af_t_sysuserrole b ,ad_t_sysrolefunction c where b.ac_st_id=c.ac_st_id  and b.ae_st_id=? ")
					.append(" 	union  ")
					.append(" 	select  aa_st_id   from aa_t_sysfunction  where aa_st_active='0' and  aa_nm_jdnum=2 )  ")
					.append(" 	and  d.aa_nm_jdnum=2 ")
					.append(" 	order by d.aa_nm_orderno ");
				List<Map<String, Object>> functions=baseBiz.queryForList(sbYI.toString(),new Object[]{ae_t_sysuser.getAe_st_id()});
				functionid1=(String) (functions.get(0).get("aa_st_id"));//1级菜单选中标识
			}
			model.addAttribute("functionid1", functionid1);
			
			StringBuffer sb2=new StringBuffer(sb).append(" and a.aa_st_type='2' and a.aa_st_parent=? order by a.aa_nm_orderno asc ");
			List<Map<String, Object>> list2=baseBiz.queryForList(sb2.toString(),new Object[]{functionid1});//2级菜单
			if(list2!=null&&list2.size()>0){
				String functionid2=(String) list2.get(0).get("aa_st_id");//2级菜单选中标识
				model.addAttribute("functionid2", functionid2);
				
				StringBuffer sb3=new StringBuffer(sb).append(" and a.aa_st_type='3' and a.aa_st_parent=? order by a.aa_nm_orderno asc ");
				for(int i=0,b=list2.size();i<b;i++){
					Map<String, Object> map=list2.get(i);
					List<Map<String, Object>> list3=baseBiz.queryForList(sb3.toString(),new Object[]{map.get("aa_st_id")});
					if(i==0&&list3!=null&&list3.size()>0){
						String functionid3=(String)list3.get(0).get("aa_st_id");
						String functionid3Url=(String)list3.get(0).get("aa_st_url");
						model.addAttribute("functionid3", functionid3);
						model.addAttribute("functionid3Url", functionid3Url);//默认显示第一个页面
					}
					map.put("childrenList", list3);
					list2.get(i).putAll(map);
				}
				model.addAttribute("CDLIST2", list2);//将2级和3级菜单放入request
			}
		}
		return "system/admin/main";
	}
	

}
