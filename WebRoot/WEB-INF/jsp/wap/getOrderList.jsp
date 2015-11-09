<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="jsplib.jsp"%>

<c:forEach items="${data }" var="v">


<section id="${v.bh_st_id}" class="sp_prodoct my_se_product_2 shop_product clearfix">
						<div class="col-sm-4 col-xs-4 col-md-4 col-lg-4" ></div>
						<div class="col-sm-8 col-xs-8 col-md-8 col-lg-8 shop_text clearfix">
							<p>${v.ae_st_name }</p>
							<p>￥${v.bh_st_spprice }</P>
						</div>
						<a href="javascript:void(0)" class="btn btn-primary btn-xs"   onclick="javascript:delectPayProduct('${v.bh_st_id}')">删除</a>
					</section>
					
					</c:forEach>
					
					<script type="text/javascript">
				function delectPayProduct(id) {
					console.debug(id);
					var url = "wap/delectPayProduct?id="+id;
					
					$.post(url,"",function callback(data) {
						
						if(data==0){
							alert("删除成功1");
							location.href = "${pageContext.request.contextPath}/wap/mysession?tt=1";
						}else{
							alert("删除失败");
						}
					},"json");
				}
				
				</script>