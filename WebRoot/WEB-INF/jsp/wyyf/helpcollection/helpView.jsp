<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<style>
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
</style>
<!--意见查看界面 -->
<div class="form-body">
	<div class="row">
		<div class="col-md-12">
			<div class="form-group">
				<label class="control-label col-md-2">意见标题：</label>
				<div class="col-md-10">
				    <div id="view_bk_st_title" class="col-md-12" style="padding-top: 7px;"></div>
				</div>
			</div>
		</div>
	</div> 
	<div class="row">
		<div class="col-md-12">
			<div class="form-group">
				<label class="control-label col-md-2">意见类型：</label>
				<div class="col-md-10">
				    <div id="view_bk_st_type" class="col-md-12" style="padding-top: 7px;"></div>
				</div>
			</div>
		</div>
	</div> 
	<!--/row-->
	<div class="row">
		<div class="col-md-12">
			<div class="form-group">
				<label class="control-label col-md-2">联系电话：</label>
				<div class="col-md-10">
					<div id="view_bk_st_phone" class="col-md-12" style="padding-top: 7px;"></div>
				</div>
			</div>
		</div>
	</div> 
	<div class="row">
		<div class="col-md-12">
			<div class="form-group">
				<label class="control-label col-md-2">意见来源：</label>
				<div class="col-md-10">
					<div id="view_bk_st_source" class="col-md-12" style="padding-top: 7px;"></div>
				</div>
			</div>
		</div>
	</div> 
	<div class="row">
		<div class="col-md-12">
			<div class="form-group">
				<label class="control-label col-md-2">提交时间：</label>
				<div class="col-md-10">
					<div id="view_bk_dt_addDate" class="col-md-12" style="padding-top: 7px;"></div>
				</div>
			</div>
		</div>
	</div> 
	<div class="row">
		<div class="col-md-12">
			<div class="form-group">
				<label class="control-label col-md-2">意见内容：</label>
				<div class="col-md-10">
					<div id="view_bk_st_content" class="col-md-12" style="padding-top: 7px;"></div>
				</div>
		</div>
	</div> 
</div>
