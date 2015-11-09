function city_change() {
	if ($("#city").val() != "") {
		$.ajax({
			type : 'POST',
			url : 'index/GetChildAreaByPid',
			data : "pid=" + $.trim($("#city").val()),
			dataType : 'text',
			success : function(msg1) {
				var msg = (new Function("return " + msg1))();
				if (msg.success) {
					$("#district option").remove();
					var district = "";
					for ( var i = -1; i < msg.result[0].length; i++) {
						if(i==-1){
							district += "<option value='" + msg.result[0][0].d_code
							+ "'>" + "--请选择--" + "</option>";
						}else{
							district += "<option value='" + msg.result[0][i].d_code
							+ "'>" + msg.result[0][i].d_name + "</option>";
						}
					}
					;
					$('#district').html(district);
				} else {
					alert("获取县的数据错误");
				}
			},
			error : function(msg) {
				alert("	网络传输错误，请检查您的网络连接");
			}
		});
	} else {
		$("#district option").remove();
	}
};
$(document).ready(
		function() {
			$("#submitNewAdd").click(
					function() {
						var id = $("#id_add").val();
						var name = $("#name").val();
						var phone = $("#phone").val();
						var sheng = $("#province").find("option:selected")
								.text();
						var shi = $("#city").find("option:selected").text();
						var xian = $("#district").find("option:selected")
								.text();
						var addr = $("#addr").val();
						if (name == "" || phone == "" || sheng == ""
								|| shi == "" || xian == "" || addr == ""
								|| xian == "--请选择--" || shi == "--请选择--"||sheng == "--请选择--"
								|| name == null || phone == null
								|| sheng == null || shi == null || xian == null
								|| addr == null) {
							alert("数据不完整");
						} else {
							Change(id, phone, name, sheng, shi, xian, addr);
						}
					});

			$.ajax({
				type : 'POST',
				url : 'index/GetArea',
				dataType : 'text',
				success : function(msg1) {
					var msg = (new Function("return " + msg1))();
					$("#province option").remove();
					var province = "";
					for ( var i = -1; i < msg.length; i++) {
						if(i == -1){
							province += "<option value='" + msg[0].d_code + "'>"
							+ "--请选择--" + "</option>";
						}else{
							province += "<option value='" + msg[i].d_code + "'>"
							+ msg[i].d_name + "</option>";
						}
					}
					$('#province').html(province);
				},
				error : function(msg) {
					alert("	网络传输错误，请检查您的网络连接");
				}
			});
		});

function Edit(id, phone, name, sheng, shi, xian, addr) {
	$("#id_add").val(id);
	$("#name").val(name);
	$("#phone").val(phone);
	$("#addr").val(addr);
	$(".zhezhao").show();
	$(".shoporder_addrnewbox").show();
	
}
function Change(id, phone, name, sheng, shi, xian, addr) {
	$.ajax({
		type : 'POST',
		url : 'index/ChangeAddress',
		data : "id=" + id + "&phone=" + phone + "&name=" + name + "&sheng="
				+ sheng + "&shi=" + shi + "&xian=" + xian + "&addr=" + addr,
		dataType : 'text',
		success : function(msg) {
			if (msg != "0") {
				if (msg == "-1") {
					$("#adrss_" + id + "_name").html(name);
					$("#adrss_" + id + "_ads").html(
							sheng + '-' + shi + '-' + xian + '-' + addr);
					$("#adrss_" + id + "_phone").html(phone);
					$("#adrss_" + id + "_edit").html(
							'<a onclick="Edit(\'' + id + '\',\'' + phone
									+ '\',\'' + name + '\',\'' + sheng
									+ '\',\'' + shi + '\',\'' + xian + '\',\''
									+ addr + '\')">修改</a>');

				} else {
					var html = '<tr id="'+msg+'">' + '<td id="adrss_' + msg + '_name">'
							+ name + '</td>' + '<td id="adress_' + msg
							+ '_ads">' + sheng + '-' + shi + '-' + xian + '-'
							+ addr + '</td>' + '<td id="adress_' + msg
							+ '_phone">' + phone + '</td>' + '<td  id="adress_'
							+ msg + '_edit"><a onclick="Edit(\'' + msg
							+ '\',\'' + phone + '\',\'' + name + '\',\''
							+ sheng + '\',\'' + shi + '\',\'' + xian + '\',\''
							+ addr + '\')">修改</a></td>' + '</tr>';
					$("#userAddr").append(html);
					 $(".paufor_addr tr").click(function(){$(this).addClass("outline").siblings().removeClass("outline");})
				}
				$(".zhezhao").hide();
				$(".shoporder_addrnewbox").hide();

			} else {
				alert("获取数据失败");
			}
		},
		error : function(msg) {
			alert("	网络传输错误，请检查您的网络连接");
		}
	});
}
function payfor(orderID) {
	var remark = $("#remark").val();
	var addrID = $("#userAddr tr.outline").attr("id");
	if (!addrID) {
		alert("请选择送货地址");
	} else {
		location.href = "index/payfor?orderID=" + orderID + "&addrID=" + addrID
				+ "&remark=" + remark;
	}
}
function province_change() {
	if ($("#province").val() != "") {
		$.ajax({
			type : 'POST',
			url : 'index/GetChildAreaByPid',
			data : "pid=" + $.trim($("#province").val()),
			dataType : 'text',
			success : function(msg1) {
				var msg = (new Function("return " + msg1))();
				if (msg.success) {
					$("#city option").remove();
					var city = "";
					for ( var i = -1; i < msg.result[0].length; i++) {
						if(i==-1){
							city += "<option value='" + msg.result[0][0].d_code
							+ "'>" + "--请选择--" + "</option>";
						}else{
							city += "<option value='" + msg.result[0][i].d_code
							+ "'>" + msg.result[0][i].d_name + "</option>";
						}
					}
					$('#city').html(city);
				} else {
					alert("获取市的数据错误");
				}
			},
			error : function(msg) {
				alert("	网络传输错误，请检查您的网络连接");
			}
		});
	} else {
		$("#city option").remove();
	}
};
