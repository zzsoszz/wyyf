package com.wyyf.action.wap;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ezmorph.bean.MorphDynaBean;

import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lys.mvc.action.BaseAjaxAction;
import com.lys.security.encryption.Encoder;
import com.lys.security.encryption.impl.MD5Encoder;
import com.lys.system.dictionary.SysStatic;
import com.lys.system.filter.SafetyFilter;
import com.lys.utils.BeanUtils;
import com.lys.utils.BizTransUtil;
import com.lys.utils.CommonUtil;
import com.lys.utils.DateConverUtil;
import com.lys.utils.JsonUtils;
import com.lys.utils.StringUtils;
import com.lys.utils.UploadFile;
import com.lys.utils.myexception.MyoneException;
import com.lys.utils.myexception.MytwoException;
import com.lys.utils.pagination.PageBean;
import com.pingplusplus.model.Event;
import com.pingplusplus.model.Webhooks;
import com.power.bean.Ae_t_sysuser;
import com.power.bean.Af_t_sysuserrole;
import com.power.bean.Ag_t_file;
import com.wyyf.action.dx.HttpSender;
import com.wyyf.action.gateway.MainAction;
import com.wyyf.bean.Be_t_Assess;
import com.wyyf.bean.Bf_t_Apply;
import com.wyyf.bean.Bg_t_prodinfo;
import com.wyyf.bean.Bh_t_orderform;
import com.wyyf.bean.Bi_t_OrderProdRelate;
import com.wyyf.bean.Bk_t_HelpsCollect;
import com.wyyf.bean.Bn_t_prodcart;

/***
 * 唯一 手机网站入口
 * @author Administrator
 */
@Scope(value = "prototype")
@Controller("WapAction")
@RequestMapping(value="/wap")
public class WapAction extends BaseAjaxAction{
	/**
	 * 首页r
	 */
	@RequestMapping
	public String index(){
		// ==== 首页广告
		List<Map<String, Object>> adtoplist = new ArrayList<Map<String, Object>>();
		String ad = "SELECT  bj_st_id,bj_st_type,bj_st_clickurl,(SELECT ag_st_url FROM ag_t_file WHERE ag_st_objid= bj_st_id  limit 0,1) bj_st_picurl	 FROM bj_t_advertisement WHERE  bj_st_enable='1' AND bj_st_type='8'  ORDER BY bj_dt_addDate  desc LIMIT 0,6;";
		adtoplist = baseBiz.queryForList(ad);
		model.addAttribute("ad", adtoplist);
		//====
		String type1=request.getParameter("type1");
		model.addAttribute("type", type1);
		return "wap/index";
	}
	private String GetOrderNum() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String OrderNum = "D" + sdf.format(Calendar.getInstance().getTime()) + MainAction.getRandomInt(3);
		return OrderNum;
	}
	public static String getRandomString(int length) { // length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	/**
	 * 得到一个表的唯一的ID
	 * 
	 * @return
	 */
	private String GetUniqueId(String tableName, String PrimaryKey) {
		String id = getRandomString(32);
		List<Map<String, Object>> isExist = baseBiz.queryForList("select * from " + tableName + " where " + PrimaryKey + " = '" + id + "'");
		while (isExist.size() > 0) {
			id = getRandomString(32);
			isExist = baseBiz.queryForList("select * from " + tableName + " where " + PrimaryKey + " = '" + id + "'");
		}
		return id;
	}
	//付款，支付补交金额
			@RequestMapping(value = "toPay2")
			public String toPay2() throws UnsupportedEncodingException {
				this.session.putValue("RedirectUrl", this.request.getRequestURL() + "?" + this.request.getQueryString());
				Ae_t_sysuser ae_t_sysuser = getTopFootInfo();
				if (null == ae_t_sysuser || null == ae_t_sysuser.getAe_st_id() || "" == ae_t_sysuser.getAe_st_id()) {
					this.createAjax(response, "index/loginp");
				}
				String price=request.getParameter("bj_maney");    //补交金额
				String rid = request.getParameter("rid");//赴约人ID
				if ("".equals(price)) {
					this.createAjax(response, "输入不能为空");
				}
				double money = Double.parseDouble(price);//String -> Double
				System.out.println(money);
				System.out.println(rid);
				String Orderid = GetUniqueId("bh_t_orderform", "bh_st_id");
				Date nowDate=getcuttDate();//当前操作时间
				String OrderNum = "D"+nowDate.getTime()+(int)(Math.random()*90000+10000);
				// 生成订单
				Bh_t_orderform order = new Bh_t_orderform();
				order.setBh_st_id(Orderid);
				order.setBh_st_bbid(rid);
				order.setBh_st_ddnum(OrderNum);
				order.setBh_st_spprice(money);
				order.setBh_st_ddstate(1 + "");
				order.setBh_st_source(1 + "");
				order.setBh_st_memberId(ae_t_sysuser.getAe_st_id());
				order.setBh_dt_addDate(getcuttDate());
				baseBiz.addTRANS(order);
				model.addAttribute("orderId", OrderNum);
				model.addAttribute("pkid", OrderNum);
				model.addAttribute("price", money);
				String sql="select  *  from bh_t_orderform t  where t.bh_st_id='"+Orderid+"'";
				List<Map<String, Object>> order1 = baseBiz.queryForList(sql);
				model.addAttribute("order", order1.get(0));
				model.addAttribute("total", order1.get(0).get("bh_st_spprice"));
				return "wap/payfor";
			}
	@RequestMapping(value="getShopList")
	public void getShopList(){
		// ======分页商家列表
		PageBean pages=null;
		String pageIndexs=request.getParameter("pageIndex");//页码
		String pageSizes=request.getParameter("pageSize");//页大小
		Integer pageIndex =StringUtils.hasText(pageIndexs)?Integer.valueOf(pageIndexs):1; //页码
		Integer pageSize =StringUtils.hasText(pageSizes)?Integer.valueOf(pageSizes):10; //页大小
		//默认页码和页大小
		pageIndex=pageIndex<1?1:pageIndex;
		pageSize=pageSize<1?10:pageSize;
		//该商品下面的所有的评论
		StringBuffer sb=new StringBuffer("SELECT * FROM (   SELECT bb_st_userid,ae_st_name,(select ag_st_url from ag_t_file where ag_st_objid = ae_st_id  and ag_st_url is not   NULL and ag_st_mark='userfile' limit 0,1) ag_st_url  ,bb_nm_jjpm,bb_st_shopinfo,bb_nm_clicks "); 
		StringBuffer sbcount=new StringBuffer(" select count(1) ");
		StringBuffer sbcommon=new StringBuffer("  FROM bb_t_shopuser,ae_t_sysuser  WHERE bb_st_userid=ae_st_id  and  ae_st_type ='3'  "); //3商家
		List<String> pramsList = new ArrayList<String>();
		
		sbcount.append(sbcommon);
		sb.append(sbcommon).append("  order by bb_nm_jjpm desc,ae_st_name ) a where a.ag_st_url is not  NULL  ");
		pages=baseBiz.getPages(sb.toString(),sbcount.toString(),pageIndex, pageSize,pramsList.toArray());
		SafetyFilter.logger.info("传出JSON:" + JsonUtils.getJsonData(pages));
		this.createAjax(response,JsonUtils.getJsonData(pages));
	}
	
	/**
	 * 城市选择
	 */
	@RequestMapping(value="city")
	public String city(String id){
		String sql = "select b.d_name from aa26 a LEFT JOIN aa26 b ON (a.d_code=b.d_parent)";
		List<Map<String, Object>> list = baseBiz.queryForList(sql+" where a.d_code="+id+"");
		model.addAttribute("district_list", list);
		String type1=request.getParameter("type1");
		model.addAttribute("type", type1);
		return "wap/city";
	}
	/**
	 * 省
	 */
	@RequestMapping(value="getParentCity")
	public String getParentCity(){
		String sql="SELECT d_name FROM aa26 WHERE d_level='1' ";
		List<Map<String, Object>> list = baseBiz.queryForList(sql);
		model.addAttribute("district_list", list);
		String type1=request.getParameter("type1");
		model.addAttribute("type", type1);
		return "wap/city";
	}
	
	/**
	 * 我要设计
	 */
	@RequestMapping(value="mydesign")
	public String  mydesign(){
		String wyyfType=request.getParameter("wyyfType");
		model.addAttribute("wyyfType", wyyfType);
		String type1=request.getParameter("type1");
		model.addAttribute("type", type1);
		return "wap/mydesign";
	}
	
	/**
	 * 我要设计
	 */
	@RequestMapping(value="mymess")
	public String  mymess(){
		//String userid=getcuttSysuserID();//"ea33bc89ff0f4b40b6d152205b33814c";//
//		String sql="select * from ae_t_sysuser where t.ae_st_type='"+type+"'";
//		List<Map<String, Object>> userlist = baseBiz.queryForList(sql);
//		model.addAttribute("model", );
		return "wap/mymess";
	}
	
	
	/**
	 * 我要装修
	 */
	@RequestMapping(value="fixture")
	public String  fixture(){
		String wyyfType=request.getParameter("wyyfType");
		model.addAttribute("wyyfType", wyyfType);
		String type=request.getParameter("type");
		String sql="select t.*,(select count(1) from bf_t_apply t2 where t2.bf_st_receiveid=t.ae_st_id)  as yuyuecount from ae_t_sysuser t where t.ae_st_type='"+type+"'";
		List<Map<String, Object>> userlist = baseBiz.queryForList(sql);
		model.addAttribute("user", userlist);
		String type1=request.getParameter("type1");
		model.addAttribute("type", type1);
		return "wap/fixture";
	}

	/**
	 * 我要装修
	 */
	@RequestMapping(value="fixturejsp")
	public String  fixturejsp(){
		String sqlx=request.getParameter("sqlx");
		model.addAttribute("sqlx", sqlx);
		String wyyfType=request.getParameter("wyyfType");
		model.addAttribute("wyyfType", wyyfType);
		String type=request.getParameter("type1");
		model.addAttribute("type", type);
		return "wap/fixturejsp";
	}
	
	/**
	 * 我要验房工长列表
	 */
	
	@RequestMapping(value="fixture_iterm")
	public String  fixture_iterm(){
		String type = request.getParameter("type");
		model.addAttribute("fl", type);
		PageBean pages=null;
		String pageIndexs=request.getParameter("pageIndex");//页码
		String pageSizes=request.getParameter("pageSize");//页大小
		Integer pageIndex =StringUtils.hasText(pageIndexs)?Integer.valueOf(pageIndexs):0; //页码
		Integer pageSize =StringUtils.hasText(pageSizes)?Integer.valueOf(pageSizes):0; //页大小
		//默认页码和页大小
		pageIndex=pageIndex<1?1:pageIndex;
		pageSize=pageSize<1?10:pageSize;
		//该商品下面的所有的评论
		
		StringBuffer sb=new StringBuffer("select l.ba_st_team_intro ,l.ba_st_price , u.ae_st_name ,u.ae_st_id ,u.ae_st_intro ,l.ba_st_grade  ,(select ag_st_url from ag_t_file where ag_st_objid = u.ae_st_id) ag_st_url "); 
		StringBuffer sbcount=new StringBuffer("select count(u.ae_st_id) ");
		StringBuffer sbcommon=new StringBuffer(" from ae_t_sysuser u,ba_t_labour l where ae_st_type ='6'  and l.ba_st_userid=u.ae_st_id ");
		List<String> params=new ArrayList<String>();
		sbcount.append(sbcommon);
		sb.append(sbcommon).append(" order by u.ae_dt_addDate desc ");
		//分页数据、分页配置 查询--
		 pages=baseBiz.getPages(sb.toString(),sbcount.toString(),pageIndex, 10);
		model.addAttribute("pageBeanObj", pages);//分页对象
		
		
		String type1=request.getParameter("type1");
		model.addAttribute("type", type1);
		model.addAttribute("pages", pages);
		String wyyfType=request.getParameter("wyyfType");
		model.addAttribute("wyyfType", wyyfType);
		SafetyFilter.logger.info("传出JSON:" + JsonUtils.getJsonData(pages));
		return "wap/fixture_iterm";
	}
	
	
	@RequestMapping(value="fixturepage")
	public String  fixturepage(){
		String type=request.getParameter("type");
		String sql="select t.*,(select count(1) from bf_t_apply t2 where t2.bf_st_receiveid=t.ae_st_id)  as yuyuecount from ae_t_sysuser t where t.ae_st_type='"+type+"'";
		List<Map<String, Object>> userlist = baseBiz.queryForList(sql);
		model.addAttribute("user", userlist);
		String type1=request.getParameter("type1");
		model.addAttribute("type", type1);
		return "wap/fixturepage";
	}
	
	
	/**
	 * 工长个人信息
	 */
	@RequestMapping(value="designermess")
	public String  designermess(){
		//分页数据、分页配置 查询--
		String userid=request.getParameter("id");
		model.addAttribute("id", userid);
		String type=request.getParameter("type");//主要给页面判断是否显示团队界面
		model.addAttribute("labourtype", type);
		
		String sql="select * from ba_t_labour t where t.ba_st_userid='"+userid+"'";
		Map<String, Object> list = baseBiz.queryForMap((sql));
		model.addAttribute("labour", list);
		Integer ba_st_grade=(Integer) list.get("ba_st_grade");
		model.addAttribute("ba_st_grade", ba_st_grade);
		
		sql="select * , (select ag_st_url from ag_t_file where ag_st_objid = t.bd_st_id) ag_st_url from bd_t_case t where t.bd_st_bbid='"+userid+"'";
		List<Map<String, Object>> caselist = baseBiz.queryForList(sql);
		model.addAttribute("caselist", caselist);
		
		sql="select count(1) from bf_t_apply t where t.bf_st_receiveid='"+userid+"'";
		Integer yuyuecount = baseBiz.queryForInt(sql);
		model.addAttribute("yuyuecount", yuyuecount);
		
		
		sql="select * from be_t_assess t where t.be_st_jbgid='"+userid+"'";
		List<Map<String, Object>> commentslist = baseBiz.queryForList(sql);
		model.addAttribute("commentslist", commentslist);
		
		
		sql="select * , (select ag_st_url from ag_t_file where ag_st_objid = t.ae_st_id) ag_st_url from ae_t_sysuser t where t.ae_st_id='"+userid+"'";
		List<Map<String, Object>> userlist = baseBiz.queryForList(sql);
		model.addAttribute("user", userlist.get(0));
		
		
		String type1=request.getParameter("type1");
		model.addAttribute("type", type1);
		
		String fl=request.getParameter("fl");
		model.addAttribute("fl", fl);
		String sjs=request.getParameter("sjs");
		model.addAttribute("sjs", sjs);
		String sqlx=request.getParameter("sqlx");
		model.addAttribute("sqlx", sqlx);
		
		return "wap/designermess";
	}
	/**
	 * 获取我的单个服务详细信息接口
	 * @param json
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="getMyServiceInfo")
	public void getMyServiceInfo(String json,HttpServletResponse response,HttpServletRequest request){
			SafetyFilter.logger.info("传入JSON:" + json);
			String jsonData = "";
			try{
				Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据
				//String loginflag = StringUtils.toStringByObject(maps.get("loginflag"),true); //登录标识
				String ae_st_id =getcuttSysuserID();
				String bf_st_id = StringUtils.toStringByObject(maps.get("bf_st_id"),true); //服务的主键ID
				//验证不能为空
				if (org.springframework.util.StringUtils.hasText(ae_st_id)&&org.springframework.util.StringUtils.hasText(bf_st_id)) {
					//查询该手机用户ID
					if(StringUtils.hasText(ae_st_id)){
						//当前手机用户登录人ID
						//该会员下面的所有的收货地址                
						StringBuffer sb = new StringBuffer("SELECT ae.ae_st_id,bf.bf_st_id,ba.ba_st_id,ba.ba_st_grade,ba.ba_st_team_intro,ba.ba_st_teamnum,ae_st_name,(SELECT  ag_st_url FROM  ag_t_file WHERE ag_st_objid=ae.ae_st_id  and ag_st_mark='userfile' limit 0,1) ag_st_url,(SELECT COUNT(1) FROM bf_t_apply WHERE bf_st_receiveid=ba.ba_st_id)ordersum,(SELECT ag_st_url FROM bd_t_case,ag_t_file WHERE bd_st_bbid=ae.ae_st_id AND ag_st_objid=bd_st_id) images");
						StringBuffer sbcommon = new StringBuffer( " FROM ae_t_sysuser ae,ba_t_labour ba ,bf_t_apply bf WHERE bf.bf_st_receiveid=ae.ae_st_id AND ba.ba_st_userid=ae.ae_st_id AND bf_st_userid=? and bf_st_id=? and ba.ba_st_type!=1 and  ba.ba_st_type!=15 ");
						List<String> pramsList=new ArrayList<String>();
						pramsList.add(ae_st_id);
						pramsList.add(bf_st_id);
						sb.append(sbcommon);
						List<Map<String, Object>> listinfo=baseBiz.queryForList(sb.toString(), pramsList.toArray());
						if(listinfo!=null&&listinfo.size()>0){
							List<Map<String,Object>> listreulst=baseBiz.queryForList("select ag_st_url from ag_t_file where ag_st_objid=? and ag_st_mark='JSRUEST'",new Object[]{bf_st_id});
							jsonData = "{\"success\":\"true\",\"msg\":\"获取成功！\",\"tainfo\":"+JsonUtils.getJsonData(listinfo.get(0))+",\"resulturl\":"+JsonUtils.getJsonDataFromCollection(listreulst)+"}";
						}else{
							jsonData = "{\"success\":\"false\",\"flag\":\"4\",\"msg\":\"请传入真实的服务ID！\"}";
						}
					} else {
						throw new MyoneException("未获取该手机用户信息");
					}
				} else {
					jsonData = "{\"success\":\"false\",\"flag\":\"2\",\"msg\":\"请先传入完整的数据信息！\"}";
				}
			}catch (MyoneException e) {
				jsonData = "{\"success\":\"false\",\"flag\":\"3\",\"msg\":\"登录标识已经失效，请重新登录！\"}";
				e.printStackTrace();
			}catch (Exception e) {
				jsonData = "{\"success\":\"false\",\"flag\":\"1\",\"msg\":\"解析数据异常！\"}";
				e.printStackTrace();
			}
			SafetyFilter.logger.info("传出JSON:" + jsonData);
			this.createAjax(response,jsonData);
	}
	
	/**
	 * 预约工长
	 */
	@RequestMapping(value="ordermess")
	public String  ordermess(){
		String userid=request.getParameter("id");
		String fl=request.getParameter("type");
		model.addAttribute("fl", fl);
		model.addAttribute("id", userid);
		
		String type1=request.getParameter("type1");
		model.addAttribute("type", type1);
		String sjs=request.getParameter("sjs");
		model.addAttribute("sjs", sjs);
		String sqlx=request.getParameter("sqlx");
		model.addAttribute("sqlx", sqlx);
		return "wap/ordermess";
	}
	
	
	
	/**
	 * 我要特价
	 */
	@RequestMapping(value="specileprice")
	public String  specileprice(){
		String type1=request.getParameter("type1");
		model.addAttribute("type", type1);
		return "wap/specileprice";
	}
	/**f
	 * 根据商家id查询对应的商品列表
	 */
	@RequestMapping(value="specilepriceProduct")
	public String  specilepriceProduct(){
		String type1=request.getParameter("type1");
		model.addAttribute("type", type1);
		String id=request.getParameter("id");
		model.addAttribute("id", id);
		String sql="select ae_st_name from bb_t_shopuser ,ae_t_sysuser  WHERE  bb_st_userid=ae_st_id AND  bb_st_userid = '"+id+"'";
		
		 Map<String, Object>  data=baseBiz.queryForMap(sql);
		 model.addAttribute("data", data.get("ae_st_name"));
		return "wap/productlist1";
	}
	
	/**
	 * 注册
	 * @return
	 */
	@RequestMapping(value = "addUser")
	public void addUser(Ae_t_sysuser ae) {
		
		ae.setVouchers("200");
		  String jsonData="";
		  // || 
			if (!org.springframework.util.StringUtils.hasText(ae.getUsername()) || !org.springframework.util.StringUtils.hasText(ae.getPassword())||!org.springframework.util.StringUtils.hasText(ae.getYzm()) ) {
				 jsonData= "{\"success\":\"false\",\"flag\":\"1\",\"msg\":\"注册失败！\"}";
			}else{
			String telcode = StringUtils.toStringByObject(ae.getYzm(), true); // 验证码
			String code = ae.getYzm().toLowerCase();
			
			if(telcode.equals(code)){
			Encoder e = new MD5Encoder();// 使用security3 的MD5加密技
			
			// 创建批量保存事务对象
			List<BizTransUtil> list = new ArrayList<BizTransUtil>();
			// 构建数据--新增---临时添加类型----
			ae.setAe_st_id(StringUtils.getUUID32());// ID
			ae.setAe_st_userkind("3");// 用户类型
			//e.encrypt("123456", "15184436308")
			//sysuser.setPassword(e.encrypt(password,username));
			//e.encrypt("123456", "15184436308") e.encrypt("123456", "15184436308")
			ae.setPassword(e.encrypt(ae.getPassword().trim(), ae.getUsername().trim()));
			
			ae.setAe_dt_addDate(getcuttDate());// 创建时间
			ae.setAe_st_lockstate("1");// 账户锁定状态：1正常，2账户停用3.注销
			ae.setAe_st_type(ae.getAe_st_type());// 这里先写死， 这里的数据来自前台
			ae.setMb(200);
			ae.setAe_st_type("1");
			list.add(new BizTransUtil(ae));
			// 角色--添加
			String type = ae.getAe_st_type();
			String roleid = (String) baseBiz.queryForObject("select ac_st_id from ac_t_sysrole where ac_st_code = ?",
					new Object[]{"2".equals(type)?"WZGLY":("3".equals(type)?"SJ":("4".equals(type)||"5".equals(type)||"6".equals(type)?"SF":"HY"))},String.class);
			Af_t_sysuserrole rf=new Af_t_sysuserrole(true);
			rf.setAe_st_id(ae.getAe_st_id());
			rf.setAc_st_id(roleid);
			list.add(new BizTransUtil(rf));
			
			try {
				baseBiz.executesTRANS(list);
				 jsonData = "{\"success\":\"true\",\"flag\":\"0\",\"msg\":\"注册成功！\"}";
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				 jsonData = "{\"success\":\"false\",\"flag\":\"1\",\"msg\":\"注册失败！\"}";
				e1.printStackTrace();
			}
			
			}else{
				jsonData = "{\"success\":\"false\",\"flag\":\"2\",\"msg\":\"验证码输入不正确！\"}";
			}}
			SafetyFilter.logger.info("传出JSON:" + jsonData);
			this.createAjax(response,jsonData);

	}
	/**f
	 * 根据商家id查询对应的商品列表
	 */
	@RequestMapping(value="productListJsp")
	public String  productListJsp(){
		String type1=request.getParameter("type1");
		model.addAttribute("type", type1);
		String id=request.getParameter("id");
		model.addAttribute("id", id);
		String sql="select ae_st_name from bb_t_shopuser ,ae_t_sysuser  WHERE  bb_st_userid=ae_st_id AND  bb_st_userid = '"+id+"'";
		
		 Map<String, Object>  data=baseBiz.queryForMap(sql);
		 model.addAttribute("data", data.get("ae_st_name"));
		return "wap/productListJsp";
	}
	/**
	 * 根据商家id查询对应的商品列表
	 */
	@RequestMapping(value="getProductList")
	public String getProductList(){
		// ======根据商家id查询对应的商品列表
		PageBean pages=null;
		String pageIndexs=request.getParameter("pageIndex");//页码
		String pageSizes=request.getParameter("pageSize");//页大小
		String id=request.getParameter("id");//id
		Integer pageIndex =StringUtils.hasText(pageIndexs)?Integer.valueOf(pageIndexs):1; //页码
		Integer pageSize =StringUtils.hasText(pageSizes)?Integer.valueOf(pageSizes):10; //页大小
		//默认页码和页大小
		pageIndex=pageIndex<1?1:pageIndex;
		pageSize=pageSize<1?10:pageSize;
		//该商品下面的所有的评论
		StringBuffer sb=new StringBuffer(" SELECT bg_st_img1 , bg_st_id , b.bg_st_name ,  b.bg_st_pricezg "); 
		StringBuffer sbcount=new StringBuffer(" select count(1) ");
		StringBuffer sbcommon=new StringBuffer(" FROM bg_t_prodinfo b  WHERE b.bg_st_bbid  = ?"); //3商家
		List<String> pramsList = new ArrayList<String>();
		pramsList.add(id);
		sbcount.append(sbcommon);
		sb.append(sbcommon).append(" order by bg_dt_addDate desc ");
		pages=baseBiz.getPages(sb.toString(),sbcount.toString(),pageIndex, pageSize,pramsList.toArray());
		model.addAttribute("pages", pages);
		//this.createAjax(response,JsonUtils.getJsonData(pages));
		
		return "wap/productitem";
		
		
	}
	/**
	 * 我要验房
	 */
	@RequestMapping(value="wzyf")
	public String  wzyf(){
		String type1=request.getParameter("type1");
		model.addAttribute("type", type1);
		String wyyfType=request.getParameter("wyyfType");
		model.addAttribute("wyyfType", wyyfType);
		
		String mf = "select COUNT(1) as mfr from  bf_t_apply where bf_st_type='2'";
		String jp = "select COUNT(1) as  jpr from  bf_t_apply where bf_st_type='3'";
		Map<String, Object> mfr = baseBiz.queryForMap(mf);
		Map<String, Object> jpr = baseBiz.queryForMap(jp);
		model.addAttribute("mfr", mfr.get("mfr"));
		model.addAttribute("jpr", jpr.get("jpr"));
	
		
		
		return "wap/wzyf";
	}
	/**
	 * 一键入住
	 */
	@RequestMapping(value="onein")
	public String  onein(){
		String type1=request.getParameter("type1");
		model.addAttribute("type", type1);
		return "wap/onein";
	}
	/**
	 * 我要检测
	 */
	@RequestMapping(value="mycheck")
	public String  mycheck(){
		String type1=request.getParameter("type1");
		model.addAttribute("type", type1);
		String sqlx=request.getParameter("sqlx");
		model.addAttribute("sqlx", sqlx);
		return "wap/mycheck";
	}
	/**
	 * download
	 */
/*	@RequestMapping(value="download")
	public String  download(){
		
		return "wap/mycheck";
	}*/
	/**
	 * 我要监理
	 */
	@RequestMapping(value="supervision")
	public String  supervision()
	{
		String fl=request.getParameter("fl");
		/*String sql="select t.*,t2.*,(select count(1) from bf_t_apply t3 where t3.bf_st_receiveid=t.ae_st_id)  as yuyuecount from ae_t_sysuser t left join ba_t_labour t2 on t.ae_st_id=t2.ba_st_userid where t2.ba_st_type='"+fl+"'";
		List<Map<String, Object>> userlist = baseBiz.queryForList(sql);
		model.addAttribute("user", userlist);*/
		String type1=request.getParameter("type1");
		model.addAttribute("type", type1);
		model.addAttribute("fl", fl);
		String sqlx=request.getParameter("sqlx");
		model.addAttribute("sqlx", sqlx);
		return "wap/supervision";
	}
	
	
//	@RequestMapping(value="supervisionpage")
//	public String  supervisionpage()
//	{
//		String type=request.getParameter("type");
//		Integer pageIndex=StringUtils.toIntegerByObject(request.getParameter("page"));
//		String sql="select t.*,t2.*,(select count(1) from bf_t_apply t3 where t3.bf_st_receiveid=t.ae_st_id)  as yuyuecount,(select t4.ag_st_url from ag_t_file t4 where ag_st_objid =t.ae_st_id) ag_st_url from ae_t_sysuser t left join ba_t_labour t2 on t.ae_st_id=t2.ba_st_userid where t2.ba_st_type="+type+"";
//		sql=getPageSql(sql, pageIndex, 10);
//		List<Map<String, Object>> userlist = baseBiz.queryForList(sql);
//		model.addAttribute("userlist", userlist);
//		String type1=request.getParameter("type1");
//		model.addAttribute("type", type1);
//		return "wap/supervisionpage";
//	}
	
	/**
	 * 我的服务
	 * @return
	 */
	
	@RequestMapping(value = "supervisionpage")
	public String supervisionpage() {
		String type = request.getParameter("type");
		String subtype = request.getParameter("subtype");
		Integer pageIndex = StringUtils.toIntegerByObject(request.getParameter("page"));
		String sql = "SELECT t.*,t2.*,(select count(1) from bf_t_apply t3 where t3.bf_st_receiveid=t.ae_st_id)  as yuyuecount,(select t4.ag_st_url from ag_t_file t4 where ag_st_objid =t.ae_st_id) ag_st_url FROM ae_t_sysuser t, ba_t_labour t2  where   t.ae_st_id=t2.ba_st_userid   ";
		if (type != null && type != "") {
			sql += "and t.ae_st_type in (" + type + ")";
		}
		if (subtype != null && subtype != "") {
			sql += "and t2.ba_st_type in (" + subtype + ")";
		}
//		if(StringUtils.hasText(userId)){
//			sql+="and   t1.bf_st_userid='"+userId+"'";
//		}
		sql = getPageSql(sql, pageIndex, 10);
		List<Map<String, Object>> userlist = baseBiz.queryForList(sql);
		model.addAttribute("userlist", userlist);
		String type1 = request.getParameter("type1");
		model.addAttribute("type", type1);
		String sqlx = request.getParameter("sqlx");
		model.addAttribute("sqlx", sqlx);
		model.addAttribute("fl", type);
		return "wap/supervisionpage";
	}
	
	
	/**
	 * 我的服务
	 * 
	 * @return
	 */
	@RequestMapping(value = "myservicepage")
	public String myservicepage() {
	    String userId=getcuttSysuserID();
		String type = request.getParameter("type");
		String subtype = request.getParameter("subtype");
		if("5".equals(type)&&"".equals(subtype)){
			model.addAttribute("sjs", "0");
		}else{
			model.addAttribute("sjs", "1");
		}
		Integer pageIndex = StringUtils.toIntegerByObject(request.getParameter("page"));
		String sql = "SELECT t1.*,t.*,t2.*,(select count(1) from bf_t_apply t3 where t3.bf_st_receiveid=t.ae_st_id)  as yuyuecount,(select t4.ag_st_url from ag_t_file t4 where ag_st_objid =t.ae_st_id) ag_st_url , (SELECT count(1) FROM be_t_assess  WHERE be_st_fbgid=t1.bf_st_userid AND be_st_jbgid=t1.bf_st_receiveid) pjs FROM bf_t_apply t1 , ae_t_sysuser t, ba_t_labour t2  where  t.ae_st_id=t1.bf_st_receiveid  AND t.ae_st_id=t2.ba_st_userid  and t1.bf_st_type != 4  ";
		if (type != null && type != "") {
			sql += "and t.ae_st_type in (" + type + ")";
		}
		if (subtype != null && subtype != "") {
			sql += "and t2.ba_st_type in (" + subtype + ")";
		}
		if(StringUtils.hasText(userId)){
			sql+="and   t1.bf_st_userid='"+userId+"'";
		}
		sql = getPageSql(sql, pageIndex, 10);
		List<Map<String, Object>> userlist = baseBiz.queryForList(sql);
		model.addAttribute("userlist", userlist);
		String type1 = request.getParameter("type1");
		model.addAttribute("type", type1);
		model.addAttribute("fl", type);
		
		if(StringUtils.hasText(userId)){
			if("6".equals(type)){//0显示
				model.addAttribute("isShow", 0);
			}else if ("4".equals(type)&&"7,8,9,10,11,12,15".equals(subtype)){
				model.addAttribute("isShow", 0);
			}else{
				model.addAttribute("isShow", 1);
			}
		}else{
			model.addAttribute("isShow", 1);
		}
		
		
	
		if(!"6".equals(type)){
		model.addAttribute("subtype", subtype);}
		
	
		return "wap/supervisionpage";
	}
	
	
	/**
	 * 提交反馈接口
	 * @param json
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="addAdvice")
	public String addAdvice(Bk_t_HelpsCollect helpmodel){
		List<BizTransUtil> list = new ArrayList<BizTransUtil>();
		helpmodel.setBk_st_type("1");
		helpmodel.setBk_st_isdel("0");
		helpmodel.setBk_st_source("1");
		helpmodel.setBk_st_id(StringUtils.getUUID32());
		list.add(new BizTransUtil(helpmodel));
		if(baseBiz.executesTRANS(list)){
			model.addAttribute("msg", "提交成功!");
			return "wap/msg";
		}else{
			model.addAttribute("msg", "操作失败!");
			return "wap/fail";
		}
	}
	
	
//	
//	xxxxx
//	@RequestMapping(value="orderdetail")
//	public String  orderdetail()
//	{
//		
//		Bi_t_OrderProdRelate orderProdRelate=new Bi_t_OrderProdRelate(true);
//		orderProdRelate.setBi_st_ddnum(bh.getBh_st_ddnum());//订单编号
//		orderProdRelate.setBi_st_spnum(spnum);//商品编号  
//		orderProdRelate.setBi_st_bbid(sbbid);//订单商品归属商家ID
//		orderProdRelate.setBi_st_spprice(spprice);//商品价格（下单时候的商品价格）
//		orderProdRelate.setBi_st_spsl(spsl);//商品数量
//		orderProdRelate.setBi_st_addUserId(userid);//创建人员ID  
//		orderProdRelate.setBi_dt_addDate(nowDate);//创建时间
//		
//		
//		return "wap/orderdetail";
//	}
//	
	
	@RequestMapping(value="orderdetail")
	public String  orderdetail()
	{
		String pkid=request.getParameter("pkid");
		String sql="select * from bi_t_orderprodrelate  t left join bg_t_prodinfo t2 on t.bi_st_spnum=t2.bg_st_num  where t.bi_st_ddnum='"+pkid+"'";
		List<Map<String, Object>> list = baseBiz.queryForList(sql);
		model.addAttribute("list", list);
		return "wap/orderdetail";
	}

	
	@RequestMapping(value="paymentpage")
	public String  paymentpage()
	{
		String userid=null;
		try {
			userid = getcuttSysuserID();
		} catch (Exception e) {
			e.printStackTrace();
			return "wap/plogin";
		}
		String status=request.getParameter("status");
		Integer pageIndex=StringUtils.toIntegerByObject(request.getParameter("page"));
		String sql="select t.*  , (SELECT count(1) FROM be_t_assess  WHERE be_st_fbgid=t.bh_st_memberId AND be_st_bgid=t.bh_st_id) pjs from bh_t_orderform t where t.bh_st_ddstate in("+status+") and t.bh_st_memberId='"+userid+"'";
		sql=getPageSql(sql, pageIndex, 10);
		List<Map<String, Object>> list = baseBiz.queryForList(sql);
		model.addAttribute("list", list);
		return "wap/paymentpage";
	}
	
	
	public String getPageSql(String sql,Integer pageindex,Integer rowPerPage)
	{
		Integer startrownum = (pageindex-1)*rowPerPage;
		Integer endrownum = (pageindex)*rowPerPage;
		//String type1=request.getParameter("type1");
		//model.addAttribute("type", type1);
		return sql=sql+" limit "+startrownum+","+rowPerPage;
	}
	
	//------------------------------------------------
	/**
	 * 商城
	 */
	@RequestMapping(value = "myshop")
	public String myshop() {
		String type1 = request.getParameter("type1");
		model.addAttribute("type", type1);
		String sql = "SELECT  bj_st_id,bj_st_type,bj_st_clickurl,(SELECT ag_st_url FROM ag_t_file WHERE ag_st_objid= bj_st_id  limit 0,1) bj_st_picurl	 FROM bj_t_advertisement WHERE  bj_st_enable='1' AND bj_st_type='3'  ORDER BY bj_dt_addDate  desc LIMIT 0,4";
		List<Map<String, Object>> order = baseBiz.queryForList(sql);
		model.addAttribute("data", order);
		return "wap/myshop";
	}
	
	
	/**
	 * COMFIRM ORDER
	 */
	@RequestMapping(value="plistmess")
	public String  plistmess(){
		
		
		//getcuttSysuserID();
		String json=request.getParameter("json");
		Gson gson=new Gson();
		java.lang.reflect.Type listType=new TypeToken<ArrayList<Bn_t_prodcart>>(){}.getType();//TypeToken内的泛型就是Json数据中的类型
		ArrayList<Bn_t_prodcart> list=gson.fromJson(json, listType);
		for(Bn_t_prodcart product:list)
		{
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("bn_st_id",product.getBn_st_id() );
			map.put("bn_st_prodnum",product.getBn_st_prodnum());
			baseBiz.updateTRANS(map, Bn_t_prodcart.class);
		}
		
		double carttotalprice=0;
		List<Map<String, Object>> cartlist=new ArrayList<Map<String, Object>>();
		for(Bn_t_prodcart product:list)
		{
			String sql="select  t.*,t2.*,t.bn_st_prodnum*bg_st_pricetj totalprice  from bn_t_prodcart t  left join bg_t_prodinfo t2 on t.bn_st_prodid=t2.bg_st_id  where t.bn_st_id='"+product.getBn_st_id()+"'";
			List<Map<String, Object>> p = baseBiz.queryForList(sql);
			cartlist.add(p.get(0));
			double totalprice=(double) p.get(0).get("totalprice");
			carttotalprice+=totalprice;
		}
		
		model.addAttribute("orderid", StringUtils.getUUID32());
		model.addAttribute("cartlist", cartlist);
		model.addAttribute("carttotalprice",carttotalprice);
		String type1=request.getParameter("type1");
		model.addAttribute("type", type1);
		return "wap/plistmess";
	}
	/**
	 *添加订单接口
	 * @param json
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="addorder")
	public void addorder(String json,HttpServletResponse response,HttpServletRequest request){
		String userid = null;
		String jsonData = "";
		try {
			userid = getcuttSysuserID();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			jsonData = "{\"success\":\"false\",\"flag\":\"1\",\"msg\":\"请先登录！\"}";
			e1.printStackTrace();
			this.createAjax(response,jsonData);
			return ;
		}
		SafetyFilter.logger.info("传入JSON:" + json);
		
		Bh_t_orderform bh=null;
		
		StringBuffer sb=new StringBuffer();
		List<BizTransUtil> list = new ArrayList<BizTransUtil>();
		try {
			Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据
			//String loginflag = StringUtils.toStringByObject(maps.get("loginflag"),true); //登录标识
			String bh_st_shname = StringUtils.toStringByObject(maps.get("bh_st_shname"),true); //收货人
			String bh_st_shphone = StringUtils.toStringByObject(maps.get("bh_st_shphone"),true); //电话
			String bh_st_shaddress = StringUtils.toStringByObject(maps.get("bh_st_shaddress"),true); //地址
			Date nowDate=getcuttDate();//当前操作时间
			//验证不能为空
			
			if (org.springframework.util.StringUtils.hasText(bh_st_shname)&&org.springframework.util.StringUtils.hasText(bh_st_shphone)&&org.springframework.util.StringUtils.hasText(bh_st_shaddress)) {
				//查询该手机用户ID
				
				if(StringUtils.hasText(userid)){
					//当前手机用户登录人ID
					ArrayList<MorphDynaBean> jsonArray=(ArrayList<MorphDynaBean>) maps.get("shopinfo");
					if(jsonArray.size()>0){
						Bh_t_orderform  orderLs = new Bh_t_orderform();
						orderLs.setBh_st_shname(bh_st_shname);
						orderLs.setBh_st_shphone(bh_st_shphone);
						orderLs.setBh_st_shaddress(bh_st_shaddress);
						orderLs.setBh_st_ddstate("1");//订单状态  //1待付款 
						orderLs.setBh_st_del("0");//是否删除
						orderLs.setBh_st_source("1");//订单来源1.app2.pc端
						orderLs.setBh_st_memberId(userid);//会员ID
						orderLs.setBh_st_addUserId(userid);//创建人员ID  
						orderLs.setBh_dt_addDate(getcuttDate());//创建时间
						//各个订单中的商品算法
						//按照商品归属商家不同，分别创建不同订单。
						Map<String,Bh_t_orderform> map=new LinkedCaseInsensitiveMap<Bh_t_orderform>();
						
						for(MorphDynaBean m:jsonArray){
							//订单相关商品信息
							String spnum=StringUtils.toStringByObject(m.get("bi_st_spnum"));//订购的商品编号
							String spname=StringUtils.toStringByObject(m.get("spname"));//订购的商品名字
							Integer spsl=StringUtils.toIntegerByObject(m.get("bi_st_spsl"));//订购的商品数量
							String sbbid=StringUtils.toStringByObject(m.get("bi_st_bbid"));//商品归属商家ID  ae
							//如果该商品找不到归属商家
							if(!org.springframework.util.StringUtils.hasText(sbbid)){
								throw new MytwoException("商品找不到归属商家，不能购买。");
							}
							//当前订单
							
							if(map.get(sbbid)==null){
								//订单信息
								bh=new Bh_t_orderform();
								BeanUtils.copyProperties(orderLs, bh, false);
								bh.setBh_st_id(StringUtils.getUUID32());//订单ID
								bh.setBh_st_ddnum("D"+nowDate.getTime()+(int)(Math.random()*90000+10000));//订单编号  D+YYYYMMDDHHSSMM00000
								bh.setBh_st_bbid(sbbid);//订单归属商家ID
								sb.append(bh.getBh_st_ddnum()).append(",");
							}else{
								if(sb.indexOf(bh.getBh_st_ddnum())==-1){
									sb.append(bh.getBh_st_ddnum()).append(",");
								}
								
								bh=map.get(sbbid);
							}
							
							
							//如果没有商品编号或商品名字，则不处理该商品数据
							if(!org.springframework.util.StringUtils.hasText(spnum)||!org.springframework.util.StringUtils.hasText(spname)){
								continue;
							}
							//如果订购的商品数量小于1
							if(spsl<1){
								throw new MytwoException("商品【"+spname+"】的购买数量不能为0，请重新选择数量。");
							}
							//查询商品信息
							List<Map<String, Object>> splist=baseBiz.queryForList("select bg_st_name,bg_nm_storeNum,bg_st_fbtpe,date_format(bg_dt_startDate,'%Y-%m-%d %T') bg_dt_startDate,date_format(bg_dt_endDate,'%Y-%m-%d %T')  bg_dt_endDate, bg_st_pricezg,bg_st_pricetj,bg_st_enable,bg_st_isdel from bg_t_prodinfo where bg_st_num=?", new Object[]{spnum});
							if(splist!=null&&splist.size()>0){
								Double spprice=StringUtils.toDoubleByObject(splist.get(0).get("bg_st_pricezg"));//商品价格--默认为会员价格
								//删除验证
								if("1".equals(splist.get(0).get("bg_st_isdel"))){
									throw new MytwoException("商品【"+spname+"】已被删除，请重新选择！");
								}
								//下架验证
								if("0".equals(splist.get(0).get("bg_st_enable"))){
									throw new MytwoException("商品【"+spname+"】已下架，请重新选择！");
								}
								//抢购过期验证
								if("2".equals(splist.get(0).get("bg_st_fbtpe"))){
									Date startDate=DateConverUtil.getDbyST(StringUtils.toStringByObject(splist.get(0).get("bg_dt_startDate")));//商品生效时间
									Date endDate=DateConverUtil.getDbyST(StringUtils.toStringByObject(splist.get(0).get("bg_dt_endDate")));//商品结束时间
									//如果当前时间小于抢购开始时间
									if(startDate!=null&&nowDate.getTime()<startDate.getTime()){
										throw new MytwoException("商品【"+spname+"】抢购时间未到，请重新选择！");
									}
									//如果当前时间大于于抢购结束时间
									if(endDate!=null&&nowDate.getTime()>endDate.getTime()){
										throw new MytwoException("商品【"+spname+"】抢购时间已经结束，请重新选择！");
									}
									spprice=StringUtils.toDoubleByObject(splist.get(0).get("bg_st_pricetj"));//特价
								}
								//如果订购的商品数量大于 了库存量
								if(spsl>StringUtils.toIntegerByObject(splist.get(0).get("bg_nm_storeNum"))){
									throw new MytwoException("商品【"+spname+"】库存不足，请重新选择数量。");
								}
								//如果商品的抢购时间在当时，则按照抢购价格计算。
								
								//修改商品库存
								BizTransUtil bizTransUtil=new BizTransUtil("update bg_t_prodinfo set bg_nm_storeNum=bg_nm_storeNum-"+spsl+" where bg_nm_storeNum>=? and bg_st_num=? ", new Object[]{spsl,spnum}, CommonUtil.UPDATE);
								bizTransUtil.setIscase(true);
								bizTransUtil.setWhen(1);
								bizTransUtil.setErorrmsg("商品【"+spname+"】库存不足，请重新选择数量。");
								list.add(bizTransUtil);
								//计算商品总价
								bh.setBh_st_spprice(bh.getBh_st_spprice()+spprice*spsl);
								//构建订单中的商品信息
								Bi_t_OrderProdRelate orderProdRelate=new Bi_t_OrderProdRelate(true);
								orderProdRelate.setBi_st_ddnum(bh.getBh_st_ddnum());//订单编号
								orderProdRelate.setBi_st_spnum(spnum);//商品编号  
								orderProdRelate.setBi_st_bbid(sbbid);//订单商品归属商家ID
								orderProdRelate.setBi_st_spprice(spprice);//商品价格（下单时候的商品价格）
								orderProdRelate.setBi_st_spsl(spsl);//商品数量
								orderProdRelate.setBi_st_addUserId(userid);//创建人员ID  
								orderProdRelate.setBi_dt_addDate(nowDate);//创建时间
								list.add(new BizTransUtil(orderProdRelate));
							}else{
								throw new MytwoException("商品【"+spname+"】不存在！");
							}
							map.put(sbbid,bh);
							
						}
						
						//遍历 添加 多个订单到 保存对象
						for (Entry<String, Bh_t_orderform> entry : map.entrySet()) {
							list.add(new BizTransUtil( entry.getValue()));
						}
					}else{
						throw new MytwoException("没有任何商品，不能下单！"); //bh  setBh_st_ddnum
					}
					if(baseBiz.executesTRANS(list)){
						
						if(sb.length()>1){
							sb=sb.deleteCharAt(sb.length()-1);
						}
						String sql="DELETE FROM bn_t_prodcart where  bn_st_memberid='"+userid+"'";
				    	baseBiz.executeTRANS(sql);
						jsonData = "{\"success\":\"true\",\"flag\":\"1\",\"order\":\""+sb.toString()+"\",\"msg\":\"下单成功！\"}";
					}
				} else {
					throw new MyoneException("未获取该手机用户信息");
				}
			} else {
				jsonData = "{\"success\":\"false\",\"flag\":\"2\",\"msg\":\"请先传入完整的数据信息！\"}";
			}
		}catch (MytwoException e) {
			jsonData = "{\"success\":\"false\",\"flag\":\"4\",\"msg\":\""+e.getMessage()+"！\"}";
			e.printStackTrace();
		}catch (MyoneException e) {
			jsonData = "{\"success\":\"false\",\"flag\":\"3\",\"msg\":\"登录标识已经失效，请重新登录！\"}";
			e.printStackTrace();
		}catch (Exception e) {
			jsonData = "{\"success\":\"false\",\"flag\":\"1\",\"msg\":\"解析数据异常！\"}";
			e.printStackTrace();
		}
		SafetyFilter.logger.info("传出JSON:" + jsonData);
		this.createAjax(response,jsonData);
	}
	
	@RequestMapping(value="payfor")
	public String  payfor(Bh_t_orderform orderform){
		String userid=getcuttSysuserID();;//getcuttSysuserID();
		List<BizTransUtil> list=new ArrayList<BizTransUtil>();
		Date nowDate=getcuttDate();//当前操作时间
		String pkid=StringUtils.getUUID32();
	    orderform.setBh_st_id(pkid);
	    orderform.setBh_dt_addDate(nowDate);
	    orderform.setBh_st_addUserId(userid);
	    orderform.setBh_st_ddnum("D"+nowDate.getTime()+(int)(Math.random()*90000+10000));
	    list.add(new BizTransUtil(orderform));
	    
	    
	    for(Bi_t_OrderProdRelate porder:orderform.getProdList())
	    {
	    	porder.setBi_st_id(StringUtils.getUUID32());
	    	porder.setBi_st_ddnum(orderform.getBh_st_ddnum());
	    	porder.setBi_dt_addDate(nowDate);
	    	porder.setBi_st_addUserId(userid);
	    	list.add(new BizTransUtil(porder));
	    }
	    
		if(SysStatic.baseBiz.executesTRANS(list)){
			String sql="select  *  from bh_t_orderform t  where t.bh_st_id='"+pkid+"'";
			List<Map<String, Object>> order = baseBiz.queryForList(sql);
			model.addAttribute("order", order.get(0));
			return "wap/payfor";
		} else {
			model.addAttribute("msg", "业务操作失败!");
			return "wap/fail";
		}
	}
	/**
	 * 查询预约订单
	 * @return
	 */
	@RequestMapping(value="checkOrder")
	public String checkOrder() {
		String id=request.getParameter("id");
		String sql="select * from bf_t_apply where bf_st_id ='"+id+"'";
		Map<String, Object>  order = baseBiz.queryForMap(sql);
		model.addAttribute("order", order);
		model.addAttribute("tag", "1");//处理页面显示   商品支付页面和 预约支付页面 不一样
		return "wap/payfor";
		
	}
	/**
	 * 获取订单列表接口
	 * @param json 
	 * @return
	 */
	@RequestMapping(value="getOrderList")
	public String getOrderList(String json,HttpServletResponse response){
		SafetyFilter.logger.info("传入JSON:" + json);
			Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据
//			String bh_st_memberId = StringUtils.toStringByObject(maps.get("bh_st_memberId"),true); //用户id
			//String loginflag = StringUtils.toStringByObject(maps.get("loginflag"),true); //登录标识
			String pageIndexs=StringUtils.toStringByObject(maps.get("pageIndex"),true);//页码
			String pageSizes=StringUtils.toStringByObject(maps.get("pageSize"),true);//页大小
			Integer pageIndex =org.springframework.util.StringUtils.hasText(pageIndexs)?Integer.valueOf(pageIndexs):0; //页码
			Integer pageSize =org.springframework.util.StringUtils.hasText(pageSizes)?Integer.valueOf(pageSizes):0; //页大小
			//默认页码和页大小
			pageIndex=pageIndex<1?1:pageIndex;
			pageSize=pageSize<1?10:pageSize;
			//验证不能为空
				//查询该手机用户ID
			  String bh_st_memberId=getcuttSysuserID();
					//当前手机用户登录人ID
					///String bh_st_memberId =StringUtils.toStringByObject(listuser.get(0).get("ae_st_id"));

					StringBuffer sb=new StringBuffer("SELECT chargeId , ae_st_name ,bh_st_id,bh_st_ddnum,bh_st_spprice,bh_st_ddstate,bh_st_source,bh_st_shname,bh_st_shaddress,bh_st_shphone,bh_st_source,date_format(bh_dt_addDate,'%Y-%m-%d %T')bh_dt_addDate "); 
					StringBuffer sbcount=new StringBuffer(" select count(1) ");
					StringBuffer sbcommon=new StringBuffer(" FROM bh_t_orderform , ae_t_sysuser  WHERE bh_st_ddstate=7  and ae_st_id=bh_st_memberId  ");
					List<String> pramsList = new ArrayList<String>();
					//人员id
					pramsList.add(bh_st_memberId);
					sbcommon.append(" and bh_st_memberId=? ");
						
					sbcount.append(sbcommon);
					sb.append(sbcommon).append(" order by bh_dt_addDate desc ");
					PageBean pages=baseBiz.getPages(sb.toString(),sbcount.toString(),pageIndex, pageSize,pramsList.toArray());
				//	jsonData = "{\"success\":\"true\",\"msg\":\"获取成功！\",\"result\":"+JsonUtils.getJsonData(pages)+"}";
			model.addAttribute("data", pages.getList());
	return "wap/getOrderList";
		
	} 
	/**
	 * 删除已支付订单
	 */
	@RequestMapping(value="delectPayProduct")
	public void  delectPayProduct(){
		 String id=request.getParameter("id");
	 if(baseBiz.deleteTRANS(id, Bh_t_orderform.class)){
		 this.createAjax(response,"0");// success
	 }else{
		 this.createAjax(response,"1");//fail
	 }
		
	}
	
	/**
	 * 获取订单商品列表接口
	 */
	@RequestMapping(value="getOrderShopList")
	public void getOrderShopList(String json,HttpServletResponse response){
		SafetyFilter.logger.info("传入JSON:" + json);
		String jsonData = "";
		try {
			Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据
			String loginflag = StringUtils.toStringByObject(maps.get("loginflag"),true); //登录标识
			String bh_st_ddnum = StringUtils.toStringByObject(maps.get("bh_st_ddnum"),true); //订单编号
			String pageIndexs=StringUtils.toStringByObject(maps.get("pageIndex"),true);//页码
			String pageSizes=StringUtils.toStringByObject(maps.get("pageSize"),true);//页大小
			Integer pageIndex =org.springframework.util.StringUtils.hasText(pageIndexs)?Integer.valueOf(pageIndexs):0; //页码
			Integer pageSize =org.springframework.util.StringUtils.hasText(pageSizes)?Integer.valueOf(pageSizes):0; //页大小
			//默认页码和页大小
			pageIndex=pageIndex<1?1:pageIndex;
			pageSize=pageSize<1?10:pageSize;
			List<String> pramsList = new ArrayList<String>();
			//验证不能为空
			if (org.springframework.util.StringUtils.hasText(loginflag)&&org.springframework.util.StringUtils.hasText(bh_st_ddnum)) {
				//查询该手机用户ID
				List<Map<String, Object>> listuser=baseBiz.queryForList("select a.ae_st_id from ae_t_sysuser a where a.ae_st_loginflag=? ", new Object[]{loginflag});
				if(listuser!=null&&listuser.size()==1){
					//当前手机用户登录人ID
					String ae_st_id =StringUtils.toStringByObject(listuser.get(0).get("ae_st_id"));
					//该会员下面的所有的收货地址
					StringBuffer sb = new StringBuffer("SELECT bi_st_bbid,bi_st_spnum,bi_st_spprice,bi_st_spsl,bi_st_remark,(SELECT ae_st_name  FROM ae_t_sysuser WHERE ae_st_id=bh.bh_st_bbid)shopname,bg.bg_st_name,bg.bg_st_id,(select count(1) from be_t_assess where be_st_bgid=bg.bg_st_id ) assesssum,bg_st_img1");
					//sb.append(" from bi_t_orderprodrelate bi,ae_t_sysuser ae,bg_t_prodinfo bg  where ae.ae_st_id=bh.bi_st_bbid and bg.bg_st_num=bi.bi_st_spnum and  bi.bi_st_ddnum=?");
					StringBuffer sqlCount = new StringBuffer("select count(1) ");
					StringBuffer sbcommon = new StringBuffer(" FROM bi_t_orderprodrelate bi,bh_t_orderform bh,bg_t_prodinfo bg  WHERE 1=1 AND  bg_st_num =bi.bi_st_spnum and bi.bi_st_ddnum=bh.bh_st_ddnum AND bh_st_memberId=? ");
					pramsList.add(ae_st_id);
					sb.append(sbcommon).append(" order by bg_st_name ");
					sqlCount.append(sbcommon);
					PageBean pages=baseBiz.getPages(sb.toString(),sqlCount.toString(),pageIndex, pageSize,pramsList.toArray());
					jsonData = "{\"success\":\"true\",\"msg\":\"获取成功！\",\"result\":"+JsonUtils.getJsonData(pages)+"}";
				} else {
					throw new MyoneException("未获取该手机用户信息");
				}
			} else {
				jsonData = "{\"success\":\"false\",\"flag\":\"2\",\"msg\":\"请先传入完整的数据信息！\"}";
			}
		}catch (MyoneException e) {
			jsonData = "{\"success\":\"false\",\"flag\":\"3\",\"msg\":\"登录标识已经失效，请重新登录！\"}";
			e.printStackTrace();
		}catch (Exception e) {
			jsonData = "{\"success\":\"false\",\"flag\":\"1\",\"msg\":\"解析数据异常！\"}";
			e.printStackTrace();
		}
		SafetyFilter.logger.info("传出JSON:" + jsonData);
		this.createAjax(response,jsonData);
	}
	
	
	/**
	 * 查询商品订单
	 * @param pkid
	 * @return
	 */
	@RequestMapping(value="payfororderid")
	public String  payfororderid(){
		String pkid=request.getParameter("pkid");
	    String body=request.getParameter("body");
		StringBuffer sb=new StringBuffer();
		pkid=sb.append("'").append(pkid).append("'").toString();
		pkid=pkid.replaceAll(",","','");
		Double total = new Double(0);
		List<Map<String, Object>> order=null;
		if (body == null) {
			String sql = "select  *  from bh_t_orderform t  where t.bh_st_ddnum in("
					+ pkid + ")";
			order = baseBiz.queryForList(sql);
			for (int i = 0; i < order.size(); i++) {
				Double bh_st_spprice = Double.parseDouble(order.get(i).get(	"bh_st_spprice")+ "");
				total += bh_st_spprice;
			}
			
			model.addAttribute("order", order.get(0));
		}
		else{
			String applySql="SELECT * FROM  bf_t_apply WHERE orderNo="+pkid+"";
			Map<String, Object> apply = baseBiz.queryForMap(applySql);
			try {
				total=Double.parseDouble(apply.get("pricheAll")+"");
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(body!=null){
			
			model.addAttribute("body", body);
		}
		model.addAttribute("total", total);
	
		return "wap/payfor";
	}
	
	/**
	 * 解析ping++返回数据
	 */
	@RequestMapping(value="returnData")
	public void returnData(){
		try {
						//获取头部所有信息
						Enumeration headerNames = request.getHeaderNames();
						while (headerNames.hasMoreElements()) {
						    String key = (String) headerNames.nextElement();
						    String value = request.getHeader(key);
						    System.out.println(key+" "+value);
						}
						// 获得 http body 内容
						BufferedReader reader = request.getReader();
						StringBuffer buffer = new StringBuffer();
						String string;
						while ((string = reader.readLine()) != null) {
						    buffer.append(string);
						}
						reader.close();
						// 解析异步通知数据
						Event event = Webhooks.eventParse(buffer.toString());
						JSONObject josn=new JSONObject(buffer.toString());
						
					
						JSONObject charge = josn.getJSONObject("data");
			
						JSONObject chargeobj = charge.getJSONObject("object");
						String id = chargeobj.getString("id");
						String orderNo = chargeobj.getString("order_no");
						String payNo = chargeobj.getString("transaction_no");
						String body = chargeobj.getString("body");
						
				       
				     //订单类型   apply 预约  goods 产品 body
						
						if (chargeobj.getBoolean("paid")) {
							if("goods".equals(body)){
								
						    	baseBiz.executeTRANS("UPDATE bh_t_orderform SET bh_st_ddstate =7 , chargeId ='"+id+"' WHERE  payNo=?",orderNo);
						    	
						    	
						   
							}else if("apply".equals(body)){
						    	baseBiz.executeTRANS("UPDATE bf_t_apply SET payState = 1  , chargeId ='"+id+"' WHERE  orderNo=?",orderNo);
						    }
						}
				       
								if ("charge.succeeded".equals(event.getType())) {
						    response.setStatus(200);
						    
						    
						} else if ("refund.succeeded".equals(event.getType())) {
						    response.setStatus(200);
						   
						    
						} else {
						    response.setStatus(500);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		}
	
	
	@RequestMapping(value="paybackapplyfororderid")
	public String  paybackapplyfororderid()
	{
		String pkid=request.getParameter("pkid");
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("bh_st_id",pkid);
		map.put("bh_st_ddstate","5");
		baseBiz.updateTRANS(map, Bh_t_orderform.class);
		return "redirect:payment";
	}
	
	
	public  double add(double num1 , double num2){
		  return num1+num2;
		 }

	@RequestMapping(value="myshoppage")
	public String  myshoppage(){
		String type1=request.getParameter("type1");
		model.addAttribute("type", type1);
		String type=request.getParameter("type");
		Integer pageIndex=StringUtils.toIntegerByObject(request.getParameter("page"));
		String sql="select  *  from bg_t_prodinfo t  where bg_st_isdel=0 and  t.bg_st_fbtpe='"+type+"'";//发布类型1.商城2.限时抢购 
		sql=getPageSql(sql, pageIndex, 10);
		List<Map<String, Object>> shoplist = baseBiz.queryForList(sql);
		model.addAttribute("shoplist", shoplist);
		return "wap/myshoppage";
	}
	
	
	/**
	 * 抢相因
	 */
	@RequestMapping(value="cheap")
	public String cheap(){
		String type1=request.getParameter("type1");
		model.addAttribute("type", type1);
		String sql="select * , now() time  from  bp_t_activityprodrelate t,bo_t_activity t2,bg_t_prodinfo t3  where  1=1 and t.bp_st_ddnum=t2.bo_st_ddnum  and t.bp_st_spnum=t3.bg_st_num order by bo_dt_addDate   desc  ";
		List<Map<String, Object>> cheaplist = baseBiz.queryForList(sql);
		model.addAttribute("cheaplist", cheaplist);
		return "wap/cheap";
	}
	
	/**
	 * 商品详情
	 */
	@RequestMapping(value="cheapProduct")
	public String  cheapProduct(){
//		String sql="select * from  bp_t_activityprodrelate t,bo_t_activity t2,bg_t_prodinfo t3  where  1=1 and t.bp_st_ddnum=t2.bo_st_ddnum  and t.bp_st_spnum=t3.bg_st_num ";
//		List<Map<String, Object>> cheaplist = baseBiz.queryForList(sql);
//		model.addAttribute("cheaplist", cheaplist);
		return "wap/cheapProduct";
	}
	/**
	 * 建材商城
	 */
	@RequestMapping(value="jiancai")
	public String  jiancai(){
		String type1=request.getParameter("type1");
		model.addAttribute("type", type1);
		String sql="select * from bg_t_prodinfo t  where  t.bg_st_randid in (SELECT ab_st_value FROM ab_t_code where ab_st_parent IN (SELECT ab_st_id FROM ab_t_code where ab_st_parent IN (SELECT ab_st_id FROM Ab_t_code WHERE ab_st_mark='PPLX_2'))) ";
		List<Map<String, Object>> plist = baseBiz.queryForList(sql);
		model.addAttribute("plist", plist);
		return "wap/jiancai";
	}
	
	/**
	 * 商品详情
	 */
	@RequestMapping(value="goodsProduct")
	public String  goodsProduct(){
		String type1=request.getParameter("type1");
		model.addAttribute("type", type1);
		
		String pkid=request.getParameter("pkid");
		String sql="select  t.* from bg_t_prodinfo t  where t.bg_st_id='"+pkid+"'";
		Map<String, Object> plist = baseBiz.queryForMap(sql);
		model.addAttribute("prodinfo", plist);
		return "wap/goodsProduct";
	}
	//----------------------------------------
	/**
	 * 购物车
	 */
	@RequestMapping(value="mysession")
	public String  mysession(){
		String type1=request.getParameter("type1");
		model.addAttribute("type", type1);
		
		String tt=request.getParameter("tt");
		model.addAttribute("tt", tt);
		
		Date nowDate=getcuttDate();//当前操作时间
		
		String userid = null;
		try {
			userid = getcuttSysuserID();
		} catch (Exception e) {
			e.printStackTrace();
			return "wap/plogin";
		}
		
		String pkid=request.getParameter("pkid");
		String num=request.getParameter("num");
		int num2=0;
		if(num==null || "".equals(num))
		{
			num2=1;
		}else{
			num2=Integer.parseInt(num);
		}
		
		if(pkid!=null && !"".equals(pkid))
		{
			String sqlcartold="select  *  from bn_t_prodcart t  where t.bn_st_prodid='"+pkid+"'";
			List<Map<String, Object>> cartlistold = baseBiz.queryForList(sqlcartold);
			if(cartlistold!=null && cartlistold.size()>0 )
			{
					Map<String, Object> cartitem = cartlistold.get(0);
					String cartitemid= (String) cartitem.get("bn_st_id");
					int prodnum= (int) cartitem.get("bn_st_prodnum");
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("bn_st_id",cartitemid );
					map.put("bn_st_prodnum",prodnum+num2);
					baseBiz.updateTRANS(map, Bn_t_prodcart.class);
			}else{
				Bn_t_prodcart p=new Bn_t_prodcart();
				p.setBn_st_id(StringUtils.getUUID32());
				p.setBn_st_prodid(pkid);
				p.setBn_st_memberid(userid);
				p.setBn_st_prodnum(num2);
				p.setBn_dt_addDate(nowDate);
				p.setBn_st_addUserId(userid);
				List<BizTransUtil> list = new ArrayList<BizTransUtil>();
				list.add(new BizTransUtil(p));
				if(baseBiz.executesTRANS(list))
				{
					System.out.println("dddd");
				}
			}
		}
		
		String sql="select  *  from bn_t_prodcart t  left join bg_t_prodinfo t2 on t.bn_st_prodid=t2.bg_st_id  where t.bn_st_memberid='"+userid+"'";
		List<Map<String, Object>> cartlist = baseBiz.queryForList(sql);
		//\"shopinfo\":[{\"bi_st_spnum\":\"14377369956191677\",\"spname\":\"茶几\",
		//\"bi_st_spsl\":\"1\",\"bi_st_bbid\":\"1beff630505d4700aaddda171399871c\"}]}";
		StringBuffer sb=new StringBuffer();
		sb.append("&#34;shopinfo&#34;:[");
        for (Map<String, Object> p : cartlist) {
        String	bi_st_spnum	=(String) p.get("bg_st_num");
        String	spname	=(String) p.get("bg_st_name");
        String	bi_st_spsl	= p.get("bn_st_prodnum")+"";
        String	bi_st_bbid	=(String) p.get("bg_st_bbid");
        sb.append("{").append("&#34;bi_st_spnum&#34;:").append("&#34;"+bi_st_spnum+"&#34;").append(",")
        .append("&#34;spname&#34;:").append("&#34;"+spname+"&#34;").append(",")
        .append("&#34;bi_st_spsl&#34;:").append("&#34;"+bi_st_spsl+"&#34;").append(",").
        append("&#34;bi_st_bbid&#34;:").append("&#34;"+bi_st_bbid+"&#34;").append("},");
		}
        if(sb.length()>1){
        	sb=sb.deleteCharAt(sb.length()-1);
        }
        sb.append("]");
       // String str=sb.toString().replaceAll("&#34;", "\\\\&#34;");
        model.addAttribute("shopinfo", sb.toString());
		model.addAttribute("cartlist", cartlist);
		return "wap/mysession";
	}
	/**
	 * 删除购物车
	 */
	@RequestMapping(value="deleteProduct")
	public void deleteProduct(){
		 String id=request.getParameter("id");
		 if(baseBiz.deleteTRANS(id, Bn_t_prodcart.class)){
			 this.createAjax(response,"0");// success
		 }else{
			 this.createAjax(response,"1");//fail
		 }
		
	}
	
	/**
	 * 添加一个商品到购物车
	 */
	@RequestMapping(value="addCar")
	public void  addCar(){
		String jsonData = "";
		String type1=request.getParameter("type1");
		model.addAttribute("type", type1);
		Date nowDate=getcuttDate();//当前操作时间
		//getcuttSysuserID(); 测试id为1
		String userid = null;
		try {
			userid = getcuttSysuserID();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jsonData = "{\"success\":\"false\",\"msg\":\"请先登录\"}";
			SafetyFilter.logger.info("传出JSON:" + jsonData);
			this.createAjax(response,jsonData);
			return;
		}
		String pkid=request.getParameter("pkid");
		String num=request.getParameter("num");
		int num2=0;
		if(num==null || "".equals(num))
		{
			num2=1;
		}else{
			num2=Integer.parseInt(num);
		}
		
		if(pkid!=null && !"".equals(pkid))
		{
			String sqlcartold="select  *  from bn_t_prodcart t  where t.bn_st_memberid = '"+userid+"'  and t.bn_st_prodid='"+pkid+"'";
			List<Map<String, Object>> cartlistold = baseBiz.queryForList(sqlcartold);
			if(cartlistold!=null && cartlistold.size()>0 )
			{
					Map<String, Object> cartitem = cartlistold.get(0);
					String cartitemid= (String) cartitem.get("bn_st_id");
					int prodnum= (int) cartitem.get("bn_st_prodnum");
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("bn_st_id",cartitemid );
					map.put("bn_st_prodnum",prodnum+num2);
					baseBiz.updateTRANS(map, Bn_t_prodcart.class);
			}else{
				Bn_t_prodcart p=new Bn_t_prodcart();
				p.setBn_st_id(StringUtils.getUUID32());
				p.setBn_st_prodid(pkid);
				p.setBn_st_memberid(userid);
				p.setBn_st_prodnum(num2);
				p.setBn_dt_addDate(nowDate);
				p.setBn_st_addUserId(userid);
				List<BizTransUtil> list = new ArrayList<BizTransUtil>();
				list.add(new BizTransUtil(p));
				if(baseBiz.executesTRANS(list))
				{
					System.out.println("dddd");
				}
			}
			
			
			
		}
		
		jsonData = "{\"success\":\"true\",\"msg\":\"添加成功\"}";
		SafetyFilter.logger.info("传出JSON:" + jsonData);
		this.createAjax(response,jsonData);
		
		
		
		/*String sql="select  *  from bn_t_prodcart t  left join bg_t_prodinfo t2 on t.bn_st_prodid=t2.bg_st_id  where t.bn_st_memberid='"+userid+"'";
		List<Map<String, Object>> cartlist = baseBiz.queryForList(sql);
		model.addAttribute("cartlist", cartlist);
		return "wap/mysession";*/
	}
	/**
	 *评价添加接口
	 * @param json
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="addAssess")
	public void addAssess(String json,HttpServletResponse response,HttpServletRequest request){
		SafetyFilter.logger.info("传入JSON:" + json);
		request.getParameter("json");
		String jsonData = "";
		List<BizTransUtil> list = new ArrayList<BizTransUtil>();
		try {
			Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据
			String be_st_bgid = StringUtils.toStringByObject(maps.get("be_st_bgid"),true);//商品评价对象 【FK】
			String be_st_jbgid = StringUtils.toStringByObject(maps.get("be_st_jbgid"),true);//商品评价对象 【FK】
			String be_st_content = StringUtils.toStringByObject(maps.get("be_st_content"),true); //电话
			Integer be_nm_shopquality = StringUtils.toIntegerByObject(maps.get("be_nm_shopquality")); //商品星级
			Integer be_nm_manner = StringUtils.toIntegerByObject(maps.get("be_nm_manner"));//服务诚信星级
			Integer be_nm_quality= StringUtils.toIntegerByObject(maps.get("be_nm_quality"));//服务质量星级
			Integer be_nm_integrity= StringUtils.toIntegerByObject(maps.get("be_nm_integrity"));//服务诚信星级
			String isshopassess = StringUtils.toStringByObject(maps.get("isshopassess"),true);//是否为商品评价 1是 0否
			//String loginflag = StringUtils.toStringByObject(maps.get("loginflag"),true); //登录标识
			//验证不能为空
	        String ae_st_id=	getcuttSysuserID();
	        
			if (org.springframework.util.StringUtils.hasText(ae_st_id)) {
				//查询该手机用户ID
			String mqb="update ae_t_sysuser set vouchers = vouchers+1 where ae_st_id='"+ae_st_id+"'" ;
			baseBiz.executeTRANS(mqb);
				//List<Map<String, Object>> listuser=baseBiz.queryForList("select a.ae_st_id from ae_t_sysuser a where a.ae_st_loginflag=? ", new Object[]{loginflag});
				//if(listuser!=null&&listuser.size()==1){
					//当前手机用户登录人ID
					//String ae_st_id =StringUtils.toStringByObject(listuser.get(0).get("ae_st_id"));
					//该会员下面的所有的收货地址
					Be_t_Assess assess = new Be_t_Assess(true);
					if(isshopassess.equals("1")){
						assess.setBe_st_bgid(be_st_bgid);
						assess.setBe_nm_shopquality(be_nm_shopquality);
					}else if(isshopassess.equals("0")){
						assess.setBe_st_jbgid(be_st_jbgid);
						assess.setBe_nm_manner(be_nm_manner);
						assess.setBe_nm_quality(be_nm_quality);
						assess.setBe_nm_integrity(be_nm_integrity);
					}
					assess.setBe_st_content(be_st_content);
					assess.setBe_st_fbgid(ae_st_id);
					assess.setBe_dt_addDate(getcuttDate());
					assess.setBe_st_addUserId(ae_st_id);
					list.add(new BizTransUtil(assess));
					if(baseBiz.executesTRANS(list)){
						jsonData = "{\"success\":\"true\",\"flag\":\"1\",\"msg\":\"评价成功！\"}";
					}
				/*} else {
					throw new MyoneException("未获取该手机用户信息");
				}*/
			} else {
				jsonData = "{\"success\":\"false\",\"flag\":\"2\",\"msg\":\"请先传入完整的数据信息！\"}";
			}
		}catch (Exception e) {
			jsonData = "{\"success\":\"false\",\"flag\":\"1\",\"msg\":\"解析数据异常！\"}";
			e.printStackTrace();
		}
		SafetyFilter.logger.info("传出JSON:" + jsonData);
		this.createAjax(response,jsonData);
	}
	
	/**
	 * 去评价
	 */
	@RequestMapping(value="previews")
	public String  previews(){
		String type1=request.getParameter("type1");
		model.addAttribute("type", type1);
		String sjs=request.getParameter("sjs");
		model.addAttribute("sjs", sjs);
		
		String pj=request.getParameter("pj");
		model.addAttribute("pj", pj);
		
			String ae_st_id =getcuttSysuserID();
			String bf_st_id = request.getParameter("aid"); //服务的主键ID
			//验证不能为空
			if (org.springframework.util.StringUtils.hasText(ae_st_id)&&org.springframework.util.StringUtils.hasText(bf_st_id)) {
				//查询该手机用户ID
				if(StringUtils.hasText(ae_st_id)){
					//当前手机用户登录人ID
					//该会员下面的所有的收货地址                
					StringBuffer sb = new StringBuffer("SELECT ae.ae_st_id,bf.bf_st_id,ba.ba_st_id,ba.ba_st_grade,ba.ba_st_team_intro,ba.ba_st_teamnum,ae_st_name,(SELECT  ag_st_url FROM  ag_t_file WHERE ag_st_objid=ae.ae_st_id  and ag_st_mark='userfile' limit 0,1) ag_st_url,(SELECT COUNT(1) FROM bf_t_apply WHERE bf_st_receiveid=ba.ba_st_id)ordersum");
					StringBuffer sbcommon = new StringBuffer( " FROM ae_t_sysuser ae,ba_t_labour ba ,bf_t_apply bf WHERE bf.bf_st_receiveid=ae.ae_st_id AND ba.ba_st_userid=ae.ae_st_id AND bf_st_userid=? and bf_st_id=? ");
					List<String> pramsList=new ArrayList<String>();
					pramsList.add(ae_st_id);
					pramsList.add(bf_st_id);
					sb.append(sbcommon);
					List<Map<String, Object>> listinfo=baseBiz.queryForList(sb.toString(), pramsList.toArray());
					if(listinfo!=null&&listinfo.size()>0){
					model.addAttribute("user", listinfo.get(0));}
					if(listinfo!=null&&listinfo.size()>0){
						List<Map<String,Object>> listreulst=baseBiz.queryForList("select ag_st_url from ag_t_file where ag_st_objid=? and ag_st_mark='JSRUEST'",new Object[]{bf_st_id});
						model.addAttribute("data", listreulst);
					}
				} 
			}
		
		
		return "wap/previews";
	}
	//-------------------------------------
	/**
	 * 我的
	 */
	@RequestMapping(value="pmyzone")
	public String  pmyzone(){
		try {
			Ae_t_sysuser user=	getcuttSysuser();
			Ae_t_sysuser showUser=null;
			showUser=user;
			String username=user.getUsername();
			StringBuffer sb=new StringBuffer();
			if(StringUtils.hasText(username)&&username.length()==11){
				sb.append(username.subSequence(0, 3)).append("*****").append(username.subSequence(8, 11));
			}
			showUser.setUsername(sb.toString());
			model.addAttribute("user", showUser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute("user", null);
			e.printStackTrace();
		}
		return "wap/pmyzone";
	}
	/*
	 * 用户注销
     */
	@RequestMapping(value="exit")
	public String exit(){
		return "wap/pmyzone";
	}
	/**
	 * 登陆
	 */
	@RequestMapping(value="plogin")
	public String  plogin(){
		String type1=request.getParameter("type1");
		model.addAttribute("type", type1);
		return "wap/plogin";
	}
	/**
	 * 注册
	 */
	@RequestMapping(value="psign")
	public String  psign(){
		String type1=request.getParameter("type1");
		model.addAttribute("type", type1);
		return "wap/psign";
	}
	/**
	 * 我的服务
	 */
	@RequestMapping(value="myservice")
	public String  myservice(){
		String type1=request.getParameter("type1");
		model.addAttribute("type", type1);
		return "wap/myservice";
	}
	/**
	 * 付款记录
	 */
	@RequestMapping(value="payment")
	public String  payment(){
		return "wap/payment";
	}
	/**
	 * 意见反馈
	 */
	@RequestMapping(value="opinion")
	public String  opinion(){
		return "wap/opinion";
	}
	/**
	 * 关于我们
	 */
	@RequestMapping(value="paboutus")
	public String  paboutus(){
		return "wap/paboutus";
	}
	
	
	 	
	
	
	//-------------------------------xyg 
	/*
	 *入住
	 */
	@RequestMapping(value="addApply")
	public String  addApply(Bf_t_Apply bf){
		
		String userid = null;
		try
		{
			userid = getcuttSysuserID();
			bf.setBf_st_userid(userid);
		}
		catch (Exception e) {
			//e.printStackTrace();
			// TODO Auto-generated catch block
				e.printStackTrace();
				if(!"2".equals(bf.getBf_st_type())){
					return "wap/plogin";
					
				}
		}
		
		
		String type1=request.getParameter("type1");
		model.addAttribute("type", type1);
		String id=StringUtils.getUUID32();
		System.out.println(bf);
		Date nowDate=getcuttDate();//当前操作时间
		String orderNo="D"+nowDate.getTime()+(int)(Math.random()*90000+10000);
		try {
			List<BizTransUtil> list = new ArrayList<BizTransUtil>();
			//修改预约
			if(org.springframework.util.StringUtils.hasText(bf.getBf_st_id()))
			{
				
			}
			//添加预约
			else{
				bf.setBf_st_id(id);// 主键ID
				bf.setBf_st_addUserId(bf.getBf_st_addUserId());//创建人员ID]bf.set
				bf.setBf_dt_addDate(getcuttDate());// 创建时间
				
				bf.setOrderNo(orderNo);
				String tjyy="SELECT ba_st_price FROM ba_t_labour WHERE ba_st_userid='"+bf.getBf_st_receiveid()+"'";
				Map<String, Object> plist = baseBiz.queryForMap(tjyy);
				
				if ("3".equals(bf.getBf_st_type())) {
					if (StringUtils.hasText(bf.getBf_st_area())) {
						int area = Integer.parseInt(bf.getBf_st_area());
						bf.setPricheAll((area * 2) + "");
					}
				} else if("7".equals(bf.getBf_st_type())||"8".equals(bf.getBf_st_type())){
					bf.setPricheAll(100+"");
					
				}else{
					
					if(StringUtils.hasText(plist.get("ba_st_price")+"")){
						
					Double pri=	Double.parseDouble(plist.get("ba_st_price")+"");
					int area = Integer.parseInt(bf.getBf_st_area());
					bf.setPricheAll((pri * area) + "");
					}
				}
				list.add(new BizTransUtil(bf));
			}
			//执行操作
			if (baseBiz.executesTRANS(list)) {
				model.addAttribute("msg", "申请提交成功!");
				String code = null;
				if("2".equals(bf.getBf_st_type())){
					code="2";
				}
				if("13".equals(bf.getBf_st_type())){
					code="13";
				}
				if("3".equals(bf.getBf_st_type())){
					code="3";
				}
				if("5".equals(bf.getBf_st_type())){
					code="5";
				}
				if("8".equals(bf.getBf_st_type())){
					code="8";
				}
				if("6".equals(bf.getBf_st_type())){
					code="6";
				}
				if("7".equals(bf.getBf_st_type())){
					code="7";
				}
				if("10".equals(bf.getBf_st_type())){
					code="10";
				}
				
				String content = "";
				String type = bf.getBf_st_type();
				String uri = "http://222.73.117.158/msg/";// 应用地址
				String account = "wangzhong";// 账号
				String pswd = "Tch147258";// 密码
				if ("1".equals(type)) {
					content = "恭喜您成功申请了网众科技旗下平台的一键入住,我们会在24小时内和您联系，请注意接听电话,网众科技客服电话400-028-5998， 如有疑问可以添加我要验房QQ群：123582777";
				} else if ("2".equals(type)) {
					content = "恭喜您成功申请了网众科技旗下平台的免费验房,我们会在24小时内和您联系，请注意接听电话,网众科技客服电话400-028-5998， 如有疑问可以添加我要验房QQ群：123582777";
				} else if ("3".equals(type)) {
					content = "恭喜您成功申请了网众科技旗下平台的金牌验房,我们会在24小时内和您联系，请注意接听电话,网众科技客服电话400-028-5998， 如有疑问可以添加我要验房QQ群：123582777";
				} else if ("4".equals(type)) {
					content = "恭喜您成功申请了网众科技旗下平台的免费设计,我们会在24小时内和您联系，请注意接听电话,网众科技客服电话400-028-5998， 如有疑问可以添加我要验房QQ群：123582777";
				} else if ("5".equals(type)) {
					content = "恭喜您成功申请了网众科技旗下平台的个性设计,我们会在24小时内和您联系，请注意接听电话,网众科技客服电话400-028-5998， 如有疑问可以添加我要验房QQ群：123582777";
				}
				/*
				 * else if("6".equals(type)) {
				 * content="恭喜您成功预约了网众科技旗下平台工长,我们会在24小时内和您联系，请注意接听电话,网众科技客服电话400-028-5998， 如有疑问可以添加我要验房QQ群：123582777"; }
				 */
				else if ("7".equals(type)) {
					content = "恭喜您成功预约了网众科技旗下平台工长,我们会在24小时内和您联系，请注意接听电话,网众科技客服电话400-028-5998， 如有疑问可以添加我要验房QQ群：123582777";
				} else if ("8".equals(type)) {
					content = "恭喜您成功预约了网众科技旗下平台师傅,我们会在24小时内和您联系，请注意接听电话,网众科技客服电话400-028-5998， 如有疑问可以添加我要验房QQ群：123582777";
				} else if ("9".equals(type)) {
					content = "恭喜您成功预约了网众科技旗下平台监理,我们会在24小时内和您联系，请注意接听电话,网众科技客服电话400-028-5998， 如有疑问可以添加我要验房QQ群：123582777";
				} else if ("10".equals(type)) {
					content = "恭喜您成功预约了网众科技旗下平台收费监理,我们会在24小时内和您联系，请注意接听电话,网众科技客服电话400-028-5998， 如有疑问可以添加我要验房QQ群：123582777";
				} else if ("12".equals(type)) {
					content = "恭喜您成功预约了网众科技旗下平台免费空气检测,我们会在24小时内和您联系，请注意接听电话,网众科技客服电话400-028-5998， 如有疑问可以添加我要验房QQ群：123582777";
				} else if ("13".equals(type)) {
					content = "恭喜您成功预约了网众科技旗下平台收费空气检测我们会在24小时内和您联系，请注意接听电话,网众科技客服电话400-028-5998， 如有疑问可以添加我要验房QQ群：123582777";
				}
				// String content =
				// "恭喜您成功预约了网众科技旗下平台收费空气检测我们会在24小时内和您联系，请注意接听电话,网众科技客服电话400-028-5998， 如有疑问可以添加我要验房QQ群：123582777";//短信内容
				boolean needstatus = true;// 是否需要状态报告，需要true，不需要false
				String product = null;// 产品ID
				String extno = null;// 扩展码
				String returnString = null;
				try {
					returnString = HttpSender.batchSend(uri, account, pswd, bf.getBf_st_tell(), content, needstatus, product, extno);
					System.out.println(returnString);
					// TODO 处理返回值,参见HTTP协议文档
				} catch (Exception e) {
					// TODO 处理异常
					e.printStackTrace();
				}
				
				model.addAttribute("code",code);
				model.addAttribute("price",bf.getPricheAll());
				model.addAttribute("id",id);
				model.addAttribute("orderNo", orderNo);
				return "wap/msg";
			}
			else 
			{
				model.addAttribute("msg", "入住提交失败!");
				return "wap/fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "wap/fail";
		}
	}
	
	/**
	 * 免费设计
	 * @param apply
	 * @param mphsr
	 * @return
	 */
	@RequestMapping(value="addApplyWitFile")
	public String  addApplyWitFile(Bf_t_Apply apply,MultipartHttpServletRequest mphsr){
		String type1=request.getParameter("type1");
	
		Date nowDate=getcuttDate();//当前操作时间
		String orderNo="D"+nowDate.getTime()+(int)(Math.random()*90000+10000);
		String userid = null;
		try
		{
			userid = getcuttSysuserID();
			apply.setBf_st_userid(userid);
		}
		catch (Exception e) {
			//e.printStackTrace();
		}
		String sql="select  t.* from ba_t_labour t  where t.ba_st_userid='"+apply.getBf_st_receiveid()+"'";
		Map<String, Object> plist = baseBiz.queryForMap(sql);
		
		String priceStr=plist.get("ba_st_price")+"";
		try {
			Double priceDou=Double.parseDouble(priceStr);
			
			model.addAttribute("type", type1);
			
			if (StringUtils.hasText(apply.getBf_st_area())) {
				int area = Integer.parseInt(apply.getBf_st_area());
				apply.setPricheAll((area * priceDou) + "");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try{
			Object obje=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			List<BizTransUtil> listSql = new ArrayList<BizTransUtil>();
			apply.setBf_st_id(StringUtils.getUUID32());//ID 
			apply.setBf_dt_addDate(getcuttDate());//创建时间
				try {
					apply.setBf_st_addUserId(getcuttSysuserID());//创建人员ID
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					if("5".equals(apply.getBf_st_type())){
						return "wap/plogin";
						
					}
				}
			apply.setOrderNo(orderNo);
			listSql.add(new BizTransUtil(apply));
			
			Map<String, MultipartFile> filemap=mphsr.getFileMap();
			Iterator<String> it = filemap.keySet().iterator();
			while(it.hasNext())
			{
				String filekey=it.next();
				MultipartFile item = filemap.get(filekey);
				Ag_t_file file = new Ag_t_file();
				file.setAg_st_mark("1");
				String uploadPath="";
				if(item!=null && item.getSize()>0){
					uploadPath=UploadFile.uploadInputStream(item.getInputStream(), item.getOriginalFilename(), "images/business/ad");
					BufferedImage buff = ImageIO.read(item.getInputStream());
					file.setAg_nm_height(buff.getHeight());//文件属性高
					file.setAg_nm_width(buff.getWidth());//文件属性宽
					file.setAg_nm_size(item.getSize());//文件属性大小
					file.setAg_st_format(item.getContentType());//文件格式
				}
				file.setAg_dt_addDate(getcuttDate());//创建时间
				file.setAg_st_addUserId(apply.getBf_st_addUserId());//创建人员ID  
				file.setAg_st_objid(apply.getBf_st_id());//文件归属ID
				file.setAg_st_objtype("bf_t_apply");//文件对应的对象
				file.setAg_st_type("1");//文件类型-图片
				file.setAg_st_url(uploadPath);////文件 的存储地址
				file.setAg_st_id(StringUtils.getUUID32());
				listSql.add(new BizTransUtil(file));
				
				
						
			}
			//执行事务操作
			if(SysStatic.baseBiz.executesTRANS(listSql)){
				String content = "";
				String uri = "http://222.73.117.158/msg/";// 应用地址
				String account = "wangzhong";// 账号
				String pswd = "Tch147258";// 密码
				if("5".equals(apply.getBf_st_type())){
					
					content = "恭喜您成功申请了网众科技旗下平台的个性设计,我们会在24小时内和您联系，请注意接听电话,网众科技客服电话400-028-5998， 如有疑问可以添加我要验房QQ群：123582777";
				}else if("4".equals(apply.getBf_st_type())){
					content = "恭喜您成功申请了网众科技旗下平台的免费设计,我们会在24小时内和您联系，请注意接听电话,网众科技客服电话400-028-5998， 如有疑问可以添加我要验房QQ群：123582777";
				}
				// String content =
				// "恭喜您成功预约了网众科技旗下平台收费空气检测我们会在24小时内和您联系，请注意接听电话,网众科技客服电话400-028-5998， 如有疑问可以添加我要验房QQ群：123582777";//短信内容
				boolean needstatus = true;// 是否需要状态报告，需要true，不需要false
				String product = null;// 产品ID
				String extno = null;// 扩展码
				String returnString = null;
				try {
					returnString = HttpSender.batchSend(uri, account, pswd,
							apply.getBf_st_tell(), content, needstatus, product,
							extno);
					System.out.println(returnString);
					// TODO 处理返回值,参见HTTP协议文档
				} catch (Exception e) {
					// TODO 处理异常
					e.printStackTrace();
				}
				
				model.addAttribute("orderNo", orderNo);
				
                if("5".equals(apply.getBf_st_type())){
                	model.addAttribute("code", "5");
                	model.addAttribute("msg", "收费验房申请成功！");
					
				}else if("4".equals(apply.getBf_st_type())){
					model.addAttribute("code", "4");
					model.addAttribute("msg", "免费验房申请成功！");
				}
              
              return "wap/msg";
				
			} else {
				model.addAttribute("msg", "业务操作失败!");
				return "wap/fail";
			}
		}catch (RuntimeException e) {
			e.printStackTrace();
			model.addAttribute("msg", "登录标识已经失效，请重新登录!");
			return "wap/fail";
		}catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "解析数据异常!");
			return "wap/fail";
		}
	}
	
	/**
	 * 获取个性设计列表
	 * @return
	 */
	@RequestMapping(value="designstyle")
	public String designstyle(){
		
		String type1=request.getParameter("type1");
		model.addAttribute("type", type1);
		String pa=request.getParameter("type");
		String ae_st_type=request.getParameter("ae_st_type");
		
		if(ae_st_type==null||"".equals(ae_st_type)){
			ae_st_type="'5'";
		}
		Integer pageIndex=StringUtils.toIntegerByObject(request.getParameter("page"));
		StringBuffer sb=new StringBuffer("select u.ae_st_name ,u.ae_st_id ,u.ae_st_intro ,l.ba_st_grade  ,(select ag_st_url from ag_t_file where ag_st_objid = u.ae_st_id) ag_st_url "); 
		StringBuffer sbcount=new StringBuffer("select count(u.ae_st_id) ");
		StringBuffer sbcommon=new StringBuffer(" from ae_t_sysuser u,ba_t_labour l where ae_st_type ="+ae_st_type+" and l.ba_st_type='"+pa+"' and l.ba_st_userid=u.ae_st_id ");
		List<String> params=new ArrayList<String>();
		sbcount.append(sbcommon);
		sb.append(sbcommon).append(" order by u.ae_dt_addDate desc ");
		//分页数据、分页配置 查询--
		PageBean pages=baseBiz.getPages(sb.toString(),sbcount.toString(),pageIndex, 10,params.toArray());
		model.addAttribute("type", pa);
		model.addAttribute("pageBeanObj", pages);//分页对象
		
		return "wap/designstyle";
	}
	

	
	
	
	// 流转化成文件
	public static void inputStream2File(InputStream is, String savePath) throws Exception{
		System.out.println("文件保存路径为:" + savePath);
		
		File file = new File(savePath);
		InputStream inputSteam = is;
		BufferedInputStream fis = new BufferedInputStream(inputSteam);
		FileOutputStream fos = new FileOutputStream(file);
		int f;
		while ((f = fis.read()) != -1){
			fos.write(f);
		}
		fos.flush();
		fos.close();
		fis.close();
		inputSteam.close();
	}
	
	
	
	
}



