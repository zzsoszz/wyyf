<%@ taglib uri="/lystag" prefix="my" %>
<%
    String param = request.getParameter("param");
%>
<my:select id="twoDivId"  clazz="form-control" nameKey="<%=param %>"/>
<input id="result" type="hidden" value="<%=param %>" />
<script language="javascript" type="text/javascript">




			$(document).ready(function() {
				var p1=$("#result").attr("value")+"_"+$("#twoDivId").val();
				//var p1 = $("#result").attr("value")+"_"+$(this).children('option:selected').val();//这就是selected的值
				
				$("#threeDivID").load('cat/threeCat.jsp?param='+p1);
			
			     $("#twoDivId").change(function() {
			    	 var p1 = $("#result").attr("value")+"_"+$(this).children('option:selected').val();//这就是selected的值
					
						$("#threeDivID").load('cat/threeCat.jsp?param='+p1);
						
					});

			});
		</script>