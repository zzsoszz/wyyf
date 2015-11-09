 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="jsplib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<title></title>

<!-- <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" /> -->
<!--<script src="js/jquery-1.11.2.min.js"></script>
 <script type="text/javascript" src="js/bootstrap.min.js"></script>
<script src="js/pmain.js"></script>
<script type="text/javascript" src="js/cav.js"></script> -->

 <link rel="stylesheet" type="text/css" href="${ctx}/css/wap/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/wap/css/main.css"/> 
<script src="${ctx}/js/wap/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/wap/js/bootstrap.min.js"> </script>
<script src="${ctx}/js/wap/js/pmain.js"></script>
<script type="text/javascript" src="${ctx}/js/wap/js/cav.js"></script> 


</head>
 
<body>

	<!--<div class="page">-->
		<%@ include file="top.jsp"%>
	<div  id="shopList"lang="0" class="0">
		
		
		
		</div>
	<!-- 		<style>
	.dsignmore{width: 100px;height: 50px;line-height: 50px;background-color: #F7F7F7;color: #868686;cursor: pointer;margin: 0 auto;}
	
	</style> -->
	
	<div class="loading_more"    onclick="javascript:getShopList()" id="next" lang="0">
		&nbsp;&nbsp;&nbsp;&nbsp;<i class="glyphicon glyphicon-arrow-down" id="idvalue" lang="${id}"></i>下一页
	</div>
			
<script type="text/javascript">
$(window).load(function () {
	getShopList(1);
	});
	function getShopList() {//退款
		var p=	parseInt($("#next").attr("style"))+1;
		var num=	parseInt($("#next").attr("lang"));
		if(num==p&&num!=0){
			$("#next").remove();
		}
	
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

	
		    <div class="tankuang"></div>
		    <div class="zhezhao"></div>
		    
			<%@ include file="footer.jsp"%>
	<!--</div>-->
</body>




</html>