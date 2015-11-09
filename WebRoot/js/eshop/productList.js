
var page='';
var OrderByType=0;//0=综合排序 1=按价格 2=按销量
var Up=0;//降序 
var minPrice='';
var maxPrice='';
var IsPromotion=false;
var keywords='';
var bb_st_id='';
$(function(){
	page=$("#page").val();
	OrderByType=$("#OrderByType").val();
	Up=$("#Up").val();
	minPrice=$("#minPrice").val();
	maxPrice=$("#maxPrice").val();
	IsPromotion=$("#IsPromotion").val();
	keywords=$("#keywords").val();
	bb_st_id=$("#bb_st_id").val();
});
function ChangePromotion(target){
	IsPromotion=target.checked;
	load();
}
function setBb_st_id(stid){
	bb_st_id=stid;
	load();
}
function load(){
	window.location='index/ProductList?keywords='+keywords+'&bb_st_id='+bb_st_id+'&IsPromotion='+IsPromotion+'&page='+page+'&OrderByType='+OrderByType+'&Up='+Up+'&minPrice='+minPrice+'&maxPrice='+maxPrice;
}
//默认排序
function zonghe(flag){
	OrderByType='0';
	if(flag=='0'){
		Up=1;
	}else{
		Up=0;
	}
	load();
}
function xiaoliang(flag){
	OrderByType='2';
	if(flag=='0'){
		Up=1;
	}else{
		Up=0;
	}
	load();
}
function jiage(flag){
	OrderByType='1';
	if(flag=='0'){
		Up=1;
	}else{
		Up=0;
	}
	load();
}
function setPrice(){
	var minp = $("#minprice").val();
	var maxp = $("#maxprice").val();
	if(!isNaN(minp)){
		minPrice=minp;
	}else{
		minPrice=0;
	}
	if(!isNaN(maxp)){
		maxPrice=maxp;
	}else{
		maxPrice="";
	}
	load();
}
function setType(flag){
	type=flag;
	load();
}
function setPage(pageIndex){
	page=pageIndex;
	load();
}

var model = avalon.define({
	$id : "box",
	IsPromotion:false,
	keywords:'',
	bb_st_id:'',
	type : '',
	page : '',
	OrderByType : 0,
	Up : 0,
	minPrice : '',
	maxPrice : '',
	shoptype : '',
	products:[],
	showPageNum:0,
	totalElements:0,
	totalPages:0,
	last:false,
	first:false,
	next:0,
	previous:0,
	currPageNumber:0,
	pageList: [1],
	zonghe : function(flag) {
		model.OrderByType = '0';
		if (flag == '0') {
			model.Up = 1;
		} else {
			model.Up = 0;
		}
		model.load();
	},
	init:function(){
		model.type = $("#type").val();
		model.page = $("#page").val();
		model.OrderByType = $("#OrderByType").val();
		model.Up = $("#Up").val();
		model.minPrice = $("#minPrice").val();
		model.maxPrice = $("#maxPrice").val();
		model.shoptype = $("#shoptype").val();
		model.IsPromotion=$("#IsPromotion").val();
		model.keywords=$("#keywords").val();
		model.bb_st_id=$("#bb_st_id").val();
		model.load();
		
	},
	xiaoliang : function(flag) {
		model.OrderByType = '2';
		if (flag == '0') {
			model.Up = 1;
		} else {
			model.Up = 0;
		}
		model.load();
	},
	jiage : function(flag) {
		model.OrderByType = '1';
		if (flag == '0') {
			model.Up = 1;
		} else {
			model.Up = 0;
		}
		model.load();
	},
	setPrice : function() {
		var minp = $("#minprice").val();
		var maxp = $("#maxprice").val();
		if (!isNaN(minp)) {
			model.minPrice = minp;
		} else {
			model.minPrice = 0;
		}
		if (!isNaN(maxp)) {
			model.maxPrice = maxp;
		} else {
			model.maxPrice = "";
		}
		model.load();
	},
	setType : function(flag) {
		type = flag;
		model.load();
	},
	setPage : function(pageIndex) {
		model.page = pageIndex;
		model.load();
	},
	 ChangePromotion:function(target){
		 model.IsPromotion=target.checked;
		 model.load();
	},
	 setBb_st_id:function(stid){
		 model.bb_st_id=stid;
		 model.load();
	},
	load : function() {
		$.ajax({
			type : 'get',
			url : 'index/ProductListAjax',
			data :'keywords='+model.keywords+'&bb_st_id='+model.bb_st_id+'&IsPromotion='+model.IsPromotion+'&page='+model.page+'&OrderByType='+model.OrderByType+'&Up='+model.Up+'&minPrice='+model.minPrice+'&maxPrice='+model.maxPrice,
			dataType : 'json',
			success : function(msg) {
				model.products=msg.data;
				model.showPageNum=msg.showPageNum;
				model.totalElements=msg.totalElements;
				model.totalPages=msg.totalPages;
				model.last=msg.last;
				model.first=msg.first;
				model.next=msg.next;
				model.previous=msg.previous;
				model.currPageNumber=msg.currPageNumber;
				model.pageList = avalon.filters.pageCountToList(model.totalPages);
			},
			error : function(msg) {
				//alert("	网络传输错误，请检查您的网络连接");
			}
		});
	}
});
model.init();










