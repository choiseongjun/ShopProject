<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="../Header.jsp"%>
</head>
<body>
<center>
   			<table border="1" style="width:600px">
			<caption>게시판</caption>
			<colgroup>
				<col width='15%' />
				<col width='*%' />
			</colgroup>
			<tbody>
				<tr>
					<td>작성자</td> 
					<td><c:out value="${boardInfo.brdwriter}"/></td> 
				</tr>
				<tr>
					<td>제목</td> 
					<td><c:out value="${boardInfo.brdtitle}"/></td> 
				</tr>
				<tr>
					<td>내용</td> 
					<td><c:out value="${boardInfo.brdmemo}" escapeXml="false"/></td> 
				</tr>
			</tbody>
		</table>    
		<a href="#" onclick="history.back(-1)">돌아가기</a>
		<a href="/Category/board1Delete?brdno=<c:out value="${boardInfo.brdno}"/>">삭제</a>
		<a href="/Category/board1Form?brdno=<c:out value="${boardInfo.brdno}"/>">수정</a>
</center>
</body>
</html>