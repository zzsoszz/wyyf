/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.64
 * Generated at: 2015-09-14 01:46:04 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp.system.sysrole;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class sysroleList_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/tlds/lystags.tld", Long.valueOf(1436172035332L));
    _jspx_dependants.put("/WEB-INF/jsp/system/sysrole/../common/crumbsCom.jsp", Long.valueOf(1436172035598L));
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
      out.write("\r\n");
      out.write("<link href=\"http://");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${header['host']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/plugin/zTree/css/zTreeStyle/zTreeStyle.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("<script type=\"text/javascript\" src=\"http://");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${header['host']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/plugin/zTree/js/jquery.ztree.core-3.5.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"http://");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${header['host']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/plugin/zTree/js/jquery.ztree.excheck-3.5.min.js\"></script>\r\n");
      out.write("<style>\r\n");
      out.write("<!--\r\n");
      out.write("ul.ztree {margin-top: 0px;border: 1px solid #617775;background: #f0f6e4;width:95%;height:300px; overflow-x:auto;}\r\n");
      out.write("-->\r\n");
      out.write("</style>\r\n");
      out.write("<div class=\"row\">\r\n");
      out.write("\t<div class=\"col-md-12\">\r\n");
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
      out.write("<div class=\"portlet box blue\">\r\n");
      out.write("\t<!-- BEGIN EXAMPLE TABLE PORTLET-->\r\n");
      out.write("\t<div class=\"portlet-title\">\r\n");
      out.write("\t\t<div class=\"caption\"><i class=\"fa fa-globe\"></i>角色列表</div>\r\n");
      out.write("\t\t<div class=\"actions\">\r\n");
      out.write("\t\t\t<a id=\"addRoleBtn\" href=\"javascript:void(0);\" class=\"btn blue\"><i class=\"fa fa-plus\"></i>新增角色</a>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div class=\"portlet-body\">\r\n");
      out.write("\t\t<div title=\"查询条件\">\r\n");
      out.write("\t\t\t<form id=\"queryForm\">\r\n");
      out.write("\t\t\t\t名称：<input type=\"text\" name=\"ac_st_name\" class=\"form-control\" style=\"width: 200px;display: inline;\">\r\n");
      out.write("\t\t\t\t标识：<input type=\"text\" name=\"ac_st_code\" class=\"form-control\" style=\"width: 200px;display: inline;\">\r\n");
      out.write("\t\t\t\t类型：");
      if (_jspx_meth_my_005fselect_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t<button type=\"button\" id=\"queryBtn\"  class=\"btn blue\">查询</button>\r\n");
      out.write("\t\t\t\t<button type=\"reset\" class=\"btn default\">重置</button>\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div id=\"tableList\" >\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<!-- END EXAMPLE TABLE PORTLET-->\r\n");
      out.write("</div>\r\n");
      out.write("<div class=\"modal fade\" id=\"add-config\" tabindex=\"-1\" role=\"basic\"  data-backdrop=\"static\"  aria-hidden=\"true\">\r\n");
      out.write("\t<div class=\"modal-dialog modal-wide\">\r\n");
      out.write("\t\t<div class=\"modal-content\">\r\n");
      out.write("\t\t\t<div class=\"modal-header\">\r\n");
      out.write("\t\t\t\t<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\"></button>\r\n");
      out.write("\t\t\t\t<h4 class=\"modal-title\" id=\"roleedit\">新增角色</h4>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<form id=\"addForm\" action=\"#\" class=\"form-horizontal\">\r\n");
      out.write("\t\t\t\t<div class=\"form-body\">\r\n");
      out.write("\t\t\t\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"col-md-6\">\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-md-12\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<label class=\"control-label col-md-4\">角色名<font style=\"color: red;\">*</font></label>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"col-md-8\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<input name=\"ac_st_id\" type=\"hidden\" value=\"\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<input name=\"ac_st_name\" type=\"text\" class=\"form-control\" placeholder=\"\" datatype=\"*1-25\"  nullmsg=\"请输入角色名！\" errormsg=\"角色名至少一个字符，最大25个字符！\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-md-12\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<label class=\"control-label col-md-4\">角色标识<font style=\"color: red;\">*</font></label>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"col-md-8\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<input name=\"ac_st_code\" type=\"text\" class=\"form-control\" placeholder=\"\" datatype=\"*1-25\"  nullmsg=\"请输入角色标识！\"  errormsg=\"角色标识至少一个字符，最大25个字符！\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-md-12\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<label class=\"control-label col-md-4\">角色类型</label>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"col-md-8\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t");
      if (_jspx_meth_my_005fselect_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-md-12\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<label class=\"control-label col-md-4\">描述</label>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"col-md-8\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<textarea name=\"ac_st_describe\" class=\"form-control\" rows=\"3\"></textarea>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"col-md-6\"> \r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"control-label col-md-3\">权限选择</label>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-md-9\">\r\n");
      out.write("\t\t\t\t\t\t\t\t <input name=\"powerids\" type=\"hidden\">\r\n");
      out.write("\t\t\t\t\t\t\t\t <div id=\"powermenudiv\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<ul id=\"powersTree\" class=\"ztree\"  ></ul>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div> \r\n");
      out.write("\t\t\t\t\t<!--/row-->\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<div class=\"modal-footer\">\r\n");
      out.write("\t\t\t\t<button type=\"button\" id=\"saveBtn\"  class=\"btn blue\">保存</button>\r\n");
      out.write("\t\t\t\t<button type=\"button\" class=\"btn default\" data-dismiss=\"modal\">取消</button>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<!-- /.modal-content -->\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<!-- /.modal-dialog -->\r\n");
      out.write("</div>\r\n");
      out.write("<script>\r\n");
      out.write("\r\n");
      out.write("\t//初始化打开页面 \r\n");
      out.write("\t$(document).ready(function() {\r\n");
      out.write("\t\t loadotherPage(\"&pageIndex=1&pageSize=10\");\r\n");
      out.write("\t});\r\n");
      out.write("\t$(function(){\r\n");
      out.write("\t\t//进入新增层\r\n");
      out.write("\t\t$(\"#addRoleBtn\").click(function(){\r\n");
      out.write("\t\t\tinitValidForm($(\"#addForm\"));//清除表单验证样式\r\n");
      out.write("\t\t\t$(\"#addForm\")[0].reset();\r\n");
      out.write("\t\t\t$(\"#addForm input[name='ac_st_id']\").val(\"\");//清空隐藏的文本框ID值\r\n");
      out.write("\t\t\t$(\"#addForm input[name='powerids']\").val(\"\");//清空隐藏的文本框权限值\r\n");
      out.write("\t\t\t$.fn.zTree.getZTreeObj(\"powersTree\").checkAllNodes(false);//取消勾选 全部节点\r\n");
      out.write("\t\t\t$(\"#roleedit\").text(\"新增角色\");\t\r\n");
      out.write("\t\t\t$('#add-config').modal('show');\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t//新增-修改权限层的保存功能\r\n");
      out.write("\t\t$(\"#saveBtn\").click(function(){\r\n");
      out.write("\t\t\tcheckFormValid($(\"#addForm\"),function(){\r\n");
      out.write("\t\t\t\tvar url = \"system/sysrole/sysroleAdd\";\r\n");
      out.write("\t\t\t\tvar params=$(\"#addForm\").serialize();\r\n");
      out.write("\t\t\t\tnewMask();\r\n");
      out.write("\t\t\t\t$.post(url,params,function callback(data){  \r\n");
      out.write("\t\t\t\t\tdelMask();\r\n");
      out.write("\t\t\t\t\tvar resp = eval(\"(\"+data+\")\"); \r\n");
      out.write("\t\t\t\t\tif(resp.success){\r\n");
      out.write("\t\t\t\t\t\tRefreshCurPage();//刷新当前页，--该函数在 commonList.jsp中\r\n");
      out.write("\t\t\t\t\t\tbootbox.alert(resp.msg);\r\n");
      out.write("\t\t\t\t\t\t$('#add-config').modal('hide');\r\n");
      out.write("\t\t\t\t\t}else{ \r\n");
      out.write("\t\t\t\t\t\tbootbox.alert(resp.msg);\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t//查询事件\r\n");
      out.write("\t\t$(\"#queryBtn\").click(function(){\r\n");
      out.write("\t\t\tClickFindPage();//查询到第1页，--该函数在 commonList.jsp中\r\n");
      out.write("\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("\t//查询函数--替换主题内容的函数\r\n");
      out.write("\tfunction loadotherPage(params) {\r\n");
      out.write("\t\tvar param=$(\"#queryForm\").serialize();//条件参数\r\n");
      out.write("\t\tvar url=\"system/sysrole/sysroleList\";\r\n");
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
      out.write("\t//进入修改角色\r\n");
      out.write("\tfunction editRole(obj){\r\n");
      out.write("\t\tinitValidForm($(\"#addForm\"));//清除表单验证样式\r\n");
      out.write("\t\t$(\"#roleedit\").text(\"编辑角色\");\r\n");
      out.write("\t\tvar roleid=$(obj).parents(\"tr\").first().find(\"input[type='checkbox'].checkboxes\").val();\r\n");
      out.write("\t\tvar url = \"system/sysrole/findRoleById\";\r\n");
      out.write("\t\tnewMask();\r\n");
      out.write("\t\t$.post(url,\"roleid=\"+roleid,function callback(data){  \r\n");
      out.write("\t\t\tdelMask();\r\n");
      out.write("\t\t\tvar resp = eval(\"(\"+data+\")\"); \r\n");
      out.write("\t\t\tif(resp.success){\r\n");
      out.write("\t\t\t\tvar obj=resp.result[0];\r\n");
      out.write("\t\t\t\tformload($(\"#addForm\"),obj);\r\n");
      out.write("\t\t\t\t$('#add-config').modal('show');\r\n");
      out.write("\t\t\t\tvar treeObj = $.fn.zTree.getZTreeObj(\"powersTree\");\r\n");
      out.write("\t\t\t\ttreeObj.checkAllNodes(false);//取消勾选 全部节点\r\n");
      out.write("\t\t\t\tvar powerids=obj.powerids;\r\n");
      out.write("\t\t\t\tif(powerids!=null&&powerids!=\"\"){\r\n");
      out.write("\t\t\t\t\tvar poweridarry=powerids.split(\",\");\r\n");
      out.write("\t\t\t\t\tfor(var i=0;i<poweridarry.length;i++){\r\n");
      out.write("\t\t\t\t\t\tvar node=treeObj.getNodesByParam(\"TREEID\", poweridarry[i])[0];\r\n");
      out.write("\t\t\t\t\t\tif(!node.checked){\r\n");
      out.write("\t\t\t\t\t\t\ttreeObj.checkNode(node, true, false);\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t} \r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}else{ \r\n");
      out.write("\t\t\t\tbootbox.alert(resp.msg);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t//角色删除 \r\n");
      out.write("\tfunction delRole(obj){\r\n");
      out.write("\t\tbootbox.confirm(\"确认删除？\",function(rs){\r\n");
      out.write("\t\t\tif(rs){\r\n");
      out.write("\t\t\t\tvar roleid=$(obj).parents(\"tr\").first().find(\"input[type='checkbox'].checkboxes\").val();\r\n");
      out.write("\t\t\t\tvar url = \"system/sysrole/sysroleDelete\";\r\n");
      out.write("\t\t\t\tnewMask();\r\n");
      out.write("\t\t\t\t$.post(url,\"deleteData=\"+roleid,function callback(data){\r\n");
      out.write("\t\t\t\t\tdelMask();\r\n");
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
      out.write("</script> \r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t\t<!--\r\n");
      out.write("\t\tvar setting = {\r\n");
      out.write("\t\t\tcheck: {\r\n");
      out.write("\t\t\t\tenable: true,\r\n");
      out.write("\t\t\t\tchkboxType: {\"Y\":\"p\", \"N\":\"ps\"}\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tview: {\r\n");
      out.write("\t\t\t\tdblClickExpand: false\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tdata: {\r\n");
      out.write("\t\t\t\tkey: {\r\n");
      out.write("\t\t\t\t\tname:\"TREENAME\"\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t,simpleData: {\r\n");
      out.write("\t\t\t\t\tenable: true,\r\n");
      out.write("\t\t\t\t\tidKey: \"TREEID\",\r\n");
      out.write("\t\t\t\t\tpIdKey: \"TREEPID\"\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tcallback: {\r\n");
      out.write("\t\t\t\tbeforeClick: beforeClick,\r\n");
      out.write("\t\t\t\tonCheck: onCheck\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t};\r\n");
      out.write("\r\n");
      out.write("\t\tvar zNodes =");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${functionTree }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(";\r\n");
      out.write("\r\n");
      out.write("\t\tfunction beforeClick(treeId, treeNode) {\r\n");
      out.write("\t\t\tvar zTree = $.fn.zTree.getZTreeObj(\"powersTree\");\r\n");
      out.write("\t\t\tzTree.checkNode(treeNode, !treeNode.checked, true, true);\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tfunction onCheck(e, treeId, treeNode) {\r\n");
      out.write("\t\t\tvar zTree = $.fn.zTree.getZTreeObj(\"powersTree\"),\r\n");
      out.write("\t\t\tnodes = zTree.getCheckedNodes(true),\r\n");
      out.write("\t\t\tv = \"\";\r\n");
      out.write("\t\t\tfor (var i=0, l=nodes.length; i<l; i++) {\r\n");
      out.write("\t\t\t\tv += nodes[i].TREEID + \",\";\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tif (v.length > 0 ) v = v.substring(0, v.length-1);\r\n");
      out.write("\t\t\t$(\"#addForm input[name='powerids']\").val(v);\r\n");
      out.write("\t\t}  \r\n");
      out.write("\t\t$(document).ready(function(){\r\n");
      out.write("\t\t\t$.fn.zTree.init($(\"#powersTree\"), setting, zNodes);\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t//-->\r\n");
      out.write("</script>\r\n");
      out.write(" \r\n");
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

  private boolean _jspx_meth_my_005fselect_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  my:select
    com.power.utils.tags.form.select.SelectTag _jspx_th_my_005fselect_005f0 = (new com.power.utils.tags.form.select.SelectTag());
    _jsp_instancemanager.newInstance(_jspx_th_my_005fselect_005f0);
    _jspx_th_my_005fselect_005f0.setJspContext(_jspx_page_context);
    // /WEB-INF/jsp/system/sysrole/sysroleList.jsp(31,7) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_my_005fselect_005f0.setName("ac_st_type");
    // /WEB-INF/jsp/system/sysrole/sysroleList.jsp(31,7) name = clazz type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_my_005fselect_005f0.setClazz("form-control");
    // /WEB-INF/jsp/system/sysrole/sysroleList.jsp(31,7) name = isAddDefaltOption type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_my_005fselect_005f0.setIsAddDefaltOption(new java.lang.Boolean(true));
    // /WEB-INF/jsp/system/sysrole/sysroleList.jsp(31,7) name = defaltOptionKey type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_my_005fselect_005f0.setDefaltOptionKey("");
    // /WEB-INF/jsp/system/sysrole/sysroleList.jsp(31,7) name = defaltOptionValue type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_my_005fselect_005f0.setDefaltOptionValue("--新选择--");
    // /WEB-INF/jsp/system/sysrole/sysroleList.jsp(31,7) name = nameKey type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_my_005fselect_005f0.setNameKey("JSLXZD");
    // /WEB-INF/jsp/system/sysrole/sysroleList.jsp(31,7) name = style type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_my_005fselect_005f0.setStyle("width: 200px; display:inline;");
    _jspx_th_my_005fselect_005f0.doTag();
    _jsp_instancemanager.destroyInstance(_jspx_th_my_005fselect_005f0);
    return false;
  }

  private boolean _jspx_meth_my_005fselect_005f1(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  my:select
    com.power.utils.tags.form.select.SelectTag _jspx_th_my_005fselect_005f1 = (new com.power.utils.tags.form.select.SelectTag());
    _jsp_instancemanager.newInstance(_jspx_th_my_005fselect_005f1);
    _jspx_th_my_005fselect_005f1.setJspContext(_jspx_page_context);
    // /WEB-INF/jsp/system/sysrole/sysroleList.jsp(74,10) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_my_005fselect_005f1.setName("ac_st_type");
    // /WEB-INF/jsp/system/sysrole/sysroleList.jsp(74,10) name = clazz type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_my_005fselect_005f1.setClazz("form-control");
    // /WEB-INF/jsp/system/sysrole/sysroleList.jsp(74,10) name = nameKey type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_my_005fselect_005f1.setNameKey("JSLXZD");
    // /WEB-INF/jsp/system/sysrole/sysroleList.jsp(74,10) name = initSelectedKey type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_my_005fselect_005f1.setInitSelectedKey("0");
    _jspx_th_my_005fselect_005f1.doTag();
    _jsp_instancemanager.destroyInstance(_jspx_th_my_005fselect_005f1);
    return false;
  }
}
