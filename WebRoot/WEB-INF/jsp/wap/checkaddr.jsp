<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="jsplib.jsp"%>
<!DOCTYPE html>
<html  lang="zh-CN">

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
		<title></title>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/wap/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/wap/css/main.css"/>
	</head>
	<body class="indexbody" >
		<nav class="index  clearfix" >
			<div class="design_nav1"><a href="wap/index" style="color: white;"><img src="${ctx}/images/wap/images/back.png" width="25"></a></div>
			<div class="design_nav2" >城市选择</div>
		</nav>
        <div  class="checkadd_country" align="center">成都</div>
			   	  <div class="checkadd_choose">
			   	     	 <dl>
			   	     	 	<dt >已开通城市列表</dt>
			   	     	 	<dd onclick="choose_addr(this)">简阳</dd>
			   	     	 	<dd onclick="choose_addr(this)">成都</dd>
			   	     	 	<dd onclick="choose_addr(this)">德阳</dd>
			   	     	 	<dd onclick="choose_addr(this)">甘肃</dd>
			   	     	 </dl>
		         </div>
		</div>
		
		
	</body>
	 <script src="${ctx}/js/wap/js/jquery-1.11.2.min.js"></script>
	 <script type="text/javascript" src="${ctx}/js/wap/js/bootstrap.min.js"> </script>
	 <script src="${ctx}/js/wap/js/pmain.js">
	 </script>
</html>
