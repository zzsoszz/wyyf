<%@ page language="java"  pageEncoding="UTF-8"%>
<%@page import="com.lys.utils.StringUtils"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Date"%>
<%@ taglib uri="/lystag" prefix="my" %>

<link rel="stylesheet" type="text/css" href="http://${header['host']}${pageContext.request.contextPath}/plugin/autocomplete/jquery.autocomplete.css" />
<script type="text/javascript" src="http://${header['host']}${pageContext.request.contextPath}/plugin/autocomplete/jquery.autocomplete.js"></script>
<style>
ul{list-style-type: none;}
</style>
<div class="modal-header">
	<h4  class="modal-title"><span id="titleText">${textTitle }活动</span><span class="zhus">注：带【<span style="color: red;">*</span>】为必填项！</span></h4>
</div>

<form id="addForm" class="form-horizontal"  method="post">
	<div class="form-body">
		 
		<div class="portlet box green">
			<div class="portlet-title">
				<div class="caption"><i class="fa fa-reorder"></i>活动基本信息</div>
				<div class="tools">
					<a href="javascript:;" class="collapse"></a>
				</div>
			</div>
			<div class="portlet-body addBorder">
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
		<div class="portlet box purple">
			<div class="portlet-title">
				<div class="caption"><i class="fa fa-reorder"></i>商品信息</div>
				<div class="tools">
					<a href="javascript:;" class="collapse"></a>
				</div>
			</div>
			<div class="portlet-body">
				<div class="row" style="margin-bottom:15px;">
					&emsp;商品总价格&nbsp;
					<input  type="text" class="form-control" value="保存时候系统自动计算" readonly="readonly"  style="width: 550px; display: inline;" />
					<button type="button" onclick="addtabobj(this,'tab1_All')" class="btn blue" myattrname="prodList">点击新增<i class="fa fa-arrow-down"></i></button>
				</div>
				<!-- /row -->
				<div id="tab1_All">
				<%
					List<Map<String,Object>> prodList=(List<Map<String,Object>>)request.getAttribute("workExps");
					for(int i=0,b=prodList.size();i<b+1;i++){
						Map<String,Object> workmap=new HashMap<String,Object>();
						if(b>0&&i<b){
							workmap=prodList.get(i);
						}
						String wymark="prodList["+i+"]";
						String divId="tab_prodList"+i+new Date().getTime();
						if(i==b){
							wymark="wduserzblist";
							divId="tab1_demo";
						}
				%>
					<div id="<%=divId %>" class="addBorder">
						<div class="row" style="margin:3px 0px 3px;">
							商&emsp;品&emsp;名<span style="color: red;">*</span>
							<input  type="text" name="<%=wymark %>.bp_st_remark" class="form-control autocomplete"  style="width: 550px; display: inline;" value="" datatype="*"  nullmsg="请输入商品名称！" />
							<div style="display:none;">
								<input  type="text" name="<%=wymark %>.bp_st_spnum" datatype="*"  nullmsg="请输入商品号！" />
								<input  type="text" name="<%=wymark %>.bp_st_bbid"  />
								<input  type="text" name="<%=wymark %>.bp_st_id" markName="ID" markCode="bp_st_id"/>
							</div>
							本次活动单价<span style="color: red;">*</span><input name="<%=wymark %>.bp_st_spprice" type="text" style="width: 100px; display: inline;"  class="form-control" value=""  datatype="/^(([1-9]\d*)|0)(\.\d{1,2})?$/"  errormsg="请输入大于0的数字，且最多2位小数！" >
							商品数量<span style="color: red;">*</span>
							<input name="<%=wymark %>.bp_st_spsl" type="text" style="width: 100px; display: inline;" class="form-control" value="" datatype="/^(0|([1-9]\d*))$/" errormsg="请输入10位以内的数字！" nullmsg="请输入商品数量！" maxlength="10" >
							<button class="btn btn-sm red" onclick="deltabobj(this)"  type="button">删除<i class="fa fa-trash-o"></i></button>
						</div>
					</div>
				<%	} %>
				</div>
			</div>
		</div>
<!-- 		<div class="row" style="margin-top:20px;"> -->
<!-- 						<div class="col-md-12"> -->
<!-- 							<font color="red"> -->
<!-- 								注： -->
<!-- 								<br/>&emsp;一、当正常下单的时候，【下单人帐号】必须属实填写 。 -->
<!-- 							</font> -->
<!-- 						</div> -->
<!-- 					</div> -->
		<!-- /portlet -->
	</div>
	<div class="modal-footer">
		<button type="button" id="saveBtn"  class="btn blue">确认创建</button>
	</div>
	<p>&nbsp;<p/> <p>&nbsp;<p/> <p>&nbsp;<p/> 
</form>

<script>
	//表单数据
	var infojsonData=${infojsonData};
	//初始化打开页面 
	$(function() {

		//加载活动基本信息
		if(infojsonData.success){
			var objresult=infojsonData.result[0];
			formload($("#addForm"),objresult);
		}
		//点击保存事件
		$("#saveBtn").click(function(){
			if($("#tab1_All div").size()<1){
				bootbox.alert("请至少添加一件商品！");
				return false;
			}
			checkFormValid($("#addForm"),function(){
				bootbox.confirm('确定是否新增活动？',function(rs){
					if(rs){
						newMask();
						var url = "wyyf/activity/activitySave";
						var params=$("#addForm").serialize();
						$.post(url,params,function callback(data){ 
							delMask();
							var id=$("#addForm input[name='bo_st_id']").val();
					    	var resp = eval("("+data+")"); 
							if(resp.success){
					            bootbox.confirm('新增成功，是否重置？',function(rs){
					    			if(rs){
					    				loadMainPage('wyyf/activity/intoOrderSave');//刷新本界面
					    			}
					    		});
							}else{ 
								bootbox.alert(resp.msg);
							}
						}); 
					}
				});  
				
			});
		});
	});
</script>
<!-- 商品搜索输入 -->
<script type="text/javascript">
    $(function(){
		DateInit.init();//时间控件初始化
    	initautocomp();//初始化自动补全控件
    });
    //初始化自动补全控件
	var personnels = ${allprodauto};//所有酒品数据 
    function initautocomp(){
    	 $(".autocomplete").autocomplete(personnels,{
             minChars: 0,//自动完成激活之前填入的最小字符
			  max:30,//列表条目数
			  resultsClass:"dropdown-menu",//设置返回结果的Class
			  width: 550,//提示的宽度
			  scrollHeight: 300,//提示的高度
			  matchContains: true,//是否只要包含文本框里的就可以
			  autoFill:false,//自动填充
			  formatItem: function(data, i, max) {//格式化列表中的条目 row:条目对象,i:当前条目数,max:总条目数
    				return '<label class="col-md-12"><label class="col-md-8">' + data.bg_st_name + '</label><label class="col-md-2">'+data.price+ ' </label><label class="col-md-2">'+data.typename+'</label></label>'
             },
             formatMatch: function(data, i, max) {//配合formatItem使用，作用在于，由于使用了formatItem，所以条目中的内容有所改变，而我们要匹配的是原始的数据，所以用formatMatch做一个调整，使之匹配原始数据
					return data.bg_st_name;
             },
			  formatResult: function(data) {//定义最终返回的数据，比如我们还是要返回原始数据，而不是formatItem过的数据
					return data.bg_st_name;
             }
	       }).result(function(event,data,formatted){
		      var formv=$(event.currentTarget).parents("div.addBorder").first();
		      formv.find("input[name$='.bp_st_spnum']").val(data.bg_st_num);
		      formv.find("input[name$='.bp_st_bbid']").val(data.bg_st_bbid);
		      formv.find("input[name$='.bp_st_spprice']").val(data.price);
	       });
    	//新增失去焦点事件
		$(".autocomplete").blur(function(){
			 $(this).val(obtain($(this).parents("div.addBorder").first().find("input[name$='.bp_st_spnum']").val()));
		});
    }
  //通过集团获取对应Name
	function obtain(key){
		var name="";
		$.each(personnels,function(n,data) {
            if(data.bg_st_num == key){
            	name=data.bg_st_name;
				return false; 
	         }
		 });
		return name;
	}
</script>
<!-- 商品新增事件 -->
<script>
	//商品信息
	var tab1_=$("#tab1_demo").html();
	$("#tab1_demo").remove();
	var tab1_num=<%=prodList.size() %>;
	//新增
	function addtabobj(obj,targetid){
		var myattrname=$(obj).attr("myattrname");
		var htmltab="";
		var htmlnum=0;
		if(myattrname=="prodList"){
			htmltab=tab1_;
			htmlnum=tab1_num++;
		}
		$("#"+targetid).append('<div id="tab_'+$(obj).attr("myattrname")+new Date().getTime()+'" class="addBorder">'+htmltab.replace(/wduserzblist\./g, $(obj).attr("myattrname")+"["+htmlnum+"].")+'</div>');
		//DateInit.init();//时间控件初始化
		initautocomp();//初始化自动补全控件
	}
	//移除商品当前对象
	function deltabobj(obj){
		var parents=$(obj).parents("div.addBorder").first();
		var id=parents.find("input[markName='ID']").val();
		//如果该条数据已经入库，删除时要请求 
		if(id!=null&&id!=""){
		}else{
			parents.remove();
		}
	}
</script>
