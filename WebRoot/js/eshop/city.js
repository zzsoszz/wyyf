$(function(){
	$.ajax({
        url: "index/GetArea",
        type: "get",
        success: function(data) {
        	var html='';
        	var obj=eval(data);
        	for(var i=0;i<obj.length;i++){
        		html+='<dt>'+obj[i].d_name+'<dt>';
        		html+='<dd>';
        		for(var j=0;j<obj[i].childCityList.length;j++){
        			html+='<a class="checkCity" href="index/setCurrentCity?cityId='+obj[i].childCityList[j].d_code+'">'+obj[i].childCityList[j].d_name+'</a>';
        		}
        		html+='</dd>';
        	}
        	$('#cityList').html(html);
        },
        cache: false,
        timeout: 5000,
        error: function() {
            //alert("超时");
        }
    });
});