package com.wyyf.action.gateway;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lys.mvc.action.BaseAjaxAction;
@Scope(value = "prototype")
@Controller("ShopAction")
@RequestMapping(value="/wyyf/shop")
public class ShopAction extends BaseAjaxAction {
	
	/*
	 * 抢相因
	 */
	@RequestMapping(value = "cheap")
	public String cheap() {
		return "wyyf/shop/cheap";
	}
	/*
	 * 建材商城
	 */
	@RequestMapping(value = "bulding")
	public String bulding() {
		return "wyyf/shop/bulding";
	}
	/*
	 * 家居商城
	 */
	@RequestMapping(value = "home")
	public String home() {
		return "wyyf/shop/home";
	}
	/**
	 * 品牌特卖
	 * @return
	 */
	@RequestMapping(value="special")
	public String special(){
		return "wyyf/shop/special";
	}
	

}
