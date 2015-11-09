package com.power.utils.tags.test;

import java.io.IOException;
import java.util.Date;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;
 
/**
 *演示从TagSupport继承来开发标签
 */
public class HelloTag extends TagSupport {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1231554229L;

	/**
     *覆盖doStartTag方法
     */
    @Override
	public int doStartTag() throws JspTagException {
    	return EVAL_BODY_INCLUDE;//告诉服务器正文的内容，并把这些内容送入输出流
    }

    /**
     *覆盖doEndTag方法
     */
    @Override
	public int doEndTag()throws JspTagException{
        String dateString =new Date().toString();
        try{
            pageContext.getOut().write("Hello World hellking.<br>现在的时间是："+dateString);
        }
        catch(IOException ex){
            throw new JspTagException("Fatal error:hello tag conld not write to JSP out");
        }
        return EVAL_PAGE;
    }
}
