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
	<body  class="indexbody onein_body" style="background-color:#eee;" >
		<!--<div class="page">-->
			<%@ include file="header.jsp"%>
	
			
			<section class="jumbotron">
				<div class="carousel slide" data-ride="carousel">
					<ol class="carousel-indicators">
					<c:forEach items="${data }" var="v" varStatus="i">
						<li data-target="#myjm" data-slide-to="0" class="<c:if test="${i.index == 0}" >active</c:if>"></li>
					</c:forEach>
					
					</ol>
					<div class="carousel-inner" role="listbox">
					
					<c:forEach items="${data }" var="v" varStatus="i">
					
				
					
					<div class="item  <c:if test="${i.index == 0}" >active</c:if>">
							<img src="${v.bj_st_picurl }" alt="" width="100%">
						</div>
					
					</c:forEach>
						
						
						
					</div>
				</div>
			</section>


			<!--轮播结束-->
			
			
			
			
			<section class="sc_header clearfix">
				<div><a href="wap/cheap"><img src="${ctx}/images/wap/images/mall.png" width="56%"  alt=""></a></div>
				<div><a href="wap/specileprice"><img src="${ctx}/images/wap/images/02_hsnaghceng2.png" width="40.5%" alt=""></a><br/><a href="wap/jiancai"><img src="${ctx}/images/wap/images/02_shangcheng3.png" width="40.5%" alt=""></a></div>
			</section>
		<!-- 	<section class="shop_xsqg">
				<div> </div> 限时抢购
			</section> -->
			
		<div id="wrapContent">
				
		</div>
		<div class="loading_more" id="loading_more" ><i class="glyphicon glyphicon-menu-down"></i>查看更多</div>
			
		<div class="tankuang " ></div>
		<div class="zhezhao" ></div>
		<!--底部-->
		<%@ include file="footer.jsp"%>
		<!--</div>-->
		  <!--<div class="navbar-fixed-bottom index_bottom">-->
        		<!--<div  class="row myfooter" style="text-align: center; ">-->
        			<!--<div class="col-sm-3 col-xs-3" onclick="pageName()">-->
        				<!--<a href="index3.html">        					-->
        					<!--<div><span class="glyphicon glyphicon-home"></span></div>-->
        				    <!--<span>首页</span>-->
        				<!--</a>-->
        			<!--</div>-->
        			<!--<div class="col-sm-3 col-xs-3" onclick="pageName()">-->
        				<!--<a href="myshop.html">        					-->
        					<!--<div><span class="glyphicon glyphicon-th-large"></span></div>-->
        					<!--<span>商城</span>-->
        				<!--</a>-->
        			<!--</div>-->
        			<!--<div class="col-sm-3 col-xs-3" onclick="pageName()">-->
        				<!--<a href="mysession.html">        					-->
        					<!--<div><span class="glyphicon glyphicon-shopping-cart"></span></div> -->
        					<!--<span>购物车</span>-->
        				<!--</a>-->
        			<!--</div>-->
        			<!--<div class="col-sm-3 col-xs-3" onclick="pageName()">-->
        				<!--<a href="pmyzone.html">        					-->
        					<!--<div><span class="glyphicon glyphicon-user"></span></div>       					-->
        					<!--<span>我的</span>-->
        				<!--</a>-->
        		   <!--</div>-->
        		<!--</div>-->
        	<!--</div>-->

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
	 	function getPage(page)
	 	{
	 		$.ajax({  
                type:"POST",  
                url:"wap/myshoppage?type=3&page="+page,  
                dataType:"html",
                success:function(data){
                	$("#wrapContent").append(data);
                },
                error:function(){  
                }
            });
	 	}
	 </script>
	 
</html>
