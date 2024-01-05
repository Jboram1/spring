<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<title>결과페이지</title>
	</head>
	<body>
	
		<c:choose>
			<c:when test="${result == 'success-bwrite'}"> <!-- == / eq 둘중 아무거나 써도 됨 -->
				<script>
					alert("게시글이 저장되었습니다.");
					location.href="blist";
				</script>
			</c:when>
			<c:otherwise>
			
			</c:otherwise>
		</c:choose>
	
	</body>
</html>