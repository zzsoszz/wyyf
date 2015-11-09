<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.lys.utils.pagination.PageBean"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.lys.utils.*"%>
<%@ include file="../top.jsp"%>
<%
	PageBean pages = (PageBean) request.getAttribute("shop_buldinglist");
%>
<div class="row  index_carousel">
	<div class="carousel slide" id="mycarousel" data-ride="carousel" data-interval="3000">
		<ol class="carousel-indicators">
			<li class="active" data-target="#mycarousel" data-slide-to="0"></li>
			<li data-target="#mycarousel" data-slide-to="1"></li>
			<li data-target="#mycarousel" data-slide-to="2"></li>
		</ol>
		<div class="carousel-inner">
			<c:forEach items="${ad_adlist}" var="val" varStatus="i">
				<div class="item <c:if test="${i.index == 0}" >active</c:if>">
					<img src="${val.ag_st_url}" style="width: 100%;height: 315px;" />
				</div>
			</c:forEach>
		</div>
		<a class="carousel-control left" data-target="#mycarousel"
			data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"></span>
		</a> <a class="carousel-control right" data-target="#mycarousel"
			data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right"></span> </a>
	</div>
</div>
<div class="dsigback">
	<div class="dsignbox">
		<div class="cheap_road" style="">
			<ul class="breadcrumb" style="">
				<li><a href="index">首页</a></li>
				<li><a href="index/shopspecial">购物商城</a></li>
				<li class="active"><a>品牌特卖</a>
				</li>
			</ul>
		</div>
		<!--品牌展示-->
		<div class="shop_specialbox clearfix">
			<!--商品盒子-->
			<c:forEach items="${shopList}" var="val" varStatus="i">
				<%-- <c:if test="${!empty val.ag_st_url}"> --%>
				<div class="shop_sbbox">
					<div class="shop_sbboxtop">
						<a href="index/ProductList?bb_st_id=${val.bb_st_userid}"> <img
							src="${val.ag_st_url}" width="480" height="200" alt="" title="">
						</a>
					</div>
					<div class="shop_sbbox_con clearfix">
						<c:forEach items="${val.get('products')}" var="obj" varStatus="j">
							<div class="shop_sbboxpic">
								<a href="index/shopmess?id=${obj.bg_st_id}"><img
									src="${obj.bg_st_img1}" alt="" title="" height="150"
									width="150">
								</a>
							</div>
						</c:forEach>
					</div>
				</div>
				<%-- </c:if> --%>
			</c:forEach>
		</div>
		<p class="pagination">
			<span class="total  btn btn-primary"><%=pages.getCurrPageNumber()%>/<%=pages.getTotalPages()%></span>
			<%
				if (pages.isFirst()) {
			%>

			<%
				} else {
			%>
			<a href="javascript:setPage(<%=pages.getPrevious()%>);" start="<%=pages.getPrevious()%>" class="prev  btn btn-primary">上一页</a>
			<%
				}
				for (int i = pages.getBetweenIndex().getStartIndex(); i <= pages.getBetweenIndex().getEndIndex(); i++) {
			%>
			<a <%if (pages.getCurrPageNumber() == i) {%> style="color: red;" 
				<%}%> href="javascript:setPage(<%=i%>);" class="btn btn-default" style="text-align:center;margin-right:3px;" start="<%=i%>"><%=i%></a>
			<%
				}
			%>
			<%
				if (pages.isLast()) {
			%>
			<%
				} else {
			%>
			<a href="javascript:setPage(<%=pages.getNext()%>);"
				start="<%=pages.getNext()%>" class="next  btn btn-primary">下一页</a>
			<%
				}
			%>
		</p>
	</div>
</div>
<script type="text/javascript">
function setPage(page){
	location.href="index/shopspecial?page="+page;
}
</script>
<%@ include file="../foot.jsp"%>