<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html class="no-js" lang="ko">
<!--<![endif]-->

<head>
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <title>EVERYONE'S POLITICS</title>
  <meta name="description" content="" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <link rel="apple-touch-icon" href="apple-touch-icon.png" />

  <!--Google Fonts link-->
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,300i,400,400i,600,600i,700,700i"
        rel="stylesheet" />
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,400i,600,600i,700,700i" rel="stylesheet" />

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

  <script src="../resources/js/vendor/modernizr-2.8.3-respond-1.4.2.min.js"></script>

  <!-- fullcalendar CDN -->
  <link href='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.css' rel='stylesheet' />
  <script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.js'></script>
  <!-- fullcalendar 언어 CDN -->
  <script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/locales-all.min.js'></script>
  <script src="https://unpkg.com/@popperjs/core@2/dist/umd/popper.js"></script>
  <script src="https://unpkg.com/tippy.js@6"></script>
  <!-- calendar custom JS-->
  <script src="../resources/js/calendar.js"></script>

  <!-- Swiper -->
  <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
  <link
          rel="stylesheet"
          href="https://unpkg.com/swiper/swiper-bundle.min.css"
  />

  <!-- font Awesome-->
  <script src="https://kit.fontawesome.com/a4196a8ce8.js" crossorigin="anonymous"></script>

  <!-- Chart js-->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>




  <style>
    .fc-day:hover{background: lightblue; cursor: pointer;}
  </style>

</head>
<sec:authorize access="isAuthenticated()">
  <sec:authentication property="principal" var="principal"/>
</sec:authorize>

<body data-spy="scroll" data-target=".navbar-collapse">
<div class="preloader">
  <div class="loaded">&nbsp;</div>
</div>
<div class="culmn">
  <header id="main_menu" class="header navbar-fixed-top">
    <div class="main_menu_bg">
      <div class="container">
        <div class="row">
          <div class="nave_menu">

            <nav class="navbar navbar-default">
              <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                          data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                  </button>
                  <a class="navbar-brand" href="/">
                    <img src="../resources/images/logo.png" />
                  </a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->

                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                  <ul class="nav navbar-nav navbar-right">
                    <li><a href="#home">메인</a></li>
                    <li><a href="#history">대선 스케줄</a></li>
                    <li><a href="#portfolio">대선 공약</a></li>
                    <li><a href="#pricing">대선 비하인드</a></li>
                    <li><a href="#team">대선 게시판</a></li>
                    <li><a href="#blog">모의 투표</a></li>
                    <li>
                      <sec:authorize access="!isAuthenticated()">
                        <a href="https://kauth.kakao.com/oauth/authorize?client_id=17d0622174b2b4cd77314e16dd2d9dde&redirect_uri=http://localhost:8080/auth/kakao/callback&response_type=code">로그인</a>
<%--                      <sec:authentication property="name" />님 안녕하세요 ^^ 예외--%>
                      </sec:authorize>
                    </li>
                      <sec:authorize access="isAuthenticated()">
                        <li><a href="/vote/myProfileInfo">마이페이지</a></li>
                       <li><a href="/logout">로그아웃</a></li>
                      </sec:authorize>
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

  <!--home Section (countdown 메인배너)-->
  <section id="home" class="home">
    <div class="overlay">
      <div class="home_skew_border">
        <div class="container">
          <div class="row">
            <div class="col-sm-12">
              <div class="main_home_slider text-center">
                <div class="single_home_slider">
                  <div class="main_home wow fadeInUp" data-wow-duration="700ms">
                    <h3>당신의 미래에 투표하세요</h3>
                    <h1>EVERYONE'S POLITICS</h1>
                    <div class="separator"></div>
                    <h1 id="countdown">
                      The most spectacular coming soon template!
                    </h1>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>



        <!-- Feature section -->

        <div class="container">
          <div class="row">
            <div class="main_feature text-center">

              <a href="#history">
                <div class="col-sm-3">
                  <div class="single_feature">
                    <div class="single_feature_icon">
                      <i class="fa fa-clone"></i>
                    </div>
                    <h4>스케줄</h4>
                    <div class="separator3"></div>
                  </div>
                </div>
              </a>

              <a href="/vote/promiseList">
                <div class="col-sm-3">
                  <div class="single_feature">
                    <div class="single_feature_icon">
                      <i class="fa fa-heart-o"></i>
                    </div>
                    <h4>공약</h4>
                    <div class="separator3"></div>
                  </div>
                </div>
              </a>

              <a href="/vote/behindList">
                <div class="col-sm-3">
                  <div class="single_feature">
                    <div class="single_feature_icon">
                      <i class="fa fa-lightbulb-o"></i>
                    </div>
                    <h4>비하인드</h4>
                    <div class="separator3"></div>
                  </div>
                </div>
              </a>

              <a href="/vote/boardList">
                <div class="col-sm-3">
                  <div class="single_feature">
                    <div class="single_feature_icon">
                      <i class="fa fa-comments-o"></i>
                    </div>
                    <h4>게시판</h4>
                    <div class="separator3"></div>
                  </div>
                </div>
              </a>

            </div>

            <!-- End Feature section -->

          </div>
        </div>
      </div>
    </div>
  </section>
  <!--End of home section (메인배너 끝) -->


  <!-- History section (스케줄)-->
  <section id="history" class="history sections">
    <div class="container">
      <div class="row">
        <div class="main_history">
          <div class="col-sm-12">
            <div id="calendar"></div>

          </div>
        </div>
      </div>
      <!--End of row -->
    </div>
    <!--End of container -->
  </section>
  <!--End of history  (스케줄 끝) -->


  <!-- Portfolio section (공약)-->
  <section id="portfolio" class="portfolio sections">
    <div class="container" style="height: 584px;">
      <div class="row">
        <div class="main_portfolio">
          <div class="col-md-12">
            <div class="head_title text-center">
              <h2>대선 공약</h2>
              <div class="subtitle">
                대통령 후보님들의 공약을 한눈에 찾아보세요
              </div>
              <div class="separator"></div>
            </div>
          </div>
          <div class="work_menu text-center">
            <div id="filters" class="toolbar mb2 mt2">
              <button class="btn-md fil-cat filter" data-filter=".all" onclick="change_filter('all')">
                ALL</button>/
              <button class="btn-md fil-cat filter" data-rel="blue" data-filter=".blue" onclick="change_filter('이재명')">
                이재명</button>/
              <button class="btn-md fil-cat filter" data-rel="red" data-filter=".red" onclick="change_filter('윤석열')">
                윤석열</button>/
              <button class="btn-md fil-cat filter" data-rel="yellow" data-filter=".yellow" onclick="change_filter('심상정')">
                심상정</button>/
              <button class="btn-md fil-cat filter" data-rel="bcards" data-filter=".bcards" onclick="change_filter('안철수')">
                안철수</button>/
            </div>
          </div>
        </div>
      </div>


      <div id="portfoliowork">
        <div class="single_portfolio tile scale-anm red ">
          <img src="../resources/images/pf1.jpeg"/>
          <a href="/vote/promiseList">
            <div class="grid_item_overlay">
              <h3>자세히보기</h3>
            </div>
          </a>
        </div>
        <div class="single_portfolio">
          <img src="../resources/images/pf2.jpeg"/>
          <a href="/vote/promiseList" class="portfolio-img">
            <div class="grid_item_overlay">
              <h3>자세히보기</h3>
            </div>
          </a>
        </div>
        <div class="single_portfolio tile scale-anm red">
          <img src="../resources/images/pf3.jpeg"/>
          <a href="/vote/promiseList" class="portfolio-img">
            <div class="grid_item_overlay">
              <h3>자세히보기</h3>
            </div>
          </a>
        </div>
        <div class="single_portfolio tile scale-anm red ">
          <img src="../resources/images/pf4.jpeg"/>
          <a href="/vote/promiseList" class="portfolio-img">
            <div class="grid_item_overlay">
              <h3>자세히보기</h3>
            </div>
          </a>
        </div>
        <div class="single_portfolio tile scale-anm blue">
          <img src="../resources/images/pf5.png"/>
          <a href="/vote/promiseList" class="portfolio-img">
            <div class="grid_item_overlay">
              <h3>자세히보기</h3>
            </div>
          </a>
        </div>

        <div class="single_portfolio tile scale-anm blue">
          <img src="../resources/images/pf6.png"/>
          <a href="/vote/promiseList" class="portfolio-img">
            <div class="grid_item_overlay">
              <h3>자세히보기</h3>
            </div>
          </a>
        </div>
        <div class="single_portfolio tile scale-anm blue">
          <img src="../resources/images/pf7.png"/>
          <a href="/vote/promiseList" class="portfolio-img">
            <div class="grid_item_overlay">
              <h3>자세히보기</h3>
            </div>
          </a>
        </div>
      </div>

      <div style="clear: both"></div>
    </div>

    <!-- End off container -->
  </section>
  <!-- End off Portfolio Section (공약 끝) -->




  <!-- Pricing Section (비하인드) -->
  <section id="pricing" class="pricing sections">
    <div class="container">
      <div class="pricing-title">
        <h2>대선 비하인드</h2>
        <div class="subtitle">
          대통령 후보님들의 비하인드를 한눈에 찾아보세요
        </div>
      </div>
      <!-- Swiper -->
      <div class="swiper mySwiper02" >
        <div class="swiper-wrapper">
          <div class="swiper-slide">
            <div class="swiper-slide-gallbox-pic01 galloboxstyle">
              <a href="/vote/behindList">
                <h2>이재명</h2>
                <p>새로운 대한민국<br />이재명은 합니다!</p>
              </a>
            </div>
          </div>
          <div class="swiper-slide">
            <div class="swiper-slide-gallbox-pic02 galloboxstyle">
              <a href="/vote/behindList">
                <h2>윤석열</h2>
                <p>공정과 상식의 나라<br />되찾겠습니다.</p>
              </a>
            </div>
          </div>
          <div class="swiper-slide">
            <div class="swiper-slide-gallbox-pic03 galloboxstyle ">
              <a href="/vote/behindList">
                <h2>심상정</h2>
                <p>
                  정의로운 복지국가,<br />정의당이 시작합니다
                </p>
              </a>
            </div>
          </div>
          <div class="swiper-slide">
            <div class="swiper-slide-gallbox-pic04">
              <a href="/vote/behindList">
                <h2>국민의당</h2>
                <p>말과 행동이 같은 정당</p>
              </a>
            </div>
          </div>
        </div>
        <div class="swiper-pagination"></div>
      </div>
    </div>

    <!-- End off container -->
  </section>
  <!-- End off Pricing Section (비하인드 끝)-->




  <!-- Team Section (게시판)-->
  <section id="team" class="team">
    <div class="main_team_area">
      <div class="container">
        <div class="row">
          <div class="col-sm-12">
            <div class="head_title textwhite text-center margin-top-80">
              <h2>대선 게시판</h2>
              <div class="subtitle">
                여러분들의 의견을 자유롭게 나누어보세요.
              </div>
              <div class="separator"></div>
            </div>
            <!-- End off Head_title -->

            <div class="main_team margin-bottom-400">
              <div class="desk1">
                <div class="bar2">
                  <h2 class="main_bar2">인기게시물</h2>
                  <a href="/vote/boardList"><div class="more1" title="더보기"><i class="fas fa-plus fa-2x"></i></div></a>
                </div>
                <div class="list1">
                  <ul>

                    <a href="/vote/boardDetail?idx=${list[0].idx}">
                      <li>
                        <div class="cont">
                          <span class="label">${list[0].filter}</span><span class="date">${list[0].sysregdate}</span><i class="fas fa-chevron-right fa-2x"></i></i><br>
                          <h3>${list[0].title}</h3>
                        </div>
                      </li>
                    </a>

                    <a href="/vote/boardDetail?idx=${list[1].idx}">
                      <li>
                        <div class="cont">
                          <span class="label">${list[1].filter}</span><span class="date">${list[1].sysregdate}</span><i class="fas fa-chevron-right fa-2x"></i></i><br>
                          <h3>${list[1].title}</h3>
                        </div>
                      </li>
                    </a>

                    <a href="/vote/boardDetail?idx=${list[2].idx}">
                      <li>
                        <div class="cont">
                          <span class="label">${list[2].filter}</span><span class="date">${list[2].sysregdate}</span><i class="fas fa-chevron-right fa-2x"></i></i><br>
                          <h3>${list[2].title}</h3>
                        </div>
                      </li>
                    </a>


                    <a href="/vote/boardDetail?idx=${list[3].idx}">
                      <li>
                        <div class="cont">
                          <span class="label">${list[3].filter}</span><span class="date">${list[3].sysregdate}</span><i class="fas fa-chevron-right fa-2x"></i><br>
                          <h3>${list[3].title}</h3>
                        </div>
                      </li>
                    </a>

                  </ul>
                </div>
              </div>
            </div>
          </div>
          <!-- End of main team contant -->
        </div>
      </div>
      <!-- End of container -->
    </div>
  </section>
  <!-- End off Team Section(게시판 끝) -->



  <!-- Blog Section (투표)-->
  <section id="blog" class="blog" style="display: flex;">
    <div class="container-fluid">
      <div class="row">
        <div class="main_blog sections">
          <div class="head_title text-center margin-top-20">
            <h2>대선 투표</h2>
            <div class="subtitle">
              여러분들의 꿈을 투표 하세요
            </div>
            <div class="separator"></div>
          </div>
          <!-- End off Head_title -->
          <div class="work_menu text-center">
            <div id="filters2" class="toolbar mb2 mt2">
              <button onclick="click_btn('all')" class="btn-md ">
                ALL</button>/
              <button onclick="click_btn('10~19')" class="btn-md ">
                10대</button>/
              <button onclick="click_btn('20~29')" class="btn-md ">
                20대</button>/
              <button onclick="click_btn('30~39')" class="btn-md ">
                30대</button>/
              <button onclick="click_btn('40~49')" class="btn-md ">
                40대</button>/
            </div>
          </div>

          <div id="chartview" class="chartview" >
            <!--차트가 그려질 부분-->
            <canvas id="myChart"></canvas>
          </div>

          <a href="/vote/voting">
            <div class="votelink" title="투표하기">
              <span class="label">투표하기</span>
            </div>
          </a>
        </div>
      </div>
    </div>
    <!-- End off container -->
  </section>
  <!-- End off Blog Section(투표 끝) -->



  <!--Footer section-->
  <section class="footer">
    <div class="container">
      <div class="row">
        <div class="col-sm-12">
          <div class="main_footer">
            <div class="row">
              <div class="col-sm-6 col-xs-12">
                <div class="copyleft-text" style="text-align: left;">
                  <a href="mailto:jooyoung.dev@gmail.com">운영자문의</a>
                </div>
              </div>


              <div class="col-sm-6 col-xs-12">
                <div class="copyright_text">
                  <p class="wow fadeInRight" data-wow-duration="1s">
                    본 사이트에서 제공되는 모든 정보는 참고자료이며, 서비스 이용에 따른 최종 책임은 이용자에게 있습니다.<br>
                    Copyright © 2021 모두의정치. All rights reserved.
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- End off footer Section-->
</div>

<!-- START SCROLL TO TOP  -->
<div class="scrollup">
  <a href="#"><i class="fa fa-chevron-up"></i></a>
</div>

<script src="../resources/js/vendor/jquery-1.11.2.min.js"></script>
<script src="../resources/js/vendor/bootstrap.min.js"></script>

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






<!-- Initialize Swiper -->
<script>
  var swiper = new Swiper(".mySwiper02", {
    slidesPerView: 2,
    spaceBetween: 30,
    pagination: {
      el: ".swiper-pagination",
      clickable: true,
    },
    breakpoints: {
      310: {
        slidesPerView: 1,
        spaceBetween: 0,
      },
      721: {
        slidesPerView: 2,
        spaceBetween: 40,
      },
      1025: {
        slidesPerView: 4,
        spaceBetween: 20,
      },
    },
  });
</script>

<!--JavaScript chart voteChartView -->


<!-- JavaScript for BarChart-->
<script type="text/javascript">

  var myChart;
  click_btn("all");

  function click_btn(r) {

    var param = {};
    param = {age_range:r};

    $.ajax({
      type: "get",
      contentType: 'application/json',
      url: "/vote/voteChartView",
      dataType: 'json',
      data: param,

      success: function(info) {
        print_chart(info);
      }
    }); //ajax end
  }

  function print_chart(data) {
    var context = document
            .getElementById('myChart')
            .getContext('2d');
    var context = document
            .getElementById('myChart')
            .getContext('2d');
    if(myChart){
      myChart.destroy();
    }
    myChart = new Chart(context, {
      type: 'bar', // 차트의 형태
      data: { // 차트에 들어갈 데이터
        labels: [
          //x 축
          '이재명','윤석열','심상정','안철수'
        ],
        datasets: [
          { //데이터
            label: '모의 투표', //차트 제목
            fill: false, // line 형태일 때, 선 안쪽을 채우는지 안채우는지
            data: data,
            backgroundColor: [
              //색상
              'rgba(54, 162, 235, 0.2)',
              'rgba(255, 99, 132, 0.2)',
              'rgba(255, 206, 86, 0.2)',
              'rgba(75, 192, 192, 0.2)',

            ],
            borderColor: [
              //경계선 색상
              'rgba(54, 162, 235, 1)',
              'rgba(255, 99, 132, 1)',
              'rgba(255, 206, 86, 1)',
              'rgba(75, 192, 192, 1)',

            ],
            borderWidth: 2 //경계선 굵기
          }/* ,
              {
                  label: 'test2',
                  fill: false,
                  data: [
                      8, 34, 12, 24
                  ],
                  backgroundColor: 'rgb(157, 109, 12)',
                  borderColor: 'rgb(157, 109, 12)'
              } */
        ]
      },
      options: {
        legend: {display: false},
        scales: {
          yAxes: [
            {
              ticks: {
                beginAtZero: true
              }
            }
          ]
        }
      }
    });
  }
</script>

<script>
  var obj={};
  obj.dataType = "JSON";
  obj.error=function(e){console.log(e)};

  map = JSON.stringify(${json});

  cri = JSON.parse(map);

  showPage = cri.page;  //보여줄 페이지

  showAmount = 12;

  var showFill = cri.filter;

  var keyWord = cri.keyword;

  listCall(showPage); //리스트 호출 함수

  function change_filter(filter) {
    showFill = filter;
    $("#portfoliowork").fadeOut(500, function (){
      listCall(showPage);
    });
  }

  // /listSub/{pagePerCnt}/{page}
  function listCall(page){
    var param = {};
    param = {"amount":showAmount, "page":page, "filter":showFill, "keyword":keyWord};

    $.ajax({
      anyne:true,
      type:'POST',
      data: JSON.stringify(param),
      contentType: 'application/json',
      // contentType: 'application/x-www-form-urlencoded; charset=euc-kr',
      url:"/vote/getMainPromiseList/",
      dataType: "text",
      success : function(result) {
        console.log(d);
        var d = JSON.parse(result);
        message = d.message;
        if (message == "fail") {
          var content = "<div class='no_object'>게시물이 없습니다.</div>";
          //내용 붙이기
          $("#portfoliowork").empty();
          $("#portfoliowork").append(content);
        } else {
          listPrint(d.list); //리스트 그리기
          $("button[class='btn-md']").attr("checked", false);

          if(showFill == '이재명') {
            $("input:radio[id='radio1']").attr("checked", true);
          } else if(showFill == '윤석열') {
            $("input:radio[id='radio2']").attr("checked", true);
          } else if(showFill == '심상정') {
            $("input:radio[id='radio3']").attr("checked", true);
          } else if(showFill == '안철수') {
            $("input:radio[id='radio4']").attr("checked", true);
          } else {
            $("input:radio[id='radio5']").attr("checked", true);
          }
        }
      },
      error: function(jqXHR, textStatus, errorThrown) {
        alert("ERROR : " + textStatus + " : " + errorThrown);
      }
    });
  }

  //받아온 리스트 그리기
  function listPrint(list){
    if(keyWord == null || keyWord == "") {
      keyWord = "";
      $('#keyword').val('');
    } else {
      $('#keyword').val(keyWord);
    }

    content ="";

    list.forEach(function(item, idx){
      // alert(item.title);
      if(item.filter == "이재명") {
        content += '<div class="single_portfolio blue">';
      } else if (item.filter == "윤석열") {
        content += '<div class="single_portfolio red">';
      } else if(item.filter == "심상정") {
        content += '<div class="single_portfolio yellow">';
      } else if(item.filter == "안철수") {
        content += '<div class="single_portfolio green">';
      }
      content +=    '<img src="'+item.fileUrl+'"/>';
      content +=        '<a href="/vote/promiseDetail?idx='+item.idx+'" class="portfolio-img">';
      content +=            '<div class="grid_item_overlay">';
      content +=                '<h3>자세히보기</h3>'
      content +=            '</div>'
      content +=        '</a>';
      content += '</div>';
    });

    //내용 붙이기
    $("#portfoliowork").empty();
    $("#portfoliowork").append(content);

    $("#portfoliowork").fadeIn(500);
  }
</script>
</body>
</html>