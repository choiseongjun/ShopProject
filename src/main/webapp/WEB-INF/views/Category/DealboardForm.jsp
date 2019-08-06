<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
.fileDrop {
	width: 80%;
	height: 100px;
	border: 1px dotted gray;
	background-color: lightslategrey;
	margin: auto;
}
</style>


 

<c:if test="${ type eq 'ifr' }">
<script>
  console.debug("-------------ifr script!!")
  parent.setUploadedFile('${ savedFileName }');
</script>
</c:if>
<style>
	table{
		width:600px;
		margin-top:200px;
	}
</style>
<%@include file="../Header.jsp"%>
<body>
			<form name="form1" action="/Multi/FreeBoardSave" method="post" enctype="multipart/form-data">
		<table border="1" >
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
		   <form action="/uploadForm" id="form1" method="POST" enctype="multipart/form-data">
        <input type="file" name="file" />
        <input type="submit" />
    </form>
    SavedFileName: ${ savedFileName }
    
     
    <hr />
    <div class="fileDrop"><p>Drop Hear!!</p></div>
    <div class="uploadedList"></div>
     
    <form action="uploadAjaxes" id="form3" method="POST" enctype="multipart/form-data">
        <input type="hidden" name="type" value="ajax" />
        <!-- <input type="file" name="file[]" id="ajax-file" style="display:none;" /> -->
        <input type="file" name="files" id="ajax-file" style="display: none;" />
        <input type="submit" value="ajax로 제출" />
    </form>
    <div id="percent">0 %</div>
    <div id="status">ready</div>
    AJAX=SavedFileName:<span id="jax-upfile"></span>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="/js/jQuery/jQuery.form.min.js"></script>  
<script>
console.debug("00000");
window.setUploadedFile=(filename)=>{
	document.getElementById('upfile').innerHTML=filename;
	document.getElementById("form2").reset();
};

const $fileDrop=$('div.fileDrop'),
		$uploadedList=$('div.uploadedList');
	
$fileDrop.on('dragover dragenter',(evt) => {
	evt.preventDefault();
});
$fileDrop.on('drop',(evt) => {
	evt.preventDefault();//사진 임의적으로 안되게 막아놓는거
	console.log('drop>>',evt.originalEvent.dataTransfer.files);
});

const percent=$('#percent'),
	  status=$('#status');
$('#form3').ajaxForm({
	beforeSend: function(){
		status.empty();
		percent.html('0%');
	},
	uploadProgress: function(event,position,total,percentComplete){
		status.html('uploading...');
		var percentVal=percentComplete + '%';
		percent.html(percentVal);
	},
	complete:function(xhr){
		status.html(xhr.responseText);
	}
})

</script>
</body>
</html>