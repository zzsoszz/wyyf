//导航
$(document).ready(function(){
    var num;
    $(".mycontainer .mynav li a").focus(function(){
		console.log("2222222222222")
		$(this).parents("li").find("a").removeClass("cur");

		$(this).siblings("ul").show();
	})
	$(".mynav li a").each(function(){
		if($($(this))[0].href==String(window.location)){
//			$(".mynav li.cur").removeClass("cur");
			
			$(this).parents("li").addClass("cur");
		}
	});
    $('input[type=text]').bind({
        focus:function(){$(this).css("outline","1px solid red")},
        blur:function(){$(this).css("outline","none")}
    })
    $(".check_news .check_newsbox img").bind({
    	focus:function(){console.log("32323")}
    })
    $(".pjknowns").hover(function(){
    	
    	$("#mjkomesbox").show().Slide();
    })
    $(".myzonegift").click(function(){
      
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
    $("#mytabs li").click(function(){
    	$(".mibi").hide();
    	$(".daijinquan").hide();
    	$("#rightbox").css("display","block");
    })
    $(".leftsidebar_box dt").click(function(){
		$(this).parent().find('dd').removeClass("menu_chioce");
		$(".leftsidebar_box dt span").attr("class","glyphicon glyphicon-triangle-right");
		$(this).parent().find('span').attr("class","glyphicon glyphicon-triangle-bottom");
//		$(".menu_chioce").slideUp(); 
		$(this).parent().find('dd').toggle();
//		$(this).parent().find('dd').addClass("menu_chioce");
	});
	//查看我的购物车
	$("#sub1").bind("mouseenter mouseleave",function(){$("#sub11").toggleClass("enter");});
	$(".fixleft").bind("click",function(){$(this).hide()});
    $(".shoporder_newaddr").click(function(){
    	console.log("2121");
    	$(".zhezhao").show();
    	$(".shoporder_addrnewbox").show();
    });
    $(".closeshoporder").click(function(){
    	$(".zhezhao").hide();
    	$(".shoporder_addrnewbox").hide();
    })
    //点击已有地址效果
    $(".paufor_addr tr").click(function(){$(this).addClass("outline").siblings().removeClass("outline");})
    //验房显示大图
    $(".check_pic a img").bind({
    	mouseenter:function(){
//  		console.log($(this)[0].attr(width)+"2222222222")
    		$(".check_showpic").show();
    		var picsrc=$(this).attr("src");
    		$(".check_showpic img").attr("src",picsrc);
    	},
        mouseleave:function(){$(".check_showpic").hide();}
       });
});

(function($){
	$.fn.Slide=function(options){
		var opts = $.extend({},$.fn.Slide.deflunt,options);
		var index=1;
		var targetLi = $("." + opts.claNav + " li", $(this));
		var clickNext = $("." + opts.claNav + " .next", $(this));
		var clickPrev = $("." + opts.claNav + " .prev", $(this));
		var ContentBox = $("." + opts.claCon , $(this));
		var ContentBoxNum=ContentBox.children().size();
		var slideH=ContentBox.children().first().height();
		var slideW=ContentBox.children().first().width();
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
			//
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
			//
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

function scroll(){
      var y =window.scrollY;
}
//向上
	var toup1;
    function toup(){
     toup1 = window.setInterval(scrup,4);
     }
    function scrup(){
     if(window.scrollY<=0){
         clearInterval(toup1);
     }else{
         scrollTo(0,window.scrollY-5);
     }
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
	location.href="index.html";
}

function myclose(){
//		console.log("323")
		$(".zhezhao").hide();
	$(".check_out").hide();
}
function gzcheck(){
	$("#gzcheck").show();
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
//sign注册发送验证码时间的函数
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
			
}

//timer处理函数
function SetRemainTime() {
	if (curCount == 0) {
		window.clearInterval(InterValObj);//停止计时器
		$("#btnSendCode").removeAttr("disabled");//启用按钮
		$("#btnSendCode").val("重新发送");
		$("#endBott").removeAttr("disabled");//启用按钮
		$("#endBott").val("重新发送");
	}
	else {
		curCount--;
		$("#btnSendCode").val("请在" + curCount + "秒内输入");
		$("#endBott").val("请在" + curCount + "秒内输入");
	}
}
