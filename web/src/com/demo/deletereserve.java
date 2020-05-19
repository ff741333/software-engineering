package com.demo;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "reserve", urlPatterns = { "/deletereserve" })
public class deletereserve extends HttpServlet{
public void doPost(HttpServletRequest request, 
                    HttpServletResponse response)
                throws ServletException, IOException{
	String id = request.getParameter("ReserveID");
	  try{
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  String url="jdbc:mysql://localhost:3306/netcar?useSSL=false&serverTimezone=GMT%2B8";
          String username="root";
          String password=",.Fgh123.";
          String reserves = "[";

          Connection conn=DriverManager.getConnection(url,username,password);
          Statement stmt = conn.createStatement();
          String sql = null;
          int flag = 0;
    	  response.setContentType("text/html;charset=UTF-8");       	    
    	  PrintWriter out = response.getWriter();
    	  sql = "delete from reserve where idreserve = "+id;
    	  flag =stmt.executeUpdate(sql);
    	  if(flag!=0) out.write(id+"�� ɾ���ɹ�");
    	  else out.write(id+"�� ɾ��ʧ��");
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
  
