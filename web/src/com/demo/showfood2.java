package com.demo;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "showfood2", urlPatterns = { "/showfood2" })
public class showfood2 extends HttpServlet{
public void doPost(HttpServletRequest request, 
                    HttpServletResponse response)
                throws ServletException, IOException{
	String FoodName=null;
	FoodName=request.getParameter("key[FoodName]");
	  try{
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  String url="jdbc:mysql://localhost:3306/netcar?useSSL=false&serverTimezone=UTC";
          String username="root";
          String password=",.Fgh123.";
          String foods = "{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":[";
          Connection conn=DriverManager.getConnection(url,username,password);
          Statement stmt = conn.createStatement();
          String sql = null;
          ResultSet rs = null;		
    	  response.setContentType("text/html;charset=UTF-8");       	    
    	  PrintWriter out = response.getWriter();
    	  String name = null;
    	  float price = 0;
    	  String type = null;
    	  String img =null;
    	  int id =0;
    	  int num = 0;
    	  if("".equals(FoodName)||FoodName==null) //判断查询框是否有字
    		  sql = "select * from food";
    	  else sql= "select * from food where name like '%" + FoodName + "%'";
    	  rs = stmt.executeQuery(sql);
    	  	  while(rs.next()){
    	  		  name = rs.getString("name");
    	  		  price = rs.getFloat("price");
    	  		  type = rs.getString("type");
    	  	      id = rs.getInt("idfood");
    	  	      img = rs.getString("imgurl");
    	  	      num = rs.getInt("num");
    	  	  if(rs.isFirst())foods+="{\"FoodsID\":\""+id+"\",\"FoodsName\":\""+name+"\",\"XPrice\":\""+price+"\",\"FoodsImg\":\""+img+"\",\"Xnum\":\""+num+"\"}";
    	  	  else foods+=",{\"FoodsID\":\""+id+"\",\"FoodsName\":\""+name+"\",\"XPrice\":\""+price+"\",\"FoodsImg\":\""+img+"\",\"Xnum\":\""+num+"\"}";
    	  	  }
    	    foods+="]}";
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
	  
  
  
