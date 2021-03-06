<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="../../include/header.jsp"%>
<link rel="stylesheet" href="../resources/css/myprofile.css">

<script>
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e) {
                document.getElementById('preview').src = e.target.result;
            };
            reader.readAsDataURL(input.files[0]);
        } else {
            document.getElementById('preview').src = "";
        }
    }

    function history_back() {
        window.history.back(this);
    }


</script>

<section class="myprofile-info common-list" style="margin-top: 80px;">
    <div class="myprofile-info-wrap">
        <div class="myprofile-inner">
            <div class="myprofile-edit-title">
                <h3>회원정보 조회/수정</h3>
            </div>
            <div class="myprofile-edit-box">
                <form action="/vote/myProfileUpdate" method="post" enctype="multipart/form-data" id="prm">
                    <input type="hidden" name="idx" value="${info.idx}"/>
                    <input type="hidden" name="username" value="${info.username}"/>
                    <ul>
                        <li><p>이메일주소</p></li>
                        <li class="myprofile-edit-email"><input type="text" name="email" placeholder="이메일 입력" value="<c:out value='${info.email}'/>"></li>
                        <li><p>이름</p></li>
                        <li><input type="text" placeholder="이름 입력" name="name" value="<c:out value='${info.name}'/>"></li>
                        <li><p>닉네임</p></li>
                        <li><input type="text" placeholder="닉네임 입력" name="nickname" value="<c:out value='${info.nickname}'/>"></li>
                        <li><p>프로필 사진</p></li>
                        <li class="myprofile-edit-img">
                            <div class="myprofile-left-img">
                                <input type="hidden" name="profile_img" value="${info.profile_img}"/>
                                <img id="preview" src="<c:out value='${info.profile_img}'/>">
                            </div>
                        </li>
                        <li><input id="input-image" type="file" onchange="readURL(this)"; placeholder="가로 세로 제한 길이: 90px" name="profileImg"></li>
                        <li class="myprofile-edit-button">
                            <button type="button" onclick="history_back(this)">취소</button>
                            <button type="submit">등록</button>
                        </li>
                    </ul>
                </form>
            </div>
        </div>
    </div>
</section>

<%@include file="../../include/footer.jsp"%>