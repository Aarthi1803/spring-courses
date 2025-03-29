package com.telusko.ServletEx1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException { //since we are getting data thus using doGet() (can also use service()). If we are posting data we can use doPost(). 
//	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		System.out.println("In Service");
		
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		out.println("<h2> <b> Hello World </b> </h2>"); // we are getting out object from response and it will write in response object

//		res.getWriter().println("Hello World");
	}
}

/* HTTP  Method: 
   We have methods like get, post, put, delete and multiple. So let's say if you want to get data from a server, you use a Get request.
   When you want to send data to the server. We use Post request.
   When you want to delete something from the server, use you send delete request.
   And if you want to update something, we use a put request.
   If you want to send data like you are creating a form in which you are submitting some data. In that case you will be using a Post request.
 */

/*
 * Servlet is responsible to accept the request from the client. And JSP is responsible to create that beautiful page which you want.
   That means you can do your processing in servlet. Not just processing.
   You can accept the request from the servlet, you can do the processing there.
   And then once you get your data, just send that data to JSP page. And JSP will send that to the client with the HTML.
 */
