<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../include/header.jsp"%>
<link rel="stylesheet" href="../resources/css/myprofile.css">

<style>
    .myprofile-info h3{
        color: black;
        font-size: 22px;
        margin-bottom: 15px;
        padding-left: 5px;
    }

    .myprofile-right {
        display: flex;
        flex-direction: column;
        justify-content: center;
    }

    .myprofile-right button {
        font-size: 1rem;
        width: 100%;
        height: 40px;
        padding: 0px 20px;
        color: white;
        background-color: rgba(216, 33, 45, 0.7);
        border-radius: 25px;
        border: solid 1px #999;
    }

    .myprofile-right button:hover {
        color: white;
        background-color: rgba(216, 33, 45);
    }


    <!--   -->
    .myprofile-box-02{
        display:flex;
    }
    .myprofile-box-02 a{
        position: relative;
        justify-content: center;
        width: 50%;
        height: 50px;
        font-size: 1.3rem;
        display: flex;
        align-items:center;
        text-decoration: none;
        background: #72839a6b;
        margin: 15px 10px;

    }
    .myprofile-box-02 a::before{
        content: '';
        position: absolute;
        top: 0;
        left: 0;
        width: 4px;
        height: 100%;
        background: #e74c3c;
        transition: 0.5s ease-in-out;
    }
    .myprofile-box-02 a::after{
        content: '';
        position: absolute;
        top: 0;
        right: 0;
        width: 4px;
        height: 100%;
        background: #e74c3c;
        transition: 0.5s ease-in-out;
    }
    .myprofile-box-02 a:nth-child(2)::before,
    .myprofile-box-02 a:nth-child(2)::after
    {
        background: #e03b4c;

    }
    .myprofile-box-02 a:hover::before,
    .myprofile-box-02 a:hover::after
    {
        ;
        width: 50%;
    }

    .myprofile-box-02 a span{
        position: absolute;
        z-index: 1;
        color: #fff;
    }
</style>
<section class="myprofile-info common-list" style="margin-top: 105px;">
    <div class="myprofile-info-wrap">
        <div class="myprofile-inner">
            <div class="myprofile-box-01">
                <div class="myprofile-left">
                    <div class="myprofile-left-img">
                        <img src="<c:out value='${info.profile_img}'/>" alt="">
                    </div>
                </div>
                <div class="myprofile-right">
                    <h3><c:out value='${info.name}'/></h3>
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
            <hr>
            <div class="myprofile-info-box-03">
                <ul>
                    <li>
                        <h4>이메일 주소</h4>
                    </li>
                    <li>
                        <p><c:out value="${info.email}"/></p>
                    </li>
                </ul>
            </div>
            <hr>
            <div class="myprofile-info-box-04">
                <ul>
                    <li>
                        <h4>닉네임</h4>
                    </li>
                    <li>
                        <p><c:out value="${info.nickname}"/></p>
                    </li>
                </ul>
            </div>
            <hr>
            <div class="myprofile-info-box-05">
                <ul>
                    <li>
                        <h4>이름</h4>
                    </li>
                    <li>
                        <p><c:out value="${info.name}"/></p>
                    </li>
                </ul>
            </div>
            <hr>
            <div class="myprofile-info-box-06">
                <ul>
                    <li>
                        <h4>프로필사진</h4>
                    </li>
                    <li class="myprofile-info-box-06-li">
                        <img src="<c:out value='${info.profile_img}'/>">
                    </li>
                </ul>
            </div>
            <hr>
            <div class="myprofile-info-box-07">
                <ul>
                    <li>
                        <h4>가입일</h4>
                    </li>
                    <li>
                        <p>
                            <c:out value="${info.sysregdate}"/>
                        </p>
                    </li>
                </ul>
            </div>
            <hr>
        </div>
    </div>
</section>

<%@include file="../../include/footer.jsp"%>