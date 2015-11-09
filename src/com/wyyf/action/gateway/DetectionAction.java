package com.wyyf.action.gateway;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lys.mvc.action.BaseAjaxAction;
@Scope(value = "prototype")
@Controller("DetectionAction")
@RequestMapping(value="/wyyf/detection")
public class DetectionAction   extends BaseAjaxAction{
	
	@RequestMapping(value="free")
	public String detection(){
		return "wyyf/detection/free";
	}
	@RequestMapping(value="aircharge")
	public String aircharge(){
		return "wyyf/detection/aircharge";
	}

}
