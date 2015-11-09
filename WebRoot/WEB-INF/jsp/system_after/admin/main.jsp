<%@ page language="java" 	pageEncoding="utf-8"%>
<%@ include file="../../commons/jsplib.jsp"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
  <head>
    <title>后台管理系统</title>
    <base href="${ctx}/">
 	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
 	<meta http-equiv="X-UA-Compatible" content="IE=edge">
 	<!-- 禁止IE生成缓存... -->
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Expires" content="0">
    <!-- 总体样式 -->
    <link href="assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <!-- 主体样式 -->
    <link href="assets/css/style-metronic.css" rel="stylesheet" type="text/css" linkid="metronic"/>
    <link href="assets/css/themes/darkBlue.css" rel="stylesheet" type="text/css" id="style_color"/>
    <link href="assets/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="assets/css/style-responsive.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" href="assets/plugins/data-tables/DT_bootstrap.css" />
    <!-- 时间控件 -->
    <link rel="stylesheet" type="text/css" href="assets/plugins/bootstrap-datepicker/css/datepicker.css" />
	<link rel="stylesheet" type="text/css" href="assets/plugins/bootstrap-datetimepicker/css/datetimepicker.css" />  
	<link href="assets/css/plugins.css" rel="stylesheet" type="text/css"/> 
    <!-- 验证控件css -->
	<link href="assets/plugins/validform/css/style.css" rel="stylesheet" type="text/css"/> 
	<!-- 自定义CSS-->
	<link href="css/style.css" rel="stylesheet" type="text/css"/> 
	<!-- END THEME STYLES -->
	<link rel="shortcut icon" href="${ctx}/images/ico/favicon.ico" />
	<!-- fwb -->
	<script type="text/javascript">
		UEDITOR_HOME_URL="${ctx}/plugin/fwb/";
	</script>
  </head>
  
  <body class="page-header-fixed">
  	<!-- 头部  -->
	<%@ include file="top.jsp"%>
	<!-- 主题  -->
	<div class="page-container">
		<!-- 左侧菜单  -->
		<%@ include file="left.jsp"%>
		<div class="page-content">
			<div  id="centerMain" ></div>
		</div>
	<!-- 底部  -->
	<jsp:include page="foot.jsp" />
	</div>
	<!--[if lt IE 9]>
	<script src="assets/plugins/respond.min.js"></script>
	<script src="assets/plugins/excanvas.min.js"></script> 
	<![endif]-->
	<!-- 主体 -->
	<script src="assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
	<script src="assets/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>    
	<script src="assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="assets/plugins/bootstrap-hover-dropdown/twitter-bootstrap-hover-dropdown.min.js" type="text/javascript" ></script>
	<script src="assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="assets/plugins/jquery.blockui.min.js" type="text/javascript"></script>  
	<script src="assets/plugins/jquery.cookie.min.js" type="text/javascript"></script>
	<script src="assets/plugins/uniform/jquery.uniform.min.js" type="text/javascript" ></script>
	<!-- 控制列表页的列显示和页中行排序 -->
	<script  src="assets/plugins/data-tables/jquery.dataTables.min.js"></script>
	<script src="assets/plugins/data-tables/DT_bootstrap.js"></script>
	<!-- 时间控件 -->
	<script type="text/javascript" src="assets/plugins/fuelux/js/spinner.min.js"></script> 
	<script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript" src="assets/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript" src="assets/plugins/jquery-inputmask/jquery.inputmask.bundle.min.js"></script> 
	<!-- 表单附带文件上传 局部刷新需要的JS -->
	<script type="text/javascript" src="js/jquery.easyui.min.js" type="text/javascript"></script>
	<!-- 表单验证 -->
	<script type="text/javascript" src="assets/plugins/validform/js/Validform_v5.3.2.js"></script>
	<!-- Alert -->
	<script src="assets/plugins/bootbox/bootbox.js"></script>
	<!-- 初始化 -->
	<script src="assets/scripts/app.js"></script>
	<script src="assets/scripts/dateInit.js"></script>
	<!-- MY script -->
	<script src="js/commom.js"></script>
  </body>
</html>
<script>
	//UI界面初始化
	jQuery(document).ready(function() {       
	   App.init();
	});
	//初始化菜单选中效果
	$(function(){
		var functionid1='${functionid1}';
		$("#topUlOne,#topUlTwo").find("li[id='"+functionid1+"']").addClass("active");
		var functionid2='${functionid2}';
		$("#leftUl li[id='"+functionid2+"']").addClass("active").addClass("open").find("a:eq(0) span:last").addClass("open");
		var functionid3='${functionid3}';
		$("#leftUl li[id='"+functionid3+"']").addClass("active").parent().css("display","block");
	});
	//初始化打开页面 
	$(document).ready(function() {
		var url='${functionid3Url}';
		if(url!=null&&url!=""){
			loadMainPage(url);
		}
		
	});
	//替换主题内容的函数
	function loadMainPage(v) {
		newMask();
		jQuery("#centerMain").load(v,"r="+new Date().getTime(),function(response,status,xhr){
			if(xhr.status==403){
				$(this).html(xhr.responseText);
			}else if(xhr.status==404){
				$(this).html(xhr.responseText);
			} 
			delMask();
		});	
	}
</script> 

