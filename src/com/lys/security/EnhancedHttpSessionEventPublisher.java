package com.lys.security;

import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.lys.system.dictionary.SysStatic;
import com.lys.system.filter.SafetyFilter;
import com.lys.utils.CommonUtil;
import com.power.bean.Aj_t_UserLogin;
import com.power.bean.Ae_t_sysuser;

public class EnhancedHttpSessionEventPublisher implements ServletContextListener, HttpSessionAttributeListener, HttpSessionListener {
    public static final String EVENT_KEY = HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;
    /**
     * 初始化上下文
     * @param sce the event
     */
    @Override
	public synchronized void contextInitialized(ServletContextEvent sce) {
    }

    /**
     * 初始化 对象 值
     * @param event The servletContextEvent
     */
    @Override
	public synchronized void contextDestroyed(ServletContextEvent event) {
    }
    /**
     * 会话创建
     */
    @Override
	public void sessionCreated(HttpSessionEvent se) { 
    }
    /**
     * 这种方法的目的是赶在用户登录并记录他们的名字
     * @param 事件的会话绑定事件
     * @see javax.servlet.http.HttpSessionAttributeListener#attributeAdded(javax.servlet.http.HttpSessionBindingEvent)
     */
    @Override
	public void attributeAdded(HttpSessionBindingEvent event) {
    	if (event.getName().equals(EVENT_KEY) && !isAnonymous()) {
            SecurityContext securityContext = (SecurityContext) event.getValue();
            if (securityContext != null && securityContext.getAuthentication().getPrincipal() instanceof Ae_t_sysuser) {
                HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();    
                Ae_t_sysuser user = (Ae_t_sysuser) securityContext.getAuthentication().getPrincipal();
                Aj_t_UserLogin aj=new Aj_t_UserLogin(true);
    			aj.setAj_st_objid(user.getAe_st_id());//对象ID
    			aj.setAj_st_objname("ae_t_sysuser");//对象所在表名
    			aj.setAj_st_status("1");//在线状态， 1 在线 2离线  
    			aj.setAj_st_loginip(CommonUtil.overshot(request));//登录IP
    			aj.setAj_dt_logintime(new Date());//登录时间
    			aj.setAj_st_sessionid(request.getSession().getId());//登录相关SESSIONID
    			aj.setAj_st_clientinfo(request.getHeader("user-agent"));//客户端信息
    			if(SysStatic.baseBiz.addTRANS(aj)){
    				SafetyFilter.logger.info("登录信息记录成功！");
    			}
            }
        }
    }

    /**
     * 会议销毁
     */
    @Override
	public void sessionDestroyed(HttpSessionEvent se) {
        Object obj = se.getSession().getAttribute(EVENT_KEY);
        if (obj != null) {
            se.getSession().removeAttribute(EVENT_KEY);
        }
    }
    /**
     * 当用户注销，从HashMap中删除自己的名字
     * @param 事件的会话绑定事件
     * @see javax.servlet.http.HttpSessionAttributeListener#attributeRemoved(javax.servlet.http.HttpSessionBindingEvent)
     */
    @Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
        if (event.getName().equals(EVENT_KEY) && !isAnonymous()) {
            SecurityContext securityContext = (SecurityContext) event.getValue();
            Authentication auth = securityContext.getAuthentication();
            if (auth != null && (auth.getPrincipal() instanceof Ae_t_sysuser)) {
            	SysStatic.baseBiz.executeTRANS("update aj_t_userlogin set aj_dt_logouttime=?,aj_st_status='2' where aj_st_sessionid=?", new Date(),event.getSession().getId());
            }
        }
    }

    /**
     * 需要的Acegi安全1.0，因为它增加了一个匿名用户的会话，并
     * 再认证后替换它. http://forum.springframework.org/showthread.php?p=63593
     * @param 事件的会话绑定事件
     * @see javax.servlet.http.HttpSessionAttributeListener#attributeReplaced(javax.servlet.http.HttpSessionBindingEvent)
     */
    @Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
    }

    /**
     * 判断是否匿名
     * @return
     */
    private boolean isAnonymous() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        if (ctx != null) {
            AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
            Authentication auth = ctx.getAuthentication();
            return resolver.isAnonymous(auth);
        }
        return true;
    }
}

