<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="jsplib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'success.jsp' starting page</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0" />
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
code   4 表示预约免费设计

 -->
  </head>

<body>
	${msg}
	<c:if test="${code ==2||code==4 }">
	<a href="wap">首页</a>
	</c:if>
	<c:if test="${code ==3 || code==5 ||code == 8 || code==6||code==7||code==10||code==13}">
		<c:redirect	url="${pageContext.request.contextPath}/wap/payfororderid?body=apply&pkid=${orderNo }" />
	</c:if>
	


</body>
</html>
