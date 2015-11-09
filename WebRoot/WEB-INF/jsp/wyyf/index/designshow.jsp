<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="top.jsp"%>
<div class="dsigback" >
	 <div class="dsignbox" style="">
	 	<div class="cheap_road"style="">
	 	  	  <ul class="breadcrumb" style="">
                   <li ><a href="index.html">首页</a></li>
                   <li ><a href="">我要设计</a></li>
						<li ><a href="index/designstyle">个性设计 </a></li>
				<li class="active"><a href="">预约</a></li>
              </ul>
	 	  </div>
        <div class="designshow_boxtop clearfix"style="">
        	<div class="designshow_boxtop_left"  style=""><img src="${data.ag_st_url}"style="width: 234px;height: 183px"></div>
        	<div class="designshow_boxtop_right" style="">
        		<span class="designshow_tr_name"style="">${data.ae_st_name}</span>
        		<span class="designshow_tr_star"style="" > <c:forEach begin="0" end="${data.ba_st_grade}">
										<span class="glyphicon glyphicon-star"></span>
									</c:forEach></span>
        	    <div>年龄：${data.ae_nm_age }岁</div>
        	    <div>入住时间：${data.bo_dt_updDate }</div>
        	    <div>作品数量：${data.num } 件</div>
        	    <div>个性宣言:${data.ae_st_intro }</div>
        	    <%-- <div class="desin_show_check"><a href="/index/toDesign?type=<%=type%>&id=${id}">预约设计师</a></div> --%>
        	    <%-- <div class="desin_show_check"><a href="/index/toDesign?type=${type}&id=${id}">预约设计师</a></div> --%>
        	    <%-- <a href="index/toDesign?id=${val.ae_st_id}&&sid=${sid}">预约他装修</a> --%>
        	    <div class="desin_show_check"><a href="javascript:checkLoginUser('${id}','${sid }');">预约</a></div>
        	</div>
        </div>
        <div class="designshow_boxcon" style="">代表作品</div>
        <div class="designshow_boxbottom clearfix" style="">
        <c:forEach items="${data1}" var="val" varStatus="i" >
        	 <div class="designshow_btbox ">
                  <div class="designshow_btbox_pic">
                     <div><img src="${val.ag_st_url }" width="315" height="177px"></div>
                    <div class="designshow_btbox_desci">${val.bd_st_name }</div>
                  </div>
              </div>
		</c:forEach>
		 
        </div>
        <!--  <div class="dsignmore"style="">
         	<span class="glyphicon glyphicon-arrow-down"></span>
         	滑动鼠标查看更多
         </div> -->
     </div>
</div>
<script>
function checkLoginUser(rid,sid){

	var oldRequestUrl=window.location.href;
	var userid="${sysuser.ae_st_id }";
	if(userid==null||userid==''){
		alert("当前未登陆请先登陆!");
		location.href="index/loginp?oldRequestUrl="+oldRequestUrl;
	}else{	
		/* location.href="index/workmessjsp?rid="+rid+"&sid="+sid; */
		location.href="index/toDesign?rid="+rid+"&sid="+sid;
	}
}
</script>
 
<%@ include file="foot.jsp"%>
