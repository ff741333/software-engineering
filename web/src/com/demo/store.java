package com.demo;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "store", urlPatterns = { "/store" })
public class store extends HttpServlet{
public void doPost(HttpServletRequest request, 
                    HttpServletResponse response)
                throws ServletException, IOException{
	String FoodID = request.getParameter("Id");
	int id = Integer.parseInt(request.getParameter("Id"));
	String num = request.getParameter("num");
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
          ResultSet rs = null;
          String sql1 = null;
          sql1 = "update food set num = num+"+num+" where idfood='"+FoodID+"'";
          if(stmt.executeUpdate(sql1)!=0) {
        	  sql1 = "INSERT INTO `store` (`storename`, `storeprice`, `date`, `num`, `idfood`) VALUES " + " ('"+admin+"','"+ price  +"',now(),"+ num +", '"+id+"')";
        	  if(stmt.executeUpdate(sql1)!=0)
        	  out.write('1');
              else out.write('0');
          }
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
	  else {
    	  response.setContentType("text/html;charset=UTF-8");       	    
    	  PrintWriter out = response.getWriter();
		  out.write("0");
    	  out.flush();
    	  out.close();
	  }
	  
  }
}
