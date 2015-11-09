package com.power.utils.tags.test;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;


@SuppressWarnings("rawtypes")
public class IterateTag extends BodyTagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3873888230922258456L;
	/**
	 *Tag的属性
	 */
	private String name;
	// it为要迭代的对象
	private Iterator it;
	// type表示it中对象的类型
	private String type;

	/**
	 *设置属性collection
	 */
	public void setCollection(Collection collection) {
		if (collection.size() > 0)
			it = collection.iterator();
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}
	

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	/**
	 *如果it属性为null，那么忽略计算tagbody。
	 */
	@Override
	public int doStartTag() throws JspTagException {
		if (it == null)
			return SKIP_BODY;
		else
			return continueNext(it);
	}

	@Override
	public int doAfterBody() throws JspTagException {
		return continueNext(it);
	}

	@Override
	public int doEndTag() throws JspTagException {
		try {
			if (bodyContent != null)
				bodyContent.writeOut(bodyContent.getEnclosingWriter());
		} catch (java.io.IOException e) {
		}
		return EVAL_PAGE;
	}

	/**
	 *保护方法，用于把it.next()设置为pagecontext的属性
	 */
	@SuppressWarnings("deprecation")
	protected int continueNext(Iterator it) throws JspTagException {
		if (it.hasNext()) {
			pageContext.setAttribute(name, it.next(), PageContext.PAGE_SCOPE);
			return EVAL_BODY_TAG;
		} else {
			return SKIP_BODY;
		}
	}
}
