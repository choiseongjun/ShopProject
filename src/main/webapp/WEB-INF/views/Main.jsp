<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="Header.jsp" %>
  <c:choose>
<c:when test="${sessionScope.userid==null}">

</c:when>
   <c:otherwise>

</c:otherwise>
</c:choose>
</body>
</html>