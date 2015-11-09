package com.lys.springmvc.intercept;


import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lys.mvc.biz.BaseBiz;
import com.lys.system.filter.SafetyFilter;
import com.lys.utils.JsonUtils;
import com.lys.utils.StringUtils;
import com.power.bean.Ah_t_log;
import com.power.bean.Ae_t_sysuser;
import com.power.biz.CommonBiz;

public class MethodsInterceptor extends HandlerInterceptorAdapter {
	@Resource(name="CommonBizCache")
	public CommonBiz commonBiz;//操作数据的对象biz
	@Resource(name="BaseBizImpl")
	public BaseBiz baseBiz;
	/**
	 * 在Controller方法执行前进行拦截
	 */
	  /** 
     * 在业务处理器处理请求之前被调用 
     * 如果返回false 
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链 
     *  
     * 如果返回true 
     *    执行下一个拦截器,直到所有的拦截器都执行完毕 
     *    再执行被拦截的Controller 
     *    然后进入拦截器链, 
     *    从最后一个拦截器往回执行所有的postHandle() 
     *    接着再从最后一个拦截器往回执行所有的afterCompletion() 
     */  
	@Override
	@SuppressWarnings("unchecked")
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HandlerMethod handler2=(HandlerMethod) handler;
		if(handler2!=null){
			StringBuffer s=new StringBuffer("本次准备访问方法的绝对路劲：");
			s.append(handler2.getBeanType().getName()+" ");//访问的类路径
			Method method=handler2.getMethod();
			s.append( Modifier.toString(method.getModifiers())+" " + method.getReturnType().getSimpleName() + " " + method.getName() + "(");
			for (Class<?> param : method.getParameterTypes()) {
				s.append(param.getSimpleName()).append(",");
			}
			if(method.getParameterTypes().length>0){
				s.deleteCharAt(s.length() - 1).append(")");
			}else{
				s.append(")");
			}
			SafetyFilter.logger.info(s.toString()); 
			//前台登录后的-权限控制
			if (request.getServletPath().matches("/signin/.*")) {
				try {
					Ae_t_sysuser user=(Ae_t_sysuser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					if(!org.springframework.util.StringUtils.hasText(user.getAe_st_id())){
						 response.sendRedirect(request.getContextPath()+"/index/loginp");//前台登录界面
						 return false;
					}
				} catch (Exception e) {
					 response.sendRedirect(request.getContextPath()+"/index/loginp");//前台登录界面
					 return false;
				}
			}
			//权限控制
			String clazzurl=handler2.getBeanType().getName();
			String methodname=method.getName();
			clazzurl=clazzurl.substring(clazzurl.lastIndexOf('.')+1);
			String wybs=clazzurl+"."+methodname;
			Map<String,Object> functions=commonBiz.getALLfunction();
			Map<String,Object> allmap=(Map<String,Object>) functions.get(wybs);
			if(allmap!=null){
				try {
					Ae_t_sysuser user=(Ae_t_sysuser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					
					String mark=StringUtils.toStringByObject(allmap.get("aa_st_mark"));//标识
					//判断是否有权限访问
					if(!mark.equals(user.getFunctions().get(wybs))){
						response.sendRedirect(request.getContextPath()+"/error/nonFunction");//没有权限提示界面
						return false;
					}
					String islog=StringUtils.toStringByObject(allmap.get("aa_st_islog"));//是否日志记录
					
					//用户操作日志记录
					if("1".equals(islog)){
						Map<String, String[]> map = request.getParameterMap();
						String jsonData = JsonUtils.getJsonData(map);
						Ah_t_log ah_t_log =  new Ah_t_log();
						ah_t_log.setAh_st_id(StringUtils.getUUID32());
						ah_t_log.setAh_st_type("2");
						ah_t_log.setAh_st_className(wybs);
						ah_t_log.setAh_st_context(jsonData);
						ah_t_log.setAh_st_ip(request.getRemoteAddr());
						ah_t_log.setAh_st_addUserId(user.getAe_st_id());
						ah_t_log.setAh_dt_addDate(new Date());
						//保存日志对象
						baseBiz.addTRANS(ah_t_log);
					}
				} catch (Exception e) {
					//response.sendRedirect(request.getContextPath()+"/system/admin");//后台登录界面
					response.sendRedirect(request.getContextPath()+"/error/concurrentSessionFilter");//不存在SessionUser了
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * 在Controller方法执行后进行拦截
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if(modelAndView!=null){
			SafetyFilter.logger.info("跳转页面：WEB-INF/jsp/"+modelAndView.getViewName()+".jsp");
		}
	}
	/**
	 * 在DispatcherServlet完全处理完请求后被调用，可以在该方法中进行一些资源清理的操作???
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		SafetyFilter.logger.info("-------------------***一个请求结束***----------------------");
	}
	
}
