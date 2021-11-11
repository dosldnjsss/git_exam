package member.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import member.bin.ZipcodeDTO;
import member.dao.MemberDAO;

public class CheckPostService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) 
			throws Throwable {
		
		//데이터 
		String sido = request.getParameter("sido");		
		String singungu = request.getParameter("sigungu");
		String roadname = request.getParameter("roadname");
		
		System.out.println(sido+" , "+singungu+" , "+roadname);
		
		List<ZipcodeDTO>list = null;
		
		//DB
		if(sido != null && roadname!=null) {//singungu : 세종시는 구가 없어서 null로 들어올수밖에 없음
			MemberDAO memberDAO = MemberDAO.getInstance();
			list = memberDAO.getZipcodeList(sido, singungu, roadname);
			//list에 ZipcodeDTO를 담아왔다.
		}
		
		//응답 
		request.setAttribute("list", list);
		return "/member/checkPost.jsp";//문자열 리턴 x, jsp 파일 
	}

}
