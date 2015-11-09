<%@ page language="java" 	pageEncoding="utf-8"%>
<%@ include file="../../commons/jsplib.jsp"%>
<%@ taglib uri="/lystag" prefix="my" %>

<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<%@ include file="../common/crumbsCom.jsp"%>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<div class="portlet box blue">
	<!-- BEGIN EXAMPLE TABLE PORTLET-->
	<div class="portlet-title">
		<div class="caption"><i class="fa fa-globe"></i>系统设置列表</div>
		<div class="actions">
			<a id="addSetBtn" href="javascript:void(0);" class="btn blue"><i class="fa fa-plus"></i>新增设置</a>
		</div>
	</div>
	<div class="portlet-body">
		<div title="查询条件">
			<form id="queryForm">
				类型：<input type="text" name="ai_st_type" class="form-control" style="width: 200px;display: inline;">
				<input type="text"   class="form-control" style="width: 200px;display: none;">
				是否启用：<my:select name="ai_st_isEnable" clazz="form-control" isAddDefaltOption="true" defaltOptionKey="" defaltOptionValue="--新选择--" nameKey="YESNO" style="width: 200px; display:inline;" />
				<button type="button" id="queryBtn"  class="btn blue">查询</button>
<%--				<button type="button" onclick="javascript:reset();" class="btn default">重置</button>--%>
			</form>
		</div>
		<div id="tableList" >
		
		</div>
	</div>
	<!-- END EXAMPLE TABLE PORTLET-->
</div>
<div class="modal fade" id="add-config" tabindex="-1" set="basic"  aria-hidden="true">
	<div class="modal-dialog modal-wide">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title" id="addsysset"></h4>
			</div>
			<form id="addForm" action="#" class="form-horizontal">
				<div class="form-body">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4">设置类型<font style="color: red;">*</font></label>
								<div class="col-md-8">
									<input name="ai_st_id" type="hidden" value="">
									<input name="ai_st_type" type="text" class="form-control" placeholder="" datatype="*"  nullmsg="请输入设置类型！">
								</div>
							</div>
						</div>
						<div class="col-md-6"> 
							 <div class="form-group">
								<label class="control-label col-md-4">是否启用</label>
								<div class="col-md-8">
									<my:select nameKey="YESNO"  name="ai_st_isEnable" clazz="form-control"  />
								</div>
							</div>
						</div>
					</div> 
					<!--/row-->
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label class="control-label col-md-2">最新链接</label>
								<div class="col-md-10">
									<input name="ai_st_remark" class="form-control"  datatype="con0-250"  errormsg="链接最长250个字符！" />
								</div>
							</div>
						</div>
					</div> 
					<!--/row-->
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label class="control-label col-md-2">设置内容</label>
								<div class="col-md-10">
									<textarea name="ai_st_content" class="form-control" rows="4"  datatype="con0-250"  errormsg="设置内容最长250个字符！"></textarea>
								</div>
							</div>
						</div>
					</div> 
					<!--/row-->
				</div>
			</form>
			
			<div class="modal-footer">
				<button type="button" id="saveBtn"  class="btn blue">保存</button>
				<button type="button" class="btn default" data-dismiss="modal">取消</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<script>

	//初始化打开页面 
	$(document).ready(function() {
		 loadotherPage("&pageIndex=1&pageSize=10");
	});
	$(function(){
		//进入新增层
		$("#addSetBtn").click(function(){
			initValidForm($("#addForm"));//清除表单验证样式
			$("#addForm")[0].reset();
			$("#addForm input[name='ai_st_id']").val("");//清空隐藏的文本框ID值
			$("#addsysset").text("新增系统设置");
			$('#add-config').modal('show');
		});
		//新增-修改权限层的保存功能
		$("#saveBtn").click(function(){
			checkFormValid($("#addForm"),function(){
				var url = "system/sysset/syssetAdd";
				var params=$("#addForm").serialize();
				newMask();
				$.post(url,params,function callback(data){  
					delMask();
					var resp = eval("("+data+")"); 
					if(resp.success){
						RefreshCurPage();//刷新当前页，--该函数在 commonList.jsp中
						bootbox.alert(resp.msg);
						$('#add-config').modal('hide');
					}else{ 
						bootbox.alert(resp.msg);
					}
				});
			});
		});
		//查询事件
		$("#queryBtn").click(function(){
			ClickFindPage();//查询到第1页，--该函数在 commonList.jsp中
		});
	});
	//查询函数--替换主题内容的函数
	function loadotherPage(params) {
		var param=$("#queryForm").serialize();//条件参数
		var url="system/sysset/syssetList";
		
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
	//进入修改系统设置
	function editSet(obj){
		initValidForm($("#addForm"));//清除表单验证样式
		$("#addsysset").text("编辑系统设置");
		var setid=$(obj).parents("tr").first().find("input[type='checkbox'].checkboxes").val();
		var url = "system/sysset/findSetById";
		newMask();
		$.post(url,"setid="+setid,function callback(data){  
			delMask();
			var resp = eval("("+data+")"); 
			if(resp.success){
				var obj=resp.result[0];
				formload($("#addForm"),obj);
				$('#add-config').modal('show');
			}else{ 
				bootbox.alert(resp.msg);
			}
		});
	}
	//系统删除 
	function delSet(obj){
		bootbox.confirm("确认删除？",function(rs){
			if(rs){
				var setid=$(obj).parents("tr").first().find("input[type='checkbox'].checkboxes").val();
				var url = "system/sysset/syssetDelete";
				newMask();
				$.post(url,"deleteData="+setid,function callback(data){  
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
