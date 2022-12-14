<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>PlayData Book Rental Service</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/css/main.css" />
<noscript>
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/assets/css/noscript.css" />
</noscript>
</head>
<body class="is-preload">

	<!-- Header -->
	<header id="header">
		<a href="index" class="title">PlayData Book Rental Service</a>
		<nav>
			<ul>
				<li><a href="index">Home</a></li>
				<li><a href="rental">대여</a></li>
				<li><a href="returnBook" class="active">반납</a></li>
				<li><a href="viewList">조회</a></li>
			</ul>
		</nav>
	</header>

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
		<section id="main" class="wrapper">
			<div class="inner">
				<h1 class="major">책 반납하기</h1>

				<!-- Form -->
				<section>
					<h2>작성해주세요</h2>
					<c:if test="${bookVO == null}">
						<form method="post" action="returnBookCheck">
							<div class="row gtr-uniform">
								<div class="col-12">
									<input type="text" name="bookSerialNumber" id="demo-email"
										value="${rentalVO.bookSerialNumber}" placeholder="책 일련번호" />
								</div>
								<div class="col-12">
									<ul class="actions">
										<li><input type="submit" value="조회" class="primary" /></li>
									</ul>
								</div>
							</div>
						</form>
					</c:if>
					<c:if test="${bookVO != null}">
						<form method="post" action="realReturnBook">
							<div class="row gtr-uniform">
								<div class="col-12">
									<input type="text" name="bookSerialNumber" id="demo-email"
										value="${bookVO.bookSerialNumber}" required="required"
										readonly="readonly" />
								</div>

								<div class="col-6 col-12-xsmall">
									<input type="text" name="bookTitle" id="demo-name"
										value="${bookVO.bookTitle}" required="required"
										readonly="readonly" />
								</div>

								<div class="col-6 col-12-xsmall">
									<input type="text" name="bookWriter" id="demo-name"
										value="${bookVO.bookWriter}" required="required"
										readonly="readonly" />
								</div>

								<div class="col-6 col-12-xsmall">
									<input type="text" id="demo-name"
										value="${rentalVO.rentalStart}" required="required"
										readonly="readonly" />
								</div>

								<div class="col-6 col-12-xsmall">
									<input type="text" id="demo-name" value="${bookVO.bookStatus}"
										required="required" readonly="readonly" />
								</div>

								<div class="col-12">
									<ul class="actions">
										<li><input type="submit" value="반납" class="primary" /></li>
									</ul>
								</div>
							</div>
						</form>
					</c:if>
				</section>

			</div>
		</section>

	</div>

	<!-- Footer -->
	<footer id="footer" class="wrapper alt">
		<div class="inner">
			<ul class="menu">
				<li>&copy; Made by Jeongil Jeong</li>
				<li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
			</ul>
		</div>
	</footer>

	<!-- Scripts -->
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/jquery.scrollex.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/jquery.scrolly.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/browser.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/breakpoints.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/util.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/main.js"></script>
	<script>
		var msg = '${msg}';
		if (msg != "") {
			alert(msg);
		}
	</script>
</body>
</html>