<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../top.jsp"%>
<link href="http://${header['host']}${pageContext.request.contextPath}/plugin/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="http://${header['host']}${pageContext.request.contextPath}/plugin/zTree/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="http://${header['host']}${pageContext.request.contextPath}/plugin/zTree/js/jquery.ztree.excheck-3.5.min.js"></script>
<style>
	ul.ztree {margin-top: 0px;border: 1px solid #617775;background: #f0f6e4;width:95%;height:250px; overflow-x:auto;}
</style>

<script type="text/javascript">
	$(function(){
		mySession();
		var param="${paramStr}";
		if(param!=''){
			if(param=='feedBack'){
				$("#_mygz a").attr("aria-expanded",false);
				$("#_mygz").removeClass("active");
				$("#mygz").attr("class", "tab-pane clearfix");
				$("#_yjfk a").attr("aria-expanded", true);
				$("#_yjfk").attr("class", "active");
				$("#yjfk").attr("class", "tab-pane active");
			}else if(param=='aboutUs'){
				$("#_mygz a").attr("aria-expanded",false);
				$("#_mygz").removeClass("active");
				$("#mygz").attr("class", "tab-pane clearfix");
				$("#_gywm a").attr("aria-expanded", true);
				$("#_gywm").attr("class", "active");
				$("#gywm").attr("class", "tab-pane active");
			}
		}
	});
</script>

<div class="dsigback">
	 <div class="dsignbox clearfix">
	 	<!--头部路线-->
	 	  <div class="cheap_road">
	 	  	  <ul class="breadcrumb">
                   <li><a href="index">首页</a></li>
                   <li class="active"><a href="index/me">我的个人中心</a></li>
              </ul>
	 	  </div>
	 	  <!--左边布局-->
	 	  <div class="myzone_left">
				<div class="myzone_leftpepole" style="">
	 	  	  	   	   <div><img src="${my_messagelist.ag_st_url}" class="img-responsive"></div>
	 	  	  	   	   <%-- <img src="${my_messagelist.ag_st_url}" class="img-responsive"> --%>
	 	  	  	       <span>${my_messagelist.ae_st_name}</span>
	 		    </div>
	 	  	  	   <div class="myzone_leftbi clearfix">
	 	  	  	   	    <div class="myzone_leftbil myzonegift" id="myzone_leftbil">
	 	  	  	   	    	 <span class="redcolor"></span>
	 	  	  	   	    	 <p>米币</p>
	 	  	  	   	    	 <a herf="index/mbRecord?id=id=${sysuser.ae_st_id}"><p>${my_messagelist.mb}</p></a>
	 	  	  	   	    </div>
	 	  	  	   	    <div class="myzone_leftbir myzonegift" id="myzone_leftbir">
	 	  	  	   	    	 <span class="redcolor"></span>
	 	  	  	   	    	 <p>代金券</p>
	 	  	  	   	    	 <p>${my_messagelist.vouchers}</p>
	 	  	  	   	    </div>
	 	  	  	   </div>
	 	  	  	   <div class="myzone_ltitle shop_bltitle" style="">我的服务</div>
	 	  	  	    <ul class="myzone_lul nav nav-tabs" style="" id="mytabs1">
	 	  	  	      <li id="" class=""><a aria-expanded="false" href="#myInter" data-toggle="tab">我的个人信息</a></li>
	 	  	  	      <li id="" class=""><a aria-expanded="false" href="#mymess" data-toggle="tab">申请成为商家</a></li>
	 	  	  	   	  <li id="_mygz" class="active"><a aria-expanded="true" href="#mygz" data-toggle="tab">我的工长</a></li>
	 	  	  	   	  <li id="_mysf" class=""><a aria-expanded="false" href="#mysf" data-toggle="tab">我的师傅</a></li>
	 	  	  	   	  <li id="_myDesign" class=""><a aria-expanded="false" href="#myDesign" data-toggle="tab">我的设计师</a></li>
	 	  	  	   	  <li id="_myjL" class=""><a aria-expanded="false" href="#myjL" data-toggle="tab">我的监理</a></li>
	 	  	  	   	  <li id="_myCheck" class=""><a aria-expanded="false" href="#myChcek" data-toggle="tab">我的检测师</a></li>
	 	  	  	   	  <li id="_fkjl" class=""><a aria-expanded="false" href="#fkjl" data-toggle="tab">付款记录</a></li>
	 	  	  	   	  <li id="_myzf" class=""><a aria-expanded="false" href="#myzf" data-toggle="tab">我的订单</a></li>
	 	  	  	   	  <li id="_yjfk" class=""><a aria-expanded="false" href="#yjfk" data-toggle="tab">意见反馈</a></li>
	 	  	  	   	  <li id="_gywm" class=""><a aria-expanded="false" href="#gywm" data-toggle="tab">关于我们</a></li>
	 	  	  	   </ul>
	 	  	  </div>
	 	     <!--右边布局-->
	 	  <div class="myzone_right">
	 	       	<!--米奇币规则-->
	 	  	      <div style="display: none;" class="mibi">
                         	<div class="mibititle">
                         		您现在的米币是<strong class="redcolor">${my_messagelist.mb}</strong>个	
                         	</div>
                         	<p class="mibititle">米币使用规则</p>
                         	<p>1.米奇币只能在米奇币商城进行兑换。</p>
                         	<p>2.米奇币只能通过注册，每日登陆签到，分享，在线支付返利，订单评价等获得。</p>
                         	<p>3.米奇币兑换比例：100米奇币=1块人民币。</p>
                         	<p class="mibititle">米币获取方式</p>
                         	<p>1.<span class="redcolor">注册</span>获得200米奇币。</p>
                         	<p>2.<span class="redcolor">每日登录签到</span>可获得30米奇币。</p>
                         	<p>3.<span class="redcolor">分享</span>可获得20米奇币。</p>
                         	<p>4.<span class="redcolor">订单支付</span>可获得 （按消费度返还1%商品价值的等值米奇币）。</p>
                         	<p>5.<span class="redcolor">订单评价</span>可获得50米奇币。</p>
                         	<p class="mibititle">米币积累记录</p>
                         	<div class="kuang" style="">
                         	   <table class="myzone_tablemibi" width="720">
                         	    <thead>
                         	     	<tr><td>时间</td>
                         	    	<td>详情</td>
                         	    	<td>交易明细</td>
                         	  </tr></thead>
                         	  <tbody>
                         	    	<tr><td>2015-12-12 12:22:21</td>
                         	    	<td>详情详情详情详情</td>
                         	    	<td>+120.00</td>
                         	  </tr></tbody>
                         	  <tbody>
                         	    	<tr><td>2015-12-12 12:22:21</td>
                         	    	<td>详情详情详情详情</td>
                         	    	<td>+120.00</td>
                         	  </tr></tbody>
                         	  <tbody>
                         	    	<tr><td>2015-12-12 12:22:21</td>
                         	    	<td>详情详情详情详情</td>
                         	    	<td>+120.00</td>
                         	  </tr></tbody>
                         	  <tbody>
                         	    	<tr><td>2015-12-12 12:22:21</td>
                         	    	<td>详情详情详情详情</td>
                         	    	<td>+120.00</td>
                         	  </tr></tbody>
                         
                         	</table>
                  
                         	</div>
                         </div>
	 	      	 <!--代金券-->
	 	      	  <div class="daijinquan" style="display: none;">
	 	      	  	  <div class="kuang_djq" style="">
	 	      	  	  	    <table class="daijq" cellpadding="" cellspacing="" width="700">
	 	      	  	      	   <thead>
	 	      	  	      	   	   <tr><td>金额</td>
	 	      	  	      	   	   <td>状态</td>
	 	      	  	      	   	   <td>有效期</td>
	 	      	  	      	   	   <td>商家信息</td>
	 	      	  	      	   	   <td>备注</td>
	 	      	  	      	   </tr></thead>
	 	      	  	      	   <tbody>
	 	      	  	      	   	    <tr><td><span class="redcolor"><!-- 100元 --></span></td>
	 	      	  	      	   	    <td><!-- 正常 --></td>
	 	      	  	      	   	    <td><!-- 2015-12-12 12:30:00 --></td>
	 	      	  	      	   	    <td><!-- 商家名称 --></td>
	 	      	  	      	   	    <td><!-- 商家备注信息，商家备注信息，商家备注信息 --></td> 
	 	      	  	      	   </tr></tbody>
	 	      	  	      	   <tbody>
	 	      	  	      	   	    <tr><td><span class="redcolor"><!-- 100元 --></span></td>
	 	      	  	      	   	    <td><!-- 正常 --></td>
	 	      	  	      	   	    <td><!-- 2015-12-12 12:30:00 --></td>
	 	      	  	      	   	    <td><!-- 商家名称 --></td>
	 	      	  	      	   	    <td><!-- 商家备注信息，商家备注信息，商家备注信息 --></td> 
	 	      	  	      	   </tr></tbody>
	 	      	  	      	   <!-- <tbody>
	 	      	  	      	   	    <tr><td><span class="redcolor">100元</span></td>
	 	      	  	      	   	    <td>正常</td>
	 	      	  	      	   	    <td>2015-12-12 12:30:00</td>
	 	      	  	      	   	    <td>商家名称</td>
	 	      	  	      	   	    <td>商家备注信息，商家备注信息，商家备注信息</td> 
	 	      	  	      	   </tr></tbody> -->
	 	      	  	      </table>
	 	      	  	  </div>
                         
	 	      	  </div>
                  <div id="rightbox" class="myservice tab-content" style="display: block;">
                        <!--我的工长，师傅，设计师右边显示框-->
                        <div class="tab-pane active" id="mygz">
                               <div class="mygz_top sessioncheck ">
                              	<ul class=" nav nav-tabs sessioncheck" style="">
	 	  	  	                	  <li class="active"><a aria-expanded="true" href="#mygz_pjno" data-toggle="tab">待评价</a></li>
	 	  	  	   	                  <li><a aria-expanded="true" href="#mygz_pjok" data-toggle="tab">已评价</a></li>
                               </ul>	   
                              </div>
                              <!-- 
                                    为评价信息
                               -->  
                               <div class="tab-content">
                              	  <div class="tab-pane  active" id="mygz_pjno">
                              	  <c:forEach items="${ov_overmanlistW}" var="val" varStatus="i">
                              	         <div class="designshow_boxtop clearfix" style="">
       	                                     <div class="designshow_boxtop_left" style=""><img src="${val.ag_st_url }"></div>
       	                                     <div class="designshow_boxtop_right" style="">
       		                                 <span class="designshow_tr_name" style="">${val.ae_st_name }</span>
       		                                 <span class="designshow_tr_star" style="">
       		                                 <c:forEach begin="0" end="${val.ba_st_grade}"><span class="glyphicon glyphicon-star"></span></c:forEach></span>
       	                                     <!-- <div>擅长：的撒大大</div> -->
       	                                     <div>入住时间：${val.ae_dt_addDate}</div>
       	                                     <div>接单数：${val.jdnum}</div>
       	                                     <div>投诉纠纷数:0</div>
       	                                     <div class="baoming_in">
												  <span class="baoming_span1">补交金额</span>:<input style="outline: medium none;" type="text" name="bj_maney" placeholder="补交金额属私下成交金额" id="bj_maney">
											 </div>
       	                                     <div class="baoming_bu check_ob">		
												  <input  type="button" value="点击付款" onclick="toPayO()">
											 </div>
       	                                     <script type="text/javascript">
	       	                                	 function toPayO(){
			       	                   				var bj_maney =$("#bj_maney").val();
			       	                   				
			       	                   				location.href="index/toPay2?bj_maney="+bj_maney+"&rid=${val.bf_st_receiveid}";
	       	                                	 } 
       	                                     </script>
        	                             </div>
        	                             <br class="clear">
        	                             <div class="mygz_ljpj" style="">立即评价<span style="">您的认可是我们最大的动力~</span></div>
        	                             <!-- 户型图展示-->
							
        	                             <p class="yellow">认真写评价最高可获得米币50个！</p>
        	                             <form id="judgeForm${ i.index+1}" method="post">
        	                             <input type="hidden" name="rid" value="${val.bf_st_userid }">
        	                             <input type="hidden"  name="sid"  value="${val.bf_st_receiveid }"/>
        	                              <input type="hidden"  name="be_st_bfid"  value="${val.bf_st_id }"/>
        	                             <input  type="hidden" name="attitude" id="attitude"/>
        	                              <input  type="hidden" name="honesty" id="honesty"/>
        	                               <input  type="hidden" name="quality" id="quality"/>
        	                               <input type="hidden"  name="be_st_bgid"  value="${val.bf_st_id }">
	        	                             <div class="mypjstar  myzone_reviews">服务态度：
	        	                                	<span name="attitude" class="star glyphicon glyphicon-star"></span>
	        	                                	<span name="attitude" class="star glyphicon glyphicon-star"></span>
	        	                                	<span name="attitude" class="star glyphicon glyphicon-star"></span>
	        	                                	<span name="attitude" class="star glyphicon glyphicon-star"></span>
	        	                                	<span name="attitude" class="star glyphicon glyphicon-star"></span>
	        	                             </div>
	        	                             <div class="mypjstar">服务诚信：
	        	                    	            <span name="honesty" class="star glyphicon glyphicon-star"></span>
	        	                    	            <span name="honesty" class="star glyphicon glyphicon-star"></span>
	        	                    	            <span name="honesty" class="star glyphicon glyphicon-star"></span>
	        	                    	            <span name="honesty" class="star glyphicon glyphicon-star"></span>
	        	                    	            <span name="honesty" class="star glyphicon glyphicon-star"></span>
	        	                             </div>
	        	                             <div class="mypjstar">服务质量：
	        	                    	            <span name="quality" class="star glyphicon glyphicon-star"></span>
	        	                    	            <span name="quality" class="star glyphicon glyphicon-star"></span>
	        	                    	            <span name="quality" class="star glyphicon glyphicon-star"></span>
	        	                    	            <span name="quality" class="star glyphicon glyphicon-star"></span>
	        	                    	            <span name="quality" class="star glyphicon glyphicon-star"></span>
	        	                              </div>
	        	                              <p><span>评价内容：</span><textarea  name="content" id="content" style="" placeholder="说点什么"></textarea>
	        	                    	
	        	                              </p>
	        	                              <div class="baoming_bu">		
					                                 <input value="提交" type="button"  onclick="submitJudge('judgeForm${ i.index+1}')">
				                              </div>
			                              </form>
                                          </div>
			                     </c:forEach>
			                     
                               

                              	  </div>
                                  <div class="tab-pane" id="mygz_pjok">
                                  	  <!--盒子-->
                                  	 
                                  	   <c:forEach items="${my_overmanlistY}" var="val" varStatus="i">
                                  	    <div class="border">
                                  	        <div class="designshow_boxtop clearfix" style="">
        	                                     <div class="designshow_boxtop_left" style=""><img src="${val.ag_st_url }"></div>
        	                                     <div class="designshow_boxtop_right" style="">
        		                                    <span class="designshow_tr_name" style="">${val.ae_st_name }</span>
        		                                    <span class="designshow_tr_star" style=""><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span></span>
        	                                       <!--  <div>擅长：的撒大大</div> -->
        	                                         <div>入住时间：${val.ae_dt_addDate}</div>
       	                                     		<div>接单数：${val.jdnum}</div>
       	                                     		<div>投诉纠纷数:0</div>
        	                                     </div>
        	                                    <br class="clear">
        	                                    <div class="mygz_ljpj">对他的评价</div>
        	                                    <div class="mypjstar">服务态度：
        	                                	    <c:forEach begin="0" end="${val.judgeMap.be_nm_manner}"><span disabled="disabled" class="star glyphicon glyphicon-star recolor"></span></c:forEach>
        	                                    </div>
        	                                    <div class="mypjstar">服务诚信：
        	                    	                <c:forEach begin="0" end="${val.judgeMap.be_nm_integrity}"><span disabled="disabled" class="star glyphicon glyphicon-star recolor"></span></c:forEach>
        	                                    </div>
        	                                    <div class="mypjstar">服务质量：
        	                    	                 <c:forEach begin="0" end="${val.judgeMap.be_nm_quality}"><span disabled="disabled" class="star glyphicon glyphicon-star recolor"></span></c:forEach>
        	                                    </div>
        	                                    <p><span>评价内容：</span>${val.judgeMap.be_st_content}</p>
                                          </div>
                                          </div>
                                          </c:forEach>
                                      
                              </div>	
                               
                                </div>
                        </div>
                        
                        
                        <!-- 我的师傅 -->
                        
                          <div class="tab-pane clearfix" id="mysf">
                               <div class="mygz_top sessioncheck ">
                              	<ul class=" nav nav-tabs sessioncheck" style="">
	 	  	  	                	  <li class="active"><a aria-expanded="true" href="#mysf_pjno" data-toggle="tab">待评价</a></li>
	 	  	  	   	                  <li><a aria-expanded="true" href="#mysf_pjok" data-toggle="tab">已评价</a></li>
                               </ul>	   
                               
                              </div>
                              <!-- 
                                    为评价信息
                               -->  
                               <div class="tab-content">
                              	  <div class="tab-pane  active" id="mysf_pjno">
                              	  <c:forEach items="${my_masterlistW}" var="val" varStatus="i">
                              	         <div class="designshow_boxtop " style="">
       	                                     <div class="designshow_boxtop_left" style="">
       	                                         <img src="${val.ag_st_url }">
       	                                     </div>
       	                                     <div class="designshow_boxtop_right" style="">
	       		                                 <span class="designshow_tr_name" style="">${val.ae_st_name }</span>
	       		                                 <span class="designshow_tr_star" style="">
		       		                                 <c:forEach begin="0" end="${val.ba_st_grade}">
		       		                                 	<span class="glyphicon glyphicon-star"></span>
		       		                                 </c:forEach>
	       		                                 </span>
	       	                                     <!-- <div>擅长：的撒大大</div> -->
	       	                                     <div>入住时间：${val.ae_dt_addDate}</div>
	       	                                     <div>接单数：${val.jdnum}</div>
	       	                                     <div>投诉纠纷数:0</div>
	       	                                     <div class="baoming_in">
												  <span class="baoming_span1">补交金额</span>:<input style="outline: medium none;" type="text" name="bj_maneyM" placeholder="补交金额属私下成交金额" id="bj_maneyM">
										     	 </div>
	       	                                     <div class="baoming_bu check_ob">		
												   <input  type="button" value="点击付款" onclick="toPayM()">
											     </div>
       	                                     <script type="text/javascript">
       	                                  function toPayM(){
		       	                   				var bj_maneyM =$("#bj_maneyM").val();
		       	                   				location.href="index/toPay2?bj_maney="+bj_maneyM+"&rid=${val.bf_st_receiveid}";
     	                                	 }
       	                                     </script>
        	                                 </div>
        	                             <br class="clear">
        	                                     <div class="mygz_ljpj" style="">立即评价<span style="">您的认可是我们最大的动力~</span></div>
        	                            		 
        	                            		 <p class="yellow">认真写评价最高可获得米币50个！</p>
		        	                             <form id="judgeForm${ i.index+1}" method="post">
			        	                              <input type="hidden" name="rid" value="${val.bf_st_userid }">
			        	                              <input type="hidden"  name="sid"  value="${val.bf_st_receiveid }"/>
			        	                             <input type="hidden"  name="be_st_bfid"  value="${val.bf_st_id }"/>
			        	                             <input  type="hidden" name="attitude" id="attitude"/>
			        	                              <input  type="hidden" name="honesty" id="honesty"/>
			        	                               <input  type="hidden" name="quality" id="quality"/>
			        	                               
			        	                             <div class="mypjstar  myzone_reviews">服务态度：
			        	                                	<span name="attitude" class="star glyphicon glyphicon-star"></span>
			        	                                	<span name="attitude" class="star glyphicon glyphicon-star"></span>
			        	                                	<span name="attitude" class="star glyphicon glyphicon-star"></span>
			        	                                	<span name="attitude" class="star glyphicon glyphicon-star"></span>
			        	                                	<span name="attitude" class="star glyphicon glyphicon-star"></span>
			        	                             </div>
			        	                             <div class="mypjstar">服务诚信：
			        	                    	            <span name="honesty" class="star glyphicon glyphicon-star"></span>
			        	                    	            <span name="honesty" class="star glyphicon glyphicon-star"></span>
			        	                    	            <span name="honesty" class="star glyphicon glyphicon-star"></span>
			        	                    	            <span name="honesty" class="star glyphicon glyphicon-star"></span>
			        	                    	            <span name="honesty" class="star glyphicon glyphicon-star"></span>
			        	                             </div>
			        	                             <div class="mypjstar">服务质量：
			        	                    	            <span name="quality" class="star glyphicon glyphicon-star"></span>
			        	                    	            <span name="quality" class="star glyphicon glyphicon-star"></span>
			        	                    	            <span name="quality" class="star glyphicon glyphicon-star"></span>
			        	                    	            <span name="quality" class="star glyphicon glyphicon-star"></span>
			        	                    	            <span name="quality" class="star glyphicon glyphicon-star"></span>
			        	                              </div>
			        	                              <p><span>评价内容：</span><textarea  name="content" id="content" style="" placeholder="说点什么"></textarea>
			        	                    	
			        	                              </p>
			        	                              <div class="baoming_bu">		
							                                 <input value="提交" type="button"  onclick="submitJudge('judgeForm${ i.index+1}')">
						                              </div>
					                              </form>
                                     </div>
			                     </c:forEach>
			                     
                               

                              	  </div>
                                  <div class="tab-pane" id="mysf_pjok">
                                  	  <!--盒子-->
                                  	 
                                  	   <c:forEach items="${my_masterlistY}" var="val" varStatus="i">
                                  	    <div class="border">
                                  	        <div class="designshow_boxtop clearfix" style="">
        	                                     <div class="designshow_boxtop_left" style=""><img src="${val.ag_st_url }"></div>
        	                                     <div class="designshow_boxtop_right" style="">
        		                                    <span class="designshow_tr_name" style="">${val.ae_st_name }</span>
        		                                    <span class="designshow_tr_star" style=""><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span></span>
        	                                       <!--  <div>擅长：的撒大大</div> -->
        	                                         <div>入住时间：${val.ae_dt_addDate}</div>
       	                                     		<div>接单数：${val.jdnum}</div>
       	                                     		<div>投诉纠纷数:011111</div>
        	                                     </div>
        	                                    <br class="clear">
        	                                    <div class="mygz_ljpj">对他的评价</div>
        	                                    <div class="mypjstar">服务态度：
        	                                	    <c:forEach begin="0" end="${val.judgeMap.be_nm_manner}"><span  class="star glyphicon glyphicon-star recolor"></span></c:forEach>
        	                                    </div>
        	                                    <div class="mypjstar">服务诚信：
        	                    	                <c:forEach begin="0" end="${val.judgeMap.be_nm_integrity}"><span  class="star glyphicon glyphicon-star recolor"></span></c:forEach>
        	                                    </div>
        	                                    <div class="mypjstar">服务质量：
        	                    	                 <c:forEach begin="0" end="${val.judgeMap.be_nm_quality}"><span  class="star glyphicon glyphicon-star recolor"></span></c:forEach>
        	                                    </div>
        	                                    <p><span>评价内容：</span>${val.judgeMap.be_st_content}</p>
                                          </div>
                                          </div>
                                          </c:forEach>
                                      
                              </div>	
                               
                                </div>
                        </div>
                        
                        <!-- 我的师傅结束 -->
                     <!-- 我的设计师开始 -->
                     <div class="tab-pane clearfix" id="myDesign">
                               <div class="mygz_top sessioncheck ">
                              	<ul class=" nav nav-tabs sessioncheck" style="">
	 	  	  	                	  <li class="active"><a aria-expanded="true" href="#myDesign_pjno" data-toggle="tab">待评价</a></li>
	 	  	  	   	                  <li><a aria-expanded="true" href="#myDesign_pjok" data-toggle="tab">已评价</a></li>
                               </ul>	   
                               
                              </div>
                              <!-- 
                                    为评价信息
                               -->  
                               <div class="tab-content">
                              	  <div class="tab-pane  active" id="myDesign_pjno">
                              	  <c:forEach items="${my_designlistW}" var="val" varStatus="i">
                              	         <div class="designshow_boxtop clearfix" style="">
       	                                     <div class="designshow_boxtop_left" style=""><img src="${val.ag_st_url }"></div>
       	                                     <div class="designshow_boxtop_right" style="">
       		                                 <span class="designshow_tr_name" style="">${val.ae_st_name }</span>
       		                                 <span class="designshow_tr_star" style="">
       		                                 <c:forEach begin="0" end="${val.ba_st_grade}"><span class="glyphicon glyphicon-star"></span></c:forEach></span>
       	                                     <!-- <div>擅长：的撒大大</div> -->
       	                                     <div>入住时间：${val.ae_dt_addDate}</div>
       	                                     <div>接单数：${val.jdnum}</div>
       	                                     <div>投诉纠纷数:0</div>
        	                             </div>
        	                             <br class="clear">
        	                             <div class="mygz_ljpj" style="">立即评价<span style="">您的认可是我们最大的动力~</span></div>
        	                             <ul class="mygz_ul clearfix" >
											 <li>
												 <img src="images/sas_15.png" >
											 </li>
											 <li>
												 <img src="images/sas_15.png">
											 </li>
											 <li>
												 <img src="images/sas_15.png">
											 </li>
										 </ul>
        	                             
        	                             <p class="yellow">认真写评价最高可获得米币50个！</p>
        	                             <form id="judgeForm${ i.index+1}" method="post">
        	                             <input type="hidden" name="rid" value="${val.bf_st_userid }">
        	                             <input type="hidden"  name="sid"  value="${val.bf_st_receiveid }"/>
        	                             <input  type="hidden" name="attitude" id="attitude"/>
        	                              <input  type="hidden" name="honesty" id="honesty"/>
        	                               <input  type="hidden" name="quality" id="quality"/>
        	                               <input type="hidden"  name="be_st_bfid"  value="${val.bf_st_id }"/>
	        	                             <div class="mypjstar  myzone_reviews">服务态度：
	        	                                	<span name="attitude" class="star glyphicon glyphicon-star"></span>
	        	                                	<span name="attitude" class="star glyphicon glyphicon-star"></span>
	        	                                	<span name="attitude" class="star glyphicon glyphicon-star"></span>
	        	                                	<span name="attitude" class="star glyphicon glyphicon-star"></span>
	        	                                	<span name="attitude" class="star glyphicon glyphicon-star"></span>
	        	                             </div>
	        	                             <div class="mypjstar">服务诚信：
	        	                    	            <span name="honesty" class="star glyphicon glyphicon-star"></span>
	        	                    	            <span name="honesty" class="star glyphicon glyphicon-star"></span>
	        	                    	            <span name="honesty" class="star glyphicon glyphicon-star"></span>
	        	                    	            <span name="honesty" class="star glyphicon glyphicon-star"></span>
	        	                    	            <span name="honesty" class="star glyphicon glyphicon-star"></span>
	        	                             </div>
	        	                             <div class="mypjstar">服务质量：
	        	                    	            <span name="quality" class="star glyphicon glyphicon-star"></span>
	        	                    	            <span name="quality" class="star glyphicon glyphicon-star"></span>
	        	                    	            <span name="quality" class="star glyphicon glyphicon-star"></span>
	        	                    	            <span name="quality" class="star glyphicon glyphicon-star"></span>
	        	                    	            <span name="quality" class="star glyphicon glyphicon-star"></span>
	        	                              </div>
	        	                              <p><span>评价内容：</span><textarea  name="content" id="content" style="" placeholder="说点什么"></textarea>
	        	                    	
	        	                              </p>
	        	                              <div class="baoming_bu">		
					                                 <input value="提交" type="button"  onclick="submitJudge('judgeForm${ i.index+1}')">
				                              </div>
			                              </form>
                                          </div>
			                     </c:forEach>
			                     
                               

                              	  </div>
                                  <div class="tab-pane" id="myDesign_pjok">
                                  	  <!--盒子-->
                                  	 
                                  	   <c:forEach items="${my_designlistY}" var="val" varStatus="i">
                                  	    <div class="border">
                                  	        <div class="designshow_boxtop clearfix" style="">
        	                                     <div class="designshow_boxtop_left" style=""><img src="${val.ag_st_url }"></div>
        	                                     <div class="designshow_boxtop_right" style="">
        		                                    <span class="designshow_tr_name" style="">${val.ae_st_name }</span>
        		                                    <span class="designshow_tr_star" style=""><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span></span>
        	                                       <!--  <div>擅长：的撒大大</div> -->
        	                                         <div>入住时间：${val.ae_dt_addDate}</div>
       	                                     		<div>接单数：${val.jdnum}</div>
       	                                     		<div>投诉纠纷数:0</div>
        	                                     </div>
        	                                    <br class="clear">
        	                                    <div class="mygz_ljpj">对他的评价</div>
        	                                     <ul class="mygz_ul clearfix" >
													 <li>
														 <img src="images/sas_15.png" >
													 </li>
													 <li>
														 <img src="images/sas_15.png">
													 </li>
													 <li>
														 <img src="images/sas_15.png">
													 </li>
												 </ul>
        	                                    <div class="mypjstar">服务态度：
        	                                	    <c:forEach begin="0" end="${val.judgeMap.be_nm_manner}"><span disabled="disabled" class="star glyphicon glyphicon-star recolor"></span></c:forEach>
        	                                    </div>
        	                                    <div class="mypjstar">服务诚信：
        	                    	                <c:forEach begin="0" end="${val.judgeMap.be_nm_integrity}"><span disabled="disabled" class="star glyphicon glyphicon-star recolor"></span></c:forEach>
        	                                    </div>
        	                                    <div class="mypjstar">服务质量：
        	                    	                 <c:forEach begin="0" end="${val.judgeMap.be_nm_quality}"><span disabled="disabled" class="star glyphicon glyphicon-star recolor"></span></c:forEach>
        	                                    </div>
        	                                    <p><span>评价内容：</span>${val.judgeMap.be_st_content}</p>
                                          </div>
                                          </div>
                                          </c:forEach>
                                      
                              </div>	
                               
                                </div>
                        </div>
                     <!-- 我的设计师结束 -->
                     
                     <!-- 我的监理开始 -->
                     
                     <div class="tab-pane clearfix" id="myjL">
                               <div class="mygz_top sessioncheck ">
                              	<ul class=" nav nav-tabs sessioncheck" style="">
	 	  	  	                	  <li class="active"><a aria-expanded="true" href="#myjL_pjno" data-toggle="tab">待评价</a></li>
	 	  	  	   	                  <li><a aria-expanded="true" href="#myjL_pjok" data-toggle="tab">已评价</a></li>
                               </ul>	   
                               
                              </div>
                              <!-- 
                                    为评价信息
                               -->  
                               <div class="tab-content">
                              	  <div class="tab-pane  active" id="myjL_pjno">
                              	  <c:forEach items="${my_supervisionlistW}" var="val" varStatus="i">
                              	         <div class="designshow_boxtop clearfix" style="">
       	                                     <div class="designshow_boxtop_left" style=""><img src="${val.ag_st_url }"></div>
       	                                     <div class="designshow_boxtop_right" style="">
       		                                 <span class="designshow_tr_name" style="">${val.ae_st_name }</span>
       		                                 <span class="designshow_tr_star" style="">
       		                                 <c:forEach begin="0" end="${val.ba_st_grade}"><span class="glyphicon glyphicon-star"></span></c:forEach></span>
       	                                     <!-- <div>擅长：的撒大大</div> -->
       	                                     <div>入住时间：${val.ae_dt_addDate}</div>
       	                                     <div>接单数：${val.jdnum}</div>
       	                                     <div>投诉纠纷数:0</div>
        	                             </div>
        	                             <br class="clear">
        	                             <div class="mygz_ljpj" style="">立即评价<span style="">您的认可是我们最大的动力~</span></div>
        	                             <p class="yellow">认真写评价最高可获得米币50个！</p>
        	                             <form id="judgeForm${ i.index+1}" method="post">
        	                             <input type="hidden" name="rid" value="${val.bf_st_userid }">
        	                             <input type="hidden"  name="sid"  value="${val.bf_st_receiveid }"/>
        	                             <input  type="hidden" name="attitude" id="attitude"/>
        	                              <input  type="hidden" name="honesty" id="honesty"/>
        	                               <input  type="hidden" name="quality" id="quality"/>
        	                              <input type="hidden"  name="be_st_bfid"  value="${val.bf_st_id }"/>
	        	                             <div class="mypjstar  myzone_reviews">服务态度：
	        	                                	<span name="attitude" class="star glyphicon glyphicon-star"></span>
	        	                                	<span name="attitude" class="star glyphicon glyphicon-star"></span>
	        	                                	<span name="attitude" class="star glyphicon glyphicon-star"></span>
	        	                                	<span name="attitude" class="star glyphicon glyphicon-star"></span>
	        	                                	<span name="attitude" class="star glyphicon glyphicon-star"></span>
	        	                             </div>
	        	                             <div class="mypjstar">服务诚信：
	        	                    	            <span name="honesty" class="star glyphicon glyphicon-star"></span>
	        	                    	            <span name="honesty" class="star glyphicon glyphicon-star"></span>
	        	                    	            <span name="honesty" class="star glyphicon glyphicon-star"></span>
	        	                    	            <span name="honesty" class="star glyphicon glyphicon-star"></span>
	        	                    	            <span name="honesty" class="star glyphicon glyphicon-star"></span>
	        	                             </div>
	        	                             <div class="mypjstar">服务质量：
	        	                    	            <span name="quality" class="star glyphicon glyphicon-star"></span>
	        	                    	            <span name="quality" class="star glyphicon glyphicon-star"></span>
	        	                    	            <span name="quality" class="star glyphicon glyphicon-star"></span>
	        	                    	            <span name="quality" class="star glyphicon glyphicon-star"></span>
	        	                    	            <span name="quality" class="star glyphicon glyphicon-star"></span>
	        	                              </div>
	        	                              <p><span>评价内容：</span><textarea  name="content" id="content" style="" placeholder="说点什么"></textarea>
	        	                    	
	        	                              </p>
	        	                              <div class="baoming_bu">		
					                                 <input value="提交" type="button"  onclick="submitJudge('judgeForm${ i.index+1}')">
				                              </div>
			                              </form>
                                          </div>
			                     </c:forEach>
			                     
                               

                              	  </div>
                                  <div class="tab-pane" id="myjL_pjok">
                                  	  <!--盒子-->
                                  	 
                                  	   <c:forEach items="${my_supervisionlistY}" var="val" varStatus="i">
                                  	    <div class="border">
                                  	        <div class="designshow_boxtop clearfix" style="">
        	                                     <div class="designshow_boxtop_left" style=""><img src="${val.ag_st_url }"></div>
        	                                     <div class="designshow_boxtop_right" style="">
        		                                    <span class="designshow_tr_name" style="">${val.ae_st_name }</span>
        		                                    <span class="designshow_tr_star" style=""><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span></span>
        	                                       <!--  <div>擅长：的撒大大</div> -->
        	                                         <div>入住时间：${val.ae_dt_addDate}</div>
       	                                     		<div>接单数：${val.jdnum}</div>
       	                                     		<div>投诉纠纷数:0</div>
        	                                     </div>
        	                                    <br class="clear">
        	                                    <div class="mygz_ljpj">对他的评价</div>
        	                                    <div class="mypjstar">服务态度：
        	                                	    <c:forEach begin="0" end="${val.judgeMap.be_nm_manner}"><span disabled="disabled" class="star glyphicon glyphicon-star recolor"></span></c:forEach>
        	                                    </div>
        	                                    <div class="mypjstar">服务诚信：
        	                    	                <c:forEach begin="0" end="${val.judgeMap.be_nm_integrity}"><span disabled="disabled" class="star glyphicon glyphicon-star recolor"></span></c:forEach>
        	                                    </div>
        	                                    <div class="mypjstar">服务质量：
        	                    	                 <c:forEach begin="0" end="${val.judgeMap.be_nm_quality}"><span disabled="disabled" class="star glyphicon glyphicon-star recolor"></span></c:forEach>
        	                                    </div>
        	                                    <p><span>评价内容：</span>${val.judgeMap.be_st_content}</p>
                                          </div>
                                          </div>
                                          </c:forEach>
                                      
                              </div>	
                               
                                </div>
                        </div>
                     <!--  我的监理结束-->
                     
                     
                     
                     <!-- 我的检测师开始 -->
                     <div class="tab-pane clearfix" id="myCheck">
                               <div class="mygz_top sessioncheck ">
                              	<ul class=" nav nav-tabs sessioncheck" style="">
	 	  	  	                	  <li class="active"><a aria-expanded="true" href="#myCheck_pjno" data-toggle="tab">待评价</a></li>
	 	  	  	   	                  <li><a aria-expanded="true" href="#myCheck_pjok" data-toggle="tab">已评价</a></li>
                               </ul>	   
                               
                              </div>
                              <!-- 
                                    为评价信息
                               -->  
                               <div class="tab-content">
                              	  <div class="tab-pane  active" id="myCheck_pjno">
                              	  <c:forEach items="${my_monitorlistW}" var="val" varStatus="i">
                              	         <div class="designshow_boxtop clearfix" style="">
       	                                     <div class="designshow_boxtop_left" style=""><img src="${val.ag_st_url }"></div>
       	                                     <div class="designshow_boxtop_right" style="">
       		                                 <span class="designshow_tr_name" style="">${val.ae_st_name }</span>
       		                                 <span class="designshow_tr_star" style="">
       		                                 <c:forEach begin="0" end="${val.ba_st_grade}"><span class="glyphicon glyphicon-star"></span></c:forEach></span>
       	                                     <!-- <div>擅长：的撒大大</div> -->
       	                                     <div>入住时间：${val.ae_dt_addDate}</div>
       	                                     <div>接单数：${val.jdnum}</div>
       	                                     <div>投诉纠纷数:0</div>
        	                             </div>
        	                             <br class="clear">
        	                             <div class="mygz_ljpj" style="">立即评价<span style="">您的认可是我们最大的动力~</span></div>
        	                             <p class="yellow">认真写评价最高可获得米币50个！</p>
        	                             <form id="judgeForm${ i.index+1}" method="post">
        	                             <input type="hidden" name="rid" value="${val.bf_st_userid }">
        	                             <input type="hidden"  name="sid"  value="${val.bf_st_receiveid }"/>
        	                             <input  type="hidden" name="attitude" id="attitude"/>
        	                              <input  type="hidden" name="honesty" id="honesty"/>
        	                               <input  type="hidden" name="quality" id="quality"/>
        	                               <input type="hidden"  name="be_st_bfid"  value="${val.bf_st_id }"/>
	        	                             <div class="mypjstar  myzone_reviews">服务态度：
	        	                                	<span name="attitude" class="star glyphicon glyphicon-star"></span>
	        	                                	<span name="attitude" class="star glyphicon glyphicon-star"></span>
	        	                                	<span name="attitude" class="star glyphicon glyphicon-star"></span>
	        	                                	<span name="attitude" class="star glyphicon glyphicon-star"></span>
	        	                                	<span name="attitude" class="star glyphicon glyphicon-star"></span>
	        	                             </div>
	        	                             <div class="mypjstar">服务诚信：
	        	                    	            <span name="honesty" class="star glyphicon glyphicon-star"></span>
	        	                    	            <span name="honesty" class="star glyphicon glyphicon-star"></span>
	        	                    	            <span name="honesty" class="star glyphicon glyphicon-star"></span>
	        	                    	            <span name="honesty" class="star glyphicon glyphicon-star"></span>
	        	                    	            <span name="honesty" class="star glyphicon glyphicon-star"></span>
	        	                             </div>
	        	                             <div class="mypjstar">服务质量：
	        	                    	            <span name="quality" class="star glyphicon glyphicon-star"></span>
	        	                    	            <span name="quality" class="star glyphicon glyphicon-star"></span>
	        	                    	            <span name="quality" class="star glyphicon glyphicon-star"></span>
	        	                    	            <span name="quality" class="star glyphicon glyphicon-star"></span>
	        	                    	            <span name="quality" class="star glyphicon glyphicon-star"></span>
	        	                              </div>
	        	                              <p><span>评价内容：</span><textarea  name="content" id="content" style="" placeholder="说点什么"></textarea>
	        	                    	
	        	                              </p>
	        	                              <div class="baoming_bu">		
					                                 <input value="提交" type="button"  onclick="submitJudge('judgeForm${ i.index+1}')">
				                              </div>
			                              </form>
                                          </div>
			                     </c:forEach>
			                     
                               

                              	  </div>
                                  <div class="tab-pane" id="myCheck_pjok">
                                  	  <!--盒子-->
                                  	 
                                  	   <c:forEach items="${my_monitorlistY}" var="val" varStatus="i">
                                  	    <div class="border">
                                  	        <div class="designshow_boxtop clearfix" style="">
        	                                     <div class="designshow_boxtop_left" style=""><img src="${val.ag_st_url }"></div>
        	                                     <div class="designshow_boxtop_right" style="">
        		                                    <span class="designshow_tr_name" style="">${val.ae_st_name }</span>
        		                                    <span class="designshow_tr_star" style=""><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span></span>
        	                                       <!--  <div>擅长：的撒大大</div> -->
        	                                         <div>入住时间：${val.ae_dt_addDate}</div>
       	                                     		<div>接单数：${val.jdnum}</div>
       	                                     		<div>投诉纠纷数:0</div>
        	                                     </div>
        	                                    <br class="clear">
        	                                    <div class="mygz_ljpj">对他的评价</div>
        	                                    <div class="mypjstar">服务态度：
        	                                	    <c:forEach begin="0" end="${val.judgeMap.be_nm_manner}"><span disabled="disabled" class="star glyphicon glyphicon-star recolor"></span></c:forEach>
        	                                    </div>
        	                                    <div class="mypjstar">服务诚信：
        	                    	                <c:forEach begin="0" end="${val.judgeMap.be_nm_integrity}"><span disabled="disabled" class="star glyphicon glyphicon-star recolor"></span></c:forEach>
        	                                    </div>
        	                                    <div class="mypjstar">服务质量：
        	                    	                 <c:forEach begin="0" end="${val.judgeMap.be_nm_quality}"><span disabled="disabled" class="star glyphicon glyphicon-star recolor"></span></c:forEach>
        	                                    </div>
        	                                    <p><span>评价内容：</span>${val.judgeMap.be_st_content}</p>
                                          </div>
                                          </div>
                                          </c:forEach>
                                      
                              </div>	
                               
                                </div>
                        </div>
                     <!-- 我的检测师结束 -->
                        <!--我的付款记录，右边显示框-->
                        <div class="tab-pane clearfix" id="fkjl">
                    	       <div class="fkjl_top">
                               	   按时间查询
                               	   <input type="date">-<input type="date">
                               	   <a href="" style="">确定</a>
                               </div>
                               <ul class="fkjl_con" style="">
                               <c:forEach items="${pay_mentlist }" var="var">
                               	  <li class="fkjl_conli clearfix"> 	
                               	  	    <div class="fkjl_pic">
                               	  	    	<img src="${val.bg_st_img1 }" height="120" width="120">
                               	  	    </div>
                               	  	    <ul>
                               	  	    	<li>${val.bg_st_name }</li>
                               	  	    	<li>规格:${val.bg_st_norms }</li>
                               	  	    	<li>数量：${val.bg_st_color }</li>
                               	  	  
                               	  	    	<li>付款金额：<strong class="redcolor">${val.bh_st_spprice }元</strong></li>
                               	  	    </ul>
                               	  </li>
                               	</c:forEach>
                               </ul>
                               
                        </div>
                         <!--我的订单，右边显示框-->
                        <div class="tab-pane clearfix" id="myzf">
                    	    <div class="sessioncheck">
                                <ul class="nav nav-tabs" id="mytabs">
                                   <li class="active"><a aria-expanded="false" href="#sy" data-toggle="tab">未支付<span>${orderListWSize }</span></a></li>
                                   <li><a aria-expanded="false" href="#bk" data-toggle="tab">已支付<span>${orderListYSize }</span></a></li>                  
                               </ul>
                              <div class="tab-content">
                            
                                   <div class="tab-pane  active" id="sy" style="">
                                                          <!--未支付信息显示-->                     
									<div class="catbox" style="width: 800px;;">
										<table id="cartTable">
											<thead>
											<tr>
												<th><label>
													<input class="check-all check" type="checkbox">&nbsp;&nbsp;全选</label></th>
												<th>商品</th>
												<th>单价(元)</th>
												<th>数量</th>
												<th>金额</th>
												<th>操作</th>
											</tr>
											</thead>
											  <c:forEach  items="${my_orderformlistW }"  var="order">     
											<tbody>
											<tr class="on">
												<td class="td_checkbox"><input class="check-one check" type="checkbox"></td>
												<td class="goods">
													<img src="images/a1.png" alt="">
													<span class="span1">
														${order.bg_st_name }
													    <p class="span2">颜色分类：${order.bg_st_color }</p>
													    <p class="span3">规格：${order.bg_st_norms }</p>
													</span>
													
												</td>
												<td class="price">
													 ${order.bi_st_spprice }
												    <p class="table_priceold">￥${order.bg_st_pricezg }</p>
												    <div class="table_line" style="width: 50px;height: 2px;border-top:2px solid #999999;position: relative;left: 30px;top:-13px;"></div>
												    <p class="iconfont icon-llpromotingtag"></p>
												</td>
												<td class="count">
													<span class="reduce"></span>
													<input class="count-input" value="${order.bi_st_spsl }" type="text">
													<span class="add">+</span></td>
												<td class="subtotal">${order.bh_st_spprice }</td>
												<td class="operation"><span class="delete" ><a href="javascript:void(0)"  onclick="deleteOrder('${order.bh_st_id}')">删除</a></span></td>
											</tr>
											
											</tbody>
											 </c:forEach>
											<!--  <tbody>
											<tr>
												<td class="td_checkbox"><input class="check-one check" type="checkbox"/></td>
												<td class="goods">
													<img src="images/a1.png" alt=""/>
													<span class="span1">
														Casio/卡西欧 EX-TR350欧TR3
													    <p class="span2">颜色分类：红色</p>
													    <p class="span3">规格：50ml</p>
													</span>
													
												</td>
												<td class="price">
													 6500.00
												    <p class="table_priceold">￥7900.00</p>
												    <div class="table_line" style="width: 50px;height: 2px;border-top:2px solid #999999;position: relative;left: 30px;top:-13px;"></div>
												    <p class="iconfont icon-llpromotingtag"></p>
												</td>
												<td class="count">
													<span class="reduce"></span>
													<input class="count-input" type="text" value="1" />
													<span class="add">+</span></td>
												<td class="subtotal">6500.00</td>
												<td class="operation"><span class="delete"><a href="javascript:void(0)"  onclick="deleteOrder('15a344bffe134b2788225296a5ba2a3d')">删除</a></span></td>
											</tr>
											
											</tbody> -->
										</table>
										<div class="session_foot" id="session_foot">
											<label class="fl select-all"><input class="check-all check" type="checkbox">&nbsp;&nbsp;全选</label>
											<a class="fl delete" id="deleteAll" href="javascript:;">删除</a>
											<div class="fr closing" onclick="getTotal();">结 算</div>
											<input id="cartTotalPrice" type="hidden">
											<div class="fr total">合计：￥<span id="priceTotal">${order.bh_st_spprice }</span></div>
											<div class="fr selected" id="selected">已选商品<span id="selectedTotal">${order.bi_st_spsl }</span>件<span class="arrow up">︽
											</span><span class="arrow down">︾</span></div>
											<div class="selected-view">
												<div id="selectedViewList" class="clearfix"><div>
												<img src="file:///C:/Users/Administrator/Desktop/WZyanfang724/images/a1.png"><span class="del" index="0">取消选择</span></div></div>
												<span class="arrow">◆<span>◆</span></span> </div>
										</div>
									</div>




                       
                       <div class="height"> </div>
                     
                     
                     </div>
                   
                    <div class="mydd tab-pane  clearfix" id="bk">
                    	<!--已支付信息显示-->
                    	 <table id="no_chexk" class="zhifuyes" border="0" cellpadding="" cellspacing=""> 
                    	 
                    	 <c:forEach items="${my_orderformlistY}"  var="val">
                    	 <thead>
                     			<tr><td colspan="6">订单号：${val.bh_st_ddnum }&nbsp;<time>${val.bh_st_ddstate }</time></td>
                     		</tr></thead>
                     		<c:forEach items="${val.goodsList }"  var="good">
                     			<tbody>
                     			<tr><td class="table_mess clearfix">
                     				<a href=""><img src="images/a1.png"></a>
                     			    <span>${good.bg_st_name }</span>
                     			    <p>颜色分类：${good.bg_st_color }</p>
                     			    <p>规格：${good.bg_st_norms }</p>
                     			</td>
                     			<td char="table_price">
                     				 <strong class="table_pricenow">￥<span>${good.bi_st_spprice }</span></strong>
                     				 <p class="table_priceold">￥${good.bg_st_pricezg }</p>
                     				  <span class="table_line" style=""></span>
                     				 <span class="iconfont icon-llpromotingtag"> </span>
                     			</td>
                     			<td class="table_num">
                     				<p>${good.bi_st_spsl }</p>
                     			</td>
                     			<td class="table_countprice">
                     				<p>实付款</p>
                     				<strong class="redcolor">￥${val.bh_st_spprice }</strong>
                     				<p>（含运费0.00）</p>
                     			</td>
                     			<td>
                     				<p>交易成功</p>
                     				<a href="index/toPingjia?bh_id=${good.bg_st_id }&num=${good.bi_st_spsl }"><span class="table_status" style="">去评价</span></a>
                     				<a href="index/tmoney?id=${val.bh_st_memberId }&bh_id=${val.bh_st_id}"><span class="table_status" style="">申请退款</span></a>
                     			</td>
                     		</tr></tbody>
                     		
                     		</c:forEach>
                     		
                    	 </c:forEach>                    	
                     		
                            
                           

                     	</table>
                     	<!--分页-->
                     	<!-- <div class="zhifu_fenye" style="">
                     		<ul class="pagination pagination-lg">
                             <li><a href="">«</a></li>
                             <li><a href="">1</a></li>
                             <li><a href="#">2</a></li>
                             <li><a href="#">3</a></li>
                             <li><a href="#">...</a></li>
                             <li><a href="#">8</a></li>
                             <li><a href="">»</a></li>
                              <span style="line-height: 50px;display: inline-block;">共8页</span>
                           </ul>
                          
                     	</div> -->
               

                    </div>
                    
                </div>
           
            </div>
                        </div>
                         <!--我的个人信息-->
                       <div class="tab-pane clearfix" id="mymess">
						   <div class="list_box mymessbox" style="height: auto;width: 100%;margin-top: 0px;">
							   <div class="clearfix" style="padding: 0px 5px;width: 100%;height: 35px;line-height: 35px;;background-color: #3499DA;color: white">
								   <span class="f_fl">用户信息</span>
								   <span class="f_fr">V</span>
							   </div>
							   <form class="user_form"  id="addForm"  encType="multipart/form-data" method="POST">
							   <input  type="hidden"  name="ae_st_id"  value="${my_messagelist.ae_st_id }"/>
							   <input  type="hidden"  name="ae_st_lockstate"  value="${my_messagelist.ae_st_lockstate }">
								   <div class="clearfix">
								   <%-- <c:forEach items="${my_messagelist}" var="user"> --%>
									   <div class="f_fl form_topleft" style="">
										   <p>账号<i class="glyphicon glyphicon-asterisk" style=""></i> <input type="text" value="${my_messagelist.username }"  name="username"></p>
										   <p>密码<i class="glyphicon glyphicon-asterisk" style=""></i>  <input type="password" value="${my_messagelist.password }" name="password"/></p>
										   <p>姓名<i class="glyphicon glyphicon-asterisk" style=""></i>  <input type="text"  value="${my_messagelist.ae_st_name }"  name="ae_st_name"></p>
										   <p><span>年龄</span> <input type="text"  value="${my_messagelist.ae_nm_age }"  name="ae_nm_age"></p>
										   <p><span>性别</span>
											   <select name="ae_st_sex" id="" style="">
											  
												   <option value="1"   <c:if test="${ my_messagelist.ae_st_sex=='1'}">selected="selected"</c:if>>男</option>
												   <option value="2"  <c:if test="${ my_messagelist.ae_st_sex=='2'}">selected="selected"</c:if>>女</option>
											   </select>
										   </p>
									   </div>
									   <div class="f_fl form_topright pic_unload" style="height: auto;">
										    <div class="form_tr_div" style="">用户头像</div>
											<div class="form-group">
														<div id="preview">
														    <img id="imghead" width="277" height="150" border="0" src=${my_messagelist.ag_st_url }><!--无预览时的默认图像，自己弄一个-->
														</div>
														<span class="btn blue fileinput-button">
														    <i class="fa fa-plus"></i>
														    <span>选择图片</span>
														    <input id="myfile" name="myfile"  type="file"  style="height: auto;top: 0px;right: 0px;margin: 0px;opacity: 0;font-size: 23px; direction: ltr;cursor: pointer; width: 277px;  margin-top: -30px;" onchange="previewImage(this)"/>
														</span>
													</div>
									   </div>
								   </div>
								<%--    </c:forEach> --%>
								   <div class="form_bott" style="width: 100%;">
									   <div class="f_fl form_topleft clearfix">
										   <p class="form_p2 f_fl"><span  class="form_p2_span1">所属地区</span>
											<div class="col-md-10 f_fl " align="left">
													  	<span class="" style="margin-left: 0px;width: 20%;"  >
													        <button class="btn blue dropdown-toggle"  type="button" onclick="showMenu3();" style="padding: 6px 12px;">
													           	请选择 
													            <i class="fa fa-angle-down"></i>
													        </button>
													    </span>
													    <div id="menuContent3" style="display:none; position: absolute;z-index: 1999;width:66%;">
															<ul id="groupsTree3" class="ztree" style="border: 1px solid #aaa;border-top:none;background: #f5f5f5;" ></ul>
														</div>
														<input name="groupids3Name" class="form-control" type="text"  readonly onclick="showMenu3();" style="display: inline;width: 76%;"  datatype="*" nullmsg="请选择！"/>
													    
											</div> 
										   </p>
										   	 
										   <p class="form_p2"><span class="form_p2_span1">昵称</span><input type="text"  name="ae_st_nickName" value="${my_messagelist.ae_st_nickName }">
											   <span class="form_p2_span2">电话</span><input type="text"  name="ae_st_tell"  value="${my_messagelist.ae_st_tell }">
										   </p>
										   <p class="form_p2"><span class="form_p2_span1">用户详细地址</span>
											   <input style="width: 80%;margin-left: -4px;" type="text"  name="ae_st_address" value="${my_messagelist.ae_st_address }">
										   </p>
										   <p class="form_p2"><span class="form_p2_span1">选择用户类型</span>
											   <select name="ae_st_type" style="margin-left: -4px;" >
				
											   
											   <option value="3"  selected="selected">商家</option>
											 
											   </select>
										   </p>
										   <p class="form_p2"><span class="form_p2_span1" style="margin-left: -4px;">面积介绍</span>
											   <input style="width: 80%" type="text"  name="bb_st_area">
										   </p>
										   <p class="form_p2"><span class="form_p2_span1">电话1</span><input type="text"  name="bb_st_phone1">
											   <span class="form_p2_span2" style="">电话2</span><input type="text"  name="bb_st_phone2">
										   </p>
										   <p class="form_p2 form_marginTop"><span class="form_p2_span1" style="vertical-align: top;">店铺介绍</span>
											   <textarea style="width: 80%;height: 80px;resize: none;margin-left: -4px;"  name="bb_st_shopinfo"></textarea>
										   </p>
										   <p class="form_p2 form_marginTop"><span class="form_p2_span1" style="vertical-align: top;">简介</span>
											   <textarea style="width: 80%;height: 80px;resize: none;margin-left: -4px;"  name=""></textarea>
										   </p>
									   </div>
								   </div>
								   <input class="list_form_submit" value="提交" type="button"  onclick="submitApply();">
							   </form>
						   </div>
						   </div>
                           <!-- 修改我的信息 -->                    
                         <div class="tab-pane clearfix" id="myInter">
                          <form id="userInfoForm"  method="post" encType="multipart/form-data" >
                          <input type="hidden"  value="${my_messagelist.ae_st_id}"  name="ae_st_id"/>
                           <input type="hidden"  value="${my_messagelist.ae_st_lockstate}"  name="ae_st_lockstate"/>
                           <input type="hidden" value="${my_messagelist.username }" id="username" name="username"/>
                       	   	   <div class="mymess_left" >
                    	     	      亲爱的<span>小伙伴</span>，来上传一张头像吧
								    <div class="dk_hkb_pic" style="left: 50px;width: 125px;height: 125px;margin-left: 288px;position: relative;margin-bottom: 30px">
										<%-- <span class="uploadImg" style="font-size:12px; overflow:hidden; position:absolute;left: -1px">
											<input type="file" class="file" size="1" style="position:absolute; z-index:100; font-size:60px;opacity:0;filter:alpha(opacity=0);">
											<a href="#" style="width: 125px;height: 125px;display: block;">
												<img src="${my_messagelist.ag_st_url }" width="125" height="125" class="img-circle">
											</a>
										</span> --%>
										<div id="preview" style="width: 125px;height: 125px;display: block;">
											<img id="imghead" width="125" height="125" src=${my_messagelist.ag_st_url }><!--无预览时的默认图像，自己弄一个-->
										</div>
										<span class="btn blue fileinput-button">
										    <i class="fa fa-plus"></i>
										    <span>选择图片</span>
										    <input id="myfile" name="myfile"  type="file"  style="height: auto;top: 0px;right: 0px;margin: 0px;opacity: 0;font-size: 23px; direction: ltr;cursor: pointer; width: 125px;  margin-top: -30px;" onchange="previewImage1(this)"/>
										</span>
								   </div>
								   支持JPG,JPEG,GIF,PNG,BMP.且小于5M
                    	      </div>
                    	      <div class="mymess_right"  style="">
                    	      <%--  <c:forEach items="${my_messagelist}" var="user"> --%>
                    	      	 <!--    <form action="" method="post"> -->
                    	      	    
                    	      	    	 <div class="baoming_in">
                    	      	    	 	 姓名：<input type="text" value="${my_messagelist.ae_st_name}" id="ae_st_name" name ="ae_st_name"/>
                    	      	    	 </div>
                    	      	    	  <div class="baoming_in">
                    	      	    	 	 密码：<input type="password" id="password" name ="password"/>
                    	      	    	 </div>
                    	      	    	 <div class="baoming_in">
                    	      	    	 	 昵称：<input type="text" value="${my_messagelist.ae_st_nickName}" id="ae_st_nickName" name="ae_st_nickName"/>
                    	      	    	 </div>
                    	      	    	 <div class="baoming_in">
                    	      	    	 	 邮箱：<input type="text" value="${my_messagelist.emll }" id="emll" name="emll"/>
                    	      	    	 </div>
                    	      	    	 <div class="baoming_in">
                    	      	    	 	手机号码：<input type="text" value="${my_messagelist.ae_st_tell }" id="ae_st_tell" name="ae_st_tell"/>
                    	      	    	 </div>
                    	      	    	  <div class="baoming_in">
                    	      	    	 	 地址：<input type="text" value="${my_messagelist.ae_st_address }" id="ae_st_address" name="ae_st_address"/>
                    	      	    	 </div>
                    	      	    	 <div class="baoming_in">
                    	      	    	 	 其他：<input type="text" value="${my_messagelist.ae_st_description }" id="ae_st_description" name="ae_st_description"/>
                    	      	    	 </div>
                    	      	    	 <div class="baoming_bu">
                    	      	    	 	<input type="button" value="保存"  onclick="editUserInfo();"/>
                    	      	    	 </div>
                    	      	    </form>
                    	      	     <%-- </c:forEach> --%>
                    	      </div>
                       	   </div>     
                        <!--意见反馈，右边显示框-->
                         <div class="tab-pane  clearfix" id="yjfk">
                         	<form id="feedBackForm"  method="post">
                    	        <div class="redcolor" style="">亲，<br>感谢您的意见反馈，提交成功之后将会赠送米奇币，请在下一版本中等待米奇币商城上线，届时可以换购商品奥</div>
                               
                               
                                <div class="clearfix">
                                	 <p class="yjfk_title" style="">问题类型：</p>
                                	 <ul class="yjfk_con" style="">
                                	 	<li><input value="1" type="checkbox" name="fk_type">收到的团购单子不想关</li>
                                	 	<li><input value="1" type="checkbox" name="fk_type">找不到我要的商家</li>
                                	 	<li><input value="1" type="checkbox" name="fk_type">团购单子太多，不好挑选</li>
                                	 	<li><input value="2" type="checkbox" name="fk_type">其他</li>
                                	 </ul>
                                </div>
                                <div class="clearfix">
                                	 <p class="yjfk_title" style="">请输入标题：</p>
                                	 <input class="yjfk_input" placeholder="请输入标题" type="text"  id="fk_title" name="fk_title">
                                </div>
                                <div class="clearfix">
                                	 <p class="yjfk_title" style="">反馈内容：</p>
                                	 <textarea id="fk_content" name="content" class="yjfk_yj" placeholder="可输入200字以内"></textarea>
                                </div>
                                <div class="clearfix">
                                	 <p class="yjfk_title" style="">请输入联系方式：</p>
                                	 <input class="yjfk_input" placeholder="请输入您的电话号码" type="text"  id="phone" name="phone">
                                </div>
                                <div class="baoming_bu" style="float: left;margin-left: 120px;">		
				                        <input value="提交" type="button"  onclick="submitFeedForm();">
			                         </div>
                         </form>
                         </div>
                         <!--关于我们右边显示框-->
                         <div class="tab-pane clearfix" id="gywm">
                    	     <div class="gywmbox" style="padding-bottom: 30px;">
                    	     <c:forEach items="${aboutMap}" var="val">
                    	     	 <p class="gywmtitle">关于我们</p>
                    	     	 ${val.bL_st_context }
                    	     </c:forEach>
                    	     </div>
                        </div>
                         
                        

                   </div>
        
	 	  </div>
	 	  
	 	   
	 	 
	 </div>
</div>

<script>
	function submitFeedForm(){
		var phone=$("#phone").val();
		var content= $("#fk_content").val();
		var loginflag="2";
		var type=$("input[name='fk_type']:checked").val();
		var title=$("#fk_title").val();
		var json="{\"bk_st_type\":'"+type+"',\"bk_st_content\":'"+content+"',\"loginflag\":'"+loginflag+"',\"bk_st_phone\":'"+phone+"',\"bk_st_title\":'"+title+"'}";
		$.post('services/addAdvice',{json:json},function(data){
			 var obj = eval('(' + data + ')');
			 var success=obj.success;
			 if(success){
				 alert("提交成功");
				 location.reload();
			 }else{
				 alert("提交失败");
			 }
		});
	}

	function submitJudge(form){
		var content=$("#"+form+" #content" ).val();
		var attitude =$("#"+form).find("[class='star glyphicon glyphicon-star recolor'][name='attitude']").size();
		var honesty =$("#"+form).find("[class='star glyphicon glyphicon-star recolor'][name='honesty']").size();
		var quality =$("#"+form).find("[class='star glyphicon glyphicon-star recolor'][name='quality']").size();
		$("#"+form).find("input[name='attitude']").val(attitude);
		$("#"+form).find("input[name='honesty']").val(honesty);
		$("#"+form).find("input[name='quality']").val(quality);
		$("#"+form).submit();
		$("#"+form).form('submit',{
			url:"index/doJudge",
			onSubmit:function(param){
			},
			success:function(data){
				if(0==data){
					alert("评价成功");
					location.reload();
					//location.href="index/doJudge";
				}else{
					alert("评价失败");
				}
			}
		});
	}
	
	function deleteOrder(orderId){
		$.post('index/deleteOrder',
				{bhid:orderId},
				function(data){
					if(data=="true"){
						/* location.reload(); */
						alert("删除成功!");
					}else{
						alert("删除失败!");
					}
			
		});
	}
	
	function editUser(obj){
		$("[mykzattr='1']").show();//保存按钮显示
		initValidForm($("#addForm"));//清除表单验证样式
		$("#addForm input[name='groupids3']").val("");//清空隐藏的文本框ID值
		getinfostatus($("#typeCZ"));//进入查看类型
		$("#addForm select[name='ae_st_type']").attr("readonly","readonly");//不能修改类型
		$("#addForm input[name='username']").attr("readonly","readonly");//不能修改帐号
		$("#addForm")[0].reset();
		$("#changeMMText").html("新密码");
		$("#addForm input[name='password']").attr("ignore","ignore");
		$("#usertile").text("编辑用户信息");
		var userid=$(obj).parents("tr").first().find("input[type='checkbox'].checkboxes").val();
		var url = "wyyf/user/findUserById";
		newMask();
		$.post(url,"userid="+userid,function callback(data){  
			delMask();
			var resp = eval("("+data+")"); 
			if(resp.success){
				var obj=resp.result[0];
				formload($("#addForm"),obj);
				loadImageKJ(userid);
				if(obj.ae_st_lockstate=='1'){
					$("#saveBtn1").hide();
				}else if(obj.ae_st_lockstate=='2'){
					$("#saveBtn2").hide();
				}
				
				$.fn.zTree.init($("#groupsTree3"), setting3, zNodes);//初始化树
				if(obj.groupsid){
					var treeObjx = $.fn.zTree.getZTreeObj("groupsTree3");
					//给自己本身所在的组加上 选择
					var node=treeObjx.getNodesByParam("TREEID", obj.groupsid)[0];
					treeObjx.checkNode(node, true, false ,true);
				}
				if(obj.ag_st_url == null || obj.ag_st_url == ""){
					document.getElementById('imghead').src="images/logo/default_img.jpg";//初始化图片选择
				}else{
					document.getElementById('imghead').src=obj.ag_st_url;
				}
				var type = obj.ae_st_type;
				if(type=='3'){
					$("#sjadd").show();
					$("#sfadd").hide();
				}else if(type=='4'||type=='5'||type=='6'){
					$("#sfadd").show();
					$("#sjadd").hide();
				}else{
					$("#sfadd").hide();
					$("#sjadd").hide();
				}
				loadotherPage_money("&pageIndex=1&pageSize=10");//加载交易记录信息
				loadotherPage_address("&pageIndex=1&pageSize=10");//加载地址信息
				$('#add-config').modal('show');
			}else{ 
				bootbox.alert(resp.msg);
			}
		});
		
	}
	
	 function previewImage1(file){
	    if(checkPic(file)){
	    	var MAXWIDTH  = 125;
	        var MAXHEIGHT = 125;
	        var img =$(file).parents("form").first().find("#imghead")[0];
	        if (file.files && file.files[0]){
	          img.onload = function(){
	            img.width =125;
	            img.height = 125;
	          }
	          var reader = new FileReader();
	          reader.onload = function(evt){img.src = evt.target.result;}
	          reader.readAsDataURL(file.files[0]);
	        }else{
	          var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
	          file.select();
	          var src = document.selection.createRange().text;
	          img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
	          div.innerHTML = "<div id=divhead style='width:277px;height:150px;"+sFilter+src+"\"'></div>";
	        }
	    }
	}
 
	function submitApply(){
		$("#addForm").form('submit',{
			url:"index/userAdd",
		    onSubmit: function(param){ 
		    }, 
		    success:function(data){ 
		    	var resp = eval("("+data+")"); 
				if(resp.success){
					alert(resp.msg);
					location.reload();
				}else{ 
					alert(resp.msg);
				}
		    }
		 });
	}
	
	function editUserInfo(){
		$("#userInfoForm").form('submit',{
			url:"index/editUserInfo",
		    onSubmit: function(param){ 
		    }, 
		    success:function(data){ 
		    	var resp = eval("("+data+")"); 
		    	if(resp.success){
					alert(resp.msg);
					location.reload();
				}else{ 
					alert(resp.msg);
				}
		    }
		 });
		//$("#userInfoForm").attr("action","index/userAdd").submit();
	}
</script>

<script>

	//初始化打开页面 
	

		//根据用户类型 onchange事件   z这个他做额时候 就是 这么 判断，的  ，对应了 新的 dvi。 
		$("#addForm select[name='ae_st_type']").change(function(){
			var myval=$(this).val();
			if(myval=='3'){
				$("#sjadd").show();
				$("#sfadd").hide();
			}else if(myval=='4'||myval=='5'||myval=='6'){
				$("#addForm select[name='ba_st_type']").html($("#addForm select[name='type"+myval+"']").html());
				$("#sfadd").show();
				$("#sjadd").hide();
			}else{
				$("#sfadd").hide();
				$("#sjadd").hide();
			}
		});
	//附件控件加载--
	function loadImageKJ(id) {
		var param="&worklname=fileList&wjlx=SQDTPSCLX";
		if(id){
			param=param+"&id="+id;
		}
		newMask();
		jQuery("#myfileList").load("wyyf/common/registerFile","rr="+new Date().getTime()+param,function(response,status,xhr){
			delMask();
			if(id){
				$("[myattr='update']").hide();//只能查看
			}else{
				$("[myattr='update']").show();
			}
			if(xhr.status==403){
				$(this).html(xhr.responseText);
			}else if(xhr.status==404){
				$(this).html(xhr.responseText);
			}
		});
	}
	//保存公共方法

	//查询函数--替换主题内容的函数
	function loadotherPage(params) {
		var param=$("#queryForm").serialize();//条件参数
		var url="wyyf/user/userList";
		
		var xsl=$("#commonid_column_toggler input[name='ISSHOWCOLUMS'][type='checkbox']:checked").serialize();//显示列的控制
		if(xsl){
			param+="&"+xsl;
		}
		newMask();
		jQuery("#tableList").load(url,param+params+"&rr="+new Date().getTime(),function(response,status,xhr){
			delMask();
			if(xhr.status==403){
				$(this).html(xhr.responseText);
			}else if(xhr.status==404){
				$(this).html(xhr.responseText);
			}
		});	
	}
	//充值或者扣款
	function czUser(obj){
		initValidForm($("#czForm"));//清除表单验证样式
		$("#czForm")[0].reset();
		var userid=$(obj).parents("tr").first().find("input[type='checkbox'].checkboxes").val();
		$("#czForm input[name='ae_st_id']").val(userid);
		$('#cztc-config').modal('show');
	}
	//修改用户信息--进入
	function editUser(obj){
		$("[mykzattr='1']").show();//保存按钮显示
		initValidForm($("#addForm"));//清除表单验证样式
		$("#addForm input[name='groupids3']").val("");//清空隐藏的文本框ID值
		getinfostatus($("#typeCZ"));//进入查看类型
		$("#addForm select[name='ae_st_type']").attr("readonly","readonly");//不能修改类型
		$("#addForm input[name='username']").attr("readonly","readonly");//不能修改帐号
		$("#addForm")[0].reset();
		$("#changeMMText").html("新密码");
		$("#addForm input[name='password']").attr("ignore","ignore");
		$("#usertile").text("编辑用户信息");
		var userid=$(obj).parents("tr").first().find("input[type='checkbox'].checkboxes").val();
		var url = "wyyf/user/findUserById";
		newMask();
		$.post(url,"userid="+userid,function callback(data){  
			delMask();
			var resp = eval("("+data+")"); 
			if(resp.success){
				var obj=resp.result[0];
				formload($("#addForm"),obj);
				loadImageKJ(userid);
				if(obj.ae_st_lockstate=='1'){
					$("#saveBtn1").hide();
				}else if(obj.ae_st_lockstate=='2'){
					$("#saveBtn2").hide();
				}
				
				$.fn.zTree.init($("#groupsTree3"), setting3, zNodes);//初始化树
				if(obj.groupsid){
					var treeObjx = $.fn.zTree.getZTreeObj("groupsTree3");
					//给自己本身所在的组加上 选择
					var node=treeObjx.getNodesByParam("TREEID", obj.groupsid)[0];
					treeObjx.checkNode(node, true, false ,true);
				}
				if(obj.ag_st_url == null || obj.ag_st_url == ""){
					document.getElementById('imghead').src="images/logo/default_img.jpg";//初始化图片选择
				}else{
					document.getElementById('imghead').src=obj.ag_st_url;
				}
				var type = obj.ae_st_type;
				if(type=='3'){
					$("#sjadd").show();
					$("#sfadd").hide();
				}else if(type=='4'||type=='5'||type=='6'){
					$("#sfadd").show();
					$("#sjadd").hide();
				}else{
					$("#sfadd").hide();
					$("#sjadd").hide();
				}
				loadotherPage_money("&pageIndex=1&pageSize=10");//加载交易记录信息
				loadotherPage_address("&pageIndex=1&pageSize=10");//加载地址信息
				$('#add-config').modal('show');
			}else{ 
				bootbox.alert(resp.msg);
			}
		});
		
	}
	//查看用户信息--进入
	function findUser(obj){
		editUser(obj);
		$("[mykzattr='1']").hide();
	}
	//查询函数--查询某个用户收货地址信息--主要针对会员
	function loadotherPage_address(params) {
		var param="bm_st_jsuserid="+$("#addForm input[name='ae_st_id']").val();//条件参数
		var url="wyyf/user/useraddressList";
		var xsl=$("#commonid_address_column_toggler input[name='ISSHOWCOLUMS'][type='checkbox']:checked").serialize();//显示列的控制
		if(xsl){
			param+="&"+xsl;
		}
		newMask();
		jQuery("#tableadressList").load(url,param+params+"&rr="+new Date().getTime(),function(response,status,xhr){
			delMask();
			if(xhr.status==403){
				$(this).html(xhr.responseText);
			}else if(xhr.status==404){
				$(this).html(xhr.responseText);
			}
		});	
	}
	//查询函数--查询某个用户详细交易记录信息
	function loadotherPage_money(params) {
		var param="bm_st_jsuserid="+$("#addForm input[name='ae_st_id']").val();//条件参数
		var url="wyyf/user/usermoneylogList";
		var xsl=$("#commonid_money_column_toggler input[name='ISSHOWCOLUMS'][type='checkbox']:checked").serialize();//显示列的控制
		if(xsl){
			param+="&"+xsl;
		}
		newMask();
		jQuery("#tablemoneylogList").load(url,param+params+"&rr="+new Date().getTime(),function(response,status,xhr){
			delMask();
			if(xhr.status==403){
				$(this).html(xhr.responseText);
			}else if(xhr.status==404){
				$(this).html(xhr.responseText);
			}
		});	
	}
	//注销用户信息
	function zxUser(obj){
		bootbox.confirm("确认是否注销该用户？",function(rs){
			if(rs){
				var roleid=$(obj).parents("tr").first().find("input[type='checkbox'].checkboxes").val();
				var url = "wyyf/user/userzx";
				newMask();
				$.post(url,"ae_st_id="+roleid,function callback(data){  
					delMask();
					var resp = eval("("+data+")"); 
					if(resp.success){
						RefreshCurPage();//刷新当前页，--该函数在 commonList.jsp中
						bootbox.alert(resp.msg);
					}else{ 
						bootbox.alert(resp.msg);
					}
				});
			}
		});
		/*bootbox.confirm("确认是否删除？",function(rs){
			if(rs){
				var roleid=$(obj).parents("tr").first().find("input[type='checkbox'].checkboxes").val();
				var url = "wyyf/user/userDelete";
				newMask();
				$.post(url,"deleteData="+roleid,function callback(data){  
					delMask();
					var resp = eval("("+data+")"); 
					if(resp.success){
						RefreshCurPage();//刷新当前页，--该函数在 commonList.jsp中
						bootbox.alert(resp.msg);
					}else{ 
						bootbox.alert(resp.msg);
					}
				});
			}
		});*/
	}
</script> 
<script>
//图片本地预览
function previewImage(file){
    if(checkPic(file)){
    	var MAXWIDTH  = 277;
        var MAXHEIGHT = 150;
        var img =$(file).parents("form").first().find("#imghead")[0];
        if (file.files && file.files[0]){
          img.onload = function(){
            img.width =277;
            img.height = 150;
          }
          var reader = new FileReader();
          reader.onload = function(evt){img.src = evt.target.result;}
          reader.readAsDataURL(file.files[0]);
        }else{
          var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
          file.select();
          var src = document.selection.createRange().text;
          img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
          div.innerHTML = "<div id=divhead style='width:277px;height:150px;"+sFilter+src+"\"'></div>";
        }
    }
}
//图片选择格式限制
function checkPic(obj) {
	var pass =$(obj).parents("form").first().find("#myfile")[0];
    var picPath = pass.value;
    var type = picPath.substring(picPath.lastIndexOf(".") + 1, picPath.length).toLowerCase();
    if (type != "jpg" && type != "bmp" && type != "gif" && type != "png") {
        pass.setCustomValidity("请上传正确的图片格式");
        bootbox.alert("请上传正确的图片格式");
        //$("#myfile").val("");
        var file = $("input[name='myfile']") 
		file.after(file.clone().val("")); 
		file.remove();
        return false;
    }else{
    	pass.setCustomValidity('');
    	return true;
    }
}
</script>
<!-- 查询条件中的组织树zTree -->
<script type="text/javascript">

	var zNodes =${groupTree };
	var setting3 = {
		check: {
			enable: true,
			chkStyle:"radio",
			radioType:"all" ,
			chkboxType: {"Y":"p", "N":"s"}
		},
		view: {
			dblClickExpand: false
		},
		data: {
			key: {
				name:"TREENAME"
			}
			,simpleData: {
				enable: true,
				idKey: "TREEID",
				pIdKey: "TREEPID"
			}
		},
		callback: {
			beforeClick: beforeClick3,
			onCheck: onCheck3
		}
	};

	function showMenu3() {
		var peojectObj = $("#addForm input[name='groupids3Name']");
		var peojectOffset = peojectObj.offset();
		var  y=peojectObj.position().top;//$("#addForm input[name='groupids3Name']")[0].scrollTop;
		var  x=peojectObj.position().left;//$("#addForm input[name='groupids3Name']")[0].scrollLeft;
		$("#menuContent3").css({left:x + "px", top:y + peojectObj.outerHeight() + "px"}).slideDown("fast");
	
		$("body").bind("mousedown", onBodyDown3);
	}
	function hideMenu3() {
		$("#menuContent3").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown3);
	}
	function onBodyDown3(event) {
		if (!( event.target.name == "groupids3Name" || event.target.id == "menuContent3" || $(event.target).parents("#menuContent3").length>0)) {
			hideMenu3();
		}
	}
	function beforeClick3(treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("groupsTree3");
		zTree.checkNode(treeNode, !treeNode.checked, true, true);
		return false;
	}
	function onCheck3(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("groupsTree3"),
		nodes = zTree.getCheckedNodes(true),
		v = "",
		name="";
		if(nodes.length>0){
			v=nodes[0].TREEID+",";
			name=nodes[0].TREENAME;
			var pNode = nodes[0].getParentNode();
			while(!!pNode ) { 
				v+= pNode.TREEID+",";
				pNode = pNode.getParentNode();
			}
			if (v.length > 0 ) v = v.substring(0, v.length-1);
		}
		
		
		/*for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i].TREEID + ",";
			name += nodes[i].TREENAME + ",";
		}
		if (v.length > 0 ) v = v.substring(0, v.length-1);
		if (name.length > 0 ) name = name.substring(0, name.length-1);*/
		$("#addForm input[name='groupids3']").val(v);
		$("#addForm input[name='groupids3Name']").val(name);
	}  
	$(document).ready(function(){
		$.fn.zTree.init($("#groupsTree3"), setting3, zNodes);
	});
</script>
<%@ include file="../foot.jsp"%>