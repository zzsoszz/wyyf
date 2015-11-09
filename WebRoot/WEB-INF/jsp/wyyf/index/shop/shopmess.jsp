<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ include file="../top.jsp"%>
<%String path = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()+ request.getContextPath(); %>
<script type="text/javascript">
	$(function(){
		/* mySession(); */
		$("#temp11").Slide({
			effect : "fade",
	    	speed : "normal",
			timer : 3000
		});
	});
</script>
<div class="dsigback" style="">
	 <div class="dsignbox" style="">
	 <c:forEach items="${shop_messlist}" var="val" varStatus="i">
	 	  <div class="dsignboxtop" style="">
	 	  	  <ul class="breadcrumb" style="background-color: transparent;">
                   <li><a href="index">首页</a></li>
                   <li><a href="index/ProductList">购物商城</a></li>
                   <li class="active"><a>商品详情</a></li>
              </ul>
	 	  </div>
	 	  <!--shopmess头部-->
	 	  <div class="clearfix" style="outline: 1px solid #F5F5F5;margin-bottom: 30px;">
	 	  	 <!--<div class="shopmess_boxleft" style="width: 400px;height: 510px;outline: 1px solid black;float: left;">-->
	 	  	 	<div id="temp11" style="float: left;">
			       <div class="JQ-content-box">
					      <ul class="JQ-slide-content" style="margin: 0px;padding: 0px;">
						     <li style="z-index: 1; opacity: 1;"><a><img src="${val.bg_st_img1 }" height="400" width="400"></a></li>
						     <li style="z-index: 0; opacity: 0;"><a><img src="${val.bg_st_img2 }" height="400" width="400"></a></li>
						     <li style="z-index: 0; opacity: 0;"><a><img src="${val.bg_st_img3 }" height="400" width="400"></a></li>
						     <li style="z-index: 0; opacity: 0;"><a><img src="${val.bg_st_img4 }" height="400" width="400"></a></li>
					      </ul>
			        </div>
			        <ul class="JQ-slide-nav clearfix">
			        	<c:if test="${!empty val.bg_st_img1 }">
				         	<li class="on"><a><img src="${val.bg_st_img1 }"></a></li>
				         </c:if>
				         <c:if test="${!empty val.bg_st_img1 }">
				         	<li class=""><a><img src="${val.bg_st_img2 }"></a></li>
				         </c:if>
				         <c:if test="${!empty val.bg_st_img1 }">
				         	<li class=""><a><img src="${val.bg_st_img3 }"></a></li>
				         </c:if>
				         <c:if test="${!empty val.bg_st_img1 }">
				         	<li class=""><a><img src="${val.bg_st_img4 }"></a></li>
				         </c:if>
			        </ul>
		        </div>
	 	  	 <!--</div>-->
		 	  	<div class="shopmess_boxright" style="">
		 	  	 	  <div class="good_describle" style="">
		 	  	 	  	${val.bg_st_name}
		 	  	 	    <p class="redcolor">${val.bg_st_summary}</p>
		 	  	 	  </div>
		 	  	 	  <div class="shopmess_right" style="">
		 	  	 	  	  <span>价格：<span class="redcolor"><strong>${val.bg_st_fbtpe==2?val.bg_st_pricetj:val.bg_st_pricezg }</strong></span>元/套</span>
		 	  	 	     <%--  <span class="shopmess_span1" style="">原价：${val.bg_st_pricezg }元/套</span> --%>
		 	  	 	      <!-- <span class="shopmess_line" style=""></span>
		 	  	 	      <div class="shopmess_mibi" style=""></div> -->
	                        <div class="table_num" style="">数量：
	                     				<span class="glyphicon glyphicon-minus" onclick="num_less()"></span>
	                     				<input id="num" value="1" type="text">
	                     				<span class="glyphicon glyphicon-plus" onclick="num_add()"></span>
	                     	</div>
	                     	<div>
	               			
	                     	<input type="hidden" value="${id }" id="proID"/>
	                     	   <a href="javascript:BuyNow()" class="shopmess_ra shopmess_ra1">立即购买</a>
	                     	   <a href="javascript:AddCart()" class="shopmess_ra shopmess_ra2">加入购物车</a>                     			
	                     	</div>      
		 	  	 	    </div>
		 	  	  </div>
		 	 </c:forEach> 	  
	 	  </div>
	 	  
          <!--shopmess内容-->
          <div class="shop_bulding  clearfix" style="">
	 	  	 	  <!--左边建材商城-->
	 	  	  <div class="shop_buldingleft" style="width: 190px;float: left;">	 	  		
                  <!--相似商品-->
	 	  		   <div class="shop_bltitle" style="">相似商品</div>
	 	  		   <div class="shop_blcon clearfix">
	 	  		   <c:forEach items="${shop_cheaplist}" var="val" varStatus="i">
                  	   <a href="index/shopmess?id=${val.bg_st_id}">
                  	      <div class="shop_blconbox">
                  	   	       <div class="shop_brbt">
                  	   	      	 <img src="${val.bg_st_img1}" alt="" title="" height="200" width="190">
                  	   	       </div>
                  	   	       <div class="shop_blbcon">
                  	   	   	        <div>${val.bg_st_name}</div>
                  	   	   	        <span class="redcolor shop_blconnum">RMB：<span class="" style="">${val.bg_st_pricetj}</span></span>
                  	   	            <span class="shop_blconsale">已售出：<span class="yellow">${val.num}</span></span>
                  	   	       </div>  	  
                  	      </div>
                  	   </a>
	 	  		    </c:forEach>  
                  	</div>
	 	  	  </div>
	 	  	  	 	  <!--左边建材商城结束-->
	 	  	  	 	  <!--右边商品信息-->
	 	  	  <div class="shop_buldingright clearfix" style="">
                  <!--右边底部商品展示-->
                  <div class="shop_brcon clearfix">
                  	   <ul class="nav nav-tabs" id="mytabs">
                          <li class="active"><a aria-expanded="false" href="#shopmess" data-toggle="tab">商品详情<span>2</span></a></li>
                          <li><a href="#shoplist" data-toggle="tab">成交记录</a></li>
                          <li><a href="#shoppj" data-toggle="tab">评价详情</a></li>
                          <!-- <li class=""><a aria-expanded="true" href="#shopkowns" data-toggle="tab">买家须知</a></li>                   -->
                       </ul>
                    <div class="tab-content">
                    
                      <div class="tab-pane fade  active in" id="shopmess" style="">
                       <!--商品详情-->
                       <c:forEach items="${shop_messlist2}" var="val" varStatus="i">
                          <ul class="shopmess_goods clearfix">
                          	<li>品牌：${val.pp}</li>
                          	<li>型号：${val.xh}</li>
                          <%-- 	<li>类型：${val.bg_st_norms}</li> --%>
                          	<li>产地：${val.cd}</li>
                          	<li>材质：${val.cz}</li>
                          </ul>
                           <ul class="shopmess_goodspic">
                           	  <%-- <li><img src="${val.bg_st_img1 }" ></li>
                           	  <li><img src="${val.bg_st_img2 }" ></li>
                           	  <li><img src="${val.bg_st_img3 }" ></li>
                           	  <li><img src="${val.bg_st_img4 }" ></li>
                           	  <li><img src="${val.bg_st_img5 }" ></li> --%>
                           	  ${val.bg_st_prodIntro}
                           </ul>		
                   		</c:forEach>
                      <div class="height"> </div>
                     </div>
                    
                      <div class="tab-pane fade clearfix" id="shoplist">
                    	<!--成交记录-->
                    	  <table class="shopmess_j1" id="shopmess_jl" style="" border="0">                     	
                     		<thead>
                     			<!--<th ></th>-->
                     			<tr><th class="table_hmess" style="">卖家</th>
                     			<th class="table_hprice" style="">价格</th>
                     			<th class="table_hnum" style="">数量</th>
                     			<th class="table_hcount">付款时间</th>
                     			<th class="table_hcaozuo" style="">款式和型号</th>
                     		</tr></thead>
                     		<!--成交记录-->
                     		<!--模板开始-->
                     		<c:forEach items="${chengjiao_list}" var="val" varStatus="i">
                     		<tbody>
                           		<tr><td class="table_mess clearfix" style="">
                     			    <span style="">${val.ae_st_name}</span>
                     			</td>
                     			<td char="table_price">
                     				 <strong class="redcolor">￥${val.bh_st_spprice}</strong>
                     			</td>
                     			<td class="table_num" style="">
                     				${val.num}
                     			</td>
                     			<td class="table_countprice">
                     				 <p class="table_priceold">${val.bh_dt_addDate}</p>
                     			</td>
                     			<td class="table_ks" style="">
                     				<span class="table_delete">${val.bg_st_remark}</span>
                     			</td></tr>
                     	    </tbody>
                     	    </c:forEach>
                     	    <!--模板结束-->
                     	    <!--记录结束-->
                     	</table>
                    </div>
                      <div class="tab-pane fade clearfix" id="shoppj">
                    	<!--评价-->
                           <ul class="shop_pj">
                           	<!--模板开始-->
                           	<c:forEach items="${assess_list}" var="val" varStatus="i">
                           	<li class="clearfix">
                           		<div class="shop_pjl">
                           			 <div><img src="${val.ag_st_url }" width="100"></div>
                           			 <span>${val.ae_st_name }</span>
                           		</div>
                           		<div class="shop_pjr">
                           			<div>
										<span>${val.be_st_content }</span>
                                        <img src="${val.bg_st_img1 }" height="50" width="50">
										<img src="${val.bg_st_img2 }" height="50" width="50">
										<img src="${val.bg_st_img3 }" height="50" width="50">
									</div>
                           			<p class="gray"><span>${val.be_dt_addDate}</span><span>评价人：${val.ae_st_name}</span></p>
                           		</div>
                           	</li>
                           	</c:forEach>
                           	<!--模板结束-->
                           	</ul>
                           	
                    </div>
                    
                    <!--   <div class="tab-pane fade clearfix in" style="display:none;" id="shopkowns"> -->
                    	<!--买家须知-->
                           <!-- <div style="width: 708px;margin: 0 auto;margin-top: 20px;">
                           	  <img src="../images/qw_05.png">
                           </div>
                      </div> -->
                    </div>
                      	   
                  </div>
                  <!--右边底部商品展示结束-->
	 	  	  </div>
	 	  	  
	 	  	</div>
	 </div>	  	
</div>
<%@ include file="../foot.jsp"%>
<script type="text/javascript">
function num_less(){
	var num=$("#num").val();
	if(num>0){
		num--;
		$("#num").val(num);
		$("#num").text(num);
	}else{
		alert("购买数量不能小于0");
	}
};
function num_add(){
	var num=$("#num").val();
		num++;
		$("#num").val(num);
		$("#num").text(num);
};
function province_change(){
if($("#province").val()!=""){
			$.ajax({
		          	type:'POST',
		          	url:'<%=path%>/index/GetChildAreaByPid',
		          	data: "pid=" + $.trim($("#province").val()), 
		          	dataType:'text',
		          	success:function(msg1){
		          	var msg = (new Function("return " + msg1))();
		          	if(msg.success){
		          	$("#city option").remove();
		          	var city = "";
		          		for(var i=0;i<msg.result[0].length;i++){
		          			city+="<option value='"+msg.result[0][i].d_code+"'>"+msg.result[0][i].d_name+"</option>";
		          		}
		          		 $('#city').html(city);
		          	}
		          	else{
		          		alert("获取市的数据错误");
		          		}
		          	},
		          	error:function(msg){
		          	alert("	网络传输错误，请检查您的网络连接");	
		          	}
			});
			}else{
				$("#city option").remove();
			}
};
function city_change(){
if($("#city").val()!=""){
			$.ajax({
		          	type:'POST',
		          	url:'<%=path%>/index/GetChildAreaByPid',
		          	data: "pid=" + $.trim($("#city").val()), 
		          	dataType:'text',
		          	success:function(msg1){
		          	var msg = (new Function("return " + msg1))();
		          	if(msg.success){
		          	$("#district option").remove();
		          	var district ="";
		          		for(var i=0;i<msg.result[0].length;i++){
		          			district+="<option value='"+msg.result[0][i].d_code+"'>"+msg.result[0][i].d_name+"</option>";
		          		};
		          		$('#district').html(district);
		          	}
		          	else{
		          		alert("获取县的数据错误");
		          		}
		          	},
		          	error:function(msg){
		          	alert("	网络传输错误，请检查您的网络连接");	
		          	}
			});
			}else{
				$("#district option").remove();
			}
};
function AddCart(){
	if($("#num").val()>0){
			$.ajax({
		          	type:'POST',
		          	url:'<%=path%>/index/mysession',
		          	data: "id=" + $.trim($("#proID").val())+"&num="+$.trim($("#num").val()), 
		          	dataType:'text',
		          	success:function(msg){
		          	if(msg){
		          		alert("已成功加入购物车");
		          	}
		          	else{
		          		alert("加入购物车失败，请稍候再试。");
		          		}
		          	},
		          	error:function(msg){
		          	alert("	网络传输错误，请检查您的网络连接");	
		          	}
			});
			}else{
				//alert("购物数量应该大于0件");
			}
}
function BuyNow(){
	if($("#num").val()>0){
			$.ajax({
		          	type:'POST',
		          	url:'<%=path%>/index/buy',
		          	data: "id=" + $.trim($("#proID").val())+"&num="+$.trim($("#num").val()), 
		          	dataType:'text',
		          	success:function(msg){
		          		window.location.href=msg;
		          	},
		          	error:function(msg){
		          	alert("	网络传输错误，请检查您的网络连接");	
		          	}
			});
			}else{
				//alert("购物数量应该大于0件");
			}
};
</script>