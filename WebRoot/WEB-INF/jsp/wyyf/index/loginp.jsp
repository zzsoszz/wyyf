<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ page import="com.utils.CookieUtil" %>
	<%
	Cookie cookie=CookieUtil.getCookieByName(request, "userName");
	Cookie cookie2=CookieUtil.getCookieByName(request, "password");
	Cookie cookie3=CookieUtil.getCookieByName(request, "flag");
	String userName="";
	String password="";
	Boolean flag=false;
	if(cookie3!=null){
		flag=Boolean.valueOf(cookie3.getValue());
	}
	if(cookie!=null&&flag){
		userName=cookie.getValue();
	}
	if(cookie2!=null&&flag){
		password=cookie2.getValue();
	}
	
%>
<!DOCTYPE html >
<html>
<head>
<base href="http://${header['host']}${pageContext.request.contextPath}/">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>忘记密码</title>
<meta name="keywords" content="" />
<meta name="Description" content="" />
<link rel="shortcut icon" href="images/ico/ico.png">
<link rel="stylesheet" href="css/c1/bootstrap.min.css" />
<link rel="stylesheet" href="css/c1/main.css" />
<link rel="stylesheet" type="text/css" href="css/c1/layout.css" />
<link rel="stylesheet" type="text/css" href="css/c1/style.css" />
<script type="text/javascript" src="js/j1/jquery/jquery.js"></script>
<script type="text/javascript" src="js/j1/jquery/bootstrap.min.js"></script>
<script type="text/javascript" src="js/j1/jquery/superSlide.js"></script>
<script type="text/javascript" src="js/j1/main.js"></script>

</head>
<body>

<div class="header clearfix logintop" style="">
       	<div class="logo logintl"><img src="images/logo1.jpg" height="110"></div>
        <div class="logintc" style="">登录</div>
	    <div class="logintr" style="">
	         	<a href="index.html">回到首页</a>
	    </div>
      </div>
<div class="bg_header">
      <div class="logincon">
      	     <div class="logincon_box">
      	     	  <div class="logincon_boxtop">用户登录</div>
      	          <form id="aaa" class="loginform" action="#" method="post">
      	          	<input type="hidden" name="sfcode" value="0"/>
      	          	<div class="baoming_in">
      	          		 账户:<input type="text" name="username"  autocomplete="on" value="<%=userName%>">
      	          	</div>
      	          	 <div class="baoming_in">
      	          		 密码:<input type="password" name="password"  autocomplete="on" value="<%=password%>">
      	          	</div>
      	          	<div class="loginform_inpu1">
      	          		<input type="checkbox" name="flag"  value="1" <%=flag?"checked":"" %>>
      	          		记住密码
      	          		<a href="index/findPwd" style="">忘记密码</a>     	          	  
      	          	</div>
      	          	<div class="baoming_bu loginform_inpu3">
      	          		 <input id="loginBtn" value="登录"  type="button">
      	          	</div>
      	          	<div class="loginform_inpu2">
      	          		<a href="index/intoregtie">立即注册&gt;&gt;</a>
      	          	</div>
      	          	<input type="hidden"  name="oldRequestUrl"  id="oldRequestUrl"  value="${oldRequestUrl }"/>
      	          </form>
      	     </div>
      </div>
      
</div>

<script type="text/javascript">
	$(function() {
		var oldRequestUrl="${oldRequestUrl}";
		//登录按钮触发事件
		$("#loginBtn").click(function() {
			onLogin();
		});
	});
	//登录函数 
	function onLogin(){
		var url = "login";
		var params=$("#aaa").serialize();
		$.post(url,params,function callback(data){  
			var resp = eval("("+data+")"); 
			if(resp.success){
				var oldRequestUrl=$("#oldRequestUrl").val();
				if(oldRequestUrl!=null&&oldRequestUrl!=''){
					location.href=oldRequestUrl;
				}else{
					location.href="${pageContext.request.contextPath}/index?r="+new Date().getTime();
				}
					
				
			}else{ 
				alert("登录失败！"+resp.msg);
			}
		});
	}
</script>


</body>
</html>
