<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
	if (session.getAttribute("currentUser") == null) {
		// response.sendRedirect("login.jsp");
		// 也可以
		response.sendRedirect(request.getContextPath() + "/login.jsp");
		// 一定要 return
		return;
	}

	String mainPage = (String) request.getAttribute("mainPage");
	if (mainPage == null || mainPage == "") {
		mainPage = "common/default.jsp";
	}
	//最后 设完值 还要 放入request
	request.setAttribute("mainPage", mainPage);
%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap-theme.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/equipment.css">
<!-- jquery要在 bootstrap之前引入 -->
<script
	src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
</head>
<body>
	<!-- 栅格布局 -->

	<div class="container">
		<!-- 3行 3个row -->
		<div class="row">
			<!-- 12 占12列 -->
			<div class="col-md-12">
				<jsp:include page="common/head.jsp"></jsp:include>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3" style="padding-top: 45px;">
				<jsp:include page="common/menu.jsp"></jsp:include>
			</div>
			<div class="col-md-9">
				<!-- 导航条 -->
				<div style="padding-top: 45px;">
					<ol class="breadcrumb">
						<!-- span定义了一个图标 -->
						<li><span class="glyphicon glyphicon-home"></span>&nbsp;<a
							href="${pageContext.request.contextPath}/main.jsp">主页</a></li>
						<li class="active">${modeName }</li>
					</ol>
				</div>
				<jsp:include page="${mainPage }"></jsp:include>
			</div>
		</div>

		<div class="row">
			<div class="col-md-12">
				<jsp:include page="common/foot.jsp"></jsp:include>
			</div>
		</div>


	</div>


</body>
</html>