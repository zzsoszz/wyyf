<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib uri="/lystag" prefix="my" %>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="http://${header['host']}${pageContext.request.contextPath}/">
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1.0"/>
    <title></title>
    <link rel="stylesheet" type="text/css" href="css/c1/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/c1/main.css"/>
</head>
<body>
<div class="downloadbox" >
    <div class="download_b1" style="width:50%;height:auto;margin:0 auto;margin-top:30px;margin-bottom:30px;"><img src="images/logopp.png" class="img-responsive" ></div>
    <div class="download_b2"  >
        <img src="images/Android.png" width="20%" >
        <p><a href="http://campaign.app.qq.com/dom/npsb/jump.jsp?pkgName=com.autumn.wyyf"><img src="images/Androidbtn.png" width="50%"></a></p>
    </div>
    <div class="download_b3"  >
        <img src="images/iOS.png" width="20%">
       <p><a href="http://www.pgyer.com/zcuY"><img src="images/iOSbtn.png"  width="50%"></a></p>
    </div>
</div>
<div class="" style="width: 80%;height: auto;margin: 0 auto;text-align:left;padding:30px 0px;;">
     <p>
         一、活动时间： 本活动长期有效，活动结束时间敬请留意官方公告。
     </p>
     <p>二、参与方式：
     </p>
    <p>
        1. 用户按照本活动规则，需使用安卓微信（微信版本5.1以上，下同）扫一扫功能扫红码，并通过应用宝成功有效下载并安装应用后即可领取微信红包，100%有奖。
    </p>
    <p>
        2. 为保障用户账户安全，活动仅对活动期间内每天8：00—24:00(以后台服务器时间为准）时段，参与扫红码活动成功安装应用的用户发放微信红包，其他时段扫红码安装应用的用户，将无法获得微信红包。
    </p>
    <p>
        3. 非Android用户不支持参与本活动。
    </p>
    <p>
        三、红包获取条件：
    </p>
    <p>
        1. 扫红码下载应用：如果您的手机已经安装了应用宝，则您使用微信扫描红码后，将通过应用宝下载安装该应用。如果您的手机未安装应用宝，则扫描红码后，系统将先自动下载安装应用宝，您需在应用宝安装后点击打开继续安装应用。

    </p>
    <p>
        2. 红包发送对象：
    </p>
    <p>
        （1）每成功扫红码安装1个应用，即可获得1个微信红包（由微信红包公共帐号发放），红包金额随机发放；若仅下载未安装或未安装成功应用，均视为无效安装。
    </p>
    <p>
        （2）如果用户参与活动当时，手机已经安装了某个应用，再通过扫红码方式安装同1个应用的，则该次视为无效安装。
    </p>
    <p>
        （3）每个手机扫红码后下载安装同1个应用的，仅能获得1个微信红包，同一应用卸载后重复扫红码安装的，该次视为无效安装。
    </p>
    <p>
        （4）每个手机每日最多可获得2个微信红包，每个自然月最多可获得10个微信红包。
    </p>
    <p>
        （5）同一部手机仅支持绑定一个微信号参与活动，若更换微信号参与，则无法获得红包。
    </p>
    <p>
        3. 红包查看：登录微信=》我=》我的银行卡=》微信红包=》查看收到的红包。
    </p>
    <p>
        四、上述活动是腾讯公司本着公平公正的原则，为回馈应用宝用户举办。如果用户有下列任一恶意刷奖行为的，腾讯公司将取消其参与及中奖资格，且有权收回其通过本活动获得的红包或其他奖品，并保留追究恶意刷奖者的法律责任的权利！

    </p>
    <p>
        1. 不符合参与资格的；
    </p>
    <p>
        2. 提供虚假信息的；
    </p>
    <p>
        3. 虚假交易或恶意破坏活动的；
    </p>
    <p>
        4. 以任何机器人软件、蜘蛛软件、爬虫软件、刷奖软件或其它任何自动方式、不正当手段等参与本活动的；
    </p>
    <p>
        5. 有任何违反诚实信用、公序良俗、公平、公正等原则行为的；
    </p>
    <p>
        6. 其他违反相关法规、本规则行为的。

    </p>
    <p>
        五、免责条款
    </p>
    <p>
        1. 用户知悉互联网存在诸多不确定性因素，因此理解并同意，如因不可抗力、网络、通讯线路故障、计算机大规模瘫痪及活动中存在大面积作弊行为等非腾讯公司原因致使本活动出现异常情况或难以继续开展的，腾讯公司有权采取包括但不限于通过各种方式消除异常情况或调整、暂停、取消本活动等措施，因此造成用户损失的，腾讯公司不承担任何责任。

    </p>
    <p>
        2. 请保证参与活动过程中手机网络正常，若因手机网络异常造成应用安装失败导致红包发送失败、获得抽奖资格失败等的，腾讯公司将不做任何补偿。

    </p>
    <p>
        六、管辖条款

    </p>
    <p>
        因与本活动相关的规则解释、活动开展等事项产生争议的，用户同意与腾讯公司友好协商解决，协商不成的，用户和腾讯公司均同意将争议提交腾讯公司所在地深圳市南山区(即本规则的签署地)人民法院诉讼解决，并适用中华人民共和国大陆地区法律。
    </p>
    <p>
        注：
        非Android用户不支持参与本活动。
    </p>
    <p>
        七、活动规则请点击查看（http://kf.qq.com/faq/120817MRJZZZ140404yEfaqm.html）
    </p>

</div>
</body>
<script src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"> </script>
<script type="text/javascript" src="js/cav.js"></script>
<script src="js/pmain.js"></script>
</html>