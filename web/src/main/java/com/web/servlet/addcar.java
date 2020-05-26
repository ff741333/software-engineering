package com.web.servlet;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "addcar", urlPatterns = { "/addcar" })
public class addcar extends HttpServlet{
	@Value("${mysql.url}")
	private String url;
	@Value("${mysql.username}")
	private String username;
	@Value("${mysql.password}")
	private String password;
public void doPost(HttpServletRequest request, 
                    HttpServletResponse response)
                throws ServletException, IOException{
	String FoodID = request.getParameter("Id");
	int id = Integer.parseInt(request.getParameter("Id"));
	String num = request.getParameter("num");
	
	  if(request.getSession().getAttribute("user")!=null&&request.getSession().getAttribute("password")!=null) { 
			String admin = (String) request.getSession().getAttribute("user");
			String psw = (String) request.getSession().getAttribute("password");
	  try{
    	  response.setContentType("text/html;charset=UTF-8");       	    
    	  PrintWriter out = response.getWriter();
    	  
		  Class.forName("com.mysql.cj.jdbc.Driver");
          Connection conn=DriverManager.getConnection(url,username,password);
          Statement stmt = conn.createStatement();
          ResultSet rs = null;
          String sql1 = null;
          String sql2 = null;
          Boolean new1 =true;
          sql1= "select idfood from car where idUser='"+admin+"'";
          rs=stmt.executeQuery(sql1);
          while(rs.next()) {
        	  if(id==rs.getInt("idfood"))   new1=false;      	  
          }
          if(new1) sql2="insert into car values ('"+FoodID+"','"+num+"','"+admin+"',now())";
          else sql2="update car set numfood = numfood+"+num+" where idfood='"+FoodID+"' and idUser='"+admin+"'";
          if(stmt.executeUpdate(sql2)!=0)out.write('1');
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
