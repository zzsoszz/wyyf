/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.64
 * Generated at: 2015-09-13 17:28:53 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp.wyyf.helpcollection;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class helplist_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(3);
    _jspx_dependants.put("/WEB-INF/jsp/wyyf/helpcollection/../../system/common/crumbsCom.jsp", Long.valueOf(1442163370945L));
    _jspx_dependants.put("/WEB-INF/tlds/lystags.tld", Long.valueOf(1436172035332L));
    _jspx_dependants.put("/WEB-INF/jsp/wyyf/helpcollection/helpView.jsp", Long.valueOf(1436172036054L));
  }

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
      out.write("<!--fwb-->\r\n");
      out.write("<script type=\"text/javascript\" charset=\"utf-8\" src=\"http://");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${header['host']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/plugin/fwb/ueditor.config.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" charset=\"utf-8\" src=\"http://");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${header['host']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/plugin/fwb/ueditor.all.min.js\"> </script>\r\n");
      out.write("<script type=\"text/javascript\" charset=\"utf-8\" src=\"http://");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${header['host']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/plugin/fwb/lang/zh-cn/zh-cn.js\"></script>\r\n");
      out.write("<style>\r\n");
      out.write(".Validform_right{display: none;}\r\n");
      out.write("</style>\r\n");
      out.write("<div class=\"row\">\r\n");
      out.write("\t<div class=\"col-md-12\">\r\n");
      out.write("\t\t<!-- BEGIN PAGE TITLE & BREADCRUMB-->\r\n");
      out.write("\t\t");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<ul class=\"page-breadcrumb breadcrumb\">\r\n");
      out.write("\t<li>\r\n");
      out.write("\t\t<i class=\"fa fa-home\"></i>\r\n");
      out.write("\t\t<a href=\"javascript:void(0);\" id=\"cai_one\"></a> \r\n");
      out.write("\t\t<i class=\"fa fa-angle-right\"></i>\r\n");
      out.write("\t</li>\r\n");
      out.write("\t<li>\r\n");
      out.write("\t\t<a href=\"javascript:void(0);\" id=\"cai_two\"></a> \r\n");
      out.write("\t\t<i class=\"fa fa-angle-right\"></i>\r\n");
      out.write("\t</li>\r\n");
      out.write("\t<li>\r\n");
      out.write("\t\t<a href=\"javascript:void(0);\" id=\"cai_three\"></a>\r\n");
      out.write("\t</li>\r\n");
      out.write("</ul>\r\n");
      out.write("<script>\r\n");
      out.write("\t$(\"#cai_one\").text($(\"#topUlOne>li.active>a\").text());\r\n");
      out.write("\t$(\"#cai_two\").text($(\"#leftUl>li.active>a\").text());\r\n");
      out.write("\t$(\"#cai_three\").text($(\"#leftUl>li.active ul>li.active>a\").text());\r\n");
      out.write("</script>");
      out.write("\r\n");
      out.write("\t\t<!-- END PAGE TITLE & BREADCRUMB-->\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<div class=\"portlet box blue\">\r\n");
      out.write("\t<div class=\"portlet-title\">\r\n");
      out.write("\t\t<div class=\"caption\"><i class=\"fa fa-globe\"></i>意见列表</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div class=\"portlet-body\">\r\n");
      out.write("\t\t<div title=\"查询条件\">\r\n");
      out.write("\t\t\t<form id=\"queryForm\">\r\n");
      out.write("\t\t\t\t标题：<input type=\"text\" name=\"title\" class=\"form-control\" style=\"width: 200px;display: inline;\">\r\n");
      out.write("\t\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" style=\"display: none;\">\r\n");
      out.write("\t\t\t\t<button type=\"button\" id=\"queryBtn\"  class=\"btn blue\"><i class=\"fa fa-search\"></i>查询</button>\r\n");
      out.write("\t\t\t\t<button type=\"reset\" class=\"btn default\">重置</button>\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div id=\"tableList\" >\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("<!-- 添加 层-->\r\n");
      out.write("<div class=\"modal fade\" id=\"add-config\" tabindex=\"-1\" role=\"basic\"  aria-hidden=\"true\">\r\n");
      out.write("\t<div class=\"modal-dialog modal-wide\">\r\n");
      out.write("\t\t<div class=\"modal-content\">\r\n");
      out.write("\t\t\t<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\"></button>\r\n");
      out.write("\t\t\t<div class=\"modal-header\">\r\n");
      out.write("\t\t\t\t<h4 class=\"modal-title\"><span id=\"addOrEditor\">新增公告</span><span class=\"zhus\">注：带【<span style=\"color: red;\">*</span>】为必填项！</span></h4>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<form style=\"margin: 0;\" id=\"addForm\" class=\"form-horizontal\"  method=\"post\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t\t\t<div class=\"modal-footer\">\r\n");
      out.write("\t\t\t\t<button type=\"button\" id=\"sendBtn\"  class=\"btn blue\">发布</button>\r\n");
      out.write("\t\t\t\t<button type=\"button\" id=\"saveBtn\"  class=\"btn blue\">暂存</button>\r\n");
      out.write("\t\t\t\t<button type=\"button\" class=\"btn default\" data-dismiss=\"modal\">取消</button>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<!-- 查看层-->\r\n");
      out.write("<div class=\"modal fade\" id=\"view-config\" tabindex=\"-1\" role=\"basic\"  aria-hidden=\"true\">\r\n");
      out.write("\t<div class=\"modal-dialog modal-wide\">\r\n");
      out.write("\t\t<div class=\"modal-content\">\r\n");
      out.write("\t\t\t<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\"></button>\r\n");
      out.write("\t\t\t<div class=\"modal-header\">\r\n");
      out.write("\t\t\t\t<h4 class=\"modal-title\">查看意见</h4>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<form style=\"margin: 0;\" id=\"viewForm\" class=\"form-horizontal\"  method=\"post\">\r\n");
      out.write("\t\t\t\t");
      out.write("\r\n");
      out.write("<style>\r\n");
      out.write("/* 弹出对话框按钮和对话框大小 */\r\n");
      out.write(".modal-backdrop {\r\n");
      out.write("z-index: 49 !important;\r\n");
      out.write("}\r\n");
      out.write(".modal {\r\n");
      out.write("z-index: 50 !important;\r\n");
      out.write("}\r\n");
      out.write("/**预览功能时候的top*/\r\n");
      out.write(".edui-default .edui-dialog {\r\n");
      out.write(" top:40px !important;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("<!--意见查看界面 -->\r\n");
      out.write("<div class=\"form-body\">\r\n");
      out.write("\t<div class=\"row\">\r\n");
      out.write("\t\t<div class=\"col-md-12\">\r\n");
      out.write("\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t<label class=\"control-label col-md-2\">意见标题：</label>\r\n");
      out.write("\t\t\t\t<div class=\"col-md-10\">\r\n");
      out.write("\t\t\t\t    <div id=\"view_bk_st_title\" class=\"col-md-12\" style=\"padding-top: 7px;\"></div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div> \r\n");
      out.write("\t<div class=\"row\">\r\n");
      out.write("\t\t<div class=\"col-md-12\">\r\n");
      out.write("\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t<label class=\"control-label col-md-2\">意见类型：</label>\r\n");
      out.write("\t\t\t\t<div class=\"col-md-10\">\r\n");
      out.write("\t\t\t\t    <div id=\"view_bk_st_type\" class=\"col-md-12\" style=\"padding-top: 7px;\"></div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div> \r\n");
      out.write("\t<!--/row-->\r\n");
      out.write("\t<div class=\"row\">\r\n");
      out.write("\t\t<div class=\"col-md-12\">\r\n");
      out.write("\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t<label class=\"control-label col-md-2\">联系电话：</label>\r\n");
      out.write("\t\t\t\t<div class=\"col-md-10\">\r\n");
      out.write("\t\t\t\t\t<div id=\"view_bk_st_phone\" class=\"col-md-12\" style=\"padding-top: 7px;\"></div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div> \r\n");
      out.write("\t<div class=\"row\">\r\n");
      out.write("\t\t<div class=\"col-md-12\">\r\n");
      out.write("\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t<label class=\"control-label col-md-2\">意见来源：</label>\r\n");
      out.write("\t\t\t\t<div class=\"col-md-10\">\r\n");
      out.write("\t\t\t\t\t<div id=\"view_bk_st_source\" class=\"col-md-12\" style=\"padding-top: 7px;\"></div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div> \r\n");
      out.write("\t<div class=\"row\">\r\n");
      out.write("\t\t<div class=\"col-md-12\">\r\n");
      out.write("\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t<label class=\"control-label col-md-2\">提交时间：</label>\r\n");
      out.write("\t\t\t\t<div class=\"col-md-10\">\r\n");
      out.write("\t\t\t\t\t<div id=\"view_bk_dt_addDate\" class=\"col-md-12\" style=\"padding-top: 7px;\"></div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div> \r\n");
      out.write("\t<div class=\"row\">\r\n");
      out.write("\t\t<div class=\"col-md-12\">\r\n");
      out.write("\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t<label class=\"control-label col-md-2\">意见内容：</label>\r\n");
      out.write("\t\t\t\t<div class=\"col-md-10\">\r\n");
      out.write("\t\t\t\t\t<div id=\"view_bk_st_content\" class=\"col-md-12\" style=\"padding-top: 7px;\"></div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div> \r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t\t\t<div class=\"modal-footer\">\r\n");
      out.write("\t\t\t\t<button type=\"button\" class=\"btn default\" data-dismiss=\"modal\">关闭</button>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("\t//初始化打开页面 \r\n");
      out.write("\t$(document).ready(function() {\r\n");
      out.write("\t\t//DateInit.init();//时间控件初始化\r\n");
      out.write("\t\tloadotherPage(\"&pageIndex=1&pageSize=10\");\r\n");
      out.write("\t});\r\n");
      out.write("\t$(function(){\r\n");
      out.write("\t\t//查询事件\r\n");
      out.write("\t\t$(\"#queryBtn\").click(function(){\r\n");
      out.write("\t\t\tClickFindPage();//查询到第1页，--该函数在 commonList.jsp中\r\n");
      out.write("\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("\t//替换主题内容的函数\r\n");
      out.write("\tfunction loadotherPage(params){\r\n");
      out.write("\t\tvar param=$(\"#queryForm\").serialize();//条件参数\r\n");
      out.write("\t\tvar url=\"wyyf/helpCollection/sethelp\";\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tvar xsl=$(\"#commonid_column_toggler input[name='ISSHOWCOLUMS'][type='checkbox']:checked\").serialize();//显示列的控制\r\n");
      out.write("\t\tif(xsl){\r\n");
      out.write("\t\t\tparam+=\"&\"+xsl;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tnewMask();\r\n");
      out.write("\t\tjQuery(\"#tableList\").load(url,param+params+\"&rr=\"+new Date().getTime(),function(response,status,xhr){\r\n");
      out.write("\t\t\tdelMask();\r\n");
      out.write("\t\t\tif(xhr.status==403){\r\n");
      out.write("\t\t\t\t$(this).html(xhr.responseText);\r\n");
      out.write("\t\t\t}else if(xhr.status==404){\r\n");
      out.write("\t\t\t\t$(this).html(xhr.responseText);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\t\r\n");
      out.write("\t}\r\n");
      out.write("\t//进入公告查看界面\r\n");
      out.write("\tfunction view(obj){\r\n");
      out.write("\t\tvar tr = $(obj).parents(\"tr\").first();\r\n");
      out.write("\t\tvar id=tr.find(\"input[type='checkbox'].checkboxes\").val();\r\n");
      out.write("\t\tvar url = \"wyyf/helpCollection/findview\";\r\n");
      out.write("\t\t$.post(url,\"id=\"+id,function callback(data){  \r\n");
      out.write("\t\t\tvar resp = eval(\"(\"+data+\")\"); \r\n");
      out.write("\t\t\tif(resp.success){\r\n");
      out.write("\t\t\t\tvar obj=resp.result[0];\r\n");
      out.write("\t\t\t\t$(\"#view_bk_st_title\").text(tr.find(\"td[myattrone=bk_st_title]\").html());\r\n");
      out.write("\t\t\t\t$(\"#view_bk_st_type\").text(tr.find(\"td[myattrone=bk_st_type]\").html());\r\n");
      out.write("\t\t\t\t$(\"#view_bk_st_content\").text(obj.bk_st_content);\r\n");
      out.write("\t\t\t\t$(\"#view_bk_st_phone\").text(tr.find(\"td[myattrone=bk_st_phone]\").html());\r\n");
      out.write("\t\t\t\t$(\"#view_bk_st_source\").text(tr.find(\"td[myattrone=bk_st_source]\").html());\r\n");
      out.write("\t\t\t\t$(\"#view_bk_dt_addDate\").text(tr.find(\"td[myattrone=bk_dt_addDate]\").html());\r\n");
      out.write("\t\t\t\t$('#view-config').modal('show');\r\n");
      out.write("\t\t\t}else{ \r\n");
      out.write("\t\t\t\tbootbox.alert(resp.msg);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t//删除\r\n");
      out.write("\tfunction deleteHelp(obj){\r\n");
      out.write("\t\tbootbox.confirm(\"确认是否删除？\",function(rs){\r\n");
      out.write("\t\t\tif(rs){\r\n");
      out.write("\t\t\t\tvar id=$(obj).parents(\"tr\").first().find(\"input[type='checkbox'].checkboxes\").val();\r\n");
      out.write("\t\t\t\tvar url = \"wyyf/helpCollection/delete\";\r\n");
      out.write("\t\t\t\t$.post(url,\"id=\"+id,function callback(data){  \r\n");
      out.write("\t\t\t\t\tvar resp = eval(\"(\"+data+\")\"); \r\n");
      out.write("\t\t\t\t\tif(resp.success){\r\n");
      out.write("\t\t\t\t\t\tRefreshCurPage();//刷新当前页，--该函数在 commonList.jsp中\r\n");
      out.write("\t\t\t\t\t\tbootbox.alert(resp.msg);\r\n");
      out.write("\t\t\t\t\t}else{ \r\n");
      out.write("\t\t\t\t\t\tbootbox.alert(resp.msg);\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("</script> ");
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
