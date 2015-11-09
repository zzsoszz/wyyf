<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ include file="top.jsp"%>
<div class="dsigback">
	 <div class="dsignbox" style="">
         <div class="cheap_road" style="">
	 	  	  <ul class="breadcrumb" style="">
                   <li><a href="index">首页</a></li>
                   <li><a href="index/overman">我要装修</a></li>
                   <li><a href="index/master">找师傅</a></li>
                   <li class="active"><a>师傅详情</a></li>
              </ul>
	 	  </div>
	 	<div class="designshow_boxtop clearfix" style="min-height:250px;">
        	<c:forEach items="${ma_masterDetaillist1}" var="val" varStatus="i">
        	<div class="designshow_boxtop_left" style=""><img src="${val.ag_st_url}" width="180px" height="180px"></div>
        	<div class="designshow_boxtop_right" style="">
        	    <p class="clearfix">
        	           <span class="designshow_tr_name f_fl" style="">${val.ae_st_name}</span>
		        	   <c:forEach begin="0" end="${val.ba_st_grade}">
		    	      	 <span class="dessty_instar f_fl">
							<span class="glyphicon glyphicon-star"></span>
							</span>
					   </c:forEach>
        	    </p>
			    <div>工种：${val.typeName}</div>
        	    <div>从业年限：${val.ba_dt_addDate}</div>
        	    <div>接单次数：${val.jdnum}</div>
        	    <div>价格：${val.ba_st_price}</div>
        	    <div>预付定金：100</div>
        	    <div>投诉纷争数：0</div><!-- index/masterOrder?rid=${val.ae_st_id}&&sid=${sid} -->
        	    <div class="desin_show_check"><a href="javascript:checkLoginUser('${val.ae_st_id}','${sid}')"   >预约</a></div>
        	</div>
        </div>
        <div align="left"></div>
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
		      	   	    <textarea id="jd_content" name="jd_content" class="yjfk_yj" placeholder="为了保证网站的权威公正性，如果您是业主且已选择该工长为您服务，请先登录账户在评价！"></textarea>
		      	   </div>
		      	   <input type="radio" name="ok" class="fixgz_pjchoose" style="margin-left: 50px;margin-top: 10px;">表扬
		      	   <input type="radio" name="ok" class="fixgz_pjchoose" style="margin-left: 50px;">投诉
		      	   <div class="fixgz_fbpl" style="" onclick="toJudge();">发表评论</div>
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


function toJudge(){
	var oldRequestUrl=window.location.href;
	var userid="${sysuser.ae_st_id }";
	if(userid==null||userid==''){
		alert("当前未登陆请先登陆!");
		location.href="index/loginp?oldRequestUrl="+oldRequestUrl;
	}else{	
		var content=$("#jd_content").val();
		var rid="${rid}";
		var sid="${sysuser.ae_st_id }";
		$.post('index/doJudge',{content:content,rid:rid,sid:sid},
				function(data){
			if(data=='0'){
				alert("评论成功");
			}else{
				alert("系统错误");			
			}
			
		});
	}
}
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