/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.64
 * Generated at: 2015-09-13 15:45:24 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp.wap;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.power.utils.PowerStatic;
import com.lys.utils.StringUtils;
import java.util.Map;
import java.util.List;
import com.lys.utils.pagination.PageBean;

public final class designstyle_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");


String type=(String)request.getAttribute("type");
PageBean pages=(PageBean)request.getAttribute("pageBeanObj");
List<Map<String, Object>> dataList=pages.getList();
for(int j=0,b=dataList.size();j<b;j++)
{
			   Map<String, Object> map= dataList.get(j);//行数据

      out.write("\r\n");
      out.write("\t<li class=\"design_people clearfix\" >\r\n");
      out.write("\t\t  \t<a href=\"wap/designermess?sjs=1&fl=");
      out.print(type);
      out.write("&id=");
      out.print(StringUtils.toStringByObject(map.get("ae_st_id")) );
      out.write("\">\r\n");
      out.write("\t\t  \t\t<div class=\"design_pleft\"  style=\"\">\r\n");
      out.write("\t\t  \t \t  <div class=\"design_pic\" style=\"\">\r\n");
      out.write("\t\t  \t \t  \t<img src=\"");
      out.print(StringUtils.toStringByObject(map.get("ag_st_url")) );
      out.write("\" class=\"img-responsive\" />\r\n");
      out.write("\t\t  \t \t  </div>\r\n");
      out.write("\t\t  \t \t  <span>");
      out.print(StringUtils.toStringByObject(map.get("ae_st_name")) );
      out.write("</span>\r\n");
      out.write("\t\t  \t   </div>\r\n");
      out.write("\t \t  \t <div class=\"design_pright clearfix\" style=\"\">\r\n");
      out.write("\t \t  \t \t <ul class=\"star_px clearfix\">\r\n");
      out.write("\t \t  \t \t \t");

					 	    int num=Integer.parseInt(StringUtils.toStringByObject(map.get("ba_st_grade")));
					 		for(int i=0;i<num; i++) 
					 		{
					 		
      out.write("\r\n");
      out.write("\t\t\t\t\t \t\t\t<li><img src='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/images/wap/images/star1.png'  class='img-responsive'></li>\r\n");
      out.write("\t\t\t\t\t\t\t");

							}
					
      out.write("\r\n");
      out.write("\t \t  \t \t </ul>\r\n");
      out.write("\t \t  \t \t <p class=\"margin_bott\">技能：");
      out.print(StringUtils.toStringByObject(map.get("ae_st_intro")) );
      out.write(" </p>\r\n");
      out.write("\t \t  \t \t <span>订单数：123 </span><span >团队人数：99</span>\r\n");
      out.write("\t \t  \t </div>\r\n");
      out.write("\t\t  \t </a> \r\n");
      out.write("\t</li>\r\n");

}

      out.write('\r');
      out.write('\n');
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
