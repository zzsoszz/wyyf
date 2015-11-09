<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/lystag" prefix="my" %>
<!-- Ztree树 -->
<link href="http://${header['host']}${pageContext.request.contextPath}/plugin/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="http://${header['host']}${pageContext.request.contextPath}/plugin/zTree/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="http://${header['host']}${pageContext.request.contextPath}/plugin/zTree/js/jquery.ztree.excheck-3.5.min.js"></script>
<style>
	ul.ztree {margin-top: 0px;border: 1px solid #617775;background: #f0f6e4;width:95%;height:250px; overflow-x:auto;}
</style>

<div class="modal-header">
	<h4  class="modal-title"><span id="titleText">维护个人信息</span><span class="zhus">注：带【<span style="color: red;">*</span>】为必填项！</span></h4>
</div>
<form id="addForm" action="#" class="form-horizontal" encType="multipart/form-data" method="POST">
	<div style="display: none;">
		<input name="ae_st_lockstate" >
	</div>
	<div class="form-body">
		<div class="tabbable tabbable-custom boxless">
			<ul class="nav nav-tabs">
				<li class="active"><a href="#tab_1" data-toggle="tab">基本信息</a></li>
				<li><a href="#tab_2" data-toggle="tab">收支信息</a></li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane active" id="tab_1">
					<div class="portlet box green">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-reorder"></i>用户信息</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
							</div>
						</div>
						<div class="portlet-body">
							<div class="row">
								<div class="col-md-6">
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label col-md-4">帐号<font style="color: red;">*</font></label>
											<div class="col-md-8">
												<input name="ae_st_id" type="hidden">
												<input name="username" type="text" class="form-control" placeholder="" datatype="s6-25"  nullmsg="请输入6到25位字符！">
											</div>
										</div>
									</div>
									<!-- <div class="col-md-12"> 
										<div class="form-group">
											<label id="changeMMText" class="control-label col-md-4">密码</label>
											<div class="col-md-8">
												<input name="password" type="password" class="form-control" datatype="password"  nullmsg="请输入密码！">
											</div>
										</div>
									</div> -->
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label col-md-4">姓名<font style="color: red;">*</font></label>
											<div class="col-md-8">
												<input name="ae_st_name" class="form-control"  datatype="s2-25"  errormsg="请输入2到25位字符！" />
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label col-md-4">年龄</label>
											<div class="col-md-8">
												<input name="ae_nm_age" class="form-control"  datatype="n0-3"  errormsg="请输入正确年龄！" />
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label col-md-4">昵称</label>
											<div class="col-md-8">
												<input name="ae_st_nickName" class="form-control"  datatype="con1-25"  errormsg="昵称最长25个字符！" />
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label col-md-4">性别</label>
											<div class="col-md-8">
												<my:select name="ae_st_sex" clazz="form-control" nameKey="SEX" />
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label">用户头像</label>
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group">
											<div id="preview">
											    <img id="imghead" width="277" height="150" border="0" src='images/logo/default_img.jpg'><!--无预览时的默认图像，自己弄一个-->
											</div>
											<span class="btn blue fileinput-button">
											    <i class="fa fa-plus"></i>
											    <span>选择图片</span>
											    <input id="myfile" name="myfile"  type="file"  style="height: auto;top: 0px;right: 0px;margin: 0px;opacity: 0;font-size: 23px; direction: ltr;cursor: pointer; width: 253px;  margin-top: -34px;" onchange="previewImage(this)"/>
											</span>
										</div>
									</div>
								</div>
							</div> 
							<!--/row-->
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<input name="groupids3" type="hidden">
										<label class="control-label col-md-2"> 所属地区：</label>
									  	<div class="col-md-10">
										  	<span class="" style="margin-left: 0px;width: 20%;"  >
										        <button class="btn blue dropdown-toggle"  type="button" onclick="showMenu3();" style="padding: 6px 12px;">
										           	请选择 
										            <i class="fa fa-angle-down"></i>
										        </button>
										    </span>
										    <div id="menuContent3" style="display:none; position: absolute;z-index: 1999;width:66%;">
												<ul id="groupsTree3" class="ztree" style="border: 1px solid #aaa;border-top:none;background: #f5f5f5;" ></ul>
											</div>
											<input name="groupids3Name" class="form-control" type="text"  readonly onclick="showMenu3();" style="display: inline;width: 76%;"  datatype="*" nullmsg="请选择！"/>
										    
										</div>
									</div>
								</div>
							</div> 
							<!--/row-->
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label class="control-label col-md-2">用户详细地址</label>
										<div class="col-md-10">
											<input name="ae_st_address" class="form-control"  datatype="con0-50"  errormsg="最长50个字符！" />
										</div>
									</div>
								</div>
							</div> 
							<!--/row-->
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label col-md-4">选择用户类型</label>
										<div class="col-md-8" id="typeCZ">
											<my:select name="ae_st_type" clazz="form-control" nameKey="USERTYPE" />
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label col-md-4">电话</label>
										<div class="col-md-8">
											<input name="ae_st_tell" class="form-control"  datatype="con3-15"  errormsg="请输入11位以内有效电话号码" />
										</div>
									</div>
								</div>
							</div>
							<!-- 商家附加内容 -->
							<div id="sjadd" style="display:none;background-color:#E1EED4; padding:10px 20px 0px 0px;">
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<input name="bb_st_id" type="hidden"/>
											<label class="control-label col-md-2">面积介绍</label>
											<div class="col-md-10">
													<input name="bb_st_area" class="form-control" datatype="con0-25"/>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-4">电话1</label>
											<div class="col-md-8">
													<input name="bb_st_phone1" class="form-control" datatype="con3-15"  errormsg="请输入15位以内有效电话号码" />
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-4">电话2</label>
											<div class="col-md-8">
													<input name="bb_st_phone2" class="form-control" datatype="con3-15"  errormsg="请输入15位以内有效电话号码" />
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label col-md-2">店铺介绍</label>
											<div class="col-md-10">
												<textarea name="bb_st_shopinfo" class="form-control" rows="3"  datatype="con0-500"  errormsg="设置内容最长500个字符！"></textarea>
											</div>
										</div>
									</div>
								</div> 
							</div>
							<!-- /row -->
							<!-- 师傅附加内容 -->
							<div id="sfadd" style="display:none;background-color:#F2D3D3; padding:10px 20px 0px 0px;">
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<div style="display: none;">
												<my:select name="type4" clazz="form-control" nameKey="USERTYPE_4"/>
												<my:select name="type5" clazz="form-control" nameKey="USERTYPE_5"/>
												<my:select name="type6" clazz="form-control" nameKey="USERTYPE_6"/>
											</div>
											<input name="ba_st_id" type="hidden"/>
											<label class="control-label col-md-4">职业类别</label>
											<div class="col-md-8">
												<select name="ba_st_type" class="form-control">
												</select>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label col-md-2">收费报价<font style="color: red;">*</font></label>
											<div class="col-md-10">
												<textarea name="ba_st_price" class="form-control" rows="3"  datatype="con0-120"  errormsg="设置内容最长120个字符！"></textarea>
											</div>
										</div>
									</div>
								</div> 
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label col-md-2">团队介绍</label>
											<div class="col-md-10">
												<textarea name="ba_st_team_intro" class="form-control" rows="3"  datatype="con0-250"  errormsg="设置内容最长250个字符！"></textarea>
											</div>
										</div>
									</div>
								</div> 
							</div>
							<!-- /row -->
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label class="control-label col-md-2">简介</label>
										<div class="col-md-10">
											<textarea name="ae_st_intro" class="form-control" rows="3"  datatype="con0-250"  errormsg="设置内容最长250个字符！"></textarea>
										</div>
									</div>
								</div>
							</div> 
							<!--/row-->
						</div>
					</div>
					<!-- /portlet -->
				</div>
				<div class="tab-pane" id="tab_2">
					 <div class="portlet box purple">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-reorder"></i>收支信息</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
							</div>
						</div>
						<div class="portlet-body">
							<div  id="tablemoneylogList" ></div>
						</div>
					</div>
					<!-- /portlet -->
				</div>
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<button type="button" id="saveBtn"  mykzattr="1" class="btn blue">保存</button>
		<button type="button" class="btn default" data-dismiss="modal">取消</button>
	</div>
</form>

<!-- 查询条件中的组织树zTree -->
<script type="text/javascript">

	var zNodes =${groupTree };
	var setting3 = {
		check: {
			enable: true,
			chkStyle:"radio",
			radioType:"all" ,
			chkboxType: {"Y":"p", "N":"s"}
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
			beforeClick: beforeClick3,
			onCheck: onCheck3
		}
	};

	function showMenu3() {
		var peojectObj = $("#addForm input[name='groupids3Name']");
		var peojectOffset = peojectObj.offset();
		var  y=peojectObj.position().top;//$("#addForm input[name='groupids3Name']")[0].scrollTop;
		var  x=peojectObj.position().left;//$("#addForm input[name='groupids3Name']")[0].scrollLeft;
		$("#menuContent3").css({left:x + "px", top:y + peojectObj.outerHeight() + "px"}).slideDown("fast");
	
		$("body").bind("mousedown", onBodyDown3);
	}
	function hideMenu3() {
		$("#menuContent3").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown3);
	}
	function onBodyDown3(event) {
		if (!( event.target.name == "groupids3Name" || event.target.id == "menuContent3" || $(event.target).parents("#menuContent3").length>0)) {
			hideMenu3();
		}
	}
	function beforeClick3(treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("groupsTree3");
		zTree.checkNode(treeNode, !treeNode.checked, true, true);
		return false;
	}
	function onCheck3(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("groupsTree3"),
		nodes = zTree.getCheckedNodes(true),
		v = "",
		name="";
		if(nodes.length>0){
			v=nodes[0].TREEID+",";
			name=nodes[0].TREENAME;
			var pNode = nodes[0].getParentNode();
			while(!!pNode ) { 
				v+= pNode.TREEID+",";
				pNode = pNode.getParentNode();
			}
			if (v.length > 0 ) v = v.substring(0, v.length-1);
		}
		
		
		/*for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i].TREEID + ",";
			name += nodes[i].TREENAME + ",";
		}
		if (v.length > 0 ) v = v.substring(0, v.length-1);
		if (name.length > 0 ) name = name.substring(0, name.length-1);*/
		$("#addForm input[name='groupids3']").val(v);
		$("#addForm input[name='groupids3Name']").val(name);
	}  
	$(document).ready(function(){
		$.fn.zTree.init($("#groupsTree3"), setting3, zNodes);
	});
</script>
<script>
	$(function(){
// 		debugger;
		//初始化数据
		var data=${jsonData };
		getinfostatus($("#typeCZ"));//进入查看类型
		$("#addForm select[name='ae_st_type']").attr("readonly","readonly");//不能修改类型
		$("#addForm input[name='username']").attr("readonly","readonly");//不能修改帐号
		var resp = data; 
		if(resp.success){
			var obj=resp.result[0];
			formload($("#addForm"),obj);
			$.fn.zTree.init($("#groupsTree3"), setting3, zNodes);//初始化树
			if(obj.groupsid){
				var treeObjx = $.fn.zTree.getZTreeObj("groupsTree3");
				//给自己本身所在的组加上 选择
				var node=treeObjx.getNodesByParam("TREEID", obj.groupsid)[0];
				treeObjx.checkNode(node, true, false ,true);
			}
			if(obj.ag_st_url == null || obj.ag_st_url == ""){
				document.getElementById('imghead').src="images/logo/default_img.jpg";//初始化图片选择
			}else{
				document.getElementById('imghead').src=obj.ag_st_url;
			}
			var type = obj.ae_st_type;
			if(type=='3'){
				$("#sjadd").show();
				$("#sfadd").hide();
			}else if(type=='4'||type=='5'||type=='6'){
				$("#sfadd").show();
				$("#sjadd").hide();
			}else{
				$("#sfadd").hide();
				$("#sjadd").hide();
			}
			loadotherPage_money("&pageIndex=1&pageSize=10");//加载交易记录信息
		}else{ 
			bootbox.alert(resp.msg);
		}
		//新增-修改权限层的保存功能
		$("#saveBtn").click(function(){
			checkFormValid($("#addForm"),function(){
				$("#addForm").form('submit',{
					url:"wyyf/info/userupd",
				    onSubmit: function(param){ 
				    }, 
				    success:function(data){ 
				    	var resp = eval("("+data+")"); 
						if(resp.success){
							bootbox.alert(resp.msg);
						}else{ 
							bootbox.alert(resp.msg);
						}
				    }
				 });
			});
		});
		//根据用户类型 onchange事件   z这个他做额时候 就是 这么 判断，的  ，对应了 新的 dvi。 
		$("#addForm select[name='ae_st_type']").change(function(){
			var myval=$(this).val();
			if(myval=='3'){
				$("#sjadd").show();
				$("#sfadd").hide();
			}else if(myval=='4'||myval=='5'||myval=='6'){
				$("#addForm select[name='ba_st_type']").html($("#addForm select[name='type"+myval+"']").html());
				$("#sfadd").show();
				$("#sjadd").hide();
			}else{
				$("#sfadd").hide();
				$("#sjadd").hide();
			}
		});
	});
	//查询函数--查询某个用户详细交易记录信息
	function loadotherPage_money(params) {
		var param="bm_st_jsuserid="+$("#addForm input[name='ae_st_id']").val();//条件参数
		var url="wyyf/user/usermoneylogList";
		var xsl=$("#commonid_money_column_toggler input[name='ISSHOWCOLUMS'][type='checkbox']:checked").serialize();//显示列的控制
		if(xsl){
			param+="&"+xsl;
		}
		newMask();
		jQuery("#tablemoneylogList").load(url,param+params+"&rr="+new Date().getTime(),function(response,status,xhr){
			delMask();
			if(xhr.status==403){
				$(this).html(xhr.responseText);
			}else if(xhr.status==404){
				$(this).html(xhr.responseText);
			}
		});	
	}
</script> 
<script>
//图片本地预览
function previewImage(file){
    if(checkPic(file)){
    	var MAXWIDTH  = 277;
        var MAXHEIGHT = 150;
        var img =$(file).parents("form").first().find("#imghead")[0];
        if (file.files && file.files[0]){
          img.onload = function(){
            img.width =277;
            img.height = 150;
          }
          var reader = new FileReader();
          reader.onload = function(evt){img.src = evt.target.result;}
          reader.readAsDataURL(file.files[0]);
        }else{
          var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
          file.select();
          var src = document.selection.createRange().text;
          img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
          div.innerHTML = "<div id=divhead style='width:277px;height:150px;"+sFilter+src+"\"'></div>";
        }
    }
}
//图片选择格式限制
function checkPic(obj) {
	var pass =$(obj).parents("form").first().find("#myfile")[0];
    var picPath = pass.value;
    var type = picPath.substring(picPath.lastIndexOf(".") + 1, picPath.length).toLowerCase();
    if (type != "jpg" && type != "bmp" && type != "gif" && type != "png") {
        pass.setCustomValidity("请上传正确的图片格式");
        bootbox.alert("请上传正确的图片格式");
        //$("#myfile").val("");
        var file = $("input[name='myfile']") 
		file.after(file.clone().val("")); 
		file.remove();
        return false;
    }else{
    	pass.setCustomValidity('');
    	return true;
    }
}
</script>