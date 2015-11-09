package com.wyyf.action;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lys.mvc.action.BaseAjaxAction;
import com.lys.utils.BizTransUtil;
import com.lys.utils.StringUtils;
import com.lys.utils.UploadFile;
import com.power.bean.Ag_t_file;

/***
 * 公共方法类
 * @author Administrator
 *
 */
@Scope(value = "prototype")
@Controller("CommonAction")
@RequestMapping(value="/wyyf/common")
public class CommonAction extends BaseAjaxAction{
	/***
	 * 查询ag表中的数据。 
	 * @return
	 */
	@RequestMapping(value="findfile")
	public String findfile(){
		String objid=request.getParameter("id");
		if(org.springframework.util.StringUtils.hasText(objid)){
			List<Map<String,Object>> list=baseBiz.queryForList("select * from ag_t_file where ag_st_objid=? and ag_st_mark!='JSRUEST' ",new Object[]{objid});
			model.addAttribute("workExps",list); 
		}
		model.addAttribute("worklname", request.getParameter("worklname")); 
		model.addAttribute("wjlx", request.getParameter("wjlx"));
		return "wyyf/common/imageSet";
	}
	/***
	 * 查询ag表中的数据。 
	 * @return
	 */
	@RequestMapping(value="findfile2")
	public String findfile2(){
		String objid=request.getParameter("id");
		if(org.springframework.util.StringUtils.hasText(objid)){
			List<Map<String,Object>> list=baseBiz.queryForList("select * from ag_t_file where ag_st_objid=? and ag_st_mark='JSRUEST'",new Object[]{objid});
			model.addAttribute("workExps",list); 
		}
		model.addAttribute("worklname", request.getParameter("worklname")); 
		//model.addAttribute("wjlx", request.getParameter("wjlx"));
		return "wyyf/common/imageSet2";
	}
	/***
	 * 查询ag表中的数据。 
	 * @return
	 */
	@RequestMapping(value="registerFile")
	public String registerFile(){
		String objid=request.getParameter("id");
		if(org.springframework.util.StringUtils.hasText(objid)){
			List<Map<String,Object>> list=baseBiz.queryForList("select * from ag_t_file where ag_st_objid=?",new Object[]{objid});
			model.addAttribute("workExps",list); 
		}
		model.addAttribute("worklname", request.getParameter("worklname")); 
		model.addAttribute("wjlx", request.getParameter("wjlx"));
		return "wyyf/common/registerFileSet";
	}
	/**
	 * 删除AG_T_FILE中的数据  
	 * @return
	 */
	@RequestMapping(value="delfile")
	public void delfile(String id){
		String jsonData="";
		try {
			if(org.springframework.util.StringUtils.hasText(id)){
				List<BizTransUtil> list=new ArrayList<BizTransUtil>();
				List<String> dellist=new ArrayList<String>();
				//查询是否有 文件
				List<Map<String,Object>> filelist= baseBiz.queryForList("select a.ag_st_url,a.ag_st_id from  ag_t_file a  where a.ag_st_id=?  ", new Object[]{id});
				if(filelist!=null&&filelist.size()>0){
					for(Map<String,Object> map:filelist){
						list.add(new BizTransUtil(map.get("ag_st_id"), Ag_t_file.class));//删除本条数据
						dellist.add(StringUtils.toStringByObject(map.get("ag_st_url")));
					}
				}
				if(baseBiz.executesTRANS(list)){
					if(dellist.size()>0){
						for(String s:dellist){
							if(org.springframework.util.StringUtils.hasText(s)){
								UploadFile.delpic(s);//删除原文件
							}
						}
					}
					jsonData = "{success:true,msg:\"删除成功!\"}";
				}else{
					jsonData = "{success:false,msg:\"删除失败!\"}";
				}
			}else{
				jsonData = "{success:false,msg:\"删除失败!\"}";
			}
		} catch (Exception e) {
			jsonData = "{success:false,msg:\"删除异常，"+e.getMessage()+"!\"}";
		}
		this.createAjax(response, jsonData);
	}
}
