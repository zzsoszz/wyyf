package com.power.action;

import java.util.ArrayList;
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
import com.power.bean.Ai_t_sysset;
import com.power.utils.PageColumn;

@Scope(value = "prototype")
@Controller("SyssetAction")
@RequestMapping(value="/system/sysset")
public class SyssetAction extends BaseAjaxAction{
	/***
	 * 进入系统设置主界面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="initSysset")
	public String initSysset( ){
		return "system/sysset/syssetList";
	}
	/**
	 * 查询系统设置列表——分页---此处每次都要进行列设置， 这种方式需要在 1.7进行改进
	 * @param response
	 * @param sysfunction
	 */
	@RequestMapping(value="syssetList")
	public String syssetFind( Ai_t_sysset ai_t_sysset,String ISSHOWCOLUMS,Integer pageIndex,Integer pageSize){
		StringBuffer sb=new StringBuffer("select a.ai_st_id,"+StringUtils.trunTypeByString("a.ai_dt_addDate")+" ai_dt_addDate ,(select ae_st_nickName from ae_t_sysuser where ae_st_id=a.ai_st_addUserId) ae_st_nickName ,a.ai_st_isEnable,a.ai_st_type  "); 
		StringBuffer sbcount=new StringBuffer("select count(a.ai_st_id) ");
		StringBuffer sbcommon=new StringBuffer(" from ai_t_sysset a where 1=1 ");
		List<String> params=new ArrayList<String>();
		//类型
		if(org.springframework.util.StringUtils.hasText(ai_t_sysset.getAi_st_type())){
			sbcommon.append(" and a.ai_st_type like ? ");
			params.add(ai_t_sysset.getAi_st_type()+"%");
		}
		//是否启用
		if(org.springframework.util.StringUtils.hasText(ai_t_sysset.getAi_st_isEnable())){
			sbcommon.append(" and a.ai_st_isEnable = ? ");
			params.add(ai_t_sysset.getAi_st_isEnable());
		}
		sbcount.append(sbcommon);
		sb.append(sbcommon).append(" order by a.ai_dt_addDate desc ");
		//分页数据、分页配置 查询
		PageBean pages=baseBiz.getPages(sb.toString(),sbcount.toString(),pageIndex, pageSize,params.toArray());
		//列设置
		List<PageColumn> columnList=new ArrayList<PageColumn>();
		columnList.add(new PageColumn("复选框", "ai_st_id").setIscheckbox(true).setWidth("30"));//复选框
		//columnList.add(new PageColumn("ID", "ai_st_id").setHiddle(true));
		columnList.add(new PageColumn("类型", "ai_st_type"));
		columnList.add(new PageColumn("是否启用", "ai_st_isEnable").setCode("YESNO").setIsshowcode(true));
		columnList.add(new PageColumn("创建人", "ae_st_nickName"));
		columnList.add(new PageColumn("创建时间", "ai_dt_addDate"));
		//操作按钮
		StringBuffer btns=new StringBuffer("<button data-toggle='modal' onclick='editSet(this);' class='btn btn-sm blue' ><i class='fa fa-pencil-square-o'></i>编辑</button> ");
		btns.append("<button data-toggle='modal' onclick='delSet(this);' class='btn btn-sm red' ><i class='fa fa-trash-o'></i>删除</button> ");
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
	 * 新增或更新系统设置
	 * @param response
	 * @param sysfunction
	 */
	@RequestMapping(value="syssetAdd")
	public void syssetAdd( Ai_t_sysset ai_t_sysset,String powerids){
		String jsonData="";
		try {
			if(ai_t_sysset!=null){			
				//创建批量保存事务对象
				List<BizTransUtil> list=new ArrayList<BizTransUtil>();
				//更新
				if (org.springframework.util.StringUtils.hasText(ai_t_sysset.getAi_st_id())) {
					//构建数据--修改
					Map<String,Object>  map=new LinkedCaseInsensitiveMap<Object>();
					map.put("ai_st_id", ai_t_sysset.getAi_st_id());//ID
					map.put("ai_st_type", ai_t_sysset.getAi_st_type());
					map.put("ai_st_isEnable", ai_t_sysset.getAi_st_isEnable());
					map.put("ai_st_content", ai_t_sysset.getAi_st_content());
					map.put("ai_st_remark",ai_t_sysset.getAi_st_remark());
					map.put("ai_dt_updDate",getcuttDate());
					map.put("ai_st_updUserId",  getcuttSysuserID());
					list.add(new BizTransUtil(map,Ai_t_sysset.class,CommonUtil.UPDATE));
				}
				//新增
				else {
					//构建数据--新增
					ai_t_sysset.setAi_st_id(StringUtils.getUUID32());
					ai_t_sysset.setAi_st_addUserId(getcuttSysuserID());
					ai_t_sysset.setAi_dt_addDate(getcuttDate());
					list.add(new BizTransUtil(ai_t_sysset));
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
	 * 删除系统设置
	 * @param response
	 * @param sysfunction
	 */
	@RequestMapping(value="syssetDelete")
	public void syssetDelete( String deleteData){
		String jsonData="";
		try {
			if(org.springframework.util.StringUtils.hasText(deleteData)){
				//创建批量保存事务对象
				List<BizTransUtil> list=new ArrayList<BizTransUtil>();
				list.add(new BizTransUtil(deleteData, Ai_t_sysset.class));
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
	 * 查询单个系统设置信息
	 * @param response
	 * @param setid
	 */
	@RequestMapping(value="findSetById")
	public void findSetById( String setid){
		if(org.springframework.util.StringUtils.hasText(setid)){
			StringBuffer sb=new StringBuffer(" select a.ai_st_id as \"ai_st_id\",a.ai_st_content as \"ai_st_content\",a.ai_st_isEnable as \"ai_st_isEnable\",a.ai_st_type as \"ai_st_type\",a.ai_st_remark as \"ai_st_remark\" from  ai_t_sysset a  ")
			.append(" where a.ai_st_id=? ");
			Map<String, Object> map=baseBiz.queryForMap(sb.toString(), new Object[]{setid});
			String jsonData = "{\"success\":true,result:["+JsonUtils.getJsonData(map)+"]}";
			this.createAjax(response, jsonData);
		}
	}
	
}



