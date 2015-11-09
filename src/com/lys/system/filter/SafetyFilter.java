package com.lys.system.filter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.util.NestedServletException;

import javax.servlet.Filter;
/**
 * 安全过滤器
 * 
 * @author shuang
 */
public class SafetyFilter implements Filter {
	public static final Logger logger = Logger.getLogger(SafetyFilter.class);
	protected String encoding = "UTF-8";
	@Override
	public void doFilter(final ServletRequest request,final ServletResponse response, final FilterChain fc)throws java.io.IOException, javax.servlet.ServletException {
		final HttpServletRequest req = (HttpServletRequest) request;
		final HttpServletResponse responses = (HttpServletResponse)response;
		req.setCharacterEncoding(encoding);
		responses.setCharacterEncoding(encoding);
		responses.setContentType(" text/html;charset=UTF-8"); 
		String ctxPath=req.getRequestURL().toString();//获取请求地址
		String requestResource =(req.getQueryString() != null ? ("?"+req.getQueryString()):"");//参数
		requestResource= URLDecoder.decode(requestResource,"UTF-8");
		logger.info("请求地址 : " + ctxPath +requestResource);
		try {
			fc.doFilter(req, response);
		}catch (NestedServletException e) {
			if(e.getCause() instanceof MaxUploadSizeExceededException){
				if(response!=null){
					response.setContentType("text/plain");
					try {
						response.getWriter().print("{success:false,msg:\"文件上传失败，文件大小超过"+((MaxUploadSizeExceededException)e.getCause()).getMaxUploadSize()/(1024*1024)+"M!\"}");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}else {
				System.out.println(e.getMessage());
			}
		} catch (final IOException e) {
			logException(e);
			throw e;
		} catch (final ServletException e) {
			logger.error("捕获Servlet异常：" + e);
			final Throwable cause = e.getRootCause();
			if (cause != null) {
				logger.error("引起Servlet异常的原因为：" + cause, cause);
			}
			throw e;
		} catch (final RuntimeException e) {
			logException(e);
			throw e;
		}
	}
	/**
	 * 销毁
	 */
	@Override
	public void destroy() {
		encoding = null;
	}
	/**
	 * 初始化
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String encodingWebxml = filterConfig.getInitParameter("encoding");
		if(encodingWebxml!=null){
			encoding=encodingWebxml;
		}
	}

	private void logException(final Exception e) {
		logger.error("捕获异常：" + e, e);
		final Throwable cause = e.getCause();
		if (cause != null && cause != e) {
			logger.error("引起异常的原因为：" + cause, cause);
		}
	}
}

