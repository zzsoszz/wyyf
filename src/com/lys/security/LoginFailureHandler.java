package com.lys.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

import com.lys.security.exception.AccessDeniedException;
import com.lys.security.exception.MethodErrorException;
import com.lys.security.exception.ValidateCodeException;


/**
 * spring security登录失败处理
 * @author shuang
 */
public class LoginFailureHandler implements AuthenticationFailureHandler {
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) throws IOException, ServletException {
		String jsonData = "{success:false,msg:\"添加失败!\"}";
		if (ex instanceof UsernameNotFoundException || ex instanceof BadCredentialsException ) {
			// 帐号错误
			jsonData="{success:false,errorType:\"codeError\",msg:\"用户名或者密码错误，请重新输入！\"}";  
		} else if (ex instanceof ValidateCodeException) {
			// 验证码错误
			jsonData="{success:false,msg:\"验证码错误！\"}";
		} else if (ex instanceof MethodErrorException) {
			// 请求方法错误
			jsonData="{success:false,msg:\"请求方法错误，"+ex.getMessage()+"\"}";
		} else if (ex instanceof SessionAuthenticationException) {
			// 登陆超时错误
			jsonData="{success:false,msg:\"登陆超时错误，"+ex.getMessage()+"\"}";
		} else if (ex instanceof AccessDeniedException) {
			// 登陆超时错误
			jsonData="{success:false,msg:\"权限错误，"+ex.getMessage()+"\"}";
		} else {
			// 未知异常错误
			//jsonData="{success:false,msg:\"未知异常错误，"+ex.getMessage()+"!\"}";
			jsonData="{success:false,msg:\""+ex.getMessage()+"\"}";
		}
		response.getWriter().print(jsonData);
	}
	
}
