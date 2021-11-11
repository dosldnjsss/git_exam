package member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

public class CheckIdService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) 
			throws Throwable {
		
		//데이터
		String id = request.getParameter("id");
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId(id);
		
		//DB
		MemberDAO memberDAO = MemberDAO.getInstance();
		boolean exist = memberDAO.isCheckId(id);//호출
		
		if(!exist) {
			return "/member/checkIdOk.jsp";
		}else {
			return "/member/checkIdFail.jsp";
		}
		
		
		
		
		//return null;
	}

}
