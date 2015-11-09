<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="top.jsp"%>
<% 
String success = (String)request.getAttribute("success");
%>
<%if("success".equals(success)){ %>
<div>支付成功</div>
<%}else{%>
<div>支付失败，请重新支付</div>
<%}%>
<jsp:include page="foot.jsp" />