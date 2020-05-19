package com.demo;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "changecustomer", urlPatterns = { "/changecustomer" })
public class changecustomer extends HttpServlet{
public void doPost(HttpServletRequest request, 
                    HttpServletResponse response)
                throws ServletException, IOException{
	String idUser = request.getParameter("idUser");
	String sex = request.getParameter("sex");
	String numPhone = request.getParameter("numPhone");
	String name = request.getParameter("name");
	String birthday = request.getParameter("birthday");

	  try{
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  String url="jdbc:mysql://localhost:3306/netcar?useSSL=false&serverTimezone=GMT%2B8";
          String username="root";
          String password=",.Fgh123.";
          
          Connection conn=DriverManager.getConnection(url,username,password);
          Statement stmt = conn.createStatement();
          String sql = null;
          int rs = 0;		
    	  response.setContentType("text/html;charset=UTF-8");       	    
    	  PrintWriter out = response.getWriter();
    	  sql = "update customer " 
    	  +"set sex='"+sex+"',numPhone='"+numPhone+"',name='"+name+"',birthday='"+birthday+"' " 
    	  + "where idUser='"+idUser+"'";
    	  rs = stmt.executeUpdate(sql);
    	  if(rs!=0)out.write('1');
    	  else out.write('0');
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
  
