package com.lys.springmvc.converter;

import java.util.Date;
import org.springframework.core.convert.converter.Converter;

import com.lys.system.filter.SafetyFilter;
import com.lys.utils.DateConverUtil;

/**
 * 全局转换器
 * @author lys
 *
 */
public class StringToDateConverter implements Converter<String, Date> {

	private String dateFormatPattern;

	public StringToDateConverter(String dateFormatPattern) {
		this.dateFormatPattern = dateFormatPattern;
	}
	
	@Override
	public Date convert(String source) {
		SafetyFilter.logger.info("进入了全局时间转换器！");
		return DateConverUtil.getDbyST(source,dateFormatPattern);
	}
 
}
