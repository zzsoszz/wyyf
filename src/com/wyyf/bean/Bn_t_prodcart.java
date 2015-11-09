package com.wyyf.bean;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lys.utils.StringUtils;
import com.lys.utils.annotation.MyTable;

/**
 *  商品购物车表
 * @author shuang
 */
@Entity
@Table(name = "bn_t_prodcart")
@MyTable(tableName = "bn_t_prodcart",pkName="bn_st_id",anotherName="bn")
public class Bn_t_prodcart  {
	@Id
	@Column(length =32)private String bn_st_id;//主键Id
	@Column(length =32)private String bn_st_memberid;//会员ID
	@Column(length =32)private String bn_st_prodid;//商品ID
	@Column(length =10)private int bn_st_prodnum;//商品数量
	@Column(length =500)private String bn_st_remark;//备注，备用字段
	@Column(length =32)private String bn_st_addUserId;//创建人员ID  
	private Date bn_dt_addDate;//创建时间
	@Column(length =32)private String bn_st_updUserId;//修改人员ID 
	private Date bn_dt_updDate;//修改时间
	
	/**
	 * 构造函数
	 */
	public Bn_t_prodcart() {}
	/**
	 * 自定义主键ID
	 * @param userid 主键ID
	 */
	public Bn_t_prodcart(String bn_st_id) {
		this.bn_st_id = bn_st_id;
	}
	/**
	 * 是否自动创建id，ID长度为32UUID
	 * @param isAutoId true=是/false=否
	 */
	public Bn_t_prodcart(boolean isAutoId) {
		if(isAutoId){
			this.bn_st_id =StringUtils.getUUID32();
		}
	}
	public String getBn_st_id() {
		return bn_st_id;
	}
	public void setBn_st_id(String bn_st_id) {
		this.bn_st_id = bn_st_id;
	}
	public String getBn_st_memberid() {
		return bn_st_memberid;
	}
	public void setBn_st_memberid(String bn_st_memberid) {
		this.bn_st_memberid = bn_st_memberid;
	}
	public String getBn_st_prodid() {
		return bn_st_prodid;
	}
	public void setBn_st_prodid(String bn_st_prodid) {
		this.bn_st_prodid = bn_st_prodid;
	}
	public int getBn_st_prodnum() {
		return bn_st_prodnum;
	}
	public void setBn_st_prodnum(int bn_st_prodnum) {
		this.bn_st_prodnum = bn_st_prodnum;
	}
	public String getBn_st_remark() {
		return bn_st_remark;
	}
	public void setBn_st_remark(String bn_st_remark) {
		this.bn_st_remark = bn_st_remark;
	}
	public String getBn_st_addUserId() {
		return bn_st_addUserId;
	}
	public void setBn_st_addUserId(String bn_st_addUserId) {
		this.bn_st_addUserId = bn_st_addUserId;
	}
	public Date getBn_dt_addDate() {
		return bn_dt_addDate;
	}
	public void setBn_dt_addDate(Date bn_dt_addDate) {
		this.bn_dt_addDate = bn_dt_addDate;
	}
	public String getBn_st_updUserId() {
		return bn_st_updUserId;
	}
	public void setBn_st_updUserId(String bn_st_updUserId) {
		this.bn_st_updUserId = bn_st_updUserId;
	}
	public Date getBn_dt_updDate() {
		return bn_dt_updDate;
	}
	public void setBn_dt_updDate(Date bn_dt_updDate) {
		this.bn_dt_updDate = bn_dt_updDate;
	}
	 
}
