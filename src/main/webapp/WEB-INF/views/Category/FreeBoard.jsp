<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
function fn_formSubmit(){
	document.form1.submit();	
}
</script>
<body>
<%@include file="../Header.jsp"%>
<center>
<a href="/Category/board1Form">글쓰기</a>
                    
<table border="1" style="width:600px">
		<caption>게시판</caption>
		<colgroup>
			<col width='8%' />
			<col width='*%' />
			<col width='15%' />
			<col width='15%' />
			<col width='10%' />
		</colgroup>
		<thead>
			<tr>
				<th>번호</th> 
				<th>제목</th>
				<th>등록자</th>
				<th>등록일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="listview" items="${listview}" varStatus="status">	
				<c:url var="link" value="/Category/board1Read">
					<c:param name="brdno" value="${listview.brdno}" />
				</c:url>		
										  				
				<tr>
					<td>
					<c:out value="${searchVO.totRow-((searchVO.page-1)*searchVO.displayRowCount + status.index)}"/>					
					</td>
					<td style="border: 1px solid black; max-width: 100px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
					<a href="${link}"><c:out value="${listview.brdtitle}"/></a></td>
					<td><c:out value="${listview.brdwriter}"/></td>
					<td><c:out value="${listview.brddate}"/></td>
					<td><c:out value="${listview.brdhit}"/></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
	<c:if test="${pageVO.totPage>1}">
		<div class="paging">
			<c:forEach var="i" begin="${pageVO.pageStart}" end="${pageVO.pageEnd}" step="1">
				<c:url var="pageLink" value="board2List">
					<c:param name="page" value="${i}" />
				</c:url>						
	            <c:choose>
	                <c:when test="${i eq pageVO.page}">
	                	<c:out value="${i}"/>
	                </c:when>
	                <c:otherwise>
	                	<a href="${pageLink}"><c:out value="${i}"/></a>
	                </c:otherwise>
	            </c:choose>
	        </c:forEach>
		</div>
		<br/>
	</c:if>		
	<form id="form1" name="form1"  method="post">
	    <jsp:include page="../Common/pagingforSubmit.jsp" />
	    
		<div>
			<input type="checkbox" name="searchType" value="brdtitle" <c:if test="${fn:indexOf(searchVO.searchType, 'brdtitle')!=-1}">checked="checked"</c:if>/>
			<label class="chkselect" for="searchType1">제목</label>
			<input type="checkbox" name="searchType" value="brdmemo" <c:if test="${fn:indexOf(searchVO.searchType, 'brdmemo')!=-1}">checked="checked"</c:if>/>
			<label class="chkselect" for="searchType2">내용</label>
			<input type="text" name="searchKeyword" style="width:150px;" maxlength="50" value='<c:out value="${searchVO.searchKeyword}"/>' onkeydown="if(event.keyCode == 13) { fn_formSubmit();}">
			<input name="btn_search" value="검색" class="btn_sch" type="button" onclick="fn_formSubmit()" />
		</div>
	</form>	

</center>
</body>
</html>