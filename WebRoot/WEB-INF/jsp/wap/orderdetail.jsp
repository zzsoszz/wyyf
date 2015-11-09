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
	<nav class="index navbar clearfix design_nav nav_bg">
		<div class="mess_nav1" align="center"><a href="wap/pmyzone" ><i class="glyphicon glyphicon-menu-left"style="color: white;"></i></a></div>
		<div class="design_nav2"  style="">订单详情</div>
		<!-- <div class="mess_nav1 index_share" onclick="tkopeng(this,121)"><i class="iconfont icon-fenxiang"></i></div> -->
	</nav>
	
	<form action="wap/payfor"   method="post" >
	
				<div class="listmess_box" style="width: 100%;">
						 <div class="listmess_box1"style="">
						 		<!-- 
				  	    		<p>订单编号:<span>${orderid}</span></p>
				  	    		<p>下单时间:<span>20150705 15:13:11</span></p>
				  	    		<p>订单状态:<span>未支付</span></p>
				  	    		-->
				        </div>
				        <div class="listmess_box2" style="">
				        	 <div class="listmess_box21" style="">商品信息</div>
				        	 <!--盒子开始-->
				        	 <c:forEach items="${list}" var="val" varStatus="i" >
				        	 	     <div class="porder_conbott clearfix">
				        	  	    	<div class="porder_conbleft">
				        	  	    		<img width="30"  height="30"  src="${val.bg_st_img1}" class="img-responsive"/>
				        	  	    	</div>
				        	  	    	<div class="porder_conbright"style="font-size: 0.7em;">
				        	  	    		<p style="height: 40px">${val.bg_st_summary}</p>
				        	  	    		<p class="clearfix gary">数量:${val.bi_st_spsl} <span class="recolor" style="float: right;">￥${val.bi_st_spsl*val.bi_st_spprice}</span></p>
				        	  	    	</div>
			        	  	         </div>
				        	 </c:forEach>
				        	 <!--盒子结束-->
			             </div>
			             
			             
			             <!-- 
			             <div class="listmess_box2" >
				        	  <div class="listmess_box21" >收货信息</div>
				        	  <div class="listmess_box1">
					  	    		<p>收货人:<span><input  name="bh_st_shname" class="plistmess_input plistmess_input1" type="text" ></span></p>
					  	    		<p>手机号码:<span><input name="bh_st_shphone"  class="plistmess_input" type="text" ></span></p>
					  	    		<p>收货地址:<span><input name="bh_st_shaddress"  class="plistmess_input" type="text" ></span></p>
				              </div>
		                 </div>
		                  <div class="listmess_box2" >
		                  	  
				        	  <div class="listmess_box21 clearfix" >应付金额
				        	  <span class="redcolor" style="float: right;">
				        	  ￥${carttotalprice}
				        	  </span>
				        	  <input type="hidden" name="bh_st_spprice" value="${carttotalprice}">
				        	  </div>
				        	   
				          </div>
				           -->
				           
				</div>
				
	</form>


	<div class="tankuang " ></div>
	<div class="zhezhao" ></div>
		
	</body>
	 <script src="${ctx}/js/wap/js/jquery-1.11.2.min.js"></script>
	 <script type="text/javascript" src="${ctx}/js/wap/js/bootstrap.min.js"> </script>
	<script type="text/javascript" src="${ctx}/js/wap/js/pmain.js"> </script>
</html>
