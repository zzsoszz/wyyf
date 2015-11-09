package com.power.utils.tags.test;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

public class OutTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5228035009336054628L;
	private Object value;

	/**
	 *覆盖doStartTag方法
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public int doStartTag() throws JspTagException {
		return EVAL_BODY_INCLUDE;
	}

	/**
	 *覆盖doEndTag方法
	 */
	@Override
	public int doEndTag() throws JspTagException {
		try {
			System.out.println(value);
			pageContext.getOut().write(value.toString());

		} catch (IOException ex) {
			throw new JspTagException(
					"Fatal error:hello tag conld not write to JSP out");
		}
		return EVAL_PAGE;
	}

}
