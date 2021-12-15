<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="../../include/header.jsp"%>

<style>
  .templatemo-item-col .behind-item {
    border: solid 1px #797979;
    border-radius: 18px;
  }
  .templatemo-item-col .behind-item a {
    color: black;
  }
  .behind-title {
    height: 70px;
  }
  .behind-img {
    width: 100%;
    height: 200px;
  }
  .behind-contents {
    height: 50px;
  }



</style>

<script>
  var obj={};
  obj.dataType = "JSON";
  obj.error=function(e){console.log(e)};

  map = JSON.stringify(${json});

  cri = JSON.parse(map);

  showPage = cri.page;  //보여줄 페이지

  showAmount = cri.amount;

  var showFill = cri.filter;

  var keyWord = cri.keyword;

  listCall(showPage); //리스트 호출 함수

  $(document).on("change","input[type=radio]",function(){
    showFill=$('input[name=fill]:checked').val();
    keyWord = "";
    //라디오 버튼 값을 가져온다.
    listCall(showPage);
  });

  $(document).on("click","input[name=search]",function(){
    keyWord=$('input[name=keyword]').val();
    listCall(showPage);
  });

  // /listSub/{pagePerCnt}/{page}
  function listCall(page){
    var param = {};
    param = {"amount":showAmount, "page":page, "filter":showFill, "keyword":keyWord};

    $.ajax({
      anyne:true,
      type:'POST',
      data: JSON.stringify(param),
      contentType: 'application/json',
      // contentType: 'application/x-www-form-urlencoded; charset=euc-kr',
      url:"/vote/getBehindPagingList/",
      dataType: "text",
      success : function(result) {
        console.log(d);
        var d = JSON.parse(result);
        message = d.message;
        if (message == "fail") {
          var content = "<div class='no_object'>게시물이 없습니다.</div>";
          //내용 붙이기
          $("#b_list").empty();
          $("#b_list").append(content);
        } else {
          listPrint(d.list, d.currPage); //리스트 그리기
          pagePrint(d.currPage,d.range); //페이징 처리
        }
      },
      error: function(jqXHR, textStatus, errorThrown) {
        alert("ERROR : " + textStatus + " : " + errorThrown);
      }
    });
  }

  //받아온 리스트 그리기
  function listPrint(list, currPage){
    if(keyWord == null || keyWord == "") {
      keyWord = "";
      $('#keyword').val('');
    } else {
      $('#keyword').val(keyWord);
    }

    content ="";

    list.forEach(function(item, idx){
      view_title = "";
      if (item.title.length > 30) {
        view_title = item.title.substr(0, 30) + "...";
      } else {
        view_title = item.title;
      }

      view_content = "";
      var newText = item.content.replace(/(<([^>]+)>)/ig,"");
      if (newText.length > 40) {
        view_content = newText.substr(0, 40) + "...";
      } else {
        view_content = newText
      }

      content += '<div class="col-lg-4 templatemo-item-col all">';
      content += "<div class='behind-item'><a href=/vote/behindDetail?idx="+item.idx+"&page="+currPage+"&filter="+showFill+"&keyword="+keyWord+">";
      content += '<div class="thumb">';
      content += '<div class="price"><span>'+item.filter+'</span></div>';
      content += '<img class="behind-img" src="'+item.fileUrl+'"/></div>'
      content += '<div class="down-content"><div class="behind-title">'
      content += '<h4>'+view_title+'</h4></div>';
      content += '<div class="behind-contents">'+view_content;
      content += '</div><div><span>'+item.sysregdate+'</span><span style="margin-left: 6px;"><i class="far fa-comment-dots"></i>3</span>';
      content += '</div>';
      content += '</div>';
      content += '</a></div>';
      content += '</div>';
    });

    //내용 붙이기
    $("#b_list").empty();
    $("#b_list").append(content);
  }


  //페이징 그리기 => 플러그인을 사용하면 쓸 필요가 없음.
  function pagePrint(currPage,range){
    //이전
    var start =1;
    var end = 5;
    var content="";

    if(currPage>5){
      //end = currPage + 4; 일 경우 페이지 변화 시 마다 페이징이 이동된다.
      //우리가 원하는 것은 5 단위로 움직일때 새로운 페이징 생성
      end = Math.ceil(currPage/5)*5; //생성가능 페이지수? 6,11,16 씩 대입해 보자
      start= end - 4;
      content +="<li><a onclick='listCall("+(start-1)+")' class='fa fa-angle-left cursor_pointer'>이전</a> |</li> ";
    }

    $("input:radio[name='fill']").attr("checked", false);

    if(showFill == '이재명') {
      $("input:radio[id='radio1']").attr("checked", true);
    } else if(showFill == '윤석열') {
      $("input:radio[id='radio2']").attr("checked", true);
    } else if(showFill == '심상정') {
      $("input:radio[id='radio3']").attr("checked", true);
    } else if(showFill == '안철수') {
      $("input:radio[id='radio4']").attr("checked", true);
    } else {
      $("input:radio[id='radio5']").attr("checked", true);
    }

    for(var i=start; i<=end;i++){
      //i는 절대 생성 가능 페이지보다 크면 안된다.
      if(i <= range){
        if(currPage == i){
          content += "<li  class='active'><a style='color: white'>"+i+"</a></li>"
        }else{
          content += "<li><a class='cursor_pointer' onclick='listCall("+i+")' style='color: black'>"+i+"</a></li>"
        }
      }
    }

    //다음 (range가 5보다 클 경우)
    if(end < range){
      content += " <li><a onclick='listCall("+(end+1)+")' class='fa fa-angle-right cursor_pointer'>다음</a></li>"
    }
    $("#paging").empty();
    $("#paging").append(content);
  }

  function create_btn() {
    if(isNaN(${principal.idx})) {
      alert("죄송합니다. 로그인이 필요한 서비스 입니다.");
    } else {
      location.href = "/vote/boardCreate";
    }
  }
</script>

<section class="behind-page common-list" id="behind">
  <div class="container">

        <div class="row">
          <div class="col-lg-12">
            <div id="fillter">
              <ul>
                <li><input type="radio" name="fill" id="radio5" value="all"><label for="radio5" class="cursor_pointer" >전체</label></li>
                <li><input type="radio" name="fill" id="radio1" value="이재명"><label for="radio1" class="cursor_pointer">이재명</label></li>
                <li><input type="radio" name="fill" id="radio2" value="윤석열"><label for="radio2" class="cursor_pointer">윤석열</label></li>
                <li><input type="radio" name="fill" id="radio3" value="심상정"><label for="radio3" class="cursor_pointer">심상정</label></li>
                <li><input type="radio" name="fill" id="radio4" value="안철수"><label for="radio4" class="cursor_pointer">안철수</label></li>
              </ul>
            </div>
          </div>
        </div>

        <div class="row behind-Cardview">
            <ul>
              <li id="b_list">

              </li>
            </ul>
        </div>

      <div class="paging">
          <!--  Pagination 시작 -->
          <ul class="pagination" id="paging">
          </ul>
          <!--  Pagination 끝 -->
      </div>

        <div class="row">
          <div class="col-lg-12">
          </div>
        </div>

      <c:if test="${principal.role == 'ADMIN'}">
          <div class="more1"><a href="/vote/behindCreate"><i class="fas fa-pen fa-2x"></i></a></div>
      </c:if>
  </div>


</section>

<!-- Scripts -->
<script src="../resources/js/isotope.min.js"></script>
<script src="../resources/js/custom.js"></script>

<%@include file="../../include/footer.jsp"%>