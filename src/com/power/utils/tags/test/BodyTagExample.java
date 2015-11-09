package com.power.utils.tags.test;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;


public class BodyTagExample extends BodyTagSupport {
	private static final long serialVersionUID = 986345721211L;
	int counts;// counts为迭代的次数。

	public BodyTagExample() {
		super();
	}

	/**
	 *设置counts属性。这个方法由容器自动调用。
	 */
	public void setCounts(int c) {
		this.counts = c;
	}

	/**
	 *覆盖doStartTag方法
	 */
	@Override
	public int doStartTag() throws JspTagException {
		System.out.println("doStartTag");
		if (counts > 0) {
			return EVAL_BODY_BUFFERED;//申请缓冲区，由setBodyContent()函数得到的BodyContent对象来处理tag的body，如果类实现了BodyTag，那么doStartTag()可用，否则非法
		} else {
			return SKIP_BODY;//如果返回SKIP_BODY则接下来的doInitBody(),setBodyContent(), doAfterBody()三个方法不会被执行、表示不显示标签间的文字
		}
	}

	@Override
	public void setBodyContent(BodyContent bodyContent) {
		System.out.println("setBodyContent");
		this.bodyContent = bodyContent;
	}
	@Override
	public void doInitBody() throws JspTagException {
		System.out.println("doInitBody");
	}
	/**
	 *覆盖doAfterBody方法
	 */
	@Override
	public int doAfterBody() throws JspTagException {
		System.out.println("doAfterBody" + counts);
		if (counts > 1) {
			counts--;
			return EVAL_BODY_BUFFERED;//申请缓冲区，由setBodyContent()函数得到的BodyContent对象来处理tag的body，如果类实现了BodyTag，那么doStartTag()可用，否则非法
		} else {
			return SKIP_BODY;//继续执行标签处理的下一步。
		}
	}

	/**
	 *覆盖doEndTag方法
	 */
	@Override
	public int doEndTag() throws JspTagException {
		System.out.println("doEndTag");
		try {
			if (bodyContent != null) {
				bodyContent.writeOut(bodyContent.getEnclosingWriter());
			}
		} catch (java.io.IOException e) {
			throw new JspTagException("IO Error: " + e.getMessage());
		}
		return EVAL_PAGE;//表示处理完标签后继续执行以下的JSP网页
		//return SKIP_PAGE;//表示不处理接下来的JSP网页
	}


}
