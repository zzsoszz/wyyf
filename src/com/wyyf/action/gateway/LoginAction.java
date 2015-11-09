package com.wyyf.action.gateway;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lys.mvc.action.BaseAjaxAction;

@Scope(value = "prototype")
@Controller("LoginAction")
@RequestMapping(value="/wyyf/login")
public class LoginAction extends BaseAjaxAction{
	/*
	 * 登陆
	 */
	@RequestMapping(value = "login1")
	public String login() {
		return "wyyf/login/login";
	}
	
	/*
	 * 注册
	 */
	@RequestMapping(value = "sign")
	public String sign() {
		return "wyyf/login/sign";
	}
}
