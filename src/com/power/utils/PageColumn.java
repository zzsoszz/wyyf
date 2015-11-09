package com.power.utils;

import java.util.Map;


/**
 * 列表封装类---设置列表的列属性
 * @author Administrator
 *
 */
public class PageColumn {
	
	private String title;//列标题
	private String column;//对应的数据库查询语句字段
	private Boolean isorder=false;//是否列有排序功能  false=否， true ==是
	private String order;//本页的排序   desc、asc 
	private String width;//列宽
	private Boolean hiddle=false;//false=显示， true ==影藏,不能被行获取到
	private Boolean hiddle2=false;//false=显示， true ==影藏， 可以被行获取
	private Boolean isnum=false;//是否显示序号
	private Boolean ischeckbox=false;//false=不为复选框列， true ==为复选框列
	private Boolean isoperation=false;//false=不为操作列， true ==为操作列
	private String code;//列对应的字典表code 父节点
	private Boolean isshowcode=false;//是否转换字典中文名
	private Boolean isSumColumn=false;//是否计算列的和

	private Boolean iscasewhen=false;//是否转换字典中文名
	private String cases;// 字段别名
	private Map<String,String> when;//根据字段值取得对应值
	private int length;// 设置显示字数
	
	/**
	 * 
	 * @param title 标题
	 * @param column 对应sql列，用作取数据
	 * @param order	排序  desc、asc 
	 * @param hiddle 是否隐藏列 false=显示， true ==影藏
	 * @param ischeckbox 是否为  复选框列 false=不为复选框列， true ==为复选框列
	 * @param width 列宽度
	 * @param isorder 是否有排序功能
	 */
	public PageColumn(String title, String column, String order, Boolean hiddle,Boolean ischeckbox,String width,Boolean isorder) {
		this.title = title;
		this.column = column;
		this.order = order;
		this.hiddle = hiddle;
		this.ischeckbox = ischeckbox;
		this.width=width;
		this.isorder=isorder;
	}
	public PageColumn(String title, String column ) {
		this.title = title;
		this.column = column;
	}
	public PageColumn(){ }
	
	public String getTitle() {
		return title;
	}
	public PageColumn setTitle(String title) {
		this.title = title;
		return this;
	}
	public String getColumn() {
		return column;
	}
	public PageColumn setColumn(String column) {
		this.column = column;
		return this;
	}
	public String getOrder() {
		return order;
	}
	public PageColumn setOrder(String order) {
		this.order = order;
		return this;
	}
	public Boolean getHiddle() {
		return hiddle;
	}
	public Boolean getIscheckbox() {
		return ischeckbox;
	}
	public PageColumn setIscheckbox(Boolean ischeckbox) {
		this.ischeckbox = ischeckbox;
		return this;
	}
	public PageColumn setHiddle(Boolean hiddle) {
		this.hiddle = hiddle;
		return this;
	}
	public String getWidth() {
		return width;
	}
	public PageColumn setWidth(String width) {
		this.width = width;
		return this;
	}
	public Boolean getIsorder() {
		return isorder;
	}
	public PageColumn setIsorder(Boolean isorder) {
		this.isorder = isorder;
		return this;
	}
	public Boolean getIsoperation() {
		return isoperation;
	}
	public PageColumn setIsoperation(Boolean isoperation) {
		this.isoperation = isoperation;
		return this;
	}
	public String getCode() {
		return code;
	}
	public PageColumn setCode(String code) {
		this.code = code;
		return this;
	}
	public Boolean getIsshowcode() {
		return isshowcode;
	}
	public PageColumn setIsshowcode(Boolean isshowcode) {
		this.isshowcode = isshowcode;
		return this;
	}
	public Boolean getIscasewhen() {
		return iscasewhen;
	}
	public PageColumn setIscasewhen(Boolean iscasewhen) {
		this.iscasewhen = iscasewhen;
		return this;
	}
	public String getCases() {
		return cases;
	}
	public PageColumn setCases(String cases) {
		this.cases = cases;
		return this;
	}
	public Map<String, String> getWhen() {
		return when;
	}
	public PageColumn setWhen(Map<String, String> when) {
		this.when = when;
		return this;
	}
	public int getLength() {
		return length;
	}
	public PageColumn setLength(int length) {
		this.length = length;
		return this;
	}
	public Boolean getIsnum() {
		return isnum;
	}
	public PageColumn setIsnum(Boolean isnum) {
		this.isnum = isnum;
		return this;
	}
	public Boolean getIsSumColumn() {
		return isSumColumn;
	}
	public PageColumn setIsSumColumn(Boolean isSumColumn) {
		this.isSumColumn = isSumColumn;
		return this;
	}
	public Boolean getHiddle2() {
		return hiddle2;
	}
	public PageColumn setHiddle2(Boolean hiddle2) {
		this.hiddle2 = hiddle2;
		return this;
	}

}
