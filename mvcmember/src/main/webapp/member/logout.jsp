<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
/*
//쿠키
Cookie[]ar = request.getCookies();//모든 쿠키를 다 꺼내와야 한다.(하나씩 꺼내는 건 안된다.)
if(ar != null){
	for(int i=0; i<ar.length; i++){
		if(ar[i].getName().equals("memName")){
			ar[i].setMaxAge(0); //쿠키 삭제
			response.addCookie(ar[i]);//클라이언트 보내기
		}
		
		if(ar[i].getName().equals("memId")){
			ar[i].setMaxAge(0); //쿠키 삭제
			response.addCookie(ar[i]);//클라이언트 보내기

		}
		System.out.println("쿠키명 = "+ ar[i].getName());//쿠키명 = JSESSIONID(다 같음)
		System.out.println("쿠키값 = "+ ar[i].getValue());//쿠키값 = 5FE57658ECC9ED96F9C8DF61C11AA3E9(컴퓨터 id - pc당 1대)
	}//for
}//if
*/

/* LogoutService.java로 옮김 
//세션 -특정 세션 제거 
session.removeAttribute("memName");//내장객체 - session
session.removeAttribute("memId");
			
//세션 - 모든 세션 제거 
session.invalidate(); //무효화
 */			
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>로그아웃</h3>
<script type="text/javascript">
window.onload=function(){
	alert("로그아웃");
	location.href="../index.jsp"; //현재폴더에서 나가서 index로 가주세요.
}
</script>
</body>
</html>