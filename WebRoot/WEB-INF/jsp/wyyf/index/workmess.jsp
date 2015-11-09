<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ include file="top.jsp"%>
<div class="dsigback" style="">
	 <div class="dsignbox" style="">
	 	<div class="blank" style=""></div>
	 	        <ul class="breadcrumb" style="text-align: left;">
                   <li ><a href="">首页</a></li>
                	<li><a href="index/workmessjsp">个性设计</a></li>
                   <li ><a href="index/workmessjsp">预约设计师</a></li>  
              </ul>
      
	 	  <div class="dsignboxcon" style="">
	 	  	<form class="dkform" id="addForm" name="formbm" method="post"  action="index/saveDesign" enctype="multipart/form-data">
		<div class="dsignboxcon" style="">
			
				<div class="baoming_in">
					姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：<input type="text"
						name="bf_st_name">
				</div>
				<div class="baoming_in">
					电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：<input type="text"
						name="bf_st_tell">
				</div>
				
				<!-- <div class="baoming_input">
					楼&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;盘：&nbsp;&nbsp;&nbsp;&nbsp;<input
						type="radio" name="bf_st_incity" value="1" checked="checked">绕城内<input
						type="radio" name="bf_st_incity" value="2">绕城外
				</div> -->
				
				
				<div class="baoming_in">
					地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：<input type="text" placeholder="请输入你的详细地址"
						name="bf_st_address">
				</div>
				<div class="baoming_input clearfix">
					<div class="dk_hkb2" style="">户型：</div>
					
					<input name="fileList[0].ag_st_addUserId" value="fileList[0].fileobj" type="hidden">
					<input name="fileList[1].ag_st_addUserId" value="fileList[1].fileobj" type="hidden">
					<div class="dk_hkb_pic" style="margin-left: 0px;">
						<span id="uploadImg"
							style="font-size: 12px; overflow: hidden; position: absolute">
							<input type="file" id="file" size="1" name="fileList[0].fileobj"
							style="position: absolute; z-index: 100; margin-left: -180px; font-size: 60px; opacity: 0; filter: alpha(opacity = 0); margin-top: -5px;">
							<a href="#"
							style="background: url(images/q_11.png); width: 90px; height: 90px; display: block;"></a>
						</span>
					</div>
					<div class="dk_hkb_pic" style="margin-left: 15px;">
						<span id="uploadImg"
							style="font-size: 12px; overflow: hidden; position: absolute">
							<input type="file" id="file" size="1" name="fileList[1].fileobj"
							style="position: absolute; z-index: 100; margin-left: -180px; font-size: 60px; opacity: 0; filter: alpha(opacity = 0); margin-top: -5px;">
							<a href="#"
							style="background: url(images/q_11.png); width: 90px; height: 90px; display: block;"></a>
						</span>
					</div>
				</div>
		</div>
		<div class="baoming_in">
			<span style="letter-spacing: 15px; margin-right: 1px;">备注：</span>
			<textarea style="width: 200px; margin-top: 20px; resize: none;"
				placeholder="请您输入200字以内" name="bf_st_remark"></textarea>
		</div>
		<div class="baoming_bu">
			  <input class="checkOkClose" type="button" value="预约" onclick="gzcheck()">
			
		</div>
		</form>
	 	  	   
	 	  </div>
	 </div>
</div>

<div class="check_out" id="gzcheck" >
  	    <div class="check_outtop clearfix" style="display: block;">
  	    	<div class="check_otleft"style="">预约成功</div>
  	    	<div class="close closeout" onclick="myclose();"><span class="glyphicon glyphicon-remove"style="color: white;"></span></div>
  	    </div>
  	    <span class="glyphicon glyphicon-ok-sign check_outfont"></span>
  	    <div class="check_outpoint">您好，您已成功预约免费设计，请等待审核。工作人员会在24小时内与您取得联系，请注意接听电话。</div>
  	    <div class="baoming_bu check_ob">		
				<input class="checkOkClose"  type="button" value="返回首页" onclick="checkok()"/>
		</div>
</div> 
  <script type="text/javascript"  type="text/javascript">
function gzcheck(){
	//$(".zhezhao").show();
	//$(".check_out").show();
	$("#addForm").form('submit',{
		url:"index/addApply",  
	     onSubmit: function(param){ 
	    }, 
	    success:function(data){ 
	    	
	    	if("0"==data){
	    	/* 	$(".zhezhao").show();
	    		$(".check_out").show(); */
	    		alert("申请成功！"); 

	    	}else{ 
	    		alert("申请失败！"); 
		} 
	    	
	    }
	 });
}
</script>
<%@ include file="foot.jsp"%>
 
