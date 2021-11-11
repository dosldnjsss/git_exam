<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.dao.BoardDAO" %>
<%@ page import="board.bean.BoardDTO" %>  
<%@ page import ="java.util.*" %>  
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table{

	color: blue;
	border-collapse: collapse;
	border-bottom: 1px solid blue;
	text-decoration : none;
	
}
tr{
	/* border-top: 1px solid blue; */
	/* border-bottom: 1px solid blue; */
	height: 15px;
}
td{
	font-size: 13px;
}
th{
	font-size: 16px;
}
#subjectA:link{
	color : black;
	text-decoration : none;
}
/*
#subjectA:visited{
	color : black;
	text-decoration : none;
} */
#subjectA:hover{
	color : green;
	text-decoration : underline;
}
/* #subjectA:link{
	color : black;
	text-decoration : none;
} */
</style>
</head>
<body>
<%-- <% 
//데이터 
request.setCharacterEncoding("UTF-8");
int pg =Integer.parseInt(request.getParameter("pg"));


//DB
//페이징 처리 - 1페이지당 5개씩 
int endNum = pg*5;
int startNum = endNum-4; --%>

<!-- //싱글톤 
BoardDAO boardDAO = BoardDAO.getInstance();

ArrayList<BoardDTO> BoardDTOlist = boardDAO.getBoardList(startNum, endNum);

int totalA = boardDAO.getTotalA();
int totalP = (totalA+4)/5;

if(BoardDTOlist!=null){
	 -->
<!-- %> -->

<!--<table border="1" cellspacing="0" cellpadding = "5" frame = "hsides" rules="rows"> : 가로만 나오게해라  -->
<table>	
		<tr>
			<th width="100">글번호</th>
			<th width="100">제목</th>
			<th width="100">작성자</th>
			<th width="100">조회수</th>
			<th width="100">작성일</th>
			
		</tr>
</table>		
<%-- <%			
	for(BoardDTO boardDTO : BoardDTOlist){
	
%> --%>
	<!-- <table>
		<tr>
			<td width="100" align="center">글번호</td>
			<td width="100" align="center">제목</td>
			<td width="100" align="center">작성자</td>
			<td width="100" align="center">조회수</td>
			<td width="100" align="center">작성일</td>
			
		</tr> -->
	<table>	
		<!--for문쓰기  -->
			<c:forEach var="boardDTO" items="${BoardDTOlist }">
			<tr>
				<td width="100" align="center">${boardDTO.getSeq()}</td>
				<td width="100" align="center">
					<a href="boardView.do?seq=${boardDTO.getSeq()}&pg=1" id="subjectA">${boardDTO.getSubject()}</a>
				</td>
				<td width="100" align="center">${boardDTO.getName()}</td>
				<td width="100" align="center">${boardDTO.getHit()}</td>
				<td width="100" align="center">${boardDTO.getLogtime()}</td>
			</tr>
			</c:forEach>
		
	
	</table> 
	
	<div style ="border: 1px red solid; width: 500px; text-align : center;">
	<c:forEach var="i" begin="1" end="${totalP}" step="1">
			<c:if test="${pg==i}">
				[<a href="boardList.do?pg=${i}" id="currentpagingA">${i}</a>]
			</c:if>
			<c:if test="${pg!=i}">
				[<a href="boardList.do?pg=${i}" id="pagingA">${i}</a>]
			</c:if>
		</c:forEach>
 	</div>
</body>
</html>