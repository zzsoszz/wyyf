<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/lystag" prefix="my" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<!--fwb-->
<script type="text/javascript" charset="utf-8" src="http://${header['host']}${pageContext.request.contextPath}/plugin/fwb/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="http://${header['host']}${pageContext.request.contextPath}/plugin/fwb/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="http://${header['host']}${pageContext.request.contextPath}/plugin/fwb/lang/zh-cn/zh-cn.js"></script>
<style>
/**预览功能时候的top*/
.edui-default .edui-dialog {
 top:40px !important;
}
.myfileclass{
	height: auto;top: 0px;right: 0px;margin: 0px;opacity: 0;font-size: 23px; direction: ltr;cursor: pointer; width:116px;  margin-top: -34px;
}
.imgylclass{
	height: 120px;width:200px;border:0;
}
</style>
<div class="modal-header">
	<h4  class="modal-title"><span id="titleText">${textTitle }商品信息</span><span class="zhus">注：带【<span style="color: red;">*</span>】为必填项！</span></h4>
</div>
<form id="addForm" class="form-horizontal"  encType="multipart/form-data" method="post">
	<!-- 商品图片管理层 -->
	<div class="modal fade"  id="img-my1-config" tabindex="-1" role="dialog"  data-backdrop="true"  aria-hidden="true">
		<div class="modal-dialog modal-wide">
			<div class="modal-content">
				<div class="modal-header">
					<h4  class="modal-title"><span id="titleText">商品图片管理</span></h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-4">
							<div id="preview">
							    <img id="imghead1" class="imgylclass" src='images/logo/default_img.jpg'><!--无预览时的默认图像，自己弄一个-->
							</div>
							<span class="btn green fileinput-button">
							    <i class="fa fa-plus"></i>
							    <span>选择图片</span>
							    <input name="myfile1" type="file" onchange="previewImage(this,'imghead1')" class="myfileclass"/>
							</span>
							<button class="btn btn-sm red" type="button" onclick="delimage('myfile1','imghead1','bg_st_img1')" style="width:50px;">删除</button>
						</div>
						<div class="col-md-4">
							<div id="preview">
							    <img id="imghead2" class="imgylclass" src='images/logo/default_img.jpg'><!--无预览时的默认图像，自己弄一个-->
							</div>
							<span class="btn green fileinput-button">
							    <i class="fa fa-plus"></i>
							    <span>选择图片</span>
							    <input name="myfile2" type="file" onchange="previewImage(this,'imghead2')" class="myfileclass"/>
							</span>
							<button class="btn btn-sm red" type="button" onclick="delimage('myfile2','imghead2','bg_st_img2')" style="width:50px;">删除</button>
						</div>
						<div class="col-md-4">
							<div id="preview">
							    <img id="imghead3" class="imgylclass" src='images/logo/default_img.jpg'><!--无预览时的默认图像，自己弄一个-->
							</div>
							<span class="btn green fileinput-button">
							    <i class="fa fa-plus"></i>
							    <span>选择图片</span>
							    <input name="myfile3" type="file" onchange="previewImage(this,'imghead3')" class="myfileclass"/>
							</span>
							<button class="btn btn-sm red" type="button" onclick="delimage('myfile3','imghead3','bg_st_img3')" style="width:50px;">删除</button>
						</div>
					</div>
					<!-- /row -->
					<div class="row" style="margin-top:10px;">
						<div class="col-md-4">
							<div id="preview">
							    <img id="imghead4" class="imgylclass" src='images/logo/default_img.jpg'><!--无预览时的默认图像，自己弄一个-->
							</div>
							<span class="btn green fileinput-button">
							    <i class="fa fa-plus"></i>
							    <span>选择图片</span>
							    <input name="myfile4" type="file" onchange="previewImage(this,'imghead4')" class="myfileclass"/>
							</span>
							<button class="btn btn-sm red" type="button" onclick="delimage('myfile4','imghead4','bg_st_img4')" style="width:50px;">删除</button>
						</div>
						<div class="col-md-4">
							<div id="preview">
							    <img id="imghead5" class="imgylclass" src='images/logo/default_img.jpg'><!--无预览时的默认图像，自己弄一个-->
							</div>
							<span class="btn green fileinput-button">
							    <i class="fa fa-plus"></i>
							    <span>选择图片</span>
							    <input name="myfile5" type="file" onchange="previewImage(this,'imghead5')" class="myfileclass"/>
							</span>
							<button class="btn btn-sm red" type="button" onclick="delimage('myfile5','imghead5','bg_st_img5')" style="width:50px;">删除</button>
						</div>
						<div class="col-md-4">
							<div id="preview">
							    <img id="imghead6" class="imgylclass" src='images/logo/default_img.jpg'><!--无预览时的默认图像，自己弄一个-->
							</div>
							<span class="btn green fileinput-button">
							    <i class="fa fa-plus"></i>
							    <span>选择图片</span>
							    <input name="myfile6" type="file" onchange="previewImage(this,'imghead6')" class="myfileclass"/>
							</span>
							<button class="btn btn-sm red" type="button" onclick="delimage('myfile6','imghead6','bg_st_img6')" style="width:50px;">删除</button>
						</div>
					</div>
					<!-- /row -->
					<div class="row" style="margin-top:20px;">
						<div class="col-md-12">
							<font color="red">
								注：第一个位置的图片为商品首图（标题图），建议上传一张图片，其他位置图片随意上传。
							</font>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn default"  id="coldecolde">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 表单属性 -->
	<div class="form-body">
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">商品名称<font style="color: red;">*</font></label>
					<div class="col-md-8">
						<div style="display:none;">
							<input name="bg_st_id" type="text" value="">
							<input name="bg_st_img1" type="text" value="">
							<input name="bg_st_img2" type="text" value="">
							<input name="bg_st_img3" type="text" value="">
							<input name="bg_st_img4" type="text" value="">
							<input name="bg_st_img5" type="text" value="">
							<input name="bg_st_img6" type="text" value="">
						</div>
						<input name="bg_st_name" type="text" class="form-control"  placeholder="" datatype="*2-100"  nullmsg="请输入商品名称！"  errormsg="商品名称至少2个字符，最大100个字符"/>
					</div>
				</div>
			</div>
			<div id="bg_st_numDIV" class="col-md-6" style="display: none;">
				<div class="form-group">
					<label class="control-label col-md-4">商品编号</label>
					<div class="col-md-8">
						<input name="bg_st_num" type="text" class="form-control" readonly="readonly" />
					</div>
				</div>
			</div>
		</div>
		<!-- /row -->
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">品牌<font style="color: red;">*</font></label>
					<div class="col-md-8">
						<input name="pp" type="text" class="form-control"  />
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">型号<font style="color: red;"></font></label>
					<div class="col-md-8">                                                                     
						<input name="xh" type="text" class="form-control" />
					</div>
				</div>
			</div>
		</div>
		<!-- /row -->
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">产地<font style="color: red;"></font></label>
					<div class="col-md-8">
						<input name="cd" type="text" class="form-control" />
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">材质<font style="color: red;"></font></label>
					<div class="col-md-8">                                                                     
						<input name="cz" type="text" class="form-control" />
					</div>
				</div>
			</div>
		</div>
			<!-- /row -->
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">市场价格<font style="color: red;">*</font></label>
					<div class="col-md-8">
						<input name="bg_st_pricezg" type="text" class="form-control" placeholder="" datatype="/^(([1-9]\d*)|0)(\.\d{1,2})?$/"  errormsg="请输入大于0的数字，且最多2位小数！"  nullmsg="请输入市场价格！" />
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">库存<font style="color: red;">*</font></label>
					<div class="col-md-8">                                                                     
						<input name="bg_nm_storeNum" type="text" class="form-control" value="0" datatype="/^(0|([1-9]\d*))$/" errormsg="请输入10位以内的数字！" nullmsg="请输入库存！" maxlength="10"/>
					</div>
				</div>
			</div>
		</div>
		<!-- /row -->
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">商品类型<font style="color: red;">*</font></label>
					<div class="col-md-8">
						<my:select id="sl"  clazz="form-control" nameKey="PPLX"/>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">活动价格</label>
					<div class="col-md-8">
						<input name="bg_st_pricetj" type="text" class="form-control" placeholder="" value="0" datatype="/^(([1-9]\d*)|0)(\.\d{1,2})?$/"  errormsg="请输入大于0的数字，且最多2位小数！"  />
					</div>
				</div>
			</div>
		</div>
		<!-- /row -->
		<div class="row">
				<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">商品二级分类<font style="color: red;">*</font></label>
					<div id="xlxDiv" class="col-md-8">
					</div>
				</div>
			</div>
			<input type="hidden"  value="${type }" id="threeIdData">
		<script language="javascript" type="text/javascript">
	
		
		function initData(){  
		 var  ids=	$("#threeIdData").val();
		
		 var bz=ids.substring(0, 1);
		 $("#sl option[value='"+bz+"']").attr("selected", "selected");
		 var two="PPLX_"+bz;
		 $("#xlxDiv").load('cat/towCat.jsp?param='+two.toString());
		}
		
		function initDataTow(){  
			 var  ids=	$("#threeIdData").val();
			 var two1=ids.substring(0, 1)+"00"+ ids.substring(1, 2);
			
			 $("#twoDivId option[value='"+two1+"']").attr("selected", "selected");
			 
			 var two="PPLX_"+ids.substring(0, 1)+"_"+two1;
			
			console.debug(two+"=");
			 $("#threeDivID").load('cat/threeCat.jsp?param='+two);
			
			}
		function initDataThree(){  
			 var  ids=	$("#threeIdData").val();
			 console.debug(ids);
			 $("#threeId option[value='"+ids+"']").attr("selected", "selected");
			}
		
		
		
			$(document).ready(function() {
				setTimeout("initData()", 50 )
				setTimeout("initDataTow()", 900 )
				setTimeout("initDataThree()", 1100 )
			//	$("#xlxDiv").load('cat/towCat.jsp?param=PPLX_1');
				
				
			     $("#sl").change(function() {
					var p1 = $(this).children('option:selected').val();//这就是selected的值 
					var two="PPLX_"+p1;
					$("#xlxDiv").load('cat/towCat.jsp?param='+two.toString());
					
					
				});
			});
			
			//console.debug($("#threeId"));
			
		</script>
			
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">商品三级分类<font style="color: red;">*</font></label>
					<div id="threeDivID" class="col-md-8">
					</div>
				</div>
			</div>
			
			
<!-- 			<div class="col-md-6"> -->
<!-- 				<div class="form-group"> -->
<!-- 					<label class="control-label col-md-4"><font style="color: red;">注：</font></label> -->
<!-- 					<div class="col-md-8">   -->
<!-- 						<input  value="活动价格会在【发布类型】为“限时抢购”时候生效" class="form-control" style="color: red;" disabled="disabled" />                                                                    -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->
		</div>
		<!-- /row -->
		<div class="row">
			
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">是否发布<font style="color: red;">*</font></label>
					<div class="col-md-8">                                                                     
						<my:select name="bg_st_enable" clazz="form-control" nameKey="YESNO"/>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">商家名称	   </label>
					<div class="col-md-8">  
				                                    
				                                                         
						<input   readonly="readonly" name="ae_st_nickName" value="${ae_st_nickName }" type="text" class="form-control"  placeholder="" datatype="*2-100"  nullmsg="请输入商品名称！"  errormsg="商品名称至少2个字符，最大100个字符"/>
					
					</div>
				</div>
			</div>
		
		</div>
		<!-- /row -->
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">发布类型<font style="color: red;">*</font></label>
					<div class="col-md-8">                                                                     
						<my:select name="bg_st_fbtpe" clazz="form-control" nameKey="SPFBLX"/>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">商品图片</label>
					<div class="col-md-8">
						<button type="button" id="imgBtn"  class="btn blue">点击进入<i class="fa fa-arrow-right"></i></button>
					</div>
				</div>
			</div>
		</div>
		<!-- /row -->
		<div class="row" id="isqgdiv" style="display: none;">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">开始时间</label>
					<div class="col-md-8">                                                                     
						<input name="bg_dt_startDate" type="text" class="form-control" readonly="readonly"/>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">结束时间</label>
					<div class="col-md-8">                                                                     
						<input name="bg_dt_endDate" type="text" class="form-control"  readonly="readonly"/>
					</div>
				</div>
			</div>
		</div>
		<!-- /row -->
		<div class="row">
			<div class="col-md-12">
				<div class="form-group">
					<label class="control-label col-md-2">商品摘要</label>
					<div class="col-md-10">
						<input name="bg_st_summary" type="text" class="form-control" placeholder="" maxlength="150"/>
					</div>
				</div>
			</div>
		</div>
		<!-- /row -->
		<div class="row">
			<div class="col-md-12">
				<div class="form-group">
					<label class="control-label col-md-2">商品介绍</label>
					<div class="col-md-10" style="z-index:49;">
						<textarea  name="bg_st_prodIntro" id="bg_st_prodIntro" style="width:100%;height:360px;top:40px;"></textarea>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<button type="button" id="saveBtn"  class="btn blue">保存</button>
		<button type="button"  id="colceupdcBtn" class="btn default" data-dismiss="modal" style="display: none;">关闭</button>
	</div>
</form>
<!-- 富文本编辑器 -->
<script>
	//实例化编辑器
	var bg_st_prodIntro_ue = UE.getEditor('bg_st_prodIntro', {
		autoHeightEnabled: false
	});
</script>
<!-- 图片预览 -->
<script>
//图片本地预览
function previewImage(file,targetid){
    if(checkPic(file)){
    	var MAXWIDTH  = 200;
        var MAXHEIGHT = 120;
        var img =$(file).parents("form").first().find("#"+targetid)[0];
        if (file.files && file.files[0]){
          img.onload = function(){
            img.width =MAXWIDTH;
            img.height = MAXHEIGHT;
          }
          var reader = new FileReader();
          reader.onload = function(evt){img.src = evt.target.result;}
          reader.readAsDataURL(file.files[0]);
        }else{
          var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
          file.select();
          var src = document.selection.createRange().text;
          img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
          div.innerHTML = "<div id=divhead style='width:"+MAXWIDTH+"px;height:"+MAXHEIGHT+"px;"+sFilter+src+"\"'></div>";
        }
    }
}
//图片删除
function delimage(filename,targetid,delname){
	var delurl=$("input[name='"+delname+"']").val();
	if(delurl!=null&&delurl!=""){
		bootbox.confirm('确认是否删除图片？',function(rs){
 			if(rs){
 				resetimage(filename,targetid,delname);
 				var url = "files/delfiles";
 				$.post(url,"delurl="+delurl,function callback(data){  
 					var resp = eval("("+data+")"); 
 					$("input[name='"+delname+"']").val("");
 					//$("#saveBtn").click();//保存一次数据
 				});
 			}
 		});
	}else{
		resetimage(filename,targetid,delname);
	}
}
//图片重置
function resetimage(filename,targetid,delname){
	var obj=$("input[name='"+filename+"']");
	var img =obj.parents("form").first().find("#"+targetid)[0];
	img.src="images/logo/default_img.jpg";
	obj.after(obj.clone().val("")); 
	obj.remove();
}
//图片选择格式限制
function checkPic(obj) {
	var pass =$(obj)[0]; 
    var picPath = pass.value;
    var type = picPath.substring(picPath.lastIndexOf(".") + 1, picPath.length).toLowerCase();
    if (type != "jpg" && type != "bmp" && type != "gif" && type != "png") {
        pass.setCustomValidity("请上传正确的图片格式");
        bootbox.alert("请上传正确的图片格式");
        var file =$(obj) ;
		file.after(file.clone().val("")); 
		file.remove();
        return false;
    }else{
    	pass.setCustomValidity('');
    	return true;
    }
}
</script>

<!-- 初始化 -->
<script>
	//初始化打开页面 
	$(function() {
		DateInit.init();//时间控件初始化
		//发布类型选择事件
		$("#addForm select[name='bg_st_fbtpe']").change(function(){
			//如果限时抢购 
			if($(this).val()=='2'){
				$("#isqgdiv").show();
			}else{
				$("#isqgdiv").hide();
			}
		});
		//进入新增商品信息层
		$("#imgBtn").click(function(){
			$('#img-my1-config').modal('show');
		});
		//图片管理层关闭
		$("#coldecolde").click(function(){
			$('#img-my1-config').modal('hide');
		});
		//点击保存事件
		$("#saveBtn").click(function(){
			checkFormValid($("#addForm"),function(){
				newMask();
				$("#addForm").form('submit',{  
				    url:"wyyf/prodInfo/prodInfoSave",  
				    onSubmit: function(param){ 
				      // param.id="123"; 
				    }, 
				    success:function(data){
				    	delMask();
				    	var id=$("#addForm input[name='bg_st_id']").val();
				    	var resp = eval("("+data+")"); 
						if(resp.success){
							//修改
							if(id!=null&&id!=""){
								RefreshCurPage();//刷新当前页
								$("#colceupdcBtn").click();
								bootbox.alert("保存成功！");
							}
							//新增
							else{
					            bootbox.confirm('新增成功，是否重置？',function(rs){
					    			if(rs){
					    				loadMainPage('wyyf/prodInfo/intoProdInfoInto');//刷新本界面
					    			}
					    		});
							}
						}else{ 
							bootbox.alert(resp.msg);
						}
				    }
				});
			});
		});
	});
	//进入修改状态时候， 初始化数据
	var updatejsonData=${updatejsonData };
	if(updatejsonData!=null&&updatejsonData!=""){
		var resp = updatejsonData;//eval("("+updatejsonData+")"); 
		if(resp.success){
			var obj=resp.result[0];
			formload($("#addForm"),obj);
			setpicfun(obj,1);
			setpicfun(obj,2);
			setpicfun(obj,3);
			setpicfun(obj,4);
			setpicfun(obj,5);
			setpicfun(obj,6);
			//富文本编辑器值初始化
			bg_st_prodIntro_ue.addListener("ready", function () {
				bg_st_prodIntro_ue.reset();
				bg_st_prodIntro_ue.setContent(obj.bg_st_prodIntro);//在富文本编辑器中设置值。
			});
			$("#bg_st_numDIV").show();
			$("#colceupdcBtn").show();
			$("#img-my1-config .modal-wide").attr("style","width: 100% !important;");
		}
	}
	//设置初始化图片函数
	function setpicfun(obj,i){
		var url=obj["bg_st_img"+i];
		if(url==null||url==""){
			url='images/logo/default_img.jpg'; 
		}
		$("#imghead"+i)[0].src=url; 
	}
	
</script>