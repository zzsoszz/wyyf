package com.lys.mvc.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 异常处理 Action
 * @author shuang
 */
@Scope(value = "prototype")
@Controller("ErrorAction")
@RequestMapping(value = "/error")
public class ErrorAction extends BaseAjaxAction {
	/**
	 * SESSION并发处理
	 * @return
	 */
	@RequestMapping(value="/concurrentSessionFilter")
	public String concurrentSessionFilter(){
		return "error/sessionComplicating";	
	}
	/**
	 * 没有访问url的权限
	 * @return
	 */
	@RequestMapping(value="/nonFunction")
	public String nonFunction(){
		return "error/nonFunction";	
	}
}
