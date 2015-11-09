<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ include file="top.jsp"%>
<div class="dsignbox" style="">
	 	<div class="cheap_road" style="">
	 	  	  <ul class="breadcrumb" style="">
                   <li><a href="index">首页</a></li>
                   <li><a>我要检测</a></li>
              </ul>
	 	  </div>
          <div class="dsignboxtop" style="">
	 	  	   您好，免费检测只针对我们我要验房APP平台上消费的用户，没有通过此平台消费的用户将不能享受免费检测服务。 
	 	  </div>
          <div class="dsignboxin clearfix" style="">
          	<div>区域</div>
          	 <ul>
          	 	<li>全部</li>
          	 	<c:forEach items="${district_list}" var="val" varStatus="">
          	 	<li>${val.d_name}</li>
          	 	</c:forEach>
          	 	<li>其他</li>
          	 </ul>
          </div>
       <!--综合排序-->
       <div class="dsignboxin clearfix" style="">
          	 <ul>
          	 	<li>综合排序<span class="glyphicon glyphicon-arrow-down"></span></li>
          	 	<li>口碑排序<span class="glyphicon glyphicon-arrow-down"></span></li>
          	 	<li>接单排序<span class="glyphicon glyphicon-arrow-down"></span></li>
          	 	<li>价格由高到低<span class="glyphicon glyphicon-arrow-down"></span></li>
          	 	<li>价格由低到高<span class="glyphicon glyphicon-arrow-down"></span></li>
          	 </ul>
          	<!--  <div style="margin-left: -80px;">共88家治理师</div> -->
          </div>
       <div class="fixgz_inbox dessty_inbox">
       <c:forEach items="${mo_monitortolllist}" var="val" varStatus="i">
    	   <div class=" clearfix fixgz_inbox_pepole dessty_inbox_pepole">
    	       <div class="tuijie_pepole_pic"><img src="${val.ag_st_url}" width="140px" height="140px"></div>
    	       <span class="tuijie_pepole_name">${val.ae_st_name}</span>  	
    	       <c:forEach begin="0" end="${val.ba_st_grade}">
    	       	<span class="dessty_instar"><span class="glyphicon glyphicon-star"></span></span>
			   </c:forEach>
    	       <div class="dessty_ingood"><strong class="redcolor">RMB：${val.ba_st_price}元/平米</strong><span class="airnum" style="margin-left: 15px;">接单数：${val.jdnum}</span></div>
    	       <div class="tuijie_pepole_check">
	    	       <a href="index/monitortollDetail?id=${val.ae_st_id}&&sid=${sid}">预约</a>
    	       </div>
    	  </div>
       </c:forEach>
    	 </div>
          <!--滑动鼠标查看更多-->
         <div class="dsignmore" style="">
         	<i class="glyphicon glyphicon-arrow-down"></i>滑动鼠标查看更多
         </div>
         <div class="" style="height: 30px;"></div>
  </div>
  <div class="check_out" style="display: block;">
  	    <div class="check_outtop clearfix" style="display: block;">
  	    	<div class="check_otleft" style="">预约提示</div>
  	    	<div class="close closeout" onclick="myclose(this);"><span class="glyphicon glyphicon-remove" style="color: white;"></span></div>
  	    </div>
  	  <!--  <span class="glyphicon glyphicon-ok-sign check_outfont"></span>-->
  	    <div class="check_outpoint" style="text-align: left;padding-left: 20px;margin-top: 20px;width: 100%;">
  	    	<h4>您好!</h4>为了您的房屋装修质量更完美，申请装修服务后我们将免费为您提供免费检测服务。<br>如果您未申请装修服务，您也可以预约收费检测<span class="redcolor">
  	    		</span>，我们将安排专业人员检测您的房屋。</div>
  	    <div class="baoming_bu check_ob">		
				<input value="我知道了" onclick="checkok()" type="button">
				<a href="index/loginp">去登陆</a>
		</div>
  </div>
</div>
<%@ include file="foot.jsp"%>