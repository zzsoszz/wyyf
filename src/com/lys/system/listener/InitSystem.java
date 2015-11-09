package com.lys.system.listener;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lys.system.filter.SafetyFilter;
import com.lys.system.listener.init.InitData;



public class InitSystem implements ServletContextListener{
	/**
	 * 系统停止时执行该方法
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		SafetyFilter.logger.info("系统结束成功！");
	}
	/**
	 * 系统启动时执行该方法
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		SafetyFilter.logger.info("系统监听开始了！");
		SafetyFilter.logger.info("====系统数据初始化开始了！=====");
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		InitData initData=context.getBean("InitData",InitData.class);
		initData.init(event);
		SafetyFilter.logger.info("====系统数据初始化结束了！=====");
	}

}
