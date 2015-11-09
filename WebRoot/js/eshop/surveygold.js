var pageIndex = 1;
var cityCode = '';
var type = '';
var OrderByType = 0;
var Up = 0;
$(function() {
	cityCode = $('#cityCode').val();
	page = $('#page').val();
	type = $('#type').val();
	OrderByType = $('#OrderByType').val();
	Up = $('#Up').val();
});
function load() {
	window.location = 'index/supervision?page=' + page + '&type=' + type
			+ '&page=' + pageIndex + '&cityCode=' + cityCode + '&OrderByType='
			+ OrderByType + '&Up=' + Up;
}
function setCityCode(code) {
	cityCode = code;
	load();
}
function setOrderBy(o, flag) {
	OrderByType = o;
	if (flag == '0') {
		Up = 1;
	} else {
		Up = 0;
	}
	load();
}
function setType(page) {
	pageIndex = page;
	load();
}

var model = avalon.define({
	$id : "box",
	pageIndex : 1,
	cityCode : '',
	type : '',
	OrderByType : 0,
	Up : 0,
	products : [],
	showPageNum : 0,
	totalElements : 0,
	totalPages : 0,
	last : false,
	first : false,
	next : 0,
	previous : 0,
	currPageNumber : 0,
	pageList : [ 1 ],
	init : function() {
		model.cityCode = $('#cityCode').val();
		model.page = $('#page').val();
		model.type = $('#type').val();
		model.OrderByType = $('#OrderByType').val();
		model.Up = $('#Up').val();
		model.load();
	},
	setCityCode : function(code) {
		model.cityCode = code;
		model.load();
	},
	Convert:function(num){
		  var list = [];
		    for (var i = 1; i <= num; i++) {
		        list.push(i);
		    }
		    return list;
	},
	setOrderBy : function(o, flag) {
		model.OrderByType = o;
		if (flag == '0') {
			model.Up = 1;
		} else {
			model.Up = 0;
		}
		model.load();
	},
	setType : function(page) {
		model.pageIndex = page;
		model.load();
	},
	load : function() {
		$.ajax({
			type : 'get',
			url : 'index/supervisionAjax',
			data : 'page=' + model.page + '&type=' + model.type
				+ '&page=' + model.pageIndex + '&cityCode=' + model.cityCode + '&OrderByType='
				+ model.OrderByType + '&Up=' + model.Up,
			dataType : 'json',
			success : function(msg) {
				model.products = msg.data;
				model.showPageNum = msg.showPageNum;
				model.totalElements = msg.totalElements;
				model.totalPages = msg.totalPages;
				model.last = msg.last;
				model.first = msg.first;
				model.next = msg.next;
				model.previous = msg.previous;
				model.currPageNumber = msg.currPageNumber;
				model.pageList = avalon.filters.pageCountToList(model.totalPages);
			},
			error : function(msg) {
				alert("	网络传输错误，请检查您的网络连接");
			}
		});
	}
});
model.init();