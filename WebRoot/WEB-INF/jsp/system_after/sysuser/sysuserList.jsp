<%@ page language="java" 	pageEncoding="utf-8"%>
<%@ include file="../../commons/jsplib.jsp"%>
<%@ taglib uri="/lystag" prefix="my" %>


<link href="${ctx}/plugin/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${ctx}/plugin/zTree/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${ctx}/plugin/zTree/js/jquery.ztree.excheck-3.5.min.js"></script>
<style>
<!--
ul.ztree {margin-top: 0px;margin-bottom: 5px;border: 1px solid #617775;background: #f0f6e4;width:95%;height:300px; overflow-x:auto;}
-->
</style>
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
		<div class="caption"><i class="fa fa-globe"></i>用户列表</div>
		<div class="actions">
			<a id="addUserBtn" href="javascript:void(0);" class="btn blue"><i class="fa fa-plus"></i>新增用户</a>
			<a id="plscydtBtn" href="javascript:void(0);" class="btn yellow"><i class="fa fa-arrow-down"></i>强制下线所有用户</a>
		</div>
	</div>
	<div class="portlet-body">
		<div title="查询条件">
			<form id="queryForm">
				昵称：<input type="text" name="ae_st_nickName" class="form-control" style="width: 150px;display: inline;">
				帐号：<input type="text" name="username" class="form-control" style="width: 150px;display: inline;">
<!-- 				类型：<my:select name="ae_st_userkind" clazz="form-control" isAddDefaltOption="true" defaltOptionKey="" defaltOptionValue="--新选择--" nameKey="YHLXZD" style="width: 150px; display:inline;" /> -->
				角色类型：<my:select  name="roletype" clazz="form-control" isJXdata="true" data="${roleSelect}"  defaltOptionKey="" defaltOptionValue="-请选择-" isAddDefaltOption="true" style="width: 150px; display:inline;" />
				上线状态：<select name="isonline" class="form-control" style="width: 100px; display:inline;">
					<option value="">--新选择--</option>
					<option value="在线">在线</option>
					<option value="离线">离线</option>
				</select>
				<button type="button" id="queryBtn"  class="btn blue">查询</button>
				<button type="button" onclick="javascript:reset();" class="btn default">重置</button> 
			</form>
		</div>
		<div  id="tableList" >
		
		</div>
	</div>
	<!-- END EXAMPLE TABLE PORTLET-->
</div>
<div class="modal fade" id="add-config" tabindex="-1" role="basic"  data-backdrop="static"  aria-hidden="true">
	<div class="modal-dialog modal-wide">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title" id="usertile"></h4>
			</div>
			<form id="addForm" action="#" class="form-horizontal">
				<div class="form-body">
					<div class="row">
						<div class="col-md-6">
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-4">帐号<font style="color: red;">*</font></label>
									<div class="col-md-8">
										<input name="ae_st_id" type="hidden" value="">
										<input name="username" type="text" class="form-control" placeholder="" datatype="*3-25"  nullmsg="请输入帐号!"  errormsg="帐号至少3个字符，最多25个字符!">
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label id="changeMMText" class="control-label col-md-4">密码</label>
									<div class="col-md-8">
										<input name="password" type="password" class="form-control" datatype="password"  nullmsg="请输入密码！">
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-4">用户类型</label>
									<div class="col-md-8">
										<my:select name="ae_st_userkind" clazz="form-control" nameKey="YHLXZD" />
									</div>
								</div>
							</div>
							<div class="col-md-12">
								 <div class="form-group">
									<label class="control-label col-md-4">昵称<font style="color: red;">*</font></label>
									<div class="col-md-8">
										<input name="ae_st_nickName" type="text" class="form-control"   datatype="*1-10"  nullmsg="请输入昵称！"   errormsg="帐号至少1个字符，最多10个字符!">
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6"> 
							<label class="control-label col-md-3">角色选择</label>
							<div class="col-md-9">
								 <input name="roleids" type="hidden">
								 <div id="rolemenudiv">
									<ul id="rolesTree" class="ztree"></ul>
								</div>
							</div>
						</div>
					</div> 
					<!--/row-->
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label class="control-label col-md-2">用户描述</label>
								<div class="col-md-10">
									<textarea name="ae_st_description" class="form-control" rows="3"></textarea>
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
		$("#addUserBtn").click(function(){
			initValidForm($("#addForm"));//清除表单验证样式
			$("#addForm")[0].reset();
			$("#changeMMText").html('密码<font style="color: red;">*</font>');
			$("#addForm input[name='password']").removeAttr("ignore");
			$("#addForm input[name='ae_st_id']").val("");//清空隐藏的文本框ID值
			$("#addForm input[name='roleids']").val("");//清空隐藏的文本框权限值
			$.fn.zTree.getZTreeObj("rolesTree").checkAllNodes(false);//取消勾选 全部节点
			$("#usertile").text("新增用户");
			$('#add-config').modal('show');
		});
		//新增-修改权限层的保存功能
		$("#saveBtn").click(function(){
			checkFormValid($("#addForm"),function(){
				var url = "system/sysuser/sysuserAdd";
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
		//强制下线所有用户
		$("#plscydtBtn").click(function(){
			bootbox.confirm("确认强制下线所有用户？",function(rs){
				if(rs){
					var url = "system/sysuser/sysuserdownline";
					newMask();
					$.post(url,"tpye=2",function callback(data){  
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
		});
	});
	//查询函数--替换主题内容的函数
	function loadotherPage(params) {
		var param=$("#queryForm").serialize();//条件参数
		var url="system/sysuser/sysuserList";
		
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
	//修改用户--进入
	function editUser(obj){
		initValidForm($("#addForm"));//清除表单验证样式
		$("#addForm")[0].reset();
		$("#changeMMText").html("新密码");
		$("#addForm input[name='password']").attr("ignore","ignore");
		$("#usertile").text("编辑用户");
		var userid=$(obj).parents("tr").first().find("input[type='checkbox'].checkboxes").val();
		var url = "system/sysuser/findUserById";
		newMask();
		$.post(url,"userid="+userid,function callback(data){  
			delMask();
			var resp = eval("("+data+")"); 
			if(resp.success){
				var obj=resp.result[0];
				formload($("#addForm"),obj);
				var treeObj = $.fn.zTree.getZTreeObj("rolesTree");
				treeObj.checkAllNodes(false);//取消勾选 全部节点
				$('#add-config').modal('show');
				var roleids=obj.roleids;
				if(roleids!=null&&roleids!=""){
					var roleidarry=roleids.split(",");
					for(var i=0;i<roleidarry.length;i++){
						var node=treeObj.getNodesByParam("TREEID", roleidarry[i])[0];
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
	//删除用户
	function delUser(obj){
		bootbox.confirm("确认是否删除？",function(rs){
			if(rs){
				var roleid=$(obj).parents("tr").first().find("input[type='checkbox'].checkboxes").val();
				var url = "system/sysuser/sysuserDelete";
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
	//强制下线用户
	function downline(obj){
		bootbox.confirm("确认强制下线？",function(rs){
			if(rs){
				var roleid=$(obj).parents("tr").first().find("input[type='checkbox'].checkboxes").val();
				var url = "system/sysuser/sysuserdownline";
				newMask();
				$.post(url,"id="+roleid,function callback(data){  
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

		var zNodes =${roleTree };

		function beforeClick(treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("rolesTree");
			zTree.checkNode(treeNode, !treeNode.checked, true, true);
			return false;
		}
		
		function onCheck(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("rolesTree"),
			nodes = zTree.getCheckedNodes(true),
			v = "";
			for (var i=0, l=nodes.length; i<l; i++) {
				v += nodes[i].TREEID + ",";
			}
			if (v.length > 0 ) v = v.substring(0, v.length-1);
			$("#addForm input[name='roleids']").val(v);
		}  
		$(document).ready(function(){
			$.fn.zTree.init($("#rolesTree"), setting, zNodes);
		});
		//-->
</script>