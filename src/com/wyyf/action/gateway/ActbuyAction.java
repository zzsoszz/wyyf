package com.wyyf.action.gateway;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lys.mvc.action.BaseAjaxAction;
@Scope(value = "prototype")
@Controller("ActbuyAction")
@RequestMapping(value="/wyyf/actbuy")
public class ActbuyAction extends BaseAjaxAction{
	
	/**
	 * 我要团购
	 * @param model
	 * @return
	 */
	@RequestMapping(value="icontent")
	public String icontent(Model model){
		return "wyyf/actbuy/icontent";
	}
	
	

}
