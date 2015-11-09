<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="/lystag" prefix="my" %>

<!--fwb-->
<script type="text/javascript" charset="utf-8" src="http://${header['host']}${pageContext.request.contextPath}/plugin/fwb/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="http://${header['host']}${pageContext.request.contextPath}/plugin/fwb/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="http://${header['host']}${pageContext.request.contextPath}/plugin/fwb/lang/zh-cn/zh-cn.js"></script>
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
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<%@ include file="../../system/common/crumbsCom.jsp"%>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>

<div class="portlet box blue">
	<div class="portlet-title">
		<div class="caption"><i class="fa fa-globe"></i>文章列表</div>
		<div class="actions">
			<a  href="javascript:void(0);" class="btn blue" id="addBtn"><i class="fa fa-plus"></i> 添加</a>
		</div>
	</div>
	<div class="portlet-body">
		<div title="查询条件">
			<form id="queryForm">
				标题：<input type="text" name="bl_st_title" class="form-control" style="width: 200px;display: inline;" onkeypress="enterPress1(event)">
				类型：<my:select name="bl_st_type" clazz="form-control" isAddDefaltOption="true" defaltOptionKey="" defaltOptionValue="--新选择--" nameKey="HELPLX" style="width: 200px; display:inline;" />
				是否发布：<my:select name="bl_st_isSend" clazz="form-control" isAddDefaltOption="true" defaltOptionKey="" defaltOptionValue="--新选择--" nameKey="YESNO" style="width: 200px; display:inline;" />
				<input type="text"  style="width: 200px;display:none;" >
				<button type="button" id="queryBtn"  class="btn blue"><i class="fa fa-search"></i>查询</button>
				<button type="reset" class="btn default">重置</button>
			</form>
		</div>
		<div id="tableList" >
			
		</div>
	</div>
</div>
<!-- 添加 层-->
<div class="modal fade" id="add-my1-config" tabindex="-1" role="basic"  data-backdrop="static"  aria-hidden="true">
	<div class="modal-dialog modal-wide">
		<div class="modal-content">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
			<div class="modal-header">
				<h4 class="modal-title"><span id="titleMsg">新增文章</span><span class="zhus">注：带【<span style="color: red;">*</span>】为必填项！</span></h4>
			</div>
			<form style="margin: 0;" id="addForm" class="form-horizontal"  method="post">
				<div class="form-body">
					<div class="row">
						<div class="col-md-8">
							<div class="form-group">
								<label class="control-label col-md-3">标题<font style="color: red;">*</font></label>
								<div class="col-md-9">
									<div style="display: none;">
										<input type="text"  name="bl_st_id" />
										<input type="text" name="bl_st_isSend" id="bl_st_isSend"/>
									</div>
									<input name="bl_st_title" type="text" class="form-control" datatype="*" nullmsg="请输入标题！" placeholder="" maxlength="100">
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label class="control-label col-md-4">类型<font style="color: red;">*</font></label>
								<div class="col-md-8">
									<my:select name="bl_st_type" clazz="form-control"  nameKey="HELPLX" otherAttr=" datatype='*' nullmsg='请选择类型！' "/>
								</div>
							</div>
						</div>
					</div> 
					<!--/row-->
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label class="control-label col-md-2">内容摘要</label>
								<div class="col-md-10">
									<input name="bl_st_summary" type="text" class="form-control" placeholder="" maxlength="250">
								</div>
							</div>
						</div>
					</div> 
					<!--/row-->
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label class="control-label col-md-2">内容<font style="color: red;">*</font></label>
								<div class="col-md-10">
									<textarea  name="bl_st_context" id="bl_st_context" style="width:100%;height:360px;top:40px;"></textarea>
								</div>
							</div>
						</div>
					</div> 
					<!--/row-->
				</div>
			</form>
			<div class="modal-footer">
				<button type="button" id="sendBtn"  class="btn blue">发布</button>
				<button type="button" id="saveBtn"  class="btn blue">暂存</button>
				<button type="button" class="btn default" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>
<!-- 富文本编辑器 -->
<script>
	//实例化编辑器
	var bl_st_context_ue = UE.getEditor('bl_st_context', {
		autoHeightEnabled: false
	});
</script>
<script>
	//初始化打开页面 
	$(document).ready(function() {
		//DateInit.init();//时间控件初始化
		loadotherPage("&pageIndex=1&pageSize=10");
	});
	$(function(){
		//进入新增层
		$("#addBtn").click(function(){
			$("#titleMsg").html("新增文章");
			$("#addForm")[0].reset();
			bl_st_context_ue.setContent("");//在富文本编辑器中设置值。
			initValidForm($("#addForm"));
			$('#add-my1-config').modal('show');
		}); 
		//新增-修改权限层的暂存功能
		$("#saveBtn").click(function(){
			$("#bl_st_isSend").val("0");
			formSave("0");
		});
		//新增-修改权限层的发布功能
		$("#sendBtn").click(function(){
			$("#bl_st_isSend").val("1");
			formSave("1");
		});
		var msgxxx = "";
		function formSave(num){
			if(bl_st_context_ue.getContentLength(true) <= 10000){
				msgxxx = "";
				if(num == "1") {
					msgxxx="发布成功！";
				} else{
					msgxxx="暂存成功！";
				}
				checkFormValid($("#addForm"),function(){
					var url = "wyyf/notice/addNotice";
					var params=$("#addForm").serialize();
					$.post(url,params,function callback(data){  
						var resp = eval("("+data+")"); 
						if(resp.success){
							bootbox.alert(msgxxx);
							RefreshCurPage();//刷新当前页，--该函数在 commonList.jsp中
							$('#add-my1-config').modal('hide');
						}else{ 
							bootbox.alert(resp.msg);
						}
						msgxxx = "";
					});
				});
			}else{
				bootbox.alert("文章内容字数超出最大允许值！");
			}
			
		}
		//查询事件
		$("#queryBtn").click(function(){
			ClickFindPage();//查询到第1页，--该函数在 commonList.jsp中
		});
	});

	//回车查询事件
	function enterPress1(e){ //传入 event 
		var e = e || window.event;
		if(e.keyCode == 13){ 
			$("#queryBtn")[0].click();
		} 
	} 
	
	//替换主题内容的函数
	function loadotherPage(params){
		var param=$("#queryForm").serialize();//条件参数
		var url="wyyf/notice/noticeList";
		
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
	//进入编辑界面
	function showEdit(obj){
		$("#titleMsg").html("编辑文章");
		initValidForm($("#addForm"));
		var id=$(obj).parents("tr").first().find("input[type='checkbox'].checkboxes").val();
		var url = "wyyf/notice/findNotice";
		$.post(url,"id="+id,function callback(data){  
			var resp = eval("("+data+")"); 
			if(resp.success){
				var obj=resp.result[0];
				formload($("#addForm"),obj);
				bl_st_context_ue.reset();
				bl_st_context_ue.setContent(obj.bl_st_context);//在富文本编辑器中设置值。
				$('#add-my1-config').modal('show');
			}else{ 
				bootbox.alert(resp.msg);
			}
		});
	}
	//删除
	function del(obj){
		bootbox.confirm("确认是否删除？",function(rs){
			if(rs){
				var id=$(obj).parents("tr").first().find("input[type='checkbox'].checkboxes").val();
				var url = "wyyf/notice/delNotice";
				$.post(url,"id="+id,function callback(data){  
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