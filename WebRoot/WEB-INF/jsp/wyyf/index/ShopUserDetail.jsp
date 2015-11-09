<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ include file="top.jsp"%>
<%
String type=(String)request.getAttribute("type");
 %>
 
 
 
<div class="dsigback">
	 <div class="dsignbox" style="">
         <div class="cheap_road" style="">
	 	  	  <ul class="breadcrumb" style="">
                   <li><a href="index">首页</a></li>
                   <%if(type!=null&&type.equals("jianli")){%>
                   	<li><a href="index/supervision?type=jianli">我要监理</a></li>
                   	<li><a href="index/supervision?type=jianli">监理详情</a></li>
                   <%}else if(type!=null&&type.equals("jiance")){ %>
                   	<li><a href="index/supervision?type=jiance">我要检测</a></li>
                   	<li><a href="index/supervision?type=jiance">检测详情</a></li>
                   <%} %>
                   <!-- <li class="active"><a>师傅详情</a></li> -->
              </ul>
	 	  </div>
	 	<div class="designshow_boxtop clearfix" style="">
        	<c:forEach items="${ma_masterDetaillist1}" var="val" varStatus="i">
        	<div class="designshow_boxtop_left" style=""><img src="${val.ag_st_url}" width="140px" height="140px"></div>
        	<div class="designshow_boxtop_right" style="">
        	   <p class="clearfix">
        		<span class="designshow_tr_name" style="float:left">${val.ae_st_name}</span>
        	   <c:forEach begin="0" end="${val.ba_st_grade}">
    	      	 <span class="dessty_instar" style="float:left">
					<span class="glyphicon glyphicon-star"></span>
					</span>
			   </c:forEach>
			   </p>
        	    <div>从业年限：${year}年</div>
        	    <div>接单次数：${val.jdnum}</div>
        	    <div>RMB：${val.ba_st_price}元/平米</div>
        	    <!-- div>投诉纷争数：0</div><!-- index/masterOrder?rid=${val.ae_st_id}&&sid=${sid} --> 
        	    <%if(type!=null&&type.equals("jianli")){%>
                   	<div class="desin_show_check"><a href="index/ShopUserOrder?shopuserid=${val.ae_st_id}&type=${type}"   >预约</a></div>
                   <%}else if(type!=null&&type.equals("jiance")){ %>
                   <div class="desin_show_check"><a href="index/ShopUserOrder?shopuserid=${val.ae_st_id}&type=${type}"   >预约</a></div>
                   <%} %>
        	    
        	</div>
        </div>
        <div class="designshow_boxcon" style="">详细介绍</div>
        <div style="text-align: left;margin-bottom: 20px;">
        	${val.ba_st_team_intro}
        </div>
        	</c:forEach>
     <div class="designshow_boxbottom clearfix" style="">
     	<div class="fixgz_bott_left" style="">
     		<div class="fixgz_blt" style="">评价详情</div>
     		  <div id="temp5">
			     <div class="JQ-content-box">
			    	<ul class="JQ-slide-content">
					
						<!-- <div style=""> -->
						
						<c:forEach items="${ma_masterDetaillist2}" var="val" varStatus="i">
						<li>
							<span class="fixgz_name">业主:${val.ae_st_name}</span><br>
							<span class="fixgz_time">时间:${val.be_dt_addDate}</span><br>
							<span class="fixgz_pj">评价内容:${val.be_st_content}</span>
						</li>
						</c:forEach>

					
					
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
     		<div class="fixgz_brt">我的项目</div>
     	    <div class="fixgz_brtbox clearfix">
     	   
     	    <c:forEach items="${ma_masterDetaillist3}" var="val" varStatus="i">
     	    	 <div class="fixgz_newbox">
     	    	 	 <div><img src="${val.ag_st_url}" width="200px"></div>
     	    	 	 <span>${val.bd_st_name}</span>
     	    	 	 <p>合同价：${val.bd_st_money}元</p>
     	    	 	 <p>面积：${val.bd_st_area}㎡</p>
     	    	 	 <p>状态:${val.bd_st_remark}</p>
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
<script>
$(function(){
	$("#temp5").Slide({
		effect : "scroolTxt",
		speed : "normal",
		timer : 3000,
		steps:1
	});

});
function checkLoginUser(rid,sid){
	var oldRequestUrl=window.location.href;
	var userid="${sysuser.ae_st_id }";
	if(userid==null||userid==''){
		alert("当前未登陆请先登陆!");
		location.href="index/loginp?oldRequestUrl="+oldRequestUrl;
	}else{	
		location.href="index/masterOrder?rid="+rid+"&sid="+sid;
	}
}
</script>
<%@ include file="foot.jsp"%>