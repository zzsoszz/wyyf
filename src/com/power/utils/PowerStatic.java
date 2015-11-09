package com.power.utils;

import java.util.HashMap;
import java.util.Map;

import com.lys.system.dictionary.SysStatic;
import com.lys.utils.StringUtils;

public class PowerStatic {
	//字典类型
	public static Map<String, String> codeType=new HashMap<String, String>();
	static{
		codeType.put("系统码", "1");
		codeType.put("业务码", "2");
		codeType.put("公用码", "3");
	}
	//是否
	public static Map<String, String> isyesorno=new HashMap<String, String>();
	static{
		isyesorno.put("否", "0");
		isyesorno.put("是", "1");
	}
	/**
	 * 获取key  对应的字典 节点 name值
	 * @param key==字典标识
	 * @param value
	 * @return
	 */
	public static String getCodeName(String key){
		Map<String,Object> objmap=null;
		if(org.springframework.util.StringUtils.hasText(key)){
			 objmap=SysStatic.commonBizCache.getALLcode().get(key);
		}
		if(objmap!=null){
			return StringUtils.toStringByObject(objmap.get("ab_st_name"));
		}
		return "";
	}
	/**
	 * 自定义的缓存key
	 * @author lys
	 *
	 */
	public enum CacheKey {
		/**
		 * 权限集合缓存key
		 */
		getALLfunction_KEY
		/**
		 * 字典集合缓存key
		 */
		,getALLcode_KEY
		//, YELLOW, RED
	}
}
