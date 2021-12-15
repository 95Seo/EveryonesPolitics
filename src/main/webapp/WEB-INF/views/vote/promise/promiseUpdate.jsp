<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="../../include/header.jsp"%>
<script
      type="text/javascript"
      src="../resources/css/ckeditor/ckeditor.js"
    ></script>

<section class="board">
  <form action="/vote/promiseUpdate" method="post" enctype="multipart/form-data">
    <input type="hidden" name="page" value="${page}"/>
    <input type="hidden" name="filter" value="${filter}"/>
    <input type="hidden" name="keyword" value="${keyword}"/>
    <input type="hidden" name="idx" value="${promise.idx}" />
  <div class="board-wrap">
    <div class="board-title">
      <p>제목</p>
      <span>
        <input type="text" name="title" value="${promise.title}" />
      </span>
    </div>
    <div class="row justify-content-md-center">
      <div class="col_c" >
        <div class="input-group">
          <textarea class="form-control" id="p_content" name="content"><c:out value="${promise.content}" escapeXml="false" /></textarea>
          <script type="text/javascript">
            CKEDITOR.replace("p_content", {
                height: 500,
                width: 1200,
                uploadUrl: "/image/drag",  // 이게 드래그 드롭을 위한 URL
                filebrowserUploadUrl: "/image"  // 파일업로드를 위한 URL
            });
          </script>
        </div>
      </div>
    </div>

    <div class="board-upload">
            <span>
              <div class="row justify-content-md-center">
                <div class="input-group mb-3">
                  <div class="custom-file">
                    &nbsp;
                      <input type="file" class="form-control-file" id="exampleFormControlFile1" name="multipartFile"/>
                  </div>
                </div>
            </span>
    </div>
    <div class="board-button">
      <a href="/vote/promiseDetail?idx=${promise.idx}&page=${page}&filter=${filter}&keyword=${keyword}"><button>취소</button></a>
      <a><button type="submit">확인</button></a>
    </div>
  </div>
  </form>
</section>

<%@include file="../../include/footer.jsp"%>
