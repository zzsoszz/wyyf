<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@page import="com.power.utils.PowerStatic"%>
<%@page import="com.lys.utils.StringUtils"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.lys.utils.pagination.PageBean"%>
<%@ include file="../top.jsp"%>
<div class="dsigback" style="">
	 <div class="dsignbox" style="">
	 	<div class="cheap_road" style="">
	 	  	  <ul class="breadcrumb" style="">
                   <li><a href="index">首页</a></li>
                   <li class="active">团代金券</li>
              </ul>
	 	  </div>
	 	<div class="designshow_boxcon">优惠卡券</div>
	 	<%
				PageBean pages=(PageBean)request.getAttribute("pageBeanObj");
				List<Map<String, Object>> dataList=pages.getList();
				%>
       <div class="dsignboxin clearfix" style="display:none;">
          	 <ul style="">
          	 	<c:if test="${priceUp==0}">
          	 		<li><a href="index/actbuy?priceUp=1&expireTimeUp=${expireTimeUp}&effectTimeUp=${effectTimeUp}&page=${page}">优惠金额</a><i class="glyphicon glyphicon-arrow-up"></i></li>
          	 	</c:if>
          	 	<c:if test="${priceUp!=0}">
          	 		<li><a href="index/actbuy?priceUp=0&expireTimeUp=${expireTimeUp}&effectTimeUp=${effectTimeUp}&page=${page}">优惠金额</a><i class="glyphicon glyphicon-arrow-down"></i></li>
          	 	</c:if>
          	 	
          	 	<c:if test="${expireTimeUp==0}">
          	 		<li><a href="index/actbuy?priceUp=${priceUp}&expireTimeUp=1&effectTimeUp=${effectTimeUp}&page=${page}">到期时间</a><i class="glyphicon glyphicon-arrow-up"></i></li>
          	 	</c:if>
          	 	<c:if test="${expireTimeUp!=0}">
          	 		<li><a href="index/actbuy?priceUp=${priceUp}&expireTimeUp=0&effectTimeUp=${effectTimeUp}&page=${page}">到期时间</a><i class="glyphicon glyphicon-arrow-down"></i></li>
          	 	</c:if>
          	 	
          	 	<c:if test="${effectTimeUp==0}">
          	 		<li><a href="index/actbuy?priceUp=${priceUp}&expireTimeUp=${expireTimeUp}&effectTimeUp=1&page=${page}">生效时间</a><i class="glyphicon glyphicon-arrow-up"></i></li>
          	 	</c:if>
          	 	<c:if test="${effectTimeUp!=0}">
          	 		<li><a href="index/actbuy?priceUp=${priceUp}&expireTimeUp=${expireTimeUp}&effectTimeUp=0&page=${page}">生效时间</a><i class="glyphicon glyphicon-arrow-down"></i></li>
          	 	</c:if>
          	 </ul>
          	 <div style="margin-left: -90px;">共${productTotal}张优惠券</div>
          </div>
       <div class=" actbuy_inbox clearfix">   
       		<c:forEach items="${pageBeanObj.getList()}" var="val" varStatus="i">   	
    	      <div class="actbuy_inbox_pepole ">
    	           <div class="actbuy_top <c:if test="${i.index%3==0}" >actbuy_20</c:if><c:if test="${i.index%3==1}" >actbuy_50</c:if><c:if test="${i.index%3==2}" >actbuy_100</c:if>">
                        <div class="clearfix">￥<span class="actbuy_money">${val.bg_st_pricezg}<!-- 商品积分,表示优惠券的总价值 --></span><span class="actbuy_way">全店通用</span></div>
                        <p>发行店铺：<span>${val.ae_st_name}</span></p>
                        <p>使用条件：<span>无条件使用</span></p>
                        <p>有效时间：<span>${val.bg_dt_startDate}至${val.bg_dt_endDate}</span></p>
     	           </div>
    	           <div class="actbuy_con">
    	       	        <img src="${val.bg_st_img1}" alt="" title="" width="200px" height="80px">
    	           </div>
    	           <div class="actbuy_lq"><a href="index/shopmess?id=${val.bg_st_id}">购买</a></div>
    	       </div>
       		</c:forEach> 
    	 </div>
 		<p class="pagination">
			<span class="total btn btn-primary"><%=pages.getCurrPageNumber() %>/<%=pages.getTotalPages() %></span>
			  <% if(pages.isFirst()){%>
			  
				 <%	}else{%>
						<a href="index/actbuy?priceUp=${priceUp}&expireTimeUp=${expireTimeUp}&effectTimeUp=${effectTimeUp}&page=<%=pages.getPrevious() %>" start="<%=pages.getPrevious() %>" class="prev btn btn-primary">上一页</a>	
				 <% }
				 	for(int i=pages.getBetweenIndex().getStartIndex();i<=pages.getBetweenIndex().getEndIndex();i++){
				 %>
				 		 <a 
				 	<%	 if(pages.getCurrPageNumber() == i){ %>
				 			style="color: red;"
				 	<%	 }  %>
				 		 href="index/actbuy?priceUp=${priceUp}&expireTimeUp=${expireTimeUp}&effectTimeUp=${effectTimeUp}&page=<%=i %>" class="btn btn-default" style="text-align:center;margin-right:3px;" start="<%=i %>"><%=i %></a>
				 <% }  %>
				 <% if(pages.isLast()){ %>
				 <% }else{ %>
						<a  href="jindex/actbuy?priceUp=${priceUp}&expireTimeUp=${expireTimeUp}&effectTimeUp=${effectTimeUp}&page=<%=pages.getNext() %>" start="<%=pages.getNext() %>" class="next btn btn-primary">下一页 </a>	
				 <% }  %>
		</p>
  </div>
</div>
<div class="check_out" id="gzcheck">
  	    <div class="check_outtop clearfix" style="display: block;">
  	    	<div class="check_otleft" style="">购买成功</div>
  	    	<div class="close closeout" onclick="myclose();"><span class="glyphicon glyphicon-remove" style="color: white;"></span></div>
  	    </div>
  	    <span class="glyphicon glyphicon-ok-sign check_outfont"></span>
  	    <div class="check_outpoint">您好，您已成功购买优惠券。您可以在家居商城中使用。</div>
  	    <div class="baoming_bu check_ob">		
				<input value="返回首页" onclick="checkok()" type="button">
		</div>
</div>
<%@ include file="../foot.jsp"%>
