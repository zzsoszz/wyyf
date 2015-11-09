<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="jsplib.jsp"%>
<!DOCTYPE html>
<html  lang="zh-CN">
	
	<head>
		<meta charset="utf-8" />
		
		<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
		<title></title>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/wap/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/css/wap/css/main.css">
	</head>
	<body class="indexbody">
		
		<%@ include file="header.jsp"%> 
	
        <div class="yanfang_nav" style="width: 100%;height: 50px;background-color: #E8E8E8;">
        	 <div class=""  >

        	 	  <ul  id="mytabs" class="nav nav-tabs yf_bd" style="width: 100%;">
        	 	 <c:if test="${wyyfType == 1}" >
        	 	
        	 	       <li class="nav_one nav_two1 active" id="checkfree"style="width: 100%;float: left; " ><a href="#checkfreebox"data-toggle="tab">免费验房</a></li>
        	 	
        	 	 </c:if>
		        <c:if test="${wyyfType == 2}" >
		        	 	<li class="nav_one nav_two2 active" id="checkgold" style="width: 100%;float: left;"><a  href="#checkgoldbox"data-toggle="tab">金牌验房</a></li>
        	    </c:if>
        	    
        	     <c:if test="${ empty  wyyfType }" >
        	        	<li class="nav_one nav_two1 active" id="checkfree"style="width: 50%;float: left;" ><a href="#checkfreebox"data-toggle="tab">免费验房</a></li>
		        	 	<li class="nav_one nav_two2" id="checkgold" style="width: 50%;float: left;"><a href="#checkgoldbox"data-toggle="tab">金牌验房</a></li>
        	    </c:if>
        	    </ul>       	    
        	 </div>
        </div>
        <iframe src="about:blank" name="blankFrame" id="blankFrame" style="display: none;"></iframe>
        <div  class="myservice tab-content" >
          <!---->
            <c:if test="${ empty  wyyfType }" >
            
                <div class="tab-pane fade in active" id="checkfreebox">
                	 <div id="yangfang_free" class="yanfang_navcon" >
                	 
                	    <form id="wzyfForm2"  target="blankFrame"   action="wap/addApply" method="post" class="yanfang_form"   >
			        	 	 <div class="yanfang_nctop" >
								  <!-- <div class="yanfang_formtop clearfix">
									  <div class="yanfang_formtop1">
										  <input type="radio" name="bf_st_incity" value="1" /><span class="myaddrin">绕城内</span>
									  </div>
									  <div class="yanfang_formtop1">
										  <input type="radio" name="bf_st_incity" value="2" /><span class="myaddrin">绕城外</span>
									  </div>
								  </div> -->
								  <div class="yanfang_formtop clearfix" style="height: auto;text-align: center">
									  <p>中国第一家挂牌的专业验房机构</p>
									  <p>现在已有<strong class="recolor">${mfr }</strong>人报名</p>
								  </div>
								  <div class="yanfang_input">
			        	 	  	  	<input class="yztj" type="text" placeholder="请输入楼盘" name="bf_st_address"  />
			        	 	  	  </div>
			        	 	  	  <div class="yanfang_input">
			        	 	  	  	  <input class="yztj" type="text" placeholder="请输入姓名" name="bf_st_name" />
			        	 	  	  </div>
			        	 	  	  <div class="yanfang_input">
			        	 	  	  	  <input class="yztj" type="tel" placeholder="请输入电话" name="bf_st_tell" />
			        	 	  	  </div>
			        	 	  	  <div class="yanfang_input">
			        	 	  	  	<input class="yanfang_px yztj number" type="text"  placeholder="请输入报名户数" name="bf_st_housnum" />
			        	 	  	  </div>
			        	 	  	   <div class="yanfang_input">
			        	 	  	  	<input  class="yztj number" type="text" placeholder="请输入建筑面积" name="bf_st_area" />
			        	 	  	  </div> 
			        	 	  	  <div class="yanfang_input">
			        	 	  	  	<input class="yztj" type="date" id="theDate" placeholder="请选择时间"   name="bf_dt_time" />
			        	 	  	  	
			        	 	  	  <script>document.getElementById('theDate').value = new Date().toISOString().substring(0, 10);</script>
			        	 	  	  	
			        	 	  	  	
			        	 	  	  </div>
			        	 	  	  
			        	 	  	  
			        	 	  	 	  
			        	 	 
			        	 	 </div>
			        	 	 
			        	 	  <div class="yanfang_nctwo">
			        	 	  	 <div>温馨提醒</div>
			        	 	  </div>
			        	 	  
							   <div class="yf_shareother" >
								   <span class="green">
								         <p>1.精装房只接受金牌验房。</p>
										 <p>2.验房时请带上户型图。</p>
										 <p>3.通过“我要验房”APP分享给小伙伴有米奇币哟。</p>
								   </span>
							   </div>
			        	 	   <!-- 
			        	 	   <input type="submit" class="btn btn-block mybuttom" >预约</button>
			        	 	    -->
			        	 	   <input readonly="readonly" onclick="tkopeng(this,2)"  type="text" class="btn btn-block mybuttom"  value="预约"/>
							   <div class="tishi" >您的信息将被严格保密，资料提交后客服将在24小时之内联系您</div>
							   <div class="clearfix yf_tel" >
	                              <span >客户热线:400-028-5998</span>
								   <a href="tel:4000285998" >拨号</a>
							   </div>
			        	 	   <!--<div class="tankuang" ></div>-->
	                           <!--<div class="zhezhao" ></div>-->
	                           
	                           <input type="hidden" name="bf_st_type"  value="2">
	                           <input type="hidden" name="bf_st_receiveid"  value="4af7711ba7ce42ce9277013a25dae58e">
                        </form>
		          </div>
                </div>
               <div class="tab-pane fade" id="checkgoldbox"> 
               	    <div id="yanfang_gold"  class="yanfang_navcon">
               	    		<form id="wzyfForm3"  action="wap/addApply" method="post" class="yanfang_form">
				        	 	<div class="yanfang_nctop" style="">
											  <div class="yanfang_formtop clearfix" style="height: auto;text-align: center">
									  <p>中国第一家挂牌的专业验房机构</p>
									  <p>现在已有<strong class="recolor">${jpr }</strong>人报名</p>
								  </div>
								              <div class="yanfang_input">
						        	 	  	  	<input class="yztj" type="text" placeholder="请输入楼盘"  name="bf_st_address" value=""/>
						        	 	  	  </div>
						        	 	  	  <div class="yanfang_input">
						        	 	  	  	<input class="yztj" id="freenum number"   type="text" placeholder="请输入报名户数"  name="bf_st_housnum" value=""/>
						        	 	  	  </div>	        	 	  	 
						        	 	  	  <div class="yanfang_input">
						        	 	  	  	<input  class="yztj number" type="text" placeholder="请输入建筑面积"  name="bf_st_area"  value=""/>
						        	 	  	  </div>
						        	 	  	  <div class="yanfang_input">
						        	 	  	  	<input class="yztj" type="date" id="theDate1" placeholder="请选择时间" value=""  name="bf_dt_time" />
						        	 	  	  	<script>document.getElementById('theDate1').value = new Date().toISOString().substring(0, 10);</script>
						        	 	  	  </div>
						        	 	  	  <div class="yanfang_input">
						        	 	  	  	  <input class="yztj" type="text" placeholder="请输入姓名"  name="bf_st_name" value=""/>
						        	 	  	  </div>
						        	 	  	  <div class="yanfang_input">
						        	 	  	  	  <input class="yztj" type="tel" placeholder="请输入电话"  name="bf_st_tell" value=""/>
						        	 	  	  </div>
				        	 	</div>
								<div class="yanfang_nctwo">
									<div>温馨提醒</div>
								</div>
								 <!--  <div class="yanfang_ncthree" >
							            <div><span class="green">金牌验房：</span>金牌验房是针对于精装房</div>
						          </div> -->
								<div class="yf_shareother" >
								     <span class="green">
								          <p>1.金牌验房2元/平方。</p>
										 <p>2.验房时请带上户型图。</p>
										 <p>3.通过“我要验房”APP分享给小伙伴有米奇币哟。</p>
								     </span>
								</div>
								<input class="btn btn-block mybuttom " onclick="tkopeng(this,3)" value="预约">
								<div class="tishi" >您的信息将被严格保密，资料提交后客服将在24小时之内联系您</div>
								<div class="clearfix yf_tel" >
									<span >客户热线:400-028-5998</span>
									<a href="tel:4000285998" >拨号</a>
								</div>
								<!--<div class="tankuang" ></div>-->
								<!--<div class="zhezhao" ></div>-->
								<input type="hidden" name="bf_st_type"  value="3">
								<input type="hidden" name="bf_st_receiveid"  value="820bb6377b7a43ba88607c777e9d6b5d">
						 </form>
						
					</div>
			   </div>
            </c:if>
           <c:if test="${wyyfType == 1}" >
                <div class="tab-pane fade in active" id="checkfreebox">
                	 <div id="yangfang_free" class="yanfang_navcon" >
                	 
                	    <form id="wzyfForm2"  target="blankFrame"   action="wap/addApply" method="post" class="yanfang_form"   >
			        	 	 <div class="yanfang_nctop" >
								  <!-- <div class="yanfang_formtop clearfix">
									  <div class="yanfang_formtop1">
										  <input type="radio" name="bf_st_incity" value="1" /><span class="myaddrin">绕城内</span>
									  </div>
									  <div class="yanfang_formtop1">
										  <input type="radio" name="bf_st_incity" value="2" /><span class="myaddrin">绕城外</span>
									  </div>
								  </div> -->
								  <div class="yanfang_formtop clearfix" style="height: auto;text-align: center">
									  <p>中国第一家挂牌的专业验房机构</p>
									  <p>现在已有<strong class="recolor">${mfr }</strong>人报名</p>
								  </div>
								  <div class="yanfang_input">
			        	 	  	  	<input class="yztj" type="text" placeholder="请输入楼盘" name="bf_st_address"  />
			        	 	  	  </div>
			        	 	  	  <div class="yanfang_input">
			        	 	  	  	  <input class="yztj" type="text" placeholder="请输入姓名" name="bf_st_name" />
			        	 	  	  </div>
			        	 	  	  <div class="yanfang_input">
			        	 	  	  	  <input class="yztj" type="tel" placeholder="请输入电话" name="bf_st_tell" />
			        	 	  	  </div>
			        	 	  	  <div class="yanfang_input">
			        	 	  	  	<input class="yanfang_px yztj number" type="text"  placeholder="请输入报名户数" name="bf_st_housnum" />
			        	 	  	  </div>
			        	 	  	   <div class="yanfang_input">
			        	 	  	  	<input  class="yztj number" type="text" placeholder="请输入建筑面积" name="bf_st_area" />
			        	 	  	  </div> 
			        	 	  	  <div class="yanfang_input">
			        	 	  	  	<input class="yztj" type="date" id="theDate" placeholder="请选择时间"   name="bf_dt_time" />
			        	 	  	  	
			        	 	  	  <script>document.getElementById('theDate').value = new Date().toISOString().substring(0, 10);</script>
			        	 	  	  	
			        	 	  	  	
			        	 	  	  </div>
			        	 	  	  
			        	 	  	  
			        	 	  	 	  
			        	 	 
			        	 	 </div>
			        	 	 
			        	 	  <div class="yanfang_nctwo">
			        	 	  	 <div>温馨提醒</div>
			        	 	  </div>
			        	 	  
							   <div class="yf_shareother" >
								   <span class="green">
								         <p>1.精装房只接受金牌验房。</p>
										 <p>2.验房时请带上户型图。</p>
										 <p>3.通过“我要验房”APP分享给小伙伴有米奇币哟。</p>
								   </span>
							   </div>
			        	 	   <!-- 
			        	 	   <input type="submit" class="btn btn-block mybuttom" >预约</button>
			        	 	    -->
			        	 	   <input readonly="readonly" onclick="tkopeng(this,2)"  type="text" class="btn btn-block mybuttom"  value="预约"/>
							   <div class="tishi" >您的信息将被严格保密，资料提交后客服将在24小时之内联系您</div>
							   <div class="clearfix yf_tel" >
	                              <span >客户热线:400-028-5998</span>
								   <a href="tel:4000285998" >拨号</a>
							   </div>
			        	 	   <!--<div class="tankuang" ></div>-->
	                           <!--<div class="zhezhao" ></div>-->
	                           
	                           <input type="hidden" name="bf_st_type"  value="2">
	                           <input type="hidden" name="bf_st_receiveid"  value="4af7711ba7ce42ce9277013a25dae58e">
                        </form>
		          </div>
                </div>
                </c:if>
              
                 <c:if test="${wyyfType == 2}" >
               <div class="tab-pane  active" id="checkgoldbox"> 
               	    <div id="yanfang_gold"  class="yanfang_navcon">
               	    		<form id="wzyfForm3"  action="wap/addApply" method="post" class="yanfang_form">
				        	 	<div class="yanfang_nctop" style="">
											 <!--  <div class="yanfang_formtop clearfix">
												  <div class="yanfang_formtop1">
														  <input type="radio" name="bf_st_incity" value="1" /><span class="myaddrin">绕城内</span>
													  </div>
													  <div class="yanfang_formtop1">
														  <input type="radio" name="bf_st_incity" value="2" /><span class="myaddrin">绕城外</span>
													  </div>
											  </div> -->
											  <div class="yanfang_formtop clearfix" style="height: auto;text-align: center">
									  <p>中国第一家挂牌的专业验房机构</p>
									  <p>现在已有<strong class="recolor">${jpr }</strong>人报名</p>
								  </div>
								              <div class="yanfang_input">
						        	 	  	  	<input class="yztj" type="text" placeholder="请输入楼盘"  name="bf_st_address" value=""/>
						        	 	  	  </div>
						        	 	  	  <div class="yanfang_input">
						        	 	  	  	<input class="yztj" id="freenum number"   type="text" placeholder="请输入报名户数"  name="bf_st_housnum" value=""/>
						        	 	  	  </div>	        	 	  	 
						        	 	  	  <div class="yanfang_input">
						        	 	  	  	<input  class="yztj number" type="text" placeholder="请输入建筑面积"  name="bf_st_area"  value=""/>
						        	 	  	  </div>
						        	 	  	  <div class="yanfang_input">
						        	 	  	  	<input class="yztj" type="date" id="theDate1" placeholder="请选择时间" value=""  name="bf_dt_time" />
						        	 	  	  	<script>document.getElementById('theDate1').value = new Date().toISOString().substring(0, 10);</script>
						        	 	  	  </div>
						        	 	  	  <div class="yanfang_input">
						        	 	  	  	  <input class="yztj" type="text" placeholder="请输入姓名"  name="bf_st_name" value=""/>
						        	 	  	  </div>
						        	 	  	  <div class="yanfang_input">
						        	 	  	  	  <input class="yztj" type="tel" placeholder="请输入电话"  name="bf_st_tell" value=""/>
						        	 	  	  </div>
				        	 	</div>
								<div class="yanfang_nctwo">
									<div>温馨提醒</div>
								</div>
								<div class="yf_shareother" >
								     <span class="green">
								          <p>1.金牌验房2元/平方。</p>
										 <p>2.验房时请带上户型图。</p>
										 <p>3.通过“我要验房”APP分享给小伙伴有米奇币哟。</p>
								     </span>
								</div>
								<input class="btn btn-block mybuttom " onclick="tkopeng(this,3)" value="预约">
								<div class="tishi" >您的信息将被严格保密，资料提交后客服将在24小时之内联系您</div>
								<div class="clearfix yf_tel" >
									<span >客户热线:400-028-5998</span>
									<a href="tel:4000285998" >拨号</a>
								</div>
								<!--<div class="tankuang" ></div>-->
								<!--<div class="zhezhao" ></div>-->
								<input type="hidden" name="bf_st_type"  value="3">
								<input type="hidden" name="bf_st_receiveid"  value="820bb6377b7a43ba88607c777e9d6b5d">
						 </form>
						
					</div>
			   </div>
			   </c:if>
			   
			   
			   
			   
        </div>
		<div class="tankuang" ></div>
		<div class="zhezhao" ></div>
	<!--底部-->
	<%@ include file="footer.jsp"%>
	
	</body>
	
	 <script src="${ctx}/js/wap/js/jquery-1.11.2.min.js"></script>
	 <script type="text/javascript" src="${ctx}/js/wap/js/bootstrap.min.js"> </script>
	 <script src="${ctx}/js/wap/js/pmain.js"></script>
	 
</html>
