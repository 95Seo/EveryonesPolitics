<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>REST</title>
<link href="resources/css/test/board.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		//ajaxSetup():  실행될  AJAX 요청에 대한 기본 속성을 정의해 재사용 
		$.ajaxSetup({
			success:function(result){					
				alert(result);
			},
			error: function (jqXHR) {
                alert("jqXHR status code:"+jqXHR.status+" message:"+jqXHR.responseText);
            }
		});//ajaxSetup
		$("#testGetListBtn").click(function(){	
			$.ajax({
				type:"get",
				url:"products",				
				success:function(productList){	
					$("#listView").empty();
					$.each(productList,function(i,product){
						$("#listView").append(product.id+" "+product.name+" "+product.maker+" "+product.price+"<br>").css("background","pink");
					});					
				}
			});//ajax
		});//click
		$("#testGetBtn").click(function(){	
			$.ajax({
				type:"get",
				url:"products/"+$("#pid").val(),				
				success:function(product){					
					alert(product.id+" "+product.name+" "+product.maker+" "+product.price);
				}
			});//ajax
		});//click	
		$("#testCreateBtn").click(function(){			
			$.ajax({
				type:"post",
				url:"products",
				data:$("#createProductForm").serialize()
			}).done(function(){ // done - success 와 동일
				$("#createProductForm")[0].reset();
			});//ajax
		});//click	
		$("#testPutBtn").click(function(){			
			$.ajax({
				type:"put",
				url:"products?"+$("#updateProductForm").serialize()
			}).always(function(){ // always 요청에 대한 처리가 success or fail 상관없이 항상 실행 
				$("#updateProductForm")[0].reset();
			});
		});//click	
		
		$("#testDeleteBtn").click(function(){			
			$.ajax({
				type:"delete",
				url:"products/"+$("#deleteId").val()
			});//ajax
		});//click		
	});//ready
</script>
<style type="text/css">
	.restImg{
		width: 600px;
		height: 400px		
	}
</style>
</head>
<body>
<%--	
REST(REpresentational State Transfer) : 분산 시스템을 위한 HTTP 기반 소프트웨어 아키텍쳐

특징 
0. HTTP 프로토콜 기반 -> 웹표준을 최대한 활용 -> Stateless ( 클라이언트의 상태를 저장 x )  

1. 웹에 존재하는 모든 자원에 고유한 URI(통합자원식별자:Uniform Resource Identifier)를 부여해 활용 
HTTP GET(조회) , POST(생성) , PUT(수정), DELELTE(삭제) 

2. "다양한 클라이언트"에게 서비스를 제공하고 클라이언트와 서버 역할의 명확한 분리가 가능.  
-> 모바일 , 태블릿 등과 같은 다양한 디바이스 및 이기종(다른 기술언어로 구축)된 시스템에게  
    HTTP기반 서비스를 제공하기 위해 사용되고 클라이언트는 REST API를 통해 
    서버와 정보를 주고 받는다 

3. "서비스별 분리 , 통합"에 대한 표준화된 방법을 제공
 
  어플리케이션의 복잡도가 증가 -> 서비스별로  
  독립적으로 구축, 운영하는 것에 대한 필요성 대두  
  ex) 고객정보서비스 | 예약서비스 | 항공편서비스 
   

관련 기술 :  SOAP -> REST  											  
											   	
REST API : 
REST 기반 서비스 API 
어플리케이션 간의 데이터 통신을 위한 어플리케이션 프로그래밍 인터페이스

RESTful : REST API 제공하는 웹서비스 시스템을 지칭 , "A 서비스 시스템은 'RESTful' 하다"



 --%>
<h3>REST  적용 웹어플리케이션 구현 예제 </h3>
   <ul> 
   	<li>
   	HTTP Request Method <br><br>
   	GET : 리스트 조회 <input type="button" value="testGetListBtn" id="testGetListBtn"><br>
   	<div id="listView"></div> 	<br> <br>
   	GET : 조회 <input type="button" value="testGetBtn" id="testGetBtn"><input type="text" id="pid"><br><br> 
	POST :생성 <input type="button" value="testCreateBtn" id="testCreateBtn"><br>
	<form id="createProductForm">
		상품명 <input type="text" name="name" size="5"> 제조사 <input type="text" name="maker" size="5"> 가격 <input type="number" name="price">
	</form>
	<br><br>
	PUT : 수정 <input type="button" value="testPutBtn" id="testPutBtn"><br>
		<form id="updateProductForm">
		상품번호 <input type="text" name="id" size="3" id="updateId"> 
		상품명 <input type="text" name="name" size="5"> 제조사 <input type="text" name="maker" size="5"> 가격 <input type="number" name="price">
	</form>
	<br>
	DELETE : 삭제 <input type="button" value="testDeleteBtn" id="testDeleteBtn">
	<input type="text" id="deleteId">
   	</li>  
   </ul>
   <%-- src/main/resources/static 아래에 images/rest-api.png 가 있다  --%>
   <img class="restImg" src="resources/image/rest-api.jpg">
</body>
</html>