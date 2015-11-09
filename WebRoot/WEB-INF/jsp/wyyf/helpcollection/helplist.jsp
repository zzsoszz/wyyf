<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="/lystag" prefix="my" %>
<!--fwb-->
<script type="text/javascript" charset="utf-8" src="http://${header['host']}${pageContext.request.contextPath}/plugin/fwb/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="http://${header['host']}${pageContext.request.contextPath}/plugin/fwb/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="http://${header['host']}${pageContext.request.contextPath}/plugin/fwb/lang/zh-cn/zh-cn.js"></script>
<style>
.Validform_right{display: none;}
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
		<div class="caption"><i class="fa fa-globe"></i>意见列表</div>
	</div>
	<div class="portlet-body">
		<div title="查询条件">
			<form id="queryForm">
				标题：<input type="text" name="title" class="form-control" style="width: 200px;display: inline;">
				<%--
				来源：<my:select name="bi_st_sendState" clazz="form-control" isAddDefaltOption="true" defaltOptionKey="" defaltOptionValue="--新选择--" nameKey="YJLYLX" style="width: 200px; display:inline;" />
				--%>
					<input type="text" style="display: none;">
				<button type="button" id="queryBtn"  class="btn blue"><i class="fa fa-search"></i>查询</button>
				<button type="reset" class="btn default">重置</button>
			</form>
		</div>
		<div id="tableList" >
			
		</div>
	</div>
</div>
<!-- 添加 层-->
<div class="modal fade" id="add-config" tabindex="-1" role="basic"  aria-hidden="true">
	<div class="modal-dialog modal-wide">
		<div class="modal-content">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
			<div class="modal-header">
				<h4 class="modal-title"><span id="addOrEditor">新增公告</span><span class="zhus">注：带【<span style="color: red;">*</span>】为必填项！</span></h4>
			</div>
			<form style="margin: 0;" id="addForm" class="form-horizontal"  method="post">

			</form>
			<div class="modal-footer">
				<button type="button" id="sendBtn"  class="btn blue">发布</button>
				<button type="button" id="saveBtn"  class="btn blue">暂存</button>
				<button type="button" class="btn default" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>

<!-- 查看层-->
<div class="modal fade" id="view-config" tabindex="-1" role="basic"  aria-hidden="true">
	<div class="modal-dialog modal-wide">
		<div class="modal-content">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
			<div class="modal-header">
				<h4 class="modal-title">查看意见</h4>
			</div>
			<form style="margin: 0;" id="viewForm" class="form-horizontal"  method="post">
				<%@include file="helpView.jsp"%>
			</form>
			<div class="modal-footer">
				<button type="button" class="btn default" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>

<script>
	//初始化打开页面 
	$(document).ready(function() {
		//DateInit.init();//时间控件初始化
		loadotherPage("&pageIndex=1&pageSize=10");
	});
	$(function(){
		//查询事件
		$("#queryBtn").click(function(){
			ClickFindPage();//查询到第1页，--该函数在 commonList.jsp中
		});
	});
	//替换主题内容的函数
	function loadotherPage(params){
		var param=$("#queryForm").serialize();//条件参数
		var url="wyyf/helpCollection/sethelp";
		
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
	//进入公告查看界面
	function view(obj){
		var tr = $(obj).parents("tr").first();
		var id=tr.find("input[type='checkbox'].checkboxes").val();
		var url = "wyyf/helpCollection/findview";
		$.post(url,"id="+id,function callback(data){  
			var resp = eval("("+data+")"); 
			if(resp.success){
				var obj=resp.result[0];
				$("#view_bk_st_title").text(tr.find("td[myattrone=bk_st_title]").html());
				$("#view_bk_st_type").text(tr.find("td[myattrone=bk_st_type]").html());
				$("#view_bk_st_content").text(obj.bk_st_content);
				$("#view_bk_st_phone").text(tr.find("td[myattrone=bk_st_phone]").html());
				$("#view_bk_st_source").text(tr.find("td[myattrone=bk_st_source]").html());
				$("#view_bk_dt_addDate").text(tr.find("td[myattrone=bk_dt_addDate]").html());
				$('#view-config').modal('show');
			}else{ 
				bootbox.alert(resp.msg);
			}
		});
	}
	//删除
	function deleteHelp(obj){
		bootbox.confirm("确认是否删除？",function(rs){
			if(rs){
				var id=$(obj).parents("tr").first().find("input[type='checkbox'].checkboxes").val();
				var url = "wyyf/helpCollection/delete";
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