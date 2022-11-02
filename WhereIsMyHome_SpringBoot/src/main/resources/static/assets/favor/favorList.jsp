<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link rel="stylesheet" href="assets/css/favorList.css" />
</head>
<body>
	<%@ include file="/common/header.jsp"%>

	<main id="main">
	<div class="breadcrumbs">
		<div class="container">
			<div class="page-title-container">
				<div class="page-title">관심 지역 목록</div>
			</div>
			<div class="favor-list-container">
				<!-- 등록된 지역이 없다면  -->
				<c:if test="${empty regions}">
					<div class="nothing-container">
						<div class="nothing-title">등록된 관심 지역이 없습니다</div>
						<div class="insert-banner-container"
							onclick="location.href=`${root }/favor?act=mvinsert`">
							<div class="insert-banner-title">관심 지역 등록하러 가기</div>
						</div>
					</div>
				</c:if>
				<!-- for each start -->
				<c:forEach var="region" items="${regions}">
					<div class="favor-list-wrapper">
						<div class="favor-list-region">
							<div class="favor-list-sido">${region.sidoName }</div>
							<div class="favor-list-gugun">${region.gugunName }</div>
							<div class="favor-list-dong">${region.dongName }</div>
						</div>
						<div class="favor-list-btn-container">
							<div id="${region.id }"
								class="favor-list-btn-wrapper favor-list-btn-del">삭제</div>
						</div>
					</div>
				</c:forEach>
				<!-- for each end -->
			</div>
		</div>
	</div>
	</main>
	<script src="/whereismyhome_be/assets/js/favorList.js"></script>
	<script src="/whereismyhome_be/assets/js/logout.js"></script>
	<script src="/whereismyhome_be/assets/js/nav.js"></script>
</body>
</html>