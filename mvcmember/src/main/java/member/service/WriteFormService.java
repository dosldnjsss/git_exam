package member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

public class WriteFormService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) 
			throws Throwable {//throws - JVM이 예외 잡아라. Throwable 부모 (모든 예외 잡아라) 
	
		return "/member/writeForm.jsp";//jsp 파일명만 넘기면 된다. 같이 forward시켜줘라.
	}

}
