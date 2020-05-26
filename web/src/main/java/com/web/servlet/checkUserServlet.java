package com.web.servlet;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "checkServlet", urlPatterns = { "/checkUserServlet" })
public class checkUserServlet extends HttpServlet{
	@Value("${mysql.url}")
	private String url;
	@Value("${mysql.username}")
	private String username;
	@Value("${mysql.password}")
	private String password;
public void doPost(HttpServletRequest request, 
                    HttpServletResponse response)
                throws ServletException, IOException{
	  String user = request.getParameter("user");
	  
	  try{
		  Class.forName("com.mysql.cj.jdbc.Driver");
          Connection conn=DriverManager.getConnection(url,username,password);
          Statement stmt = conn.createStatement();
          String sql = "select * from user where idUser = '"+user+"'";
          ResultSet rs = stmt.executeQuery(sql);
    	  response.setContentType("text/html;charset=UTF-8");       	    
    	  PrintWriter out = response.getWriter();
    	  String name = null;
    	  while(rs.next()){
    		  name = rs.getString("idUser");  		  
          }
          if(name != null ) {
        	  out.write("1");
          }
          else {
        	  out.write("0");
          }
    	  rs.close();
          stmt.close();
          conn.close();
	  }catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}

  }
  }

