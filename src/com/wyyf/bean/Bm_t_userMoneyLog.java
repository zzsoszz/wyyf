package com.wyyf.bean;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lys.utils.StringUtils;
import com.lys.utils.annotation.MyTable;

/**
 *  用户资金交易详细记录表
 * @author shuang
 */
@Entity
@Table(name = "bm_t_usermoneylog")
@MyTable(tableName = "bm_t_usermoneylog",pkName="bm_st_id",anotherName="bm")
public class Bm_t_userMoneyLog  {
	@Id
	@Column(length =32)private String bm_st_id;//Id
	@Column(length =32)private String bm_st_jsuserid;//接收用户ID【FK】--被处理的用户
	@Column(length =32)private String bm_st_fsuserid;//发送用户ID【FK】--操作人
	@Column(length =10)private double bm_nm_money;//本次交易金额
	@Column(length =1)private String bm_st_type;//交易类型 （1=预约支付、2=竞价花费）
	@Column(length =1)private String bm_st_operation;//操作类型 （1=充值、2=扣款 ）
	@Column(length =500)private String bm_st_remark;//备注，备用字段
	@Column(length =32)private String bm_st_addUserId;//创建人员ID  
	private Date bm_dt_addDate;//创建时间
	@Column(length =32)private String bm_st_updUserId;//修改人员ID 
	private Date bm_dt_updDate;//修改时间
	
	/**
	 * 构造函数
	 */
	public Bm_t_userMoneyLog() {}
	/**
	 * 自定义主键ID
	 * @param userid 主键ID
	 */
	public Bm_t_userMoneyLog(String bm_st_id) {
		this.bm_st_id = bm_st_id;
	}
	/**
	 * 是否自动创建id，ID长度为32UUID
	 * @param isAutoId true=是/false=否
	 */
	public Bm_t_userMoneyLog(boolean isAutoId) {
		if(isAutoId){
			this.bm_st_id =StringUtils.getUUID32();
		}
	}
	public String getBm_st_id() {
		return bm_st_id;
	}
	public void setBm_st_id(String bm_st_id) {
		this.bm_st_id = bm_st_id;
	}
	public String getBm_st_jsuserid() {
		return bm_st_jsuserid;
	}
	public void setBm_st_jsuserid(String bm_st_jsuserid) {
		this.bm_st_jsuserid = bm_st_jsuserid;
	}
	public String getBm_st_fsuserid() {
		return bm_st_fsuserid;
	}
	public void setBm_st_fsuserid(String bm_st_fsuserid) {
		this.bm_st_fsuserid = bm_st_fsuserid;
	}
	public double getBm_nm_money() {
		return bm_nm_money;
	}
	public void setBm_nm_money(double bm_nm_money) {
		this.bm_nm_money = bm_nm_money;
	}
	public String getBm_st_type() {
		return bm_st_type;
	}
	public void setBm_st_type(String bm_st_type) {
		this.bm_st_type = bm_st_type;
	}
	public String getBm_st_operation() {
		return bm_st_operation;
	}
	public void setBm_st_operation(String bm_st_operation) {
		this.bm_st_operation = bm_st_operation;
	}
	public String getBm_st_remark() {
		return bm_st_remark;
	}
	public void setBm_st_remark(String bm_st_remark) {
		this.bm_st_remark = bm_st_remark;
	}
	public String getBm_st_addUserId() {
		return bm_st_addUserId;
	}
	public void setBm_st_addUserId(String bm_st_addUserId) {
		this.bm_st_addUserId = bm_st_addUserId;
	}
	public Date getBm_dt_addDate() {
		return bm_dt_addDate;
	}
	public void setBm_dt_addDate(Date bm_dt_addDate) {
		this.bm_dt_addDate = bm_dt_addDate;
	}
	public String getBm_st_updUserId() {
		return bm_st_updUserId;
	}
	public void setBm_st_updUserId(String bm_st_updUserId) {
		this.bm_st_updUserId = bm_st_updUserId;
	}
	public Date getBm_dt_updDate() {
		return bm_dt_updDate;
	}
	public void setBm_dt_updDate(Date bm_dt_updDate) {
		this.bm_dt_updDate = bm_dt_updDate;
	}
	 
}
