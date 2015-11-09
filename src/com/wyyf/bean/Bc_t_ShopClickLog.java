package com.wyyf.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lys.utils.StringUtils;
import com.lys.utils.annotation.MyTable;
/**
 * 商家用户点击记录表--用户商家竞价 排名 的扣款记录
 * 每次点击都会扣除商家资金，但分每天每个IP来计算。
 * @author Administrator
 */

@Entity
@Table(name = "bc_t_shopclicklog")
@MyTable(tableName = "bc_t_shopclicklog",pkName="bc_st_id",anotherName="bc")
public class Bc_t_ShopClickLog {
	@Id
	@Column(length=32)private String bc_st_id;//主键ID
	@Column(length=32)private String bc_st_bbid;//bb_t_shopuser 【FK】商家用户表外键
	@Column(length=20)private String bc_st_ip;//点击IP
	
	@Column(length =500)private String bc_st_remark;//备注，备用字段
	@Column(length =32)private String bc_st_addUserId;//创建人员ID  
	private Date bc_dt_addDate;//创建时间
	@Column(length =32)private String bc_st_updUserId;//修改人员ID 
	/**
	 * 构造函数
	 */
	public Bc_t_ShopClickLog() {}
	/**
	 * 自定义主键ID
	 * @param ae_st_id 主键ID
	 */
	public Bc_t_ShopClickLog(String bc_st_id) {
		this.bc_st_id = bc_st_id;
	}
	/**
	 * 是否自动创建id，ID长度为32UUID
	 * @param isAutoId true=是/false=否
	 */
	public Bc_t_ShopClickLog(boolean isAutoId) {
		if(isAutoId){
			this.bc_st_id =StringUtils.getUUID32();
		}
	}
	public String getBc_st_id() {
		return bc_st_id;
	}
	public void setBc_st_id(String bc_st_id) {
		this.bc_st_id = bc_st_id;
	}
	public String getBc_st_bbid() {
		return bc_st_bbid;
	}
	public void setBc_st_bbid(String bc_st_bbid) {
		this.bc_st_bbid = bc_st_bbid;
	}
	public String getBc_st_ip() {
		return bc_st_ip;
	}
	public void setBc_st_ip(String bc_st_ip) {
		this.bc_st_ip = bc_st_ip;
	}
	public String getBc_st_remark() {
		return bc_st_remark;
	}
	public void setBc_st_remark(String bc_st_remark) {
		this.bc_st_remark = bc_st_remark;
	}
	public String getBc_st_addUserId() {
		return bc_st_addUserId;
	}
	public void setBc_st_addUserId(String bc_st_addUserId) {
		this.bc_st_addUserId = bc_st_addUserId;
	}
	public Date getBc_dt_addDate() {
		return bc_dt_addDate;
	}
	public void setBc_dt_addDate(Date bc_dt_addDate) {
		this.bc_dt_addDate = bc_dt_addDate;
	}
	public String getBc_st_updUserId() {
		return bc_st_updUserId;
	}
	public void setBc_st_updUserId(String bc_st_updUserId) {
		this.bc_st_updUserId = bc_st_updUserId;
	}
	 
}
