package board.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import board.dao.BoardDAO;

public class BoardWriteService implements CommandProcess{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) 
			throws Throwable {
		
		//데이터 
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		//세션
		HttpSession session = request.getSession();
		String name = (String)session.getAttribute("memName");
		String id = (String)session.getAttribute("memId");
		String email = (String)session.getAttribute("memEmail");
		
		//문자열로 온다.
		//String name = (String) session.getAttribute("memName"); 
		//String id = (String) session.getAttribute("memId"); 
		//String email = (String) session.getAttribute("memEmail"); 
		
		
		//Object로 온다
		//MembmerDTO memberDTO = (MemberDTO)session.getAttribute("membDTO");
		//String name = memberDTO.getName(); 
		//String id = memberDTO.getId();
		//String email = memberDTO.getEmail() + "@" + memberDTO.getEmail2;
	
		
		//BoardDTO boardDTO = new BoardDTO();
		//boardDTO.setSubject(subject);
		//boardDTO.setContent(content);
		//boardDTO.setName(name);
		//boardDTO.setId(id);
		//boardDTO.setEmail(email);
		
		//DTO도 되고 Map도 된다.
		Map<String, String> map = new HashMap<String,String>();//dto대신 map을 써도 된다. 
		map.put("id", id);
		map.put("name", name);
		map.put("email", email);
		map.put("subject", subject);
		map.put("content", content);

		
		//DB - 싱글톤
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.write(map);
		
		

	
		System.out.println(id);
		System.out.println(name);
		System.out.println(email);
				
			
		return "/board/boardWrite.jsp";
	}
	
}

