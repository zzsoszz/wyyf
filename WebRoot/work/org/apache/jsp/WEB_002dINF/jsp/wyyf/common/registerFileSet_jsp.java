/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.64
 * Generated at: 2015-09-14 01:47:23 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp.wyyf.common;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Date;
import org.springframework.util.LinkedCaseInsensitiveMap;
import com.lys.utils.StringUtils;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public final class registerFileSet_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");

	List<Map<String,Object>> prodList=(List<Map<String,Object>>)request.getAttribute("workExps");	
	String worklname=StringUtils.toStringByObject(request.getAttribute("worklname"));
	if(prodList==null){
		prodList=new ArrayList<Map<String,Object>>();
	}

      out.write("\r\n");
      out.write("<div myattr=\"update\">\r\n");
      out.write("\t<button type=\"button\" onclick=\"addtabobj(this,'tab1_All')\" class=\"btn blue\" myattrname=\"");
      out.print(worklname );
      out.write("\">点击新增<i class=\"fa fa-arrow-down\"></i></button>\r\n");
      out.write("</div>\r\n");
      out.write("<div id=\"tab1_All\">\r\n");

	
	for(int i=0,b=prodList.size();i<b+1;i++){
		Map<String,Object> workmap=new LinkedCaseInsensitiveMap<Object>();
		if(b>0&&i<b){
			workmap=prodList.get(i);
		}
		String wymark=worklname+"["+i+"]";
		String divId="tab_"+worklname+i+new Date().getTime();
		if(i==b){
			wymark="wduserzblist";
			divId="tab1_demo";
		}
		String url=StringUtils.toStringByObject(workmap.get("ag_st_url"));
		request.setAttribute("tp_name", wymark+".ag_st_mark");//置于页面对象，用于my:select加载
		request.setAttribute("tpmrz", StringUtils.toStringByObject(workmap.get("ag_st_mark")));//置于页面对象，用于my:select加载

      out.write("\r\n");
      out.write("\t<div id=\"");
      out.print(divId );
      out.write("\" class=\"addBorder\">\r\n");
      out.write("\t\t<div class=\"row\" >\r\n");
      out.write("\t\t\t<div style=\"display:none;\">\r\n");
      out.write("\t\t\t\t<input  type=\"text\" name=\"");
      out.print(wymark );
      out.write(".ag_st_id\" markName=\"ID\" markCode=\"ag_st_id\" value=\"");
      out.print(StringUtils.toStringByObject(workmap.get("ag_st_id"))  );
      out.write("\"/>\r\n");
      out.write("\t\t\t\t<input name=\"");
      out.print(wymark );
      out.write(".ag_st_url\" value=\"");
      out.print(url  );
      out.write("\">\r\n");
      out.write("\t\t\t\t<input name=\"");
      out.print(wymark );
      out.write(".ag_st_addUserId\" value=\"");
      out.print(wymark );
      out.write(".fileobj\"/>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t");
if(StringUtils.hasText(url)){ 
      out.write("\r\n");
      out.write("\t\t\t\t<a href=\"");
      out.print(url );
      out.write("\" target=\"_blank\">下载附件</a>\r\n");
      out.write("\t\t\t");
}else{
      out.write("\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t");
} 
      out.write("\r\n");
      out.write("\t\t\t<img myid=\"imghead");
      out.print(wymark );
      out.write(".\" class=\"imgylclass\" src=''><!--无预览时的默认图像，自己弄一个-->\r\n");
      out.write("\t\t\t");
      if (_jspx_meth_my_005fselect_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t <input name=\"");
      out.print(wymark );
      out.write(".fileobj\" type=\"file\" style=\"display: inline;\"   myattr=\"update\"/>\r\n");
      out.write("\t\t\t <button class=\"btn btn-sm red\" onclick=\"deltabobj(this)\"  type=\"button\" myattr=\"update\">删除<i class=\"fa fa-trash-o\"></i></button>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
	} 
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
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
      out.write("\t\tif(myattrname==\"");
      out.print(worklname );
      out.write("\"){\r\n");
      out.write("\t\t\thtmltab=tab1_;\r\n");
      out.write("\t\t\thtmlnum=tab1_num++;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t$(\"#\"+targetid).append('<div id=\"tab_'+myattrname+new Date().getTime()+'\" class=\"addBorder\">'+htmltab.replace(/wduserzblist\\./g, myattrname+\"[\"+htmlnum+\"].\")+'</div>');\r\n");
      out.write("\t}\r\n");
      out.write("\t//移除商品当前对象\r\n");
      out.write("\tfunction deltabobj(obj){\r\n");
      out.write("\t\tvar parents=$(obj).parents(\"div.addBorder\").first();\r\n");
      out.write("\t\tvar id=parents.find(\"input[markName='ID']\").val();\r\n");
      out.write("\t\t//如果该条数据已经入库，删除时要请求 \r\n");
      out.write("\t\tif(id!=null&&id!=\"\"){\r\n");
      out.write("\t\t\talert(\"删除无效，该处只能进行查看。\");\r\n");
      out.write("\t\t}else{\r\n");
      out.write("\t\t\tparents.remove();\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
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
    // /WEB-INF/jsp/wyyf/common/registerFileSet.jsp(50,3) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_my_005fselect_005f0.setName((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${tp_name}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
    // /WEB-INF/jsp/wyyf/common/registerFileSet.jsp(50,3) name = otherAttr type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_my_005fselect_005f0.setOtherAttr(" style='width: 100px;display: inline;' ");
    // /WEB-INF/jsp/wyyf/common/registerFileSet.jsp(50,3) name = nameKey type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_my_005fselect_005f0.setNameKey((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${wjlx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
    // /WEB-INF/jsp/wyyf/common/registerFileSet.jsp(50,3) name = initSelectedKey type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_my_005fselect_005f0.setInitSelectedKey((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${tpmrz}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
    _jspx_th_my_005fselect_005f0.doTag();
    _jsp_instancemanager.destroyInstance(_jspx_th_my_005fselect_005f0);
    return false;
  }
}
