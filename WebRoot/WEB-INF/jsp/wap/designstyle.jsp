<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@page import="com.power.utils.PowerStatic"%>
<%@page import="com.lys.utils.StringUtils"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.lys.utils.pagination.PageBean"%>

<%

String type=(String)request.getAttribute("type");
PageBean pages=(PageBean)request.getAttribute("pageBeanObj");
List<Map<String, Object>> dataList=pages.getList();
for(int j=0,b=dataList.size();j<b;j++)
{
			   Map<String, Object> map= dataList.get(j);//行数据
%>
	<li class="design_people clearfix" >
		  	<a href="wap/designermess?sjs=1&fl=<%=type%>&id=<%=StringUtils.toStringByObject(map.get("ae_st_id")) %>">
		  		<div class="design_pleft"  style="">
		  	 	  <div class="design_pic" style="">
		  	 	  	<img src="<%=StringUtils.toStringByObject(map.get("ag_st_url")) %>" class="img-responsive" />
		  	 	  </div>
		  	 	  <span><%=StringUtils.toStringByObject(map.get("ae_st_name")) %></span>
		  	   </div>
	 	  	 <div class="design_pright clearfix" style="">
	 	  	 	 <ul class="star_px clearfix">
	 	  	 	 	<%
					 	    int num=Integer.parseInt(StringUtils.toStringByObject(map.get("ba_st_grade")));
					 		for(int i=0;i<num; i++) 
					 		{
					 		%>
					 			<li><img src='${ctx}/images/wap/images/star1.png'  class='img-responsive'></li>
							<%
							}
					%>
	 	  	 	 </ul>
	 	  	 	 <p class="margin_bott">技能：<%=StringUtils.toStringByObject(map.get("ae_st_intro")) %> </p>
	 	  	 	 <span>订单数：123 </span><span >团队人数：99</span>
	 	  	 </div>
		  	 </a> 
	</li>
<%
}
%>
