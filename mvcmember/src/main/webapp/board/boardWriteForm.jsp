<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
div{
	color : red;
	font-weigth: bold;
	font-size: 8pt;
}
</style>
</head>
<body>
	<h3>글쓰기</h3>
	<form name="boardWrite" method="post" action="http://localhost:8080/mvcmember/board/boardWrite.do">
		<table border="1" cellspacing="0" cellpadding="5">
			<tr>
				<td width="100" align="center">제목</td>
				<td>
					<input type="text" id="subject" name="subject" size="48">
					<div id="subjectDiv"></div>
				</td>
			</tr>
			
			<tr>
				<td width="100" align="center">내용</td>
				<td>
					<textarea cols="50" rows="15" id="content" name="content"></textarea>
					<div id="contentDiv"></div>
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<input type="button" id="writeBtn" value="글쓰기">
					<input type="reset" value="다시작성">
				</td>
			</tr>
		
		</table>
	</form> 
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="../js/board.js"></script>

</body>
</html>