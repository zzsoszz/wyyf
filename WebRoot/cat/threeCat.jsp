<%@ taglib uri="/lystag" prefix="my" %>
	<%
    String param = request.getParameter("param");
%>
<my:select id="threeId" name="bg_st_randid" clazz="form-control" nameKey="<%=param %>"/>