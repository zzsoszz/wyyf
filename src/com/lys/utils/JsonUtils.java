package com.lys.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.commons.beanutils.PropertyUtils;

import com.lys.system.filter.SafetyFilter;
/**
 * JSON对象工具类
 * 
 * @author shuang  
 */
public final class JsonUtils {
	private JsonUtils() {}
	/**
	 * 将对象转换成JSON字符串 
	 * @param bean BO/VO对象
	 * @return JSON字符串
	 * @说明：对bean中有Date类型的数据可以成功转换成字符串格式的时间类型,例如："date":"2011-11-22 00:00:00"
	 */
	public static String getJsonData(Object bean) {
		//JSONObject.fromObject(bean, getJsonConfig(null)).toString().r;
		return  JSONObject.fromObject(bean, getJsonConfig(null)).toString()+"";
	}
	/**
	 * 将集合类型转换成JSON字符串 
	 * @param collection 集合对象
	 * @return JSON字符串
	 * @说明：对collection中有java.sql.Timestamp类型的数据 暂时不能 转换成字符串格式的时间类型,例如结果是："date":{"date":22,"day":2,"hours":0,"minutes":0,"month":10,"nanos":0,"seconds":0,"time":1321891200000,"timezoneOffset":-480,"year":111}
	 */
	@SuppressWarnings("rawtypes")
	public static String getJsonDataFromCollection(Collection collection) {
		return JSONArray.fromObject(collection, getJsonConfig(null)).toString();
	}
	/**
	 * 将JSON数组数据转换为Java Bean对象列表 
	 * @param data JSON数组数据
	 * @param beanClass 待转换的Bean类型 --LinkedCaseInsensitiveMap
	 * @return 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getBeanListFromJsonData(String data, Class beanClass) {
		JSONArray jsonArray = JSONArray.fromObject(data);
		List list = new ArrayList(jsonArray.size());
		for (Iterator iter = jsonArray.iterator(); iter.hasNext();) {
			JSONObject jsonObject = (JSONObject) iter.next();
			list.add(JSONObject.toBean(jsonObject, beanClass));
		}
		return list;
	}
	/**
	 * 将JSON数据转换为Java Bean对象 
	 * @param data JSON字符串
	 * @param beanClass 待转换的Bean类型--LinkedCaseInsensitiveMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Object getBeanFromJsonData(String data, Class beanClass) {
		JSONObject jsonObject = JSONObject.fromObject(data);
		return JSONObject.toBean(jsonObject, beanClass);
	}
	//-------------------------------
	/**
	 * 转换含有非标准日期的JSON字符串，默认日期格式为yyyy-MM-dd HH:mm:ss 
	 * @param jsonData JSON字符串
	 * @param dateProps 日期属性名称数组
	 * @return
	 */
	public static String formatJsonData(String jsonData, String[] dateProps) {
		return formatJsonData(jsonData, dateProps, "yyyy-MM-dd'T'HH:mm:ss");
	}
	/**
	 * 转换含有非标准日期的JSON字符串 
	 * @param jsonData JSON字符串
	 * @param dateProps 日期属性名称数组
	 * @param dateFormat 日期格式字符串
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String formatJsonData(String jsonData, String[] dateProps, String dateFormat) {
		if (dateProps == null)
			return jsonData;
		JSONObject jsonObject = JSONObject.fromObject(jsonData);
		for (Iterator iter = Arrays.asList(dateProps).iterator(); iter.hasNext();) {
			String dateProp = (String) iter.next();
			if (jsonObject.has(dateProp))
				fixJSONObject(jsonObject, dateProp, dateFormat);
		}
		return jsonObject.toString();
	}
	/**
	 * 转换含有非标准日期的JSON数组数据 
	 * @param jsonData  JSON数组数据
	 * @param dateProps 日期属性名称数组
	 * @param dateFormat 日期格式字符串
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String formatJsonDataArray(String jsonData, String[] dateProps, String dateFormat) {
		if (dateProps == null)
			return jsonData;
		JSONArray jsonArray = JSONArray.fromObject(jsonData);
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			for (Iterator iter = Arrays.asList(dateProps).iterator(); iter.hasNext();) {
				String dateProp = (String) iter.next();
				if (jsonObject.has(dateProp))
					fixJSONObject(jsonObject, dateProp, dateFormat);
			}
		}
		return jsonArray.toString();
	}
	
	/**
	 * 将JSON数据转换为Java Bean对象，含有待转换的日期，格式为yyyy-MM-dd HH:mm:ss 
	 * @param data  JSON字符串
	 * @param dateProps 日期属性名称数组
	 * @param beanClass 待转换的Bean类型
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Object getBeanFromJsonData(String data, String[] dateProps, Class beanClass) {
		return getBeanFromJsonData(data, dateProps, "yyyy-MM-dd'T'HH:mm:ss", beanClass);
	}
	/**
	 * 将JSON数据转换为Java Bean对象，含有待转换的日期 
	 * @param data  JSON字符串
	 * @param dateProps 日期属性名称数组
	 * @param dateFormat  JSON字符串中含有的日期格式
	 * @param beanClass 待转换的Bean类型
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Object getBeanFromJsonData(String data, String[] dateProps, String dateFormat, Class beanClass) {
		return getBeanFromJsonData(formatJsonData(data, dateProps, dateFormat), beanClass);
	}
	
	/**
	 * 将JSON数组数据转换为Java Bean对象列表，含有待转换的日期，格式为yyyy-MM-dd HH:mm:ss 
	 * @param data JSON数组数据
	 * @param dateProps 日期属性名称数组
	 * @param beanClass 待转换的Bean类型
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static List getBeanListFromJsonData(String data, String[] dateProps, Class beanClass) {
		return getBeanListFromJsonData(data, dateProps, "yyyy-MM-dd'T'HH:mm:ss", beanClass);
	}
	/**
	 * 将JSON数组数据转换为Java Bean对象列表，含有待转换的日期 
	 * @param data  JSON数组数据
	 * @param dateProps 日期属性名称数组
	 * @param dateFormat JSON字符串中含有的日期格式
	 * @param beanClass  待转换的Bean类型
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static List getBeanListFromJsonData(String data, String[] dateProps, String dateFormat, Class beanClass) {
		return getBeanListFromJsonData(formatJsonDataArray(data, dateProps, dateFormat), beanClass);
	}
	/**
	 * 将JSON数组数据转换为Java Bean对象和扩展属性映射的列表，含有待转换的日期，格式为yyyy-MM-dd HH:mm:ss 
	 * @param data JSON数组数据
	 * @param dateProps 扩展属性数据数组
	 * @param extendProps 扩展属性名称数组
	 * @param beanClass 待转换的Bean类型
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getBeanListWithExtendFromJsonData(String data, String[] dateProps, String[] extendProps, Class beanClass) {
		JSONArray jsonArray = new JSONArray();
		if (dateProps != null && dateProps.length > 0) {
			String fdata = formatJsonDataArray(data, dateProps, "yyyy-MM-dd'T'HH:mm:ss");
			jsonArray = JSONArray.fromObject(fdata);
		} else {
			jsonArray = JSONArray.fromObject(data);
		}
		List list = new ArrayList(jsonArray.size());
		for (Iterator iter = jsonArray.iterator(); iter.hasNext();) {
			JSONObject jsonObject = (JSONObject) iter.next();
			Map extendMap = new HashMap();
			for (int i = 0; i < extendProps.length; i++) {
				try {
					extendMap.put(extendProps[i], PropertyUtils.getProperty(jsonObject, extendProps[i]));
				} catch (Exception ex) {}
			}
			list.add(new Object[] { JSONObject.toBean(jsonObject, beanClass),extendMap });
		}
		return list;
	}
	/**
	 * 获取Json环境上下文默认设置 
	 * @param dateFormat Date类型的转换格式
	 * @return JsonConfig对象
	 */
	public static JsonConfig getJsonConfig(String dateFormat) {
		JsonDateValueProcessor beanProcessor = new JsonDateValueProcessor();
		if (dateFormat != null) {
			DateFormat df = new SimpleDateFormat(dateFormat);
			beanProcessor.setDateFormat(df);
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.registerJsonValueProcessor(java.util.Date.class,beanProcessor);
		return jsonConfig;
	}
	/**
	 * JSON数据中的日期修正为{time:longValue}型
	 * @param jsonObject  JSON对象
	 * @param dateProp 日期属性名称
	 * @param dateFormat 日期格式字符串
	 * @return
	 */
	private static void fixJSONObject(JSONObject jsonObject, String dateProp, String dateFormat) {
		try {
			if (jsonObject.get(dateProp).equals(null) || jsonObject.get(dateProp).equals("")) {
				jsonObject.put(dateProp, new JSONObject(true));
			} else {
				Date date = new SimpleDateFormat(dateFormat).parse(jsonObject.get(dateProp).toString());
				jsonObject.put(dateProp, "{\"time\":"+ JSONObject.fromObject(date).get("time") + "}");
			}
		} catch (ParseException e) {
			SafetyFilter.logger.error("转换JSON数据错误, 在JSON 字符串中 ："+jsonObject+"缺乏属性: '"+ dateProp + "' 。 " , e);
		}
	}
}
