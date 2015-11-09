<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/lystag" prefix="my" %>

<style>
.Validform_right{display: none;}
</style>
<style> 
       
        .white_content { 
            display: none; 
            position: absolute; 
            top: 25%; 
            left: 25%; 
            width: 55%; 
            height: 55%; 
            padding: 20px; 
            border: 10px solid orange; 
            background-color: white; 
            z-index:1002; 
            overflow: auto; 
        } 
    </style> 
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<%@ include file="../../system/common/crumbsCom.jsp"%>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<div class="portlet box blue">
	<!-- BEGIN EXAMPLE TABLE PORTLET-->
	<div class="portlet-title">
		<div class="caption"><i class="fa fa-globe"></i>订单列表</div>
		<div class="actions">
			<%--<a id="addBtn" href="javascript:void(0);" class="btn blue"><i class="fa fa-plus"></i> 添加</a>--%>
		</div>
	</div>
	<div class="portlet-body">
		<div title="查询条件">
			<form id="queryForm">
				订单编号：<input type="text" name="bh_st_ddnum" class="form-control" style="width: 200px;display: inline;" >
				订单状态：<my:select name="bh_st_ddstate" clazz="form-control" nameKey="DDZT" isAddDefaltOption="true" defaltOptionKey="" defaltOptionValue="-请选择-" style="width: 200px;display: inline;"/>
				<input type="text"  style="display:none;" >
				<button type="button" id="querybtn"  class="btn blue"><i class="fa fa-search"></i>查询</button>
			<button class="btn default"  type="reset">重置</button>
			</form>
		</div>
		<div  id="tableList" >
		
		</div>
	</div>
	<!-- END EXAMPLE TABLE PORTLET-->
</div>
<!-- 编辑订单信息层 -->
<div class="modal fade" id="add-my1-config" tabindex="-1" role="basic"  data-backdrop="static"  aria-hidden="true">
	<div class="modal-dialog modal-wide">
		<div class="modal-content">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
			<div class="modal-header">
				<h4  class="modal-title"><span id="titleText">编辑订单信息</span><span class="zhus">注：带【<span style="color: red;">*</span>】为必填项！</span></h4>
			</div>
			<form id="addForm" class="form-horizontal"  method="post">
				<div class="form-body" id="addFormbodyDIV">
				
				</div>
				<div class="modal-footer">
					<button type="button" id="saveBtn"  class="btn blue" style="display: none;">保存</button>
					<button type="button" class="btn default" data-dismiss="modal">关闭</button>
				</div>
			</form>
		</div>
	</div>
</div>
<div id="light" class="white_content" style="display:none" >
					<a id="wytk" href =""   target="_blank">点击去退款</a>
					<a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display='none';">关闭</a>
					</div> 
<script>
	//初始化打开页面 
	$(document).ready(function() {
		DateInit.init();//时间控件初始化
		loadotherPage("&pageIndex=1&pageSize=10");
	});
	$(function(){
		//点击保存事件
		$("#saveBtn").click(function(){
			checkFormValid($("#addForm"),function(){
				var url = "wyyf/orderForm/orderFormSave";
				var params=$("#addForm").serialize();
				var id=$("#addForm input[name='bh_st_id']").val();
				$.post(url,params,function callback(data){  
					var resp = eval("("+data+")"); 
					if(resp.success){
						RefreshCurPage();//刷新当前页，--该函数在 commonList.jsp中
						$('#add-my1-config').modal('hide');
						//修改
						if(id!=null&&id!=""){
							bootbox.alert("修改成功！");
						}
					}else{ 
						bootbox.alert(resp.msg);
					}
				});
			});
		});
		//查询按钮点击事件
		$("#querybtn").click(function(){
			ClickFindPage();
		});
		
	});
	
	//替换主题内容的函数
	function loadotherPage(params) {
		var param=$("#queryForm").serialize();//条件参数
		var url="wyyf/orderForm/orderFormList";
		
		var xsl=$("#commonid_column_toggler input[name='ISSHOWCOLUMS'][type='checkbox']:checked").serialize();//显示列的控制
		if(xsl){
			param+="&"+xsl;
		}
		newMask();
		jQuery("#tableList").load(url,param+params+"&rr="+new Date().getTime(),function(response,status,xhr){
			delMask();
			if(xhr.status==403){
				$(this).html(xhr.responseText);
			}else if(xhr.status==404){
				$(this).html(xhr.responseText);
			}
		});
	}
	//进入修改订单信息=--去修改
	function editBtn(obj){
		$("#saveBtn").show();//确认按钮显示
		var orderFormId=$(obj).parents("tr").first().find("input[type='checkbox'].checkboxes").val();
		newMask();
		jQuery("#addFormbodyDIV").load("wyyf/orderForm/queryInfo","id="+orderFormId,function(response,status,xhr){
			delMask();
			if(xhr.status==403){
				$(this).html(xhr.responseText);
			}else if(xhr.status==404){
				$(this).html(xhr.responseText);
			}
			$('#add-my1-config').modal('show');
			delMask(); 
		});	
	}
	function tkdo(obj){//退款
		 var orderNo=$(obj).parent().siblings()[1].innerHTML;
		 var orderFormId=$(obj).parents("tr").first().find("input[myname='chargeId']").val();
		
		 var json="{\"id\":'"+orderFormId+"',\"from\":'1',\"price\":\"1\",\"orderNo\":'"+ $.trim(orderNo)+"',\"description\":'1'}";
		$.post("services/refund", {
				"json" : json
			}, function(data) {
			
					
				if(data.success=='true'){
					 var  str=data.refund.failure_msg;
					var url=str.substring(str.indexOf("https"),str.length);
					
					document.getElementById('light').style.display='block';
					$("#wytk").attr("href",url);
					 console.debug(url);
					 //confirm(url);
					 
				}
				 
			}, "json");
	}
	//查看订单信息
	function showBtn(obj) {
		$("#saveBtn").hide();//确认按钮隐藏
		var orderFormId = $(obj).parents("tr").first().find(
				"input[type='checkbox'].checkboxes").val();
		newMask();
		jQuery("#addFormbodyDIV").load("wyyf/orderForm/queryInfo",
				"id=" + orderFormId, function(response, status, xhr) {
					delMask();
					if (xhr.status == 403) {
						$(this).html(xhr.responseText);
					} else if (xhr.status == 404) {
						$(this).html(xhr.responseText);
					}
					$('#add-my1-config').modal('show');
				});
	}
	//删除订单信息
	function delBtn(obj) {
		bootbox.confirm('确定是否删除该订单？', function(rs) {
			if (rs) {
				newMask();
				var orderFormId = $(obj).parents("tr").first().find(
						"input[type='checkbox'].checkboxes").val();
				var url = "wyyf/orderForm/orderFormdelete";
				$.post(url, "id=" + orderFormId, function callback(data) {
					delMask();
					var resp = eval("(" + data + ")");
					if (resp.success) {
						RefreshCurPage();//刷新当前页，--该函数在 commonList.jsp中
						bootbox.alert(resp.msg);
					} else {
						bootbox.alert(resp.msg);
					}
				});
			}
		});
	}
</script> 

