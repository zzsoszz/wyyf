package com.wyyf.bean;


import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.lys.utils.StringUtils;
import com.lys.utils.annotation.MyTable;

/**
 *  活动表
 * @author shuang
 */
@Entity
@Table(name = "bo_t_activity")
@MyTable(tableName = "bo_t_activity",pkName="bo_st_id",anotherName="bo")
public class Bo_t_Activity  {
	@Transient private List<Bp_t_ActivityProdRelate> prodList;//活动商品List
	@Id
	@Column(length =32)private String bo_st_id;//主键Id
	@Column(length =100)private String bo_st_title;//活动标题
	@Column(length =500)private String bo_st_ddremark;//活动主题 (客户建议)
	@Column(length =32)private String bo_st_ddnum;//活动编号  D+YYYYMMDDHHSSMM000
	@Column(length =3)private String bo_st_ddstate;//活动是否发布 // 1=是 0=否
	@Column(length =20)private double bo_st_spprice;//活动商品总价格
	@Column(length =1)private String bo_st_del;//是否删除
	private Date bo_dt_startDate;//活动生效时间
	private Date bo_dt_endDate;//活动结束时间
	@Column(length =500)private String bo_st_remark;//备注，备用字段
	@Column(length =32)private String bo_st_addUserId;//创建人员ID  
	private Date bo_dt_addDate;//创建时间
	@Column(length =32)private String bo_st_updUserId;//修改人员ID 
	private Date bo_dt_updDate;//修改时间
	
	/**
	 * 构造函数
	 */
	public Bo_t_Activity() {}
	/**
	 * 自定义主键ID
	 * @param userid 主键ID
	 */
	public Bo_t_Activity(String bo_st_id) {
		this.bo_st_id = bo_st_id;
	}
	/**
	 * 是否自动创建id，ID长度为32UUID
	 * @param isAutoId true=是/false=否
	 */
	public Bo_t_Activity(boolean isAutoId) {
		if(isAutoId){
			this.bo_st_id =StringUtils.getUUID32();
		}
	}
	public String getBo_st_id() {
		return bo_st_id;
	}
	public void setBo_st_id(String bo_st_id) {
		this.bo_st_id = bo_st_id;
	}
	public String getBo_st_ddnum() {
		return bo_st_ddnum;
	}
	public void setBo_st_ddnum(String bo_st_ddnum) {
		this.bo_st_ddnum = bo_st_ddnum;
	}
	public double getBo_st_spprice() {
		return bo_st_spprice;
	}
	public void setBo_st_spprice(double bo_st_spprice) {
		this.bo_st_spprice = bo_st_spprice;
	}
	public String getBo_st_ddstate() {
		return bo_st_ddstate;
	}
	public void setBo_st_ddstate(String bo_st_ddstate) {
		this.bo_st_ddstate = bo_st_ddstate;
	}
	public String getBo_st_ddremark() {
		return bo_st_ddremark;
	}
	public void setBo_st_ddremark(String bo_st_ddremark) {
		this.bo_st_ddremark = bo_st_ddremark;
	}
	public String getBo_st_del() {
		return bo_st_del;
	}
	public void setBo_st_del(String bo_st_del) {
		this.bo_st_del = bo_st_del;
	}
	public String getBo_st_remark() {
		return bo_st_remark;
	}
	public void setBo_st_remark(String bo_st_remark) {
		this.bo_st_remark = bo_st_remark;
	}
	public String getBo_st_addUserId() {
		return bo_st_addUserId;
	}
	public void setBo_st_addUserId(String bo_st_addUserId) {
		this.bo_st_addUserId = bo_st_addUserId;
	}
	public Date getBo_dt_addDate() {
		return bo_dt_addDate;
	}
	public void setBo_dt_addDate(Date bo_dt_addDate) {
		this.bo_dt_addDate = bo_dt_addDate;
	}
	public String getBo_st_updUserId() {
		return bo_st_updUserId;
	}
	public void setBo_st_updUserId(String bo_st_updUserId) {
		this.bo_st_updUserId = bo_st_updUserId;
	}
	public Date getBo_dt_updDate() {
		return bo_dt_updDate;
	}
	public void setBo_dt_updDate(Date bo_dt_updDate) {
		this.bo_dt_updDate = bo_dt_updDate;
	}
	public Date getBo_dt_startDate() {
		return bo_dt_startDate;
	}
	public void setBo_dt_startDate(Date bo_dt_startDate) {
		this.bo_dt_startDate = bo_dt_startDate;
	}
	public Date getBo_dt_endDate() {
		return bo_dt_endDate;
	}
	public void setBo_dt_endDate(Date bo_dt_endDate) {
		this.bo_dt_endDate = bo_dt_endDate;
	}
	public String getBo_st_title() {
		return bo_st_title;
	}
	public void setBo_st_title(String bo_st_title) {
		this.bo_st_title = bo_st_title;
	}
	public List<Bp_t_ActivityProdRelate> getProdList() {
		return prodList;
	}
	public void setProdList(List<Bp_t_ActivityProdRelate> prodList) {
		this.prodList = prodList;
	}
	 
}
