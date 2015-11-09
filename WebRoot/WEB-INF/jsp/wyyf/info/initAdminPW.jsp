<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<%@ include file="../../system/common/crumbsCom.jsp"%>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<div class="portlet box blue">
	<div class="portlet-title">
		<div class="caption"><i class="fa fa-globe"></i>修改密码</div>
	</div>
	<div class="portlet-body">
	<form id="pswForm" class="form-horizontal"  encType="multipart/form-data" method="post">
		<div class="form-body">
			<div class="row">
				<div class="col-md-10">
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label col-md-4">原密码<font style="color: red;display: inline">*</font></label>
							<div class="col-md-8">
								<input name="oldPsw" type="password" class="form-control" nullmsg="请输入原密码！" errormsg="请输入原密码！"/>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label col-md-4">新密码<font style="color: red;display: inline">*</font></label>
							<div class="col-md-8">
								<input name="newPsw" type="password" class="form-control" nullmsg="请设置密码！" datatype="*6-16" errormsg="密码范围在6~16位之间！"/>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label col-md-4">确认密码<font style="color: red;display: inline">*</font></label>
							<div class="col-md-8">
								<input name="newPsw2" type="password" class="form-control"  datatype="*" recheck="newPsw" nullmsg="请再输入一次密码！" errormsg="您两次输入的账号密码不一致！" />
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="modal-footer" style="text-align: center;">
			<button type="button" id="savePsw"  class="btn blue"><i class="fa fa-check"></i>修改保存</button>
<%--			<button type="button" class="btn default" id="close" data-dismiss="modal">取消</button>--%>
		</div>
	</form>
	</div>
</div>
<script>
	$(function(){
		$("#savePsw").click(function(){
			checkFormValid($("#pswForm"), function() {
				var param = $("#pswForm").serialize();
				$.post("wyyf/info/updatePsw",param,function(data){
					data = eval("("+data+")");
					bootbox.alert(data.msg);
					if(data.success=='true'){
						$("#pswForm")[0].reset();
						}
				});
			});
		});
	});
</script>
