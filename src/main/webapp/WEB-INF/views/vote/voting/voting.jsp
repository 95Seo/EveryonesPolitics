
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="../../include/header.jsp"%>

<link rel="stylesheet" href="../resources/css/votepage.css" />

<script>
    $(document).on("click","input[type=radio]",function(){
        showFill=$('input[name=fill]:checked').val();
        //라디오 버튼 값을 가져온다.
        chang_fill(showFill);
    });

    function vote_btn() {
        if(isNaN(${principal.idx})) {
            alert("죄송합니다. 로그인이 필요한 서비스 입니다.");
            location.href ="/";
        } else if(!isNaN(${principal.vote})) {
            alert("죄송합니다. 이미 투표를 완료 하셨습니다.");
            location.href ="/";
        } else {
            alert("투표에 성공하였습니다 !.");
        }
    }

    function chang_fill(fill) {
        $("input:radio[name='fill']").attr("checked", false);

        if(fill == '이재명') {
            $("input:radio[id='radio1']").attr("checked", true);
        } else if(fill == '윤석열') {
            $("input:radio[id='radio2']").attr("checked", true);
        } else if(fill == '심상정') {
            $("input:radio[id='radio3']").attr("checked", true);
        } else if(fill == '안철수') {
            $("input:radio[id='radio4']").attr("checked", true);
        }
    }
</script>

<style>
    .candidate01 {
        display: inline-block;
        background: url("../resources/images/behind01.jpg");
        background-size: 100% 100%;

    }


    .candidate01 label:hover {
        background: rgba(131, 226, 255, 0.7);
        border-radius: 20px;
        cursor: pointer;
    }

    .candidate01:hover h2 {
        color: black;
    }

    .candidate01:hover p {
        color: black;
    }



    #voting input[checked] + label {
        animation-play-state: paused;
        background: rgb(231 76 60 / 84%);
        border-radius: 20px;
        cursor: pointer;

    }



    @keyframes up {
        from {
            transform: translateY(0px);
        }
        to {
            transform: translateY(-30px);
        }
    }

    .candidate02 {
        display: inline-block;
        background: url("../resources/images/behind02.jpg");
        background-size: 100% 100%;

    }


    .candidate02 label:hover {
        background: rgba(216, 33, 45, 0.7);
        cursor: pointer;
        border-radius: 20px;
    }

    .candidate02:hover h2 {
        color: black;
    }

    .candidate02:hover p {
        color: black;
    }

    .candidate03 {
        display: inline-block;
        background: url("../resources/images/behind03.jpg");
        background-size: 100% 100%;

    }



    .candidate03:hover label {
        background: rgba(255, 202, 44, 0.7);
        border-radius: 20px;
        cursor: pointer;
    }

    .candidate03:hover h2 {
        color: black;
    }

    .candidate03:hover p {
        color: black;
    }

    .candidate04 {
        display: inline-block;
        background: url("../resources/images/behind04.jpg");
        background-size: 100% 100%;

    }



    .candidate04:hover label {
        background: rgba(234, 85, 4, 0.7);
        border-radius: 20px;
        cursor: pointer;
    }

    .candidate04:hover h2 {
        color: black;
    }

    .candidate04:hover p {
        color: black;
    }

</style>
<section id="votepage" class="votepage sections">
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <div class="votepage-title">
                    <div class="votepage-title-icon">
                        <i class="fas fa-circle"></i>
                        <i class="fas fa-circle"></i>
                    </div>
                    <span>당신의&nbsp;<h2>후보</h2>에 투표하세요 !!</span>
                    <div class="subtitle">
                        <p>내가 지지하는 대통령 후보님들을 투표해보세요</p>
                    </div>
                </div>
            </div>
        </div>

        <div class="row vote-Card">
            <form action="/form" method="post">
                <div class="vote-Row" id="voting">
                    <div class="col-sm-3">
                        <li class="candidate01 comon-candidate">
                            <input type="radio" name="fill" id="radio1" value="이재명">
                            <label for="radio1" class="cursor_pointer">
                                <h2>이재명</h2>
                                <p>새로운 대한민국<br />이재명은 합니다!</p>
                            </label>
                        </li>
                    </div>

                    <div class="col-sm-3">
                        <li class="candidate02 comon-candidate">
                            <input type="radio" name="fill" id="radio2" value="윤석열">
                            <label for="radio2" class="cursor_pointer">
                                <h2>윤석열</h2>
                                <p>공정과 상식의 나라<br />되찾겠습니다.</p>
                            </label>
                        </li>
                    </div>
                </div>

                <div class="vote-Row" id="voting">
                    <div class="col-sm-3">
                        <li class="candidate03 comon-candidate">
                            <input type="radio" name="fill" id="radio3" value="심상정">
                            <label for="radio3" class="cursor_pointer">
                                <h2>심상정</h2>
                                <p>정의로운 복지국가,<br />정의당이 시작합니다</p>
                            </label>
                        </li>
                    </div>

                    <div class="col-sm-3">
                        <li class="candidate04 comon-candidate">
                            <input type="radio" name="fill" id="radio4" value="안철수">
                            <label for="radio4" class="cursor_pointer">
                                <h2>안철수</h2>
                                <p>말과 행동이 같은 정당</p>
                            </label>
                        </li>
                    </div>
                </div>
        </div>

        <div class="row">
            <div class="col-sm-12">
                <div class="vote-date-expirebox">
                    <ul class="vote-icon">
                        <li><i class="fas fa-vote-yea"></i></li>
                        <li><input type="submit" onclick="vote_btn()" value="투표하기"></li>
                    </ul>
                    <div class="vote-date-expire">
                        <p>투표 기간</p>
                        <p>12/17~12/24</p>
                        <p>총 투표 수 : <c:out value="${voteCount}" /></p>
                    </div>
                </div>
            </div>
        </div>
        </form>
    </div>
</section>


<%@include file="../../include/footer.jsp"%>