package com.wyyf.action.gateway;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lys.mvc.action.BaseAjaxAction;

@Scope(value = "prototype")
@Controller("CheckAction")
@RequestMapping(value="/index")
public class CheckAction extends BaseAjaxAction {
	@RequestMapping(value="toCheckFree")
	public String toCheckFree(){
		return "wyyf/check/icontent";
	}

}
