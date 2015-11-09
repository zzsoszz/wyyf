<%@ page language="java"  pageEncoding="UTF-8"%>
<%@page import="com.lys.utils.StringUtils"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@page import="com.lys.utils.DateConverUtil"%>
<%@ taglib uri="/lystag" prefix="my" %>

<div class="tabbable tabbable-custom boxless">
	<ul class="nav nav-tabs">
		<li class="active"><a href="#tab_1" data-toggle="tab">活动基本信息</a></li>
		<li><a href="#tab_2" data-toggle="tab">商品信息</a></li>
	</ul>
	<div class="tab-content">
		<div class="tab-pane active" id="tab_1">
			<div class="portlet box green">
				<div class="portlet-title">
					<div class="caption"><i class="fa fa-reorder"></i>活动基本信息</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a>
					</div>
				</div>
				<div class="portlet-body">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4">活动编号</label>
								<div class="col-md-8">
									<div style="display:none;">
										<input name="bo_st_id" type="text" value="" alt="活动ID">
									</div>
									<input name="bo_st_ddnum" type="text" class="form-control"   readonly="readonly"/>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4">活动商品总价格</label>
								<div class="col-md-8">
									<input name="bo_st_spprice" type="text" class="form-control"   readonly="readonly" />
								</div>
							</div>
						</div>
					</div>
					<!-- /row -->
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4">活动标题<span style="color: red;">*</span></label>
								<div class="col-md-8">
									<input name="bo_st_title" type="text" class="form-control" datatype="*2-50"   errormsg="姓名至少2个字符，最大50个字符" />
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4">是否发布<span style="color: red;">*</span></label>
								<div class="col-md-8">
									<my:select name="bo_st_ddstate" clazz="form-control"  nameKey="YESNO" />
								</div>
							</div>
						</div>
					</div>
					<!-- /row -->
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4">时间开始</label>
								<div class="col-md-8">                                                                     
									<input name="bo_dt_startDate" type="text" class="form-control form-control-inline input-group form_advance_datetime"  size="16"  datatype="*"   nullmsg="请输入开始时间！"/>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4">时间结束</label>
								<div class="col-md-8">                                                                     
									<input name="bo_dt_endDate" type="text" class="form-control form-control-inline input-group form_advance_datetime"  size="16" datatype="*"   nullmsg="请输入结束时间！"/>
								</div>
							</div>
						</div>
					</div> 
					<!-- /row -->
					<div class="row" >
						<div class="col-md-12">
							<div class="form-group">
								<label class="control-label col-md-2">活动主题</label>
								<div class="col-md-10">
									<textarea name="bo_st_ddremark"  rows="3" class="form-control" maxlength="250"></textarea>
								</div>
							</div>
						</div>
					</div>
					<!-- /row -->
				</div>
			</div>
			<!-- /portlet -->
		</div>
		<div class="tab-pane" id="tab_2">
			 <div class="portlet box purple">
				<div class="portlet-title">
					<div class="caption"><i class="fa fa-reorder"></i>商品信息</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a>
					</div>
				</div>
				<div class="portlet-body">
				<%
					List<Map<String, Object>> splist=(List<Map<String, Object>>)request.getAttribute("splist");
					for(Map<String, Object> map:splist){
				%>
					<div class="row"  style="background-color: #F0F0F0;color:#5100FC;height:150px; margin-top:5px;">
						<div class="col-md-4">
							<img alt="图片" src="<%="".equals(StringUtils.toStringByObject(map.get("bg_st_img1")))?"images/logo/default_img.jpg":StringUtils.toStringByObject(map.get("bg_st_img1")) %>" 
							onerror="nofind('images/logo/default_img.jpg');"  style="width:150px;height:130px;margin-top:8px;"
							/>
						</div>
						<div class="col-md-8">
							<h4>
								商品名字：<%=StringUtils.toStringByObject(map.get("bg_st_name")) %><br/>
								商品价格：<span style="color:red;"><%=StringUtils.toStringByObject(map.get("bp_st_spprice")) %></span><br/>
								商品数量：<%=StringUtils.toStringByObject(map.get("bp_st_spsl")) %><br/>
							</h4>
						</div>
					</div>
					<!-- /row -->
				<%	} %>
				</div>
			</div>
			<!-- /portlet -->
		</div>
	</div>
</div>


<script>
	var infojsonData=${infojsonData};
	//初始化打开页面 
	$(function() {
		DateInit.init();//时间控件初始化
		//加载活动基本信息
		if(infojsonData.success){
			var obj=infojsonData.result[0];
			formload($("#addForm"),obj);
		}else{ 
			bootbox.alert(resp.msg);
		}
		
	});
</script>