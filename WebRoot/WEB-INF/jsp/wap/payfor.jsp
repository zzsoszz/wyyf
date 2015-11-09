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
		
		<%@ include file="top.jsp"%>
		
         <div class="payfor_con" style="" align="center">
         	      <div><img src="${ctx}/images/wap/images/09.png"></div>
         	      <div>待支付金额<span class="recolor"> 
         	    ${total }
         	  <%--   <c:forEach items="${order}" var="v">
         	    
         	    ${v }
         	    </c:forEach> --%>
         	    <%-- <c:choose> 
         	      <c:when test="${tag ==1 }">
         	       ${order.pricheAll}
         	      </c:when>
                  <c:otherwise>   
				  ${order.bh_st_spprice}
				  </c:otherwise>
                 </c:choose> --%>

			</span>元</div>
         	      <ul class="payfor_check clearfix" style="padding-left:0px;">
         	      	<!--<img src="${ctx}/images/wap/images/092.png" width="160">-->
         	     	<!-- <li><a onclick="pay(4)" href="javascript:void(0)">支付宝</a></li> 
         	     	<li><a onclick="pay(2)" href="javascript:void(0)">微信支付</a></li>-->
         	     	<li><a onclick="pay(5)" href="javascript:void(0)">银联卡支付</a></li>
         	     </ul>
         </div>
<input type="hidden"  id="num" value=${order.bh_st_ddnum}/>
		
	</body>
	 <script src="../js/pingpp_pay.js"></script>
	  <script src="${ctx}/js/wap/js/jquery-1.11.2.min.js"></script>
	 <script type="text/javascript" src="${ctx}/js/wap/js/bootstrap.min.js"> </script>
	 <script type="text/javascript">
		/* $(function() {
			//登录按钮触发事件 
			$("#loginBtn").click(function() {
				onLogin();
			});
			$("#yzf").click(function() {
				yzf();
			});
		}); */
		//获取购物车商品g
		var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};
		function pay(t) {//测试下单
			var url = "services/charge";
		    var type=t;
		  
		 
		    var body=getUrlParameter("body");
		    var data;
		    var orderNo=getUrlParameter("pkid");
		     if(orderNo==null){
		    	orderNo="${order.bh_st_ddnum}";
		    } 
		  
		    if(body!=null){
		     data="json={\"type\":"+type+",\"isios\":\"3\",\"orderNo\":\""+orderNo+"\",\"body\":'apply'}";
		    }else{
			 data="json={\"type\":"+type+",\"isios\":\"3\",\"orderNo\":\""+orderNo+"\",\"body\":'goods'}";
		    	
		    }
		  
			$.post(url,data,function callback(data) {
				console.debug(data);
				pingpp.createPayment(data, function(result, error){
				    if (result == "success") {
				        // 只有微信公众账号 wx_pub 支付成功的结果会在这里返回，其他的 wap 支付结果都是在 extra 中对应的 URL 跳转。
				    } else if (result == "fail") {
				        // charge 不正确或者微信公众账号支付失败时会在此处返回
				    } else if (result == "cancel") {
				        // 微信公众账号支付取消支付
				    }
				});
				
			}
			,"json");
		}
		
		</script>
</html>
