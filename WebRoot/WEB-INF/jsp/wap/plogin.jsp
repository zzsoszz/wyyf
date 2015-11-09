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
		<script src="${ctx}/js/wap/js/jquery-1.11.2.min.js"></script>
	 <script type="text/javascript" src="${ctx}/js/wap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/wap/js/cav.js"></script>
	<script type="text/javascript" src="${ctx}/js/wap/js/pmain.js"></script>
	<script type="text/javascript" src="js/j1/jquery/superSlide.js"></script>
	</head>
	<body class=""  >
	<!--plogin_body-->
		<nav class="index clearfix mess_nav nav_bg" >
			<div class="mess_nav1" ><a href="javascript:;" onClick="javascript :history.back(-1);"><i class="glyphicon glyphicon-menu-left"style="color:white "></i></a></div>
			<div class="mess_nav2" >登陆</div>
		</nav>
		
      	          	<input type="hidden" id="h2" name="sfcode" value="0"/>
        <div  id="container"  class="plogin_body clearfix" style="background-size: cover;">
			<!--<div  id="anitOut"style="position: relative;padding: 0px;" >-->
                <!--<div style="position: absolute;left:50%;top:-20px;margin-left: -75px;;z-index: 3;">-->
				
					
					<div class="plogin_box" style="padding-top:50px;" >
						<ul>
							<li><img src="${ctx}/images/wap/images/phone.png" width="25"/><input id="tel" type="tel" placeholder="请输入电话号码"></li>
							<li><img src="${ctx}/images/wap/images/pass.png" width="25"/><input id="pass" type="password"  placeholder="请输入密码"></li>
						</ul>
					</div>
						<button id="loginBtn" class="btn btn-block mybuttom">登陆</button>
	               <input type="hidden" id="h1"  name="oldRequestUrl"  id="oldRequestUrl"  value="${oldRequestUrl }"/>
      	         
				<!--</div>-->

			<!--</div>-->
			  
		</div>

	
        <div class="plogin_share navbar-fixed-bottom">
        	<!--   <ul>
        	  	<li >
        	  	      <img src="${ctx}/images/wap/images/QQ.png" width="25" />
        	  	      <p>QQ登陆</p>
        	  	</li>
        	  	<li>
        	  	      <img src="${ctx}/images/wap/images/09支付图标3.png" width="25" />
        	  	      <p>微信登陆</p>
        	  	</li>
        	  	<li>
        	  	      <img src="${ctx}/images/wap/images/weibo.png" width="25" />
        	  	      <p>微博登陆</p>
        	  	</li>
        	  </ul> -->
        	  
        </div>
	    <div class="tankuang"></div>
	    <div class="zhezhao"></div>

	<script type="text/javascript">
		$(function() {
			var oldRequestUrl = "${oldRequestUrl}";
			//登录按钮触发事件
			$("#loginBtn").click(function() {
				onLogin();
			});
		});
		//登录函数 
		function onLogin() {
			var url = "login";
			var tel=$("#tel").val();
			var password=$("#pass").val();
			var oldRequestUrl=$("#h1").val();
			
				var p="sfcode=0&username="+tel+"&password="+password+"&oldRequestUrl="+oldRequestUrl+"&r="+ new Date().toString();;
				
			console.debug(p);
			$.post(url,p,function callback(data) {
								var resp = eval("(" + data + ")");
								
								if (resp.success) {
									
									var oldRequestUrl = $("#oldRequestUrl")
											.val();
									if (oldRequestUrl != null
											&& oldRequestUrl != '') {
										location.href = oldRequestUrl;
									} else {
										location.href = "${pageContext.request.contextPath}/wap/pmyzone?r="
												+ new Date().getTime();
									}

								} else {
									alert("登录失败！" );
									/* location.href = "${pageContext.request.contextPath}wap/plogin?r="
											+ new Date().getTime(); */
								}
							});
		}
	</script>

</body>
	 
</html>
