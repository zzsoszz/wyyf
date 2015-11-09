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
<body>
	<div class="page">
		<nav class="index navbar header_center">
		<!-- <input type="text"> -->
			<input type="text" class="form-control search_Input" placeholder="搜索商家">
			<label for="" class="control-label search_Label">取消</label>
		</nav>
		<section class="search_body clearfix">
		<div>大家都在搜</div>
		<div class="search_body_btn">瓷砖</div>
		<div class="search_body_btn">地板</div>
		<div class="search_body_btn">墙纸</div>
		<div class="search_body_btn">圣象</div>
		<div class="search_body_btn">TOTO洁具</div>
		</section>
		<section class="search_body clearfix">
		<div>搜索历史</div>
		<div class="search_body_btn2">瓷砖</div>
		<div class="search_body_btn2">地板</div>
		<div class="search_body_btn2">墙纸</div>
		<div class="search_body_btn2">圣象</div>
		<div class="search_body_btn2">TOTO洁具</div>
		</section>
	</div>	
</body>
<script src="${ctx}/js/wap/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/wap/js/bootstrap.min.js"> </script>
</html>