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
		<!-- <ol class="carousel-indicators">
			<li class="active" data-target="#mycarousel" data-slide-to="0"></li>
			<li data-target="#mycarousel" data-slide-to="1"></li>
			<li data-target="#mycarousel" data-slide-to="2"></li>
		</ol> -->
		<div class="carousel-inner">
			<c:forEach items="${ad_adlist}" var="val" varStatus="i">
				<div class="item <c:if test="${i.index == 0}">active</c:if>">
					<img src="${val.ag_st_url}" style="width: 100%;height: 315px;" />
				</div>
			</c:forEach>
		</div>
		<!-- <a class="carousel-control left" data-target="#mycarousel"
			data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"></span>
		</a> <a class="carousel-control right" data-target="#mycarousel"
			data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right"></span> </a> -->
	</div>
</div>
<div class="dsigback">
	<div class="dsignbox">
		<!--头部路线-->
		<div class="cheap_road" style="">
			<ul class="breadcrumb" style="">
				<li><a href="index">首页</a></li>
				<li><a>购物商城</a></li>
			
			</ul>
		</div>
		<div class="dsignboxin clearfix" style="">
          	<div>品牌</div>
          	 <ul>
          	 	<c:forEach items="${shopList}" var="val" varStatus="i">
          	 	
          	 	<c:if test="${!empty val.ae_st_name }">
					<li ms-click="setBb_st_id('${val.bb_st_userid}')"><a  href="javascript:void(0);">${val.ae_st_name}</a></li>
				</c:if>
				</c:forEach>
          	 </ul>
          </div>
          <div class="dsignboxin clearfix shoplist_nav2" style="">
	          	 <ul>
						
						<li ms-click="zonghe(Up)">综合<span ms-attr-class="{{(OrderByType==0 && Up==0) ? 'glyphicon glyphicon-arrow-down' : 'glyphicon glyphicon-arrow-up'}}" ></span></li>
       
				
						<li ms-click="xiaoliang(Up)">销量<span ms-attr-class="{{(OrderByType==2 && Up==0) ? 'glyphicon glyphicon-arrow-down' : 'glyphicon glyphicon-arrow-up'}}" ></span></li>
       
					
						<li ms-click="jiage(Up)">价格<span ms-attr-class="{{(OrderByType==1 && Up==0) ? 'glyphicon glyphicon-arrow-down' : 'glyphicon glyphicon-arrow-up'}}" ></span></li>
       
					</ul>
	          	 <span class="shoplist_span" style="">
	          	 <input id="minprice" value="${minPrice}" class="shop_brinput" type="text"/>-<input value="${maxPrice}" id="maxprice" class="shop_brinput" type="text"/></span>
	          	 <a class="shoplist_a" ms-click="setPrice()" href="javascript:void(0)">确定</a>

				<span class="shoplist_span3" style="">
				<input id="IsPromotion" type="checkbox"  ms-click="ChangePromotion(this);"/>促销</span>

	          	 <div style="">共{{totalElements}}件商品</div>
          </div>
		<div class="shop_brcon clearfix shoplist_box">
		   <a ms-repeat-val="products" ms-href="index/shopmess?id={{val.bg_st_id}}">
                  	      <div class="shop_brconbox">
                  	   	       <div class="shop_brbt">
                  	   	      	 <img ms-src="{{val.bg_st_img1 }}" alt="" title="" height="200" width="250">
                  	   	       </div>
                  	   	       <div class="shop_brbcon clearfix">
                  	   	   	        <div>{{val.bg_st_name }}</div>
                  	   	   	        <span class="redcolor shop_brconnum f_fl">RMB：<span class="" style="">{{val.bg_st_pricezg}}</span></span>
                  	   	            <span class="shop_brconsale f_fl">库存：<span class="yellow">{{val.productNum }}</span></span>
                  	   	       </div>
                  	      </div>
                  	   </a>
		</div>
		<!--滑动鼠标查看更多-->
  <p class="pagination">
	 	  	<span class="total btn btn-primary ">{{currPageNumber}}/{{totalPages}}&nbsp;</span>
	 	  	<a href="javascript:void(0);" ms-click="setPage(previous)"  ms-if="!first" class="prev  btn btn-primary"> 上一页</a>	
	 	  	 <a  class="btn btn-default" style="text-align:center;margin-right:3px;" ms-repeat-page="pageList" ms-click="setPage(page);" ms-css-color="page==currPageNumber?'red':'none'" href="javascript:void(0);" >{{page}}</a>
	 	  	<a  href="javascript:void(0);" ms-click="setPage(next)"  ms-if="!last"  class="next btn btn-primary">下一页 </a>	
	 	  </p>

	</div>
</div>
<script type="text/javascript" src="js/eshop/productList.js"></script>
<%@ include file="../foot.jsp"%>