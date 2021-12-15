<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="../../include/header.jsp"%>
<link rel="stylesheet" href="../resources/css/myprofile.css">

<script>
    var obj={};
    obj.dataType = "JSON";
    obj.error=function(e){console.log(e)};

    map = JSON.stringify(${json});

    cri = JSON.parse(map);

    showPage = cri.page;  //보여줄 페이지

    showAmount = cri.amount;

    var showFill = cri.filter;

    var keyWord = cri.keyword;

    listCall(showPage); //리스트 호출 함수

    // /listSub/{pagePerCnt}/{page}
    function listCall(page){
        var param = {};
        param = {"amount":showAmount, "page":page, "filter":showFill, "keyword":keyWord};

        $.ajax({
            anyne:true,
            type:'POST',
            data: JSON.stringify(param),
            contentType: 'application/json',
            // contentType: 'application/x-www-form-urlencoded; charset=euc-kr',
            url:"/vote/selectMyBoardList/",
            dataType: "text",
            success : function(result) {
                console.log(d);
                var d = JSON.parse(result);
                message = d.message;
                if (message == "fail") {
                    var content = "<div class='no_object'>게시물이 없습니다.</div>";
                    //내용 붙이기
                    $("#list").empty();
                    $("#list").append(content);
                } else {
                    listPrint(d.list, d.currPage); //리스트 그리기
                    pagePrint(d.currPage,d.range); //페이징 처리
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("ERROR : " + textStatus + " : " + errorThrown);
            }
        });
    }

    //받아온 리스트 그리기
    function listPrint(list, currPage){
        if(keyWord == null || keyWord == "") {
            keyWord = "";
            $('#keyword').val('');
        } else {
            $('#keyword').val(keyWord);
        }

        var content="";
        list.forEach(function(item, idx){
            content += "<li>"
            content +="<div class='no'>"+item.rownum+"</div>"
            content +="<div class='candidate'>"+ item.filter +"</div>"  // 여기다가 item 값 넣어주세용
            content +="<div class='subject'><a href=/vote/boardDetail?idx="+item.idx+"&page="+currPage+"&filter="+showFill+"&keyword="+keyWord+" style='color: #797979'>"+item.title+"</a></div>"
            content +="<div class='date'>"+item.sysregdate+"</div>"
            content +="<div class='writer'>"+item.nickName+"</div>"
            content +="<div class='view-num'>"+item.viewCnt+"</div>"
            content +="<div class='recommend-num'>"+item.likeCnt+"</div>"
            content += "</li>"
        });
        //내용 붙이기
        $("#list-bd").empty();
        $("#list-bd").append(content);
    }


    //페이징 그리기 => 플러그인을 사용하면 쓸 필요가 없음.
    function pagePrint(currPage,range){
        //이전
        var start =1;
        var end = 5;
        var content="";

        if(currPage>5){
            //end = currPage + 4; 일 경우 페이지 변화 시 마다 페이징이 이동된다.
            //우리가 원하는 것은 5 단위로 움직일때 새로운 페이징 생성
            end = Math.ceil(currPage/5)*5; //생성가능 페이지수? 6,11,16 씩 대입해 보자
            start= end - 4;
            content +="<li><a onclick='listCall("+(start-1)+")' class='fa fa-angle-left cursor_pointer'>이전</a> |</li> ";
        }

        for(var i=start; i<=end;i++){
            //i는 절대 생성 가능 페이지보다 크면 안된다.
            if(i <= range){
                if(currPage == i){
                    content += "<li  class='active'><a style='color: white'>"+i+"</a></li>"
                }else{
                    content += "<li><a class='cursor_pointer' onclick='listCall("+i+")' style='color: black'>"+i+"</a></li>"
                }
            }
        }

        //다음 (range가 5보다 클 경우)
        if(end < range){
            content += " <li><a onclick='listCall("+(end+1)+")' class='fa fa-angle-right cursor_pointer'>다음</a></li>"
        }
        $("#paging").empty();
        $("#paging").append(content);
    }
</script>

<section class="myprofile-info common-list" style="margin-top: 105px;">
    <div class="myprofile-info-wrap">
        <div class="myprofile-inner">
            <div class="myprofile-box-01">
                <div class="myprofile-left">
                    <div class="myprofile-left-img">
                        <img src=<c:out value="${info.profileImg}"/> />
                    </div>
                </div>
                <div class="myprofile-right">
                    <h3>${info.name}</h3>
                    <a href="/vote/myProfileUpdate"><button>회원정보변경</button></a>
                </div>
            </div>
            <div class="myprofile-box-02">
                <a href="/vote/myProfileInfo">
                    <span>나의정보 보기</span>
                    <span>나의정보 보기</span>
                </a>
                <a href="/vote/myProfileWrite">
                    <span>작성 글 보기</span>
                    <span>작성 글 보기</span>
                </a>
            </div>
            <!-- board Section(게시판 리스트)-->
            <div class="myprofile-list">
                <div class="myprofile-list-wrap">
                    <div class="promise-wrap">
                        <div class="list-box" style="max-width: 100%;">
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
                            <ul id="list-bd" class="list-bd">
                                <li>
                                    <div class="no">1</div>
                                    <div class="candidate">신주영</div>
                                    <div class="subject"><a href="/vote/boardDetail">게시물 제목이 출력되는곳</a></div>
                                    <div class="date">2021-12-31</div>
                                    <div class="writer">백동채</div>
                                    <div class="view-num">4</div>
                                    <div class="recommend-num">65</div>
                                </li>
                                <li>
                                    <div class="no">2</div>
                                    <div class="candidate">신주영</div>
                                    <div class="subject"><a href="/vote/boardDetail">게시물 제목이 출력되는곳</a></div>
                                    <div class="date">2021-12-23</div>
                                    <div class="writer">백동채</div>
                                    <div class="view-num">4</div>
                                    <div class="recommend-num">65</div>

                                </li>
                                <li>
                                    <div class="no">3</div>
                                    <div class="candidate">신주영</div>
                                    <div class="subject"><a href="/vote/boardDetail">게시물 제목이 출력되는곳</a></div>
                                    <div class="date">2021-12-23</div>
                                    <div class="writer">백동채</div>
                                    <div class="view-num">4</div>
                                    <div class="recommend-num">65</div>
                                </li>
                                <li>
                                    <div class="no">4</div>
                                    <div class="candidate">신주영</div>
                                    <div class="subject"><a href="/vote/boardDetail">게시물 제목이 출력되는곳</a></div>
                                    <div class="date">2021-12-23</div>
                                    <div class="writer">백동채</div>
                                    <div class="view-num">4</div>
                                    <div class="recommend-num">65</div>
                                </li>
                                <li>
                                    <div class="no">5</div>
                                    <div class="candidate">신주영</div>
                                    <div class="subject"><a href="/vote/boardDetail">게시물 제목이 출력되는곳</a></div>
                                    <div class="date">2021-12-23</div>
                                    <div class="writer">백동채</div>
                                    <div class="view-num">4</div>
                                    <div class="recommend-num">65</div>
                                </li>
                                <li>
                                    <div class="no">6</div>
                                    <div class="candidate">신주영</div>
                                    <div class="subject"><a href="/vote/boardDetail">게시물 제목이 출력되는곳</a></div>
                                    <div class="date">2021-12-23</div><div class="writer">백동채</div>
                                    <div class="view-num">4</div>
                                    <div class="recommend-num">65</div>
                                </li>

                            </ul>
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
    </div>
</section>

<%@include file="../../include/footer.jsp"%>