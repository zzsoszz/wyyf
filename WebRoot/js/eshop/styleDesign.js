var pageIndex=1;
var cityCode='';
var zxType='0';
var OrderByType=0;
var Up=0;
$(function(){
	
	cityCode=$('#cityCode').val();
	pageIndex=$('#page').val();
	zxType=$('#zxType').val();
	OrderByType=$('#OrderByType').val();
	Up=$('#Up').val();
});
function load(){
	location.href='index/designstyle?page='+pageIndex+'&zxType='+zxType+'&cityCode='+cityCode+'&OrderByType='+OrderByType+'&Up='+Up;
}
function setCityCode(code){
	cityCode=code;
	load();
}
function setOrderBy(o,flag){
	OrderByType=o;
	if(flag=='0'){
		Up=1;
	}else{
		Up=0;
	}
	load();
	
}
function setPage(page){
	pageIndex=page;
	load();
}

function setZx(zx){
	zxType=zx;
	load();
}