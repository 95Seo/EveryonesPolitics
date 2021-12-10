<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
                <div class="view-inner-content">
                    <div class="view-title">
                        <h2>이재명 曰 내가 계곡 탁상 다 밀어버린다</h2>
                    </div>
                    <div class="recommend-view-flex">
                        <div class="recommend-view-flex-wrap">
                            <div class="recommend-view-flex-wrap-left">
                                <span class="recommend-span-txt">조회 수</span>
                                <span class="recmmend-span-num"><b>12</b></span>
                            </div>
                            <div class="recommend-view-flex-wrap-right">
                                <span class="recommend-span-txt">추천 수</span>
                                <span class="recmmend-span-num"><b>2</b></span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="view-txt">
                <p>이것들이 말이야 좋게좋게 말하니까 말도 안듣고<br>
                    제가 조질꺼니가 걱정하지 마십쇼<br>
                    야 탁상 다밀어 이것들아<br>
                </p>
            </div>
            <div class="view-button-vote">
                <a href="#none" class="view-vote">
                    <div>
                        <i class="fas fa-heart"></i>
                        <span id="view-vote-count">1</span>
                    </div>
                    <p>공감</p>
                </a>
                <a href="#none" class="view-declare">
                    <div>
                        <i class="fas fa-exclamation-triangle"></i>
                        <span id="view-declare-count">2</span>
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