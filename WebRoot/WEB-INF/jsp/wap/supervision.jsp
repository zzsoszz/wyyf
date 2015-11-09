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
		
		<%@ include file="header.jsp"%>
	
        <div class="yanfang_nav" >    	 
        	 <div class=" col-xs-12 " >
        	 	 <ul   class="nav nav-tabs design_check yf_bd">
		        	 	<!-- <li class="nav_one nav_two3 active "  style="width: 50%;margin: 0px;padding: 0px;"><a href="#super_free"data-toggle="tab">免费监理</a></li> -->
		        	 	<li class="nav_one nav_two4 active"  style="width: 100%;margin: 0px;padding: 0px;"><a href="#super_gold"data-toggle="tab">收费监理</a></li>
        	    </ul>
        	 </div>
        </div>
        <div  class="myservice tab-content" >
                   <!--      <div class="tab-pane active" id="super_free">  
                             <div class="yanfang_navcon" style="min-height: 520px;">
				        	 	<div class="yanfang_nctop design_nomol super_visoncon"  style="padding: 40px 10px;">
				        	 		  <span>XXX业主您好！
				        	 		    <p style="text-indent: 24px;">系统检测到您已经在网众平台申请装修服务，我们将免费为您提供监督服务，
				        	 		    	谢谢您对网众的认可，请预约免费监理，我们将安排专业人员全程监督您的房屋装修过程</p>
				        	 		  </span>
				        	 	</div>
				        	 	   <button class="btn btn-block mybuttom" style="">预约</button>
				           </div>        
                        </div> -->
                    <!--个性设计-->    
                        <div class="tab-pane  active" id="super_gold" style="min-height: 520px;">
       						<div  class="yanfang_navcon">
					        	 	<div class="yanfang_nctop" >
				                       <ul class="design_specile" id="design_specile">

				                       </ul>
					        	 	  
					        	 	   <div class="loading_more" id="loading_more" ><i class="glyphicon glyphicon-menu-down"></i>查看更多</div>
					
				<!-- 	<style>
	.dsignmore{width: 100px;height: 50px;line-height: 50px;background-color: #F7F7F7;color: #868686;cursor: pointer;margin: 0 auto;}
	
	</style>
					<div class="dsignmore" style="" onclick="getPage(1)" id="loading_more"
					
						lang="31">
						&nbsp;&nbsp;&nbsp;&nbsp;<i class="glyphicon glyphicon-arrow-down"></i>下一页
					</div> -->
				</div>
				
					        </div>
                        </div>
              
                       
        </div>
		<div class="tankuang " ></div>
		<div class="zhezhao" ></div>
	<!--底部-->
	<%@ include file="footer.jsp"%>
	
	</body>
	 <script src="${ctx}/js/wap/js/jquery-1.11.2.min.js"></script>
	 <script type="text/javascript" src="${ctx}/js/wap/js/bootstrap.min.js"> </script>
	 <script src="${ctx}/js/wap/js/pmain.js"></script>
	 
	  <script>
	 	$(document).ready(
	 		function()
	 		{
	 			//取出第一页
	 			getPage(1);
	 			$("#loading_more").data("page",1);
	 			$("#loading_more").click(
	 				function()
	 				{
	 					var curpage=$("#loading_more").data("page");
	 					getPage(++curpage);
	 					$("#loading_more").data("page",curpage);
	 				}
	 			);
	 		}
		);
	 	(function($){
	 		$.getUrlParam = function(name)
	 		{
	 		var reg
	 		 = new RegExp("(^|&)"+
	 		 name +"=([^&]*)(&|$)");
	 		var r
	 		 = window.location.search.substr(1).match(reg);
	 		if (r!=null) return unescape(r[2]); return null;
	 		}
	 		})(jQuery);
	 	function getPage(page)
	 	{
	 		$.ajax({  
                type:"POST",  
                url:"wap/supervisionpage?type=4&sqlx="+$.getUrlParam('sqlx')+"&subtype="+$.getUrlParam('fl')+"&page="+page,  
                dataType:"html",
                success:function(data){
                	$("#design_specile").append(data);
                },
                error:function(){  
                                  }
            });
	 	}
	 </script>
	 
</html>
