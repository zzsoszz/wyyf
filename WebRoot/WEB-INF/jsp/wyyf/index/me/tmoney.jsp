
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.lys.utils.pagination.PageBean"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ include file="../top.jsp"%>
<div class="dsigback" style="">
	<div class="dsignbox" style="">
		<div class="designshow_boxtop clearfix " style="text-align: left;">
		<form action="index/tmoneyEnd" method="post">
			<div class="tmoney_title"
				style="font-size: 17px; height: 50px; line-height: 50px; font-weight: bold; border-bottom: 1px solid #D64654;">商品信息</div>
			<div class="mypjstar">订单编号：${order.bh_st_ddnum}</div>
			<div class="mypjstar">下单时间：${order.bh_dt_addDate}</div>
			<div class="mypjstar">交易金额：￥${order.bh_st_spprice}</div>
			<p>
				<span>退款原因:</span>
				<textarea style="" placeholder="请说明退款原因" name="bf_st_remark"></textarea>
			</p>
			<div class="baoming_bu">
			<input type="hidden" name="bh_id" value="${bh_id}"/>
				<input value="提交" type="submit">
			</div>
		</form>
		</div>
	</div>
</div>
<%@ include file="../foot.jsp"%>