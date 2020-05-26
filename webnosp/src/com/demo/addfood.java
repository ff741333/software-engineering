package com.demo;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "addfood", urlPatterns = { "/addfood" })
public class addfood extends HttpServlet{
public void doPost(HttpServletRequest request, 
                    HttpServletResponse response)
                throws ServletException, IOException{
	String name = request.getParameter("name");
    String type = request.getParameter("type");
	String price = request.getParameter("price");
	
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
    	  sql2 = "insert into food set name='" + name + "',price='" + price + "' ,type='" + type + "'";
    	  flag =stmt.executeUpdate(sql2);
 
    	  if(flag!=0) out.write("添加成功");
    	  else out.write("添加失败");

    	  }
          else 
    		  out.write("请先登录");
          
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
