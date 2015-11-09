<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ include file="top.jsp"%>
<div class="clear"></div>
<div class="row  index_carousel" style="margin-top:10px;">
          <div class="carousel slide" id="mycarousel" data-ride="carousel" data-interval="3000">
            <ol class="carousel-indicators">
              <li class="active" data-target="#mycarousel" data-slide-to="0"></li>
              <li data-target="#mycarousel" data-slide-to="1"></li>
              <li data-target="#mycarousel" data-slide-to="2"></li>
            </ol>
            <div class="carousel-inner">
                <div class="item active">
                     <img src="images/d_02.png" alt="" title="" width="100%">
                </div>
                <div class="item">
                     <img src="images/d_02.png" alt="" title="" width="100%">
                </div>
                <div class="item">
                     <img src="images/d_02.png" alt="" title="" width="100%">
                </div>
            </div>
            <a class="carousel-control left" data-target="#mycarousel" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left"></span>
            </a>
            <a class="carousel-control right" data-target="#mycarousel" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right"></span>
            </a>
          </div>
        </div>
        <!-- 图片轮播结束 -->
<div class="" style="width: 100%;background-color: white;">
	   <div class="check_hose " style="width: 1000px;margin: 0 auto;">
	   	    <div class="cheap_road" style="">
	 	  	  <ul class="breadcrumb" style="">
                   <li><a href="index.html">首页</a></li>
                   <li><a href="">我要验房</a></li>
                   <li class="active"><a href="">金牌验房</a></li>
              </ul>
	 	    </div>
	 	  
		   <div class="clearfix" style="width: 100%;">
			   <div style="float: left;width: 65%;">

				   <div class="check_hm_title">验房须知</div>
				   <div class="clearfloat ">
					   <div class="check_hm_con">金牌验房:</div>
					   <p class="check_hm_contop">验房当天用户请带上户型图，以便验房师为您提供装修建议。  为了不耽搁验房进度，请业主准时到达验房目的地。收费标准：按购房合同建筑面积2元/平方计算，同时我们会开具正式的增值税发票。</p>
				   </div>
				   <!-- <p class="check_hm_conbott">2.精装房统一绕城以内3户起验，绕城以外5公里以内5户起验</p>
				   <p class="check_hm_conbott">3.每平米只需2元或xx米币<span class="redcolor">（米奇币来源：分享，浏览，成交奖励）</span>。一户即可安排，时间自定</p> -->
				   <div class="check_hm_title">报名方式</div>
				   <div class="clearfloat">
					   <div class="check_hm_con">方法一：</div>
					   <p class="check_hm_contop">1.登录我要验房网（http://www.wangzhong.com）&gt;2.点击验房报名页面&gt;3.报名&gt;4.预定成功（接收到1条预约成功验房的短信&gt;5.等待网众工作人员电话联系</p>
				   </div>
				   <div class="clearfloat">
					   <div class="check_hm_con">方法二：</div>
					   <p class="check_hm_contop">关注网众科技微信订阅号：wzkj2015 或者我要验房服务号：wyyf666 即可报名</p>
				   </div>
				   <div class="clearfloat">
					   <div class="check_hm_con">方法三：</div>
					   <p class="check_hm_contop">各大应用市场或者应用宝下载"我要验房"APP 即可报名！</p>
				   </div>
				    <div class="clearfloat">
					   <div class="check_hm_con">方法四：</div>
					   <p class="check_hm_contop">拨打网众科技热线电话：400-028-5998 或者加入 我要验房咨询交流群：123582777 咨询"验房统计员"</p>
				   </div>
			   </div>
			   
			   <div class="check_bmbox" style="float: left;">
				   <div class="check_bmbox_top ">已有
					   <span class="menunum">${jpyf.num}</span>
					   人报名
				   </div>
				   <span style="color: #333333;font-size: 0.875em;">请如实完成下面信息</span>
				   
				   <form id="addform" name="formbm" method="post" action="#">
					   <div class="baoming_in">
						   楼盘名称：<input type="text" name="bf_st_remark"><!-- 备用字段 -->
					   </div>
					   <div class="baoming_in">
						   详细地址:<input style="margin-left:6%;" type="text" value="${address}" id="bf_st_address"name="bf_st_address">
					   </div>
					   <div class="baoming_in">
						   建筑面积：<input type="text" value="${bf_st_area}" id="bf_st_area" name="bf_st_area">
					   </div>
					   <div class="baoming_in">
						   预约时间：<input  class="laydate-icon"  name="bf_dt_time">
					   </div>
					   <div class="baoming_in">
						  &nbsp;&nbsp;&nbsp;&nbsp;姓&nbsp;&nbsp;名：<input type="text" value="${name}" name="bf_st_name"/>
					   </div>
					   <div class="baoming_in">
						 &nbsp;&nbsp;&nbsp;&nbsp;电&nbsp;&nbsp;话：<input class="noinput" placeholder="请输入有效电话号码" type="tel" value="${tell}" name="bf_st_tell">
					   </div>

					   <!-- <div class="baoming_input baoming_input1" style="padding-left: 0px;">
						   <span class="baoming_span1" style="width: 82px;">楼盘</span>:<input name="bf_st_incity" value="1" type="radio">绕城内<input name="bf_st_incity" value="2" type="radio">绕城外
					   </div> -->
					   <p class="redcolor">备注：1.精装房只接受金牌验房 2.验房时请带上户型图 3.通过APP赶紧分享给小伙伴有米奇币.</p>
					   <div class="baoming_bu">
					   <input type="hidden" id ="type" name="type" value="3"/><!-- 申请类型 -->
					   <input type="hidden" name="ris" value=""/><!--赴约人ID -->
					   <input type="hidden" name="sid" value="${sid}"/><!--申请人ID -->
						   <input type="button" value="立即支付" onclick="toSubmit();" >
					   </div>
				   </form>
		<script type="text/javascript" type="text/javascript">
				function toSubmit(){
					var oldRequestUrl=window.location.href;
					var userid="${sysuser.ae_st_id }";
					if(userid==null||userid==''){
						alert("当前未登陆请先登陆!");
						location.href="index/loginp?oldRequestUrl="+oldRequestUrl;
					}else{	
						$("#addform").form('submit', {
							url : "index/addApply",
							onSubmit : function(param) {
							},
							success : function(data) {
								if ("0" == data) {
									//alert("申请成功！");
									//location.helf="index";
									$("#zhifu").show();
								} else {
									alert("申请失败！");
								}
							}
						});
					}
					
				}
		</script>

		   </div>
           </div>
		   <div class="check_hm_title">关于验房</div>
	   	   <div class="clearfloat">
	   	       <div class="check_hm_con2">A:&nbsp;为什么要验房？</div>
   	   	    </div>
   	   	    <p class="check_hm_conanswer">验房的目的是通过专业的第三方机构对新房进行全面检查，将交房前的问题提交给开发商整改，避免后期
   	   	          收房后，业主承担房屋质量责任，避免不必要的经济损失。往往很多时候，业主没有验房经验和专业知识，就只看表面的问题，没深入理解房屋是否真的存在问题，
   	   	          只有通过专业的验房，发现问题及时记录，对于日后与KFS交涉的时候，可以更有利于维护自己的合法权益，而且，在房子进住之前给房子一个狠狠的体检，可以住的更放心，更舒服，
   	   	    不用再为以后维修和装整房子烦恼。</p><br class="clear">
   	   	    <div class="clearfloat">
	   	       <div class="check_hm_con2">B:&nbsp;我要验房网专业吗？</div>
   	   	    </div>
   	   	    <p class="check_hm_conanswer">我要验房网团队均为我要验房网的专业验房师，由我要验房网当家人“米奇”亲自带队！</p><br class="clear">
   	   	    <div class="clearfloat">
	   	       <div class="check_hm_con2">C:&nbsp;我要验房网有专业的设备吗？</div>
   	   	    </div>
   	   	    <p class="check_hm_conanswer">专业的验房工具，保障验房的质量！</p>
   	   	    <p class="check_hm_conanswer">验房工具包括：空鼓锤，靠尺，阴阳角水平测试仪，红外线测距仪，验电器，水平仪，卷尺等。</p><br class="clear">
   	   	    <div class="clearfloat">
	   	       <div class="check_hm_con2">D:&nbsp;验房有哪些检测项目？</div>
   	   	    </div>
   	   	    <!--<p class="check_hm_conanswer">网众验房团队均为网众团购网的专业验房师，由网众团购网当家人“米奇”亲自带队！</p><br class="clear" />-->
   	   	    <div class="check_hm_con3">1.入门户</div><div class="check_hm_con3">2.配电箱及多媒体箱</div><div class="check_hm_con3">3.检查地面</div>
   	   	    <div class="check_hm_con3">4.检查电源</div><div class="check_hm_con3">5.检查窗户</div><div class="check_hm_con3">6.检查阴阳角是否垂直</div>
   	   	    <div class="check_hm_con3">7.测层高</div><div class="check_hm_con3">8.测墙面平整度</div><div class="check_hm_con3">9.检查卫生间和厨房</div>
   	   	    <div class="check_hm_con3">10.检查墙面</div><br class="clear">
   	   	    <div class="clearfloat" style="margin-top: 20px;">
	   	       <div class="check_hm_con2">E:&nbsp;验收时常见6大问题？</div>
   	   	    </div>
   	   	    <div class="check_hm_con4">空鼓<img src="images/sas_15.png" alt="" title=""></div>
   	   	    <div class="check_hm_con4">电路<img src="images/sas_17.png" alt="" title=""></div>
   	   	    <div class="check_hm_con4">阴阳角<img src="images/sas_19.png" alt="" title=""></div>
   	   	    <div class="check_hm_con4">门窗<img src="images/sas_21.png" alt="" title=""></div>
   	   	    <div class="check_hm_con4">裂缝<img src="images/sas_23.png" alt="" title=""></div>
   	   	    <div class="check_hm_con4">平整度<img src="images/sas_25.png" alt="" title=""></div>
   	   	    <br class="clear">
   	   	    
       </div>
      </div>
</div>
<div class="check_out" id="zhifu" style="display: none;">
  	    <div class="check_outtop clearfix" style="">
  	    	<div class="check_otleft" style="">报名成功</div>
  	    	<div class="close closeout" onclick="myclose(this)"><span class="glyphicon glyphicon-remove" style="color: white;"></span></div>
  	    </div>
  	    <span class="glyphicon glyphicon-ok-sign check_outfont"></span>
  	    <div class="check_outpoint">恭喜您成为第<span>${jpyf.num}+1</span>号金牌验房用户。我们会在24小时内为您受理，请注意接听电话。</div>
  	    <div class="baoming_bu check_ob">		
				<input id="zf" value="支付" type="button">
		</div>
		<script type="text/javascript">
			$(function() {
				$("#zf").click(function() {
					toPay();
				});
				$("#tempmikigold").Slide({
					effect : "scroolLoop",
					autoPlay:false,
					speed : "normal",
					timer : 3000,
					steps:1
				});
				$(".laydate-icon").bind('click',laydate)
			});
			function toPay(){
				var address=$("#bf_st_address").val();
				var area=$("#bf_st_area").val();
				var money=parseFloat(area);
				var type =$("#type").val();
				location.href="index/toPay?money="+money+"&address="+address+"&area="+area+"&type="+type;
			}
		</script>
  </div>
   <div class="check_news" style="">
       	     <div class="check_newsbox" style="">
       	     	 <div class="check_nbtitle">米奇多次受邀上四川电视台《天府房产》节目和成都电视台FM105.1“私家房产”我要验房节目以及接受众多媒体采访</div>
				 <div class="slide-box">
					 <div class="slide-content" id="tempmikigold">
						 <div class="wrap" style="height: 143px">
							 <ul style="width: 1260px;" class="JQ-slide-content">
								 <li><img src="images/ds_13.png"></li>
								 <li><img src="images/ds_15.png"></li>
								 <li><img src="images/ds_17.png"></li>
								 <li><img src="images/ds_15.png"></li>
								 <li><img src="images/ds_13.png"></li>
								 <li><img src="images/ds_17.png"></li>
								 <li><img src="images/ds_13.png"></li>
							 </ul>
						 </div>
						 <div class="JQ-slide-nav">
							 <a class="prev" href="#">
								 <b class="corner"></b>
								 <span>‹</span>
								 <b class="corner"></b>
							 </a>
							 <a class="next" href="#">
								 <b class="corner"></b>
								 <span>›</span>
								 <b class="corner"></b>
							 </a>
						 </div>
					 </div>
				 </div>
       	     </div>
       </div>
  
  <div class="check_out" id="checkok" style="display: none;">
  	    <div class="check_outtop clearfix" style="">
  	    	<div class="check_otleft" style="">支付成功</div>
  	    	<div class="close closeout" onclick="myclose(this);"><span class="glyphicon glyphicon-remove" style="color: white;"></span></div>
  	    </div>
  	    <span class="glyphicon glyphicon-ok-sign check_outfont"></span>
  	    <div class="check_outpoint">恭喜您成为第<span>123</span>号金牌验房用户。我们会在24小时内为您受理，请注意接听电话。</div>
  	    <div class="baoming_bu check_ob">		
				<input value="确认" onclick="checkok()" type="button">
		</div>
  </div>
  <div class="check_out" style="display: none;">
  	    <div class="check_outtop clearfix" style="">
  	    	<div class="check_otleft" style="">人数不足</div>
  	    	<div class="close closeout" onclick="myclose(this)"><span class="glyphicon glyphicon-remove" style="color: white;"></span></div>
  	    </div>
  	    <span class="glyphicon glyphicon-ok-sign check_outfont"></span>
  	    <div class="check_outpoint">参加用户未达到5户不能使用免费验房，请分享此页面邀请您身边的伙伴一起参与免费验房或者选择金牌验房<span class="redcolor">金牌验房按2元/平米收费，一户即可安排，时间自定</span></div>
  	    <div class="baoming_bu check_two">		
				<input value="邀请小伙伴" type="button">
				<input value="金牌验房" type="button">
		</div>
		<!--<div class="baoming_bu check_two">-->		
				
		<!--</div>-->
  </div>
  
  <div class="check_showpic" style="">
	 <img src="">
</div>
<%@ include file="foot.jsp"%>


