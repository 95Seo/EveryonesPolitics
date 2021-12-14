
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="../../include/header.jsp"%>

<link rel="stylesheet" href="../resources/css/votepage.css" />

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

            <div class="vote-Row">
                <div class="col-sm-3">
                    <li class="candidate01 comon-candidate">
                        <a href="#none">
                            <h2>이재명</h2>
                            <p>새로운 대한민국<br />이재명은 합니다!</p>
                        </a>
                    </li>
                </div>

                <div class="col-sm-3">
                    <li class="candidate02 comon-candidate">
                        <a href="#none">
                            <h2>윤석열</h2>
                            <p>공정과 상식의 나라<br />되찾겠습니다.</p>
                        </a>
                    </li>
                </div>
            </div>

            <div class="vote-Row">
                <div class="col-sm-3">
                    <li class="candidate03 comon-candidate">
                        <a href="#none">
                            <h2>심상정</h2>
                            <p>정의로운 복지국가,<br />정의당이 시작합니다</p>
                        </a>
                    </li>
                </div>

                <div class="col-sm-3">
                    <li class="candidate04 comon-candidate">
                        <a href="#none">
                            <h2>안철수</h2>
                            <p>말과 행동이 같은 정당</p>
                        </a>
                    </li>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-12">
                <div class="vote-date-expirebox">
                    <div class="vote-icon">
                        <i class="fas fa-vote-yea"></i>
                    </div>
                    <div class="vote-date-expire">
                        <p>투표 기간</p>
                        <p>12/17~12/24</p>
                        <p>총 투표 수 : <c:out value="${voteCount}" /></p>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <!-- End off container -->
</section>


<%@include file="../../include/footer.jsp"%>