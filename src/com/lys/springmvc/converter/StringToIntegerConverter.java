package com.lys.springmvc.converter;

import org.springframework.core.convert.converter.Converter;

import com.lys.system.filter.SafetyFilter;
import com.lys.utils.StringUtils;

/**
 * 全局转换器-String转int
 * @author lys
 *
 */
public class StringToIntegerConverter implements Converter<String, Integer> {

	@Override
	public Integer convert(String source) {
		SafetyFilter.logger.info("进入了全局数字转换器！");
		return StringUtils.toIntegerByObject(source);
	}
 
}
