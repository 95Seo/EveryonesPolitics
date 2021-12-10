<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="../../include/header.jsp"%>
<link rel="stylesheet" href="../resources/css/myprofile.css">

<section class="myprofile-info">
    <div class="myprofile-write-wrap">
        <div class="myprofile-inner">
            <div class="myprofile-box-01">
                <div class="myprofile-left">
                    <div class="myprofile-left-img">
                        <img src="../resources/images/behind01.jpg" alt="">
                    </div>
                </div>
                <div class="myprofile-right">
                    <h3>서영준</h3>
                    <a href="/vote/myProfileUpdate"><button>회원정보변경</button></a>
                </div>
            </div>
            <div class="myprofile-box-02">
                <ul>
                    <li><a href="/vote/myProfileInfo">회원정보 보기</a>
                    </li>
                    <li><a href="/vote/myProfileWrite">작성 글 보기</a>
                    </li>
                </ul>
            </div>
            <hr>
            <div class="myprofile-write-box-03">
                <h3>작성 글 보기</h3>
            </div>
            <!-- board Section(게시판 리스트)-->
            <div class="myprofile-list">
                <div class="myprofile-list-wrap">
                    <div class="promise-wrap">
                        <div class="list-box">
                            <ul class="list-hd">
                                <li>
                                    <div class="no">번호</div>
                                    <div class="subject">제목</div>
                                    <div class="date">등록일</div>
                                    <div class="writer">글쓴이</div>
                                    <div class="view-num">조회 수</div>
                                    <div class="recommend-num">추천 수</div>
                                </li>
                            </ul>
                            <ul class="list-bd">
                                <li>
                                    <div class="no">1</div>
                                    <div class="subject"><a href="/vote/boardDetail">게시물 제목이 출력되는곳</a></div>
                                    <div class="date">2021-12-31</div>
                                    <div class="writer">백동채</div>
                                    <div class="view-num">4</div>
                                    <div class="recommend-num">65</div>
                                </li>
                                <li>
                                    <div class="no">2</div>
                                    <div class="subject"><a href="/vote/boardDetail">게시물 제목이 출력되는곳</a></div>
                                    <div class="date">2021-12-23</div>
                                    <div class="writer">백동채</div>
                                    <div class="view-num">4</div>
                                    <div class="recommend-num">65</div>

                                </li>
                                <li>
                                    <div class="no">3</div>
                                    <div class="subject"><a href="/vote/boardDetail">게시물 제목이 출력되는곳</a></div>
                                    <div class="date">2021-12-23</div>
                                    <div class="writer">백동채</div>
                                    <div class="view-num">4</div>
                                    <div class="recommend-num">65</div>
                                </li>
                                <li>
                                    <div class="no">4</div>
                                    <div class="subject"><a href="/vote/boardDetail">게시물 제목이 출력되는곳</a></div>
                                    <div class="date">2021-12-23</div>
                                    <div class="writer">백동채</div>
                                    <div class="view-num">4</div>
                                    <div class="recommend-num">65</div>
                                </li>
                                <li>
                                    <div class="no">5</div>
                                    <div class="subject"><a href="/vote/boardDetail">게시물 제목이 출력되는곳</a></div>
                                    <div class="date">2021-12-23</div>
                                    <div class="writer">백동채</div>
                                    <div class="view-num">4</div>
                                    <div class="recommend-num">65</div>
                                </li>
                                <li>
                                    <div class="no">6</div>
                                    <div class="subject"><a href="/vote/boardDetail">게시물 제목이 출력되는곳</a></div>
                                    <div class="date">2021-12-23</div><div class="writer">백동채</div>
                                    <div class="view-num">4</div>
                                    <div class="recommend-num">65</div>
                                </li>

                            </ul>
                        </div>
                        <div class="myprofile-list-btn">
                            <a href="/vote/boardCreate">글쓰기</a>
                        </div>

                        <div class="myprofile-paging">
                            <ul>
                                <li><a href="#none">처음</a></li>
                                <li><a href="#none">1</a></li>
                                <li><a href="#none">2</a></li>
                                <li><a href="#none">3</a></li>
                                <li><a href="#none">마지막</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<%@include file="../../include/footer.jsp"%>