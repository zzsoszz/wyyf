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
        	 	<li class="nav_one nav_two3  "  style="width: 100%;margin: 0px;padding: 0px;"><a style="line-height:42px" href="#design_nomal"data-toggle="tab">户型优化</a></li>
        	 	 </c:if>
		        	 	 <c:if test="${wyyfType == 2}" >
		        	 	<li class="nav_one nav_two4"  style="width: 100%;margin: 0px;padding: 0px;"><a style="line-height:42px" href="#design_specil"data-toggle="tab">个性设计</a></li>
        	    </c:if>
        	    
        	     <c:if test="${ empty  wyyfType }" >
			  	<li class="nav_one nav_two3  active"  style="width: 50%;margin: 0px;padding: 0px;"><a href="#design_nomal"data-toggle="tab">户型优化</a></li>
		        	 	<li class="nav_one nav_two4"  style="width: 50%;margin: 0px;padding: 0px;"><a href="#design_specil"data-toggle="tab">个性设计</a></li>
		        	 
			    </c:if>
		        	 	
		        	 	
		        	 	<!--<li class="nav_one nav_two5"  style="width: 33.333%;margin: 0px;padding: 0px;"><a href="#design_gold"data-toggle="tab">金牌设计</a></li>-->
        	    </ul>
        	 </div>
        </div>
        <div  class="myservice tab-content" >
	 	          <!--免费设计-->
	 	           <iframe src="about:blank" name="blankFrame" id="blankFrame" style="display: none;"></iframe>
                        <div class="tab-pane fade in active" id="design_nomal">
                             <div class="yanfang_navcon" >
				        	 	<div class="yanfang_nctop design_nomol"  style="">
				        	 		  <span>网众科技竭诚为您服务，网众科技设计部直接为您提供房屋的平面布局图</span>
				        	 		<form  id="wzyfForm7"  target="blankFrame" action="wap/addApplyWitFile" method="post" class="yanfang_form" enctype="multipart/form-data">
				        	 	  	  <div class="yanfang_input">
				        	 	  	  	<input class="yztj" type="text" placeholder="请输入楼盘名称" name="bf_st_address" value=""/>
				        	 	  	  </div>				        	 	  	  
				        	 	  	  <div class="yanfang_input">
				        	 	  	  	  <input class="yztj"  type="text" placeholder="请输入姓名"   name="bf_st_name" value=""/>
				        	 	  	  </div>
				        	 	  	  <div class="yanfang_input">
				        	 	  	  	  <input class="yztj" type="tel"  placeholder="请输入手机"   name="bf_st_tell" value="" />
				        	 	  	  </div>
				        	 	  	  <div class="yanfang_input">
				        	 	  	  	  <input class="yztj" type="date" id="theDate1" placeholder="请输入预约时间"   name="bf_st_time"  />
				        	 	  	  	  <script>document.getElementById('theDate1').value = new Date().toISOString().substring(0, 10);</script>
				        	 	  	  </div>

				        	 	  	  <div class="yanfang_input">
				        	 	  	  	<input class="yztj number" type="text" placeholder="请输入建筑面积" name="bf_st_area"  value=""/>
				        	 	  	  </div> 
				        	 	  	   <div class="yanfang_input">
					        	 	  	   	<div class="mydsign_huxing"  style="">户型</div>
					        	 	  	   	 <ul class="mydesign_addpic clearfix">
					        	 	  	   	 	<li class="dk_hkb_pic">
													<span class="uploadImg" style="font-size:12px; overflow:hidden; position:absolute">
														
														<input name="myfile[0]" type="file" class="file" size="1" style="position:absolute; z-index:100;  font-size:60px;opacity:0;filter:alpha(opacity=0); width:60px;height:60px;">
														 <!-- 
														<input name="myfile[0]" type="file" >
														-->
														<a href="#" style="width: 60px;height: 60px;display: block;">
															<img src="${ctx}/images/wap/images/addpic.png"width="60" height="60">
														</a>
													</span>
												</li>
					        	 	  	   	 	<li class="dk_hkb_pic">
													 <span class="uploadImg" style="font-size:12px; overflow:hidden; position:absolute">
														<input name="myfile[1]" type="file" class="file" size="1" style="position:absolute; z-index:100;  font-size:60px;opacity:0;filter:alpha(opacity=0); width:60px;height:60px;">
														<a href="#" style="width: 60px;height: 60px;display: block;">
															<img src="${ctx}/images/wap/images/addpic.png"width="60" height="60">
														</a>
													</span>
												</li>
					        	 	  	   	 	<li class="dk_hkb_pic">
													<span class="uploadImg" style="font-size:12px; overflow:hidden; position:absolute">
														<input name="myfile[2]" type="file" class="file" size="1" style="position:absolute; z-index:100;  font-size:60px;opacity:0;filter:alpha(opacity=0); width:60px;height:60px;">
														<a href="#" style="width: 60px;height: 60px;display: block;">
															<img src="${ctx}/images/wap/images/addpic.png"width="60" height="60">
														</a>
													</span>
												</li>
					        	 	  	   	 </ul>
				        	 	  	   </div>
				        	 	  	   <textarea class="design_textarea" name="bf_st_remark" rows="" cols="" style="" placeholder="请输入居家需求200字以内"></textarea>
				        	 	  	   <div class="tishi"  style="padding-top:15px;">
				        	 		       	<span class="redcolor">温馨提示，请上传清晰户型图。</span>免费设计只提供（平面布局。如需要更多设计需求，可选择个性设计，个性设计包括（施工图，效果图等全套图纸，费用根据客户需求面议））
				        	 	  	   </div>
				        	 	  	   <input type="hidden" name="bf_st_type"  value="4">
				        	 	  	   <input type="hidden" name="bf_st_receiveid"  value="e58e7927a2c9436788aefb219539bb7e">
				        	 	  	   <div class="yf_shareother" >
										     <span class="green">	
												 <p>通过“我要验房”APP分享给小伙伴有米奇币哟。</p>
										     </span>
										</div>
				        	 	  	    <input readonly="readonly" onclick="tkopeng(this,7)"  type="text" class="btn btn-block mybuttom"  value="预约"/>
				        	 	  	   <div class="tishi" >您的信息将被严格保密，资料提交后客服将在24小时之内联系您</div>
				        	 	    </form>
				        	 	    
				        	 	</div>
				        	 	
				        	 	<!-- <button class="btn btn-block mybuttom" style="" onclick="tkopeng(this,7)">预约</button> -->
				        	 	
				        	 	
				        	 	
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
                url:"wap/designstyle?type=1&page="+page,  
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
