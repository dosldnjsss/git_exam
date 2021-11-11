<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3><strong id="useid">${param.id}</strong>는 사용 가능</h3><!--${requestScope.id }은(는) 사용가능합니다. -->
	<input type="button" value="사용하기" id="userid" name="userid" onclick="closeIdCheck()">
																<%-- onclick="useId('${param.id }')" --%>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="../js/member.js"></script>	
		
</body>
</html>