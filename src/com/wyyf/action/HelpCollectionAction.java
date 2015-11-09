package com.wyyf.action;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lys.mvc.action.BaseAjaxAction;
import com.lys.utils.BizTransUtil;
import com.lys.utils.CommonUtil;
import com.lys.utils.JsonUtils;
import com.lys.utils.pagination.PageBean;
import com.power.utils.PageColumn;

/**
 * 意见查看管理
 * @author shuang
 *
 */
@Scope(value = "prototype")
@Controller("HelpCollectionAction")
@RequestMapping(value = "/wyyf/helpCollection")
public class HelpCollectionAction extends BaseAjaxAction {

	/**
	 * 需求收集初始化
	 * @return
	 */
	@RequestMapping(value = "initHelplist")
	public String initHelplist() {
		return "wyyf/helpcollection/helplist";
	}

	/**
	 * 按标题查询并分页
	 * @param model
	 * @param content
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "sethelp")
	public String setInitWxuser(Model model, String title, Integer pageIndex, Integer pageSize) {
		StringBuffer sb = new StringBuffer("select bk_st_id,bk_st_type,bk_st_title,bk_st_content,bk_st_stutas,bk_st_phone,bk_st_source,")
		.append("date_format(bk_dt_addDate,'%Y-%m-%d %h:%m:%s')bk_dt_addDate from bk_t_helpcollect where bk_st_isdel!='1'");
		StringBuffer s = new StringBuffer("select count(1) from bk_t_helpcollect where bk_st_isdel='0'");
		PageBean page;
		List<String> pramsList = new ArrayList<String>();
		//根据标题查找
		if (org.springframework.util.StringUtils.hasText(title)) {
			sb.append(" and bk_st_title like ?"); 	
			s.append(" and bk_st_title like ?");
			pramsList.add("%" + title + "%");
		}
		sb.append("  order by bk_dt_addDate DESC");
		page = baseBiz.getPages(sb.toString(), s.toString(), pageIndex,
				pageSize, pramsList.toArray(new String[]{}));
		List<PageColumn> columnList = new ArrayList<PageColumn>();
		//结果集放入list中
		columnList.add(new PageColumn("序号", "bk_st_id").setIsnum(true).setWidth("30"));//序号
		//columnList.add(new PageColumn("ID", "bk_st_id").setHiddle(true));
		columnList.add(new PageColumn("标题", "bk_st_title"));
		columnList.add(new PageColumn("内容", "bk_st_content").setLength(10));
		columnList.add(new PageColumn("提交时间", "bk_dt_addDate"));
		columnList.add(new PageColumn("来源", "bk_st_source").setCode("YJLYLX").setIsshowcode(true));
		columnList.add(new PageColumn("联系电话", "bk_st_phone"));
		columnList.add(new PageColumn("类型", "bk_st_type").setCode("YJLX").setIsshowcode(true));
		//columnList.add(new PageColumn("是否打开", "bk_st_stutas"));
		//columnList.add(new PageColumn("备注", "bk_st_remark"));
		StringBuffer btns = new StringBuffer("<button data-toggle='modal' onclick='view(this);' class='btn btn-sm blue' >查看<i class='fa fa-pencil-square-o'></i></button> ");
		btns.append("<button data-toggle='modal' onclick='deleteHelp(this);' class='btn btn-sm red' >删除<i class='fa fa-trash-o'></i></button> ");
		columnList.add(new PageColumn("操作", btns.toString()).setIsoperation(true));
		// 列显示/隐藏配置
		model.addAttribute("pageBeanObj", page);// 分页对象
		model.addAttribute("columnList", columnList);// 控制列设置的集合
		return "system/common/commonList";
	}
	
	
	/**
	 * 根据ID查找一条记录
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "findview")
	public void selectWxuserById(HttpServletResponse response, String id) {
		String jsonData = "";
		if (org.springframework.util.StringUtils.hasText(id)){
			//根据ID查找对象
			StringBuffer sb = new StringBuffer("select bk_st_content  from bk_t_helpcollect where bk_st_id=?");
			Map<String, Object> map = baseBiz.queryForMap(sb.toString(), new Object[]{id});
			jsonData = "{\"success\":true,result:["+JsonUtils.getJsonData(map) + "]}";
			this.createAjax(response, jsonData); 
		}
	}
	
	/***
	 * 删除意见
	 * @param model
	 * @param id
	 * @return
	 */
	
	@RequestMapping(value="delete")
	public void delete(HttpServletResponse response,String id){
		String jsonData = "";// 返回的json数据
		try {
			if(org.springframework.util.StringUtils.hasText(id)){
				List<BizTransUtil> list = new ArrayList<BizTransUtil>();
				//修改内容对应的所有关系表中的发送方删除状态
				list.add(new BizTransUtil(" update bk_t_helpcollect set bk_st_isdel='1',bk_st_updUserId=?,bk_dt_updDate =?  where bk_st_id =? "
						, new Object[]{getcuttSysuserID(),getcuttDate(),id}, CommonUtil.UPDATE));
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
