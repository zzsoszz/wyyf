<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="jsplib.jsp"%>
<header class="pindex_header clearfix" style="">
       <div class="pindex_headerleft clearfix" style="">
           <div style=""><img src="${ctx}/images/wap/images/plogo.png" height="40"></div>
           <!-- <div class="p_index_addr" onclick="tkopeng(this,132)"><span>成都</span><i class="glyphicon glyphicon-menu-down"></i></div> -->
       </div>
      <div class="pindex_headerright clearfix" style="">
          <ul  class="" style="">
              <li><a href="wap/pmyzone"><i class="glyphicon glyphicon-user"></i></a></li>
              <li><a href="wap/mysession"><i class="glyphicon glyphicon-shopping-cart"></i></a></li>
        <!--       <li onclick="tkopeng(this,121)"><i class="iconfont icon-fenxiang" style="font-size: 1em"></i></li> -->
          </ul>
      </div>
</header>

<c:if test="${type != 1}" >

<nav class="pindex_nav" style="">
    <ul>
        <li onclick="pageName()"><a  class="nav_back" href="wap">首页</a></li>
        <li onclick="pageName()"><a href="wap/wzyf">验房</a></li>
        <li onclick="pageName()"><a href="wap/mydesign">设计</a></li>
        <li onclick="pageName()"><a href="wap/fixturejsp?sqlx=8">装修</a></li>
        <li onclick="pageName()"><a href="wap/supervision?fl=13&sqlx=10">监理</a></li>
        <li onclick="pageName()"><a href="wap/mycheck?sqlx=13">检测</a></li>
        <li onclick="pageName()"><a href="wap/myshop">商城</a></li>
        <!--  <li><a  class="nav_back" href="wap">首页</a></li>
        <li><a href="wap/wzyf">验房</a></li>
        <li ><a href="wap/mydesign">设计</a></li>
        <li><a href="wap/fixturejsp">装修</a></li>
        <li ><a href="wap/supervision?fl=13">监理</a></li>
        <li ><a href="wap/mycheck">检测</a></li>
        <li ><a href="wap/myshop">商城</a></li> -->
    </ul>
</nav>
</c:if>