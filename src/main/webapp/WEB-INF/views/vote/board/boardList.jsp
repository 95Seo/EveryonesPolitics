<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../../include/header.jsp"%>

<script>
    var obj={};
    obj.dataType = "JSON";
    obj.error=function(e){console.log(e)};

    showPage = JSON.parse ( ${json} ) ;  //보여줄 페이지
    showFill = 5;

    listCall(showPage, showFill); //리스트 호출 함수

    $(document).on("change","input[type=radio]",function(){
        fill=$('input[name=fill]:checked').val();
        //라디오 버튼 값을 가져온다.
        listCall(showPage, fill);
    });

    // /listSub/{pagePerCnt}/{page}
    function listCall(page, fill){
        obj.url="/vote/board/boardList/";
        obj.type="get";
        obj.data ={};
        obj.url +=10+"/";
        obj.url += page+"/"
        obj.url += fill;
        console.log(obj.url);

        obj.success= function(d){
            console.log(d);
            listPrint(d.list); //리스트 그리기
            showPage = d.currPage;

            pagePrint(d.currPage,d.range, d.fill) //페이징 처리
        };
        ajaxCall(obj);
    }

    function ajaxCall(obj){
        $.ajax(obj);
    }

    //받아온 리스트 그리기
    function listPrint(list){
        var content="";
        list.forEach(function(item, idx){
            content += "<li>"
            content +="<div class='no'>"+item.rownum+"</div>"
            content +="<div class='candidate'> 윤석열 </div>"  // 여기다가 item 값 넣어주세용
            content +="<div class='subject'><a href=/vote/boardDetail?idx="+item.idx+" style='color: #797979'>"+item.title+"</a></div>"
            content +="<div class='date'>"+item.sysregdate+"</div>"
            content +="<div class='writer'>"+item.nickName+"</div>"
            content +="<div class='view-num'>"+item.viewCnt+"</div>"
            content +="<div class='recommend-num'>"+item.likeCnt+"</div>"
            content += "</li>"
        });
        //내용 붙이기
        $("#list").empty();
        $("#list").append(content);
    }


    //페이징 그리기 => 플러그인을 사용하면 쓸 필요가 없음.
    function pagePrint(currPage,range,fill){
        //이전
        var start =1;
        var end = 5;
        var content="";

        if(currPage>5){
            //end = currPage + 4; 일 경우 페이지 변화 시 마다 페이징이 이동된다.
            //우리가 원하는 것은 5 단위로 움직일때 새로운 페이징 생성
            end = Math.ceil(currPage/5)*5; //생성가능 페이지수? 6,11,16 씩 대입해 보자
            start= end - 4;
            content +="<li><a href='#' onclick='listCall("+(start-1)+','+fill+")' class='fa fa-angle-left'>이전</a> |</li> ";
        }

        $("input:radio[name='fill']").attr("checked", false);

        if(fill == 1) {
            $("input:radio[id='radio1']").attr("checked", true);
        } else if(fill == 2) {
            $("input:radio[id='radio2']").attr("checked", true);
        } else if(fill == 3) {
            $("input:radio[id='radio3']").attr("checked", true);
        } else if(fill == 4) {
            $("input:radio[id='radio4']").attr("checked", true);
        } else {
            $("input:radio[id='radio5']").attr("checked", true);
        }

        if(fill == 5)

        for(var i=start; i<=end;i++){
            //i는 절대 생성 가능 페이지보다 크면 안된다.
            if(i <= range){
                if(currPage == i){
                    content += "<li  class='active'><a style='color: white'>"+i+"</a></li>"
                }else{
                    content += "<li><a href='#' onclick='listCall("+i+','+fill+")' style='color: black'>"+i+"</a></li>"
                }
            }
        }

        //다음 (range가 5보다 클 경우)
        if(end < range){
            content += " <li><a href='#' onclick='listCall("+(end+1)+','+fill+")' class='fa fa-angle-right'>다음</a></li>"
        }
        $("#paging").empty();
        $("#paging").append(content);
    }


    $("#radio2").prop("checked",true);




</script>


<style>
    .search-box{
        display: flex;
        justify-content: flex-end;
        margin-bottom: 15px;

    }

    .search-box input{
        position: relative;
        display: inline-block;
        font-size: 20px;
        box-sizing: border-box;
        transition: .5s;
    }

    .search-box input[type="text"]{
        background:#fff;
        outline: none;
        padding: 0 25px;
        border: 1px solid #999999;
        border-radius: 5px 0 0 5px;
        width:200px;
        height: 33px;

    }

    .search-box input[type="submit"]{
        position: relative;
        width:100px;
        height: 33px;
        border: 1px solid #999999;
        border-radius: 0 5px 5px 0 ;
        cursor: pointer;
        background: #a12c2f;
        color: #fff;
        left: -5px;
    }
    .search-box input[type="submit"]:hover{
        background: #ff4722;
    }

</style>

<!-- board Section(게시판 리스트)-->
<section id="board-list" class="board-list common-list">

    <div class="container">
        <div class="row">
            <div id="fillter">
                <ul>
                    <li><input type="radio" name="fill" id="radio5" value="5"><label for="radio5" >전체</label></li>
                    <li><input type="radio" name="fill" id="radio1" value="1"><label for="radio1">이재명</label></li>
                    <li><input type="radio" name="fill" id="radio2" value="2"><label for="radio2">윤석열</label></li>
                    <li><input type="radio" name="fill" id="radio3" value="3"><label for="radio3">심상정</label></li>
                    <li><input type="radio" name="fill" id="radio4" value="4"><label for="radio4">안철수</label></li>
                </ul>
            </div>

            <div class="search-box">
                <form>
                    <input type="text" name="" placeholder="입력하세요">
                    <input type="submit" name="" value="검색">
                </form>
            </div>


            <div class="promise-list-wrap">
                <div class="promise-wrap">
                    <div class="list-box">
                        <ul class="list-hd">
                            <li>
                                <div class="no">번호</div>
                                <div class="candidate">후보</div>
                                <div class="subject">제목</div>
                                <div class="date">등록일</div>
                                <div class="writer">글쓴이</div>
                                <div class="view-num">조회 수</div>
                                <div class="recommend-num">추천 수</div>
                            </li>
                        </ul>
                        <ul id="list" class="list-bd">
                        </ul>
                    </div>
                    <div class="list-btn">
                        <a href="/vote/boardCreate">글쓰기</a>
                    </div>

                    <div class="paging">
                        <!--  Pagination 시작 -->
                        <ul class="pagination" id="paging">
                        </ul>
                        <!--  Pagination 끝 -->
                    </div>
                </div>
            </div>
        </div>
    </div>

</section>

<%@include file="../../include/footer.jsp"%>