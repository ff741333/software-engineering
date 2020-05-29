package com.web.servlet;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "selectstore", urlPatterns = { "/selectstore" })
public class selectstore extends HttpServlet{
	@Value("${mysql.url}")
	private String url;
	@Value("${mysql.username}")
	private String username;
	@Value("${mysql.password}")
	private String password;
public void doPost(HttpServletRequest request, 
                    HttpServletResponse response)
                throws ServletException, IOException{
	String Id=request.getParameter("Id");
	String date = request.getParameter("date");
	  try{
		  Class.forName("com.mysql.cj.jdbc.Driver");
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
    	  String supplier = null;

    	  if(Id.isEmpty()&&date.isEmpty())
    	  	sql="select * from store,food where store.idfood = food.idfood order by submitdate desc";
    	  else if(Id.isEmpty()&&!date.isEmpty()) {
			  String[] datearr = date.split("~");
			  sql = "select * from store,food where  submitdate between '" + datearr[0].trim() + " 00:00:00' and '" + datearr[1].trim() + " 23:59:59' and store.idfood = food.idfood order by submitdate desc";
		  }else if(!Id.isEmpty()&&date.isEmpty())
		  	sql= "select * from store,food where store.idfood = '" + Id + "' and store.idfood = food.idfood order by submitdate desc";
    	  else {
			  String[] datearr = date.split("~");
			  sql = "select * from store,food where  store.idfood = '" + Id + "' and submitdate between '" + datearr[0].trim() + " 00:00:00' and '" + datearr[1].trim() + " 23:59:59' and store.idfood = food.idfood order by submitdate desc";
		  }
    	  rs = stmt.executeQuery(sql);
    	  	  while(rs.next()){
    	  		  name = rs.getString("name");
    	  		  price = rs.getFloat("storeprice");
    	  	      num = rs.getInt("num");
    	  	      submitdate = rs.getTimestamp("submitdate");
    	  	      instoredate = rs.getDate("instoredate");
    	  	      supplier = rs.getString("supplier");
    	  	  if(rs.isFirst())store+="{\"name\":\""+name+"\",\"supplier\":\""+supplier+"\",\"storeprice\":\""+price+"\",\"submitdate\":\""+submitdate+"\",\"num\":\""+num+"\",\"instoredate\":\""+instoredate+"\"}";
    	  	  else store+=",{\"name\":\""+name+"\",\"supplier\":\""+supplier+"\",\"storeprice\":\""+price+"\",\"submitdate\":\""+submitdate+"\",\"num\":\""+num+"\",\"instoredate\":\""+instoredate+"\"}";
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
	  
  
  
