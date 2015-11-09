package com.wyyf.action.gateway;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lys.mvc.action.BaseAjaxAction;
@Scope(value = "prototype")
@Controller("SurveygoldAction")
@RequestMapping(value="/wyyf/surveygold")
public class SurveygoldAction extends BaseAjaxAction{
	/*
	 * 我要监督
	 */
	@RequestMapping(value = "surveygold")
	public String surveygold() {
		return "wyyf/surveygold/surveygold";
	}
}
