<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ include file="top.jsp"%>
<div class="dsigback" style="">
	 <div class="dsignbox" style="">
	 	<div class="blank" style=""></div>
	 	        <ul class="breadcrumb" style="text-align: left;">
                   <li><a href="">首页</a></li>
                   <li class="active"><a href="index">我要监测</a></li>
                   <li><a>免费监测</a></li>  
              </ul>
      
	 	  <div class="dsignboxcon" style="">
	 	  	 <form class="" name="formbm" method="post" action="index/toApply">
			     <div class="baoming_in">
			         	姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：<input placeholder="您的姓名" type="text" name="bf_st_name">
			     </div>
			     <div class="baoming_in">
				                   电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：<input placeholder="您的联系方式" type="text" name="bf_st_tell">
			     </div>
			     <div class="baoming_input">
			              楼&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;盘：&nbsp;&nbsp;&nbsp;&nbsp;<input name="addr" type="radio" value="bf_st_incity">绕城内<input name="addr" type="radio">绕城外
			     </div>
			     <div class="baoming_in">
				       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input placeholder="请输入您的详细地址" name="bf_st_address" style="margin-left: 60px;" type="text">
			     </div>
			     <div class="baoming_in">
				               预约时间：<input type="text">
			     </div>
			     <div class="baoming_in">
			        	<span style="letter-spacing: 15px;margin-right: 1px;">备注：</span><textarea style="width: 200px;margin-top: 20px;resize: none;" name="bf_st_remark" placeholder="请您输入200字以内"></textarea>
			     </div>
			     <div class="baoming_bu">		
			     <input type="hidden" name="type" value="12"/>
				        <!-- <input value="预约" onclick="gzcheck()" type="button"> -->
				        <input type="submit" value="预约"/>
			     </div>
	      </form>
	 	  	   
	 	  </div>
	 </div>
</div>
  <!-- 预约成功 -->
 <div style="display: none;" class="check_out" id="gzcheck">
  	    <div class="check_outtop clearfix" style="display: block;">
  	    	<div class="check_otleft" style="">预约成功</div>
  	    	<div class="close closeout" onclick="myclose();"><span class="glyphicon glyphicon-remove" style="color: white;"></span></div>
  	    </div>
  	    <span class="glyphicon glyphicon-ok-sign check_outfont"></span>
  	    <div class="check_outpoint">您好，您已成功预约监测师傅，请等待审核。工作人员会在24小时内与您取得联系，请注意接听电话。</div>
  	    <div class="baoming_bu check_ob">		
				<input value="返回首页" onclick="checkok()" type="button">
		</div>
</div>
<%@ include file="foot.jsp"%>