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
					<li><a href="#four" class="button scrolly">전체 기록 조회 및 일련번호로 조회하기</a></li>
				</ul>
			</div>
		</section>

		<!-- One -->
		<section id="one" class="wrapper style2 spotlights">
			<section>
				<div class="content">
					<div class="inner">
						<h2>책 찾아보기</h2>
						<form method="post" action="findBook">
							<div class="row gtr-uniform">
								<div class="col-12">
									<select name="searchType" id="demo-category">
										<option value="title" ${pageVO.cri.searchType == 'title' ? 'selected' : ''}>제목</option>
										<option value="writer" ${pageVO.cri.searchType == 'writer' ? 'selected' : ''}>작가</option>
										<option value="serialNumber" ${pageVO.cri.searchType == 'serialNumber' ? 'selected' : ''}>일련 번호</option>
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
				<ul class="actions">
					<li><a href="viewPopularity" class="button">보기</a></li>
				</ul>
			</div>
		</section>

		<!-- Three -->
		<section id="three" class="wrapper style1 fade-up">
			<div class="inner">
				<h2>내 대여 기록보기</h2>
				<form method="post" action="myRentalRecord">
					<div class="row gtr-uniform">
						<div class="col-6 col-12-xsmall">
							<input type="text" name="name" id="demo-name" value=""
								placeholder="이름" />
						</div>
						<div class="col-6 col-12-xsmall">
							<input type="text" name="birth" id="demo-email" value=""
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
		
		<section id="four" class="wrapper style2 spotlights">
			<section>
				<div class="content">
					<div class="inner">
						<h2>전체 기록 조회 및 일련번호로 조회하기</h2>
						<form method="post" action="findBySerialNumber">
							<div class="row gtr-uniform">
								<div class="col-6 col-12-xsmall">
									<input type="text" name="searchKey" id="demo-name"
										value="${pageVO.cri.searchKey}" placeholder="일련번호를 입력해주세요" />
								</div>
								<div class="col-6 col-12-xsmall">
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