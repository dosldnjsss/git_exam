package member.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

public class ModifyFormService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		

		MemberDTO memberDTO = new MemberDTO();
		
		//세션
		HttpSession session = request.getSession(); 
		memberDTO = (MemberDTO) session.getAttribute("memberDTO");

		request.setAttribute("memberDTO", memberDTO);
		
		
		return "/member/modifyForm.jsp";
	}
}
