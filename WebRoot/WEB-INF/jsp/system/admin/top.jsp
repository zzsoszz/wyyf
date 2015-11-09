<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>

<div class="header navbar navbar-inverse navbar-fixed-top">
	<div class="header-inner">
    	<!-- BEGIN LOGO -->  
        <a class="navbar-brand" href="javascript:void(0);" style="line-height: 40px;">
            <img class="img-responsive" alt="logo" src="images/logo/logo_login.png" style="height: 35px;display: inline;"/>
			<span style="color: #fff;margin-left: 5px;"><span style="color: #ff9946;">后台</span>管理系统</span>
        </a>
        <div class="hor-menu hidden-sm hidden-xs">
			<ul id="topUlOne" class="nav navbar-nav">
				<c:forEach items="${CDLIST1}" var="row">
					<c:choose>
						<c:when test="${row.aa_st_active=='1' }">
							<sec:authorize ifAnyGranted="${row.aa_st_mark}">
								<li id="<c:out value='${row.aa_st_id}'/>">
									<a href="<c:out value='${row.aa_st_url}'/>" myattr="${row.aa_st_mark}"><c:out value='${row.aa_st_title}' /></a>
								</li>
							</sec:authorize>
						</c:when>
						<c:otherwise>
							<li id="<c:out value='${row.aa_st_id}'/>">
								<a href="<c:out value='${row.aa_st_url}'/>" myattr="${row.aa_st_mark}"><c:out value='${row.aa_st_title}' /></a>
							</li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ul>
		</div>
		
        <!-- BEGIN RESPONSIVE MENU TOGGLER --> 
		<a href="javascript:;" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
			<img src="assets/img/menu-toggler.png" alt="" />
		</a>
        <ul class="nav navbar-nav pull-right"> 
			<li class="dropdown user">
				<div class="dropdown-toggle">
				<img alt="" src="assets/img/avatar1_small.png" width="30" height="30"/>
				<span class="username">${username }</span>
				</div>
			</li>
			<li class="dropdown zhuxiao">
				<div class="dropdown-toggle">
				<a href="javascript:void(0);" style="color: #DDD;"><i class="fa fa-question"></i>帮助</a>
				</div>
			</li>
			<li class="dropdown zhuxiao">
				<div class="dropdown-toggle">
				<a href="/logout" style="color: #DDD;"><i class="fa fa-power-off"></i>注销</a>
				</div>
			</li>
		</ul>
		<!-- END USER LOGIN DROPDOWN -->
    </div>
</div>
<div class="clearfix"></div>
