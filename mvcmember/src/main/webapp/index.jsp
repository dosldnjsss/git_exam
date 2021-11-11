<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>*** 메인화면 ***</h3>

<c:if test ="${sessionScope.memId ==null}"><!--세션이 없다면 - memId  -->
	<a href="http://localhost:8080/mvcmember/member/writeForm.do">회원가입</a><br>
	<a href="/mvcmember/member/loginForm.do">로그인</a><br><!--로그인할때 로그아웃 뜨면 안된다. - 세션으로 구분  -->
</c:if>

<c:if test ="${sessionScope.memId !=null}"><!--세션이 있다면 - memId  -->
	<a href="/mvcmember/member/logout.do">로그아웃</a><br><!--로그아웃할때는 로그인 뜨면 안된다. - 세션으로 구분  -->
	<a href="/mvcmember/member/modifyForm.do">회원정보수정</a><br>
	<a href="/mvcmember/board/boardWriteForm.do">글쓰기</a><br>
</c:if>

<a href="/mvcmember/board/boardList.do?pg=1">목록</a><br>
<a href=""></a><br>

</body>
</html>