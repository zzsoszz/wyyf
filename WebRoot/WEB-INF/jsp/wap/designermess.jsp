<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="jsplib.jsp"%>

<!DOCTYPE html>
<html  lang="zh-CN">
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
		<base href="http://${header['host']}${pageContext.request.contextPath}/">
		<title></title>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/wap/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/wap/css/main.css"/>
	</head>
	<body class="indexbody onein_body" >
		<nav class="index clearfix mess_nav" style="" >
			<div class="mess_nav1" ><a href="javascript:;" onClick="javascript :history.back(-1);" ><i class="glyphicon glyphicon-menu-left" ></i></a></div>
			<div class="mess_nav2 mess_navps" >个人信息</div>
			<div class="mess_nav3" ><a href="wap/ordermess?type=${fl}&id=${id}&sjs=${sjs}&sqlx=${sqlx}" >预约</a></div>
		</nav>
		<div class="mess_con1" >
			  <div class="col-xs-12 col-sm-12 mess_con1_box1" align="center" >
			  	   <div class="" ><img src="${user.ag_st_url }" class="img-responsive img-circle"></div>
			       <span>${user.ae_st_name} ${user.ae_st_sex} ${user.ae_nm_age}岁</span>
			  </div>
			  <div class="mess_con1_box2 clearfix" style="">
				  	<div class="mess_conbox21" style="">
				  		<span class="yellowcolor">${yuyuecount}</span>
				  		<p>接单数</p>
				  	</div>
				  	<div class="mess_conbox22" style="" align="center">
					  		<div>
					  		   <ul class="star_px clearfix"style="width: 55%;">
						  		   <%
						  				Integer ba_st_grade=(Integer)request.getAttribute("ba_st_grade");
						  		   		for(int i=0;i<ba_st_grade;i++)
						  		   		{
						  		   %>
						  					<li><img src="${ctx}/images/wap/images/star1.png"  class="img-responsive"></li>
						  		   <%
						  		   		}
						  		   %>
		                       </ul>
					  		</div>
				  		<p>星级</p>
				  	</div>
			  </div>
			 <div class="mess_con1_box3  "> 
	        	 <div class="mess_con1_box31 clearfix" style="">
	        	 	 <ul  id="mytabs" class="nav nav-tabs mess_cb31 yf_bd2" style="width: 100%;">
	        	 	        <c:if test="${ empty sjs }">
			        	 	<li class="nav_one  active" style="width: 30%;"><a href="#mess_team"data-toggle="tab" >团队介绍</a></li>
			        	 	<li class="nav_one " style="width: 30%;"><a href="#mess_ever"data-toggle="tab" >往期案例</a></li>
			        	 	<li class="nav_one " style="width: 30%;"><a href="#mess_pj"data-toggle="tab" >用户评价</a></li>
			        	 	</c:if>
			        	 	<c:if test="${not empty sjs }">
			        	 	<li class="nav_one " style="width: 45%;"><a href="#mess_ever"data-toggle="tab" >往期案例</a></li>
			        	 	<li class="nav_one " style="width: 45%;"><a href="#mess_pj"data-toggle="tab" >用户评价</a></li>
			        	 	</c:if>
			        	 	
	        	    </ul>
	        	 </div>
              </div>
             
             <div  class=" tab-content" style="color: #000000;">
	 	     
                        <div class="tab-pane active" id="mess_team">  
                              <div style="padding: 10px 10px; text-indent:24px">
                              	 ${labour.ba_st_team_intro}
                              </div>
                        </div>
                         <div class="tab-pane " id="mess_ever">  
                             <div id="" class="yanfang_navcon" >
					        	 	<div class="yanfang_nctop" style="">
				                       <ul class="design_specile">
					                       <c:forEach items="${caselist}" var="val" varStatus="i" >
						                      <li class="design_people clearfix"  >
					                       	  	 <div class="design_pleft" >
					                       	  	 	  <div class="design_pic" style="">
					                       	  	 	  	<img src="${val.ag_st_url }" class="img-responsive" />
					                       	  	 	  </div>
					                       	  	 </div>
					                       	  	 <div class="design_pright mess_everright clearfix" style="">
					                       	  	 	    <ul class="clearfix">
					                       	  	 	    	<li><img src="${ctx}/images/wap/images/01_113.png" width="20"><span class="mess_blank">${val.bd_st_name}</span> <span class="mess_blank">${val.bd_st_area}平方米</span></li>
					                       	  	 	    	<li><img src="${ctx}/images/wap/images/01_112.png" width="20"><span class="mess_blank">装修费用</span> <span class="mess_blank recolor">${val.bd_st_money}万</span></li>
					                       	  	 	    	<li><img src="${ctx}/images/wap/images/01_111.png" width="20"><span class="mess_blank">装修时间</span> <span class="mess_blank">${val.bd_dt_time}</span></li>
					                       	  	 	    </ul>
					                       	  	 </div>
					                       	  </li>
											</c:forEach>
											
				                       </ul>
					        	 	</div>
				
					        </div>
                        </div>
                         <div class="tab-pane  " id="mess_pj">  
                              <div class="container">
                              	   <table border="0" cellspacing="" cellpadding="" class="mess_pjtable"> 	  
                              	 <c:forEach items="${commentslist}" var="val" varStatus="i" >
	                              	   	<tr>
	                              	   
	                              	   		<%-- <td>${val.be_st_fbgid}</td> --%>
	                              	   		<td>${val.be_st_content}</td>
	                              	   	</tr>
                              	   </c:forEach>
                              	   </table>
                              </div>
                        </div>
			  
		 </div>
        
		
		
		
	</body>
	 <script src="${ctx}/js/wap/js/jquery-1.11.2.min.js"></script>
	 <script type="text/javascript" src="${ctx}/js/wap/js/bootstrap.min.js"> </script>
	 <script type="text/javascript" src="${ctx}/js/wap/js/pmain.js"></script>
</html>
