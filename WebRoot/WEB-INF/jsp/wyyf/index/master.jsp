<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@page import="com.lys.utils.pagination.PageBean"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.lys.utils.*"%>
<%@ include file="top.jsp"%>
<%
PageBean pages=(PageBean)request.getAttribute("su_supervisionlist");
 %>
<div class="dsigback" style="">
	<input type="hidden" id="page" value="${page}" />
	<input type="hidden" id="cityCode" value="${cityCode}" />
	<input type="hidden" id="zxType" value="${zxType}" />
	<input type="hidden" id="OrderByType" value="${OrderByType}" />
	<input type="hidden" id="Up" value="${Up}" />
	 <div class="dsignbox" style="">
	 	  <div class="dsignboxtop cheap_road" style="">
	 	  	  <ul class="breadcrumb" style="background-color: transparent;">
                   <li><a href="index">首页</a></li>
                   <li class="active"><a href="index">我要装修</a></li>
                   <li><a href="index/master">找师傅</a></li>
              </ul>
	 	  </div>
          <!-- <div class="dsignboxtop" style="">
	 	  	   你好，网众竭诚为您服务.
	 	  </div> -->
          <div class="dsignboxin clearfix" style="">
          	<div>区域</div>
          	 <ul>
          	 	<li ${cityCode==CurrentCityCode?'class="checkli"':''} onclick="setCityCode(${CurrentCityCode})">全部</li>
          	 	<c:forEach items="${district_list}" var="val" varStatus="">
          	 	<li ${cityCode==val.d_code?'class="checkli"':''} onclick="setCityCode(${val.d_code})">${val.d_name}</li>
          	 	</c:forEach>
          	 </ul>
          </div>
          <div class="dsignboxin clearfix" style="">
          	<div>装修类型</div>
          	<ul style="">
          	   <li ${zxType=='0'?'class="checkli"':''} onclick="setZx('0')">全部</li>
          	  <c:forEach items="${sf }"  var="val">
          	 	<li ${zxType==val.ab_st_value?'class="checkli"':''} onclick="setZx('${val.ab_st_value}')">${val.ab_st_name }</li>
          	 	</c:forEach>
          	 </ul>
          	 <%-- <ul>
          	 	<li ${zxType=='0'?'class="checkli"':''} onclick="setZx('0')">全部</li>
          	 
          	 	<li ${zxType=='11'?'class="checkli"':''} onclick="setZx('11')">开孔师</li>
          	 	<li ${zxType=='12'?'class="checkli"':''} onclick="setZx('12')">铲墙师</li>
          	 	<li ${zxType=='15'?'class="checkli"':''} onclick="setZx('15')">安装师</li>
          	 	<li ${zxType=='7'?'class="checkli"':''} onclick="setZx('7')">木工</li>
          	 </ul> --%>
          </div>
          <div class="dsignboxin clearfix" style="">
          	 <ul>
          	 	<li onclick="setOrderBy(0,${Up})"  ${OrderByType==0?'class="checkli"':''}>综合排序<span class="glyphicon ${OrderByType==0&&Up==0?'glyphicon-arrow-down':'glyphicon-arrow-up'}"></span></li>
          	 	<li onclick="setOrderBy(1,${Up})"  ${OrderByType==1?'class="checkli"':''}>口碑排序<span class="glyphicon ${OrderByType==1&&Up==0?'glyphicon-arrow-down':'glyphicon-arrow-up'}"></span></li>
          	 	<li onclick="setOrderBy(2,${Up})"  ${OrderByType==2?'class="checkli"':''}>接单排序<span class="glyphicon ${OrderByType==2&&Up==0?'glyphicon-arrow-down':'glyphicon-arrow-up'}"></span></li>
          	 </ul>
          	 <div style="margin-left: -80px;">共${LabourTotal}位师傅</div>
          </div>
       <div class="fixgz_inbox dessty_inbox ">
       <c:forEach items="${su_supervisionlist.getList()}" var="val" varStatus="i">
    	   <div class=" clearfix fixgz_inbox_pepole dessty_inbox_pepole">
    	   	<a href="index/masterDetail?id=${val.ba_st_userid}&&sid=${sid}">
    	       <div class="tuijie_pepole_pic"><img src="${val.ag_st_url}" width="180px" height="180px"></div>
    	       <span class="tuijie_pepole_name">${val.ae_st_name}</span>  	
    	       <c:forEach begin="0" end="${val.ba_st_grade}">
    	       <span class="dessty_instar"><span class="glyphicon glyphicon-star"></span></span>
			   </c:forEach>
    	       <div class="dessty_ingood clearfix"><strong class="redcolor">RMB：${val.ba_st_price}元/平米</strong><span class="airnum" >接单数：${val.COUNT==null?0:val.COUNT}</span></div>
    	       </a>
    	       <div class="tuijie_pepole_check">
	    	       <a href="index/masterDetail?id=${val.ba_st_userid}&&sid=${sid}">预约</a>
    	       </div>
    	  </div>
       </c:forEach>
   
    	 </div>
          <p class="pagination">
			<span class="total  btn btn-primary"><%=pages.getCurrPageNumber() %>/<%=pages.getTotalPages() %></span>
			  <% if(pages.isFirst()){%>
			  
				 <%	}else{%>
						<a href="javascript:setPage(<%=pages.getPrevious() %>);" start="<%=pages.getPrevious() %>" class="prev btn btn-primary">上一页</a>	
				 <% }
				 	for(int i=pages.getBetweenIndex().getStartIndex();i<=pages.getBetweenIndex().getEndIndex();i++){
				 %>
				 		 <a 
				 	<%	 if(pages.getCurrPageNumber() == i){ %>
				 			style="color: red;"
				 	<%	 }  %>
				 		 href="javascript:setPage(<%=i %>);" class="btn btn-default" style="text-align:center;margin-right:3px;" start="<%=i %>"><%=i %></a>
				 <% }  %>
				 <% if(pages.isLast()){ %>
				 <% }else{ %>
						<a  href="javascript:setPage(<%=pages.getNext() %>);" start="<%=pages.getNext() %>" class="next  btn btn-primary">下一页 </a>	
				 <% }  %>
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
<script type="text/javascript" src="js/eshop/wyzx.js?v1.1"></script>
<%@ include file="foot.jsp"%>