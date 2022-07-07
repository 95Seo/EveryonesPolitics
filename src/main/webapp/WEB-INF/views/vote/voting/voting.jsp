<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@include file="../../include/header.jsp"%>

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
          <span
            >당신의&nbsp;
            <h2>후보</h2>
            에 투표하세요 !!</span
          >
          <div class="subtitle">
            <p>내가 지지하는 대통령 후보님들을 투표해보세요</p>
          </div>
        </div>
      </div>
    </div>

    <div class="row vote-Card">
      <form action="/form" method="post" id="frm">
        <div class="vote-Row" id="voting">
          <div class="col-sm-3">
            <li class="candidate01 comon-candidate">
              <input type="radio" name="vote" id="radio1" value="1" />
              <label for="radio1" class="cursor_pointer">
                <div class="vote-checked"></div>
                <%--
                <h2>이재명</h2>
                --%> <%--
                <p>새로운 대한민국<br />이재명은 합니다!</p>
                --%>
              </label>
            </li>
          </div>

          <div class="col-sm-3">
            <li class="candidate02 comon-candidate">
              <input type="radio" name="vote" id="radio2" value="2" />
              <label for="radio2" class="cursor_pointer">
                <div class="vote-checked"></div>
                <%--
                <h2>윤석열</h2>
                --%> <%--
                <p>공정과 상식의 나라<br />되찾겠습니다.</p>
                --%>
              </label>
            </li>
          </div>
        </div>

        <div class="vote-Row" id="voting">
          <div class="col-sm-3">
            <li class="candidate03 comon-candidate">
              <input type="radio" name="vote" id="radio3" value="3" />
              <label for="radio3" class="cursor_pointer">
                <div class="vote-checked"></div>
                <%--
                <h2>심상정</h2>
                --%> <%--
                <p>정의로운 복지국가,<br />정의당이 시작합니다</p>
                --%>
              </label>
            </li>
          </div>
        </div>
      </form>
      <div class="row">
        <div class="col-sm-12">
          <div class="vote-date-expirebox">
            <ul class="vote-icon">
              <li>
                <a onclick="vote_btn()"
                  ><input type="submit" class="votingbtn" value="투표하기"
                /></a>
              </li>
            </ul>
            <div class="vote-date-expire">
              <p>투표 기간</p>
              <p>12/17~12/24</p>
              <p>총 투표 수 : <c:out value="${voteCount}" /></p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>

<script>
  var vote = ${vote}

  $(document).on("click","input[type=radio]",function(){
      showFill=$('input[name=vote]:checked').val();
      //라디오 버튼 값을 가져온다.
      chang_fill(showFill);
  });


  function vote_btn() {
      if(isNaN(${principal.idx})) {
          alert("죄송합니다. 로그인이 필요한 서비스 입니다.");
      } else {
          if (vote != 0) {
              alert("죄송합니다. 이미 투표를 완료 하셨습니다.");
          } else {
              alert("투표에 성공하였습니다 !.");
              $("#frm").submit();
          }
      }
  }


  function chang_fill(fill) {
      if(fill == 1) {
          $("input:radio[id='radio1']").attr("checked", true);
          $("input:radio[id='radio2']").attr("checked", false);
          $("input:radio[id='radio3']").attr("checked", false);
          $("input:radio[id='radio4']").attr("checked", false);
      } else if(fill == 2) {
          $("input:radio[id='radio1']").attr("checked", false);
          $("input:radio[id='radio2']").attr("checked", true);
          $("input:radio[id='radio3']").attr("checked", false);
          $("input:radio[id='radio4']").attr("checked", false);
      } else if(fill == 3) {
          $("input:radio[id='radio1']").attr("checked", false);
          $("input:radio[id='radio2']").attr("checked", false);
          $("input:radio[id='radio3']").attr("checked", true);
          $("input:radio[id='radio4']").attr("checked", false);
      } else if(fill == 4) {
          $("input:radio[id='radio1']").attr("checked", false);
          $("input:radio[id='radio2']").attr("checked", false);
          $("input:radio[id='radio3']").attr("checked", false);
          $("input:radio[id='radio4']").attr("checked", true);
      }
  }
</script>

<%@include file="../../include/footer.jsp"%>
