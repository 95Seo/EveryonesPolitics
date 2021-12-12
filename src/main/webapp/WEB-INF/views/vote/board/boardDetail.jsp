<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../../include/header.jsp"%>
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal"/>
</sec:authorize>
<script>
    var obj={};
    obj.dataType = "JSON";
    obj.error=function(e){console.log(e)};

    board_nm = "tb_board"; //보여줄 페이지
    board_idx = JSON.parse ( ${json} );

    detailCall(board_nm, board_idx); //리스트 호출 함수

    $(document).on("click","div[class=view-vote]",function(){
        check_id = this.id;

        if(check_id == 'guest') {
            alert("로그인이 필요한 서비스 입니다.");
        } else {
            if(check_id == 'active') {
                like = 'N'
            } else if(check_id == 'no') {
                like = 'Y'
            }
            updateLike(board_nm, board_idx, like);
        }
    });

    // /vote/updateLike/{page}/{idx}/{like}
    function updateLike(page,idx,like){
        obj.url="/vote/updateLike/";
        obj.type="get";
        obj.data ={};
        obj.url += page + "/";
        obj.url += idx + "/"
        obj.url += like;
        console.log(obj.url);

        obj.success= function(d){
            console.log(d);
            detailCall(board_nm, board_idx);
        };

        ajaxCall(obj);
    }

    // /vote/board/boardDetail/{page}/{idx}
    // likeCnt - 좋아요 수
    // likeYn - 좋아요 여부
    function detailCall(page,idx){
        obj.url="/vote/board/boardDetail/";
        obj.type="get";
        obj.data ={};
        obj.url += page + "/";
        obj.url += idx;
        console.log(obj.url);

        obj.success= function(d){
            console.log(d);
            likePrint(d.likeCnt, d.like.likeYn); //리스트 그리기
        };
        ajaxCall(obj);
    }

    // ajax 호출
    function ajaxCall(obj){
        $.ajax(obj);
    }

    // 좋아요 그리기
    function likePrint(likes, my_like){
        like_cnt="<b>"+likes+"</b>";
        if(my_like == 'Y') {
            $("div[class=view-vote]").attr('id', 'active');
        } else if(my_like == 'N') {
            $("div[class=view-vote]").attr('id', 'no');
        } else {
            $("div[class=view-vote]").attr('id', 'guest');
        }
        //내용 붙이기
        $(".likeCnt").empty();
        $(".likeCnt").append(like_cnt);
    }
</script>
<section class="board-detail common-list">
    <div class="container">
        <div class="row">
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
                                    <a href="<c:out value="${user.profile_img}"/>"><c:out value="${user.nickname}"/></a>
                                    <p>14일전</p>
                                </div>
                            </div>
                            <div class="view-inner-content">
                                <div class="view-title" id="title">
                                    <h2><c:out value="${board.title}" /></h2>
                                </div>
                                <div class="recommend-view-flex">
                                    <div class="recommend-view-flex-wrap">
                                        <div class="recommend-view-flex-wrap-left">
                                            <span class="recommend-span-txt">조회 수</span>
                                            <span class="recmmend-span-num" id="viewCnt"><b><c:out value="${board.viewCnt}" /></b></span>
                                        </div>
                                        <div class="recommend-view-flex-wrap-right">
                                            <span class="recommend-span-txt">추천 수</span>
                                            <span class="likeCnt recmmend-span-num"></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="view-txt" id="content">
                            <c:out value="${board.content}" escapeXml="false" />
                        </div>
                        <div class="view-button-vote">
                            <div name="like" class="view-vote">
                                <div>
                                    <i class="fas fa-heart"></i>
                                    <span id="view-vote-count" class="likeCnt"></span>
                                </div>
                                <p>공감</p>
                            </div>
                        </div>
                        <div class="view-button-box">
                            <div class="view-list-button">
                                <a href="/vote/boardList?&page=${cri.page}&fill=${cri.fill}">
                                    <button>목록</button>
                                </a>
                            </div>
                            <c:if test="${board.writerIdx==principal.idx}">
                                <div class="view-write-button">
                                    <a href="/vote/boardUpdate?idx=${board.idx}&page=${cri.page}&fill=${cri.fill}">
                                        <button>수정</button>
                                    </a>
                                </div>
                            </c:if>
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
        </div>
    </div>

</section>
<%@include file="../../include/footer.jsp"%>