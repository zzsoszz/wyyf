/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.64
 * Generated at: 2015-09-13 15:26:03 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class _404_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html;charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=Edge,chrome=1\"/>\r\n");
      out.write("    <meta name=\"renderer\" content=\"webkit\">\r\n");
      out.write("    <title>我要验房</title>\r\n");
      out.write("    <meta name=\"keywords\" content=\"我要验房,我要设计,我要装修,找工长,找师傅,我要建材,我要家具,我要监理,我要检测,验房,网众验房,设计，装修，建材，家具，监理管家，空气检测，装修管家\"/>\r\n");
      out.write("    <meta name=\"Description\" content=\"\"/>\r\n");
      out.write("    <link rel=\"shortcut icon\" href=\"images/ico.png\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"css/bootstrap.min.css\"/>\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"css/c1/main.css\"/>\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/layout.css\"/>\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\"/>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"js/j1/jquery/jquery.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"js/j1/jquery/bootstrap.min.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"js/j1/jquery/superSlide.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"js/j1/main.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<!-- header start -->\r\n");
      out.write("<!--logo-->\r\n");
      out.write("<div class=\"heght50\"></div>\r\n");
      out.write("<div class=\"sign_header\" >\r\n");
      out.write("    <div class=\"header clearfix logintop\"style=\"\">\r\n");
      out.write("        <div class=\"logo logintl\"style=\"\"><img src=\"images/logo1.jpg\" height=\"110\"/></div>\r\n");
      out.write("        <div class=\"logintc\" style=\"\">404</div>\r\n");
      out.write("        <div class=\"logintr\" style=\"\">\r\n");
      out.write("            <a href=\"index.html\"><span class=\"redcolor\">返回首页</span></a>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"signcon\">\r\n");
      out.write("        <div class=\"error_box\" >\r\n");
      out.write("            <div class=\"error_boxcon\">\r\n");
      out.write("                <a class=\"btn btn-danger\" href=\"index.html\" >回到首页</a>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<!--显示金额-->\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- <div class=\"footer navbar-fixed-bottom\" >\r\n");
      out.write("    <div class=\"footer1\">\r\n");
      out.write("        <span>友情链接：</span>\r\n");
      out.write("        <span><a href=\"\">搜房</a></span>\r\n");
      out.write("        <span class=\"index_footWrapper_vertical\">|</span>\r\n");
      out.write("        <span><a href=\"\">中国建筑新闻网——四川</a></span>\r\n");
      out.write("        <span class=\"index_footWrapper_vertical\">|</span>\r\n");
      out.write("        <span><a href=\"\">天府房产</a></span>\r\n");
      out.write("        <span class=\"index_footWrapper_vertical\">|</span>\r\n");
      out.write("        <span><a href=\"\">成都旅行社</a></span>\r\n");
      out.write("        <span class=\"index_footWrapper_vertical\">|</span>\r\n");
      out.write("        <span><a href=\"\">成都楼盘网201409</a></span>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"footer2\">\r\n");
      out.write("        <span>联系方式：</span>\r\n");
      out.write("        <span><a href=\"\">关于我们</a></span>\r\n");
      out.write("        <span class=\"index_footWrapper_vertical\">|</span>\r\n");
      out.write("        <span><a href=\"\">联系方式</a></span>\r\n");
      out.write("        <span class=\"index_footWrapper_vertical\">|</span>\r\n");
      out.write("        <span><a href=\"\">免责申明</a></span>\r\n");
      out.write("        <span class=\"index_footWrapper_vertical\">|</span>\r\n");
      out.write("        <span><a href=\"\">反馈</a></span>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"footer2\" style=\"\">\r\n");
      out.write("        <span><a href=\"\">www.wangzhong.com</a></span>&nbsp;&nbsp;\r\n");
      out.write("        <span><a href=\"\">成都网众天弘科技有限公司</a></span>&nbsp;&nbsp;\r\n");
      out.write("        <span><a href=\"\">Copyrighgt©2015</a></span>&nbsp;&nbsp;\r\n");
      out.write("        <span><a href=\"\">蜀ICP备15018106号-1</a></span>\r\n");
      out.write("    </div>\r\n");
      out.write("</div> -->\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
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
