package com.demo;
import java.io.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "selectstore", urlPatterns = { "/selectstore" })
public class selectstore extends HttpServlet{
public void doPost(HttpServletRequest request, 
                    HttpServletResponse response)
                throws ServletException, IOException{
	String Id=request.getParameter("Id");
	  try{
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  String url="jdbc:mysql://localhost:3306/netcar?useSSL=false&serverTimezone=UTC";
          String username="root";
          String password=",.Fgh123.";
          String store = "[";
          Connection conn=DriverManager.getConnection(url,username,password);
          Statement stmt = conn.createStatement();
          String sql = null;
          ResultSet rs = null;		
    	  response.setContentType("text/html;charset=UTF-8");       	    
    	  PrintWriter out = response.getWriter();
    	  Timestamp submitdate = null;
    	  Date instoredate = null;
    	  float price = 0;
    	  int num = 0;
    	  String name = null;
          sql= "select * from store,food where store.idfood = '" + Id + "' and store.idfood = food.idfood order by submitdate desc";
    	  rs = stmt.executeQuery(sql);
    	  	  while(rs.next()){
    	  		  name = rs.getString("name");
    	  		  price = rs.getFloat("storeprice");
    	  	      num = rs.getInt("num");
    	  	      submitdate = rs.getTimestamp("submitdate");
    	  	      instoredate = rs.getDate("instoredate");
    	  	  if(rs.isFirst())store+="{\"name\":\""+name+"\",\"storeprice\":\""+price+"\",\"submitdate\":\""+submitdate+"\",\"num\":\""+num+"\",\"instoredate\":\""+instoredate+"\"}";
    	  	  else store+=",{\"name\":\""+name+"\",\"storeprice\":\""+price+"\",\"submitdate\":\""+submitdate+"\",\"num\":\""+num+"\",\"instoredate\":\""+instoredate+"\"}";
    	  	  }
    	    store+="]";
    	  out.write(store);
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
	  
  
  
