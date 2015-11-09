<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="jsplib.jsp"%>

<c:forEach items="${userlist}" var="val" varStatus="i" >
	<li class="design_people clearfix" >
	              
	                      	  		<div class="design_pleft"  style="">
	                      	  		<a href="wap/designermess?fl=${fl }&id=${val.ae_st_id}&sqlx=${sqlx}">
	                      	  	 	  <div class="design_pic" style="">
	                      	  	 	  	<img src="${val.ag_st_url}" class="img-responsive " />
	                      	  	 	  </div>
	                      	  	 	    </a>
	                      	  	 	   <span>${val.ae_st_name}</span>
	                      	  	   </div>
	                       	  	 <div class="design_pright clearfix" style="">
	                       	  	 	 <ul class="star_px clearfix">
	                       	  	 	 	<c:forEach var="i" begin="1" end="${val.ba_st_grade}" step="1"> 
											<li><img src="${ctx}/images/wap/images/star1.png"  class="img-responsive "></li>
										</c:forEach>
	                       	  	 	 	<!-- 
	                       	  	 	 	<li><img src="${ctx}/images/wap/images/star1.png"  class="img-responsive"></li>
	                       	  	 	 	<li><img src="${ctx}/images/wap/images/star1.png"  class="img-responsive"></li>
	                       	  	 	 	<li><img src="${ctx}/images/wap/images/star2.png"  class="img-responsive"></li>
	                       	  	 	 	<li><img src="${ctx}/images/wap/images/star2.png"  class="img-responsive"></li>
	                       	  	 	 	 -->
	                       	  	 	 	 <p class="margin_bott">简介：${val.ae_st_intro}</p>
		                       	  	 	 <span class="starpx_1">团队介绍：${val.ba_st_team_intro} </span>
		                       	  	 	 <span class="starpx_2">收费标准：${val.ba_st_price}/平方</span>
		                       	  	 	 <c:if test="${ isShow ==0 }">
										 <span class="baoming_span1">补交金额</span>:<input style="outline: medium none;" type="text" name="bj_maney" placeholder="补交金额属私下成交金额" id="bj_maney">
										 <input class="btn btn-primary btn-xs" style="margin-top:3px;" type="button" value="点击付款" onclick="toPayO()">
										 </c:if>
		                       	  	 	 <c:if test="${val.pjs==0  }">
		                       	  	 	  <span class="starpx_2"><a class="btn btn-primary btn-xs" style="color:#fff;" href="wap/previews?id=${val.ae_st_id }&aid=${val.bf_st_id}&sjs=${sjs}">去评价</a></span>
		                       	  	 	 </c:if>
		                       	  	 	 <c:if test="${ val.pjs != 0  && sjs == 0 }">
		                       	  	 	  <span class="starpx_2"><a class="btn btn-primary btn-xs" style="color:#fff;" href="wap/previews?id=${val.ae_st_id }&aid=${val.bf_st_id}&sjs=${sjs}&pj=1">查看</a></span>
		                       	  	 	  </c:if> 
										
										 
										
       	                                     <script type="text/javascript">
	       	                                	 function toPayO(){
			       	                   				var bj_maney =$("#bj_maney").val();
			       	                   			
			       	                   				location.href="wap/toPay2?bj_maney="+bj_maney+"&rid=${val.bf_st_receiveid}";
	       	                                	 } 
       	                                     </script>
	                       	  	 	 </ul>
	                       	  	 	 
	                       	  	 </div>
	            
	</li>
</c:forEach>
										
											
