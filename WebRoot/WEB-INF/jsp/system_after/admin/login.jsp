<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../commons/jsplib.jsp"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
	<base href="${ctx}/">
	<meta charset="utf-8" /> 
	<title>后台管理系统</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
	<meta name="MobileOptimized" content="320">
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
	<link href="assets/css/style.css" rel="stylesheet" type="text/css"/>
	<link href="assets/css/style-responsive.css" rel="stylesheet" type="text/css"/>
	<link href="assets/css/plugins.css" rel="stylesheet" type="text/css"/>
	<link href="assets/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
	<link href="assets/css/pages/login.css" rel="stylesheet" type="text/css"/>
	<link href="assets/css/custom.css" rel="stylesheet" type="text/css"/>
	<!-- END THEME STYLES -->
	<link rel="shortcut icon" href="${ctx}/images/ico/favicon.ico" />
	<!--[if lt IE 9]>
		<style>
			.login {
				background: #777 url('assets/img/bg_login1.png') no-repeat fixed top;
			}
		</style>
	<![endif]--> 
	<style>
		#javaScr_ys{
			background: #fff;
			line-height: 100px;
			text-align: center;
			font-size: 40px;
			color: #F00;
		}
		.overlay {
			background: #fff;
			filter: alpha(opacity=80);
			opacity: 0.8; 
			top: 0px;
			left: 0px;
			width: 100%;
			height: 100%;
			z-index: 100; 
			position:absolute;
		}
	</style> 
</head>
	
<!-- BEGIN BODY -->
<body class="login">
<noscript>
		<div id="javaScr_ys" class="overlay">对不起，您的浏览器禁止JavaScript脚本！</div>
	</noscript>
	<!-- BEGIN LOGO -->
	<div class="logo">
<%--		<img src="images/logo/logo_login.png" alt="" height="80" width="80" /> --%>
		<p>后台管理系统</p>
	</div>
	<!-- END LOGO -->
	<!-- BEGIN LOGIN -->
	<div class="content">
		<!-- BEGIN LOGIN FORM -->
		<form class="login-form" action="" method="post">
			<input type="hidden" name="sfcode" value="0"/>
			<h3 class="form-title"><i class="fa fa-user"></i>用户登录</h3>
			<%--<div class="alert alert-danger display-hide">
				<button class="close" data-close="alert"></button>
				<span>请输入你的帐号、密码和验证码.</span>
			</div>
			--%><div class="form-group">
				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
				<label class="control-label visible-ie8 visible-ie9">帐号</label>
				<div class="input-icon">
					<i class="fa fa-user"></i>
					<input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="帐号" name="username"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label visible-ie8 visible-ie9">密码</label>
				<div class="input-icon">
					<i class="fa fa-lock"></i>
					<input class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="密码" name="password"/>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label visible-ie8 visible-ie9">验证码</label>
						<div class="input-icon input-group">
							<i class="fa fa-lock"></i>
							<input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="验证码" name="validateCode"/>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label visible-ie8 visible-ie9">&emsp;</label>
						<div class="input-icon input-group">
							<img  id="img_c" onclick="changecode();" src="" align="absmiddle"  style="border:0px;display:inline;"/>
						</div>
					</div>
				</div>
			</div>
			<%--<div class="row">
				<label class="checkbox">
				<input type="checkbox" name="rememberMe" value="true"/> 记住密码
				</label>
			</div>
			--%><div class="forget-password">
				<button id="loginBtn" type="button" class="btn blue">
				登录 <i class="m-icon-swapright m-icon-white"></i>
				</button>
				<!-- <h4>忘记密码 ?</h4>
				<p>
					不用担心，请点击 <a href="javascript:;"  id="forget-password">这里</a>
					重置你的密码.
				</p>
				 -->
			</div>
		</form>
		<!-- END LOGIN FORM -->        
		<!-- BEGIN FORGOT PASSWORD FORM -->
		<form class="forget-form" action="index.html" method="post">
			<h3 >忘记密码 ?</h3>
			<p>在下面输入您的电子邮件地址来重设密码.</p>
			<div class="form-group">
				<div class="input-icon">
					<i class="fa fa-envelope"></i>
					<input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="邮箱" name="email" />
				</div>
			</div>
			<div class="form-actions">
				<button type="button" id="back-btn" class="btn">
				<i class="m-icon-swapleft"></i> 返回
				</button>
				<button type="submit" class="btn green pull-right">
				提交 <i class="m-icon-swapright m-icon-white"></i>
				</button>            
			</div>
		</form>
		<!-- END FORGOT PASSWORD FORM -->
	</div>
	<!-- END LOGIN -->
	<!-- BEGIN COPYRIGHT -->
	<div class="copyright">
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
		changecode();//初始化验证码
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
					location.href="${pageContext.request.contextPath}/system/admin/initMain?r="+new Date().getTime();
				}else{ 
					bootbox.alert(resp.msg);
					if(resp.errorType=="codeError"){
						changecode();//重新获取验证码
					}
				}
			});
		}
		}
	}
	//键盘键入事件
	document.onkeydown=checkKey;
	//获取验证码函数
	function changecode(){
		$("#img_c")[0].src='imageCode?r='+new Date().getTime();//加个这个目的是让IE 能识别及时的相同url访问 
		$("input[name='validateCode']").val("");//清空验证码值
	}
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


