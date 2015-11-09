<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="jsplib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'success.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<!-- 

code   2 表示免费验房
code   3 表示金牌验房  id  预约需要支付费用的预约表id
 
code   5 表示预约个性设计师

 -->
  </head>
  
  <body>
   	操作成功!${msg}
   	<c:if test="${code ==3 }">
   	<%-- <a href="wap/checkOrder?id="+${id}+"">去支付</a> --%>
   		<a href="wap/checkOrder?id=${id}">去支付</a>
   	</c:if>
   		<c:if test="${code ==5 }">
   	<%-- <a href="wap/checkOrder?id="+${id}+"">去支付</a> --%>
   		<a href="wap/checkOrder?id=${id}">去支付</a>
   	</c:if>
   	
   	
  </body>
</html>
