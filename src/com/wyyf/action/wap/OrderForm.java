package com.wyyf.action.wap;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lys.mvc.action.BaseAjaxAction;

@Scope(value = "prototype")
@Controller("OrderForm")
@RequestMapping(value="OrderForm")
public class OrderForm extends BaseAjaxAction{
	/**
	 * 首页r
	 */
	public String index(){
		String sql="select b.bj_st_id,b.bj_st_clickurl, b.bj_st_enable,b.bj_st_remark,b.bj_st_title,b.bj_st_type ,b.bj_nm_orderno,a.ag_st_url,a.ag_st_id  from bj_t_advertisement b left JOIN  ag_t_file a on (b.bj_st_id = a.ag_st_objid and a.ag_st_objtype='bj_t_advertisement' and a.ag_st_mark='adTile') ";
		List<Map<String, Object>> list = baseBiz.queryForList(sql+" where bj_st_type='1'  or bj_st_type='5'  and bj_st_enable='1' order by  bj_nm_orderno desc,bj_dt_updDate desc  limit 0,6 ");
		model.addAttribute("main_centerlist", list);
		String type1=request.getParameter("type1");
		model.addAttribute("type", type1);
		return "wap/index";
	}
}
