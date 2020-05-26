package com.web.servlet;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "changeshopcar", urlPatterns = { "/changeshopcar" })
public class changeshopcar extends HttpServlet{
	@Value("${mysql.url}")
	private String url;
	@Value("${mysql.username}")
	private String username;
	@Value("${mysql.password}")
	private String password;
public void doPost(HttpServletRequest request, 
                    HttpServletResponse response)
                throws ServletException, IOException{
	String event = request.getParameter("event");
	String name = request.getParameter("FoodsName");
	String admin = (String) request.getSession().getAttribute("user");
	  try{
		  Class.forName("com.mysql.cj.jdbc.Driver");

          Connection conn=DriverManager.getConnection(url,username,password);
          Statement stmt = conn.createStatement();
          String sql = null;
          int rs =0;
          ResultSet rs1 = null;
    	  response.setContentType("text/html;charset=UTF-8");       	    
    	  PrintWriter out = response.getWriter();
    	  
    	  if(event.equals("add"))
    		  sql="update car set numfood = numfood +1 where idUser = '"+ admin +"' and idfood = (select idfood from food where name = '"+name+"')";
    	  else if(event.equals("sub")) {    		  
    		  sql="select numfood from car  where idUser = '"+ admin +"' and idfood = (select idfood from food where name = '"+name+"')";
    		  rs1 = stmt.executeQuery(sql);
    		  while(rs1.next()) {
    			  rs=rs1.getInt("numfood");
    		  }
    		  if(rs==1) 
        		  sql="delete from car where idUser = '"+ admin +"' and idfood = (select idfood from food where name = '"+name+"')";
    		  else
    		  sql="update car set numfood = numfood -1 where idUser = '"+ admin +"' and idfood = (select idfood from food where name = '"+name+"')";
    	  }
    	  else     		 
    		  sql="delete from car where idUser = '"+ admin +"' and idfood = (select idfood from food where name = '"+name+"')";
    	  rs=stmt.executeUpdate(sql);
    	  if(rs!=0) out.write("1");
    	  else out.write("0");
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
  
