<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>PlayData Book Rental Service</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/main.css" />
		<noscript><link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/noscript.css" /></noscript>
	</head>
	<body class="is-preload">

		<!-- Sidebar -->
			<section id="sidebar">
				<div class="inner">
					<nav>
						<ul>
							<li><a href="#intro">Welcome</a></li>
							<li><a href="#one">책 대여 / 반납</a></li>
							<li><a href="#two">대여 기록 조회</a></li>
							<li><a href="#three">관리자 메뉴</a></li>
						</ul>
					</nav>
				</div>
			</section>

		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Intro -->
					<section id="intro" class="wrapper style1 fullscreen fade-up">
						<div class="inner">
							<h1>PlayData Book Rental Service</h1>
							<p> 환영합니다 <br/>
							당신의 여정을 응원합니다. </p>
							<ul class="actions">
								<li><a href="#one" class="button scrolly">책 대여 / 반납하기</a></li>
							</ul>
						</div>
					</section>

				<!-- One -->
					<section id="one" class="wrapper style2 spotlights">
						<section>
							<a href="#" class="image"><img src="${pageContext.request.contextPath}/resources/images/202004211422083541_3.jpg" alt="" data-position="center center" /></a>
							<div class="content">
								<div class="inner">
									<h2>책 대여하기</h2>
									<p>책은 기본적으로 7일 대여가 가능하며 부득이한 사정이 있을 경우 매니저님께 별도로 말씀해주시길 바랍니다.</p>
									<ul class="actions">
										<li><a href="rental" class="button">대여</a></li>
									</ul>
								</div>
							</div>
						</section>
						<section>
							<a href="#" class="image"><img src="${pageContext.request.contextPath}/resources/images/37572_31732_5246.jpg" alt="" data-position="top center" /></a>
							<div class="content">
								<div class="inner">
									<h2>책 반납하기</h2>
									<p>별도의 사정으로 책이 회손됐을 경우 매니저님께 말씀해주세요. 7일 이내로 반납한 경우가 아닐 시 별도의 불이익이 있을 수 있습니다.</p>
									<ul class="actions">
										<li><a href="generic.html" class="button">반납</a></li>
									</ul>
								</div>
							</div>
						</section>
					</section>

				<!-- Two -->
					<section id="two" class="wrapper style3 fade-up">
						<div class="inner">
							<h2>대여 기록 조회</h2>
							<p>내가 빌리고 싶은 책이 혹시 없다면, 대여기록을 살펴보세요. 혹은 어떤 책을 골라야할지 모르겠다면 다른사람들은 어떤 책들을 위주로 많이 대여했는지 살펴보세요!</p>
							<div class="features">
								<section>
									<span class="icon solid major fa-desktop"></span>
									<h3>책 찾아보기</h3>
									<p>책 제목 혹은 저자 이름으로 책이 대여됐는지 남아있는지 확인해보세요!</p>
								</section>
								<section>
									<span class="icon solid major fa-cog"></span>
									<h3>책 인기 순위 보기</h3>
									<p>어떤 책들이 가장 많이 대여 됐는지 확인해보세요!</p>
								</section>
								<section>
									<span class="icon solid major fa-lock"></span>
									<h3>내 대여 기록 보기</h3>
									<p>이름과 생년월일으로 내 대여기록을 확인해보세요!</p>
								</section>
								<section>
									<span class="icon solid major fa-desktop"></span>
									<h3>책 대여 / 반납하기</h3>
									<p>책 대여 혹은 반납하기</p>
								</section>
							</div>

								<ul class="actions">
										<li><a href="viewList" class="button">보기</a></li>
								</ul>
						</div>
					</section>

				<!-- Three -->
					<section id="three" class="wrapper style1 fade-up">
						<div class="inner">
							<h2>관리자 로그인</h2>
							<p>책 관리하기</p>
							<div class="split style1">
								<section>
									<form method="post" action="/library/manager/managerLogin">
										<div class="fields">
											<div class="field half">
												<label for="name">ID</label>
												<input type="text" name="managerId" id="name" />
											</div>
											<div class="field half">
												<label for="email">Password</label>
												<input type="password" name="managerPw" id="email" />
											</div>
										</div>
										<ul>
											<li><input type="submit" class="button submit" value="Login"></a></li>
										</ul>
									</form>
								</section>
								<section>
									<ul class="contact">
										<li>
											<h3>Address</h3>
											<span>12345 Somewhere Road #654<br />
											Nashville, TN 00000-0000<br />
											USA</span>
										</li>
										<li>
											<h3>Email</h3>
											<a href="#">user@untitled.tld</a>
										</li>
										<li>
											<h3>Phone</h3>
											<span>(000) 000-0000</span>
										</li>
										<li>
											<h3>Social</h3>
											<ul class="icons">
												<li><a href="#" class="icon brands fa-twitter"><span class="label">Twitter</span></a></li>
												<li><a href="#" class="icon brands fa-facebook-f"><span class="label">Facebook</span></a></li>
												<li><a href="#" class="icon brands fa-github"><span class="label">GitHub</span></a></li>
												<li><a href="#" class="icon brands fa-instagram"><span class="label">Instagram</span></a></li>
												<li><a href="#" class="icon brands fa-linkedin-in"><span class="label">LinkedIn</span></a></li>
											</ul>
										</li>
									</ul>
								</section>
							</div>
						</div>
					</section>

			</div>

		<!-- Footer -->
			<footer id="footer" class="wrapper style1-alt">
				<div class="inner">
					<ul class="menu">
						<li>&copy; Made by Jeongil Jeong</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
					</ul>
				</div>
			</footer>

		<!-- Scripts -->
			<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.min.js"></script>
			<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.scrollex.min.js"></script>
			<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.scrolly.min.js"></script>
			<script src="${pageContext.request.contextPath}/resources/assets/js/browser.min.js"></script>
			<script src="${pageContext.request.contextPath}/resources/assets/js/breakpoints.min.js"></script>
			<script src="${pageContext.request.contextPath}/resources/assets/js/util.js"></script>
			<script src="${pageContext.request.contextPath}/resources/assets/js/main.js"></script>
		<script>
			var msg = '${msg}';
			if (msg != "") {
				alert(msg);
			}
		</script>
	</body>
</html>