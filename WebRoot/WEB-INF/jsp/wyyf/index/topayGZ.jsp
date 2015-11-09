<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="top.jsp"%>
<div class="payfor_con">

	 <div class="payfor_box" style="">
	 	<input type="hidden" id="orderId" value="${orderId}"/>
	 	<div class="parfor_top clearfix" style="">
	 		 <ul>
	 		 	<%-- <li>收货地址: ${addr}</li>
	 		 	<li>待支付金额：<strong class="redcolor" style="">￥${price }</strong></li> --%>
	 		 	<li>项目：${addr}</li>
	 		 	<li>${area}㎡</li>
	 		 	<li>待支付金额：<strong class="redcolor"style="">￥${price}</strong></li>
	 		 </ul>
	 	</div>
	 	<div class="payfor_item" style="">选择支付方式</div>
	 	<div class="payfor_way" style="">第三方支付</div>
	 	<div class="payfor_choose clearfix">
	 		<ul>
	 			<li><input name="way" checked value="1" type="radio"><span><img src="images/qq_14.png"></span></li>
	 			<li><input name="way" value="2" type="radio"><span><img src="images/qq_16.png"></span></li>
	 		</ul>
	 	</div>
	 	<div class="payfor_way" style="">银联在线支付（支持快捷支付，无需开通网银）</div>
	 	<div class="payfor_choose clearfix" style="margin-bottom: 230px;">
	 		<ul>
	 			<li><input name="way" value="3" type="radio"><span><img src="images/qqqq_47.png"></span></li>
	 		</ul> 		
	 	</div>
	 	<div class="payfor_bottom clearfix" style="">
	 		 <div style="display: none;"><span>使用米奇币</span><input type="text">个<span class="redcolor">-xx.xx元</span></div>
	 		<div>实际支付金额：<strong class="redcolor">￥${price}</strong></div>
	 		<a href="javascript:pay()"><div class="qrzf">确认支付 </div></a>
	 	</div>
	 <!-- =========================预约工长可享受免费服务============================================== -->	
	 	<form id="addform" name="formbm" method="post" action="index/addApplyGZ">
			<div class="check_out" id="gzcheck" style="display: block">
	  	    <div class="check_outtop clearfix" >
	  	    	<div class="check_otleft"style=""></div>
	  	    	<div class="close closeout" onclick="myclose(this)"><span class="glyphicon glyphicon-remove"style="color: white;"></span></div>
	  	    </div>
	  	    <div class="check_outpoint"style="padding-top: 20px;">
	            <p>若完成工长预约并成功支付，您还可以选择以下免费服务:</p>
	        </div>
	        <div class="check_outpoint"style="padding-top: 10px;">
	            <input name="mfjl" type="checkbox" value="0">免费监理
	            <input name="jcjq" type="checkbox" value="1">免费检测甲醛
	        </div>
	  	    <div class="baoming_bu check_ob">	
	  	    	<input type="hidden" name="addr" value="${addr}"/>	
	  	    	<input type="hidden" name="area" value="${area}"/>	
	  	    	<input type="hidden" name="price" value="${price}"/>	
				<input type="submit" value="确认" />
			</div>
		    </div>
	    </form>
	    
	    <!-- <script type="text/javascript">
		   
	  	    function goApplyGZ(){
			$("#addform").form('submit', {
					url : "index/addApplyGZ",
					onSubmit : function(param) {
					},
					success : function(data) {
						if ("0" == data) {
							//alert("申请成功！");
							//$("#addform").style.display="none"
						} else {
							//alert("申请失败！");
						}
					} 
				});
			}
	    </script> -->
	    
 </div>
</div>

<script type="text/javascript" src="js/eshop/pay.js?v1.1"></script>
<%@ include file="foot.jsp"%>