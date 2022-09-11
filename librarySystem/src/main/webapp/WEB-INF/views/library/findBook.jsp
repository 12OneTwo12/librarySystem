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
				<li><a href="findBook" class="active">책 찾아보기</a></li>
				<li><a href="viewPopularity">책 인기 순위 보기</a></li>
				<li><a href="myRentalRecord">내 대여 기록 보기</a></li>
				<li><a href="findBySerialNumber">일련번호로 조회하기</a></li>
			</ul>
		</nav>
	</header>

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
		<section id="main" class="wrapper">
			<div class="inner">
				<h1 class="major">내 대여 기록 보기</h1>

				<!-- Form -->
				<section>
					<form method="post" action="findBook">
						<div class="row gtr-uniform">
							<div class="col-12">
								<select name="searchType" id="demo-category">
									<option value="title"
										${pageVO.cri.searchType == 'title' ? 'selected' : ''}>제목</option>
									<option value="writer"
										${pageVO.cri.searchType == 'writer' ? 'selected' : ''}>작가</option>
									<option value="serialNumber"
										${pageVO.cri.searchType == 'serialNumber' ? 'selected' : ''}>일련
										번호</option>
								</select>
							</div>

							<div class="col-6 col-12-xsmall">
								<input type="text" name="searchKey" id="demo-name"
									value="${pageVO.cri.searchKey}" placeholder="검색어를 입력해주세요" />
							</div>
							<div class="col-6 col-12-xsmall">
								<ul class="actions">
									<li><input type="submit" value="검색" class="primary" /></li>
								</ul>
							</div>
						</div>
					</form>
				</section>
				<section>
					<div class="table-wrapper">
						<table class="alt">
							<thead>
								<tr>
									<th>Book Serial Number</th>
									<th>Title</th>
									<th>Writer</th>
									<th>Status</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="vo" items="${list}">
									<tr>
										<td>${vo.bookSerialNumber}</td>
										<td>${vo.bookTitle}</td>
										<td>${vo.bookWriter}</td>
										<td>${vo.bookStatus}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div class="pagination">
							<a
								href="findBook?searchKey=${pageVO.cri.searchKey}&pageNum=1&amount=${pageVO.amount}"
								class="button small">첫 페이지로 이동</a>

							<c:if test="${pageVO.prev}">
								<a
									href="findBook?searchKey=${pageVO.cri.searchKey}&pageNum=${pageVO.start-1}&amount=${pageVO.amount}"
									class="button small">이전 페이지로 이동</a>
							</c:if>

							<c:forEach var="num" begin="${pageVO.start}" end="${pageVO.end}"
								step="1">
								<a
									href="findBook?searchKey=${pageVO.cri.searchKey}&pageNum=${num}&amount=${pageVO.amount}"><span
									class="button small ${pageVO.page == num ? 'currentpage' : ''}"
									style="${pageVO.page == num ? 'border-color: #ffffff !important; ' : ''}">${num}</span></a>
							</c:forEach>

							<c:if test="${pageVO.next}">
								<a
									href="findBook?searchKey=${pageVO.cri.searchKey}&pageNum=${pageVO.end+1}&amount=${pageVO.amount}"
									class="button small">다음 페이지로 이동</a>
							</c:if>
							<a
								href="findBook?searchKey=${pageVO.cri.searchKey}&pageNum=${pageVO.realEnd}&amount=${pageVO.amount}"
								class="button small">마지막 페이지로 이동</a>
						</div>

					</div>

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