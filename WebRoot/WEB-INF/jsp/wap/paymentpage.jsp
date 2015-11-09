<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="jsplib.jsp"%>
<c:forEach items="${list}" var="val" varStatus="i" >
		<section class="sp_prodoct my_se_product_2 shop_product clearfix">
						<a href="wap/orderdetail?pkid=${val.bh_st_ddnum}">
							<div class="listmess_box1"style="">
								<p>订单编号:<span>${val.bh_st_ddnum}</span></p>
								<p>下单时间:<span>${val.bh_dt_addDate}</span></p>
								
								<p>订单状态:
									<c:choose>
										<c:when test="${val.bh_st_ddstate==1}">
											<span class="recolor">待支付</span>
										</c:when>
										<c:when test="${val.bh_st_ddstate==2}">
											<<span class="recolor">待配送</span>
										</c:when>
										<c:when test="${val.bh_st_ddstate==3}">
											<span class="recolor">配送中</span>
										</c:when>
										<c:when test="${val.bh_st_ddstate==4}">
											<span class="recolor">已完成</span>
										</c:when>
										<c:when test="${val.bh_st_ddstate==5}">
											<span class="recolor">退款中</span>
										</c:when>
										<c:when test="${val.bh_st_ddstate==6}">
											<span class="recolor">已退款</span>
										</c:when>
										<c:when test="${val.bh_st_ddstate==7}">
											<span class="recolor">已支付</span>
										</c:when>
										<c:when test="${val.bh_st_ddstate==8}">
											<span class="recolor">申请退款</span>
										</c:when>
										<c:otherwise>
											<span class="recolor">其他</button></a>
										</c:otherwise>
									</c:choose>
								</p>
								<p>总价:<span class="recolor">￥${val.bh_st_spprice}</span></p>
							</div>
						</a>
						
						<c:choose>
							<c:when test="${val.bh_st_ddstate==1}">
								<a href="wap/payfororderid?pkid=${val.bh_st_ddnum}"><button class="btn my_se_evaluate" onclick="">去支付</button></a>
							</c:when>
							<c:when test="${val.bh_st_ddstate==7}">
								<a href="wap/paybackapplyfororderid?pkid=${val.bh_st_id}"><button class="btn my_se_evaluate" onclick="">申请退款</button></a>
								
								<c:if test="${val.pjs==0 }">
								 <a href="wap/previews?id=${val.bh_st_id }&isShop=2">去评价</a>
								 </c:if>
							</c:when>
							<c:otherwise>
								<!-- <a href="wap/payment"><button class="btn my_se_evaluate" onclick="">请等待</button></a> -->
							</c:otherwise>
						</c:choose>
						
						
		</section>
</c:forEach>

