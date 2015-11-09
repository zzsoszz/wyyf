<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="/lystag" prefix="my" %>

<link href="http://${header['host']}${pageContext.request.contextPath}/plugin/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="http://${header['host']}${pageContext.request.contextPath}/plugin/zTree/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="http://${header['host']}${pageContext.request.contextPath}/plugin/zTree/js/jquery.ztree.excheck-3.5.min.js"></script>
<style>
<!--
ul.ztree {margin-top: 0px;border: 1px solid #617775;background: #f0f6e4;width:95%;height:300px; overflow-x:auto;}
-->
</style>
<div class="row">
	<div class="col-md-12">
		<%@ include file="../common/crumbsCom.jsp"%>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<div class="portlet box blue">
	<!-- BEGIN EXAMPLE TABLE PORTLET-->
	<div class="portlet-title">
		<div class="caption"><i class="fa fa-globe"></i>角色列表</div>
		<div class="actions">
			<a id="addRoleBtn" href="javascript:void(0);" class="btn blue"><i class="fa fa-plus"></i>新增角色</a>
		</div>
	</div>
	<div class="portlet-body">
		<div title="查询条件">
			<form id="queryForm">
				名称：<input type="text" name="ac_st_name" class="form-control" style="width: 200px;display: inline;">
				标识：<input type="text" name="ac_st_code" class="form-control" style="width: 200px;display: inline;">
				类型：<my:select name="ac_st_type" clazz="form-control" isAddDefaltOption="true" defaltOptionKey="" defaltOptionValue="--新选择--" nameKey="JSLXZD" style="width: 200px; display:inline;" />
				<button type="button" id="queryBtn"  class="btn blue">查询</button>
				<button type="reset" class="btn default">重置</button>
			</form>
		</div>
		<div id="tableList" >
		
		</div>
	</div>
	<!-- END EXAMPLE TABLE PORTLET-->
</div>
<div class="modal fade" id="add-config" tabindex="-1" role="basic"  data-backdrop="static"  aria-hidden="true">
	<div class="modal-dialog modal-wide">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title" id="roleedit">新增角色</h4>
			</div>
			<form id="addForm" action="#" class="form-horizontal">
				<div class="form-body">
					<div class="row">
						<div class="col-md-6">
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-4">角色名<font style="color: red;">*</font></label>
									<div class="col-md-8">
										<input name="ac_st_id" type="hidden" value="">
										<input name="ac_st_name" type="text" class="form-control" placeholder="" datatype="*1-25"  nullmsg="请输入角色名！" errormsg="角色名至少一个字符，最大25个字符！">
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-4">角色标识<font style="color: red;">*</font></label>
									<div class="col-md-8">
										<input name="ac_st_code" type="text" class="form-control" placeholder="" datatype="*1-25"  nullmsg="请输入角色标识！"  errormsg="角色标识至少一个字符，最大25个字符！">
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-4">角色类型</label>
									<div class="col-md-8">
										<my:select name="ac_st_type" clazz="form-control" nameKey="JSLXZD" initSelectedKey="0"/>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-4">描述</label>
									<div class="col-md-8">
										<textarea name="ac_st_describe" class="form-control" rows="3"></textarea>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6"> 
							<label class="control-label col-md-3">权限选择</label>
							<div class="col-md-9">
								 <input name="powerids" type="hidden">
								 <div id="powermenudiv">
									<ul id="powersTree" class="ztree"  ></ul>
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
		$("#addRoleBtn").click(function(){
			initValidForm($("#addForm"));//清除表单验证样式
			$("#addForm")[0].reset();
			$("#addForm input[name='ac_st_id']").val("");//清空隐藏的文本框ID值
			$("#addForm input[name='powerids']").val("");//清空隐藏的文本框权限值
			$.fn.zTree.getZTreeObj("powersTree").checkAllNodes(false);//取消勾选 全部节点
			$("#roleedit").text("新增角色");	
			$('#add-config').modal('show');
		});
		//新增-修改权限层的保存功能
		$("#saveBtn").click(function(){
			checkFormValid($("#addForm"),function(){
				var url = "system/sysrole/sysroleAdd";
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
		var url="system/sysrole/sysroleList";
		
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
	//进入修改角色
	function editRole(obj){
		initValidForm($("#addForm"));//清除表单验证样式
		$("#roleedit").text("编辑角色");
		var roleid=$(obj).parents("tr").first().find("input[type='checkbox'].checkboxes").val();
		var url = "system/sysrole/findRoleById";
		newMask();
		$.post(url,"roleid="+roleid,function callback(data){  
			delMask();
			var resp = eval("("+data+")"); 
			if(resp.success){
				var obj=resp.result[0];
				formload($("#addForm"),obj);
				$('#add-config').modal('show');
				var treeObj = $.fn.zTree.getZTreeObj("powersTree");
				treeObj.checkAllNodes(false);//取消勾选 全部节点
				var powerids=obj.powerids;
				if(powerids!=null&&powerids!=""){
					var poweridarry=powerids.split(",");
					for(var i=0;i<poweridarry.length;i++){
						var node=treeObj.getNodesByParam("TREEID", poweridarry[i])[0];
						if(!node.checked){
							treeObj.checkNode(node, true, false);
						}
					} 
				}
			}else{ 
				bootbox.alert(resp.msg);
			}
		});
	}
	//角色删除 
	function delRole(obj){
		bootbox.confirm("确认删除？",function(rs){
			if(rs){
				var roleid=$(obj).parents("tr").first().find("input[type='checkbox'].checkboxes").val();
				var url = "system/sysrole/sysroleDelete";
				newMask();
				$.post(url,"deleteData="+roleid,function callback(data){
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
<script type="text/javascript">
		<!--
		var setting = {
			check: {
				enable: true,
				chkboxType: {"Y":"p", "N":"ps"}
			},
			view: {
				dblClickExpand: false
			},
			data: {
				key: {
					name:"TREENAME"
				}
				,simpleData: {
					enable: true,
					idKey: "TREEID",
					pIdKey: "TREEPID"
				}
			},
			callback: {
				beforeClick: beforeClick,
				onCheck: onCheck
			}
		};

		var zNodes =${functionTree };

		function beforeClick(treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("powersTree");
			zTree.checkNode(treeNode, !treeNode.checked, true, true);
			return false;
		}
		
		function onCheck(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("powersTree"),
			nodes = zTree.getCheckedNodes(true),
			v = "";
			for (var i=0, l=nodes.length; i<l; i++) {
				v += nodes[i].TREEID + ",";
			}
			if (v.length > 0 ) v = v.substring(0, v.length-1);
			$("#addForm input[name='powerids']").val(v);
		}  
		$(document).ready(function(){
			$.fn.zTree.init($("#powersTree"), setting, zNodes);
		});
		//-->
</script>
 
