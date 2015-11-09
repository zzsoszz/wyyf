package com.wyyf.action.gateway;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lys.mvc.action.BaseAjaxAction;
@Scope(value = "prototype")
@Controller("CarAction")
@RequestMapping(value="/wyyf/car")
public class CarAction  extends BaseAjaxAction{
	/**
	 * 我的购物车
	 * @return
	 */
	@RequestMapping(value="listcar1")
	public String listcar(){
		return "wyyf/car/list";
	}

}
