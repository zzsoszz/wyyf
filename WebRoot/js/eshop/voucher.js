var priceUp=false;//优惠金额排序
var expireTimeUp =false;//到期时间排序
var effectTimeUp=false;//生效时间排序
var page=1;
var pageSize=20;
$(function(){
	//alert('页面加载完毕');
	//loadActBuy(priceUp,expireTimeUp,effectTimeUp,page,pageSize);
})
function loadActBuy(priceUp,expireTimeUp,effectTimeUp,page,pageSize){
	$.ajax({
        url: "/wyyf/index/GetChildAreaByPid",
        type: "get",
        success: function(data) {
            alert(data);
        },
        cache: false,
        timeout: 5000,
        error: function() {
            alert("超时");
        }
    });
	
}