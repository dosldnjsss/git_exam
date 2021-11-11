<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- 
<%

String name = null; 
String id = null; 


//쿠키
/*
Cookie[]ar = request.getCookies();//모든 쿠키를 다 꺼내와야 한다.(하나씩 꺼내는 건 안된다.)

if(ar != null){
	for(int i=0; i<ar.length; i++){
		String cookieName = ar[i].getName();//쿠키명	
		String cookieValue = ar[i].getValue();//값
		
		System.out.println("쿠키명 = "+ cookieName);//쿠키명 = JSESSIONID(다 같음)
		System.out.println("쿠키값 = "+ cookieValue);//쿠키값 = 5FE57658ECC9ED96F9C8DF61C11AA3E9(컴퓨터 id - pc당 1대)
		
		if(cookieName.equals("memName"))name = cookieValue; 
		if(cookieName.equals("memId")) id = cookieValue;
		
	}//for
}//if
*/

//세션 
name = (String)session.getAttribute("memName"); //jsp 내장객체 : session(그냥 session이라고 쓰면 된다.)
id = (String)session.getAttribute("memId"); 
%>    
    --%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
img{
	width: 50px;
	heigth:50px;
	cursor : pointer; /*마우스 모양을 화살표에서 손가락으로 바뀐다.손가락: 링크  */
}

</style>
</head>
<body>
<%-- <h3>${requestScope.name }님 로그인</h3> --%>
<%-- <%=name %>님 로그인 --%>

<img src="../image/dog.jpg" onclick="location.href='../index.jsp'"><!--속성 : inline이라서 비추천 - 매번 와서 고쳐야함  -->
${sessionScope.memName }님 로그인<!--자바파일로 들어옴. 위에 코드 불필요-->
<br><br>

<input type="button" value="로그아웃" onclick="location.href='/mvcmember/member/logout.do'"><!--요청 - ControlServlet으로 가라/ jsp로 바로 가면 안된다.  -->
<input type="button" value="회원정보수정" onclick="location.href='/mvcmember/member/modifyForm.do'">
<!--http://localhost:8080/mvcmember/member/logout.do  -->
</body>
</html>