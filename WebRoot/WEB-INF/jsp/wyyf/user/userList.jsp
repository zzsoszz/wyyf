<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="/lystag" prefix="my" %>
<!-- Ztree树 -->
<link href="http://${header['host']}${pageContext.request.contextPath}/plugin/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="http://${header['host']}${pageContext.request.contextPath}/plugin/zTree/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="http://${header['host']}${pageContext.request.contextPath}/plugin/zTree/js/jquery.ztree.excheck-3.5.min.js"></script>
<style>
	ul.ztree {margin-top: 0px;border: 1px solid #617775;background: #f0f6e4;width:95%;height:250px; overflow-x:auto;}
</style>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<%@ include file="../../system/common/crumbsCom.jsp"%>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<div class="portlet box blue">
	<!-- BEGIN EXAMPLE TABLE PORTLET-->
	<div class="portlet-title">
		<div class="caption"><i class="fa fa-globe"></i>用户列表</div>
		<div class="actions">
			<a id="addUserBtn" href="javascript:void(0);" class="btn blue"><i class="fa fa-plus"></i>新增用户</a>
		</div>
	</div>
	<div class="portlet-body">
		<div title="查询条件">
			<form id="queryForm">
				姓名：<input type="text" name="ae_st_name" class="form-control" style="width: 150px;display: inline;">
				帐号：<input type="text" name="username" class="form-control" style="width: 150px;display: inline;">
				<!-- 状态：<my:select name="ae_st_isjh" clazz="form-control" isAddDefaltOption="true" defaltOptionKey="" defaltOptionValue="--新选择--" nameKey="ZHZT" style="width: 150px; display:inline;" />
				手机号：<input type="text" name="ae_st_phone" class="form-control" style="width: 150px;display: inline;"> -->
				<button type="button" id="queryBtn"  class="btn blue">查询</button>
				<button type="button" onclick="javascript:reset();" class="btn default">重置</button> 
			</form>
		</div>
		<div  id="tableList" >
		
		</div>
	</div>
	<!-- END EXAMPLE TABLE PORTLET-->
</div>
<!-- 添加/编辑弹出层 -->
<div class="modal fade" id="add-config" tabindex="-1" set="basic"  aria-hidden="true">
	<div class="modal-dialog modal-wide">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title" id="usertile"></h4>
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
							<li><a href="#tab_3" data-toggle="tab">收货地址信息</a></li>
							<li><a href="#tab_4" data-toggle="tab">附件资料</a></li>
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
														<label class="control-label col-md-4">ID<font style="color: red;">*</font></label>
														<div class="col-md-8">
															<input name="ae_st_id"  readonly="readonly" type="text" class="form-control" placeholder=""  nullmsg="请输入6到25位字符！">
														</div>
													</div>
												</div>
												<div class="col-md-12">
													<div class="form-group">
														<label class="control-label col-md-4">帐号<font style="color: red;">*</font></label>
														<div class="col-md-8">
															<input name="ae_st_id" type="hidden">
															<input name="username" type="text" class="form-control" placeholder="" datatype="s6-25"  nullmsg="请输入6到25位字符！">
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
														<label class="control-label col-md-4">姓名/品牌名称<font style="color: red;">*</font></label>
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
														<label class="control-label" >照片  (注)商家传品牌相片、其他传个人头像</label>
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
											<div class="col-md-6">
												<div class="form-group">
													<label class="control-label col-md-4">昵称</label>
													<div class="col-md-8">
														<input name="ae_st_nickName" class="form-control"  datatype="con1-25"  errormsg="昵称最长25个字符！" />
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label class="control-label col-md-4">电话</label>
													<div class="col-md-8">
														<input name="ae_st_tell" class="form-control"  datatype="con3-15"  errormsg="请输入15位以内有效电话号码" />
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
											<script type="text/javascript">
											
											function checkNum(obj) {  
											     //检查是否是非数字值  
											     if (isNaN(obj.value)) {  
											         obj.value = "";  
											     }  
											     if (obj != null) {  
											         //检查小数点后是否对于两位http://blog.csdn.net/shanzhizi  
											         if (obj.value.toString().split(".").length > 1 && obj.value.toString().split(".")[1].length > 2) {  
											             alert("小数点后多于两位！");  
											             obj.value = "";  
											         }  
											     }  
											 }  
											</script>
											<div class="row">
												<div class="col-md-12">
													<div class="form-group">
														<label class="control-label col-md-2">收费相关描述</label>
														<div class="col-md-10">
														<input type="text" name="ba_st_price" value="0" onkeyup="checkNum(this)" />  
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
							<div class="tab-pane" id="tab_3">
								 <div class="portlet box purple">
									<div class="portlet-title">
										<div class="caption"><i class="fa fa-reorder"></i>收货地址信息</div>
										<div class="tools">
											<a href="javascript:;" class="collapse"></a>
										</div>
									</div>
									<div class="portlet-body">
										<div  id="tableadressList" ></div>
									</div>
								</div>
								<!-- /portlet -->
							</div>
							<div class="tab-pane" id="tab_4">
								 <div class="portlet box purple">
									<div class="portlet-title">
										<div class="caption"><i class="fa fa-reorder"></i>附件资料</div>
										<div class="tools">
											<a href="javascript:;" class="collapse"></a>
										</div>
									</div>
									<div class="portlet-body">
										<div  id="myfileList" ></div>
									</div>
								</div>
								<!-- /portlet -->
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" id="saveBtn1" mykzattr="1" class="btn blue">审核通过</button>
					<button type="button" id="saveBtn2"  mykzattr="1" class="btn blue">审核不通过</button>
					<button type="button" id="saveBtn"  mykzattr="1" class="btn blue">保存</button>
					<button type="button" class="btn default" data-dismiss="modal">取消</button>
				</div>
			</form>
			
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>

<!-- 充值或者扣款弹出层 -->
<div class="modal fade" id="cztc-config" tabindex="-1" set="basic"  aria-hidden="true">
	<div class="modal-dialog modal-wide">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">资金操作</h4>
			</div>
			<form id="czForm" action="#" class="form-horizontal">
				<div class="form-body">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4">操作类型</label>
								<div class="col-md-8">
									<input name="ae_st_id" type="hidden" value="">
									<select name="czkc" class="form-control" style="width: 100px; display:inline;">
										<option value="1">充值</option>
										<option value="2">扣款</option>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4">操作金额<font style="color: red;">*</font></label>
								<div class="col-md-8">
									<input name="ae_nm_zjnum" class="form-control"   datatype="/^([1-9]\d*)(\.\d{1,2})?$/"  errormsg="请输入大于0的数字，且最多2位小数！"  />
								</div>
							</div>
						</div>
					</div> 
					<!--/row-->
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label class="control-label col-md-2">备注</label>
								<div class="col-md-10">
									<textarea name="ae_st_remark" class="form-control" rows="4"  datatype="con0-250"  errormsg="设置内容最长250个字符！"></textarea>
								</div>
							</div>
						</div>
					</div> 
					<!--/row-->
				</div>
			</form>
			
			<div class="modal-footer">
				<button type="button" id="czBtn"  class="btn blue">保存</button>
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
			$.fn.zTree.init($("#groupsTree3"), setting3, zNodes);
			$("[mykzattr='1']").show();//保存按钮显示
			initValidForm($("#addForm"));//清除表单验证样式
			updatePeopleinfo($("#typeCZ"));//进入编辑类型
			$("#addForm input[name='username']").removeAttr("readonly");//能修改帐号
			$("#addForm select[name='ae_st_type']").removeAttr("readonly");//新增时候可以选择类型
			$("#tablemoneylogList").html("");
			$("#tableadressList").html("");
			$("#addForm")[0].reset();
			$("#changeMMText").html('密码<font style="color: red;">*</font>');
			$("#addForm input[name='password']").removeAttr("ignore");
			$("#addForm input[name='ae_st_id']").val("");//清空隐藏的文本框ID值
			$("#addForm input[name='groupids3']").val("");//清空隐藏的文本框ID值
			
			$("#usertile").text("新增用户");
			$("#sfadd").hide();
			$("#sjadd").hide();
			$('#add-config').modal('show');
		});
		//新增-修改权限层的保存功能
		$("#saveBtn").click(function(){
			savecomfun();
		});
		//审核通过
		$("#saveBtn1").click(function(){
			$("#addForm input[name='ae_st_lockstate']").val("1");
			savecomfun();
		});
		//审核不通过
		$("#saveBtn2").click(function(){
			$("#addForm input[name='ae_st_lockstate']").val("2");
			savecomfun();
		});
		//提交充值
		$("#czBtn").click(function(){
			checkFormValid($("#czForm"),function(){
				var url = "wyyf/user/czuser";
				var params=$("#czForm").serialize();
				newMask();
				$.post(url,params,function callback(data){  
					delMask();
					var resp = eval("("+data+")"); 
					if(resp.success){
						RefreshCurPage();//刷新当前页，--该函数在 commonList.jsp中
						bootbox.alert(resp.msg);
						$('#cztc-config').modal('hide');
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
	//附件控件加载--
	function loadImageKJ(id) {
		var param="&worklname=fileList&wjlx=SQDTPSCLX";
		if(id){
			param=param+"&id="+id;
		}
		newMask();
		jQuery("#myfileList").load("wyyf/common/registerFile","rr="+new Date().getTime()+param,function(response,status,xhr){
			delMask();
			if(id){
				$("[myattr='update']").hide();//只能查看
			}else{
				$("[myattr='update']").show();
			}
			if(xhr.status==403){
				$(this).html(xhr.responseText);
			}else if(xhr.status==404){
				$(this).html(xhr.responseText);
			}
		});
	}
	//保存公共方法
	function savecomfun(){
		checkFormValid($("#addForm"),function(){
			$("#addForm").form('submit',{
				url:"wyyf/user/userAdd",
			    onSubmit: function(param){ 
			    }, 
			    success:function(data){ 
			    	var resp = eval("("+data+")"); 
					if(resp.success){
						RefreshCurPage();//刷新当前页，--该函数在 commonList.jsp中
						bootbox.alert(resp.msg);
						$('#add-config').modal('hide');
					}else{ 
						bootbox.alert(resp.msg);
					}
			    }
			 });
		});
	}
	//查询函数--替换主题内容的函数
	function loadotherPage(params) {
		var param=$("#queryForm").serialize();//条件参数
		var url="wyyf/user/userList";
		
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
	//充值或者扣款
	function czUser(obj){
		initValidForm($("#czForm"));//清除表单验证样式
		$("#czForm")[0].reset();
		var userid=$(obj).parents("tr").first().find("input[type='checkbox'].checkboxes").val();
		$("#czForm input[name='ae_st_id']").val(userid);
		$('#cztc-config').modal('show');
	}
	//修改用户信息--进入
	function editUser(obj){
		$("[mykzattr='1']").show();//保存按钮显示
		initValidForm($("#addForm"));//清除表单验证样式
		$("#addForm input[name='groupids3']").val("");//清空隐藏的文本框ID值
		getinfostatus($("#typeCZ"));//进入查看类型
		$("#addForm select[name='ae_st_type']").attr("readonly","readonly");//不能修改类型
		$("#addForm input[name='username']").attr("readonly","readonly");//不能修改帐号
		$("#addForm")[0].reset();
		$("#changeMMText").html("新密码");
		$("#addForm input[name='password']").attr("ignore","ignore");
		$("#usertile").text("编辑用户信息");
		var userid=$(obj).parents("tr").first().find("input[type='checkbox'].checkboxes").val();
		var url = "wyyf/user/findUserById";
		newMask();
		$.post(url,"userid="+userid,function callback(data){  
			delMask();
			var resp = eval("("+data+")"); 
			if(resp.success){
				var obj=resp.result[0];
				formload($("#addForm"),obj);
				loadImageKJ(userid);
				if(obj.ae_st_lockstate=='1'){
					$("#saveBtn1").hide();
				}else if(obj.ae_st_lockstate=='2'){
					$("#saveBtn2").hide();
				}
				
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
				loadotherPage_address("&pageIndex=1&pageSize=10");//加载地址信息
				$('#add-config').modal('show');
			}else{ 
				bootbox.alert(resp.msg);
			}
		});
		
	}
	//查看用户信息--进入
	function findUser(obj){
		editUser(obj);
		$("[mykzattr='1']").hide();
	}
	//查询函数--查询某个用户收货地址信息--主要针对会员
	function loadotherPage_address(params) {
		var param="bm_st_jsuserid="+$("#addForm input[name='ae_st_id']").val();//条件参数
		var url="wyyf/user/useraddressList";
		var xsl=$("#commonid_address_column_toggler input[name='ISSHOWCOLUMS'][type='checkbox']:checked").serialize();//显示列的控制
		if(xsl){
			param+="&"+xsl;
		}
		newMask();
		jQuery("#tableadressList").load(url,param+params+"&rr="+new Date().getTime(),function(response,status,xhr){
			delMask();
			if(xhr.status==403){
				$(this).html(xhr.responseText);
			}else if(xhr.status==404){
				$(this).html(xhr.responseText);
			}
		});	
	}
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
	//注销用户信息
	function zxUser(obj){
		bootbox.confirm("确认是否注销该用户？",function(rs){
			if(rs){
				var roleid=$(obj).parents("tr").first().find("input[type='checkbox'].checkboxes").val();
				var url = "wyyf/user/userzx";
				newMask();
				$.post(url,"ae_st_id="+roleid,function callback(data){  
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
		/*bootbox.confirm("确认是否删除？",function(rs){
			if(rs){
				var roleid=$(obj).parents("tr").first().find("input[type='checkbox'].checkboxes").val();
				var url = "wyyf/user/userDelete";
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
		});*/
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