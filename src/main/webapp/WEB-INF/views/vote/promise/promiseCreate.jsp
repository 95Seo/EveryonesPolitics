<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="../../include/header.jsp"%>
<script
        type="text/javascript"
        src="../resources/css/ckeditor/ckeditor.js">
</script>
<script src="../resources/js/promise.js"></script>

<section class="board">

  <div class="board-wrap">
    <div class="board-title">
      <label for="title">Title</label>
      <input type="text" id="title" name="title" />

    </div>
    <div class="row justify-content-md-center">
      <div class="col_c" >
        <div class="input-group">
          <textarea class="form-control" id="content" name="content"></textarea>
          <script type="text/javascript">
            CKEDITOR.replace("content", { height: 500, width: 1200 });
          </script>
        </div>
      </div>
    </div>

    <div class="board-upload">
            <span>
              <div class="row justify-content-md-center">
                <div class="input-group mb-3">
                  <div class="custom-file">
<%--                    &nbsp;<input--%>
<%--                          type="file"--%>
<%--                          class="form-control-file"--%>
<%--                          id="exampleFormControlFile1"--%>
<%--                  />--%>
                  </div>
                </div>
            </span>
    </div>
    <div class="board-button">
      <a href="/vote/promiseList"><button>돌아가기</button></a>
      <button id="btn-save">등록하기</button>
    </div>
  </div>

</section>

<%@include file="../../include/footer.jsp"%>