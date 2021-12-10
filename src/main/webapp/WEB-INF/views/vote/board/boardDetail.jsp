<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="../../include/header.jsp"%>
<div class="view-wrap">
    <div class="view-box">
        <div class="view-nav-title">
            <h2>자유게시판</h2>
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
                        <h2>윤석열이 왜 당대표... 20,30세대 분노</h2>
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
                <p>낮엔 파란 하늘 별이 보이는 밤 기분 좋은날 오랜만에 볼까<br>
                    내가 사랑하는 삶을 사랑하지 나는 기분 좋은날 우야야야양야<br>
                    아저스트워너 비 칠 마 호미 하얀색의 모래 또 파란색의 물위에<br>
                    파도들이 쓱 밀려오지 전화해둬 잡아줘 펜션 이미 올라온 텐션 yeah
                    나는 문재인을 사랑합니다.
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
                    <a href="/vote/boardList">
                        <button>목록</button>
                    </a>
                </div>
                <div class="view-write-button">
                    <a href="/vote/boardCreate">
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