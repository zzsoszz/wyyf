<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="jsplib.jsp"%>
<c:forEach items="${pages.data}" var="v" varStatus="i">
<section class="sp_prodoct clearfix">
			<div class="col-sm-4 col-xs-4"><a href="wap/goodsProduct?pkid=${v.bg_st_id }" class="tosession"><img class="img-responsive" src="${v.bg_st_img1}"></a></div>
			<div class="col-sm-8 col-xs-8 sp_text clearfix" s>
				 	<div class="f_fl col-sm-8 col-xs-8" >
				 	      <p  class="sp_pro_name"> ${v.bg_st_name}</p>
					      <p>￥${v.bg_st_pricezg }</p>
				 	</div>
				 	<div class="f_fl col-sm-4 col-xs-4">
				 	      <a href="wap/goodsProduct?pkid=${v.bg_st_id }" class="tosession btn btn-primary btn-xs"><p>去购买</p></a>				
				 	</div>
			 	
					
				</div>

				
			</section>
</c:forEach>
