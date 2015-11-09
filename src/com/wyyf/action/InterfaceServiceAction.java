package com.wyyf.action;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ezmorph.bean.MorphDynaBean;

import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import sun.misc.BASE64Decoder;

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
import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.PingppException;
import com.pingplusplus.model.Charge;
import com.pingplusplus.model.Event;
import com.pingplusplus.model.Refund;
import com.pingplusplus.model.Webhooks;
import com.power.bean.Ae_t_sysuser;
import com.power.bean.Af_t_sysuserrole;
import com.power.bean.Ag_t_file;
import com.wyyf.action.dx.HttpSender;
import com.wyyf.bean.Ba_t_Labour;
import com.wyyf.bean.Bb_t_ShopUser;
import com.wyyf.bean.Be_t_Assess;
import com.wyyf.bean.Bf_t_Apply;
import com.wyyf.bean.Bh_t_orderform;
import com.wyyf.bean.Bi_t_OrderProdRelate;
import com.wyyf.bean.Bk_t_HelpsCollect;
import com.wyyf.bean.Bq_t_UsefulAddress;


/**
 * app移动接口
 * @author laolang
 *
 */

@Scope(value = "prototype")
@Controller("InterfaceServiceAction")
@RequestMapping(value = "/services")
public class InterfaceServiceAction extends BaseAjaxAction{
//
	public static String apiKey = "sk_live_zGMmfNLh87sghw4qjeWs4DnP";
	public static String ios = "app_b1e9mPO0u94S1aH0";
	String uri = "http://222.73.117.158/msg/";// 应用地址
	String account = "wangzhong";// 账号
	public static String html="app_j9S4O4G00GC0jHWj";
	/**
	 * pingpp 管理平台对应的应用ID
	 */
	public static String android = "app_evPuL0iXnTGGn5WP";
	String pswd = "Tch147258";// 密码
	boolean needstatus = true;// 是否需要状态报告，需要true，不需要false
	String product = null;// 产品ID
	String extno = null;// 扩展码
	/**
	 * 第三方登录
	 */
	
	@RequestMapping(value="loginOth")//处理三方登陆的接口
	public void loginOth(String json){
		SafetyFilter.logger.info("传入JSON:" + json);
		String jsonData = "";
		List<BizTransUtil> list = new ArrayList<BizTransUtil>();
		try {
			Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据
			String uid = StringUtils.toStringByObject(maps.get("uid"),true);//帐号/邮箱/手机号
			String type = StringUtils.toStringByObject(maps.get("type"),true);//帐号/邮箱/手机号
			//查询该手机用户信息
			/*String ll="";
			if("1".equals(type)){
				ll="  a.xl = "+uid;
			}
			if("2".equals(type)){
				ll="  a.wx = "+uid;
			}
			if("3".equals(type)){
				ll="  a.uid = '"+uid+"'";
			}*/
			
			String sql="select ae_st_id , ae_st_intro,  ae_st_shshi , ae_st_sex  , ae_nm_age ,  ae_st_id,ae_st_lockstate,ae_st_name ,ae_st_tell ,(select ag_st_url from ag_t_file where ag_st_objid = a.ae_st_id and ag_st_mark='userfile' limit 0,1 ) ag_st_url  from ae_t_sysuser a where 1=1   ";
			String str="";
			StringBuffer sb=new StringBuffer();
			if("1".equals(type)){
				str=" and xl = '"+uid+"'";
			}
			if("2".equals(type)){
				str=" and wx = '"+uid.replaceAll("-", "_")+"'";
			}
			if("3".equals(type)){
				str=" and uid = '"+uid+"'";
			}
			sb.append(sql).append(str);
			
			Map<String, Object> listuser=baseBiz.queryForMap(sb.toString());
			if(listuser.size()>0){
				List<Map<String, Object>> l=new ArrayList<Map<String, Object>>();
				l.add(listuser);
				//如果存在登录标识,则返回以前登录标识
				String loginflag=StringUtils.getUUID32();//生成登录标识
				//修改登录标识
				baseBiz.executeTRANS("update ae_t_sysuser set ae_st_lastlogonIp=?,ae_dt_updDate=?,ae_st_loginflag =? where ae_st_id =? ",CommonUtil.overshot(request),getcuttDate(), loginflag,listuser.get("ae_st_id"));
				jsonData = "{\"success\":\"true\",\"msg\":\"登录成功！\",\"loginflag\":\""+loginflag+"\",\"result\":"+JsonUtils.getJsonDataFromCollection(l)+"}";
		
			}else{
				jsonData = "{\"success\":\"false\",\"flag\":\"1\",\"msg\":\"用户不存在\"}";
			}
			
		}catch (Exception e1) {
			jsonData = "{\"success\":\"false\",\"flag\":\"1\",\"msg\":\"用户不存在\"}";
		}
		SafetyFilter.logger.info("传出JSON:" + jsonData);
		this.createAjax(response,jsonData);
	}
	/**
	 * 绑定手机号
	 * @param json
	 */
	@RequestMapping(value="bindingTel")
	public void bindingTel(String json){
		String jsonData="";
		Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据
		String username = StringUtils.toStringByObject(maps.get("username"),true); //支付类型
		String password = StringUtils.toStringByObject(maps.get("password"),true); //访问类型 1android 2 ios
		String uid = StringUtils.toStringByObject(maps.get("uid"),true); //
		String type = StringUtils.toStringByObject(maps.get("type"),true); //qq 新浪  微信
		
		Encoder e=new MD5Encoder();//使用security3 的MD5加密技术
		if (org.springframework.util.StringUtils.hasText(username) && org.springframework.util.StringUtils.hasText(password)) {
			password= e.encrypt(password,username);
			List<Map<String, Object>> list=baseBiz.queryForList("select uid , ae_st_intro,  ae_st_shshi , ae_st_sex  , ae_nm_age ,  ae_st_id,ae_st_lockstate,ae_st_name ,ae_st_tell ,(select ag_st_url from ag_t_file where ag_st_objid = a.ae_st_id and ag_st_mark='userfile' limit 0,1 ) ag_st_url from ae_t_sysuser a where username=? and password=?" , new Object[]{username,password});
			if(list!=null&&list.size()==1){
				String ae_st_id=StringUtils.toStringByObject(list.get(0).get("ae_st_id"));
				String ae_st_lockstate=StringUtils.toStringByObject(list.get(0).get("ae_st_lockstate"));
				if(!"1".equals(ae_st_lockstate)){
					try {
						throw new MyoneException("账户状态已被注销或停用。");
					} catch (MyoneException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				String str="";
				if("1".equals(type)){
					str=" , xl = '"+uid+"'";
				}
				if("2".equals(type)){
					uid=uid.replaceAll("-", "_");
					str=" , wx = '"+uid+"'";
				}
				if("3".equals(type)){
					str=" , uid = '"+uid+"'";
				}
				
				
				//如果存在登录标识,则返回以前登录标识
				String loginflag=StringUtils.getUUID32();//生成登录标识
				//修改登录标识
				baseBiz.executeTRANS(" update ae_t_sysuser set ae_st_lastlogonIp=?,ae_dt_updDate=?,ae_st_loginflag =?  "+str+" where ae_st_id =? ",CommonUtil.overshot(request),getcuttDate(), loginflag,ae_st_id);
				jsonData = "{\"success\":\"true\",\"msg\":\"登录成功！\",\"loginflag\":\""+loginflag+"\",\"result\":"+JsonUtils.getJsonDataFromCollection(list)+"}";
				
				
			} else {
				jsonData = "{\"success\":\"false\",\"flag\":\"3\",\"msg\":\"用户名或密码错误！\"}";
			}
		} else {
			jsonData = "{\"success\":\"false\",\"flag\":\"2\",\"msg\":\"请先传入完整的数据信息！\"}";
		}
		  this.createAjax(response,jsonData);
	}
	
	
	 /**
     * 创建Charge
     * @return
     */
	@RequestMapping(value="charge")
    public void charge(String json) {
		SafetyFilter.logger.info("传入JSON:" + json);
		//String title,String body,String amount,String t,String ip
		Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据
	/*	String title = StringUtils.toStringByObject(maps.get("title"),true);//标题
		String body = StringUtils.toStringByObject(maps.get("body"),true); //商品的描述信息 Unicode 
		String amount = StringUtils.toStringByObject(maps.get("amount"),true); //钱
*/		
		String t = StringUtils.toStringByObject(maps.get("type"),true); //支付类型
		String isios = StringUtils.toStringByObject(maps.get("isios"),true); //访问类型 1android 2 ios 3 html
		String ip = StringUtils.toStringByObject(maps.get("ip"),true); //ip
		String orderNo = StringUtils.toStringByObject(maps.get("orderNo"),true); //订单编号
		orderNo=orderNo.replaceAll(",","Z");
     	String body = StringUtils.toStringByObject(maps.get("body"),true); //订单类型   apply 预约  goods 产品 body
     	Date nowDate=getcuttDate();//当前操作时间
     	String payNo="D"+nowDate.getTime()+(int)(Math.random()*90000+10000);

		Pingpp.apiKey = apiKey;
        Charge charge = null;
        String type=null; //1 支付宝 2、微信 3、 银联卡
        if("1".equals(t)){
        	type="alipay";
        }else if("2".equals(t)){
        	type="wx";
        }else if("3".equals(t)){
        	type="upacp";
        }//
        else if("4".equals(t)){
        	type="alipay_wap";
        }
        else if("5".equals(t)){
        	type="upacp_wap";
        }
        
		if (type == null) {
			this.createAjax(response, "支付类型不能为空");// 1
			return;
		}
		//Map<String, Object> list=null;
		
		StringBuffer sb1=new StringBuffer();
		
		if(orderNo.indexOf("Z")!=-1){
			String[] str=orderNo.split("Z");
			for(String s:str)
			{
				if("goods".equals(body)){
					Map<String, Object> list =baseBiz.queryForMap("select bh_st_spprice , ae_st_name  from bh_t_orderform o , ae_t_sysuser u where  o.bh_st_bbid = u.ae_st_id and bh_st_ddnum = ?", new Object[]{s});
					sb1.append(list.get("ae_st_name")).append(",");
					baseBiz.executeTRANS("UPDATE bh_t_orderform SET  payNo= '"+payNo+"' WHERE  bh_st_ddnum=?",s);
				}
			}
			
			
			
		}else{
			
			if("apply".equals(body)){
				Map<String, Object> list=baseBiz.queryForMap("SELECT  pricheAll , bf_st_name from bf_t_apply a , ae_t_sysuser u WHERE   a.bf_st_userid =u.ae_st_id and  orderNo=?", new Object[]{orderNo});
				sb1.append(list.get("bf_st_name"));
				baseBiz.executeTRANS("UPDATE bf_t_apply SET payNo= '"+payNo+"' , bh_st_source = "+isios+" ,payType="+t+"   WHERE  orderNo=?",orderNo);
			}else if("goods".equals(body)){
				Map<String, Object> list=baseBiz.queryForMap("select bh_st_spprice , ae_st_name  from bh_t_orderform o , ae_t_sysuser u where  o.bh_st_bbid = u.ae_st_id and bh_st_ddnum = ?", new Object[]{orderNo});
				sb1.append(list.get("ae_st_name"));
				baseBiz.executeTRANS("UPDATE bh_t_orderform SET  payNo= '"+payNo+"' , bh_st_source = "+isios+" ,payType="+t+" WHERE  bh_st_ddnum=?",orderNo);
			}
			
		}
		
		
		
		
		
		
        
        Map<String, Object> chargeMap = new HashMap<String, Object>();
        String price="1";///list.get("bh_st_spprice").toString().substring(0, list.get("bh_st_spprice").toString().indexOf("."));
        chargeMap.put("amount","2" /* Integer.parseInt(price)*100*/);//订单总金额 list.get("bh_st_spprice")
        chargeMap.put("currency", "cny");//人民币
        String str1=sb1.toString();
        
        if(str1.length()>16){
        	
        	str1=str1.substring(0, 15);
        }
        chargeMap.put("subject","".equals(str1)?"0":str1);
    //  chargeMap.put("result_url", "http://www.baidu.com");
        chargeMap.put("body", body);//商品的描述信息 Unicode 
    	
        chargeMap.put("order_no", payNo );//订单编号 "D"+nowDate.getTime()+(int)(Math.random()*90000+10000)
        chargeMap.put("channel", type);//支付使用的第三方支付渠道
        chargeMap.put("client_ip", ip==null||"".equals(ip)?"127.0.0.1":ip);//发起支付请求终端的 IP 地址
        Map<String, String> app = new HashMap<String, String>();
		if ("2".equals(isios)) {
			app.put("id", ios);
		} else if ("1".equals(isios)) {
			app.put("id", android);
		}
		else if ("3".equals(isios)) {
			app.put("id", html);
			Map<String, String> initialMetadata = new HashMap<String, String>();
      	  initialMetadata.put("result_url", "http://www.wangzhong.com/wap");
      	chargeMap.put("extra", initialMetadata);
		}
     
        chargeMap.put("app", app);
        try {
        	/*
        	Map<String, String> initialMetadata = new HashMap<String, String>();
        	  initialMetadata.put("result_url", "http://www.wangzhong.com/wap");
        	chargeMap.put("extra", initialMetadata);*/
        	  
        	 // chargeMap.put("extra", "{\"success_url\":\"http://www.wangzhong.com/wap\",\"cancel_rurl\":\"http://www.baidu.com\"}");
        	  
            //发起交易请求
            charge = Charge.create(chargeMap);
            System.out.println(charge);
        } catch (PingppException e) {
            e.printStackTrace();
        }
        this.createAjax(response,charge);
    }	
	
	
	
	/**
	 * 判断登录标识是否失效
	 * @param json 
	 * @return
	 */
	@RequestMapping(value="judgeISLogin")
	public void judgeISLogin(String json,HttpServletResponse response){
		SafetyFilter.logger.info("传入JSON:" + json);
		String jsonData = "";
		try {
			Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据
			String loginflag = StringUtils.toStringByObject(maps.get("loginflag"),true); //登录标识
			//验证不能为空
			if (org.springframework.util.StringUtils.hasText(loginflag)) {
				//查询该手机用户ID
				List<Map<String, Object>> list=baseBiz.queryForList("select ae_st_id from ae_t_sysuser where ae_st_loginflag=?", new Object[]{loginflag});
				if(list!=null&&list.size()==1){
					jsonData = "{\"success\":\"true\",\"msg\":\"标识有效！\"}";
				} else {
					throw new MyoneException("未获取该手机用户信息");
				}
			} else {
				throw new MyoneException("未获取该手机用户信息");
			}
		} catch (MyoneException e) {
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
     * 退款
     * @param charge
     * @return
     */
	@RequestMapping(value="refund")
    public void refund(String json) {
		Pingpp.apiKey = apiKey;
        Refund refund = null;
        String jsonData="";
        Map<String, Object> params2 = new HashMap<String, Object>();
        try {
        	Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据
			String from = StringUtils.toStringByObject(maps.get("from"),true); //0 yuyue  1 shangping
			String orderNo = StringUtils.toStringByObject(maps.get("orderNo"),true);
			Map<String, Object> list=baseBiz.queryForMap("SELECT * FROM bh_t_orderform WHERE bh_st_ddnum = ?" , new Object[]{orderNo});
		   
			String bh_st_spprice=list.get("bh_st_spprice")+"";//商品总价
			String chargeId=list.get("chargeId")+"";
			Charge charge = Charge.retrieve(chargeId);
			
			
			String description = StringUtils.toStringByObject(maps.get("description"),true);
			Map<String, String> initialMetadata = new HashMap<String, String>();
			initialMetadata.put("from", from);
			initialMetadata.put("orderNo", orderNo);
        	params2.put("metadata", initialMetadata);
        	params2.put("description", org.springframework.util.StringUtils.hasText(description)?description:"0");
        	params2.put("amount", "1"); //商品总价
        	
            refund = charge.getRefunds().create(params2);
            System.out.println(refund);
            jsonData = "{\"success\":\"true\",\"flag\":\"1\",\"msg\":\"退款成功！\",\"refund\":"+refund.toString()+"}";
            if ("1".equals(from)) {
				baseBiz.executeTRANS("UPDATE bh_t_orderform SET bh_st_ddstate = 5   WHERE  bh_st_ddnum=?",orderNo);
			} else if ("0".equals(from)) {
				baseBiz.executeTRANS("UPDATE bf_t_apply SET  payState =4  WHERE  orderNo=?",orderNo);
			}
        } catch (Exception e) {
        	jsonData = "{\"success\":\"false\",\"flag\":\"1\",\"msg\":\"解析数据异常！\"}";
            e.printStackTrace();
        }
        
        SafetyFilter.logger.info("传出JSON:" + jsonData);
		this.createAjax(response,jsonData);
    }
    
	/**
	 * 用户登录验证
	 * @param json
	 * @return
	 */  
	@RequestMapping(value="phonelogin")
	public void phonelogin(String json,HttpServletResponse response,HttpServletRequest request){
		SafetyFilter.logger.info("传入JSON:" + json);
		System.out.print(21231);
		String jsonData = "";
		try {
			Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据
			String username = StringUtils.toStringByObject(maps.get("username"),true);//帐号/邮箱/手机号
			String password = StringUtils.toStringByObject(maps.get("password"),true); //密码
		 
			Encoder e=new MD5Encoder();//使用security3 的MD5加密技术 
			//验证不能为空
			if (org.springframework.util.StringUtils.hasText(username) && org.springframework.util.StringUtils.hasText(password)) {
				password= e.encrypt(password,username);
				
				List<Map<String, Object>> list=baseBiz.queryForList("select ae_st_intro,  ae_st_shshi , ae_st_sex  , ae_nm_age ,  ae_st_id,ae_st_lockstate,ae_st_name ,ae_st_tell ,(select ag_st_url from ag_t_file where ag_st_objid = a.ae_st_id and ag_st_mark='userfile' limit 0,1 ) ag_st_url from ae_t_sysuser a where username=? and password=?" , new Object[]{username,password});
				if(list!=null&&list.size()==1){
					String ae_st_id=StringUtils.toStringByObject(list.get(0).get("ae_st_id"));
					String ae_st_lockstate=StringUtils.toStringByObject(list.get(0).get("ae_st_lockstate"));
					if(!"1".equals(ae_st_lockstate)){
						throw new MyoneException("账户状态已被注销或停用。");
					}
					//如果存在登录标识,则返回以前登录标识
					String loginflag=StringUtils.getUUID32();//生成登录标识
					//修改登录标识
					baseBiz.executeTRANS(" update ae_t_sysuser set ae_st_lastlogonIp=?,ae_dt_updDate=?,ae_st_loginflag =? where ae_st_id =? ",CommonUtil.overshot(request),getcuttDate(), loginflag,ae_st_id);
					jsonData = "{\"success\":\"true\",\"msg\":\"登录成功！\",\"loginflag\":\""+loginflag+"\",\"result\":"+JsonUtils.getJsonDataFromCollection(list)+"}";
				} else {
					jsonData = "{\"success\":\"false\",\"flag\":\"3\",\"msg\":\"用户名或密码错误！\"}";
				}
			} else {
				jsonData = "{\"success\":\"false\",\"flag\":\"2\",\"msg\":\"请先传入完整的数据信息！\"}";
			}
		}catch (MyoneException e) {
			jsonData = "{\"success\":\"false\",\"flag\":\"4\",\"msg\":\""+e.getMessage()+"\"}";
			e.printStackTrace();
		}catch (Exception e) {
			jsonData = "{\"success\":\"false\",\"flag\":\"1\",\"msg\":\"解析数据异常！\"}";
			e.printStackTrace();
		}
		SafetyFilter.logger.info("传出JSON:" + jsonData);
		this.createAjax(response,jsonData);
	}
	/**
	 * 申请退款
	 * @param json
	 */
	@RequestMapping(value="refundApply")
	public void refundApply(String json){
		SafetyFilter.logger.info("传入JSON:" + json);
		String jsonData = "";
		try {
			Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据
			String orderNo = StringUtils.toStringByObject(maps.get("orderNo"),true);//帐号/邮箱/手机号
			baseBiz.executeTRANS("UPDATE bh_t_orderform SET bh_st_ddstate = 5   WHERE  bh_st_ddnum=?",orderNo); //8  申请退款
			jsonData = "{\"success\":\"true\",\"flag\":\"1\",\"msg\":\"申请退款成功！\"}";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			jsonData = "{\"success\":\"false\",\"flag\":\"1\",\"msg\":\"申请退款失败！\"}";
			e.printStackTrace();
		}
		SafetyFilter.logger.info("传出JSON:" + jsonData);
		this.createAjax(response,jsonData);
	}
	
	
	/**
	 * 退款状态
	 * @param json
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="refundState")
	public void refundState(HttpServletResponse response,HttpServletRequest request){
		       
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
				//String orderNo = chargeobj.getString("order_no");
				JSONObject metadata = chargeobj.getJSONObject("metadata");
			    String from=metadata.getString("from");
			    String orderNo=metadata.getString("orderNo");
			    String description= chargeobj.getString("description");
		  		
		  		
				if ("succeeded".equals(chargeobj.getString("status"))) {
					if ("1".equals(from)) {
						
						baseBiz.executeTRANS("UPDATE bh_t_orderform SET bh_st_ddstate = 6   WHERE  bh_st_ddnum=?",orderNo);
						
					} else if ("0".equals(from)) {
						baseBiz.executeTRANS("UPDATE bf_t_apply SET payState = 1  WHERE  orderNo=?",orderNo);
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
	/**
	 * 订单支付状态
	 * @param json
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="payState")
	public void payState(HttpServletResponse response,HttpServletRequest request){
		       
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
					String body = chargeobj.getString("body");
					
			       
			     //订单类型   apply 预约  goods 产品 body
					
					if (chargeobj.getBoolean("paid")) {
						if("goods".equals(body)){
					    	baseBiz.executeTRANS("UPDATE bh_t_orderform SET bh_st_ddstate =7 , chargeId ='"+id+"' WHERE  bh_st_ddnum=?",orderNo);
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
	/**
	 * 用户注册及信息维护
	 * @param json
	 * @return
	 */
	@RequestMapping(value="addAndEditUser")
	public void addAndEditUser(String json,HttpServletResponse response,HttpServletRequest request){
		SafetyFilter.logger.info("传入JSON:" + json);
		String jsonData = "";
		String fileurl="";
		Encoder e=new MD5Encoder();//使用security3 的MD5加密技术
		List<BizTransUtil> list = new ArrayList<BizTransUtil>();
		//删除以前的图片
		List<String> dellist=new ArrayList<String>();
		try {
			Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据
			String username = StringUtils.toStringByObject(maps.get("username"),true);//帐号/邮箱/手机号
			String password = StringUtils.toStringByObject(maps.get("password"),true); //密码
			String telcode = StringUtils.toStringByObject(maps.get("telcode"),true); //验证码
			String type = StringUtils.toStringByObject(maps.get("type"),true); //接口调用标识不能为空  1= 新增 2=修改
			String code=String.valueOf(request.getSession().getAttribute("phoneCode")).toLowerCase();
			String ae_st_type = StringUtils.toStringByObject(maps.get("ae_st_type"),true); //用户类型1、普通用户 2、平台管理员 3、 商家 4、师傅
			String loginflag = StringUtils.toStringByObject(maps.get("loginflag"),true); //登录标识--如果传了 ，并查询有，说明是在修改信息

			String uid= StringUtils.toStringByObject(maps.get("uid"),true);
			if(!("1".equals(type)||"2".equals(type))){
				throw new RuntimeException("接口调用标识不能为空");
			}
			String ae_st_id = "";//主键id
			//验证不能为空--如果是修改，需要通过loginflag获取id
			if ("2".equals(type)) {
				if(org.springframework.util.StringUtils.hasText(loginflag)){
					//查询该手机用户ID
					List<Map<String, Object>> listuser=baseBiz.queryForList("select a.ae_st_id from ae_t_sysuser a where a.ae_st_loginflag=? ", new Object[]{loginflag});
					if(listuser!=null&&listuser.size()==1){
						//当前手机用户登录人ID
						ae_st_id=StringUtils.toStringByObject(listuser.get(0).get("ae_st_id"));
					}else{
						throw new MyoneException();
					}
				}else{
					throw new RuntimeException("修改时，请传入登录标识");
				}
			}
			//注册
			if(!org.springframework.util.StringUtils.hasText(ae_st_id)&&org.springframework.util.StringUtils.hasText(ae_st_type)){
				if (org.springframework.util.StringUtils.hasText(username) && org.springframework.util.StringUtils.hasText(password)&&org.springframework.util.StringUtils.hasText(telcode)&&telcode.equals(code)) {
					Ae_t_sysuser sysuser = new Ae_t_sysuser(true);
					sysuser.setUsername(username);
					
					sysuser.setPassword(e.encrypt(password,username));
					sysuser.setAe_st_lockstate("1");
					sysuser.setUid(uid);
					if(org.springframework.util.StringUtils.hasText(ae_st_type)){
						sysuser.setAe_st_type(ae_st_type);
					}else{
						sysuser.setAe_st_type("1");
					}
					
					//商家
					if(ae_st_type.equals("3")){
						Bb_t_ShopUser shopuser = new Bb_t_ShopUser();
						shopuser.setBb_st_userid(sysuser.getAe_st_id());//设置外键关系
						list.add(new BizTransUtil(shopuser));
					}//师傅
					else if(ae_st_type.equals("4")){
						Ba_t_Labour Labour = new Ba_t_Labour(true);
						Labour.setBa_st_userid(sysuser.getAe_st_id());//设置外键关系
						list.add(new BizTransUtil(Labour));
					}
					list.add(new BizTransUtil(sysuser));
					
					// 角色--添加
					String type1 = ae_st_type;
					String roleid = (String) baseBiz.queryForObject("select ac_st_id from ac_t_sysrole where ac_st_code = ?",
							new Object[]{"2".equals(type1)?"WZGLY":("3".equals(type1)?"SJ":("4".equals(type1)||"5".equals(type1)||"6".equals(type1)?"SF":"HY"))},String.class);
					Af_t_sysuserrole rf=new Af_t_sysuserrole(true);
					rf.setAe_st_id(sysuser.getAe_st_id());
					rf.setAc_st_id(roleid);
					list.add(new BizTransUtil(rf));
				}//新增
				else{
					throw new RuntimeException("请传入完成数据");
				}
				
				if(baseBiz.executesTRANS(list)){
					jsonData = "{\"success\":\"true\",\"flag\":\"1\",\"msg\":\"注册成功！\"}";
				}
			}
			//编辑
			else{
				//头像维护 ??
				String dyimg =StringUtils.toStringByObject(maps.get("dyimg"),true);;//相关图片 二进制
				if(org.springframework.util.StringUtils.hasText(dyimg)){
					BASE64Decoder decoder = new BASE64Decoder();
					byte[] bytes1 = decoder.decodeBuffer(dyimg);
					ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
					if (bais.available() > 1048576) {
						throw new MytwoException("图片大小只能小于1M，请重新选择！");
					}
					/*System.out.println("11"+ImageIO.read(bais)+"11");
					System.out.println("11"+ImageIO.read(bais)+"11");
					System.out.println("11"+ImageIO.read(bais)+"11");
					BufferedImage bi1 = ImageIO.read(bais);
					System.err.println(bi1);*/
					String imgname=StringUtils.getUUID32()+ ".jpg";
					java.io.File w2 = new java.io.File(UploadFile.getAbsolutePath("images/business/user/",imgname));// 可以是jpg,png,gif格式
					ImageIO.write(ImageIO.read(bais), "jpg", w2);
					// 不管输出什么格式图片，此处不需改动
						fileurl ="images/business/user/"+imgname;
					Ag_t_file file=new Ag_t_file(true);
					file.setAg_st_objtype("ae_t_sysuser");//文件对应的对象
					file.setAg_dt_addDate(getcuttDate());//创建时间
					file.setAg_st_addUserId(ae_st_id);//创建人员ID  
					file.setAg_st_objid(ae_st_id);//文件归属ID
					file.setAg_st_type("1");//文件类型-图片
					file.setAg_st_mark("userfile");//自定义文件标识
					file.setAg_st_url(fileurl);////文件 的存储地址
					list.add(new BizTransUtil(file));
					//查询原来的头像
					List<Map<String, Object>> oldlist = baseBiz.queryForList("select ag_st_url,ag_st_id from ag_t_file where ag_st_objid=? and ag_st_mark='userfile' ", new Object[]{ae_st_id});
					if(oldlist!=null&&oldlist.size()>0){
						for(Map<String, Object> oldmap:oldlist){
							dellist.add(StringUtils.toStringByObject(oldmap.get("ag_st_url")));
							list.add(new BizTransUtil(oldmap.get("ag_st_id"), Ag_t_file.class));
						}
					}
				}
				/***
				 * 如果未获取到的其他属性信息， 则修改为空！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
				 */
				//用户信息修改----
//				Ae_t_sysuser ae= (Ae_t_sysuser)BeanUtils.turnBean(maps, Ae_t_sysuser.class);//取得maps中的所有相关ae对象中的值
				Ae_t_sysuser ae=new Ae_t_sysuser(ae_st_id);//取得maps中的所有相关ae对象中的值
				ae.setPassword(StringUtils.toStringByObject(maps.get("password"),true));
				ae.setUsername(StringUtils.toStringByObject(maps.get("username"),true));
				ae.setAe_st_name(StringUtils.toStringByObject(maps.get("ae_st_name"),true));
				ae.setAe_st_tell(StringUtils.toStringByObject(maps.get("ae_st_tell"),true));
				
				ae.setAe_st_sex(StringUtils.toStringByObject(maps.get("ae_st_sex"),true));
				ae.setAe_nm_age(StringUtils.toIntegerByObject(maps.get("ae_nm_age")));
				ae.setAe_st_address(StringUtils.toStringByObject(maps.get("ae_st_address"),true));
				ae.setAe_st_intro(StringUtils.toStringByObject(maps.get("ae_st_intro"),true));
				ae.setAe_st_shsheng(StringUtils.toStringByObject(maps.get("ae_st_shsheng"),true));
				ae.setAe_st_shshi(StringUtils.toStringByObject(maps.get("ae_st_shshi"),true));
				ae.setAe_st_shxian(StringUtils.toStringByObject(maps.get("ae_st_shxian"),true));
				ae.setAe_st_nickName(StringUtils.toStringByObject(maps.get("ae_st_nickName"),true));
				//构建数据--修改
				Map<String,Object>  map=new LinkedCaseInsensitiveMap<Object>();
				map.put("ae_st_id", ae.getAe_st_id());//ID
				//如果要修改密码
				if(org.springframework.util.StringUtils.hasText(ae.getPassword())){
					map.put("password", e.encrypt(ae.getPassword(), ae.getUsername()));//MD5加密
				}	
				map.put("ae_st_updUserId",  ae.getAe_st_id());
				map.put("ae_dt_updDate", getcuttDate());
				map.put("ae_st_name", ae.getAe_st_name());//姓名
				map.put("ae_st_tell", ae.getAe_st_tell());//电话
				map.put("ae_st_sex", ae.getAe_st_sex());//性别
				map.put("ae_nm_age", ae.getAe_nm_age());//年龄
				map.put("ae_st_address", ae.getAe_st_address());//详细地址
				map.put("ae_st_intro", ae.getAe_st_intro());//简介
				map.put("ae_st_shsheng", ae.getAe_st_shsheng());//省
				map.put("ae_st_shshi", ae.getAe_st_shshi());//市
				map.put("ae_st_shxian", ae.getAe_st_shxian());//县/区
				map.put("ae_st_nickName", ae.getAe_st_nickName());//用户昵称
				
				list.add(new BizTransUtil(map,Ae_t_sysuser.class,CommonUtil.UPDATE));
				
				//执行
				if(baseBiz.executesTRANS(list)){
					if(dellist.size()>0){
						for(String s:dellist){
							if(org.springframework.util.StringUtils.hasText(s)){
								UploadFile.delpic(s);//删除头像
							}
						}
					}
					
					//jsonData = "{\"success\":\"true\",\"msg\":\"登录成功！\",\"loginflag\":\""+loginflag+"\",\"result\":"+JsonUtils.getJsonDataFromCollection(list)+"}";
					//List<Map<String, Object>> list=baseBiz.queryForList("select ae_st_intro,  ae_st_shshi , ae_st_sex  , ae_nm_age ,  ae_st_id,ae_st_lockstate,ae_st_name ,ae_st_tell ,(select ag_st_url from ag_t_file where ag_st_objid = a.ae_st_id and ag_st_mark='userfile' limit 0,1 ) ag_st_url from ae_t_sysuser a where username=? and password=?" , new Object[]{username,password});
				
					List<Map<String, Object>> list1=new ArrayList<Map<String,Object>>();
					
					Map<String, Object> map1=new HashMap<String, Object>();
					
					map1.put("ae_st_intro", ae.getAe_st_intro());//简介
					map1.put("ae_st_shshi", ae.getAe_st_shshi());//市
					map1.put("ae_st_sex", ae.getAe_st_sex());//性别
					map1.put("ae_nm_age", ae.getAe_nm_age());//年龄 
					map1.put("ae_st_id",  ae.getAe_st_id());
					map1.put("ae_st_lockstate",  ae.getAe_st_lockstate());
					map1.put("ae_st_name",  ae.getAe_st_name());
					map1.put("ae_st_tell",  ae.getAe_st_tell());
					map1.put("ag_st_url",  fileurl);
					list1.add(map1);
					
					jsonData = "{\"success\":\"true\",\"msg\":\"编辑成功！\",\"loginflag\":\""+loginflag+"\",\"result\":"+JsonUtils.getJsonDataFromCollection(list1)+"}";
				}
			}
			
		}catch (MyoneException e1) {
			jsonData = "{\"success\":\"false\",\"flag\":\"3\",\"msg\":\"登录标识已经失效，请重新登录！\"}";
		}catch (Exception e1) {
			jsonData = "{\"success\":\"false\",\"flag\":\"1\",\"msg\":\"解析数据异常！\"}";
		}
		SafetyFilter.logger.info("传出JSON:" + jsonData);
		this.createAjax(response,jsonData);
	}
	
	/**
	 * 预约接口
	 * @param json
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="addApply")
	public void addApply(String json,HttpServletResponse response,HttpServletRequest request){
		SafetyFilter.logger.info("传入JSON:" + json);
		String jsonData = "";
		String bf_st_type ="";
		List<BizTransUtil> list = new ArrayList<BizTransUtil>();
		try {
			Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据
			String price = StringUtils.toStringByObject(maps.get("price"),true);//jiage
			String bf_st_name = StringUtils.toStringByObject(maps.get("bf_st_name"),true);//姓名
			String bf_st_tell = StringUtils.toStringByObject(maps.get("bf_st_tell"),true); //电话
			String bf_dt_time = StringUtils.toStringByObject(maps.get("bf_dt_time"),true); //预约时间
			String bf_st_address = StringUtils.toStringByObject(maps.get("bf_st_address"),true); //地址
			bf_st_type= StringUtils.toStringByObject(maps.get("bf_st_type"),true); //类型
			String bf_st_incity = StringUtils.toStringByObject(maps.get("bf_st_incity"),true); //城内城外  1=城内 2=城外
			String bf_st_area = StringUtils.toStringByObject(maps.get("bf_st_area"),true); //面积
   //       String bf_st_userid = StringUtils.toStringByObject(maps.get("bf_st_userid"),true); //申请人id
			String loginflag = StringUtils.toStringByObject(maps.get("loginflag"),true); //登录标识
			String bf_st_receiveid = StringUtils.toStringByObject(maps.get("bf_st_receiveid"),true); //预约工长
			String bf_st_housnum = StringUtils.toStringByObject(maps.get("bf_st_housnum"),true); //户数记录
			String bf_st_userid ="";//申请人id
			//验证不能为空
			if (org.springframework.util.StringUtils.hasText(loginflag)) {
				//查询该手机用户ID
				List<Map<String, Object>> listuser=baseBiz.queryForList("select a.ae_st_id from ae_t_sysuser a where a.ae_st_loginflag=? ", new Object[]{loginflag});
				if(listuser!=null&&listuser.size()==1){
					//当前手机用户登录人ID
					bf_st_userid=StringUtils.toStringByObject(listuser.get(0).get("ae_st_id"));
				} 
			}
			//验证不能为空
			if(org.springframework.util.StringUtils.hasText(bf_st_tell)){
				Bf_t_Apply apply = new Bf_t_Apply(true);
				apply.setBf_st_name(bf_st_name);
				apply.setBf_st_tell(bf_st_tell);
				apply.setBf_dt_time(DateConverUtil.getDbyST(bf_dt_time, "yyyy-MM-dd"));
				apply.setBf_st_address(bf_st_address);
				apply.setBf_st_type(bf_st_type);
				apply.setBf_st_incity(bf_st_incity);
				apply.setPricheAll(price);
				apply.setBf_st_area(bf_st_area);//"D"+nowDate.getTime()+(int)(Math.random()*90000+10000)
				Date nowDate=getcuttDate();//当前操作时间
				String orderNo="D"+nowDate.getTime()+(int)(Math.random()*90000+10000);
				apply.setOrderNo(orderNo);
				apply.setBf_st_receiveid(bf_st_receiveid);
				apply.setBf_st_userid(bf_st_userid);
				apply.setBf_st_housnum(bf_st_housnum);
				apply.setBf_st_addUserId(bf_st_userid);
				apply.setBf_dt_addDate(getcuttDate());
				apply.setBf_dt_updDate(getcuttDate());
				apply.setBf_st_updUserId(bf_st_userid);
				list.add(new BizTransUtil(apply));
				if(baseBiz.executesTRANS(list)){
					String returnString =null;
					String content="";
					if("1".equals(bf_st_type)){
						content="恭喜您成功申请了网众科技旗下平台的一键入住,我们会在24内和你联系，请注意接听电话   网众科技客户热线  400-028-5998";
					}else if("2".equals(bf_st_type)){
						content="恭喜您成功申请了网众科技旗下平台的免费验房,我们会在24内和你联系，请注意接听电话   网众科技客户热线  400-028-5998";
					}
					else if("3".equals(bf_st_type)){
						content="恭喜您成功申请了网众科技旗下平台的金牌验房,我们会在24内和你联系，请注意接听电话   网众科技客户热线  400-028-5998";
					}
					else if("4".equals(bf_st_type)){
						content="恭喜您成功申请了网众科技旗下平台的免费设计,我们会在24内和你联系，请注意接听电话   网众科技客户热线  400-028-5998";
					}
					else if("5".equals(bf_st_type)){
						content="恭喜您成功申请了网众科技旗下平台的个性设计,我们会在24内和你联系，请注意接听电话   网众科技客户热线  400-028-5998";
					}
					else if("7".equals(bf_st_type)){
						content="恭喜您成功预约了网众科技旗下平台工长,我们会在24内和你联系，请注意接听电话   网众科技客户热线  400-028-5998";
					}
					else if("8".equals(bf_st_type)){
						content="恭喜您成功预约了网众科技旗下平台师傅,我们会在24内和你联系，请注意接听电话   网众科技客户热线  400-028-5998";
					}
					else if("9".equals(bf_st_type)){
						content="恭喜您成功预约了网众科技旗下平台监理,我们会在24内和你联系，请注意接听电话   网众科技客户热线  400-028-5998";
					}
					else if("10".equals(bf_st_type)){
						content="恭喜您成功预约了网众科技旗下平台收费监理,我们会在24内和你联系，请注意接听电话   网众科技客户热线  400-028-5998";
					}
					else if("12".equals(bf_st_type)){
						content="恭喜您成功预约了网众科技旗下平台免费空气检测,我们会在24内和你联系，请注意接听电话   网众科技客户热线  400-028-5998";
					}
					else if("13".equals(bf_st_type)){
						content="恭喜您成功预约了网众科技旗下平台收费空气检测我们会在24内和你联系，请注意接听电话   网众科技客户热线  400-028-5998";
					}
					
					
					try {
						
						String uri = "http://222.73.117.158/msg/";//应用地址
						String account = "wangzhong";//账号
						String pswd = "Tch147258";//密码
						String mobiles = bf_st_tell;//手机号码，多个号码使用","分割
					
						boolean needstatus = true;//是否需要状态报告，需要true，不需要false
						String product = null;//产品ID
						String extno = null;//扩展码
					    returnString= HttpSender.batchSend(uri, account, pswd, mobiles, content, needstatus, product, extno);
						System.out.println(returnString);
					} catch (Exception e) {
						jsonData = "{\"success\":\"false\" ,\"msg\":\"验证码生成失败,"+e.getMessage()+"！\"}";
					}
					response.setCharacterEncoding("UTF-8");
					
					
					
					jsonData = "{\"success\":\"true\",\"flag\":\"1\",\"orderNo\":\""+orderNo+"\",\"msg\":\"预约成功！\"}";
				}
			} else {
				jsonData = "{\"success\":\"false\",\"flag\":\"2\",\"msg\":\"请先传入完整的数据信息！\"}";
			}
		}catch (Exception e) {
			jsonData = "{\"success\":\"false\",\"flag\":\"3\",\"msg\":\"解析数据异常！\"}";
			e.printStackTrace();
		}
		SafetyFilter.logger.info("传出JSON:" + jsonData);
		this.createAjax(response,jsonData);
	}
	@RequestMapping(value="check")
	public void check(String json,HttpServletResponse response,HttpServletRequest request) throws Exception {
		SafetyFilter.logger.info("传入JSON:" + json);
		String jsonData = "";
		List<BizTransUtil> list = new ArrayList<BizTransUtil>();
		
		
		try {
			String phone=request.getParameter("phone");
			if(!org.springframework.util.StringUtils.hasText(phone)){
				throw new RuntimeException("手机号必须填写！");
			}
			
			int a=(int)(Math.random()*8999)+1000;
			String uri = "http://222.73.117.158/msg/";//应用地址
			String account = "wangzhong";//账号
			String pswd = "Tch147258";//密码
			String mobiles = phone;//手机号码，多个号码使用","分割
			String content = "亲爱的用户，您的验证码是"+a+"，1分钟内有效。";//短信内容
			boolean needstatus = true;//是否需要状态报告，需要true，不需要false
			String product = null;//产品ID
			String extno = null;//扩展码
			String returnString =null;
			try {
				returnString= HttpSender.batchSend(uri, account, pswd, mobiles, content, needstatus, product, extno);
				//TODO 处理返回值,参见HTTP协议文档
			} catch (Exception e) {
				//TODO 处理异常
				e.printStackTrace();
			}
			String[] str=returnString.split(",");
			System.out.println(returnString);
			String[] code1=str[1].split("\n");
			if("0".equals(code1[0])){
				//modelMap.addAttribute("phoneCode", a);//在验证接口取得的时候，使用String.valueOf(request.getSession().getAttribute("phoneCode")).toLowerCase())
				SafetyFilter.logger.info("本次手机验证码："+a);
				jsonData = "{\"success\":\"true\" ,\"msg\":\"验证码生成成功！\"}";
			}else{
				jsonData = "{\"success\":\"false\" ,\"msg\":\"验证码生成失败！\"}";
			}
			
			
		} catch (Exception e) {
			jsonData = "{\"success\":\"false\" ,\"msg\":\"验证码生成失败,"+e.getMessage()+"！\"}";
		}
		response.setCharacterEncoding("UTF-8");
		this.createAjax(response,jsonData);
	}
	/**
	 * 获取类型接口 比如工长类型
	 */
	@RequestMapping(value="getLabourCode")
	public void getLabourCode(String json){
		String jsonData;
		SafetyFilter.logger.info("传入JSON:" + json);
			try {
				Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据
				String tag = StringUtils.toStringByObject(maps.get("tag")); //1工长 2除开监测 监理师
				String type = null;
				String sql1="";
				if("1".equals(tag)){
					type="USERTYPE_6";
				} 
				if("2".equals(tag)){
					type="USERTYPE_4";
					sql1=" AND ab_st_value NOT IN (13,14,1)";
				}
				String sql="SELECT ab_st_name , ab_st_value FROM ab_t_code WHERE ab_st_parent =(SELECT ab_st_id FROM ab_t_code WHERE ab_st_mark=?   )"+sql1+"";
				List<Map<String, Object>> listuser=baseBiz.queryForList(sql, new Object[]{type});
				jsonData = "{\"success\":\"true\",\"msg\":\"获取成功！\",\"result\":"+JsonUtils.getJsonDataFromCollection(listuser)+"}";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				jsonData = "{\"success\":\"false\",\"msg\":\"获取失败！\"}";
				e.printStackTrace();
			}
			this.createAjax(response,jsonData);
	}
	
	/**
	 * 获取工长师傅接口
	 * @param json 
	 * @return
	 */
	@RequestMapping(value="getLabourList")
	public void getLabourList(String json,HttpServletResponse response){
		SafetyFilter.logger.info("传入JSON:" + json);
		String jsonData = "";
		try {
			Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据
			if(maps!=null){
				String ae_st_type = StringUtils.toStringByObject(maps.get("ae_st_type")); //类型  4=师傅5=设计师6=工长
				//这个数据是另一个接口先进行查询的。然后通过APP 再传入本接口
				String ba_st_type = StringUtils.toStringByObject(maps.get("ba_st_type")); //职业类型 1=验房师 1=个性设计 2=金牌设计 1=商户装修2=商品房装修3=商品房装修
				String ae_st_shxian = StringUtils.toStringByObject(maps.get("ae_st_shxian")); //地址
				String sequence = StringUtils.toStringByObject(maps.get("sequence")); //排序 1=评价 2= 订单数 3=价格
				String pageIndexs=StringUtils.toStringByObject(maps.get("pageIndex"),true);//页 码
				String pageSizes=StringUtils.toStringByObject(maps.get("pageSize"),true);//页大小
				String father=StringUtils.toStringByObject(maps.get("father"),true);//市
				Integer pageIndex =org.springframework.util.StringUtils.hasText(pageIndexs)?Integer.valueOf(pageIndexs):0; //页码
				Integer pageSize =org.springframework.util.StringUtils.hasText(pageSizes)?Integer.valueOf(pageSizes):0; //页大小
				//默认页码和页大小
				pageIndex=pageIndex<1?1:pageIndex;
				pageSize=pageSize<1?10:pageSize;
				//验证不能为空
//				if (StringUtils.hasText(ae_st_id)) {
					List<String> params=new ArrayList<String>();
					//用户头像存在 ag_t_file表中
					StringBuffer sb=new StringBuffer("SELECT  ba_st_price ,ae_st_name,ae_st_type,(select ag_st_url from ag_t_file where ag_st_objid = ae_st_id  and ag_st_mark='userfile' limit 0,1) ag_st_url,ae_st_tell,ae_st_sex,ae_nm_age,ae_st_intro,ae_st_id,ba_st_type,ba_st_grade,ba_st_teamnum,ba_st_price,ba_st_team_intro ,( SELECT COUNT(1) FROM bf_t_apply WHERE bf_st_receiveid= ae_st_id) ordersum  "); 
					StringBuffer sbcount=new StringBuffer(" select count(1) ");
					StringBuffer sbcommon=new StringBuffer(" from ba_t_labour ,ae_t_sysuser where 1=1 and ba_st_userid= ae_st_id ");
					//类型
					if(org.springframework.util.StringUtils.hasText(ae_st_type)){
						sbcommon.append(" and ae_st_type =?");
						params.add(ae_st_type);
					}
					
					//市
					if(org.springframework.util.StringUtils.hasText(father)){
						sbcommon.append(" and ae_st_shshi =?");
						params.add(father);
					}
					//职业类型
					if(org.springframework.util.StringUtils.hasText(ba_st_type)){
						sbcommon.append(" and ba_st_type in ("+ba_st_type+")  ");
					}
					if(org.springframework.util.StringUtils.hasText(ae_st_shxian)){
						sbcommon.append(" and ae_st_shxian=? ");
						params.add(ae_st_shxian);
					}
					sbcount.append(sbcommon);
					sb.append(sbcommon);
					//排序--评价
					if(sequence.equals("1")){
						sb.append(" order by ba_st_grade desc ");
					}//订单数
					else if(sequence.equals("2")){
						sb.append(" order by ordersum desc ");
					}else if(sequence.equals("3")){
						sb.append(" order by ba_st_price asc ");
					}else{
						sb.append(" order by  ordersum desc,ba_st_grade desc,ae_st_name ");
					}
					//评论列表
					PageBean pages=baseBiz.getPages(sb.toString(),sbcount.toString(),pageIndex, pageSize,params.toArray());
					jsonData = "{\"success\":\"true\",\"msg\":\"获取成功！\",\"result\":"+JsonUtils.getJsonData(pages)+"}";
			}else {
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
	 * 获取工长师傅案例接口
	 * @param json 
	 * @return
	 */                     
	@RequestMapping(value="getLabourCaseList")
	public void getLabourCaseList(String json,HttpServletResponse response){
		SafetyFilter.logger.info("传入JSON:" + json);
		String jsonData = "";
		try {
			Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据
			String bd_st_bbid = StringUtils.toStringByObject(maps.get("bd_st_bbid"),true); //师傅id
			String pageIndexs=StringUtils.toStringByObject(maps.get("pageIndex"),true);//页码
			String pageSizes=StringUtils.toStringByObject(maps.get("pageSize"),true);//页大小
			Integer pageIndex =org.springframework.util.StringUtils.hasText(pageIndexs)?Integer.valueOf(pageIndexs):0; //页码
			Integer pageSize =org.springframework.util.StringUtils.hasText(pageSizes)?Integer.valueOf(pageSizes):0; //页大小
			//默认页码和页大小
			pageIndex=pageIndex<1?1:pageIndex;
			pageSize=pageSize<1?10:pageSize;
			//验证不能为空
			if (org.springframework.util.StringUtils.hasText(bd_st_bbid)) {
				//该商品下面的所有的评论
				StringBuffer sb=new StringBuffer("SELECT bd_st_id,bd_st_name,bd_st_area, date_format(bd_dt_time,'%Y-%m-%d') bd_dt_time ,bd_st_money,bd_st_remark,(select ag_st_url from ag_t_file where ag_st_objid=bd_st_id) ag_st_url "); 
				StringBuffer sbcount=new StringBuffer(" select count(1) ");
				StringBuffer sbcommon=new StringBuffer(" FROM bd_t_case WHERE 1=1 and bd_st_bbid=? ");
				List<String> pramsList = new ArrayList<String>();
				pramsList.add(bd_st_bbid);
				sbcount.append(sbcommon);
				sb.append(sbcommon).append(" order by bd_dt_addDate desc ");
				//评论列表
				PageBean pages=baseBiz.getPages(sb.toString(),sbcount.toString(),pageIndex, pageSize,pramsList.toArray());
				jsonData = "{\"success\":\"true\",\"msg\":\"获取成功！\",\"result\":"+JsonUtils.getJsonData(pages)+"}";
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
	 * 获取师傅、商品评价接口
	 * @param json 
	 * @return
	 */
	@RequestMapping(value="getUserAssessList")
	public void getUserAssessList(String json,HttpServletResponse response){
		SafetyFilter.logger.info("传入JSON:" + json);
		String jsonData = "";
		try {
			Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据
			String be_st_bgid= StringUtils.toStringByObject(maps.get("be_st_bgid"),true); //商品id
			String be_st_jbgid = StringUtils.toStringByObject(maps.get("be_st_jbgid"),true); //师傅ae id
			String pageIndexs=StringUtils.toStringByObject(maps.get("pageIndex"),true);//页码
			String pageSizes=StringUtils.toStringByObject(maps.get("pageSize"),true);//页大小
			Integer pageIndex =org.springframework.util.StringUtils.hasText(pageIndexs)?Integer.valueOf(pageIndexs):0; //页码
			Integer pageSize =org.springframework.util.StringUtils.hasText(pageSizes)?Integer.valueOf(pageSizes):0; //页大小
			//默认页码和页大小
			pageIndex=pageIndex<1?1:pageIndex;
			pageSize=pageSize<1?10:pageSize;
			//该商品下面的所有的评论
			StringBuffer sb=new StringBuffer("select be_st_id,(select ae_st_name from ae_t_sysuser where ae_st_id=be_st_fbgid) ae_st_name,be_st_content,be_nm_manner,be_nm_integrity,be_nm_quality,be_nm_shopquality,date_format(be_dt_addDate,'%Y-%m-%d %T') be_dt_addDate "); 
			StringBuffer sbcount=new StringBuffer(" select count(1) ");
			StringBuffer sbcommon=new StringBuffer(" FROM be_t_assess WHERE 1=1 ");
			List<String> pramsList = new ArrayList<String>();
			//师傅
			if (org.springframework.util.StringUtils.hasText(be_st_jbgid)) {
				pramsList.add(be_st_jbgid);
				sbcommon.append(" and be_st_jbgid =? ");
			} //商品
			else if(org.springframework.util.StringUtils.hasText(be_st_bgid)){
				pramsList.add(be_st_bgid);
				sbcommon.append(" and be_st_bgid =? ");
			}else{
				throw new RuntimeException("请先传入完整的数据信息");
			}
			sbcount.append(sbcommon);
			sb.append(sbcommon).append(" order by be_dt_addDate desc ");
			PageBean pages=baseBiz.getPages(sb.toString(),sbcount.toString(),pageIndex, pageSize,pramsList.toArray());
			jsonData = "{\"success\":\"true\",\"msg\":\"获取成功！\",\"result\":"+JsonUtils.getJsonData(pages)+"}";
		}catch (Exception e) {
			jsonData = "{\"success\":\"false\",\"flag\":\"1\",\"msg\":\"解析数据异常！\"}";
			e.printStackTrace();
		}
		SafetyFilter.logger.info("传出JSON:" + jsonData);
		this.createAjax(response,jsonData);
	} 
	
	/**
	 * 进入商品首页接口
	 * @param json 
	 * @return
	 */
	@RequestMapping(value="getMainShopList")
	public void getMainShopList(String json,HttpServletResponse response){
		SafetyFilter.logger.info("传入JSON:" + json);
		String jsonData = "";
		Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据
		String istop =StringUtils.toStringByObject(maps.get("istop"),true); //是否首页
		// PC_APP首页大广告  1   APP精品推荐 2 APP品牌特卖 3 APP居家商城 4 PC验房 5 6抢相因
		String sql=getAd(istop);
		try {
			List<Map<String,Object>> adtoplist = new ArrayList<Map<String,Object>>();
			adtoplist = baseBiz.queryForList(sql.toString());
			jsonData = "{\"success\":\"true\",\"msg\":\"获取成功！\",\"result\":"+JsonUtils.getJsonDataFromCollection(adtoplist)+"}";
		}catch (Exception e) {
			jsonData = "{\"success\":\"false\",\"flag\":\"1\",\"msg\":\"解析数据异常！\"}";
			e.printStackTrace();
		}
		SafetyFilter.logger.info("传出JSON:" + jsonData);
		this.createAjax(response,jsonData);
	}
	private String getAd(String istop)  {
		String sql=null;
		if ("1".equals(istop)) {//首页
			sql = "SELECT  bj_st_id,bj_st_type,bj_st_clickurl,(SELECT ag_st_url FROM ag_t_file WHERE ag_st_objid= bj_st_id  limit 0,1) bj_st_picurl	 FROM bj_t_advertisement WHERE  bj_st_enable='1' AND bj_st_type='"+istop+"'  ORDER BY bj_dt_addDate  desc LIMIT 0,6";
		}
		else{
			sql = "SELECT bj_st_id,bj_st_type,bj_st_clickurl,(SELECT ag_st_url FROM ag_t_file WHERE ag_st_objid= bj_st_id  limit 0,1) bj_st_picurl	 FROM bj_t_advertisement WHERE  bj_st_enable='1' AND bj_st_type='"+istop+"'  ORDER BY bj_dt_addDate  desc LIMIT 0,6";
		}
		return sql;
		
	}
	/**
	 * 获取商品列表接口
	 * @param json 
	 * @return
	 */
	@RequestMapping(value="getShopList")
	public void getShopList(String json,HttpServletResponse response){
		SafetyFilter.logger.info("传入JSON:" + json);
		String jsonData = "";
		try { //zheli ca
			Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据
			String bg_st_fbtpe =StringUtils.toStringByObject(maps.get("bg_st_fbtpe")); //类型
			String bg_st_bbid =StringUtils.toStringByObject(maps.get("bg_st_bbid")); //商家id
			String bg_st_name =StringUtils.toStringByObject(maps.get("bg_st_name")); //商品名称
			String reordertype =StringUtils.toStringByObject(maps.get("reordertype")); //排序规则1为商品销量，2位商品价格 3 新品 4好评数
			String pageIndexs=StringUtils.toStringByObject(maps.get("pageIndex"),true);//页码
			String pageSizes=StringUtils.toStringByObject(maps.get("pageSize"),true);//页大小
			String bg_st_randid=StringUtils.toStringByObject(maps.get("bg_st_randid"),true);//产品类型
			Integer pageIndex =org.springframework.util.StringUtils.hasText(pageIndexs)?Integer.valueOf(pageIndexs):0; //页码
			Integer pageSize =org.springframework.util.StringUtils.hasText(pageSizes)?Integer.valueOf(pageSizes):0; //页大小
			//默认页码和页大小
			pageIndex=pageIndex<1?1:pageIndex;
			pageSize=pageSize<1?10:pageSize;  
			StringBuffer sb=new StringBuffer("SELECT (select COUNT(1) from be_t_assess where be_st_bgid=bg_st_id and be_nm_shopquality>6) hps,bg_nm_storeNum , bg_st_pricetj ,date_format(bg_dt_startDate,'%Y-%m-%d ')bg_dt_startDate  ,date_format(bg_dt_endDate,'%Y-%m-%d %T')bg_dt_endDate  , bg_st_id,bg_st_name,bg_st_bbid,bg_st_randid,bg_st_pricezg,bg_st_num,bg_st_summary,bg_st_randid,bg_st_img1 bg_st_shopurl,(select count(1) from bi_t_orderprodrelate where bi_st_ddnum=bg.bg_st_num)probill "); 
			StringBuffer sbcount=new StringBuffer(" select count(1) ");
			StringBuffer sbcommon=new StringBuffer(" FROM bg_t_prodinfo bg WHERE 1=1 and bg_st_enable='1' and bg_st_isdel='0' ");
			List<String> pramsList = new ArrayList<String>();
			//商家
			if(org.springframework.util.StringUtils.hasText(bg_st_bbid)){
				sbcommon.append(" and bg_st_bbid=? ");
				pramsList.add(bg_st_bbid);
			}
			//发布类型
			if(org.springframework.util.StringUtils.hasText(bg_st_fbtpe)){
				sbcommon.append(" and bg_st_fbtpe=? ");
				pramsList.add(bg_st_fbtpe);
			}
			
			//产品类型
			if(org.springframework.util.StringUtils.hasText(bg_st_randid)){
				sbcommon.append(" and bg_st_randid=? ");
				pramsList.add(bg_st_randid);
			}
			
			//商品名称
			if(org.springframework.util.StringUtils.hasText(bg_st_name)){
				sbcommon.append(" and bg_st_name like ? ");
				pramsList.add("%"+bg_st_name+"%");
				//pramsList.add("%"+bg_st_fbtpe+"%");
			}
			sbcount.append(sbcommon);
			sb.append(sbcommon);
			//排序
			if(org.springframework.util.StringUtils.hasText(reordertype)){
				if(reordertype.equals("1")){
					sb.append(" order by probill desc ");
				}else if(reordertype.equals("11")){
					sb.append(" order by probill asc ");
				}else if(reordertype.equals("2")){
					sb.append(" order by bg_st_pricezg desc ");
				}else if(reordertype.equals("22")){
					sb.append(" order by bg_st_pricezg asc ");
				}else if(reordertype.equals("3")){
					sb.append(" order by bg_dt_addDate desc ");
				}else if(reordertype.equals("33")){
					sb.append(" order by bg_dt_addDate asc ");
				}else if(reordertype.equals("4")){
					sb.append(" order by hps desc ");
				}else if(reordertype.equals("44")){
					sb.append(" order by hps asc ");
				}
				
			}else{
				sb.append(" order by bg_dt_addDate desc ");
			}
			PageBean pages=baseBiz.getPages(sb.toString(),sbcount.toString(),pageIndex, pageSize,pramsList.toArray());
			jsonData = "{\"success\":\"true\",\"msg\":\"获取成功！\",\"result\":"+JsonUtils.getJsonData(pages)+"}";
		}catch (Exception e) {
			jsonData = "{\"success\":\"false\",\"flag\":\"1\",\"msg\":\"解析数据异常！\"}";
			e.printStackTrace();
		}
		SafetyFilter.logger.info("传出JSON:" + jsonData);
		this.createAjax(response,jsonData);
	}
	/**
	 * 获取字典表接口
	 * @param json 
	 * @return
	 */
	@RequestMapping(value="getCodeList")
	public void getCodeList(String json,HttpServletResponse response){
		SafetyFilter.logger.info("传入JSON:" + json);
		String jsonData = "";
		try { //zheli ca
			Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据
			String initDidableKey=StringUtils.toStringByObject(maps.get("initDidableKey"));//（值）设置不包含该值 （1,2）
			String initCludeKey=StringUtils.toStringByObject(maps.get("initCludeKey"));//（值）设置只包含值 （1,2）
		    String nameKey=StringUtils.toStringByObject(maps.get("nameKey"));//或者SEX  ,这里必传，比如要查询性别的 字典， 传如 SEX 。参考字典表
	    	try {
	    		if(org.springframework.util.StringUtils.hasText(nameKey)){
	    			//不能有的值 集合
	    			Map<String,String> disableMap=new HashMap<String, String>();
	    			if(org.springframework.util.StringUtils.hasText(initDidableKey)){
	    				String ss[]=StringUtils.getArrayByArray(initDidableKey.split(","));
	    				for(String s:ss){
	    					disableMap.put(s,"1");
	    				}
	    			}
	    			//只能有的值 集合
	    			Map<String,String> cludeMap=new HashMap<String, String>();
	    			if(org.springframework.util.StringUtils.hasText(initCludeKey)){
	    				String ss[]=StringUtils.getArrayByArray(initCludeKey.split(","));
	    				for(String s:ss){
	    					cludeMap.put(s,"1");
	    				}
	    			}
    				Map<String,Object> objmap=SysStatic.commonBizCache.getALLcode().get(nameKey);//从缓存中获取子项值
    				List<Map<String,Object>> childs=(List<Map<String, Object>>) objmap.get("childs");
    				List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
	        		if(childs!=null&&childs.size()>0){
	        			for (Map<String,Object> option : childs) {
	        				Map<String,Object> maptemp=new HashMap<String,Object>();
	        				Object key=option.get("ab_st_value");
	        				//如果禁用一些值不显示，则跳过
	        				if(org.springframework.util.StringUtils.hasText(disableMap.get(key))){
	        					continue;
	        				}
	        				//如果只能让一些值显示，否则执行下一个
	        				if(org.springframework.util.StringUtils.hasText(initCludeKey)){
	            				if(!org.springframework.util.StringUtils.hasText(cludeMap.get(key))){
	            					continue;
	            				}
	            			}
	        				maptemp.put("key", key);
	        				maptemp.put("value", option.get("ab_st_name"));
	        				result.add(maptemp);
	        	        }
	        		}
	    			jsonData = "{\"success\":\"true\",\"msg\":\"获取成功！\",\"result\":"+JsonUtils.getJsonDataFromCollection(result)+"}";
	        	}else{
	        		jsonData = "{\"success\":\"false\",\"flag\":\"3\",\"msg\":\"namekey必须传入！\"}";
	        	}
			} catch (Exception e) {
				jsonData = "{\"success\":\"false\",\"flag\":\"2\",\"msg\":\"获取字典失败！\"}";
			}
		}catch (Exception e) {
			jsonData = "{\"success\":\"false\",\"flag\":\"1\",\"msg\":\"解析数据异常！\"}";
			e.printStackTrace();
		}
		SafetyFilter.logger.info("传出JSON:" + jsonData);
		this.createAjax(response,jsonData);
	}
	/**
	 * 获取活动商品列表接口
	 * @param json 
	 * @return
	 */
	@RequestMapping(value="getActivityShopList")
	public void getActivityShopList(String json,HttpServletResponse response){
		SafetyFilter.logger.info("传入JSON:" + json);
		String jsonData = "";
		try { 
			Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据
//			String bg_st_fbtpe =StringUtils.toStringByObject(maps.get("bg_st_fbtpe")); //类型
//			String bg_st_bbid =StringUtils.toStringByObject(maps.get("bg_st_bbid")); //商家id
//			String bg_st_name =StringUtils.toStringByObject(maps.get("bg_st_name")); //商品名称
//			String reordertype =StringUtils.toStringByObject(maps.get("reordertype")); //排序规则1为商品销量，2位商品价格
//			String bg_st_randid=StringUtils.toStringByObject(maps.get("bg_st_randid"),true);//产品类型
			String pageIndexs=StringUtils.toStringByObject(maps.get("pageIndex"),true);//页码
			String pageSizes=StringUtils.toStringByObject(maps.get("pageSize"),true);//页大小
			Integer pageIndex =org.springframework.util.StringUtils.hasText(pageIndexs)?Integer.valueOf(pageIndexs):0; //页码
			Integer pageSize =org.springframework.util.StringUtils.hasText(pageSizes)?Integer.valueOf(pageSizes):0; //页大小
			//默认页码和页大小
			pageIndex=pageIndex<1?1:pageIndex;
			pageSize=pageSize<1?10:pageSize;  
			StringBuffer sb=new StringBuffer("select bg_nm_storeNum, bg_st_bbid,  date_format(bo_dt_startDate,'%Y-%m-%d %T') bo_dt_startDate ,bg_st_name ,bg_st_id , date_format(bo_dt_endDate,'%Y-%m-%d %T')   bo_dt_endDate ,bp_st_spnum,bp_st_spprice,bp_st_spsl,bg_st_pricezg,bg_st_img1,bg_t_prodinfo.bg_st_pricetj"); 
			StringBuffer sbcount=new StringBuffer(" select count(1) ");
			StringBuffer sbcommon=new StringBuffer(" from bo_t_activity,bp_t_activityprodrelate,bg_t_prodinfo where bo_st_ddstate='1' and bo_st_del='0' and bo_st_ddnum=bp_st_ddnum and bp_st_spnum=bg_st_num ");
			List<String> pramsList = new ArrayList<String>();
//			//商家
//			if(StringUtils.hasText(bg_st_bbid)){
//				sbcommon.append(" and bg_st_bbid=? ");
//				pramsList.add(bg_st_bbid);
//			}
			sbcount.append(sbcommon);
			sb.append(sbcommon).append(" order by bo_dt_endDate desc");
			PageBean pages=baseBiz.getPages(sb.toString(),sbcount.toString(),pageIndex, pageSize,pramsList.toArray());
			jsonData = "{\"success\":\"true\",\"msg\":\"获取成功！\",\"result\":"+JsonUtils.getJsonData(pages)+"}";
		}catch (Exception e) {
			jsonData = "{\"success\":\"false\",\"flag\":\"1\",\"msg\":\"解析数据异常！\"}";
			e.printStackTrace();
		}
		SafetyFilter.logger.info("传出JSON:" + jsonData);
		this.createAjax(response,jsonData);
	} 

	/**
	 * 获取商品详情接口
	 * @param json 
	 * @return
	 */
	@RequestMapping(value="getShopInfoById")
	public void getShopInfoById(String json,HttpServletResponse response){
		SafetyFilter.logger.info("传入JSON:" + json);
		String jsonData = "";
		try {
			Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据
			String bg_st_id =StringUtils.toStringByObject(maps.get("bg_st_id")); //商品id
			//该商品下面的所有的评论
			if(org.springframework.util.StringUtils.hasText(bg_st_id)){
				StringBuffer sb=new StringBuffer("SELECT * "); 
				sb.append(" FROM bg_t_prodinfo WHERE bg_st_id=? ");
				Map<String,Object> map = baseBiz.queryForMap(sb.toString(),new Object[]{bg_st_id});
				jsonData = "{\"success\":\"true\",\"msg\":\"获取成功！\",\"result\":"+JsonUtils.getJsonData(map)+"}";
			}else{
				jsonData = "{\"success\":\"false\",\"flag\":\"1\",\"msg\":\"请传入完整数据\"}";
			}
		}catch (Exception e) {
			jsonData = "{\"success\":\"false\",\"flag\":\"1\",\"msg\":\"解析数据异常！\"}";
			e.printStackTrace();
		}
		SafetyFilter.logger.info("传出JSON:" + jsonData);
		this.createAjax(response,jsonData);
	} 
	
	/**
	 * 进入获取广告商品图片接口
	 * @param json 
	 * @return
	 */
	@RequestMapping(value="getAdList")
	public void getAdList(String json,HttpServletResponse response){
		SafetyFilter.logger.info("传入JSON:" + json);
		String jsonData = "";
		try {
			Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据
//			Integer pageIndex =StringUtils.toIntegerByObject(maps.get("pageIndex")); //页码
//			Integer pageSize =StringUtils.toIntegerByObject(maps.get("pageSize")); //页大小
			//该商品下面的所有的评论
			StringBuffer sb=new StringBuffer("SELECT bj_st_id,bj_st_type,bj_st_clickurl,(SELECT ag_st_url FROM ag_t_file WHERE ag_st_objid= bj_st_id  limit 0,1)bj_st_picurl "); 
			StringBuffer sbcount=new StringBuffer(" select count(1) ");
			StringBuffer sbcommon=new StringBuffer(" FROM bj_t_advertisement WHERE 1=1 and bj_st_enable='1' ");
			List<String> pramsList = new ArrayList<String>();
			sbcount.append(sbcommon);
			sb.append(sbcommon).append(" order by bj_dt_addDate desc ");
			PageBean pages=baseBiz.getPages(sb.toString(),sbcount.toString(),1, 4,pramsList.toArray());
			jsonData = "{\"success\":\"true\",\"msg\":\"获取成功！\",\"result\":"+JsonUtils.getJsonData(pages)+"}";
		}catch (Exception e) {
			jsonData = "{\"success\":\"false\",\"flag\":\"1\",\"msg\":\"解析数据异常！\"}";
			e.printStackTrace();
		}
		SafetyFilter.logger.info("传出JSON:" + jsonData);
		this.createAjax(response,jsonData);
	} 
	
	/**
	 * 获取商家列表接口
	 * @param json 
	 * @return
	 */
	@RequestMapping(value="getShopUserList")
	public void getShopUserList(String json,HttpServletResponse response){
		SafetyFilter.logger.info("传入JSON:" + json);
		String jsonData = "";
		try {
			Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据
			String pageIndexs=StringUtils.toStringByObject(maps.get("pageIndex"),true);//页码
			String name= StringUtils.toStringByObject(maps.get("name"),true);
			String pageSizes=StringUtils.toStringByObject(maps.get("pageSize"),true);//页大小
			Integer pageIndex =org.springframework.util.StringUtils.hasText(pageIndexs)?Integer.valueOf(pageIndexs):0; //页码
			Integer pageSize =org.springframework.util.StringUtils.hasText(pageSizes)?Integer.valueOf(pageSizes):0; //页大小
			//默认页码和页大小
			pageIndex=pageIndex<1?1:pageIndex;
			pageSize=pageSize<1?10:pageSize;
			//该商品下面的所有的评论
			/*StringBuffer sb=new StringBuffer("SELECT bb_st_userid,ae_st_name,(select ag_st_url from ag_t_file where ag_st_objid = ae_st_id  and ag_st_mark='userfile' limit 0,1) ag_st_url,bb_nm_jjpm,bb_st_shopinfo,bb_nm_clicks "); 
			StringBuffer sbcount=new StringBuffer(" select count(1) ");
			String sqlname="";
			if(name!=null&&!"".equals(name)){
				sqlname="and ae_st_name  like '%"+name+"%' ";
			}
			StringBuffer sbcommon=new StringBuffer(" FROM bb_t_shopuser,ae_t_sysuser  WHERE  ae_st_type ='3'  and bb_st_userid=ae_st_id   "+sqlname);
			sbcount.append(sbcommon);
			sb.append(sbcommon).append(" order by bb_nm_jjpm desc,ae_st_name ");*/
			String sqlname="";
			sqlname=" and C.ae_st_name  like '%"+name+"%' ";//参数没有进行为空测试，强行负的值。（应该修改）
			String shopSql = "SELECT  bb_st_userid,ae_st_name,(select ag_st_url from ag_t_file where ag_st_objid = ae_st_id  and ag_st_mark='userfile' limit 0,1) ag_st_url,bb_nm_jjpm,bb_st_shopinfo,bb_nm_clicks  FROM  Bb_t_ShopUser B , ae_t_sysuser C , ag_t_file D where B.bb_st_userid = C.ae_st_id and   D.ag_st_url is not null and  D.ag_st_objid = C.ae_st_id"+sqlname;
			String shopSql1 = "SELECT count(1) FROM  Bb_t_ShopUser B , ae_t_sysuser C , ag_t_file D where B.bb_st_userid = C.ae_st_id and   D.ag_st_url is not null and  D.ag_st_objid = C.ae_st_id";
			
			PageBean pages = baseBiz.getPages(shopSql, shopSql1, pageIndex, pageSize);

			
			
			
			jsonData = "{\"success\":\"true\",\"msg\":\"获取成功！\",\"result\":"+JsonUtils.getJsonData(pages)+"}";
		}catch (Exception e) {
			jsonData = "{\"success\":\"false\",\"flag\":\"1\",\"msg\":\"解析数据异常！\"}";
			e.printStackTrace();
		}
		SafetyFilter.logger.info("传出JSON:" + jsonData);
		this.createAjax(response,jsonData);
	} 
	/**
	 * 获取师傅订单列表接口
	 * @param json
	 * @return
	 */
	@RequestMapping(value="getSfOrderList")
	public void getSfOrderList(String json,HttpServletResponse response){
		SafetyFilter.logger.info("传入JSON:" + json);
		String jsonData = "";
		try {
			Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据
//			String bh_st_memberId = StringUtils.toStringByObject(maps.get("bh_st_memberId"),true); //用户id
			String loginflag = StringUtils.toStringByObject(maps.get("loginflag"),true); //登录标识
			String pageIndexs=StringUtils.toStringByObject(maps.get("pageIndex"),true);//页码
			String pageSizes=StringUtils.toStringByObject(maps.get("pageSize"),true);//页大小
			Integer pageIndex =org.springframework.util.StringUtils.hasText(pageIndexs)?Integer.valueOf(pageIndexs):0; //页码
			Integer pageSize =org.springframework.util.StringUtils.hasText(pageSizes)?Integer.valueOf(pageSizes):0; //页大小
			//默认页码和页大小
			pageIndex=pageIndex<1?1:pageIndex;
			pageSize=pageSize<1?10:pageSize;
			//验证不能为空
			if (org.springframework.util.StringUtils.hasText(loginflag)) {
				//查询该手机用户ID
				List<Map<String, Object>> listuser=baseBiz.queryForList("select a.ae_st_id from ae_t_sysuser a where a.ae_st_loginflag=? ", new Object[]{loginflag});
				if(listuser!=null&&listuser.size()==1){
					//当前手机用户登录人ID
					String bh_st_memberId =StringUtils.toStringByObject(listuser.get(0).get("ae_st_id"));

					StringBuffer sb=new StringBuffer("select bf_st_userid, bf_st_name ,  orderNo ,  payState , bf_st_address  , bf_dt_addDate , pricheAll "); 
					StringBuffer sbcount=new StringBuffer(" select count(1) ");//from bf_t_apply  WHERE bf_st_userid = ?
					StringBuffer sbcommon=new StringBuffer(" FROM bf_t_apply WHERE 1=1 ");
					List<String> pramsList = new ArrayList<String>();
					//人员id
					pramsList.add(bh_st_memberId);
					sbcommon.append(" and bf_st_userid=? ");
						
					sbcount.append(sbcommon);
					sb.append(sbcommon).append(" order by bf_dt_addDate desc ");
					PageBean pages=baseBiz.getPages(sb.toString(),sbcount.toString(),pageIndex, pageSize,pramsList.toArray());
					jsonData = "{\"success\":\"true\",\"msg\":\"获取成功！\",\"result\":"+JsonUtils.getJsonData(pages)+"}";
				}else {
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
	 * 获取订单列表接口
	 * @param json
	 * @return
	 */
	@RequestMapping(value="getOrderList")
	public void getOrderList(String json,HttpServletResponse response){
		SafetyFilter.logger.info("传入JSON:" + json);
		String jsonData = "";
		try {
			Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据
//			String bh_st_memberId = StringUtils.toStringByObject(maps.get("bh_st_memberId"),true); //用户id
			String loginflag = StringUtils.toStringByObject(maps.get("loginflag"),true); //登录标识
			String pageIndexs=StringUtils.toStringByObject(maps.get("pageIndex"),true);//页码
			String pageSizes=StringUtils.toStringByObject(maps.get("pageSize"),true);//页大小
			Integer pageIndex =org.springframework.util.StringUtils.hasText(pageIndexs)?Integer.valueOf(pageIndexs):0; //页码
			Integer pageSize =org.springframework.util.StringUtils.hasText(pageSizes)?Integer.valueOf(pageSizes):0; //页大小
			//默认页码和页大小
			pageIndex=pageIndex<1?1:pageIndex;
			pageSize=pageSize<1?10:pageSize;
			//验证不能为空
			if (org.springframework.util.StringUtils.hasText(loginflag)) {
				//查询该手机用户ID
				List<Map<String, Object>> listuser=baseBiz.queryForList("select a.ae_st_id from ae_t_sysuser a where a.ae_st_loginflag=? ", new Object[]{loginflag});
				if(listuser!=null&&listuser.size()==1){
					//当前手机用户登录人ID
					String bh_st_memberId =StringUtils.toStringByObject(listuser.get(0).get("ae_st_id"));

					StringBuffer sb=new StringBuffer("SELECT chargeId , bh_st_id,bh_st_ddnum,bh_st_spprice,bh_st_ddstate,bh_st_source,bh_st_shname,bh_st_shaddress,bh_st_shphone,bh_st_source,date_format(bh_dt_addDate,'%Y-%m-%d %T')bh_dt_addDate "); 
					StringBuffer sbcount=new StringBuffer(" select count(1) ");
					StringBuffer sbcommon=new StringBuffer(" FROM bh_t_orderform WHERE 1=1 ");
					List<String> pramsList = new ArrayList<String>();
					//人员id
					pramsList.add(bh_st_memberId);
					sbcommon.append(" and bh_st_memberId=? ");
						
					sbcount.append(sbcommon);
					sb.append(sbcommon).append(" order by bh_dt_addDate desc ");
					PageBean pages=baseBiz.getPages(sb.toString(),sbcount.toString(),pageIndex, pageSize,pramsList.toArray());
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
					StringBuffer sbcommon = new StringBuffer(" FROM bi_t_orderprodrelate bi,bh_t_orderform bh,bg_t_prodinfo bg  WHERE 1=1 AND  bg_st_num =bi.bi_st_spnum and bi.bi_st_ddnum=bh.bh_st_ddnum AND bh_st_memberId=? and bh_st_ddnum='"+bh_st_ddnum+"'");//bh_st_ddnum对应订单编号的一张订单的详情信息
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
	 * 获取类型
	 * @param json
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="getDecorationType")
	public void getDecorationType(String json,HttpServletResponse response){
		SafetyFilter.logger.info("传入JSON:" + json);
		String jsonData = "";
		try {
			List<Map<String,Object>> childs=new ArrayList<Map<String,Object>>();
			Map<String,Object> objmap=SysStatic.commonBizCache.getALLcode().get("USERTYPE_6");//从缓存中获取子项值
			childs=(List<Map<String, Object>>) objmap.get("childs");
			jsonData = "{\"success\":\"true\",\"flag\":\"1\",\"result\":"+JsonUtils.getJsonDataFromCollection(childs)+"}";
		}catch (Exception e) {
			jsonData = "{\"success\":\"false\",\"flag\":\"1\",\"msg\":\"解析数据异常！\"}";
		}
		SafetyFilter.logger.info("传出JSON:" + jsonData);
		this.createAjax(response,jsonData);
	}
	/**
	 * 新增或更新系统用户
	 * @param response
	 * @param sysfunction
	 */
	@RequestMapping(value="userupd")
	public void userupd(Ae_t_sysuser ae_t_sysuser,Ba_t_Labour labour,Bb_t_ShopUser shopUser,Ag_t_file oldFile,MultipartHttpServletRequest multipartRequest){
		String jsonData="";
		try {
			if(!org.springframework.util.StringUtils.hasText(ae_t_sysuser.getUsername())){
				throw new RuntimeException("帐号不能为空");
			}
			//选择的地区
			String groupids3=request.getParameter("groupids3");
			if(org.springframework.util.StringUtils.hasText(groupids3)){
				String[] groups=groupids3.split(",");
				for(int b=groups.length,i=b-1;i>=0;i--){
					if(i==b-1){
						ae_t_sysuser.setAe_st_shsheng(groups[i]);
					}else if(i==b-2){
						ae_t_sysuser.setAe_st_shshi(groups[i]);
					}else if(i==b-3){
						ae_t_sysuser.setAe_st_shxian(groups[i]);
					}
				}
			}
//			Encoder e=new MD5Encoder();//使用security3 的MD5加密技术
			//创建批量保存事务对象
			List<BizTransUtil> list=new ArrayList<BizTransUtil>();
			String id = ae_t_sysuser.getAe_st_id();
			//更新
			if (org.springframework.util.StringUtils.hasText(id)) {
				//构建数据--修改
				Map<String,Object>  map=new LinkedCaseInsensitiveMap<Object>();
				map.put("ae_st_id", id);//ID
//				if(StringUtils.hasText(ae_t_sysuser.getPassword())){
//					map.put("password", e.encrypt(ae_t_sysuser.getPassword(), ae_t_sysuser.getUsername()));//MD5加密
//				}
				map.put("ae_st_updUserId", getcuttSysuserID());
				map.put("ae_dt_updDate", getcuttDate());
//				map.put("ae_st_lockstate", ae_t_sysuser.getAe_st_lockstate());//审核通过/不通过状态修改
				map.put("ae_st_name", ae_t_sysuser.getAe_st_name());//姓名
				map.put("ae_st_tell", ae_t_sysuser.getAe_st_tell());//电话
				map.put("ae_st_sex", ae_t_sysuser.getAe_st_sex());//性别
				map.put("ae_nm_age", ae_t_sysuser.getAe_nm_age());//年龄 
				map.put("ae_st_address", ae_t_sysuser.getAe_st_address());//详细地址
				map.put("ae_st_intro", ae_t_sysuser.getAe_st_intro());//简介
				map.put("ae_st_shsheng", ae_t_sysuser.getAe_st_shsheng());//省
				map.put("ae_st_shshi", ae_t_sysuser.getAe_st_shshi());//市
				map.put("ae_st_shxian", ae_t_sysuser.getAe_st_shxian());//县/区
//				map.put("ae_nm_zjnum", ae_t_sysuser.getAe_nm_zjnum());//资金余额
				map.put("ae_st_nickName", ae_t_sysuser.getAe_st_nickName());//用户昵称
				list.add(new BizTransUtil(map,Ae_t_sysuser.class,CommonUtil.UPDATE));
				//用户表子表修改
				String type = ae_t_sysuser.getAe_st_type();
				if("3".equals(type)){
					Map<String,Object>  sjmap=new LinkedCaseInsensitiveMap<Object>();
					sjmap.put("bb_st_id", shopUser.getBb_st_id());
					sjmap.put("bb_st_area", shopUser.getBb_st_area());
					sjmap.put("bb_st_phone1", shopUser.getBb_st_phone1());
					sjmap.put("bb_st_phone2", shopUser.getBb_st_phone2());
					sjmap.put("bb_st_shopinfo", shopUser.getBb_st_shopinfo());
					list.add(new BizTransUtil(sjmap,Bb_t_ShopUser.class,CommonUtil.UPDATE));
				}else if("4".equals(type)||"5".equals(type)||"6".equals(type)){
					Map<String,Object>  sfmap=new LinkedCaseInsensitiveMap<Object>();
					sfmap.put("ba_st_id", labour.getBa_st_id());
					sfmap.put("ba_st_type", labour.getBa_st_type());
					sfmap.put("ba_st_price", labour.getBa_st_price());
					sfmap.put("ba_st_team_intro", labour.getBa_st_team_intro());
					list.add(new BizTransUtil(sfmap,Ba_t_Labour.class,CommonUtil.UPDATE));
				}
			}
			//新增图片/删除图片
			MultipartFile mf = multipartRequest.getFile("myfile");
			String delfileurl = "";
			if(mf!=null && mf.getSize()>0){
				//如果是修改，且文件表不为空，则删除相关文件表数据，和清除文件
				if (org.springframework.util.StringUtils.hasText(id)){
					list.add(new BizTransUtil("delete from ag_t_file where ag_st_objid=?", new Object[]{id},CommonUtil.DELETE));//删除本条数据
					delfileurl=oldFile.getAg_st_url();
				}
				Ag_t_file file=new Ag_t_file(true);
				//上传文件的相对路径
				String uploadPath=UploadFile.uploadInputStream(mf.getInputStream(), mf.getOriginalFilename(), "images/business/user");
				BufferedImage buff = ImageIO.read(mf.getInputStream());
				file.setAg_nm_height(buff.getHeight());//文件属性高
				file.setAg_nm_width(buff.getWidth());//文件属性宽
				file.setAg_nm_size(mf.getSize());//文件属性大小
				file.setAg_st_format(mf.getContentType());//文件格式
				file.setAg_dt_addDate(getcuttDate());//创建时间
				file.setAg_st_addUserId(getcuttSysuserID());//创建人员ID  
				file.setAg_st_objid(ae_t_sysuser.getAe_st_id());//文件归属ID
				file.setAg_st_objtype("ae_t_sysuser");//文件对应的对象
				file.setAg_st_type("1");//文件类型-图片
				file.setAg_st_mark("userfile");//自定义文件标识
				file.setAg_st_url(uploadPath);////文件 的存储地址
				list.add(new BizTransUtil(file));
			}
			//执行操作
			if (baseBiz.executesTRANS(list)) {
				if(org.springframework.util.StringUtils.hasText(delfileurl)){
					UploadFile.delpic(delfileurl);//删除原文件
				}
				jsonData = "{success:true,msg:\"操作成功!\"}";
			} else {
				jsonData = "{success:false,msg:\"操作异常!\"}";
			}
		}  catch (Exception e) {
			jsonData = "{success:false,msg:\"操作异常，"+StringUtils.replaceBlank(e.getMessage())+"!\"}";
		}
		this.createAjax(response,jsonData);
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
			String loginflag = StringUtils.toStringByObject(maps.get("loginflag"),true); //登录标识
			//验证不能为空
			if (org.springframework.util.StringUtils.hasText(loginflag)) {
				//查询该手机用户ID
				List<Map<String, Object>> listuser=baseBiz.queryForList("select a.ae_st_id from ae_t_sysuser a where a.ae_st_loginflag=? ", new Object[]{loginflag});
				if(listuser!=null&&listuser.size()==1){
					//当前手机用户登录人ID
					String ae_st_id =StringUtils.toStringByObject(listuser.get(0).get("ae_st_id"));
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
					assess.setPjbs(1);                                    //添加预约默认给0  添加了评价后给值给1
					System.out.println(assess.getPjbs());
					assess.setBe_st_content(be_st_content);
					assess.setBe_st_fbgid(ae_st_id);
					assess.setBe_dt_addDate(getcuttDate());
					assess.setBe_st_addUserId(ae_st_id);
					list.add(new BizTransUtil(assess));
					if(baseBiz.executesTRANS(list)){
						jsonData = "{\"success\":\"true\",\"flag\":\"1\",\"msg\":\"评价成功！\"}";
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
	 *添加订单接口
	 * @param json
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="addorder")
	public void addorder(String json,HttpServletResponse response,HttpServletRequest request){
		SafetyFilter.logger.info("传入JSON:" + json);
		String jsonData = "";
		Bh_t_orderform bh=null;
		List<BizTransUtil> list = new ArrayList<BizTransUtil>();
		try {
			Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据
			String loginflag = StringUtils.toStringByObject(maps.get("loginflag"),true); //登录标识
			String bh_st_shname = StringUtils.toStringByObject(maps.get("bh_st_shname"),true); //收货人
			String bh_st_shphone = StringUtils.toStringByObject(maps.get("bh_st_shphone"),true); //电话
			String bh_st_shaddress = StringUtils.toStringByObject(maps.get("bh_st_shaddress"),true); //地址
			Date nowDate=getcuttDate();//当前操作时间
			//验证不能为空
			if (org.springframework.util.StringUtils.hasText(loginflag)&&org.springframework.util.StringUtils.hasText(bh_st_shname)&&org.springframework.util.StringUtils.hasText(bh_st_shphone)&&org.springframework.util.StringUtils.hasText(bh_st_shaddress)) {
				//查询该手机用户ID
				List<Map<String, Object>> listuser=baseBiz.queryForList("select a.ae_st_id from ae_t_sysuser a where a.ae_st_loginflag=? ", new Object[]{loginflag});
				if(listuser!=null&&listuser.size()==1){
					//当前手机用户登录人ID
					String ae_st_id =StringUtils.toStringByObject(listuser.get(0).get("ae_st_id"));
					
					ArrayList<MorphDynaBean> jsonArray=(ArrayList<MorphDynaBean>) maps.get("shopinfo");
					if(jsonArray.size()>0){
						Bh_t_orderform  orderLs = new Bh_t_orderform();
						orderLs.setBh_st_shname(bh_st_shname);
						orderLs.setBh_st_shphone(bh_st_shphone);
						orderLs.setBh_st_shaddress(bh_st_shaddress);
						orderLs.setBh_st_ddstate("1");//订单状态  //1待付款 
						orderLs.setBh_st_del("0");//是否删除
						orderLs.setBh_st_source("1");//订单来源1.app2.pc端
						orderLs.setBh_st_memberId(ae_st_id);//会员ID
						orderLs.setBh_st_addUserId(ae_st_id);//创建人员ID  
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
							}else{
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
								orderProdRelate.setBi_st_addUserId(ae_st_id);//创建人员ID  
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
						jsonData = "{\"success\":\"true\",\"flag\":\"1\",\"order\":\""+bh.getBh_st_ddnum()+"\",\"msg\":\"下单成功！\"}";
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
	
	/**
	 * 获取关于我们接口
	 * @param json
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="getAboutUs")
	public void getAboutUs(String json,HttpServletResponse response,HttpServletRequest request){
			SafetyFilter.logger.info("传入JSON:" + json);
			String jsonData = "";
			try{
				StringBuffer sb = new StringBuffer("select bl_st_id,bl_st_context from bl_t_noticecontext where bl_st_isSend='1' and bl_st_type='6' order by bl_dt_addDate  ");
				List<Map<String,Object>> list = baseBiz.queryForList(sb.toString());
				if(list.size()>0){
					jsonData = "{\"success\":\"true\",\"msg\":\"获取成功！\",\"result\":"+JsonUtils.getJsonData(list.get(0))+"}";
				}else{
					jsonData = "{\"success\":\"false\",\"flag\":\"1\",\"msg\":\"暂无信息！\"}";
				}
			}catch (Exception e) {
				jsonData = "{\"success\":\"false\",\"flag\":\"3\",\"msg\":\"解析数据异常！\"}";
				e.printStackTrace();
			}
			SafetyFilter.logger.info("传出JSON:" + jsonData);
			this.createAjax(response,jsonData);
	}
	/**
	 * 获取预约订单
	 * @param json
	 */
	@RequestMapping(value="getOrder")
	public void getOrder(String json){
        String order=request.getParameter("order");
		if(!org.springframework.util.StringUtils.hasText(order)){
			String jsonData = "{\"success\":\"false\",\"flag\":\"1\",\"msg\":\"获取订单失败\"}";
			this.createAjax(response,jsonData);
			return ;
		}
		String sql="SELECT bf_st_name ,  bf_st_tell , pricheAll  FROM bf_t_apply  where  orderNo="+json+" ";
		List<Map<String, Object>> list1 = baseBiz.queryForList(sql);
	    String	jsonData = "{\"success\":\"true\",\"msg\":\"获取成功！\",\"result\":"+JsonUtils.getJsonDataFromCollection(list1)+"}";
	    this.createAjax(response,jsonData);
	}
	
	/**
	 * 提交反馈接口
	 * @param json
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="addAdvice")
	public void addAdvice(String json,HttpServletResponse response,HttpServletRequest request){
			SafetyFilter.logger.info("传入JSON:" + json);
			String jsonData = "";
			List<BizTransUtil> list = new ArrayList<BizTransUtil>();
			try{
				Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据
				String bk_st_type = StringUtils.toStringByObject(maps.get("bk_st_type"),true); //类型
				String bk_st_phone= StringUtils.toStringByObject(maps.get("bk_st_phone"),true); //联系电话--非必填
				String bk_st_content = StringUtils.toStringByObject(maps.get("bk_st_content"),true); //内容
				String loginflag = StringUtils.toStringByObject(maps.get("loginflag"),true); //登录标识--非必填
				String title=StringUtils.toStringByObject(maps.get("bk_st_titie"),true);
				String bk_st_addUserId ="";
				//目前提建议只有两种类型
				if(!("1".equals(bk_st_type)||"2".equals(bk_st_type))){
					throw new RuntimeException("意见类型不正确！"); 
				}
				//验证不能为空
				if (org.springframework.util.StringUtils.hasText(loginflag)) {
					//查询该手机用户ID
					List<Map<String, Object>> listuser=baseBiz.queryForList("select a.ae_st_id from ae_t_sysuser a where a.ae_st_loginflag=? ", new Object[]{loginflag});
					if(listuser!=null&&listuser.size()==1){
						//当前手机用户登录人ID
						bk_st_addUserId =StringUtils.toStringByObject(listuser.get(0).get("ae_st_id"));
					}
				} 
				if(org.springframework.util.StringUtils.hasText(bk_st_content)){
					Bk_t_HelpsCollect helpsCollect = new Bk_t_HelpsCollect(true);
					helpsCollect.setBk_dt_addDate(getcuttDate());
					helpsCollect.setBk_st_type(bk_st_type);
					helpsCollect.setBk_st_content(bk_st_content);
					helpsCollect.setBk_st_title(title);
					helpsCollect.setBk_st_isdel("0");
					helpsCollect.setBk_st_source("1");
					helpsCollect.setBk_st_phone(bk_st_phone);;
					helpsCollect.setBk_st_addUserId(bk_st_addUserId);
					list.add(new BizTransUtil(helpsCollect));
				}else{
					throw new RuntimeException("请输入内容");
				}
				if(baseBiz.executesTRANS(list)){
					jsonData = "{\"success\":\"true\",\"flag\":\"1\",\"msg\":\"提交成功\"}";
				}
			}catch (Exception e) {
				jsonData = "{\"success\":\"false\",\"flag\":\"2\",\"msg\":\""+e.getMessage()+"\"}";
				e.printStackTrace();
			}
			SafetyFilter.logger.info("传出JSON:" + jsonData);
			this.createAjax(response,jsonData);
	}
	
	/**
	 * 获取我的服务列表接口
	 * @param json
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="getMyServiceList")
	public void getMyServiceList(String json,HttpServletResponse response,HttpServletRequest request){
			SafetyFilter.logger.info("传入JSON:" + json);
			String jsonData = "";
			try{
				Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据
				//String ae_st_id = StringUtils.toStringByObject(maps.get("ae_st_id"),true); //会员ID
				String loginflag = StringUtils.toStringByObject(maps.get("loginflag"),true); //登录标识
				String falgtype = StringUtils.toStringByObject(maps.get("falgtype"),true); //标识
				String pageIndexs=StringUtils.toStringByObject(maps.get("pageIndex"),true);//页码
				String pageSizes=StringUtils.toStringByObject(maps.get("pageSize"),true);//页大小
				Integer pageIndex =org.springframework.util.StringUtils.hasText(pageIndexs)?Integer.valueOf(pageIndexs):0; //页码
				Integer pageSize =org.springframework.util.StringUtils.hasText(pageSizes)?Integer.valueOf(pageSizes):0; //页大小
				//默认页码和页大小
				pageIndex=pageIndex<1?1:pageIndex;
				pageSize=pageSize<1?10:pageSize;
				List<String> pramsList=new ArrayList<String>();
				//验证不能为空
				if (org.springframework.util.StringUtils.hasText(loginflag)&&org.springframework.util.StringUtils.hasText(falgtype)) {
					//查询该手机用户ID
					List<Map<String, Object>> list=baseBiz.queryForList("select a.ae_st_id from ae_t_sysuser a where a.ae_st_loginflag=? ", new Object[]{loginflag});
					if(list!=null&&list.size()==1){
						//当前手机用户登录人ID
						String ae_st_id =StringUtils.toStringByObject(list.get(0).get("ae_st_id"));
						//该会员下面的所有的收货地址
						StringBuffer sb = new StringBuffer("SELECT bf.orderNo,bf.payState,ae.ae_st_id,bf.bf_st_id,ba.ba_st_id,ba.ba_st_grade,ba.ba_st_team_intro,ba.ba_st_teamnum,ae_st_name,(SELECT  ag_st_url FROM  ag_t_file WHERE ag_st_objid=ae.ae_st_id  and ag_st_mark='userfile' limit 0,1) ag_st_url,(SELECT COUNT(1) FROM bf_t_apply WHERE bf_st_receiveid=ba.ba_st_userid)ordersum,(select pjbs from be_t_assess where  be_st_jbgid= ba.ba_st_userid and be_st_fbgid ='"+ae_st_id+"' ) pjbs");
						StringBuffer sbcount = new StringBuffer("select count(1) ");
						StringBuffer sbcommon = new StringBuffer( " FROM ae_t_sysuser ae,ba_t_labour ba ,bf_t_apply bf WHERE bf.bf_st_receiveid=ae.ae_st_id AND ba.ba_st_userid=ae.ae_st_id AND bf_st_userid=? ");
						pramsList.add(ae_st_id);
						//验房
						if(falgtype.equals("1")){
							sbcommon.append(" and bf_st_type in('2','3') ");
						}//设计
						else if(falgtype.equals("2")){
							sbcommon.append(" and bf_st_type in('4','5','6') ");
						}//装修(师傅)
						else if(falgtype.equals("3")){
							sbcommon.append(" and bf_st_type in('8','11') ");
						}//监理
						else if(falgtype.equals("4")){
							sbcommon.append(" and bf_st_type in('9','10') ");
						}//检测
						else if(falgtype.equals("5")){
							sbcommon.append(" and bf_st_type in('12','13') ");
						}//装修（工长）
						else if(falgtype.equals("6")){
							sbcommon.append(" and bf_st_type in('7') ");
						}
						sbcount.append(sbcommon);
						sb.append(sbcommon).append(" order by bf_dt_addDate desc ");
						PageBean pages=baseBiz.getPages(sb.toString(),sbcount.toString(),pageIndex, pageSize,pramsList.toArray());
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
	private String StringUtilstoStringByObject(Object object, boolean b) {
		// TODO Auto-generated method stub
		return null;
	}
	@RequestMapping(value="getNum")
	public void getNum(){
		String jsonData = "";
		try {
			//免费验房统计
			Map<String, Object> mfyf=baseBiz.queryForMap("SELECT COUNT(1) num FROM bf_t_apply WHERE bf_st_type='2'");
			//金牌验房统计
			Map<String, Object> jpyf=baseBiz.queryForMap("SELECT COUNT(1) num FROM bf_t_apply WHERE bf_st_type='3'");
			jsonData = "{\"success\":\"true\",\"flag\":\"1\",\"mfyf\":\""+mfyf.get("num")+"\",\"jpyf\":\""+jpyf.get("num")+"\",\"msg\":\"请求成功！\"}";
			System.out.println(mfyf.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			jsonData = "{\"success\":\"false\",\"flag\":\"1\",\"msg\":\"解析数据异常！\"}";
			e.printStackTrace();
		}
		SafetyFilter.logger.info("传出JSON:" + jsonData);
		this.createAjax(response,jsonData);
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
				String loginflag = StringUtils.toStringByObject(maps.get("loginflag"),true); //登录标识
				String bf_st_id = StringUtils.toStringByObject(maps.get("bf_st_id"),true); //服务的主键ID
				//验证不能为空
				if (org.springframework.util.StringUtils.hasText(loginflag)&&org.springframework.util.StringUtils.hasText(bf_st_id)) {
					//查询该手机用户ID
					List<Map<String, Object>> list=baseBiz.queryForList("select a.ae_st_id from ae_t_sysuser a where a.ae_st_loginflag=? ", new Object[]{loginflag});
					if(list!=null&&list.size()==1){
						//当前手机用户登录人ID
						String ae_st_id =StringUtils.toStringByObject(list.get(0).get("ae_st_id"));
						//该会员下面的所有的收货地址                
						StringBuffer sb = new StringBuffer("SELECT ae.ae_st_id,bf.bf_st_id,ba.ba_st_id,ba.ba_st_grade,ba.ba_st_team_intro,ba.ba_st_teamnum,ae_st_name,(SELECT  ag_st_url FROM  ag_t_file WHERE ag_st_objid=ae.ae_st_id  and ag_st_mark='userfile' limit 0,1) ag_st_url,(SELECT COUNT(1) FROM bf_t_apply WHERE bf_st_receiveid=ba.ba_st_id)ordersum,(SELECT ag_st_url FROM bd_t_case,ag_t_file WHERE bd_st_bbid=ae.ae_st_id AND ag_st_objid=bd_st_id) images");
						StringBuffer sbcommon = new StringBuffer( " FROM ae_t_sysuser ae,ba_t_labour ba ,bf_t_apply bf WHERE bf.bf_st_receiveid=ae.ae_st_id AND ba.ba_st_userid=ae.ae_st_id AND bf_st_userid=? and bf_st_id=? ");
						List<String> pramsList=new ArrayList<String>();
						pramsList.add(ae_st_id);
						pramsList.add(bf_st_id);
						sb.append(sbcommon);
						List<Map<String, Object>> listinfo=baseBiz.queryForList(sb.toString(), pramsList.toArray());
						if(listinfo!=null&&listinfo.size()>0){
							String sql="select ag_st_url from ag_t_file where ag_st_objid=?";
							List<Map<String,Object>> listreulst=baseBiz.queryForList(sql,new Object[]{bf_st_id});
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
	 * 获取我的交易列表接口
	 * @param json
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="getMoneyLogList")
	public void getMoneyLogList(String json,HttpServletResponse response,HttpServletRequest request){
			SafetyFilter.logger.info("传入JSON:" + json);
			String jsonData = "";
			try{
				Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据
				String loginflag = StringUtils.toStringByObject(maps.get("loginflag"),true); //登录标识
				String pageIndexs=StringUtils.toStringByObject(maps.get("pageIndex"),true);//页码
				String pageSizes=StringUtils.toStringByObject(maps.get("pageSize"),true);//页大小
				Integer pageIndex =org.springframework.util.StringUtils.hasText(pageIndexs)?Integer.valueOf(pageIndexs):0; //页码
				Integer pageSize =org.springframework.util.StringUtils.hasText(pageSizes)?Integer.valueOf(pageSizes):0; //页大小
				//默认页码和页大小
				pageIndex=pageIndex<1?1:pageIndex;
				pageSize=pageSize<1?10:pageSize;
				List<String> pramsList=new ArrayList<String>();
				//验证不能为空
				if (org.springframework.util.StringUtils.hasText(loginflag)) {
					//查询该手机用户ID
					List<Map<String, Object>> list=baseBiz.queryForList("select a.ae_st_id from ae_t_sysuser a where a.ae_st_loginflag=? ", new Object[]{loginflag});
					if(list!=null&&list.size()==1){
						//当前手机用户登录人ID
						String ae_st_id =StringUtils.toStringByObject(list.get(0).get("ae_st_id"));
						//该会员下面的所有的交易信息
						StringBuffer sb = new StringBuffer("SELECT bm_nm_money,bm_st_type,bm_st_operation,bm_st_remark,date_format(bm_dt_addDate,'%Y-%m-%d %T') bm_dt_addDate ");
						StringBuffer sbcount = new StringBuffer("select count(1) ");
						StringBuffer sbcommon = new StringBuffer( " FROM  bm_t_usermoneylog WHERE 1=1 and bm_st_fsuserid=?");
						pramsList.add(ae_st_id);
						sbcount.append(sbcommon);
						sb.append(sbcommon).append(" order by bm_dt_addDate desc ");
						PageBean pages=baseBiz.getPages(sb.toString(),sbcount.toString(),pageIndex, pageSize,pramsList.toArray());
						jsonData = "{\"success\":\"true\",\"msg\":\"获取成功！\",\"result\":"+JsonUtils.getJsonData(pages)+"}";
					} else {
						throw new MyoneException("未获取该手机用户信息");
					}
				} else {
					jsonData = "{\"success\":\"false\",\"flag\":\"2\",\"msg\":\"请先传入完整的数据信息！\"}";
				}
			} catch (MyoneException e) {
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
	 * 获取地区一级机构（省）及下属机构接口
	 * @param json
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="getAddressList")
	public void getAddressList(String json,HttpServletResponse response,HttpServletRequest request){
			SafetyFilter.logger.info("传入JSON:" + json);
			String jsonData = "";
			try{
				Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据
				String d_parent = StringUtils.toStringByObject(maps.get("d_parent"),true); //父ID
				StringBuffer sb = new StringBuffer("SELECT d_level,d_code,d_name,d_parent FROM aa26 WHERE 1=1 ");
				List<Map<String,Object>> lists =null;
				if(org.springframework.util.StringUtils.hasText(d_parent)){
					//(select d_code from aa26 where d_name like '%成都市%')
					sb.append(" and d_parent = "+d_parent+" ");
					lists = baseBiz.queryForList(sb.toString());
				}else{
					sb.append(" and d_level=1 ");
					lists = baseBiz.queryForList(sb.toString());
				}
				jsonData = "{\"success\":\"true\",\"msg\":\"获取成功！\",\"result\":"+JsonUtils.getJsonDataFromCollection(lists)+"}";
			}catch (Exception e) {
				jsonData = "{\"success\":\"false\",\"flag\":\"3\",\"msg\":\"解析数据异常！\"}";
				e.printStackTrace();
			}
			SafetyFilter.logger.info("传出JSON:" + jsonData);
			this.createAjax(response,jsonData);
	}
	
	/***
	 * 收货人地址添加/修改
	 * @param json
	 * @param response
	 * @return
	 */
	@RequestMapping(value="addUsefulAddress")
	public void addUsefulAddress(String json,HttpServletResponse response){
		SafetyFilter.logger.info("传入JSON:" + json);
		String jsonData = "";
		try {
			Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据//解析数据
			String loginflag = StringUtils.toStringByObject(maps.get("loginflag"),true); //登录标识
			String id = StringUtils.toStringByObject(maps.get("id"),true);//主键ID--如果是修改必须传入，否则为新增
			String shname = StringUtils.toStringByObject(maps.get("shname"),true);//收货人姓名
			String shphone = StringUtils.toStringByObject(maps.get("shphone"),true);//收货人电话
			String shsheng = StringUtils.toStringByObject(maps.get("shsheng"),true);//省
			String shshi = StringUtils.toStringByObject(maps.get("shshi"),true);//市
			String shxian = StringUtils.toStringByObject(maps.get("shxian"),true);//县/区
			String shaddress = StringUtils.toStringByObject(maps.get("shaddress"),true);//收货人地址
			//验证不能为空
			if (org.springframework.util.StringUtils.hasText(loginflag)&&org.springframework.util.StringUtils.hasText(shname)&&org.springframework.util.StringUtils.hasText(shphone)&&org.springframework.util.StringUtils.hasText(shsheng)&&org.springframework.util.StringUtils.hasText(shshi)&&org.springframework.util.StringUtils.hasText(shaddress)) {
				//查询该手机用户ID
				List<Map<String, Object>> list=baseBiz.queryForList("select a.ae_st_id,(select count(bq_st_id) from bq_t_usefuladdress where bq_st_memberid=a.ae_st_id) uanum from ae_t_sysuser a where a.ae_st_loginflag=? ", new Object[]{loginflag});
				if(list!=null&&list.size()==1){
					//当前手机用户登录人ID
					String userId =StringUtils.toStringByObject(list.get(0).get("ae_st_id"));
					Integer uanum =StringUtils.toIntegerByObject(list.get(0).get("uanum"));
					List<BizTransUtil> listobj=new ArrayList<BizTransUtil>();
					//修改
					if(org.springframework.util.StringUtils.hasText(id)){
						//构建数据--修改
						Map<String,Object>  map=new LinkedCaseInsensitiveMap<Object>();
						map.put("bq_st_id", id);//ID
						map.put("bq_st_name", shname);//收货人姓名
						map.put("bq_st_phone", shphone);//收货人电话
						map.put("bq_st_sheng", shsheng);//省
						map.put("bq_st_shi", shshi);//市
						map.put("bq_st_xian", shxian);//县/区
						map.put("bq_st_adress", shaddress);//详细地址
						map.put("bq_st_updUserId", userId);//修改人员ID 
						map.put("bq_dt_updDate", getcuttDate());//修改时间
						listobj.add(new BizTransUtil(map,Bq_t_UsefulAddress.class,CommonUtil.UPDATE));
					}
					//新增
					else{
						//每个会员最多只能添加10个地址 
						if (uanum>9) {
							throw new MytwoException();
						}
						Bq_t_UsefulAddress ua = new Bq_t_UsefulAddress(true);
						ua.setBq_st_memberid(userId);//归属会员ID
						ua.setBq_st_name(shname);//收货人姓名
						ua.setBq_st_phone(shphone);//收货人电话
						ua.setBq_st_sheng(shsheng);//省
						ua.setBq_st_shi(shshi);//市
						ua.setBq_st_xian(shxian);//县/区
						ua.setBq_st_adress(shaddress);//详细地址
						ua.setBq_st_addUserId(userId);//创建人员ID  
						ua.setBq_dt_addDate(getcuttDate());//创建时间
						ua.setBq_st_ismr("0");//是否默认， 否
						listobj.add(new BizTransUtil(ua));
					}
					//地址添加
					if(baseBiz.executesTRANS(listobj)){
						jsonData = "{\"success\":\"true\",\"msg\":\"保存成功\"}";
					}else{
						jsonData = "{\"success\":\"false\",\"flag\":\"4\",\"msg\":\"网络异常，请重试\"}";
					}
				} else {
					throw new MyoneException("未获取该手机用户信息");
				}
			} else {
				jsonData = "{\"success\":\"false\",\"flag\":\"2\",\"msg\":\"请先传入完整的数据信息！\"}";
			}
		} catch (MytwoException e) {
			jsonData = "{\"success\":\"false\",\"flag\":\"5\",\"msg\":\"每个会员最多只能添加10个地址 ！\"}";
			e.printStackTrace();
		} catch (MyoneException e) {
			jsonData = "{\"success\":\"false\",\"flag\":\"3\",\"msg\":\"登录标识已经失效，请重新登录！\"}";
			e.printStackTrace();
		}catch (Exception e) {
			jsonData = "{\"success\":\"false\",\"flag\":\"1\",\"msg\":\"解析数据异常！\"}";
			e.printStackTrace();
		}
		SafetyFilter.logger.info("传出JSON:" + jsonData);
		this.createAjax(response,jsonData);
	} 
	/***
	 * 设置收货人地址默认
	 * @param json
	 * @param response
	 * @return
	 */
	@RequestMapping(value="setUsefulAddressMR")
	public void setUsefulAddressMR(String json,HttpServletResponse response){
		SafetyFilter.logger.info("传入JSON:" + json);
		String jsonData = "";
		try {
			Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据//解析数据
			String loginflag = StringUtils.toStringByObject(maps.get("loginflag"),true); //登录标识
			String id = StringUtils.toStringByObject(maps.get("id"),true);//主键ID--收货地址的
			//验证不能为空
			if (org.springframework.util.StringUtils.hasText(loginflag)&&org.springframework.util.StringUtils.hasText(id)) {
				//查询该手机用户ID
				List<Map<String, Object>> list=baseBiz.queryForList("select a.ae_st_id from ae_t_sysuser a where a.ae_st_loginflag=? ", new Object[]{loginflag});
				if(list!=null&&list.size()==1){
					//当前手机用户登录人ID
					String userId =StringUtils.toStringByObject(list.get(0).get("ae_st_id"));
					List<BizTransUtil> listobj=new ArrayList<BizTransUtil>();
					//取消原来的默认地址
					listobj.add(new BizTransUtil("update bq_t_usefuladdress set bq_st_ismr='0' where bq_st_memberid=? and bq_st_ismr='1'", new Object[]{userId}, CommonUtil.UPDATE));
					//设置现在的默认地址
					listobj.add(new BizTransUtil("update bq_t_usefuladdress set bq_st_ismr='1' where bq_st_id=? ", new Object[]{id}, CommonUtil.UPDATE));
					//地址添加
					if(baseBiz.executesTRANS(listobj)){
						jsonData = "{\"success\":\"true\",\"msg\":\"设置成功\"}";
					}else{
						jsonData = "{\"success\":\"false\",\"flag\":\"4\",\"msg\":\"网络异常，请重试\"}";
					}
				} else {
					throw new MyoneException("未获取该手机用户信息");
				}
			} else {
				jsonData = "{\"success\":\"false\",\"flag\":\"2\",\"msg\":\"请先传入完整的数据信息！\"}";
			}
		} catch (MyoneException e) {
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
	 * 收货人地址列表查询接口
	 * @param json 
	 * @return
	 */
	@RequestMapping(value="getUsefulAddress")
	public void getUsefulAddress(String json,HttpServletResponse response){
		SafetyFilter.logger.info("传入JSON:" + json);
		String jsonData = "";
		try {
			Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据//解析数据
			String loginflag = StringUtils.toStringByObject(maps.get("loginflag"),true); //登录标识
			//验证不能为空
			if (org.springframework.util.StringUtils.hasText(loginflag)) {
				//查询该手机用户ID
				List<Map<String, Object>> list=baseBiz.queryForList("select a.ae_st_id from ae_t_sysuser a where a.ae_st_loginflag=? ", new Object[]{loginflag});
				if(list!=null&&list.size()==1){
					//当前手机用户登录人ID
					String userId =StringUtils.toStringByObject(list.get(0).get("ae_st_id"));
					//该会员下面的所有的收货地址
					List<Map<String, Object>> uaList=baseBiz.queryForList("select bq_st_id,bq_st_ismr,bq_st_name,bq_st_phone,bq_st_sheng,bq_st_shi,bq_st_xian,bq_st_adress from bq_t_usefuladdress where bq_st_memberid=?", new Object[]{userId});
					jsonData = "{\"success\":\"true\",\"msg\":\"获取成功！\",\"uaList\":"+JsonUtils.getJsonDataFromCollection(uaList)+"}";
				} else {
					throw new MyoneException("未获取该手机用户信息");
				}
			} else {
				jsonData = "{\"success\":\"false\",\"flag\":\"2\",\"msg\":\"请先传入完整的数据信息！\"}";
			}
		} catch (MyoneException e) {
			jsonData = "{\"success\":\"false\",\"flag\":\"3\",\"msg\":\"登录标识已经失效，请重新登录！\"}";
			e.printStackTrace();
		}catch (Exception e) {
			jsonData = "{\"success\":\"false\",\"flag\":\"1\",\"msg\":\"解析数据异常！\"}";
			e.printStackTrace();
		}
		SafetyFilter.logger.info("传出JSON:" + jsonData);
		this.createAjax(response,jsonData);
	} 
	/***
	 * 删除收货人地址
	 * @param json
	 * @param response
	 * @return
	 */
	@RequestMapping(value="delUsefulAddress")
	public void delUsefulAddress(String json,HttpServletResponse response){
		SafetyFilter.logger.info("传入JSON:" + json);
		String jsonData = "";
		try {
			Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据//解析数据
			String loginflag = StringUtils.toStringByObject(maps.get("loginflag"),true); //登录标识
			String id = StringUtils.toStringByObject(maps.get("id"),true);//收货主键ID
			//验证不能为空
			if (org.springframework.util.StringUtils.hasText(loginflag)&&org.springframework.util.StringUtils.hasText(id)) {
				//查询该手机用户ID
				List<Map<String, Object>> list=baseBiz.queryForList("select a.ae_st_id from ae_t_sysuser a where a.ae_st_loginflag=? ", new Object[]{loginflag});
				if(list!=null&&list.size()==1){
					List<BizTransUtil> listobj=new ArrayList<BizTransUtil>();
					//取消原来的默认地址
					listobj.add(new BizTransUtil(id, Bq_t_UsefulAddress.class));
					//执行
					if(baseBiz.executesTRANS(listobj)){
						jsonData = "{\"success\":\"true\",\"msg\":\"删除成功\"}";
					}else{
						jsonData = "{\"success\":\"false\",\"flag\":\"4\",\"msg\":\"网络异常，请重试\"}";
					}
				} else {
					throw new MyoneException("未获取该手机用户信息");
				}
			} else {
				jsonData = "{\"success\":\"false\",\"flag\":\"2\",\"msg\":\"请先传入完整的数据信息！\"}";
			}
		} catch (MyoneException e) {
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
	 * 查询当前APP版本是否最新
	 * @param json
	 * @return
	 */
	@RequestMapping(value="updversion")
	public void updversion(String json,HttpServletResponse response){
		
		SafetyFilter.logger.info("传入JSON:" + json);
		String jsonData = "";
		try {
			Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据//解析数据
			String version = StringUtils.toStringByObject(maps.get("version"),true);//集团ID
			//验证不能为空
			if (org.springframework.util.StringUtils.hasText(version)) {
				//查询当前版本号
				List<Map<String, Object>> list=baseBiz.queryForList("select ai_st_content,ai_st_remark from ai_t_sysset where ai_st_type = 'version'");
				if(list!=null&&list.size()==1){
					if(!version.equals(list.get(0).get("ai_st_content"))){
						jsonData = "{\"success\":\"false\",\"flag\":\"4\",\"msg\":\"有新版本！\",\"version\":\""+list.get(0).get("ai_st_content")+"\",\"loadurl\":\""+list.get(0).get("ai_st_remark")+"\"}";
					} else {
						jsonData = "{\"success\":\"true\",\"msg\":\"当前版本为最新版本\"}";
					}
				} else {
					jsonData = "{\"success\":\"false\",\"flag\":\"3\",\"msg\":\"获取版本信息失败\"}";
				}
			} else {
				jsonData = "{\"success\":\"false\",\"flag\":\"2\",\"msg\":\"请先传入完整的数据信息！\"}";
			}
		} catch (Exception e) {
			jsonData = "{\"success\":\"false\",\"flag\":\"1\",\"msg\":\"解析数据异常！\"}";
			e.printStackTrace();
		}
		SafetyFilter.logger.info("传出JSON:" + jsonData);
		this.createAjax(response,jsonData);
	}
	/**通过账户获取用户信息
	 */
	@RequestMapping(value="getUserByName")
	public void getUserByName(String json){
	
	     String jsonData = "";
	    Encoder e=new MD5Encoder();//使用security3 的MD5加密技术
		try {
		Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据
		String username = StringUtils.toStringByObject(maps.get("username"),true);//帐号/邮箱/手机号
		String telcode = StringUtils.toStringByObject(maps.get("telcode"),true); //验证码
		String tCode=String.valueOf(request.getSession().getAttribute("phoneCode")).toLowerCase();
		if(org.springframework.util.StringUtils.hasText(username)&&org.springframework.util.StringUtils.hasText(telcode)){
			if(telcode.equals(tCode)){
				List<Map<String, Object>> list=baseBiz.queryForList("select a.ae_st_id from ae_t_sysuser a where a.username="+username+" ");
				if(list!=null&&list.size()>0){
					jsonData = "{\"success\":\"true\",\"id\":\""+list.get(0).get("ae_st_id")+"\",\"msg\":\"获取成功！\"}";
				}else{
					jsonData = "{\"success\":\"false\",\"id\":\"\",\"msg\":\"用户不存在！\"}";
				}
			}else{
				jsonData = "{\"success\":\"false\",\"id\":\"\",\"msg\":\"验证码不正确！\"}";
			}
		}
		
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			jsonData = "{\"success\":\"false\",\"id\":\"\",\"msg\":\"解析异常！\"}";
			e1.printStackTrace();
		}
	    
		SafetyFilter.logger.info("传出JSON:" + jsonData);
		this.createAjax(response,jsonData);
	}
	/**
	 * 忘记密码 修改密码
	 * @param json
	 * @return
	 */
	@RequestMapping(value="updatePassword")
	public void updatePassword(String json,HttpServletResponse response,HttpServletRequest request){
		SafetyFilter.logger.info("传入JSON:" + json);
		String jsonData = "";
		Encoder e=new MD5Encoder();//使用security3 的MD5加密技术
		try {
			Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据
			String username = StringUtils.toStringByObject(maps.get("username"),true);//帐号/邮箱/手机号
			String telcode = StringUtils.toStringByObject(maps.get("telcode"),true); //验证码
			String password = StringUtils.toStringByObject(maps.get("password"),true);//传入新密码

		    String tCode=String.valueOf(request.getSession().getAttribute("phoneCode")).toLowerCase();
		    if(org.springframework.util.StringUtils.hasText(username)&&org.springframework.util.StringUtils.hasText(telcode)&&org.springframework.util.StringUtils.hasText(password)&&telcode.equals(tCode)){
		    	List<Map<String, Object>> list=baseBiz.queryForList("select  ae_st_intro,  ae_st_shshi , ae_st_sex  , ae_nm_age ,  ae_st_id,ae_st_lockstate,ae_st_name ,ae_st_tell ,(select ag_st_url from ag_t_file where ag_st_objid = ae_st_id and ag_st_mark='userfile' limit 0,1 ) ag_st_url from ae_t_sysuser   where username="+username+" ");
		    	if(list!=null&&list.size()>0){
		    		//如果存在登录标识,则返回以前登录标识
					String loginflag=StringUtils.getUUID32();//生成登录标识
					//修改登录标识
					baseBiz.executeTRANS(" update ae_t_sysuser set ae_st_lastlogonIp=?,ae_dt_updDate=?,ae_st_loginflag =? ,password =?  where ae_st_id =? ",CommonUtil.overshot(request),getcuttDate(), loginflag,e.encrypt(password, username),list.get(0).get("ae_st_id"));
		    		jsonData = "{\"success\":\"true\",\"msg\":\"修改成功！\",\"loginflag\":\""+loginflag+"\",\"result\":"+JsonUtils.getJsonDataFromCollection(list)+"}";
				}else{
					jsonData = "{\"success\":\"false\",\"id\":\"\",\"msg\":\"用户不存在！\"}";
				}
		    	
		    }
			
		}catch (Exception e1) {
			jsonData = "{\"success\":\"false\",\"flag\":\"3\",\"msg\":\"解析数据异常！\"}";
		}
		SafetyFilter.logger.info("传出JSON:" + jsonData);
		this.createAjax(response,jsonData);
	}
	
	/**
	 * 删除订单
	 * @param json
	 * @return
	 */
	@RequestMapping(value="deleteOrder")
	public void deleteOrder(String json,HttpServletResponse response,HttpServletRequest request){
		SafetyFilter.logger.info("传入JSON:" + json);
		String jsonData = "";
		try {
			Map maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据
			String id =StringUtils.toStringByObject(maps.get("bh_st_id"),true);//传入的订单号对应的ID
			String orderNo = StringUtils.toStringByObject(maps.get("bh_st_ddnum"),true);//传入的订单号
			System.out.println(orderNo);
			String loginflag = StringUtils.toStringByObject(maps.get("loginflag"),true); //登录标识
			//String  id="select bh_st_id from bh_t_orderform  where bh_st_ddnum='"+orderNo+"'";
			//new BizTransUtil("delete from bh_t_orderform where bh_st_id=?", new Object[]{id},CommonUtil.DELETE);    //删除本条数据
			boolean a = baseBiz.deleteTRANS(id, Bh_t_orderform.class);
			if(a=true)                           //删除数据
			{
				jsonData = "{\"success\":\"true\",\"flag\":\"3\",\"msg\":\"删除成功！\"}";
			}
			else{
				jsonData = "{\"success\":\"false\",\"msg\":\"删除失败！\",\"loginflag\"}";
			}
		}catch(Exception e1)
		{
			jsonData = "{\"success\":\"false\",\"flag\":\"1\",\"msg\":\"数据11111111111异常!\"}";
		}
		SafetyFilter.logger.info("传出JSON:" + jsonData);
		this.createAjax(response,jsonData);
   }
}
