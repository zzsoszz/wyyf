<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ include file="top.jsp"%>
<div class="dsigback" style="">
	 <div class="dsignbox" style="">
	 	<div class="blank" style=""></div>
	 	        <ul class="breadcrumb" style="text-align: left;">
	 	            <li ><a href="index.html">首页</a></li>
                   <li ><a href="index">我要设计</a></li>
                   <li ><a href="index">个性设计 </a></li>
                   <li><a href="index/overmanDetail">预约</a></li>
                   <li  class="active"><a>预约个性设计师</a></li>  
                   
                   <!-- <li><a href="">首页</a></li>
                   <li class="active"><a href="index/overmanDetail">个性设计师详情</a></li>
                   <li><a>预约个性设计师</a></li>   -->
              </ul>
      
	 	  <div class="dsignboxcon" >
	 	  
	 	  	 <form id="addform" name="formbm" method="post" action="#">
                 <div class="baoming_in">
                     <span class="baoming_span1">姓名</span>:<input type="text" name="bf_st_name">
                 </div>
                 <div class="baoming_in">
                     <span class="baoming_span1">电话</span>:<input class="noinput" placeholder="请输入有效电话号码" type="tel" name="bf_st_tell">
                 </div>
                 <div class="baoming_in">
                     <span class="baoming_span1">楼盘</span>:<input type="text" name="bf_st_address" id="bf_st_address">
                 </div>
                 <div class="baoming_input clearfix" style="padding-top:8px;padding-left:65px;;height:105px;">
                       <span class="baoming_span1 f_fl" style="">户型图</span>:
                         <div class="dk_hkb_pic f_fl pic_unload" style="margin-left: 18px;">
								<span id="uploadImg" style="font-size: 12px; overflow: hidden; position: absolute">
									<input type="file" id="file" size="1" name="fileList[0].fileobj"
									style="position: absolute; z-index: 100; width:90px;height:90px;  font-size: 60px; opacity: 0; filter: alpha(opacity = 0); ">
									<a href="#" style="width: 90px; height: 90px; display: block;">
									  <img src="images/q_11.png" width="90" height="90">
									</a>
								</span>
						</div>
						<div class="dk_hkb_pic f_fl pic_unload" style="margin-left: 111px;">
								<span id="uploadImg"style="font-size: 12px; overflow: hidden; position: absolute">
									<input type="file" id="file" size="1" name="fileList[0].fileobj"
									style="position: absolute; z-index: 100; width:90px;height:90px;  font-size: 60px; opacity: 0; filter: alpha(opacity = 0); ">
									<a href="#" style="width: 90px; height: 90px; display: block;">
									  <img src="images/q_11.png" width="90" height="90">
									</a>
								</span>
						</div>
						<div class="dk_hkb_pic f_fl pic_unload" style="margin-left: 203px;">
								<span id="uploadImg"style="font-size: 12px; overflow: hidden; position: absolute">
									<input type="file" id="file" size="1" name="fileList[0].fileobj"
									style="position: absolute; z-index: 100; width:90px;height:90px;  font-size: 60px; opacity: 0; filter: alpha(opacity = 0); ">
									<a href="#" style="width: 90px; height: 90px; display: block;">
									  <img src="images/q_11.png" width="90" height="90">				
									</a>
								</span>
						</div>
							
							
                 
                 </div>
                 
                 
                 
                 
                 <div class="baoming_in">
                     <span class="baoming_span1">房屋面积</span>:<input style="outline: medium none;" type="text" name="bf_st_area"  id="bf_st_area">
                 </div>
                 <div class="baoming_in">
                     <span class="baoming_span1">预约时间</span>:<input  class="laydate-icon" name="bf_dt_time">
                 </div>
                 <div class="baoming_in">
                     <span class="baoming_span1" style="margin-top: 20px;vertical-align: top;">备注</span><textarea style="width: 220px;margin-top: 20px;resize: none;" placeholder="请您输入200字以内" name="bf_st_remark"></textarea>
                 </div>
			     <div class="baoming_bu">		
					 <input type="hidden" name="type" value="5"  id="type"/>     <!-- 预约类型    -->
				     <input type="hidden"  name="rid" value="${rid}"  id="rid"/><!-- 赴约人ID -->
				     <input type="hidden"  name="sid" value="${sid}"  id="sid"/><!-- 申请人ID -->
			         <input value="预约" onclick="gzcheck()" type="button">
			     </div>
	      </form>
	   <script type="text/javascript" type="text/javascript">
	   $(function(){
       	$(".laydate-icon").bind('click',laydate)

       })
				function gzcheck(){
				$("#addform").form('submit', {
						url : "index/addApply",
						onSubmit : function(param) {
						},
						success : function(data) {
							if ("0" == data) {
								//alert("申请成功！");
								$("#okcheck").show();
							} else {
								alert("申请失败！"+"电话号码错误");
							}
						}
					});
				}
		</script>
	 	  </div>
	 </div>
</div>
  <!-- 预约成功 -->
 <div style="display: none;" class="check_out" id="okcheck">
  	    <div class="check_outtop clearfix" style="display: block;">
  	    	<div class="check_otleft" style="">预约成功</div>
  	    	<div class="close closeout" onclick="myclose(this)"><span class="glyphicon glyphicon-remove" style="color: white;"></span></div>
  	    </div>
  	    <span class="glyphicon glyphicon-ok-sign check_outfont"></span>
  	    <div class="check_outpoint">您好，您已成功预约个性设计师，请等待审核。工作人员会在24小时内与您取得联系，请注意接听电话。</div>
  	    <div class="baoming_bu check_ob">		
				<input  type="button" value="点击付款" onclick="toPay()">
		</div>
		<script type="text/javascript">
			function fh() {
				location.href="index";
			}
			function toPay(){
				var type=$("#type").val();
				var rid=$("#rid").val();
				var address=$("#bf_st_address").val();
				var area=$("#bf_st_area").val();
				var money=parseFloat(area);
				location.href="index/toPay?money="+money+"&address="+address+"&area="+area+"&type="+type+"&rid="+rid;
			}
		</script>
</div>
<%@ include file="foot.jsp"%>

