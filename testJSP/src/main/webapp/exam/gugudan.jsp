<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%

for(int i=1; i<=9; i++){
	for(int j=1; j<=9; j++){
		System.out.print(j+"*"+i+"="+i*j+"<br>");
	}
}

%>  --%>   
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
<%
for(int i=1; i<=9; i++){
	for(int j=2; j<=9; j++){
		out.write(j+"*"+i+"="+i*j+"\t");
	}
	out.write("<br>");//web안에서 out을쓰면 자바코드를 쓰기 때문에 아예 안쓴다. 
} %>

<% for(int i=1; i<=9; i++){ %>
	<% for(int dan=2; dan<=9; dan++){ %>
		<td width="150" align="center">
		<%=dan %> * <%=i %> = <%=dan*i %>
	<%}//for dan  %>
	</tr>
<%}//for i %>
</table>
</body>
</html>