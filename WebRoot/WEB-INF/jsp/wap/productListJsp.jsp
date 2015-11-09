<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="jsplib.jsp"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
		<title></title>
		 <link rel="stylesheet" type="text/css" href="${ctx}/css/wap/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/wap/css/main.css"/> 
<script src="${ctx}/js/wap/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/wap/js/bootstrap.min.js"> </script>
<script src="${ctx}/js/wap/js/pmain.js"></script>
<script type="text/javascript" src="${ctx}/js/wap/js/cav.js"></script> 


</head>
<body>
	<!--<div class="page">-->
		<nav class="index clearfix mess_nav nav_bg" >
			<div class="mess_nav1" ><a href="javascript:;" onClick="javascript :history.back(-1);"><i class="glyphicon glyphicon-menu-left"style="color: white"></i></a></div>
			<div class="mess_nav2" >${data }</div>
		</nav>
	<div id="design_specile" lang="0" class="0" >
		<!-- 
				<section class="sp_prodoct clearfix">
				<div class="col-sm-4 col-xs-4"><img class="img-responsive" src="images/temai.jpg" /></div>
				<div class="col-sm-8 col-xs-8 sp_text clearfix">
					<p>立邦漆 立邦墙面卫生净味全能内墙漆底漆 15L</p>
					<p>￥223.00</P>
						<div class="btn-group btn-group-xs" role="group">
							<button class="btn sp_button" onclick="sp_add(this)">+</button>
							<button class="btn sp_button">1</button>
							<button class="btn sp_button" onclick="sp_reduce(this)">-</button>
							<a href="javascript:void(0)" class="tosession"><i class="glyphicon glyphicon-shopping-cart green"></i></a>
						</div>
					</div>
				</section> -->


	</div>
	<div class="loading_more"     id="loading_more" lang="0" >
		&nbsp;&nbsp;<i class="glyphicon glyphicon-arrow-down" id="idvalue" lang="${id}"></i>下一页
	</div>
		    <div class="tankuang"></div>
		    <div class="zhezhao"></div>
	<footer style="width: 100%;">
		<div class="pindex_footer">
			<ul>
				<li><a href="index.html">首页</a></li>
				<li><a href="mysession.html">购物车</a></li>
				<li><a href="plogin.html">登录</a></li>
				<li><a href="psign.html">注册</a></li>
			</ul>
		</div>
		<div class="pindex_bott" >
			Copyrighgt©2015 蜀ICP备15018106号-1 成都网众天弘科技有限公司
		</div>
	</footer>
	<!--</div>-->
</body>
 <script src="${ctx}/js/wap/js/jquery-1.11.2.min.js"></script>
	 <script type="text/javascript" src="${ctx}/js/wap/js/bootstrap.min.js"> </script>
	 <script src="${ctx}/js/wap/js/pmain.js"></script>
	 <script>
	 
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
                url:"wap/getProductList?id="+$.getUrlParam('id')+"&pageIndex="+page,  
                dataType:"html",
                success:function(data){
					
                	
		            	$("#loading_more").attr('style', data.currPageNumber);
						$("#loading_more").attr('lang', data.totalPages);

						
						if (data.trim()=="") {
							$("#loading_more").remove();
						}
						$("#design_specile").append(data);
						//alert($('div').html(data));
					},
					error : function() {
					}
				});
			}
		</script>
</html>