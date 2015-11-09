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
	<body >
	<%@ include file="header.jsp"%>
	
	<div class="yanfang_nav" style="width: 100%;height: 50px;background-color: #E8E8E8;">
		<div class=""  >
			<ul  id="mytabs" class="nav nav-tabs yf_bd" style="width: 100%;">
			
			<c:if test="${wyyfType == 1}" >
        	 	
        	 	<li class="nav_one nav_two1 active" id="checkfree"style="width: 100%;float: left;" ><a   href="#fixture_gz"data-toggle="tab">找工长</a></li>
        	 	
        	</c:if>
		     <c:if test="${wyyfType == 2}" >
		        	 	<li class="nav_one nav_two2 active" id="checkgold" style="width: 100%;float: left;"><a   href="#fixture_master"data-toggle="tab">找师傅</a></li>
        	 </c:if>
        	    
        	     <c:if test="${ empty  wyyfType }" >
        	  <li class="nav_one nav_two1 active" id="checkfree"style="width: 50%;float: left;" ><a href="#fixture_gz"data-toggle="tab">找工长</a></li>
				<li class="nav_one nav_two2" id="checkgold" style="width: 50%;float: left;"><a href="#fixture_master"data-toggle="tab">找师傅</a></li>
			    </c:if>
			
			
			
				
				
			</ul>
		</div>
	</div>
		
		
		<section  class="myservice tab-content" >
	 	          <!---->
	 	           <c:if test="${ empty  wyyfType }" >
	 	           
                   <div class="tab-pane active" id="fixture_gz" style="min-height: 520px;">
                   	   <div  class="yanfang_navcon">
			        	 	<div class="yanfang_nctop"  >
		                       <ul class="design_specile" id="design_specile" >
		                       </ul>
			        	 	</div>
				      </div>
				      <div class="loading_more"  id="loading_more" ><i class="glyphicon glyphicon-menu-down"></i>查看更多</div>
                   	   
                   	
			      </div>
			      <div class="tab-pane " id="fixture_master"style="min-height: 520px;">
                   	   <div  class="yanfang_navcon">
			        	 	<div class="yanfang_nctop" >
			                     <ul class="design_specile" id="design_specile1" >
			                     
			                     </ul>
			        	 		
			        	 	</div>
				      </div>
				      <div class="loading_more"  id="loading_more1" ><i class="glyphicon glyphicon-menu-down"></i>查看更多</div>
			      </div>
			      </c:if>
	 	          <c:if test="${wyyfType == 1}" >
                   <div class="tab-pane active" id="fixture_gz" style="min-height: 520px;">
                   	   <div  class="yanfang_navcon">
			        	 	<div class="yanfang_nctop"  >
		                       <ul class="design_specile" id="design_specile" >
		                       
		                       
		                       </ul>
			        	 	</div>
				      </div>
				      <div class="loading_more"  id="loading_more" ><i class="glyphicon glyphicon-menu-down"></i>查看更多</div>
                   	   
                   	
			      </div>
			      </c:if>
			      <c:if test="${wyyfType == 2}" >
			      <div class="tab-pane  active" id="fixture_master"style="min-height: 520px;">
                   	   <div  class="yanfang_navcon">
			        	 	<div class="yanfang_nctop" >
			                     <ul class="design_specile" id="design_specile1" >
			                     
			                     
			                     </ul>
			        	 		
			        	 	</div>
				      </div>
				      <div class="loading_more"  id="loading_more1" ><i class="glyphicon glyphicon-menu-down"></i>查看更多</div> 
				      
			      </div>
			      
			      </c:if>
		</section>
		<div class="tankuang " ></div>
		<div class="zhezhao" ></div>
	<!--底部-->
	<%@ include file="footer.jsp"%>
	
		
		
	</body>
	 <script src="${ctx}/js/wap/js/jquery-1.11.2.min.js"></script>
	 <script type="text/javascript" src="${ctx}/js/wap/js/bootstrap.min.js"> </script>
	 <script type="text/javascript" src="${ctx}/js/wap/js/pmain.js"></script>
	 
	 <script>
	 	$(document).ready(
	 		function()
	 		{
	 			//取出第一页
	 			getPage(1);
	 			getPage1(1);
	 			$("#loading_more").data("page",1);
	 			$("#loading_more").click(
	 				function()
	 				{
	 					var curpage=$("#loading_more").data("page");
	 					getPage(++curpage);
	 					$("#loading_more").data("page",curpage);
	 				}
	 			);
	 			
	 			$("#loading_more1").data("page",1);
	 			$("#loading_more1").click(
	 				function()
	 				{
	 					var curpage=$("#loading_more1").data("page");
	 					getPage1(++curpage);
	 					$("#loading_more1").data("page",curpage);
	 				}
	 			);
	 			
	 			
	 		}
		);
	 	
	 	
	 	
	 	function getPage(page)
	 	{
	 		$.ajax({  
                type:"POST",  
                url:"wap/fixture_iterm?type=1&pageIndex="+page,  
                dataType:"html",
                success:function(data){
					
                	
		            	$("#loading_more").attr('style', data.currPageNumber);
						$("#loading_more").attr('lang', data.totalPages);

						
						if (data.trim()=="") {
							$("#loading_more").remove();
						}
						$("#design_specile").append(data);
						//alert($('div').html(data));
					},
					error : function() {
						alert("过账失败");
					}
				});
			}
	 	function getPage1(page)
	 	{
	 		$.ajax({  
                type:"POST",  
                url:"wap/supervisionpage?type=4&subtype=7,8,9,10,11,12&sqlx=8&page="+page,  
                dataType:"html",
                success:function(data){
				
                	
		            	$("#loading_more").attr('style', data.currPageNumber);
						$("#loading_more").attr('lang', data.totalPages);

						
						if (data.trim()=="") {
							$("#loading_more").remove();
						}
						$("#design_specile1").append(data);
						//alert($('div').html(data));
					},
					error : function() {
					}
				});
			}
		</script>
</html>

