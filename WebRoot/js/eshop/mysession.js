$(function() {
	mySession();
});
function shop_delete(pid) {
	var conf = confirm('确定删除此商品吗？');
	if (conf) {
		$.ajax({
			type : 'POST',
			url : 'index/DeleteCart',
			data : "pid=" + pid,
			dataType : 'text',
			success : function(msg1) {
				if ("true" == msg1) {
					$('#tr_' + pid).remove();
					mySession().getTotal();
				} else {
					alert("删除失败");
				}
			},
			error : function(msg) {
				alert("	网络传输错误，请检查您的网络连接");
			}
		});
	}
}
//生成订单
function createOrder(){
	if ($('tr').hasClass('on')) {
		var pids = "";
		var nums = "";
		$("tr[class='on']").find("input[name='id_pro']").each(function() {
			pids += "'" + $(this).val() + "',";
		});
		$.ajax({
			type : 'POST',
			url : 'index/CreateOrder',
			data : "pids=" + pids,
			dataType : 'text',
			success : function(msg) {
				location.href=msg;
			},
			error : function(msg) {
				alert("	网络传输错误，请检查您的网络连接");
			}
		});
	} else {
		alert("请先选择物品");
	}
}

function CancelProduct(productId,buycartId){
	$.ajax({
		type : 'POST',
		url : 'index/DeleteProductFromBuyCart',
		data : "productId=" + productId,
		dataType : 'text',
		success : function(msg) {
			if ("true" == msg) {
				$('#'+buycartId).remove();
			} else {
				alert(msg);
			}
		},
		error : function(msg) {
			alert("	购物车商品删除失败");
		}
	});
}