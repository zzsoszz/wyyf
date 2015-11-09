<%-- 面包屑公用界面 --%>
<%@ page language="java" 	pageEncoding="utf-8"%>

<ul class="page-breadcrumb breadcrumb">
	<li>
		<i class="fa fa-home"></i>
		<a href="javascript:void(0);" id="cai_one"></a> 
		<i class="fa fa-angle-right"></i>
	</li>
	<li>
		<a href="javascript:void(0);" id="cai_two"></a> 
		<i class="fa fa-angle-right"></i>
	</li>
	<li>
		<a href="javascript:void(0);" id="cai_three"></a>
	</li>
</ul>
<script>
	$("#cai_one").text($("#topUlOne>li.active>a").text());
	$("#cai_two").text($("#leftUl>li.active>a").text());
	$("#cai_three").text($("#leftUl>li.active ul>li.active>a").text());
</script>