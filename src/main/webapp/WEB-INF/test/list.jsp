<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Tables</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				관리자 메인 페이지
			</div>
		</div>
		<!--  end panel-body -->
	</div>
	<!-- end panel -->
	<table class="table table-inbox table-hover" id="newTable" style="table-layout: fixed">
                      <tbody>
                        <thead>
                          <tr>
                            <th data-breakpoints="xs" style="width:30px;"></th>
                            <th style="width:60px;">Id</th>
                            <th>UserId</th>
                            <th style="width:330px; text-align:center;">Title</th>
                          </tr>
                        </thead>
                        <c:forEach items="${list}" var="test" varStatus="status">
                        <tr class="unread">
                          <td class="id">
                          	<c:out value="${test.id}" />
                          </td>
                          <td class="userId"><c:out value="${test.userId}" /></td>
                          <td class="title"><c:out value="${test.title}" /></td>
                        </tr>
						</c:forEach>
                      </tbody>
                    </table>
</div>
<!-- /.row -->
