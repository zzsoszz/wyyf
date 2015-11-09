package com.wyyf.bean;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lys.utils.StringUtils;
import com.lys.utils.annotation.MyTable;

/**
 *  商品表
 * @author shuang
 */
@Entity
@Table(name = "bg_t_prodinfo")
@MyTable(tableName = "bg_t_prodinfo",pkName="bg_st_id",anotherName="bg")
public class Bg_t_prodinfo {
	@Id
	@Column(length =32)private String bg_st_id;//商品Id
	//(图片跟file表进行关联)
	@Column(length =200)private String bg_st_name;//商品名

	@Column(length =200)private String bg_st_color;//颜色
	@Column(length =200)private String bg_st_norms;//规格
	@Column(length =200)private String pp;//品牌
	@Column(length =200)private String xh;//型号
	@Column(length =200)private String cd;//产地
	@Column(length =200)private String cz;//材质
	
	@Column(length =32)private String bg_st_bbid;//商家 用户ID【FK】
	@Column(length =32)private String bg_st_randid;//类型ID（1=家具、2=建材、3=智能家居、4=软装配饰。码表自定义），5=购物券
	@Column(length =32)private String bg_st_num;//商品编号  YYYYMMDDHHSSMM000
	@Column(length =20)private double bg_st_pricezg;//商品职工价格
	@Column(length =20)private double bg_st_pricetj;//商品特价==抢购价
	@Column(length =10)private int bg_nm_fen;//商品积分--无用
	@Column(length =10)private int bg_nm_storeNum;//库存数量
	@Column(length =300)private String bg_st_summary;//商品摘要
	@Column(length =10000)private String bg_st_prodIntro;//商品介绍
	@Column(length =2)private String bg_st_fbtpe;//发布类型1.商城2.限时抢购 
	private Date bg_dt_startDate;//商品生效时间
	private Date bg_dt_endDate;//商品结束时间
	@Column(length =2)private String bg_st_enable;//是否发布 1、发布0下架
	@Column(length =2)private String bg_st_isdel;//是否删除 1、删除0未删除
	@Column(length =2)private String bg_st_state;//状态（置顶、热销、特推）--无用
	@Column(length =500)private String bg_st_remark;//备注，备用字段
	@Column(length =32)private String bg_st_addUserId;//创建人员ID  
	private Date bg_dt_addDate;//创建时间
	@Column(length =32)private String bg_st_updUserId;//修改人员ID 
	
	
	public String getPp() {
		return pp;
	}
	public void setPp(String pp) {
		this.pp = pp;
	}
	public String getBg_st_color() {
		return bg_st_color;
	}
	public void setBg_st_color(String bg_st_color) {
		this.bg_st_color = bg_st_color;
	}
	public String getBg_st_norms() {
		return bg_st_norms;
	}
	public void setBg_st_norms(String bg_st_norms) {
		this.bg_st_norms = bg_st_norms;
	}
	private Date bg_dt_updDate;//修改时间
	/**商品图片6张**/
	@Column(length =200)private String bg_st_img1;//图片1
	@Column(length =200)private String bg_st_img2;//图片2
	@Column(length =200)private String bg_st_img3;//图片3
	@Column(length =200)private String bg_st_img4;//图片4
	@Column(length =200)private String bg_st_img5;//图片5
	@Column(length =200)private String bg_st_img6;//图片6
	/**
	 * 构造函数
	 */
	public Bg_t_prodinfo() {}
	/**
	 * 自定义主键ID
	 * @param userid 主键ID
	 */
	public Bg_t_prodinfo(String bg_st_id) {
		this.bg_st_id = bg_st_id;
	}
	/**
	 * 是否自动创建id，ID长度为32UUID
	 * @param isAutoId true=是/false=否
	 */
	public Bg_t_prodinfo(boolean isAutoId) {
		if(isAutoId){
			this.bg_st_id =StringUtils.getUUID32();
		}
	}
	public String getBg_st_id() {
		return bg_st_id;
	}
	public void setBg_st_id(String bg_st_id) {
		this.bg_st_id = bg_st_id;
	}
	public String getBg_st_name() {
		return bg_st_name;
	}
	public void setBg_st_name(String bg_st_name) {
		this.bg_st_name = bg_st_name;
	}
	public String getBg_st_bbid() {
		return bg_st_bbid;
	}
	public void setBg_st_bbid(String bg_st_bbid) {
		this.bg_st_bbid = bg_st_bbid;
	}
	public String getBg_st_randid() {
		return bg_st_randid;
	}
	public void setBg_st_randid(String bg_st_randid) {
		this.bg_st_randid = bg_st_randid;
	}
	public String getBg_st_num() {
		return bg_st_num;
	}
	public void setBg_st_num(String bg_st_num) {
		this.bg_st_num = bg_st_num;
	}
	public double getBg_st_pricezg() {
		return bg_st_pricezg;
	}
	public void setBg_st_pricezg(double bg_st_pricezg) {
		this.bg_st_pricezg = bg_st_pricezg;
	}
	public double getBg_st_pricetj() {
		return bg_st_pricetj;
	}
	public void setBg_st_pricetj(double bg_st_pricetj) {
		this.bg_st_pricetj = bg_st_pricetj;
	}
	public int getBg_nm_fen() {
		return bg_nm_fen;
	}
	public void setBg_nm_fen(int bg_nm_fen) {
		this.bg_nm_fen = bg_nm_fen;
	}
	public int getBg_nm_storeNum() {
		return bg_nm_storeNum;
	}
	public void setBg_nm_storeNum(int bg_nm_storeNum) {
		this.bg_nm_storeNum = bg_nm_storeNum;
	}
	public String getBg_st_summary() {
		return bg_st_summary;
	}
	public void setBg_st_summary(String bg_st_summary) {
		this.bg_st_summary = bg_st_summary;
	}
	public String getBg_st_prodIntro() {
		return bg_st_prodIntro;
	}
	public void setBg_st_prodIntro(String bg_st_prodIntro) {
		this.bg_st_prodIntro = bg_st_prodIntro;
	}
	public String getBg_st_fbtpe() {
		return bg_st_fbtpe;
	}
	public void setBg_st_fbtpe(String bg_st_fbtpe) {
		this.bg_st_fbtpe = bg_st_fbtpe;
	}
	public Date getBg_dt_startDate() {
		return bg_dt_startDate;
	}
	public void setBg_dt_startDate(Date bg_dt_startDate) {
		this.bg_dt_startDate = bg_dt_startDate;
	}
	public Date getBg_dt_endDate() {
		return bg_dt_endDate;
	}
	public void setBg_dt_endDate(Date bg_dt_endDate) {
		this.bg_dt_endDate = bg_dt_endDate;
	}
	public String getBg_st_enable() {
		return bg_st_enable;
	}
	public void setBg_st_enable(String bg_st_enable) {
		this.bg_st_enable = bg_st_enable;
	}
	public String getBg_st_isdel() {
		return bg_st_isdel;
	}
	public void setBg_st_isdel(String bg_st_isdel) {
		this.bg_st_isdel = bg_st_isdel;
	}
	public String getBg_st_state() {
		return bg_st_state;
	}
	public void setBg_st_state(String bg_st_state) {
		this.bg_st_state = bg_st_state;
	}
	public String getBg_st_remark() {
		return bg_st_remark;
	}
	public void setBg_st_remark(String bg_st_remark) {
		this.bg_st_remark = bg_st_remark;
	}
	public String getBg_st_addUserId() {
		return bg_st_addUserId;
	}
	public void setBg_st_addUserId(String bg_st_addUserId) {
		this.bg_st_addUserId = bg_st_addUserId;
	}
	public Date getBg_dt_addDate() {
		return bg_dt_addDate;
	}
	public void setBg_dt_addDate(Date bg_dt_addDate) {
		this.bg_dt_addDate = bg_dt_addDate;
	}
	public String getBg_st_updUserId() {
		return bg_st_updUserId;
	}
	public void setBg_st_updUserId(String bg_st_updUserId) {
		this.bg_st_updUserId = bg_st_updUserId;
	}
	public Date getBg_dt_updDate() {
		return bg_dt_updDate;
	}
	public void setBg_dt_updDate(Date bg_dt_updDate) {
		this.bg_dt_updDate = bg_dt_updDate;
	}
	public String getBg_st_img1() {
		return bg_st_img1;
	}
	public void setBg_st_img1(String bg_st_img1) {
		this.bg_st_img1 = bg_st_img1;
	}
	public String getBg_st_img2() {
		return bg_st_img2;
	}
	public void setBg_st_img2(String bg_st_img2) {
		this.bg_st_img2 = bg_st_img2;
	}
	public String getBg_st_img3() {
		return bg_st_img3;
	}
	public void setBg_st_img3(String bg_st_img3) {
		this.bg_st_img3 = bg_st_img3;
	}
	public String getBg_st_img4() {
		return bg_st_img4;
	}
	public void setBg_st_img4(String bg_st_img4) {
		this.bg_st_img4 = bg_st_img4;
	}
	public String getBg_st_img5() {
		return bg_st_img5;
	}
	public void setBg_st_img5(String bg_st_img5) {
		this.bg_st_img5 = bg_st_img5;
	}
	public String getBg_st_img6() {
		return bg_st_img6;
	}
	public void setBg_st_img6(String bg_st_img6) {
		this.bg_st_img6 = bg_st_img6;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getCd() {
		return cd;
	}
	public void setCd(String cd) {
		this.cd = cd;
	}
	public String getCz() {
		return cz;
	}
	public void setCz(String cz) {
		this.cz = cz;
	}
	
}
