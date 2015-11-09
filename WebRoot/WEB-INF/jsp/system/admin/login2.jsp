<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
	<base href="http://${header['host']}${pageContext.request.contextPath}/">
	<meta charset="utf-8" /> 
	<title>后台管理系统</title>
	<meta http-equiv=”X-UA-Compatible” content=”IE=Edge,chrome=1″ >
	<meta name="renderer" content="webkit">
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<!-- 禁止IE生成缓存... -->
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Expires" content="0">
	<!-- BEGIN GLOBAL MANDATORY STYLES -->          
	<link href="assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
	<link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<link href="assets/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
	<!-- END GLOBAL MANDATORY STYLES -->
	<!-- BEGIN PAGE LEVEL STYLES --> 
	<link rel="stylesheet" type="text/css" href="assets/plugins/select2/select2_metro.css" />
	<!-- END PAGE LEVEL SCRIPTS -->
	<!-- BEGIN THEME STYLES --> 
	<link href="assets/css/style-metronic.css" rel="stylesheet" type="text/css"/>
<%--	<link href="assets/css/style.css" rel="stylesheet" type="text/css"/>--%>
	<link href="assets/css/style-responsive.css" rel="stylesheet" type="text/css"/>
	<link href="assets/css/plugins.css" rel="stylesheet" type="text/css"/>
	<link href="assets/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
	<link href="css/login.css" rel="stylesheet" type="text/css"/>
	<link href="assets/css/custom.css" rel="stylesheet" type="text/css"/>
	<!-- END THEME STYLES -->
<%--	<link rel="shortcut icon" href="http://${header['host']}${pageContext.request.contextPath}/images/ico/favicon.ico" />--%>
	 <!--[if lt IE 9]>
		<style>
			body{
				filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(
				src='/images/loginBg.jpg',
				sizingMethod='scale');
			}
		</style>
	<![endif]-->
	<style type="text/css">
		*, *:before, *:after {box-sizing:content-box}
		a {color: #ffffff;text-decoration: none;}
		.modal-title{color: #000;}
	</style>
	 <sec:authorize ifAnyGranted="aftermain_into">
		<script>
			location.href="${pageContext.request.contextPath}/system/admin/initIndex";//如果管理员已经登录过了，可以直接跳入首页，可用于其他登录过的用户等 
		</script>
	</sec:authorize>
</head>
	
<!-- BEGIN BODY -->
<body class="login">
<noscript>
		<div id="javaScr_ys" class="overlay">对不起，您的浏览器禁止JavaScript脚本！</div>
	</noscript>
    <div class="loginBox">
    	<form class="login-form loginCon" action="" method="post">	
	    	<div class="title">
	        	<h3>后台项目管理系统</h3>
	            <h4>用户登录</h4>
	        </div>	
	        <div class="form-group">
	       		<div class="input_username input-icon">
	        		<input class="input_250" type="text" name="username"/>
	        	</div>
	        </div>
	        <div class="form-group">
	        	<div class="input_password input-icon">
	        		<input class="input_250" type="password"  name="password"/>
	        	</div>
	        </div>
	        <%--<div class="yzm">
	        	<div class="input_yzm form-group">
	        	<div class=" input-icon" style="height: 40px;">
	        		<input class="input_120"   name="validateCode"/>
	        		</div>
	        	</div>
	            <img  id="img_c" onclick="changecode();" src="" align="absmiddle"  style="border:0px;display:inline;"/>
	        </div>
	        --%>
	        <div class="forgot_pw">
	        	<span class="floatL"><label><input class="checkbox" type="checkbox" name="rememberMe" value="true" />记住密码</label></span>
	            <%--<a class="floatR">忘记密码？</a> --%>
	       </div>
	        <div class="clear"></div>
	        <a class="loginBtn" id="loginBtn">登录</a>
        </form>
        <div class="footer">
            <p>后台项目管理系统</p>
            <p>Copyright 2014 All Rights Reserved</p>
    	</div>
    </div>
	<!-- END COPYRIGHT -->
	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->   
	<!--[if lt IE 9]>
	<script src="assets/plugins/respond.min.js"></script>
	<script src="assets/plugins/excanvas.min.js"></script> 
	<![endif]-->   
	<script src="assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
	<script src="assets/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
	<script src="assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="assets/plugins/bootstrap-hover-dropdown/twitter-bootstrap-hover-dropdown.min.js" type="text/javascript" ></script>
	<script src="assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="assets/plugins/jquery.blockui.min.js" type="text/javascript"></script>  
	<script src="assets/plugins/jquery.cookie.min.js" type="text/javascript"></script>
	<script src="assets/plugins/uniform/jquery.uniform.min.js" type="text/javascript" ></script>
	<!-- END CORE PLUGINS -->
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script src="assets/plugins/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>	
	<script type="text/javascript" src="assets/plugins/select2/select2.min.js"></script>     
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- Alert -->
	<script src="assets/plugins/bootbox/bootbox.js"></script>
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="assets/scripts/app.js" type="text/javascript"></script>
	<script src="assets/scripts/login.js" type="text/javascript"></script>
</body>
<!-- END BODY -->
</html>
<!-- END PAGE LEVEL SCRIPTS --> 
<script>
	//初始化页面
	jQuery(document).ready(function() {     
		App.init();
		Login.init();
		judge();//判断浏览器版本 
	});
</script>
<!-- END JAVASCRIPTS -->
<script type="text/javascript">
	$(function() {
		//登录按钮触发事件
		$("#loginBtn").click(function() {
			onLogin();
		});
		/*changecode();//初始化验证码*/
	});
	//键盘键入函数
	function checkKey(e) {
		var e=e||event;
		var currKey=e.keyCode||e.which||e.charCode;
		if(13 == currKey){
			if($("button[data-bb-handler='ok']").length>0){
				$("button[data-bb-handler='ok']").click();
			}else{
				onLogin();
			}
		}
	}
	//登录函数
	function onLogin(){
		if(judge()){
		if ($('.login-form').validate().form()) {
			var url = "login";
			var params=$(".login-form").serialize();
			$.post(url,params,function callback(data){  
				var resp = eval("("+data+")"); 
				if(resp.success){
					location.href="${pageContext.request.contextPath}/system/admin/initIndex?r="+new Date().getTime();
				}else{ 
					bootbox.alert(resp.msg);
					/*if(resp.errorType=="codeError"){
						changecode();//重新获取验证码
					}*/
				}
			});
		}
		}
	}
	//键盘键入事件
	document.onkeydown=checkKey;
	//获取验证码函数
	/*function changecode(){
		$("#img_c")[0].src='imageCode?r='+new Date().getTime();//加个这个目的是让IE 能识别及时的相同url访问 
		$("input[name='validateCode']").val("");//清空验证码值
	}*/
	//判断IE浏览器版本
	function judge(){
		if(navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion .split(";")[1].replace(/[ ]/g,"")< "MSIE8.0"){
			bootbox.confirm('对不起！您当前浏览器版本过低，故请升级该浏览器！',function(rs){
				if(rs){
					window.location="http://ie.microsoft.com/";
				}else{
					bootbox.alert('对不起！如因网络原因不能升级，请选择非IE浏览器！');
				}
			});
			return false;
		}
		return true;
	}
</script>


