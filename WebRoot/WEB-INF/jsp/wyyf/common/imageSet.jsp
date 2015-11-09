<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@page import="org.springframework.util.LinkedCaseInsensitiveMap"%>
<%@page import="com.lys.utils.StringUtils"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@ taglib uri="/lystag" prefix="my" %>
<style>
.myfileclass{
	height: auto;top: 0px;right: 0px;margin: 0px;opacity: 0;font-size: 23px; direction: ltr;cursor: pointer; width:176px;  margin-top: -34px;
}
.imgylclass{
	height: 120px;width:200px;border:0;
}
</style>
<%
	List<Map<String,Object>> prodList=(List<Map<String,Object>>)request.getAttribute("workExps");	
	String worklname=StringUtils.toStringByObject(request.getAttribute("worklname"));
	if(prodList==null){
		prodList=new ArrayList<Map<String,Object>>();
	}
%>
<div class="row" myattr="update">
	<div class="col-md-12">
		<div class="form-group">
			<label class="control-label col-md-2">新增图片</label>
			<div class="col-md-10">
				<button type="button" onclick="addtabobj(this,'tab1_All')" class="btn blue" myattrname="<%=worklname %>">点击新增<i class="fa fa-arrow-down"></i></button>
			</div>
		</div>
	</div>
</div>
<div id="tab1_All">
<%
	
	for(int i=0,b=prodList.size();i<b+1;i++){
		Map<String,Object> workmap=new LinkedCaseInsensitiveMap<Object>();
		if(b>0&&i<b){
			workmap=prodList.get(i);
		}
		String wymark=worklname+"["+i+"]";
		String divId="tab_"+worklname+i+new Date().getTime();
		if(i==b){
			wymark="wduserzblist";
			divId="tab1_demo";
		}
		String url=StringUtils.toStringByObject(workmap.get("ag_st_url"));
		request.setAttribute("tp_name", wymark+".ag_st_mark");//置于页面对象，用于my:select加载
		request.setAttribute("tpmrz", StringUtils.toStringByObject(workmap.get("ag_st_mark")));//置于页面对象，用于my:select加载
%>
	<div id="<%=divId %>" class="addBorder">
		<div class="row" >
			<div style="display:none;">
				<input  type="text" name="<%=wymark %>.ag_st_id" markName="ID" markCode="ag_st_id" value="<%=StringUtils.toStringByObject(workmap.get("ag_st_id"))  %>"/>
				<input name="<%=wymark %>.ag_st_url" value="<%=url  %>">
				<input name="<%=wymark %>.ag_st_addUserId" value="<%=wymark %>.fileobj"/>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">图片类型<span style="color: red;">*</span></label>
					<div class="col-md-8">
						<my:select name="${tp_name}" clazz="form-control" nameKey="${wjlx}" initSelectedKey="${tpmrz}"/>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<div class="col-md-8">
						<div id="preview">
						<a target="_blank" href="<%if(StringUtils.hasText(url)){ %><%=url %><%}else{%>images/logo/default_img.jpg<%} %>">
						    <img myid="imghead<%=wymark %>." class="imgylclass" src='<%if(StringUtils.hasText(url)){ %><%=url %><%}else{%>images/logo/default_img.jpg<%} %>'></a><!--无预览时的默认图像，自己弄一个-->
						</div>
						<span class="btn green fileinput-button" myattr="update">
						    <i class="fa fa-plus"></i>
						    <span>选择图片</span>
						    <input name="<%=wymark %>.fileobj" type="file" onchange="previewImage(this,'imghead<%=wymark %>.')" class="myfileclass"/>
						</span>
					</div>
					<div class="col-md-4">
						<%--
						<button class="btn btn-sm red" type="button" onclick="delimage('<%=wymark %>.ag_st_addUserId','imghead<%=wymark %>.','<%=wymark %>.ag_st_url')" style="width:50px;">删除</button>
						 --%>
						<button class="btn btn-sm red" onclick="deltabobj(this)"  type="button" myattr="update">删除<i class="fa fa-trash-o"></i></button>
					</div>
				</div>
			</div>
			
		</div>
	</div>
<%	} %>
</div>

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
		if(myattrname=="<%=worklname %>"){
			htmltab=tab1_;
			htmlnum=tab1_num++;
		}
		$("#"+targetid).append('<div id="tab_'+myattrname+new Date().getTime()+'" class="addBorder">'+htmltab.replace(/wduserzblist\./g, myattrname+"["+htmlnum+"].")+'</div>');
	}
	//移除商品当前对象
	function deltabobj(obj){
		var parents=$(obj).parents("div.addBorder").first();
		var id=parents.find("input[markName='ID']").val();
		//如果该条数据已经入库，删除时要请求 
		if(id!=null&&id!=""){
			/*bootbox.confirm('确定是否删除？',function(rs){
				if(rs){
					var url = "wyyf/common/delfile";
					$.post(url,"id="+id,function callback(data){  
						var resp = eval("("+data+")"); 
						if(resp.success){
							parents.remove();
							bootbox.alert(resp.msg);
						}else{ 
							bootbox.alert(resp.msg);
						}
					});
				}else{
				  //取消操作
				}
			}); */
			bootbox.alert("删除无效，该处只能进行查看。");
		}else{
			parents.remove();
		}
	}
</script>
<!-- 图片预览 -->
<script>
//图片本地预览
function previewImage(file,targetid){
    if(checkPic(file)){
    	var MAXWIDTH  = 200;
        var MAXHEIGHT = 120;
        var img =$(file).parents("form").first().find("img[myid='"+targetid+"']")[0];
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
//图片删除--本功能暂不使用
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

//图片重置--本功能暂不使用
function resetimage(filename,targetid,delname){
	var obj=$("input[name='"+filename+"']");
	var img =obj.parents("form").first().find("img[myid='"+targetid+"']")[0];
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
 
