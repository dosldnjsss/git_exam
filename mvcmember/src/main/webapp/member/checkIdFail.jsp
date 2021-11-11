<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>아이디 ${param.id }는 사용 불가능</h3><!--${requestScope.id }  -->
	<form action="/mvcmember/member/checkId.do">
		<div>
		아이디 
		<input type="text" name="id" id="id">
		<input type="submit" value="중복체크">
		</div>
	</form>

<!-- <script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="../js/member.js"></script>	 -->
	
</body>
</html>