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
<body style="background-color:#F0F0F0">
	<div class="page">
		<%@ include file="top.jsp"%>

		 <div class="jumbotron cheap_prodoct_page" style="">
		            <div class="carousel slide" data-ride="carousel">
		                <ol class="carousel-indicators">
		                    <li data-target="#myjm" data-slide-to="0" class="active"></li>
		                    <li data-target="#myjm" data-slide-to="1"></li>
		                    <li data-target="#myjm" data-slide-to="2"></li>
		                    <li data-target="#myjm" data-slide-to="3"></li>
		                </ol>
		                <div class="carousel-inner" role="listbox">
		                    <div class="item active">
		                        <img src="${prodinfo.bg_st_img1}" alt="" width="100%">		
		                    </div>
		                    <div class="item">
		                        <img src="${prodinfo.bg_st_img2}" alt="" width="100%">
		                    </div>
		                    <div class="item">
		                        <img src="${prodinfo.bg_st_img3}" alt="" width="100%">
		                    </div>
		                    <div class="item">
		                        <img src="${prodinfo.bg_st_img4}" alt="" width="100%">
		                    </div>
		                </div>
		            </div>
                </div>
                <section class="ch_pr_qianggou clearfix">
                	产品名：${prodinfo.bg_st_name}<span class="goods_price">￥${prodinfo.bg_st_pricezg}</span>
                </section>
                
                <section class=" shop_xsqg ch_pr_qianggou2">
                	<div> </div> 商品详情
                </section>
                 <div class="ch_pr_btn">
                	<button id="loginBtn" target="${prodinfo.bg_st_id}" class="btn btn-success btn-lg btn-block" type="submit">加入到购物车</button>
                </div>
              
                <div class="ch_pr_qianggou3">
               		 ${prodinfo.bg_st_prodIntro}
                </div>
               
                
	</div>
</body>
<script src="${ctx}/js/wap/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/wap/js/bootstrap.min.js"> </script>
 <script type="text/javascript">
		$(function() {
			var oldRequestUrl = "${oldRequestUrl}";
			//登录按钮触发事件
			$("#loginBtn").click(function() {
				onLogin();
			});
		});
		//登录函数 
		function onLogin() {
			var url = "wap/addCar";
		    var id=$("#loginBtn").attr("target");
		    console.debug(id);
			$.post(url,"pkid="+id,function callback(data) {
			if(data.success=="true"){
				alert("加入购物车成功！");
				
			}else if(data.success="false"){
				alert("请先登录");
				location.href = "${pageContext.request.contextPath}/wap/pmyzone?r="	+ new Date().getTime();
				
			}else{
				alert("加入购物车失败！");
			}},"json");
		}
		</script>
</html>