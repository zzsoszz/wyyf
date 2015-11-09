package com.power.biz;

import java.util.Map;


public interface CommonBiz{ 
	/**
	 * 取得所有权限
	 * @return 权限的map集合
	 */
	public Map<String, Object> getALLfunction();
	/**
	 * 添加一个权限到权限集合中
	 * @return
	 */
	public boolean addALLfunction(String classUrl,String mark);
	/**
	 * 删除权限缓存
	 * @return
	 */
	public boolean delALLfunction();
	/**
	 * 取得所有字典
	 * @return 字典的map集合
	 */
	public Map<String,Map<String,Object>> getALLcode();
	/**
	 * 删除字典缓存
	 * @return
	 */
	public boolean delALLcode();
}
