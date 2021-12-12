<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="../../include/header.jsp"%>

<!-- board Section(게시판 리스트)-->
<section id="board-list" class="board-list common-list">
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
                    <c:forEach items="${promiseboardList}" var="promise_list">
                        <li>
                            <div class="no"><c:out value="${promise_list.idx}" /></div>
                            <div class="subject"><a href="/vote/promiseDetail?idx=${promise_list.idx}"><c:out value="${promise_list.title}" /></a></div>
                            <div class="date"><c:out value="${promise_list.sysregdate}" /></div>
                            <div class="writer"><c:out value="${promise_list.writerIdx}" /></div>
                            <div class="view-num"><c:out value="${promise_list.viewCnt}" /></div>
                            <div class="recommend-num">0</div>
                        </li>
                    </c:forEach>
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