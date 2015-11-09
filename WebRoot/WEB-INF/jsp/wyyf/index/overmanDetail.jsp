<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ include file="top.jsp"%>
<div class="dsigback">
	 <div class="dsignbox" style="">
         <div class="cheap_road" style="">
	 	  	  <ul class="breadcrumb" style="">
                   <li><a href="index">首页</a></li>
                   <li><a href="index/surveygold">我要装修</a></li>
                   <li class="active"><a>工长详情</a></li>
              </ul>
	 	  </div>
        <div class="designshow_boxtop clearfix" style="">
        	<c:forEach items="${ov_overmanDetaillist1}" var="val" varStatus="i">
        	<div class="designshow_boxtop_left" style=""><img src="${val.ag_st_url}" width="140px" height="140px"></div>
        	<div class="designshow_boxtop_right" style="">
			   <span class="tuijie_pepole_name">${val.ae_st_name}</span>  	
    	       <c:forEach begin="0" end="${val.ba_st_grade}">
    	       <span class="dessty_instar"style="float:none">
					<span class="glyphicon glyphicon-star"></span>
			  </span>
			   </c:forEach>
        	    <div>从业年限：${val.ba_dt_addDate}</div>
        	    <div>接单次数：${val.jdnum}</div>
        	    <%-- <div>价格：${val.ba_st_price}</div> --%>
        	    <div>本人接受网众科技第三方监督，零增项，不忽悠</div>
        	    <div>预付定金:100</div>
        	    <div>投诉纷争数:0</div>
        	    <%-- <div class="desin_show_check"><a href="index/overmanOrder?rid=${val.ae_st_id}&&sid=${sid}">预约他装修</a></div> --%>
        	    <div class="desin_show_check"><a href="javascript:checkLoginUser('${val.ae_st_id}','${sid}')">预约</a></div>
        	</div>
        </div>
       
        <div class="designshow_boxcon" style="">简介</div>
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
					
						<c:forEach items="${ov_overmanDetaillist2}" var="val" varStatus="i">
							<li>
							<span class="fixgz_name">业主:${val.ae_st_name}</span>
							<span class="fixgz_time">${val.be_dt_addDate}</span><br>
							<span class="fixgz_pj">${val.be_st_content}</span>
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
     	    <div class="fixgz_brtbox clearfix" >
	     	    <c:forEach items="${ov_overmanDetaillist3}" var="val" varStatus="i">
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
        		var content=$("jd_content").val();
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
		location.href="index/overmanOrder?rid="+rid+"&sid="+sid;
	}
}
</script>
<%@ include file="foot.jsp"%>