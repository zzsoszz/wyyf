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
<body class="indexbody">

				<nav class="index navbar clearfix design_nav nav_bg">
					<div class="mess_nav1" style=""><a href="wap/myshop" ><i class="glyphicon glyphicon-menu-left"style="color: white"></i></a></div>
					 <div class="design_nav2"  style="">商家列表</div> 
					<!-- <div class="mess_nav1 index_share" onclick="tkopeng(this,121)"><i class="iconfont icon-fenxiang"></i></div> -->
				</nav>
				<div  id="shopList">
					
					</div>
					<style>
	.dsignmore{width: 100px;height: 50px;line-height: 50px;background-color: #F7F7F7;color: #868686;cursor: pointer;margin: 0 auto;}
	
	</style>
	<div class="dsignmore" style="0"  onclick="getShopList()" id="next" lang="0" >
		&nbsp;&nbsp;&nbsp;&nbsp;<i class="glyphicon glyphicon-arrow-down"></i>下一页
	</div>
				
                <div class="tankuang"></div>
                <div class="zhezhao"></div>



</body>
<script src="${ctx}/js/wap/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/wap/js/bootstrap.min.js"> </script>
<script type="text/javascript" src="${ctx}/js/wap/js/pmain.js"></script>
<script type="text/javascript">
$(window).load(function () {
	getShopList(1);
	});
	function getShopList() {//退款
		var p=	parseInt($("#next").attr("style"))+1;
		var num=	parseInt($("#next").attr("lang"));
		/* if(num==p&&num!=0){
			$("#next").remove();
		}
	 */
		$.post("wap/getShopList", {
			"pageIndex" : p
		}, function(data) {
			
			var lengthNum=data.list.length;//lang
			$("#next").attr('style',data.currPageNumber);
			$("#next").attr('lang',data.totalPages);
			
			for(i=0 ; i<lengthNum;i++){
				var line="<li> <a href=\"wap/specilepriceProduct?id="+data.list[i].bb_st_userid+"\"><img src="+data.list[i].ag_st_url+" class=\"img-responsive\" width=\"100%\"></a></li>";
				$("#shopList").append(line);
				
			}

		}, "json");
	}
</script>
</html>
