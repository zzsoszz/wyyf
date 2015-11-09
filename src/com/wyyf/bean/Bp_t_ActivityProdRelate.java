package com.wyyf.bean;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lys.utils.StringUtils;
import com.lys.utils.annotation.MyTable;

/**
 *  活动商品关系表（商品活动多对多）
 * @author shuang
 */
@Entity
@Table(name = "bp_t_activityprodrelate")
@MyTable(tableName = "bp_t_activityprodrelate",pkName="bp_st_id",anotherName="bp")
public class Bp_t_ActivityProdRelate {
	@Id
	@Column(length =32)private String bp_st_id;//主键Id
	@Column(length =32)private String bp_st_ddnum;//活动编号 
	@Column(length =32)private String bp_st_spnum;//商品编号  
	@Column(length =20)private double bp_st_spprice;//活动商品价格（活动时候的商品价格）
	@Column(length =10)private int bp_st_spsl;//活动商品数量（库存）
	@Column(length =500)private String bp_st_remark;//备注，备用字段
	@Column(length =32)private String bp_st_addUserId;//创建人员ID  
	private Date bp_dt_addDate;//创建时间
	@Column(length =32)private String bp_st_updUserId;//修改人员ID 
	private Date bp_dt_updDate;//修改时间
	
	/**
	 * 构造函数
	 */
	public Bp_t_ActivityProdRelate() {}
	/**
	 * 自定义主键ID
	 * @param userid 主键ID
	 */
	public Bp_t_ActivityProdRelate(String bp_st_id) {
		this.bp_st_id = bp_st_id;
	}
	/**
	 * 是否自动创建id，ID长度为32UUID
	 * @param isAutoId true=是/false=否
	 */
	public Bp_t_ActivityProdRelate(boolean isAutoId) {
		if(isAutoId){
			this.bp_st_id =StringUtils.getUUID32();
		}
	}
	public String getBp_st_id() {
		return bp_st_id;
	}
	public void setBp_st_id(String bp_st_id) {
		this.bp_st_id = bp_st_id;
	}
	public String getBp_st_ddnum() {
		return bp_st_ddnum;
	}
	public void setBp_st_ddnum(String bp_st_ddnum) {
		this.bp_st_ddnum = bp_st_ddnum;
	}
	public String getBp_st_spnum() {
		return bp_st_spnum;
	}
	public void setBp_st_spnum(String bp_st_spnum) {
		this.bp_st_spnum = bp_st_spnum;
	}
	public double getBp_st_spprice() {
		return bp_st_spprice;
	}
	public void setBp_st_spprice(double bp_st_spprice) {
		this.bp_st_spprice = bp_st_spprice;
	}
	public int getBp_st_spsl() {
		return bp_st_spsl;
	}
	public void setBp_st_spsl(int bp_st_spsl) {
		this.bp_st_spsl = bp_st_spsl;
	}
	public String getBp_st_remark() {
		return bp_st_remark;
	}
	public void setBp_st_remark(String bp_st_remark) {
		this.bp_st_remark = bp_st_remark;
	}
	public String getBp_st_addUserId() {
		return bp_st_addUserId;
	}
	public void setBp_st_addUserId(String bp_st_addUserId) {
		this.bp_st_addUserId = bp_st_addUserId;
	}
	public Date getBp_dt_addDate() {
		return bp_dt_addDate;
	}
	public void setBp_dt_addDate(Date bp_dt_addDate) {
		this.bp_dt_addDate = bp_dt_addDate;
	}
	public String getBp_st_updUserId() {
		return bp_st_updUserId;
	}
	public void setBp_st_updUserId(String bp_st_updUserId) {
		this.bp_st_updUserId = bp_st_updUserId;
	}
	public Date getBp_dt_updDate() {
		return bp_dt_updDate;
	}
	public void setBp_dt_updDate(Date bp_dt_updDate) {
		this.bp_dt_updDate = bp_dt_updDate;
	}
	 
}
