<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ include file="top.jsp"%>
<div class="dsigback" style="">
	 <div class="dsignbox" style="">
	 	<div class="blank" style=""></div>
	 	        <ul class="breadcrumb" style="text-align: left;">
                   <li><a href="">网众科技</a></li>
                   <li class="active"><a href="index/overmanDetail">验房师详情</a></li>
                   <li><a>预约验房师</a></li>  
              </ul>
      
	 	  <div class="dsignboxcon" style="">
	 	  
	 	  	 <form id="addform" name="formbm" method="post" action="#">
                 <div class="baoming_in">
                     <span class="baoming_span1">姓名</span>:<input type="text" name="bf_st_name">
                 </div>
                 <div class="baoming_in">
                     <span class="baoming_span1">电话</span>:<input class="noinput" placeholder="请输入有效电话号码" type="tel" name="bf_st_tell">
                 </div>
                <!--  <div class="baoming_input baoming_input1">
                     <span class="baoming_span1">楼盘</span>:<input name="bf_st_incity" value="1" type="radio">绕城内<input name="bf_st_incity" value="2" type="radio">绕城外
                 </div> -->
                 <div class="baoming_in">
                     <span class="baoming_span1">地址</span>:<input type="text" name="bf_st_address"  id="bf_st_address">
                 </div>
                 <div class="baoming_in">
                     <span class="baoming_span1">房屋面积</span>:<input style="outline: medium none;" type="text" name="bf_st_area"  id="bf_st_area">
                 </div>
                 <div class="baoming_in">
                     <span class="baoming_span1">预约时间</span>:<input type="date" name="bf_dt_time">
                 </div>
                 <div class="baoming_in">
                     <span class="baoming_span1" style="margin-top: 20px;vertical-align: top;">备注</span><textarea style="width: 220px;margin-top: 20px;resize: none;" placeholder="请您输入200字以内" name="bf_st_remark"></textarea>
                 </div>
			     <div class="baoming_bu">		
					 <input type="hidden" name="type" value="7"  id="type"/>     <!-- 预约类型    -->
				     <input type="hidden"  name="rid" value="${rid}"  id="rid"/><!-- 赴约人ID -->
				     <input type="hidden"  name="sid" value="${sid}"  id="sid"/><!-- 申请人ID -->
			         <input value="预约" onclick="gzcheck()" type="button">
			     </div>
	      </form>
	   <script type="text/javascript" type="text/javascript">
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
  	    	<div class="close closeout" onclick="myclose();"><span class="glyphicon glyphicon-remove" style="color: white;"></span></div>
  	    </div>
  	    <span class="glyphicon glyphicon-ok-sign check_outfont"></span>
  	    <div class="check_outpoint">您好，您已成功预约工长，请等待审核。工作人员会在24小时内与您取得联系，请注意接听电话。</div>
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

