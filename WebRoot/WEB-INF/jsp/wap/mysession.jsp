<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="jsplib.jsp"%>
<!DOCTYPE html>
<html  lang="zh-CN">

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
		<title></title>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/wap/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/wap/css/main.css"/>		
	</head>
		<script src="${ctx}/js/wap/js/jquery-1.11.2.min.js"></script>
	<body>
		<!--<div class="page">-->
			<nav class="index navbar clearfix design_nav nav_bg" >
				<div class="mess_nav1" align="center"><a href="javascript:;" onClick="javascript :history.back(-1);"><i class="glyphicon glyphicon-menu-left"style="color: white;"></i></a></div>
				<div class="design_nav2"  style="">购物车</div>
				<!-- <div class="mess_nav1 index_share" onclick="tkopeng(this,121)"><i class="iconfont icon-fenxiang"></i></div> -->
			</nav>
			<!-- <section class="nav nav-tabs design_check my_se_status">
				<ul class="clearfix nav nav-tabs ">
					<li id="wzfLi" class=" active"><a href="#notPay" data-toggle="tab">未支付</a></li>
					<li id="yzfLi"><a href="#havePay" data-toggle="tab" id="yzf"> 已支付</a></li>
				</ul>
			</section> -->
			<!-- 未支付 -->
			<!-- <div  class="myservice tab-content"> -->	
				<div class="tab-pane active" id="notPay"style="min-height: 570px;">
				<!-- 购物车无商品时显示，请见pmian.js 67行		 -->
				
				
				<c:forEach items="${cartlist}" var="val" varStatus="i" >
					<section class="my_se_product clearfix" >
						  <div class="my_se_checkbox">
								<input class="check" type="checkbox" name="sessioncheck"/>							
								<label class="checkflag" >&nbsp;&nbsp;</label> 
							   <input type="hidden"  class="pid" value="${val.bn_st_id}"/> 
						  </div>
						<div class="my_se_product_1 clearfix">
							<img  class="img-responsive my_se_image"   src="${val.bg_st_img1 }" alt="" >
							<div class="clearfix">
								<p class="session_pp" >${val.bg_st_summary}</p>
								<p class="session_pp clearfix">
								    <input type="hidden" class="price" value="${val.bg_st_pricezg}">
									<span class="price f_fl ">￥${val.bg_st_pricezg}</span>
									<span class="f_fr " >数量:<input type="text" class="count" value="${val.bn_st_prodnum}" style="width:15%;text-align:center;" disabled="disabled"> </span>
								</P>
								<p class="session_pp">
								
								总价:<span class="countprice"></span><span class="my_se_countprice"><a href="javascript:void(0)" class="btn btn-primary btn-xs"  onclick="javascript:deleteProduct('${val.bn_st_id}')">删除</a></span> </p>
								
								
							</div>
						</div>
					</section>
				</c:forEach>
				<script type="text/javascript">
				function deleteProduct(id) {
					console.debug(id);
					var url = "wap/deleteProduct?id="+id;
					
					$.post(url,"",function callback(data) {
						if(data==0){
							alert("删除成功");
							location.href = "${pageContext.request.contextPath}/wap/mysession";
						}else{
							alert("删除失败");
						}
					},"json");
				}
				var countPrice=0;
				var allprice=0;
				var p=1;
				$(function(){
					$(".my_se_product").each(function(){

		                var price=$(this).find(".price").val();
						var count=$(this).find(".count").val();
						 countPrice=(parseInt(count)*parseFloat(price)).toFixed(2);
						console.log(countPrice)
						$(this).find(".countprice").html("￥"+countPrice);
						allprice+=parseFloat(countPrice);
						
		                $("#pri").html(allprice)
					});
					var checkboxList=document.getElementsByName("sessioncheck");
					 for(var i=0;i<checkboxList.length;i++){
						 checkboxList[i].checked=true;
					 }
					
				})
				
				
				
				
				
				
				</script>
				
 				<div class="padding10">  总金额: ￥<strong id="pri" class="redcolor"></strong></div>
				<div id="dd" class="ch_pr_btn my_se_bigbtn">
						<button id="loginBtn"  class="btn btn-info btn-md btn-block btn-submit" type="submit">
							
							<!-- <a href="wap/plistmess" style="display: block;color: white">确认支付</a> -->
							确认支付
							
							
						</button>
				</div>
				<div class="listmess_box1">
			  	    		<p>收货人:<span><input id="shr" value="" class="plistmess_input " type="text"></span></p>
			  	    		<p>手机号码:<span><input id="sjhm"  value="" class="plistmess_input" type="text"></span></p>
			  	    		<p>收货地址:<span><input id="shdz" value="" class="plistmess_input" type="text"></span></p>
			  	    		
		    </div>
			</div>
			<!--  已支付 -->
			<!-- <div  class="tab-pane" id="havePay" style="min-height:570px;">
			
			</div>
			
			
			
		</div> -->
		<!-- //底部 -->
		
		
		 
		<%@ include file="footer.jsp"%>
	
		<div class="tankuang " ></div>
		<div class="zhezhao" ></div>
		<input  id="tt" type="hidden" value="${tt}" />
		
			<input  id="shopinfo" type="hidden" value="${shopinfo }" />
		
		
		
	</body>

	<script type="text/javascript" src="${ctx}/js/wap/js/bootstrap.min.js"> </script>
	<script src="${ctx}/js/wap/js/pmain.js"></script>
	<script src="${ctx}/js/wap/js/jquery.json.min.js"></script>
	
	
	
	 <script type="text/javascript">
		$(function() {
		
		/* var tt=$.getUrlParam('tt');
		if(tt==1){
			$("#notPay").attr("class","tab-pane");
			$("#havePay").attr("class","tab-pane active");
		} */
	var t=	$("#tt").val();
	if(t==1){ 
		$("#notPay").attr("class","tab-pane");
		$("#havePay").attr("class","tab-pane active");
		
		$("#wzfLi").attr("class","");
		$("#yzfLi").attr("class","active");
		
	} yzf();
	
			//登录按钮触发事件 
			$("#loginBtn").click(function() {
				console.debug(1);
				onLogin();
			});
			$("#yzf").click(function() {
				yzf();
			});
		});
		//获取购物车商品
		function onLogin() {//测试下单
			var url = "wap/addorder";
		
		
		var shr=$("#shr").val();
		var sjhm=$("#sjhm").val();
		var shdz=$("#shdz").val();
		
		var  shopinfo=$("#shopinfo").val();
			var data="json={\"bh_st_shname\":\""+shr+"\",\"bh_st_shphone\":\""+sjhm+"\",\"bh_st_shaddress\":\""+shdz+"\","+shopinfo+"}";
		
			console.debug(data);
			$.post(url,data,function callback(data) {
				console.debug(data.success);
				
			if(data.success=="true"){
				
				location.href = "${pageContext.request.contextPath}/wap/payfororderid?pkid="+data.order;
				
			}else{
				alert(data.msg);
				
			}},"json");
		}
		//获取已支付订单
		function yzf() {
			
			$("#havePay").html("");
			
			$("yzf").attr("havePay","tab-pane active");
			var url = "wap/getOrderList";
			var data="json={\"pageIndex\":\"1\"}";//这里写死了 测试用
			$.post(url,data,function callback(data) {
				console.debug(data);
				$("#havePay").append(data);
				
			},"html");
		}
		</script>
	
	
	
	
	
	<!--  <script>
	 
	   var parseParam=function(param, key){
	 	    var paramStr="";
	 	    if(param instanceof String||param instanceof Number||param instanceof Boolean){
	 	        paramStr+="&"+key+"="+encodeURIComponent(param);
	 	    }else{
	 	        $.each(param,function(i){
	 	            var k=key==null?i:key+(param instanceof Array?"["+i+"]":"."+i);
	 	            paramStr+='&'+parseParam(this, k);
	 	        });
	 	    }
	 	    return paramStr.substr(1);
	 	};
	 	
	 	
	 	function Product(productele,changecallback)
	 	{
	 		self=this;
	 		this.price=productele.find(".price").text();
	 		this.checkele=productele.find(".checkflag");
	 		this.countele=productele.find(".count");
	 		this.pid=productele.find(".pid").val();
	 		this.my_se_countpriceele=productele.find(".my_se_countprice");
	 		
	 		
	 		this.init=function()
	 		{
	 			this.countele.change(
	 		 			function()
	 		 			{
	 		 				self.my_se_countpriceele.text(self.countele.val()* self.price);
	 		 				changecallback();
	 		 			}
	 		 	);
	 			//self.countele.val(1);
	 			self.countele.change();
	 		};
	 		
	 		this.init();
	 	}
	 	
		function Cart(cartele)
		{
			thiscart=this;
			this.productlist=cartele.find(".my_se_product");
			this.submitele=cartele.find(".btn-submit");
			
			this.plist=[];
			this.plistresult;
			this.totalprice=0;
			
			this.freshCart=function()
			{
				var checkplist=thiscart.plist.filter(
						function(obj)
						{
							if(obj.checkele.hasClass("checked")==true)
							{
								return true;
							}
							else
							{
								return false;
							}
						}
				);
				
				thiscart.plistresult=$(checkplist).map(
				  function(index)
				  {
					var pp={};
					pp.bn_st_id=this.pid;
					pp.bn_st_prodnum=this.countele.val();
					return pp;		
				 }
				).get();
				
				
				thiscart.totalprice=0;
				
				$(checkplist).each(function(index)
					 {
						thiscart.totalprice+= this.my_se_countpriceele.text();		
					 }
				);
				
			};
			
			
			this.productlist.each(
				function()
				{
					var p=new Product($(this),
						$.proxy(
							function()
							{
								thiscart.freshCart();
							}
						,thiscart)
					);
					thiscart.plist.push(p);
				}
			);
			
			this.submitele.click(
					function()
					{
						thiscart.freshCart();
						//alert(thiscart.pliststr);
						//var params = {width:1900, height:1200 };
						//var str = jQuery.param(thiscart.plistresult);
						//alert(str);
						//alert(thiscart.plistresult.length);
						if(thiscart.plistresult.length<=0)
						{
							alert("please choose one at least");
							return;
						}
						var json=$.toJSON(thiscart.plistresult);
						if(json)
						{
							window.location.href="${ctx}/wap/plistmess?json="+json;
						}
					}
			);
		}
	 	$(document).ready(
	 		function()
	 		{
	 			var card=new Cart($(".myservice"));
	 		}
		);
	 	
	 	
	 </script> -->
	 
	
	 
</html>
