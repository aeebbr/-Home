<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<title>SSAFY</title>
</head>
<body>
	<header id="header" class="header fixed-top d-flex align-items-center">
		<div
			class="container d-flex align-items-center justify-content-between">
			<a href="${root }/index.jsp"
				class="logo d-flex align-items-center me-auto me-lg-0"> <!-- Uncomment the line below if you also wish to use an image logo -->
				<h1>
					구해줘 Home<span>.</span>
				</h1>
			</a>

			<!-- 로그인하지 않은 상태 -->
			<c:if test="${empty userinfo}">
				<nav id="navbar" class="navbar sticky-top">
					<ul id="main-nav">
						<li class="nav-item"><a class="nav-link" aria-current="page"
							href="#">공지사항</a></li>
						<li class="nav-item"><a class="nav-link" aria-current="page"
							href="#">오늘의 뉴스</a></li>
					</ul>
				</nav>
				<div id="nav-right-container">
					<a class="btn-book-a-table nav-link" href="#"
						data-bs-toggle="modal" data-bs-target="#sign-modal">회원가입</a> <a
						id="nav-login" class="btn-gray btn-book-a-table nav-link" href="#"
						data-bs-toggle="modal" data-bs-target="#login-modal">로그인</a>
				</div>
			</c:if>

			<!-- 로그인된 상태 -->
			<c:if test="${!empty userinfo}">
				<nav id="navbar" class="navbar sticky-top">
					<ul id="main-nav">
						<li class="nav-item"><a class="nav-link" aria-current="page"
							href="#">자유글</a></li>
						<li class="nav-item"><a class="nav-link" aria-current="page"
							href="#">주변탐방</a></li>
						<li class="nav-item"><a class="nav-link" aria-current="page"
							href="${root }/favor/insert">관심지역 등록</a></li>
						<li class="nav-item"><a class="nav-link" aria-current="page"
							href="${root }/favor/list">관심지역 둘러보기</a></li>
					</ul>
				</nav>
				<div id="nav-right-container">
					<a class="btn-book-a-table nav-link" id="nav-info"
						href="/user/info">회원정보</a>
					<a class="btn-gray btn-book-a-table nav-link" id="nav-logout"
						onclick="handleClickLogout()" href="#">로그아웃</a>
				</div>
			</c:if>

			<i class="mobile-nav-toggle mobile-nav-show bi bi-list"></i> <i
				class="mobile-nav-toggle mobile-nav-hide d-none bi bi-x"></i>
		</div>
	</header>
</body>