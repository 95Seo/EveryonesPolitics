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

    fill = "";
    $(document).on("click","input[type=radio]",function(){
        fill=$('input[name=filter]:checked').val();
        selectFill(fill);
    });

    function selectFill(showFill) {
        if(showFill == '이재명') {
            $("input:radio[id='radio1']").attr("checked", true);
            $("input:radio[id='radio2']").attr("checked", false);
            $("input:radio[id='radio3']").attr("checked", false);
            $("input:radio[id='radio4']").attr("checked", false);
        } else if(showFill == '윤석열') {
            $("input:radio[id='radio1']").attr("checked", false);
            $("input:radio[id='radio2']").attr("checked", true);
            $("input:radio[id='radio3']").attr("checked", false);
            $("input:radio[id='radio4']").attr("checked", false);
        } else if(showFill == '심상정') {
            $("input:radio[id='radio1']").attr("checked", false);
            $("input:radio[id='radio2']").attr("checked", false);
            $("input:radio[id='radio3']").attr("checked", true);
            $("input:radio[id='radio4']").attr("checked", false);
        } else if(showFill == '안철수') {
            $("input:radio[id='radio1']").attr("checked", false);
            $("input:radio[id='radio2']").attr("checked", false);
            $("input:radio[id='radio3']").attr("checked", false);
            $("input:radio[id='radio4']").attr("checked", true);
        }
    }

    function submit() {
        text = CKEDITOR.instances.content.getData();
        checked = $('input:radio[name="filter"]').is(':checked');
        if($("#title").val()=="") {
            alert("제목을 입력해 주세요.");
        } else if(text=="") {
            alert("내용을 입력해 주세요.");
        } else if(checked == false) {
            alert("카테고리를 선택해 주세요.");
        } else {
            $('#frm').submit();
        }
    }

    function cancel() {
        location.href = "/vote/boardList";
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
    <div class="container">
        <div class="row">
            <form action="/vote/boardCreate" method="POST" id="frm">
                <div class="board-wrap">
                    <div id="fillter">
                        <ul>
                            <li><input type="radio" name="filter" id="radio1" value="이재명"><label for="radio1" class="cursor_pointer">이재명</label></li>
                            <li><input type="radio" name="filter" id="radio2" value="윤석열"><label for="radio2" class="cursor_pointer">윤석열</label></li>
                            <li><input type="radio" name="filter" id="radio3" value="심상정"><label for="radio3" class="cursor_pointer">심상정</label></li>
                            <li><input type="radio" name="filter" id="radio4" value="안철수"><label for="radio4" class="cursor_pointer">안철수</label></li>
                        </ul>
                    </div>
                    <div class="board-title">
                        <p>제목</p>
                        <span>
                    <input type="text" id="title" name="title" />
                    </span>
                    </div>

                    <div class="col_c" style="display: flex; justify-content: center;" >
                        <div class="input-group" style="width: 100%;">
                            <textarea class="form-control" id="content" name="content"></textarea>
                            <script type="text/javascript">
                                CKEDITOR.replace("content", {
                                    height: 500,
                                    uploadUrl: "/image/drag?dir=BOARD/CONTENT",  // 이게 드래그 드롭을 위한 URL
                                    filebrowserUploadUrl: "/image?dir=BOARD/CONTENT"  // 파일업로드를 위한 URL
                                });
                            </script>
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
                        </div>
                    </span>
                    </div>
                    <div class="board-button">
                        <a onclick="cancel()">돌아가기</a>
                        <a id="submit" onclick="submit()">등록하기</a>
                    </div>
                </div>
            </form>
        </div>
    </div>

</section>

<%@include file="../../include/footer.jsp"%>
