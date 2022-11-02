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
<link href="../css/main.css" rel="stylesheet" />
<link rel="stylesheet" href="../css/mainCustom.css" />
</head>
<body>
	<%@ include file="/common/header.jsp"%>

	<main id="main">
	<div class="breadcrumbs">
		<div class="container">
			<div class="region-container" id="region">
				<div class="region-title-container">
					<div class="region-title">관심 지역 등록</div>
					<div class="region-sub-title">관심 지역을 등록하세요</div>
				</div>
				<form method="post" action="${root }/favor">
					<input type="hidden" name="act" value="insert">
					<div class="row col-md-12 justify-content-center mb-2">
						<div class="form-group col-md-2">
							<select class="form-select bg-secondary text-light" id="sido"
								name="sidoCode">
								<option value="">도/광역시</option>
							</select>
						</div>
						<div class="form-group col-md-2">
							<select class="form-select bg-secondary text-light" id="gugun"
								name="gugunCode">
								<option value="">시/구/군</option>
							</select>
						</div>
						<div class="form-group col-md-2">
							<select class="form-select bg-secondary text-light" id="dong"
								name="dongCode">
								<option value="">동</option>
							</select>
						</div>
					</div>
					<div class="region-button-wrapper">
						<button type="button" class="btn" id="btn-main"
							onclick="handleClickAddRegion()">등록</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	</main>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
	<script src="../js/favor.js"></script>
	<script src="../js/logout.js"></script>
	<script src="../js/nav.js"></script>
	<script src="../js/main.js"></script>
</body>
</html>