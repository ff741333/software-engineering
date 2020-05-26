package com.demo;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "selectorder", urlPatterns = { "/selectorder" })
public class selectorder extends HttpServlet{
public void doPost(HttpServletRequest request, 
                    HttpServletResponse response)
                throws ServletException, IOException{
	String date = request.getParameter("date");
	String user = request.getParameter("user");
	  try{
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  String url="jdbc:mysql://localhost:3306/netcar?useSSL=false&serverTimezone=GMT%2B8";
          String username="root";
          String password=",.Fgh123.";
          String order = "[";

          Connection conn=DriverManager.getConnection(url,username,password);
          Statement stmt = conn.createStatement();
          String sql = null;
          ResultSet rs = null;		
    	  response.setContentType("text/html;charset=UTF-8");       	    
    	  PrintWriter out = response.getWriter();
    	  if(!date.isEmpty()) 
    		  sql = "select idexpense,cost,date,status from expense "  
      		  		+ "where DATE_FORMAT(date,'%Y-%m-%d') = '"+ date +"' and idUser='"+ user +"'"  ;    	  
    	  else 
    		  sql = "select idexpense,cost,date,status from expense "  
        		  		+ "where idUser='"+ user +"'";
    	  rs = stmt.executeQuery(sql);
    	  
    	  	  while(rs.next()){
    	  		if(rs.isFirst())order+="{\"idexpense\":\""+rs.getInt("idexpense")+
    	  				"\",\"status\":\""+rs.getString("status")+
    	  				"\",\"cost\":\""+rs.getFloat("cost")+
    	  				"\",\"date\":\""+rs.getTimestamp("date")+"\"}";
    	  		else 
    	  			order+=",{\"idexpense\":\""+rs.getInt("idexpense")+
        	        "\",\"status\":\""+rs.getString("status")+	
	  				"\",\"cost\":\""+rs.getFloat("cost")+
	  				"\",\"date\":\""+rs.getTimestamp("date")+"\"}";
    	  	  }
    	  	  order+="]";
    	  	  out.write(order);
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
  
