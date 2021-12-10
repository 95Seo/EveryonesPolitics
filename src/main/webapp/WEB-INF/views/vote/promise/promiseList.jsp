<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="../../include/header.jsp"%>

<!-- board Section(게시판 리스트)-->
<section id="board-list" class="board-list">
    <div class="promise-list-wrap">
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
                        <div class="subject"><a href="/vote/promiseDetail">게시물 제목이 출력되는곳</a></div>
                        <div class="date">2021-12-31</div>
                        <div class="writer">백동채</div>
                        <div class="view-num">4</div>
                        <div class="recommend-num">65</div>
                    </li>
                    <li>
                        <div class="no">2</div>
                        <div class="subject"><a href="/vote/promiseDetail">게시물 제목이 출력되는곳</a></div>
                        <div class="date">2021-12-23</div>
                        <div class="writer">백동채</div>
                        <div class="view-num">4</div>
                        <div class="recommend-num">65</div>
                    </li>
                    <li>
                        <div class="no">3</div>
                        <div class="subject"><a href="/vote/promiseDetail">게시물 제목이 출력되는곳</a></div>
                        <div class="date">2021-12-23</div>
                        <div class="writer">백동채</div>
                        <div class="view-num">4</div>
                        <div class="recommend-num">65</div>
                    </li>
                    <li>
                        <div class="no">4</div>
                        <div class="subject"><a href="/vote/promiseDetail">게시물 제목이 출력되는곳</a></div>
                        <div class="date">2021-12-23</div>
                        <div class="writer">백동채</div>
                        <div class="view-num">4</div>
                        <div class="recommend-num">65</div>
                    </li>
                    <li>
                        <div class="no">5</div>
                        <div class="subject"><a href="/vote/promiseDetail">게시물 제목이 출력되는곳</a></div>
                        <div class="date">2021-12-23</div>
                        <div class="writer">백동채</div>
                        <div class="view-num">4</div>
                        <div class="recommend-num">65</div>
                    </li>
                    <li>
                        <div class="no">6</div>
                        <div class="subject"><a href="/vote/promiseDetail">게시물 제목이 출력되는곳</a></div>
                        <div class="date">2021-12-23</div><div class="writer">백동채</div>
                        <div class="view-num">4</div>
                        <div class="recommend-num">65</div>
                    </li>

                </ul>
            </div>
            <div class="list-btn">
                <a href="/vote/promiseCreate">글쓰기</a>
            </div>

            <div class="paging">
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
</section>

<%@include file="../../include/footer.jsp"%>