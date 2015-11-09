<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="/lystag" prefix="my" %>

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
		<div class="caption"><i class="fa fa-globe"></i>门户设置列表</div>
		<div class="actions">
			<a id="addAdBtn" href="javascript:void(0);" class="btn blue"><i class="fa fa-plus"></i> 添加</a>
		</div>
	</div>
	<div class="portlet-body">
		<div title="查询条件">
			<form id="queryForm">
				姓名：<input type="text" name="bf_st_name" class="form-control" style="width: 200px;display: inline;">
				类型：<my:select name="bf_st_type" clazz="form-control" nameKey="SQLX" isAddDefaltOption="true" defaltOptionKey="" defaltOptionValue="-请选择-"  style="width: 200px;display: inline;"/>
				<input type="text"   class="form-control" style="width: 200px;display: none;">
				<button type="button" id="querySolution"  class="btn blue"><i class="fa fa-search"></i>查询</button>
				<button type="reset"  class="btn default">重置</button>
			</form>
		</div>
		<div  id="tableList" >
		
		</div>
	</div>
	<!-- END EXAMPLE TABLE PORTLET-->
</div>
<!-- 添加门户设置层 -->
<div class="modal fade" id="add-config" tabindex="-1" role="basic"  data-backdrop="static"  aria-hidden="true">
	<div class="modal-dialog modal-wide">
		<div class="modal-content">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
			<div class="modal-header">
				<h4 class="modal-title"><span id="addOrEditor">新增申请单</span><span class="zhus">注：带【<span style="color: red;">*</span>】为必填项！</span></h4>
			</div>
			<form id="addForm" action="#" class="form-horizontal" encType="multipart/form-data" method="post">
				<div style="display: none;">
					<input name="bf_st_id" type="text" value="">
				</div>
				<div class="form-body">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4">类型</label>
								<div class="col-md-8">
									<my:select name="bf_st_type" clazz="form-control" nameKey="SQLX" />
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4">姓名<font style="color: red;">*</font></label>
								<div class="col-md-8">
									<input name="bf_st_name" class="form-control"  datatype="s2-25"  errormsg="姓名最少2个字符，最长25个字符,且不能输入非法字符！" />
								</div>
							</div>
						</div>
					</div> 
					<!--/row-->
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4">电话<font style="color: red;">*</font></label>
								<div class="col-md-8">
									<input name="bf_st_tell" class="form-control"  datatype="n6-16"  errormsg="电话最少6个数字，最长16个数字！" />
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4">预约时间</label>
								<div class="col-md-8">
									<input name="bf_dt_time" type="text" class="form-control form-control-inline date-picker"   value="" data-date-format="yyyy-mm-dd"  data-date-start-date="+0d"/>
								</div>
							</div>
						</div>
					</div> 
					<!--/row-->
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4">地址</label>
								<div class="col-md-8">
									<input name="bf_st_address" class="form-control"  datatype="*0-50"  errormsg="地址最长50个字符！" />
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4">户数记录</label>
								<div class="col-md-8">
									<input name="bf_st_housnum" class="form-control" datatype="*0-16"  errormsg="地址最长16个字符！"  />
								</div>
							</div>
						</div>
					</div>  
					<!--/row-->
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4">面积</label>
								<div class="col-md-8">
									<input name="bf_st_area" class="form-control"  datatype="*0-16"  errormsg="面积最长50个字符！" />
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4">城内城外</label>
								<div class="col-md-8">
									<my:select name="bf_st_incity" clazz="form-control" nameKey="HXCWLX" />
								</div>
							</div>
						</div>
					</div> 
					<!--/row-->
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4">赴约人帐号</label>
								<div class="col-md-8">
									<input name="bf_st_username" class="form-control"  readonly="readonly" />
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4">是否真实</label>
								<div class="col-md-8">
									<my:select name="bf_st_isreal" clazz="form-control" nameKey="YESNO" />
								</div>
							</div>
						</div>
					</div> 
					<!--/row-->
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4">申请人帐号</label>
								<div class="col-md-8">
									<input name="bf_st_receivename" class="form-control"  readonly="readonly" />
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
									<textarea name="bf_st_remark" class="form-control" rows="3" maxlength="250"></textarea>
								</div>
							</div>
						</div>
					</div>
					<!-- 图片上传控件 -->
					<div id="imageDIVadmin">
					
					</div>
					<!-- 结果图片上传控件 -->
					<div id="imageDIVjsresult">
					
					</div>
					<%--
					<div class="row" style="margin-top:20px;">
						<div class="col-md-12">
							<font color="red">
								注：
								<br/>&emsp;一、设置网站LOGO，操作：【类型】设为“网站LOGO”，然后【选择图片】，然后选择【是否启用】为“是”最后保存。
								<br/>&emsp;&emsp;&emsp;特别注意：可以设置多个网站LOGO，但是只会随机选择一个启用的LOGO设置。
								<br/>&emsp;二、设置网站名，操作：【类型】设为“网站名”，然后设置【内容】为'XX商城'，然后选择【是否启用】为“是”最后保存。
								<br/>&emsp;&emsp;&emsp;特别注意：可以设置多个网站名，但是只会随机选择一个启用的网站名设置。
								<br/>&emsp;三、设置首页大广告，操作：【类型】设为“首页大广告”，然后设置【选择图片】，然后可设置【内容】为一个URL地址,然后选择【是否启用】为“是”最后保存。
								<br/>&emsp;&emsp;&emsp;特别注意：可以设置多个首页大广告，最多不能超过5张。每个广告的点击都会跳转到设置好的URL地址。【序号】为显示的顺序。
								<br/>&emsp;四、设置首页精品推荐，操作：【类型】设为“首页精品推荐”，然后【选择图片】，然后可设置【内容】为一个商品编号,然后选择【是否启用】为“是”最后保存。
								<br/>&emsp;&emsp;&emsp;特别注意：可以设置多个首页精品推荐，最多不能超过5个。每个精品推荐的点击都会跳转到设置商品编号详情页面。【序号】为显示的顺序。
							</font>
						</div>
					</div>
					 --%>
				</div>
			</form>
			<div class="modal-footer">
				<button type="button" id="saveBtn"  class="btn blue" myattr="update">保存</button>
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
		DateInit.init();//时间控件初始化
		loadotherPage("&pageIndex=1&pageSize=10");
	});
	$(function(){
		//进入新增层
		$("#addAdBtn").click(function(){
			$("#addForm")[0].reset();
			initValidForm($("#addForm"));
			//document.getElementById('imghead').src="images/logo/default_img.jpg";//初始化图片选择
			$("#addOrEditor").html("新增申请单");
			loadImageKJ();//加载图上传控件
			$('#add-config').modal('show');
		});
		//新增-修改层的保存功能
		$("#saveBtn").click(function(){
			checkFormValid($("#addForm"),function(){
				var id=$("#addForm input[name='bj_st_id']").val();
				newMask();
				$("#addForm").form('submit',{
					url:"wyyf/apply/applyAdd",  
				     onSubmit: function(param){ 
				    }, 
				    success:function(data){ 
						delMask();
				    	var resp = eval("("+data+")"); 
						if(resp.success){
							RefreshCurPage();//刷新当前页，--该函数在 commonList.jsp中
							//修改
							if(id!=null&&id!=""){
								bootbox.alert("编辑成功！");
							}
							//新增
							else{
								bootbox.alert("新增成功！");
							}
							$('#add-config').modal('hide');
						}else{ 
							bootbox.alert(resp.msg);
						} 
				    }
				 });
			});
		});
		//查询
		$("#querySolution").click(function(){
			ClickFindPage();//查询到第1页，--该函数在 commonList.jsp中
		});
	});
	//图片上传控件加载
	function loadImageKJ2(id) {
		var param="&worklname=file2List";
		if(id){
			param=param+"&id="+id;
		}
		newMask();
		jQuery("#imageDIVjsresult").load("wyyf/common/findfile2","rr="+new Date().getTime()+param,function(response,status,xhr){
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
	//图片上传控件加载
	function loadImageKJ(id) {
		var param="&worklname=fileList&wjlx=SQDTPSCLX";
		if(id){
			param=param+"&id="+id;
		}
		newMask();
		jQuery("#imageDIVadmin").load("wyyf/common/findfile","rr="+new Date().getTime()+param,function(response,status,xhr){
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
	//替换主题内容的函数
	function loadotherPage(params) {
		var param=$("#queryForm").serialize();//条件参数
		var url="wyyf/apply/applyList";
		
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
	//去查看进入
	function findapply(obj){
		$("#addForm")[0].reset();
		var advertisementId=$(obj).parents("tr").first().find("input[type='checkbox'].checkboxes").val();
		loadImageKJ(advertisementId);//加载图上传控件
		loadImageKJ2(advertisementId);//加载图上传控件
		var url = "wyyf/apply/findApplyById";
		newMask();
		$.post(url,"id="+advertisementId,function callback(data){   
			delMask();
			var resp = eval("("+data+")"); 
			if(resp.success){
				var obj=resp.result[0];
				formload($("#addForm"),obj);
				/*if(obj.ag_st_url == null || obj.ag_st_url == ""){
					document.getElementById('imghead').src="images/logo/default_img.jpg";//初始化图片选择
				}else{
					document.getElementById('imghead').src=obj.ag_st_url;
				}*/
				$("#addOrEditor").html("查看申请单");
				initValidForm($("#addForm"));//清除表单验证样式 
				$('#add-config').modal('show');
			}else{ 
				bootbox.alert(resp.msg);
			}
		});
	}
	
	//删除门户设置 
	function delapply(obj){
		bootbox.confirm('确定是否删除？',function(rs){
			if(rs){
				newMask();
				var advertisementId=$(obj).parents("tr").first().find("input[type='checkbox'].checkboxes").val();
				var url = "wyyf/apply/applyDelete";
				$.post(url,"id="+advertisementId,function callback(data){  
					delMask();
					var resp = eval("("+data+")"); 
					if(resp.success){
						RefreshCurPage();//刷新当前页，--该函数在 commonList.jsp中
						bootbox.alert(resp.msg);
					}else{ 
						bootbox.alert(resp.msg);
					}
				});
			}else{
			  //取消操作
			}
		});
	}
</script> 
 
