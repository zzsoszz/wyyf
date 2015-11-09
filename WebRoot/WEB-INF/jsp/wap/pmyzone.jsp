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
		<nav class="index clearfix mess_nav" >
			<div class="mess_nav1" ><a href="wap" ><i class="glyphicon glyphicon-menu-left"></i></a></div>
			<div class="mess_nav2" >我的个人中心</div>
			<!-- <div class="mess_nav1 index_share" onclick="tkopeng(this,121)"><i class="iconfont icon-fenxiang"></i></div> -->
		</nav>
		<div id="container" class="mess_con1"  style="height: 220px;">
			  <div id="anitOut" style="position: relative;padding: 0px;"  class="col-xs-12 col-sm-12 mess_con1_box1" align="center" >
				 
				  <div style="position: absolute;left:50%;top:-20px;margin-left: -75px;padding-top:80px;;z-index: 3;">
				       <c:if test="${not empty user }">
				       <p>${user.ae_st_nickName }</p>
				        <p>${user.username }</p>
				       </c:if>
				         <c:if test="${ empty user }">
					  <a href="wap/plogin" class="mybutt mybuttlogin">登陆</a><a href="wap/psign" class="mybutt mybuttsign" >注册</a>
				       </c:if>
				 
				  </div>
			  </div>
		</div>
		<div class="pmyzone_list" style="width: 90%;margin: 0 auto;">
			  <ul>
			        <c:if test="${not empty user }">
			        <li><img src="${ctx}/images/wap/images/my01.png" width="25"><a href="wap/myservice">我的服务</a></li>
					<li><img src="${ctx}/images/wap/images/my02.png" width="25"><a href="wap/payment">我的订单</a></li>
					<li><img src="${ctx}/images/wap/images/my03.png" width="25"><a href="wap/opinion">意见反馈</a></li>
					<li><img src="${ctx}/images/wap/images/my04.png" width="25"><a href="wap/paboutus">关于我们</a></li>
					<li><img src="${ctx}/images/wap/images/my04.png" width="25"><a href="wap/logout">退出登录</a></li>
			        </c:if>
			        <c:if test="${ empty user }">
			        <li><img src="${ctx}/images/wap/images/my03.png" width="25"><a href="wap/opinion">意见反馈</a></li>
					<li><img src="${ctx}/images/wap/images/my04.png" width="25"><a href="wap/paboutus">关于我们</a></li>
			        </c:if>
					
			  </ul>
		</div>

		<div class="tankuang"></div>
		<div class="zhezhao"></div>
		
		
	</body>
	<script src="${ctx}/js/wap/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/wap/js/bootstrap.min.js"> </script>
	<script type="text/javascript" src="${ctx}/js/wap/js/cav.js"></script>
	<script src="${ctx}/js/wap/js/pmain.js"></script>
	<script type="text/javascript">
	  $(function(){
		  mycav();
	  })
	
	</script>
	
</html>