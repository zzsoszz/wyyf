package com.lys.utils;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * json 自我封装需要的类
 * @author shuang
 */
public class JsonDateValueProcessor implements JsonValueProcessor {
	private DateFormat dateFormat;
	public JsonDateValueProcessor() {
		dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	public DateFormat getDateFormat() {
		return dateFormat;
	}
	public void setDateFormat(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}
	@Override
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		return process(value, jsonConfig);
	}
	@Override
	public Object processObjectValue(String key, Object value,JsonConfig jsonConfig) {
		return process(value, jsonConfig);
	}
	private Object process(Object value, JsonConfig jsonConfig) {
		Object dateValue = value;
		if (dateValue instanceof Date)
			dateValue = new java.util.Date(((Date) dateValue).getTime());
		if (dateValue instanceof java.util.Date)
			return dateFormat.format(dateValue);
		else
			return dateValue;
	}

}