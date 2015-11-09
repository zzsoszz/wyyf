<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:include page="top.jsp" />
<div class="dsigback" style="">
	 <div class="dsignbox" style="">
	 	<div class="cheap_road" style="">
	 	  	  <ul class="breadcrumb" style="">
                   <li><a href="index">首页</a></li>
                   <li><a href="index">我要设计</a></li>
                   <li class="active"><a href="index/workmessjsp">免费设计</a></li>
              </ul>
	 	  </div>
	 	  <div class="dsignboxcon" style="width:540px;margin-left:120px;">
	 	  	 <form class="dkform" name="formbm" method="post" id="dkform">
	 	  	 <input type="hidden"  name="bf_st_type" value="4">
                 <div class="baoming_in">
                     <span class="baoming_span1">姓名</span>:<input type="text" name="bf_st_name">
                 </div>
                 <div class="baoming_in">
                     <span class="baoming_span1">电话</span>:<input type="tel"  name="bf_st_tell">
                 </div>
                 <!-- <div class="baoming_input baoming_input1">
                     <span class="baoming_span1">楼盘</span>:<input name="local" value="1" type="radio">绕城内<input name="local" value="2" type="radio">绕城外
                 </div> -->
                <!--  <div class="baoming_in">
                     <span class="baoming_span1">装修风格</span>:<input type="text">
                 </div> -->
                 <div class="baoming_in">
                     <span class="baoming_span1">楼盘</span>:<input type="text"  name="bf_st_address"  id="bf_st_address">
                 </div>

                 <div class="baoming_input clearfix baoming_input1">
                     <span class="dk_hkb2 baoming_span1" style="">户型</span>
                     <div class="dk_hkb_pic pic_unload" style="margin-left: 20px;">
			               	    <span class="uploadImg" style="font-size:12px; overflow:hidden; position:absolute">
                                    <input class="file" size="1" style="position:absolute; z-index:100; margin-left:-180px; font-size:60px;opacity:0;filter:alpha(opacity=0); margin-top:-5px;" type="file">
                                    <a href="#" style="width: 90px;height: 90px;display: block;">
                                        <img src="images/q_11.png" height="90" width="90">
                                    </a>
                                </span>
                     </div>
                     <div class="dk_hkb_pic pic_unload" style="margin-left: 15px;">
			              	    <span class="uploadImg" style="font-size:12px; overflow:hidden; position:absolute">
                                    <input class="file" size="1" style="position:absolute; z-index:100; margin-left:-180px; font-size:60px;opacity:0;filter:alpha(opacity=0); margin-top:-5px;" type="file">
                                    <a href="#" style="width: 90px;height: 90px;display: block;">
                                        <img src="images/q_11.png" height="90" width="90">
                                    </a>
                                </span>
                     </div>
                     <div class="dk_hkb_pic pic_unload" style="margin-left: 15px;">
			              	    <span class="uploadImg" style="font-size:12px; overflow:hidden; position:absolute">
                                    <input class="file" size="1" style="position:absolute; z-index:100; margin-left:-180px; font-size:60px;opacity:0;filter:alpha(opacity=0); margin-top:-5px;" type="file">
                                    <a href="#" style="width: 90px;height: 90px;display: block;">
                                        <img src="images/q_11.png" height="90" width="90">
                                    </a>
                                </span>
                     </div>
                 </div>
                 <div class="baoming_in">
                     <span class="baoming_span1">面积</span>:<input type="text"  name="bf_st_area"  id="bf_st_area">
                 </div>
                 <div class="baoming_in">
                     <span class="baoming_span1">预约时间</span>:<input  class="laydate-icon" name="bf_dt_time">
                 </div>
			     <div class="baoming_in">
			        	<span class="baoming_span1" style="margin-top: 20px;margin-left:33px;;vertical-align: top;">居家需求</span>
			        	<textarea  name="bf_st_remark" style="width: 220px;height:100px;margin-top: 20px;resize: none;" placeholder="请您输入200字以内,例如装修风格，居住人口，项目需求，个人想法等"></textarea>
			     </div>
					   <p class="redcolor" style="padding-left:120px;">备注：1.精装房只接受金牌验房 2.验房时请带上户型图 3.通过APP赶紧分享给小伙伴有米奇币.</p>
					   <div class="baoming_bu">
					   <input type="hidden" name="type" value="4"  id="type"/><!-- 申请类型 -->
					   <input type="hidden" name="rid" value="${rid}"  id="rid"/><!--赴约人ID -->
					   <input type="hidden" name="sid" value="${sid}"  id="sid"/><!--申请人ID -->
					  </div>
			     <div class="baoming_bu">		
				        <input value="预约" onclick="toSubmit()" type="button" style="margin-left:130px;">
			     </div>
	           </form>	   
	 	  </div>
	 </div>
</div>
<script>
$(function(){
	$(".laydate-icon").bind('click',laydate);
})
	function toSubmit(){
		var oldRequestUrl=window.location.href;
    	var userid="${sysuser.ae_st_id }";
    	if(userid==null||userid==''){
    		alert("当前未登陆请先登陆!");
    		location.href="index/loginp?oldRequestUrl="+oldRequestUrl;
    	}else{
    		$("#dkform").form('submit',{
    			url:"index/addApply",
    			onSubmit:function(param){
    			},
    			success:function(data){
    				if(0==data){
    					$("#okcheck").show();
    					//location.href="index/doJudge";
    				}else{
    					alert("提交失败");
    				}
    			}
    		});
    	}
		
	}
</script>

<div style="display: none;" class="check_out" id="okcheck">
  	    <div class="check_outtop clearfix" style="display: block;">
  	    	<div class="check_otleft" style="">预约成功</div>
  	    	<div class="close closeout" onclick="myclose();"><span class="glyphicon glyphicon-remove" style="color: white;"></span></div>
  	    </div>
  	    <span class="glyphicon glyphicon-ok-sign check_outfont"></span>
  	    <div class="check_outpoint">您好，您已成功预约户型设计，请等待审核。工作人员会在24小时内与您取得联系，请注意接听电话。</div>
  	    <div class="baoming_bu check_ob">		
				<a href="index"><input  value="确定"  onclick="toPay();"  type="button"></a>
		</div>
		 <script type="text/javascript">
			function fh() {
				location.href="index";
			}
			function toPay(){
				location.herf="index/wyyf/index";
				/* var type=$("#type").val();
				var rid=$("#rid").val();
				var address=$("#bf_st_address").val();
				var area=$("#bf_st_area").val();
				var money=parseFloat(area);
				location.href="index/toPay?money="+money+"&address="+address+"&area="+area+"&type="+type+"&rid="+rid; */
			}
		</script> 
</div>

<jsp:include page="foot.jsp" />





