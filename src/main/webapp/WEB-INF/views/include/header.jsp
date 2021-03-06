<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="ko">
  <!--<![endif]-->

  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>모두의정치</title>

    <!-- 선호 URL -->
    <link rel="canonical" href="https://www.everyonespolitics.site/">
    <!-- 페이지 설명 -->
    <meta name="description" content="대선관련 정치 커뮤니티 웹사이트입니다."/>
    <!-- Open Graph 제목 -->
    <meta property="og:title" content="모두의정치">
    <!-- Open Graph 설명 -->
    <meta property="og:description" content="대선관련 정치 커뮤니티 웹사이트입니다.">

    <!-- 모바일 사용성 -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <!-- Open Graph Url -->
    <meta property="og:url" content="https://www.everyonespolitics.site/">

    <!-- 사이트 타입 -->
    <meta property="og:type" content="website">

    <!-- 사이트 소유확인을 위한 HTML 태그 -->
    <meta name="naver-site-verification" content="28d53048530f4a5fcbbf6000f62d6733132ca7e5" />
    <meta name="google-site-verification" content="zxUk91IP7EFeuB8ylxPf5ON87CrCoVNtttdS0IWDF2I" />

    <link rel="apple-touch-icon" href="apple-touch-icon.png" />

    <!--Google Fonts link-->
    <link
      href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,300i,400,400i,600,600i,700,700i"
      rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,400i,600,600i,700,700i" rel="stylesheet"/>

    <!-- Additional CSS Files -->
    <link rel="stylesheet" href="../resources/css/templatemo-edu-meeting.css" />

    <link rel="stylesheet" href="../resources/css/iconfont.css" />
    <link rel="stylesheet" href="../resources/css/slick/slick.css" />
    <link rel="stylesheet" href="../resources/css/slick/slick-theme.css" />
    <link rel="stylesheet" href="../resources/css/font-awesome.min.css" />
    <link rel="stylesheet" href="../resources/css/jquery.fancybox.css" />
    <link rel="stylesheet" href="../resources/css/bootstrap.css" />
    <link rel="stylesheet" href="../resources/css/bootstrap.min.css" />
    <link rel="stylesheet" href="../resources/css/magnific-popup.css" />
    <!--        <link rel="stylesheet" href="../resources/css/bootstrap-theme.min.css">-->

    <!--For Plugins external css-->
    <link rel="stylesheet" href="../resources/css/plugins.css" />

    <!--Theme custom css -->
    <link rel="stylesheet" href="../resources/css/style.css" />

    <!--Theme Responsive css-->
    <link rel="stylesheet" href="../resources/css/responsive.css" />

    <script src="../resources/js/vendor/modernizr-2.8.3-respond-1.4.2.min.js"></script>

    <script
            type="text/javascript"
            src="../resources/css/ckeditor/ckeditor.js"
    ></script>

    <!-- font Awesome-->
  <script src="https://kit.fontawesome.com/a4196a8ce8.js" crossorigin="anonymous"></script>



    <script src="../resources/js/isotope.min.js"></script>
    <script src="../resources/js/custom.js"></script>

    <script src="../resources/js/vendor/jquery-1.11.2.min.js"></script>

    <script src="../resources/js/jquery.magnific-popup.js"></script>
    <script src="../resources/js/jquery.mixitup.min.js"></script>
    <script src="../resources/js/jquery.easing.1.3.js"></script>
    <script src="../resources/js/jquery.masonry.min.js"></script>

    <!--slick slide js -->
    <script src="../resources/css/slick/slick.js"></script>
    <script src="../resources/css/slick/slick.min.js"></script>

    <script src="../resources/js/jquery.countdown.min.js"></script>
    <script src="../resources/js/plugins.js"></script>
    <script src="../resources/js/main.js"></script>
    <script src="../resources/js/calcdate.js"></script>

  </head>

  <style>


    .header{
      background-color: rgba(34, 61, 97, 0.64);
    }
  </style>
  <sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal"/>
  </sec:authorize>

  <body data-spy="scroll" data-target=".navbar-collapse">
    <div class="preloader">
      <div class="loaded">&nbsp;</div>
    </div>
    <div class="culmn">
      <header id="main_menu" class="header navbar-fixed-top ">
        <div class="main_menu_bg">
          <div class="container">
            <div class="row">
              <div class="nave_menu">
                <nav class="navbar navbar-default">
                  <div class="container-fluid">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                      <button
                        type="button"
                        class="navbar-toggle collapsed"
                        data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1"
                        aria-expanded="false"
                      >
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                      </button>
                      <a class="navbar-brand" href="/" >
                        <img src="../resources/images/logo.png" />
                      </a>
                    </div>

                    <!-- Collect the nav links, forms, and other content for toggling -->

                    <div
                      class="collapse navbar-collapse"
                      id="bs-example-navbar-collapse-1"
                    >
                      <ul class="nav navbar-nav navbar-right">
                        <li><a href="/vote/promiseList">대선 공약</a></li>
                        <li><a href="/vote/behindList">대선 비하인드</a></li>
                        <li><a href="/vote/boardList">대선 게시판</a></li>
                        <li><a href="/vote/voting">모의 투표</a></li>
                        <c:choose>
                          <c:when test="${empty principal.username}">
                           <li ><a href="https://kauth.kakao.com/oauth/authorize?client_id=17d0622174b2b4cd77314e16dd2d9dde&redirect_uri=http://localhost:8080/auth/kakao/callback&response_type=code">로그인</a></li>
                          </c:when>
                          <c:otherwise>
                            <li><a href="/vote/myProfileInfo">마이페이지</a></li>
                            <li><a href="/logout">로그아웃</a></li>
                          </c:otherwise>

                        </c:choose>

                      </ul>
                    </div>
                  </div>
                </nav>
              </div>
            </div>
          </div>
        </div>
      </header>
      <!--End of header -->
    </div>
