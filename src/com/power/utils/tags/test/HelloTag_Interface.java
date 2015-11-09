package com.power.utils.tags.test;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;


/**
 * 
 *演示怎么实现Tag接口的方式来开发标签程序
 */

public class HelloTag_Interface implements Tag{
	private PageContext pageContext;
	private Tag parent;
	public HelloTag_Interface(){
		super();
	}
	/**
	 * 
	 *设置标签的页面的上下文
	 */
	@Override
	public void setPageContext(final PageContext pageContext) {
		this.pageContext=pageContext;
	}
	/**
	 * 
	 *设置上一级标签
	 */
	@Override
	public void setParent(final Tag parent) {
		this.parent = parent;
	}
	/**
	 * 
	 *开始标签时的操作
	 */

	@Override
	public int doStartTag() throws JspTagException {
		return SKIP_BODY; // 返回SKIP_BODY，表示不计算标签体
	}
	/**
	 * 
	 *结束标签时的操作
	 */
	@Override
	public int doEndTag() throws JspTagException {
		try {
			pageContext.getOut().write("Hello World!你好，世界！");
		}catch (java.io.IOException e){
			throw new JspTagException("IO Error: " + e.getMessage());
		}
		return EVAL_PAGE;
	}

	/**
	 * 
	 *release用于释放标签程序占用的资源，比如使用了数据库，那么应该关闭这个连接。
	 */
	@Override
	public void release() {
	}
	@Override
	public Tag getParent(){
		return parent;
	}

}
