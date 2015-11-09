<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../top.jsp"%>
<div class="payfor_con">
	 <div class="payfor_box" style="">
	 	<div class="parfor_top clearfix" style="">
	 		 <ul>
	 		 	<li>收货地址：${addr}</li>
	 		 	<li>待支付金额：<strong class="redcolor" style="">￥ ${price}</strong></li>
	 		 </ul>
	 	</div>
	 	<div class="payfor_item" style="">选择支付方式</div>
	 	<div class="payfor_way" style="">第三方支付</div>
	 	<div class="payfor_choose clearfix">
	 		<ul>
	 			<li><input name="way" value="1" type="radio"><span><img src="images/qq_14.png"></span></li>
	 			<li><input name="way" value="2" type="radio"><span><img src="images/qq_16.png"></span></li>
	 		</ul>
	 	</div>
	 	<div class="payfor_way" style="">银联在线支付（支持快捷支付，无需开通网银）</div>
	 	<div class="payfor_choose clearfix" style="margin-bottom: 230px;">
	 		<ul>
	 			<li><input name="way" value="21" type="radio"><span><img src="images/qqqq_47.png"></span></li>
	 		</ul> 		
	 	</div>
	 	<div class="payfor_bottom clearfix" style="">
	 		 <!--  div style="display: none;"><span>使用米奇币</span><input type="text">个<span class="redcolor">-xx.xx元</span></div-->
	 		<div>实际支付金额：<strong class="redcolor">￥ ${price}</strong></div>
	 		<a href=""><div class="qrzf">确认支付 </div></a>
	 	</div>
 </div>
</div>
<%@ include file="../foot.jsp"%>