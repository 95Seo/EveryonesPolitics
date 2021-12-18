<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="../../include/header.jsp"%>
<script
      type="text/javascript"
      src="../resources/css/ckeditor/ckeditor.js"
    ></script>

<script>
  CKEDITOR.on('dialogDefinition', function (ev) {

    var dialogName = ev.data.name;
    var dialog = ev.data.definition.dialog;
    var dialogDefinition = ev.data.definition;
    if (dialogName == 'image') {
      dialog.on('show', function (obj) {
        this.selectPage('Upload'); //업로드텝으로 시작
      });
      dialogDefinition.removeContents('advanced'); // 자세히탭 제거
      dialogDefinition.removeContents('Link'); // 링크탭 제거
    }
  });

  function submit() {
    text = CKEDITOR.instances.content.getData();
    if($("#title").val()=="") {
      alert("제목을 입력해 주세요.");
    } else if(text=="") {
      alert("내용을 입력해 주세요.");
    } else {
      $('#frm').submit();
    }
  }

  function cancel() {
    location.href = "/vote/boardDetail?idx=${board.idx}&page=${page}&filter=${filter}&keyword=${keyword}";
  }
</script>

<style>
  .board-button > a {
    cursor: pointer;
    background: #efefef;
    display: inline-block;
    border: 1px solid #dcdcdc;
    width: 150px;
    height: 70px;
    line-height: 70px;
    color: black;
  }
</style>

<section class="board common-list" style="margin-top: 80px">
  <form action="/vote/boardUpdate" id="frm" method="post" enctype="multipart/form-data">
    <input type="hidden" name="page" value="${page}"/>
    <input type="hidden" name="filter" value="${filter}"/>
    <input type="hidden" name="keyword" value="${keyword}"/>
    <input type="hidden" name="idx" value="${board.idx}" />
  <div class="board-wrap">
    <div class="board-title">
      <p>제목</p>
      <span>
        <input type="text" id="title" name="title" value="${board.title}" />
      </span>
    </div>
    <div class="row justify-content-md-center">
      <div class="col_c" >
        <div class="input-group">
          <textarea class="form-control" id="content" name="content"><c:out value="${board.content}" escapeXml="false" /></textarea>
          <script type="text/javascript">
            CKEDITOR.replace("content", {
                height: 500,
                width: 1200,
                uploadUrl: "/image/drag?dir=BOARD/CONTENT",  // 이게 드래그 드롭을 위한 URL
                filebrowserUploadUrl: "/image?dir=BOARD/CONTENT"  // 파일업로드를 위한 URL
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
                  </div>
                </div>
            </span>
    </div>
    <div class="board-button">
      <a onclick="cancel()">돌아가기</a>
      <a id="submit" onclick="submit()">등록하기</a>
    </div>
  </div>
  </form>
</section>

<%@include file="../../include/footer.jsp"%>
