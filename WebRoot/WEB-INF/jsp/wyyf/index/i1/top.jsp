<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<!DOCTYPE html >
<html>
	<head>
		<base href="http://${header['host']}${pageContext.request.contextPath}/">
		<meta charset="utf-8">
		<!--<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>-->
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>网众科技，成都团购验房网，团房，团装修，团建材，家具一站式服务平台，成都免费验房</title>
		<meta name="keywords" content=""/>
		<meta name="Description" content=""/>
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="css/main.css"/>
		  <script type="text/javascript" src="js/j1/require/require1.js"></script>
		<!--[if lt IE 9]>
		
		    <script type="text/javascript" src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/main.js"></script>
		
		<script type="text/javascript">
$(function(){
    //推荐工长
	$("#temp4").Slide({
		effect : "scroolLoop",
		autoPlay:false,
    	speed : "normal",
		timer : 3000,
		steps:2
	});
	//装修贷
    $("#temp6").Slide({
		effect : "scroolY",
    	speed : "normal",
		timer : 3000
	});
	//推荐师傅
	$("#temp8").Slide({
		effect : "scroolLoop",
		autoPlay:false,
    	speed : "normal",
		timer : 3000,
		steps:2
	});
	
	
});
</script>
</head>
<body>
<!--top-->
<header>
     <div class="bg_headerinfo">
    <div class="header_info">
    	
	    <div class="jsUsercenter clearfix" >
	    	
	    	 <ul class="header_info_nav">
	    	 	<li><a href="index"><span class="redcolor">网众科技首页</span></a></li>
	    	 	<li>您好，<a href="index/loginp">请登录</a></li>
	    	 	<li><a href="index/intoregtie">免费注册</a></li>
	    	 	<li>消息(<span class="redcolor"></span>)</li>
	    	 	<li id="sub1" ><i class="glyphicon glyphicon-shopping-cart redcolor"></i><a href="mysession.html">我的购物车</a>(<span class="redcolor"></span>)
	    	 	      <div class="submenu submenu1" id="sub11">
	    	 	      	      最近加入的宝贝：
                               <ul>
                                   <li>
                                   	   <div class="subleft"><img src="images/c_23.png" class="img-responsive"/></div>
                                       <div class="subcon">
                                         	<span>美国代购美国代购美国代购美国代购</span>
                                        	<span class="gray">美国代购美国代购美国代购美国代购</span>
                                       </div>
                                       <div class="subbott">
                                        	<span>￥<strong class="redcolor">590.00</strong></span>
                                        	<span>删除</span>
                                       </div>
                                   </li>
                                   <li>
                                   	   <div class="subleft"><img src="images/c_23.png" class="img-responsive"/></div>
                                       <div class="subcon">
                                         	<span>美国代购美国代购美国代购美国代购</span>
                                        	<span class="gray">美国代购美国代购美国代购美国代购</span>
                                       </div>
                                       <div class="subbott">
                                        	<span>￥<strong class="redcolor">590.00</strong></span>
                                        	<span>删除</span>
                                       </div>
                                   </li>
                                   <li>
                                   	   <div class="subleft"><img src="images/c_23.png" class="img-responsive"/></div>
                                       <div class="subcon">
                                         	<span>美国代购美国代购美国代购美国代购</span>
                                        	<span class="gray">美国代购美国代购美国代购美国代购</span>
                                       </div>
                                       <div class="subbott">
                                        	<span>￥<strong class="redcolor">590.00</strong></span>
                                        	<span>删除</span>
                                       </div>
                                   </li>
                                   <li>
                                   	    <a href="mysession.html"><div class="check_mysession">查看我的购物车</div></a>
                                   </li>
                               </ul>
                </div>

	    	 	</li>
	    	 	<li><a href="index/me">我的个人中心</a></li>
	    	 </ul>	    
	     </div>
	    
	      
     </div>
  </div>
<!--logo-->
    <div class="bg_header">
      <div class="header">
        	<div class="logo"><img src="images/logo1.jpg" height="100"/></div>
       	    <div class="number"><img src="images/ewe_06.png"/></div>
	        <div class="search">
	        	<input type="text"><a href="shoplist.html"><span class="glyphicon glyphicon-search"></span></a>
	   	        <!-- <div class="header_bott" style="">
	   	   	      <ul>
	   	   	     	<li><a href="">空调</a></li>
	   	   	    	<li><a href="">空调</a></li>
	   	    	   	<li><a href="">空调</a></li>
	   	    	   	<li><a href="">空调</a></li>
	   	   	    	<li><a href="">空调</a></li>
	   	   	    	<li><a href="">空调</a></li>
	   	    	   	<li><a href="">空调</a></li>
	   	    	   	<li><a href="">空调</a></li>
	   	    	   	<li><a href="">空调</a></li>
	   	   	     </ul>
	   	      </div> -->
	      </div>
    
      
      </div>
  </div>	
</header>


<div class="mainbutton_bg">
   <nav>
  	    <ul class="nav-main">
            <li><a href="index.html">网众首页</a></li>
            <li id="li-1">我要验房<span></span></li>
            <li id="li-2">我要设计<span></span></li>
            <li id="li-4">我要抢单<span></span></li>
            <!--<li><a href="actbuy.html">我要团购</a></li>-->
            <li id="li-5">家居商城<span></span></li>
            <li ><a href="index/supervision">我要监督</a></li>
            <li id="li-3">我要监测<span></span></li>
        </ul>
        <!--隐藏盒子-->
        <div id="box-1" class="hidden-box hidden-loc-index">
            <ul>
                <li><a href="index/checkfree">免费验房</a></li>
                <li><a href="index/checkgold">金牌验房</a></li>
            </ul>
        </div>
        <div id="box-2" class="hidden-box hidden-loc-us">
            <ul> 
                <li><a href="index/toDesign">平民设计</a></li>
                <li><a href="index/designstyle?type=1">个性设计</a></li>

                <li><a href="index/designstyle?type=2">金牌设计</a></li>
            </ul>
        </div>
        <div id="box-3" class="hidden-box hidden-loc-info">
            <ul>
                <li><a href="index/monitorfree">免费监测</a></li>
                <li><a href="index/monitortoll">收费治理</a></li>
            </ul>
        </div>
        <div id="box-4" class="hidden-box hidden-loc-find">
            <ul>
                <li><a href="index/overman">找工长</a></li>
                <li><a href="index/master">找师傅</a></li>
            </ul>
        </div>
        <div id="box-5" class="hidden-box hidden-loc-shop">
            <ul>
            	<li><a href="index/voucher">团代金券</a></li>
                <li><a href="index/shopcheap">抢相因</a></li>
                <li><a href="index/shopspecial">品牌特卖</a></li>
                <li><a href="index/shopbulding">建材商城</a></li>
                <li><a href="index/home">家居商城</a></li>
            </ul>
        </div>
    </nav>
</div>
 