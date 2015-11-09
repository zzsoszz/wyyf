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
	<input type="hidden" id="type" value="${type}" /> <input type="hidden"
		id="page" value="${page}" /> <input type="hidden" id="OrderByType"
		value="${OrderByType}" /> <input type="hidden" id="Up" value="${Up}" />
	<input type="hidden" id="minPrice" value="${minPrice}" /> <input
		type="hidden" id="maxPrice" value="${maxPrice}" /> 
		<input type="hidden" id="shoptype" value="${shoptype}" />
		<input type="hidden" id="IsPromotion" value="${IsPromotion}" />
		<input type="hidden" id="keywords" value="${keywords}" />
		<input type="hidden" id="bb_st_id" value="${bb_st_id}" />
	<div class="carousel slide" id="mycarousel" data-interval="3000">
		<ol class="carousel-indicators">
			<li class="active" data-target="#mycarousel" data-slide-to="0"></li>
			<li data-target="#mycarousel" data-slide-to="1"></li>
			<li data-target="#mycarousel" data-slide-to="2"></li>
		</ol>
		<div class="carousel-inner">
			<c:forEach items="${ad_adlist}" var="val" varStatus="i">
				<div class="item <c:if test="${i.index == 0}">active</c:if>">
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
		<!--头部路线-->
		<div class="cheap_road" style="">
			<ul class="breadcrumb" style="">
				<li><a href="index">首页</a></li>
			
			</ul>
		</div>
		<div class="dsignboxin clearfix" style="">
          	<div>品牌</div>
          	 <ul>
          	 	<c:forEach items="${shopList}" var="val" varStatus="i">
					<li><a href="javascript:setBb_st_id('${val.bb_st_userid}');">${val.ae_st_name}</a></li>
				</c:forEach>
          	 </ul>
          </div>
          <div class="dsignboxin clearfix shoplist_nav2" style="">
	          	 <ul>
						<li onclick="zonghe(${Up})" ${OrderByType==0? 'class="checkli"':''}>综合<span
							class="glyphicon ${OrderByType==0&&Up==0?'glyphicon-arrow-down':'glyphicon-arrow-up'}"></span>
						</li>
						<li onclick="xiaoliang(${Up})" ${OrderByType==2? 'class="checkli"':''}>销量<span
							class="glyphicon ${OrderByType==2&&Up==0?'glyphicon-arrow-down':'glyphicon-arrow-up'}"></span>
						</li>
						<li onclick="jiage(${Up})" ${OrderByType==1? 'class="checkli"':''}>价格<span
							class="glyphicon ${OrderByType==1&&Up==0?'glyphicon-arrow-down':'glyphicon-arrow-up'}"></span>
						</li>
					</ul>
	          	 <span class="shoplist_span" style="">
	          	 <input id="minprice" value="${minPrice}" class="shop_brinput" type="text"/>-<input value="${maxPrice}" id="maxprice" class="shop_brinput" type="text"/></span>
	          	 <a class="shoplist_a" href="javascript:setPrice()">确定</a>
	          	 <c:choose>
					<c:when test="${IsPromotion}">
				<span class="shoplist_span3" style=""><input id="IsPromotion" type="checkbox" checked  onChange="ChangePromotion(this);"/>促销</span>
	          	 
				</c:when>
				<c:otherwise>
				<span class="shoplist_span3" style=""><input id="IsPromotion" type="checkbox"  onChange="ChangePromotion(this);"/>促销</span>
	          	 
				</c:otherwise>
				</c:choose>
	          	 <div style="">共${ProductTotal }件商品</div>
          </div>
		<div class="shop_brcon clearfix shoplist_box">
			<c:forEach items="${shop_buldinglist.getList()}" var="val" varStatus="i">
				<a href="index/shopmess?id=${val.bg_st_id}">
					<div class="shop_brconbox">
						<div class="shop_brbt">
							<img src="${val.bg_st_img1 }" alt="" title="" height="200" width="250"/>
						</div>
						<div class="shop_brbcon">
							<div>${val.bg_st_name }</div>
							<span class="redcolor shop_brconnum">RMB：<span class="" style="">${val.bg_st_pricezg}</span> 
							</span> <span class="shop_brconsale">库存：<span class="yellow">${val.productNum}</span> </span>
						</div>
					</div> 
				</a>
			</c:forEach>
		</div>
		<!--滑动鼠标查看更多-->
		<p class="pagination">
			<span class="total  btn btn-primary"><%=pages.getCurrPageNumber()%>/<%=pages.getTotalPages()%></span>
			<%
				if (pages.isFirst()) {
			%>

			<%
				} else {
			%>
			<a href="javascript:setPage(<%=pages.getPrevious()%>);"
				start="<%=pages.getPrevious()%>" class="prev  btn btn-primary">上一页</a>
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
				start="<%=pages.getNext()%>" class="next  btn btn-primary">下一页 </a>
			<%
				}
			%>
		</p>
	</div>
</div>
<script type="text/javascript" src="js/eshop/productList.js"></script>
<%@ include file="../foot.jsp"%>