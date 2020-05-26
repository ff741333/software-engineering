package com.demo;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "deletefood", urlPatterns = { "/deletefood" })
public class deletefood extends HttpServlet{
public void doPost(HttpServletRequest request, 
                    HttpServletResponse response)
                throws ServletException, IOException{
	String id = request.getParameter("Id");
	
	  if(request.getSession().getAttribute("user")!=null&&request.getSession().getAttribute("password")!=null) { 
			String admin = (String) request.getSession().getAttribute("user");
			String psw = (String) request.getSession().getAttribute("password");
	  try{
    	  response.setContentType("text/html;charset=UTF-8");       	    
    	  PrintWriter out = response.getWriter();
    	  
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  String url="jdbc:mysql://localhost:3306/netcar?useSSL=false&serverTimezone=GMT%2B8";
          String username="root";
          String password=",.Fgh123.";
          Connection conn=DriverManager.getConnection(url,username,password);
          Statement stmt = conn.createStatement();
          
          String sql2 = null;
          String sql3 = null;
          sql3 = "select * from user where idUser = '" + admin + "' and psw = '" + psw +"'";
          if(stmt.executeQuery(sql3).next()) {
          int flag = 0;
          int flag1 = 0;
    	  sql2 =  "delete from food where idfood = "+id;
    	  flag =stmt.executeUpdate(sql2);
    	  sql3 = "delete from store where idfood = "+id;
    	  flag1 =stmt.executeUpdate(sql3);
    	  if(flag!=0) out.write("1");
    	  else out.write("0");

    	  }
          else 
    		  out.write("0");
          
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
	  else {
    	  response.setContentType("text/html;charset=UTF-8");       	    
    	  PrintWriter out = response.getWriter();
		  out.write("���ȵ�¼^-^");
    	  out.flush();
    	  out.close();
	  }
	  
  }
}
