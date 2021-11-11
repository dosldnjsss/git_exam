<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table{
	border-collapse: collapse; 
	/* border-top : 1px solid black; */
}
tr{
	border-top: 1px solid black;
}
.content{
	word-break : break-all;
}


</style>
</head>
<body>
<table>
	<%-- 	<c:forEach var="boardDTO" items="${BoardDTOContent}"> --%>
		<tr>
			<td colspan="3" width = "400" height="30">${BoardDTOContent[0].getSubject()}</td>
		</tr>
		
		<tr>
			<td width = "400">글번호: ${BoardDTOContent[0].getSeq()}</td>
			<td width = "400">작성자:${BoardDTOContent[0].getName()}</td>
			<td width = "400">조회수:${BoardDTOContent[0].getHit()}</td>
		</tr>
		
		<tr>
			<td colspan="3" width = "400" height="500" valign="top" class="content">
				${BoardDTOContent[0].getContent()}
			</td>
		</tr>
	<%-- 	</c:forEach> --%>
		<tr>
			<td width = "400">
				<input type="button" value="목록" onclick="location.href='boardWriteList.jsp?pg=${pg}'">
			</td>
		</tr>
		
	</table>

</body>
</html>