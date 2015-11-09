package com.wyyf.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.lys.mvc.action.BaseAjaxAction;
import com.lys.system.filter.SafetyFilter;
import com.wyyf.action.dx.HttpSender;

/**
 * 手机验证--生成验证码
 * @author shuang
 */
@Scope(value = "prototype")
@SessionAttributes("phoneCode")
@Controller("PhoneYZAction")
@RequestMapping(value = "/phoneCode")
public class PhoneYZAction extends BaseAjaxAction {
	/**
	 * 读出验证码图形并返回到前台
	 * @param req
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.POST)
	public void check(ModelMap modelMap) throws Exception {
		String jsonData = "";
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
				modelMap.addAttribute("phoneCode", a);//在验证接口取得的时候，使用String.valueOf(request.getSession().getAttribute("phoneCode")).toLowerCase())
				SafetyFilter.logger.info("本次手机验证码："+a);
				jsonData = "{\"success\":\"true\" ,\"msg\":\"验证码生成成功！\",\"code\":"+a+"}";
			}else{
				jsonData = "{\"success\":\"false\" ,\"msg\":\"验证码生成失败！\"}";
			}
			
			
		} catch (Exception e) {
			jsonData = "{\"success\":\"false\" ,\"msg\":\"验证码生成失败,"+e.getMessage()+"！\"}";
		}
		response.setCharacterEncoding("UTF-8");
		this.createAjax(response,jsonData);
	}
	
	
	@RequestMapping(value="sendCode", method = RequestMethod.POST)
	public @ResponseBody String  sendCode(ModelMap modelMap) throws Exception {
		String jsonData = "";
		int aaa=0;
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
				modelMap.addAttribute("phoneCode", a);//在验证接口取得的时候，使用String.valueOf(request.getSession().getAttribute("phoneCode")).toLowerCase())
				SafetyFilter.logger.info("本次手机验证码："+a);
				aaa=a;
				jsonData = "{\"success\":\"true\" ,\"msg\":\"验证码生成成功！\"}";
			}else{
				jsonData = "{\"success\":\"false\" ,\"msg\":\"验证码生成失败！\"}";
			}
			
			
		} catch (Exception e) {
			jsonData = "{\"success\":\"false\" ,\"msg\":\"验证码生成失败,"+e.getMessage()+"！\"}";
		}
		return String.valueOf(aaa);
	}
	
	
}
