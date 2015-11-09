//$(function(){
//	$(".nav_one").bind({
//	 click:function(){
//	 	function(){
//		console.log($(this).attr("id"))
//	  $(this).addClass("border").siblings().removeClass("border");
//	  var objID=$(this).attr("id");
//	  if(objID="checkfree"){
//	  	$("#yangfang_free").show();
//	  	$("#yanfang_gold").hide();
//	  }else if(objID=="checkgold"){
//	  	$("#yangfang_free").hide();
//	  	$("#yanfang_gold").show();
//	  }
//	})
//})
$(function(){
	$(".nav_one").bind({
		click:function(){
			console.log($(this).attr("id"))
	      $(this).addClass("border").siblings().removeClass("border");
		  var objID=$(this).attr("id");
		  if(objID="checkfree"){
		  	$("#yangfang_free").show();
		  	$("#yangfang_gold").hide();
		  }else if(objID=="checkgold"){
		  	$("#yangfang_free").hide();
		  	$("#yangfang_gold").show();
		  }
		}
	})
	
	
})
