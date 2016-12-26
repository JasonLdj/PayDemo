package com.jsonliang.pay.baidupay.uils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

public class Structs2Util {

	//-- header �������� --//
	private static final String HEADER_ENCODING = "encoding";
	private static final String HEADER_NOCACHE = "no-cache";
	private static final String DEFAULT_ENCODING = "GBK";
	private static final boolean DEFAULT_NOCACHE = true;

	//-- content-type �������� --//
	private static final String TEXT_TYPE = "text/plain";
	private static final String JSON_TYPE = "application/json";
	private static final String XML_TYPE = "text/xml";
	private static final String HTML_TYPE = "text/html";
	private static final String JS_TYPE = "text/javascript";
		
	public static void render(HttpServletResponse response,String info)throws ServletException, IOException{
		response.setCharacterEncoding(DEFAULT_ENCODING);
		response.setContentType("text/html;charset=gbk");  
		response.setHeader("content-type","text/html;charset=gbk");
		PrintWriter out = response.getWriter();
		out.println(info);
	    out.flush();
	    out.close();
	}
	
	public static void Redirect(HttpServletResponse response,String info)throws ServletException, IOException{
		response.setCharacterEncoding(DEFAULT_ENCODING);
		response.setContentType("text/html;charset=gbk");  
		response.setHeader("content-type","text/html;charset=gbk");
		PrintWriter out = response.getWriter();
		response.sendRedirect(info); 
	    out.flush();
	    out.close();
	}

}
