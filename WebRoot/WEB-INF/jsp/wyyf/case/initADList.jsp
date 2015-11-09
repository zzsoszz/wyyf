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
		<div class="caption"><i class="fa fa-globe"></i>案例列表</div>
		<div class="actions">
			<a id="addAdBtn" href="javascript:void(0);" class="btn blue"><i class="fa fa-plus"></i> 添加</a>
		</div>
	</div>
	<div class="portlet-body">
		<div title="查询条件">
			<form id="queryForm">
				标题：<input type="text" name="bd_st_name" class="form-control" style="width: 200px;display: inline;">
				是否发布：<my:select name="bd_st_isfb" clazz="form-control" nameKey="YESNO" isAddDefaltOption="true" defaltOptionKey="" defaltOptionValue="-请选择-"  style="width: 200px;display: inline;"/>
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
<!-- 添加案例层 -->
<div class="modal fade" id="add-config" tabindex="-1" role="basic"  data-backdrop="static"  aria-hidden="true">
	<div class="modal-dialog modal-wide">
		<div class="modal-content">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
			<div class="modal-header">
				<h4 class="modal-title"><span id="addOrEditor">新增案例</span><span class="zhus">注：带【<span style="color: red;">*</span>】为必填项！</span></h4>
			</div>
			<form id="addForm" action="#" class="form-horizontal" encType="multipart/form-data" method="post">
				<div class="form-body">
					<div class="row">
						<div class="col-md-6">
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-4">案例名称<font style="color:red;">*</font></label>
									<div class="col-md-8">
										<div style="display: none;">
											<input name="bd_st_id" type="text" value="">
											<input name="ag_st_id" type="text" value="" alt="文件ID">
											<input name="ag_st_url" type="text" value="" alt="文件路径">
										</div>
										<input name="bd_st_name" type="text" class="form-control" placeholder="" maxlength="25" datatype="*">
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-4">是否发布</label>
									<div class="col-md-8">
										<my:select name="bd_st_isfb" clazz="form-control" nameKey="YESNO" />
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-4">涉及面积</label>
									<div class="col-md-8">
										<input name="bd_st_area" type="text" class="form-control" placeholder=""  maxlength="25">
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-4">序号</label>
									<div class="col-md-8">
										<input name="bd_nm_orderno" type="text" class="form-control" value="0" placeholder="" datatype="/^\d{1,4}$/"  nullmsg="请输入序号！" errormsg="序号只能为1-4位非负整数！">
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-4">时间<font style="color:red;">*</font></label>
									<div class="col-md-8">
										<input name="bd_dt_time" type="text" class="form-control form-control-inline input-group form_advance_datetime"  size="16" datatype="*"   nullmsg="请选择时间！"/>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6"> 
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label">效果图预览</label>
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
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4">涉及费用</label>
								<div class="col-md-8">
									<input name="bd_st_money" type="text" class="form-control" placeholder="" datatype="/^(([1-9]\d*)|0)(\.\d{1,2})?$/"  errormsg="请输入大于0的数字，且最多2位小数！" ignore="ignore" nullmsg="请输入价格！" />
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
									<textarea name="bd_st_remark" class="form-control" rows="3" maxlength="250"></textarea>
								</div>
							</div>
						</div>
					</div>
					<!-- <div class="row" style="margin-top:20px;">
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
					</div> -->
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
		DateInit.init();//时间控件初始化
		loadotherPage("&pageIndex=1&pageSize=10");
	});
	$(function(){
		//进入新增层
		$("#addAdBtn").click(function(){
			$("#addForm")[0].reset();
			initValidForm($("#addForm"));
			document.getElementById('imghead').src="images/logo/default_img.jpg";//初始化图片选择
			$("#addOrEditor").html("新增案例");
			$('#add-config').modal('show');
		});
		//新增-修改层的保存功能
		$("#saveBtn").click(function(){
			checkFormValid($("#addForm"),function(){
				newMask();
				var id=$("#addForm input[name='bd_st_id']").val();
				$("#addForm").form('submit',{
					url:"wyyf/case/caseAdd",  
				     onSubmit: function(param){ 
				    }, 
				    success:function(data){ 
						delMask();
				    	var resp = eval("("+data+")"); 
						if(resp.success){
							RefreshCurPage();//刷新当前页，--该函数在 commonList.jsp中
							//修改
							if(id!=null&&id!=""){
								$('#add-config').modal('hide');
								bootbox.alert("编辑成功！");
							}
							//新增
							else{
								$('#add-config').modal('hide');
								bootbox.alert("新增成功！");
							}
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
	//替换主题内容的函数
	function loadotherPage(params) {
		var param=$("#queryForm").serialize();//条件参数
		var url="wyyf/case/caseList";
		
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
	//修改案例
	function editAdvertisement(obj){
		$("#addForm")[0].reset();
		var caseId=$(obj).parents("tr").first().find("input[type='checkbox'].checkboxes").val();
		var url = "wyyf/case/findCaseById";
		newMask();
		$.post(url,"caseId="+caseId,function callback(data){   
			delMask();
			var resp = eval("("+data+")"); 
			if(resp.success){
				var obj=resp.result[0];
				formload($("#addForm"),obj);
				if(obj.ag_st_url == null || obj.ag_st_url == ""){
					document.getElementById('imghead').src="images/logo/default_img.jpg";//初始化图片选择
				}else{
					document.getElementById('imghead').src=obj.ag_st_url;
				}
				$("#addOrEditor").html("编辑案例");
				initValidForm($("#addForm"));//清除表单验证样式 
				$('#add-config').modal('show');
			}else{ 
				bootbox.alert(resp.msg);
			}
		});
	}
	
	//删除案例 
	function delAdvertisement(obj){
		bootbox.confirm('确定是否删除？',function(rs){
			if(rs){
				newMask();
				var caseId=$(obj).parents("tr").first().find("input[type='checkbox'].checkboxes").val();
				var url = "wyyf/case/caseDelete";
				$.post(url,"caseId="+caseId,function callback(data){  
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
 
