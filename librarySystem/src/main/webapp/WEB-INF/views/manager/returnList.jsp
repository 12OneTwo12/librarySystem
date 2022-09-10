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
		<a href="/library/library/index" class="title">PlayData Book Rental Service</a>
		<nav>
			<ul>
				<li><a href="/library/library/index">Home</a></li>
				<li><a href="register">책 등록</a></li>
				<li><a href="remove" >책 목록 / 책 삭제</a></li>
				<li><a href="returnList" class="active">반납 독촉 목록</a></li>
				<li><a href="mypage">MyPage</a></li>
				<li><a href="logout">Logout</a></li>
			</ul>
		</nav>
	</header>

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
		<section id="main" class="wrapper">
			<div class="inner">
				<h1 class="major">책 목록</h1>

				<section>
					<div class="table-wrapper">
						<table class="alt">
							<thead>
								<tr>
									<th>Book Serial Number</th>
									<th>Title</th>
									<th>Writer</th>
									<th>Status</th>
									<th>Name</th>
									<th>Rental Start</th>
									<th>Rental End</th>
									<th>Phone Number</th>
								</tr>
							</thead>
							<tbody style="font-size: 18px;">
								<c:forEach var="vo" items="${list}">
									<tr>
										<td>${vo.bookSerialNumber}</td>
										<td>${vo.bookTitle}</td>
										<td>${vo.bookWriter}</td>
										<td>${vo.bookStatus}</td>
										<td>${vo.name}</td>
										<td>${vo.rentalStart}</td>
										<td>${vo.rentalEnd}</td>
										<td>${vo.phoneNumber}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div class="pagination">
							<a
								href="returnList?pageNum=1&amount=${pageVO.amount}"
								class="button small">첫 페이지로 이동</a>

							<c:if test="${pageVO.prev}">
								<a
									href="returnList?pageNum=${pageVO.start-1}&amount=${pageVO.amount}"
									class="button small">이전 페이지로 이동</a>
							</c:if>

							<c:forEach var="num" begin="${pageVO.start}" end="${pageVO.end}"
								step="1">
								<a
									href="returnList?pageNum=${num}&amount=${pageVO.amount}"><span
									class="button small ${pageVO.page == num ? 'currentpage' : ''}"
									style="${pageVO.page == num ? 'border-color: #ffffff !important; ' : ''}">${num}</span></a>
							</c:forEach>

							<c:if test="${pageVO.next}">
								<a
									href="returnList?pageNum=${pageVO.end+1}&amount=${pageVO.amount}"
									class="button small">다음 페이지로 이동</a>
							</c:if>
							<a
								href="returnList?pageNum=${pageVO.realEnd}&amount=${pageVO.amount}"
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
		
		function deleteBookF(number){
    		event.preventDefault(); 
    		
    		if(confirm("삭제 하시겠습니까?")){
    			var link = "deleteBook?bookNumber="+number;
    			location.href = link;
    		}
    	}
	</script>
</body>
</html>