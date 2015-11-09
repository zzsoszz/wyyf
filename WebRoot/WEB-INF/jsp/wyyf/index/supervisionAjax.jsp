<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@page import="com.lys.utils.pagination.PageBean"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.lys.utils.*"%>
<%@ include file="top.jsp"%>
<%
PageBean pages=(PageBean)request.getAttribute("su_supervisionlist");
String type=(String)request.getAttribute("type");
 %>
<div class="dsigback" style="">
	<input type="hidden" id="page" value="${page}" />
	<input type="hidden" id="cityCode" value="${cityCode}" />
	<input type="hidden" id="type" value="${type}" />
	<input type="hidden" id="OrderByType" value="${OrderByType}" />
	<input type="hidden" id="Up" value="${Up}" />
	 <div class="dsignbox" style="">
	 	  <div class="dsignboxtop cheap_road" style="">
	 	  	  <ul class="breadcrumb" style="background-color: transparent;">
                   <li><a href="index">首页</a></li>
                   <%if(type.equals("jianli")){ %>
                   <li class="active"><a>我要监理</a></li>
                   <%}else{ %>
                   <li class="active"><a>我要检测</a></li>
                   <%} %>
              </ul>
	 	  </div>
          <!-- <div class="dsignboxtop" style="">
	 	  	   你好，网众竭诚为您服务.
	 	  </div> -->
          <div class="dsignboxin clearfix" style="">
          	<div>区域</div>
          	 <ul>
          	 	<li ${cityCode==CurrentCityCode?'class="checkli"':''} ms-click="setCityCode(${CurrentCityCode})">全部</li>
          	 	<c:forEach items="${district_list}" var="val" varStatus="">
          	 	<li ${cityCode==val.d_code?'class="checkli"':''} ms-click="setCityCode(${val.d_code})">${val.d_name}</li>
          	 	</c:forEach>
          	 </ul>
          </div>
          <div class="dsignboxin clearfix" style="">
          	 <ul>
          	 	<li ms-click="setOrderBy(0,Up)">综合排序<span ms-attr-class="{{(OrderByType==0 && Up==0) ? 'glyphicon glyphicon-arrow-down' : 'glyphicon glyphicon-arrow-up'}}" ></span></li>
       
          	 	<li ms-click="setOrderBy(1,Up)" >口碑排序<span  ms-attr-class="{{(OrderByType==1 && Up==0) ? 'glyphicon glyphicon-arrow-down' : 'glyphicon glyphicon-arrow-up'}}"></span></li>
      
          	 	<li ms-click="setOrderBy(2,Up)" >接单排序<span  ms-attr-class="{{(OrderByType==2 && Up==0) ? 'glyphicon glyphicon-arrow-down' : 'glyphicon glyphicon-arrow-up'}}"></span></li>
          	 
          	 	<li ms-click="setOrderBy(3,Up)" >价格由高到低<span  ms-attr-class="{{(OrderByType==3 && Up==0) ? 'glyphicon glyphicon-arrow-down' : 'glyphicon glyphicon-arrow-up'}}"></span></li>
          	 
          	 </ul>
          	 <div style="margin-left: -80px;">{{totalElements}}</div>
          </div>
       <div class="fixgz_inbox dessty_inbox ">
       <!--<c:forEach items="${su_supervisionlist.getList()}" var="val" varStatus="i">
    	   <div class=" clearfix fixgz_inbox_pepole dessty_inbox_pepole">
    	       <div class="tuijie_pepole_pic"><img src="${val.ag_st_url}" width="160px" height="160px"></div>
    	       <span class="tuijie_pepole_name">${val.ae_st_name}</span>  	
    	       <c:forEach begin="0" end="${val.ba_st_grade}">
    	       <span class="dessty_instar"><span class="glyphicon glyphicon-star"></span></span>
			   </c:forEach>
    	       <div class="dessty_ingood"><strong class="redcolor">RMB：${val.ba_st_price}元/平米</strong><span class="airnum" style="margin-left: 15px;">接单数：${val.COUNT==null?0:val.COUNT}</span></div>
    	       <div class="tuijie_pepole_check">
	    	       <a href="index/ShopUserDetail?id=${val.ba_st_userid}&&sid=${sid}&type=${type}">预约他监理</a>
    	       </div>
    	  </div>
       </c:forEach>-->
          <div ms-repeat-val="products" class=" clearfix fixgz_inbox_pepole dessty_inbox_pepole">
    	       <div class="tuijie_pepole_pic"><img ms-src="{{val.ag_st_url}}" width="180px" height="180px"></div>
    	       <span class="tuijie_pepole_name">{{val.ae_st_name}}</span>  	
    	       <span class="dessty_instar" ms-repeat-star="Convert(val.ba_st_grade)"><span class="glyphicon glyphicon-star"></span></span>
    	       <div class="dessty_ingood clearfix"><strong class="redcolor">RMB：{{val.ba_st_price}}元/平米</strong><span class="airnum" >接单数：{{val.COUNT}}</span></div>
    	       <div class="tuijie_pepole_check">
    	       <%if(type.equals("jianli")){ %>
	    	       <a ms-href="index/ShopUserDetail?id={{val.ba_st_userid}}&type={{type}}">预约</a>
	    	       <%}else{ %>
	    	       <a ms-href="index/ShopUserDetail?id={{val.ba_st_userid}}&type={{type}}">预约</a>
	    	       <%} %>
    	       </div>
    	  </div>
    	 </div>
     <p class="pagination">
	 	  	<span class="total btn btn-primary">{{currPageNumber}}/{{totalPages}}&nbsp;</span>
	 	  	<a href="javascript:void(0);" ms-click="setPage(previous)"  ms-if="!first" class="prev btn btn-primary">上一页</a>	
	 	  	<a class="btn btn-default" style="text-align:center;margin-right:3px;" ms-repeat-page="pageList" ms-click="setPage(page);" ms-css-color="page==currPageNumber?'red':'none'" href="javascript:void(0);" >{{page}}&nbsp;</a>
	 	  	<a  href="javascript:void(0);" ms-click="setPage(next)"  ms-if="!last"  class="next btn btn-primary">下一页 </a>	
	 	  </p>
         <div class="" style="height: 30px;"></div>
  </div>
  <div class="check_out" style="display: none;">
  	    <div class="check_outtop clearfix" style="display: block;">
  	    	<div class="check_otleft" style="">预约提示</div>
  	    	<div class="close closeout" onclick="myclose(this);"><span class="glyphicon glyphicon-remove" style="color: white;"></span></div>
  	    </div>
  	  <!--  <span class="glyphicon glyphicon-ok-sign check_outfont"></span>-->
  	    <div class="check_outpoint" style="text-align: left;padding-left: 20px;margin-top: 20px;width: 100%;">
  	    	<h4>您好!</h4>为了您的房屋装修质量更完美，申请装修服务后我们将免费为您提供监督服务。<br>如果您未申请装修服务，您也可以预约收费监理<span class="redcolor">
  	    		（6元/平方米）</span>，我们将安排专业人员监督您的房屋装修过程。</div>
  	    <div class="baoming_bu check_ob">		
				<input value="我知道了"  type="button" onclick="checkok();" />
				<a href="index/loginp">去登陆</a>
		</div>
  </div>
</div>
<script type="text/javascript" src="js/eshop/surveygold.js"></script>
<%@ include file="foot.jsp"%>