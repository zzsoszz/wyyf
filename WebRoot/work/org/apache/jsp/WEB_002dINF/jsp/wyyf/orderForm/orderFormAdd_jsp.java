/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.64
 * Generated at: 2015-09-13 17:28:42 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp.wyyf.orderForm;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.lys.utils.StringUtils;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Date;

public final class orderFormAdd_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/WEB-INF/tlds/lystags.tld", Long.valueOf(1436172035332L));
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
      response.setContentType("text/html;charset=UTF-8");
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
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"http://");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${header['host']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/plugin/autocomplete/jquery.autocomplete.css\" />\r\n");
      out.write("<script type=\"text/javascript\" src=\"http://");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${header['host']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/plugin/autocomplete/jquery.autocomplete.js\"></script>\r\n");
      out.write("<style>\r\n");
      out.write("ul{list-style-type: none;}\r\n");
      out.write("</style>\r\n");
      out.write("<div class=\"modal-header\">\r\n");
      out.write("\t<h4  class=\"modal-title\"><span id=\"titleText\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${textTitle }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("订单</span><span class=\"zhus\">注：带【<span style=\"color: red;\">*</span>】为必填项！</span></h4>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<form id=\"addForm\" class=\"form-horizontal\"  method=\"post\">\r\n");
      out.write("\t<div class=\"form-body\">\r\n");
      out.write("\t\t \r\n");
      out.write("\t\t<div class=\"portlet box green\">\r\n");
      out.write("\t\t\t<div class=\"portlet-title\">\r\n");
      out.write("\t\t\t\t<div class=\"caption\"><i class=\"fa fa-reorder\"></i>订单基本信息</div>\r\n");
      out.write("\t\t\t\t<div class=\"tools\">\r\n");
      out.write("\t\t\t\t\t<a href=\"javascript:;\" class=\"collapse\"></a>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"portlet-body addBorder\">\r\n");
      out.write("\t\t\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t\t\t<div class=\"col-md-6\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"control-label col-md-4\">订单编号</label>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-md-8\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<div style=\"display:none;\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<input name=\"bh_st_id\" type=\"text\" value=\"\" alt=\"订单ID\">\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t<input name=\"bh_st_ddnum\" type=\"text\" class=\"form-control\"   readonly=\"readonly\"/>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<!-- /row -->\r\n");
      out.write("\t\t\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t\t\t<div class=\"col-md-6\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"control-label col-md-4\">收货人姓名<span style=\"color: red;\">*</span></label>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-md-8\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<input name=\"bh_st_shname\" type=\"text\" class=\"form-control\" datatype=\"*2-25\"  nullmsg=\"请输入收货人姓名！\"  errormsg=\"姓名至少2个字符，最大25个字符\" />\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"col-md-6\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"control-label col-md-4\">收货人电话<span style=\"color: red;\">*</span></label>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-md-8\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<input name=\"bh_st_shphone\" type=\"text\" class=\"form-control\" maxlength=\"15\" datatype=\"*2-15\"  nullmsg=\"请输入收货人电话！\"  errormsg=\"姓名至少2个字符，最大15个字符\"/>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<!-- /row -->\r\n");
      out.write("\t\t\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t\t\t<div class=\"col-md-6\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"control-label col-md-4\">收货人地址<span style=\"color: red;\">*</span></label>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-md-8\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<input name=\"bh_st_shaddress\" type=\"text\" class=\"form-control\" maxlength=\"50\"  datatype=\"*2-25\"  nullmsg=\"请输入收货人地址！\"  errormsg=\"姓名至少2个字符，最大25个字符\"/>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"col-md-6\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"control-label col-md-4\">下单人帐号<span style=\"color: red;\">*</span></label>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-md-8\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<input name=\"username\" type=\"text\" class=\"form-control\" maxlength=\"25\" datatype=\"*3-25\"  nullmsg=\"请输入下单人帐号！\"  errormsg=\"帐号至少3个字符，最大25个字符\"/>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<!-- /row -->\r\n");
      out.write("\t\t\t\t<div class=\"row\" >\r\n");
      out.write("\t\t\t\t\t<div class=\"col-md-12\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"control-label col-md-2\">客户建议</label>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-md-10\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<textarea name=\"bh_st_ddremark\"  rows=\"3\" class=\"form-control\" maxlength=\"250\"></textarea>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<!-- /row -->\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<!-- /portlet -->\r\n");
      out.write("\t\t<div class=\"portlet box purple\">\r\n");
      out.write("\t\t\t<div class=\"portlet-title\">\r\n");
      out.write("\t\t\t\t<div class=\"caption\"><i class=\"fa fa-reorder\"></i>商品信息</div>\r\n");
      out.write("\t\t\t\t<div class=\"tools\">\r\n");
      out.write("\t\t\t\t\t<a href=\"javascript:;\" class=\"collapse\"></a>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"portlet-body\">\r\n");
      out.write("\t\t\t\t<div class=\"row\" style=\"margin-bottom:15px;\">\r\n");
      out.write("\t\t\t\t\t&emsp;商品总价格&nbsp;\r\n");
      out.write("\t\t\t\t\t<input  type=\"text\" class=\"form-control\" value=\"下单时候系统自动计算\" readonly=\"readonly\"  style=\"width: 550px; display: inline;\" />\r\n");
      out.write("\t\t\t\t\t<button type=\"button\" onclick=\"addtabobj(this,'tab1_All')\" class=\"btn blue\" myattrname=\"prodList\">点击新增<i class=\"fa fa-arrow-down\"></i></button>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<!-- /row -->\r\n");
      out.write("\t\t\t\t<div id=\"tab1_All\">\r\n");
      out.write("\t\t\t\t");

					List<Map<String,Object>> prodList=(List<Map<String,Object>>)request.getAttribute("workExps");
					for(int i=0,b=prodList.size();i<b+1;i++){
						Map<String,Object> workmap=new HashMap<String,Object>();
						if(b>0&&i<b){
							workmap=prodList.get(i);
						}
						String wymark="prodList["+i+"]";
						String divId="tab_prodList"+i+new Date().getTime();
						if(i==b){
							wymark="wduserzblist";
							divId="tab1_demo";
						}
				
      out.write("\r\n");
      out.write("\t\t\t\t\t<div id=\"");
      out.print(divId );
      out.write("\" class=\"addBorder\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"row\" style=\"margin:3px 0px 3px;\">\r\n");
      out.write("\t\t\t\t\t\t\t商&emsp;品&emsp;名<span style=\"color: red;\">*</span>\r\n");
      out.write("\t\t\t\t\t\t\t<input  type=\"text\" name=\"");
      out.print(wymark );
      out.write(".bi_st_remark\" class=\"form-control autocomplete\"  style=\"width: 550px; display: inline;\" value=\"");
      out.print(StringUtils.toStringByObject(workmap.get("bi_st_remark")) );
      out.write("\" datatype=\"*\"  nullmsg=\"请输入商品名称！\" />\r\n");
      out.write("\t\t\t\t\t\t\t<div style=\"display:none;\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<input  type=\"text\" name=\"");
      out.print(wymark );
      out.write(".bi_st_spnum\" datatype=\"*\"  nullmsg=\"请输入商品号！\" />\r\n");
      out.write("\t\t\t\t\t\t\t\t<input  type=\"text\" name=\"");
      out.print(wymark );
      out.write(".bi_st_bbid\"  />\r\n");
      out.write("\t\t\t\t\t\t\t\t<input  type=\"text\" name=\"");
      out.print(wymark );
      out.write(".bi_st_id\" markName=\"ID\" markCode=\"bi_st_id\"/>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t单价<input name=\"");
      out.print(wymark );
      out.write(".bi_st_spprice\" type=\"text\" style=\"width: 100px; display: inline;\" readonly=\"readonly\" class=\"form-control\" value=\"");
      out.print(StringUtils.toStringByObject(workmap.get("bi_st_spprice")) );
      out.write("\"  >\r\n");
      out.write("\t\t\t\t\t\t\t商品数量<span style=\"color: red;\">*</span>\r\n");
      out.write("\t\t\t\t\t\t\t<input name=\"");
      out.print(wymark );
      out.write(".bi_st_spsl\" type=\"text\" style=\"width: 100px; display: inline;\" class=\"form-control\" value=\"");
      out.print(!"".equals(StringUtils.toStringByObject(workmap.get("bi_st_spsl")))?StringUtils.toStringByObject(workmap.get("bi_st_spsl")):"1" );
      out.write("\" datatype=\"/^(0|([1-9]\\d*))$/\" errormsg=\"请输入10位以内的数字！\" nullmsg=\"请输入商品数量！\" maxlength=\"10\" >\r\n");
      out.write("\t\t\t\t\t\t\t<button class=\"btn btn-sm red\" onclick=\"deltabobj(this)\"  type=\"button\">删除<i class=\"fa fa-trash-o\"></i></button>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t");
	} 
      out.write("\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"row\" style=\"margin-top:20px;\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"col-md-12\">\r\n");
      out.write("\t\t\t\t\t\t\t<font color=\"red\">\r\n");
      out.write("\t\t\t\t\t\t\t\t注：\r\n");
      out.write("\t\t\t\t\t\t\t\t<br/>&emsp;一、当正常下单的时候，【下单人帐号】必须属实填写 。\r\n");
      out.write("\t\t\t\t\t\t\t</font>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t<!-- /portlet -->\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div class=\"modal-footer\">\r\n");
      out.write("\t\t<button type=\"button\" id=\"saveBtn\"  class=\"btn blue\">确认下单</button>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<p>&nbsp;<p/> <p>&nbsp;<p/> <p>&nbsp;<p/> \r\n");
      out.write("</form>\r\n");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("\t//表单数据\r\n");
      out.write("\tvar infojsonData=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${infojsonData}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(";\r\n");
      out.write("\t//初始化打开页面 \r\n");
      out.write("\t$(function() {\r\n");
      out.write("\r\n");
      out.write("\t\t//加载订单基本信息\r\n");
      out.write("\t\tif(infojsonData.success){\r\n");
      out.write("\t\t\tvar objresult=infojsonData.result[0];\r\n");
      out.write("\t\t\tformload($(\"#addForm\"),objresult);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t//点击保存事件\r\n");
      out.write("\t\t$(\"#saveBtn\").click(function(){\r\n");
      out.write("\t\t\tif($(\"#tab1_All div\").size()<1){\r\n");
      out.write("\t\t\t\tbootbox.alert(\"请至少添加一件商品！\");\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tcheckFormValid($(\"#addForm\"),function(){\r\n");
      out.write("\t\t\t\tbootbox.confirm('确定是否下单？',function(rs){\r\n");
      out.write("\t\t\t\t\tif(rs){\r\n");
      out.write("\t\t\t\t\t\tnewMask();\r\n");
      out.write("\t\t\t\t\t\tvar url = \"wyyf/orderForm/orderFormSave\";\r\n");
      out.write("\t\t\t\t\t\tvar params=$(\"#addForm\").serialize();\r\n");
      out.write("\t\t\t\t\t\t$.post(url,params,function callback(data){ \r\n");
      out.write("\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\tdelMask();\r\n");
      out.write("\t\t\t\t\t\t\tvar id=$(\"#addForm input[name='bh_st_id']\").val();\r\n");
      out.write("\t\t\t\t\t    \tvar resp = eval(\"(\"+data+\")\"); \r\n");
      out.write("\t\t\t\t\t\t\tif(resp.success){\r\n");
      out.write("\t\t\t\t\t\t\t\t//修改\r\n");
      out.write("\t\t\t\t\t\t\t\tif(id!=null&&id!=\"\"){\r\n");
      out.write("\t\t\t\t\t\t\t\t\tRefreshCurPage();//刷新当前页\r\n");
      out.write("\t\t\t\t\t\t\t\t\t$(\"#colceupdcBtn\").click();\r\n");
      out.write("\t\t\t\t\t\t\t\t\tbootbox.alert(\"保存成功！\");\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t//新增\r\n");
      out.write("\t\t\t\t\t\t\t\telse{\r\n");
      out.write("\t\t\t\t\t\t            bootbox.confirm('新增成功，是否重置？',function(rs){\r\n");
      out.write("\t\t\t\t\t\t    \t\t\tif(rs){\r\n");
      out.write("\t\t\t\t\t\t    \t\t\t\tloadMainPage('wyyf/orderForm/intoOrderSave');//刷新本界面\r\n");
      out.write("\t\t\t\t\t\t    \t\t\t}\r\n");
      out.write("\t\t\t\t\t\t    \t\t});\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t}else{ \r\n");
      out.write("\t\t\t\t\t\t\t\tbootbox.alert(resp.msg);\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t}); \r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t});  \r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("</script>\r\n");
      out.write("<!-- 商品搜索输入 -->\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("    $(function(){\r\n");
      out.write("    \tinitautocomp();//初始化自动补全控件\r\n");
      out.write("    });\r\n");
      out.write("    //初始化自动补全控件\r\n");
      out.write("\tvar personnels = ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${allprodauto}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(";//所有酒品数据 \r\n");
      out.write("    function initautocomp(){\r\n");
      out.write("    \t $(\".autocomplete\").autocomplete(personnels,{\r\n");
      out.write("             minChars: 0,//自动完成激活之前填入的最小字符\r\n");
      out.write("\t\t\t  max:30,//列表条目数\r\n");
      out.write("\t\t\t  resultsClass:\"dropdown-menu\",//设置返回结果的Class\r\n");
      out.write("\t\t\t  width: 550,//提示的宽度\r\n");
      out.write("\t\t\t  scrollHeight: 300,//提示的高度\r\n");
      out.write("\t\t\t  matchContains: true,//是否只要包含文本框里的就可以\r\n");
      out.write("\t\t\t  autoFill:false,//自动填充\r\n");
      out.write("\t\t\t  formatItem: function(data, i, max) {//格式化列表中的条目 row:条目对象,i:当前条目数,max:总条目数\r\n");
      out.write("    \t\t\t\treturn '<label class=\"col-md-12\"><label class=\"col-md-8\">' + data.bg_st_name + '</label><label class=\"col-md-2\">'+data.price+ ' </label><label class=\"col-md-2\">'+data.typename+'</label></label>'\r\n");
      out.write("             },\r\n");
      out.write("             formatMatch: function(data, i, max) {//配合formatItem使用，作用在于，由于使用了formatItem，所以条目中的内容有所改变，而我们要匹配的是原始的数据，所以用formatMatch做一个调整，使之匹配原始数据\r\n");
      out.write("\t\t\t\t\treturn data.bg_st_name;\r\n");
      out.write("             },\r\n");
      out.write("\t\t\t  formatResult: function(data) {//定义最终返回的数据，比如我们还是要返回原始数据，而不是formatItem过的数据\r\n");
      out.write("\t\t\t\t\treturn data.bg_st_name;\r\n");
      out.write("             }\r\n");
      out.write("\t       }).result(function(event,data,formatted){\r\n");
      out.write("\t\t      var formv=$(event.currentTarget).parents(\"div.addBorder\").first();\r\n");
      out.write("\t\t      formv.find(\"input[name$='.bi_st_spnum']\").val(data.bg_st_num);\r\n");
      out.write("\t\t      formv.find(\"input[name$='.bi_st_bbid']\").val(data.bg_st_bbid);\r\n");
      out.write("\t\t      formv.find(\"input[name$='.bi_st_spprice']\").val(data.price);\r\n");
      out.write("\t       });\r\n");
      out.write("    \t//新增失去焦点事件\r\n");
      out.write("\t\t$(\".autocomplete\").blur(function(){\r\n");
      out.write("\t\t\t $(this).val(obtain($(this).parents(\"div.addBorder\").first().find(\"input[name$='.bi_st_spnum']\").val()));\r\n");
      out.write("\t\t});\r\n");
      out.write("    }\r\n");
      out.write("  //通过集团获取对应Name\r\n");
      out.write("\tfunction obtain(key){\r\n");
      out.write("\t\tvar name=\"\";\r\n");
      out.write("\t\t$.each(personnels,function(n,data) {\r\n");
      out.write("            if(data.bg_st_num == key){\r\n");
      out.write("            \tname=data.bg_st_name;\r\n");
      out.write("\t\t\t\treturn false; \r\n");
      out.write("\t         }\r\n");
      out.write("\t\t });\r\n");
      out.write("\t\treturn name;\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("<!-- 商品新增事件 -->\r\n");
      out.write("<script>\r\n");
      out.write("\t//商品信息\r\n");
      out.write("\tvar tab1_=$(\"#tab1_demo\").html();\r\n");
      out.write("\t$(\"#tab1_demo\").remove();\r\n");
      out.write("\tvar tab1_num=");
      out.print(prodList.size() );
      out.write(";\r\n");
      out.write("\t//新增\r\n");
      out.write("\tfunction addtabobj(obj,targetid){\r\n");
      out.write("\t\tvar myattrname=$(obj).attr(\"myattrname\");\r\n");
      out.write("\t\tvar htmltab=\"\";\r\n");
      out.write("\t\tvar htmlnum=0;\r\n");
      out.write("\t\tif(myattrname==\"prodList\"){\r\n");
      out.write("\t\t\thtmltab=tab1_;\r\n");
      out.write("\t\t\thtmlnum=tab1_num++;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t$(\"#\"+targetid).append('<div id=\"tab_'+$(obj).attr(\"myattrname\")+new Date().getTime()+'\" class=\"addBorder\">'+htmltab.replace(/wduserzblist\\./g, $(obj).attr(\"myattrname\")+\"[\"+htmlnum+\"].\")+'</div>');\r\n");
      out.write("\t\t//DateInit.init();//时间控件初始化\r\n");
      out.write("\t\tinitautocomp();//初始化自动补全控件\r\n");
      out.write("\t}\r\n");
      out.write("\t//移除商品当前对象\r\n");
      out.write("\tfunction deltabobj(obj){\r\n");
      out.write("\t\tvar parents=$(obj).parents(\"div.addBorder\").first();\r\n");
      out.write("\t\tvar id=parents.find(\"input[markName='ID']\").val();\r\n");
      out.write("\t\t//如果该条数据已经入库，删除时要请求 \r\n");
      out.write("\t\tif(id!=null&&id!=\"\"){\r\n");
      out.write("\t\t\tbootbox.confirm('确定是否删除？',function(rs){\r\n");
      out.write("\t\t\t\tif(rs){\r\n");
      out.write("\t\t\t\t\tvar url = \"wx/wduser/wdusezbDelete\";\r\n");
      out.write("\t\t\t\t\t$.post(url,\"id=\"+id,function callback(data){  \r\n");
      out.write("\t\t\t\t\t\tvar resp = eval(\"(\"+data+\")\"); \r\n");
      out.write("\t\t\t\t\t\tif(resp.success){\r\n");
      out.write("\t\t\t\t\t\t\tparents.remove();\r\n");
      out.write("\t\t\t\t\t\t\tbootbox.alert(resp.msg);\r\n");
      out.write("\t\t\t\t\t\t}else{ \r\n");
      out.write("\t\t\t\t\t\t\tbootbox.alert(resp.msg);\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t  //取消操作\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}); \r\n");
      out.write("\t\t}else{\r\n");
      out.write("\t\t\tparents.remove();\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
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
