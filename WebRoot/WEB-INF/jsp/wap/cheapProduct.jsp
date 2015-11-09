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
		<nav class="index navbar clearfix design_nav">
			<div class="design_nav1" style=""><a href="wap/cheap" style="color: white;"><img src="${ctx}/images/wap/images/back.png"width="25"></a></div>
			<div class="design_nav2"  style="">抢相因</div>
			<div class="mess_nav1 index_share" onclick="tkopeng(this,121)"><i class="iconfont icon-fenxiang"></i></div>
		</nav>
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
		                        <img src="${ctx}/images/wap/images/qiangxiangyin.jpg" alt="" width="100%">		
		                    </div>
		                    <div class="item">
		                        <img src="${ctx}/images/wap/images/qiangxiangyin.jpg" alt="" width="100%">
		                    </div>
		                    <div class="item">
		                        <img src="${ctx}/images/wap/images/qiangxiangyin.jpg" alt="" width="100%">
		                    </div>
		                    <div class="item">
		                        <img src="${ctx}/images/wap/images/qiangxiangyin.jpg" alt="" width="100%">
		                    </div>
		                </div>
		            </div>
                </div>
                <section class="ch_pr_qianggou">
                	限时抢购 <span class="label label-danger">06:30:20</span>
                </section>
                <section class=" shop_xsqg ch_pr_qianggou2">
                	<div> </div> 订单详情
                </section>
                <p class="ch_pr_qianggou3">
                	灯影牛肉，是四川省达州市的汉族传统名食。牛肉片薄如纸，色红亮，味麻辣鲜脆，细嚼之，回味无穷。灯影，即皮影戏，用灯光把兽皮或纸板做成的人 物剪影投射到幕布上。用“灯影”来称这种牛肉，足见其肉片之薄，薄到在灯光下可透出物象，如同皮影戏中的幕布.
                </p>
                <div class="ch_pr_btn">
                	<button class="btn btn-info btn-lg btn-block" type="submit">立即购买</button>
                </div>
                
	</div>
	<div class="tankuang " ></div>
	<div class="zhezhao" ></div>
</body>
<script src="${ctx}/js/wap/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/wap/js/bootstrap.min.js"> </script>
<script type="text/javascript" src="${ctx}/js/wap/js/pmain.js"></script>
</html>