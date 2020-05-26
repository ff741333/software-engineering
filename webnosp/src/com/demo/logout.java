package com.demo;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "logout", urlPatterns = { "/logout" })
public class logout extends HttpServlet{
public void doPost(HttpServletRequest request, 
                    HttpServletResponse response)
                throws ServletException, IOException{ 
	String user = (String) request.getSession().getAttribute("user");
	String psw = (String) request.getSession().getAttribute("password");
	if(user==null||psw==null) {
		response.sendRedirect("testLogin.html");
	}
	else {
		 try{
			  Class.forName("com.mysql.cj.jdbc.Driver");
			  String url="jdbc:mysql://localhost:3306/netcar?useSSL=false&serverTimezone=UTC";
	          String username="root";
	          String password=",.Fgh123.";
	          Connection conn=DriverManager.getConnection(url,username,password);
	          Statement stmt = conn.createStatement();
	          String sql = "select * from user where idUser = '" + user + "' and psw='" + psw + "'";
	          if(stmt.executeQuery(sql)!=null) {
	          ResultSet rs = stmt.executeQuery(sql);
	    	  response.setContentType("text/html;charset=UTF-8");       	    
	    	  PrintWriter out = response.getWriter();
	    	  String status = null;
	    	  while(rs.next()) {
	    		  status = rs.getString("identity");
	    	  }
	    	  if(status.equals("admin"))
	    		  response.sendRedirect("indexadmin.html?userName="+user);
	    	  else if(status.equals("customer"))
	    		  response.sendRedirect("indexuser.html?userName="+user);
	    	  else 
	    		  response.sendRedirect("indexsupplier.html?userName="+user);
	    	  rs.close();
	          }
	          else 
	      		response.sendRedirect("testLogin.html");
	  		
	          stmt.close();
	          conn.close(); 
		  }catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

  }
public void doGet(HttpServletRequest request, 
        HttpServletResponse response)
    throws ServletException, IOException{ 
	doPost(request,response);
}
}


