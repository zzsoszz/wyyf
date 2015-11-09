<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ include file="top.jsp"%>
<style>
	.video embed{
		width: 270px;
		height: 182px;
	}
</style>
<div class="f_wh100">
    <div class="mycontainer" style="width: 1000px;">
    <div class="user-col clear f_mt20">
        <!-- 第一部分 start --> 
			<div class="user-left-main f_fl">
                <div class="tab-title clear">
                    <a href="javascript:void(0)" class="on" >免费验房</a>
                    <a href="javascript:void(0)" class="last"  onclick="checkLoginUser()" >金牌验房</a>
                </div>
                <div class="tab-box">
                    <div class="list">
                     <form id="addform1" class="dkform" name="formbm" method="post"  action="index/addApplyMf"  >
                     <input type="hidden" name="type" value="2">
                     <input type="hidden"  name="bf_st_userid" value="${sysuser.ae_st_id }"> 
                        <h3>${mfyf.num}户业主的共同选择</h3>
                        <p><input style="outline: medium none;" class="noinput" placeholder="请输入你的姓名" type="text" id="bf_st_name" name="bf_st_name"></p>
                        <p><input class="noinput" placeholder="请输入有效电话号码" type="tel" id="bf_st_tell" name="bf_st_tell"></p>
                        <p><input style="outline: medium none;" class="noinput" placeholder="请输入你的楼盘" http://127.0.0.1/# type="text" id="bf_st_address" name="bf_st_address"></p>
                       <!--  <p><input class="subnitClass" type="button" value="立即报名" onclick="checkin1A()"></p> -->
                        <p><input class="subnitClass" type="submit" value="立即报名" id="addform1" ></p>
                      </form>
                    </div>
                    <div style="display: none;" class="list">
                    <form id="addform2" class="dkform" name="formbm" method="post"  action="index/addApplyjp"  enctype="multipart/form-data" >
                       <input type="hidden" name="type" value="3">
                        <input type="hidden"  name="bf_st_userid" value="${sysuser.ae_st_id }"> 
                        <h3>${jpyf.num }户业主的共同选择</h3>
                        <p><input class="noinput" placeholder="请输入你的姓名" type="text" id="bf_st_name" name="bf_st_name"></p>
                        <p><input class="noinput" placeholder="请输入你的联系电话" type="tel" id="bf_st_tell" name="bf_st_tell"></p>
                        <p><input class="noinput" placeholder="请输入你的面积" type="text" id="bf_st_area" name="bf_st_area"></p>
                        <p><input class="noinput" placeholder="请输入你的楼盘地址" type="text" http://127.0.0.1/# type="text" id="bf_st_address" name="bf_st_address"></p>
                        <p><input class="subnitClass" type="submit"  value="立即报名" id="addform2" hidden="${sysuser.ae_st_id}"></p>
                    </form>
                    
            <!-- script type="text/javascript" type="text/javascript">
				function checkin1A() {
					alert("11111111111");
					$("#addform1").form('submit',{
						url:"index/addApplyMf",
						onSubmit:function(param){},
						success:function(data){
							alert(data);
							 if(0==data){
								location.href="index/checkfree";
							}
							else {
								location.href="index/checkfree";
								alert("申请失败!"+"您输入的电话号码有误"); 
							} 
						}
					});
				} 
				function checkin1B() {
					$("#addform2").form('submit',{
						url:"index/addApply",
						onSubmit:function(param){
						},
						success:function(data){
							if(0==data){
								alert("申请成功");
								/* location.href="index"; */
								$("#zhifu").show();
							}else{
								alert("申请失败!"+"您输入的电话号码有误");
							}
						}
					});
				}
				/* var telid= $("#telid1").val();
				 	var telid=null;
					if(num==1){
						 telid= $("#telid1").val();
					}esle{
						 telid= $("#telid").val();
					}
				if(telid==""||telid==null){
					return alert("电话不能为空！");
				}
					$("#addForm1").form('submit', {
						url : "index/addApply",
						onSubmit : function(param) {
						},
						success : function(data) {
							if ("0" == data) {
								$("#telid1").val("");
						    	$("#telid").val("");
								alert("申请成功！");
							} else {
								alert("申请失败！");
							}
						}
					}); */
			</script> -->
                    </div>
                </div>
            </div>
            <!-- 广告栏图片 -->
            <div class="user-middle-main f_fl">
                <div class="banner">
                    <div class="row  index_carousel" style="width: 500px;height: 370px;">
                        <div class="carousel slide" id="mycarousel2" data-ride="carousel" data-interval="3000">
                            <ol class="carousel-indicators">
                             <c:forEach items="${main_centerlist1}" var="val" varStatus="i">
                             <li  <c:if test="${i.index == 0}" >class="active"</c:if> <c:if test="${i.index != 0}" >class=""</c:if> data-target="#mycarousel2" data-slide-to="0"></li>
                             </c:forEach>
                            </ol>
                            <div class="carousel-inner">
                                <c:forEach items="${main_centerlist1}" var="val" varStatus="i">
	                                <div class="item <c:if test="${i.index == 0}" >active</c:if>">
	                                    <a href="${val.bj_st_clickurl}"><img src="${val.ag_st_url}" alt="" title="" height="370" width="640"></a>
	                                    <%-- <img src="${val.ag_st_url}" alt="" title="" height="370" width="640"> --%>
	                                </div>
                                </c:forEach>
                            </div>
                            <a class="carousel-control left" data-target="#mycarousel2" data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left"></span>
                            </a>
                            <a class="carousel-control right" data-target="#mycarousel2" data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right"></span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="user-right-main f_fr div-left">
                <div class="title clear">
                    <strong class="f_fl">热点事件</strong>
                    <a href="http://www.wangzhong.com:8080/forum.php" target="_blank" class="f_fr">更多&gt;</a>
                </div>
                <div class="video">
                <!--     <img src="images/shipin_36.png" height="182" width="270"> -->
                	<c:if test="${ videoMap!=''}">
                	${videoMap.br_st_url }
                	</c:if>
                  <c:if test="${ videoMap==''}">
                	<img src="images/shipin_36.png" height="182" width="270">
                	</c:if>
                	
                </div> 
                 <iframe target="_blank"  frameborder=”no”  height="370" border=”0″ marginwidth=”0″ marginheight=”0″ scrolling=”no"  width="100%"   src="http://www.wangzhong.com:8080/forum_thread_newest.php"> </iframe> 
               <!--  <ul>
                    <li><a href="#">热点事件热点事件热点事件热事件热点事件...</a></li>
                    <li><a href="#">热点事件热点事件热点事件热事件热点事件....</a></li>
                    <li><a href="#">热点事件热点事件热点事件热事件热点事件....</a></li>
                    <li><a href="#">热点事件热点事件热点事件热事件热点事件....</a></li>
                    <li><a href="#">热点事件热点事件热点事件热事件热点事件....</a></li>
                    <li><a href="#">热点事件热点事件热点事件热事件热点事件....</a></li>
                </ul> -->
            </div>
       <div class="check_out" id="zhifu" style="display: none;">
  	    <div class="check_outtop clearfix" style="">
  	    	<div class="check_otleft" style="">报名成功</div>
  	    	<div class="close closeout" onclick="myclose(this)"><span class="glyphicon glyphicon-remove" style="color: white;"></span></div>
  	    </div>
  	    <span class="glyphicon glyphicon-ok-sign check_outfont"></span>
  	    <div class="check_outpoint">恭喜您成为第<span>${jpyf.num}+1</span>号金牌验房用户。我们会在24小时内为您受理，请注意接听电话。</div>
  	    <div class="baoming_bu check_ob">		
				<input id="zf" value="支付" type="button">
		</div>
		<script type="text/javascript">
			$(function() {
				$("#zf").click(function() {
				/* 	payfor(); */
					toPay();
				});
				$('.lxftime').each(function (){
			        var endTime=new Date(Date.parse($(this).attr('endtime'))).getTime() ;
			        var beginTime=new Date(Date.parse($(this).attr('begintime'))).getTime() ;
			        var nowTime=new Date(Date.parse($(this).attr('nowtime'))).getTime() ;

			        $(this).attr('endtime',endTime);
			        $(this).attr('begintime',beginTime);
			        $(this).attr('nowtime',nowTime);
			    })

			    setInterval(lxfEndtime,1000);
			});
	
			function toPay(){
				var address=$("#bf_st_address").val();
				var area=$("#bf_st_area").val();
				var money=parseFloat(area);
				location.href="index/toPay?money="+money+"&address="+address+"&area="+area;
			}
		</script>
  </div>
        </div>
        <!-- // 第一部分 end -->
        <!-- 验房师傅-->
        <div class="promot-col f_mt50">
            <div class="module-title clear">
                <div class="tab clear f_fl">
                    <span class="on">验房师傅</span>
                    <!-- <img src="images/business/user/284d0030-97b3-4d81-a58c-62516970ee96.jpg"/> -->
                </div>
            </div>
            <div class="promot-box">
                <div class="slide slideGroup">
                    <div class="slideBox slideBox1">
                        <a class="sPrev" href="javascript:void(0)"><i class="glyphicon glyphicon-menu-left"></i></a>
                        <div class="tempWrap" style="overflow:hidden; position:relative; width:1000px">
                        <div class="tempWrap" style="overflow:hidden; position:relative; width:1250px">
                        <ul style="width: 4500px; position: relative; overflow: hidden; padding: 0px; margin: 0px; left: 0px;">
                        <c:forEach items="${yfsf}" var="val" varStatus="i">
                            <li style="float: left; width: 234px;" class="clone">
                                <div class="pic" style="width:234px;height:auto:overflow:hidden">
                                <%-- <a href="index/masterDetail?id=${val.ae_st_id}" target="_blank"> --%>
                                <a href="index/Verify?id=${val.ae_st_id}&sid=${sysuser.ae_st_id}" target="_blank">
                                <img src="${val.ag_st_url}" style="width:234px;height:234px;">
                                </a></div>
                                <div class="title1 " style="position: absolute;z-index:2;bottom:0px;left:8px;width:234px;height:70px;background: rgba(0,0,0,.5);filter:alpha(opacity=30);background: rgb(255,255,255)\9;opacity: .7\9;">
                                   <a href="index/Verify?id=${val.ae_st_id}&sid=${sysuser.ae_st_id}" target="_blank" style="color:white">
                                   <%--  <a href="index/masterDetail?id=${val.ae_st_id}" target="_blank"> --%>
                                        <div class="title_top clearfix"><span class="f_fl">${val.ae_st_name}</span><span class="f_fr yfmaster_font">
                                        <c:forEach begin="0" end="${ba_st_grade}"><i class="glyphicon glyphicon-star"></i></c:forEach></span>
                                        </div>
                                        <div class="title_bott"><strong class="recolor f_fl">接单数：${val.jdnum}</strong></div>
                                    </a>
                                </div>
                            </li>
                        </c:forEach>
                            </ul></div></div>
                        <a class="sNext" href="javascript:void(0)"><i class="glyphicon glyphicon-menu-right"></i></a>
                    </div>
                </div>
            </div>
            
            
            
            <%-- <div class="promot-box">
                <div class="slide slideGroup">
                    <div class="slideBox slideBox1">
                        <a class="sPrev" href="javascript:void(0)"><i class="glyphicon glyphicon-menu-left"></i></a>
                        <div class="tempWrap" style="overflow:hidden; position:relative; width:1000px">
                        <div class="tempWrap" style="overflow:hidden; position:relative; width:1250px">
                        <ul style="width: 4500px; position: relative; overflow: hidden; padding: 0px; margin: 0px; left: 35px;">
                        <c:forEach items="${yfsf}" var="val" varStatus="i">
                            <li style="float: left; width: 234px;" class="clone">
                                <div class="pic">
                                <a href="index/masterDetail?id=${val.ae_st_id}" target="_blank">
                                <a href="index/Verify?id=${val.ae_st_id}&sid=${sysuser.ae_st_id}" target="_blank">
                                <img src="${val.ag_st_url}" width="100px">
                                </a></div>
                                <div class="title">
                                   <a href="index/Verify?id=${val.ae_st_id}&sid=${sysuser.ae_st_id}" target="_blank">
                                    <a href="index/masterDetail?id=${val.ae_st_id}" target="_blank">
                                        <div class="title_top clearfix"><span class="f_fl">${val.ae_st_name}</span><span class="f_fr yfmaster_font">
                                        <c:forEach begin="0" end="${ba_st_grade}"><i class="glyphicon glyphicon-star"></i></c:forEach></span>
                                        </div>
                                        <div class="title_bott"><strong class="recolor f_fl">接单数：${val.jdnum}</strong></div>
                                    </a>
                                </div>
                            </li>
                        </c:forEach>
                            </ul></div></div>
                        <a class="sNext" href="javascript:void(0)"><i class="glyphicon glyphicon-menu-right"></i></a>
                    </div>
                </div>
            </div> --%>
        </div>
        <!-- 第四部分 找工长 找师傅 start -->
        <div class="find-col f_mt50">
            <div class="module-title clear">
                <div class="tab clear f_fl">
                    <span class="on">找工长</span>
                    <span>找师傅</span>
                </div>
                <div class="more f_fr">
                    <a href="index/overman">更多&gt;</a>
                </div>
            </div>
            <div class="find-box">
                <div class="box clear">
                    <div class="info f_fl">
                        <h3>企业精神</h3>
                        <p>
                            本网站的工长都是由网众科技签约认证，帮您新家省30%，在装修过程中拒绝恶意的增项，漏项，选择了平台的工长将会为您提供第三方的监理和
                            空气质量检测，让您无忧装新家，放心住新家，网众科技在此感谢您选择了此平台，投诉热线:</p><p>400-028-5998.</p>
                        <p></p>
                    </div>
                    <div class="list f_fr">
                     <c:forEach items="${main_centerlist2}" var="val" varStatus="i">
                        <div class="table">
                            <img src="${val.ag_st_url}" alt="" height="235" width="235" >
                            <a href="index/overmanDetail?id=${val.ae_st_id}&sid=${sysuser.ae_st_id}" style="position: absolute;bottom: 12px;left: 7px;background: url('../../images/tiaotiao_41.png')no-repeat;color: white;line-height: 30px;padding: 5px 0px 0px 15px;text-align: left;width: 250px;height: 70px;background-size:234px 70px;">
                                  <p>${val.ae_st_name}<span class="list_ap_span" style="margin-left: 8%;;color: #F4990A;font-size: 18px;">
	                                  <c:forEach begin="0" end="${val.ba_st_grade}">
	                                 	 <i class="glyphicon glyphicon-star"></i>
	                                  </c:forEach></span></p>
                                  <p>接单数：${val.jdnum }</p>
                            </a>
                        </div>
                    </c:forEach>
                    </div>
                </div>
                <div style="display: none;" class="box  clear">
                    <div class="info f_fl">
                        <h3>企业精神</h3>
                        <p>
                            本网站的师傅都是由网众科技签约认证，帮您新家省30%，在装修过程中拒绝恶意的增项，漏项，选择了平台的师傅将会为您提供第三方的监理和
                            空气质量检测，让您无忧装新家，放心住新家，网众科技在此感谢您选择了此平台，投诉热线:</p><p>400-028-5998.</p>
                        <p></p>
                    </div>
                    <div class="list f_fr">
                    <c:forEach items="${main_centerlist3}" var="val" varStatus="i">
                       <div class="table">
                            <img src="${val.ag_st_url}" alt="" height="235" width="235">
                            <a href="index/masterDetail?id=${val.ae_st_id}" style="position: absolute;bottom: 12px;left: 7px;background: url('../../images/tiaotiao_41.png')no-repeat;color: white;line-height: 30px;padding: 5px 0px 0px 15px;text-align: left;width: 250px;height: 70px;background-size:234px 70px;">
                                  <p>${val.ae_st_name}<span class="list_ap_span" style="margin-left: 8%;;color: #F4990A;font-size: 18px;">
	                                  <c:forEach begin="0" end="${val.ba_st_grade}">
	                                 	 <i class="glyphicon glyphicon-star"></i>
	                                  </c:forEach></span></p>
                                  <p>接单数：${val.jdnum }</p>
                            </a>
                        </div>
                    </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <!-- // 第四部分 找师傅 end -->

        <!-- 监理监测 start -->
        <div class="control-col f_mt50">
            <div class="fix-module-title clear">
                <div class="tab clear f_fl">
                    <!--<span class="on">监理&检测</span>-->
                </div>
            </div>
            <div class="control-box clear">
                <a href="index/aircharge" class="f_db f_fl">
                <!-- <a href="index/surveygold" class="f_db f_fl"> -->
                    <img src="images/zhuangxiu_06.png"  height="254"  alt="" width="500">
                    <div class="info">
                        <h4>我要检测</h4>
                        <p>专业空气检测仪器把关，让您远离甲醛的危害</p>
                    </div>
                </a>
                <a href="index/supervision" class="f_db f_fr">
                <!-- <a href="index/aircharge" class="f_db f_fr"> -->
                    <img src="images/zhuangxiu_05.png" alt=""  width="500">
                    <div class="info">
                        <h4>我要监理</h4>
                        <p>专业监理人员把守，让装修中隐藏的问题现形</p>
                    </div>
                </a>
            </div>
        </div>
        <!-- // 监理监测 end -->

        <!-- 第二部分 我要特价 start -->
        <div class="promot-col f_mt50">

            <div class="module-title clear">
                <div class="tab clear f_fl">
                    <span class="on">我要特价</span>
                </div>
            </div>
            <div class="promot-box">

                <!-- tab start-->
                <div class="time-line">
                    <ul class="clear">
                        <li>
                            <span class="on">
                                正在抢购<br>
                                <i></i>
                            </span>
                        </li>
                        <li>
                            <span>
                                明日预告<br>
                               <i></i>
                            </span>
                        </li>
                        <li>
                            <a href="index/shopcheap">
                                <span>
                                   更多<br>
                                    <i></i>
                               </span>
                            </a>
                        </li>
                    </ul>
                </div>
                <!-- tab end -->

                <div class="slide slideGroup">
                    <div class="slideBox slideBox2">
                        <a class="sPrev" href="javascript:void(0)"><i class="glyphicon glyphicon-menu-left"></i></a>
<!--                         <div class="tempWrap" style="overflow:hidden; position:relative; width:1000px"><div class="tempWrap" style="overflow:hidden; position:relative; width:1000px">
 -->                        
                        <ul style="width: 3500px; position: relative; overflow: hidden; padding: 0px; margin: 0px; left: 0px;">

                        <c:forEach items="${zzqg}" var="val" varStatus="i">
                            <li style="float: left; width: 234px;" class="clone">
                                <div class="sPrev_time recolor lxftime" type="type2" shopid="${val.bg_st_id }" nowtime="${val.nowTime}" begintime="${val.bo_dt_startDate }" endtime="${val.bo_dt_endDate }"> </div> 
                                <div class="pic"><a href="index/shopmess?id=${val.bg_st_id }" target="_blank"><img src="${val.bg_st_img1 }"></a></div>
                                <div class="title">
                                    <a href="index/shopmess?id=${val.bg_st_id}" target="_blank">
                                        <div class="title_top clearfix"><span class="f_fl" style="width:160px;height:25px;overflow:hidden">${val.bg_st_name }</span><span class="f_fr">已抢${val.num }件</span></div>
                                        </a><div class="title_bott"><a href="index/shopmess?id=${val.bg_st_id}" target="_blank"><strong class="recolor f_fl">￥${val.bg_st_pricetj}件 </strong></a><a href="index/buy?id=${val.bg_st_id}" class="f_fr">立即抢</a></div>
                                </div>
                            </li>
                        </c:forEach>
                        </ul>
                       <!--  </div> </div> -->
                        <a class="sNext" href="javascript:void(0)"><i class="glyphicon glyphicon-menu-right"></i></a>
                    </div>
                    <div style="display: none;" class="slideBox slideBox2">
                        <a class="sPrev" href="javascript:void(0)"><i class="glyphicon glyphicon-menu-left"></i></a>
                        <!-- <div class="tempWrap" style="overflow:hidden; position:relative; width:1000px"><div class="tempWrap" style="overflow:hidden; position:relative; width:1000px">
                       -->
                        <ul style="width: 3500px; position: relative; overflow: hidden; padding: 0px; margin: 0px; left: 0px;">                      
                        <c:forEach items="${mryg}" var="val" varStatus="i">
                            <li style="float: left; width: 234px;" class="clone"> 
                                <div class="sPrev_time recolor lxftime" type="type2" shopid="${val.bg_st_id }" nowtime="${val.nowTime}" begintime="${val.bo_dt_startDate }" endtime="${val.bo_dt_endDate }"></div>  
                                <div class="pic"><a href="index/shopmess?id=${val.bg_st_id }" target="_blank"><img src="${val.bg_st_img1 }"></a></div>
                                <div class="title">
                                    <a href="index/shopmess?id=${val.bg_st_id}" target="_blank">
                                        <div class="title_top clearfix"><span class="f_fl">${val.bg_st_name }</span><span class="f_fr">已抢${val.num }件</span></div>
                                        </a><div class="title_bott"><a href="index/shopmess?id=${val.bg_st_id}" target="_blank"><strong class="recolor f_fl">￥${val.bg_st_pricetj}件 </strong></a><a href="index/buy?id=${val.bg_st_id}" class="f_fr">立即抢</a></div>
                                </div>
                            </li>
                        </c:forEach> 
                        </ul>
                        <!-- </div></div> -->
                        <a class="sNext" href="javascript:void(0)"><i class="glyphicon glyphicon-menu-right"></i></a>
                    </div>
                </div>

            </div>

        </div>
        <!-- // 第二部分 我要特价 end -->

        <!-- 第三部分 广告位 start -->
        <div class="ad-col f_mt50">
            <div id="temp6">
                <div class="JQ-content-box">
                    <ul style="top: -200px;" class="JQ-slide-content">
                    <%-- <c:forEach items="${main_centerlist4}" var="val" varStatus="i">
                        <li><a href=""><img alt="" title="" src="${val.ag_st_url}" height="100" width="1000"></a> </li>
                    </c:forEach> --%>
                      <li><a href=""><img alt="" title="" src="../images/banner1.png" height="100" width="1000"></a> </li>
                    
                     </ul>
                    <!-- <ul class="JQ-slide-nav">
                        <li class="">1</li>
                        <li class="">2</li>
                        <li class="on">3</li>
                    </ul> -->
                </div>
            </div>

        </div>
        <script>
            $(function(){

                //装修贷
                $("#temp6").Slide({
                    effect : "scroolY",
                    speed : "normal",
                    timer : 3000
                });
            });
        </script>
        <!-- 家具建材 start -->
        <div class="furnish-col f_mt50">
            <div class="fix-module-title clear">
                <div class="tab clear f_fl">
                    <span class="on">购物商城</span>
                </div>
                <div class="more f_fr">
                    <a href="index/shophome">更多&gt;</a>
                </div>
            </div>
            <div class="furnish-box">
                <dl class="clear">
                    <dt><a href="index/shopbulding?shoptype=building"><img src="images/deng_21.png" alt="" height="200" width="200"></a></dt>
                    <c:forEach items="${mjc}" var="val" varStatus="i">
	                    <dd>
	                        <a href="index/shopmess?id=${val.bg_st_id}" class="f_db clear">
	                            <p>
	                                <strong>建材热卖</strong><br>
	                                ${val.bg_st_name}
	                                </p>
	                            <img class="f_fr" src="${val.bg_st_img1}" alt="" height="128" width="200">
	                        </a>
	                    </dd>
                    </c:forEach>
                    
                </dl>
                
                <dl class="clear f_mt20">
                    <dt><a href="index/shopbulding?shoptype=electrical"><img src="images/deng_07.png" alt="" height="200" width="200"></a></dt>
                    <c:forEach items="${mjj}" var="val" varStatus="i">
	                    <dd>
	                        <a href="index/shopmess?id=${val.bg_st_id}" class="f_db clear">
	                            <p>
	                                <strong>家具热卖</strong><br>
	                                ${val.bg_st_name}
	                                </p>
	                            <img class="f_fr" src="${val.bg_st_img1}" alt="" height="128" width="200">
	                        </a>
	                    </dd>
                    </c:forEach>
                </dl>
            </div>
        </div>
        <!-- // 家具建材 end -->

        <!-- 装修学堂 start -->
        <div class="dec-col f_mt50">
            <div class="fix-module-title clear">
                <div class="tab clear f_fl">
                    <span class="on">论坛</span>
                </div>
                <div class="bbs f_fr">
                    <a href="http://www.wangzhong.com:8080/forum.php">进入网众论坛交流&gt;</a>
                </div>
            </div>
            <div class="dec-box clear">
                <div class="left f_fl clear clearfix">
                    <div class="list f_fl fix-margin">
                        <span class="f_db f_fl bg_2ab6a6"><em>验房回顾</em></span>
                       
                        <iframe   frameborder=”no” border=”0″ marginwidth=”0″ marginheight=”0″ scrolling=”no" width="820px" height="200px" src="http://www.wangzhong.com:8080/forum_thread_one.php"></iframe> 
                    </div>
                </div>
            </div>
        </div>
        <!-- // 装修学堂 end -->
    </div>
</div>
<script>
function checkLoginUser(){
	var oldRequestUrl=window.location.href;
	var userid="${sysuser.ae_st_id }";
	if(userid==null||userid==''){
		alert("当前未登陆请先登陆!");
		location.href="index/loginp?oldRequestUrl="+oldRequestUrl;
	}
}
</script>
<%@ include file="foot.jsp"%>