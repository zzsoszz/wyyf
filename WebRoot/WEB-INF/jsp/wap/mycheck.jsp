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
		
		<!--<nav class="index navbar clearfix design_nav">-->
			<!--<div class="design_nav1" style=""><a href="index3.html" style="color: white;"><img src="${ctx}/images/wap/images/back.png"width="25"></a></div>-->
			<!--<div class="design_nav2"  style="">空气检测</div>-->
			<!--<div class="mess_nav1 index_share" onclick="tkopeng(this,121)"><i class="iconfont icon-fenxiang"></i></div>-->
		<!--</nav>-->
        <div class="yanfang_nav" >    	 
        	 <div class=" col-xs-12 " >
        	 	 <ul   class="nav nav-tabs design_check yf_bd">
		        	 	<!-- <li class="nav_one nav_two3 active " style="width: 50%;margin: 0px;padding: 0px;"><a href="#check_free"data-toggle="tab">免费检测</a></li> -->
		        	 	<li class="nav_one nav_two4 active" style="width: 100%;margin: 0px;padding: 0px;"><a href="#check_gold"data-toggle="tab">免费检测</a></li>
        	    </ul>
        	 </div>
        </div>
        
        <div  class="myservice tab-content" >
	 	          <!--
                        <div class="tab-pane " id="check_free">
                             <div id="" class="yanfang_navcon" style="height: 520px;">
                             	<form action="wap/addApply" id="wzyfForm7" method="post" class="yanfang_form">
						        	 	<div class="yanfang_nctop design_nomol"  style="">
						        	 		  <p style="text-indent: 24px;padding-bottom: 10px;">网众竭诚为您服务，请填写您的信息预约免费检测，我们将安排专业人员上门免费检测您装修的房屋空气质量是否合格</p>
						        	 		
						        	 	  	  <div class="yanfang_input">
						        	 	  	  	  <input type="text" placeholder="请输入姓名" name="bf_st_name"  value="姓名1" />
						        	 	  	  </div>
						        	 	  	  <div class="yanfang_input">
						        	 	  	  	  <input type="text" placeholder="请输入手机" name="bf_st_tell" value="13730666347"/>
						        	 	  	  </div>
						        	 	  	  <div class="yanfang_input">
						        	 	  	  	<input type="date" placeholder="预约时间" name="bf_dt_time"  value="2015-01-05"/>
						        	 	  	  </div>
						        	 	  	  <div class="yanfang_input">
						        	 	  	  	<input type="text" placeholder="小区地址" name="bf_st_address"  value="mycheckaddress"/>
						        	 	  	  </div> 
						        	 	</div>
						        	 	<button class="btn btn-block mybuttom" onclick="tkopeng(this,7)">预约</button>
				        	 	</form>
				           </div>
                        </div>-->
                        <!--收费治理-->    
                        <div class="tab-pane  active" id="check_gold">
                        	
       						<div class="yanfang_navcon">
					        	 	<div class="yanfang_nctop" >
				                       <ul class="design_specile" id="design_specile">
				                       	 
				                       </ul>
					        	 		<div class="loading_more" id="loading_more" ><i class="glyphicon glyphicon-menu-down"></i>查看更多</div>
					        	 	</div>
				
					        </div>
                        </div>
                        
                        
                        
                        <!--金牌设计
                        <div class="tab-pane fade " id="design_gold">  
     						    <div  class="yanfang_navcon" >
					        	 	<div class="yanfang_nctop" style="">
				                       <ul class="design_specile">
				                       	 
				                       	 <li class="design_people clearfix" >
				                       	  	 <a href="wap/designermess">
				                       	  	 	<div class="design_pleft"  style="">
				                       	  	 	  <div class="design_pic" style="">
				                       	  	 	  	<img src="${ctx}/images/wap/images/my06.png" class="img-responsive" />
				                       	  	 	  </div>
				                       	  	 	   <span>刘女士</span>
				                       	  	    </div>
					                       	  	 <div class="design_pright clearfix" style="">
					                       	  	 	 <ul class="star_px clearfix">
					                       	  	 	 	<li><img src="${ctx}/images/wap/images/star1.png"  class="img-responsive"></li>
					                       	  	 	 	<li><img src="${ctx}/images/wap/images/star1.png"  class="img-responsive"></li>
					                       	  	 	 	<li><img src="${ctx}/images/wap/images/star1.png"  class="img-responsive"></li>
					                       	  	 	 	<li><img src="${ctx}/images/wap/images/star2.png"  class="img-responsive"></li>
					                       	  	 	 	<li><img src="${ctx}/images/wap/images/star2.png"  class="img-responsive"></li>
					                       	  	 	 </ul>
					                       	  	 	 <p class="margin_bott">技能：金牌设计师</p>
					                       	  	 	 <span>订单数：123 </span><span >团队人数：99</span>
					                       	  	 </div>
				                       	  	 </a>
				                       	  </li>
				                       	  
				                       	  <li class="design_people clearfix" >
				                       	  	 <a href="wap/designermess">
				                       	  	 	<div class="design_pleft"  style="">
				                       	  	 	  <div class="design_pic" style="">
				                       	  	 	  	<img src="${ctx}/images/wap/images/my06.png" class="img-responsive" />
				                       	  	 	  </div>
				                       	  	 	   <span>刘女士</span>
				                       	  	    </div>
					                       	  	 <div class="design_pright clearfix" style="">
					                       	  	 	 <ul class="star_px clearfix">
					                       	  	 	 	<li><img src="${ctx}/images/wap/images/star1.png"  class="img-responsive"></li>
					                       	  	 	 	<li><img src="${ctx}/images/wap/images/star1.png"  class="img-responsive"></li>
					                       	  	 	 	<li><img src="${ctx}/images/wap/images/star1.png"  class="img-responsive"></li>
					                       	  	 	 	<li><img src="${ctx}/images/wap/images/star2.png"  class="img-responsive"></li>
					                       	  	 	 	<li><img src="${ctx}/images/wap/images/star2.png"  class="img-responsive"></li>
					                       	  	 	 </ul>
					                       	  	 	 <p class="margin_bott">技能：金牌设计师</p>
					                       	  	 	 <span>订单数：123 </span><span >团队人数：99</span>
					                       	  	 </div>
				                       	  	 </a>
				                       	  </li>
				                       	   <li class="design_people clearfix" >
				                       	  	 <div class="design_pleft"  style="">
				                       	  	 	  <div class="design_pic" style="">
				                       	  	 	  	<img src="${ctx}/images/wap/images/my06.png" class="img-responsive" />
				                       	  	 	  </div>
				                       	  	 	   <span>刘女士</span>
				                       	  	 </div>
				                       	  	 <div class="design_pright clearfix" style="">
				                       	  	 	 <ul class="star_px clearfix">
				                       	  	 	 	<li><img src="${ctx}/images/wap/images/star1.png"  class="img-responsive"></li>
				                       	  	 	 	<li><img src="${ctx}/images/wap/images/star1.png"  class="img-responsive"></li>
				                       	  	 	 	<li><img src="${ctx}/images/wap/images/star1.png"  class="img-responsive"></li>
				                       	  	 	 	<li><img src="${ctx}/images/wap/images/star2.png"  class="img-responsive"></li>
				                       	  	 	 	<li><img src="${ctx}/images/wap/images/star2.png"  class="img-responsive"></li>
				                       	  	 	 </ul>
				                       	  	 	 <p class="margin_bott">技能：金牌设计师</p>
				                       	  	 	 <span>订单数：123 </span><span >团队人数：99</span>
				                       	  	 </div>
				                       	  </li>
				                       </ul>
					        	 	</div>
					        </div>
                        </div>
                        
                        -->
                        
                        
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
	 					console.debug(curpage++);
	 					getPage(curpage++);
	 					$("#loading_more").data("page",curpage);
	 				}
	 			);
	 		}
		);
	 	
	 	
	 	function getPage(page)
	 	{console.debug(page);
	 	
	 		$.ajax({  
                type:"POST",  
                url:"wap/supervisionpage?sqlx=13&type=4&subtype=14&page="+page,  
                dataType:"html",
                success:function(data){
                	$("#design_specile").append(data);
                },
                error:function(){  
                    alert("过账失败");  
                }
            });
	 	}
	 </script>
	 
	 
	 
</html>
