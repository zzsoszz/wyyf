package com.wyyf.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lys.mvc.action.BaseAjaxAction;
import com.lys.utils.JsonUtils;
import com.lys.utils.StringUtils;
import com.power.bean.Ab_t_code;
import com.power.biz.CommonBiz;

/**
 * 自定义业务类型设置
 * @author Administrator
 *
 */
@Scope(value = "prototype")
@Controller("JYCodeAction")
@RequestMapping(value="/wyyf/code")
public class JYCodeAction extends BaseAjaxAction{
	@Resource(name="CommonBizCache")
	public CommonBiz commonBiz;//操作数据的对象biz
	/***
	 * 进入字典主界面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="initCode")
	public String initCode(Model model){
		StringBuffer sb=new StringBuffer("select a.ab_st_id TREEID,a.ab_nm_openorclose open,a.ab_st_parent TREEPID,a.ab_st_name TREENAME,a.ab_nm_jdnum,a.ab_st_sysmark from ab_t_code a where ab_st_isuserset='1' order by a.ab_nm_orderno asc");
		String json=JsonUtils.getJsonDataFromCollection(baseBiz.queryForList(sb.toString()));
		model.addAttribute("codeTree", json);
		return "wyyf/code/codeList";
	}
	/**
	 * 添加或修改字典--通过表单提交保存
	 * @param response
	 * @param sysfunction
	 */
	@RequestMapping(value="codeaddorupd")
	public void codeAddOrUpd(HttpServletResponse response,Ab_t_code code){
		String jsonData="";
		try {
			//验证
			if(!org.springframework.util.StringUtils.hasText(code.getAb_st_parent())){
				throw new RuntimeException("父节点不能为空！");
			}
			//查询父标识
			String parentMark=StringUtils.toStringByObject(baseBiz.queryForObject("select ab_st_mark from ab_t_code where ab_st_id=? ",new Object[]{code.getAb_st_parent()}, String.class),true);
			if(!org.springframework.util.StringUtils.hasText(parentMark)){
				throw new RuntimeException("父节点标识不存在，请重新打开页面！");
			}
			//修改
			if(org.springframework.util.StringUtils.hasText(code.getAb_st_id())){
				Map<String,Object>  map=new LinkedCaseInsensitiveMap<Object>();
				//如果是3级 就不更新标识
				if(code.getAb_nm_jdnum()!=3){
					map.put("ab_st_mark", parentMark+"_"+code.getAb_st_value());
				}
				map.put("ab_st_value", code.getAb_st_value());
				map.put("ab_st_name", code.getAb_st_name());
				map.put("ab_st_type", code.getAb_st_type());
				map.put("ab_nm_openorclose", code.getAb_nm_openorclose());
				map.put("ab_nm_orderno", code.getAb_nm_orderno());
				map.put("ab_st_describe", code.getAb_st_describe());
				map.put("ab_st_sysmark", code.getAb_st_sysmark());
				map.put("ab_nm_jdnum", code.getAb_nm_jdnum());
				map.put("ab_st_updUserId", getcuttSysuserID());//修改人ID
				map.put("ab_dt_updDate", getcuttDate());//修改时间
				map.put("ab_st_id", code.getAb_st_id()); //ID
				Integer c=baseBiz.updateTRANS(map, Ab_t_code.class);
				if(c==1){
					jsonData = "{success:true,msg:\"修改成功!\",result:["+JsonUtils.getJsonData(code)+"]}";
				}else {
					jsonData = "{success:false,msg:\"修改失败!\"}";
				}
			}
			//新增
			else{
				code.setAb_st_isuserset("1");//默认允许用户设置
				code.setAb_st_id(StringUtils.getUUID32());
				code.setAb_st_addUserId(getcuttSysuserID());
				code.setAb_dt_addDate(getcuttDate());
				code.setAb_st_mark(parentMark+"_"+code.getAb_st_value());
				//执行保存
				if(baseBiz.addTRANS(code)){
					jsonData = "{success:true,msg:\"添加成功!\",result:["+JsonUtils.getJsonData(code)+"]}";
				}else{
					jsonData = "{success:false,msg:\"添加失败!\"}";
				}
			}
			//清除缓存
			commonBiz.delALLcode();
			
		} catch (Exception e) {
			jsonData = "{success:false,msg:\"保存异常，请检查是否存在相同码值!\"}";
			e.printStackTrace();
		}
		this.createAjax(response,jsonData);
	}
	
	/**
	 * 查询字典--根据id
	 * @param response
	 * @param sysfunction
	 */
	@RequestMapping(value="findCodeById")
	public void findCodeById(HttpServletResponse response,Ab_t_code code){
		if(code!=null&&org.springframework.util.StringUtils.hasText(code.getAb_st_id())){
			StringBuffer sb=new StringBuffer(" select a.ab_st_id, a.ab_st_describe, a.ab_st_mark, a.ab_st_name, a.ab_nm_openorclose, a.ab_nm_orderno,a.ab_nm_jdnum,a.ab_st_sysmark, ")
							.append(" a.ab_st_parent,a.ab_st_type,(select ab_st_name from ab_t_code where  ab_st_id=a.ab_st_parent) ab_st_parentName,a.ab_st_value")
							.append(" from  ab_t_code a  ");
			sb.append(" where a.ab_st_id=? ");
			Map<String, Object>  map=baseBiz.queryForMap(sb.toString(), new Object[]{code.getAb_st_id()});
			String jsonData = "{\"success\":true,result:["+JsonUtils.getJsonData(map)+"]}";
			this.createAjax(response, jsonData);
		}
	}
	
	/**
	 * 删除字典
	 * @param response
	 * @param sysfunction
	 */
	@RequestMapping(value="codeDelete")
	public void codeDelete(HttpServletResponse response, String codeid, String parentid) {
		String jsonData = "";
		try {
			if (codeid != null) {
				if ("null".equals(parentid) || "0".equals(parentid) || !org.springframework.util.StringUtils.hasText(parentid)) {
					jsonData = "{success:false,msg:\"删除失败,不能删除根节点!\"}";
				} else {
					if (baseBiz.deleteTRANS(StringUtils.getArrayByArray(codeid.split(",")), Ab_t_code.class)) {
						// 清除缓存
						commonBiz.delALLcode();
						jsonData = "{success:true,msg:\"删除成功!\"}";
					} else {
						jsonData = "{success:false,msg:\"删除失败!\"}";
					}
				}
			} else {
				jsonData = "{success:false,msg:\"删除失败!\"}";
			}
		} catch (Exception e) {
			jsonData = "{success:false,msg:\"删除异常，" + e.getMessage() + "!\"}";
			e.printStackTrace();
		}
		this.createAjax(response, jsonData);
	}

}
