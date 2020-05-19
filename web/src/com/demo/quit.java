package com.demo;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "quit", urlPatterns = { "/quit" })
public class quit extends HttpServlet{
public void doPost(HttpServletRequest request, 
                    HttpServletResponse response)
                throws ServletException, IOException{
	request.getSession().removeAttribute("user");
	request.getSession().removeAttribute("password");
	response.sendRedirect("testLogin.html");
	}
public void doGet(HttpServletRequest request, 
        HttpServletResponse response)
        		throws ServletException, IOException{
	doPost(request,response);

}
  }

