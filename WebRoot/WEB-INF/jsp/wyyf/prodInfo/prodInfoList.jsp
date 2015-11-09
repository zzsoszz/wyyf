<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/lystag" prefix="my" %>

<style>
.Validform_right{display: none;}
/* 弹出对话框按钮和对话框大小 */
.modal-backdrop {
z-index: 49 !important;
}
.modal {
z-index: 50 !important;
}
/**预览功能时候的top*/
.edui-default .edui-dialog {
 top:40px !important;
}
.input-daterange input {
    text-align: left;
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
		<div class="caption"><i class="fa fa-globe"></i>商品列表</div>
		<div class="actions">
<!-- 			<a id="setQGTimeBtn" href="javascript:void(0);" class="btn blue"><i class="fa fa-plus"></i> 设置抢购时间</a> -->
		</div>
	</div>
	<div class="portlet-body">
		<div title="查询条件">
			<form id="queryForm">
				商品编号：<input type="text" name="bg_st_num" class="form-control" style="width: 200px;display: inline;" >
				商品名称：<input  name="bg_st_name" class="form-control "  type="text" value="" style="width: 200px;display: inline;" />
				商品类型：<my:select name="bg_st_randid" clazz="form-control" nameKey="PPLX" style="width: 100px;display: inline;" isAddDefaltOption="true" defaltOptionKey="" defaltOptionValue="-请选择-"/>
				<button type="button" id="queryBtn"  class="btn blue">查询</button>
				<button type="button" onclick="javascript:reset();" class="btn default">重置</button> 
			</form>
		</div>
		<div  id="tableList" >
		
		</div>
	</div>
	<!-- END EXAMPLE TABLE PORTLET-->
</div>
<!-- 修改商品层 -->
<div class="modal fade" id="upd-my1-config" tabindex="-1" role="basic"  data-backdrop="static"  aria-hidden="true">
	<div class="modal-dialog modal-wide">
		<div class="modal-content">
			<div id="updconfigdiv" class="modal-body">
				  
			</div>
		</div>
	</div>
</div>
<!-- 抢购时间设置层 -->
<div class="modal fade" id="set-qgtime-config" tabindex="-1" role="basic"  data-backdrop="static"  aria-hidden="true">
	<div class="modal-dialog modal-wide">
		<div class="modal-content">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
			<div class="modal-header">
				<h4 class="modal-title"><span id="titleMsg">抢购时间设置</span><span class="zhus">注：带【<span style="color: red;">*</span>】为必填项！</span></h4>
			</div>
			<form style="margin: 0;" id="setForm" class="form-horizontal"  method="post">
				<div class="form-body">
					<div class="row">
						<div class="col-md-6">
							<label class="control-label col-md-4">时间开始</label>
							<div class="col-md-8">                                                                     
								<input name="bg_dt_startDate" type="text" class="form-control form-control-inline input-group form_advance_datetime"  size="16"  datatype="*"   nullmsg="请输入开始时间！"/>
							</div>
						</div>
						<div class="col-md-6">
							<label class="control-label col-md-4">时间结束</label>
							<div class="col-md-8">                                                                     
								<input name="bg_dt_endDate" type="text" class="form-control form-control-inline input-group form_advance_datetime"  size="16" datatype="*"   nullmsg="请输入结束时间！"/>
							</div>
						</div>
					</div> 
					<!--/row-->
				</div>
			</form>
			<div class="modal-footer">
				<button type="button" id="setsaveBtn"  class="btn blue">保存设置</button>
				<button type="button" class="btn default" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>

<script>
	//初始化打开页面 
	$(document).ready(function() {
		DateInit.init();//时间控件初始化
		loadotherPage("&pageIndex=1&pageSize=10");
	});
	$(function(){
		//查询按钮点击事件
		$("#queryBtn").click(function(){
			ClickFindPage();//查询到第1页，--该函数在 commonList.jsp中
		});
		//设置抢购时间  按钮 点击事件
		$("#setQGTimeBtn").click(function(){
			$("#setForm")[0].reset();
			$('#set-qgtime-config').modal('show');
		});
		//新增-修改权限层的发布功能
		$("#setsaveBtn").click(function(){
			checkFormValid($("#setForm"),function(){
				var url = "wyyf/prodInfo/setqgTime";
				var params=$("#setForm").serialize();
				$.post(url,params,function callback(data){  
					var resp = eval("("+data+")"); 
					if(resp.success){
						RefreshCurPage();//刷新当前页，--该函数在 commonList.jsp中
						$('#set-qgtime-config').modal('hide');
					}
					bootbox.alert(resp.msg);
				});
			});
		});
	});
	
	//替换主题内容的函数
	function loadotherPage(params) {
		var param=$("#queryForm").serialize();//条件参数
		var url="wyyf/prodInfo/prodInfoList";
		
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
	//进入修改商品信息
	function editBtn(obj){
		var prodInfoId=$(obj).parents("tr").first().find("input[type='checkbox'].checkboxes").val();
		newMask();
		jQuery("#updconfigdiv").load('wyyf/prodInfo/intoProdInfoInto?id='+prodInfoId,"r="+new Date().getTime(),function(response,status,xhr){
			delMask();
			if(xhr.status==403){
				$(this).html(xhr.responseText);
			}else if(xhr.status==404){
				$(this).html(xhr.responseText);
			}
			$('#upd-my1-config').modal('show');
		});	
	}
	//删除商品信息
	function delBtn(obj){
		bootbox.confirm('确定是否删除该商品？',function(rs){
			if(rs){
				var prodInfoId=$(obj).parents("tr").first().find("input[type='checkbox'].checkboxes").val();
				var url = "wyyf/prodInfo/prodInfodelete";
				newMask();
				$.post(url,"id="+prodInfoId,function callback(data){  
					delMask();
					var resp = eval("("+data+")"); 
					if(resp.success){
						RefreshCurPage();//刷新当前页，--该函数在 commonList.jsp中
						bootbox.alert(resp.msg);
					}else{ 
						bootbox.alert(resp.msg);
					}
				});
			}
		});
	}
 
</script> 

