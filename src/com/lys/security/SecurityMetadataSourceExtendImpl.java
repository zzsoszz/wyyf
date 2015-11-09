package com.lys.security;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.RequestMatcher;


/**
 * 初始化时加载角色资源关联数据
 * 
 * @author shuang
 */
public class SecurityMetadataSourceExtendImpl implements FilterInvocationSecurityMetadataSource {
	private boolean expire = false; // 过期标识
	private RequestMatcher requestMatcher; // 匹配规则
	private String matcher; // 规则标识
	private Map<String, Collection<ConfigAttribute>> kv = new HashMap<String, Collection<ConfigAttribute>>(); // 资源集合
	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
	/**
	 * 初始化方法时候从数据库中读取资源
	 */
	public void init() {
		load();
	}
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return	new HashSet<ConfigAttribute>();
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		return null;
	}
	/**
	 * 加载所有权限URL关系
	 */
	public void load() {
	}
	/**
	 * 刷新资源
	 */
	public void expireNow() {
		this.expire = true;
	}
	public void setMatcher(String matcher) {
		this.matcher = matcher;
	}

	public RequestMatcher getRequestMatcher() {
		return requestMatcher;
	}
	public void setRequestMatcher(RequestMatcher requestMatcher) {
		this.requestMatcher = requestMatcher;
	}
	public Map<String, Collection<ConfigAttribute>> getKv() {
		return kv;
	}
	public void setKv(Map<String, Collection<ConfigAttribute>> kv) {
		this.kv = kv;
	}
	public String getMatcher() {
		return matcher;
	}
	public void setExpire(boolean expire) {
		this.expire = expire;
	}
	public boolean isExpire() {
		return expire;
	}

}