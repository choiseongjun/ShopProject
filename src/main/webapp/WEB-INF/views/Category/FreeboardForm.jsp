<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
function fn_formSubmit(){
    var form1 = document.form1;
    
    if (form1.brdtitle.value=="") {
        alert("글 제목을 입력해주세요.");
        form1.brdtitle.focus();
        return;
    }
    if (form1.brdmemo.value=="") {
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
   <form name="form1" a href="#" onclick="fn_formSubmit()" action="/Category/FreeBoardSave" method="post">
        <table border="1" style="width:600px">
            <caption>게시판</caption>
            <colgroup>
                <col width='15%' />
                <col width='*%' />
            </colgroup>
            <tbody>
               
                <tr>
                    <td>제목</td> 
                    <td><input type="text" name="brdtitle" size="70" maxlength="250"></td> 
                </tr>
                <tr>
                    <td>내용</td> 
                    <td><textarea name="brdmemo" rows="5" cols="60"></textarea></td> 
                </tr>
            </tbody>
        </table>    
        <a href="#" onclick="form1.submit()">저장</a>
    </form>    
</center>
</body>
</html>