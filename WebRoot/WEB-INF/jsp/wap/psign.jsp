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
	 <script type="text/javascript" src="${ctx}/js/wap/js/bootstrap.min.js"> </script>
	 <script type="text/javascript" src="${ctx}/js/wap/js/pmain.js"></script>
	
	</head>
	<body class="" >
			<nav class="index clearfix mess_nav nav_bg" >
				<div class="mess_nav1" ><a href="javascript:;" onClick="javascript :history.back(-1);"><i class="glyphicon glyphicon-menu-left" style="color:white"></i></a></div>
				<div class="mess_nav2" >注册</div>
			</nav>
			
			<div class="plogin_body clearfix">

			 
			  <div class="plogin_box" style="padding-top:50px;">
			  	 <ul>
			  	 	<li><input id="tel" class="signnum"  type="tel"  placeholder="请输入电话号码"></li>
			  	 	<li><input id="pass" class="signpass"  type="password"  placeholder="请输入密码"></li>
			  	 	<li><input class="signpass2" type="password"   placeholder="请再次输入密码"></li>
			  	 	<li><input id="yzm" type="text"  placeholder="验证码" style="width: 70%;"><input id="btnSendCode" disabled="true"class="btn btn-danger" type="button" value="获取验证码" onclick="sendMessage()" style="width: 30%;"></li>
			  	 </ul>
			  </div>

			  
		  </div>
			<button  id="loginBtn" class="btn btn-block mybuttom" >注册</button>
		 <div class="tankuang"></div>
	     <div class="zhezhao"></div>
		
		
	</body>
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
			var url = "wap/addUser";
			var tel=$("#tel").val();
			var password=$("#pass").val();
			var yzm=$("#yzm").val();
			var p="yzm="+yzm+"&username="+tel+"&password="+password;
			console.debug(p);
			$.post(url,p,function callback(data) {
				
				
			if(data.success=="true"){
				alert("注册成功！");
				location.href = "${pageContext.request.contextPath}/wap/pmyzone?r="	+ new Date().getTime();
			}else{
				alert("注册失败！");
			}},"json");
		}
		var InterValObj; //timer变量，控制时间
		var count = 60; //间隔函数，1秒执行
		var curCount;//当前剩余秒数
		function sendMessage() {
			console.log("1111111")
			 curCount = count;
			//设置button效果，开始计时
			$("#btnSendCode").attr("disabled", "true");
			$("#btnSendCode").val("请在" + curCount + "秒内输入");
			InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
			//向后台发送处理数据
			 var tel=$("#tel").val();			
				 var tel=$("#tel").val();
					$.post("phoneCode", {
						phone : tel
					});
				
		}
		function SetRemainTime() {
			if (curCount == 0) {
				window.clearInterval(InterValObj);//停止计时器
				$("#btnSendCode").removeAttr("disabled");//启用按钮
				$("#btnSendCode").val("重新发送");
			}
			else {
				curCount--;
				$("#btnSendCode").val("请在" + curCount + "秒内输入");
			}
		}

		</script>
</html>
