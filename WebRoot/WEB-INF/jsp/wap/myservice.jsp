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
	<body class="" >
		<nav class="index clearfix mess_nav nav_bg" >
			<div class="mess_nav1" ><a href="wap/pmyzone" ><i class="glyphicon glyphicon-menu-left" style="color: white"></i></a></div>
			<div class="mess_nav2" >我的服务</div>
			<!-- <div class="mess_nav1 index_share" onclick="tkopeng(this,121)"><i class="iconfont icon-fenxiang"></i></div> -->
		</nav>
         <div class="yanfang_nav" >    	 
        	 	 <ul  id="" class="nav nav-tabs  myservice_html yf_bd">
		        	 	<li class="  active "><a href="#mysevice_gz"data-toggle="tab">工长</a></li>
		        	 	<li class=" "  ><a href="#mysevice_shifu"data-toggle="tab">师傅</a></li>
		        	 	<li class=" " ><a href="#mysevice_design"data-toggle="tab">设计师</a></li>
		        	 	<li class=" "  ><a href="#mysevice_jianli"data-toggle="tab">监理师</a></li>
		        	 	<li class=" " ><a href="#mysevice_jiance"data-toggle="tab">检测师</a></li>
        	    </ul>
        </div>
        <div  class=" tab-content" >
<!--工长-->        	
               <div class="tab-pane fade in active" id="mysevice_gz"  data-type="6"   data-subtype="1,2,3" >  
		           <div class="yanfang_navcon">
					        	<div class="yanfang_nctop" >
				                   <ul class="design_specile">
				                   </ul>
					        	 </div>
					</div>
		       		<div class="loading_more"  id="loading_more" >
                  	   <i class="glyphicon glyphicon-menu-down"></i>
                  	       查看更多
                    </div>
               </div>
<!--师傅-->               
		       <div class="tab-pane fade" id="mysevice_shifu"  data-type="4"  data-subtype="7,8,9,10,11,12,15"  >
		       	    <div class="yanfang_navcon">
					        	<div class="yanfang_nctop" >
				                  <ul class="design_specile">
				                  </ul>
					        	</div>
					 </div>
		       		<div class="loading_more"  id="loading_more" >
                  	   <i class="glyphicon glyphicon-menu-down"></i>
                  	       查看更多
                    </div>
		       </div>
<!--设计师-->		       
		       <div class="tab-pane fade" id="mysevice_design"   data-type="5"  data-subtype="" >
		       	     <div class="yanfang_navcon">
					        	<div class="yanfang_nctop" >
				                  <ul class="design_specile">
				                  </ul>
					        	</div>
					 </div>
					 <div class="loading_more"  id="loading_more" >
                  	   <i class="glyphicon glyphicon-menu-down"></i>
                  	       查看更多
                    </div>
		       </div>
		       
<!--监理师-->  
               <div class="tab-pane fade" id="mysevice_jianli" data-type="4"  data-subtype="13" >
		       	     <div class="yanfang_navcon">
					        	<div class="yanfang_nctop" >
				                  <ul class="design_specile">
				                  </ul>
					        	</div>
					 </div>
					 <div class="loading_more"  id="loading_more" >
                  	   <i class="glyphicon glyphicon-menu-down"></i>
                  	       查看更多
                     </div>
				</div>
				
<!--检测师-->
               <div class="tab-pane fade" id="mysevice_jiance" data-type="4"  data-subtype="14">
			       	        <div class="yanfang_navcon">
					        	<div class="yanfang_nctop" >
				                  <ul class="design_specile">
				                  </ul>
					        	</div>
					        </div>
					        <div class="loading_more"  id="loading_more" >
		                  	   <i class="glyphicon glyphicon-menu-down"></i>
		                  	       查看更多
		                    </div>
		                    
		       </div>
        </div>
		<div class="tankuang " ></div>
		<div class="zhezhao" ></div>
	</body>
	 <script src="${ctx}/js/wap/js/jquery-1.11.2.min.js"></script>
	 <script type="text/javascript" src="${ctx}/js/wap/js/bootstrap.min.js"> </script>
	 <script type="text/javascript" src="${ctx}/js/wap/js/pmain.js"></script>
	
	 <script>
	 	$(document).ready(
	 		function()
	 		{
	 			//取出第一页
	 			
	 			/*
	 			$(".nav-tabs").find("li").click(
	 				function()
	 				{
	 					var li=$(this);
	 					if(li.hasClass("active"))
	 					{
	 						var tab_pane=li.find("a").attr("href");
	 						load($(tab_pane));
	 					}
	 				}
	 			);
	 			*/
	 			
	 			$(".tab-pane").each(function(){
	 				new TabPanel($(this));
	 		    });
	 			
	 		}
		);
	 	
	 	
	 	
	 	function TabPanel(tab_pane)
	 	{
	 		var self=this;
	 		this.getPage= function(page,type,subtype)
		 	{
		 		$.ajax({  
	                type:"POST",  
	                url:"wap/myservicepage?type="+type+"&subtype="+subtype+"&page="+page,  
	                dataType:"html",
	                success:function(data){
	                	tab_pane.find(".design_specile").append(data);
	                },
	                error:function(){  
	                }
	            });
		 	}
	 		this.init=function(){
		 			var type=tab_pane.data("type");
		 			var subtype=tab_pane.data("subtype");
		 			self.getPage(1,type,subtype);
		 			tab_pane.data("page",1);
		 			tab_pane.find(".loading_more").click(
		 				function()
		 				{
		 					var pane=self;
		 					var curpage= tab_pane.data("page");
		 					var type=tab_pane.data("type");
		 					var subtype=tab_pane.data("subtype");
		 					self.getPage(++curpage,type,subtype);
		 					tab_pane.data("page",curpage);
		 				}
	 			    );
	 		}
	 		this.init();
	 	}
	 	
	 	
	 	
	 </script>
	
	
</html>
