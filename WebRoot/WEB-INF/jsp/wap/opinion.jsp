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
	<body class="" style="background-color: #F4F4F4">
		<form method="post" action="wap/addAdvice">	
			<nav class="index clearfix mess_nav nav_bg"  >
				<div class="mess_nav1" ><a href="wap/pmyzone" ><i class="glyphicon glyphicon-menu-left" style="color: white"></i></a></div>
				<div class="mess_nav2" >意见反馈</div>
				<!-- <div class="mess_nav1 index_share" onclick="tkopeng(this,121)"><i class="iconfont icon-fenxiang"></i></div> -->
			</nav>
		     <div class="opinon_boxcon" style="padding-top:20px;">
	          	  <textarea name="bk_st_content"  placeholder="请输入意见反馈（200字以内）">
	          	  </textarea>
	          	  <input class="btn mybuttom" type="submit" value="提交" style="margin-left;5%;"/>
	          	  <!-- 
	              <a class="btn mybuttom" onclick="tkopeng(this,555)">提交</a>
	               -->
	              <div class="tankuang" ></div>
                  <div class="zhezhao" ></div>
		     </div>
		</form>
	</body>
	 <script src="${ctx}/js/wap/js/jquery-1.11.2.min.js"></script>
	 <script type="text/javascript" src="${ctx}/js/wap/js/bootstrap.min.js"> </script>
	 <script type="text/javascript" src="${ctx}/js/wap/js/pmain.js"></script>
</html>
