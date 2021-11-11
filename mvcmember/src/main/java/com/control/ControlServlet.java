package com.control;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//서블릿은 등록을 해야한다. - web.xml 또는 어노테이션 @WebServlet("/ControlServlet") 
// @WebServlet("/ControlServlet")
public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> map = new HashMap<String, Object>();
	   
	   public void init(ServletConfig config) {//1번호출
	      String propertyConfig = config.getInitParameter("propertyConfig");//properties 파일을 읽는다. (매번 못 간다)
	      System.out.println("propertyConfig = "+propertyConfig+"\n");
	      
	      FileInputStream fin = null;
	      Properties properties = new Properties();
	      
	      try {
	         fin = new FileInputStream(propertyConfig);//파일읽는다. 
	         properties.load(fin);
	         System.out.println("properties = "+properties);
	         
	      } catch (IOException e) {
	         e.printStackTrace();
	      }finally{
	         try {
	            fin.close();
	         } catch (IOException e) {
	            e.printStackTrace();
	         }
	      }
	      System.out.println();
	      
	      Iterator it = properties.keySet().iterator();
	      while(it.hasNext()) {
	         String key = (String)it.next();
	         System.out.println("key = "+key);
	         
	         String className = properties.getProperty(key);
	         System.out.println("className = "+className);
	         
	         try {
	            Class<?> classType = Class.forName(className);
	            Object ob = classType.newInstance();//생성(클래스타입), new가 안된다. 
	            
	            System.out.println("ob = "+ob);
	            
	            map.put(key, ob);//맵 안에다가 보관한다. - properties랑 연결되는게 아니고 map이랑 연결되는 것이다. 
	            
	         } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	         } catch (InstantiationException e) {
	            e.printStackTrace();
	         } catch (IllegalAccessException e) {
	            e.printStackTrace();
	         } catch (IllegalArgumentException e) {
	            e.printStackTrace();
	         } 
	         
	         System.out.println();
	      }//while
	      
	   }
    
	//주소로 넘어온 데이터 - 한글 처리 o 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		execute(request,response);//어떤 방식이든 request,response를 가지고 execute()로 가라.
	}
	//post와 get은 한글처리빼고는 같다. 
	
	//내부적으로 넘어온 데이터 - 한글 처리 x 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		execute(request,response);//어떤 방식이든 request,response를 가지고 execute()로 가라.

	}
	
	//get방식과 post방식이 만나는 곳 - execute (어떤 요청이 들어와도 여기로 빠진다)  
	protected void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//한글처리
		if(request.getMethod().equals("POST")){//form의 method 부분. POST 이부분 무조건 대문자. 
			request.setCharacterEncoding("UTF-8");
		}
		
		//요청 url을 앞머리를 자르고 뒤에꺼만 들어와라 
		//http://localhost:8080/mvcmember/member/writeForm.do 요청
		String category = request.getServletPath();// -> /member/writeForm.do(주소 중 이거만 잘라라)
	    System.out.println("category = "+category);
	      
	      //부모 (interface)
	    CommandProcess commandProcess = (CommandProcess)map.get(category);//map 부른다. 요청부분을 주면 가져온다. 
	    System.out.println("commandProcess = "+commandProcess);
	      
	    String view = null;
	    try {
	       view = commandProcess.requestPro(request, response);//추상메소드 요청(request,response 보낸다) - 돌아와서 - view에다가 jsp파일을 넣는다.
	    } catch (Throwable e) {
	       e.printStackTrace();
	    }
	      
	      //forward  (jsp, servlet 연결)
	      RequestDispatcher dispatcher = request.getRequestDispatcher(view);//상대번지(절대번지 x) 
	      dispatcher.forward(request, response);//제어권 넘기기

	}

}
