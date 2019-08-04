<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/core-style.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<%@ page session="true"%>
<html> 
<head> 
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

	<title>Callie HTML Template</title>

	<!-- Google font -->
	<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700%7CMuli:400,700" rel="stylesheet">

	<!-- Bootstrap -->
	<link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css" />

	<!-- Font Awesome Icon -->
    <link rel="stylesheet" href="/css/font-awesome.min.css">

	<!-- Custom stlylesheet -->
	<link type="text/css" rel="stylesheet" href="/css/style.css" />

	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->

</head>

<body>
	<!-- HEADER -->
	<header id="header"><%-- 
		<!-- NAV -->
		<div id="nav">
			<!-- Top Nav -->
			<div id="nav-top">
				<div class="container">
					<!-- social -->
					<ul class="nav-social">
						<li><a href="#"><i class="fa fa-facebook"></i></a></li>
						<li><a href="#"><i class="fa fa-twitter"></i></a></li>
						<li><a href="#"><i class="fa fa-google-plus"></i></a></li>
						<li><a href="#"><i class="fa fa-instagram"></i></a></li>
					</ul>
					<!-- /social -->

					<!-- logo -->
					<div class="nav-logo">
						<!-- <a href="/" class="logo"><img src="/img/logo.png" alt=""></a> -->
						<a href="/"><h1>바이크모아</h1></a>
						
					</div>
					<!-- /logo -->

					<!-- search & aside toggle -->
					<div class="nav-btns">
						<button class="aside-btn"><i class="fa fa-bars"></i></button>
						<button class="search-btn"><i class="fa fa-search"></i></button>
						<div id="nav-search">
							<form>
								<input class="input" name="search" placeholder="Enter your search...">
							</form>
							<button class="nav-close search-close">
								<span></span>
							</button>
						</div>
					</div>
					<!-- /search & aside toggle -->
				</div>
			</div>
			<!-- /Top Nav -->

			<!-- Main Nav -->
			<div id="nav-bottom">
				<div class="container">
					<!-- nav -->
					<ul class="nav-menu">
						<li class="has-dropdown">
							<a href="/registration">회원</a>
							<div class="dropdown">
								<div class="dropdown-body">
									<ul class="dropdown-list">
										<li><a href="/registration">회원가입</a></li>
										  <c:choose>
						<c:when test="${sessionScope.userid==null}">
										<li><a href="/login">로그인</a></li>
						</c:when>
						<c:otherwise>
									<li><a href="/member/logout.do">로그아웃</a></li>
						</c:otherwise>
						</c:choose>
										<li><a href="/go">Author page</a></li>
										<li><a href="about.html">About Us</a></li>
										<li><a href="contact.html">Contacts</a></li>
										<li><a href="blank.html">Regular</a></li>
									</ul>
								</div>
							</div>
						</li>
						<li class="has-dropdown megamenu">
						
						</li>
						<li class="has-dropdown megamenu">
							<a href="#">종합</a>
							<div class="dropdown">
								<div class="dropdown-body">
									<div class="row">
										<div class="col-md-3">
											<h4 class="dropdown-heading">자유</h4>
											<ul class="dropdown-list">
												<li><a href="/Category/FreeBoardList.do?bgno=1">자유게시판</a></li>
												<li><a href="/Category/FreeBoardList.do?bgno=2">입문Tip 게시판</a></li>
												<li><a href="/Category/FreeBoardList.do?bgno=3">질문&답변</a></li>
												<li><a href="/Category/FreeBoardList.do?bgno=4">사고 Q&A</a></li>
												<li><a href="/Category/FreeBoardList.do?bgno=5">국산바이크게시판</a></li>
												<li><a href="/Category/FreeBoardList.do?bgno=6">해외바이크게시판</a></li>
											<li><a href="#">분실및도난</a></li>
											</ul>
										</div>
										<div class="col-md-3">
											<h4 class="dropdown-heading">중고거래</h4>
											<ul class="dropdown-list">
												<li><a href="/Multi/FreeBoardList.do?bgno=1">센터매물</a></li>
												<li><a href="#">개인매물</a></li>
												<li><a href="#">바이크 부품</a></li>
												<li><a href="#">튜닝용품</a></li>
												<li><a href="#">개인용품</a></li>																								
											</ul>
											<h4 class="dropdown-heading">Tip</h4>
											<ul class="dropdown-list">
												<li><a href="#">중고거래Tip</a></li>
												<li><a href="#">개인정비 Tip</a></li>
												<li><a href="#">센터후기</a></li>
											</ul>
										</div>
										<div class="col-md-3">
											<h4 class="dropdown-heading">정보</h4>
											<ul class="dropdown-list">
	  											<li><a href="#">바이크 세계뉴스</a></li>
												<li><a href="#">바이크 Review</a></li>
												<li><a href="#">개인 바이크후기</a></li>
												<li><a href="#">바이크튜닝후기</a></li>
												<li><a href="#">센터 소개</a></li>
											</ul>
											<h4 class="dropdown-heading">Travel</h4>
											<ul class="dropdown-list">
												<li><a href="#">투어 코스 Q&A</a></li>
												<li><a href="#">맛집&&카페 추천</a></li>
												<li><a href="#">바이크 여행 후기</a></li>
												<li><a href="#">전국일주 후기</a></li>
												<li><a href="#">세계일주 후기</a></li>	
											</ul>
										</div>
										<div class="col-md-3">
											<h4 class="dropdown-heading">활동</h4>
											<ul class="dropdown-list">
												<li><a href="#">개인바이크 사진</a></li>
												<li><a href="#">투어 Tip</a></li>
												<li><a href="#">번개 모집게시판</a></li>
												<li><a href="#">번개 후기</a></li>
												<li><a href="#">전국일주 모집</a></li>											
											</ul>
										</div>
									</div>
								</div>
							</div>
						</li>
					<!-- 	<li><a href="#">Technology</a></li>
						<li><a href="#">Health</a></li>
						<li><a href="#">Travel</a></li> -->
					</ul>
					<!-- /nav -->
				</div>
			</div>
			<!-- /Main Nav -->

			<!-- Aside Nav -->
			<div id="nav-aside">
				<ul class="nav-aside-menu">
					<li><a href="/registration">홈</a></li>
					<li class="has-dropdown"><a>Categories</a>
						<ul class="dropdown">
							<li><a href="#">Lifestyle</a></li>
							<li><a href="#">Fashion</a></li>
							<li><a href="/registration">Technology</a></li>
							<li><a href="#">Travel</a></li>
							<li><a href="#">Health</a></li>
						</ul>
					</li>
					<li><a href="about.html">About Us</a></li>
					<li><a href="contact.html">Contacts</a></li>
					<li><a href="#">Advertise</a></li>
				</ul>
				<button class="nav-close nav-aside-close"><span></span></button>
			</div>
			<!-- /Aside Nav -->
		</div>
        <!-- /NAV -->
        
	</header> --%>
	<!-- /HEADER -->
 <!-- ##### Header Area Start ##### -->
    <header class="header_area">
        <div class="classy-nav-container breakpoint-off d-flex align-items-center justify-content-between">
            <!-- Classy Menu -->
            <nav class="classy-navbar" id="essenceNav">
                <!-- Logo -->
                <a class="nav-brand" href="/"><img src="/img/core-img/logo.png" alt=""></a>
                <!-- Navbar Toggler -->
                <div class="classy-navbar-toggler">
                    <span class="navbarToggler"><span></span><span></span><span></span></span>
                </div>
                <!-- Menu -->
                <div class="classy-menu">
                    <!-- close btn -->
                    <div class="classycloseIcon">
                        <div class="cross-wrap"><span class="top"></span><span class="bottom"></span></div>
                    </div>
                    <!-- Nav Start -->
                    <div class="classynav">
                        <ul>
                            <!-- <li><a href="#">Shop</a>
                                <div class="megamenu">
                                    <ul class="single-mega cn-col-4">
                                        <li class="title">Women's Collection</li>
                                        <li><a href="shop.html">Dresses</a></li>
                                        <li><a href="shop.html">Blouses &amp; Shirts</a></li>
                                        <li><a href="shop.html">T-shirts</a></li>
                                        <li><a href="shop.html">Rompers</a></li>
                                        <li><a href="shop.html">Bras &amp; Panties</a></li>
                                    </ul>
                                    <ul class="single-mega cn-col-4">
                                        <li class="title">Men's Collection</li>
                                        <li><a href="shop.html">T-Shirts</a></li>
                                        <li><a href="shop.html">Polo</a></li>
                                        <li><a href="shop.html">Shirts</a></li>
                                        <li><a href="shop.html">Jackets</a></li>
                                        <li><a href="shop.html">Trench</a></li>
                                    </ul>
                                    <ul class="single-mega cn-col-4">
                                        <li class="title">Kid's Collection</li>
                                        <li><a href="shop.html">Dresses</a></li>
                                        <li><a href="shop.html">Shirts</a></li>
                                        <li><a href="shop.html">T-shirts</a></li>
                                        <li><a href="shop.html">Jackets</a></li>
                                        <li><a href="shop.html">Trench</a></li>
                                    </ul>
                                    <div class="single-mega cn-col-4">
                                        <img src="img/bg-img/bg-6.jpg" alt="">
                                    </div>
                                </div>
                            </li> -->
      <!--                       여기서부터 메뉴바내려오게 할수있는데 나중에 수정  -->
                            <!-- <li><a href="#">Pages</a>
                                <ul class="dropdown">
                                    <li><a href="/">Home</a></li>
                                    <li><a href="shop.html">Shop</a></li>
                                    <li><a href="single-product-details.html">Product Details</a></li>
                                    <li><a href="checkout.html">Checkout</a></li>
                                    <li><a href="blog.html">Blog</a></li>
                                    <li><a href="single-blog.html">Single Blog</a></li>
                                    <li><a href="regular-page.html">Regular Page</a></li>
                                    <li><a href="contact.html">Contact</a></li>
                                </ul>
                            </li> -->
                            <li><a href="/Multi/FreeBoardList.do?bgno=1">Portfolio List</a></li>
                            <li><a href="contact.html">Contact</a></li>
                        </ul>
                    </div>
                    <!-- Nav End -->
                </div>
            </nav>

            <!-- Header Meta Data -->
            <div class="header-meta d-flex clearfix justify-content-end">
                <!-- Search Area -->
                <div class="search-area">
                    <form action="#" method="post">
                        <input type="search" name="search" id="headerSearch" placeholder="Type for search">
                        <button type="submit"><i class="fa fa-search" aria-hidden="true"></i></button>
                    </form>
                </div>
                <!-- Favourite Area -->
                <div class="favourite-area">
                    <a href="#"><img src="/img/core-img/heart.svg" alt=""></a>
                </div> 
                <!-- User Login Info -->
        
                <div class="user-login-info">
          <c:choose>
 		 <c:when test="${sessionScope.userid==null}">
                    <a href="/login"><img src="/img/core-img/user.svg" alt=""></a>
                    </c:when>
						<c:otherwise>
						<a href="/member/logout.do"><img src="/img/core-img/user.svg" alt=""></a>
						</c:otherwise>
		</c:choose>
                </div>
                <!-- Cart Area -->
                <div class="cart-area">
                    <a href="#" id="essenceCartBtn"><img src="/img/core-img/bag.svg" alt=""> <span>3</span></a>
                </div>
            </div>

        </div>
    </header>
    <!-- ##### Header Area End ##### -->

    


	<!-- jQuery Plugins -->
	<script src="/js/jquery.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="/js/jquery.stellar.min.js"></script>
	<script src="/js/main.js"></script>

</body>
</html>