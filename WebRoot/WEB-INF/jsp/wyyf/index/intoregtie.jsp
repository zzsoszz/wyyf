<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ include file="top.jsp"%>
<script type="text/javascript" src="js/j1/main.js"></script>
<div class="bg_header">
      <div class="signcon">
      	     <div class=" clearfix">
      	     	    <div class="signcon_left">
      	     	    	<form id="addForm" action="index/intoregtieSave" class="form-horizontal" encType="multipart/form-data" method="post">
	      	     	   	   <table border="0" cellpadding="10" cellspacing="10" width="350"> 
	      	     	   	     	<tbody>
	      	     	   	     	<tr style="text-align: left">
								 <td>用户类型：
									 <select name="ae_st_type" id="" style="width:50%">
										 <option value="1">普通用户</option>
										 <option value="3">商家</option>
										 <option value="4">师傅</option>
										 <option value="5">设计师</option>
										 <option value="6">工长</option>
									 </select>
								 </td>
							 </tr>
	      	     	   	     	
		      	     	   	     	<tr>
		      	     	   	     		<td>用户名：<input name="username" class="signname" placeholder="4-20位字符，可由中英文数字组成" type="text"></td>
		      	     	   	     	</tr>
		      	     	   	     	<tr>   	     	   	     		
		      	     	   	     		<td>设置密码：<input name="password" class="signpass" placeholder="密码由6-16位字符组成" type="text"></td>
		      	     	   	     	</tr>
		      	     	   	     	<tr>
		      	     	   	     		<td>用确认密码：<input name="surepassword" class="signpass2"  placeholder="请再次输入密码" type="text"></td>
		      	     	   	     	</tr>
		      	     	   	     	<tr>
		      	     	   	     		<td>
											<!-- 文件上传控件 
											<div id="fileDIVadmin">
											
											</div>
											<script type="text/javascript">
												$(function(){
													var param="&worklname=fileList&wjlx=SQDTPSCLX";
													jQuery("#fileDIVadmin").load("wyyf/common/registerFile","rr="+new Date().getTime()+param,function(response,status,xhr){
														$("[myattr='update']").show();
														if(xhr.status==403){
															$(this).html(xhr.responseText);
														}else if(xhr.status==404){
															$(this).html(xhr.responseText);
														}
													});
												});
											</script>-->
										</td>
		      	     	   	     	</tr>
		      	     	   	     </tbody>
	      	     	   	     </table>	
	      	     	   	     <div class="baoming_bu ">
	      	     	   	         <input class="signreading" type="checkbox">我已阅读并且同意网众科技的<span class="bluecolor">用户协议</span>
	      	     	   	     </div>
	      	     	   	     <div class="baoming_bu ">
	      	          		       <input value="注册" type="submit">
	      	          	     </div>
      	     	   	     </form>
      	     	   </div>
      	            <div class="signcon_right" style="">
      	            	<span class="sign_rt" style="">微信扫一扫快速登录</span>
      	            	<div class="sign_rpic" style=";">
      	            		<img src="images/c_35.png">
      	            	</div>
      	            	<div class="sign_rbott" style="">
      	            		<div style="">选择更便捷的登录方式：</div>
      	            		 <a href=""><span class="signfont"><i class="iconfont icon-qq"></i></span></a>
      	            		 <a href=""><span class="signfont"><i class="iconfont icon-xinlangweibo"></i></span></a>
      	            	</div>
      	            </div>
      	     </div>
      </div>
</div>
<div class="footer navbar-fixed-bottom">
  	 <div class="footer1">
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
        <span><a href="">成都展峰文化传媒有限公司</a></span>&nbsp;&nbsp;
        <span><a href="">Copyrighgt©2013</a></span>&nbsp;&nbsp;
        <span><a href="">蜀ICP备12005320号</a></span>
    </div> 
  </div>
  <%@ include file="foot.jsp"%>