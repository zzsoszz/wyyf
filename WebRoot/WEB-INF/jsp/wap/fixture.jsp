<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="jsplib.jsp"%>
<!DOCTYPE html>
<html  lang="zh-CN">
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
		<title></title>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/wap/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/wap/css/main.css"/>
	</head>
	<body class="indexbody" >
		
		<%@ include file="header.jsp"%>
		

        <div class="yanfang_nav" >    	 
        	 <div class=" col-xs-12 " style="">
        	 	 <ul  id="" class="nav nav-tabs design_check yf_bd">
			
			<c:if test="${wyyfType == 1}" >
        	 	<li class="nav_one nav_two3  "  style="width: 100%;margin: 0px;padding: 0px;"><a style="line-height:42px" href="#design_nomal"data-toggle="tab">找工长</a></li>
        	 	 </c:if>
		        	 	 <c:if test="${wyyfType == 2}" >
		        	 	<li class="nav_one nav_two4"  style="width: 100%;margin: 0px;padding: 0px;"><a style="line-height:42px" href="#design_specil"data-toggle="tab">找师傅</a></li>
        	    </c:if>
        	    
        	     <c:if test="${ empty  wyyfType }" >
			  	<li class="nav_one nav_two3  active"  style="width: 50%;margin: 0px;padding: 0px;"><a href="#design_nomal"data-toggle="tab">找工长</a></li>
		        	 	<li class="nav_one nav_two4"  style="width: 50%;margin: 0px;padding: 0px;"><a href="#design_specil"data-toggle="tab">找师傅</a></li>
		        	 
			    </c:if>
		        	 	
		        	 	
		        	 	<!--<li class="nav_one nav_two5"  style="width: 33.333%;margin: 0px;padding: 0px;"><a href="#design_gold"data-toggle="tab">金牌设计</a></li>-->
        	    </ul>
        	 </div>
        </div>
        <div  class="myservice tab-content" >
	 	          <!--免费设计-->
	 	            <c:if test="${ empty  wyyfType }" >
                       <div class="tab-pane fade in active" id="design_specil" style="min-height: 525px;">
       						<div  class="yanfang_navcon">
					        	 	<div class="yanfang_nctop" >
				                       <ul class="design_specile" id="design_specile">
				                       
				                       </ul>
					        	 	</div>
				                   <div class="loading_more"  id="loading_more" >
				                   	   <i class="glyphicon glyphicon-menu-down"></i>
				                   	       查看更多
				                   	</div>
				                   	
				                   	 <%--  <c:choose> 
         	    
         	      <c:when test="${data.totalPages !=1 }">
         	       ${order.pricheAll}
         	      </c:when>
                  <c:otherwise>   
				  ${order.bh_st_spprice}
				  </c:otherwise>
</c:choose> --%>
					        </div>
                        </div>
                    <!--个性设计-->    
                        <div class="tab-pane fade in" id="design_specil" style="min-height: 525px;">
       						<div  class="yanfang_navcon">
					        	 	<div class="yanfang_nctop" >
				                       <ul class="design_specile" id="design_specile">
				                       
				                       </ul>
					        	 	</div>
				                   <div class="loading_more"  id="loading_more" >
				                   	   <i class="glyphicon glyphicon-menu-down"></i>
				                   	       查看更多
				                   	</div>
				                   	
				                   	 <%--  <c:choose> 
         	    
         	      <c:when test="${data.totalPages !=1 }">
         	       ${order.pricheAll}
         	      </c:when>
                  <c:otherwise>   
				  ${order.bh_st_spprice}
				  </c:otherwise>
</c:choose> --%>
					        </div>
                        </div>
	 	            
	 	            </c:if>
	 	          <c:if test="${wyyfType == 1}" >
                       <div class="tab-pane fade in active" id="design_specil" style="min-height: 525px;">
       						<div  class="yanfang_navcon">
					        	 	<div class="yanfang_nctop" >
				                       <ul class="design_specile" id="design_specile">
				                       
				                       </ul>
					        	 	</div>
				                   <div class="loading_more"  id="loading_more" >
				                   	   <i class="glyphicon glyphicon-menu-down"></i>
				                   	       查看更多
				                   	</div>
				                   	
				                   	 <%--  <c:choose> 
         	    
         	      <c:when test="${data.totalPages !=1 }">
         	       ${order.pricheAll}
         	      </c:when>
                  <c:otherwise>   
				  ${order.bh_st_spprice}
				  </c:otherwise>
</c:choose> --%>
					        </div>
                        </div>
                        </c:if>
                        <c:if test="${wyyfType == 2}" >
                    <!--个性设计-->    
                        <div class="tab-pane fade in active" id="design_specil" style="min-height: 525px;">
       						<div  class="yanfang_navcon">
					        	 	<div class="yanfang_nctop" >
				                       <ul class="design_specile" id="design_specile">
				                       
				                       </ul>
					        	 	</div>
				                   <div class="loading_more"  id="loading_more" >
				                   	   <i class="glyphicon glyphicon-menu-down"></i>
				                   	       查看更多1
				                   	</div>
				                   	
				                   	 <%--  <c:choose> 
         	    
         	      <c:when test="${data.totalPages !=1 }">
         	       ${order.pricheAll}
         	      </c:when>
                  <c:otherwise>   
				  ${order.bh_st_spprice}
				  </c:otherwise>
</c:choose> --%>
					        </div>
                        </div>
                        </c:if>

        </div>
		<div class="tankuang " ></div>
		<div class="zhezhao" ></div>
	<!--底部-->
	<%@ include file="footer.jsp"%>
	
	</body>
	 <script src="${ctx}/js/wap/js/jquery-1.11.2.min.js"></script>
	 <script type="text/javascript" src="${ctx}/js/wap/js/bootstrap.min.js"> </script>
	 <script src="${ctx}/js/wap/js/pmain.js"></script>
	 <script>
	 	$(document).ready(
	 		function()
	 		{
	 			//取出第一页
	 			getPage(1);
	 			$("#loading_more").data("page",1);
	 			
	 			
	 			$("#loading_more").click(
	 				function()
	 				{
	 					var curpage=$("#loading_more").data("page");
	 					console.debug(curpage);
	 					getPage(++curpage);
	 					$("#loading_more").data("page",curpage);
	 				}
	 			);
	 		}
		);
	 	
	 	
	 	function getPage(page)
	 	{
	 		$.ajax({  
                type:"POST",  
                url:"wap/fixture_iterm?pageIndex="+page,  
                dataType:"html",
                success:function(data){
					
                	
		            	$("#loading_more").attr('style', data.currPageNumber);
						$("#loading_more").attr('lang', data.totalPages);

						
						
						$("#design_specile").append(data);
						//alert($('div').html(data));
					},
					error : function() {
					}
				});
			}
		</script>
</html>
