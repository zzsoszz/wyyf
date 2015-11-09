<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="/lystag" prefix="my" %>
<!-- Ztree树 -->
<link href="http://${header['host']}${pageContext.request.contextPath}/plugin/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="http://${header['host']}${pageContext.request.contextPath}/plugin/zTree/js/jquery.ztree.core-3.5.min.js"></script>
<style>
<!--
ul.ztree {margin-top: 0px;width:95%;  height:460px; overflow-x:auto;}
-->
</style>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
			<%@ include file="../common/crumbsCom.jsp"%>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<div class="row">
	<div class="col-md-4">
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption"><i class="fa fa-cogs"></i>权限树</div>
				<div class="tools">
					<a href="javascript:;" class="collapse"></a>
				</div>
			</div>
			
			<div class="portlet-body" style="padding: 10px;">
				<div class="btn-group">
					<button  id="addBtn" type="button" class="btn green"><i class="fa fa-plus"></i>新增 </button>
					<button  id="delBtn" type="button" class="btn btn-danger" style="margin:0px 5px 0px 5px;"><i class='fa fa-trash-o'></i>删除</button>
				</div>
				<div>
					<ul id="sysfuntionTree" class="ztree"></ul>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-8">
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption"><i class="fa fa-cogs"></i>权限节点基本信息</div>
				<div class="tools">
					<a href="javascript:;" class="collapse"></a>
				</div>
			</div>
			<div class="portlet-body form">
				<form id="addorUPdFrom" action="#" class="form-horizontal">
					<div class="form-body">
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-4">父节点名</label>
									<div class="col-md-8">
										<input name="aa_st_id" type="hidden" class="form-control" placeholder="">
										<input name="aa_st_parent" type="hidden" class="form-control" placeholder="">
										<input name="aa_st_sysmark" type="hidden" class="form-control" placeholder="">
										<input name="aa_st_parentName" type="text" disabled="disabled" class="form-control" placeholder="">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-4">权限标识<font color="red">*</font></label>
									<div class="col-md-8">
										<input name="aa_st_mark" type="text" class="form-control" placeholder="" datatype="*"  nullmsg="请输入权限标识！">
									</div>
								</div>
							</div>
						</div>
						<!--/row-->
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-4">权限名字<font color="red">*</font></label>
									<div class="col-md-8">
										<input name="aa_st_title" type="text" class="form-control" placeholder="" datatype="*"  nullmsg="请输入权限名字！">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-4">权限类型</label>
									<div class="col-md-8">
										<my:select name="aa_st_type" clazz="form-control" nameKey="QXLXZD" initSelectedKey="4"/>
									</div>
								</div>
							</div>
						</div>
						<!--/row-->
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-4">相关URL</label>
									<div class="col-md-8">
										<input name="aa_st_url" type="text" class="form-control" placeholder="">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-4">类标识<font color="red">*</font></label>
									<div class="col-md-8">
										<input name="aa_st_classUrl" type="text" class="form-control" placeholder="" datatype="*"  nullmsg="请输入类标识！">
									</div>
								</div>
							</div>
						</div>
						<!--/row-->
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-4">是否生效</label>
									<div class="col-md-8">
										<my:select name="aa_st_active" clazz="form-control" nameKey="YESNO" initSelectedKey="0"/>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-4">是否打开</label>
									<div class="col-md-8">
										<my:select name="aa_nm_openorclose" clazz="form-control" nameKey="YESNO" initSelectedKey="0"/>
									</div>
								</div>
							</div>
						</div>
						<!--/row-->
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-4">序号<font color="red">*</font></label>
									<div class="col-md-8">
										<input name="aa_nm_orderno" type="text" class="form-control" placeholder="" datatype="*"  nullmsg="请输入序号！">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-4">图标</label>
									<div class="col-md-3">
										<input name="aa_st_image" type="text" class="form-control" placeholder="">
									</div>
									<div id="imagesdiv" class="col-md-5">
										<i class="fa fa-home"></i>
										<button id="imageSelect" type="button" class="btn btn-warning">选择 <i class=""></i></button>
									</div>
								</div>
							</div>
						</div>
						<!--/row-->
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-4">节点级号</label>
									<div class="col-md-8">
										<input name="aa_nm_jdnum" type="text" readonly="readonly" class="form-control" placeholder="">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-4">是否日志</label>
									<div class="col-md-8">
										<my:select name="aa_st_islog" clazz="form-control" nameKey="YESNO" initSelectedKey="0"/>
									</div>
								</div>
							</div>
						</div>
						<!--/row-->
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-2">权限描述</label>
									<div class="col-md-10">
										<textarea name="aa_st_describe" class="form-control" rows="3"></textarea>
									</div>
								</div>
							</div>
						</div>
						<!--/row-->
					</div>
					<div class="form-actions fluid">
						<div class="row">
							<div class="col-md-6">
								<div class="col-md-offset-3 col-md-9">
									<button id="updButton" type="button" class="btn green"> <i class="fa fa-check"></i>修改</button>
								</div>
							</div>
							<div class="col-md-6"></div>
						</div>
					</div>
				</form>

			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="images-config" tabindex="1" role="basic"  data-backdrop="static"  aria-hidden="true">
	<div class="modal-dialog modal-wide">
		<div class="modal-content">
			<%@ include file="imageSelect.jsp"%>
			<div class="modal-footer">
				<button type="button" class="btn default" data-dismiss="modal">取消</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>

<script language="javascript">
	var setting = {
		data: {
			key: {
				name:"TREENAME"
			}
			,simpleData: {
				enable: true,
				idKey: "TREEID",
				pIdKey: "TREEPID"
			}
		}
		,callback: {
			onClick: zTreeOnClick
		}
		
	};
	var zNodes =${functionTree };

	//节点单击事件
	function zTreeOnClick(event, treeId, treeNode) {
		findNodeInfo(treeNode.TREEID);
	}
	//查询节点信息到修改表单
	function findNodeInfo(treeid){
		$("#updButton").text("修改保存");
		var url = "system/sysfunction/sysfunctionFind";
		newMask();
		$.post(url,"aa_st_id="+treeid,function callback(data){  
			delMask();
			var resp = eval("("+data+")"); 
			if(resp.success){
				var obj=resp.result[0];
				formload($("#addorUPdFrom"),obj);
				$("#addorUPdFrom #imagesdiv i").attr("class",obj.aa_st_image);
				initValidForm($("#addorUPdFrom"));//清除表单验证样式
			}else{ 
				bootbox.alert(resp.msg);
			}
		});
	}
	//初始化
	jQuery(document).ready(function() {       
		//权限树初始化
		$.fn.zTree.init($("#sysfuntionTree"), setting, zNodes);
		//弹出图片选择层
		$("#imageSelect").click(function(){
			$('#images-config').modal('show');
		});

		//进入新增权限层
		$("#addBtn").click(function(){
			var curtzTree = $.fn.zTree.getZTreeObj("sysfuntionTree"),selectNodes = curtzTree.getSelectedNodes();
			if (selectNodes.length == 0) {
				bootbox.alert("请先选择一个节点");
				return false;
			}else{
				$("#addorUPdFrom")[0].reset();
				$("#updButton").text("添加保存");
				$("#addorUPdFrom input[name='aa_st_id']").val("");
				$("#addorUPdFrom input[name='aa_st_parent']").val(selectNodes[0].TREEID);
				$("#addorUPdFrom input[name='aa_st_parentName']").val(selectNodes[0].TREENAME);
				$("#addorUPdFrom input[name='aa_nm_jdnum']").val(selectNodes[0].aa_nm_jdnum+1);
				$("#addorUPdFrom input[name='aa_st_sysmark']").val(selectNodes[0].aa_st_sysmark+','+selectNodes[0].TREEID);
				initValidForm($("#addorUPdFrom"));//清除表单验证样式
			}
		});
		//新增--修改权限的保存功能
		$("#updButton").click(function(){
			var curtzTree = $.fn.zTree.getZTreeObj("sysfuntionTree"), selectNodes= curtzTree.getSelectedNodes();
			if (selectNodes.length == 0) {
				bootbox.alert("节点未被选中！");
				return false;
			}
			checkFormValid($("#addorUPdFrom"),function(){
				var curtzTree = $.fn.zTree.getZTreeObj("sysfuntionTree"), selectNodes= curtzTree.getSelectedNodes();//此处需要重新获取 选中的节点 
				var url = "system/sysfunction/sysfunctionAddorupd";
				var params=$("#addorUPdFrom").serialize();
				var id=$("#addorUPdFrom input[name='aa_st_id']").val();
				newMask();
				$.post(url,params,function callback(data){  
					delMask();
					var resp = eval("("+data+")"); 
					if(resp.success){
						var obj=resp.result[0];
						//修改
						if(id!=null&&id!=""){
							selectNodes[0].TREENAME=obj.aa_st_title;
							curtzTree.updateNode(selectNodes[0]); 
						}
						//新增
						else{
							curtzTree.addNodes(selectNodes[0], {TREEID:obj.aa_st_id, TREEPID:obj.aa_st_parent, TREENAME:obj.aa_st_title,aa_nm_jdnum:obj.aa_nm_jdnum,aa_st_sysmark:obj.aa_st_sysmark});
						}
					}
					bootbox.alert(resp.msg);
				});
			});
			
		});
		//删除节点
		$("#delBtn").click(function(){
		   var zTree = $.fn.zTree.getZTreeObj("sysfuntionTree"), nodes = zTree.getSelectedNodes();
			if (nodes.length == 0) {
				bootbox.alert("请先选择一个节点");
				return false;
			}else{ 
				bootbox.confirm("确认删除字典：" + nodes[0].TREENAME + " 吗？",function(rs){
					if(rs){
						var url = "system/sysfunction/sysfunctionDelete";
						var str = getAllChildrenNodes(nodes[0],nodes[0].TREEID);//获得本节点ID和所有子节点ID，不支持异步缓存数据 
						newMask();
						$.post(url,"functionid="+str+"&parentid="+nodes[0].TREEPID,function callback(data){  
							delMask();
							var resp = eval("("+data+")"); 
							if(resp.success){
								var parentnode=nodes[0].getParentNode();
								findNodeInfo(parentnode.TREEID);
								zTree.selectNode(parentnode,false);
								zTree.removeNode(nodes[0]);
							}
							bootbox.alert(resp.msg);
						});
					}
				});
			}
		});
		
	});
</script>
<script language="javascript">
	
	jQuery(document).ready(function() {
		//图片选择的点击事件     
		$("#tab_1_1 div.fa-item").click(function(){
			var classvalue=$(this).find("i").attr("class");
			$("#addorUPdFrom input[name='aa_st_image']").val(classvalue);
			$("#addorUPdFrom #imagesdiv i").attr("class",classvalue);
			$('#images-config').modal('hide');
		});
	});
</script>
