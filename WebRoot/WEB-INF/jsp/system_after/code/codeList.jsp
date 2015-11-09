<%@ page language="java" 	pageEncoding="utf-8"%>
<%@ taglib uri="/lystag" prefix="my" %>
<%@ include file="../../commons/jsplib.jsp"%>
<!-- Ztree树 -->
<link href="${ctx}/plugin/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${ctx}/plugin/zTree/js/jquery.ztree.core-3.5.min.js"></script>
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
				<div class="caption"><i class="fa fa-cogs"></i>字典树</div>
				<div class="tools">
					<a href="javascript:;" class="collapse"></a>
				</div>
			</div>
			
			<div class="portlet-body" style="padding: 10px;">
				<div class="btn-group">
					<button  id="addBtn" type="button" class="btn green"><i class="fa fa-plus"></i>新增 </button>
					<button  id="addCodebyFile" type="button" class="btn purple" style="margin:0px 5px 0px 5px;"><i class="fa fa-share"></i>导入字典 </button>
					<button  id="delBtn" type="button" class="btn btn-danger"> <i class="fa fa-trash-o"></i>删除</button>
				</div>
				<div>
					<ul id="codeTree" class="ztree"></ul>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-8">
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption"><i class="fa fa-cogs"></i>字典节点基本信息</div>
				<div class="tools">
					<a href="javascript:;" class="collapse"></a>
				</div>
			</div>
			<div class="portlet-body form">
				<form id="addorUPdFrom" action="#" class="form-horizontal">
					<div id="testdiv" class="form-body">
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-4">父节点名</label>
									<div class="col-md-8">
										<input name="ab_st_id" type="hidden" class="form-control" placeholder="">
										<input name="ab_st_parent" type="hidden" class="form-control" placeholder="">
										<input name="ab_st_sysmark" type="hidden" class="form-control" placeholder="">
										<input name="ab_st_parentName" type="text" disabled="disabled" class="form-control" placeholder="">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-4">字典标识<font style="color: red;">*</font></label>
									<div class="col-md-8">
										<input name="ab_st_mark" type="text" class="form-control" datatype="*1-32"  errormsg="字典标识至少一个字符，最大25个字符！" nullmsg="请输入字典标识！">
									</div>
								</div>
							</div>
						</div>
						<!--/row-->
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-4">码值</label>
									<div class="col-md-8">
										<input name="ab_st_value" type="text" class="form-control" placeholder="" >
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-4">码值名<font style="color: red;">*</font></label>
									<div class="col-md-8">
										<input name="ab_st_name" type="text" class="form-control" datatype="*1-50"  errormsg="码值名至少一个字符，最大50个字符！"  nullmsg="请输入码值名！">
									</div>
								</div>
							</div>
						</div>
						<!--/row-->
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-4">字典类型</label>
									<div class="col-md-8">
										<my:select name="ab_st_type" clazz="form-control" nameKey="ZDLXZD" initSelectedKey="1"/>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-4">是否打开</label>
									<div class="col-md-8">
										<my:select name="ab_nm_openorclose" clazz="form-control" nameKey="YESNO" initSelectedKey="0"/>
									</div>
								</div>
							</div>
						</div>
						<!--/row-->
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-4">序号<font style="color: red;">*</font></label>
									<div class="col-md-8">
										<input name="ab_nm_orderno" type="text" class="form-control" datatype="n1-50"  errormsg="序号至少一个数字，最大4个数字！"   nullmsg="请输入序号！">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-4">节点级号</label>
									<div class="col-md-8">
										<input name="ab_nm_jdnum" type="text" readonly="readonly" class="form-control" placeholder="">
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-4">是否用户控制<font style="color: red;">*</font></label>
									<div class="col-md-8">
										<my:select name="ab_st_isuserset" clazz="form-control" nameKey="YESNO" initSelectedKey="0"/>
									</div>
								</div>
							</div>
						</div>
						<!--/row-->
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-2">字典描述</label>
									<div class="col-md-10">
										<textarea name="ab_st_describe" class="form-control" rows="3"></textarea>
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
									<button id="updButton" type="button" class="btn green"><i class="fa fa-check"></i>修改 </button>
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

<div class="modal fade" id="filedr-config" tabindex="-1" role="basic"  data-backdrop="static"  aria-hidden="true">
	<div class="modal-dialog modal-wide">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">字典导入&emsp;&emsp;&emsp;&emsp;<a href="/files/files/system/字典导入模版.xls" style="font-size:13px">字典模版下载</a></h4>
			</div>
			<form id="addFormbyFile"  class="form-horizontal" encType="multipart/form-data" method="post">
				<div class="form-body">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4">父节点名</label>
								<div class="col-md-8">
									<input name="ab_st_parent" type="hidden" class="form-control" placeholder="">
									<input name="ab_st_parentName" type="text" readonly="readonly" class="form-control" placeholder="">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4">Excel选择<font style="color: red;">*</font></label>
								<div class="col-md-8">
									<input name="myfile" type="file" class="form-control" placeholder=""  datatype="*"  nullmsg="请选择字典模版文件！">
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
									<input name="ab_st_sysmark" type="hidden" class="form-control" placeholder="">
									<input name="ab_nm_jdnum" type="text" readonly="readonly" class="form-control" placeholder="">
								</div>
							</div>
						</div>
					</div>
					<!--/row-->
				</div>
			</form>
			<div id="drresultDIV" style="width:100%;height:100px;background-color: #FCF5F5; padding:20px;">
				导入结果
			</div>
			<div class="modal-footer">
				<button type="button" id="filedrBtn"  class="btn blue">导入</button>
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
	var zNodes =${codeTree };
	//节点单击事件
	function zTreeOnClick(event, treeId, treeNode) {
		findNodeInfo(treeNode.TREEID);
	}
	//查询节点信息到修改表单
	function findNodeInfo(treeid){
		$("#updButton").text("修改保存");
		var url = "system/code/findCodeById";
		newMask();
		$.post(url,"ab_st_id="+treeid,function callback(data){  
			delMask();
			var resp = eval("("+data+")"); 
			if(resp.success){
				var obj=resp.result[0];
				formload($("#addorUPdFrom"),obj);
				initValidForm($("#addorUPdFrom"));//清除表单验证样式
			}else{ 
				bootbox.alert(resp.msg);
			}
		});
	}

	
	//初始化
	jQuery(document).ready(function() {       
		//字典树初始化
		$.fn.zTree.init($("#codeTree"), setting, zNodes);
		
		//进入字典导入层
		$("#addCodebyFile").click(function(){
			var curtzTree = $.fn.zTree.getZTreeObj("codeTree"),selectNodes = curtzTree.getSelectedNodes();
			if (selectNodes.length == 0) {
				bootbox.alert("请先选择一个节点");
				return false;
			}else{
				$("#drresultDIV").text("导入结果");
				$("#addFormbyFile")[0].reset();
				$("#addFormbyFile input[name='ab_st_parent']").val(selectNodes[0].TREEID);
				$("#addFormbyFile input[name='ab_st_parentName']").val(selectNodes[0].TREENAME);
				$("#addFormbyFile input[name='ab_nm_jdnum']").val(selectNodes[0].ab_nm_jdnum+1);
				$("#addFormbyFile input[name='ab_st_sysmark']").val(selectNodes[0].ab_st_sysmark+','+selectNodes[0].TREEID);
				initValidForm($("#addFormbyFile"));//清除表单验证样式
				$('#filedr-config').modal('show');
			}
		});
		//导入字典-- 导入事件
		$("#filedrBtn").click(function(){
			checkFormValid($("#addFormbyFile"),function(){
				newMask();
				$("#addFormbyFile").form('submit',{  
				    url:"system/code/addCodeByfile",  
				    onSubmit: function(param){ 
				      // param.id="123"; 
				    }, 
				    success:function(data){
				    	delMask();
				    	var resp = eval("("+data+")"); 
				    	$("#drresultDIV").text(resp.msg);
				    	//每次导入完成后清空文件
						var file = $("input[name='myfile']") 
						file.after(file.clone().val("")); 
						file.remove(); 
						if(resp.success){
							bootbox.alert("数据导入成功，需要你重新加载页面。");
						}else{ 
							bootbox.alert(resp.msg);
						} 
				    }  
				});
			});
		
		});
		//进入新增字典层
		$("#addBtn").click(function(){
			var curtzTree = $.fn.zTree.getZTreeObj("codeTree"),selectNodes = curtzTree.getSelectedNodes();
			if (selectNodes.length == 0) {
				bootbox.alert("请先选择一个节点");
				return false;
			}else{
				$("#addorUPdFrom")[0].reset();
				$("#updButton").text("添加保存");
				$("#addorUPdFrom input[name='ab_st_id']").val("");
				$("#addorUPdFrom input[name='ab_st_parent']").val(selectNodes[0].TREEID);
				$("#addorUPdFrom input[name='ab_st_parentName']").val(selectNodes[0].TREENAME);
				$("#addorUPdFrom input[name='ab_nm_jdnum']").val(selectNodes[0].ab_nm_jdnum+1);
				$("#addorUPdFrom input[name='ab_st_sysmark']").val(selectNodes[0].ab_st_sysmark+','+selectNodes[0].TREEID);
				initValidForm($("#addorUPdFrom"));//清除表单验证样式
			}
		});
		//新增--修改字典的保存功能
		$("#updButton").click(function(){
			var curtzTree = $.fn.zTree.getZTreeObj("codeTree"), selectNodes= curtzTree.getSelectedNodes();
			if (selectNodes.length == 0) {
				bootbox.alert("节点未被选中！");
				return false;
			}
			checkFormValid($("#addorUPdFrom"),function(){
				var curtzTree = $.fn.zTree.getZTreeObj("codeTree"), selectNodes= curtzTree.getSelectedNodes();//此处需要重新获取 选中的节点 
				var url = "system/code/codeaddorupd";
				var params=$("#addorUPdFrom").serialize();
				var id=$("#addorUPdFrom input[name='ab_st_id']").val();
				newMask();
				$.post(url,params,function callback(data){  
					delMask();
					var resp = eval("("+data+")"); 
					if(resp.success){
						var obj=resp.result[0];
						//修改
						if(id!=null&&id!=""){
							selectNodes[0].TREENAME=obj.ab_st_name;
							curtzTree.updateNode(selectNodes[0]); 
						}
						//新增
						else{
							curtzTree.addNodes(selectNodes[0], {TREEID:obj.ab_st_id, TREEPID:obj.ab_st_parent, TREENAME:obj.ab_st_name,ab_nm_jdnum:obj.ab_nm_jdnum,ab_st_sysmark:obj.ab_st_sysmark});
						}
					}
					bootbox.alert(resp.msg);
				});
			});
		});
		
		
		//删除节点
		$("#delBtn").click(function(){
		   var zTree = $.fn.zTree.getZTreeObj("codeTree"), nodes = zTree.getSelectedNodes();
			if (nodes.length == 0) {
				bootbox.alert("请先选择一个节点");
				return false;
			}else{ 
				bootbox.confirm("确认删除字典：" + nodes[0].TREENAME + " 吗？",function(rs){
					if(rs){
						var url = "system/code/codeDelete";
						var str = getAllChildrenNodes(nodes[0],nodes[0].TREEID);//获得本节点ID和所有子节点ID，不支持异步缓存数据 
						newMask();
						$.post(url,"codeid="+str+"&parentid="+nodes[0].TREEPID,function callback(data){  
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
