<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>구해줘 Home</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css" />

<!-- Google Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,600;1,700&family=Amatic+SC:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&family=Inter:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap"
	rel="stylesheet" />

<!-- Vendor CSS Files -->
<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="assets/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet" />
<link href="assets/vendor/aos/aos.css" rel="stylesheet" />
<link href="assets/vendor/glightbox/css/glightbox.min.css"
	rel="stylesheet" />
<link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet" />

<!-- Template Main CSS File -->
<link href="assets/css/main.css" rel="stylesheet" />
<link rel="stylesheet" href="assets/css/mainCustom.css" />
</head>

<body>
	<%@ include file="common/header.jsp"%>

	<main id="main">
	<div class="breadcrumbs">
		<div class="container">
			<!-- 배너 -->
			<div class="banner-container">
				<div class="banner-wrapper">
					<div class="banner-title">
						<div class="banner-top-title">Where Is My HOME?</div>
						<h5>내 집은 어디에?</h5>
						<h5>우리 집을 손쉽게 찾아보세요.</h5>
					</div>
					<div class="banner-button-wrapper">
						<button>
							<a href="#region"> 지역별로 집 보러 가기 </a>
						</button>
					</div>
				</div>
			</div>

			<!-- 메인 -->
			<div class="region-container" id="region">
				<div class="region-title-container">
					<div class="region-title">지역별 아파트 검색</div>
					<div class="region-sub-title">지역을 선택해서 시세를 알아보세요</div>
				</div>
				<form action="apt" method="get">
					<input type="hidden" name="action" value="aptlist">
					<div class="row col-md-12 justify-content-center mb-2">
						<div class="form-group col-md-2">
							<select class="form-select bg-secondary text-light" id="sido">
							</select>
						</div>
						<div class="form-group col-md-2">
							<select class="form-select bg-secondary text-light" id="gugun">
								<option value="">시/구/군</option>
							</select>
						</div>
						<div class="form-group col-md-2">
							<select class="form-select bg-secondary text-light" id="dong">
								<option value="">동</option>
							</select>
						</div>
					</div>

					<input type="hidden" name="sido" id="sidoValue" value=""> <input
						type="hidden" name="gugun" id="gugunValue" value=""> <input
						type="hidden" name="dong" id="dongValue" value="">

					<div class="banner-button-wrapper" style="text-align: center">
						<button type="submit" id="list-btn" class="btn btn-light"
							style="margin-top: 20px">검색</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	</main>

	<footer id="footer" class="footer">
		<div class="container">
			<div class="row gy-3">
				<div class="col-lg-3 col-md-9 d-flex">
					<i class="bi bi-geo-alt icon"></i>
					<div>
						<h4>Address</h4>
						<p>
							(SSAFY) <br /> 서울시 강남구 테헤란로 멀티스퀘어<br />
						</p>
					</div>
				</div>
				<div class="col-lg-3 col-md-9 footer-links d-flex">
					<i class="bi bi-telephone icon"></i>
					<div>
						<h4>Call</h4>
						<p>
							<strong>Phone:</strong> 1544-9001<br /> <strong>Email:</strong>
							admin@ssafy.com<br />
						</p>
					</div>
				</div>
				<div class="col-lg-3 col-md-9 footer-links">
					<h4>Follow Us</h4>
					<div class="social-links d-flex">
						<a href="#" class="twitter"><i class="bi bi-twitter"></i></a> <a
							href="#" class="facebook"><i class="bi bi-facebook"></i></a> <a
							href="#" class="instagram"><i class="bi bi-instagram"></i></a> <a
							href="#" class="linkedin"><i class="bi bi-linkedin"></i></a>
					</div>
				</div>
			</div>
		</div>

		<div class="container">
			<div class="copyright">
				&copy; Copyright <strong><span>구해줘 Home</span></strong>. All Rights
				Reserved
			</div>
			<div class="credits">
				<!-- All the links in the footer should remain intact. -->
				<!-- You can delete the links only if you purchased the pro version. -->
				<!-- Licensing information: https://bootstrapmade.com/license/ -->
				<!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/yummy-bootstrap-restaurant-website-template/ -->
				Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
			</div>
		</div>
	</footer>

	<!-- *** modal start -->
	<!-- !!! 회원가입 modal -->
	<div class="modal fade" id="sign-modal" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">회원가입</h4>
					<button type="button" class="btn-close" id="btn-sign-up-close"
						data-bs-dismiss="modal"></button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<form id="form-join" method="POST">
						<input type="hidden" name="act" value="join" />
						<div class="row">
							<div class="input-wrapper row mb-3">
								<div class="col-md-3">
									<label for="id" class="form-label">아이디</label>
								</div>
								<div class="col-md-9">
									<input type="text" class="form-control" id="id" name="userid"
										placeholder="아이디를 입력해 주세요" />
									<div class="input-valid" id="id-sign-up-valid"></div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="input-wrapper row mb-3">
								<div class="col-md-3">
									<label for="password" class="form-label">비밀번호</label>
								</div>
								<div class="col-md-9">
									<input type="password" class="form-control" id="password"
										name="userpwd" placeholder="영문 숫자 포함 6자리 이상" />
									<div class="input-valid" id="password-sign-up-valid"></div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="input-wrapper row mb-3">
								<div class="col-md-3">
									<label for="name" class="form-label">이름</label>
								</div>
								<div class="col-md-9">
									<input type="text" class="form-control" id="name"
										name="username" placeholder="이름을 입력해 주세요" />
									<div class="input-valid" id="name-sign-up-valid"></div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="input-wrapper row mb-3">
								<div class="col-md-3">
									<label for="address" class="form-label">주소</label>
								</div>
								<div class="col-md-9">
									<input type="text" class="form-control" id="address"
										name="useraddr" placeholder="주소를 입력해 주세요" />
									<div class="input-valid" id="address-sign-up-valid"></div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="input-wrapper row mb-3">
								<div class="col-md-3">
									<label for="phone-number" class="form-label">전화번호</label>
								</div>
								<div class="col-md-9">
									<input type="text" class="form-control" id="phone-number"
										name="userphonenumber" placeholder="010-xxxx-xxxx" />
									<div class="input-valid" id="phone-number-sign-up-valid"></div>
								</div>
							</div>
						</div>
					</form>
				</div>

				<!-- Modal footer -->
				<div class="modal-body">
					<button type="button" id="btn-sign-up"
						class="btn-form-red btn btn-sm" onclick="handleClickSignup()">회원가입</button>
					<button type="button" id="btn-sign-up-cancle"
						class="btn-form-red btn-form-gray btn btn-sm" onclick="handleClickSignupCancle()">취소</button>
				</div>
			</div>
		</div>
	</div>

	<!-- !!! 로그인 modal -->
	<div class="modal fade" id="login-modal" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">로그인</h4>
					<button type="button" id="btn-login-close" class="btn-close"
						data-bs-dismiss="modal"></button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<form id="form-login" method="POST" action="">
						<div class="row">
							<div class="input-wrapper row mb-3">
								<div class="col-md-3">
									<label for="login-id" class="form-label">아이디</label>
								</div>
								<div class="col-md-9">
									<input type="text" class="form-control" id="login-id"
										name="userid" placeholder="아이디를 입력해 주세요" />
									<div class="input-valid" id="id-login-valid"></div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="input-wrapper row mb-3">
								<div class="col-md-3">
									<label for="login-password" class="form-label">비밀번호</label>
								</div>
								<div class="col-md-9">
									<input type="password" class="form-control" id="login-password"
										name="userpwd" placeholder="비밀번호를 입력해 주세요" />
									<div class="input-valid" id="password-login-valid"></div>
								</div>
							</div>
						</div>
					</form>
				</div>

				<!-- Modal footer -->
				<div class="modal-body">
					<div class="input-valid login-failed">${msg}</div>
					<button type="button" id="btn-login"
						class="btn-form-red btn btn-sm" onclick="handleClickLogin()">로그인</button>
					<button type="button" id="btn-find-pw"
						class="btn-form-red-border btn btn-sm"
						onclick="alert('coming soon')">비밀번호 찾기</button>
					<button type="button" id="btn-login-cancle"
						class="btn-form-red btn-form-gray btn btn-sm" onclick="handleClickloginCancle()">
						취소</button>
				</div>
			</div>
		</div>
	</div>

	<!-- !!! 회원정보 modal -->
	<div class="modal fade" id="info-modal" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">회원 정보 확인</h4>
					<button type="button" class="btn-close" id="btn-close-info"
						data-bs-dismiss="modal"></button>
				</div>

				<!-- Modal body -->
				<div id="info-container">
					<div class="modal-body">
						<form action="">
							<div class="row">
								<div class="input-wrapper row mb-3">
									<div class="col-md-3">아이디</div>
									<div class="col-md-9 info" id="info-id">${member.userId}</div>
								</div>
							</div>
							<div class="row">
								<div class="input-wrapper row mb-3">
									<div class="col-md-3">비밀번호</div>
									<div class="col-md-9 info" id="info-password">${member.userPwd }</div>
								</div>
							</div>
							<div class="row">
								<div class="input-wrapper row mb-3">
									<div class="col-md-3">이름</div>
									<div class="col-md-9 info" id="info-name">${member.userName}</div>
								</div>
							</div>
							<div class="row">
								<div class="input-wrapper row mb-3">
									<div class="col-md-3">주소</div>
									<div class="col-md-9 info" id="info-address">${member.userAddr}</div>
								</div>
							</div>
							<div class="row">
								<div class="input-wrapper row mb-3">
									<div class="col-md-3">전화번호</div>
									<div class="col-md-9 info" id="info-phone-number">${member.userPhoneNum}</div>
								</div>
							</div>
						</form>
					</div>
				</div>

				<!-- Modal footer -->
				<div class="info-button-container modal-body">
					<button type="button" id="btn-info-modi"
						class="btn-form-red btn btn-sm" onclick="handleClickOpenModi()">수정</button>
					<button type="button" id="btn-info-del"
						class="btn-form-red-border btn btn-sm" onclick="handleClickDel()">탈퇴</button>
					<button type="button" id="btn-info-check"
						class="btn-form-red btn-form-gray btn btn-sm"
						onclick="handleClickInfoClose()">닫기</button>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
	<script src="./assets/js/nav.js"></script>
	<script src="./assets/js/signUp.js"></script>
	<script src="./assets/js/login.js"></script>
	<script src="./assets/js/logout.js"></script>
	<script src="./assets/js/main.js"></script>
	<script type="text/javascript">
		window.onload = function() {
			document.getElementById('list-btn').onclick = function() {
				let sidoSel = document.querySelector("#sido");
				let sido = sidoSel.options[sidoSel.selectedIndex].textContent;
				document.getElementById("sidoValue").value = sido;

				let gugunSel = document.querySelector("#gugun");
				let gugun = gugunSel.options[gugunSel.selectedIndex].textContent;
				document.getElementById("gugunValue").value = gugun;

				let dongSel = document.querySelector("#dong");
				let dong = dongSel.options[dongSel.selectedIndex].textContent;
				document.getElementById("dongValue").value = dong;
			}
		}
	</script>
</body>
</html>
