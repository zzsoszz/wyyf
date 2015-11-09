function pay(){
	var type=$("input[name='way']:checked").val();
	var orderId=$("#orderId").val();
	$.ajax({
		type : 'GET',
		url : 'index/PayMoney?type='+type+'&orderId='+orderId,
		dataType : 'text',
		success : function(charge) {
			if(charge=="1"){
				alert("该订单已支付，请不要重复支付");
				return;
			}
			pingpp.createPayment(charge, function(result, error){
				//alert(result+"-"+error.extra+"-"+error.msg);
				console.log(result+" "+error.extra+" "+error.msg);
			    if (result == "success") {
			        // 只有微信公众账号 wx_pub 支付成功的结果会在这里返回，其他的 wap 支付结果都是在 extra 中对应的 URL 跳转。
			    } else if (result == "fail") {
			        // charge 不正确或者微信公众账号支付失败时会在此处返回
			    } else if (result == "cancel") {
			        // 微信公众账号支付取消支付
			    }
			});
		},
		error : function(msg) {
			alert("	网络传输错误，请检查您的网络连接");
		}
	});
	
	
}