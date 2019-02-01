<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
	function fn_formSubmit() {
		var form1 = document.form1;

		if (form1.brdmemo.value == "") {
			alert("글 내용을 입력해주세요.");
			form1.brdmemo.focus();
			return;
		}
		form1.submit();
	}
</script>


<body>
	<%@include file="../Header.jsp"%>
	<center>
			<form name="form1" action="/Multi/FreeBoardSave" method="post" enctype="multipart/form-data">
		<table border="1" style="width:600px">
			<caption>게시판</caption>
			<colgroup>
				<col width='15%' />
				<col width='*%' />
			</colgroup>
			<tbody>

				<tr>
					<td>제목</td>
					<td><input type="text" name="brdtitle" size="70"
						maxlength="250" value="<c:out value="${boardInfo.brdtitle}"/>"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea name="brdmemo" rows="5" cols="60"><c:out
								value="${boardInfo.brdmemo}" /></textarea></td>
				</tr>
				<tr>
					<td>첨부</td>
					<td><c:forEach var="listview" items="${listview}"
							varStatus="status">
							<input type="checkbox" name="fileno"
								value="<c:out value="${listview.fileno}"/>">
							<a
								href="fileDownload?filename=<c:out value="${listview.filename}"/>&downname=<c:out value="${listview.realname }"/>">
								<c:out value="${listview.filename}" />
							</a>
							<c:out value="${listview.size2String()}" />
							<br />
						</c:forEach> <input type="file" name="uploadfile" multiple="" /></td>
				</tr>
			</tbody>
			</table>
			<input type="hidden" name="bgno" value="<c:out value="${bgno}"/>"> 
			<input type="hidden" name="brdno"
				value="<c:out value="${boardInfo.brdno}"/>"> <a href="#"
				onclick="form1.submit()">저장</a>
		</form>
	</center>
</body>
</html>