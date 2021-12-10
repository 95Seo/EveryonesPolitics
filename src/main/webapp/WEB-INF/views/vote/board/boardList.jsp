<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../../include/header.jsp"%>

<script>
    $(document).ready(function() {

        var actionForm = $("#actionForm");

        var pageNumTag = $("input[name='pageNum']").clone();
        var amountTag = $("input[name='amount']").clone();
        var keywordTag = $("input[name='keyword']").clone();
        var typeTag = $("input[name='type']").clone();

        // 페이지 번호 클릭 이벤트
        $(".paginate_button a").on("click", function(e) {
            e.preventDefault();
            // console.log('click');
            actionForm.find("input[name='pageNum']").val($(this).attr("href"));
            actionForm.submit();
        });
    });
</script>

<!-- Form 시작 -->
<form id='actionForm' action="/vote/boardList" method='get'>
    <input type='hidden' name='pageNum' value='${pageMaker.cri.pageNum}'>
    <input type='hidden' name='amount' value='${pageMaker.cri.amount}'>
    <input type='hidden' name='type' value='<c:out value="${ pageMaker.cri.type }"/>'>
    <input type='hidden' name='keyword'	value='<c:out value="${ pageMaker.cri.keyword }"/>'>
</form>

<!-- board Section(게시판 리스트)-->
<section id="board-list" class="board-list">
    <div class="promise-list-wrap">
        <div class="promise-wrap">
            <div class="list-box">
                <ul class="list-hd">
                    <li>
                        <div class="no">번호</div>
                        <div class="subject">제목</div>
                        <div class="date">등록일</div>
                        <div class="writer">글쓴이</div>
                        <div class="view-num">조회 수</div>
                        <div class="recommend-num">추천 수</div>
                    </li>
                </ul>
                <ul class="list-bd">
                    <c:forEach items="${boardList}" var="board_list">
                        <li>
                            <div class="no"><c:out value="${board_list.idx}" /></div>
                            <div class="subject"><a href="/vote/boardDetail?${board_list.idx}"><c:out value="${board_list.title}" /></a></div>
                            <div class="date"><c:out value="${board_list.sysregdate}" /></div>
                            <div class="writer"><c:out value="${board_list.idx}" /></div>
                            <div class="view-num"><c:out value="${board_list.view_cnt}" /></div>
                            <div class="recommend-num">0</div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="list-btn">
                <a href="/vote/boardCreate">글쓰기</a>
            </div>

            <div class="paging">
                <!--  Pagination 시작 -->
                <ul class="pagination">
                    <c:if test="${pageMaker.prev}">
                        <li class="paginate_button previous"><a href="${pageMaker.startPage -1}">Previous</a></li>
                    </c:if>
                    <c:forEach var="num" begin="${pageMaker.startPage}"	end="${pageMaker.endPage}">
                        <li class="paginate_button  ${pageMaker.cri.pageNum == num ? "active":""} ">
                            <a href="${num}">${num}</a>
                        </li>
                    </c:forEach>
                    <c:if test="${pageMaker.next}">
                        <li class="paginate_button next"><a href="${pageMaker.endPage +1 }">Next</a></li>
                    </c:if>
                </ul>
                <!--  Pagination 끝 -->
            </div>
        </div>
    </div>
</section>

<%@include file="../../include/footer.jsp"%>