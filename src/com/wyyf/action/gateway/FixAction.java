package com.wyyf.action.gateway;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lys.mvc.action.BaseAjaxAction;

@Scope(value = "prototype")
@Controller("FixAction")
@RequestMapping(value="/wyyf/fix")
public class FixAction extends BaseAjaxAction {
	/**
	 * 找工长
	 * @return
	 */
	@RequestMapping(value="icontent")
	public String icontent(){
		return "wyyf/fix/icontent";
	}
	/**
	 * 找师傅
	 * @return
	 */
	@RequestMapping(value="fixturemaster")
	public String fixturemaster(){
		return "wyyf/fix/fixturemaster";
	}

	/**
	 * 师傅详情
	 * @return
	 */
	@RequestMapping(value="fixdetail")
	public String fixdetail(){
		return "wyyf/fix/labour_detail";
	}
}
