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
	<body style="background-color: #EDEDED;">
		<nav class="index clearfix mess_nav nav_bg" >
			<div class="mess_nav1" ><a href="javascript:;" onClick="javascript :history.back(-1);"><i class="glyphicon glyphicon-menu-left" style="color: white"></i></a></div>
			<div class="mess_nav2" >订单列表</div>
			<!-- <div class="mess_nav1 index_share" onclick="tkopeng(this,121)"><i class="iconfont icon-fenxiang"></i></div> -->
		</nav>
		<div class="yanfang_nav" style="width: 100%;height: 50px;background-color: #E8E8E8;">
			<div class=""  >
				<ul  id="mytabs" class="nav nav-tabs yf_bd" style="width: 100%;">
					<li class="nav_one nav_two1 active" style="width: 33%;float: left;" ><a href="#wodedingdan"data-toggle="tab">待支付</a></li>
					<li class="nav_one nav_two2"  style="width: 33%;float: left;"><a href="#fukuanjilu"data-toggle="tab">已支付</a></li>
					<li class="nav_one nav_two2"  style="width: 33%;float: left;"><a href="#payback"data-toggle="tab">我的退款</a></li>
				</ul>
			</div>
		</div>
		<div  class=" tab-content" >
			<div class="tab-pane fade in active" id="wodedingdan" style="background-color: white"   data-status="1">
				<div class="payment_box" >
					
				</div>
				
				 <div class="loading_more"  id="loading_more" >
                  	   <i class="glyphicon glyphicon-menu-down"></i>
                  	       查看更多
                  </div>
			</div>
			
			<div class="tab-pane fade " id="fukuanjilu"   data-status="7">
				<div class="payment_box" >
					
				</div>
				 <div class="loading_more"  id="loading_more" >
                  	   <i class="glyphicon glyphicon-menu-down"></i>
                  	       查看更多
                  </div>
			</div>

			<div class="tab-pane fade " id="payback"  data-status="8,5,6">
				<div class="payment_box" >
					
				</div>
				 <div class="loading_more"  id="loading_more" >
                  	   <i class="glyphicon glyphicon-menu-down"></i>
                  	       查看更多
                  </div>
			</div>
			
		</div>





		<div class="tankuang " ></div>
		<div class="zhezhao" ></div>
		
	</body>
	 <script src="${ctx}/js/wap/js/jquery-1.11.2.min.js"></script>
	 <script type="text/javascript" src="${ctx}/js/wap/js/bootstrap.min.js"> </script>
	<script type="text/javascript" src="${ctx}/js/wap/js/pmain.js"></script>
	<script type="text/javascript" src="${ctx}/js/wap/js/cav.js"></script>
	
	
	 <script>
	 	$(document).ready(
	 		function()
	 		{
	 			//取出第一页
	 			$(".tab-pane").each(function(){
	 				new TabPanel($(this));
	 		    });
	 		}
		);
	 	
	 	
	 	function TabPanel(tab_pane)
	 	{
	 		var self=this;
	 		var status=tab_pane.data("status");
	 		this.getPage= function(page)
		 	{
		 		$.ajax({
	                type:"POST",  
	                url:"wap/paymentpage?status="+status+"&page="+page,  
	                dataType:"html",
	                success:function(data){
	                		tab_pane.find(".payment_box").append(data);
	                },
	                error:function(){  
	                }
	            });
		 	}
	 		this.init=function(){
		 			var status=tab_pane.data("status");
		 			self.getPage(1);
		 			tab_pane.data("page",1);
		 			tab_pane.find(".loading_more").click(
		 				function()
		 				{
		 					var curpage=tab_pane.data("page");
		 					self.getPage(++curpage);
		 					tab_pane.data("page",curpage);
		 				}
	 			    );
	 		}
	 		this.init();
	 	}
	 	
	 </script>
	
	
</html>