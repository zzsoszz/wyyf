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
		<nav class="index clearfix mess_nav" style="background-color: #1E76B8;color: white">
			<div class="mess_nav1" ><a href="javascript:;" onClick="javascript :history.back(-1);"><i class="glyphicon glyphicon-menu-left"style="color: white;"></i></a></div>
			<div class="mess_nav2" >评价</div>
			<!-- <div class="mess_nav1 index_share" onclick="tkopeng(this,121)"><i class="iconfont icon-fenxiang"></i></div> -->
		</nav>
		<c:if test="${sjs ==0 }">
		<div class="preview_top">
            <p class="preview_topp">网众竭诚为您服务，网众设计师为您精心打造的房屋设计图。</p>
			  <img src="${user.ag_st_url }" class="img-circle" width="30%">
			<p>刘女士</p>
			<p class="preview_ti">户型设计图</p>
			<dl class="clearfix">
			<c:forEach items="${data }" var="v">
			<dd><img src="${v.ag_st_url}" class="img-responsive"></dd>
			</c:forEach>
			</dl>
		</div>
		</c:if>
		
		<c:if test="${empty pj }">
		<div class="" style="width: 100%;background-color: #F4F4F4;padding-bottom: 10px;">
			<ul class="preview_ul">
				<li>服务态度:<span><i class="glyphicon glyphicon-star"></i><i class="glyphicon glyphicon-star"></i><i class="glyphicon glyphicon-star"></i><i class="glyphicon glyphicon-star"></i><i class="glyphicon glyphicon-star"></i></span></li>
				<li>服务诚信:<span><i class="glyphicon glyphicon-star"></i><i class="glyphicon glyphicon-star"></i><i class="glyphicon glyphicon-star"></i><i class="glyphicon glyphicon-star"></i><i class="glyphicon glyphicon-star"></i></span></li>
				<li>服务质量:<span><i class="glyphicon glyphicon-star"></i><i class="glyphicon glyphicon-star"></i><i class="glyphicon glyphicon-star"></i><i class="glyphicon glyphicon-star"></i><i class="glyphicon glyphicon-star"></i></span></li>
			</ul>
		</div>
        <div class="opinon_box" >
	          <div class="opinon_boxcon" >
	          	<textarea name="" id="content" placeholder="请输入意见反馈（200字以内）"></textarea>
	             <!-- <a class="btn mybuttom" onclick="tkopeng(this,666)">提交</a> -->
	              <a class="btn mybuttom" onclick="submit()">提交</a>
	              <div class="tankuang" ></div>
                  <div class="zhezhao" ></div>
	          </div>
		</div>

		</c:if>
		
	</body>
	 <script src="${ctx}/js/wap/js/jquery-1.11.2.min.js"></script>
	 <script type="text/javascript" src="${ctx}/js/wap/js/bootstrap.min.js"> </script>
	 <script src="${ctx}/js/wap/js/pmain.js"></script>
		<script>
		
		
		(function($){
	 		$.getUrlParam = function(name)
	 		{
	 		var reg
	 		 = new RegExp("(^|&)"+
	 		 name +"=([^&]*)(&|$)");
	 		var r
	 		 = window.location.search.substr(1).match(reg);
	 		if (r!=null) return unescape(r[2]); return null;
	 		}
	 		})(jQuery);
		
			//获取购物车商品
			function submit() {
			var url = "wap/addAssess";
			var id=$.getUrlParam("id");
			var isShop=$.getUrlParam("isShop");
			var content = $("#content").val();
			var data ;
			if(isShop==2){
				 data = "json={\"be_nm_integrity\":\"5\",\"be_nm_manner\":\"5\",\"be_st_content\":\""+content+"\",\"be_nm_quality\":\"5\",\"isshopassess\":\"1\",\"be_st_bgid\":\""+id+"\"}";
			}else{
				 data = "json={\"be_nm_integrity\":\"5\",\"be_nm_manner\":\"5\",\"be_st_content\":\""+content+"\",\"be_nm_quality\":\"5\",\"isshopassess\":\"0\",\"be_st_jbgid\":\""+id+"\"}";
			}
			
		
				console.debug(data);
				$
						.post(
								url,
								data,
								function callback(data) {
									console.debug(data.success);
		
									if (data.success == "true") {
										tkopeng(1,666);
										/* location.href = "${pageContext.request.contextPath}/wap/payfororderid?pkid="
												+ data.order; */
		
									} else {
										alert(data.msg);
		
									}
								}, "json");
			}
		</script>
</html>
