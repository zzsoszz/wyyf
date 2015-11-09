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
		<div class="caption"><i class="fa fa-globe"></i>用户资料</div>
	</div>
	<div class="portlet-body">
		<form id="addSysForm" class="form-horizontal">
		<div class="form-body">
			<div class="row">
				<div class="col-md-10">
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label col-md-4">用户类型</label>
							<div class="col-md-8">
								<input name="" disabled="disabled" type="text" value="超级管理员" class="form-control"/>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label col-md-4">用户昵称</label>
							<div class="col-md-8">
								<input name="" disabled="disabled" value="${map.ae_st_nickName }" type="text" class="form-control" />
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label col-md-4">上次登录ip</label>
							<div class="col-md-8">
								<input name="" disabled="disabled" type="text" value="${map.ae_st_lastlogonIp}" class="form-control"  />
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label col-md-4">用户描述</label>
							<div class="col-md-8">
								<input name="" disabled="disabled" type="text" value="${map.ae_st_description}" class="form-control" />
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label col-md-4">上次登录时间</label>
							<div class="col-md-8">
								<input name="" disabled="disabled" type="text" value="${map.ae_dt_lastlogontime}" class="form-control"/>
							</div>
						</div>
					</div>
				</div>
			</div><%--
		<div class="modal-footer">
			 <button type="button" id="savePsw" class="btn blue">提交</button>
			 <button type="button" class="btn default" data-dismiss="modal">取消</button>
		</div> 
		--%></div>
	</form>
	</div>
</div>
