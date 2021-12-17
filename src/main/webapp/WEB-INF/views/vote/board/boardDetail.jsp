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
    board_idx = JSON.parse ( ${idx} );

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
<style>
    .view-inner-wrap > .view-inner-wrap-01 > .view-profile > .view-profile-left > .view-profile-left-img > img {
        width: 100%;
        height: 100%;
        object-fit: cover;
    }
    .view-profile-right > a {
        color: black;
    }
</style>
<section class="board-detail common-list" style="margin-top: 90px;">
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
                                        <img src="<c:out value='${user.profile_img}'/>">
                                    </div>
                                </div>
                                <div class="view-profile-right">
                                    <a href=""><c:out value="${user.nickname}"/></a>
                                    <p><c:out value="${calcTime}"/></p>
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
                                    <i class="far fa-heart"></i>
                                    <span id="view-vote-count" class="likeCnt"></span>
                                </div>
                                <p>공감</p>
                            </div>
                        </div>


                        <div class="view-button-box">
                            <div class="view-list-button">
                                <a href="/vote/boardList?&page=${page}&filter=${filter}&keyword=${keyword}">
                                    <button>목록</button>
                                </a>
                            </div>
                            <c:if test="${board.writerIdx==principal.idx}">
                                <div class="view-button-box-02">
                                <div class="view-delete-button">
                                    <a href="/vote/boardDelete?idx=${board.idx}&page=${page}&filter=${filter}&keyword=${keyword}">
                                        <button>삭제</button>
                                    </a>
                                </div>
                                <div class="view-write-button">
                                    <a href="/vote/boardUpdate?idx=${board.idx}&page=${page}&filter=${filter}&keyword=${keyword}">
                                        <button>수정</button>
                                    </a>
                                </div>
                                </div>
                            </c:if>
                        </div>

                        <!--                     추가                         -->
                        <!--  댓글  -->

                        <label for="comment_content" style="margin-bottom: 10px; color: #030303; font-weight: 400; font-size: 1.6rem;">댓글</label>
                        <form name="commentInsertForm" style="margin-bottom: 50px;">
                            <div class="input-group" style="display: flex">
                                <div class="imput-group-left" >
                                    <img src="<c:out value='${user.profile_img}'/>" style="height: 40px;" width="40px;">
                                </div>
                                <div class="input-group-right" style="width: 80%;">
                                    <input type="hidden" name="bno" value="${board.idx}"/>
                                    <input type="text" class="form-control" id="comment_content" name="comment_content" placeholder="공개 댓글 추가...">
                                </div>
                                <span class="input-group-btn">
                                    <button class="btn btn-default" type="button" name="commentInsertBtn" style="background:rgba(0, 0, 0, 0.05); color: rgba(0, 0, 0, 0.05); margin-top: 0px; padding: 10px; min-width: 100px">댓글</button>
                                 </span>
                            </div>
                        </form>

                        <div class="commentList">

                        </div>

                    </div>
                    <!--                     추가                         -->
                    <script>
                        var bno = ${board.idx}; //게시글 번호

                        $('[name=commentInsertBtn]').click(function(){ //댓글 등록 버튼 클릭시
                            var insertData = $('[name=commentInsertForm]').serialize(); //commentInsertForm의 내용을 가져옴
                            commentInsert(insertData); //Insert 함수호출(아래)
                        });



                        //댓글 목록
                        function commentList(){
                            $.ajax({
                                url : '/comment/list',
                                type : 'get',
                                data : {'bno':bno},
                                success : function(data){
                                    var a ='';
                                    $.each(data, function(key, value){
                                        a += '<div class="commentList-wrap" style="display: flex">';
                                        a += '<div class="commentList-left">';
                                        a += '<img src="<c:out value='${user.profile_img}'/>" style="height: 40px;" width="40px;">';
                                        a += '</div>';
                                        a += '<div class="commentArea" style="border-bottom:1px solid darkgray; margin-bottom: 10px; width: 80%;">';
                                        a += '<div class="commentInfo'+value.cno+'" style="font-size: 1.3rem; font-weight: 500; color: #030303;">'+value.nickname;
                                        a += '<a onclick="commentUpdate('+value.cno+',\''+value.content+'\');"> 수정 </a>';
                                        a += '<a onclick="commentDelete('+value.cno+');"> 삭제 </a> </div>';
                                        a += '<div class="commentContent'+value.cno+'" style="font-size: 1.4rem; font-weight: 400; color: #030303;"> <p>'+value.content +'</p>';
                                        a += '</div></div>';
                                        a += '</div>';
                                    });

                                    $(".commentList").html(a);
                                }
                            });
                        }

                        //댓글 등록
                        function commentInsert(insertData){
                            $.ajax({
                                url : '/comment/insert',
                                type : 'post',
                                data : insertData,
                                success : function(data){
                                    if(data == 1) {
                                        commentList(); //댓글 작성 후 댓글 목록 reload
                                        $('[name=content]').val('');
                                    }
                                }
                            });
                        }

                        //댓글 수정 - 댓글 내용 출력을 input 폼으로 변경
                        function commentUpdate(cno, content){
                            var a ='';

                            a += '<div class="input-group">';
                            a += '<input type="text" class="form-control" name="content_'+cno+'" value="'+content+'"/>';
                            a += '<span class="input-group-btn"><button class="btn btn-default" type="button" onclick="commentUpdateProc('+cno+');">수정</button> </span>';
                            a += '</div>';

                            $('.commentContent'+cno).html(a);

                        }

                        //댓글 수정
                        function commentUpdateProc(cno){
                            var updateContent = $('[name=content_'+cno+']').val();

                            $.ajax({
                                url : '/comment/update',
                                type : 'post',
                                data : {'content' : updateContent, 'cno' : cno},
                                success : function(data){
                                    if(data == 1) commentList(bno); //댓글 수정후 목록 출력
                                }
                            });
                        }

                        //댓글 삭제
                        function commentDelete(cno){

                            $.ajax({
                                url : '/comment/delete/'+cno,
                                type : 'post',
                                success : function(data){
                                    if(data == 1) commentList(bno); //댓글 삭제후 목록 출력
                                }
                            });
                        }


                        $(document).ready(function(){
                            commentList(); //페이지 로딩시 댓글 목록 출력
                        });



                    </script>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<%@include file="../../include/footer.jsp"%>