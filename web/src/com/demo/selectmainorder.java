package com.demo;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "selectmainorder", urlPatterns = { "/selectmainorder" })
public class selectmainorder extends HttpServlet{
public void doPost(HttpServletRequest request, 
                    HttpServletResponse response)
                throws ServletException, IOException{
	String idexpense = request.getParameter("idexpense");
	  try{
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  String url="jdbc:mysql://localhost:3306/netcar?useSSL=false&serverTimezone=GMT%2B8";
          String username="root";
          String password=",.Fgh123.";
          String mainorder = "[";

          Connection conn=DriverManager.getConnection(url,username,password);
          Statement stmt = conn.createStatement();
          String sql = null;
          ResultSet rs = null;		
    	  response.setContentType("text/html;charset=UTF-8");       	    
    	  PrintWriter out = response.getWriter();
    	  if(!idexpense.isEmpty()) 
    		  sql = "select food.name,numFood,consumption.date from consumption,food "  
      		  		+ "where consumption.idFood=food.idFood and idexpense='"+ idexpense +"'";
    	  rs = stmt.executeQuery(sql);
    	  
    	  	  while(rs.next()){
    	  		if(rs.isFirst())mainorder+="{\"nameFood\":\""+rs.getString("name")+
    	  				"\",\"numFood\":\""+rs.getInt("numFood")+
    	  				"\",\"date\":\""+rs.getTimestamp("date")+"\"}";
    	  		else 
    	  			mainorder+=",{\"nameFood\":\""+rs.getString("name")+
	  				"\",\"numFood\":\""+rs.getInt("numFood")+
	  				"\",\"date\":\""+rs.getTimestamp("date")+"\"}";
    	  	  }
    	  	  mainorder+="]";
    	  	  out.write(mainorder);
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
public void doGet(HttpServletRequest request, 
        HttpServletResponse response) throws ServletException, IOException {
	this.doPost(request,response);
}
	  
  }
  
