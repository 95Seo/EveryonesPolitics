<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!--                     추가                         -->
<!--  댓글  -->

<label for="comment-content" style="margin-bottom: 10px; color: #030303; font-weight: 400; font-size: 1.6rem;">댓글</label>
<form name="commentInsertForm" style="margin-bottom: 50px;">
    <div class="input-group" style="display: flex">

        <li>
            <sec:authorize access="!isAuthenticated()">
                <div class="imput-group-left" style="margin-right: 15px;" >
                    <img src="https://vote-web-bucket.s3.ap-northeast-2.amazonaws.com/USER/PROFILE/DEFAULT/person.jpg" class="comment-img">
                </div>
            </sec:authorize>
        </li>
        <sec:authorize access="isAuthenticated()">
            <div class="imput-group-left" style="margin-right: 15px;" >
                <img src="<c:out value='${current_img}'/>" class="comment-img">
            </div>
        </sec:authorize>


        <div class="input-group-right" style="width: 80%;">
            <input type="hidden" name="bno" value="${board.idx}"/>
            <input type="hidden" name="boardNm" value="tb_board"/>
            <input type="text" class="form-control" id="comment-content" name="content" placeholder="공개 댓글 추가...">
        </div>
    </div>
    <span class="btn_right" style="width: 85%;">
                                    <button class="btn btn-default" type="button" name="commentInsertBtn" onclick="create_btn()" >댓글</button>
                                    <input type="reset" value="취소" class="btn btn-default">
                                </span>
</form>



<div class="commentList">
</div>

<style>
    .comment-img{
        height: 40px;
        width: 40px;
        border-radius: 50%;
    }


    .btn-default{
        background:rgba(0, 0, 0, 0.05);
        color: rgb(0 0 0 / 45%);
        margin-top: 0px;
        padding: 10px;
        min-width: 100px;
    }
    .toggle {
        display: none;
    }

    .Toggle-box{
        display: block;
        position: absolute;
        border: 1px solid #99999942;
        border-radius: 5px;
        background: #f8efef40;
        padding: 0px 36px 0px 16px;
    }
    .Toggle-box a{
        display: flex;
        align-items: center;
    }

    .Toggle-box i{
        color: black;
        margin-right: 12px;
        font-size: 17px;
    }

    .Toggle-box p {
        font-size: 15px;
        color: black;
        font-weight: 400;

    }

    #commentInfo_button{
        background: none;
        border: none;
    }

    .btn_right{
        display: flex;
        justify-content: flex-end;
    }
    .active {
        color:#e85647;
    }
</style>

<!--                     추가                         -->

<script>

    function create_btn() {
        if(isNaN(${principal.idx})) {
            alert("죄송합니다. 로그인이 필요한 서비스 입니다.");
        } else {

        }
    }

    function timeForToday(value){
        const today = new Date();
        const timeValue = new Date(value);

        const betweenTime = Math.floor((today.getTime() - timeValue.getTime()) / 1000 / 60);

        if ( betweenTime < 1){
            return `방금전`;
        } else if (betweenTime < 60) {
            return betweenTime+`분전`;
        }



        const betweenTimeHour = Math.floor(betweenTime / 60);
        if(betweenTimeHour < 24){
            return betweenTimeHour +`시간전`;
        }

        const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
        if(betweenTimeDay < 365){
            return betweenTimeDay + `일전`;
        }

        return Math.floor(betweenTimeDay / 365) + `년전`;
    }

    $(document).ready(function (){
        ReplyList(); //페이지 로딩시 댓글 목록 출력
    });
    let bno = ${board.idx}; //게시글 번호
    let login_idx = ${current_idx};

    $('[name=commentInsertBtn]').click(function(){ //댓글 등록 버튼 클릭시
        var insertData = $('[name=commentInsertForm]').serialize(); //commentInsertForm의 내용을 가져옴
        commentInsert(insertData); //Insert 함수호출(아래)
    });

    //댓글 목록
    const ReplyList = function (){
        $.ajax({
            url : '/picture_replyList.do',
            type : 'get',
            data : {
                'bno':bno,
                'boardNm': board_nm
            },
            success : function(data){
                var listHtml ='';
                let i = 0;
                $.each(data, function(key, value) {
                    let cno = value.cno;
                    let bno = value.bno;
                    let grp = value.grp;
                    let grps = value.grps;
                    let grpl = value.grpl;
                    let writer = value.writer;
                    let content = value.content;
                    let wdate = value.sysregdate;
                    let wnickname = value.nickname;
                    let wprofile = value.profileImg;
                    let use_yn = value.useYn;
                    let real_delete = value.realDelete;
                    let likeYn = value.likeYn;
                    let likeCnt = value.likeCnt
                    let grpsCnt = value.grpsCnt;

                    if(real_delete == 'N') {
                        // 모댓글 출력
                        listHtml += '       <div class="commentList-wrap" style="display:flex; margin-bottom: 20px ">';
                        listHtml += "       <div class='commentList-left' style='margin: 5px 15px 0px 0px;'>";
                        listHtml += '           <img src="' + wprofile + '" class="comment-img">';
                        listHtml += "       </div>";
                        listHtml += "       <div class='commentArea' style='margin-bottom: 10px; width: 80%;'>";
                        listHtml += "           <div class='commentInfo' style='font-size: 1.3rem; font-weight: 500; color: #030303; display: flex; justify-content: space-between;'><div>"+wnickname+"<span style='margin-left: 10px; color: #797979;'>"+timeForToday(wdate)+"</span></div>";
                        if (login_idx == writer) {
                            listHtml += '           <div>';
                            if(use_yn == 'Y') {
                                listHtml += '<button id="commentInfo_button" class="'+cno+'"><i class="fas fa-ellipsis-v" style="color: black; font-size: 15px;"></i></button>';
                            }
                            listHtml += '           <div id="'+cno+'" class="toggle" name="t-box">';
                            listHtml += '               <div class="Toggle-box" style="display: block;">';
                            listHtml += '               <a onclick="UpdateReply(' + cno + ',\'' + content + '\');">';
                            listHtml += '                   <i class="fas fa-pen"></i><p>수정</p></a>';
                            listHtml += '               <a a href="javascript:" cno="' + cno + '" grpl="' + grpl + '" bno="' + bno + '" grp="' + grp + '" class="reply_delete">';
                            listHtml += '                   <i class="fas fa-trash"></i><p>삭제</p></a></div></div></div>';
                        }
                        listHtml += '</div>';
                        if (use_yn == 'N') {
                            listHtml += '       <div class="commentContent'+cno+'"><p style="font-size: 1.2rem; font-weight: 400; color: #e20000; ">(삭제된 댓글입니다.)</p></div>';
                        } else {
                            listHtml += '       <div class="commentContent'+cno+'"><p style="font-size: 1.0rem; font-weight: 400; color: #030303; ">'+content +'</p></div>';
                        }

                        // 답글 버튼
                        listHtml += '           <div class="commentLike'+cno+'" style="font-size: 1.0rem; font-weight: 400; color: #030303; ">';
                        if(likeYn == 'Y') {
                            listHtml += '               <i id="reply_likeUp" class="far fa-thumbs-up active like_up'+cno+'" likeYn="'+likeYn+'" cno="' + cno + '" style="margin-right: 10px;" >'+likeCnt+'</i>';
                        } else {
                            listHtml += '               <i id="reply_likeUp"  class="far fa-thumbs-up like_up'+cno+'" likeYn="'+likeYn+'" cno="' + cno + '" style="margin-right: 10px;" >'+likeCnt+'</i>';
                        }

                        if(likeYn == 'N') {
                            listHtml += '               <i id="reply_likeDown" class="far fa-thumbs-down active" likeYn="'+likeYn+'" cno="' + cno + '" style="margin-right: 10px; color:#e85647;"></i>';
                        } else {
                            listHtml += '               <i id="reply_likeDown" class="far fa-thumbs-down" likeYn="'+likeYn+'" cno="' + cno + '" style="margin-right: 10px;"></i>';
                        }

                        listHtml += '<a style="cursor: pointer;" class="writer_reply_start" data-toggle="collapse" data-target="#re_replyWrite'+cno+'" aria-expanded="false" aria-controls="collapseExample">답글</a>';
                        listHtml += '           </div>';

                        listHtml += '       <div class="col-3 reply-right">';
                        listHtml += '           <div>';

                        listHtml += '           <a style="cursor: pointer;" class="writer_reply_start_'+cno+'" data-toggle="collapse" data-target="#re_replyList'+cno+'" aria-expanded="false" aria-controls="collapseExample">';
                        if(grpsCnt != 0) {
                            listHtml += '<i class="fas fa-caret-down" style="color: #23527c; margin-right: 10px;"></i>'+grpsCnt+'개의 답글 보기';
                        }
                        listHtml += '</a>';

                        listHtml += '           </div>';
                        listHtml += '       </div>';
                        listHtml += '        </div></div>';

                        // 댓글에 답글 달기를 누르면 답글입력란이 나온다.
                        // ----답글입력란
                        listHtml += '<div class="collapse row rereply_write" id="re_replyWrite' + cno + '">';
                        listHtml += '       <div class="commentWrite-wrap" style="margin-left: 30px; width: 80%;">';
                        if(login_idx != 0) {
                            listHtml += '<div style="display: flex;">';
                            listHtml += '<div class="imput-group-left" style="margin-right: 15px;" >';
                            listHtml += '<img src="' + wprofile + '" class="comment-img"></div>';
                            listHtml += '<div class="input-group-right" style="width: 100%;">';
                            listHtml += '<input type="text" class="form-control" id="input_rereply' + cno + '" name="content" placeholder="답글입력..."></div>';
                            listHtml += '</div>';
                            // 답글달기 버튼이 눌리면 모댓글 번호(cno)와 게시물번호 (bno)를 함수에 전달한다.

                            // 동적으로 넣은 html태그에서 발생하는 이벤트는 동적으로 처리해줘야한다 !!!!
                            // 예를들어, 동적으로 넣은 html태그에서 발생하는 click 이벤트는 html태그 안에서 onclick으로 처리하면 안되고, jquery에서 클래스명이나 id값으로 받아서 처리하도록 해야한다.
                            // list += '            <button onclick="javascript:WriteReReply('+no+','+bno+')" type="button" class="btn btn-success mb-1 write_rereply" >답글&nbsp;달기</button>';
                            // 위 코드는 클릭되어도 값이 넘겨지지 않는다. 값이 undefined가 된다.
                            // 아래코드처럼 짜야한다. click이벤트를 처리하지 않고 데이터(no, bno)만 속성으로 넘겨주도록 작성한다.

                            listHtml += '<span class="btn_right">';
                            listHtml += '  <button type="button" class="btn btn-default write_rereply" cno="' + cno + '" bno="' + bno + '">답글</button>';
                            listHtml += '  <button type="button" class="btn btn-default"  onclick="ReplyList();">취소</span>';

                            // ------ 답글입력란 끝
                        }

                        listHtml += '</div></div>';

                        // 댓글에 답글 보기를 누르면 답글리스트가 나온다.
                        // ----답글리스트
                        listHtml += '<div class="collapse row rereply_List" id="re_replyList' + cno + '" style="margin-left: 30px;">';
                        listHtml += '       <div class="commentList-wrap" id="rereplyList' + cno + '" style="margin-bottom: 20px; margin-left: 30px;">';

                        listHtml += '</div></div>';
                    }
                });

                listHtml += '</div></div>';

                $(".commentList").empty();
                $(".commentList").html(listHtml);

                // 답글에서 답글달기를 누르면 input란에 "@답글작성자"가 들어간다.
                // 답글을 작성한 후 답글달기 버튼을 눌렀을 때 그 click event를 아래처럼 jquery로 처리한다.
                $('button.btn.btn-default.write_rereply').on('click', function() {

                    // 답글을 DB에 저장하는 함수를 호출한다. bno와 no를 같이 넘겨주어야 한다.
                    WriteReReply($(this).attr('bno'), $(this).attr('cno'));
                });

                $('i#reply_likeUp').on('click', function() {
                    let cno = $(this).attr('cno');
                    let likeYn = $(this).attr('likeYn');

                    // O로 만들기
                    if(likeYn == 'Y') {
                        changReplyLike('O', cno);
                        // 좋아요 올리기
                    } else {
                        changReplyLike('Y', cno);
                    }
                });

                $('i#reply_likeDown').on('click', function() {
                    let cno = $(this).attr('cno');
                    let likeYn = $(this).attr('likeYn');

                    // O로 만들기
                    if(likeYn == 'N') {
                        changReplyLike('O', cno);
                        // 좋아요 내리기
                    } else {
                        changReplyLike('N', cno);
                    }
                });

                // 삭제버튼을 클릭했을 때
                $('.reply_delete').on('click', function (){
                    // 모댓글 삭제일때
                    DeleteReply($(this).attr('cno'), $(this).attr('bno'), $(this).attr('grp'));

                });

                ReReplyList(); //페이지 로딩시 대댓글 목록 출력

            }
        });
    }

    //대댓글 목록
    const ReReplyList = function (){
        $.ajax({
            url : '/picture_rereplyList.do',
            type : 'get',
            data : {
                'bno':bno,
                'boardNm': board_nm
            },
            success : function(data){
                var temp = 0;
                var listHtml = "";
                $.each(data, function(key, value) {
                    let cno = value.cno;
                    let bno = value.bno;
                    let grp = value.grp;
                    let grps = value.grps;
                    let grpl = value.grpl;
                    let writer = value.writer;
                    let content = value.content;
                    let wdate = value.sysregdate;
                    let wnickname = value.nickname;
                    let wprofile = value.profileImg;
                    let use_yn = value.useYn;
                    let real_delete = value.realDelete;
                    let likeYn = value.likeYn;
                    let likeCnt = value.likeCnt
                    let grpsCnt = value.grps.Cnt;

                    if(temp != grp) {
                        temp = grp;
                        listHtml ='';
                    }

                    // 답글일때
                    listHtml += " <div class='commentRelist' style='display: flex;'>";
                    listHtml += "       <div class='commentList-left' style='margin: 5px 15px 0px 0px;'>";
                    listHtml += '           <img src="' + wprofile + '" class="comment-img" style="height: 24px; width: 24px;">';
                    listHtml += "       </div>";
                    listHtml += "       <div class='commentArea' style='margin-bottom: 10px; width: 80%;'>";
                    listHtml += "           <div class='commentInfo' style='font-size: 1.2rem; font-weight: 400; color: #030303; display: flex; justify-content: space-between;'><div>"+wnickname + "<span style='margin-left: 10px;color: #797979;'>"+timeForToday(wdate)+"</span></div>";
                    if (login_idx == writer) {
                        listHtml += '           <div>';
                        if(use_yn == 'Y') {
                            listHtml += '<button id="commentInfo_button" class="'+cno+'"><i class="fas fa-ellipsis-v" style="color: black; font-size: 15px;"></i></button>';
                        }
                        listHtml += '           <div id="'+cno+'" class="toggle" name="t-box">';
                        listHtml += '               <div class="Toggle-box" style="display: block;">';
                        listHtml += '               <a onclick="UpdateReReply(' + cno + ',\'' + content + '\');">';
                        listHtml += '                   <i class="fas fa-pen"></i><p>수정</p></a>';
                        listHtml += '               <a a href="javascript:" cno="' + cno + '" grpl="' + grpl + '" bno="' + bno + '" grp="' + grp + '" class="reply_delete">';
                        listHtml += '                   <i class="fas fa-trash"></i><p>삭제</p></a></div></div></div>';
                    }
                    listHtml += '</div>';
                    if (use_yn == 'N') {
                        listHtml += '       <div class="commentContent'+cno+'"><p style="font-size: 1.0rem; font-weight: 400; color: #e20000; ">(삭제된 댓글입니다.)</p></div>';
                    } else {
                        listHtml += '       <div class="commentContent'+cno+'"><p style="font-size: 1.0rem; font-weight: 400; color: #030303; ">'+content +'</p></div>';
                    }
                    // 좋아요 싫어요 버튼
                    listHtml += '           <div class="commentLike'+cno+'" style="font-size: 1.0rem; font-weight: 400; color: #030303; ">';
                    if(likeYn == 'Y') {
                        listHtml += '               <i id="rereply_likeUp" class="far fa-thumbs-up active like_up'+cno+'" likeYn="'+likeYn+'" cno="' + cno + '" style="margin-right: 10px;" >'+likeCnt+'</i>';
                    } else {
                        listHtml += '               <i id="rereply_likeUp" class="far fa-thumbs-up like_up'+cno+'" likeYn="'+likeYn+'" cno="' + cno + '" style="margin-right: 10px;" >'+likeCnt+'</i>';
                    }

                    if(likeYn == 'N') {
                        listHtml += '               <i id="rereply_likeDown" class="far fa-thumbs-down active" likeYn="'+likeYn+'" cno="' + cno + '" style="margin-right: 10px; color:#e85647;"></i>';
                    } else {
                        listHtml += '               <i id="rereply_likeDown" class="far fa-thumbs-down" likeYn="'+likeYn+'" cno="' + cno + '" style="margin-right: 10px;"></i>';
                    }
                    listHtml += '           </div>';
                    listHtml += '       </div>';
                    listHtml += '     </div>';
                    listHtml += '</div>';

                    $("#rereplyList"+grp).html(listHtml);
                });

                // 삭제버튼을 클릭했을 때
                $('.reply_delete').on('click', function (){
                    //답글 삭제일때
                    DeleteReReply($(this).attr('cno'), $(this).attr('bno'), $(this).attr('grp'));
                });

                $('i#rereply_likeUp').on('click', function() {
                    let cno = $(this).attr('cno');
                    let likeYn = $(this).attr('likeYn');

                    // O로 만들기
                    if(likeYn == 'Y') {
                        changReReplyLike('O', cno);
                        // 좋아요 올리기
                    } else {
                        changReReplyLike('Y', cno);
                    }
                });

                $('i#rereply_likeDown').on('click', function() {
                    let cno = $(this).attr('cno');
                    let likeYn = $(this).attr('likeYn');

                    // O로 만들기
                    if(likeYn == 'N') {
                        changReReplyLike('O', cno);
                        // 좋아요 내리기
                    } else {
                        changReReplyLike('N', cno);
                    }
                });

            }
        });
    }

    const changReplyLike = function ( likeYn, cno) {
        $.ajax({
            url : '/reply_like_change.do',
            type : 'get',
            data : {
                boardNm : board_nm,
                bno : bno,
                cno : cno,
                likeYn : likeYn
            },
            // 좋아요 갯수 리턴받아서 뿌려주자..!
            success : function (pto) {
                let reply = pto.likeCnt;
                // $('.like_up'+cno).html(reply);

                // 게시물 번호(bno)에 해당하는 댓글 리스트를 새로 받아오기
                ReplyList();
            },
            error : function () {
                console.log('서버 에러');
            }
        });
    }

    const changReReplyLike = function ( likeYn, cno) {
        $.ajax({
            url : '/reply_like_change.do',
            type : 'get',
            data : {
                boardNm : board_nm,
                bno : bno,
                cno : cno,
                likeYn : likeYn
            },
            // 좋아요 갯수 리턴받아서 뿌려주자..!
            success : function (pto) {
                let reply = pto.likeCnt;
                // $('.like_up'+cno).text(reply);

                // 게시물 번호(bno)에 해당하는 댓글 리스트를 새로 받아오기
                ReReplyList();
            },
            error : function () {
                console.log('서버 에러');
            }
        });
    }

    const WriteReReply = function (bno, cno) {

        // 댓글 입력란의 내용을 가져온다.
        // ||""를 붙인 이유 => 앞뒤 공백을 제거한다. (띄어쓰기만 입력했을때 댓글 작성안되게 처리하기 위함)
        let content = $("#input_rereply"+cno).val();
        content = content.trim();

        if(content == "") {
            alert("글을 입력하세요!");
        } else {
            // 입력란 비우기
            $("#input_rereply"+cno).val("");

            // reply+1 하고 그 값을 가져옴
            $.ajax({
                url : '/picture_write_rereply.do',
                type : 'get',
                data : {
                    boardNm : board_nm,
                    cno : cno,
                    bno : bno,
                    content : content
                },
                success : function (pto) {
                    let reply = pto.grpsCnt;
                    let grp = pto.grp;
                    $('.writer_reply_start_'+grp).html(reply+"개의 답글 보기");

                    // 게시물 번호(bno)에 해당하는 댓글 리스트를 새로 받아오기
                    ReReplyList();
                },
                error : function () {
                    console.log('서버 에러');
                }
            });
        }
    };

    // 모댓글 삭제일때
    const DeleteReply = function (cno, bno, grp) {
        // grp이 no인 댓글이 있는 경우 content에 null을 넣고 없으면 삭제한다.
        $.ajax({
            url : '/picture_delete_reply.do',
            type : 'get',
            data : {
                boardNm : board_nm,
                cno : cno,
                bno : bno,
                grp : grp
            },
            success : function (pto) {
                let reply = pto.reply;

                // 페이지, 모달창에 댓글수 갱신
                $('#m_reply'+bno).text(reply);
                $('#reply'+bno).text(reply);

                // 게시물 번호(bno)에 해당하는 댓글 리스트를 새로 받아오기
                ReplyList();
            },
            error : function () {
                alert("서버 에러");
            }
        });
    };

    // 답글 삭제일때
    const DeleteReReply = function (cno, bno, grp) {
        // 답글을 삭제한다.
        $.ajax({
            url : '/picture_delete_rereply.do',
            type : 'get',
            data : {
                boardNm : board_nm,
                cno : cno,
                bno : bno,
                grp : grp
            },
            success : function (pto) {
                let reply = pto.grpsCnt;
                $('.writer_reply_start_'+cno).text(reply+"개의 답글 보기");

                // 게시물 번호(bno)에 해당하는 댓글리스트를 새로 받아오기
                ReReplyList();
            },
            error : function () {
                console.log('서버 에러');
            }
        });
    };

    $(document).on("click","button[id=commentInfo_button]",function(){
        let cno = $(this).attr('class');
        if($("#"+cno).attr('class') != 'toggle') {
            $("div[name=t-box]").addClass('toggle');
        } else {
            $("div[name=t-box]").addClass('toggle');
            $("#"+cno).removeClass('toggle');
        }
    });

    //댓글 등록
    function commentInsert(insertData){
        $.ajax({
            url : '/picture_write_reply.do',
            type : 'get',
            data : insertData,
            success : function(data){
                $('[name=content]').val('');
                ReplyList(); //댓글 작성 후 댓글 목록 reload=
            }
        });
    }

    //댓글 수정 - 댓글 내용 출력을 input 폼으로 변경
    const UpdateReply = function(cno, content){
        $("div[name=t-box]").addClass('toggle');
        var a ='';

        a += '<div class="input-group" style="display:flex;  flex-direction: column;">';
        a += '<input type="text" class="form-control" name="content_'+cno+'" value="'+content+'"/>';
        a += '<span class="btn_right">';
        a += '<button class="btn btn-default" type="button" onclick="UpdateReplyProc('+cno+');">저장</button>'
        a += '<button class="btn btn-default" type="button" onclick="ReplyList();">취소</span>';
        a += '</div>';

        $('.commentContent'+cno).html(a);

    }

    //댓글 수정 - 댓글 내용 출력을 input 폼으로 변경
    const UpdateReReply = function(cno, content){
        $("div[name=t-box]").addClass('toggle');
        var a ='';

        a += '<div class="input-group" style="display:flex;  flex-direction: column;">';
        a += '<input type="text" class="form-control" name="content_'+cno+'" value="'+content+'"/>';
        a += '<span class="btn_right">';
        a += '<button class="btn btn-default" type="button" onclick="UpdateReReplyProc('+cno+');">저장</button>'
        a += '<button class="btn btn-default" type="button" onclick="ReReplyList();">취소</span>';
        a += '</div>';

        $('.commentContent'+cno).html(a);

    }

    //댓글 수정
    const UpdateReplyProc = function(cno){
        var updateContent = $('[name=content_'+cno+']').val();

        $.ajax({
            url : '/picture_replyUpdate.do',
            type : 'get',
            data : {'content' : updateContent, 'cno' : cno},
            success : function(data){
                ReplyList(); //댓글 수정후 목록 출력
            }
        });
    }

    //댓글 수정
    const UpdateReReplyProc = function (cno){
        var updateContent = $('[name=content_'+cno+']').val();

        $.ajax({
            url : '/picture_replyUpdate.do',
            type : 'get',
            data : {'content' : updateContent, 'cno' : cno},
            success : function(data){
                ReReplyList(); //댓글 수정후 목록 출력
            }
        });
    }

    // //댓글 삭제
    // function commentDelete(cno){
    //
    //     $.ajax({
    //         url : '/comment/delete/'+cno,
    //         type : 'post',
    //         success : function(data){
    //             if(data == 1) commentList(bno); //댓글 삭제후 목록 출력
    //         }
    //     });
    // }
    //
</script>
