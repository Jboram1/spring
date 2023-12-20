<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <script src="http://code.jquery.com/jquery-latest.min.js"></script>
  <title>뷰페이지</title>
  <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500,700,900&display=swap&subset=korean" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
  <link rel="stylesheet" href="css/style.css">
  <link rel="stylesheet" href="css/read.css">
</head>
<body>
<section>
    <h1><a href="/">NOTICE</a></h1>
    <form action="" id="bFrm" name="bFrm" method="post">
    <input type="hidden" name="bno" value="${map.bdto.bno}">
    

    <table>
      <colgroup>
        <col width="15%">
        <col width="50%">
        <col width="15%">
        <col width="20%">
        
      </colgroup>
      <tr>
      	<th>글번호</th>
        <th colspan="3">${map.bdto.bno}</th>
      </tr>
      <tr>
      	<td><strong>제목</strong></td>
        <td colspan="3">${map.bdto.btitle}</td>
      </tr>
      <tr>
        <td><strong>작성자</strong></td>
        <td>${map.bdto.id}</td>
        <td><strong>조회수</strong></td>
        <td>${map.bdto.bhit}</td>
      </tr>
       <tr>
      	<td colspan="4" class="article">${map.bdto.bcontent}</td>
      </tr>
      <tr>
      	<td><strong>파일이름</strong></td>
        <td colspan="3">${map.bdto.bfile}</td>
      </tr>
      <tr>
      	<td><strong>이미지</strong></td>
        <td colspan="3">
        <c:if test="${map.bdto.bfile != null}">
        	<img src="/upload/${map.bdto.bfile}">
        </c:if>
        <c:if test="${map.bdto.bfile == null}">
        	첨부파일 없음
        </c:if>
       	</td>
      </tr>
    
      <tr>
      	<td><strong>다음글</strong></td>
        <td colspan="3">
        <c:if test="${map.nextbdto != null}">
        	<a href="bView?bno=${map.nextbdto.bno}">${map.nextbdto.btitle}</a></td>
        </c:if>
        <c:if test="${map.nextbdto == null}"> 다음글이 없습니다요</c:if>
      </tr>
      <tr>
      	<td><strong>이전글</strong></td>
        <td colspan="3">
        <c:if test="${map.prebdto != null}">
        	<a href="bView?bno=${map.prebdto.bno}">${map.prebdto.btitle}</a></td>
        </c:if>
        <c:if test="${map.prebdto == null}"> 이전글이 없습니다요</c:if>
      </tr>
      
    </table>
	<script>
		$(function(){
			//삭제
			$(".delBtn").click(function(){
				if(confirm("게시글을 삭제하시겠습니까?")){
					$("#bFrm").attr("action","bDelete").submit();
				}	
			});
		});
	</script>
    <a href="bList"><div class="list">목록</div></a>
    <div class="list delBtn" style="cursor:pointer;">삭제</div></a>
    <a href=""><div class="list">수정</div></a>
    <a href=""><div class="list">답변달기</div></a>
    </form>
  </section>
</body>
</html>