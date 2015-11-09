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
 *  文章内容表 （帮助中心） 
 * @author shuang
 */
@Entity
@Table(name = "bl_t_noticecontext")
@MyTable(tableName = "bl_t_noticecontext",pkName="bl_st_id",anotherName="bl")
public class Bl_t_noticecontext implements Serializable {
	private static final long serialVersionUID = 1000000212L;
	@Id
	@Column(length =32)private String bl_st_id;//ID  
	@Column(length =200)private String bl_st_title;//标题
	@Column(length =500)private String bl_st_summary;//内容摘要
	@Column(length =10000)private String bl_st_context;//发送内容
	@Column(length =2)private String bl_st_isSend;//是否发布
	@Column(length =2)private String bl_st_type;//类型：1.热门话题2.米奇帮3.装修日记4.装修经验5.活动回顾6.关于我们 
	@Column(length =500)private String bl_st_remark;//备注，备用字段
	@Column(length =32)private String bl_st_addUserId;//创建人员ID  
	private Date bl_dt_addDate;//创建时间
	@Column(length =32)private String bl_st_updUserId;//修改人员ID 
	private Date bl_dt_updDate;//修改时间
	
	/**
	 * 构造函数
	 */
	public Bl_t_noticecontext() {}
	/**
	 * 自定义主键ID
	 * @param userid 主键ID
	 */
	public Bl_t_noticecontext(String bl_st_id) {
		this.bl_st_id = bl_st_id;
	}
	/**
	 * 是否自动创建id，ID长度为32UUID
	 * @param isAutoId true=是/false=否
	 */
	public Bl_t_noticecontext(boolean isAutoId) {
		if(isAutoId){
			this.bl_st_id =StringUtils.getUUID32();
		}
	}
	public String getBl_st_id() {
		return bl_st_id;
	}
	public void setBl_st_id(String bl_st_id) {
		this.bl_st_id = bl_st_id;
	}
	public String getBl_st_title() {
		return bl_st_title;
	}
	public void setBl_st_title(String bl_st_title) {
		this.bl_st_title = bl_st_title;
	}
	public String getBl_st_summary() {
		return bl_st_summary;
	}
	public void setBl_st_summary(String bl_st_summary) {
		this.bl_st_summary = bl_st_summary;
	}
	public String getBl_st_context() {
		return bl_st_context;
	}
	public void setBl_st_context(String bl_st_context) {
		this.bl_st_context = bl_st_context;
	}
	public String getBl_st_isSend() {
		return bl_st_isSend;
	}
	public void setBl_st_isSend(String bl_st_isSend) {
		this.bl_st_isSend = bl_st_isSend;
	}
	public String getBl_st_type() {
		return bl_st_type;
	}
	public void setBl_st_type(String bl_st_type) {
		this.bl_st_type = bl_st_type;
	}
	public String getBl_st_remark() {
		return bl_st_remark;
	}
	public void setBl_st_remark(String bl_st_remark) {
		this.bl_st_remark = bl_st_remark;
	}
	public String getBl_st_addUserId() {
		return bl_st_addUserId;
	}
	public void setBl_st_addUserId(String bl_st_addUserId) {
		this.bl_st_addUserId = bl_st_addUserId;
	}
	public Date getBl_dt_addDate() {
		return bl_dt_addDate;
	}
	public void setBl_dt_addDate(Date bl_dt_addDate) {
		this.bl_dt_addDate = bl_dt_addDate;
	}
	public String getBl_st_updUserId() {
		return bl_st_updUserId;
	}
	public void setBl_st_updUserId(String bl_st_updUserId) {
		this.bl_st_updUserId = bl_st_updUserId;
	}
	public Date getBl_dt_updDate() {
		return bl_dt_updDate;
	}
	public void setBl_dt_updDate(Date bl_dt_updDate) {
		this.bl_dt_updDate = bl_dt_updDate;
	}
	 
}
