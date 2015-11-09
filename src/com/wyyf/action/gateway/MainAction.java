package com.wyyf.action.gateway;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipaySubmit;
import com.lys.mvc.action.BaseAjaxAction;
import com.lys.mvc.biz.BaseBiz;
import com.lys.ping.PingCharge;
import com.lys.security.encryption.Encoder;
import com.lys.security.encryption.impl.MD5Encoder;
import com.lys.utils.BizTransUtil;
import com.lys.utils.CommonUtil;
import com.lys.utils.JsonUtils;
import com.lys.utils.StringUtils;
import com.lys.utils.UploadFile;
import com.lys.utils.pagination.PageBean;
import com.pingplusplus.model.Charge;
import com.power.bean.Ae_t_sysuser;
import com.power.bean.Af_t_sysuserrole;
import com.power.bean.Ag_t_file;
import com.power.utils.DateUtils;
import com.wyyf.action.InterfaceServiceAction;
import com.wyyf.action.dx.HttpSender;
import com.wyyf.bean.Ba_t_Labour;
import com.wyyf.bean.Bb_t_ShopUser;
import com.wyyf.bean.Be_t_Assess;
import com.wyyf.bean.Bf_t_Apply;
import com.wyyf.bean.Bh_t_orderform;
import com.wyyf.bean.Bi_t_OrderProdRelate;
import com.wyyf.bean.Bn_t_prodcart;

/***
 * 门户网站入口
 * 
 * @author Administrator
 */
@Scope(value = "prototype")
@Controller("MainAction")
@RequestMapping(value = "/index")
public class MainAction extends BaseAjaxAction {
	public static final Logger logger = Logger.getLogger(MainAction.class);
	/***
	 * 进入商城首页
	 * @param req
	 * @return
	 */
	@RequestMapping
	public String index() {
		getTopFootInfo();
		String cityCode=request.getParameter("cityCode");
		String CurrentCityCode = this.session.getAttribute("CurrentCityCode").toString();
		if(cityCode==null||cityCode.trim().equals("")){
			cityCode=CurrentCityCode;
		}
		Map<String, Object> CurrentArea=baseBiz.queryForMap("SELECT * FROM aa26 WHERE d_code='"+cityCode+"'");
		// 广告公用查询SQL
		String sql = "select b.bj_st_id,b.bj_st_clickurl, b.bj_st_enable,b.bj_st_remark,b.bj_st_title,b.bj_st_type ,b.bj_nm_orderno,a.ag_st_url,a.ag_st_id  from bj_t_advertisement b left JOIN  ag_t_file a on (b.bj_st_id = a.ag_st_objid and a.ag_st_objtype='bj_t_advertisement' and a.ag_st_mark='adTile') ";
		// 首页top大广告
		
		List<Map<String, Object>> list1 = baseBiz.queryForList(sql + " where bj_st_type='1' and bj_st_enable='1' order by  bj_nm_orderno desc,bj_dt_updDate desc limit 0,6 ");
		List<Map<String, Object>> list11 = new ArrayList<Map<String, Object>>();
		/*for (int i = 0; i < list1.size(); i++) {
	    	   String bj_st_clickurl=(String) list1.get(i).get("bj_st_clickurl");
	    	   JSONObject jsonObject = JSONObject.fromObject(bj_st_clickurl);
	    	   String url= jsonObject.getString("url");
	    	   Map<String, Object> map=new HashMap<String,Object>();
	    	   map.put("url", url);
	    	   list11.add(map);
			}
		list1.addAll(list11);*/
		model.addAttribute("main_centerlist1", list1);

		// 已经报名的人数
		Integer num = baseBiz.queryForInt("select count(bf_st_id) from bf_t_apply where bf_st_type='14'");
		String nums = "0000" + num;
		nums = nums.substring(nums.length() - 4);
		model.addAttribute("main_baomingnum", nums.toCharArray());

		// 验房师傅
		String sql22 = "select ae_st_name,ae_st_id,(select COUNT(1) from  bf_t_apply where bf_st_receiveid= ae_st_id) jdnum,(case when ba_st_grade<2 then 0 when 2<=ba_st_grade<4 then 1 when 4<=ba_st_grade<6 then 2 when 6<=ba_st_grade<8 then 3 else 4 end) ba_st_grade,(select ag_st_url from ag_t_file where ag_st_objid = ae_st_id) ag_st_url from ae_t_sysuser ,ba_t_labour where ae_st_id=ba_st_userid and ae_st_type='4' and ba_st_type='1'";
		if(CurrentArea.get("d_level").toString().equals("1")){
			sql22=sql22+" and ae_st_shsheng ='"+cityCode +"' ";
		}else if(CurrentArea.get("d_level").toString().equals("2")){
			sql22=sql22+" and ae_st_shshi ='"+cityCode +"' ";
			
		}else if(CurrentArea.get("d_level").toString().equals("3")){
			sql22=sql22+" and B.ae_st_shxian ='"+cityCode +"' ";
		}
		model.addAttribute("yfsf", baseBiz.queryForList(sql22));

		// 工长--抽取6个工长--根据添加时间倒序，推荐一下新进工长
		String sql2 = "select ae_st_name,ae_st_id,ba_st_grade,(select COUNT(1) from  bf_t_apply where bf_st_receiveid= ae_st_id) jdnum,(case when ba_st_grade<2 then 0 when 2<=ba_st_grade<4 then 1 when 4<=ba_st_grade<6 then 2 when 6<=ba_st_grade<8 then 3 else 4 end) ba_st_grade,(select ag_st_url from ag_t_file where ag_st_objid = ae_st_id) ag_st_url from ae_t_sysuser ,ba_t_labour where ae_st_lockstate != 3 and ae_st_id=ba_st_userid and ae_st_type='6' ";
		if(CurrentArea.get("d_level").toString().equals("1")){
			sql2=sql2+" and ae_st_shsheng ='"+cityCode +"' ";
		}else if(CurrentArea.get("d_level").toString().equals("2")){
			sql2=sql2+" and ae_st_shshi ='"+cityCode +"' ";
			
		}else if(CurrentArea.get("d_level").toString().equals("3")){
			sql2=sql2+" and B.ae_st_shxian ='"+cityCode +"' ";
			
		}
		sql2=sql2+" order by ae_dt_addDate desc limit 0,6 ";
		model.addAttribute("main_centerlist2", baseBiz.queryForList(sql2));

		// 师傅--抽取6个师傅--根据添加时间倒序，推荐一下新进师傅
		String sql3 = "select ae_st_name,ae_st_id,ba_st_grade,(select COUNT(1) from  bf_t_apply where bf_st_receiveid= ae_st_id) jdnum,(case when ba_st_grade<2 then 0 when 2<=ba_st_grade<4 then 1 when 4<=ba_st_grade<6 then 2 when 6<=ba_st_grade<8 then 3 else 4 end) ba_st_grade,(select ag_st_url from ag_t_file where ag_st_objid = ae_st_id) ag_st_url from ae_t_sysuser ,ba_t_labour where ba_st_type in(7,8,9,10,11,12) and ae_st_id=ba_st_userid and ae_st_type='4'  ";
		if(CurrentArea.get("d_level").toString().equals("1")){
			sql3=sql3+" and ae_st_shsheng ='"+cityCode +"' ";
		}else if(CurrentArea.get("d_level").toString().equals("2")){
			sql3=sql3+" and ae_st_shshi ='"+cityCode +"' ";
			
		}else if(CurrentArea.get("d_level").toString().equals("3")){
			sql3=sql3+" and B.ae_st_shxian ='"+cityCode +"' ";
			
		}
		sql3=sql3+"  order by ae_dt_addDate desc limit 0,6";
		
		model.addAttribute("main_centerlist3", baseBiz.queryForList(sql3));

		// 经典案例
		List<Map<String, Object>> jdal = baseBiz.queryForList("SELECT a.ag_st_url FROM bd_t_case b,ag_t_file a WHERE a.ag_st_objid=b.bd_st_id AND b.bd_st_name='经典案例'");
		model.addAttribute("jdal", jdal);

		// 买家具--抽取4个家具商品--根据添加时间倒叙,推荐新商品
		String sql5 = "SELECT b.bg_st_id,b.bg_st_name,b.bg_st_img1 FROM bg_t_prodinfo b WHERE b.bg_st_randid=1 and b.bg_st_isdel='0' ORDER BY b.bg_dt_addDate DESC LIMIT 0,4";
		model.addAttribute("mjj", baseBiz.queryForList(sql5));

		// 买建材--抽取4个商品--根据添加时间倒叙,推荐新商品
		String sql6 = "SELECT b.bg_st_id,b.bg_st_name,b.bg_st_img1 FROM bg_t_prodinfo b WHERE b.bg_st_randid=2 and b.bg_st_isdel='0' ORDER BY b.bg_dt_addDate DESC LIMIT 0,4";
		model.addAttribute("mjc", baseBiz.queryForList(sql6));

		// 首页中部大广告2
		List<Map<String, Object>> list5 = baseBiz.queryForList(sql + " where bj_st_type='6' and bj_st_enable='1' order by  bj_nm_orderno desc,bj_dt_updDate desc limit 0,6 ");
	
		model.addAttribute("main_centerlist5", list5);
		// 品牌特卖
		List<Map<String, Object>> list51 = baseBiz.queryForList(sql + " where bj_st_type='8' and bj_st_enable='1' order by  bj_nm_orderno desc,bj_dt_updDate desc limit 0,1 ");
		model.addAttribute("main_centerlist51", list51);
		// 热门话题--图片
		List<Map<String, Object>> list6 = baseBiz.queryForList(sql + " where bj_st_type='7' and bj_st_enable='1' order by  bj_nm_orderno desc,bj_dt_updDate desc limit 0,1 ");
		model.addAttribute("main_centerlist6", list6);
		// 热门话题（1.热门话题2.米奇帮3.装修日记4.装修经验5.活动回顾）--内容
		List<Map<String, Object>> list7 = baseBiz.queryForList("select bl_st_id,bl_st_title,bl_st_type from bl_t_noticecontext where bl_st_type in('1','2','3','4','5') and bl_st_isSend='1' ");
		model.addAttribute("main_centerlist7", list7);
		// 品牌商家
		List<Map<String, Object>> list8 = baseBiz.queryForList(" select ae_st_header,ae_st_id,(select count(*) from be_t_assess where be_st_jbgid=ae_st_id) pjnum from  ae_t_sysuser where ae_st_type='3' order by pjnum desc limit 0,12 ");
		model.addAttribute("main_centerlist8", list8);

		// 我要特价(正在抢购)
		List<Map<String, Object>> zzqg = baseBiz.queryForList("SELECT  now() nowTime , bg.bg_st_id,bg.bg_st_img1,bo.bo_dt_startDate,bg.bg_st_name,bg.bg_st_pricetj,bo.bo_dt_endDate,(SELECT count(1) FROM bh_t_orderform bh,bi_t_orderprodrelate bi WHERE bi.bi_st_ddnum=bh.bh_st_ddnum AND bi.bi_st_spnum=bg.bg_st_num) num FROM bg_t_prodinfo bg LEFT JOIN bp_t_activityprodrelate bp ON (bg.bg_st_num=bp.bp_st_spnum) LEFT JOIN bo_t_activity bo ON (bo.bo_st_ddnum=bp.bp_st_ddnum) WHERE bo.bo_st_title='正在抢购'");
		
		model.addAttribute("zzqg", zzqg);
		// 我要特价(明日预告)
		List<Map<String, Object>> mryg = baseBiz.queryForList("SELECT bg.bg_st_id,bg.bg_st_img1,bo.bo_dt_startDate,bg.bg_st_name,bg.bg_st_pricetj,bo.bo_dt_endDate,(SELECT count(1) FROM bh_t_orderform bh,bi_t_orderprodrelate bi WHERE bi.bi_st_ddnum=bh.bh_st_ddnum AND bi.bi_st_spnum=bg.bg_st_num) num FROM bg_t_prodinfo bg LEFT JOIN bp_t_activityprodrelate bp ON (bg.bg_st_num=bp.bp_st_spnum) LEFT JOIN bo_t_activity bo ON (bo.bo_st_ddnum=bp.bp_st_ddnum) WHERE bg.bg_st_fbtpe='1'and bo.bo_st_title='明日预告'");
		model.addAttribute("mryg", mryg);
		// 首页底部大广告(pc验房)
		List<Map<String, Object>> list4 = baseBiz.queryForList(sql + " where bj_st_type='5' and bj_st_enable='1' order by  bj_nm_orderno desc,bj_dt_updDate desc limit 0,6 ");
		model.addAttribute("main_centerlist4", list4);

		// 免费验房统计
		Map<String, Object> mfyf = baseBiz.queryForMap("SELECT COUNT(1) num FROM bf_t_apply WHERE bf_st_type='2'");
		model.addAttribute("mfyf", mfyf);

		// 金牌验房统计
		Map<String, Object> jpyf = baseBiz.queryForMap("SELECT COUNT(1) num FROM bf_t_apply WHERE bf_st_type='3'");
		model.addAttribute("jpyf", jpyf);
		//查询首页视频
		String videoSql="select * from br_t_video order by br_st_addDate desc";
		List<Map<String, Object>>  videos=baseBiz.queryForList(videoSql);
		if(videos.size()>0){
			model.addAttribute("videoMap", videos.get(0));
		}else {
			model.addAttribute("videoMap", "");
		}
		
		return "wyyf/index/index";
	}

	/**
	 * PC端-公共预约
	 * 
	 * @param bf
	 */
	@RequestMapping(value = "addApply", method = RequestMethod.POST)
	public void addApply(Bf_t_Apply bf) throws Exception {
		String content = null;
		Ae_t_sysuser ae_t_sysuser = getTopFootInfo();
		String type = request.getParameter("type");
		if(!"2".equals(type)){
			if (null == ae_t_sysuser || null == ae_t_sysuser.getAe_st_id() || "" == ae_t_sysuser.getAe_st_id()) {
				this.createAjax(response, "1");
			}
		}
		
		String rid = request.getParameter("rid");// 接收人id
		// String sid=request.getParameter("sid");//申请人id
		if (bf.getBf_st_tell() == null || "".equals(bf.getBf_st_tell())) {
			this.createAjax(response, "5");
			throw new RuntimeException("电话号码不能为空");
		}
		// 判断报名人数
		/*
		 * if(StringUtils.toIntegerByObject(type)==2&&StringUtils.toIntegerByObject
		 * (request.getParameter("bf_st_housnum"))<0){ this.createAjax(response,
		 * "2"); throw new RuntimeException("报名人数不足5人"); }
		 */
		try {
			List<BizTransUtil> list = new ArrayList<BizTransUtil>();
			// 修改预约
			if (org.springframework.util.StringUtils.hasText(bf.getBf_st_id())) {
			}
			// 添加预约
			else {
				bf.setBf_st_id(StringUtils.getUUID32());// 主键ID
				try {
					bf.setBf_st_addUserId(getcuttSysuserID());// 创建人员ID
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				bf.setBf_dt_addDate(getcuttDate());// 创建时间
				bf.setBf_st_receiveid(rid);// 赴约人ID
				bf.setBf_st_userid(ae_t_sysuser.getAe_st_id());// 申请人ID
				bf.setBf_st_type(type);// 申请类型
				list.add(new BizTransUtil(bf));
			}
			// 执行操作
			if (baseBiz.executesTRANS(list)) {
				this.createAjax(response, "0");
				String uri = "http://222.73.117.158/msg/";// 应用地址
				String account = "wangzhong";// 账号
				String pswd = "Tch147258";// 密码
				String mobiles = bf.getBf_st_tell();// 手机号码，多个号码使用","分割

				if ("1".equals(type)) {
					content = "恭喜您成功申请了网众科技旗下平台的一键入住,我们会在24小时内和您联系，请注意接听电话,网众科技客服电话:400-028-5998,如有疑问可以添加我要验房QQ群123582777";
				} else if ("2".equals(type)) {
					content = "恭喜您成功申请了网众科技旗下平台的免费验房,我们会在24小时内和您联系，请注意接听电话,网众科技客服电话:400-028-5998,如有疑问可以添加我要验房QQ群123582777";
				} else if ("3".equals(type)) {
					content = "恭喜您成功申请了网众科技旗下平台的金牌验房,我们会在24小时内和您联系，请注意接听电话,网众科技客服电话:400-028-5998,如有疑问可以添加我要验房QQ群123582777";
				} else if ("4".equals(type)) {
					content = "恭喜您成功申请了网众科技旗下平台的免费设计,我们会在24小时内和您联系，请注意接听电话,网众科技客服电话:400-028-5998,如有疑问可以添加我要验房QQ群123582777";
				} else if ("5".equals(type)) {
					content = "恭喜您成功申请了网众科技旗下平台的个性设计,我们会在24小时内和您联系，请注意接听电话,网众科技客服电话:400-028-5998,如有疑问可以添加我要验房QQ群123582777";
				}
				else if ("7".equals(type)) {
					content = "恭喜您成功预约了网众科技旗下平台工长,我们会在24小时内和您联系，请注意接听电话,网众科技客服电话:400-028-5998,如有疑问可以添加我要验房QQ群123582777";
				} else if ("8".equals(type)) {
					content = "恭喜您成功预约了网众科技旗下平台师傅,我们会在24小时内和您联系，请注意接听电话,网众科技客服电话:400-028-5998,如有疑问可以添加我要验房QQ群123582777";
				} else if ("9".equals(type)) {
					content = "恭喜您成功预约了网众科技旗下平台免费监理,我们会在24小时内和您联系，请注意接听电话,网众科技客服电话:400-028-5998,如有疑问可以添加我要验房QQ群123582777";
				} else if ("10".equals(type)) {
					content = "恭喜您成功预约了网众科技旗下平台收费监理,我们会在24小时内和您联系，请注意接听电话,网众科技客服电话:400-028-5998,如有疑问可以添加我要验房QQ群123582777";
				} else if ("12".equals(type)) {
					content = "恭喜您成功预约了网众科技旗下平台免费空气检测,我们会在24小时内和您联系，请注意接听电话,网众科技客服电话:400-028-5998,如有疑问可以添加我要验房QQ群123582777";
				} else if ("13".equals(type)) {
					content = "恭喜您成功预约了网众科技旗下平台收费空气检测我们会在24小时内和您联系，请注意接听电话,网众科技客服电话:400-028-5998,如有疑问可以添加我要验房QQ群123582777";
				}
				boolean needstatus = true;// 是否需要状态报告，需要true，不需要false
				String product = null;// 产品ID
				String extno = null;// 扩展码
				String returnString = null;
				try {
					returnString = HttpSender.batchSend(uri, account, pswd, mobiles, content, needstatus, product, extno);
					System.out.println(returnString);
					// TODO 处理返回值,参见HTTP协议文档
				} catch (Exception e) {
					// TODO 处理异常
					e.printStackTrace();
				}
			} else {
				this.createAjax(response, "1");
			}
		} catch (Exception e) {
			this.createAjax(response, "1");
		}
	}
	/**
	 * PC端-预约工长可免费监理和免费甲醛检测
	 * @param bf
	 */
	@RequestMapping(value = "addApplyGZ", method = RequestMethod.POST)
	public String addApplyGZ() throws Exception {
		getTopFootInfo();
		String content = null;
		Ae_t_sysuser ae_t_sysuser = getTopFootInfo();
		if (null == ae_t_sysuser || null == ae_t_sysuser.getAe_st_id() || "" == ae_t_sysuser.getAe_st_id()) {
			this.createAjax(response, "1");
		}
		String addr = request.getParameter("addr");//地址
		String area = request.getParameter("area");//面积
		Double price = 100.0;//价格(预约价格固定为100.0)
		
		model.addAttribute("addr", addr);
		model.addAttribute("area", area);
		model.addAttribute("price", price);
		
		System.out.println(request.getParameter("mfjl"));;
		System.out.println(request.getParameter("jcjq"));;
		
		String mfjl = request.getParameter("mfjl");
		String jcjq = request.getParameter("jcjq");
		
		if(mfjl != null){
			try {
				List<BizTransUtil> list = new ArrayList<BizTransUtil>();
				Bf_t_Apply bf = new Bf_t_Apply();
				// 添加预约
				bf.setBf_st_id(StringUtils.getUUID32());// 主键ID
				bf.setBf_st_addUserId(getcuttSysuserID());// 创建人员ID
				bf.setBf_dt_addDate(getcuttDate());// 创建时间
				bf.setBf_st_userid(ae_t_sysuser.getAe_st_id());// 申请人ID
				bf.setBf_st_tell(ae_t_sysuser.getAe_st_tell());//电话
				bf.setBf_st_type("9");// 申请类型
				list.add(new BizTransUtil(bf));
				// 执行操作
				
				if (baseBiz.executesTRANS(list)) {
					String uri = "http://222.73.117.158/msg/";// 应用地址
					String account = "wangzhong";// 账号
					String pswd = "Tch147258";// 密码
					String mobiles = bf.getBf_st_tell();// 手机号码，多个号码使用","分割
					
					content = "恭喜您成功预约了网众科技旗下平台免费监理,我们会在24小时内和您联系，请注意接听电话";
					
					// "恭喜您成功预约了网众科技旗下平台收费空气检测我们会在24小时内和您联系，请注意接听电话";//短信内容
					boolean needstatus = true;// 是否需要状态报告，需要true，不需要false
					String product = null;// 产品ID
					String extno = null;// 扩展码
					String returnString = null;
					try {
						returnString = HttpSender.batchSend(uri, account, pswd, mobiles, content, needstatus, product, extno);
						System.out.println(returnString);
						// TODO 处理返回值,参见HTTP协议文档
					} catch (Exception e) {
						// TODO 处理异常
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				this.createAjax(response, "1");
			}
		}
		if(jcjq != null){
			try {
				List<BizTransUtil> list = new ArrayList<BizTransUtil>();
				Bf_t_Apply bf = new Bf_t_Apply();
				// 添加预约
				bf.setBf_st_id(StringUtils.getUUID32());// 主键ID
				bf.setBf_st_addUserId(getcuttSysuserID());// 创建人员ID
				bf.setBf_dt_addDate(getcuttDate());// 创建时间
				bf.setBf_st_userid(ae_t_sysuser.getAe_st_id());// 申请人ID
				bf.setBf_st_tell(ae_t_sysuser.getAe_st_tell());//电话
				bf.setBf_st_type("12");// 申请类型
				list.add(new BizTransUtil(bf));
				// 执行操作
				if (baseBiz.executesTRANS(list)) {
					
					String uri = "http://222.73.117.158/msg/";// 应用地址
					String account = "wangzhong";// 账号
					String pswd = "Tch147258";// 密码
					String mobiles = bf.getBf_st_tell();// 手机号码，多个号码使用","分割
					
					content = "恭喜您成功预约了网众科技旗下平台免费检测甲醛,我们会在24小时内和您联系，请注意接听电话";
					
					// "恭喜您成功预约了网众科技旗下平台收费空气检测我们会在24小时内和您联系，请注意接听电话";//短信内容
					boolean needstatus = true;// 是否需要状态报告，需要true，不需要false
					String product = null;// 产品ID
					String extno = null;// 扩展码
					String returnString = null;
					try {
						returnString = HttpSender.batchSend(uri, account, pswd, mobiles, content, needstatus, product, extno);
						System.out.println(returnString);
						// TODO 处理返回值,参见HTTP协议文档
					} catch (Exception e) {
						// TODO 处理异常
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				this.createAjax(response, "1");
			}
		}
		
		String Orderid = GetUniqueId("bh_t_orderform", "bh_st_id");
		String OrderNum = GetOrderNum();
		// 生成订单
		Bh_t_orderform order = new Bh_t_orderform();
		order.setBh_st_id(Orderid);
//		order.setBh_st_bbid(rid);免费预约，赴约人为网众平台
		order.setBh_st_ddnum(OrderNum);
		order.setBh_st_spprice(price);
		order.setBh_st_ddstate(1 + "");
		order.setBh_st_source(1 + "");
		order.setBh_st_memberId(ae_t_sysuser.getAe_st_id());
		order.setBh_dt_addDate(getcuttDate());
		baseBiz.addTRANS(order);
		model.addAttribute("orderId", Orderid);
		model.addAttribute("price", price);
		model.addAttribute("area", area);
		model.addAttribute("addr", addr);
		
		return "wyyf/index/topay";
	}
	/**
	 * PC端-首页免费验房
	 * 
	 * @param bf
	 */
	@RequestMapping(value = "addApplyMf"/*, method = RequestMethod.POST*/)
	public String addApplyMf() throws Exception {
		String name=request.getParameter("bf_st_name"); //姓名
		String tell=request.getParameter("bf_st_tell"); //电话
		String address=request.getParameter("bf_st_address");//地址
		Map<String, Object> mfyf = baseBiz.queryForMap("SELECT COUNT(1) num FROM bf_t_apply WHERE bf_st_type='2'");
		model.addAttribute("mfyf", mfyf);
	
		model.addAttribute("name",name);
		model.addAttribute("tell",tell);
		model.addAttribute("address",address);
		return "wyyf/index/checkfree";
	}
	/**
	 * PC端-首页金牌验房
	 * 
	 * @param bf
	 */
	@RequestMapping(value = "addApplyjp"/*, method = RequestMethod.POST*/)

	public String addApplyjp() throws Exception {
		getTopFootInfo();
		String ae_st_id =request.getParameter("ae_st_id");//预约人ID
		String name=request.getParameter("bf_st_name"); //姓名
		String tell=request.getParameter("bf_st_tell"); //电话
		String address=request.getParameter("bf_st_address");//地址
		String bf_st_area =request.getParameter("bf_st_area");//面积
		
		String sid = request.getParameter("id");// 获取登陆者ID
		/*
		 * if(sid==null||"".equals(sid)){ return "wyyf/index/loginp"; }
		 */
		// 金牌验房统计
		Map<String, Object> jpyf = baseBiz.queryForMap("SELECT COUNT(1) num FROM bf_t_apply WHERE bf_st_type='3'");
		model.addAttribute("jpyf", jpyf);
		model.addAttribute("sid", sid);
		
		model.addAttribute("ae_st_id",ae_st_id);
		model.addAttribute("name",name);
		model.addAttribute("tell",tell);
		model.addAttribute("address",address);
		model.addAttribute("bf_st_area",bf_st_area);
		return "wyyf/index/checkgold";
	}

	// ---------------------PC端统一预约入口--------------------------------------------
	@RequestMapping(value = "toApply")
	public String checkfreeEnd(Bf_t_Apply bf_t_Apply) {
		getTopFootInfo();
		String type = request.getParameter("type");
		if (StringUtils.toIntegerByObject(type) == 2 && StringUtils.toIntegerByObject(bf_t_Apply.getBf_st_housnum()) >= 5) {// 免费验房,判断报名人数
			// 创建批量保存事务对象
			List<BizTransUtil> list = new ArrayList<BizTransUtil>();
			bf_t_Apply.setBf_st_id(StringUtils.getUUID32());
			bf_t_Apply.setBf_st_type(type);
			list.add(new BizTransUtil(bf_t_Apply));
			if (baseBiz.executesTRANS(list)) {
				this.createAjax(response, "0");
			} else {
				this.createAjax(response, "1");
			}
			return null;
		} else if (StringUtils.toIntegerByObject(type) == 2 && StringUtils.toIntegerByObject(bf_t_Apply.getBf_st_housnum()) < 5) {
			this.createAjax(response, "2");
			return null;
		} else if (StringUtils.toIntegerByObject(type) == 88) {// 个人中心,个人信息的保存
			// 个人信息保存...
			return "wyyf/index/me/myHomePage";
		} else {// 其他预约
			List<BizTransUtil> list = new ArrayList<BizTransUtil>();
			list.add(new BizTransUtil(bf_t_Apply));
			bf_t_Apply.setBf_st_id(StringUtils.getUUID32());
			bf_t_Apply.setBf_st_type(type);
			baseBiz.executesTRANS(list);
			if (baseBiz.executesTRANS(list)) {
				this.createAjax(response, "0");
				return null;
			} else {
				this.createAjax(response, "1");
				return null;
			}
		}
	}

	// -------------------我要验房-----------------------------------------------
	/***
	 * 免费验房
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "checkfree")
	public String checkfree() {
		/*getTopFootInfo();
		String sid = request.getParameter("id");// 登陆者ID
		if (sid == null || "".equals(sid)) {
			return "wyyf/index/loginp";
		}*/
		// 免费验房统计
		Map<String, Object> mfyf = baseBiz.queryForMap("SELECT COUNT(1) num FROM bf_t_apply WHERE bf_st_type='2'");
		model.addAttribute("mfyf", mfyf);
	/*	model.addAttribute("sid", sid);*/

		return "wyyf/index/checkfree";
	}

	/***
	 * 进入金牌验房
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "checkgold")
	public String checkgold() {
		getTopFootInfo();

		String sid = request.getParameter("id");// 获取登陆者ID
		/*
		 * if(sid==null||"".equals(sid)){ return "wyyf/index/loginp"; }
		 */
		// 金牌验房统计
		Map<String, Object> jpyf = baseBiz.queryForMap("SELECT COUNT(1) num FROM bf_t_apply WHERE bf_st_type='3'");
		model.addAttribute("jpyf", jpyf);
		model.addAttribute("sid", sid);
		return "wyyf/index/checkgold";
	}

	// 提交预约
	@RequestMapping(value = "checkgoldEnd")
	public void checkgoldEnd(Bf_t_Apply bf_t_Apply) {

		// 创建批量保存事务对象
		List<BizTransUtil> list = new ArrayList<BizTransUtil>();
		bf_t_Apply.setBf_st_id(StringUtils.getUUID32());
		bf_t_Apply.setBf_st_type("3");
		list.add(new BizTransUtil(bf_t_Apply));
		baseBiz.executesTRANS(list);
	}
	
	
	// -------------------我要装修-----------------------------------------------
	/**
	 * 找工长
	 * 
	 * @return
	 */
	@RequestMapping(value = "overman")
	public String overman() {
		getTopFootInfo();
		String sid = request.getParameter("id");// 登陆者ID
		model.addAttribute("sid", sid);
		// 城市代码
		String cityCode = request.getParameter("cityCode");
		// 页数
		Integer page = StringUtils.toIntegerByObject(request.getParameter("page"));
		if (page == null || page <= 0) {
			page = Integer.valueOf(1);
		}
		// 类型

		String CurrentCityCode = this.session.getAttribute("CurrentCityCode").toString();
		if (cityCode == null || cityCode.trim().equals("")) {
			cityCode = CurrentCityCode;
		}
		Map<String, Object> CurrentCityInfo = baseBiz.queryForMap("SELECT * FROM aa26 WHERE d_code='" + CurrentCityCode + "'");
		// 区域选择
		String sql2 = "select b.* from aa26 a LEFT JOIN aa26 b ON (a.d_code=b.d_parent)";
		List<Map<String, Object>> list2 = baseBiz.queryForList(sql2 + " where a.d_code='" + CurrentCityCode + "'");
		model.addAttribute("district_list", list2);
		Map<String, Object> CurrentArea=baseBiz.queryForMap("SELECT * FROM aa26 WHERE d_code='"+cityCode+"'");
		StringBuilder sql=new StringBuilder("SELECT B.ae_st_name,D.ag_st_url, C.COUNT, A.* FROM ba_t_labour A LEFT JOIN ae_t_sysuser B ON B.ae_st_id = A.ba_st_userid LEFT JOIN ( SELECT B.ae_st_id, COUNT(*) COUNT FROM ae_t_sysuser B LEFT JOIN bf_t_apply C ON C.bf_st_receiveid = B.ae_st_id GROUP BY B.ae_st_id ) C ON C.ae_st_id = A.ba_st_id LEFT JOIN ag_t_file D on B.ae_st_id=D.ag_st_objid  WHERE A.ba_st_type IN (SELECT ab_st_value from ab_t_code WHERE ab_st_parent ='18da19f17a22429e9ec0deae0f45b9ef' )  and B.ae_st_type = '6'");
		StringBuilder totalSql=new StringBuilder("SELECT  COUNT(1) FROM ba_t_labour A LEFT JOIN ae_t_sysuser B ON B.ae_st_id = A.ba_st_userid LEFT JOIN ( SELECT B.ae_st_id, COUNT(*) COUNT FROM ae_t_sysuser B LEFT JOIN bf_t_apply C ON C.bf_st_receiveid = B.ae_st_id GROUP BY B.ae_st_id ) C ON C.ae_st_id = A.ba_st_id LEFT JOIN ag_t_file D on B.ae_st_id=D.ag_st_objid WHERE A.ba_st_type IN (SELECT ab_st_value from ab_t_code WHERE ab_st_parent ='18da19f17a22429e9ec0deae0f45b9ef' ) and B.ae_st_type = '6'");
		if(CurrentArea.get("d_level").toString().equals("1")){
			sql.append(" AND B.ae_st_shsheng ='"+cityCode +"' ");
			totalSql.append(" AND B.ae_st_shsheng ='"+cityCode +"'");
		}else if(CurrentArea.get("d_level").toString().equals("2")){
			sql.append(" AND B.ae_st_shshi ='"+cityCode +"' ");
			totalSql.append(" AND B.ae_st_shshi ='"+cityCode +"' ");
		}else if(CurrentArea.get("d_level").toString().equals("3")){
			sql.append(" AND B.ae_st_shxian ='"+cityCode +"' ");
			totalSql.append(" AND B.ae_st_shxian ='"+cityCode +"' ");
		}

		String zxType = request.getParameter("zxType");
		if (zxType != null && !zxType.equals("") && !zxType.equals("0")) {
			sql.append(" and 	A.ba_st_type='" + zxType + "'");
		} else {
			zxType = "0";
		}
		// 排序类型
		String OrderByType = request.getParameter("OrderByType");
		if (OrderByType == null || OrderByType.equals("")) {
			OrderByType = "0";
		}

		String Up = request.getParameter("Up");
		if (Up == null || Up.equals("")) {
			Up = "0";
		}
		if (OrderByType.equals("0")) {
			if (Up.equals("0")) {
				sql.append(" ORDER BY A.ba_st_grade DESC,C.COUNT DESC ,A.ba_st_price DESC ");
			} else {
				sql.append(" ORDER BY A.ba_st_grade ASC,C.COUNT ASC ,A.ba_st_price ASC ");
			}
		} else if (OrderByType.equals("1")) {
			if (Up.equals("0")) {
				sql.append(" ORDER BY A.ba_st_grade DESC ");
			} else {
				sql.append(" ORDER BY A.ba_st_grade ASC ");
			}
		} else if (OrderByType.equals("2")) {
			if (Up.equals("0")) {
				sql.append(" ORDER BY C.COUNT DESC ");
			} else {
				sql.append(" ORDER BY C.COUNT ASC ");
			}
		} else if (OrderByType.equals("3")) {
			if (Up.equals("0")) {
				sql.append(" ORDER BY A.ba_st_price DESC ");
			} else {
				sql.append(" ORDER BY A.ba_st_price ASC");
			}
		}
		PageBean pages = baseBiz.getPages(sql.toString(), totalSql.toString(), page, 16);
		int LabourTotal = baseBiz.queryForInt(totalSql.toString());

		model.addAttribute("zxType", zxType);
		model.addAttribute("CurrentCityCode", CurrentCityCode);
		model.addAttribute("cityCode", cityCode);
		model.addAttribute("OrderByType", OrderByType);
		model.addAttribute("Up", Up);
		model.addAttribute("page", page);
		model.addAttribute("LabourTotal", LabourTotal);
		model.addAttribute("su_supervisionlist", pages);

		// 查询创工长对应的子类型
		String sql1 = "SELECT * FROM ab_t_code where ab_st_parent='18da19f17a22429e9ec0deae0f45b9ef'";
		List<Map<String, Object>> childTypes = baseBiz.queryForList(sql1);
		model.addAttribute("childs", childTypes);
		return "wyyf/index/overman";
	}

	/**
	 * 找验房师
	 * 
	 * @return
	 */
	@RequestMapping(value = "Verify")
	public String Verify() {
		getTopFootInfo();
		String sid = request.getParameter("id");// 登陆者ID
		String id = request.getParameter("id");

		model.addAttribute("sid", sid);
		// 城市代码
		String cityCode = request.getParameter("cityCode");
		// 页数
		Integer page = StringUtils.toIntegerByObject(request.getParameter("page"));
		if (page == null || page <= 0) {
			page = Integer.valueOf(1);
		}
		// 类型

		String CurrentCityCode = this.session.getAttribute("CurrentCityCode").toString();
		if (cityCode == null || cityCode.trim().equals("")) {
			cityCode = CurrentCityCode;
		}
		Map<String, Object> CurrentCityInfo = baseBiz.queryForMap("SELECT * FROM aa26 WHERE d_code='" + CurrentCityCode + "'");
		// 区域选择
		String sql2 = "select b.* from aa26 a LEFT JOIN aa26 b ON (a.d_code=b.d_parent)";
		List<Map<String, Object>> list2 = baseBiz.queryForList(sql2 + " where a.d_code='" + CurrentCityCode + "'");
		model.addAttribute("district_list", list2);
		Map<String, Object> CurrentArea = baseBiz.queryForMap("SELECT * FROM aa26 WHERE d_code='" + cityCode + "'");
		StringBuilder sql = new StringBuilder("SELECT D.ag_st_url,B.ae_st_name, C.COUNT, A.* FROM ba_t_labour A LEFT JOIN ae_t_sysuser B ON B.ae_st_id = A.ba_st_userid LEFT JOIN ( SELECT B.ae_st_id, COUNT(*) COUNT FROM ae_t_sysuser B LEFT JOIN bf_t_apply C ON C.bf_st_receiveid = B.ae_st_id GROUP BY B.ae_st_id ) C ON C.ae_st_id = A.ba_st_id LEFT JOIN ag_t_file D on D.ag_st_objid=A.ba_st_userid WHERE A.ba_st_type ='1' and B.ae_st_type='4'");
		StringBuilder totalSql = new StringBuilder("SELECT COUNT(1) FROM ba_t_labour A LEFT JOIN ae_t_sysuser B ON B.ae_st_id = A.ba_st_userid LEFT JOIN ( SELECT B.ae_st_id, COUNT(*) COUNT FROM ae_t_sysuser B LEFT JOIN bf_t_apply C ON C.bf_st_receiveid = B.ae_st_id GROUP BY B.ae_st_id ) C ON C.ae_st_id = A.ba_st_id LEFT JOIN ag_t_file D on D.ag_st_objid=A.ba_st_userid WHERE A.ba_st_type='1' and B.ae_st_type='4'");
		if (CurrentArea.get("d_level").toString().equals("1")) {
			sql.append(" AND B.ae_st_shsheng ='" + cityCode + "' ");
		} else if (CurrentArea.get("d_level").toString().equals("2")) {
			sql.append(" AND B.ae_st_shshi ='" + cityCode + "' ");
		} else if (CurrentArea.get("d_level").toString().equals("3")) {
			sql.append(" AND B.ae_st_shxian ='" + cityCode + "' ");
		}

		// 排序类型
		String OrderByType = request.getParameter("OrderByType");
		if (OrderByType == null || OrderByType.equals("")) {
			OrderByType = "0";
		}

		String Up = request.getParameter("Up");
		if (Up == null || Up.equals("")) {
			Up = "0";
		}
		if (OrderByType.equals("0")) {
			if (Up.equals("0")) {
				sql.append(" ORDER BY A.ba_st_grade DESC,C.COUNT DESC ,A.ba_st_price DESC ");
			} else {
				sql.append(" ORDER BY A.ba_st_grade ASC,C.COUNT ASC ,A.ba_st_price ASC ");
			}
		} else if (OrderByType.equals("1")) {
			if (Up.equals("0")) {
				sql.append(" ORDER BY A.ba_st_grade DESC ");
			} else {
				sql.append(" ORDER BY A.ba_st_grade ASC ");
			}
		} else if (OrderByType.equals("2")) {
			if (Up.equals("0")) {
				sql.append(" ORDER BY C.COUNT DESC ");
			} else {
				sql.append(" ORDER BY C.COUNT ASC ");
			}
		} else if (OrderByType.equals("3")) {
			if (Up.equals("0")) {
				sql.append(" ORDER BY A.ba_st_price DESC ");
			} else {
				sql.append(" ORDER BY A.ba_st_price ASC");
			}
		}
		PageBean pages = baseBiz.getPages(sql.toString(), totalSql.toString(), page, 16);
		int LabourTotal = baseBiz.queryForInt(totalSql.toString());

		model.addAttribute("CurrentCityCode", CurrentCityCode);
		model.addAttribute("cityCode", cityCode);
		model.addAttribute("OrderByType", OrderByType);
		model.addAttribute("Up", Up);
		model.addAttribute("page", page);
		model.addAttribute("LabourTotal", LabourTotal);
		model.addAttribute("su_supervisionlist", pages);// 分页对象
		/* 验房师所有详情页面 */
		// return "wyyf/index/Verify";
		return "wyyf/index/Verify";
	}

	/**
	 * 验房师详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "checkManDetail")
	public String checkManDetail() {
		getTopFootInfo();
		String id = request.getParameter("id");// 对应工长的ID
		String sid = request.getParameter("sid");
		model.addAttribute("sid", sid);
		model.addAttribute("rid", id);
		// 工长信息
		String sql1 = "select ae_st_name,ae_st_id,a.ba_st_price,(select COUNT(1) from  bf_t_apply where bf_st_receiveid= ae_st_id) jdnum,(case when ba_st_grade<2 then 0 when 2<=ba_st_grade<4 then 1 when 4<=ba_st_grade<6 then 2 when 6<=ba_st_grade<8 then 3 else 4 end) ba_st_grade,(select ag_st_url from ag_t_file where ag_st_objid = ae_st_id) ag_st_url,ba_st_teamnum,ba_dt_addDate,ba_st_team_intro from ae_t_sysuser ,ba_t_labour a";
		List<Map<String, Object>> list1 = baseBiz.queryForList(sql1 + " where ae_st_id=ba_st_userid and ae_st_type='4' and ba_st_type='1'  AND ae_st_id='" + id + "'");
		model.addAttribute("ov_checkManDetaillist1", list1);
		// 评价信息
		String sql2 = "SELECT b.ae_st_name,be_st_content,be_dt_addDate FROM ae_t_sysuser a,be_t_assess,ae_t_sysuser b WHERE be_st_jbgid=a.ae_st_id AND be_st_fbgid=b.ae_st_id";
		List<Map<String, Object>> list2 = baseBiz.queryForList(sql2 + " AND a.ae_st_id='" + id + "'");
		model.addAttribute("ov_checkManDetaillist2", list2);
		// 案例信息
		String sql3 = "SELECT ag_st_url,bd_st_name,bd_st_money,bd_st_area,bd_st_remark FROM bd_t_case,ae_t_sysuser,ag_t_file WHERE bd_st_bbid=ae_st_id AND ag_st_objid=bd_st_id";
		List<Map<String, Object>> list3 = baseBiz.queryForList(sql3 + "  AND ae_st_id='" + id + "'");
		model.addAttribute("ov_checkManDetaillist3", list3);
		return "wyyf/index/checkManDetail";
	}

	/**
	 * 预约验房师
	 * 
	 * @return
	 */
	@RequestMapping(value = "checkManOrder")
	public String checkManOrder() {
		getTopFootInfo();
		String rid = request.getParameter("rid");// 赴约人ID
		String sid = request.getParameter("sid");// 申请人ID
		if (sid == null || "".equals(sid)) {
			return "wyyf/index/loginp";
		}
		model.addAttribute("rid", rid);
		model.addAttribute("sid", sid);
		return "wyyf/index/checkManOrder";
	}

	@RequestMapping(value = "searchOverman", method = RequestMethod.POST)
	public String searchOverman() {
		getTopFootInfo();
		String sid = request.getParameter("id");// 登陆者ID
		String areaCode = request.getParameter("areaCode");
		String zxType = request.getParameter("zxType");
		String orderType = request.getParameter("orderType");
		model.addAttribute("orderType", orderType);
		model.addAttribute("areaCode", areaCode);
		model.addAttribute("zxType", zxType);
		model.addAttribute("sid", sid);
		// 区域查询
		String sql2 = "select b.d_name,b.d_code from aa26 a LEFT JOIN aa26 b ON (a.d_code=b.d_parent)";
		List<Map<String, Object>> list2 = baseBiz.queryForList(sql2 + " where a.d_code=510100");
		model.addAttribute("district_list", list2);
		String sql = "select ae_st_name,ae_st_id,(select COUNT(1) from  bf_t_apply where bf_st_receiveid= ae_st_id) jdnum,(case when ba_st_grade<2 then 0 when 2<=ba_st_grade<4 then 1 when 4<=ba_st_grade<6 then 2 when 6<=ba_st_grade<8 then 3 else 4 end) ba_st_grade,(select ag_st_url from ag_t_file where ag_st_objid = ae_st_id) ag_st_url,ba_st_teamnum from ae_t_sysuser ,ba_t_labour";
		sql = sql + " where ae_st_id=ba_st_userid and ae_st_type='6'";
		if (zxType != null && !zxType.equals("") && !zxType.equals("all")) {
			sql = sql + "  and ba_st_type='" + zxType + "'";
		}
		if (areaCode != null && !areaCode.equals("") && !areaCode.equals("all")) {
			sql = sql + "  and ae_st_shxian='" + areaCode + "'";
		}
		if (orderType != null && !orderType.equals("")) {
			if (orderType.equals("normal")) {

			} else if (orderType.equals("judge")) {
				sql = sql + "  order by ba_st_grade desc ";
			} else if (orderType.equals("count")) {
				sql = sql + "  order by jdnum desc ";
			}
		}
		List<Map<String, Object>> list = baseBiz.queryForList(sql);
		model.addAttribute("ov_overmanlist", list);
		return "wyyf/index/overman";
	}

	/**
	 * 工长详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "overmanDetail")
	public String overmanDetail() {
		getTopFootInfo();
		String id = request.getParameter("id");// 对应工长的ID
		String sid = request.getParameter("sid");
		model.addAttribute("sid", sid);
		model.addAttribute("rid", id);
		// 工长信息
		String sql1 = "select ae_st_name,ae_st_id,ba_st_price,(select COUNT(1) from  bf_t_apply where bf_st_receiveid= ae_st_id) jdnum,(case when ba_st_grade<2 then 0 when 2<=ba_st_grade<4 then 1 when 4<=ba_st_grade<6 then 2 when 6<=ba_st_grade<8 then 3 else 4 end) ba_st_grade,(select ag_st_url from ag_t_file where ag_st_objid = ae_st_id) ag_st_url,ba_st_teamnum,date_format(ba_dt_addDate ,'%Y-%m-%d')ba_dt_addDate,ba_st_team_intro from ae_t_sysuser ,ba_t_labour a";
		List<Map<String, Object>> list1 = baseBiz.queryForList(sql1 + " where ae_st_id=ba_st_userid and ae_st_type='6' AND ae_st_id='" + id + "'");
		model.addAttribute("ov_overmanDetaillist1", list1);
		// 评价信息
		String sql2 = "SELECT b.ae_st_name,be_st_content,be_dt_addDate FROM ae_t_sysuser a,be_t_assess,ae_t_sysuser b WHERE be_st_jbgid=a.ae_st_id AND be_st_fbgid=b.ae_st_id";
		List<Map<String, Object>> list2 = baseBiz.queryForList(sql2 + " AND a.ae_st_id='" + id + "'");
		model.addAttribute("ov_overmanDetaillist2", list2);
		// 案例信息
		String sql3 = "SELECT ag_st_url,bd_st_name,bd_st_money,bd_st_area,bd_st_remark FROM bd_t_case,ae_t_sysuser,ag_t_file WHERE bd_st_bbid=ae_st_id AND ag_st_objid=bd_st_id";
		List<Map<String, Object>> list3 = baseBiz.queryForList(sql3 + "  AND ae_st_id='" + id + "'");
		model.addAttribute("ov_overmanDetaillist3", list3);
		return "wyyf/index/overmanDetail";
	}

	/**
	 * 预约工长
	 * 
	 * @return
	 */
	@RequestMapping(value = "overmanOrder")
	public String overmanOrder() {
		getTopFootInfo();
		String rid = request.getParameter("rid");// 赴约人ID
		String sid = request.getParameter("sid");// 申请人ID
		if (sid == null || "".equals(sid)) {
			return "wyyf/index/loginp";
		}
		model.addAttribute("rid", rid);
		model.addAttribute("sid", sid);
		return "wyyf/index/overmanOrder";
	}

	/**
	 * 找师傅
	 * 
	 * @return
	 */
	@RequestMapping(value = "master")
	public String fixturemaster() {
		getTopFootInfo();
		String sid = request.getParameter("id");// 登陆者ID
		model.addAttribute("sid", sid);
		// 城市代码
		String cityCode = request.getParameter("cityCode");
		// 页数
		Integer page = StringUtils.toIntegerByObject(request.getParameter("page"));
		if (page == null || page <= 0) {
			page = Integer.valueOf(1);
		}
		// 类型

		String CurrentCityCode = this.session.getAttribute("CurrentCityCode").toString();
		if (cityCode == null || cityCode.trim().equals("")) {
			cityCode = CurrentCityCode;
		}
		Map<String, Object> CurrentCityInfo = baseBiz.queryForMap("SELECT * FROM aa26 WHERE d_code='" + CurrentCityCode + "'");
		// 区域选择
		String sql2 = "select b.* from aa26 a LEFT JOIN aa26 b ON (a.d_code=b.d_parent)";
		List<Map<String, Object>> list2 = baseBiz.queryForList(sql2 + " where a.d_code='" + CurrentCityCode + "'");
		model.addAttribute("district_list", list2);
		Map<String, Object> CurrentArea=baseBiz.queryForMap("SELECT * FROM aa26 WHERE d_code='"+cityCode+"'");
		//StringBuilder sql=new StringBuilder("SELECT B.ae_st_name,D.ag_st_url, C.COUNT, A.* FROM ba_t_labour A LEFT JOIN ae_t_sysuser B ON B.ae_st_id = A.ba_st_userid LEFT JOIN ( SELECT COUNT(1) AS 'COUNT', C.bf_st_receiveid FROM bf_t_apply C LEFT JOIN ae_t_sysuser B ON B.ae_st_id = C.bf_st_receiveid ) C ON C.bf_st_receiveid = B.ae_st_id  LEFT JOIN ag_t_file D on B.ae_st_id=D.ag_st_objid WHERE A.ba_st_type IN ( SELECT ab_st_value FROM ab_t_code WHERE ab_st_parent = 'ccbb497536cd4335a17a9032b70f47a7' AND ab_st_value <> '1' ) AND B.ae_st_type = '4' ");
		StringBuilder sql=new StringBuilder("SELECT B.ae_st_name,D.ag_st_url, C.COUNT, A.* FROM ba_t_labour A LEFT JOIN ae_t_sysuser B ON B.ae_st_id = A.ba_st_userid LEFT JOIN ( SELECT COUNT(1) AS 'COUNT', C.bf_st_receiveid FROM bf_t_apply C LEFT JOIN ae_t_sysuser B ON B.ae_st_id = C.bf_st_receiveid ) C ON C.bf_st_receiveid = B.ae_st_id  LEFT JOIN ag_t_file D on B.ae_st_id=D.ag_st_objid WHERE A.ba_st_type IN ( 7 ,8,9,10,11,12 ) AND B.ae_st_type = '4' ");
		//StringBuilder totalSql=new StringBuilder("SELECT COUNT(1) FROM ba_t_labour A LEFT JOIN ae_t_sysuser B ON B.ae_st_id = A.ba_st_userid LEFT JOIN ( SELECT COUNT(1) AS 'COUNT', C.bf_st_receiveid FROM bf_t_apply C LEFT JOIN ae_t_sysuser B ON B.ae_st_id = C.bf_st_receiveid  ) C ON  C.bf_st_receiveid = B.ae_st_id  LEFT JOIN ag_t_file D on B.ae_st_id=D.ag_st_objid WHERE A.ba_st_type IN (SELECT ab_st_value  from ab_t_code WHERE ab_st_parent ='ccbb497536cd4335a17a9032b70f47a7' and ab_st_value<>'1' )  AND B.ae_st_type = '4'");
		StringBuilder totalSql=new StringBuilder("SELECT COUNT(1) FROM ba_t_labour A LEFT JOIN ae_t_sysuser B ON B.ae_st_id = A.ba_st_userid LEFT JOIN ( SELECT COUNT(1) AS 'COUNT', C.bf_st_receiveid FROM bf_t_apply C LEFT JOIN ae_t_sysuser B ON B.ae_st_id = C.bf_st_receiveid  ) C ON  C.bf_st_receiveid = B.ae_st_id  LEFT JOIN ag_t_file D on B.ae_st_id=D.ag_st_objid WHERE A.ba_st_type IN (7 ,8,9,10,11,12 )  AND B.ae_st_type = '4'");
		if(CurrentArea.get("d_level").toString().equals("1")){
			sql.append(" AND B.ae_st_shsheng ='"+cityCode +"' ");
			totalSql.append(" AND B.ae_st_shsheng ='"+cityCode +"' ");
		}else if(CurrentArea.get("d_level").toString().equals("2")){
			sql.append(" AND B.ae_st_shshi ='"+cityCode +"' ");
			totalSql.append(" AND B.ae_st_shshi ='"+cityCode +"' ");
		}else if(CurrentArea.get("d_level").toString().equals("3")){
			sql.append(" AND B.ae_st_shxian ='"+cityCode +"' ");
			totalSql.append(" AND B.ae_st_shxian ='"+cityCode +"' ");
		}

		String zxType = request.getParameter("zxType");
		if (zxType != null && !zxType.equals("") && !zxType.equals("0")) {
			sql.append(" and 	A.ba_st_type='" + zxType + "'");
		} else {
			zxType = "0";
		}
		// 排序类型
		String OrderByType = request.getParameter("OrderByType");
		if (OrderByType == null || OrderByType.equals("")) {
			OrderByType = "0";
		}

		String Up = request.getParameter("Up");
		if (Up == null || Up.equals("")) {
			Up = "0";
		}
		if (OrderByType.equals("0")) {
			if (Up.equals("0")) {
				sql.append(" ORDER BY A.ba_st_grade DESC,C.COUNT DESC ,A.ba_st_price DESC ");
			} else {
				sql.append(" ORDER BY A.ba_st_grade ASC,C.COUNT ASC ,A.ba_st_price ASC ");
			}
		} else if (OrderByType.equals("1")) {
			if (Up.equals("0")) {
				sql.append(" ORDER BY A.ba_st_grade DESC ");
			} else {
				sql.append(" ORDER BY A.ba_st_grade ASC ");
			}
		} else if (OrderByType.equals("2")) {
			if (Up.equals("0")) {
				sql.append(" ORDER BY C.COUNT DESC ");
			} else {
				sql.append(" ORDER BY C.COUNT ASC ");
			}
		} else if (OrderByType.equals("3")) {
			if (Up.equals("0")) {
				sql.append(" ORDER BY A.ba_st_price DESC ");
			} else {
				sql.append(" ORDER BY A.ba_st_price ASC");
			}
		}
		PageBean pages = baseBiz.getPages(sql.toString(), totalSql.toString(), page, 16);
		int LabourTotal = baseBiz.queryForInt(totalSql.toString());
		String sql3 ="SELECT * FROM ab_t_code where ab_st_parent='ccbb497536cd4335a17a9032b70f47a7'";
		List<Map<String, Object>> childTypes=baseBiz.queryForList(sql3);
		model.addAttribute("sf",childTypes);
		model.addAttribute("zxType", zxType);
		model.addAttribute("CurrentCityCode", CurrentCityCode);
		model.addAttribute("cityCode", cityCode);
		model.addAttribute("OrderByType", OrderByType);
		model.addAttribute("Up", Up);
		model.addAttribute("page", page);
		model.addAttribute("LabourTotal", LabourTotal);
		model.addAttribute("su_supervisionlist", pages);// 分页对象
		return "wyyf/index/master";
	}

	@RequestMapping(value = "searchMaster")
	public String searchMaster() {
		getTopFootInfo();
		String sid = request.getParameter("id");// 登陆者ID
		String areaCode = request.getParameter("areaCode");
		String zxType = request.getParameter("zxType");
		String orderType = request.getParameter("orderType");
		model.addAttribute("orderType", orderType);
		model.addAttribute("areaCode", areaCode);
		model.addAttribute("zxType", zxType);
		model.addAttribute("sid", sid);
		// 区域查询
		String sql2 = "select b.d_name from aa26 a LEFT JOIN aa26 b ON (a.d_code=b.d_parent)";
		List<Map<String, Object>> list2 = baseBiz.queryForList(sql2 + " where a.d_code=510100");
		model.addAttribute("district_list", list2);
		String sql = "select ae_st_name,ae_st_id,(select COUNT(1) from  bf_t_apply where bf_st_receiveid= ae_st_id) jdnum,(case when ba_st_grade<2 then 0 when 2<=ba_st_grade<4 then 1 when 4<=ba_st_grade<6 then 2 when 6<=ba_st_grade<8 then 3 else 4 end) ba_st_grade,(select ag_st_url from ag_t_file where ag_st_objid = ae_st_id) ag_st_url,ba_st_teamnum,ba_st_type from ae_t_sysuser ,ba_t_labour";
		sql = sql + " where ae_st_id=ba_st_userid and (ae_st_type='5' or ae_st_type='4')";

		if (zxType != null && !zxType.equals("") && !zxType.equals("all")) {
			sql = sql + "  and ba_st_type='" + zxType + "'";
		}
		if (areaCode != null && !areaCode.equals("") && !areaCode.equals("all")) {
			sql = sql + "  and ae_st_shxian='" + areaCode + "'";
		}
		if (orderType != null && !orderType.equals("")) {
			if (orderType.equals("normal")) {

			} else if (orderType.equals("judge")) {
				sql = sql + "  order by ba_st_grade desc ";
			} else if (orderType.equals("count")) {
				sql = sql + "  order by jdnum desc ";
			}
		}

		List<Map<String, Object>> list = baseBiz.queryForList(sql);
		model.addAttribute("ma_masterlist", list);
		return "wyyf/index/master";
	}

	/**
	 * 师傅详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "masterDetail")
	public String fixdetail() {
		getTopFootInfo();
		String id = request.getParameter("id");// 师傅ID(赴约人)
		String sid = request.getParameter("sid");// 申请人ID
		Ae_t_sysuser ae_t_sysuser = getTopFootInfo();
		if (null != ae_t_sysuser || null != ae_t_sysuser.getAe_st_id() || "" != ae_t_sysuser.getAe_st_id()) {
			if (sid == null || sid.equals("")) {
				sid = ae_t_sysuser.getAe_st_id();
			}
		}
		String type = request.getParameter("type");// 类型
		model.addAttribute("type", type);
		model.addAttribute("sid", sid);
		model.addAttribute("rid", id);
		// 师傅信息
		String masterInfo="SELECT ae_st_name, ae_st_id, a.ba_st_price, a.ba_st_type,"
				+ "  ( SELECT B.ab_st_name FROM ab_t_code B WHERE B.ab_st_value = a.ba_st_type AND B.ab_st_parent"
				+ "  = 'ccbb497536cd4335a17a9032b70f47a7' ) typeName, ( SELECT COUNT(1) FROM bf_t_apply WHERE bf_st_receiveid"
				+ " = ae_st_id ) jdnum, ( CASE WHEN ba_st_grade < 2 THEN 0 WHEN 2 <= ba_st_grade < 4 THEN 1 WHEN 4 <= ba_st_grade "
				+ "< 6 THEN 2 WHEN 6 <= ba_st_grade < 8 THEN 3 ELSE 4 END ) ba_st_grade, ( SELECT ag_st_url FROM ag_t_file "
				+ " WHERE ag_st_objid = ae_st_id ) ag_st_url, ba_st_teamnum, date_format(ba_dt_addDate, '%Y-%m-%d') ba_dt_addDate,"
				+ " ba_st_team_intro FROM ae_t_sysuser, ba_t_labour a WHERE ae_st_id = ba_st_userid AND (ae_st_type = '4') AND"
				+ " ae_st_id = '"+id+"'";
		List<Map<String, Object>> list1 = baseBiz.queryForList(masterInfo);
		model.addAttribute("ma_masterDetaillist1", list1);
		// 评价信息
		String sql2 = "SELECT b.ae_st_name,be_st_content,be_dt_addDate FROM ae_t_sysuser a,be_t_assess,ae_t_sysuser b WHERE be_st_jbgid=a.ae_st_id AND be_st_fbgid=b.ae_st_id";
		List<Map<String, Object>> list2 = baseBiz.queryForList(sql2 + " AND a.ae_st_id='" + id + "'");
		model.addAttribute("ma_masterDetaillist2", list2);
		// 案例信息
		String sql3 = "SELECT ag_st_url,bd_st_name,bd_st_money,bd_st_area,bd_st_remark FROM bd_t_case,ae_t_sysuser,ag_t_file WHERE bd_st_bbid=ae_st_id AND ag_st_objid=bd_st_id";
		List<Map<String, Object>> list3 = baseBiz.queryForList(sql3 + "  AND ae_st_id='" + id + "'");
		model.addAttribute("ma_masterDetaillist3", list3);
		return "wyyf/index/masterDetail";
	}

	/**
	 * 预约师傅
	 * 
	 * @return
	 */
	@RequestMapping(value = "masterOrder")
	public String masterOrder() {
		getTopFootInfo();
		String rid = request.getParameter("rid");
		model.addAttribute("rid", rid);// 赴约人ID
		String sid = request.getParameter("sid");
		model.addAttribute("sid", sid);// 申请人ID

		if (sid == null || "".equals(sid)) {
			return "wyyf/index/loginp";
		}

		return "wyyf/index/masterOrder";
	}

	// -------------------我要监理-----------------------------------------------
	
	@RequestMapping(value = "supervisionAjax")
	public void supervisionAjax() {
		String sid = request.getParameter("id");// 登陆者ID
		model.addAttribute("sid", sid);
		// 城市代码
		String cityCode = request.getParameter("cityCode");
		// 页数
		Integer page = StringUtils.toIntegerByObject(request.getParameter("page"));
		if (page == null || page <= 0) {
			page = Integer.valueOf(1);
		}
		// 类型
		String type = request.getParameter("type");
		if (type == null || type.trim().equals("")) {
			type = "jianli";
		}

		String ba_st_type = "13";
		if (type.equals("jianli")) {
			ba_st_type = "13";
		} else if (type.equals("jiance")) {
			ba_st_type = "14";
		} else {
			ba_st_type = "13";
		}

		String CurrentCityCode = this.session.getAttribute("CurrentCityCode").toString();
		if (cityCode == null || cityCode.trim().equals("")) {
			cityCode = CurrentCityCode;
		}

		Map<String, Object> CurrentArea = baseBiz.queryForMap("SELECT * FROM aa26 WHERE d_code='" + cityCode + "'");
		StringBuilder sql = new StringBuilder("SELECT   (select ag_st_url from ag_t_file where ag_st_objid = B.ae_st_id) ag_st_url,(select COUNT(1) from  bf_t_apply where bf_st_receiveid= B.ae_st_id ) COUNT,A.* FROM ba_t_labour A LEFT JOIN ae_t_sysuser B ON B.ae_st_id = A.ba_st_userid  WHERE A.ba_st_type='" + ba_st_type + "'");
		StringBuilder totalSql = new StringBuilder("SELECT Count(*) FROM ba_t_labour A ,ae_t_sysuser B where A.ba_st_userid=B.ae_st_id AND A.ba_st_type='" + ba_st_type + "'");
		if (CurrentArea.get("d_level").toString().equals("1")) {
			sql.append(" AND B.ae_st_shsheng ='" + cityCode + "' ");
		} else if (CurrentArea.get("d_level").toString().equals("2")) {
			sql.append(" AND B.ae_st_shshi ='" + cityCode + "' ");
		} else if (CurrentArea.get("d_level").toString().equals("3")) {
			sql.append(" AND B.ae_st_shxian ='" + cityCode + "' ");
		}

		// 排序类型
		String OrderByType = request.getParameter("OrderByType");
		if (OrderByType == null || OrderByType.equals("")) {
			OrderByType = "0";
		}

		String Up = request.getParameter("Up");
		if (Up == null || Up.equals("")) {
			Up = "0";
		}
		if (OrderByType.equals("0")) {
			if (Up.equals("0")) {
				sql.append(" ORDER BY A.ba_st_grade DESC,COUNT DESC ,A.ba_st_price DESC ");
			} else {
				sql.append(" ORDER BY A.ba_st_grade ASC,COUNT ASC ,A.ba_st_price ASC ");
			}
		} else if (OrderByType.equals("1")) {
			if (Up.equals("0")) {
				sql.append(" ORDER BY A.ba_st_grade DESC ");
			} else {
				sql.append(" ORDER BY A.ba_st_grade ASC ");
			}
		} else if (OrderByType.equals("2")) {
			if (Up.equals("0")) {
				sql.append(" ORDER BY COUNT DESC ");
			} else {
				sql.append(" ORDER BY COUNT ASC ");
			}
		} else if (OrderByType.equals("3")) {
			if (Up.equals("0")) {
				sql.append(" ORDER BY A.ba_st_price DESC ");
			} else {
				sql.append(" ORDER BY A.ba_st_price ASC");
			}
		}
		PageBean pages = baseBiz.getPages(sql.toString(), totalSql.toString(), page, 16);
		this.createAjax(response, JsonUtils.getJsonData(pages),"application/json");
	}
	/**
	 * 我要监理
	 * 
	 * @return
	 */
	@RequestMapping(value = "supervision")
	public String supervision() {
		getTopFootInfo();
		String sid = request.getParameter("id");// 登陆者ID
		model.addAttribute("sid", sid);
		// 城市代码
		String cityCode = request.getParameter("cityCode");
		// 页数
		Integer page = StringUtils.toIntegerByObject(request.getParameter("page"));
		if (page == null || page <= 0) {
			page = Integer.valueOf(1);
		}
		// 类型
		String type = request.getParameter("type");
		if (type == null || type.trim().equals("")) {
			type = "jianli";
		}

		String ba_st_type = "13";
		if (type.equals("jianli")) {
			ba_st_type = "13";
		} else if (type.equals("jiance")) {
			ba_st_type = "14";
		} else {
			ba_st_type = "13";
		}

		String CurrentCityCode = this.session.getAttribute("CurrentCityCode").toString();
		if (cityCode == null || cityCode.trim().equals("")) {
			cityCode = CurrentCityCode;
		}
		Map<String, Object> CurrentCityInfo = baseBiz.queryForMap("SELECT * FROM aa26 WHERE d_code='" + CurrentCityCode + "'");
		// 区域选择
		String sql2 = "select b.* from aa26 a LEFT JOIN aa26 b ON (a.d_code=b.d_parent)";
		List<Map<String, Object>> list2 = baseBiz.queryForList(sql2 + " where a.d_code='" + CurrentCityCode + "'");
		model.addAttribute("district_list", list2);
		Map<String, Object> CurrentArea = baseBiz.queryForMap("SELECT * FROM aa26 WHERE d_code='" + cityCode + "'");
		StringBuilder sql = new StringBuilder("SELECT   (select ag_st_url from ag_t_file where ag_st_objid = B.ae_st_id) ag_st_url,C.COUNT,A.* FROM ba_t_labour A LEFT JOIN ae_t_sysuser B ON B.ae_st_id = A.ba_st_userid LEFT JOIN (SELECT B.ae_st_id ,COUNT(*) COUNT FROM ae_t_sysuser B LEFT JOIN  bf_t_apply C ON C.bf_st_receiveid = B.ae_st_id  GROUP BY B.ae_st_id) C ON C.ae_st_id = A.ba_st_id WHERE A.ba_st_type='" + ba_st_type + "'");
		StringBuilder totalSql = new StringBuilder("SELECT Count(*) FROM ba_t_labour A ,ae_t_sysuser B where A.ba_st_userid=B.ae_st_id AND A.ba_st_type='" + ba_st_type + "'");
		if (CurrentArea.get("d_level").toString().equals("1")) {
			sql.append(" AND B.ae_st_shsheng ='" + cityCode + "' ");
		} else if (CurrentArea.get("d_level").toString().equals("2")) {
			sql.append(" AND B.ae_st_shshi ='" + cityCode + "' ");
		} else if (CurrentArea.get("d_level").toString().equals("3")) {
			sql.append(" AND B.ae_st_shxian ='" + cityCode + "' ");
		}

		// 排序类型
		String OrderByType = request.getParameter("OrderByType");
		if (OrderByType == null || OrderByType.equals("")) {
			OrderByType = "0";
		}

		String Up = request.getParameter("Up");
		if (Up == null || Up.equals("")) {
			Up = "0";
		}
		if (OrderByType.equals("0")) {
			if (Up.equals("0")) {
				sql.append(" ORDER BY A.ba_st_grade DESC,C.COUNT DESC ,A.ba_st_price DESC ");
			} else {
				sql.append(" ORDER BY A.ba_st_grade ASC,C.COUNT ASC ,A.ba_st_price ASC ");
			}
		} else if (OrderByType.equals("1")) {
			if (Up.equals("0")) {
				sql.append(" ORDER BY A.ba_st_grade DESC ");
			} else {
				sql.append(" ORDER BY A.ba_st_grade ASC ");
			}
		} else if (OrderByType.equals("2")) {
			if (Up.equals("0")) {
				sql.append(" ORDER BY C.COUNT DESC ");
			} else {
				sql.append(" ORDER BY C.COUNT ASC ");
			}
		} else if (OrderByType.equals("3")) {
			if (Up.equals("0")) {
				sql.append(" ORDER BY A.ba_st_price DESC ");
			} else {
				sql.append(" ORDER BY A.ba_st_price ASC");
			}
		}
		PageBean pages = baseBiz.getPages(sql.toString(), totalSql.toString(), page, 16);
		int LabourTotal = baseBiz.queryForInt(totalSql.toString());

		model.addAttribute("type", type);
		model.addAttribute("CurrentCityCode", CurrentCityCode);
		model.addAttribute("cityCode", cityCode);
		model.addAttribute("OrderByType", OrderByType);
		model.addAttribute("Up", Up);
		model.addAttribute("page", page);
		model.addAttribute("LabourTotal", LabourTotal);
		model.addAttribute("su_supervisionlist", pages);// 分页对象

		return "wyyf/index/supervisionAjax";
	}

	@RequestMapping(value = "changeCity")
	public String changeCity() {
		getTopFootInfo();
		return "wyyf/index/changeCity";
	}

	/**
	 * 监理师详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "supervisionDetail")
	public String supervisionDetail() {
		getTopFootInfo();
		String id = request.getParameter("id");
		String rid = request.getParameter("id");
		String sid = request.getParameter("sid");
		model.addAttribute("rid", rid);
		model.addAttribute("sid", sid);
		// 监督师信息
		String sql1 = "select ae_st_name,ae_st_id,(select COUNT(1) from  bf_t_apply where bf_st_receiveid= ae_st_id) jdnum,(case when ba_st_grade<2 then 0 when 2<=ba_st_grade<4 then 1 when 4<=ba_st_grade<6 then 2 when 6<=ba_st_grade<8 then 3 else 4 end) ba_st_grade,(select ag_st_url from ag_t_file where ag_st_objid = ae_st_id) ag_st_url,ba_st_teamnum,ba_dt_addDate,ba_st_team_intro,ba_st_remark from ae_t_sysuser ,ba_t_labour ";
		List<Map<String, Object>> list1 = baseBiz.queryForList(sql1 + "  where ae_st_id=ba_st_userid and ba_st_type='13' AND ae_st_id='" + id + "'");
		model.addAttribute("su_supervisionDetaillist1", list1);

		// 评价信息
		List<Map<String, Object>> list2 = baseBiz.queryForList("SELECT b.ae_st_name,be_st_content,be_dt_addDate FROM ae_t_sysuser a,be_t_assess,ae_t_sysuser b WHERE be_st_jbgid=a.ae_st_id AND be_st_fbgid=b.ae_st_id AND a.ae_st_id='" + id + "'");
		model.addAttribute("su_supervisionDetaillist2", list2);

		// 案例信息
		List<Map<String, Object>> list3 = baseBiz.queryForList("SELECT ag_st_url,bd_st_name,bd_st_money,bd_st_area,bd_st_remark FROM bd_t_case,ae_t_sysuser,ag_t_file WHERE bd_st_bbid=ae_st_id AND ag_st_objid=bd_st_id  AND ae_st_id='" + id + "'");
		model.addAttribute("su_supervisionDetaillist3", list3);

		return "wyyf/index/supervisionDetail";
	}

	/**
	 * 预约监理师
	 * 
	 * @return
	 */
	@RequestMapping(value = "supervisionOrder")
	public String supervisionOrder() {
		getTopFootInfo();
		String rid = request.getParameter("rid");
		String sid = request.getParameter("sid");
		model.addAttribute("rid", rid);
		model.addAttribute("sid", sid);
		if (sid == null || "".equals(sid)) {
			return "wyyf/index/loginp";
		}
		return "wyyf/index/supervisionOrder";
	}

	/**
	 * 我要检测
	 * 
	 * @return
	 */
	@RequestMapping(value = "aircharge")
	public String monitortoll() {
		getTopFootInfo();
		String sid = request.getParameter("id");// 登陆者ID
		model.addAttribute("sid", sid);
		// 区域选择
		String sql2 = "select b.d_name from aa26 a LEFT JOIN aa26 b ON (a.d_code=b.d_parent)";
		List<Map<String, Object>> list2 = baseBiz.queryForList(sql2 + " where a.d_code=510100");
		model.addAttribute("district_list", list2);
		// 检测师信息
		List<Map<String, Object>> list = baseBiz.queryForList("select ae_st_name,ae_st_id,(select COUNT(1) from  bf_t_apply where bf_st_receiveid= ae_st_id) jdnum,(case when ba_st_grade<2 then 0 when 2<=ba_st_grade<4 then 1 when 4<=ba_st_grade<6 then 2 when 6<=ba_st_grade<8 then 3 else 4 end) ba_st_grade,(select ag_st_url from ag_t_file where ag_st_objid = ae_st_id) ag_st_url,ba_st_price from ae_t_sysuser ,ba_t_labour where ae_st_id=ba_st_userid and ba_st_type='14'");
		model.addAttribute("mo_monitortolllist", list);
		return "wyyf/index/monitortoll";
	}

	/**
	 * 检测师详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "monitortollDetail")
	public String monitortollDetail() {
		getTopFootInfo();
		String id = request.getParameter("id");
		String rid = request.getParameter("id");// 赴约人ID
		String sid = request.getParameter("sid");// 申请人ID
		model.addAttribute("rid", rid);
		model.addAttribute("sid", sid);
		// 监督师信息
		String sql1 = "select ae_st_name,ae_st_id,(select COUNT(1) from  bf_t_apply where bf_st_receiveid= ae_st_id) jdnum,(case when ba_st_grade<2 then 0 when 2<=ba_st_grade<4 then 1 when 4<=ba_st_grade<6 then 2 when 6<=ba_st_grade<8 then 3 else 4 end) ba_st_grade,(select ag_st_url from ag_t_file where ag_st_objid = ae_st_id) ag_st_url,ba_st_teamnum,ba_dt_addDate,ba_st_team_intro,ba_st_remark from ae_t_sysuser ,ba_t_labour ";
		List<Map<String, Object>> list1 = baseBiz.queryForList(sql1 + "  where ae_st_id=ba_st_userid and ba_st_type='14' AND ae_st_id='" + id + "'");
		model.addAttribute("mo_monitortollDetaillist1", list1);

		// 评价信息
		List<Map<String, Object>> list2 = baseBiz.queryForList("SELECT b.ae_st_name,be_st_content,be_dt_addDate FROM ae_t_sysuser a,be_t_assess,ae_t_sysuser b WHERE be_st_jbgid=a.ae_st_id AND be_st_fbgid=b.ae_st_id AND a.ae_st_id='" + id + "'");
		model.addAttribute("mo_monitortollDetaillist2", list2);

		// 案例信息
		List<Map<String, Object>> list3 = baseBiz.queryForList("SELECT ag_st_url,bd_st_name,bd_st_money,bd_st_area,bd_st_remark FROM bd_t_case,ae_t_sysuser,ag_t_file WHERE bd_st_bbid=ae_st_id AND ag_st_objid=bd_st_id  AND ae_st_id='" + id + "'");
		model.addAttribute("mo_monitortollDetaillist3", list3);

		return "wyyf/index/monitortollDetail";

	}

	/**
	 * 预约检测师
	 * 
	 * @return
	 */
	@RequestMapping(value = "monitortollOrder")
	public String monitortollOrder() {
		getTopFootInfo();
		String rid = request.getParameter("rid");
		String sid = request.getParameter("sid");
		model.addAttribute("rid", rid);
		model.addAttribute("sid", sid);

		if (sid == null || "".equals(sid)) {
			return "wyyf/index/loginp";
		}

		return "wyyf/index/monitortollOrder";
	}

	// -------------------我的个人中心-----------------------------------------
	/**
	 * 我的个人信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "myzone")
	public String myzone() {
		return "index/me/myzone";
	}
    /*
     *普通用户保存修改个人信息 
	 */
	@RequestMapping(value = "saveUser")
	public String saveUser(Ae_t_sysuser ae_t_sysuser,Ag_t_file oldFile,MultipartHttpServletRequest multipartRequest)
	{
		String returnStr = "";
		//创建批量保存事务对象
		List<BizTransUtil> list=new ArrayList<BizTransUtil>();
		String id = ae_t_sysuser.getAe_st_id();
		//更新
		if (org.springframework.util.StringUtils.hasText(id)) {
			//构建数据--修改
			Map<String,Object>  map=new LinkedCaseInsensitiveMap<Object>();
			map.put("ae_st_id", id);//ID
			map.put("ae_st_updUserId", getcuttSysuserID());
			map.put("ae_dt_updDate", getcuttDate());
			map.put("ae_st_name", ae_t_sysuser.getAe_st_name());//姓名
			map.put("password", ae_t_sysuser.getPassword()); //密码
			map.put("ae_st_tell", ae_t_sysuser.getAe_st_tell());//电话
			map.put("ae_st_sex", ae_t_sysuser.getAe_st_sex());//性别
			map.put("ae_nm_age", ae_t_sysuser.getAe_nm_age());//年龄 
			map.put("ae_st_address", ae_t_sysuser.getAe_st_address());//详细地址
			map.put("ae_st_nickName", ae_t_sysuser.getAe_st_nickName());//用户昵称
			list.add(new BizTransUtil(map,Ae_t_sysuser.class,CommonUtil.UPDATE));
		   }
			// 执行操作
			if (baseBiz.executesTRANS(list)) 
			{
				model.addAttribute("msg", "修改成功!");
				returnStr = "redirect:/me/myHomePage";
			} else {
				model.addAttribute("msg", "修改失败!");
				returnStr = "wyyf/me/myHomePage";
			}
		return returnStr;
	}
	/**
	 * 我的个人中心
	 * 
	 * @return
	 */
	@RequestMapping(value = "me")
	public String me() {
		getTopFootInfo();
		StringBuffer sb = new StringBuffer("select a.d_code TREEID,'0' open,a.d_parent TREEPID,a.d_name TREENAME from aa26 a  ");
		String json = JsonUtils.getJsonDataFromCollection(baseBiz.queryForList(sb.toString()));
		model.addAttribute("groupTree", json);
		String paramStr = request.getParameter("param");
		if (paramStr == null || paramStr.equals("")) {
			paramStr = "";
		}

		model.addAttribute("paramStr", paramStr);
		String sid = request.getParameter("id");// 登陆者ID
		model.addAttribute("sid", sid);
		// 我的个人信息
		String sql1 = "SELECT ae_st_lockstate,username,mb,vouchers,ag_st_url,ae_st_id ,password,ae_st_name,ae_nm_age,ae_st_sex,ae_st_address,ae_st_shsheng," + "ae_st_shshi,ae_st_nickName,ae_st_tell,ae_st_type from ae_t_sysuser left join ag_t_file  on ag_st_objid=ae_st_id ";
		sql1 = sql1 + " where  ae_st_id='" + sid + "'";
		Map<String, Object> list1 = baseBiz.queryForMap(sql1);
		model.addAttribute("my_messagelist", list1);
		//申请成为商家
	
		
		//我的工长(未评价)
		String queryNotJudge="SELECT A.bf_st_id, A.bf_st_name, A.bf_st_receiveid, A.bf_st_userid, C.ae_st_type, E.be_st_jbgid, F.ba_st_type, ( SELECT CASE WHEN IFNULL(B.ba_st_grade, 0) < 2 THEN 0 WHEN 2 <= IFNULL(B.ba_st_grade, 0) < 4 THEN 1 WHEN 4 <= IFNULL(B.ba_st_grade, 0) < 6 THEN 2 WHEN 6 <= IFNULL(B.ba_st_grade, 0) < 8 THEN 3 ELSE 4 END FROM ba_t_labour B WHERE b.ba_st_userid = A.bf_st_receiveid ) ba_st_grade, C.ae_st_name, C.ae_dt_addDate, ( SELECT COUNT(1) FROM bf_t_apply A WHERE A.bf_st_receiveid = C.ae_st_id ) jdnum, ( SELECT D.ag_st_url FROM ag_t_file D WHERE D.ag_st_objid = A.bf_st_receiveid ) ag_st_url FROM bf_t_apply A LEFT JOIN ae_t_sysuser C ON C.ae_st_id = A.bf_st_receiveid LEFT JOIN be_t_assess E ON E.be_st_bfid = A.bf_st_id LEFT JOIN ba_t_labour F ON f.ba_st_userid = A.bf_st_receiveid "
				+ "WHERE E.be_st_bfid IS NULL "
				+ "AND bf_st_userid = '"+sid+"'";
		
		String queryHasJudged="SELECT A.bf_st_id, A.bf_st_name, A.bf_st_receiveid, A.bf_st_userid, C.ae_st_type, E.be_st_jbgid, F.ba_st_type, ( SELECT CASE WHEN IFNULL(B.ba_st_grade, 0) < 2 THEN 0 WHEN 2 <= IFNULL(B.ba_st_grade, 0) < 4 THEN 1 WHEN 4 <= IFNULL(B.ba_st_grade, 0) < 6 THEN 2 WHEN 6 <= IFNULL(B.ba_st_grade, 0) < 8 THEN 3 ELSE 4 END FROM ba_t_labour B WHERE b.ba_st_userid = A.bf_st_receiveid ) ba_st_grade, C.ae_st_name, C.ae_dt_addDate, ( SELECT COUNT(1) FROM bf_t_apply A WHERE A.bf_st_receiveid = C.ae_st_id ) jdnum, ( SELECT D.ag_st_url FROM ag_t_file D WHERE D.ag_st_objid = A.bf_st_receiveid ) ag_st_url FROM bf_t_apply A LEFT JOIN ae_t_sysuser C ON C.ae_st_id = A.bf_st_receiveid LEFT JOIN be_t_assess E ON E.be_st_bfid = A.bf_st_id LEFT JOIN ba_t_labour F ON f.ba_st_userid = A.bf_st_receiveid "
				+ " WHERE E.be_st_bfid IS NOT NULL "
				+ "AND bf_st_userid = '"+sid+"'";
		//List<Map<String, Object>> list2 = baseBiz.queryForList("select ae_st_name,ae_st_id,(select COUNT(1) from  bf_t_apply a where a.bf_st_receiveid= ae_st_id) jdnum,(case when ba_st_grade<2 then 0 when 2<=ba_st_grade<4 then 1 when 4<=ba_st_grade<6 then 2 when 6<=ba_st_grade<8 then 3 else 4 end) ba_st_grade,(select ag_st_url from ag_t_file where ag_st_objid = ae_st_id) ag_st_url,ba_st_teamnum from ae_t_sysuser ,ba_t_labour ,bf_t_apply b,be_t_assess where ae_st_id=ba_st_userid and ae_st_type='6'  AND b.bf_st_receiveid=ae_st_id AND be_st_jbgid=ae_st_id AND be_st_content IS NULL AND bf_st_userid='"+sid+"'");
		List<Map<String, Object>> list2 = baseBiz.queryForList(queryNotJudge+" and C.ae_st_type = '6' ");
		// 申请成为商家

		// 我的工长(未评价)

		// List<Map<String, Object>> list2 =
		// baseBiz.queryForList("select ae_st_name,ae_st_id,(select COUNT(1) from  bf_t_apply a where a.bf_st_receiveid= ae_st_id) jdnum,(case when ba_st_grade<2 then 0 when 2<=ba_st_grade<4 then 1 when 4<=ba_st_grade<6 then 2 when 6<=ba_st_grade<8 then 3 else 4 end) ba_st_grade,(select ag_st_url from ag_t_file where ag_st_objid = ae_st_id) ag_st_url,ba_st_teamnum from ae_t_sysuser ,ba_t_labour ,bf_t_apply b,be_t_assess where ae_st_id=ba_st_userid and ae_st_type='6'  AND b.bf_st_receiveid=ae_st_id AND be_st_jbgid=ae_st_id AND be_st_content IS NULL AND bf_st_userid='"+sid+"'");
		model.addAttribute("ov_overmanlistW", list2);
//		//我的工长(已评价)
//		String sql22 = "select ba_dt_addDate,ae_st_name,ae_st_id,(select COUNT(1) from  bf_t_apply where bf_st_receiveid= ae_st_id) jdnum,ba_st_grade,(select ag_st_url from ag_t_file where ag_st_objid = ae_st_id) ag_st_url,(select be_st_content from be_t_assess where be_st_jbgid=ae_st_id) be_st_content,(select be_nm_manner from be_t_assess where be_st_jbgid=ae_st_id) be_nm_manner,(select be_nm_integrity from be_t_assess where be_st_jbgid=ae_st_id) be_nm_integrity,(select be_nm_quality from be_t_assess where be_st_jbgid=ae_st_id) be_nm_quality from ae_t_sysuser ,ba_t_labour, bf_t_apply a where a.bf_st_receiveid=ae_st_id";
//		List<Map<String, Object>> list22 = baseBiz.queryForList(sql22+" AND ae_st_id=ba_st_userid  and ae_st_type='6'  AND a.bf_st_userid='"+sid+"'");
		
		List<Map<String, Object>> list22 = baseBiz.queryForList(queryHasJudged+" and C.ae_st_type = '6'");
		// //我的工长(已评价)
		// String sql22 =
		// "select ba_dt_addDate,ae_st_name,ae_st_id,(select COUNT(1) from  bf_t_apply where bf_st_receiveid= ae_st_id) jdnum,ba_st_grade,(select ag_st_url from ag_t_file where ag_st_objid = ae_st_id) ag_st_url,(select be_st_content from be_t_assess where be_st_jbgid=ae_st_id) be_st_content,(select be_nm_manner from be_t_assess where be_st_jbgid=ae_st_id) be_nm_manner,(select be_nm_integrity from be_t_assess where be_st_jbgid=ae_st_id) be_nm_integrity,(select be_nm_quality from be_t_assess where be_st_jbgid=ae_st_id) be_nm_quality from ae_t_sysuser ,ba_t_labour, bf_t_apply a where a.bf_st_receiveid=ae_st_id";
		// List<Map<String, Object>> list22 =
		// baseBiz.queryForList(sql22+" AND ae_st_id=ba_st_userid  and ae_st_type='6'  AND a.bf_st_userid='"+sid+"'");
		for (Map<String, Object> map : list22) {
			String beJudegeId = map.get("be_st_jbgid").toString();
			Map<String, Object> judgeMap = baseBiz.queryForMap("select * from be_t_assess where be_st_jbgid='" + beJudegeId + "'");
			map.put("judgeMap", judgeMap);
		}
		model.addAttribute("my_overmanlistY",list22);
//		//我的师傅(未评价)
//		String sql3 = "";
		List<Map<String, Object>> list3 = baseBiz.queryForList(queryNotJudge+"  and C.ae_st_type = '4'");
		model.addAttribute("my_masterlistW",list3);
//		//我的师傅(已评价)
//		String sql33 = "";
		List<Map<String, Object>> list33 = baseBiz.queryForList(queryHasJudged+" and C.ae_st_type = '4'");
		for (Map<String, Object> map : list33) {
			String beJudegeId = map.get("be_st_jbgid").toString();
			Map<String, Object> judgeMap = baseBiz.queryForMap("select * from be_t_assess where be_st_jbgid='" + beJudegeId + "'");
			map.put("judgeMap", judgeMap);
		}
		model.addAttribute("my_masterlistY",list33);
//		//我的设计师(未评价)
//		String sql4 = "";
		List<Map<String, Object>> list4 = baseBiz.queryForList(queryNotJudge+"  and C.ae_st_type = '5'");
		model.addAttribute("my_designlistW",list4);
//		//我的设计师(已评价)
//		String sql44 = "";
		List<Map<String, Object>> list44 = baseBiz.queryForList(queryHasJudged+"  and C.ae_st_type = '5'");
		for (Map<String, Object> map : list44) {
			String beJudegeId = map.get("be_st_jbgid").toString();
			Map<String, Object> judgeMap = baseBiz.queryForMap("select * from be_t_assess where be_st_jbgid='" + beJudegeId + "'");
			map.put("judgeMap", judgeMap);
		}
		model.addAttribute("my_designlistY",list44);
//		//我的监理(未评价)
//		String sql5 = "";
		//List<Map<String, Object>> list5 = baseBiz.queryForList(sql5+"");
		List<Map<String, Object>> list5 = baseBiz.queryForList(queryNotJudge+"  and F.ba_st_type='13'");
		model.addAttribute("my_supervisionlistW",list5);
//		List<Map<String, Object>> list5 = baseBiz.queryForList(sql5+"");
//		model.addAttribute("my_supervisionlistW",list5);
//		//我的监理(已评价)
//		String sql55 = "";
		List<Map<String, Object>> list55 = baseBiz.queryForList(queryHasJudged+" and F.ba_st_type='13'");
		for (Map<String, Object> map : list55) {
			String beJudegeId = map.get("be_st_jbgid").toString();
			Map<String, Object> judgeMap = baseBiz.queryForMap("select * from be_t_assess where be_st_jbgid='" + beJudegeId + "'");
			map.put("judgeMap", judgeMap);
		}
		model.addAttribute("my_supervisionlistY",list55);
//		//我的检测师(未评价)
//		String sql6 = "";
		List<Map<String, Object>> list6 = baseBiz.queryForList(queryNotJudge+"  and F.ba_st_type='14'");
		model.addAttribute("my_monitorlistW",list6);
//		//我的检测师(已评价)
//		String sql66 = "";
		List<Map<String, Object>> list66 = baseBiz.queryForList(queryHasJudged+"  and F.ba_st_type='14'");
		for (Map<String, Object> map : list66) {
			String beJudegeId = map.get("be_st_jbgid").toString();
			Map<String, Object> judgeMap = baseBiz.queryForMap("select * from be_t_assess where be_st_jbgid='" + beJudegeId + "'");
			map.put("judgeMap", judgeMap);
		}
		model.addAttribute("my_monitorlistY",list66);
		
		//付款记录
		//String sql7 = "SELECT ag.ag_st_url,bg.bg_st_name,bg.bg_st_summary,(SELECT count(*) FROM bg_t_prodinfo WHERE ae.ae_st_id=bg.bg_st_id) num,bh.bh_st_spprice FROM bg_t_prodinfo bg LEFT JOIN ag_t_file ag ON (bg.bg_st_id=ag.ag_st_objid) LEFT JOIN bi_t_orderprodrelate bi ON(bi.bi_st_spnum=bg.bg_st_num)LEFT JOIN bh_t_orderform bh ON (bh.bh_st_ddnum=bi.bi_st_ddnum AND bh.bh_st_ddstate=2 OR bh.bh_st_ddstate=3 OR bh.bh_st_ddstate=4 OR bh.bh_st_ddstate=5) LEFT JOIN ae_t_sysuser ae ON(ae.ae_st_id=bg.bg_st_bbid AND bh.bh_st_bbid=ae.ae_st_id AND ae.ae_st_type=1)";
		String sql7="SELECT c.bg_st_color,c.bg_st_norms ,a.bh_st_ddnum,c.bg_st_name ,b.bi_st_spsl,b.bi_st_spprice,c.bg_st_pricezg ,c.bg_st_summary,a.bh_st_ddstate,a.bh_dt_addDate,a.bh_st_spprice,c.bg_st_img1,c.bg_st_pricezg,a.bh_dt_addDate  "
				+ "from bh_t_orderform a   "
				+ "left JOIN  bi_t_orderprodrelate b on a.bh_st_ddnum=b.bi_st_ddnum     "
				+ "LEFT JOIN bg_t_prodinfo c on b.bi_st_spnum=c.bg_st_num "
				+ "where  a.bh_st_ddstate<>'1' ";
		List<Map<String, Object>> list7 = baseBiz.queryForList(sql7+"and a.bh_st_memberId='"+sid+"'");

		model.addAttribute("pay_mentlist", list7);
		// 我的订单(未支付)
		// 首先查出我所有的订单
		/*
		 * String
		 * myPayedOrderSql="SELECT * from bh_t_orderform where bh_st_memberId='"
		 * +sid+"'  and bh_st_ddstate ='1'"; List<Map<String, Object>>
		 * orderList=baseBiz.queryForList(myPayedOrderSql); for (Map<String,
		 * Object> orderMap : orderList) { String
		 * ddnum=orderMap.get("bh_st_ddnum").toString(); //根据每个订单号查询出 每个订单号对应的商品
		 * String goodSql=
		 * "SELECT A.bi_st_id, A.bi_st_ddnum, A.bi_st_spprice, A.bi_st_spsl, A.bi_st_spnum, B.bg_st_name, B.bg_st_color, B.bg_st_norms, B.bg_st_summary, C.ag_st_url, B.bg_st_pricezg,B.bg_st_id FROM bi_t_orderprodrelate A LEFT JOIN bg_t_prodinfo B ON A.bi_st_spnum = B.bg_st_num LEFT JOIN ag_t_file C ON C.ag_st_objid = B.bg_st_id WHERE bi_st_ddnum = '"
		 * +ddnum+"'"; List<Map<String, Object>>
		 * goodList=baseBiz.queryForList(goodSql); orderMap.put("goodsList",
		 * goodList); }
		 */
		// String sql8 =
		// "SELECT ag.ag_st_url image,bg.bg_st_name,bg.bg_st_pricezg,bg.bg_st_pricetj,bh.bh_st_spprice FROM bh_t_orderform bh LEFT JOIN bi_t_orderprodrelate bi ON(bh.bh_st_ddnum=bi.bi_st_ddnum) LEFT JOIN bg_t_prodinfo bg ON (bi.bi_st_spnum=bg.bg_st_num) LEFT JOIN ag_t_file ag ON(ag.ag_st_objid=bg.bg_st_id) LEFT JOIN ae_t_sysuser ae ON (bh.bh_st_bbid=ae.ae_st_id)";
		String sql8 = "SELECT c.bg_st_color,c.bg_st_norms , a.bh_st_ddnum,c.bg_st_name ,b.bi_st_spsl,b.bi_st_spprice,c.bg_st_pricezg ,c.bg_st_summary,a.bh_st_ddstate,a.bh_dt_addDate,a.bh_st_spprice,c.bg_st_img1,c.bg_st_pricezg,a.bh_dt_addDate,a.bh_st_memberId,c.bg_st_id,a.bh_st_id  " + "from bh_t_orderform a   " + "left JOIN  bi_t_orderprodrelate b on a.bh_st_ddnum=b.bi_st_ddnum     " + "LEFT JOIN bg_t_prodinfo c on b.bi_st_spnum=c.bg_st_num ";
		List<Map<String, Object>> list8 = baseBiz.queryForList(sql8 + " WHERE a.bh_st_memberId='" + sid + "'  and a.bh_st_ddstate='1'");
		model.addAttribute("my_orderformlistW", list8);
		model.addAttribute("orderListWSize", list8.size());
		// 我的订单(已支付)
		String myOrderSql = "SELECT * from bh_t_orderform where bh_st_memberId='" + sid + "'  and bh_st_ddstate <> '1'";
		List<Map<String, Object>> orderList2 = baseBiz.queryForList(myOrderSql);
		for (Map<String, Object> orderMap : orderList2) {
			String ddnum = orderMap.get("bh_st_ddnum").toString();
			// 根据每个订单号查询出 每个订单号对应的商品
			String goodSql = "SELECT A.bi_st_id, A.bi_st_ddnum, A.bi_st_spprice, A.bi_st_spsl, A.bi_st_spnum, B.bg_st_name, B.bg_st_color, B.bg_st_norms, B.bg_st_summary, C.ag_st_url, B.bg_st_pricezg,B.bg_st_id FROM bi_t_orderprodrelate A LEFT JOIN bg_t_prodinfo B ON A.bi_st_spnum = B.bg_st_num LEFT JOIN ag_t_file C ON C.ag_st_objid = B.bg_st_id WHERE bi_st_ddnum = '" + ddnum + "'";
			List<Map<String, Object>> goodList = baseBiz.queryForList(goodSql);
			orderMap.put("goodsList", goodList);
		}
		model.addAttribute("orderListYSize", orderList2.size());
		// String sql88 =
		// "SELECT bh.bh_st_id,bh.bh_st_ddnum,bh.bh_dt_addDate,ag.ag_st_url image,bg.bg_st_name,bg.bg_st_pricezg,bg.bg_st_pricetj,bh.bh_st_spprice FROM bh_t_orderform bh LEFT JOIN bi_t_orderprodrelate bi ON(bh.bh_st_ddnum=bi.bi_st_ddnum) LEFT JOIN bg_t_prodinfo bg ON (bi.bi_st_spnum=bg.bg_st_num) LEFT JOIN ag_t_file ag ON(ag.ag_st_objid=bg.bg_st_id) LEFT JOIN ae_t_sysuser ae ON (bh.bh_st_bbid=ae.ae_st_id)";
		// List<Map<String, Object>> list88 =
		// baseBiz.queryForList(sql8+" WHERE  a.bh_st_memberId='"+sid+"'  and a.bh_st_ddstate<>'1'");
		model.addAttribute("my_orderformlistY", orderList2);

		// 关于我们
		String aboutMeSql="SELECT bl_st_context FROM bl_t_noticecontext WHERE bl_st_type=6";
		List<Map<String, Object>> list = baseBiz.queryForList(aboutMeSql);
		model.addAttribute("aboutMap", list);
		return "wyyf/index/me/myHomePage";
	}

	// ------------------评价,预约,退款申请--------------------------------------
	/**
	 * 保存个人信息
	 * 
	 * @param assess
	 * @return
	 */
	@RequestMapping(value = "personage")
	public String personage(Ae_t_sysuser user) {
		getTopFootInfo();
		// 创建批量保存事务对象
		List<BizTransUtil> list = new ArrayList<BizTransUtil>();
		user.setAe_st_id(StringUtils.getUUID32());
		user.setAe_dt_addDate(new Date());
		user.setAe_st_type("1");
		list.add(new BizTransUtil(user));
		return "wyyf/index/me/myHomePage";
	}

	/***
	 * 评价工长 /师傅/设计师/监理师/检测师
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "toAssess")
	public String toAssess(Be_t_Assess assess) {
		getTopFootInfo();
		String be_st_jbgid_id = request.getParameter("be_st_jbgid_id");// 被评价人
		String be_st_fbgid_id = request.getParameter("be_st_fbgid_id");// 评价人
		// 创建批量保存事务对象
		List<BizTransUtil> list = new ArrayList<BizTransUtil>();
		assess.setBe_st_id(StringUtils.getUUID32());
		assess.setBe_st_fbgid(be_st_jbgid_id);
		assess.setBe_st_jbgid(be_st_jbgid_id);
		assess.setBe_dt_addDate(new Date());
		list.add(new BizTransUtil(assess));
		return "wyyf/index/me/myHomePage";
	}

	/***
	 * 商品评价
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "toPingjia")
	public String toPingjia() {
		this.session.putValue("RedirectUrl", this.request.getRequestURL() + "?" + this.request.getQueryString());
		getTopFootInfo();
		String id = request.getParameter("id");
		String bh_id = request.getParameter("bh_id");
		String num = request.getParameter("num");
		Map<String, Object> projectInfo = baseBiz.queryForMap("SELECT * FROM bg_t_prodinfo where bg_st_id= '" + bh_id + "'");
		model.addAttribute("projectInfo", projectInfo);
		model.addAttribute("num", num);
		model.addAttribute("bh_id", bh_id);
		return "wyyf/index/me/toPingjia";
	}

	@RequestMapping(value = "pingjiaEnd")
	public String pingjiaEnd(MultipartHttpServletRequest multipartRequest) {
		this.session.putValue("RedirectUrl", this.request.getRequestURL() + "?" + this.request.getQueryString());
		Ae_t_sysuser ae_t_sysuser = getTopFootInfo();
		if (null == ae_t_sysuser || null == ae_t_sysuser.getAe_st_id() || "" == ae_t_sysuser.getAe_st_id()) {
			return "wyyf/index/loginp";
		}
		String bh_id = request.getParameter("bh_id");// 被评价对象
		String Content = request.getParameter("content");

		// 接受图片
		// 获得文件：
		List<MultipartFile> files = multipartRequest.getFiles("file");
		List<BizTransUtil> filelist = new ArrayList<BizTransUtil>();
		String uuid = StringUtils.getUUID32();
		for (int i = 0; i < files.size(); i++) {
			if (files.get(i).getSize() > 0) {
				try {
					String uploadPath = UploadFile.uploadInputStream(files.get(i).getInputStream(), files.get(i).getOriginalFilename(), "images/business");

					Ag_t_file file = new Ag_t_file();
					file.setAg_dt_addDate(getcuttDate());// 创建时间

					file.setAg_st_objid(uuid);// 文件归属ID
					file.setAg_st_objtype("be_t_assess");// 文件对应的对象
					file.setAg_st_source("be_t_assess");
					file.setAg_st_type("1");
					file.setAg_st_mark("评价图片");
					file.setAg_st_format(files.get(0).getContentType());
					file.setAg_st_url(uploadPath);// //文件 的存储地址
					file.setAg_st_id(StringUtils.getUUID32());
					filelist.add(new BizTransUtil(file));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		baseBiz.executesTRANS(filelist);
		List<BizTransUtil> list = new ArrayList<>();
		Be_t_Assess assess = new Be_t_Assess();
		assess.setBe_st_id(uuid);
		assess.setBe_nm_manner(StringUtils.toIntegerByObject(request.getParameter("be_nm_manner")));
		assess.setBe_nm_integrity(StringUtils.toIntegerByObject(request.getParameter("be_nm_integrity")));
		assess.setBe_nm_quality(StringUtils.toIntegerByObject(request.getParameter("be_nm_quality")));
		assess.setBe_st_content(Content);
		assess.setBe_dt_addDate(new Date());
		assess.setBe_st_jbgid(bh_id);
		assess.setBe_st_fbgid(ae_t_sysuser.getAe_st_id());
		assess.setBe_st_addUserId(ae_t_sysuser.getAe_st_id());
		list.add(new BizTransUtil(assess));
		Boolean b = baseBiz.executesTRANS(list);
		return "wyyf/index/me/myHomePage";
	}

	/***
	 * 申请退款
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "tmoney")
	public String tmoney() {
		this.session.putValue("RedirectUrl", this.request.getRequestURL() + "?" + this.request.getQueryString());
		getTopFootInfo();
		String id = request.getParameter("id");
		String bh_id = request.getParameter("bh_id");
		String sql88 = "SELECT ae.ae_st_id,bg.bg_st_id,ag.ag_st_url image,bg.bg_st_name,bg.bg_st_pricezg,bg.bg_st_pricetj," + "bh.bh_st_spprice,bh_st_id,bh_st_ddstate,bh_st_ddnum,bh_dt_addDate FROM bh_t_orderform bh LEFT JOIN bi_t_orderprodrelate bi ON(bh.bh_st_ddnum=bi.bi_st_ddnum) LEFT JOIN bg_t_prodinfo bg ON (bi.bi_st_spnum=bg.bg_st_num) LEFT JOIN ag_t_file ag ON(ag.ag_st_objid=bg.bg_st_id) LEFT JOIN ae_t_sysuser ae ON (bh.bh_st_bbid=ae.ae_st_id)";
		// List<Map<String, Object>> list88 =
		// baseBiz.queryForList(sql88+" WHERE bh.bh_st_ddstate = 4 and  bh.bh_st_id='"+bh_id+"'"
		// + " AND ae.ae_st_id='"+id+"'");
		// bf_st_remark 退款原因
		// model.addAttribute("t_moneylist",list88);
		String sql = "SELECT * FROM bh_t_orderform  WHERE bh_st_id='" + bh_id + "'";

		Map<String, Object> map = this.baseBiz.queryForMap(sql);
		this.model.addAttribute("order", map);
		this.model.addAttribute("bh_id", bh_id);

		return "wyyf/index/me/tmoney";
	}

	@RequestMapping(value = "tmoneyEnd")
	public String tmoneyEnd() {
		this.session.putValue("RedirectUrl", this.request.getRequestURL() + "?" + this.request.getQueryString());
		getTopFootInfo();
		String bh_id = request.getParameter("bh_id");
		String sql = "UPDATE bh_t_orderform SET bh_st_ddstate = 8 WHERE bh_st_id='" + bh_id + "'";
		this.baseBiz.executeTRANS(sql);
		// 无法写退款原因
		return "wyyf/index/me/myHomePage";
	}

	@RequestMapping(value = "User_protocol")
	public String User_protocol() {
		System.out.println(1);
		return "wyyf/index/User_protocol";
	}
	// -------------------家居商城----------------------------------------------------------
	/**
	 * 我要特价
	 * 
	 * @return
	 */
	@RequestMapping(value = "shopcheap")
	public String shopcheap() {
		this.session.putValue("RedirectUrl", this.request.getRequestURL() + "?" + this.request.getQueryString());
		getTopFootInfo();
		// 广告
		List<Map<String, Object>> list = baseBiz.queryForList("SELECT ag_st_url FROM ag_t_file,bj_t_advertisement WHERE ag_st_objid=bj_st_id AND bj_st_type='6'");
		model.addAttribute("ad_adlist", list);
		// 活动商品
		Integer pageIndex = StringUtils.toIntegerByObject(request.getParameter("page"));
		StringBuffer sb = new StringBuffer("SELECT now() nowTime , bg.bg_st_id,bg.bg_st_img1,bo.bo_dt_startDate,bg.bg_st_name,bg.bg_st_pricetj,bg.bg_st_pricezg,bo.bo_st_spprice,bo.bo_dt_endDate,(SELECT count(1) FROM bh_t_orderform bh,bi_t_orderprodrelate bi WHERE bi.bi_st_ddnum=bh.bh_st_ddnum AND bi.bi_st_spnum=bg.bg_st_num) num");
		StringBuffer sbcount = new StringBuffer("select count(bg.bg_st_id) ");
		StringBuffer sbcommon = new StringBuffer(" FROM bg_t_prodinfo bg LEFT JOIN bp_t_activityprodrelate bp ON (bg.bg_st_num=bp.bp_st_spnum) LEFT JOIN bo_t_activity bo ON (bo.bo_st_ddnum=bp.bp_st_ddnum) where bo.bo_st_title='正在抢购' and bg.bg_st_enable=1  and bg.bg_st_isdel = 0 ");
		List<String> params = new ArrayList<String>();
		sbcount.append(sbcommon);
		sb.append(sbcommon).append("order by bo.bo_st_spprice desc ");
		// 分页数据、分页配置 查询--
		PageBean pages = baseBiz.getPages(sb.toString(), sbcount.toString(), pageIndex, 8, params.toArray());
		model.addAttribute("pages", pages);// 分页对象

		// List<Map<String, Object>> list2 = baseBiz.queryForList();
		// model.addAttribute("shop_cheaplist", list2);
		return "wyyf/index/shop/shopcheap";
	}

	/**
	 * 商品详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "shopmess")
	public String shopmess() {
		getTopFootInfo();
		this.session.putValue("RedirectUrl", this.request.getRequestURL() + "?" + this.request.getQueryString());
		// model.addAttribute("oldRequestUrl", oldUrlRedirect);
		// 商品详情
		String id = request.getParameter("id");
		List<Map<String, Object>> list = baseBiz.queryForList("SELECT bg_st_fbtpe,bg_st_id,bg_st_img1,bg_st_img2,bg_st_img3,bg_st_img4,bg_st_name,bg_st_summary,bg_st_pricezg,bg_st_pricetj FROM bg_t_prodinfo where bg_st_id='" + id + "'");
		model.addAttribute("shop_messlist", list);
		// 区域选择
		// String sql3 =
		// "select b.d_name b.d_code from aa26 a LEFT JOIN aa26 b ON (a.d_code=b.d_parent)";
		// List<Map<String, Object>> list2 = baseBiz.queryForList(sql3);
		// model.addAttribute("area_list",list2);
		// 区域选择
		String sql2 = "select a.d_name,a.d_code from aa26 a where a.d_level='1'";
		List<Map<String, Object>> list7 = baseBiz.queryForList(sql2);
		model.addAttribute("Province_list", list7);
		// 相似商品(列举5个最新商品)
		List<Map<String, Object>> list3 = baseBiz.queryForList("SELECT bg.bg_st_id,bg.bg_st_img1,bo.bo_dt_startDate,bg.bg_st_name,bg.bg_st_pricetj,bg.bg_st_pricezg,bo.bo_dt_endDate,(SELECT count(1) FROM bh_t_orderform bh,bi_t_orderprodrelate bi WHERE bi.bi_st_ddnum=bh.bh_st_ddnum AND bi.bi_st_spnum=bg.bg_st_num) num FROM bg_t_prodinfo bg LEFT JOIN bp_t_activityprodrelate bp ON (bg.bg_st_num=bp.bp_st_spnum) LEFT JOIN bo_t_activity bo ON (bo.bo_st_ddnum=bp.bp_st_ddnum) where bo.bo_st_title='正在抢购' ORDER BY bg.bg_dt_startDate DESC LIMIT 0,5 ");
		model.addAttribute("shop_cheaplist", list3);
		// 商品详情2
		String sql4 = "SELECT * FROM bg_t_prodinfo";
		List<Map<String, Object>> list4 = baseBiz.queryForList(sql4 + " where bg_st_id='" + id + "'");
		model.addAttribute("shop_messlist2", list4);
		// 成交记录
		List<Map<String, Object>> list5 = baseBiz.queryForList("SELECT ae_st_name,bh_st_spprice,bi_st_spsl num,bh_dt_addDate,bg_st_remark FROM bg_t_prodinfo,bb_t_shopuser,bh_t_orderform,Bi_t_OrderProdRelate,ae_t_sysuser WHERE ae_st_id=bb_st_userid and bg_st_bbid=bb_st_id AND bi_st_spnum=bg_st_num AND bi_st_ddnum=bh_st_ddnum   AND bg_st_enable=1 and   bg_st_isdel = 0  AND bg_st_id='" + id + "'");
		model.addAttribute("chengjiao_list", list5);
		// 评价详情
		List<Map<String, Object>> list6 = baseBiz.queryForList("SELECT (SELECT ag_st_url FROM ag_t_file WHERE ag_st_objid=ae_st_id) ag_st_url,ae_st_name,be_st_content,be_dt_addDate,bg_st_img1,bg_st_img2,bg_st_img3 FROM bg_t_prodinfo,be_t_assess,ae_t_sysuser WHERE be_st_bgid=bg_st_id AND be_st_fbgid=ae_st_id and bg_st_id='" + id + "'");
		model.addAttribute("assess_list", list6);
		model.addAttribute("id", id);
		return "wyyf/index/shop/shopmess";
	}

	/**
	 * 团代金券
	 * 
	 * @return
	 */
	@RequestMapping(value = "actbuy")
	public String voucher() {
		this.session.putValue("RedirectUrl", this.request.getRequestURL() + "?" + this.request.getQueryString());
		getTopFootInfo();
		String priceUp = request.getParameter("priceUp");
		String expireTimeUp = request.getParameter("expireTimeUp");
		String effectTimeUp = request.getParameter("effectTimeUp");
		Integer pageIndex = StringUtils.toIntegerByObject(request.getParameter("page"));
		if (pageIndex.intValue() <= 0) {
			pageIndex = 1;
		}
		int pageSize = 20;
		StringBuffer sb = new StringBuffer("SELECT p.*,(SELECT ae_st_name FROM ae_t_sysuser where ae_st_id=p.bg_st_bbid) ae_st_name FROM bg_t_prodinfo p  where  p.bg_st_randid='5' and p.bg_st_enable = 1  and p.bg_st_isdel = 0 ");

		StringBuffer orderBySql = new StringBuffer();
		if (priceUp != null && !priceUp.equals("")) {
			if (priceUp.equals("0")) {
				orderBySql.append("  p.bg_st_pricetj ASC, ");
			} else {
				orderBySql.append("  p.bg_st_pricetj DESC, ");
			}
		} else {
			priceUp = "0";
			orderBySql.append("  p.bg_st_pricetj ASC, ");
		}

		if (expireTimeUp != null && !expireTimeUp.equals("")) {
			if (expireTimeUp.equals("0")) {
				orderBySql.append(" p.bg_dt_endDate ASC, ");
			} else {
				orderBySql.append(" p.bg_dt_endDate DESC, ");
			}
		} else {
			expireTimeUp = "0";
			orderBySql.append("  p.bg_dt_endDate ASC, ");
		}

		if (effectTimeUp != null && !effectTimeUp.equals("")) {
			if (effectTimeUp.equals("0")) {
				orderBySql.append(" p.bg_dt_startDate ASC ");
			} else {
				orderBySql.append(" p.bg_dt_startDate DESC ");
			}
		} else {
			effectTimeUp = "0";
			orderBySql.append("  p.bg_dt_startDate ASC ");
		}
		if (orderBySql.length() != 0) {
			orderBySql = new StringBuffer(" ORDER BY ").append(orderBySql.toString());
			sb.append(orderBySql);
		}

		logger.info("请求SQL : " + sb.toString());
		PageBean pages = baseBiz.getPages(sb.toString(), "SELECT count(*) FROM bg_t_prodinfo where  bg_st_randid='5' and bg_st_enable = 1 and   bg_st_isdel = 0 ", pageIndex, pageSize);
		// 计算总的代金劵数量
		int productTotal = baseBiz.queryForInt("SELECT COUNT(*) FROM bg_t_prodinfo p  where  p.bg_st_randid='5' and bg_st_enable = 1 and   bg_st_isdel = 0 ");
		model.addAttribute("priceUp", priceUp);
		model.addAttribute("expireTimeUp", expireTimeUp);
		model.addAttribute("effectTimeUp", effectTimeUp);
		model.addAttribute("page", pageIndex + "");
		model.addAttribute("productTotal", productTotal + "");
		model.addAttribute("pageBeanObj", pages);// 分页对象
		return "wyyf/index/shop/voucher";
	}

	/**
	 * 品牌特卖
	 * 
	 * @return
	 */
	@RequestMapping(value = "shopspecial")
	public String shopspecial() {
		this.session.putValue("RedirectUrl", this.request.getRequestURL() + "?" + this.request.getQueryString());
		getTopFootInfo();
		Integer pageIndex = StringUtils.toIntegerByObject(request.getParameter("page"));
		if (pageIndex.intValue() <= 0) {
			pageIndex = 1;
		}
		Integer pageSize = 20;
		// 广告
		String sql = "SELECT ag_st_url FROM ag_t_file,bj_t_advertisement WHERE ag_st_objid=bj_st_id AND bj_st_type='3'";
		List<Map<String, Object>> list = baseBiz.queryForList(sql);
		model.addAttribute("ad_adlist", list);
		String shopSql = "SELECT D.ag_st_url,B.* FROM  Bb_t_ShopUser B , ae_t_sysuser C , ag_t_file D where B.bb_st_userid = C.ae_st_id and   D.ag_st_url is not null and  D.ag_st_objid = C.ae_st_id";
		String shopSql1 = "SELECT count(1) FROM  Bb_t_ShopUser B , ae_t_sysuser C , ag_t_file D where B.bb_st_userid = C.ae_st_id and   D.ag_st_url is not null and  D.ag_st_objid = C.ae_st_id";
		PageBean pages = baseBiz.getPages(shopSql, shopSql1, pageIndex, pageSize);

		// 商家列表
		// List<Map<String, Object>> shopList = baseBiz.queryForList(shopSql);
		for (int i = 0; i < pages.getData().size(); i++) {
			String productSql = "SELECT * FROM bg_t_prodinfo WHERE bg_st_enable = 1 and   bg_st_isdel = 0 and bg_st_bbid ='" + pages.getData().get(i).get("bb_st_userid").toString() + "' limit 0,3";
			
			pages.getData().get(i).put("products", baseBiz.queryForList(productSql));
			
		}
		// 品牌商品
		// List<Map<String, Object>> list2 =
		// baseBiz.queryForList("SELECT bg_st_id,bg_st_name,bg_st_randid,bg_st_img1,bg_st_img2,bg_st_img3,bg_st_img4 FROM bg_t_prodinfo where bg_st_fbtpe='1'");
		model.addAttribute("shopList", pages.getData());
		model.addAttribute("shop_buldinglist", pages);
		return "wyyf/index/shop/shopspecial";
	}
	/**
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "ProductListAjax")
	@ResponseBody
	public void ProductListAjax() throws UnsupportedEncodingException {
		StringBuffer orderBySql = new StringBuffer();
		StringBuffer whereSql = new StringBuffer();
		StringBuffer totalSql = new StringBuffer("SELECT Count(*) FROM bg_t_prodinfo B");
		String IsPromotion = request.getParameter("IsPromotion");
		String keywordsStr = request.getParameter("keywords");
		String keywords = "";
		if (null != keywordsStr && !keywordsStr.equals("")) {
			keywords = new String(keywordsStr.getBytes("ISO-8859-1"), "UTF-8");
		}
		String bb_st_id = this.request.getParameter("bb_st_id");
		String minPrice = request.getParameter("minPrice");
		String maxPrice = request.getParameter("maxPrice");
		String OrderByType = request.getParameter("OrderByType");
		String Up = request.getParameter("Up");
		Integer page = StringUtils.toIntegerByObject(request.getParameter("page"));
		if (page == null || page <= 0) {
			page = Integer.valueOf(1);
		}
		if (keywords == null || keywords.trim().equals("")) {
			keywords = "";
		} else {
			if (whereSql.length() != 0) {
				whereSql.append(" AND B.bg_st_name like '%" + keywords + "%' ");
			} else {
				whereSql.append(" WHERE B.bg_st_name like '%" + keywords + "%' ");
			}
		}

		if (bb_st_id == null || bb_st_id.trim().equals("")) {
			bb_st_id = "";
		} else {
			if (whereSql.length() != 0) {
				whereSql.append(" AND B.bg_st_bbid = '" + bb_st_id + "' ");
			} else {
				whereSql.append(" WHERE B.bg_st_bbid = '" + bb_st_id + "' ");
			}
		}

		if (minPrice == null || minPrice.equals("")) {
			minPrice = "";
		} else {
			if (whereSql.length() != 0) {
				whereSql.append(" AND B.bg_st_pricezg >= " + minPrice + " ");
			} else {
				whereSql.append(" WHERE B.bg_st_pricezg >= " + minPrice + " ");
			}
		}
		if (maxPrice == null || maxPrice.equals("")) {
			maxPrice = "";
		} else {
			if (whereSql.length() != 0) {
				whereSql.append(" AND B.bg_st_pricezg <= " + maxPrice + " ");
			} else {
				whereSql.append(" WHERE B.bg_st_pricezg <= " + maxPrice + " ");
			}
		}
		if (IsPromotion == null || IsPromotion.equals("")) {
			IsPromotion = "false";
		} else {
			if (IsPromotion.equals("true")) {
				if (whereSql.length() != 0) {
					whereSql.append(" AND B.bg_st_randid ='9'");
				} else {
					whereSql.append(" WHERE B.bg_st_randid ='9' ");
				}
			}
		}

		if (OrderByType == null || OrderByType.equals("")) {
			OrderByType = "0";
		}

		if (Up == null || Up.equals("")) {
			Up = "0";
		}
		if (OrderByType.equals("0")) {
			if (Up.equals("0")) {
				orderBySql.append(" ORDER BY A.productNum DESC,B.bg_st_pricezg DESC ");
			} else {
				orderBySql.append(" ORDER BY A.productNum ASC,B.bg_st_pricezg ASC ");
			}
		} else if (OrderByType.equals("1")) {
			if (Up.equals("0")) {
				orderBySql.append(" ORDER BY B.bg_st_pricezg DESC ");
			} else {
				orderBySql.append(" ORDER BY B.bg_st_pricezg ASC ");
			}
		} else if (OrderByType.equals("2")) {
			if (Up.equals("0")) {
				orderBySql.append(" ORDER BY A.productNum DESC");
			} else {
				orderBySql.append(" ORDER BY A.productNum ASC");
			}
		}

		StringBuffer sql = new StringBuffer("SELECT A.productNum,B.* FROM bg_t_prodinfo B LEFT JOIN (SELECT  Count(b.bi_st_spsl) productNum,p.bg_st_num productId FROM bg_t_prodinfo p ,bi_t_orderprodrelate b WHERE b.bi_st_spnum = p.bg_st_num group by productId) A ON A.productId = B.bg_st_num ");
		sql.append(whereSql).append(orderBySql);
		totalSql.append(whereSql);
		PageBean pages = baseBiz.getPages(sql.toString(), totalSql.toString(), page, 18);
		this.createAjax(response, JsonUtils.getJsonData(pages),"application/json");

	}
	/**
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "ProductList")
	public String ProductList() throws UnsupportedEncodingException {
		this.session.putValue("RedirectUrl", this.request.getRequestURL() + "?" + this.request.getQueryString());
		StringBuffer orderBySql = new StringBuffer();
		StringBuffer whereSql = new StringBuffer();
		StringBuffer totalSql = new StringBuffer("SELECT Count(*) FROM bg_t_prodinfo B");
		String IsPromotion = request.getParameter("IsPromotion");
		String keywordsStr = request.getParameter("keywords");
		String keywords = "";
		if (null != keywordsStr && !keywordsStr.equals("")) {
			keywords = keywordsStr;
		}
		String bb_st_id = this.request.getParameter("bb_st_id");
		String minPrice = request.getParameter("minPrice");
		String maxPrice = request.getParameter("maxPrice");
		String OrderByType = request.getParameter("OrderByType");
		String Up = request.getParameter("Up");
		Integer page = StringUtils.toIntegerByObject(request.getParameter("page"));
		if (page == null || page <= 0) {
			page = Integer.valueOf(1);
		}
		if (keywords == null || keywords.trim().equals("")) {
			keywords = "";
		} else {
			if (whereSql.length() != 0) {
				whereSql.append(" AND B.bg_st_name like '%" + keywords + "%' ");
			} else {
				whereSql.append(" WHERE B.bg_st_name like '%" + keywords + "%' ");
			}
		}

		if (bb_st_id == null || bb_st_id.trim().equals("")) {
			bb_st_id = "";
		} else {
			if (whereSql.length() != 0) {
				whereSql.append(" AND B.bg_st_bbid = '" + bb_st_id + "' ");
			} else {
				whereSql.append(" WHERE B.bg_st_bbid = '" + bb_st_id + "' ");
			}
		}

		if (minPrice == null || minPrice.equals("")) {
			minPrice = "";
		} else {
			if (whereSql.length() != 0) {
				whereSql.append(" AND B.bg_st_pricezg >= " + minPrice + " ");
			} else {
				whereSql.append(" WHERE B.bg_st_pricezg >= " + minPrice + " ");
			}
		}
		if (maxPrice == null || maxPrice.equals("")) {
			maxPrice = "";
		} else {
			if (whereSql.length() != 0) {
				whereSql.append(" AND B.bg_st_pricezg <= " + maxPrice + " ");
			} else {
				whereSql.append(" WHERE B.bg_st_pricezg <= " + maxPrice + " ");
			}
		}
		if (IsPromotion == null || IsPromotion.equals("")) {
			IsPromotion = "false";
		} else {
			if (IsPromotion.equals("true")) {
				if (whereSql.length() != 0) {
					whereSql.append(" AND B.bg_st_randid ='9'");
				} else {
					whereSql.append(" WHERE B.bg_st_randid ='9' ");
				}
			}
		}

		if (OrderByType == null || OrderByType.equals("")) {
			OrderByType = "0";
		}

		if (Up == null || Up.equals("")) {
			Up = "0";
		}
		if (OrderByType.equals("0")) {
			if (Up.equals("0")) {
				orderBySql.append(" ORDER BY A.productNum DESC,B.bg_st_pricezg DESC ");
			} else {
				orderBySql.append(" ORDER BY A.productNum ASC,B.bg_st_pricezg ASC ");
			}
		} else if (OrderByType.equals("1")) {
			if (Up.equals("0")) {
				orderBySql.append(" ORDER BY B.bg_st_pricezg DESC ");
			} else {
				orderBySql.append(" ORDER BY B.bg_st_pricezg ASC ");
			}
		} else if (OrderByType.equals("2")) {
			if (Up.equals("0")) {
				orderBySql.append(" ORDER BY A.productNum DESC");
			} else {
				orderBySql.append(" ORDER BY A.productNum ASC");
			}
		}

		StringBuffer sql = new StringBuffer("SELECT A.productNum,B.* FROM bg_t_prodinfo B LEFT JOIN (SELECT  Count(b.bi_st_spsl) productNum,p.bg_st_num productId FROM bg_t_prodinfo p ,bi_t_orderprodrelate b WHERE b.bi_st_spnum = p.bg_st_num group by productId) A ON A.productId = B.bg_st_num ");
		sql.append(whereSql).append(orderBySql);
		totalSql.append(whereSql);
		PageBean pages = baseBiz.getPages(sql.toString(), totalSql.toString(), page, 18);
		int ProductTotal = baseBiz.queryForInt(totalSql.toString());

		String shopSql = "SELECT C.ae_st_name,D.ag_st_url,B.* FROM  Bb_t_ShopUser B LEFT JOIN ae_t_sysuser C ON B.bb_st_userid = C.ae_st_id LEFT JOIN ag_t_file D ON D.ag_st_objid = C.ae_st_id";
		// 商家列表
		List<Map<String, Object>> shopList = baseBiz.queryForList(shopSql);
		model.addAttribute("keywords", keywords);
		model.addAttribute("bb_st_id", bb_st_id);
		model.addAttribute("IsPromotion", IsPromotion);
		model.addAttribute("OrderByType", OrderByType);
		model.addAttribute("Up", Up);
		model.addAttribute("minPrice", minPrice);
		model.addAttribute("maxPrice", maxPrice);
		model.addAttribute("page", page);
		model.addAttribute("ProductTotal", ProductTotal);
		model.addAttribute("shop_buldinglist", pages);
		model.addAttribute("shopList", shopList);
		// 推荐商品(根据商品时间倒叙,推荐5个新商品)

		return "wyyf/index/shop/ProductListAjax";
	}

	/**
	 * 建材商城
	 * 
	 * @return
	 */
	/**
	 * 建材商城
	 * 
	 * @return
	 */
	@RequestMapping(value = "shopbulding")
	public String shopbulding() {
		this.session.putValue("RedirectUrl", this.request.getRequestURL() + "?" + this.request.getQueryString());
		StringBuffer orderBySql = new StringBuffer();
		StringBuffer whereSql = new StringBuffer(" WHERE B.bg_st_enable=1 and B.bg_st_isdel = 0 ");
		StringBuffer totalSql = new StringBuffer("SELECT Count(*) FROM bg_t_prodinfo B");
		String type = request.getParameter("type");
		String shoptype = request.getParameter("shoptype");// 类型ID（1=家具、2=建材、3=智能家居、4=软装配饰。码表自定义），5=购物券
		String ab_st_value = "PPLX_2";
		if ("building".equals(shoptype)) {
			type = "2";
			ab_st_value = "PPLX_2";
		} else if ("electrical".equals(shoptype)) {
			type = "1";
			ab_st_value = "PPLX_1";
		} else {
			type = "2";
			ab_st_value = "PPLX_2";
		}
		// 获取侧边的类型
		List<Map<String, Object>> categoryList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = baseBiz.queryForMap("SELECT * FROM ab_t_code WHERE ab_st_mark ='" + ab_st_value + "'", null);
		List<Map<String, Object>> nvgList = getnvgList_Ab_t_codeByPid(map.get("ab_st_id").toString());
		
		
		categoryList.addAll(getChild_Ab_t_codeByPid(map.get("ab_st_id").toString()));
		categoryList.add(map);
		model.addAttribute("nvgList", nvgList);

		// 组装SQL
		String bg_st_randid = "";
		for (int i = 0; i < categoryList.size(); i++) {
			String tempAb_st_value = categoryList.get(i).get("ab_st_value").toString();
			if (!tempAb_st_value.trim().equals("")&&type.equals(tempAb_st_value.trim())) {
				bg_st_randid += tempAb_st_value + ",";
			}
		}

		bg_st_randid = bg_st_randid.substring(0, bg_st_randid.length() - 1);
		whereSql.append(" AND B.bg_st_randid in (" + bg_st_randid + ") ");

		String minPrice = request.getParameter("minPrice");
		if (minPrice == null || minPrice.equals("")) {
			minPrice = "";
		} else {
			if (whereSql.length() != 0) {
				whereSql.append(" AND B.bg_st_pricezg >= " + minPrice + " ");
			} else {
				whereSql.append(" WHERE B.bg_st_pricezg >= " + minPrice + " ");
			}
		}
		String maxPrice = request.getParameter("maxPrice");
		if (maxPrice == null || maxPrice.equals("")) {
			maxPrice = "";
		} else {
			if (whereSql.length() != 0) {
				whereSql.append(" AND B.bg_st_pricezg <= " + maxPrice + " ");
			} else {
				whereSql.append(" WHERE B.bg_st_pricezg <= " + maxPrice + " ");
			}
		}

		String OrderByType = request.getParameter("OrderByType");
		if (OrderByType == null || OrderByType.equals("")) {
			OrderByType = "0";
		}

		String Up = request.getParameter("Up");
		if (Up == null || Up.equals("")) {
			Up = "0";
		}
		if (OrderByType.equals("0")) {
			if (Up.equals("0")) {
				orderBySql.append(" ORDER BY A.productNum DESC,B.bg_st_pricezg DESC ");
			} else {
				orderBySql.append(" ORDER BY A.productNum ASC,B.bg_st_pricezg ASC ");
			}
		} else if (OrderByType.equals("1")) {
			if (Up.equals("0")) {
				orderBySql.append(" ORDER BY B.bg_st_pricezg DESC ");
			} else {
				orderBySql.append(" ORDER BY B.bg_st_pricezg ASC ");
			}
		} else if (OrderByType.equals("2")) {
			if (Up.equals("0")) {
				orderBySql.append(" ORDER BY A.productNum DESC");
			} else {
				orderBySql.append(" ORDER BY A.productNum ASC");
			}
		}

		Integer page = StringUtils.toIntegerByObject(request.getParameter("page"));
		if (page == null || page <= 0) {
			page = Integer.valueOf(1);
		}
		getTopFootInfo();
		// 广告
		List<Map<String, Object>> list = baseBiz.queryForList("SELECT ag_st_url FROM ag_t_file,bj_t_advertisement WHERE ag_st_objid=bj_st_id AND bj_st_type='4'");
		model.addAttribute("ad_adlist", list);
		StringBuffer sql = new StringBuffer("SELECT A.productNum,B.* FROM bg_t_prodinfo B LEFT JOIN (SELECT  Count(b.bi_st_spsl) productNum,p.bg_st_num productId FROM bg_t_prodinfo p ,bi_t_orderprodrelate b WHERE b.bi_st_spnum = p.bg_st_num group by productId) A ON A.productId = B.bg_st_num ");

		// 建材商城
		sql.append(whereSql).append(orderBySql);
		totalSql.append(whereSql);
		PageBean pages = baseBiz.getPages(sql.toString(), totalSql.toString(), page, 18);
		// 查询商品总数
		String prouctTotalSql1 = "SELECT Count(*) FROM bg_t_prodinfo WHERE bg_st_randid IN (SELECT ab_st_value FROM ab_t_code WHERE ab_st_parent IN (SELECT ab_st_id FROM ab_t_code WHERE ab_st_parent IN (SELECT ab_st_id FROM ab_t_code WHERE ab_st_mark ='" + ab_st_value + "')))";
		String prouctTotalSql2 = "SELECT Count(*) FROM bg_t_prodinfo WHERE bg_st_randid IN (SELECT ab_st_value FROM ab_t_code WHERE ab_st_parent IN (SELECT ab_st_id FROM ab_t_code WHERE ab_st_mark ='" + ab_st_value + "'))";
		String prouctTotalSql3 = "SELECT Count(*) FROM bg_t_prodinfo WHERE bg_st_randid IN (SELECT ab_st_value FROM ab_t_code WHERE ab_st_mark ='" + ab_st_value + "')";
		int ProductTotal = baseBiz.queryForInt(prouctTotalSql1) + baseBiz.queryForInt(prouctTotalSql2) + baseBiz.queryForInt(prouctTotalSql3);
		model.addAttribute("type", type);
		model.addAttribute("shoptype", shoptype);
		model.addAttribute("OrderByType", OrderByType);
		model.addAttribute("Up", Up);
		model.addAttribute("minPrice", minPrice);
		model.addAttribute("maxPrice", maxPrice);
		model.addAttribute("page", page);
		model.addAttribute("ProductTotal", ProductTotal);
		model.addAttribute("shop_buldinglist", pages);// 分页对象
		// 推荐商品(根据商品时间倒叙,推荐5个新商品)
		List<Map<String, Object>> list3 = baseBiz.queryForList("SELECT bg_st_id,bg_st_name,bg_st_randid,bg_st_img1,bg_nm_storeNum,bg_st_pricezg,bg_st_img2,bg_st_img3,bg_st_img4 FROM bg_t_prodinfo where bg_st_fbtpe='1' and bg_st_randid='" + type + "'  ORDER BY bg_dt_startDate desc LIMIT 0,5");
		model.addAttribute("tuijian_buldinglist", list3);

		return "wyyf/index/shop/shopbuldingajax";
	}
	/*@RequestMapping(value = "shopbulding")
	public String shopbulding() {
		this.session.putValue("RedirectUrl", this.request.getRequestURL() + "?" + this.request.getQueryString());
		StringBuffer orderBySql = new StringBuffer();
		StringBuffer whereSql = new StringBuffer(" WHERE B.bg_st_enable=1 and B.bg_st_isdel = 0 ");
		StringBuffer totalSql = new StringBuffer("SELECT Count(*) FROM bg_t_prodinfo B");
		String type = request.getParameter("type");
		String shoptype = request.getParameter("shoptype");// 类型ID（1=家具、2=建材、3=智能家居、4=软装配饰。码表自定义），5=购物券
		String ab_st_value = "PPLX_2";
		if ("building".equals(shoptype)) {
			type = "2";
			ab_st_value = "PPLX_2";
		} else if ("electrical".equals(shoptype)) {
			type = "1";
			ab_st_value = "PPLX_1";
		} else {
			type = "2";
			ab_st_value = "PPLX_2";
		}
		// 获取侧边的类型
		List<Map<String, Object>> categoryList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = baseBiz.queryForMap("SELECT * FROM ab_t_code WHERE ab_st_mark ='" + ab_st_value + "'", null);
		List<Map<String, Object>> nvgList = getnvgList_Ab_t_codeByPid(map.get("ab_st_id").toString());
		
		
		categoryList.addAll(getChild_Ab_t_codeByPid(map.get("ab_st_id").toString()));
		categoryList.add(map);
		model.addAttribute("nvgList", nvgList);

		// 组装SQL
		String bg_st_randid = "";
		for (int i = 0; i < categoryList.size(); i++) {
			String tempAb_st_value = categoryList.get(i).get("ab_st_value").toString();
			if (!tempAb_st_value.trim().equals("")&&type.equals(tempAb_st_value.trim())) {
				bg_st_randid += tempAb_st_value + ",";
			}
		}

		bg_st_randid = bg_st_randid.substring(0, bg_st_randid.length() - 1);
		whereSql.append(" AND B.bg_st_randid in (" + bg_st_randid + ") ");

		String minPrice = request.getParameter("minPrice");
		if (minPrice == null || minPrice.equals("")) {
			minPrice = "";
		} else {
			if (whereSql.length() != 0) {
				whereSql.append(" AND B.bg_st_pricezg >= " + minPrice + " ");
			} else {
				whereSql.append(" WHERE B.bg_st_pricezg >= " + minPrice + " ");
			}
		}
		String maxPrice = request.getParameter("maxPrice");
		if (maxPrice == null || maxPrice.equals("")) {
			maxPrice = "";
		} else {
			if (whereSql.length() != 0) {
				whereSql.append(" AND B.bg_st_pricezg <= " + maxPrice + " ");
			} else {
				whereSql.append(" WHERE B.bg_st_pricezg <= " + maxPrice + " ");
			}
		}

		String OrderByType = request.getParameter("OrderByType");
		if (OrderByType == null || OrderByType.equals("")) {
			OrderByType = "0";
		}

		String Up = request.getParameter("Up");
		if (Up == null || Up.equals("")) {
			Up = "0";
		}
		if (OrderByType.equals("0")) {
			if (Up.equals("0")) {
				orderBySql.append(" ORDER BY A.productNum DESC,B.bg_st_pricezg DESC ");
			} else {
				orderBySql.append(" ORDER BY A.productNum ASC,B.bg_st_pricezg ASC ");
			}
		} else if (OrderByType.equals("1")) {
			if (Up.equals("0")) {
				orderBySql.append(" ORDER BY B.bg_st_pricezg DESC ");
			} else {
				orderBySql.append(" ORDER BY B.bg_st_pricezg ASC ");
			}
		} else if (OrderByType.equals("2")) {
			if (Up.equals("0")) {
				orderBySql.append(" ORDER BY A.productNum DESC");
			} else {
				orderBySql.append(" ORDER BY A.productNum ASC");
			}
		}

		Integer page = StringUtils.toIntegerByObject(request.getParameter("page"));
		if (page == null || page <= 0) {
			page = Integer.valueOf(1);
		}
		getTopFootInfo();
		// 广告
		List<Map<String, Object>> list = baseBiz.queryForList("SELECT ag_st_url FROM ag_t_file,bj_t_advertisement WHERE ag_st_objid=bj_st_id AND bj_st_type='4'");
		model.addAttribute("ad_adlist", list);
		StringBuffer sql = new StringBuffer("SELECT A.productNum,B.* FROM bg_t_prodinfo B LEFT JOIN (SELECT  Count(b.bi_st_spsl) productNum,p.bg_st_num productId FROM bg_t_prodinfo p ,bi_t_orderprodrelate b WHERE b.bi_st_spnum = p.bg_st_num group by productId) A ON A.productId = B.bg_st_num ");

		// 建材商城
		sql.append(whereSql).append(orderBySql);
		totalSql.append(whereSql);
		PageBean pages = baseBiz.getPages(sql.toString(), totalSql.toString(), page, 18);
		// 查询商品总数
		String prouctTotalSql1 = "SELECT Count(*) FROM bg_t_prodinfo WHERE bg_st_randid IN (SELECT ab_st_value FROM ab_t_code WHERE ab_st_parent IN (SELECT ab_st_id FROM ab_t_code WHERE ab_st_parent IN (SELECT ab_st_id FROM ab_t_code WHERE ab_st_mark ='" + ab_st_value + "')))";
		String prouctTotalSql2 = "SELECT Count(*) FROM bg_t_prodinfo WHERE bg_st_randid IN (SELECT ab_st_value FROM ab_t_code WHERE ab_st_parent IN (SELECT ab_st_id FROM ab_t_code WHERE ab_st_mark ='" + ab_st_value + "'))";
		String prouctTotalSql3 = "SELECT Count(*) FROM bg_t_prodinfo WHERE bg_st_randid IN (SELECT ab_st_value FROM ab_t_code WHERE ab_st_mark ='" + ab_st_value + "')";
		int ProductTotal = baseBiz.queryForInt(prouctTotalSql1) + baseBiz.queryForInt(prouctTotalSql2) + baseBiz.queryForInt(prouctTotalSql3);
		model.addAttribute("type", type);
		model.addAttribute("shoptype", shoptype);
		model.addAttribute("OrderByType", OrderByType);
		model.addAttribute("Up", Up);
		model.addAttribute("minPrice", minPrice);
		model.addAttribute("maxPrice", maxPrice);
		model.addAttribute("page", page);
		model.addAttribute("ProductTotal", ProductTotal);
		model.addAttribute("shop_buldinglist", pages);// 分页对象
		// 推荐商品(根据商品时间倒叙,推荐5个新商品)
		List<Map<String, Object>> list3 = baseBiz.queryForList("SELECT bg_st_id,bg_st_name,bg_st_randid,bg_st_img1,bg_nm_storeNum,bg_st_pricezg,bg_st_img2,bg_st_img3,bg_st_img4 FROM bg_t_prodinfo where bg_st_fbtpe='1' and bg_st_randid='" + type + "'  ORDER BY bg_dt_startDate desc LIMIT 0,5");
		model.addAttribute("tuijian_buldinglist", list3);

		return "wyyf/index/shop/shopbuldingajax";
	}*/
	@RequestMapping(value = "GetProducts")
	@ResponseBody
	public void GetProducts() {
		StringBuffer orderBySql = new StringBuffer();
		StringBuffer whereSql = new StringBuffer(" WHERE B.bg_st_enable=1 and B.bg_st_isdel = 0 ");
		StringBuffer totalSql = new StringBuffer("SELECT Count(*) FROM bg_t_prodinfo B");
		String type = request.getParameter("type");
		String shoptype = request.getParameter("shoptype");// 类型ID（1=家具、2=建材、3=智能家居、4=软装配饰。码表自定义），5=购物券
		String ab_st_value = "PPLX_2";
		if ("building".equals(shoptype)) {
			//type = "2";
			ab_st_value = "PPLX_2";
		} else if ("electrical".equals(shoptype)) {
			//type = "1";
			ab_st_value = "PPLX_1";
		} else {
			//type = "2";
			ab_st_value = "PPLX_2";
		}
		// 获取侧边的类型
		List<Map<String, Object>> categoryList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = baseBiz.queryForMap("SELECT * FROM ab_t_code WHERE ab_st_mark ='" + ab_st_value + "'", null);
		categoryList.addAll(getChild_Ab_t_codeByPid(map.get("ab_st_id").toString()));
		categoryList.add(map);
		model.addAttribute("categoryList", categoryList);

		// 组装SQL
		String bg_st_randid = "";
		for (int i = 0; i < categoryList.size(); i++) {
			String tempAb_st_value = categoryList.get(i).get("ab_st_value").toString();
			if ((!tempAb_st_value.trim().equals("")&&type.equals(tempAb_st_value.trim()))||type.equals("1")||type.equals("2")) {
				bg_st_randid += tempAb_st_value + ",";
			}
		}

		bg_st_randid = bg_st_randid.substring(0, bg_st_randid.length() - 1);
		whereSql.append(" AND B.bg_st_randid in (" + bg_st_randid + ") ");

		String minPrice = request.getParameter("minPrice");
		if (minPrice == null || minPrice.equals("")) {
			minPrice = "";
		} else {
			if (whereSql.length() != 0) {
				whereSql.append(" AND B.bg_st_pricezg >= " + minPrice + " ");
			} else {
				whereSql.append(" WHERE B.bg_st_pricezg >= " + minPrice + " ");
			}
		}
		String maxPrice = request.getParameter("maxPrice");
		if (maxPrice == null || maxPrice.equals("")) {
			maxPrice = "";
		} else {
			if (whereSql.length() != 0) {
				whereSql.append(" AND B.bg_st_pricezg <= " + maxPrice + " ");
			} else {
				whereSql.append(" WHERE B.bg_st_pricezg <= " + maxPrice + " ");
			}
		}

		String OrderByType = request.getParameter("OrderByType");
		if (OrderByType == null || OrderByType.equals("")) {
			OrderByType = "0";
		}

		String Up = request.getParameter("Up");
		if (Up == null || Up.equals("")) {
			Up = "0";
		}
		if (OrderByType.equals("0")) {
			if (Up.equals("0")) {
				orderBySql.append(" ORDER BY A.productNum DESC,B.bg_st_pricezg DESC ");
			} else {
				orderBySql.append(" ORDER BY A.productNum ASC,B.bg_st_pricezg ASC ");
			}
		} else if (OrderByType.equals("1")) {
			if (Up.equals("0")) {
				orderBySql.append(" ORDER BY B.bg_st_pricezg DESC ");
			} else {
				orderBySql.append(" ORDER BY B.bg_st_pricezg ASC ");
			}
		} else if (OrderByType.equals("2")) {
			if (Up.equals("0")) {
				orderBySql.append(" ORDER BY A.productNum DESC");
			} else {
				orderBySql.append(" ORDER BY A.productNum ASC");
			}
		}
		Integer page = StringUtils.toIntegerByObject(request.getParameter("page"));
		if (page == null || page <= 0) {
			page = Integer.valueOf(1);
		}
		// 广告
		List<Map<String, Object>> list = baseBiz.queryForList("SELECT ag_st_url FROM ag_t_file,bj_t_advertisement WHERE ag_st_objid=bj_st_id AND bj_st_type='4'");
		model.addAttribute("ad_adlist", list);
		StringBuffer sql = new StringBuffer("SELECT A.productNum,B.* FROM bg_t_prodinfo B LEFT JOIN (SELECT  Count(b.bi_st_spsl) productNum,p.bg_st_num productId FROM bg_t_prodinfo p ,bi_t_orderprodrelate b WHERE b.bi_st_spnum = p.bg_st_num group by productId) A ON A.productId = B.bg_st_num ");

		// 建材商城
		sql.append(whereSql).append(orderBySql);
		totalSql.append(whereSql);
		PageBean pages = baseBiz.getPages(sql.toString(), totalSql.toString(), page, 18);
		// 查询商品总数
		String prouctTotalSql1 = "SELECT Count(*) FROM bg_t_prodinfo WHERE bg_st_randid IN (SELECT ab_st_value FROM ab_t_code WHERE ab_st_parent IN (SELECT ab_st_id FROM ab_t_code WHERE ab_st_parent IN (SELECT ab_st_id FROM ab_t_code WHERE ab_st_mark ='" + ab_st_value + "')))";
		String prouctTotalSql2 = "SELECT Count(*) FROM bg_t_prodinfo WHERE bg_st_randid IN (SELECT ab_st_value FROM ab_t_code WHERE ab_st_parent IN (SELECT ab_st_id FROM ab_t_code WHERE ab_st_mark ='" + ab_st_value + "'))";
		String prouctTotalSql3 = "SELECT Count(*) FROM bg_t_prodinfo WHERE bg_st_randid IN (SELECT ab_st_value FROM ab_t_code WHERE ab_st_mark ='" + ab_st_value + "')";
		pages.setTotalCount(baseBiz.queryForInt(prouctTotalSql1) + baseBiz.queryForInt(prouctTotalSql2) + baseBiz.queryForInt(prouctTotalSql3));
		this.createAjax(response, JsonUtils.getJsonData(pages),"application/json");

	}
	/*@RequestMapping(value = "GetProducts")
	@ResponseBody
	public void GetProducts() {
		StringBuffer orderBySql = new StringBuffer();
		StringBuffer whereSql = new StringBuffer(" WHERE B.bg_st_enable=1 and B.bg_st_isdel = 0 ");
		StringBuffer totalSql = new StringBuffer("SELECT Count(*) FROM bg_t_prodinfo B");
		String type = request.getParameter("type");
		String shoptype = request.getParameter("shoptype");// 类型ID（1=家具、2=建材、3=智能家居、4=软装配饰。码表自定义），5=购物券
		String ab_st_value = "PPLX_2";
		if ("building".equals(shoptype)) {
			//type = "2";
			ab_st_value = "PPLX_2";
		} else if ("electrical".equals(shoptype)) {
			//type = "1";
			ab_st_value = "PPLX_1";
		} else {
			//type = "2";
			ab_st_value = "PPLX_2";
		}
		// 获取侧边的类型
		List<Map<String, Object>> categoryList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = baseBiz.queryForMap("SELECT * FROM ab_t_code WHERE ab_st_mark ='" + ab_st_value + "'", null);
		categoryList.addAll(getChild_Ab_t_codeByPid(map.get("ab_st_id").toString()));
		categoryList.add(map);
		model.addAttribute("categoryList", categoryList);

		// 组装SQL
		String bg_st_randid = "";
		for (int i = 0; i < categoryList.size(); i++) {
			String tempAb_st_value = categoryList.get(i).get("ab_st_value").toString();
			if (!tempAb_st_value.trim().equals("")&&type.equals(tempAb_st_value.trim())) {
				bg_st_randid += tempAb_st_value + ",";
			}
		}

		bg_st_randid = bg_st_randid.substring(0, bg_st_randid.length() - 1);
		whereSql.append(" AND B.bg_st_randid in (" + bg_st_randid + ") ");

		String minPrice = request.getParameter("minPrice");
		if (minPrice == null || minPrice.equals("")) {
			minPrice = "";
		} else {
			if (whereSql.length() != 0) {
				whereSql.append(" AND B.bg_st_pricezg >= " + minPrice + " ");
			} else {
				whereSql.append(" WHERE B.bg_st_pricezg >= " + minPrice + " ");
			}
		}
		String maxPrice = request.getParameter("maxPrice");
		if (maxPrice == null || maxPrice.equals("")) {
			maxPrice = "";
		} else {
			if (whereSql.length() != 0) {
				whereSql.append(" AND B.bg_st_pricezg <= " + maxPrice + " ");
			} else {
				whereSql.append(" WHERE B.bg_st_pricezg <= " + maxPrice + " ");
			}
		}

		String OrderByType = request.getParameter("OrderByType");
		if (OrderByType == null || OrderByType.equals("")) {
			OrderByType = "0";
		}

		String Up = request.getParameter("Up");
		if (Up == null || Up.equals("")) {
			Up = "0";
		}
		if (OrderByType.equals("0")) {
			if (Up.equals("0")) {
				orderBySql.append(" ORDER BY A.productNum DESC,B.bg_st_pricezg DESC ");
			} else {
				orderBySql.append(" ORDER BY A.productNum ASC,B.bg_st_pricezg ASC ");
			}
		} else if (OrderByType.equals("1")) {
			if (Up.equals("0")) {
				orderBySql.append(" ORDER BY B.bg_st_pricezg DESC ");
			} else {
				orderBySql.append(" ORDER BY B.bg_st_pricezg ASC ");
			}
		} else if (OrderByType.equals("2")) {
			if (Up.equals("0")) {
				orderBySql.append(" ORDER BY A.productNum DESC");
			} else {
				orderBySql.append(" ORDER BY A.productNum ASC");
			}
		}
		Integer page = StringUtils.toIntegerByObject(request.getParameter("page"));
		if (page == null || page <= 0) {
			page = Integer.valueOf(1);
		}
		// 广告
		List<Map<String, Object>> list = baseBiz.queryForList("SELECT ag_st_url FROM ag_t_file,bj_t_advertisement WHERE ag_st_objid=bj_st_id AND bj_st_type='4'");
		model.addAttribute("ad_adlist", list);
		StringBuffer sql = new StringBuffer("SELECT A.productNum,B.* FROM bg_t_prodinfo B LEFT JOIN (SELECT  Count(b.bi_st_spsl) productNum,p.bg_st_num productId FROM bg_t_prodinfo p ,bi_t_orderprodrelate b WHERE b.bi_st_spnum = p.bg_st_num group by productId) A ON A.productId = B.bg_st_num ");

		// 建材商城
		sql.append(whereSql).append(orderBySql);
		totalSql.append(whereSql);
		PageBean pages = baseBiz.getPages(sql.toString(), totalSql.toString(), page, 18);
		// 查询商品总数
		String prouctTotalSql1 = "SELECT Count(*) FROM bg_t_prodinfo WHERE bg_st_randid IN (SELECT ab_st_value FROM ab_t_code WHERE ab_st_parent IN (SELECT ab_st_id FROM ab_t_code WHERE ab_st_parent IN (SELECT ab_st_id FROM ab_t_code WHERE ab_st_mark ='" + ab_st_value + "')))";
		String prouctTotalSql2 = "SELECT Count(*) FROM bg_t_prodinfo WHERE bg_st_randid IN (SELECT ab_st_value FROM ab_t_code WHERE ab_st_parent IN (SELECT ab_st_id FROM ab_t_code WHERE ab_st_mark ='" + ab_st_value + "'))";
		String prouctTotalSql3 = "SELECT Count(*) FROM bg_t_prodinfo WHERE bg_st_randid IN (SELECT ab_st_value FROM ab_t_code WHERE ab_st_mark ='" + ab_st_value + "')";
		pages.setTotalCount(baseBiz.queryForInt(prouctTotalSql1) + baseBiz.queryForInt(prouctTotalSql2) + baseBiz.queryForInt(prouctTotalSql3));
		this.createAjax(response, JsonUtils.getJsonData(pages),"application/json");

	}*/

	/**
	 * 家具商城
	 * 
	 * @return
	 */
	@RequestMapping(value = "shophome")
	public String home() {
		getTopFootInfo();
		// 广告
		List<Map<String, Object>> list = baseBiz.queryForList("SELECT ag_st_url FROM ag_t_file,bj_t_advertisement WHERE ag_st_objid=bj_st_id AND bj_st_type='4'");
		model.addAttribute("ad_adlist", list);

		// 家具商城
		List<Map<String, Object>> list2 = baseBiz.queryForList("SELECT bg_st_id,bg_st_name,bg_st_randid,bg_nm_storeNum,bg_st_pricezg,bg_st_img1,bg_st_img2,bg_st_img3,bg_st_img4 FROM bg_t_prodinfo where bg_st_fbtpe='1' and bg_st_randid='1'");
		model.addAttribute("shop_buldinglist", list2);

		// 推荐商品
		List<Map<String, Object>> list3 = baseBiz.queryForList("SELECT bg_st_id,bg_st_name,bg_st_randid,bg_st_img1,bg_nm_storeNum,bg_st_pricezg,bg_st_img2,bg_st_img3,bg_st_img4 FROM bg_t_prodinfo where bg_st_fbtpe='1' and bg_st_randid='1'  ORDER BY bg_dt_startDate desc LIMIT 0,5");
		model.addAttribute("tuijian_buldinglist", list3);

		return "wyyf/index/shop/shophome";
	}

	// -------------------------------------------------------
	/***
	 * 进入登录界面
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "loginp")
	public String loginp(RedirectAttributes redirectAttributes) {

		String oldRequestUrl = request.getParameter("oldRequestUrl");
		InterfaceServiceAction serviceAction;

		model.addAttribute("oldRequestUrl", oldRequestUrl);
		redirectAttributes.addFlashAttribute("old", oldRequestUrl);
		return "wyyf/index/loginp";
	}

	/***
	 * 进入注册界面
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "intoregtie")
	public String intoregtie() {
		// 首先查询出所有的用户类型
		String sql1 = "SELECT * FROM ab_t_code WHERE ab_st_parent=(SELECT ab_st_id from ab_t_code where ab_st_mark='USERTYPE') and ab_st_mark !='USERTYPE_2'";
		List<Map<String, Object>> userTypes = baseBiz.queryForList(sql1);
		// 根据用户类型找出它所包含的子分类
		for (Map<String, Object> item : userTypes) {
			String parentCode = item.get("ab_st_id").toString();
			String sql2 = "SELECT * FROM ab_t_code where ab_st_parent='" + parentCode + "'";
			List<Map<String, Object>> childTypes = baseBiz.queryForList(sql2);
			item.put("childs", childTypes);
		}
		model.addAttribute("userType", userTypes);
		return "wyyf/index/i1/sign";
	}

	@RequestMapping(value = "down")
	public String down() {
		return "wyyf/index/down/download";
	}

	/***
	 * 注册保存---该方法要修改，请注意
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "intoregtieSave")
	public String intoregtieSave(Ae_t_sysuser ae, Ba_t_Labour labour, Bb_t_ShopUser shopUser, MultipartHttpServletRequest multipartRequest) {
		String returnStr = "";
		try {
			if (!org.springframework.util.StringUtils.hasText(ae.getUsername()) || !org.springframework.util.StringUtils.hasText(ae.getPassword()) || !org.springframework.util.StringUtils.hasText(ae.getAe_st_type()) || !org.springframework.util.StringUtils.hasText(ae.getYzm())) {
				throw new RuntimeException("帐号/密码/验证码/类型不能为空");
			}
			String telcode = StringUtils.toStringByObject(ae.getYzm(), true); // 验证码
			String code = ae.getYzm().toLowerCase();
			Encoder e = new MD5Encoder();// 使用security3 的MD5加密技
			// 创建批量保存事务对象
			List<BizTransUtil> list = new ArrayList<BizTransUtil>();
			// 构建数据--新增---临时添加类型----
			ae.setAe_st_id(StringUtils.getUUID32());// ID
			ae.setAe_st_userkind("3");// 用户类型
			ae.setPassword(e.encrypt(ae.getPassword(), ae.getUsername()));
			ae.setAe_dt_addDate(getcuttDate());// 创建时间
			ae.setAe_st_lockstate("1");// 账户锁定状态：1正常，2账户停用3.注销
			ae.setAe_st_type(ae.getAe_st_type());// 这里先写死， 这里的数据来自前台
			ae.setMb(200);
			list.add(new BizTransUtil(ae));

			// 用户类型表添加
			String usertype = ae.getAe_st_type();
			if ("3".equals(usertype)) {

				if (!telcode.equals(code)) {
					model.addAttribute("msg", "验证码错误");
				} else {
					// 添加商家附加信息
					String sjid = StringUtils.getUUID32(); // 商家id
					ae.setAe_st_objid(sjid);
					ae.setAe_st_objtype("bb_t_shopuser");
					shopUser.setBb_st_id(sjid);
					shopUser.setBb_st_userid(ae.getAe_st_id());
					shopUser.setBb_dt_addDate(getcuttDate());
					list.add(new BizTransUtil(shopUser));
				}

			} else if ("4".equals(usertype) || "5".equals(usertype) || "6".equals(usertype)) {
				// 添加师傅附加信息
				String sfid = StringUtils.getUUID32(); // 师傅id
				ae.setAe_st_objid(sfid);
				ae.setAe_st_objtype("ba_t_labour");
				labour.setBa_st_id(sfid);
				labour.setBa_st_userid(ae.getAe_st_id());
				labour.setBa_dt_addDate(getcuttDate());
				list.add(new BizTransUtil(labour));
			}

			// 角色--添加
			String type = ae.getAe_st_type();
			String roleid = (String) baseBiz.queryForObject("select ac_st_id from ac_t_sysrole where ac_st_code = ?", new Object[] { "2".equals(type) ? "WZGLY" : ("3".equals(type) ? "SJ" : ("4".equals(type) || "5".equals(type) || "6".equals(type) ? "SF" : "HY")) }, String.class);
			Af_t_sysuserrole rf = new Af_t_sysuserrole(true);
			rf.setAe_st_id(ae.getAe_st_id());
			rf.setAc_st_id(roleid);
			list.add(new BizTransUtil(rf));

			// 上传的相关图片
			List<Ag_t_file> fileList = ae.getFileList();
			if (fileList != null && fileList.size() > 0) {
				MultipartFile mf = null;
				for (Ag_t_file file : fileList) {
					// 如果没有选择，自定义类型，跳过
					if (!org.springframework.util.StringUtils.hasText(file.getAg_st_mark())) {
						continue;
					}
					mf = multipartRequest.getFile(file.getAg_st_addUserId());
					String uploadPath = "";
					if (mf != null && mf.getSize() > 0) {
						uploadPath = UploadFile.uploadInputStream(mf.getInputStream(), mf.getOriginalFilename(), "images/business/ad");
						file.setAg_nm_size(mf.getSize());// 文件属性大小
						file.setAg_st_format(mf.getContentType());// 文件格式
					}
					file.setAg_dt_addDate(getcuttDate());// 创建时间
					file.setAg_st_objid(ae.getAe_st_id());// 文件归属ID
					file.setAg_st_objtype("ae_t_sysuser");// 文件对应的对象
					file.setAg_st_type("5");// 文件类型
					file.setAg_st_url(uploadPath);// //文件 的存储地址
					file.setAg_st_id(StringUtils.getUUID32());
					list.add(new BizTransUtil(file));
				}
			}
			// 执行操作
			if (baseBiz.executesTRANS(list)) {
				model.addAttribute("msg", "注册成功");
				returnStr = "redirect:/index/loginp";
			} else {
				model.addAttribute("msg", "注册失败");
				returnStr = "wyyf/index/fail";
			}
		} catch (Exception e) {
			model.addAttribute("msg", "注册失败");
			returnStr = "wyyf/index/fail";
		}
		return returnStr;
	}

	/**
	 * 立即购买
	 * 
	 * @return
	 */
	@RequestMapping(value = "buy")
	@ResponseBody
	public void buy() {
		Ae_t_sysuser ae_t_sysuser = getTopFootInfo();
		if (null == ae_t_sysuser || null == ae_t_sysuser.getAe_st_id() || "" == ae_t_sysuser.getAe_st_id()) {
			String oldUrlRedirect = this.request.getRequestURL() + "?" + this.request.getQueryString();
			model.addAttribute("oldRequestUrl", oldUrlRedirect);
			this.createAjax(response, "index/loginp?oldRequestUrl=" + this.session.getValue("RedirectUrl"));
			return;
		} else {
			String pid = request.getParameter("id");// 商品ID
			int num = Integer.parseInt(request.getParameter("num"));
			this.createAjax(response, "index/buyCart?pid=" + pid + "&num=" + num + "&page=1");
		}
		// return "wyyf/index/shop/buy";
	}

	/**
	 * 立即购买
	 * 
	 * @return
	 */
	@RequestMapping(value = "buyCart")
	public String buyCart() {
		this.session.putValue("RedirectUrl", this.request.getRequestURL() + "?" + this.request.getQueryString());
		// 验证用户是否登录
		Ae_t_sysuser ae_t_sysuser = getTopFootInfo();
		if (null == ae_t_sysuser || null == ae_t_sysuser.getAe_st_id() || "" == ae_t_sysuser.getAe_st_id()) {
			String oldUrlRedirect = this.request.getRequestURL() + "?" + this.request.getQueryString();
			model.addAttribute("oldRequestUrl", oldUrlRedirect);
			return "wyyf/index/loginp";
		}
		// 获取购买的商品编号
		String pid = request.getParameter("pid");// 商品ID
		// 获取购买商品的数量
		String numStr = request.getParameter("num");
		// 将商品加入购物车，如果购物车已经存在该商品，则商品数应该加num，如果购物车不存在在应该写入
		if (pid != null && numStr != null && !pid.trim().equals("") && !numStr.trim().equals("")) {
			int num = Integer.valueOf(numStr);
			List<Map<String, Object>> listx = baseBiz.queryForList("SELECT * from bn_t_prodcart proCart where proCart.bn_st_memberid='" + ae_t_sysuser.getAe_st_id() + "' and proCart.bn_st_prodid = '" + pid + "'");
			if (listx.size() == 0) {
				Bn_t_prodcart pro = new Bn_t_prodcart();
				String id = getRandomString(32);
				List<Map<String, Object>> isExist = baseBiz.queryForList("select * from bn_t_prodcart where bn_st_id = '" + id + "'");
				while (isExist.size() > 0) {
					id = getRandomString(32);
					isExist = baseBiz.queryForList("select * from bn_t_prodcart where bn_st_id = '" + id + "'");
				}
				pro.setBn_dt_addDate(getcuttDate());
				pro.setBn_st_addUserId(ae_t_sysuser.getAe_st_addUserId());
				pro.setBn_st_memberid(ae_t_sysuser.getAe_st_id());
				pro.setBn_st_prodid(pid);
				pro.setBn_st_prodnum(num);
				pro.setBn_st_id(id);
				// 存储
				baseBiz.addTRANS(pro);
			} else {
				listx.get(0).put("bn_st_prodnum", Integer.parseInt(listx.get(0).get("bn_st_prodnum").toString()) + num);
				baseBiz.updateTRANS(listx.get(0), Bn_t_prodcart.class);
			}
		}
		// 重新查询购物车的商品

		List<Map<String, Object>> BuyCarList = baseBiz.queryForList("SELECT proCart.bn_st_prodid,proCart.bn_st_prodnum,proCart.bn_st_id,pro.bg_st_fbtpe,pro.bg_st_id,pro.bg_st_name,pro.bg_nm_storeNum,pro.bg_st_img1,pro.bg_st_prodIntro,pro.bg_st_summary,pro.bg_st_pricezg,pro.bg_st_pricetj FROM bn_t_prodcart proCart JOIN bg_t_prodinfo pro ON  proCart.bn_st_prodid=pro.bg_st_id and proCart.bn_st_memberid ='" + ae_t_sysuser.getAe_st_id() + "'");
		model.addAttribute("BuyCarList", BuyCarList);
		return "wyyf/index/buyCart";

	}

	/**
	 * 删除购物车单个商品
	 * 
	 * @return
	 */
	@RequestMapping(value = "DeleteProductFromBuyCart")
	@ResponseBody
	public void DeleteProductFromBuyCart() {
		Ae_t_sysuser ae_t_sysuser = getTopFootInfo();
		if (ae_t_sysuser == null) {
			this.createAjax(response, "请先登录");
		}
		String productId = request.getParameter("productId");// 商品ID
		if (productId == null || productId.trim().equals("")) {
			this.createAjax(response, "删除购物车商品失败.");
		}
		String sql = "DELETE FROM bn_t_prodcart WHERE bn_st_memberid = '" + ae_t_sysuser.getAe_st_id() + "' AND bn_st_prodid = '" + productId + "'";
		int num = baseBiz.executeTRANS(sql);
		if (num != 0) {
			this.createAjax(response, "true");
		} else {
			this.createAjax(response, "删除购物车商品失败.");
		}
	}

	/**
	 * 删除购物车内的数据并且传递给后来界面
	 */
	@RequestMapping(value = "DeleteGetCart")
	public String DeleteGetCart() {
		this.session.putValue("RedirectUrl", this.request.getRequestURL() + "?" + this.request.getQueryString());
		Ae_t_sysuser ae_t_sysuser = getTopFootInfo();
		if (null == ae_t_sysuser || null == ae_t_sysuser.getAe_st_id() || "" == ae_t_sysuser.getAe_st_id()) {
			return "wyyf/index/loginp";
		}
		String pids = request.getParameter("pids");// 商品ID
		if (pids.endsWith(",")) {
			pids = pids.substring(0, pids.length() - 1);
		}
		String nums = request.getParameter("nums");// 商品ID

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		// for(int i = 0 ;i<pids.length;i++){
		// if(i%2==0){
		List<Map<String, Object>> user = baseBiz.queryForList("SELECT * FROM bq_t_usefuladdress WHERE bq_st_memberid='" + ae_t_sysuser.getAe_st_id() + "'");// ae_t_sysuser
		List<Map<String, Object>> list1 = baseBiz.queryForList("select bn_st_id,bn_st_prodid from bn_t_prodcart where bn_st_id in (" + pids + ")");
		String ids = "";
		for (int h = 0; h < list1.size(); h++) {
			if (h != list1.size() - 1) {
				ids += "'" + (String) list1.get(h).get("bn_st_prodid") + "',";
			} else {
				ids += "'" + (String) list1.get(h).get("bn_st_prodid") + "'";
			}
		}
		list = baseBiz.queryForList("select * from bg_t_prodinfo where bg_st_id in (" + ids + ")");
		String pid[] = pids.split(",");
		for (int i = 0; i < pid.length; i++) {
			baseBiz.executeTRANS("delete from bn_t_prodcart where bn_st_id=?", pid[i]);
		}
		List<Map<String, Object>> province = baseBiz.queryForList("select * from aa26 where d_parent=0");
		String numArr[] = nums.split("-");
		float qaz = 0.0f;
		for (int t = 0; t < list.size(); t++) {
			list.get(t).put("num", numArr[t]);// bg_st_fbtpe==2
			if ("2".equals(list.get(t).get("bg_st_fbtpe").toString())) {
				qaz += Float.valueOf(list.get(t).get("bg_st_pricetj").toString()) * Integer.parseInt(list.get(t).get("num").toString());
			} else {
				qaz += Float.valueOf(list.get(t).get("bg_st_pricezg").toString()) * Integer.parseInt(list.get(t).get("num").toString());
			}

		}
		// 创建订单
		String Orderid = getRandomString(32);
		List<Map<String, Object>> isExistorder = baseBiz.queryForList("select * from bh_t_orderform where bh_st_id = '" + Orderid + "'");
		while (isExistorder.size() > 0) {
			Orderid = getRandomString(32);
			isExistorder = baseBiz.queryForList("select * from bh_t_orderform where bh_st_id = '" + Orderid + "'");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Bh_t_orderform order = new Bh_t_orderform();
		order.setBh_st_id(Orderid);
		order.setBh_st_bbid(ae_t_sysuser.getAe_st_id());
		order.setBh_st_ddnum("D" + sdf.format(Calendar.getInstance().getTime()) + MainAction.getRandomInt(3));
		order.setBh_st_spprice(qaz);
		order.setBh_st_ddstate(1 + "");
		order.setBh_st_source(2 + "");
		order.setBh_st_memberId(ae_t_sysuser.getAe_st_id());
		order.setBh_dt_addDate(getcuttDate());
		baseBiz.addTRANS(order);
		Bi_t_OrderProdRelate rel = new Bi_t_OrderProdRelate();
		rel.setBi_st_ddnum(order.getBh_st_ddnum());
		rel.setBi_st_bbid(ae_t_sysuser.getAe_st_id());
		rel.setBi_dt_addDate(getcuttDate());
		for (int i = 0; i < list.size(); i++) {
			String id = getRandomString(32);
			List<Map<String, Object>> isExist = baseBiz.queryForList("select * from bi_t_orderprodrelate where bi_st_id = '" + id + "'");
			while (isExist.size() > 0) {
				id = getRandomString(32);
				isExist = baseBiz.queryForList("select * from bi_t_orderprodrelate where bi_st_id = '" + id + "'");
			}
			rel.setBi_st_id(id);
			rel.setBi_st_spnum(list.get(i).get("bg_st_id").toString());
			if ("2".equals(list.get(i).get("bg_st_id"))) {
				rel.setBi_st_spprice((double) list.get(i).get("bg_st_pricetj"));
			} else {
				rel.setBi_st_spprice((double) list.get(i).get("bg_st_pricezg"));
			}
			rel.setBi_st_spsl(Integer.parseInt(list.get(i).get("num").toString()));
			baseBiz.addTRANS(rel);
		}
		model.addAttribute("list", list);// 商品信息
		model.addAttribute("province", province);
		model.addAttribute("user", user);// 用户地址信息
		model.addAttribute("qaz", qaz);
		model.addAttribute("orderID", Orderid);// 订单ID
		return "wyyf/index/shop/buy";
	}

	/**
	 * 确认支付
	 * 
	 * @return
	 */

	@RequestMapping(value = "payfor")
	public String payfor() {
		this.session.putValue("RedirectUrl", this.request.getRequestURL() + "?" + this.request.getQueryString());
		Ae_t_sysuser ae_t_sysuser = getTopFootInfo();
		if (null == ae_t_sysuser || null == ae_t_sysuser.getAe_st_id() || "" == ae_t_sysuser.getAe_st_id()) {
			return "wyyf/index/loginp";
		}
		// orderID="+orderID+"&addrID="+addrID+"&remark="+remark;
		String orderID = request.getParameter("orderID");// 订单ID
		String addrID = request.getParameter("addrID");// 地址ID
		String remark = request.getParameter("remark");// 备注ID
		List<Map<String, Object>> orderList = baseBiz.queryForList("select * from bh_t_orderform where bh_st_id = '" + orderID + "'");
		List<Map<String, Object>> AddrList = baseBiz.queryForList("select * from bq_t_usefuladdress where bq_st_id = '" + addrID + "'");
		if (AddrList.size() > 0 && orderList.size() > 0) {
			orderList.get(0).put("bh_st_shname", AddrList.get(0).get("bq_st_name").toString());
			orderList.get(0).put("bh_st_shphone", AddrList.get(0).get("bq_st_phone").toString());
			orderList.get(0).put("bh_st_shaddress", AddrList.get(0).get("bq_st_sheng").toString() + AddrList.get(0).get("bq_st_shi").toString() + AddrList.get(0).get("bq_st_xian").toString() + AddrList.get(0).get("bq_st_adress").toString());
			orderList.get(0).put("bh_st_remark", remark);
			// baseBiz.updateTRANS(orderList.get(0), Bh_t_orderform.class);
			String add = orderList.get(0).get("bh_st_shaddress").toString();
			String price = orderList.get(0).get("bh_st_spprice").toString();
			model.addAttribute("addr", add);
			model.addAttribute("price", price);
			model.addAttribute("orderID", orderID);
			return "wyyf/index/pay";
		} else {
		}
		return "";
	}
	@RequestMapping(value = "paytest")
	public String paytest() {
		return "wyyf/index/paytest";
	}
	@RequestMapping(value = "aliPayTest")
	public void aliPayTest(Writer writer) throws IOException{
		String payID="alipay";
	    //支付类型
        String payment_type = "1";
        String return_url = "/Main/Index";
        String show_url = "";
        String anti_phishing_key = "";
        //若要使用请调用类文件submit中的query_timestamp函数        //客户端的IP地址
        String exter_invoke_ip = "";
        //非局域网的外网IP地址，如：221.0.0.1
        //把请求参数打包成数组
        HashMap<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset.toLowerCase());
        sParaTemp.put("service", "create_direct_pay_by_user");
        sParaTemp.put("payment_type", payment_type);
        //sParaTemp.Add("notify_url", notify_url);
        sParaTemp.put("return_url", return_url);
        sParaTemp.put("seller_email", AlipayConfig.seller_email);
        sParaTemp.put("out_trade_no", "123213243213");
        sParaTemp.put("subject", "测试");
        sParaTemp.put("total_fee",0.1 + "");
        sParaTemp.put("body", "ces d");
        if (payID != "alipay")
        {
            sParaTemp.put("defaultbank", payID);
        }
        sParaTemp.put("show_url", show_url);
        sParaTemp.put("anti_phishing_key", anti_phishing_key);
        sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
        //建立请求
        String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
        writer.write(sHtmlText);
	}
	/**
	 * 预约付款
	 */
	@RequestMapping(value = "pay")
	public String pay() {
		return "wyyf/index/pay";
	}

	/**
	 * 地区根据父id获取子数据
	 */
	@RequestMapping(value = "getCoupon")
	@ResponseBody
	public void getCoupon(boolean priceUp, boolean expireTimeUp, boolean effectTimeUp, int page, int pageSize) {
		String jsonData = "{success:false,msg:\"未获取到信息!\"}";
		try {
			String sql = "SELECT * FROM bg_t_prodinfo WHERE bg_st_randid = '5' ";

			List<Map<String, Object>> childAreas = baseBiz.queryForList("SELECT * FROM ");
			jsonData = "{\"success\":true,result:[" + JsonUtils.getJsonDataFromCollection(childAreas) + "]}";
			System.out.println(childAreas);

		} catch (Exception ex) {
			jsonData = "{success:false,msg:\"获取数据出错!\"}";
		}
		this.createAjax(response, jsonData);
	}

	@RequestMapping(value = "GetProductTyps")
	@ResponseBody
	public void GetProductTyps() {
		String jsonData = "{success:false,msg:\"未获取到信息!\"}";
		try {
			String type = request.getParameter("type");// 商品ID
			String sql = "SELECT * FROM ab_t_code WHERE ab_st_mark ='" + type + "'";
			// Ab_t_code entry = (Ab_t_code)baseBiz.queryForObject(sql,
			// Ab_t_code.class);
			Map<String, Object> map = baseBiz.queryForMap(sql, null);
			List<Map<String, Object>> list = getChild_Ab_t_codeByPid(map.get("ab_st_id").toString());
			jsonData = "{\"success\":true,result:[" + JsonUtils.getJsonDataFromCollection(list) + "]}";

		} catch (Exception ex) {
			jsonData = "{success:false,msg:\"获取数据出错!" + ex.getMessage() + "\"}";
		}
		this.createAjax(response, jsonData);
	}

	@RequestMapping(value = "setCurrentCity")
	public String setCurrentCity() {
		this.session.putValue("RedirectUrl", this.request.getRequestURL() + "?" + this.request.getQueryString());
		String CurrentCity = request.getParameter("cityId");// 商品ID
		this.session.putValue("CurrentCityCode", CurrentCity);
		getTopFootInfo();
		return "wyyf/index/index";
	}

	@RequestMapping(value = "GetArea")
	@ResponseBody
	public void GetArea() {
		String jsonData = "";
		try {
			getTopFootInfo();
			String sql2 = "select * from aa26 where d_parent=0";
			List<Map<String, Object>> list = baseBiz.queryForList(sql2);
			for (int i = 0; i < list.size(); i++) {
				List<Map<String, Object>> childCityList = baseBiz.queryForList("select * from aa26 where d_parent='" + list.get(i).get("d_code").toString() + "'");
				list.get(i).put("childCityList", childCityList);
			}
			jsonData = JsonUtils.getJsonDataFromCollection(list);
		} catch (Exception ex) {

		}
		this.createAjax(response, jsonData);
	}

	@RequestMapping(value = "DeleteCart")
	@ResponseBody
	public void DeleteCartData() {
		Boolean jsonData = false;
		String pid = request.getParameter("pid");// 商品ID
		Integer isSuccess = baseBiz.executeTRANS("delete from bn_t_prodcart where bn_st_id=?", pid);
		if (isSuccess > 0) {
			jsonData = true;
		}
		this.createAjax(response, jsonData);
	}

	/**
	 * 更新购物车商品的数量
	 */
	@RequestMapping(value = "UpdateProductNumForBuyCart")
	@ResponseBody
	public void UpdateProductNumForBuyCart() {
		String pid = request.getParameter("pid");
		String num = request.getParameter("num");
		baseBiz.executeTRANS("UPDATE bn_t_prodcart SET bn_st_prodnum=? WHERE bn_st_id=?", num, pid);
		this.createAjax(response, "");
	}

	public List<Map<String, Object>> getChild_Ab_t_codeByPid(String pid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "SELECT * FROM ab_t_code WHERE ab_st_parent='" + pid + "'";
		List<Map<String, Object>> temList = baseBiz.queryForList(sql);
		for (int i = 0; i < temList.size(); i++) {
			list.addAll(getChild_Ab_t_codeByPid(temList.get(i).get("ab_st_id").toString()));
		}
		list.addAll(temList);
		return list;
	}
	/*public List<Map<String, Object>> getnvgList_Ab_t_codeByPid(String pid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "SELECT * FROM ab_t_code WHERE ab_st_parent='" + pid + "'";
		List<Map<String, Object>> temList = baseBiz.queryForList(sql);
		for (int i = 0; i < temList.size(); i++) {
			temList.get(i).put("childList", getnvgList_Ab_t_codeByPid(temList.get(i).get("ab_st_id").toString()));
		}
		list.addAll(temList);
		return list;
	}*/
	public List<Map<String, Object>> getnvgList_Ab_t_codeByPid(String pid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "SELECT * FROM ab_t_code WHERE ab_st_parent='" + pid + "'";
		List<Map<String, Object>> temList = baseBiz.queryForList(sql);
		for (int i = 0; i < temList.size(); i++) {
			temList.get(i).put("childList", getnvgList_Ab_t_codeByPid(temList.get(i).get("ab_st_id").toString()));
		}
		list.addAll(temList);
		return list;
	}
	//付款
	@RequestMapping(value = "toPay")
	public String toPay() throws UnsupportedEncodingException {
		this.session.putValue("RedirectUrl", this.request.getRequestURL() + "?" + this.request.getQueryString());
		getTopFootInfo();
		Ae_t_sysuser ae_t_sysuser = getTopFootInfo();
		if (null == ae_t_sysuser || null == ae_t_sysuser.getAe_st_id() || "" == ae_t_sysuser.getAe_st_id()) {
			this.createAjax(response, "index/loginp");
		}
		String yy_maney=request.getParameter("yy_maney");   //预约金额 
		//String money = request.getParameter("money");       
		String type = request.getParameter("type");
		String rid = request.getParameter("rid");
		double current;
		String address = request.getParameter("address");
		String  area = request.getParameter("area");
		System.out.println(area);
		//如果师傅的类型等于3的话 就是剩余2
	    if(type.equals("3"))
	    {
	    	current=Double.valueOf(area)*2;
	    }
		//判断预约金是否为空 如果不为空则给100
		else if(yy_maney != null  && type.equals("7") || type.equals("8"))
		{
			current =Double.valueOf(yy_maney);
		}
		//否则就面积剩师傅价格
		else {
			String sql="select ba_st_price from ba_t_labour where ba_st_userid ='"+rid+"'";
			String  price=baseBiz.queryString(sql);
			current=Double.valueOf(price)*Double.valueOf(area);
		}
		String Orderid = GetUniqueId("bh_t_orderform", "bh_st_id");
		String OrderNum = GetOrderNum();
		// 生成订单
		Bh_t_orderform order = new Bh_t_orderform();
		order.setBh_st_id(Orderid);
		order.setBh_st_bbid(rid);
		order.setBh_st_ddnum(OrderNum);
		order.setBh_st_spprice(current);
		order.setBh_st_ddstate(1 + "");
		order.setBh_st_source(1 + "");
		order.setBh_st_memberId(ae_t_sysuser.getAe_st_id());
		order.setBh_dt_addDate(getcuttDate());
		baseBiz.addTRANS(order);
		model.addAttribute("orderId", Orderid);
		model.addAttribute("price", current);
		model.addAttribute("area", area);
		model.addAttribute("addr", address);
		
		
		String what = request.getParameter("what");
		if(what != null){
			return "wyyf/index/topayGZ";
		}else{
			return "wyyf/index/topay";
		}
		
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
			String OrderNum = GetOrderNum();
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
			model.addAttribute("orderId", Orderid);
			model.addAttribute("price", money);
			
			return "wyyf/index/topay2";
		}

	@RequestMapping(value = "PayMoneyEnd")
	public String PayMoneyEnd() {
		this.session.putValue("RedirectUrl", this.request.getRequestURL() + "?" + this.request.getQueryString());
		String success = request.getParameter("success");
		String out_trade_no = request.getParameter("out_trade_no");
		if (success.equals("success")) {
			Map<String, Object> map = this.baseBiz.queryForMap("SELECT * FROM bh_t_orderform WHERE bh_st_ddnum=?", new String[] { out_trade_no });
			this.baseBiz.updateTRANS(map, Bh_t_orderform.class);
		}
		model.addAttribute("success", success);
		return "wyyf/index/payMoneyEnd";
	}

	@RequestMapping(value = "PayMoney")
	@ResponseBody
	public void PayMoney() {

		String type = request.getParameter("type");
		String orderId = request.getParameter("orderId");
		String channel = "";
		if (type != null && type.toString().equals("1")) {
			channel = "alipay_wap";
		} else if (type != null && type.toString().equals("2")) {
			channel = "wx_pub";
		} else if (type != null && type.toString().equals("3")) {
			channel = "upacp_wap";
		} else {
			channel = "alipay";
		}
		Map<String, Object> map = this.baseBiz.queryForMap("SELECT * FROM bh_t_orderform WHERE bh_st_id=?", new String[] { orderId });
		if (!map.get("bh_st_ddstate").toString().equals("1")) {
			this.createAjax(response, "1");
		}
		PingCharge ce = new PingCharge();
		int price = (int) Float.parseFloat(map.get("bh_st_spprice").toString());
		String Subject = map.get("bh_st_ddnum").toString();
		String OrderId = map.get("bh_st_ddnum").toString();
		;
		Charge charge = ce.charge(channel, price, Subject, Subject, OrderId);
		this.createAjax(response, charge);
	}

	/**
	 * 
	 * 生成订单接口
	 */
	@RequestMapping(value = "CreateOrder")
	@ResponseBody
	public void CreateOrder() {

		Ae_t_sysuser ae_t_sysuser = getTopFootInfo();
		if (null == ae_t_sysuser || null == ae_t_sysuser.getAe_st_id() || "" == ae_t_sysuser.getAe_st_id()) {
			String oldUrlRedirect = this.request.getRequestURL() + "?" + this.request.getQueryString();
			model.addAttribute("oldRequestUrl", oldUrlRedirect);
			this.createAjax(response, "index/loginp?oldRequestUrl=" + this.session.getValue("RedirectUrl"));
			return;
		}
		String pid = request.getParameter("pids");// 商品ID组合
		if (pid.endsWith(",")) {
			pid = pid.substring(0, pid.length() - 1);
		}
		double totalPrice = 0.0f;// 订单总价
		// 生成订单号
		String Orderid = GetUniqueId("bh_t_orderform", "bh_st_id");
		String OrderNum = GetOrderNum();
		// 根据购物车id获取商品
		List<Bi_t_OrderProdRelate> relateList = new ArrayList<>();// 关系列表
		// 购物车商品列表
		List<Map<String, Object>> buycartList = this.baseBiz.queryForList("SELECT * FROM bn_t_prodcart WHERE bn_st_id IN (" + pid + ")");
		for (int i = 0; i < buycartList.size(); i++) {
			Map<String, Object> product = this.baseBiz.queryForMap("SELECT * FROM bg_t_prodinfo WHERE bg_st_id ='" + buycartList.get(i).get("bn_st_prodid").toString() + "'");
			buycartList.get(i).put("product", product);
			float tempPrice = 0.0f;// 下单时的价格
			float tempTotlePriceForRelate = 0.0f;// 一个关系的总价
			int tempPriceNum = 0;// 下单时 该商品在购物车的数量
			if (product.get("bg_st_fbtpe").equals("1")) {
				tempPrice = Float.parseFloat(product.get("bg_st_pricezg").toString());
			} else if (product.get("bg_st_fbtpe").equals("2")) {
				tempPrice = Float.parseFloat(product.get("bg_st_pricetj").toString());
			}
			tempPriceNum = Integer.parseInt(buycartList.get(i).get("bn_st_prodnum").toString());
			tempTotlePriceForRelate = tempPrice * tempPriceNum;
			totalPrice += tempTotlePriceForRelate;
			// 商品与订单的关系
			Bi_t_OrderProdRelate relate = new Bi_t_OrderProdRelate();
			relate.setBi_st_id(GetUniqueId("bi_t_orderprodrelate", "bi_st_id"));
			relate.setBi_dt_addDate(getcuttDate());
			relate.setBi_st_bbid(ae_t_sysuser.getAe_st_id());
			relate.setBi_st_spnum(product.get("bg_st_num").toString());
			relate.setBi_st_spprice(tempPrice);
			relate.setBi_st_addUserId(ae_t_sysuser.getAe_st_id());
			relate.setBi_st_spsl(tempPriceNum);
			relate.setBi_st_ddnum(OrderNum);
			relateList.add(relate);
		}
		// 生成订单
		Bh_t_orderform order = new Bh_t_orderform();

		order.setBh_st_id(Orderid);
		order.setBh_st_bbid(ae_t_sysuser.getAe_st_id());
		order.setBh_st_ddnum(OrderNum);
		order.setBh_st_spprice(totalPrice);
		order.setBh_st_ddstate(1 + "");
		order.setBh_st_source(2 + "");
		order.setBh_st_memberId(ae_t_sysuser.getAe_st_id());
		order.setBh_dt_addDate(getcuttDate());
		baseBiz.addTRANS(order);
		baseBiz.addsTRANS(relateList, Bi_t_OrderProdRelate.class);
		baseBiz.executeTRANS("DELETE FROM bn_t_prodcart WHERE bn_st_id IN (" + pid + ")");
		this.createAjax(response, "index/orderDetail?orderid=" + Orderid);
	}

	@RequestMapping(value = "orderDetail")
	public String orderDetail() {
		this.session.putValue("RedirectUrl", this.request.getRequestURL() + "?" + this.request.getQueryString());
		Ae_t_sysuser ae_t_sysuser = getTopFootInfo();
		if (null == ae_t_sysuser || null == ae_t_sysuser.getAe_st_id() || "" == ae_t_sysuser.getAe_st_id()) {
			return "wyyf/index/loginp";
		}
		// 这儿少一个无权查看订单的页面
		String orderid = this.request.getParameter("orderid");
		Map<String, Object> order = this.baseBiz.queryForMap("SELECT * FROM bh_t_orderform WHERE bh_st_id = '" + orderid + "'");
		List<Map<String, Object>> productRelateList = this.baseBiz.queryForList("SELECT * FROM bi_t_orderprodrelate WHERE bi_st_ddnum='" + order.get("bh_st_ddnum").toString() + "'");
		for (int i = 0; i < productRelateList.size(); i++) {
			Map<String, Object> product = this.baseBiz.queryForMap("select * from bg_t_prodinfo where bg_st_num ='" + productRelateList.get(i).get("bi_st_spnum").toString() + "'");
			productRelateList.get(i).put("product", product);
		}
		// 获得用户收货地址
		List<Map<String, Object>> userAddress = baseBiz.queryForList("SELECT * FROM bq_t_usefuladdress WHERE bq_st_memberid='" + ae_t_sysuser.getAe_st_id() + "'");// ae_t_sysuser

		this.model.addAttribute("order", order);
		this.model.addAttribute("productRelateList", productRelateList);
		this.model.addAttribute("userAddress", userAddress);
		return "wyyf/index/shop/buy";
	}

	private String GetOrderNum() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String OrderNum = "D" + sdf.format(Calendar.getInstance().getTime()) + MainAction.getRandomInt(3);
		return OrderNum;
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

	@RequestMapping(value = "mysession")
	public void mysession() {
		this.session.putValue("RedirectUrl", this.request.getRequestURL() + "?" + this.request.getQueryString());
		Ae_t_sysuser ae_t_sysuser = getTopFootInfo();
		String pid = request.getParameter("id");
		int num = Integer.parseInt(request.getParameter("num"));
		model.addAttribute("pid", pid);
		Bn_t_prodcart pro = new Bn_t_prodcart();
		String id = getRandomString(32);
		List<Map<String, Object>> isExist = baseBiz.queryForList("select * from bn_t_prodcart where bn_st_id = '" + id + "'");
		while (isExist.size() > 0) {
			id = getRandomString(32);
			isExist = baseBiz.queryForList("select * from bn_t_prodcart where bn_st_id = '" + id + "'");
		}
		pro.setBn_dt_addDate(getcuttDate());
		pro.setBn_st_addUserId(ae_t_sysuser.getAe_st_addUserId());
		pro.setBn_st_memberid(ae_t_sysuser.getAe_st_id());
		pro.setBn_st_prodid(pid);
		pro.setBn_st_prodnum(num);
		pro.setBn_st_id(id);
		if (baseBiz.addTRANS(pro))
			this.createAjax(response, true);
		else {
			this.createAjax(response, false);
		}
	}

	// 评价详情
	@RequestMapping(value = "doJudge", method = RequestMethod.POST)
	@ResponseBody
	public void doJudge(Be_t_Assess bta,Ae_t_sysuser ats) {
		String content = request.getParameter("content");
		String bfid=request.getParameter("be_st_bfid");
		System.out.println(content);
		String rid = request.getParameter("rid");
		System.out.println(rid);
		String sid = request.getParameter("sid");
		System.out.println(sid);
		String attitude = request.getParameter("attitude");
		System.out.println(attitude);
		String honesty = request.getParameter("honesty");
		System.out.println(honesty);
		String quality = request.getParameter("quality");
		System.out.println(quality);
		if (attitude == null || attitude.equals("")) {
			attitude = "0";
		}
		if (honesty == null || honesty.equals("")) {
			honesty = "0";
		}

		if (quality == null || quality.equals("")) {
			quality = "0";
		}

		try {
			List<BizTransUtil> list = new ArrayList<BizTransUtil>();
			// 修改预约
			if (org.springframework.util.StringUtils.hasText(bta.getBe_st_id())) {
			}
			// 添加预约
			else {
				bta.setBe_st_id(StringUtils.getUUID32());// 主键ID
				bta.setBe_st_addUserId(getcuttSysuserID());// 创建人员ID
				bta.setBe_dt_addDate(getcuttDate());// 创建时间
				bta.setBe_st_fbgid(getcuttSysuserID());// 评价人
				bta.setBe_st_jbgid(sid);// 被评价人
				bta.setBe_st_content(content);
				bta.setBe_st_updUserId(getcuttSysuserID());
				bta.setBe_nm_integrity(Integer.valueOf(honesty));
				bta.setBe_nm_quality(Integer.valueOf(quality));
				bta.setBe_nm_manner(Integer.valueOf(attitude));
				list.add(new BizTransUtil(bta));
				
			}
			// 执行操作
			if (baseBiz.executesTRANS(list)) {
				List<BizTransUtil> updatelist = new ArrayList<BizTransUtil>();
				//查出当前登陆用户的米币余额
				String sql1="select mb from Ae_t_sysuser where Ae_st_id='"+rid+"'";
				String mb=baseBiz.queryString(sql1);
				System.out.println(mb);
				double mb1=Double.valueOf(mb);
				double mb2=mb1+50;
				Map<String, Object> map1=new HashMap<String,Object>();
				map1.put("mb",mb2);
				map1.put("ae_st_id",rid);
				
				updatelist.add(new BizTransUtil(map1,Ae_t_sysuser.class,CommonUtil.UPDATE));
				boolean flag=baseBiz.executesTRANS(updatelist);
				this.createAjax(response, "0");

			} else {
				this.createAjax(response, "1");
			}
		} catch (Exception e) {
			this.createAjax(response, "1");
		}
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

	public static String getRandomInt(int length) { // length表示生成字符串的长度
		String base = "0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	@RequestMapping(value = "GetChildAreaByPid")
	@ResponseBody
	public void GetChildAreaByPid() {
		String jsonData = "{success:false,msg:\"未获取到信息!\"}";
		String pid = request.getParameter("pid");// 商品ID
		List<Map<String, Object>> childAreas = baseBiz.queryForList("SELECT * FROM aa26 WHERE d_parent='" + pid + "'");
		jsonData = "{\"success\":true,result:[" + JsonUtils.getJsonDataFromCollection(childAreas) + "]}";
		System.out.println(childAreas);
		this.createAjax(response, jsonData);
	}

	@RequestMapping(value = "userAdd")
	public void userAdd(Ae_t_sysuser ae_t_sysuser, Ba_t_Labour labour, Bb_t_ShopUser shopUser, Ag_t_file oldFile, MultipartHttpServletRequest multipartRequest) {
		String jsonData = "";
		try {
			if (!org.springframework.util.StringUtils.hasText(ae_t_sysuser.getUsername())) {
				throw new RuntimeException("帐号不能为空");
			}
			// 选择的地区
			String groupids3 = request.getParameter("groupids3");
			if (org.springframework.util.StringUtils.hasText(groupids3)) {
				String[] groups = groupids3.split(",");
				for (int b = groups.length, i = b - 1; i >= 0; i--) {
					if (i == b - 1) {
						ae_t_sysuser.setAe_st_shsheng(groups[i]);
					} else if (i == b - 2) {
						ae_t_sysuser.setAe_st_shshi(groups[i]);
					} else if (i == b - 3) {
						ae_t_sysuser.setAe_st_shxian(groups[i]);
					}
				}
			}
			Encoder e = new MD5Encoder();// 使用security3 的MD5加密技术
			// 创建批量保存事务对象
			List<BizTransUtil> list = new ArrayList<BizTransUtil>();
			String id = ae_t_sysuser.getAe_st_id();
			// 更新
			if (org.springframework.util.StringUtils.hasText(id)) {
				// 构建数据--修改
				Map<String, Object> map = new LinkedCaseInsensitiveMap<Object>();
				map.put("ae_st_id", id);// ID
				if (org.springframework.util.StringUtils.hasText(ae_t_sysuser.getPassword())) {
					map.put("password", e.encrypt(ae_t_sysuser.getPassword(), ae_t_sysuser.getUsername()));// MD5加密
				}
				map.put("ae_st_updUserId", getcuttSysuserID());
				map.put("ae_dt_updDate", getcuttDate());
				map.put("ae_st_lockstate", ae_t_sysuser.getAe_st_lockstate());// 审核通过/不通过状态修改
				map.put("ae_st_name", ae_t_sysuser.getAe_st_name());// 姓名
				map.put("ae_st_tell", ae_t_sysuser.getAe_st_tell());// 电话
				map.put("ae_st_sex", ae_t_sysuser.getAe_st_sex());// 性别
				map.put("ae_nm_age", ae_t_sysuser.getAe_nm_age());// 年龄
				map.put("ae_st_address", ae_t_sysuser.getAe_st_address());// 详细地址
				map.put("ae_st_intro", ae_t_sysuser.getAe_st_intro());// 简介
				map.put("ae_st_shsheng", ae_t_sysuser.getAe_st_shsheng());// 省
				map.put("ae_st_shshi", ae_t_sysuser.getAe_st_shshi());// 市
				map.put("ae_st_shxian", ae_t_sysuser.getAe_st_shxian());// 县/区
				map.put("ae_nm_zjnum", ae_t_sysuser.getAe_nm_zjnum());// 资金余额
				map.put("ae_st_nickName", ae_t_sysuser.getAe_st_nickName());// 用户昵称
				map.put("ae_st_type", ae_t_sysuser.getAe_st_type());
				list.add(new BizTransUtil(map, Ae_t_sysuser.class, CommonUtil.UPDATE));

				String type = ae_t_sysuser.getAe_st_type();
				if ("3".equals(type)) {
					Map<String, Object> sjmap = new LinkedCaseInsensitiveMap<Object>();
					sjmap.put("bb_st_id", shopUser.getBb_st_id());
					sjmap.put("bb_st_area", shopUser.getBb_st_area());
					sjmap.put("bb_st_phone1", shopUser.getBb_st_phone1());
					sjmap.put("bb_st_phone2", shopUser.getBb_st_phone2());
					sjmap.put("bb_st_shopinfo", shopUser.getBb_st_shopinfo());
					list.add(new BizTransUtil(sjmap, Bb_t_ShopUser.class, CommonUtil.UPDATE));
				} else if ("4".equals(type) || "5".equals(type) || "6".equals(type)) {
					Map<String, Object> sfmap = new LinkedCaseInsensitiveMap<Object>();
					sfmap.put("ba_st_id", labour.getBa_st_id());
					sfmap.put("ba_st_type", labour.getBa_st_type());
					sfmap.put("ba_st_price", labour.getBa_st_price());
					sfmap.put("ba_st_team_intro", labour.getBa_st_team_intro());
					list.add(new BizTransUtil(sfmap, Ba_t_Labour.class, CommonUtil.UPDATE));
				}
			}
			// 新增
			else {
				// 构建数据--新增
				String aeid = StringUtils.getUUID32();
				ae_t_sysuser.setAe_st_id(aeid);
				ae_t_sysuser.setAe_st_userkind("3");// 用户类型
				ae_t_sysuser.setAe_st_addUserId(getcuttSysuserID());
				ae_t_sysuser.setAe_dt_addDate(getcuttDate());
				ae_t_sysuser.setPassword(e.encrypt(ae_t_sysuser.getPassword(), ae_t_sysuser.getUsername()));
				ae_t_sysuser.setAe_st_lockstate("1");// 账户锁定状态：1正常，2账户停用3.注销

				String usertype = ae_t_sysuser.getAe_st_type();
				if ("3".equals(usertype)) {
					// 添加商家附加信息
					String sjid = StringUtils.getUUID32(); // 商家id
					ae_t_sysuser.setAe_st_objid(sjid);
					ae_t_sysuser.setAe_st_objtype("bb_t_shopuser");
					shopUser.setBb_st_id(sjid);
					shopUser.setBb_st_userid(aeid);
					shopUser.setBb_st_addUserId(getcuttSysuserID());
					shopUser.setBb_dt_addDate(getcuttDate());
					list.add(new BizTransUtil(shopUser));
				} else if ("4".equals(usertype) || "5".equals(usertype) || "6".equals(usertype)) {
					// 添加师傅附加信息
					String sfid = StringUtils.getUUID32(); // 师傅id
					ae_t_sysuser.setAe_st_objid(sfid);
					ae_t_sysuser.setAe_st_objtype("ba_t_labour");
					labour.setBa_st_id(sfid);
					labour.setBa_st_userid(aeid);
					labour.setBa_st_addUserId(getcuttSysuserID());
					labour.setBa_dt_addDate(getcuttDate());
					list.add(new BizTransUtil(labour));
				}
				list.add(new BizTransUtil(ae_t_sysuser));

				String type = ae_t_sysuser.getAe_st_type();
				String roleid = (String) baseBiz.queryForObject("select ac_st_id from ac_t_sysrole where ac_st_code = ?", new Object[] { "2".equals(type) ? "WZGLY" : ("3".equals(type) ? "SJ" : ("4".equals(type) || "5".equals(type) || "6".equals(type) ? "SF" : "HY")) }, String.class);
				Af_t_sysuserrole rf = new Af_t_sysuserrole(true);
				rf.setAe_st_id(aeid);
				rf.setAc_st_id(roleid);
				list.add(new BizTransUtil(rf));
			}

			// 新增图片/删除图片
			MultipartFile mf = multipartRequest.getFile("myfile");
			String delfileurl = "";
			if (mf != null && mf.getSize() > 0) {
				// 如果是修改，且文件表不为空，则删除相关文件表数据，和清除文件
				if (org.springframework.util.StringUtils.hasText(id)) {
					list.add(new BizTransUtil("delete from ag_t_file where ag_st_objid=?", new Object[] { id }, CommonUtil.DELETE));// 删除本条数据
					delfileurl = oldFile.getAg_st_url();
				}
				Ag_t_file file = new Ag_t_file(true);
				// 上传文件的相对路径
				String uploadPath = UploadFile.uploadInputStream(mf.getInputStream(), mf.getOriginalFilename(), "images/business/user");
				BufferedImage buff = ImageIO.read(mf.getInputStream());
				file.setAg_nm_height(buff.getHeight());// 文件属性高
				file.setAg_nm_width(buff.getWidth());// 文件属性宽
				file.setAg_nm_size(mf.getSize());// 文件属性大小
				file.setAg_st_format(mf.getContentType());// 文件格式
				file.setAg_dt_addDate(getcuttDate());// 创建时间
				file.setAg_st_addUserId(getcuttSysuserID());// 创建人员ID
				file.setAg_st_objid(ae_t_sysuser.getAe_st_id());// 文件归属ID
				file.setAg_st_objtype("ae_t_sysuser");// 文件对应的对象
				file.setAg_st_type("1");// 文件类型-图片
				file.setAg_st_mark("userfile");// 自定义文件标识
				file.setAg_st_url(uploadPath);// //文件 的存储地址
				list.add(new BizTransUtil(file));
			}
			// 执行操作
			if (baseBiz.executesTRANS(list)) {
				if (org.springframework.util.StringUtils.hasText(delfileurl)) {
					UploadFile.delpic(delfileurl);// 删除原文件
				}
				jsonData = "{success:true,msg:\"操作成功!\"}";
			} else {
				jsonData = "{success:false,msg:\"操作异常!\"}";
			}
		} catch (Exception e) {
			jsonData = "{success:false,msg:\"操作异常，" + StringUtils.replaceBlank(e.getMessage()) + "!\"}";
		}
		this.createAjax(response, jsonData);
	}

	@RequestMapping(value = "ChangeAddress")
	@ResponseBody
	public void ChangeorAddAddress() {
		String jsonData = "";
		try {
			Ae_t_sysuser sysUser = getTopFootInfo();
			if (null == sysUser || null == sysUser.getAe_st_id() || "" == sysUser.getAe_st_id()) {
				String oldUrlRedirect = this.request.getRequestURL() + "?" + this.request.getQueryString();
				model.addAttribute("oldRequestUrl", oldUrlRedirect);
				this.createAjax(response, "index/loginp?oldRequestUrl=" + this.session.getValue("RedirectUrl"));
				return;
			}
			String id = request.getParameter("id");//
			String phone = request.getParameter("phone");//
			String name = request.getParameter("name");//
			String sheng = request.getParameter("sheng");//
			String shi = request.getParameter("shi");//
			String xian = request.getParameter("xian");//
			String addr = request.getParameter("addr");//
			if (id.equals("0")) {// add
				id = getRandomString(12);
				List<Map<String, Object>> isExist = baseBiz.queryForList("select * from bn_t_prodcart where bn_st_id = '" + id + "'");
				while (isExist.size() > 0) {
					id = getRandomString(12);
					isExist = baseBiz.queryForList("select * from bq_t_usefuladdress where bq_st_id = '" + id + "'");
				}
				// Bq_t_UsefulAddress b=new Bq_t_UsefulAddress();

				Integer num = baseBiz.executeTRANS("insert into bq_t_usefuladdress(bq_st_id,bq_st_ismr,bq_st_name,bq_st_phone,bq_st_sheng,bq_st_shi,bq_st_xian,bq_st_adress,bq_st_memberid,bq_st_addUserId) values('" + id + "','" + 1 + "','" + name + "','" + phone + "','" + sheng + "','" + shi + "','" + xian + "','" + addr + "','" + sysUser.getAe_st_id() + "','" + sysUser.getAe_st_addUserId() + "')");
				// baseBiz.addTRANS(b);
				if (num > 0) {
					this.createAjax(response, id + "");
				} else {
					this.createAjax(response, 0);
				}
			} else {// update
				Integer num = baseBiz.executeTRANS("update bq_t_usefuladdress set bq_st_ismr=1,bq_st_name=?,bq_st_phone=?,bq_st_sheng=?,bq_st_shi=?,bq_st_xian=?,bq_st_adress=? where bq_st_id=?", name, phone, sheng, shi, xian, addr, id);
				if (num > 0) {
					this.createAjax(response, -1 + "");
				} else {
					this.createAjax(response, 0);
				}
			}
		} catch (Exception ex) {
			jsonData = ex.getMessage();
		}
		this.createAjax(response, jsonData);
	}

	@RequestMapping(value = "findPwd")
	public String findPwd() {
		return "wyyf/index/findPwd";
	}

	@RequestMapping(value = "validTel")
	public @ResponseBody
	String validTel() {
		String tel = request.getParameter("tel");
		String sql = "SELECT COUNT(1) FROM ae_t_sysuser where username='" + tel + "'";
		int count = baseBiz.queryForInt(sql);
		if (count == 1) {
			return "true";
		} else {
			return "false";
		}
	}

	@RequestMapping(value = "editPwd")
	public @ResponseBody
	String editPwd() {
		String pwd = request.getParameter("pwd");
		String tel = request.getParameter("tel");
		Encoder e = new MD5Encoder();
		String password = e.encrypt(pwd, tel);
		String sql = "update ae_t_sysuser set password=?  where username=?";
		int count = baseBiz.executeTRANS(sql, password, tel);
		if (count == 1) {
			return "true";
			
		} else {
			return "false";
		}
	}

	/**
	 * 监理详情页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "ShopUserDetail")
	public String ShopUserDetail() {
		this.session.putValue("RedirectUrl", this.request.getRequestURL() + "?" + this.request.getQueryString());
		getTopFootInfo();
		String type = request.getParameter("type");
		String ba_st_type = "13";
		if (type.toString().equals("jianli")) {
			ba_st_type = "13";
		} else if (type.toString().equals("jiance")) {
			ba_st_type = "14";
		}
		String id = request.getParameter("id");// 师傅ID(赴约人)
		String sid = request.getParameter("sid");// 申请人ID
		model.addAttribute("type", type);
		model.addAttribute("sid", sid);
		// 监理师傅信息
		String sql1 = "select ba_st_price,ae_st_name,ae_st_id,(select COUNT(1) from  bf_t_apply where bf_st_receiveid= ae_st_id) jdnum,(case when ba_st_grade<2 then 0 when 2<=ba_st_grade<4 then 1 when 4<=ba_st_grade<6 then 2 when 6<=ba_st_grade<8 then 3 else 4 end) ba_st_grade,(select ag_st_url from ag_t_file where ag_st_objid = ae_st_id) ag_st_url,ba_st_teamnum,ba_dt_addDate,ba_st_team_intro from ae_t_sysuser ,ba_t_labour ";
		List<Map<String, Object>> list1 = baseBiz.queryForList(sql1 + " where ae_st_id=ba_st_userid and (ae_st_type='5' or ae_st_type='4') AND ae_st_id='" + id + "'");
		model.addAttribute("ma_masterDetaillist1", list1);
		int year=0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyyyyy-MM-dd HH:mm:ss");
		if(list1.size()>0){
			year = Calendar.getInstance().getTime().getYear()-DateUtils.parseDate(list1.get(0).get("ba_dt_addDate").toString()).getYear()+1;
		}
		// 评价信息
		String sql2 = "SELECT b.ae_st_name,be_st_content,be_dt_addDate FROM ae_t_sysuser a,be_t_assess,ae_t_sysuser b WHERE be_st_jbgid=a.ae_st_id AND be_st_fbgid=b.ae_st_id";
		List<Map<String, Object>> list2 = baseBiz.queryForList(sql2 + " AND a.ae_st_id='" + id + "'");
		model.addAttribute("ma_masterDetaillist2", list2);
		// 案例信息
		String sql3 = "SELECT ag_st_url,bd_st_name,bd_st_money,bd_st_area,bd_st_remark FROM bd_t_case,ae_t_sysuser,ag_t_file WHERE bd_st_bbid=ae_st_id AND ag_st_objid=bd_st_id";
		List<Map<String, Object>> list3 = baseBiz.queryForList(sql3 + "  AND ae_st_id='" + id + "'");
		model.addAttribute("ma_masterDetaillist3", list3);
		model.addAttribute("year", year);
		return "wyyf/index/ShopUserDetail";
	}

	/**
	 * 预约界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "ShopUserOrder")
	public String ShopUserOrder() {
		this.session.putValue("RedirectUrl", this.request.getRequestURL() + "?" + this.request.getQueryString());
		Ae_t_sysuser ae_t_sysuser = getTopFootInfo();
		if (null == ae_t_sysuser || null == ae_t_sysuser.getAe_st_id() || "" == ae_t_sysuser.getAe_st_id()) {
			return "wyyf/index/loginp";
		}
		String shopuserid = request.getParameter("shopuserid");
		String type = request.getParameter("type");
		model.addAttribute("rid", shopuserid);// 赴约人ID
		String ba_st_type = "";
		if (type.toString().equals("jianli")) {
			ba_st_type = "9";
		} else if (type.toString().equals("jiance")) {
			ba_st_type = "12";
		}
		model.addAttribute("ba_st_type", ba_st_type);// 赴约人ID
		return "wyyf/index/ShopUserOrderEnd";
	}

	@RequestMapping(value = "deleteOrder")
	public @ResponseBody
    String deleteOrder() {
		
		String id = request.getParameter("bhid");
		boolean flag = baseBiz.deleteTRANS(id, Bh_t_orderform.class);
		if (flag) {
			return "true";
		} else {
			return "false";
		}
	}
	@RequestMapping(value = "aboutUs")
	public String gywm(){
		// 关于我们
		getTopFootInfo();
		String aboutMeSql="SELECT bl_st_context FROM bl_t_noticecontext WHERE bl_st_type=6";
		List<Map<String, Object>> list = baseBiz.queryForList(aboutMeSql);
		model.addAttribute("aboutMap", list);
		return "wyyf/index/aboutUs";
	}
	
	@RequestMapping(value = "callMe")
	public String callMe(){
		getTopFootInfo();
		return "wyyf/index/callMe";
	}
	@RequestMapping(value = "mzsm")
	public String mzsm(){
		getTopFootInfo();
		return "wyyf/index/mzsm";
	}
	
	
	@RequestMapping(value = "editUserInfo")
	public void editUserInfo(Ae_t_sysuser ae_t_sysuser, Ba_t_Labour labour, Bb_t_ShopUser shopUser, Ag_t_file oldFile, MultipartHttpServletRequest multipartRequest) {
		String jsonData = "";
		try {
			if (!org.springframework.util.StringUtils.hasText(ae_t_sysuser.getUsername())) {
				throw new RuntimeException("帐号不能为空");
			}
			// 选择的地区
			String groupids3 = request.getParameter("groupids3");
			if (org.springframework.util.StringUtils.hasText(groupids3)) {
				String[] groups = groupids3.split(",");
				for (int b = groups.length, i = b - 1; i >= 0; i--) {
					if (i == b - 1) {
						ae_t_sysuser.setAe_st_shsheng(groups[i]);
					} else if (i == b - 2) {
						ae_t_sysuser.setAe_st_shshi(groups[i]);
					} else if (i == b - 3) {
						ae_t_sysuser.setAe_st_shxian(groups[i]);
					}
				}
			}
			Encoder e = new MD5Encoder();// 使用security3 的MD5加密技术
			// 创建批量保存事务对象
			List<BizTransUtil> list = new ArrayList<BizTransUtil>();
			String id = ae_t_sysuser.getAe_st_id();
			// 更新
			if (org.springframework.util.StringUtils.hasText(id)) {
				// 构建数据--修改
				Map<String, Object> map = new LinkedCaseInsensitiveMap<Object>();
				map.put("ae_st_id", id);// ID
				if (org.springframework.util.StringUtils.hasText(ae_t_sysuser.getPassword())) {
					map.put("password", e.encrypt(ae_t_sysuser.getPassword(), ae_t_sysuser.getUsername()));// MD5加密
				}
				map.put("ae_st_updUserId", getcuttSysuserID());
				map.put("ae_dt_updDate", getcuttDate());
				map.put("ae_st_lockstate", ae_t_sysuser.getAe_st_lockstate());// 审核通过/不通过状态修改
				map.put("ae_st_name", ae_t_sysuser.getAe_st_name());// 姓名
				map.put("ae_st_tell", ae_t_sysuser.getAe_st_tell());// 电话
				map.put("emll", ae_t_sysuser.getEmll());
				map.put("ae_st_description", ae_t_sysuser.getAe_st_description());
				/*map.put("ae_st_sex", ae_t_sysuser.getAe_st_sex());// 性别
				map.put("ae_nm_age", ae_t_sysuser.getAe_nm_age());// 年龄
*/				map.put("ae_st_address", ae_t_sysuser.getAe_st_address());// 详细地址
				/*map.put("ae_st_intro", ae_t_sysuser.getAe_st_intro());// 简介
				map.put("ae_st_shsheng", ae_t_sysuser.getAe_st_shsheng());// 省
				map.put("ae_st_shshi", ae_t_sysuser.getAe_st_shshi());// 市
				map.put("ae_st_shxian", ae_t_sysuser.getAe_st_shxian());// 县/区
				map.put("ae_nm_zjnum", ae_t_sysuser.getAe_nm_zjnum());// 资金余额
*/				map.put("ae_st_nickName", ae_t_sysuser.getAe_st_nickName());// 用户昵称
				/*map.put("ae_st_type", ae_t_sysuser.getAe_st_type());*/
				list.add(new BizTransUtil(map, Ae_t_sysuser.class, CommonUtil.UPDATE));

				String type = ae_t_sysuser.getAe_st_type();
				if ("3".equals(type)) {
					Map<String, Object> sjmap = new LinkedCaseInsensitiveMap<Object>();
					sjmap.put("bb_st_id", shopUser.getBb_st_id());
					sjmap.put("bb_st_area", shopUser.getBb_st_area());
					sjmap.put("bb_st_phone1", shopUser.getBb_st_phone1());
					sjmap.put("bb_st_phone2", shopUser.getBb_st_phone2());
					sjmap.put("bb_st_shopinfo", shopUser.getBb_st_shopinfo());
					list.add(new BizTransUtil(sjmap, Bb_t_ShopUser.class, CommonUtil.UPDATE));
				} else if ("4".equals(type) || "5".equals(type) || "6".equals(type)) {
					Map<String, Object> sfmap = new LinkedCaseInsensitiveMap<Object>();
					sfmap.put("ba_st_id", labour.getBa_st_id());
					sfmap.put("ba_st_type", labour.getBa_st_type());
					sfmap.put("ba_st_price", labour.getBa_st_price());
					sfmap.put("ba_st_team_intro", labour.getBa_st_team_intro());
					list.add(new BizTransUtil(sfmap, Ba_t_Labour.class, CommonUtil.UPDATE));
				}
			}
			// 新增
			else {
				// 构建数据--新增
				String aeid = StringUtils.getUUID32();
				ae_t_sysuser.setAe_st_id(aeid);
				ae_t_sysuser.setAe_st_userkind("3");// 用户类型
				ae_t_sysuser.setAe_st_addUserId(getcuttSysuserID());
				ae_t_sysuser.setAe_dt_addDate(getcuttDate());
				ae_t_sysuser.setPassword(e.encrypt(ae_t_sysuser.getPassword(), ae_t_sysuser.getUsername()));
				ae_t_sysuser.setAe_st_lockstate("1");// 账户锁定状态：1正常，2账户停用3.注销

				String usertype = ae_t_sysuser.getAe_st_type();
				if ("3".equals(usertype)) {
					// 添加商家附加信息
					String sjid = StringUtils.getUUID32(); // 商家id
					ae_t_sysuser.setAe_st_objid(sjid);
					ae_t_sysuser.setAe_st_objtype("bb_t_shopuser");
					shopUser.setBb_st_id(sjid);
					shopUser.setBb_st_userid(aeid);
					shopUser.setBb_st_addUserId(getcuttSysuserID());
					shopUser.setBb_dt_addDate(getcuttDate());
					list.add(new BizTransUtil(shopUser));
				} else if ("4".equals(usertype) || "5".equals(usertype) || "6".equals(usertype)) {
					// 添加师傅附加信息
					String sfid = StringUtils.getUUID32(); // 师傅id
					ae_t_sysuser.setAe_st_objid(sfid);
					ae_t_sysuser.setAe_st_objtype("ba_t_labour");
					labour.setBa_st_id(sfid);
					labour.setBa_st_userid(aeid);
					labour.setBa_st_addUserId(getcuttSysuserID());
					labour.setBa_dt_addDate(getcuttDate());
					list.add(new BizTransUtil(labour));
				}
				list.add(new BizTransUtil(ae_t_sysuser));

				String type = ae_t_sysuser.getAe_st_type();
				String roleid = (String) baseBiz.queryForObject("select ac_st_id from ac_t_sysrole where ac_st_code = ?", new Object[] { "2".equals(type) ? "WZGLY" : ("3".equals(type) ? "SJ" : ("4".equals(type) || "5".equals(type) || "6".equals(type) ? "SF" : "HY")) }, String.class);
				Af_t_sysuserrole rf = new Af_t_sysuserrole(true);
				rf.setAe_st_id(aeid);
				rf.setAc_st_id(roleid);
				list.add(new BizTransUtil(rf));
			}

			// 新增图片/删除图片
			MultipartFile mf = multipartRequest.getFile("myfile");
			String delfileurl = "";
			if (mf != null && mf.getSize() > 0) {
				// 如果是修改，且文件表不为空，则删除相关文件表数据，和清除文件
				if (org.springframework.util.StringUtils.hasText(id)) {
					list.add(new BizTransUtil("delete from ag_t_file where ag_st_objid=?", new Object[] { id }, CommonUtil.DELETE));// 删除本条数据
					delfileurl = oldFile.getAg_st_url();
				}
				Ag_t_file file = new Ag_t_file(true);
				// 上传文件的相对路径
				String uploadPath = UploadFile.uploadInputStream(mf.getInputStream(), mf.getOriginalFilename(), "images/business/user");
				BufferedImage buff = ImageIO.read(mf.getInputStream());
				file.setAg_nm_height(buff.getHeight());// 文件属性高
				file.setAg_nm_width(buff.getWidth());// 文件属性宽
				file.setAg_nm_size(mf.getSize());// 文件属性大小
				file.setAg_st_format(mf.getContentType());// 文件格式
				file.setAg_dt_addDate(getcuttDate());// 创建时间
				file.setAg_st_addUserId(getcuttSysuserID());// 创建人员ID
				file.setAg_st_objid(ae_t_sysuser.getAe_st_id());// 文件归属ID
				file.setAg_st_objtype("ae_t_sysuser");// 文件对应的对象
				file.setAg_st_type("1");// 文件类型-图片
				file.setAg_st_mark("userfile");// 自定义文件标识
				file.setAg_st_url(uploadPath);// //文件 的存储地址
				list.add(new BizTransUtil(file));
			}
			// 执行操作
			if (baseBiz.executesTRANS(list)) {
				if (org.springframework.util.StringUtils.hasText(delfileurl)) {
					UploadFile.delpic(delfileurl);// 删除原文件
				}
				jsonData = "{success:true,msg:\"操作成功!\"}";
			} else {
				jsonData = "{success:false,msg:\"操作异常!\"}";
			}
		} catch (Exception e) {
			jsonData = "{success:false,msg:\"操作异常，" + StringUtils.replaceBlank(e.getMessage()) + "!\"}";
		}
		this.createAjax(response, jsonData);
	}
	
	//米币积累记录(暂时不弄(邓涛说的))
	@RequestMapping(value="mbRecord")
	public String mbRecord()
	{
	     //取得登陆者ID
		 String id  =request.getParameter("id");
		 
	   return null; 
	}
}