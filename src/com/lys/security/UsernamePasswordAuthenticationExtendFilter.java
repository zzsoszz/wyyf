package com.lys.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.lys.security.exception.ValidateCodeException;
import com.utils.CookieUtil;

/**
 * 重载SECURITY3的UsernamePasswordAuthenticationFilter的attemptAuthentication,
 * obtainUsername,obtainPassword方法(完善逻辑) 增加验证码校验模块 添加验证码属性 添加验证码功能开关属性
 * @author shuang
 */
@SessionAttributes("imageCode") 
public class UsernamePasswordAuthenticationExtendFilter extends	UsernamePasswordAuthenticationFilter {
	// 验证码字段
	private String validateCodeParameter = "validateCode";
	// 是否开启验证码功能
	private boolean openValidateCode = false;
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		// 只接受POST方式传递的数据
		if (!"POST".equals(request.getMethod()))
			throw new RuntimeException("不支持非POST方式的请求!");
		// 开启验证码功能的情况
		if (isOpenValidateCode()){
			String sfcode=request.getParameter("sfcode");//是否填写验证码
			if(!"0".equals(sfcode)){
				checkValidateCode(request);
			}
		}
		// 获取Username和Password
		String flag=request.getParameter("flag");
		String username = obtainUsername(request);
		String password = obtainPassword(request);
		int  loginMaxAge = 30*24*60*60;
		if(flag!=null&&!flag.equals("")&&flag.equals("1")){
			CookieUtil.addCookie(response , "userName" , username , loginMaxAge); 
			CookieUtil.addCookie(response , "password" , password , loginMaxAge);  
			CookieUtil.addCookie(response , "flag" , "true", loginMaxAge); 
		}else {
			CookieUtil.removeCookie(response, "userName");
			CookieUtil.removeCookie(response, "password");
			CookieUtil.removeCookie(response, "flag");
			CookieUtil.addCookie(response , "flag" , "false" , loginMaxAge);
		}
		System.out.println(flag);
		
		// UsernamePasswordAuthenticationToken实现Authentication校验
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
		// 允许子类设置详细属性
		setDetails(request, authRequest);
		// 运行UserDetailsService的loadUserByUsername再次封装Authentication
		return this.getAuthenticationManager().authenticate(authRequest);
	}
	// 匹对验证码的正确性
	public void checkValidateCode(HttpServletRequest request) {
		String jcaptchaCode = obtainValidateCodeParameter(request);
		if (null == jcaptchaCode)
			throw new RuntimeException("验证码超时,请重新获取!");
		if(!jcaptchaCode.toLowerCase().equals(String.valueOf(request.getSession().getAttribute("imageCode")).toLowerCase())){
			throw new ValidateCodeException("验证码不正确,请重新输入!");
		}
	}

	public String obtainValidateCodeParameter(HttpServletRequest request) {
		Object obj = request.getParameter(getValidateCodeParameter());
		return null == obj ? "" : obj.toString().trim();
	}

	@Override
	protected String obtainUsername(HttpServletRequest request) {
		Object obj = request.getParameter(getUsernameParameter());
		return null == obj ? "" : obj.toString().trim();
	}

	@Override
	protected String obtainPassword(HttpServletRequest request) {
		Object obj = request.getParameter(getPasswordParameter());
		return null == obj ? "" : obj.toString().trim();
	}
	public String getValidateCodeParameter() {
		return validateCodeParameter;
	}

	public void setValidateCodeParameter(String validateCodeParameter) {
		this.validateCodeParameter = validateCodeParameter;
	}

	public boolean isOpenValidateCode() {
		return openValidateCode;
	}

	public void setOpenValidateCode(boolean openValidateCode) {
		this.openValidateCode = openValidateCode;
	}

}
