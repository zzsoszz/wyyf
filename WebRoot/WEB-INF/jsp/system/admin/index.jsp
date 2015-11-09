<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="http://${header['host']}${pageContext.request.contextPath}/">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>后台管理系统</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
		<link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		<link href="assets/css/style-metronic.css" rel="stylesheet" type="text/css" linkid="metronic"/>
		<link href="css/index.css" rel="stylesheet" type="text/css" />
		<!--[if lt IE 9]>
		<style>
			.pageWrapper{
				filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(
				src='/images/bg.jpg',
				sizingMethod='scale');
			}
		</style>
	<![endif]-->
	</head>
	<body>
		<div class="pageWrapper">
			<div class="header">
		    	<div class="content">
		        	<div class="title">后台管理系统</div>
		            <div class="signOut">
		            	<ul>
		                	<li><img alt="" src="assets/img/avatar1_small.png" width="30" height="30" style="float: left; padding-top: 8px; padding-right: 5px;"/><span class="username">${username }</span></li>
		                    <li>|</li>
		                    <li><a href="/logout">注销</a></li>
		                </ul>
		            </div>
		        </div>
		    </div>
		    <div class="pageMain">
				<a class="part sp" href="system/admin/initMain?functionid1=33ea0dbfc45e4372b82809b330e0fc92"><span>审批</span><i class="icon_sp"></i></a>
		        <a class="part hz" href="system/admin/initMain?functionid1=73e19a518d8d464ea421806e551f0e68"><span>核准</span><i class="icon_hz"></i></a>
		        <a class="part ba mr_0" href="system/admin/initMain?functionid1=f4570e341d7047cdba30b2c662a86419"><span>备案</span><i class="icon_ba"></i></a>
		        <a class="part zhcx" href="system/admin/initMain?functionid1=440da020984b4c488cd13ba15144461f"><span>综合查询</span><i class="icon_zhcx"></i></a>
		        <a class="part tjfx" href="system/admin/initMain?functionid1=b19a6345044f4bb99635a826295a789d"><span>统计分析</span><i class="icon_tjfx"></i></a>
		        <a class="part xtgl mr_0"  href="system/admin/initMain?functionid1=d32debb50ee0484daea6ee8e208670bf"><span>系统管理</span><i class="icon_xtgl"></i></a>
		    </div>
		    <div class="footer">
		    	<p>后台管理系统</p>
		        <p>Copyright 2014 All Rights Reserved</p>
		    </div>
		</div>
		<script src="assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
		<script src="assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- Alert -->
		<script src="assets/plugins/bootbox/bootbox.js"></script>
	</body>
</html>
<script type="text/javascript">
<!--
	function msg(){
		bootbox.alert("暂无权限访问...");
	}
//-->
</script>
