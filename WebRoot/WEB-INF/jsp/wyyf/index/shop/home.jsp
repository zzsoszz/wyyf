<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../top.jsp"%>
<div class="row  index_carousel">
          <div class="carousel slide" id="mycarousel" data-interval="3000">
            <ol class="carousel-indicators">
              <li class="active" data-target="#mycarousel" data-slide-to="0"></li>
              <li data-target="#mycarousel" data-slide-to="1"></li>
              <li data-target="#mycarousel" data-slide-to="2"></li>
            </ol>
            <div class="carousel-inner">
            <%-- <c:forEach items="${ad_adlist}" var="val" varStatus="i">
                <div class="item active">
                     <img src="${val.ag_st_url }" alt="" title="" width="280px">
                </div>
            </c:forEach> --%>
            <c:forEach items="${ad_adlist}" var="val" varStatus="i" >
				<div class="item <c:if test="${i.index == 2}" >active</c:if>">
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
                   <li><a href="index.html">网众科技</a></li>
                   <li><a href="">家居商城</a></li>
                   <li class="active"><a href="">建材商城</a></li>                   
              </ul>
	 	  </div>
	 	  <!--路线结束-->

	 	  <div class="shop_bulding  clearfix">
	 	  	 	  <!--左边建材商城-->
	 	  	  <div class="shop_buldingleft">
	 	  		 <div class="shop_bltitle">建材商城</div>
	             <!--导航-->
	              <div class="shop_blnav">
	             	      <div class="leftsidebar_box">
		                      <div class="line"></div>
		                      <dl class="system_log">
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
		                    </dl>
                     </div>
                  </div>
                  <!--最新推荐商品-->
	 	  		   <div class="shop_bltitle" style="">最新/推荐商品</div>
	 	  		   <div class="shop_blcon clearfix">
	 	  		   <c:forEach items="${tuijian_buldinglist}" var="val" varStatus="i">
                  	   <a href="index/shopmess?id=${val.bg_st_id}">
                  	      <div class="shop_blconbox">
                  	   	       <div class="shop_brbt">
                  	   	      	 <img src="${val.ag_st_url}" alt="" title="" height="200" width="190">
                  	   	       </div>
                  	   	       <div class="shop_blbcon">
                  	   	   	        <div>${val.bg_st_name}</div>
                  	   	   	        <span class="redcolor shop_blconnum">RMB：<span class="" style="">${val.bg_st_pricezg }</span></span>
                  	   	            <span class="shop_blconsale">库存：<span class="yellow">${val.bg_nm_storeNum }</span></span>
                  	   	       </div>  	  
                  	      </div>
                  	   </a>
                  </c:forEach>
                  </div>
	 	  	  </div>
	 	  	  	 	  <!--左边建材商城结束-->
	 	  	  	 	  <!--右边商品信息-->
	 	  	  <div class="shop_buldingright clearfix" style="">
	 	  		      <!--右边头部排序--->
	 	  		   <div class="dsignboxin clearfix" style="">
          	           <ul>
          	 	         <li>综合<span class="glyphicon glyphicon-arrow-down"></span></li>
          	 	         <li>销量<span class="glyphicon glyphicon-arrow-down"></span></li>
          	 	         <li>价格<span class="glyphicon glyphicon-arrow-down"></span></li>
          	          </ul>
          	          <input class="shop_brinput" type="text">-<input class="shop_brinput" type="text"><span class="shop_brok">确定</span>
                  </div>
                  <!--右边头部排序结束-->
                  <!--右边底部商品展示-->
                  <div class="shop_brcon clearfix">
                  <c:forEach items="${shop_buldinglist}" var="val" varStatus="i">
                  	   <a href="index/shopmess?id=${val.bg_st_id}">
                  	      <div class="shop_brconbox">
                  	   	       <div class="shop_brbt">
                  	   	      	 <img src="${val.ag_st_url }" alt="" title="" height="200" width="250">
                  	   	       </div>
                  	   	       <div class="shop_brbcon">
                  	   	   	        <div>${val.bg_st_name }</div>
                  	   	   	        <span class="redcolor shop_brconnum">RMB：<span class="" style="">${val.bg_st_pricezg }</span></span>
                  	   	            <span class="shop_brconsale">库存：<span class="yellow">${val.bg_nm_storeNum }</span></span>
                  	   	       </div>  
                  	      </div>
                  	   </a>
                  </c:forEach>
                  </div>
                  <!--右边底部商品展示结束-->
	 	  	  </div>
	 	  	
	 	  </div>
	 	  <!--滑动鼠标查看更多-->
         <div class="dsignmore" style="">
         	<i class="glyphicon glyphicon-arrow-down"></i>滑动鼠标查看更多
         </div>
	 </div>
</div>
<%@ include file="../foot.jsp"%>