package com.wyyf.bean;


import java.io.Serializable;
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
 *  订单表
 * @author shuang
 */
@Entity
@Table(name = "bh_t_orderform")
@MyTable(tableName = "bh_t_orderform",pkName="bh_st_id",anotherName="bh")
public class Bh_t_orderform implements Serializable {
	/***参数------start****/
	@Transient private List<Bi_t_OrderProdRelate> prodList;//商品List
	/***参数------end****/
	private static final long serialVersionUID = 1000000206L;
	
	
	@Id
	@Column(length =32)private String bh_st_id;//主键Id
	@Column(length =32)private String bh_st_bbid;//商家用户ID【FK】--每个订单属于一ae用户
	@Column(length =32)private String bh_st_ddnum;//订单编号  D+YYYYMMDDHHSSMM000
	@Column(length =20)private double bh_st_spprice;//商品总价格
	@Column(length =32)private String bh_st_psry;//配送人员
	@Column(length =3)private String bh_st_ddstate;//订单状态 //1待支付、2待配送、3配送中（待收货）、4已完成（待评价） 5.退款zhong  6.已退款   7.已支付   8、申请退款
	@Column(length =500)private String bh_st_ddremark;//订单备注 (客户建议)
	@Column(length =1)private String bh_st_del;//是否删除
	@Column(length =1)private String bh_st_source;//订单来源1.app2.pc端
	@Column(length =1)private String payType;//支付类型
	@Column(length =32)private String bh_st_memberId;//下单人会员ID
	
	@Column(length =50)private String chargeId;//chargeId
	@Column(length =50)private String bh_st_shname;//收货人姓名
	@Column(length =15)private String bh_st_shphone;//收货人电话
	@Column(length =100)private String bh_st_shaddress;//收货人地址
	
	@Column(length =500)private String bh_st_remark;//备注，备用字段  
	@Column(length =32)private String bh_st_addUserId;//创建人员ID
	private Date bh_dt_addDate;//创建时间
	@Column(length =32)private String bh_st_updUserId;//修改人员ID 
	@Column(length =32)private String payNo;//支付订单号
	private Date bh_dt_updDate;//修改时间
	
	
	
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getPayNo() {
		return payNo;
	}
	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}
	public String getChargeId() {
		return chargeId;
	}
	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}
	/**
	 * 构造函数
	 */
	public Bh_t_orderform() {}
	/**
	 * 自定义主键ID
	 * @param userid 主键ID
	 */
	public Bh_t_orderform(String bh_st_id) {
		this.bh_st_id = bh_st_id;
	}
	/**
	 * 是否自动创建id，ID长度为32UUID
	 * @param isAutoId true=是/false=否
	 */
	public Bh_t_orderform(boolean isAutoId) {
		if(isAutoId){
			this.bh_st_id =StringUtils.getUUID32();
		}
	}
	public List<Bi_t_OrderProdRelate> getProdList() {
		return prodList;
	}
	public void setProdList(List<Bi_t_OrderProdRelate> prodList) {
		this.prodList = prodList;
	}
	public String getBh_st_id() {
		return bh_st_id;
	}
	public void setBh_st_id(String bh_st_id) {
		this.bh_st_id = bh_st_id;
	}
	public String getBh_st_ddnum() {
		return bh_st_ddnum;
	}
	public void setBh_st_ddnum(String bh_st_ddnum) {
		this.bh_st_ddnum = bh_st_ddnum;
	}
	public double getBh_st_spprice() {
		return bh_st_spprice;
	}
	public void setBh_st_spprice(double bh_st_spprice) {
		this.bh_st_spprice = bh_st_spprice;
	}
	public String getBh_st_psry() {
		return bh_st_psry;
	}
	public void setBh_st_psry(String bh_st_psry) {
		this.bh_st_psry = bh_st_psry;
	}
	public String getBh_st_ddstate() {
		return bh_st_ddstate;
	}
	public void setBh_st_ddstate(String bh_st_ddstate) {
		this.bh_st_ddstate = bh_st_ddstate;
	}
	public String getBh_st_ddremark() {
		return bh_st_ddremark;
	}
	public void setBh_st_ddremark(String bh_st_ddremark) {
		this.bh_st_ddremark = bh_st_ddremark;
	}
	public String getBh_st_del() {
		return bh_st_del;
	}
	public void setBh_st_del(String bh_st_del) {
		this.bh_st_del = bh_st_del;
	}
	public String getBh_st_source() {
		return bh_st_source;
	}
	public void setBh_st_source(String bh_st_source) {
		this.bh_st_source = bh_st_source;
	}
	public String getBh_st_memberId() {
		return bh_st_memberId;
	}
	public void setBh_st_memberId(String bh_st_memberId) {
		this.bh_st_memberId = bh_st_memberId;
	}
	public String getBh_st_shname() {
		return bh_st_shname;
	}
	public void setBh_st_shname(String bh_st_shname) {
		this.bh_st_shname = bh_st_shname;
	}
	public String getBh_st_shphone() {
		return bh_st_shphone;
	}
	public void setBh_st_shphone(String bh_st_shphone) {
		this.bh_st_shphone = bh_st_shphone;
	}
	public String getBh_st_shaddress() {
		return bh_st_shaddress;
	}
	public void setBh_st_shaddress(String bh_st_shaddress) {
		this.bh_st_shaddress = bh_st_shaddress;
	}
	public String getBh_st_remark() {
		return bh_st_remark;
	}
	public void setBh_st_remark(String bh_st_remark) {
		this.bh_st_remark = bh_st_remark;
	}
	public String getBh_st_addUserId() {
		return bh_st_addUserId;
	}
	public void setBh_st_addUserId(String bh_st_addUserId) {
		this.bh_st_addUserId = bh_st_addUserId;
	}
	public Date getBh_dt_addDate() {
		return bh_dt_addDate;
	}
	public void setBh_dt_addDate(Date bh_dt_addDate) {
		this.bh_dt_addDate = bh_dt_addDate;
	}
	public String getBh_st_updUserId() {
		return bh_st_updUserId;
	}
	public void setBh_st_updUserId(String bh_st_updUserId) {
		this.bh_st_updUserId = bh_st_updUserId;
	}
	public Date getBh_dt_updDate() {
		return bh_dt_updDate;
	}
	public void setBh_dt_updDate(Date bh_dt_updDate) {
		this.bh_dt_updDate = bh_dt_updDate;
	}
	public String getBh_st_bbid() {
		return bh_st_bbid;
	}
	public void setBh_st_bbid(String bh_st_bbid) {
		this.bh_st_bbid = bh_st_bbid;
	}
 
}
