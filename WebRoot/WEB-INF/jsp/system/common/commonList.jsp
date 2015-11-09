<%@page import="com.lys.utils.DoubleUtils"%>
<%@page import="org.springframework.util.LinkedCaseInsensitiveMap"%>
<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="com.lys.utils.pagination.PageBean"%>
<%@page import="com.power.utils.PageColumn"%>
<%@page import="com.power.utils.PowerStatic"%>
<%@page import="com.lys.utils.StringUtils"%>
<style>
.pagination{visibility: visible !important;padding-left: 15px;}
#tool_tip{
	position:absolute;
	border:1px solid #DDD;
	background:#ffffff;
	padding:10px;
	color:#333;
	display:none;
	width: 300px;
	white-space: pre-wrap;word-wrap:break-word;
}
</style>
<%
	String listMark=StringUtils.toStringByObject(request.getAttribute("listMark"),true);//后台传入的标识，用于页面多个tabList 控件的使用
	if(StringUtils.hasText(listMark)){
		listMark="_"+listMark;
	}
	List<PageColumn> columnList=(List<PageColumn>)request.getAttribute("columnList");
	PageBean pages=(PageBean)request.getAttribute("pageBeanObj");
	Map<String, String> showcolumnMap=(Map<String, String>)request.getAttribute("showcolumnMap");
%>

<script type="text/javascript">
var TableAdvanced<%=listMark %> = function () {
     var initTable = function() {
        var oTable = $("#commonid<%=listMark %>").dataTable( { 
        	"aaSorting": [
   	          	<%
   	          		for(int i=0;i<columnList.size();i++){
   	          			PageColumn map=columnList.get(i);
   	          			if(map.getOrder()!=null){
   	          	%>
   	          				[<%=i %>, '<%=map.getOrder() %>'],
   	          	<%			
   	          			}
   	          		}
   	          	%>
    	          	], 
     		"aoColumns": [
				<%
					for(int i=0;i<columnList.size()-1;i++){
						%>
							{ "bSortable": false },
				<%	}
				%>
				{ "bSortable": false }
     	  	 ], 
            "iDisplayLength": -1,//显示全部，分页自己来编辑处理
            "oLanguage": {
   			    "sEmptyTable": "无数据"
   			}
        });
        $("#commonid<%=listMark %>_wrapper>div.row").remove();//移除UI界面自带的分页
       	//设置选择列显示的  事件
        $("#commonid<%=listMark %>_column_toggler input[type='checkbox']").change(function(){
            var iCol = parseInt($(this).attr("data-column"));
            var bVis = oTable.fnSettings().aoColumns[iCol].bVisible;
            oTable.fnSetColumnVis(iCol, (bVis ? false : true));//设置 某列是否可见
        });
    	<%
	  		for(int i=0;i<columnList.size();i++){
	  			PageColumn map=columnList.get(i);
	  			if( (showcolumnMap==null&&map.getHiddle()) || (showcolumnMap!=null&& showcolumnMap.get(String.valueOf(i))==null)){
	  	%>
	  		oTable.fnSetColumnVis(<%=i %>, false);//设置 某列隐藏
	  	<%			
	  			}
	  		}
	  	%>
    };
    return {
        init: function () {
            if (!jQuery().dataTable) {
                return;
            }
            initTable();
        }
    };
}();
</script>

<table id="commonid<%=listMark %>" class="table table-striped table-bordered table-hover table-full-width"  style="margin-top:20px;">
	<thead>
		<tr>
			<%
		  		for(int i=0;i<columnList.size();i++){
		  			PageColumn map=columnList.get(i);
		  			if(map.getIscheckbox()){
		  	%>	
		  				<th><input type="checkbox" class="group-checkable"/></th>
		  	<%		}else if(map.getHiddle2()){%>	
						<td style="display: none;">
				 		</td>
			<%		}else{%>
						<th><%=map.getTitle() %></th>
			<%}		
		  		}
		  	%>
		</tr>
	</thead>
	<tbody>
	<%
		Boolean isTotal=false;//是否计算某些列的和值
		Map<String, Double> someColumMap=new LinkedCaseInsensitiveMap<Double>();
		List<Map<String, Object>> dataList=pages.getList();
  		for(int j=0,b=dataList.size();j<b;j++){
  			Map<String, Object> map= dataList.get(j);//行数据
  	%>
	  	<tr >
  			<%
		  		for(int i=0;i<columnList.size();i++){
		  			PageColumn columnmap=columnList.get(i);//列对象
		  			String column=columnmap.getColumn();
		  	%>
		  	<%	if(columnmap.getHiddle2()){%>	
		 		<td style="display: none;">
		 			<input myname="<%=column %>" value="<%=map.get(column) %>" />
		 		</td>
			<%	continue;
			}%>
		  		<td <%if(columnmap.getWidth()!=null){%>width="<%=columnmap.getWidth() %>"<%} %>
		  	<%		if(columnmap.getIscheckbox()){%>
		  				><input type="checkbox" class="checkboxes" value="<%=map.get(column)%>" />
		  	<%		}else if(columnmap.getIsoperation()){%>	
		  	<%		 	if(columnmap.getIscasewhen()){%>
		  					><%=columnmap.getWhen().get(map.get(columnmap.getCases())) %> 
		  	<%			}else{%>
		  					><%=column %> 
		  	<%			}%>
  						
  			<%		}else if(columnmap.getIsnum()){%>	
							><%=(j+1)+pages.getPageSize()*(pages.getCurrPageNumber()-1) %>
							<div style="display:none;"><input type="checkbox" class="checkboxes" value="<%=map.get(column) %>" /></div>
			<%		}else{%>	
						 myattrone="<%=column %>" 
							<%if(columnmap.getIsshowcode()){%>
								><%=PowerStatic.getCodeName(columnmap.getCode()+"_"+map.get(column)) %>
							<%}else{%>
								<%
									//如果有列要计算总和
									if(columnmap.getIsSumColumn()){
										isTotal=true;
										someColumMap.put(column,DoubleUtils.add(StringUtils.toDoubleByObject(someColumMap.get(column)),StringUtils.toDoubleByObject(map.get(column)) ) );
									}
								%>
								<%if(map.get(column) != null && columnmap.getLength() > 0 && columnmap.getLength() < map.get(column).toString().length() ) {%>
									myattrtwo="<%=map.get(column)%>" ><%=map.get(column).toString().substring(0,columnmap.getLength()) %>...
								<%} else {%>
									><%=map.get(column)==null?"":map.get(column) %>
								<%} %>
							<%} %>
			<%		}%>
		  		</td>
		  	<%	}
		  	%>
		</tr>
  	<%	}%>
  	<%	if(isTotal){%>
  		<tr>
			<%
		  		for(int i=0;i<columnList.size();i++){
		  			PageColumn columnmap=columnList.get(i);
		  			if(i==0){
		  	%>	
		  				<td>合计</td>
		  	<%		}else{%>	
						<td><%= someColumMap.get(columnmap.getColumn())!=null?someColumMap.get(columnmap.getColumn()):"" %></td>
			<%		}		
		  		}
		  	%>
		</tr>	
  	<%	}%>
	</tbody>
</table>

<div class="row">
	<ul id="pageUL<%=listMark %>" class="pagination" style="width:auto !important;float: left;">
	     <% if(pages.isFirst()){%>
				<li><a href="javascript:void(0);">首页</a></li><li><a href="javascript:void(0);">上一页</a></li>
		 <%	}else{%>
				<li><a href="javascript:void(0);" start="1" limit="<%=pages.getPageSize() %>" >首页</a></li>
				<li><a href="javascript:void(0);" start="<%=pages.getPrevious() %>" limit="<%=pages.getPageSize() %>">上一页</a></li>
		 <% }
		 	for(int i=pages.getBetweenIndex().getStartIndex();i<=pages.getBetweenIndex().getEndIndex();i++){
		 %>
		 		<li><a 
		 	<%	 if(pages.getCurrPageNumber() == i){ %>
		 			 style="color:red !important;"
		 	<%	 }  %>
		 		 href="javascript:void(0);" start="<%=i %>" limit="<%=pages.getPageSize() %>" ><%=i %></a></li>
		 <% }  %>
		 <% if(pages.isLast()){ %>
				<li><a href="javascript:void(0);">下一页</a></li><li><a href="javascript:void(0);">尾页</a></li>
		 <% }else{ %>
				<li><a href="javascript:void(0);" start="<%=pages.getNext() %>" limit="<%=pages.getPageSize() %>" >下一页</a></li>
				<li><a href="javascript:void(0);" start="<%=pages.getTotalPages() %>" limit="<%=pages.getPageSize() %>" >尾页</a></li> 
		 <% }  %>
	</ul>
	
	<div style="padding-top:20px;padding-right:15px; text-align: left;float: right;">
		<div class="btn-group dropup ">
			<a class="btn default " href="#" data-toggle="dropdown" style="padding: 1px 4px;">显示列<i class="fa fa-angle-up"></i></a>
			<div id="commonid<%=listMark %>_column_toggler" style="height:220px;overflow: auto;" class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">
					<%
				  		for(int i=0;i<columnList.size();i++){
				  			PageColumn map=columnList.get(i);
				  	%>
				  		<label>
					  		<input name="ISSHOWCOLUMS" type="checkbox"  mytattr="<%=map.getTitle() %>" value="<%=i %>" data-column="<%=i %>"
					  		<%
					  			//控制初始化列是否显示与隐藏
					  		if(!( (showcolumnMap==null&&map.getHiddle()) || (showcolumnMap!=null&& showcolumnMap.get(String.valueOf(i))==null))){%> checked <%}
					  		%>
					  		 /><%=map.getTitle() %>
				  		 </label>
				    <% } %>
			</div>
		</div>
	</div>
	
	<div  style="padding-top:20px;padding-right:15px; text-align: right;width:440px !important; float: right;">
		<div class="control-label col-md-12" style="font-size:12px;padding-right: 0px;">
		 	共<%=pages.getTotalElements() %>条数据,当前<font color="red"><%=pages.getCurrPageNumber() %>/<%=pages.getTotalPages() %></font>页,每页显示
			<select id="changePagesiz<%=listMark %>" style="border: 1px solid #888;">
				<%if(pages.getPageSize()!=5&&pages.getPageSize()!=10&&pages.getPageSize()!=20&&pages.getPageSize()!=50){%>
					<option selected="selected" value="<%=pages.getPageSize() %>"><%=pages.getPageSize() %></option>
				<%} %>
				<option <%if(pages.getPageSize()==5){%> selected="selected" <%} %> value="5">5</option>
				<option <%if(pages.getPageSize()==10){%> selected="selected" <%} %> value="10">10</option>
				<option <%if(pages.getPageSize()==20){%> selected="selected" <%} %> value="20">20</option>
				<option <%if(pages.getPageSize()==50){%> selected="selected" <%} %> value="50">50</option>
			</select>
			条, 跳转到第
					<input id="changePageIndex<%=listMark %>" size="3" onkeyup="this.value=this.value.replace(/\D/g,'')" 
												onafterpaste="this.value=this.value.replace(/\D/g,'')"
												onkeypress="enterPress<%=listMark %>(event)" onkeydown="enterPress<%=listMark %>()" style="border: 1px solid #888;">
			页<input id="changePageIndexbtn<%=listMark %>"  type="button" class="btn blue" value="GO" style=" padding: 2px 5px;margin: -2px 0 0 5px;font-weight: bold;font-size: 12px;"/>
		</div>
	</div>
	
	
	
</div>
<script>
	 //初始化控件 table列表
	 TableAdvanced<%=listMark %>.init();
	 //设置页大小
	 $("#changePagesiz<%=listMark %>").change(function(){
		 gongyongtiaozhuan<%=listMark %>(1,$(this).val());//刷新列表
	 });
	 //跳转页码
	 $("#changePageIndexbtn<%=listMark %>").click(function(){
		 var pageindex=$("#changePageIndex<%=listMark %>").val();
		 if(pageindex!=null&&pageindex!=""){
			 gongyongtiaozhuan<%=listMark %>(pageindex,$("#changePagesiz<%=listMark %>").val());//刷新列表
		 }
	 });
	//翻页
	 $("#pageUL<%=listMark %> li").on('click', function() {
		 var start=$(this).find("a").attr("start");
		 var limit=$("#changePagesiz<%=listMark %>").val();//$(this).find("a").attr("limit");
		 if(start!=null&&limit!=null){
			 gongyongtiaozhuan<%=listMark %>(start,limit);//刷新列表
		 }
	 });
	//列表标题行复选框点击
	 $("#commonid<%=listMark %> input[type='checkbox'].group-checkable").click(function(){
		 $("#commonid<%=listMark %> input[type='checkbox'].checkboxes").prop("checked",$(this).prop("checked"))
	 });
	//鼠标悬停显示剩余内容事件
	$("#commonid<%=listMark %> td[myattrtwo]").mouseover(function(e){
		var a = $(this).attr("myattrtwo");
		 if(a){
			$(this).css("cursor", "pointer");
		    var tooltip = "<div id='tool_tip'>"+ a +"<\/div>"; //创建 div 元素
			$(this).append(tooltip);	//把它追加到文档中
			//$("#tool_tip").css({"top": (e.pageY+20) + "px","left": (e.pageX+10)  + "px"}).show("fast");	  //设置x坐标和y坐标，并且显示
			$("#tool_tip").css({"top": ( $(this).position().top+$(this).outerHeight() ) + "px","left": $(this).position().left + "px"}).show("fast");	  //设置x坐标和y坐标，并且显示
		 }
    }).mouseout(function(){		
		$("#tool_tip").remove();   //移除 
    }).mousemove(function(e){
		//$("#tool_tip").css({"top": (e.pageY+20) + "px","left": (e.pageX+10)  + "px"});
	});
//刷新当前页码
function RefreshCurPage<%=listMark %>(){
	gongyongtiaozhuan<%=listMark %>(<%=pages.getCurrPageNumber() %>,<%=pages.getPageSize() %>);
}
//点击查询按钮执行的函数，目的为了让起设置页大小生效
function ClickFindPage<%=listMark %>(){
	gongyongtiaozhuan<%=listMark %>(1,<%=pages.getPageSize() %>);
}
//公共跳转页
function gongyongtiaozhuan<%=listMark %>(pageIndex,pageSize){
	var parmas="";
	if(pageIndex!=null){
		parmas+="&pageIndex="+pageIndex;//页码
	}
	if(pageSize!=null){
		parmas+="&pageSize="+pageSize;//页大小
	}
	loadotherPage<%=listMark %>(parmas);//在具体 List.jsp中。
	/*//替换主题内容的函数
	function loadotherPage(params) {
		var param=$("#queryForm").serialize();
		jQuery("#tableList").load("system/sysuser/sysuserList",param+"&"+params,function(response,status,xhr){
			if(xhr.status==403){
				$(this).html(xhr.responseText);
			}else if(xhr.status==404){
				$(this).html(xhr.responseText);
			}
		});	
	}*/
}
//跳转文本框中的回车事件
function enterPress<%=listMark %>(e){ //传入 event 
	var e = e || window.event; 
	if(e.keyCode == 13){ 
		$("#changePageIndexbtn<%=listMark %>")[0].click();
	} 
} 

</script>


