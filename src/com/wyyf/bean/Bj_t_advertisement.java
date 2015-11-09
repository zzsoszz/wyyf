package com.wyyf.bean;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lys.utils.StringUtils;
import com.lys.utils.annotation.MyTable;

/**
 *  图片表 （存储各种广告的 ）
 * @author shuang
 */
@Entity
@Table(name = "bj_t_advertisement")
@MyTable(tableName = "bj_t_advertisement",pkName="bj_st_id",anotherName="bj")
public class Bj_t_advertisement implements Serializable {
	private static final long serialVersionUID = 1000000210L;
	@Id
	@Column(length =32)private String bj_st_id;//ID  
	@Column(length =3)private String bj_st_type;//类型：1.首页大广告 2.精品推荐,3品牌特卖 4家居商城
	@Column(length =100)private String bj_st_picurl;//类型：1.首页大广告 2.精品推荐
	@Column(length =400)private String bj_st_clickurl;//文件点击商品链接（主要针对图片）
	@Column(length =1)private String bj_st_enable;//是否启用 
	
	@Column(length =4)private int bj_nm_orderno;//序号length="4"*
	@Column(length =200)private String bj_st_title;//标题
	@Column(length =500)private String bj_st_summary;//内容摘要
	@Column(length =1000)private String bj_st_context;//发送内容
	
	@Column(length =500)private String bj_st_remark;//备注，备用字段
	@Column(length =32)private String bj_st_addUserId;//创建人员ID  
	private Date bj_dt_addDate;//创建时间
	@Column(length =32)private String bj_st_updUserId;//修改人员ID 
	private Date bj_dt_updDate;//修改时间
	
	/**
	 * 构造函数
	 */
	public Bj_t_advertisement() {}
	/**
	 * 自定义主键ID
	 * @param userid 主键ID
	 */
	public Bj_t_advertisement(String bj_st_id) {
		this.bj_st_id = bj_st_id;
	}
	/**
	 * 是否自动创建id，ID长度为32UUID
	 * @param isAutoId true=是/false=否
	 */
	public Bj_t_advertisement(boolean isAutoId) {
		if(isAutoId){
			this.bj_st_id =StringUtils.getUUID32();
		}
	}
	public String getBj_st_id() {
		return bj_st_id;
	}
	public void setBj_st_id(String bj_st_id) {
		this.bj_st_id = bj_st_id;
	}
	public String getBj_st_type() {
		return bj_st_type;
	}
	public void setBj_st_type(String bj_st_type) {
		this.bj_st_type = bj_st_type;
	}
	public String getBj_st_clickurl() {
		return bj_st_clickurl;
	}
	public void setBj_st_clickurl(String bj_st_clickurl) {
		this.bj_st_clickurl = bj_st_clickurl;
	}
	public String getBj_st_enable() {
		return bj_st_enable;
	}
	public void setBj_st_enable(String bj_st_enable) {
		this.bj_st_enable = bj_st_enable;
	}
	public int getBj_nm_orderno() {
		return bj_nm_orderno;
	}
	public void setBj_nm_orderno(int bj_nm_orderno) {
		this.bj_nm_orderno = bj_nm_orderno;
	}
	public String getBj_st_title() {
		return bj_st_title;
	}
	public void setBj_st_title(String bj_st_title) {
		this.bj_st_title = bj_st_title;
	}
	public String getBj_st_summary() {
		return bj_st_summary;
	}
	public void setBj_st_summary(String bj_st_summary) {
		this.bj_st_summary = bj_st_summary;
	}
	public String getBj_st_context() {
		return bj_st_context;
	}
	public void setBj_st_context(String bj_st_context) {
		this.bj_st_context = bj_st_context;
	}
	public String getBj_st_remark() {
		return bj_st_remark;
	}
	public void setBj_st_remark(String bj_st_remark) {
		this.bj_st_remark = bj_st_remark;
	}
	public String getBj_st_addUserId() {
		return bj_st_addUserId;
	}
	public void setBj_st_addUserId(String bj_st_addUserId) {
		this.bj_st_addUserId = bj_st_addUserId;
	}
	public Date getBj_dt_addDate() {
		return bj_dt_addDate;
	}
	public void setBj_dt_addDate(Date bj_dt_addDate) {
		this.bj_dt_addDate = bj_dt_addDate;
	}
	public String getBj_st_updUserId() {
		return bj_st_updUserId;
	}
	public void setBj_st_updUserId(String bj_st_updUserId) {
		this.bj_st_updUserId = bj_st_updUserId;
	}
	public Date getBj_dt_updDate() {
		return bj_dt_updDate;
	}
	public void setBj_dt_updDate(Date bj_dt_updDate) {
		this.bj_dt_updDate = bj_dt_updDate;
	}
	public String getBj_st_picurl() {
		return bj_st_picurl;
	}
	public void setBj_st_picurl(String bj_st_picurl) {
		this.bj_st_picurl = bj_st_picurl;
	}
}
