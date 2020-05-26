package com.web.servlet;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "regServlet", urlPatterns = { "/regServlet" })
public class regServlet extends HttpServlet{
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
	  String pwd = request.getParameter("pwd");
	  try{
    	  response.setContentType("text/html;charset=UTF-8");       	    
    	  PrintWriter out = response.getWriter();
		  Class.forName("com.mysql.cj.jdbc.Driver");
          Connection conn=DriverManager.getConnection(url,username,password);
          Statement stmt = conn.createStatement();
          String sql1 = "select * from user where idUser = '" + user + "'";
          if(!stmt.executeQuery(sql1).next()) {
          String sql = "insert into user set idUser = '" + user +"' , psw = '" + pwd +"' , identity = 'customer'";
          int rs = stmt.executeUpdate(sql);
    	  
          if(rs!=0) {
        	  out.write("1");
          }
          else {
        	  out.write("0");
          }
          }
          else {
        	  out.write("2");
          }
          out.flush();
          out.close();
          stmt.close();
          conn.close();
	  }catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}

  }
  }

