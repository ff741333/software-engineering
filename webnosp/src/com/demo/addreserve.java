package com.demo;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "addreserve", urlPatterns = { "/addreserve" })
public class addreserve extends HttpServlet{
public void doPost(HttpServletRequest request, 
                    HttpServletResponse response)
                throws ServletException, IOException{
	String customername = request.getParameter("customername");
	String customerphonenum = request.getParameter("customerphonenum");
	String date1 = request.getParameter("date1");
	String date2 = request.getParameter("date2");
	String date = date1 + " " + date2 +":0";
	String desk = request.getParameter("desk");
	
	  if(request.getSession().getAttribute("user")!=null&&request.getSession().getAttribute("password")!=null) { 
			String admin = (String) request.getSession().getAttribute("user");
			String psw = (String) request.getSession().getAttribute("password");
	  try{
    	  response.setContentType("text/html;charset=UTF-8");       	    
    	  PrintWriter out = response.getWriter();
    	  
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  String url="jdbc:mysql://localhost:3306/netcar?useSSL=false&serverTimezone=GMT%2B8";
          String username="root";
          String password=",.Fgh123.";
          Connection conn=DriverManager.getConnection(url,username,password);
          Statement stmt = conn.createStatement();
          
          String sql1 = null;
          String sql2 = null;
          String sql3 = null;
          sql3 = "select * from user where idUser = '" + admin + "' and psw = '" + psw +"'";
          if(stmt.executeQuery(sql3).next()) {
          sql1 = "select * from reserve where idDesk=" + desk + " and reservetime='" + date + "'";
          if(!stmt.executeQuery(sql1).next()) {
          int flag = 0;
    	  sql2 = "insert into reserve " + 
    	  		"set idDesk=" + desk + ",idAdmin=(select idAdmin from admin where idUser ='" + admin + "'),reservetime='" + date + 
    	  		"' ,customername='" + customername + "',customerphonenum='" + customerphonenum + "'";
    	  flag =stmt.executeUpdate(sql2);
 
    	  if(flag!=0) out.write(customername + "预定成功  时间为" + date1 + date2);
    	  else out.write(customername + "预定失败");

    	  }
          else 
    		  out.write("该时间有人预定了");
          }
          else 
    		  out.write("请先登录");
          
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
		  out.write("���ȵ�¼^-^");
    	  out.flush();
    	  out.close();
	  }
	  
  }
}
