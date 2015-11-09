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
		<nav class="index navbar clearfix design_nav nav_bg" >
			<div class="design_nav1" style=""><a href="javascript:;" onClick="javascript :history.back(-1);"><i class="glyphicon glyphicon-menu-left" style="color: white"></i></a></div>
			<div class="design_nav2"  style="">建材商场</div>
		</nav>

	<!-- <header class="jc_header">
		<div class="col-sm-3 col-xs-3" onclick="my_header(this)">排序条件</div>
		<div class="col-sm-3 col-xs-3" onclick="my_header(this)">价格</div>
		<div class="col-sm-3 col-xs-3" onclick="my_header(this)">所有商家</div>
		<div class="col-sm-3 col-xs-3" onclick="my_header(this)">筛选</div>
	</header> -->
	<div class="jc_body"><div class="jc_xiala"><ul></ul></div></div>
	<div class="jc_zhezhao">	
	
			<c:forEach items="${plist}" var="val" varStatus="i">
				<section class="sp_prodoct clearfix">
					<div class="col-sm-4 col-xs-4">
						<a href="wap/goodsProduct?pkid=${val.bg_st_id}"><img class="img-responsive" src="${val.bg_st_img1}" /></a>	
					</div>
					<div class="col-sm-8 col-xs-8 sp_text my_shop_text clearfix">
					   <div  class="f_fl col-sm-8 col-xs-8">
					        <p class="sp_pro_name">${val.bg_st_summary}</p>
						    <p>￥${val.bg_st_pricezg}</P>
					   
					   </div>
						
						<div class="my_shop_care f_fl col-sm-4 col-xs-4" style=";text-align:center;">
						    <a href="wap/goodsProduct?pkid=${val.bg_st_id}" class="btn btn-primary btn-xs ">
				                                    <p>去购买	</p>			
							</a>
						</div>
							
					 </div>
					 <input type="hidden" class="pkid" value="${val.bg_st_id}">
				</section>
			</c:forEach>
		</div>	
	</div>
</body>
 <script src="${ctx}/js/wap/js/jquery-1.11.2.min.js"></script>
 <script type="text/javascript" src="${ctx}/js/wap/js/bootstrap.min.js"> </script>
 <script type="text/javascript" src="${ctx}/js/wap/js/pmain.js"> </script>
 <script type="text/javascript" >
	$(".addtocart").click(function()
		{
			var p=$(this).closest(".sp_prodoct");
			var pkid=p.find(".pkid").val();
			var pvalue=p.find(".btn_value").text();
			window.location.href="wap/mysession?pkid="+pkid+"&num="+pvalue;
		}
	);
 </script>
</html>