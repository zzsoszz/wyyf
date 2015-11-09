package com.lys.mvc.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.lys.system.filter.SafetyFilter;
import com.lys.utils.Captcha;

/**
 * 生成验证码
 * @author shuang
 */
@Scope(value = "prototype")
@SessionAttributes("imageCode")
@Controller("ImageAction")
@RequestMapping(value = "/imageCode")
public class ImageAction extends BaseAjaxAction {
	/**
	 * 读出验证码图形并返回到前台
	 * @param req
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping
	public ResponseEntity<byte[]> download(HttpServletRequest req,ModelMap modelMap) throws Exception {
		try {
			Captcha captcha = new Captcha(135, 40, 4, 35);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			modelMap.addAttribute("imageCode", captcha.getRandomCode());
			SafetyFilter.logger.info("本次验证码："+captcha.getRandomCode());
			return new ResponseEntity<byte[]>(captcha.getBytes(), headers,HttpStatus.CREATED);
		} catch (Exception e) {
			SafetyFilter.logger.error("验证码加载失败");
			return null;
		}
		
	}
	
}
