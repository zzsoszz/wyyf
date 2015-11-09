package com.power.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lys.mvc.action.BaseAjaxAction;
import com.lys.system.filter.SafetyFilter;
import com.lys.utils.BizTransUtil;
import com.lys.utils.CommonUtil;
import com.lys.utils.JsonUtils;
import com.lys.utils.StringUtils;
import com.power.bean.Aa_t_sysfunction;
import com.power.biz.CommonBiz;

@Scope(value = "prototype")
@Controller("SysfunctionAction")
@RequestMapping(value="/system/sysfunction")
public class SysfunctionAction extends BaseAjaxAction{
	@Resource(name="CommonBizCache")
	public CommonBiz commonBiz;//操作数据的对象biz
	
	/***
	 * 进入权限主界面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="initSysfunction")
	public String initSysfunction(){
		StringBuffer sb=new StringBuffer("select a.aa_st_id TREEID,a.aa_nm_openorclose  as \"open\",a.aa_st_parent TREEPID,a.aa_st_title TREENAME,a.aa_nm_jdnum  as \"aa_nm_jdnum\",a.aa_st_sysmark as \"aa_st_sysmark\" from  aa_t_sysfunction a order by a.aa_nm_orderno asc");
		String json=JsonUtils.getJsonDataFromCollection(baseBiz.queryForList(sb.toString()));
		model.addAttribute("functionTree", json);
		return "system/sysfunction/sysfunctionList";
	}
	
	/**
	 * 添加权限
	 * @param response
	 * @param aa_t_sysfunction
	 */
	@RequestMapping(value="sysfunctionAddorupd")
	public void sysfunctionAddorupd(Aa_t_sysfunction aa_t_sysfunction){
		String jsonData="";
		try {
			//验证
			if(!org.springframework.util.StringUtils.hasText(aa_t_sysfunction.getAa_st_parent())){
				throw new RuntimeException("父节点不能为空！");
			}
			//修改
			if(org.springframework.util.StringUtils.hasText(aa_t_sysfunction.getAa_st_id())){
				Map<String,Object>  map=new LinkedCaseInsensitiveMap<Object>();
				map.put("aa_st_mark", aa_t_sysfunction.getAa_st_mark());
				map.put("aa_st_title", aa_t_sysfunction.getAa_st_title());
				map.put("aa_st_type", aa_t_sysfunction.getAa_st_type());
				map.put("aa_st_url", aa_t_sysfunction.getAa_st_url());
				map.put("aa_st_classUrl", aa_t_sysfunction.getAa_st_classUrl());
				map.put("aa_st_active", aa_t_sysfunction.getAa_st_active());
				map.put("aa_nm_openorclose", aa_t_sysfunction.getAa_nm_openorclose());
				map.put("aa_nm_orderno", aa_t_sysfunction.getAa_nm_orderno());
				map.put("aa_st_describe", aa_t_sysfunction.getAa_st_describe());
				map.put("aa_st_image", aa_t_sysfunction.getAa_st_image());
				map.put("aa_st_sysmark", aa_t_sysfunction.getAa_st_sysmark());
				map.put("aa_nm_jdnum", aa_t_sysfunction.getAa_nm_jdnum());
				map.put("aa_st_updUserId", getcuttSysuserID());//修改人ID
				map.put("aa_dt_updDate", getcuttDate());//修改时间
				map.put("aa_st_id", aa_t_sysfunction.getAa_st_id()); //ID
				map.put("aa_st_islog", aa_t_sysfunction.getAa_st_islog()); //日志记录
				Integer c=baseBiz.updateTRANS(map, Aa_t_sysfunction.class);
				if(c==1){
					jsonData = "{success:true,msg:\"修改成功!\",result:["+JsonUtils.getJsonData(aa_t_sysfunction)+"]}";
					commonBiz.delALLfunction();
				}else {
					jsonData = "{success:false,msg:\"修改失败!\"}";
				}
			}
			//新增
			else{
				//数据构造
				aa_t_sysfunction.setAa_st_id(StringUtils.getUUID32());
				aa_t_sysfunction.setAa_dt_addDate(getcuttDate());
				aa_t_sysfunction.setAa_st_addUserId(getcuttSysuserID());
				//执行保存
				if(baseBiz.addTRANS(aa_t_sysfunction)){
					//如果权限启用，则加入权限控制范围
					if("1".equals(aa_t_sysfunction.getAa_st_active())){
						if(commonBiz.addALLfunction(aa_t_sysfunction.getAa_st_classUrl(), aa_t_sysfunction.getAa_st_mark())){
							SafetyFilter.logger.info("权限集合更新:添加了一个权限 类路径="+aa_t_sysfunction.getAa_st_classUrl()+"  唯一标识="+aa_t_sysfunction.getAa_st_mark());
						}
					}
					jsonData = "{success:true,msg:\"添加成功!\",result:["+JsonUtils.getJsonData(aa_t_sysfunction)+"]}";
				}else{
					jsonData = "{success:false,msg:\"添加失败!\"}";
				}
			}
		} catch (Exception e) {
			jsonData = "{success:false,msg:\"保存异常，"+e.getMessage()+"!\"}";
			e.printStackTrace();
		}
		this.createAjax(response,jsonData);
	}
	
	/**
	 * 查询权限
	 * @param response
	 * @param aa_t_sysfunction
	 */
	@RequestMapping(value="sysfunctionFind")
	public void sysfunctionFind(Aa_t_sysfunction aa_t_sysfunction){
		if(aa_t_sysfunction!=null&&org.springframework.util.StringUtils.hasText(aa_t_sysfunction.getAa_st_id())){
			StringBuffer sb=new StringBuffer(" select a.aa_st_id as \"aa_st_id\",a.aa_st_active as \"aa_st_active\",a.aa_dt_addDate as \"aa_dt_addDate\",a.aa_st_addUserId as \"aa_st_addUserId\""
					+ ",a.aa_st_describe as \"aa_st_describe\",a.aa_st_mark as \"aa_st_mark\",a.aa_nm_openorclose as \"aa_nm_openorclose\",a.aa_nm_orderno as \"aa_nm_orderno\",a.aa_st_image as \"aa_st_image\""
					+ ",a.aa_st_parent as \"aa_st_parent\",a.aa_st_title as \"aa_st_title\",a.aa_st_type as \"aa_st_type\",(select aa_st_title from aa_t_sysfunction where  aa_st_id=a.aa_st_parent)  as \"aa_st_parentName\""
					+ ",a.aa_st_url as \"aa_st_url\",a.aa_st_classUrl as \"aa_st_classUrl\",a.aa_nm_jdnum as \"aa_nm_jdnum\",a.aa_st_sysmark as \"aa_st_sysmark\",a.aa_st_islog as \"aa_st_islog\" from  aa_t_sysfunction a ");
			sb.append(" where a.aa_st_id=? ");
			Map<String, Object> map=baseBiz.queryForMap(sb.toString(), new Object[]{aa_t_sysfunction.getAa_st_id()});
			String jsonData = "{\"success\":true,result:["+JsonUtils.getJsonData(map)+"]}";
			this.createAjax(response, jsonData);
		}
	}
	/**
	 * 删除权限
	 * @param response
	 * @param sysfunction
	 */
	@RequestMapping(value="sysfunctionDelete")
	public void sysfunctionDelete(String functionid,String parentid){
		String jsonData="";
		try {
			if(functionid!=null){
				if("null".equals(parentid)||"0".equals(parentid)||!org.springframework.util.StringUtils.hasText(parentid)){
					jsonData = "{success:false,msg:\"删除失败,不能删除根节点!\"}";
				}else{
					String[] ss=functionid.split(",");
					//批量执行的--事务执行--准备数据
					 List<BizTransUtil> transList=new ArrayList<BizTransUtil>();
					 transList.add(new BizTransUtil(StringUtils.getArrayByArray(ss), Aa_t_sysfunction.class));//删除权限
					 transList.add(new BizTransUtil("delete from ad_t_sysrolefunction where aa_st_id in("+StringUtils.toStringBySqlIn(ss)+")", new Object[]{}, CommonUtil.DELETE));//删除权限角色中间表对应数据
					 if(baseBiz.executesTRANS(transList)){
						 commonBiz.delALLfunction();
						 jsonData = "{success:true,msg:\"删除成功!\"}";
					 }else{
						 jsonData = "{success:false,msg:\"删除失败!\"}";
					 }
					jsonData = "{success:true,msg:\"删除成功!\"}";
				}
			}else{
				jsonData = "{success:false,msg:\"删除失败!\"}";
			}
		}catch (Exception e) {
			jsonData = "{success:false,msg:\"删除异常，"+e.getMessage()+"!\"}";
			e.printStackTrace();
		}
		this.createAjax(response, jsonData);
	}
}
