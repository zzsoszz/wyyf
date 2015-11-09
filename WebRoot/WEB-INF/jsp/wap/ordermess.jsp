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
		
		<base href="http://${header['host']}${pageContext.request.contextPath}/"> 
	</head>
	<body class="indexbody">
	
	
	
		<nav class="index clearfix mess_nav" style="background-color: #1E76B8;color: white">
			<div class="mess_nav1" ><a href="javascript:;" onClick="javascript :history.back(-1);"><i class="glyphicon glyphicon-menu-left" style="color: white"></i></a></div>
			<div class="mess_nav2" >预约</div>
			<!-- <div class="mess_nav1 index_share" onclick="tkopeng(this,121)"><i class="iconfont icon-fenxiang"></i></div> -->
		</nav>

        <div class=" ordermess_form" > 
	       	<!-- 设计师预约 -->
	       	
	      <%--    <c:choose> 
         	      <c:when test="${fl ==1  }">
         	      	 --%>
         	      	 
         	      	 <c:if test="${ not empty sjs}">
	       	 <form id="wzyfForm7" action="wap/addApplyWitFile" method="post" class="yanfang_form" enctype="multipart/form-data">
		        	<div class="yanfang_nctop" style="">
			        	 	  	  <div class="yanfang_input">
			        	 	  	  	  <input class="yztj" type="text" placeholder="请输入姓名"    name="bf_st_name" />
			        	 	  	  </div>
			        	 	  	  <div class="yanfang_input">
			        	 	  	  	  <input class="yztj" type="tel" placeholder="请输入电话号码" name="bf_st_tell" />
			        	 	  	  </div>
			        	 	  	  <div class="yanfang_input">
			        	 	  	  	<input class="yztj" type="date" id="theDate1" placeholder="请选择时间"  value="" name="bf_dt_time" />
			        	 	  	  </div>
			        	 	  	   <script>document.getElementById('theDate1').value = new Date().toISOString().substring(0, 10);</script>
			        	 	  	  <div class="yanfang_input">
			        	 	  	  	<input class="yztj" type="text" placeholder="请输入楼盘详细地址" name="bf_st_address" />
			        	 	  	  </div>
			        	 	  	  <div class="yanfang_input">
			        	 	  	  	<input class="yztj " type="text" placeholder="请输入面积" name="bf_st_area" />
			        	 	  	  </div>   
				        	 	 
				        	 	  <div class="yanfang_input">
					        	 	  	   	<div class="mydsign_huxing" style="">户型</div>
					        	 	  	   	 <ul class="mydesign_addpic clearfix">
					        	 	  	   	 	<li class="dk_hkb_pic">
													<span class="uploadImg" style="font-size:12px; overflow:hidden; position:absolute">
														
														<input name="myfile[0]" type="file" class="file" size="1" style="position:absolute; z-index:100; font-size:60px;opacity:0;filter:alpha(opacity=0); width:60px;height:60px;">
														 <!-- 
														<input name="myfile[0]" type="file" >
														-->
														<a href="#" style="width: 60px;height: 60px;display: block;">
															<img src="${ctx}/images/wap/images/addpic.png" width="60" height="60">
														</a>
													</span>
												</li>
					        	 	  	   	 	<li class="dk_hkb_pic">
													 <span class="uploadImg" style="font-size:12px; overflow:hidden; position:absolute">
														<input name="myfile[1]" type="file" class="file" size="1" style="position:absolute; z-index:100;  font-size:60px;opacity:0;filter:alpha(opacity=0);width:60px;height:60px;">
														<a href="#" style="width: 60px;height: 60px;display: block;">
															<img src="${ctx}/images/wap/images/addpic.png" width="60" height="60">
														</a>
													</span>
												</li>
					        	 	  	   	 	<li class="dk_hkb_pic">
													<span class="uploadImg" style="font-size:12px; overflow:hidden; position:absolute">
														<input name="myfile[2]" type="file" class="file" size="1" style="position:absolute; z-index:100;  font-size:60px;opacity:0;filter:alpha(opacity=0);width:60px;height:60px;">
														<a href="#" style="width: 60px;height: 60px;display: block;">
															<img src="${ctx}/images/wap/images/addpic.png" width="60" height="60">
														</a>
													</span>
												</li>
					        	 	  	   	 </ul>
				        	 	  	   </div>
				        	 	  	    <div class="yanfang_input">
				        	 	  	   	<input class="btn btn-block mybuttom mybuttom1" onclick="tkopeng(this,7)" value="预约">
				        	 	  	     <div class="tankuang" ></div>
		                                 <div class="zhezhao" ></div> 
				        	 	  </div>
				        	 	  <input type="hidden" name="bf_st_receiveid" value="${id}">
				        	 	  <input type="hidden" name="bf_st_type" value="5">
				      </div>
			   </form>
         	     </c:if>   
				 <c:if test="${empty sjs}">
				 <!-- 工长预约 -->
			   <form id="wzyfForm4"  action="wap/addApply" method="post" class="yanfang_form">
		        	<div class="yanfang_nctop" style="">
			        	 	  	  <div class="yanfang_input">
			        	 	  	  	  <input class="yztj" type="text" placeholder="请输入姓名"    name="bf_st_name" value=""/>
			        	 	  	  </div>
			        	 	  	  <div class="yanfang_input">
			        	 	  	  	  <input class="yztj" type="tel" placeholder="请输入电话号码" name="bf_st_tell" value="" />
			        	 	  	  </div>
			        	 	  	  <div class="yanfang_input">
			        	 	  	  	<input class="yztj" type="date" id="theDate" placeholder="请选择时间"  name="bf_dt_time"  value=""/>
			        	 	  	  	 <script>document.getElementById('theDate').value = new Date().toISOString().substring(0, 10);</script>
			        	 	  	  </div>
			        	 	  	  <div class="yanfang_input">
			        	 	  	  	<input class="yztj number" type="text" placeholder="请输入房屋面积" name="bf_st_area" value=""/>
			        	 	  	  </div> 
			        	 	  	   <div class="yanfang_input">
			        	 	  	  	<input class="yztj" type="text" placeholder="请输入楼盘详细地址" name="bf_st_address" value=""/>
			        	 	  	  </div>   
				        	 	  <div class="yanfang_input">
				        	 	  	   	<!-- <button class="btn btn-block mybuttom" onclick="tkopeng(this,4)">预约</button> -->
				        	 	  	     <input class="btn btn-primary mybuttom mybuttom1" onclick="tkopeng(this,4)" value="预约">
				        	 	  	     <div class="tankuang" ></div>
		                                 <div class="zhezhao" ></div>
				        	 	  </div>
				        	 	  <input type="hidden" name="bf_st_receiveid" value="${id}">
				        	 	  <input type="hidden" name="bf_st_type" value="${sqlx }">
				      </div>
			   </form>
			   
			   </c:if>
				 
				
	       	
	       	
	       	
	       
			   
			   
			   
			   
        </div>
	</body>
	 <script src="${ctx}/js/wap/js/jquery-1.11.2.min.js"></script>
	 <script type="text/javascript" src="${ctx}/js/wap/js/bootstrap.min.js"> </script>
	 <script src="${ctx}/js/wap/js/pmain.js"></script>
</html>
