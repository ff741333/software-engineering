package com.demo;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "sendorder", urlPatterns = { "/sendorder" })
public class sendorder extends HttpServlet{
public void doPost(HttpServletRequest request, 
                    HttpServletResponse response)
                throws ServletException, IOException{
	  try{
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  String url="jdbc:mysql://localhost:3306/netcar?useSSL=false&serverTimezone=UTC";
          String username="root";
          String password=",.Fgh123.";       
          Connection conn=DriverManager.getConnection(url,username,password);
          Statement stmt = conn.createStatement();
          String sql = null;

          ResultSet rs1 = null;	
          int rs= 0;

          int i=0;
          float summoney = 0;
          String idexpense = "0";
    	  response.setContentType("text/html;charset=UTF-8");       	    
    	  PrintWriter out = response.getWriter();
    	  
    	  
    	  String admin=null;
    	  admin = (String) request.getSession().getAttribute("user");
    	  if(!admin.isEmpty()) {
        	  sql = "insert into expense set idUser='"+ admin +"',status='配送中',idAdmin='1',date = now()";
              rs = stmt.executeUpdate(sql);
              sql = "SELECT LAST_INSERT_ID() from expense";
              rs1 = stmt.executeQuery(sql);
              while(rs1.next()) {
                  idexpense = rs1.getString("LAST_INSERT_ID()");
              }
    		  sql = "select * from car,food where food.idfood=car.idfood and idUser ='"+admin+"'";
              rs1 = stmt.executeQuery(sql);
              while(rs1.next()) {
            	//  num[i]=rs1.getInt("numfood");
            	//  idfood[i]=rs1.getInt("idfood");
            	  i++;
            	  summoney+=rs1.getInt("numfood")*rs1.getFloat("price");
            	  
              }
              int[] num = new int[i];
              int[] idfood =new int[i];
              rs1.first();
              num[0]=rs1.getInt("numfood");
              idfood[0]=rs1.getInt("idfood");
              i=1;
              while(rs1.next()) {
            	  
              	  num[i]=rs1.getInt("numfood");
              	  idfood[i]=rs1.getInt("idfood");
              	  i++;
              }
              
              while(i!=0) {
            	  i--;
            	  sql = "insert into consumption values ('"+ idexpense +"','" + idfood[i] +"' , '"+ num[i] +"' ,'1' ,'"+ admin +"',now() )";
                  stmt.executeUpdate(sql);
              }
              sql = "update expense set cost = '"+summoney+"' where idexpense = '" + idexpense+"'";
              stmt.executeUpdate(sql);
              sql = "delete  from car where idUser = '"+admin+"'";
              rs=stmt.executeUpdate(sql);
              if(rs!=0) out.write("1");
              else out.write("0");
    	  }

    	  out.flush();
    	  out.close();
    	  rs1.close();
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
	  
  
  
