<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="../../include/header.jsp"%>
<link rel="stylesheet" href="../resources/css/myprofile.css">

<section class="myprofile-info">
    <div class="myprofile-info-wrap">
        <div class="myprofile-inner">
            <div class="myprofile-edit-title">
                <h3>회원정보 조회/수정</h3>
            </div>
            <div class="myprofile-edit-box">
                <form action="">
                    <ul>
                        <li><p>이메일주소</p></li>
                        <li class="myprofile-edit-email"><input type="text" placeholder="이메일 입력"></li>
                        <li><p>이름</p></li>
                        <li><input type="text" placeholder="이름 입력"></li>
                        <li><p>닉네임</p></li>
                        <li><input type="text" placeholder="닉네임 입력"></li>
                        <li><p>프로필 사진</p></li>
                        <li class="myprofile-edit-img">
                            <div class="myprofile-left-img">
                                <img src="../resources/images/behind01.jpg" alt="">
                            </div>
                            <div><button>삭제</button></div>
                        </li>
                        <li><input type="file"></li>
                        <li><p>가로 제한 길이: 90px, 세로 제한 길이 90px</p></li>
                        <li class="myprofile-edit-button">
                            <button>취소</button>
                            <button>등록</button>
                        </li>
                    </ul>
                </form>
            </div>


        </div>
    </div>
</section>

<%@include file="../../include/footer.jsp"%>