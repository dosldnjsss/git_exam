package com.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandProcess {//부모 - request, response 받아서 String타입(jsp파일)로 넘길것 
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable;
}
