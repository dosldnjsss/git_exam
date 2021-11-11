package member.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

public class LoginService implements CommandProcess{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) 
			throws Throwable {
		//데이터 
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		//String email = request.getParameter("email");
		
		//DB
		//싱글톤
		MemberDAO memberDAO = MemberDAO.getInstance();		
		//String[]arr = memberDAO.login(id,pwd);//호출
		MemberDTO memberDTO = memberDAO.login(id,pwd); // 자바는 리턴값이 1개. 그래서 배열, dto, Map
		
		//응답
		 //if(arr==null) {
		if(memberDTO==null) {
			return "/member/loginFail.jsp";
		}else {
			//쿠키생성
			/*
			Cookie cookie =new Cookie("memName",name);//쿠키생성 - (쿠키명, 값)
			cookie.setMaxAge(3000);//초 단위(3000초동안만 쿠키가 살아있어라.) 
			//setPath()를 지정 안해도 시간을 늘리면 된다. 
			//cookie.setPath("/member/");-- - 만약 URL를 /member/로 지정하면 member폴더로 쿠키 전송해라
			response.addCookie(cookie);//클라이언트로 보내기
			
			//쿠키2생성
			Cookie cookie2 =new Cookie("memId",id);//쿠키생성 - (쿠키명, 값)
			cookie2.setMaxAge(3000);//초 단위(3000초동안만 쿠키가 살아있어라.) 
			//cookie2.setPath("/member/");//-- 만약 URL를 /member/로 지정하면 member폴더로 쿠키 전송해라
			response.addCookie(cookie2);
			
			//response.sendRedirect("/mvcmember/member/loginOk.jsp");
			*/
			
			//세션 
			HttpSession session = request.getSession();//세션 생성 (인터페이스 - new 안된다)
//			session.setAttribute("memName", arr[0]);
//			session.setAttribute("memId",  id);
//			session.setAttribute("memEmail",arr[1]);
//			
//			//모든 정보가 쿠키로 들어가서 데이터를 보낼 필요가 없다. - 광범위하게 쓸 떄는 request를 많이 쓴다. 
//			request.setAttribute("name", arr[0]);//방법2. 데이터를 싣는다.(데이터이름, 데이터) (request는 Servlet꺼다. 하나의 request가 돌고 있는 거다) 
			
			
			//하나씩 담아도 되고 
			session.setAttribute("memName", memberDTO.getName());
			session.setAttribute("memId", id);
			session.setAttribute("memEmail", memberDTO.getEmail1()+"@"+memberDTO.getEmail2());
			
			//DTO를 다 받아와도 된다. 
			session.setAttribute("memberDTO", memberDTO);  
			
			return "/member/loginOk.jsp";
			//return "/member/loginOk.jsp?name="+name;//방법1. 주소로 넘겨준다 
		}
	}

}
