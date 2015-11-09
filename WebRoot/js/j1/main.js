//导航
$(document).ready(function(){
	$(".close").click(function(){
        $(this).parents(".check_out").hide();
    });
    $("checkOkClose").click(function(){
        $(this).parents(".check_out").hide();
    })
	//input 样式改变
	if($('div.tab-box').length > 0){
		$('div.tab-box').find('input[type="text"]').on('focusin focusout',function(event){
			event = event || window.event;
			var old = $(this)[0].defaultValue;
			if(event.type == 'focusin'){
				$(this).css({borderColor:'#f25618'});
				if($(this).val() == old){
					$(this).val(' ');
				}
			}else{
				$(this).removeAttr('style');
				if($(this).val() == ' '){
					$(this).val(old);
				}
			}
		});
	}
	/*$(".laydate-icon").bind('click',laydate)*/
	//前端--星星 2015/7/27 13:50:49
$(".bmNum").keyup(function(){
		var regNum=/^[0-9]*$/;
		 var bmNumObj=$(this).val();
		console.log($(this).val()+"0");
		if(!regNum.test(bmNumObj)){
			$(this).val("");
			$(this).attr("placeholder","请输入数字")
		}
	});




	/*滚动效果*/
	$(".promot-box .slide").slide({ mainCell:"ul",vis:4,prevCell:".sPrev",nextCell:".sNext",effect:"leftLoop"});

	/*免费验房tab切换*/
	$(".user-left-main").slide({
		titCell:".tab-title a",
		mainCell:".tab-box",
		trigger:"click"
	});
	/*抢相因tab*/
	$('.promot-box').slide({
		titCell:".time-line ul li span",
		mainCell:".slide",
		trigger:"click"
	});
	/*找师傅*/
	$('.find-col').slide({
		titCell:".module-title .tab span",
		mainCell:".find-box",
		trigger:"click"
	});
	//导航
	$(".mycontainer .mynav li a").focus(function(){
		console.log("2222222222222")
		$(this).parents("li").find("a").removeClass("cur");

		$(this).siblings("ul").show();
	})
	$(".mynav li a").each(function(){
		if($($(this))[0].href==String(window.location)){
			//$(".mynav li.cur").removeClass("cur");
			$(this).parents("li").addClass("cur");
		}
	});
	$(".slideGroup .slideBox2").slide({
		mainCell:"ul",
		vis:4,
		prevCell:".sPrev",
		nextCell:".sNext",
		effect:"leftLoop",
		autoPlay:"true"


	});
	$(".slideGroup .slideBox1").slide({
		mainCell:"ul",
		vis:5,
		prevCell:".sPrev",
		nextCell:".sNext",
		effect:"leftLoop",
		autoPlay:"true"


	});

    $('input[type=text]').bind({
        focus:function(){$(this).css("outline","1px solid #3499DA")},
        blur:function(){$(this).css("outline","none")}
    })
    $(".check_news .check_newsbox img").bind({
    	focus:function(){console.log("32323")}
    })
    //我的个人中心
    $(".pjknowns").hover(function(){
    	
    	$("#mjkomesbox").show().Slide();
    })
    $(".myzonegift").click(function(){
      
	   console.log("11111111");
	   console.log($(this).attr("id"));
	   var myid=$(this).attr("id");
	   if(myid=="myzone_leftbil"){
	   	    $(".mibi").show();
	   	    $(".daijinquan").hide();
	   }else if(myid=="myzone_leftbir") {
	   	    $(".mibi").hide();
	   	    $(".daijinquan").show();
	   }
      $("#rightbox").css("display","none");
    });
    $("#mytabs1 li").click(function(){
    	$(".mibi").hide();
    	$(".daijinquan").hide();
    	$("#rightbox").css("display","block");
    })
    $(".leftsidebar_box dt").click(function(){
		$(this).parent().find('dd').removeClass("menu_chioce");
		$(".leftsidebar_box dt span").attr("class","glyphicon glyphicon-triangle-right");
		$(this).parent().find('span').attr("class","glyphicon glyphicon-triangle-bottom"); 
		$(this).parent().find('dd').toggle();
	});
	//查看我的购物车
	$("#sub1").bind("mouseenter mouseleave",function(){$("#sub11").toggleClass("enter");});
	$(".fixleft").bind("click",function(){$(this).hide()});
    $(".shoporder_newaddr").click(function(){
    	$("#id_add").val(0);
		$("#name").val("");
		$("#phone").val("");
		$("#addr").val("");
    	$(".zhezhao").show();
    	$(".shoporder_addrnewbox").show();
    });
    $(".closeshoporder").click(function(){
    	$(".zhezhao").hide();
    	$(".shoporder_addrnewbox").hide();
    })
    //点击已有地址效果
    /*$(".paufor_addr tr").click(function(){$(this).addClass("outline").siblings().removeClass("outline");})*/
    $(".paufor_addr  tbody").on("click","tr",function(){
    	$(this).addClass("outline").siblings().removeClass("outline");
    })
    //验房显示大图
	$(".fixright .fixbox:nth-child(1)").click(function(){
		$(this).parents(".fixright").hide();
	});


	//品牌商家
    linum = $('.mainlist li').length;//图片数量
	w = linum/2 * 200;//ul宽度
	$('.piclist').css('width', w + 'px');//ul宽度
	$('.swaplist').html($('.mainlist').html());//复制内容
	
	$('.og_next').click(function(){
		
		if($('.swaplist,.mainlist').is(':animated')){
			$('.swaplist,.mainlist').stop(true,true);
		}
		
		if($('.mainlist li').length>5){//多于4张图片
			ml = parseInt($('.mainlist').css('left'));//默认图片ul位置
			sl = parseInt($('.swaplist').css('left'));//交换图片ul位置
			if(ml<=0 && ml>w*-1){//默认图片显示时
				$('.swaplist').css({left: '1000px'});//交换图片放在显示区域右侧
				$('.mainlist').animate({left: ml - 1000 + 'px'},'slow');//默认图片滚动				
				if(ml==(w-1000)*-1){//默认图片最后一屏时
					$('.swaplist').animate({left: '0px'},'slow');//交换图片滚动
				}
			}else{//交换图片显示时
				$('.mainlist').css({left: '1000px'})//默认图片放在显示区域右
				$('.swaplist').animate({left: sl - 1000 + 'px'},'slow');//交换图片滚动
				if(sl==(w-1000)*-1){//交换图片最后一屏时
					$('.mainlist').animate({left: '0px'},'slow');//默认图片滚动
				}
			}
		}
	})
	$('.og_prev').click(function(){
		
		if($('.swaplist,.mainlist').is(':animated')){
			$('.swaplist,.mainlist').stop(true,true);
		}
		
		if($('.mainlist li').length>5){
			ml = parseInt($('.mainlist').css('left'));
			sl = parseInt($('.swaplist').css('left'));
			if(ml<=0 && ml>w*-1){
				$('.swaplist').css({left: w * -1 + 'px'});
				$('.mainlist').animate({left: ml + 1000 + 'px'},'slow');				
				if(ml==0){
					$('.swaplist').animate({left: (w - 1000) * -1 + 'px'},'slow');
				}
			}else{
				$('.mainlist').css({left: (w - 1000) * -1 + 'px'});
				$('.swaplist').animate({left: sl + 1000 + 'px'},'slow');
				if(sl==0){
					$('.mainlist').animate({left: '0px'},'slow');
				}
			}
		}
	});
	//酱菜案例
	    linum = $('.mainlist2 li').length;//图片数量
		w = linum/2 * 335;//ul宽度
		$('.piclist2').css('width', w + 'px');//ul宽度
		$('.swaplist2').html($('.mainlist2').html());//复制内容
	$('.al_next').click(function(){
		
		if($('.swaplist2,.mainlist2').is(':animated')){
			$('.swaplist2,.mainlist2').stop(true,true);
		}
		
		if($('.mainlist2 li').length>3){//多于4张图片
			ml = parseInt($('.mainlist2').css('left'));//默认图片ul位置
			sl = parseInt($('.swaplist2').css('left'));//交换图片ul位置
			if(ml<=0 && ml>w*-1){//默认图片显示时
				$('.swaplist2').css({left: '1000px'});//交换图片放在显示区域右侧
				$('.mainlist2').animate({left: ml - 1000 + 'px'},'slow');//默认图片滚动				
				if(ml==(w-1000)*-1){//默认图片最后一屏时
					$('.swaplist2').animate({left: '0px'},'slow');//交换图片滚动
				}
			}else{//交换图片显示时
				$('.mainlist2').css({left: '1000px'})//默认图片放在显示区域右
				$('.swaplist2').animate({left: sl - 1000 + 'px'},'slow');//交换图片滚动
				if(sl==(w-1000)*-1){//交换图片最后一屏时
					$('.mainlist2').animate({left: '0px'},'slow');//默认图片滚动
				}
			}
		}
	})
	$('.al_prev').click(function(){
		
		if($('.swaplist2,.mainlist2').is(':animated')){
			$('.swaplist2,.mainlist2').stop(true,true);
		}
		
		if($('.mainlist li').length>3){
			ml = parseInt($('.mainlist2').css('left'));
			sl = parseInt($('.swaplist2').css('left'));
			if(ml<=0 && ml>w*-1){
				$('.swaplist2').css({left: w * -1 + 'px'});
				$('.mainlist2').animate({left: ml + 1000 + 'px'},'slow');				
				if(ml==0){
					$('.swaplist2').animate({left: (w - 1000) * -1 + 'px'},'slow');
				}
			}else{
				$('.mainlist2').css({left: (w - 1000) * -1 + 'px'});
				$('.swaplist2').animate({left: sl + 1000 + 'px'},'slow');
				if(sl==0){
					$('.mainlist2').animate({left: '0px'},'slow');
				}
			}
		}
	});
	//导航效果
	$(".mynav li").hover(function(){
		$(this).find(".mynav_son").css("display",'block')
	},function(){
		$(this).find(".mynav_son").css("display",'none')
	});
	$(".mynav li").click(function(){
    //
		$(this).addClass("cur").siblings().removeClass("cur");
    //
	})
	//$(".mynav>li").click(function(){
		//$(this).parents(".mynav li").addClass("cur").siblings().removeClass("cur");
		//$(this).addClass("cur").siblings().removeClass("cur");

	//})

});

(function($){
	$.fn.Slide=function(options){
		var opts = $.extend({},$.fn.Slide.deflunt,options);
		var index=1;
		var targetLi = $("." + opts.claNav + " li", $(this));//鐩爣瀵硅薄
		var clickNext = $("." + opts.claNav + " .next", $(this));//鐐瑰嚮涓嬩竴涓寜閽�
		var clickPrev = $("." + opts.claNav + " .prev", $(this));//鐐瑰嚮涓婁竴涓寜閽�
		var ContentBox = $("." + opts.claCon , $(this));//婊氬姩鐨勫璞�
		var ContentBoxNum=ContentBox.children().size();//婊氬姩瀵硅薄鐨勫瓙鍏冪礌涓暟
		var slideH=ContentBox.children().first().height();//婊氬姩瀵硅薄鐨勫瓙鍏冪礌涓暟楂樺害锛岀浉褰撲簬婊氬姩鐨勯珮搴�
		var slideW=ContentBox.children().first().width();//婊氬姩瀵硅薄鐨勫瓙鍏冪礌瀹藉害锛岀浉褰撲簬婊氬姩鐨勫搴�
		var autoPlay;
		var slideWH;
		if(opts.effect=="scroolY"||opts.effect=="scroolTxt"){
			slideWH=slideH;
		}else if(opts.effect=="scroolX"||opts.effect=="scroolLoop"){
			ContentBox.css("width",ContentBoxNum*slideW);
			slideWH=slideW;
		}else if(opts.effect=="fade"){
			ContentBox.children().first().css("z-index","1");
		}

		return this.each(function() {
			var $this=$(this);
			//婊氬姩鍑芥暟
			var doPlay=function(){
				$.fn.Slide.effect[opts.effect](ContentBox, targetLi, index, slideWH, opts);
				index++;
				if (index*opts.steps >= ContentBoxNum) {
					index = 0;
				}
			};
			clickNext.click(function(event){
				$.fn.Slide.effectLoop.scroolLeft(ContentBox, targetLi, index, slideWH, opts,function(){
					for(var i=0;i<opts.steps;i++){
						ContentBox.find("li:first",$this).appendTo(ContentBox);
					}
					ContentBox.css({"left":"0"});
				});
				event.preventDefault();
			});
			clickPrev.click(function(event){
				for(var i=0;i<opts.steps;i++){
					ContentBox.find("li:last").prependTo(ContentBox);
				}
				ContentBox.css({"left":-index*opts.steps*slideW});
				$.fn.Slide.effectLoop.scroolRight(ContentBox, targetLi, index, slideWH, opts);
				event.preventDefault();
			});
			//鑷姩鎾斁
			if (opts.autoPlay) {
				autoPlay = setInterval(doPlay, opts.timer);
				ContentBox.hover(function(){
					if(autoPlay){
						clearInterval(autoPlay);
					}
				},function(){
					if(autoPlay){
						clearInterval(autoPlay);
					}
					autoPlay=setInterval(doPlay, opts.timer);
				});
			}

			//鐩爣浜嬩欢
			targetLi.hover(function(){
				if(autoPlay){
					clearInterval(autoPlay);
				}
				index=targetLi.index(this);
				window.setTimeout(function(){$.fn.Slide.effect[opts.effect](ContentBox, targetLi, index, slideWH, opts);},200);

			},function(){
				if(autoPlay){
					clearInterval(autoPlay);
				}
				autoPlay = setInterval(doPlay, opts.timer);
			});
		});
	};
	$.fn.Slide.deflunt={
		effect : "scroolY",
		autoPlay:true,
		speed : "normal",
		timer : 1000,
		defIndex : 0,
		claNav:"JQ-slide-nav",
		claCon:"JQ-slide-content",
		steps:1
	};
	$.fn.Slide.effectLoop={
		scroolLeft:function(contentObj,navObj,i,slideW,opts,callback){
			contentObj.animate({"left":-i*opts.steps*slideW},opts.speed,callback);
			if (navObj) {
				navObj.eq(i).addClass("on").siblings().removeClass("on");
			}
		},

		scroolRight:function(contentObj,navObj,i,slideW,opts,callback){
			contentObj.stop().animate({"left":0},opts.speed,callback);

		}
	}
	$.fn.Slide.effect={
		fade:function(contentObj,navObj,i,slideW,opts){
			contentObj.children().eq(i).stop().animate({opacity:1},opts.speed).css({"z-index": "1"}).siblings().animate({opacity: 0},opts.speed).css({"z-index":"0"});
			navObj.eq(i).addClass("on").siblings().removeClass("on");
		},
		scroolTxt:function(contentObj,undefined,i,slideH,opts){
			//alert(i*opts.steps*slideH);
			contentObj.animate({"margin-top":-opts.steps*slideH},opts.speed,function(){
				for( var j=0;j<opts.steps;j++){
					contentObj.find("li:first").appendTo(contentObj);
				}
				contentObj.css({"margin-top":"0"});
			});
		},
		scroolX:function(contentObj,navObj,i,slideW,opts,callback){
			contentObj.stop().animate({"left":-i*opts.steps*slideW},opts.speed,callback);
			if (navObj) {
				navObj.eq(i).addClass("on").siblings().removeClass("on");
			}
		},
		scroolY:function(contentObj,navObj,i,slideH,opts){
			contentObj.stop().animate({"top":-i*opts.steps*slideH},opts.speed);
			if (navObj) {
				navObj.eq(i).addClass("on").siblings().removeClass("on");
			}
		}
	};
})(jQuery);

//搜索商品
function searchGoods(obj){

	console.log($(obj).prev("input").val());//获取输入框里面的值
	location.href="shoplist.html";

}


function closebmbox(){
//	console.log("323")
	$(".check_bmbox").hide();
	
}
function loginnow(){
//	console.log("3232")
	$("#zhifu").show();
	$(".zhezhao").show();
	$(".check_bmbox").hide();
}
function checkzhifu(){
	$("#checkok").show();
		location.href="payfor.html";
}
function checkok(){
	$(".check_out").hide();
	//location.href="index.html";
}
/*function checkin(){
	alert("进来了！");
	$(".zhezhao").show();
	$(".check_out").hide();
}*/
function myclose(obj){
	console.log("222222222222222")
	$(obj).parents(".check_out").hide();
}

function dktj(){
	$("#dktj").show();
}
//免费监理弹框
function mfjl(){
	$("#mfjl").show();
}
function closemy(obj){
	$(this).parent(".fixleft").css("display","none");
}
//-------------------------------新增加特效
//找工长页面选择
$(".fixture_tx ul li").click(function(){
	console.log("232");
//	$(this).addClass("active").siblings().removeClass("active")
})
$(".dsignboxin ul li").click(function(){
	console.log("ewew")
	$(this).addClass("checkli").siblings().removeClass(".checkli")
})
$(".table_num span:nth-child(1)").click(function(){
	console.log("1111111111")
})


$(function(){
//	购物车chexkox改变总价

//---评价时候的星星
	$(".mypjstar span").click(function(){
		console.log("eeeeeeeeeee")
		$(this).addClass("recolor").nextAll().removeClass("recolor");
		$(this).prevAll().addClass("recolor")
	})
    $(".dsignboxin ul li").click(function(){
		$(this).addClass("checkli").siblings().removeClass("checkli");
	})
	//上传图片
	$(".pic_unload input").change(function(){
		var f =$(this)[0].files[0];
		var uploadsrc = window.URL.createObjectURL(f);
		$(this).siblings("a").find("img")[0].src = uploadsrc;
	});

         //首页推荐工长动态添加宽度
	      gzcount=$(".wrap .JQ-slide-content li").length;
	      //console.log(gzcount)
          gzwidth=gzcount*200;//包含工长的UL的宽度
	      $(".wrap .JQ-slide-content li").css('width'+gzwidth+'px');

         //免费验房和金牌验房底部轮播，
	      freenum=$("#checkfree_lunbo  li").length;
	      w=freenum*220//加载后ul的宽度
         //$("#checkfree_lunbo ").css('width',w+'px');//ul宽度


// 注册时手机号码验证
	$("input[type=tel]").blur(function(){
		var tel=/^0?(13|14|15|18)[0-9]{9}$/;
		console.log(tel+"telteltel");
		var inNum=$(this).val();
		console.log($(this).val());
		if(!tel.test(inNum)){
			$(this).val("");
			$(this).attr("placeholder","请输入有效电话号码")
			//$(this).focus()
		}else{
			//向后台发送此号码是否已被注册
			$.ajax({

			})
		}

	});
	$(".signpass").blur(function(){
		console.log("222222222222")
		var passCh=/^[a-zA-Z0-9_-]{6,16}$/;
		var passNum=$(this).val()
		if(!passCh.test(passNum)){
			$(this).val("");
			$(this).attr("placeholder","请输入6到16位字母或数字");
			//$(this).focus();
		}
	});

	$(".signpass2").blur(function(){
		var pass1=$(".signpass").val();
		$(this).val();
		if($(this).val()!=pass1){
			$(this).val("");
			$(this).attr("placeholder","输入的密码和上次不一样")
			//$(this).focus();
		}else{
			console.log($("#signname").val())
			if($("#signname").val()!=""){
				$("#btnSendCode").removeAttr("disabled");//启用按钮
			}

		}
	})

});
// sign注册发送验证码时间的函数
var InterValObj; //timer变量，控制时间
var count = 60; //间隔函数，1秒执行
var curCount;//当前剩余秒数

function sendMessage() {
	        //$("")
			curCount = count;
			//设置button效果，开始计时
			$("#btnSendCode").attr("disabled", "true");
			$("#btnSendCode").val("请在" + curCount + "秒内输入");
			InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
			//向后台发送处理数据
			$.ajax({
				type: "POST", //用POST方式传输
				dataType: "text", //数据格式:JSON
				//url: 'Login.ashx', //目标地址
				//data: "dealType=" + dealType +"&uid=" + uid + "&code=" + code,
				error: function (XMLHttpRequest, textStatus, errorThrown) { },
				success: function (msg){

				}
			});
}

//timer处理函数
function SetRemainTime() {
	if (curCount == 0) {
		window.clearInterval(InterValObj);//停止计时器
		$("#btnSendCode").removeAttr("disabled");//启用按钮
		$("#btnSendCode").val("重新发送");
	}
	else {
		curCount--;
		$("#btnSendCode").val("请在" + curCount + "秒内输入");
	}
}
//购物车
function mySession(){
	if (!document.getElementsByClassName) {
		document.getElementsByClassName = function (cls) {
			var ret = [];
			var els = document.getElementsByTagName('*');
			for (var i = 0, len = els.length; i < len; i++) {

				if (els[i].className.indexOf(cls + ' ') >=0 || els[i].className.indexOf(' ' + cls + ' ') >=0 || els[i].className.indexOf(' ' + cls) >=0) {
					ret.push(els[i]);
				}
			}
			return ret;
		}
	}

	var table = document.getElementById('cartTable'); // 购物车表格
	var selectInputs = document.getElementsByClassName('check'); // 所有勾选框
	var checkAllInputs = document.getElementsByClassName('check-all') // 全选框
	var tr = table.children[1].rows; //行
	var selectedTotal = document.getElementById('selectedTotal'); //已选商品数目容器
	var priceTotal = document.getElementById('priceTotal'); //总计
	var deleteAll = document.getElementById('deleteAll'); // 删除全部按钮
	var selectedViewList = document.getElementById('selectedViewList'); //浮层已选商品列表容器
	var selected = document.getElementById('selected'); //已选商品
	var session_foot = document.getElementById('session_foot');

	// 更新总数和总价格，已选浮层
	function getTotal() {
		var seleted = 0;
		var price = 0;
		var HTMLstr = '';
		for (var i = 0, len = tr.length; i < len; i++) {
			if (tr[i].getElementsByTagName('input')[0].checked) {
				tr[i].className = 'on';
				seleted += parseInt(tr[i].getElementsByTagName('input')[1].value);
				price += parseFloat(tr[i].cells[4].innerHTML);
				HTMLstr += '<div><img src="' + tr[i].getElementsByTagName('img')[0].src + '"><span onclick="CancelProduct(\''+tr[i].getElementsByTagName('img')[0].id +'\',\''+tr[i].id+'\')" class="del" index="' + i + '">取消选择</span></div>'
			}
			else {
				tr[i].className = '';
			}
		}
		selectedTotal.innerHTML = seleted;
		priceTotal.innerHTML = price.toFixed(2);
		selectedViewList.innerHTML = HTMLstr;

		if (seleted == 0) {
			session_foot.className = 'session_foot';
		}
	}
	// 计算单行价格
	function getSubtotal(tr) {
		var cells = tr.cells;
		var price = cells[2]; //单价
		var subtotal = cells[4]; //小计td
		var countInput = tr.getElementsByTagName('input')[1]; //数目input
		var span = tr.getElementsByTagName('span')[1]; //-号
		//写入HTML
		subtotal.innerHTML = (parseInt(countInput.value) * parseFloat(price.innerHTML)).toFixed(2);
		//如果数目只有一个，把-号去掉
		if (countInput.value == 1) {
			span.innerHTML = '';
		}else{
			span.innerHTML = '-';
		}
		//当前商品id
		var pid = tr.getElementsByTagName('input')[0].value;
		 $.ajax({
				type : 'POST',
				url : 'index/UpdateProductNumForBuyCart',
				data : "pid=" + pid+"&num="+countInput.value,
				dataType : 'text',
				success : function(msg) {
				},
				error : function(msg) {
				}
			});
	}

	// 点击选择框
	for(var i = 0; i < selectInputs.length; i++ ){
		selectInputs[i].onclick = function () {
			if (this.className.indexOf('check-all') >= 0) { //如果是全选，则吧所有的选择框选中
				for (var j = 0; j < selectInputs.length; j++) {
					selectInputs[j].checked = this.checked;
				}
			}
			if (!this.checked) { //只要有一个未勾选，则取消全选框的选中状态
				for (var i = 0; i < checkAllInputs.length; i++) {
					checkAllInputs[i].checked = false;
				}
			}
			getTotal();//选完更新总计
		}
	}

	// 显示已选商品弹层
	selected.onclick = function () {
		if (selectedTotal.innerHTML != 0) {
			session_foot.className = (session_foot.className == 'session_foot' ? 'session_foot show' : 'session_foot');
		}
	}

	//已选商品弹层中的取消选择按钮
	selectedViewList.onclick = function (e) {
		var e = e || window.event;
		var el = e.srcElement;
		if (el.className=='del') {
			var input =  tr[el.getAttribute('index')].getElementsByTagName('input')[0]
			input.checked = false;
			input.onclick();
		}
	}

	//为每行元素添加事件
	for (var i = 0; i < tr.length; i++) {
		//将点击事件绑定到tr元素
		tr[i].onclick = function (e) {
			var e = e || window.event;
			var el = e.target || e.srcElement; //通过事件对象的target属性获取触发元素
			var cls = el.className; //触发元素的class
			var countInout = this.getElementsByTagName('input')[1]; // 数目input
			var value = parseInt(countInout.value); //数目
			//通过判断触发元素的class确定用户点击了哪个元素
			switch (cls) {
				case 'add': //点击了加号
					countInout.value = value + 1;
					getSubtotal(this);
					break;
				case 'reduce': //点击了减号
					if (value > 1) {
						countInout.value = value - 1;
						getSubtotal(this);
					}
					break;
				
					
			}
			getTotal();
		}
		// 给数目输入框绑定keyup事件
		tr[i].getElementsByTagName('input')[1].onkeyup = function () {
			var val = parseInt(this.value);
			if (isNaN(val) || val <= 0) {
				val = 1;
			}
			if (this.value != val) {
				this.value = val;
			}
			getSubtotal(this.parentNode.parentNode); //更新小计
			getTotal(); //更新总数
		}
	}
	// 点击全部删除
//	deleteAll.onclick = function () {
//		if (selectedTotal.innerHTML != 0) {
//			var con = confirm('确定删除所选商品吗？'); //弹出确认框
//			if (con) {
//				for (var i = 0; i < tr.length; i++) {
//					// 如果被选中，就删除相应的行
//					if (tr[i].getElementsByTagName('input')[0].checked) {
//						tr[i].parentNode.removeChild(tr[i]); // 删除相应节点
//						i--; //回退下标位置
//					}
//				}
//			}
//		} else {
//			alert('请选择商品！');
//		}
//		getTotal(); //更新总数
//	}
	// 默认全选
	checkAllInputs[0].checked = true;
	checkAllInputs[0].onclick();

}



function lxfEndtime(){

    $(".lxftime").each(function(){
        mathTime($(this));
    });
};
function formateTime(time){
    var timeRefer=[86700,3600,60,1];
    var ret=[];
    var len=timeRefer.length;
    var sec=Math.round(time/1000);

    for(var i=0;i<len;i++)
    {
        ret[i]=Math.floor(sec/timeRefer[i]);

        sec-=ret[i]*timeRefer[i];

    }

    return ret;
}

function mathTime(obj){

    var type=$(obj).attr('type');
    var endTime=$(obj).attr('endtime');
    var beginTime=$(obj).attr('begintime');
    var nowTime=$(obj).attr('nowtime');


    $(obj).attr('nowtime',parseInt(nowTime)+1000);

    if(nowTime<beginTime)
    {
        var time=formateTime(beginTime-nowTime);
         if(type=="type1"){
             $(obj).html("<span class='cheap_lttitle'>开抢倒计时</span>"+
                     "<span class='cheap_lasttime cheap_bgredcolor'>"+time[0]+"</span>:"+
                     "<span class='cheap_lasttime cheap_bgredcolor'>"+time[1]+"</span>:"+
                     "<span class='cheap_lasttime cheap_bgredcolor'>"+time[2]+"</span>:"+
                     "<span class='cheap_lasttime cheap_bgredcolor'>"+time[3]+"</span>")
             $(obj).siblings().find("a").attr("href","javascript:void(0)")
             $(obj).siblings().find("a .cheap_br_check").removeClass("cheap_check_red").addClass("cheap_check_gary").html("即将开抢")

         }else if(type=="type2"){
             $(obj).html("开抢倒计时"+time[0]+":"+time[1]+":"+time[2]+":"+time[3]);
             $(obj).siblings("div").find("a").attr("href","javascript:void(0)")
         }

    }else if(nowTime>=beginTime && nowTime<endTime)
    {
        var time=formateTime(endTime-nowTime);
        if(type=="type1"){
            $(obj).html("<span class='cheap_lttitle'>距离本场结束</span>"+
                    "<span class='cheap_lasttime cheap_bgredcolor'>"+time[0]+"</span>:"+
                    "<span class='cheap_lasttime cheap_bgredcolor'>"+time[1]+"</span>:"+
                    "<span class='cheap_lasttime cheap_bgredcolor'>"+time[2]+"</span>:"+
                    "<span class='cheap_lasttime cheap_bgredcolor'>"+time[3]+"</span>")
            $(obj).siblings().find("a").attr("href","index/shopmess?id="+$(obj).attr("shopid"))
        }else if(type=="type2"){
            $(obj).html("距离本场结束"+time[0]+":"+time[1]+":"+time[2]+":"+time[3]);
            $(obj).siblings("div").find("a").attr("href","index/shopmess?id="+$(obj).attr("shopid"))

        }

    }else if(nowTime>=endTime)
    {
//        $(obj).html('活动结束');
        if(type=="type1"){
            $(obj).html("<span class='cheap_lttitle'>已结束</span>"+
                    "<span class='cheap_lasttime cheap_bggarycolor'>00</span>&nbsp;:"+
                    "<span class='cheap_lasttime cheap_bggarycolor'>00</span>&nbsp;:"+
                    "<span class='cheap_lasttime cheap_bggarycolor'>00</span>")//如果结束日期小于当前日期就提示过期啦
            $(obj).siblings().find("a").attr("href","javascript:void(0)")
            $(obj).siblings().find("a .cheap_br_check").removeClass("cheap_check_red").addClass("cheap_check_gary").html("已结束")
        }else if(type=="type2"){
            $(obj).html("已结束： 00:00:00");
            $(obj).siblings("div").find("a").attr("href","javascript:void(0)")
        }
    }

}
