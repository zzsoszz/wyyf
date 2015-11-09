<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../top.jsp"%>
<%@page import="java.util.List"%>
<%@page import="com.lys.utils.pagination.PageBean"%>
<%PageBean pages=(PageBean)request.getAttribute("pages");%>
<div class="row  index_carousel">
          <div class="carousel slide" id="mycarousel" data-ride="carousel" data-interval="3000">
            <ol class="carousel-indicators">
            	<c:forEach items="${ad_adlist}" var="val" varStatus="i" >
              <li <c:if test="${i.index==0}" >class="active"</c:if> data-target="#mycarousel" data-slide-to="${i.index}"></li>
              </c:forEach>
            </ol>
          
            <div class="carousel-inner">
            
            <c:forEach items="${ad_adlist}" var="val" varStatus="i" >
				<div class="item <c:if test="${i.index == 0}" >active</c:if>">
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
	 	   <div class="cheap_road" style="">
	 	  	  <ul class="breadcrumb" style="">
                   <li><a href="index">首页</a></li>
                   <li class="active"><a href="">我要特价</a></li>                   
              </ul>
	 	  </div>
	 	 
	 	  <!--抢相因商品cheap_box-->
	 	  <div class="cheap_bigbox clearfix">
	 	  <c:forEach items="${pages.getList()}" var="val" varStatus="i">
	 	  	  <div class="cheap_box clearfix">
	 	  	  <div class="cheap_time clearfix lxftime" type="type1"  shopid="${val.bg_st_id}" nowtime="${val.nowTime}" begintime="${val.bo_dt_startDate }" endtime="${val.bo_dt_endDate }">


                </div>
	 		  <%-- <div class="cheap_time clearfix">
	 		  	<input id="startDate_${val.bg_st_id}" type="hidden" name="startDate" value="${val.bo_dt_startDate }"/>
	 		  	<input id="endDate_${val.bg_st_id}" type="hidden" name="endDate" value="${val.bo_dt_endDate }"/>
						<span class="cheap_lttitle">本场结束时间：</span>
						${val.bo_dt_startDate }  ${val.bo_dt_endDate }
                      <span id="Time1_${val.bg_st_id}" class="cheap_lasttime cheap_bgredcolor">0</span>
                      <span id="Time2_${val.bg_st_id}" class="cheap_lasttime cheap_bgredcolor">4</span>&nbsp;:
                      <span id="Time3_${val.bg_st_id}" class="cheap_lasttime cheap_bgredcolor">3</span>
                      <span id="Time4_${val.bg_st_id}" class="cheap_lasttime cheap_bgredcolor">3</span>&nbsp;:
                      <span id="Time5_${val.bg_st_id}" class="cheap_lasttime cheap_bgredcolor">0</span>
                      <span id="Time6_${val.bg_st_id}" class="cheap_lasttime cheap_bgredcolor">0</span>
               </div> --%>
 	  	       <div class="cheap_boxleft">
 	  	       	  <img src="${val.bg_st_img1}" alt="" title="" >
 	  	       </div>
 	  	       <div class="cheap_boxright">
 	  	       	    <div class="goods_name">${val.bg_st_name}</div>
 	  	       	    <span class="redcolor">RMB：<strong>${val.bg_st_pricetj}</strong></span>
 	  	       	    <span class="cheap_oldprice">${val.bg_st_pricezg}</span>
                       <span class="cheap_line"></span>
                       <div class="cheap_salenum gray">已售:${val.num}件</div>
                       <a href="index/shopmess?id=${val.bg_st_id}">
                       	<div class="cheap_br_check cheap_check_red">马上抢</div>
                       </a>
 	  	       </div>
	 	  	  </div>
	 	  </c:forEach>
	 	  </div>
	 	  <!--商品显示盒子结束-->
	 	   <p class="pagination">
			<span class="total  btn btn-primary"><%=pages.getCurrPageNumber() %>/<%=pages.getTotalPages() %></span>
			  <% if(pages.isFirst()){%>
			  
				 <%	}else{%>
						<a href="javascript:void(0);" start="<%=pages.getPrevious() %>" class="prev btn btn-primary">上一页</a>	
				 <% }
				 	for(int i=pages.getBetweenIndex().getStartIndex();i<=pages.getBetweenIndex().getEndIndex();i++){
				 %>
				 		 <a 
				 	<%	 if(pages.getCurrPageNumber() == i){ %>
				 			style="color: red;"
				 	<%	 }  %>
				 		 href="javascript:void(0);" class="btn btn-default" style="text-align:center;margin-right:3px;" start="<%=i %>"><%=i %></a>
				 <% }  %>
				 <% if(pages.isLast()){ %>
				 <% }else{ %>
						<a  href="javascript:void(0);" start="<%=pages.getNext() %>" class="next btn btn-primary">下一页 </a>	
				 <% }  %>
		</p>
		<script>
			//翻页
			 $("p.pagination a").on('click', function() {
				 var start=$(this).attr("start");
				 location.href="index/shopcheap?page="+start;
			 });
		</script>
	 	  <!--滑动鼠标查看更多>
         <div class="dsignmore" style="">
         	<i class="glyphicon glyphicon-arrow-down"></i>滑动鼠标查看更多
         </div-->
	 </div>
</div>
<script>
(function (){

    $('.lxftime').each(function (){
        var endTime=new Date(Date.parse($(this).attr('endtime'))).getTime() ;
        var beginTime=new Date(Date.parse($(this).attr('begintime'))).getTime() ;
        var nowTime=new Date(Date.parse($(this).attr('nowtime'))).getTime() ;

        $(this).attr('endtime',endTime);
        $(this).attr('begintime',beginTime);
        $(this).attr('nowtime',nowTime);
    })

    setInterval(lxfEndtime,1000);
})();

</script>
<%@ include file="../foot.jsp"%>