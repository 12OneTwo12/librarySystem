<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>MyPage</title>
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
		<a href="index.html" class="title">PlayData Book Rental Service</a>
		<nav>
			<ul>
				<li><a href="/library/library/index">Home</a></li>
				<li><a href="register">책 등록</a></li>
				<li><a href="remove">책 목록 / 책 삭제</a></li>
				<li><a href="mypage" class="active">MyPage</a></li>
				<li><a href="logout">Logout</a></li>
			</ul>
		</nav>
	</header>

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
		<section id="main" class="wrapper">
			<div class="inner">
				<h1 class="major">MyPage</h1>

				<!-- Form -->
				<section>
					<h2>Manager account</h2>
					<form method="post" action="changePw">
						<div class="row gtr-uniform">
							<div class="col-12">
								<input type="text" name="managerId" id="demo-email" value="${sessionVO.managerId}"
									placeholder="ID" readonly="readonly" />
							</div>
							<div class="col-12">
								<input type="password" name="managerPw" id="demo-email" value=""
									placeholder="Password" required="required" />
							</div>
							<div class="col-12">
								<input type="password" name="managerNewPw" id="demo-email" value=""
									placeholder="New Password" required="required" />
							</div>
							<div class="col-12">
								<input type="password" name="managerNewPwCheck" id="demo-email" value=""
									placeholder="New Password Check" required="required" />
							</div>
							<div class="col-12">
								<ul class="actions">
									<li><input type="submit" value="비밀번호 변경"
										class="primary" /></li>
								</ul>
							</div>
						</div>
					</form>
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