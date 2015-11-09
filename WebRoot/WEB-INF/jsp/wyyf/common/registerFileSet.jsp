<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@page import="org.springframework.util.LinkedCaseInsensitiveMap"%>
<%@page import="com.lys.utils.StringUtils"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@ taglib uri="/lystag" prefix="my" %>
<%
	List<Map<String,Object>> prodList=(List<Map<String,Object>>)request.getAttribute("workExps");	
	String worklname=StringUtils.toStringByObject(request.getAttribute("worklname"));
	if(prodList==null){
		prodList=new ArrayList<Map<String,Object>>();
	}
%>
<div myattr="update">
	<button type="button" onclick="addtabobj(this,'tab1_All')" class="btn blue" myattrname="<%=worklname %>">点击新增<i class="fa fa-arrow-down"></i></button>
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
			<%if(StringUtils.hasText(url)){ %>
				<a href="<%=url %>" target="_blank">下载附件</a>
			<%}else{%>
				
			<%} %>
			<img myid="imghead<%=wymark %>." class="imgylclass" src=''><!--无预览时的默认图像，自己弄一个-->
			<my:select name="${tp_name}" otherAttr=" style='width: 100px;display: inline;' " nameKey="${wjlx}" initSelectedKey="${tpmrz}"/>
			 <input name="<%=wymark %>.fileobj" type="file" style="display: inline;"   myattr="update"/>
			 <button class="btn btn-sm red" onclick="deltabobj(this)"  type="button" myattr="update">删除<i class="fa fa-trash-o"></i></button>
			
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
			alert("删除无效，该处只能进行查看。");
		}else{
			parents.remove();
		}
	}
</script>
 
