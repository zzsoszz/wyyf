<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="top.jsp"%>
<div class="payfor_con">
<form action="index/aliPayTest">
		<input name="total_fee" id="total_fee" type="hidden" value="0.1" />
		<input name="out_trade_no" id="out_trade_no" type="hidden" value="1234578" />
	<div class="payBox" id="OnlinePayBox">
		<div class="title">支付宝支付</div>
		<div class="bankList">
			<div class="li">
				<input type="radio" checked name="payID" value="alipay"><img
					src="images/pay/logo/alipay.gif">
			</div>
			<div class="clear"></div>
		</div>
		<div class="title">选择网银支付</div>
		<div class="bankList" style="padding-bottom:0px;">
			<div class="li">
				<input type="radio" name="payID" value="ICBCB2C"><img
					src="images/pay/logo/bank_icbc.gif">
			</div>
			<div class="li">
				<input type="radio" name="payID" value="BOCB2C"><img
					src="images/pay/logo/bank_boc.gif">
			</div>
			<div class="li">
				<input type="radio" name="payID" value="CMB"><img
					src="images/pay/logo/bank_cmb.gif">
			</div>
			<div class="li">
				<input type="radio" name="payID" value="CCB"><img
					src="images/pay/logo/bank_ccb.gif">
			</div>
			<div class="li">
				<input type="radio" name="payID" value="ABC"><img
					src="images/pay/logo/bank_abc.gif">
			</div>
			<div class="li">
				<input type="radio" name="payID" value="COMM-DEBIT"><img
					src="images/pay/logo/bank_comm.gif">
			</div>
			<div class="li">
				<input type="radio" name="payID" value="POSTGC"><img
					src="images/pay/logo/bank_psbc.gif">
			</div>
			<div class="li">
				<input type="radio" name="payID" value="CMBC"><img
					src="images/pay/logo/bank_cmbc.gif">
			</div>
			<div class="clear"></div>
			<div class="otherBank"
				onclick="$('.otherBank').hide();$('#moreBankDiv').show();$('#otherBanksq').show();">↓选择其他银行</div>
		</div>
		<div class="bankList" id="moreBankDiv"
			style="display:none; padding-top:0px;">
			<div class="li">
				<input type="radio" name="payID" value="CIB"><img
					src="images/pay/logo/bank_cib.gif">
			</div>
			<div class="li">
				<input type="radio" name="payID" value="CITIC "><img
					src="images/pay/logo/bank_citic.gif">
			</div>
			<div class="li">
				<input type="radio" name="payID" value="BJBANK"><img
					src="images/pay/logo/beijing.gif">
			</div>
			<div class="li">
				<input type="radio" name="payID" value="CEBBANK"><img
					src="images/pay/logo/bank_ceb.gif">
			</div>
			<div class="li">
				<input type="radio" name="payID" value="CMBC"><img
					src="images/pay/logo/bank_hx.jpg">
			</div>
			<div class="li">
				<input type="radio" name="payID" value="SPABANK"><img
					src="images/pay/logo/bank_pingan.gif">
			</div>
			<div class="li">
				<input type="radio" name="payID" value="SPDB"><img
					src="images/pay/logo/bank_spdb.gif">
			</div>
			<div class="li">
				<input type="radio" name="payID" value="GDB"><img
					src="images/pay/logo/bank_cgb.gif">
			</div>
			<div class="li">
				<input type="radio" name="payID" value="BJRCB"><img
					src="images/pay/logo/BJRCB_OUT.gif">
			</div>
			<div class="li">
				<input type="radio" name="payID" value="NBBANK"><img
					src="images/pay/logo/NBBANK_OUT.gif">
			</div>
			<div class="clear"></div>
			<div class="otherBanksq"
				onclick="$('.otherBank').show();$('#moreBankDiv').hide();$('#otherBanksq').hide();">↑收起</div>
		</div>

	</div>
	<input type="submit" value="提交"/>
	</form>
</div>

	<script type="text/javascript" src="js/eshop/pay.js"></script>
<%@ include file="foot.jsp"%>