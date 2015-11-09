<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <meta name="renderer" content="webkit">
    <title>忘记密码</title>
    <meta name="keywords" content="我要验房,我要设计,我要装修,找工长,找师傅,我要建材,我要家具,我要监理,我要检测,验房,网众验房,设计，装修，建材，家具，监理管家，空气检测，装修管家"/>
    <meta name="Description" content=""/>
    <link rel="shortcut icon" href="images/ico.png">
    <link rel="stylesheet" href="css/c1/c1/c1/c1/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/c1/c1/c1/c1/main.css"/>
    <link rel="stylesheet" type="text/css" href="css/c1/c1layout.css"/>
    <link rel="stylesheet" type="text/css" href="css/c1/style.css"/>
    <script type="text/javascript" src="js/j1/jquery/jquery.js"></script>
    <script type="text/javascript" src="js/j1/jquery/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/j1/jquery/superSlide.js"></script>
    <script type="text/javascript" src="js/j1/main.js"></script>
</head>
<body>
<div class="heght50"></div>
<div class="sign_header" >
    <div class="header clearfix logintop"style="">
        <div class="logo logintl"style=""><img src="images/logo1.jpg" height="110"/></div>
        <div class="logintc" style="">找回密码</div>
        <div class="logintr" style="">
            <a href="index.html"><span class="redcolor">返回首页</span></a>
        </div>
    </div>
    <div class="signcon">
        <div class="error_box" >
            <div class="forget_box" style="">

                <div class="forget_boxcon" style="">

                    <div class="forget_road_box" style="">
                        <div class="forget_road_box1" style="">
                            <div class="forget_road"><img src="images/step1.png" width="829" height="85"></div>
                            <form action="" >
                                <p ><input class="findBack_tel" type="tel" placeholder="填写注册时填写的电话号码" s></p>
                                <p><input type="button" class="btn btn-success" value="找回密码" onclick="findPass()"></p>
                            </form>
                        </div>
                        <div class="forget_road_box2"  style="">
                            <div class="forget_road"><img src="images/step2.png" width="829" height="85"></div>
                            <p>密码重置短信已发送至您的邮箱</p>
                            <p class="green">133-3333-3333</p>
                            <p>请输入验证码:<input class="forget_yzm" type="text"></p>
                            <p>
                                <input id="endBott" type="button" class="btn btn-success" value="59秒后重新获取" style="margin-right: 10px;" onclick="sendMessage_pass()">
                                <input type="button" class="btn btn-success" value="确定" onclick="findPass_yz()"></p>
                        </div>
                        <div class="forget_road_box3" style="text-align: right">
                            <div class="forget_road"><img src="images/step3.png" width="829" height="85"></div>
                            <p>电话号码:<span class="green">133-3333-3333</span></p>
                            <form action="" style=" ">
                                <p><input type="password" class="signpass" placeholder="输入新密码"></p>
                                <p><input type="password" class="signpass2" placeholder="请再次输入新密码"></p>
                                <p><input type="button" class="btn btn-success" value="确定" ></p>
                            </form>

                        </div>


                    </div>



                </div>


            </div>

        </div>

    </div>

</div>



<div class="footer  navbar-fixed-bottom" >
    <div class="footer1 ">
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







</body>
<script>
//    找回密码第一步
    function findPass(){
        var tel=/^0?(13|14|15|18)[0-9]{9}$/;//电话号码正则表达式
        var findBack_tel=$(".findBack_tel").val();//号码的值
        console.log( findBack_tel)
        if(tel.test(findBack_tel)){//判断如果是电话号码
            $(".forget_road_box1").hide()
            $(".forget_road_box2").show();
            sendMessage_pass();//调用倒计时函数
        }else{
            console.log("2222222222222222222222")
        }
    }
    function sendMessage_pass() {
        //$("")
        curCount = count;
        //设置button效果，开始计时
        $("#endBott").attr("disabled", "true");
        $("#endBott").val("请在" + curCount + "秒内输入");
        InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
        //向后台发送处理数据
        $.ajax({
            type: "POST", //用POST方式传输
            dataType: "text", //数据格式:JSON
            error: function (XMLHttpRequest, textStatus, errorThrown) { },
            success: function (msg){

            }
        });
    }
//找回密码第二步
    function findPass_yz(){
       var input_yzm =$(".forget_yzm").val();//获取输入框中验证码的值

       if(input_yzm==123){
           $(".forget_road_box2").hide()
           $(".forget_road_box3").show();
       }

   }
</script>
</html>