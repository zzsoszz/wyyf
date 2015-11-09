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
<body >
<nav class="index clearfix mess_nav" style="background-color: #1E76B8;color: white" >
    <div class="mess_nav1" ><a href="wap/pmyzone.html" ><i class="glyphicon glyphicon-menu-left" style="color: white"></i></a></div>
    <div class="mess_nav2" >我的个人信息</div>
    <div class="mess_nav1 index_share" ><span style="display: block;">保存</span></div>
</nav>
<div class="col-xs-12 col-sm-12 psign_pic" align="center" >
    <div class="dk_hkb_pic" style="">
        <input type="file" class="file" size="1" style="">
        <a href="#" style="display: block;">
                 <img src="${ctx}/images/wap/images/my06.png"width="150" height="150">
        </a>
    </div>
</div>
<div class="plogin_box" style="color: black;width: 100%;" >

    <form action="">
    <p  class="mymess_p" >
    <span class="mymess_left" >姓名</span>
    <input class="mymess_con" type="text" value="张三">
    <span  class="mymess_right glyphicon glyphicon-menu-right"></span>
    </p>
    <p  class="mymess_p" >
    <span class="mymess_left" >年龄</span>
    <input class="mymess_con" type="text" value="46">
    <span  class="mymess_right glyphicon glyphicon-menu-right"></span>
    </p>
    <p  class="mymess_p" >
    <span class="mymess_left" >联系电话</span>
    <input class="mymess_con" type="text" value="02633993339">
    <span  class="mymess_right glyphicon glyphicon-menu-right"></span>
    </p>
    <p  class="mymess_p" >
    <span class="mymess_left" >手机号码</span>
    <input class="mymess_con" type="text" value="18299220229">
    <span  class="mymess_right glyphicon glyphicon-menu-right"></span>
    </p>
    <p  class="mymess_p" >
    <span class="mymess_left" >所在城市</span>
    <input class="mymess_con" type="text" value="成都">
    <span  class="mymess_right glyphicon glyphicon-menu-right"></span>
    </p>
    <div class="opinon_boxcon" >
    <textarea name=""  placeholder="请输入介绍（200字以内）" style="height: 100px;"></textarea>
    </div>




    </form>
</div>
<!--<div style="" >-->

<!--</div>-->


</body>
<script src="${ctx}/js/wap/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/wap/js/bootstrap.min.js"> </script>
<script type="text/javascript" src="${ctx}/js/wap/js/cav.js"></script>
<script src="${ctx}/js/wap/js/pmain.js"></script>
</html>