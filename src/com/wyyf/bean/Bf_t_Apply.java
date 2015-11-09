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
import com.power.bean.Ag_t_file;
/**
 * 申请单表
 * @author Administrator
 */

@Entity
@Table(name = "bf_t_apply")
@MyTable(tableName = "bf_t_apply",pkName="bf_st_id",anotherName="bf")
public class Bf_t_Apply {
	@Transient private List<Ag_t_file> fileList;//图片List--申请的时候使用
	@Transient private List<Ag_t_file> file2List;//图片List--添加结果集的时候使用
	@Id
	@Column(length=32)private String bf_st_id;//主键ID
	@Column(length=50)private String bf_st_name;//姓名
	@Column(length=20)private String bf_st_tell;//电话
	private Date bf_dt_time;//预约时间（见面或沟通的大概时间）
	@Column(length=100)private String bf_st_address;//地址
	@Column(length=32)private String bf_st_housnum;//户数记录
	@Column(length=32)private String bf_st_area;//面积
	@Column(length=2)private String bf_st_type;//类型，1=一键入住2=验房免费预约3验房金盘预约4=平民设计预约
												//5个性设计预约6金盘设计预约7=工长预约8=师傅预约9=免费监理预约
												//10收费监理预约11=装修贷预约12=免费空气检测预约13=收费空气检测预约
												//14=报名申请
	@Column(length=1)private String bf_st_incity;//城内城外  1=城内 2=城外
	@Column(length=1)private String bf_st_isreal;//是否真实  1=是 0=否
	@Column(length=32)private String bf_st_userid;//申请人id ae【FK】（可以为空，预约可以是非系统用户的人员）
	@Column(length=32)private String bf_st_receiveid;//赴约 人 id ae【FK】
	@Column(length=32)private String mfjl;//是否免费监理 1表示监理 2表示不监理
	
	@Column(length =500)private String bf_st_remark;//备注，备用字段
	@Column(length =32)private String bf_st_addUserId;//创建人员ID  
	private Date bf_dt_addDate;//创建时间
	@Column(length =32)private String bf_st_updUserId;//修改人员ID 
	private Date bf_dt_updDate;//修改时间
	@Column(length =32)private String orderNo;//订单编号 
	@Column(length =50)private String chargeId;//chargeId
	@Column(length =32)private String payState;// 1、已支付  2、未支付3 已退款4\退款中
	@Column(length =32)private String pricheAll;//预约金额
	@Column(length =32)private String payNo;//支付订单号
	@Column(length =1)private String bh_st_source;//订单来源1.app2.pc端
	@Column(length =1)private String payType;//支付类型
	
	
	public String getBh_st_source() {
		return bh_st_source;
	}
	public void setBh_st_source(String bh_st_source) {
		this.bh_st_source = bh_st_source;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getChargeId() {
		return chargeId;
	}
	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}
	public String getPayNo() {
		return payNo;
	}
	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}
	public String getMfjl() {
		return mfjl;
	}
	public void setMfjl(String mfjl) {
		this.mfjl = mfjl;
	}
	public String getPayState() {
		return payState;
	}
	public void setPayState(String payState) {
		this.payState = payState;
	}
	public String getPricheAll() {
		return pricheAll;
	}
	public void setPricheAll(String pricheAll) {
		this.pricheAll = pricheAll;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	String yzm;//验证码
	
	
	public String getYzm() {
		return yzm;
	}
	public void setYzm(String yzm) {
		this.yzm = yzm;
	}
	/**
	 * 构造函数
	 */
	public Bf_t_Apply() {}
	/**
	 * 自定义主键ID
	 * @param bf_st_id 主键ID
	 */
	public Bf_t_Apply(String bf_st_id) {
		this.bf_st_id = bf_st_id;
	}
	/**
	 * 是否自动创建id，ID长度为32UUID
	 * @param isAutoId true=是/false=否
	 */
	public Bf_t_Apply(boolean isAutoId) {
		if(isAutoId){
			this.bf_st_id =StringUtils.getUUID32();
		}
	}
	public String getBf_st_id() {
		return bf_st_id;
	}
	public void setBf_st_id(String bf_st_id) {
		this.bf_st_id = bf_st_id;
	}
	public String getBf_st_name() {
		return bf_st_name;
	}
	public void setBf_st_name(String bf_st_name) {
		this.bf_st_name = bf_st_name;
	}
	public String getBf_st_tell() {
		return bf_st_tell;
	}
	public void setBf_st_tell(String bf_st_tell) {
		this.bf_st_tell = bf_st_tell;
	}
	public Date getBf_dt_time() {
		return bf_dt_time;
	}
	public void setBf_dt_time(Date bf_dt_time) {
		this.bf_dt_time = bf_dt_time;
	}
	public String getBf_st_address() {
		return bf_st_address;
	}
	public void setBf_st_address(String bf_st_address) {
		this.bf_st_address = bf_st_address;
	}
	public String getBf_st_housnum() {
		return bf_st_housnum;
	}
	public void setBf_st_housnum(String bf_st_housnum) {
		this.bf_st_housnum = bf_st_housnum;
	}
	public String getBf_st_area() {
		return bf_st_area;
	}
	public void setBf_st_area(String bf_st_area) {
		this.bf_st_area = bf_st_area;
	}
	public String getBf_st_type() {
		return bf_st_type;
	}
	public void setBf_st_type(String bf_st_type) {
		this.bf_st_type = bf_st_type;
	}
	public String getBf_st_incity() {
		return bf_st_incity;
	}
	public void setBf_st_incity(String bf_st_incity) {
		this.bf_st_incity = bf_st_incity;
	}
	public String getBf_st_isreal() {
		return bf_st_isreal;
	}
	public void setBf_st_isreal(String bf_st_isreal) {
		this.bf_st_isreal = bf_st_isreal;
	}
	public String getBf_st_userid() {
		return bf_st_userid;
	}
	public void setBf_st_userid(String bf_st_userid) {
		this.bf_st_userid = bf_st_userid;
	}
 
	public String getBf_st_remark() {
		return bf_st_remark;
	}
	public void setBf_st_remark(String bf_st_remark) {
		this.bf_st_remark = bf_st_remark;
	}
	public String getBf_st_addUserId() {
		return bf_st_addUserId;
	}
	public void setBf_st_addUserId(String bf_st_addUserId) {
		this.bf_st_addUserId = bf_st_addUserId;
	}
	public Date getBf_dt_addDate() {
		return bf_dt_addDate;
	}
	public void setBf_dt_addDate(Date bf_dt_addDate) {
		this.bf_dt_addDate = bf_dt_addDate;
	}
	public String getBf_st_updUserId() {
		return bf_st_updUserId;
	}
	public void setBf_st_updUserId(String bf_st_updUserId) {
		this.bf_st_updUserId = bf_st_updUserId;
	}
	public Date getBf_dt_updDate() {
		return bf_dt_updDate;
	}
	public void setBf_dt_updDate(Date bf_dt_updDate) {
		this.bf_dt_updDate = bf_dt_updDate;
	}
	public List<Ag_t_file> getFileList() {
		return fileList;
	}
	public void setFileList(List<Ag_t_file> fileList) {
		this.fileList = fileList;
	}
	public String getBf_st_receiveid() {
		return bf_st_receiveid;
	}
	public void setBf_st_receiveid(String bf_st_receiveid) {
		this.bf_st_receiveid = bf_st_receiveid;
	}
	public List<Ag_t_file> getFile2List() {
		return file2List;
	}
	public void setFile2List(List<Ag_t_file> file2List) {
		this.file2List = file2List;
	}
	
	
}

