<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="jsplib.jsp"%>

 <c:forEach items="${pages.data }" var="v">
<li class="design_people clearfix">
		                       	  	<a href="wap/designermess?fl=${fl }&id=${v.ae_st_id}&sqlx=7">
		                       	  		<div class="design_pleft" style="">
		                       	  	 	  <div class="design_pic" style="">
		                       	  	 	  	<img src="${v.ag_st_url }" class="img-responsive">
		                       	  	 	  </div>
		                       	  	 	   <span>${v.ae_st_name }</span>
		                       	  	   </div>
			                       	  	 <div class="design_pright clearfix" style="">
			                       	  	 	 <ul class="star_px clearfix">
			                       	  	 	
			                       	  	 	<c:if test="${v.ba_st_grade != 0}">
			                       	  	 	<c:forEach  begin="0" end="${v.ba_st_grade}" var="v1">
			                       	  	 	<li><img src="${ctx}/images/wap/images/star1.png" class="img-responsive"></li>
			                       	  	 	</c:forEach>
			                       	  	 	</c:if>
			                       	  	 	  </ul>
			                       	  	 	 <p class="margin_bott">简介：${v.ae_st_intro }</p>
			                       	  	 	 <span class="starpx_1">团队介绍：${v.ba_st_team_intro }</span>
			                       	  	 	 <span class="starpx_2">收费标准：${v.ba_st_price }/平方</span>
			                       	  	 </div>
		                       	  	 </a> 
		                       	  </li>
		                       	  
		                       	  </c:forEach>