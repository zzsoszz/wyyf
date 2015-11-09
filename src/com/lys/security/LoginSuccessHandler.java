package com.lys.security;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.LinkedCaseInsensitiveMap;

import com.lys.mvc.biz.BaseBiz;
import com.lys.system.filter.SafetyFilter;
import com.lys.utils.CommonUtil;
import com.power.bean.Ae_t_sysuser;

/**
 * spring security登录成功处理
 * 
 * @author shuang
 */
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	@Resource(name="BaseBizImpl")
	public BaseBiz baseBiz;
	/**
	 * 登录成功后
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response, Authentication authentication)throws IOException, ServletException {
		updateSysuser(request);//修改数据库中登录后的状态
		response.getWriter().print("{success:true,msg:\"登录成功!\"}");
	}
	/**
	 * 登录成功后修改User的信息，例如登录状态，最后登录IP 等
	 * @param user
	 * @param request
	 * @return
	 */
	private void updateSysuser(HttpServletRequest request) {
		try {
			Ae_t_sysuser user = (Ae_t_sysuser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Map<String,Object> map= new LinkedCaseInsensitiveMap<Object>();
			map.put("ae_st_id", user.getAe_st_id());
			map.put("ae_dt_lastlogontime", new Date());// 本次登陆时间
			map.put("ae_nm_loginedcount",user.getAe_nm_loginedcount()+1);//登录次数
			map.put("ae_st_lastlogonIp",CommonUtil.overshot(request));//本次登录的IP
			baseBiz.updateTRANS(map, Ae_t_sysuser.class);
		} catch (Exception e) {
			SafetyFilter.logger.error("登录信息记录失败："+e.getMessage());
		}
		
	}
}
