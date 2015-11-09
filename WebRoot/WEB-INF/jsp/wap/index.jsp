<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="jsplib.jsp"%>
<%@page import="com.power.utils.PowerStatic"%>
<%@page import="com.lys.utils.StringUtils"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%> 
<%@page import="com.lys.utils.pagination.PageBean"%>
<script src="${ctx}/js/wap/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/wap/js/bootstrap.min.js"></script>
<script src="${ctx}/js/wap/js/pmain.js"></script>
<!DOCTYPE html>
<html lang="zh-CN">

<meta charset="utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<title></title>
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/wap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/wap/css/main.css">

<head>
<meta property="wb:webmaster" content="4b951ff616287774" />
</head>
<body>

	<%@ include file="header.jsp"%>

	<!--轮播开始-->
	<section class="jumbotron">
		<div class="carousel slide" data-ride="carousel">
			<ol class="carousel-indicators">
				<c:forEach items="${ad}" var="val" varStatus="i">
					<li <c:if test="${i.index == 0}" >class="active"</c:if>
						<c:if test="${i.index != 0}" >class=""</c:if>
						data-target="#mycarousel2" data-slide-to="0"></li>
				</c:forEach>
			</ol>
			<div class="carousel-inner" role="listbox">
				<c:forEach items="${ad}" var="val" varStatus="i">


					<div class="item <c:if test="${i.index == 0}" >active</c:if>">
						<c:if test="${not empty val.bj_st_clickurl }">
							<a href="${val.bj_st_clickurl }"> <img
								src="${val.bj_st_picurl}" alt="" title="" height="370"
								width="640">
							</a> 
						</c:if>
						<c:if test="${ empty val.bj_st_clickurl }">
							<img src="${val.bj_st_picurl}" alt="" title="" height="370"
								width="640">
						</c:if>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>
	<!--轮播结束-->
	<div class=""
		style="width: 94%; margin: 0 auto; height: 30px; border-left: 3px solid #39424A;; margin-top: 15px; font-weight: bold; padding-left: 10px;; line-height: 30px;">品牌商家</div>
	<section class="pindex_con" style="">
		<ul id="shopList">
		
		
		
		</ul>
	</section>
	<style>
	.dsignmore{width: 100px;height: 50px;line-height: 50px;background-color: #F7F7F7;color: #868686;cursor: pointer;margin: 0 auto;}
	
	</style>
	<div class="dsignmore" style="0"  onclick="getShopList()" id="next" lang="0" >
		&nbsp;&nbsp;&nbsp;&nbsp;<i class="glyphicon glyphicon-arrow-down"></i>下一页
	</div>
	<div class="tankuang "></div>
	<div class="zhezhao"></div>

	<!--底部-->
	<%@ include file="footer.jsp"%>
</body>



<script type="text/javascript">
$(window).load(function () {
	getShopList();
	});
	function getShopList() {//退款
		var p=	parseInt($("#next").attr("style"))+1;
		
		$.post("wap/getShopList", {
			"pageIndex" : p
		}, function(data) {
			
			var lengthNum=data.list.length;//lang
			$("#next").attr('style',data.currPageNumber);
			console.debug(1);
			for(i=0 ; i<lengthNum;i++){
				var line="<li> <a href=\"wap/productListJsp?id="+data.list[i].bb_st_userid+"\"><img  src="+data.list[i].ag_st_url+" class=\"img-responsive\" ></a></li>";
				$("#shopList").append(line);
				
			}

		}, "json");
	}
</script>

</html>
