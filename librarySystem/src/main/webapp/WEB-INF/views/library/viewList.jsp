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

	<!-- Sidebar -->
	<section id="sidebar">
		<div class="inner">
			<nav>
				<ul>
					<li><a href="index">Welcome</a></li>
					<li><a href="index#one">책 대여 / 반납</a></li>
					<li><a href="#intro">대여 기록 조회</a></li>
					<li><a href="index#three">관리자 메뉴</a></li>
				</ul>
			</nav>
		</div>
	</section>

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Intro -->
		<section id="intro" class="wrapper style1 fullscreen fade-up">
			<div class="inner">
				<h1>대여 기록 조회</h1>
				<p>
					환영합니다 <br /> 당신의 여정을 응원합니다.
				</p>
				<ul class="actions">
					<li><a href="#one" class="button scrolly">책 찾아보기</a></li>
					<li><a href="#two" class="button scrolly">책 인기 순위 보기</a></li>
					<li><a href="#three" class="button scrolly">내 대여 기록 보기</a></li>
				</ul>
			</div>
		</section>

		<!-- One -->
		<section id="one" class="wrapper style2 spotlights">
			<section>
				<div class="content">
					<div class="inner">
						<h2>책 찾아보기</h2>
						<form method="post" action="#">
							<div class="row gtr-uniform">
								<div class="col-12">
									<select name="demo-category" id="demo-category">
										<option value="">제목</option>
										<option value="">작가</option>
									</select>
								</div>
								<div class="col-12">
									<input type="email" name="demo-email" id="demo-email" value=""
										placeholder="검색어를 입력해주세요" />
								</div>
								<div class="col-12">
									<ul class="actions">
										<li><input type="submit" value="검색" class="primary" /></li>
									</ul>
								</div>
							</div>
						</form>
						<p></p>
					</div>
				</div>
			</section>
		</section>

		<!-- Two -->
		<section id="two" class="wrapper style3 fade-up">
			<div class="inner">
				<h2>책 인기 순위 보기</h2>
				<p></p>

			</div>
		</section>

		<!-- Three -->
		<section id="three" class="wrapper style1 fade-up">
			<div class="inner">
				<h2>내 대여 기록보기</h2>
				<form method="post" action="#">
					<div class="row gtr-uniform">
						<div class="col-6 col-12-xsmall">
							<input type="text" name="demo-name" id="demo-name" value=""
								placeholder="이름" />
						</div>
						<div class="col-6 col-12-xsmall">
							<input type="text" name="demo-email" id="demo-email" value=""
								placeholder="생년월일" />
						</div>
						<div class="col-12">
							<ul class="actions">
								<li><input type="submit" value="검색" class="primary" /></li>
							</ul>
						</div>
					</div>
				</form>
			</div>
		</section>

	</div>

	<!-- Footer -->
	<footer id="footer" class="wrapper style1-alt">
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