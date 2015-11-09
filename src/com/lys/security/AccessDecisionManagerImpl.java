package com.lys.security;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * 判断是否有权限执行。。
 * @author shuang
 */

public class AccessDecisionManagerImpl implements AccessDecisionManager {
	@Override
	public void decide(Authentication authentication, Object object,Collection<ConfigAttribute> attributes) throws AccessDeniedException, InsufficientAuthenticationException {
		if (null == attributes)
			return;
		for (ConfigAttribute attribute : attributes) {
			String needRole = ((SecurityConfig)attribute).getAttribute();//needRole为访问相应的资源应该具有的权限。
			for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {//grantedAuthority为用户所被赋予的权限
				if (needRole.equals(grantedAuthority.getAuthority()))//这里待新的思路去实现权限验证判断。
					return;
			}
		}
		throw new AccessDeniedException("权限不足!");//看看能否在这里弄一个请求，使其跳转至权限不足界面
	}
	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}
	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
}