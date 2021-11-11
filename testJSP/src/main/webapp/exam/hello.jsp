<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
//전역변수, 1번 생성, servlet-init()
String name="홍길동"; //자바코드 위치 아무곳에 써도 상관없다. 화면에 뿌렸다(자바코드로 인식x)
int age= 25;
%>

<%
//지역변수, 매번, servlet-service()
age++;
%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!--Hello JSP!!<br> - html주석  -->
<%--안녕하세요 JSP!!<br> - jsp주석 --%>
나의 이름은 <%=name %>입니다. <br>
<!--내 나이는 <%=age %>살 입니다.<br>  - html주석 : 처리한다(age가 올라간다.) 변수 없으면 error -->
<%--<% out.println("내 나이는 "+ age +"살 입니다."); %> --%>
</body>
</html>