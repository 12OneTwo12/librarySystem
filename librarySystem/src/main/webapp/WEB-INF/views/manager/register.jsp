<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<a href="/library/library/index" class="title">PlayData Book
			Rental Service</a>
		<nav>
			<ul>
				<li><a href="/library/library/index">Home</a></li>
				<li><a href="register" class="active">책 등록</a></li>
				<li><a href="remove">책 목록 / 삭제</a></li>
				<li><a href="returnList">반납 독촉 목록</a></li>
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
				<h1 class="major">책 등록하기</h1>

				<!-- Form -->
				<section>
					<h2>작성해주세요</h2>
					<form method="post" action="bookRegister">
						<div class="row gtr-uniform">
							<div class="col-12">
								<input type="text" name="bookTitle" id="demo-name"
									value="${bookVO.bookTitle}" placeholder="책 제목"
									required="required" />
							</div>
							<div class="col-6 col-12-xsmall">
								<input type="text" name="bookWriter" id="demo-name"
									value="${bookVO.bookWriter}" placeholder="작가"
									required="required" />
							</div>
							<div class="col-6 col-12-xsmall">
								<select name="bookCategory" id="demo-category">
									<option value="Others">- Category -</option>
									<option value="Java">Java</option>
									<option value="JS">JS</option>
									<option value="Python">Python</option>
									<option value="DB">DB</option>
									<option value="Others">Others</option>
								</select>
							</div>
							<div class="col-12">
								<ul class="actions">
									<li><input type="submit" value="등록" class="primary" /></li>
								</ul>
							</div>
						</div>
					</form>
				</section>
				<div class="col-6 col-12-xsmall">
					<span class="image fit"> <img
						src="/barcodes/${realSerialNumber}.png"
						alt="바코드가 생성될 예정입니다. 파일을 저장 후 인쇄하여 책에 부착해주세요">
					</span>
				</div>
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