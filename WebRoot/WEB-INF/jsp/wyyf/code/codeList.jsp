<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
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
		<%@ include file="../../system/common/crumbsCom.jsp"%>
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
					<button  id="addBtn" type="button" class="btn green">新增<i class="fa fa-share"></i></button>
					<button  id="delBtn" type="button" class="btn btn-danger">删除 <i class="fa fa-times"></i></button>
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
										<div style="display:none;">
											
											<my:select name="ab_st_type" clazz="form-control" nameKey="ZDLXZD" initSelectedKey="2" initDidableKey="0,1,3"/>
											<input name="ab_nm_jdnum" type="text" readonly="readonly" class="form-control" placeholder="">
										</div>
										<input name="ab_st_parentName" type="text" disabled="disabled" class="form-control" placeholder="">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-4">标识</label>
									<div class="col-md-8">
										<input name="ab_st_mark" type="text" class="form-control" placeholder="" readonly="readonly" >
									</div>
								</div>
							</div>
						</div>
						<!--/row-->
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-4">码值<font color="red">*</font></label>
									<div class="col-md-8">
										<input name="ab_st_value" type="text" class="form-control" placeholder="" datatype="*"  nullmsg="请输入码值！" maxlength="50">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-4">码值名<font color="red">*</font></label>
									<div class="col-md-8">
										<input name="ab_st_name" type="text" class="form-control" placeholder="" datatype="*"  nullmsg="请输入名字！" maxlength="50">
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
										<input name="ab_nm_orderno" type="text" class="form-control" placeholder=""  datatype="/^\d{1,4}$/"  nullmsg="请输入序号！" errormsg="序号只能为1-4位非负整数！">
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
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-2">字典描述</label>
									<div class="col-md-10">
										<textarea name="ab_st_describe" class="form-control" rows="3"  maxlength="250"></textarea>
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
									<button id="updButton" type="button" class="btn green">修改保存 <i class="fa fa-check"></i></button>
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
		var url = "wyyf/code/findCodeById";
		$.post(url,"ab_st_id="+treeid,function callback(data){  
			var resp = eval("("+data+")"); 
			if(resp.success){
				var obj=resp.result[0];
				formload($("#addorUPdFrom"),obj);
				$("#addorUPdFrom").find("span.Validform_checktip").empty().removeClass("Validform_wrong Validform_right");//移除表单中验证信息
				$("#addorUPdFrom").find("select,input,textarea").removeClass("Validform_error");//移除表单中验证信息
			}else{ 
				bootbox.alert(resp.msg);
			}
		});
	}

	
	//初始化
	jQuery(document).ready(function() {       
		//字典树初始化
		$.fn.zTree.init($("#codeTree"), setting, zNodes);
		//进入新增字典层
		$("#addBtn").click(function(){
			var curtzTree = $.fn.zTree.getZTreeObj("codeTree"),selectNodes = curtzTree.getSelectedNodes();
			 if(selectNodes[0].ab_nm_jdnum!='3'){
				bootbox.alert("不能在该节点下新增数据！");
				return false;
			}
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
				$("#addorUPdFrom").find("span.Validform_checktip").empty().removeClass("Validform_wrong Validform_right");//移除表单中验证信息
				$("#addorUPdFrom").find("select,input,textarea").removeClass("Validform_error");//移除表单中验证信息
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
				var url = "wyyf/code/codeaddorupd";
				var params=$("#addorUPdFrom").serialize();
				var id=$("#addorUPdFrom input[name='ab_st_id']").val();
				$.post(url,params,function callback(data){  
					var resp = eval("("+data+")"); 
					if(resp.success){
						var obj=resp.result[0];
						//修改
						if(id!=null&&id!=""){
							selectNodes[0].TREENAME=obj.ab_st_name;
							curtzTree.updateNode(selectNodes[0]); 
							findNodeInfo(selectNodes[0].TREEID);
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
		   if(nodes[0].ab_nm_jdnum=='3'){
				bootbox.alert("不能删除该节点");
				return false;
			}
			if (nodes.length == 0) {
				bootbox.alert("请先选择一个节点");
				return false;
			}else{ 
				bootbox.confirm("确认删除字典：" + nodes[0].TREENAME + " 吗？",function(rs){
					if(rs){
						var url = "wyyf/code/codeDelete";
						var str = getAllChildrenNodes(nodes[0],nodes[0].TREEID);//获得本节点ID和所有子节点ID，不支持异步缓存数据 
						$.post(url,"codeid="+str+"&parentid="+nodes[0].TREEPID,function callback(data){  
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
