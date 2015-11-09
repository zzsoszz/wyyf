<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="jsplib.jsp"%>
<!DOCTYPE html>
<html  lang="zh-CN">

<head>
	<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
		<title></title>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/wap/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/wap/css/main.css"/>
</head>
<body style="background-color:#F0F0F0;">
	<div class="page">
		<nav class="index clearfix mess_nav nav_bg">
			<div class="mess_nav1" ><a href="wap/myshop" ><i class="glyphicon glyphicon-menu-left" style="color: white"></i></a></div>
			<div class="mess_nav2" >抢相因</div>
			<!-- <div class="mess_nav1 index_share" onclick="tkopeng(this,121)"><i class="iconfont icon-fenxiang"></i></div> -->
		</nav>
		
		 <div class="jumbotron" style="">
		            <div class="carousel slide" data-ride="carousel">
		                <ol class="carousel-indicators">
		                    <li data-target="#myjm" data-slide-to="0" class="active"></li>
		                    <li data-target="#myjm" data-slide-to="1"></li>
		                    <li data-target="#myjm" data-slide-to="2"></li>
		                </ol>
		                <div class="carousel-inner" role="listbox">
		                    <div class="item active">
		                        <img src="${ctx}/images/wap/images/temai.jpg" alt="" width="100%">
		                    </div>
		                    <div class="item">
		                        <img src="${ctx}/images/wap/images/temai.jpg" alt="" width="100%">
		                    </div>
		                    <div class="item">
		                        <img src="${ctx}/images/wap/images/temai.jpg" alt="" width="100%">
		                    </div>
		                </div>
		            </div>
                </div>
                
                <section class="ch_body_big">
                	
                <!--轮播结束--></section>

				<c:forEach items="${cheaplist}" var="val" varStatus="i">
					<section class="ch_body" >
					<%-- ${val.bo_dt_startDate} --%>
	               		<div class="ch_body_1" shopId="${val.bg_st_id}" begintime="${val.bo_dt_startDate }" nowtime="${val.time }" endtime="${val.bo_dt_endDate }"></div>
	               		<div class="ch_body_2">
	               			<section class="sp_prodoct shop_product ch_product clearfix">
	               				<div class="col-sm-4 col-xs-4 col-md-4 col-lg-4"><img class="img-responsive" src="${val.bg_st_img1}" /></div>
	               				<div class="col-sm-8 col-xs-8 col-md-8 col-lg-8 shop_text clearfix">
	               					<p>${val.bg_st_summary}</p>
	               					<p>￥${val.bp_st_spprice} <del>￥${val.bg_st_pricezg}</del></P>				
	               					<a href="wap/goodsProduct?pkid=${val.bg_st_id}" class="btn btn-primary btn-xs" style="padding:8px 10px;;">马上抢</a>			
	               					</div>
	               				</section>
	               		</div>
               		</section>
          	 	</c:forEach>
				
	</div>
	<div class="tankuang " ></div>
	<div class="zhezhao" ></div>
</body>
<script src="${ctx}/js/wap/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/wap/js/bootstrap.min.js"> </script>
<script src="${ctx}/js/wap/js/pmain.js"></script>
<script>

(function (){

    $('.ch_body_1').each(function (){
        var endTime=new Date(Date.parse($(this).attr('endtime'))).getTime() ;
        var beginTime=new Date(Date.parse($(this).attr('begintime'))).getTime() ;
        var nowTime=new Date(Date.parse($(this).attr('nowtime'))).getTime() ;

        $(this).attr('endtime',endTime);
        $(this).attr('begintime',beginTime);
        $(this).attr('nowtime',nowTime);
    })

    setInterval(lxfEndtime,1000);
})();
 function lxfEndtime(){
	 
	$(".ch_body_1").each(function(){
		
		mathTime($(this));
	});
};
function formateTime(time){
	var timeRefer=[86700,3600,60,1];
	var ret=[];
	var len=timeRefer.length;
	var sec=Math.round(time/1000);
	
	for(var i=0;i<len;i++)
	{
		ret[i]=Math.floor(sec/timeRefer[i]);

		sec-=ret[i]*timeRefer[i];

	}

	return ret;
}

function mathTime(obj){
	
	var endTime=$(obj).attr('endtime');
	var beginTime=$(obj).attr('begintime');
	var nowTime=$(obj).attr('nowtime');
	$(obj).attr('nowtime',parseInt(nowTime)+1000);

	if(nowTime<beginTime)
	{
		var time=formateTime(beginTime-nowTime);
	
		$(obj).html("开抢倒计时"+time[0]+":"+time[1]+":"+time[2]+":"+time[3])
		$(obj).siblings(".ch_body_2").find("a").attr("href","javascript:void(0)");
		$(obj).siblings(".ch_body_2 ").find("a button").html("马上抢");
		
		
		/*  */
		

	}else if(nowTime>=beginTime && nowTime<endTime)
	{
		var time=formateTime(endTime-nowTime);
	
			$(obj).html("倒计时"+time[0]+":"+time[1]+":"+time[2]+":"+time[3])
			$(obj).siblings(".ch_body_2").find("a").attr("href","wap/goodsProduct?pkid="+$(obj).attr("shopId"));

	}else if(nowTime>=endTime)
	{

			$(obj).html("已结束00:00:00");
			$(obj).css("background-color","grey")
			$(obj).siblings(".ch_body_2").find("a").attr("href","javascript:void(0)");
			$(obj).siblings(".ch_body_2 a").find("button").html("已结束");
			//如果结束日期小于当前日期就提示过期啦
			
	}

}
 
 
 
 
 
</script>




</html>