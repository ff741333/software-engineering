package com.demo;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "loginServlet", urlPatterns = { "/loginServlet" })
public class loginServlet extends HttpServlet{
public void doPost(HttpServletRequest request, 
                    HttpServletResponse response)
                throws ServletException, IOException{
	  String user = request.getParameter("account");
	  String psw = request.getParameter("password");
	  
	  try{
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  String url="jdbc:mysql://localhost:3306/netcar?useSSL=false&serverTimezone=UTC";
          String username="root";
          String password=",.Fgh123.";
          Connection conn=DriverManager.getConnection(url,username,password);
          Statement stmt = conn.createStatement();
          String sql = "select * from user where idUser = '" + user + "'";
          ResultSet rs = stmt.executeQuery(sql);
    	  response.setContentType("text/html;charset=UTF-8");       	    
    	  PrintWriter out = response.getWriter();
    	  String name=null;
    	  String psw1=null;
    	  String status = null;
    	  while(rs.next()) {
           name = rs.getString("idUser");
           psw1=rs.getString("psw");
           status=rs.getString("identity");
        	 }
          if(name != null && psw.equals(psw1)) {
        	  request.getSession().setAttribute("user", user);
        	  request.getSession().setAttribute("password", psw1);
        	  if(status.equals("admin"))
        		  out.write("2");
        	  else if(status.equals("customer"))
        		  out.write("1");
        	  else
        		  out.write("3");
          }
          else {
        	  out.write("0");
          }
          out.flush();
          out.close();
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

