<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
	<div class="page-sidebar navbar-collapse collapse">
			<ul id="topUlTwo" class="page-sidebar-menu visible-sm visible-xs" style="display: none !important;">
				<c:forEach items="${CDLIST1}" var="row">
					<c:choose>
						<c:when test="${row.aa_st_active=='1' }">
							<sec:authorize ifAnyGranted="${row.aa_st_mark}">
								<li id="<c:out value='${row.aa_st_id}'/>">
									<a href="<c:out value='${row.aa_st_url}'/>"><c:out value='${row.aa_st_title}' /></a>
								</li>
							</sec:authorize>
						</c:when>
						<c:otherwise> 
							<li id="<c:out value='${row.aa_st_id}'/>">
								<a href="<c:out value='${row.aa_st_url}'/>"><c:out value='${row.aa_st_title}' /></a>
							</li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ul>
		    <ul id="leftUl" class="page-sidebar-menu">
		        <li style="background: none;border: none;">
		        	<div class="sidebar-toggler hidden-sm hidden-xs"></div>
		        </li>
		        <c:forEach items="${CDLIST2}" var="row"  >
			        <c:choose>
						<c:when test="${row.aa_st_active=='1' }">
							<sec:authorize ifAnyGranted="${row.aa_st_mark}">
								<li id="<c:out value='${row.aa_st_id}'/>">
						        	<a href="javascript:void(0);" onclick="selectDAMenu(this);">
									    <i class="<c:out value='${row.aa_st_image}'/>"></i>
									    <span class="title"><c:out value='${row.aa_st_title}'/></span>
<%--									    <span class="selected"></span>--%>
									    <span class="arrow"></span>
									</a>
									<c:if test="${ !empty row.childrenList  }">
										<ul class="sub-menu" style="display: none;">
											<c:forEach items="${row.childrenList}" var="row3"  >
												
												<c:choose>
													<c:when test="${row3.aa_st_active=='1' }">
														<sec:authorize ifAnyGranted="${row3.aa_st_mark}">
														    <li id="<c:out value='${row3.aa_st_id}'/>">
																<a href="javascript:void(0);" onclick="selectMenu(this);loadMainPage('<c:out value='${row3.aa_st_url}'/>');">
<%--																    <span class="badge badge-roundless badge-important"><c:out value='${fn:substring(row3.aa_st_title, 0, 1)}'/></span>--%>
																    <c:out value='${row3.aa_st_title}'/>
																    <span class="selected"></span>
																</a>
															</li>
														</sec:authorize>
													</c:when>
													<c:otherwise>
														<li id="<c:out value='${row3.aa_st_id}'/>">
															<a href="javascript:void(0);" onclick="selectMenu(this);loadMainPage('<c:out value='${row3.aa_st_url}'/>');">
<%--															    <span class="badge badge-roundless badge-important"><c:out value='${fn:substring(row3.aa_st_title, 0, 1)}'/></span>--%>
															    <c:out value='${row3.aa_st_title}'/>
															    <span class="selected"></span>
															</a>
														</li>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</ul>
									</c:if>
						        </li>
					        </sec:authorize>
						</c:when>
						<c:otherwise>
							<li id="<c:out value='${row.aa_st_id}'/>">
					        	<a href="javascript:void(0);" onclick="selectDAMenu(this);">
								    <i class="<c:out value='${row.aa_st_image}'/>"></i>
								    <span class="title"><c:out value='${row.aa_st_title}'/></span>
<%--								    <span class="selected"></span>--%>
								    <span class="arrow"></span>
								</a>
								<c:if test="${ !empty row.childrenList  }">
									<ul class="sub-menu" style="display: none;">
										<c:forEach items="${row.childrenList}" var="row3"  >
											
											<c:choose>
												<c:when test="${row3.aa_st_active=='1' }">
													<sec:authorize ifAnyGranted="${row3.aa_st_mark}">
													    <li id="<c:out value='${row3.aa_st_id}'/>">
															<a href="javascript:void(0);" onclick="selectMenu(this);loadMainPage('<c:out value='${row3.aa_st_url}'/>');">
<%--															    <span class="badge badge-roundless badge-important"><c:out value='${fn:substring(row3.aa_st_title, 0, 1)}'/></span>--%>
															    <c:out value='${row3.aa_st_title}'/>
															    <span class="selected"></span>
															</a>
														</li>
													</sec:authorize>
												</c:when>
												<c:otherwise>
													<li id="<c:out value='${row3.aa_st_id}'/>">
														<a href="javascript:void(0);" onclick="selectMenu(this);loadMainPage('<c:out value='${row3.aa_st_url}'/>');">
<%--														    <span class="badge badge-roundless badge-important"><c:out value='${fn:substring(row3.aa_st_title, 0, 1)}'/></span>--%>
														    <c:out value='${row3.aa_st_title}'/>
														    <span class="selected"></span>
														</a>
													</li>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</ul>
								</c:if>
					        </li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
		    </ul>
		   
		</div>
<script>
//二级导航点击事件
function selectDAMenu(v){
	$('#leftUl>li').removeClass('active');
	$(v).parent().addClass("active");
}
//三级导航点击事件
function selectMenu(v){
	$('#leftUl ul.sub-menu>li').removeClass('active');
	$(v).parent().addClass("active");
}
</script>