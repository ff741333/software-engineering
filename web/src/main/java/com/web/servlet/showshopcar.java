package com.web.servlet;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "showshopcar", urlPatterns = { "/showshopcar" })
public class showshopcar extends HttpServlet{
	@Value("${mysql.url}")
	private String url;
	@Value("${mysql.username}")
	private String username;
	@Value("${mysql.password}")
	private String password;
public void doPost(HttpServletRequest request, 
                    HttpServletResponse response)
                throws ServletException, IOException{
	  try{
		  Class.forName("com.mysql.cj.jdbc.Driver");
          String foods = "{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":[";
          Connection conn=DriverManager.getConnection(url,username,password);
          Statement stmt = conn.createStatement();
          String sql = null;
          ResultSet rs = null;		
    	  response.setContentType("text/html;charset=UTF-8");       	    
    	  PrintWriter out = response.getWriter();
    	  String name = null;
    	  float price = 0;
    	  int num =0;    
    	  String admin=null;
    	  admin = (String) request.getSession().getAttribute("user");
    	  if(!admin.isEmpty()) {
    		  sql = "select * from car,food where car.idfood=food.idfood and idUser='"+admin+"'";
    	  
    	  
    	  rs = stmt.executeQuery(sql);
    	  	  while(rs.next()){
    	  		  name = rs.getString("food.name");
    	  		  price = rs.getFloat("price");
    	  		  num = rs.getInt("numfood");
    	  	  if(rs.isFirst())foods+="{\"FoodsName\":\""+name+"\",\"XPrice\":\""+price+"\",\"num\":\""+num+"\"}";
    	  	  else foods+=",{\"FoodsName\":\""+name+"\",\"XPrice\":\""+price+"\",\"num\":\""+num+"\"}";
    	  	  }
    	    foods+="]}";
    	  }
    	  out.write(foods);
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
	this.doPost(request, response);
}

}
	  
  
  
