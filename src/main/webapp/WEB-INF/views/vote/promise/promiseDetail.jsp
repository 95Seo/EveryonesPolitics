<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../../include/header.jsp"%>

<div class="view-wrap">
    <div class="view-box">
        <div class="view-nav-title">
            <h2>공약게시판</h2>
        </div>
        <div class="view-inner-wrap">
            <div class="view-inner-wrap-01">
                <div class="view-profile">
                    <div class="view-profile-left">
                        <div class="view-profile-left-img">
                        </div>
                    </div>
                    <div class="view-profile-right">
                        <a href="#none">글쓴이</a>
                        <p>14일전</p>
                    </div>
                </div>
            </div>
                <div class="view-inner-content">
                    <div class="view-title">
                        <h2><c:out value="${promiseDetail.title}" /></h2>
                    </div>
                    <div class="recommend-view-flex">
                        <div class="recommend-view-flex-wrap">
                            <div class="recommend-view-flex-wrap-left">
                                <span class="recommend-span-txt">조회 수</span>
                                <span class="recmmend-span-num"><b><c:out value="${promiseDetail.viewCnt}" /></b></span>
                            </div>
                            <div class="recommend-view-flex-wrap-right">
                                <span class="recommend-span-txt">추천 수</span>
                                <span class="recmmend-span-num"><b><c:out value="${promiseDetail.likeCnt}" /></b></span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="view-txt">
                <p><c:out value="${promiseDetail.content}" /></p>
            </div>
            <div class="view-button-vote">
                <a href="#none" class="view-vote">
                    <div>
                        <i class="fas fa-heart"></i>
                        <span id="view-vote-count"><c:out value="${promiseDetail.likeCnt}" /></span>
                    </div>
                    <p>공감</p>
                </a>
                <a href="#none" class="view-declare">
                    <div>
                        <i class="fas fa-exclamation-triangle"></i>
                        <span id="view-declare-count"><c:out value="${promiseDetail.dislikeCnt}" /></span>
                    </div>
                    <p>신고</p>
                </a>
            </div>
            <div class="view-button-box">
                <div class="view-list-button">
                    <a href="/vote/promiseList">
                        <button>목록</button>
                    </a>
                </div>
                <div class="view-write-button">
                    <a href="/vote/promiseCreate">
                        <button>글쓰기</button>
                    </a>
                </div>
            </div>
        </div>
        <div class="comment-wrap">
            <div id="form-commentInfo">
                <div id="comment-count">댓글<span id="count">0</span>
                </div>
                <div class="comment-submit">
                    <input id="comment-input" placeholder="댓글을 입력해 주세요.">
                    <div class="button-submit">
                        <button id="submit">등록</button>
                    </div>
                </div>
            </div>
            <div id=comments>
            </div>

            <script src="../resources/js/comment.js"></script>
        </div>
    </div>
</div>
<%@include file="../../include/footer.jsp"%>