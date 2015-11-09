<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib uri="/lystag" prefix="my" %>
<!DOCTYPE html>
<html>
	<head>
	<base href="http://${header['host']}${pageContext.request.contextPath}/">
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
		<title>我要验房</title>
		<link rel="shortcut icon" href="images/ico/ico.png">
		<meta name="keywords" content=""/>
		<meta name="Description" content=""/>
		<link rel="stylesheet" type="text/css" href="css/c1/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="css/c1/main.css"/>
		<script type="text/javascript" src="js/j1/jquery/jquery.js"></script>
		<script type="text/javascript" src="js/j1/jquery/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/j1/main.js"></script>
		    <!--[if lt IE 9]>
    <script type="text/javascript" src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
	</head>

<body style="background-color: white;">
<div class="heght50"></div>
<div class="bg_header" >
     <div class="header clearfix logintop"style="">
       	<div class="logo logintl"style=""><img src="images/logo1.jpg" height="110"/></div>
        <div class="logintc" style="">免费注册</div>
	    <div class="logintr" style="">
	         	已有账号<a href="index/loginp"><span class="redcolor">点此登录</span></a>	   	
	    </div>
      </div>
  
      <div class="signcon">
      	     <div class=" clearfix" >
      	     	    <div class="signcon_left" >
      	     	    <form onsubmit="return checkData();" id="addForm" action="index/intoregtieSave" class="form-horizontal"  method="post" encType="multipart/form-data"  >
      	     	    <input type="hidden"  name="ba_st_type" id="ba_st_type"/>
      	     	    <input type="hidden"  name="ae_st_type" id="ae_st_type"/>
      	     	     <td>
      	     	   	     <table border="0" width="400"cellpadding="10" cellspacing="10" style="text-align:left;"> 
      	     	   	     	
							<tr style="text-align: left">
								 <td class="sign_tablle_td">用户类型：
									 <select id="types" style="width:50%;height:35px;line-height:35px;padding:0px;vertical-align: middle;"  
									 onchange="showChild()">
									 <option  value="" selected="selected">请选择</option>
									 <c:forEach  items="${userType }"  var="val">
      	     	   	     				 <option value="${val.ab_st_value }|${val.ab_st_mark }">${val.ab_st_name }</option>
      	     	   	     			</c:forEach>
									 </select>
								 </td>
							 </tr>
							 <c:forEach  items="${userType }"  var="val">
							 <c:if test="${not empty val.childs }">
							 	<tr style="text-align: left;display: none;" id="${val.ab_st_mark }" class="hideall">
								 <td class="sign_tablle_td">${val.ab_st_name }类型：
									 <select  class="chooseValue" id="" style="width:50%;height:35px;line-height:35px;padding:0px;vertical-align: middle;" onchange="bingChild(this.value)">
									 	 <option  value="" selected="selected">请选择</option>
										 <c:forEach items="${val.childs }" var="child">
												 <option value="${child.ab_st_value }">${child.ab_st_name }</option>
										 </c:forEach>
									 </select>
								 </td>
							 </tr>	
							 </c:if>
							 </c:forEach>
      	     	   	     	<tr>
      	     	   	     		<td>账&nbsp;&nbsp;号：<input class="signname"  id="tel" name="username"  type="tel" placeholder="请输入您的手机号"  onblur="validTel();"><span class="sign_ps"></span></td>
      	     	   	     	</tr>
      	     	   	   
      	     	   	     	<tr>   	     	   	     		
      	     	   	     		<td>设置密码：<input class="signpass"type="password"  name="password"   placeholder="密码由6-16位字母或数字组成" onblur="checkPass();"><span class="sign_ps"></span></td>
      	     	   	     	</tr>
      	     	   	     	<tr>
      	     	   	     		<td>确认密码：<input class="signpass2" id="pwd2"  type="password" placeholder="请再次输入密码" onkeyup="checkPass2();"><span class="sign_ps"></span></td>
      	     	   	     		
      	     	   	     	</tr>
      	     	   	     	
      	     	   	     <!-- 	<tr>
      	     	   	     		<td>验&nbsp;&nbsp;证&nbsp;&nbsp;码：<input id="checkValue" name="yzm" class="signpass" type="text"  placeholder="请输入验证码"><input type="button" onclick="check()" value="发送验证码"></td>
      	     	   	     		
      	     	   	     	</tr> -->
      	     	   	     	<tr>
								 <td style="text-align: left">验&nbsp;证&nbsp;码：
								 <input class="signcode" type="text" id="yzm"  name="yzm"  placeholder="请输入验证码" style="width: 37%;"  onblur="checkCode(this.value)">
								
								     <input id="btnSendCode" disabled="true" type="button" class="btn btn-danger"    value="获取验证码" style="width: 130px;" onclick="sendMessage()">
								 <span class="sign_ps"></span>
								 </td>
							 </tr>
							 <input type="hidden" id="hiddenCode" />
						</table>
      	     	   	     <div class="baoming_bu ">
      	     	   	         <input class="signreading" type="checkbox" checked="checked"/>我已阅读并且同意网众科技的<a href="User_protocol" class="bluecolor">用户协议</a>
      	     	   	     </div>
      	     	   	     <div class="baoming_bu ">
      	          		       <input type="submit" value="注册" />
      	          	     </div>
      	          	     </form>
      	     	   </div>
      	            <div class="signcon_right" style="">
      	            	<span class="sign_rt" style="">微信扫一扫快速登录</span>
      	            	<div class="sign_rpic" style=";">
      	            		<img src="images/c_35.png" />
      	            	</div>
      	            	<div class="sign_rbott" style="">
      	            		<div style="">选择更便捷的登录方式：</div>
      	            		 <a href=""><span class="signfont"><i class="iconfont icon-qq" ></i></span></a>
      	            		 <a href=""><span class="signfont"><i class="iconfont icon-xinlangweibo" ></i></span></a>
      	            	</div>
      	            </div>
      	     </div>
      
      </div>
      
</div>

<!--显示金额-->
 	
	 	
	 	
 <!--  <div class="footer navbar-fixed-bottom" >
  	 <div class="footer1">
        <span>友情链接：</span>
        <span><a href="">搜房</a></span>
        <span class="index_footWrapper_vertical">|</span>
        <span><a href="">中国建筑新闻网——四川</a></span>
        <span class="index_footWrapper_vertical">|</span>
        <span><a href="">天府房产</a></span>
        <span class="index_footWrapper_vertical">|</span>
        <span><a href="">成都旅行社</a></span>
        <span class="index_footWrapper_vertical">|</span>
        <span><a href="">成都楼盘网201409</a></span>
    </div>
    <div class="footer2">
        <span>联系方式：</span>
        <span><a href="">关于我们</a></span>
        <span class="index_footWrapper_vertical">|</span>
        <span><a href="">联系方式</a></span>
        <span class="index_footWrapper_vertical">|</span>
        <span><a href="">免责申明</a></span>
        <span class="index_footWrapper_vertical">|</span>
        <span><a href="">反馈</a></span>
    </div>
    <div class="footer2" style="">
        <span><a href="">www.wangzhong.com</a></span>&nbsp;&nbsp;
        <span><a href="">成都网众天弘科技有限公司</a></span>&nbsp;&nbsp;
        <span><a href="">Copyrighgt©2015</a></span>&nbsp;&nbsp;
        <span><a href="">蜀ICP备15018106号-1</a></span>
    </div> 
  </div>
 -->

</body>

<script>
//sign注册范松验证码时间的函数
var InterValObj; //timer变量，控制时间
var count = 60; //间隔函数，1秒执行
var curCount;//当前剩余秒数
function bingChild(value){
	$("#ba_st_type").val(value);
	
}

function showChild(){
	var value=$("#types").val();
	hideAll();
	if(value!=null&&value!=''){
		
		var arr=value.split("|");
		var id=arr[1];
		$("#"+id).show();
		$("#ae_st_type").val(arr[0]);
		
	}
}

function hideAll(){
	$(".hideall").each(function(){
		$(this).hide();
	});
}

function validTel(){
	var tel=/^0?(13|14|15|18)[0-9]{9}$/;//电话号码正则表达式
    var findBack_tel=$("#tel").val();//号码的值
    console.log( findBack_tel)
    if(tel.test(findBack_tel)){//判断如果是电话号码
    	$.post('index/validTel',
    			{tel:findBack_tel},
    			function(data){
    				if(data=="true"){
    					alert("该手机号已经注册过了！");
    					$("#tel").val("");
    					$("#tel").focus();
    				

    				}else{
    					$(".signname").next(".sign_ps").html("<i class='glyphicon glyphicon-ok green' ></i>");
    				}
    			}
    	);
    }else{
        alert("请输入正确的手机号码！");
        $("#tel").val("");
		$("#tel").focus();
    }
}
function checkPass(){
	var passCh=/^[a-zA-Z0-9_-]{6,16}$/;
	var passNum=$(".signpass").val()
	if(!passCh.test(passNum)){
		$(".signpass").val("");
		$(".signpass").attr("placeholder","请输入6到16位字母或数字");
		/* $(".signpass").next(".sign_ps").html("<i class='glyphicon glyphicon-remove redcolor' ></i>"); */
 	//$(this).focus();
	}else{
		$(".signpass").next(".sign_ps").html("<i class='glyphicon glyphicon-ok green' ></i>");
	}
}
function checkPass2(){
	var pass1=$(".signpass").val();
	/* $(this).val(); */
	if($(".signpass2").val()!=pass1){
		/* $(".signpass2").val("");
		$(".signpass2").attr("placeholder","输入的密码和上次不一样"); */
		$(".signpass2").next(".sign_ps").html("<i class='glyphicon glyphicon-remove redcolor' ></i>");
		//$(this).focus();
	}else{
		console.log($("#signname").val());
		$(".signpass2").next(".sign_ps").html("<i class='glyphicon glyphicon-ok green' ></i>")
		if($("#signname").val()!=""&&pass1!=""&&$(".signpass2").val()!=""){
			$("#btnSendCode").removeAttr("disabled");//启用按钮
		}

	}

}
function sendMessage() {
	curCount = count;
	//设置button效果，开始计时
	$("#btnSendCode").attr("disabled", "true");
	$("#btnSendCode").val("请在" + curCount + "秒内输入");
	InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
	//向后台发送处理数据
	 var tel=$("#tel").val();
	 $.post("phoneCode", {
		phone : tel},function(data){
			var json=eval('('+data+')');
			$("#hiddenCode").val(json.code);
		}
	);
}
//timer处理函数
function SetRemainTime() {
	if (curCount == 0) {
		window.clearInterval(InterValObj);//停止计时器
		$("#btnSendCode").removeAttr("disabled");//启用按钮
		$("#btnSendCode").val("重新发送");
		$("#hiddenCode").val("");
	}
	else {
		curCount--;
		$("#btnSendCode").val("请在" + curCount + "秒内输入");
	}
}

function checkCode(value){

	if(value==''||value==null){
		$(".signcode").next(".sign_ps").html("<i class='glyphicon glyphicon-remove redcolor' ></i>");
	}else{
		var code=$("#hiddenCode").val();
		if(value==code){
			$(".signcode").next(".sign_ps").html("<i class='glyphicon glyphicon-ok green' ></i>")
			
		}else{
			$(".signcode").next(".sign_ps").html("<i class='glyphicon glyphicon-remove redcolor' ></i>");
		}
	}
}

function checkData(){
	var tel=$("#tel").val();
	var password=$(".signpass").val();
	var pwd=$(".signpass2").val();
	var yzm=$("#yzm").val();
	if(tel==null||tel==''){
		alert("请输入用户名");
		return false;
	}
	if(password==null||password==''){
		alert("请输入密码");
		return false;
	}
	if(pwd==null||pwd==''){
		alert("请再次输入密码");
		return false;
	}
	var code=$("#hiddenCode").val();
	if(yzm==null||yzm==''){
		alert("请输入验证码");
		return false;
	}else {
		if(code!=yzm){
			alert("验证码输入错误");
			return  false;
		}
	}
	return true;
}
</script>
</html>
