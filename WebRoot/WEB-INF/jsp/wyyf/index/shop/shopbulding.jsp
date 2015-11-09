<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.lys.utils.pagination.PageBean"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.lys.utils.*"%>
<%@ include file="../top.jsp"%>
<%
PageBean pages=(PageBean)request.getAttribute("shop_buldinglist");
 %>
<div class="row  index_carousel">
			<input type="hidden" id="type" value="${type}"/>
			<input type="hidden" id="page" value="${page}"/>
			<input type="hidden" id="OrderByType" value="${OrderByType}"/>
			<input type="hidden" id="Up" value="${Up}"/>
			<input type="hidden" id="minPrice" value="${minPrice}"/>
			<input type="hidden" id="maxPrice" value="${maxPrice}"/>
			<input type="hidden" id="shoptype" value="${shoptype}"/>
          <div class="carousel slide" id="mycarousel" data-ride="carousel" data-interval="3000">
            <ol class="carousel-indicators">
              <li class="active" data-target="#mycarousel" data-slide-to="0"></li>
              <li data-target="#mycarousel" data-slide-to="1"></li>
              <li data-target="#mycarousel" data-slide-to="2"></li>
            </ol>
            <div class="carousel-inner">
            <c:forEach items="${ad_adlist}" var="val" varStatus="i" >
				<div class="item <c:if test="${i.index == 0}">active</c:if>">
					<img src="${val.ag_st_url}" style="width: 100%;height: 315px;"/>
                </div>
			</c:forEach>
            </div>
            <a class="carousel-control left" data-target="#mycarousel" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left"></span>
            </a>
            <a class="carousel-control right" data-target="#mycarousel" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right"></span>
            </a>
          </div>
        </div>
        <div class="dsigback">
	 <div class="dsignbox">
	 	<!--头部路线-->
	 	   <div class="cheap_road" style="">
	 	  	  <ul class="breadcrumb" style="">
                   <li><a href="index">网众科技</a></li>
                   <li><a >商城</a></li>
                   <li class="active"><a>${shoptype=="building"?"建材商城":"家具商城"}</a></li>                   
              </ul>
	 	  </div>
	 	  <!--路线结束-->
	 	  <div class="shop_bulding  clearfix">
	 	  	 	  <!--左边建材商城-->
	 	  	  <div class="shop_buldingleft">
	 	  		 <div class="shop_bltitle">${shoptype=="building"?"建材商城":"家具商城"}</div>
	             <!--导航-->
	             <%List<Map<String, Object>> categoryList =(List<Map<String, Object>>)request.getAttribute("categoryList"); %>
	              <div class="shop_blnav">
	             	      <div class="leftsidebar_box">
		                    <div class="line"></div>
		                    <%for(int i=0;categoryList!=null&&i<categoryList.size();i++){ %>
		                
		                    		<%for(int j=0;j<categoryList.size();j++){ %>
		                    			<%if(categoryList.get(j).get("ab_st_parent").toString().equals(categoryList.get(i).get("ab_st_id").toString())){ %>
		                    			<dl class="system_log">
		                    				<%if(PageInnerMethod.IsExistChildItem(categoryList,categoryList.get(j))){%>
		                    					<dt><span  class="glyphicon glyphicon-triangle-right"><%=categoryList.get(j).get("ab_st_name")%></span></dt>
		                    				<%}else{%>
			                      				<dt><span onclick="setType(<%=categoryList.get(j).get("ab_st_type")%>)" class="glyphicon glyphicon-triangle-right"><%=categoryList.get(j).get("ab_st_name")%></span></dt>
			                      			<%} %>
			                      			<%for(int s=0;s<categoryList.size();s++){ %>
			                    				<%if(categoryList.get(s).get("ab_st_parent").toString().equals(categoryList.get(j).get("ab_st_id").toString())){ %>
			                    					<dd style="display: block;" ><a href="javascript:setType(<%=categoryList.get(s).get("ab_st_type")%>)"><%=categoryList.get(s).get("ab_st_name")%></a></dd>
			                    				<%} %>
		                    				<%} %>
		                    			</dl>
		                    			<%}%>
		                    		
		                    	<%}%>
		                    <%}%>
		                    <!-- <dl class="system_log">
			                      <dt><span class="glyphicon glyphicon-triangle-right"></span>基础建材</dt>
		                    </dl>
	                        <dl class="custom">
			                      <dt><span class="glyphicon glyphicon-triangle-right"></span>厨卫材料</dt>
		                    </dl>
	                        <dl class="channel">
			                      <dt><span class="glyphicon glyphicon-triangle-right"></span>客卧材料</dt>
		                    </dl>
	                        <dl class="app">
			                     <dt><span class="glyphicon glyphicon-triangle-right"></span>灯饰照明</dt>
		                    </dl>
                            <dl class="cloud">
			                     <dt><span class="glyphicon glyphicon-triangle-right"></span>家居建材</dt>
		                    </dl> -->
                     </div>
                  </div>
                  <!--最新推荐商品-->
	 	  		   <div class="shop_bltitle" style="">最新/推荐商品</div>
	 	  		   <c:forEach items="${tuijian_buldinglist}" var="val" varStatus="i">
	 	  		   <div class="shop_blcon clearfix">
                  	   <a href="index/shopmess?id=${val.bg_st_id}">
                  	      <div class="shop_blconbox">
                  	   	       <div class="shop_brbt">
                  	   	      	 <img src="${val.bg_st_img1 }" alt="" title="" height="200" width="190">
                  	   	       </div>
                  	   	       <div class="shop_blbcon">
                  	   	   	        <div>${val.bg_st_name }</div>
                  	   	   	        <span class="redcolor shop_blconnum ">RMB：<span class="" style="">${val.bg_st_pricezg }</span></span>
                  	   	            <span class="shop_blconsale">库存：<span class="yellow">${val.bg_nm_storeNum }</span></span>
                  	   	       </div>  	  
                  	      </div>
                  	   </a>
                  </div>
                  </c:forEach>
	 	  	  </div>
	 	  	  	 	  <!--左边建材商城结束-->
	 	  	  	 	  <!--右边商品信息-->
	 	  	  <div class="shop_buldingright clearfix" style="">
	 	  		      <!--右边头部排序--->
	 	  		   <div class="dsignboxin clearfix" style="">
          	           <ul>
          	 	         <li onclick="zonghe(${Up})"  ${OrderByType==0?'class="checkli"':''}>综合<span class="glyphicon ${OrderByType==0&&Up==0?'glyphicon-arrow-down':'glyphicon-arrow-up'}"></span></li>
          	 	         <li onclick="xiaoliang(${Up})" ${OrderByType==2?'class="checkli"':''}>销量<span class="glyphicon ${OrderByType==2&&Up==0?'glyphicon-arrow-down':'glyphicon-arrow-up'}"></span></li>
          	 	         <li onclick="jiage(${Up})" ${OrderByType==1?'class="checkli"':''}>价格<span class="glyphicon ${OrderByType==1&&Up==0?'glyphicon-arrow-down':'glyphicon-arrow-up'}"></span></li>
          	          </ul>
          	          <input id="minprice" value="${minPrice}" class="shop_brinput" type="text">-<input  value="${maxPrice}" id="maxprice" class="shop_brinput" type="text"><span
						onclick="setPrice()" class="shop_brok">确定</span> <span class="shop_brnum">共${ProductTotal }件商品</span>
                  </div>
                  <!--右边头部排序结束-->
                  <!--右边底部商品展示-->
                  <div class="shop_brcon clearfix">
                  	   <c:forEach items="${shop_buldinglist.getList()}" var="val" varStatus="i">
                  	   <a href="index/shopmess?id=${val.bg_st_id}">
                  	      <div class="shop_brconbox">
                  	   	       <div class="shop_brbt">
                  	   	      	 <img src="${val.bg_st_img1 }" alt="" title="" height="200" width="250">
                  	   	       </div>
                  	   	       <div class="shop_brbcon">
                  	   	   	        <div>${val.bg_st_name }</div>
                  	   	   	        <span class="redcolor shop_brconnum">RMB：<span class="" style="">${val.bg_st_pricezg}</span></span>
                  	   	            <span class="shop_brconsale">库存：<span class="yellow">${val.productNum }</span></span>
                  	   	       </div>
                  	      </div>
                  	   </a>
                  	   </c:forEach>
                  </div>
                  <!--右边底部商品展示结束-->
	 	  	  </div>
	 	  </div>
	 	  <!--滑动鼠标查看更多-->
      	 	   <p class="pagination">
			<span class="total"><%=pages.getCurrPageNumber() %>/<%=pages.getTotalPages() %></span>
			  <% if(pages.isFirst()){%>
			  
				 <%	}else{%>
						<a href="javascript:setPage(<%=pages.getPrevious() %>);" start="<%=pages.getPrevious() %>" class="prev">« 上一页</a>	
				 <% }
				 	for(int i=pages.getBetweenIndex().getStartIndex();i<=pages.getBetweenIndex().getEndIndex();i++){
				 %>
				 		 <a 
				 	<%	 if(pages.getCurrPageNumber() == i){ %>
				 			style="color: red;"
				 	<%	 }  %>
				 		 href="javascript:setPage(<%=i %>);" start="<%=i %>"><%=i %></a>
				 <% }  %>
				 <% if(pages.isLast()){ %>
				 <% }else{ %>
						<a  href="javascript:setPage(<%=pages.getNext() %>);" start="<%=pages.getNext() %>" class="next">下一页 »</a>	
				 <% }  %>
		</p>
	 </div>
</div>
<script type="text/javascript" src="js/eshop/shopbulding.js"></script>
<%@ include file="../foot.jsp"%>