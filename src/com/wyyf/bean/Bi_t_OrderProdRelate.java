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
 *  订单商品关系表（商品订单多对多）
 * @author shuang
 */
@Entity
@Table(name = "bi_t_orderprodrelate")
@MyTable(tableName = "bi_t_orderprodrelate",pkName="bi_st_id",anotherName="bi")
public class Bi_t_OrderProdRelate implements Serializable {
	private static final long serialVersionUID = 1000000207L;
	@Id
	@Column(length =32)private String bi_st_id;//主键Id
	@Column(length =32)private String bi_st_ddnum;//订单编号 
	@Column(length =32)private String bi_st_bbid;//商家用户ID【FK】--每个订单属于一ae用户
	@Column(length =32)private String bi_st_spnum;//商品编号  
	@Column(length =20)private double bi_st_spprice;//商品价格（下单时候的商品价格）
	@Column(length =10)private int bi_st_spsl;//商品数量
	@Column(length =500)private String bi_st_remark;//备注，备用字段
	@Column(length =32)private String bi_st_addUserId;//创建人员ID  
	private Date bi_dt_addDate;//创建时间
	@Column(length =32)private String bi_st_updUserId;//修改人员ID 
	private Date bi_dt_updDate;//修改时间
	
	/**
	 * 构造函数
	 */
	public Bi_t_OrderProdRelate() {}
	/**
	 * 自定义主键ID
	 * @param userid 主键ID
	 */
	public Bi_t_OrderProdRelate(String bi_st_id) {
		this.bi_st_id = bi_st_id;
	}
	/**
	 * 是否自动创建id，ID长度为32UUID
	 * @param isAutoId true=是/false=否
	 */
	public Bi_t_OrderProdRelate(boolean isAutoId) {
		if(isAutoId){
			this.bi_st_id =StringUtils.getUUID32();
		}
	}
	public String getBi_st_id() {
		return bi_st_id;
	}
	public void setBi_st_id(String bi_st_id) {
		this.bi_st_id = bi_st_id;
	}
	public String getBi_st_ddnum() {
		return bi_st_ddnum;
	}
	public void setBi_st_ddnum(String bi_st_ddnum) {
		this.bi_st_ddnum = bi_st_ddnum;
	}
	public String getBi_st_spnum() {
		return bi_st_spnum;
	}
	public void setBi_st_spnum(String bi_st_spnum) {
		this.bi_st_spnum = bi_st_spnum;
	}
	public double getBi_st_spprice() {
		return bi_st_spprice;
	}
	public void setBi_st_spprice(double bi_st_spprice) {
		this.bi_st_spprice = bi_st_spprice;
	}
	public int getBi_st_spsl() {
		return bi_st_spsl;
	}
	public void setBi_st_spsl(int bi_st_spsl) {
		this.bi_st_spsl = bi_st_spsl;
	}
	public String getBi_st_remark() {
		return bi_st_remark;
	}
	public void setBi_st_remark(String bi_st_remark) {
		this.bi_st_remark = bi_st_remark;
	}
	public String getBi_st_addUserId() {
		return bi_st_addUserId;
	}
	public void setBi_st_addUserId(String bi_st_addUserId) {
		this.bi_st_addUserId = bi_st_addUserId;
	}
	public Date getBi_dt_addDate() {
		return bi_dt_addDate;
	}
	public void setBi_dt_addDate(Date bi_dt_addDate) {
		this.bi_dt_addDate = bi_dt_addDate;
	}
	public String getBi_st_updUserId() {
		return bi_st_updUserId;
	}
	public void setBi_st_updUserId(String bi_st_updUserId) {
		this.bi_st_updUserId = bi_st_updUserId;
	}
	public Date getBi_dt_updDate() {
		return bi_dt_updDate;
	}
	public void setBi_dt_updDate(Date bi_dt_updDate) {
		this.bi_dt_updDate = bi_dt_updDate;
	}
	public String getBi_st_bbid() {
		return bi_st_bbid;
	}
	public void setBi_st_bbid(String bi_st_bbid) {
		this.bi_st_bbid = bi_st_bbid;
	}
	 
}
