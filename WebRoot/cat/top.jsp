<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<!DOCTYPE html >
<html>
<head>
<base href="http://${header['host']}${pageContext.request.contextPath}/">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta property="qc:admins" content="234163277067167207676375716450" />
<title>我要验房</title>
<meta name="keywords" content="我要验房,我要设计,我要装修,找工长,找师傅,我要建材,我要家具,我要监理,我要检测,验房,网众验房,设计，装修，建材，家具，监理管家，空气检测，装修管家"/>
<meta name="Description" content="中国第一家专业挂牌验房机构，互联网+验房"/>
<link rel="shortcut icon" href="images/ico/ico.png">
<link rel="stylesheet" href="css/c1/bootstrap.min.css" />
<link rel="stylesheet" href="css/c1/main.css" />
<link rel="stylesheet" type="text/css" href="css/c1/layout.css" />
<link rel="stylesheet" type="text/css" href="css/c1/style.css" />
<link rel="stylesheet" type="text/css" href="css/c1/laydate.css"/>
<script type="text/javascript" src="js/j1/jquery/jquery.js"></script>
<script type="text/javascript" src="js/j1/jquery/bootstrap.min.js"></script>
<script type="text/javascript" src="js/j1/jquery/superSlide.js"></script>
<script type="text/javascript" src="js/j1/main.js?v1.0"></script>
<script type="text/javascript" src="js/j1/laydate.js"></script>
<script type="text/javascript" src="js/eshop/pingpp_pay.js"></script>
<script type="text/javascript" src="js/eshop/alipay_in_weixin/ap.js"></script>
<script type="text/javascript" src="js/eshop/avalon.js"></script>
<!-- 表单附带文件上传 局部刷新需要的JS -->
	<script type="text/javascript" src="js/jquery.easyui.min.js" type="text/javascript"></script>
<script type="text/javascript">
/* function checkLoginUser(rid,sid){
	var oldRequestUrl=window.location.href;
	var userid="${sysuser.ae_st_id }";
	alert(userid);
	if(userid==null||userid==''){
		alert("当前未登陆请先登陆!");
		location.href="index/loginp?oldRequestUrl="+oldRequestUrl;
	}else{	
		location.href="index/toDesign?rid="+rid+"&sid="+sid;
	}
} */
$(function(){
    //推荐工长
	$("#temp4").Slide({
		effect : "scroolLoop",
		autoPlay:false,
    	speed : "normal",
		timer : 3000,
		steps:2
	});
	//装修贷
  /*   $("#temp6").Slide({
		effect : "scroolY",
    	speed : "normal",
		timer : 3000
	}); */
	//推荐师傅
	$("#temp8").Slide({
		effect : "scroolLoop",
		autoPlay:false,
    	speed : "normal",
		timer : 3000,
		steps:2
	});
});
</script>
</head>
<body  ms-controller="box">
<!--top-->
<div id="top" class="f_wh100">
	 <div class="header header_ps">
			<div class="header_psson">
				<div class="head-top clear">
				<dl style="float: left;width: 50%;">
	                <dt id="Your_city">${CurrentCityInfo.d_name}</dt>
	                <dd>[<a href="index/changeCity">切换城市</a>]</dd>
	            </dl>
					<dl class="index_topdl clearfix" style="float:right;width:50%;">
						 <dd class="last"  style="float:right">
							<a href="index/buyCart">我的购物车</a>
						</dd>
						

						<c:choose>
							<c:when test="${empty sysuser.ae_st_id }">
								<dd  style="float:right">
									<a href="index/intoregtie">免费注册</a>
								</dd>
							</c:when>
							<c:otherwise>
								<dd  style="float:right">
									<a href="index/me?id=${sysuser.ae_st_id}">个人中心</a>
								</dd>
								<dd   style="float:right">
									<a href="system/admin/initMain">管理中心</a>
								</dd>
							</c:otherwise>
						</c:choose>
						<dd style="float:right">
							<c:choose>
								<c:when test="${empty sysuser.ae_st_id }">
									<a href="index/loginp" class="c_e60012">请登录</a>
								</c:when>
								<c:otherwise>
							你好，【${sysuser.username }】
							 <a href="index/exit" class="c_e60012">注销</a>
								</c:otherwise>
							</c:choose>
						</dd>
					</dl>
				</div>
			</div>
		</div>
		<div class="mycontainer">
        <div class="header">
            <div class="head-middle clear clearfix">
                <a href="index" class="logo f_db f_fl">
                    <img src="images/a_06.png" alt="" height="59" width="303">
                </a>
                <div class="search f_fl">
                    <form action="index/ProductList" method="Get" >
                    <p class="s_form clear">
                        <input class="keywords" value="${keywords}" name="keywords" type="text">
                        <input class="subBtn" value="搜索"  type="submit">
                    </p>
                    </form>
                </div>
                <div class="index-tell f_fr">
                    <img src="images/call_05.png" class="f_fl" alt="" height="48" width="48">
                    <dl class="f_fl" style="text-align: center">
                        <dt>网众科技客服热线</dt>
                        <dd>400-028-5998</dd>
                    </dl>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="f_wh100 nav-box">
    <div class="mycontainer">
        <ul class="mynav clear ">
            <li><a href="index?id=${sysuser.ae_st_id}" class="mynav-a">首页</a></li>
            <li><a class="mynav-a" href="index/checkfree?id=${sysuser.ae_st_id}">我要验房<span></span></a>
                <ul style="display: none;" class="mynav_son">
                    <li><a href="index/checkfree?id=${sysuser.ae_st_id}">免费验房</a></li>
                    <li><a href="index/checkgold?id=${sysuser.ae_st_id}">金牌验房</a></li>
                </ul>
            </li>
            <li><a class="mynav-a" href="index/toDesign?id=${sysuser.ae_st_id}">我要设计<span></span></a>
                <ul class="mynav_son">
                    <%-- <li><a href="javascript:checkLoginUser('${val.ae_st_id}','${sid}')">免费设计</a></li> --%>
                    <li><a href="index/workmessjsp?id=${sysuser.ae_st_id}">户型优化</a></li>
                    <li><a href="index/designstyle?id=${sysuser.ae_st_id}">个性设计</a></li>
                </ul>

            </li>
            <li><a class="mynav-a" href="index/overman?id=${sysuser.ae_st_id}">我要装修</a><span></span>
                <ul class="mynav_son">
                    <li><a href="index/overman?id=${sysuser.ae_st_id}">找工长</a></li>
                    <li><a href="index/master?id=${sysuser.ae_st_id}">找师傅</a></li>
                    <%-- <li><a href="index/Verify?id=${sysuser.ae_st_id}">验房师</a></li> --%>
                </ul>
            </li>
            <li><a class="mynav-a" href="index/shopcheap?id=${sysuser.ae_st_id}">我要特价<span></span></a></li>
            <li><a class="mynav-a" href="index/shopspecial?id=${sysuser.ae_st_id}">购物商城<span></span></a>
                <ul style="display: none;" class="mynav_son">
                    <li><a href="index/actbuy?id=${sysuser.ae_st_id}">团代金券</a></li>
                    <li><a href="index/shopspecial?id=${sysuser.ae_st_id}">品牌特卖</a></li>
                    <li><a href="index/shopbulding?shoptype=building&id=${sysuser.ae_st_id}">建材商城</a></li>
                    <li><a href="index/shopbulding?shoptype=electrical&id=${sysuser.ae_st_id}">家具商城</a></li>
                </ul>
            </li>
            <li><a class="mynav-a" href="index/supervision?type=jianli&id=${sysuser.ae_st_id}">我要监理</a></li>
            <li><a class="mynav-a" href="index/supervision?type=jiance&id=${sysuser.ae_st_id}">我要检测</a> </li>
            <li><a class="mynav-a" href="http://www.wangzhong.com:8080/forum.php" target="_blank" >论坛<span></span></a></li>
        </ul>
    </div>
</div>
