package com.wyyf.bean;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lys.utils.StringUtils;
import com.lys.utils.annotation.MyTable;

/**
 *  收货人常用地址表（会员下单常用地址表）每个人最多记录3个常用地址
 * @author shuang
 */
@Entity
@Table(name = "bq_t_usefuladdress")
@MyTable(tableName = "bq_t_usefuladdress",pkName="bq_st_id",anotherName="bq")
public class Bq_t_UsefulAddress {
	@Id
	@Column(length =32)private String bq_st_id;//主键Id
	@Column(length =1)private String bq_st_ismr;//是否默认
	@Column(length =20)private String bq_st_name;//收货人姓名
	@Column(length =15)private String bq_st_phone;//收货人电话
	@Column(length =50)private String bq_st_sheng;//省
	@Column(length =100)private String bq_st_shi;//市
	@Column(length =100)private String bq_st_xian;//县/区
	@Column(length =200)private String bq_st_adress;//详细地址
	@Column(length =32)private String bq_st_memberid;//归属会员ID
	@Column(length =500)private String bq_st_remark;//备注，备用字段
	@Column(length =32)private String bq_st_addUserId;//创建人员ID  
	private Date bq_dt_addDate;//创建时间
	@Column(length =32)private String bq_st_updUserId;//修改人员ID 
	private Date bq_dt_updDate;//修改时间
	
	/**
	 * 构造函数
	 */
	public Bq_t_UsefulAddress() {}
	/**
	 * 自定义主键ID
	 * @param userid 主键ID
	 */
	public Bq_t_UsefulAddress(String bq_st_id) {
		this.bq_st_id = bq_st_id;
	}
	/**
	 * 是否自动创建id，ID长度为32UUID
	 * @param isAutoId true=是/false=否
	 */
	public Bq_t_UsefulAddress(boolean isAutoId) {
		if(isAutoId){
			this.bq_st_id =StringUtils.getUUID32();
		}
	}
	public String getBq_st_id() {
		return bq_st_id;
	}
	public void setBq_st_id(String bq_st_id) {
		this.bq_st_id = bq_st_id;
	}
	public String getBq_st_memberid() {
		return bq_st_memberid;
	}
	public void setBq_st_memberid(String bq_st_memberid) {
		this.bq_st_memberid = bq_st_memberid;
	}
	public String getBq_st_ismr() {
		return bq_st_ismr;
	}
	public void setBq_st_ismr(String bq_st_ismr) {
		this.bq_st_ismr = bq_st_ismr;
	}
	public String getBq_st_name() {
		return bq_st_name;
	}
	public void setBq_st_name(String bq_st_name) {
		this.bq_st_name = bq_st_name;
	}
	public String getBq_st_phone() {
		return bq_st_phone;
	}
	public void setBq_st_phone(String bq_st_phone) {
		this.bq_st_phone = bq_st_phone;
	}
	public String getBq_st_sheng() {
		return bq_st_sheng;
	}
	public void setBq_st_sheng(String bq_st_sheng) {
		this.bq_st_sheng = bq_st_sheng;
	}
	public String getBq_st_shi() {
		return bq_st_shi;
	}
	public void setBq_st_shi(String bq_st_shi) {
		this.bq_st_shi = bq_st_shi;
	}
	public String getBq_st_xian() {
		return bq_st_xian;
	}
	public void setBq_st_xian(String bq_st_xian) {
		this.bq_st_xian = bq_st_xian;
	}
	public String getBq_st_adress() {
		return bq_st_adress;
	}
	public void setBq_st_adress(String bq_st_adress) {
		this.bq_st_adress = bq_st_adress;
	}
	public String getBq_st_remark() {
		return bq_st_remark;
	}
	public void setBq_st_remark(String bq_st_remark) {
		this.bq_st_remark = bq_st_remark;
	}
	public String getBq_st_addUserId() {
		return bq_st_addUserId;
	}
	public void setBq_st_addUserId(String bq_st_addUserId) {
		this.bq_st_addUserId = bq_st_addUserId;
	}
	public Date getBq_dt_addDate() {
		return bq_dt_addDate;
	}
	public void setBq_dt_addDate(Date bq_dt_addDate) {
		this.bq_dt_addDate = bq_dt_addDate;
	}
	public String getBq_st_updUserId() {
		return bq_st_updUserId;
	}
	public void setBq_st_updUserId(String bq_st_updUserId) {
		this.bq_st_updUserId = bq_st_updUserId;
	}
	public Date getBq_dt_updDate() {
		return bq_dt_updDate;
	}
	public void setBq_dt_updDate(Date bq_dt_updDate) {
		this.bq_dt_updDate = bq_dt_updDate;
	}
	 
	
}
