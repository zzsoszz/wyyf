package com.wyyf.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lys.mvc.action.BaseAjaxAction;
import com.lys.utils.BizTransUtil;
import com.lys.utils.CommonUtil;
import com.lys.utils.JsonUtils;
import com.lys.utils.StringUtils;
import com.lys.utils.pagination.PageBean;
import com.power.utils.PageColumn;
import com.wyyf.bean.Bl_t_noticecontext;

/***
 * 文章管理--帮助中心
 * @author xt
 *
 */
@Scope(value = "prototype")
@Controller("NoticeContextAction")
@RequestMapping(value="/wyyf/notice")
public class NoticeContextAction extends BaseAjaxAction{
	/***
	 * 进入帮助中心管理主界面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="initNotice")
	public String initNotice(Model model){
		return "wyyf/notice/noticeList";
	}

	/***
	 * 查询帮助中心列表
	 * @param model
	 * @param ISSHOWCOLUMS
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="noticeList")
	public String noticeList(Model model,Bl_t_noticecontext noticeContext,String ISSHOWCOLUMS,Integer pageIndex,Integer pageSize){
		StringBuffer sb=new StringBuffer("select bl_st_id,date_format( bl_dt_addDate,'%Y-%m-%d %T') bl_dt_addDate,bl_st_isSend,bl_st_title,bl_st_type "); 
		StringBuffer sbcount=new StringBuffer(" select count(bl_st_id) ");
		StringBuffer sbcommon=new StringBuffer(" from bl_t_noticecontext where 1=1 ");
		List<String> params=new ArrayList<String>();
		//标题
		if(org.springframework.util.StringUtils.hasText(noticeContext.getBl_st_title())){
			sbcommon.append(" and bl_st_title like ? ");
			params.add("%"+noticeContext.getBl_st_title()+"%");
		}
		//类型
		if(org.springframework.util.StringUtils.hasText(noticeContext.getBl_st_type())){
			sbcommon.append(" and bl_st_type = ? ");
			params.add(noticeContext.getBl_st_type());
		}
		//是否发布
		if(org.springframework.util.StringUtils.hasText(noticeContext.getBl_st_isSend())){
			sbcommon.append(" and bl_st_isSend = ? ");
			params.add(noticeContext.getBl_st_isSend());
		}
		sbcount.append(sbcommon);
		sb.append(sbcommon).append(" order by bl_st_type ");
		//分页数据、分页配置 查询
		PageBean pages=baseBiz.getPages(sb.toString(),sbcount.toString(),pageIndex, pageSize,params.toArray());
		//列设置
		List<PageColumn> columnList=new ArrayList<PageColumn>();
		//columnList.add(new PageColumn(null, "bl_st_id").setIscheckbox(true).setWidth("30"));//复选框
		//columnList.add(new PageColumn("序号", "bl_st_id").setIsnum(true).setWidth("30"));//序号
		//columnList.add(new PageColumn("ID", "bl_st_id").setHiddle(true));
		columnList.add(new PageColumn("序号", "bl_st_id").setIsnum(true).setWidth("30"));//序号
		columnList.add(new PageColumn("标题", "bl_st_title").setLength(10));
		//columnList.add(new PageColumn("内容摘要", "bl_st_summary").setLength(30));
		columnList.add(new PageColumn("类型", "bl_st_type").setCode("HELPLX").setIsshowcode(true));
		columnList.add(new PageColumn("是否发布", "bl_st_isSend").setCode("YESNO").setIsshowcode(true));
		columnList.add(new PageColumn("创建时间", "bl_dt_addDate"));
		//columnList.add(new PageColumn("是否删除", "bl_st_isDel").setCode("YESNO").setIsshowcode(true));
		//操作按钮
		StringBuffer btns=new StringBuffer("<button data-toggle='modal' onclick='showEdit(this);' class='btn btn-sm blue' ><i class='fa fa-pencil-square-o'></i>编辑</button>");
		btns.append("<button data-toggle='modal' onclick='del(this);' class='btn btn-sm red' ><i class='fa fa-trash-o'></i>删除</button>");
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
	/***
	 * 添加/修改 帮助中心
	 * @param response
	 * @param user
	 */
	@RequestMapping(value = "addNotice")
	public void addNotice(HttpServletResponse response, Bl_t_noticecontext noticeContext) {
		String jsonData="";
		try {
			//帮助中心标题不能空
			if(org.springframework.util.StringUtils.hasText(noticeContext.getBl_st_title())){	
				//问题类型不能为空
				if(!org.springframework.util.StringUtils.hasText(noticeContext.getBl_st_type())){
					throw new RuntimeException("类型不能为空!");
				}
				//创建批量保存事务对象
				List<BizTransUtil> list=new ArrayList<BizTransUtil>();
				//如果要发布，则相同类型的问题只能存在一条发布内容
//				if("1".equals(noticeContext.getBl_st_isSend())){
//					list.add(new BizTransUtil("update bl_t_noticecontext set bl_st_isSend='0' where bl_st_type=?", new Object[]{noticeContext.getBl_st_type()}, CommonUtil.UPDATE));
//				}
				//更新
				if (org.springframework.util.StringUtils.hasText(noticeContext.getBl_st_id())) {
					//构建数据--修改
					Map<String,Object>  map=new LinkedCaseInsensitiveMap<Object>();
					map.put("bl_st_id", noticeContext.getBl_st_id());//ID
					map.put("bl_st_title", noticeContext.getBl_st_title());//标题
					map.put("bl_st_summary", noticeContext.getBl_st_summary());//摘要
					map.put("bl_st_context", noticeContext.getBl_st_context());//内容
					map.put("bl_st_isSend", noticeContext.getBl_st_isSend());//是否发布
					map.put("bl_st_type", noticeContext.getBl_st_type());//类型：1.联系客服2.挑选商品3.订单问题4.物流问题5.售后问题
					map.put("bl_dt_updDate",getcuttDate());
					map.put("bl_st_updUserId",  getcuttSysuserID());
					list.add(new BizTransUtil(map,Bl_t_noticecontext.class,CommonUtil.UPDATE));
				}
				//新增
				else {
					//构建数据--新增
					//内容表
					noticeContext.setBl_st_id(StringUtils.getUUID32());//ID
					noticeContext.setBl_st_addUserId(getcuttSysuserID());//当前人
					noticeContext.setBl_dt_addDate(getcuttDate());//当前时间
					list.add(new BizTransUtil(noticeContext));
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
	
	/***
	 * 查询单个帮助中心信息
	 * @param model
	 * @param ISSHOWCOLUMS
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="findNotice")
	public void findNotice(HttpServletResponse response,String id){
		if(org.springframework.util.StringUtils.hasText(id)){
			StringBuffer sb=new StringBuffer("select bl_st_id,bl_st_isSend,bl_st_title,bl_st_type,bl_st_summary,bl_st_context from bl_t_noticecontext ");
			sb.append(" where bl_st_id =? ");
			Map<String, Object> map=baseBiz.queryForMap(sb.toString(), new Object[]{id});
			String jsonData = "{\"success\":true,result:["+JsonUtils.getJsonData(map)+"]}";
			this.createAjax(response, jsonData);
		}
	}
	
	/** 
	/***
	 * 删除单条帮助中心
	 * @param model
	 * @param id
	 * @return
	 */
	
	@RequestMapping(value="delNotice")
	public void delNotice(HttpServletResponse response,String id){
		String jsonData = "";// 返回的json数据
		try {
			if(org.springframework.util.StringUtils.hasText(id)){
				List<BizTransUtil> list = new ArrayList<BizTransUtil>();
				//修改内容对应的所有关系表中的发送方删除状态
				list.add(new BizTransUtil(id, Bl_t_noticecontext.class) );
				//执行操作
				if (baseBiz.executesTRANS(list)) {
					jsonData = "{success:true,msg:\"删除成功!\"}";
				} else {
					jsonData = "{success:false,msg:\"删除失败，操作异常!\"}";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonData = "{success:false,msg:\"删除失败，操作异常!\"}";
		}
		this.createAjax(response,jsonData);
	}
}