package board.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import board.bean.BoardDTO;
import board.dao.BoardDAO;

public class BoardListService implements CommandProcess{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) 
			throws Throwable {
		
		//데이터 
		 int pg = Integer.parseInt(request.getParameter("pg"));

		//DB
		//페이징 처리 - 1페이지당 3개씩 
		int endNum = pg*5;
		int startNum = endNum-4;


		BoardDAO boardDAO = BoardDAO.getInstance(); //싱글톤

		ArrayList<BoardDTO> BoardDTOlist = boardDAO.getBoardList(startNum, endNum);

		
		int totalA = boardDAO.getTotalA();//총글수
		int totalP = (totalA+4)/5;//페이지 번호 [1페이지당 10개 : (total+9)/10]


		request.setAttribute("BoardDTOlist", BoardDTOlist);
		request.setAttribute("totalP", totalP);
		
		return "/board/boardList.jsp";
		
	}
}