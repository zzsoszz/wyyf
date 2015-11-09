<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="jsplib.jsp"%>
<c:forEach items="${shoplist}" var="val" varStatus="i" >
				<section class="sp_prodoct my_se_product_2 shop_product clearfix">
					<div class="col-sm-4 col-xs-4"><img class="img-responsive" src="${val.bg_st_img1}" /></div>
					<div class="col-sm-8 col-xs-8 shop_text clearfix">
						<p>${val.bg_st_name}</p>
						<p>￥${val.bg_st_pricezg}</P>				
							<a href="wap/goodsProduct?pkid=${val.bg_st_id}"><button class="btn shop_button btn-primary" onclick="">去购买</button></a>			
						</div>
				</section>
</c:forEach>

