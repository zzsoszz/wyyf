

(function($){
	 		$.getUrlParam
	 		 = function(name)
	 		{
	 		var reg
	 		 = new RegExp("(^|&)"+
	 		 name +"=([^&]*)(&|$)");
	 		var r
	 		 = window.location.search.substr(1).match(reg);
	 		if (r!=null) return unescape(r[2]); return null;
	 		}
	 		})(jQuery);


var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?ed0a53e9a41e0024fb668a5734f58b19";
  var s = document.getElementsByTagName("script")[0];
  s.parentNode.insertBefore(hm, s);
})();
$(document).ready(function(){
		$(".ch_body_1:even").css("background-color","#F8C55C");
		$(".ch_body_1:odd").css("background-color","#E95355");	
		cssCheckBox();
		viewProduct();
		fontColor();
		pageName();
		/*console.log(document.referrer);*/
		// 注册时手机号码验证
		$("input[type=tel]").blur(function(){
			var tel=/^0?(13|14|15|18)[0-9]{9}$/;			
			var inNum=$(this).val();
			if(!tel.test(inNum)){
				$(this).val("");
				$(this).attr("placeholder","请输入有效电话号码")
				$(this).focus();
			}else{}
		});
		
		$(".signpass").blur(function(){
			//var passCh= /^[\@A-Za-z0-9\!\#\$\%\^\&\*\.\~]{6,22}$/;
			var passCh=/^[A-Za-z0-9_-]+$/;
			if(!$(this).val().test(passCh)){
				$(this).val("");
				$(this).attr("placeholder","请输入6到16位字母或数字");
				$(this).focus();
			}
		});
		$(".signpass2").bind('input propertychange',function(){
			var pass1=$(".signpass").val();
			$(this).val();
			if($(this).val()!=pass1){

				$(this).focus();
			}else{
				console.log($("#signname").val());

				if($("#signname").val()!=""&&pass1!=""&&$(this).val()!=""){
					$("#btnSendCode").removeAttr("disabled");//启用按钮
				}

			}
		})
		/*$(".signpass2").on("keyup",function(){
			var pass1=$(".signpass").val();
			$(this).val();
			if($(this).val()!=pass1){

				$(this).focus();
			}else{
				console.log($("#signname").val());

				if($("#signname").val()!=""&&pass1!=""&&$(this).val()!=""){
					$("#btnSendCode").removeAttr("disabled");//启用按钮
				}

			}
		})*/
         /*$(".number").blur(function(){
        	 
        	 var number=/^[0-9]*$/;
        	 console.log($(this).val());
        	 console.log(number)
        	 if($(this).val()!=number){
        		 $(this).val("");
        		 $(this).attr("placeholder","请输入数字");
        		 console.log($(this).val())
        	 }
         });*/
//	    找工长界面选择特效
	$(".fixture_ps ul li a").click(function(){
		$(this).addClass(".fix_color");
	});
})

 function my_header(obj){
 	$(".jc_xiala ul").show();
 	$(".sp_zz").css({"visibility":"visible"});
 	$(".jc_xiala ul").text('');	
 	$(".jc_header div").css({"color":"#272822"});
 	$(obj).css({"color":"#1E76B8"});
 	if($(obj).index()===0){ 
 		$.map(["默认排序","评价最高","最新发布","销量优先"],function(name){
 			$(".jc_xiala ul").append("<li onclick='sp_li()'>"+name+"</li>")
 		}) 		
 	}else if($(obj).index()===1){ 		
 		$.map(["默认排序","从低到高","从高到低"],function(name){
 			$(".jc_xiala ul").append("<li onclick='sp_li()'>"+name+"</li>")
 		})
 	}else if($(obj).index()===2){ 		
 		$.map(["**商家","***商家"],function(name){
 			$(".jc_xiala ul").append("<li onclick='sp_li()'>"+name+"</li>")
 		})
 	}else if($(obj).index()===3){
 		
 		$.map(["50-100","100-200"],function(name){
 			$(".jc_xiala ul").append("<li onclick='sp_li()'>"+name+"</li>")
 		}) 		
 	} 	
 }

function sp_li(){ 
 		$(".jc_xiala ul").fadeOut("fast"); 	
 		$(".sp_zz").css({"visibility":"hidden"});
 		$(".jc_header div").css({"color":"#272822"});
 	}
this.i=0;
function sp_add(obj){			
	this.i=parseInt($(obj).next().text());	
	this.i++;
	$(obj).next().text(this.i);
}
function sp_reduce(obj){
	this.i=parseInt($(obj).prev().text());	
	if(this.i>0){this.i--;}	
	$(obj).prev().text(this.i);
}
//替换复选框的样式
function cssCheckBox() {
    $(":input[type=checkbox] + label").on("click",this, function(){   
    if(this.thecheck===true){            
    	this.thecheck=false;
    	$(this).removeClass("checked");
    }
    else{     
    	this.thecheck=true;            
    	$(this).addClass("checked");  
    }
})
}
// 当购物车为空的状态
function viewProduct(){	
	if($("#notPay").find(".my_se_product").length===0){	
	$("#notPay").text('');	
		$("#notPay").css({"width":"100%","text-align":"center"})
		$("#notPay").append("<div class='my_se_shop'><span class='glyphicon glyphicon-shopping-cart'></span></div><div class='my_se_shop1'>购物车为空</div><a href=wap/myshop><button class='btn btn-info btn-default my_se_btn_last' type='submit'>去逛逛</button></a>");	
	}
}
//已评价 字体颜色改变
function fontColor(){
	$("#havePay .my_se_evaluate").map(function(){
		if($(this).text()=="已评价"){
			$(this).css({"background-color":"#999"});			
		}else if($(this).text()=="去评价"){
			$(this).click(function(){
				window.location.href="previews.html";
			})
			
		}
	})	
}
//底部
 function pageName(){ 	
 	var pageArr=["wap","wzyf","mydesign","fixturejsp","supervision?fl=13","mycheck","myshop"]
         var strUrl=location.href;
         var arrUrl=strUrl.split("/");
         var strPage=arrUrl[arrUrl.length-1];
         for(var i=0;i<pageArr.length;i++){         	
         	if(pageArr[i]==strPage){   
         	$(".pindex_nav ul li").eq(i).find($("a")).css({"border-bottom": "2px solid #1E76B8"})
         	}        
         }
         
     }
     
//**的
$(function(){
	$(".fixture_navul li").click(function(){$(this).addClass("green").siblings().removeClass("green");})
//评价
	$(".preview_ul li span i").click(function(){
		$(this).addClass("recolor").nextAll().removeClass("recolor");
		$(this).prevAll().addClass("recolor")
	});
	//上传图片预览
	$(".dk_hkb_pic input").change(function(){
		var f =$(this)[0].files[0];
		var uploadsrc = window.URL.createObjectURL(f);
		$(this).siblings("a").find("img")[0].src = uploadsrc;
	});
//	定位
	$(".p_index_addr").click(function(){
		$(".tankuang").show();
		$(".zhezhao").show()
	});
	//导航

})


function choose_addr(obj){
	$(obj).addClass("check_adddd").siblings().removeClass("check_adddd");
	var addr=$(obj).text();
	$(".p_index_addr span").text(addr);
	$(".tankuang").hide();
	$(".zhezhao").hide();
}
//弹出框

function tkopeng(obj,page){
	var s=0;

    $(obj).parents("form").find("input.yztj").each(function(){
//    	console.log("aaaaaaaaaa");
    	if($(this).val()==""||$(this).val()==null){
//    		console.log("xxx");
//    		console.log($(this).val());
    		s+=1;
    	}else{
    		console.log($(this).val());
//    		console.log("ggggg")
    	}
    	
    });
//    console.log(s+"     sssss")

		 if(s==0){
			 if(page=="1"){
					//预约一键入住
					$(".tankuang").html(" <div align='right' class='tankuang_close' onclick='closed(this)'><input  class='yftype' type='hidden'  value='1' />  <img src='images/wap/images/close.png'width='25'>"+
						"</div><p style='text-align:center;text-indent: 24px;'>恭喜您预约成功，我们会在24小时内为您受理，请注意接听电话。</p>"+
						"<div class='tangkuang_btn' align='center'onclick='tkclose(this)'>确定</div> ").show();
				}else if(page=="2"){
					//免费验房
					$(".tankuang").html(" <div align='right' class='tankuang_close' onclick='closed(this)'>  <input  class='yftype' type='hidden'  value='2' />   <img src='images/wap/images/close.png'width='25'>"+
						"</div><p style='text-align:center;text-indent: 24px;'>恭喜您预约成功，我们会在24小时内为您受理，请注意接听电话。</p>"+
						"<div class='tangkuang_btn' align='center'onclick='tkclose(this)'>确定</div> ").show();
				}else if(page=="3"){
					//金牌验房
					$(".tankuang").html(" <div align='right' class='tankuang_close' onclick='closed(this)'> <input  class='yftype' type='hidden'  value='3' />    <img src='images/wap/images/close.png'width='25'>"+
					"</div>恭喜您已预约成功，我们会在24小时内为您受理，请注意接听电话。<div class='tangkuang_btn' align='center'onclick='tkclose(this)'>确定</div> ").show();
				}else if(page=="4"){
					//预约工长弹框
					$(".tankuang").html(" <div align='right' class='tankuang_close' onclick='closed(this)'> <input  class='yftype' type='hidden'  value='4' />    <img src='images/wap/images/close.png'width='25'>"+
						"</div>恭喜您已预约成功，我们会在24小时内为您受理，请注意接听电话。<div class='tangkuang_btn' align='center'onclick='tkclose(this)'>确定</div> ").show();
				}else if(page=="7"){
					$(".tankuang").html(" <div align='right' class='tankuang_close' onclick='closed(this)'> <input  class='yftype' type='hidden'  value='7' />    <img src='images/wap/images/close.png'width='25'>"+
					"</div>恭喜您已预约成功，我们会在24小时内为您受理，请注意接听电话。<div class='tangkuang_btn' align='center'onclick='tkclose(this)'>确定</div> ").show();
					
				}else if(page=="555"){
					//一键反馈弹框
					$(".tankuang").html(" <div align='right' class='tankuang_close' onclick='closed(this)'><img src='images/wap/images/close.png'width='25'>"+
						"</div>提交成功，感谢您的反馈，赠送米奇币，请等待下一个版本中米奇币将在商城上线，届时可以购买。<div class='tangkuang_btn' align='center'onclick='tkclose(this)'>确定</div> ").show();
				}else if(page=="666"){
					$(".tankuang").html(" <div align='right'  class='tankuang_close' onclick='closed(this)'><img src='images/wap/images/close.png'width='25'></div>"+
						"<p style='text-align:center'>提交成功.</p><div class='tangkuang_btn' align='center'onclick='tkclose1(this)'><a href='javascript:;' onClick='javascript :history.back(-1);'>确定</a></div> ").show();
				}else if(page=="7"){
					$(".tankuang").html(" <div align='right' class='tankuang_close' onclick='closed(this)'>  <input  class='yftype' type='hidden'  value='7' /> <img src='images/wap/images/close.png'width='25'>"+
						"</div><p style='text-align:center;text-indent: 24px;'>恭喜您预约成功，我们会在24小时内为您受理，请注意接听电话。</p>"+
						"<div class='tangkuang_btn' align='center'onclick='tkclose(this)'>确定</div> ").show();
				}else if(page=="132"){
					$(".tankuang").html(" <div align='right' class='tankuang_close' onclick='closed(this)'><img src='../../images/wap/images/close.png'width='25'>"+
						"</div><div class='checkadd_choose'><dl><dt >已开通城市列表</dt><dd onclick='choose_addr(this)'>简阳</dd><dd onclick='choose_addr(this)'>成都</dd>"+
						"<dd onclick='choose_addr(this)'>德阳</dd><dd onclick='choose_addr(this)'>甘肃</dd></dl></div>");
				}
			   $(".zhezhao").show();
//			   submitform(obj);
			  
		  }else{
			  $(".tankuang").html(" <div align='right' class='tankuang_close' onclick='closed(this)'><img src='images/wap/images/close.png'width='25'>"+
				"</div>请输入完整信息。<div class='tangkuang_btn' align='center'onclick='tkclose(this)'>确定</div> ").show();
			  $(".zhezhao").show();
		  }    	 
	 }

//关闭弹出框
//这个是点击确定之后的关闭
function tkclose(obj){
	$(obj).parent().hide();
	$(".zhezhao").hide();
	//$("form input").val("");
	submitform(obj);
	
}


//提交表单
function submitform(obj){

	var yfele=$(obj).parents(".tankuang").find(".yftype");
	if(yfele!=null)
	{
		var yftype=yfele.val();
		console.log(yftype+"yftype")
		console.log($("#wzyfForm"+yftype)+"11111111111111111")
		$("#wzyfForm"+yftype).submit();
		console.log("3333")
	}
	$(obj).parents("form").find("input.yztj").each(function(){
    	console.log("aaaaaaaaaa");
    	
    });
}

//这个是地单击关闭按钮时候的关闭
function closed(obj){
	$(obj).parent().hide();
	$(".zhezhao").hide();
}
//个人中心背景效果
function mycav(){
	if (!window.ActiveXObject && !!document.createElement("canvas").getContext) {
		$.getScript("http://im-img.qq.com/pcqq/js/200/cav.js?_=1428576021379",
			function () {
				var t = {
					width: 1.5,
					height: 1.5,
					depth: 10,
					segments: 12,
					slices: 6,
					xRange: 0.8,
					yRange: 0.1,
					zRange: 1,
					ambient: "#525252",
					diffuse: "#FFFFFF",
					speed: 0.0002
				};
				var G = {
					count: 2,
					xyScalar: 1,
					zOffset: 100,
					ambient: "#002c4a",
					diffuse: "#005584",
					//ambient: "#10ADA2",
					//diffuse: "#17CEC4",
					speed: 0.001,
					gravity: 1200,
					dampening: 0.95,
					minLimit: 10,
					maxLimit: null,
					minDistance: 20,
					maxDistance: 400,
					autopilot: false,
					draw: false,
					bounds: CAV.Vector3.create(),
					step: CAV.Vector3.create(Math.randomInRange(0.2, 1), Math.randomInRange(0.2, 1), Math.randomInRange(0.2, 1))
				};
				var m = "canvas";
				var E = "svg";
				var x = {
					renderer: m
				};
				var i, n = Date.now();
				var L = CAV.Vector3.create();
				var k = CAV.Vector3.create();
				var z = document.getElementById("container");
				var w = document.getElementById("anitOut");
				//var z=document.getElementsByClassName("mycontainer");
				//var w = document.getElementsByClassName("anitOut");
				var D, I, h, q, y;
				var g;
				var r;

				function C() {
					F();
					p();
					s();
					B();
					v();
					K(z.offsetWidth, z.offsetHeight);
					o()
				}

				function F() {
					g = new CAV.CanvasRenderer();
					H(x.renderer)
				}

				function H(N) {
					if (D) {
						w.removeChild(D.element)
					}
					switch (N) {
						case m:
							D = g;
							break
					}
					D.setSize(z.offsetWidth, z.offsetHeight);
					w.appendChild(D.element)
				}

				function p() {
					I = new CAV.Scene()
				}

				function s() {
					I.remove(h);
					D.clear();
					q = new CAV.Plane(t.width * D.width, t.height * D.height, t.segments, t.slices);
					y = new CAV.Material(t.ambient, t.diffuse);
					h = new CAV.Mesh(q, y);
					I.add(h);
					var N, O;
					for (N = q.vertices.length - 1; N >= 0; N--) {
						O = q.vertices[N];
						O.anchor = CAV.Vector3.clone(O.position);
						O.step = CAV.Vector3.create(Math.randomInRange(0.2, 1), Math.randomInRange(0.2, 1), Math.randomInRange(0.2, 1));
						O.time = Math.randomInRange(0, Math.PIM2)
					}
				}

				function B() {
					var O, N;
					for (O = I.lights.length - 1; O >= 0; O--) {
						N = I.lights[O];
						I.remove(N)
					}
					D.clear();
					for (O = 0; O < G.count; O++) {
						N = new CAV.Light(G.ambient, G.diffuse);
						N.ambientHex = N.ambient.format();
						N.diffuseHex = N.diffuse.format();
						I.add(N);
						N.mass = Math.randomInRange(0.5, 1);
						N.velocity = CAV.Vector3.create();
						N.acceleration = CAV.Vector3.create();
						N.force = CAV.Vector3.create()
					}
				}

				function K(O, N) {
					D.setSize(O, N);
					CAV.Vector3.set(L, D.halfWidth, D.halfHeight);
					s();
				}

				function o() {
					i = Date.now() - n;
					u();
					M();
					requestAnimationFrame(o)
				}

				function u() {
					var Q, P, O, R, T, V, U, S = t.depth / 2;
					CAV.Vector3.copy(G.bounds, L);
					CAV.Vector3.multiplyScalar(G.bounds, G.xyScalar);
					CAV.Vector3.setZ(k, G.zOffset);
					for (R = I.lights.length - 1; R >= 0; R--) {
						T = I.lights[R];
						CAV.Vector3.setZ(T.position, G.zOffset);
						var N = Math.clamp(CAV.Vector3.distanceSquared(T.position, k), G.minDistance, G.maxDistance);
						var W = G.gravity * T.mass / N;
						CAV.Vector3.subtractVectors(T.force, k, T.position);
						CAV.Vector3.normalise(T.force);
						CAV.Vector3.multiplyScalar(T.force, W);
						CAV.Vector3.set(T.acceleration);
						CAV.Vector3.add(T.acceleration, T.force);
						CAV.Vector3.add(T.velocity, T.acceleration);
						CAV.Vector3.multiplyScalar(T.velocity, G.dampening);
						CAV.Vector3.limit(T.velocity, G.minLimit, G.maxLimit);
						CAV.Vector3.add(T.position, T.velocity)
					}
					for (V = q.vertices.length - 1; V >= 0; V--) {
						U = q.vertices[V];
						Q = Math.sin(U.time + U.step[0] * i * t.speed);
						P = Math.cos(U.time + U.step[1] * i * t.speed);
						O = Math.sin(U.time + U.step[2] * i * t.speed);
						CAV.Vector3.set(U.position, t.xRange * q.segmentWidth * Q, t.yRange * q.sliceHeight * P, t.zRange * S * O - S);
						CAV.Vector3.add(U.position, U.anchor)
					}
					q.dirty = true
				}

				function M() {
					D.render(I)
				}

				function J(O) {
					var Q, N, S = O;
					var P = function (T) {
						for (Q = 0, l = I.lights.length; Q < l; Q++) {
							N = I.lights[Q];
							N.ambient.set(T);
							N.ambientHex = N.ambient.format()
						}
					};
					var R = function (T) {
						for (Q = 0, l = I.lights.length; Q < l; Q++) {
							N = I.lights[Q];
							N.diffuse.set(T);
							N.diffuseHex = N.diffuse.format()
						}
					};
					return {
						set: function () {
							P(S[0]);
							R(S[1])
						}
					}
				}

				function v() {
					window.addEventListener("resize", j)
				}

				function A(N) {
					CAV.Vector3.set(k, N.x, D.height - N.y);
					CAV.Vector3.subtract(k, L)
				}

				function j(N) {
					K(z.offsetWidth, z.offsetHeight);
					M()
				}

				C();
			})
	} else {
		alert('调用cav.js失败');
	}
}


