package com.lys.cache;

import java.io.Serializable;
import java.lang.reflect.Method;

import javax.annotation.Resource;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.lys.system.filter.SafetyFilter;
import com.power.utils.MyKey;

/**
 * 缓存方法拦截器核心代码
 * @author shuang
 */
public class EhCacheMethodInterceptor implements MethodInterceptor, InitializingBean {
	@Resource(name="defaultCache")
	private Cache cache;
	public EhCacheMethodInterceptor() {
		super();
	}
	public void setCache(Cache cache) {
		this.cache = cache;
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(cache, "Need a cache. Please use setCache(Cache) create it.");  
	}
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		String cacheKey = getCacheKey(invocation);//获取缓存key值
		if(org.springframework.util.StringUtils.hasText(cacheKey)){
			Element element = cache.get(cacheKey);
			// 缓存节点不存在的情况
			if (null == element) {
				synchronized (this) {
					// 这里判断是为了降低强制同步的负面影响,只需第一个操作该添加过程,后来者则跳过
					if (null == cache.get(cacheKey))
						element = putValueToCache(invocation, element, cacheKey);
				}
			}
			// 返回缓存值
			return element.getValue();
		}else{
			return invocation.proceed();
		}
		
	}
	// 新增节点放到缓存区
	private Element putValueToCache(MethodInvocation invocation,Element element, String cacheKey) throws Throwable {
		Object result = invocation.proceed();
		element = new Element(cacheKey, (Serializable) result);
		cache.put(element);
		//SafetyFilter.logger.info(new StringBuffer("===添加==缓存key：").append(cacheKey).append("，value：").append(result).toString());
		SafetyFilter.logger.info(new StringBuffer("===添加==缓存key：").append(cacheKey).toString());
		return element;
	}
	/**
	 * 获取缓存key值
	 * @param invocation 执行方法的对象
	 * @return String 缓存key
	 */
	private String getCacheKey(MethodInvocation invocation) {
		try {
			Class<?> clazz=invocation.getThis().getClass();
			Method method= invocation.getMethod();
			MyKey my=clazz.getDeclaredMethod(method.getName(),method.getParameterTypes()).getAnnotation(MyKey.class);
			if(my!=null&&org.springframework.util.StringUtils.hasText(my.key().toString())){
				return my.key().toString();
			}else{
				throw new RuntimeException(new StringBuffer("缓存的方法（").append(clazz.getName()).append(".").append(method.getName()).append("）不存在MyKey注解").toString());
			}
		} catch (Exception e) {
			SafetyFilter.logger.error("获取缓存key失败！消息："+e.getMessage());
			return null;
		}
		
	}

}