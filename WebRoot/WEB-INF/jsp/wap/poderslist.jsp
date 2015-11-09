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
	<body class="backcolor" >
		<nav class="index clearfix mess_nav" >
			<div class="mess_nav1" ><a href="wap/pmyzone" ><img src="${ctx}/images/wap/images/back.png" width="25"></a></div>
			<div class="mess_nav2" >我的订单</div>
		</nav>
        <div class="porderlist_box" >
        	  <ul>
        	  	<!--盒子开始-->
        	  	<li >
        	  		<div class="porder_contop" >下单时间: <time>2015-7-8</time></div>
        	  	    <a href="wap/plistmess">
        	  	    	<div class="porder_conbott clearfix">
	        	  	    	<div class="porder_conbleft">
	        	  	    		<img src="${ctx}/images/wap/images/02_00商城2.png" class="img-responsive"/>
	        	  	    	</div>
	        	  	    	<div class="porder_conbright">
	        	  	    		<p>订单编号:<span>1232132</span></p>
	        	  	    		<p>订单金额:<span>￥223.00</span></p>
	        	  	    		<p>订单状态:<span>已完成</span></p>
	        	  	    	</div>
        	  	        </div>
        	  	    </a>
        	  	</li>
        	  	
        	  	<!--盒子结束-->
        	  </ul>  
        </div>
 
		
		
		
	</body>
	 <script src="${ctx}/js/wap/js/jquery-1.11.2.min.js"></script>
	 <script type="text/javascript" src="${ctx}/js/wap/js/bootstrap.min.js"> </script>
</html>
