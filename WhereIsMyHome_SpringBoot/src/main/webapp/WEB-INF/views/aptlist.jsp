<%@page import="com.google.gson.Gson"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="com.ssafy.apt.dto.AptDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	ArrayList<AptDto> aptList = (ArrayList<AptDto>) request.getAttribute("aptList");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta content="width=device-width, initial-scale=1.0" name="viewport" />

<title>구해줘 Home</title>
<meta content="" name="description" />
<meta content="" name="keywords" />
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
<!-- =======================================================
  * Template Name: Yummy - v1.1.0
  * Template URL: https://bootstrapmade.com/yummy-bootstrap-restaurant-website-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>

<body>
	<%@ include file="common/header.jsp"%>

	<main id="main"> <!-- ======= About Section ======= -->
	<div class="breadcrumbs">
		<div class="container">
			<div class="banner-container">
				<div class="banner-wrapper">
					<div class="banner-title">
						<h1 style="color: white; text-align: center">지역별 아파트 검색</h1>
						<h5 style="color: white; text-align: center; margin-top: 20px">
							지역을 선택해서 시세를 알아보세요</h5>
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

						<input type="hidden" name="sido" id="sidoValue" value="">
						<input type="hidden" name="gugun" id="gugunValue" value="">
						<input type="hidden" name="dong" id="dongValue" value="">

						<div class="banner-button-wrapper" style="text-align: center">
							<button type="submit" id="list-btn" class="btn btn-light"
								style="margin-top: 20px">검색</button>
						</div>
					</form>
				</div>
			</div>

			<c:if test="${not empty aptList}">
				<div style="height: 70px"></div>
				<div id="map" style="width: 100%; height: 400px;"></div>
				<!-- //////////////////////////////  맵 start  ///////////////////////////////////// -->
				<script type="text/javascript"
					src="//dapi.kakao.com/v2/maps/sdk.js?appkey=32b7b36540e75a778fb8400e8a821a41&libraries=services"></script>
				<!-- //////////////////////////////  맵 end  ///////////////////////////////////// -->
			</c:if>

			<c:if test="${empty aptList}">
			<div style="text-align: center;">
			<br>
				<h3>해당하는 지역의 아파트 매물이 없습니다.</h3>
				</div>
			</c:if>
			<c:if test="${not empty aptList}">
				<table class="table table-hover text-center">
					<tr>
						<th>아파트이름</th>
						<th>층</th>
						<th>면적</th>
						<th>법정동</th>
						<th>거래금액</th>
					</tr>
					<!-- //////////////////////////////  apt list start  ///////////////////////////////////// -->
					<tbody id=aptTable>
						<%
							for (AptDto apt : aptList) {
									Gson gson = new Gson();
									String aptJson = gson.toJson(apt);
						%>
						<tr id="aptInfo" onclick='selectDetailApt(<%=aptJson%>)'>
							<td><%=apt.getAptName()%></td>
							<td><%=apt.getFloor()%></td>
							<td><%=apt.getArea()%></td>
							<td><%=apt.getDongName()%></td>
							<td><%=apt.getDealAmount()%></td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</c:if>
			<!-- //////////////////////////////  apt list end  ///////////////////////////////////// -->

		</div>
	</div>
	<!-- End Contact Section --> </main>
	<!-- End #main -->

	<!-- ======= Footer ======= -->
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
	<!-- End Footer -->

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
						class="btn-form-red btn-form-gray btn btn-sm"
						onclick="handleClickSignupCancle()">취소</button>
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
						<input type="hidden" name="act" value="login" />
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
						class="btn-form-red btn-form-gray btn btn-sm"
						onclick="handleClickloginCancle()">취소</button>
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

	<a href="#"
		class="scroll-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

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

			// ------------------------ map start ------------------------
			var positions = new Array();
			
			let centerLat = '${aptList[0].lat}';
			let centerLng = '${aptList[0].lng}';

			<c:forEach var="apt" items="${aptList}">
				var loc = {
					title : "${apt.aptName}",
					latlng : new kakao.maps.LatLng(${apt.lat}, ${apt.lng})
				}
				positions.push(loc);
			</c:forEach>

			var mapContainer = document.getElementById('map'), // 지도를 표시할 div
			mapOption = {
				center : new kakao.maps.LatLng(centerLat, centerLng), // 지도의 중심좌표
				level : 4
			// 지도의 확대 레벨
			};

			var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

			// 마커 이미지의 이미지 주소입니다
			var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

			for (var i = 0; i < positions.length; i++) {
				// 마커 이미지의 이미지 크기 입니다
				var imageSize = new kakao.maps.Size(24, 35);

				// 마커 이미지를 생성합니다
				var markerImage = new kakao.maps.MarkerImage(imageSrc,
						imageSize);

				// 마커를 생성합니다
				var marker = new kakao.maps.Marker({
					map : map, // 마커를 표시할 지도
					position : positions[i].latlng, // 마커를 표시할 위치
					title : positions[i].title,
					image : markerImage
				// 마커 이미지
				});
			}
			// ------------------------ map end ------------------------
		}
		
		// 상세조회
		function selectDetailApt(apt) {
			console.log(apt);
			
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = {
		        center: new kakao.maps.LatLng(apt.lat, apt.lng), // 지도의 중심좌표
		        level: 3 // 지도의 확대 레벨
		    };

			var map = new kakao.maps.Map(mapContainer, mapOption);

			// 마커가 표시될 위치입니다 
			var markerPosition  = new kakao.maps.LatLng(apt.lat, apt.lng);

			// 마커를 생성합니다
			var marker = new kakao.maps.Marker({
			    position: markerPosition
			});

			// 마커가 지도 위에 표시되도록 설정합니다
			marker.setMap(map);

			var iwContent = 
				'<div style="background: #50627F; color: #fff; text-align: center; line-height:22px; border-radius:4px; padding:0px 10px; width:330px; height:250px;">'+
					'<br><h5>상세정보</h5>' +
					'<table style="width:290px; margin-top:15px; margin-left:15px;">' +
						'<tr>' +
							'<td>이름</td>' +
							'<td>' + apt.aptName + '</td>' +
						'</tr>' +
						'<tr>' +
							'<td>가격</td>' +
							'<td>' + apt.dealAmount + '</td>' +
						'</tr>' +
						'<tr>' +
							'<td>주소</td>' +
							'<td>' + apt.sidoName + " " + apt.gugunName + " " + apt.dongName + " " + apt.jibun + '</td>' +
						'</tr>' +
						'<tr>' +
							'<td>면적</td>' +
							'<td>' + apt.area + '</td>' +
						'</tr>' +
						'<tr>' +
							'<td>경도</td>' +
							'<td>' + apt.lat + '</td>' +
						'</tr>' +
						'<tr>' +
							'<td>위도</td>' +
							'<td>' + apt.lng + '</td>' +
						'</tr>' +
						'<tr>' +
							'<td>Code</td>' +
							'<td>' + apt.aptCode + '</td>' +
						'</tr>' +
					'</table>' +
				'</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
			    iwPosition = new kakao.maps.LatLng(apt.lat, apt.lng); //인포윈도우 표시 위치입니다

			// 인포윈도우를 생성합니다
			var infowindow = new kakao.maps.InfoWindow({
			    position : iwPosition, 
			    content : iwContent 
			});
			  
			// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
			infowindow.open(map, marker);    
		}
	</script>
</body>
</html>
