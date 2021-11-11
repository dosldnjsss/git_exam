package board.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;
import board.bean.BoardDTO;
import board.dao.BoardDAO;

public class BoardViewService implements CommandProcess{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) 
			throws Throwable {
		
		int pg = Integer.parseInt(request.getParameter("pg"));
		int seq = Integer.parseInt(request.getParameter("seq"));

		//싱글톤 
		BoardDAO boardDAO = BoardDAO.getInstance();

		ArrayList<BoardDTO> BoardDTOContent = boardDAO.getGuestbookContent(seq);
		//BoardDTO boardDTO = boardDAO.getGuestbookContent(seq); - 한명분이라서 이렇게 가져오면 된다.

		request.setAttribute("BoardDTOContent", BoardDTOContent);
		//request.setAttribute("pg", pg);
			
		return "/board/boardView.jsp";
		
	}

}
