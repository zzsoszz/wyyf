<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE HTML>
<html>
  <head>
  <script type="text/javascript">
  /*
  try{
      if (/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)) {
          window.location.href="http://m.wangzhong.com/wap";
      }else{
          window.location.href="http://www.wangzhong.com/index";
      }
  }catch(e){}
  */
  
  try{
      if (/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)) {
          window.location.href="${ctx}/wap";
      }else{
          window.location.href="${ctx}/index";
      }
  }catch(e){}
  
  </script>
  
<meta property="wb:webmaster" content="4b951ff616287774" />
  
    
   <!--  <meta http-equiv="refresh" content="0;url=/index"> -->
    <title>正在进入...</title>
  </head>
  <body>
  </body>
</html>
