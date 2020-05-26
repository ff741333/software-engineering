package com.demo;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "totalcar", urlPatterns = { "/totalcar" })
public class totalcar extends HttpServlet{
public void doPost(HttpServletRequest request, 
                    HttpServletResponse response)
                throws ServletException, IOException{
	String admin = (String)request.getSession().getAttribute("user");
	  try{
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  String url="jdbc:mysql://localhost:3306/netcar?useSSL=false&serverTimezone=GMT%2B8";
          String username="root";
          String password=",.Fgh123.";
          String total = null;

          Connection conn=DriverManager.getConnection(url,username,password);
          Statement stmt = conn.createStatement();
          String sql = null;
          ResultSet rs = null;		
    	  response.setContentType("text/html;charset=UTF-8");       	    
    	  PrintWriter out = response.getWriter();
		  sql = "select * from car,food where food.idfood=car.idfood and idUser ='"+admin+"'";
		  rs = stmt.executeQuery(sql);
		  int sumnum = 0;
		  float summoney = 0;
          while(rs.next()) {
        	  sumnum+=rs.getInt("numfood");
        	  summoney+=rs.getInt("numfood")*rs.getFloat("price");
        	  
          }
          total="{\"sumnum\":\"" + sumnum+ "\",\"summoney\":\"" + summoney + "\"}";
    	  	  out.write(total);
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
  
