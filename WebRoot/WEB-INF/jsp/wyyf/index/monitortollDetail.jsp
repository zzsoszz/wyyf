<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ include file="top.jsp"%>
<div class="dsigback">
	 <div class="dsignbox" style="">
         <div class="cheap_road" style="">
	 	  	  <ul class="breadcrumb" style="">
                   <li><a href="index">首页</a></li>
                   <li><a href="index/aircharge">我要检测</a></li>
                   <li class="active"><a>检测师详情</a></li>
              </ul>
	 	 </div>
        <div class="designshow_boxtop clearfix" style="">
        	<c:forEach items="${mo_monitortollDetaillist1}" var="val" varStatus="i">
        	<div class="designshow_boxtop_left" style=""><img src="${val.ag_st_url}" width="140px" height="140px"></div>
        	<div class="designshow_boxtop_right" style="">
        	    <p class="clearfix">
        	        <span class="designshow_tr_name f_fl" style="">${val.ae_st_name}</span>
	        	   <c:forEach begin="0" end="${val.ba_st_grade}">
		    	        <span class="dessty_instar f_fl">
							 <span class="glyphicon glyphicon-star"></span>
					    </span>
				   </c:forEach>
        	    </p>
        		
        	    <div>入住时间：${val.ba_dt_addDate}</div>
        	    <div>签单业主数：${val.jdnum}</div>
        	    <div>投诉纷争数：${val.ba_st_remark}</div>
        	    <div class="desin_show_check"><a href="index/monitortollOrder?rid=${rid}&&sid=${sid}">预约检测</a></div>
        	</div>
        </div>
        <div class="designshow_boxcon" style="">团队介绍</div>
        <div style="text-align: left;margin-bottom: 20px;">
        	${val.ba_st_team_intro}
        </div>
        	</c:forEach>
     <div class="designshow_boxbottom clearfix" style="">
     	<div class="fixgz_bott_left" style="">
     		<div class="fixgz_blt" style="">业主好评</div>
     		  <div id="temp5">
			     <div class="JQ-content-box">
			    	<ul class="JQ-slide-content">
					<li>
						<div style="">
						${mo_monitortollDetaillist2}
						<c:forEach items="${mo_monitortollDetaillist2}" var="val" varStatus="i">
							<span class="fixgz_name">业主:${val.ae_st_name}</span><br>
							<span class="fixgz_time">时间:${val.be_dt_addDate}</span><br>
							<span class="fixgz_pj">评价内容:${val.be_st_content}</span>
						</c:forEach>
						</div>
					</li>
				</ul>
			</div>
		</div><!--temp5 end-->
		      <div class="fixgz_blb" style="">
		      	   <div class="fixgz_blbcon" style="">
		      	   	    为了保证网站的权威公正性，如果您是业主且已选择该工长为您服务，请先登录账户在评价！
		      	   </div>
		      	   <input type="radio" name="ok" class="fixgz_pjchoose" style="margin-left: 50px;margin-top: 10px;">表扬
		      	   <input type="radio" name="ok" class="fixgz_pjchoose" style="margin-left: 50px;">投诉
		      	   <div class="fixgz_fbpl" style="">发表评论</div>
		      </div>
     	</div>
     	<div class="fixgz_bott_right" style="">
     		<div class="fixgz_brt">往期案例</div>
     	    <div class="fixgz_brtbox clearfix">
     	    ${mo_monitortollDetaillist3}
     	    <c:forEach items="${mo_monitortollDetaillist3}" var="val" varStatus="i">
     	    	 <div class="fixgz_newbox">
     	    	 	 <div><img src="${val.ag_st_url}" width="200px"></div>
     	    	 	 <span>${val.bd_st_name}</span>
     	    	 	 <p>合同价：${val.bd_st_money}元</p>
     	    	 	 <p>面积：${val.bd_st_area}㎡</p>
     	    	 </div>
     	   	</c:forEach>
     	</div>
     	<div class="dsignmore" style="">
         	<span class="glyphicon glyphicon-arrow-down"></span>
         	滑动鼠标查看更多
         </div>
        	 
        </div>
         
         
         <div style="height: 30px;"></div>
     </div>
</div>
<%@ include file="foot.jsp"%>