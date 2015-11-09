package com.lys.springmvc.formatter;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import org.springframework.format.Formatter;

import com.lys.system.filter.SafetyFilter;
import com.lys.utils.DateConverUtil;

/**
 * 格式化器-例子，因为该Date 有@DateTimeFormat
 * @author lys
 *
 */
public class DateToStringFormatter implements Formatter<Date> {
	private String dateFormatPattern;
	public DateToStringFormatter(String dateFormatPattern) {
		this.dateFormatPattern = dateFormatPattern;
	}
	/**
	 * 格式化，是后台拿到值，格式化到前台
	 */
	@Override
	public String print(Date date, Locale arg1) {
		SafetyFilter.logger.info("进入了格式化！");
		return DateConverUtil.getSbyDT(date,dateFormatPattern);
	}
	/**
	 * 解析，从前台得到值，并解析，类似于转换器，但会替换相同类型的转换器
	 */
	@Override
	public Date parse(String dateString, Locale arg1) throws ParseException {
		SafetyFilter.logger.info("进入了解析！");
		return DateConverUtil.getDbyST(dateString,dateFormatPattern);//此处不能返回空值，如果为null将有异常errors出现在页面的使用errors的地方 
	}
 
}
