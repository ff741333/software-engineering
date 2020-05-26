package com.demo;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "selectServlet", urlPatterns = { "/selectServlet" })
public class selectServlet extends HttpServlet{
public void doPost(HttpServletRequest request, 
                    HttpServletResponse response)
                throws ServletException, IOException{
	String year = request.getParameter("year");
	String month = request.getParameter("month");
	  try{
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  String url="jdbc:mysql://localhost:3306/netcar?useSSL=false&serverTimezone=UTC";
          String username="root";
          String password=",.Fgh123.";
          String profit = "[";
          String date = null;

          Connection conn=DriverManager.getConnection(url,username,password);
          Statement stmt = conn.createStatement();
          String sql = null;
          ResultSet rs = null;		
          PrintWriter out;
    	  response.setContentType("text/html;charset=UTF-8"); 
    	  out = response.getWriter();
    	  sql = "select * from profit where year(date) = "+ year +" and month(date) = " + month ;
    	  rs = stmt.executeQuery(sql);
    	  
    	  	  while(rs.next()){
    	  		if(rs.isFirst())profit+="{\"ProfitID\":\""+rs.getInt("idprofit")+
    	  				"\",\"Date\":\""+rs.getDate("date")+
    	  				"\",\"Income\":\""+rs.getFloat("income")+
    	  				"\",\"Cost\":\""+rs.getFloat("cost")+
    	  				"\",\"Profit\":\""+rs.getFloat("profit")+"\"}";
    	  		else 
    	  			profit+=",{\"ProfitID\":\""+rs.getInt("idprofit")+
	  				"\",\"Date\":\""+rs.getDate("date")+
	  				"\",\"Income\":\""+rs.getFloat("income")+
	  				"\",\"Cost\":\""+rs.getFloat("cost")+
	  				"\",\"Profit\":\""+rs.getFloat("profit")+"\"}";
    	  	  }
    	  	  profit+="]";
    	  	  out.write(profit);
    	  
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
  
