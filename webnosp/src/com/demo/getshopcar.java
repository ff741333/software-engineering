package com.demo;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@WebServlet(name = "getshopcar", urlPatterns = { "/getshopcar" })
public class getshopcar extends HttpServlet{
public void doPost(HttpServletRequest request, 
                    HttpServletResponse response)
                throws ServletException, IOException{
	  String foods = request.getParameter("foods");
	  JSONArray foodArray = JSONArray.fromObject(foods);
	  String money = request.getParameter("money");
	  JSONObject moneyObject = JSONObject.fromObject(money);
	  int i=0;
	  int rs = 0;
	  String idexpense = "0";
	  try{
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  String url="jdbc:mysql://localhost:3306/netcar?useSSL=false&serverTimezone=UTC";
          String username="root";
          String password=",.Fgh123.";
          Connection conn=DriverManager.getConnection(url,username,password);
          Statement stmt = conn.createStatement();
          ResultSet rs1 = null;
          String sql = "";
          sql = "insert into expense set idDesk='"+ moneyObject.getString("idDesk") +"',cost='"+ moneyObject.getString("cost") +"',idUser='"+ moneyObject.getString("idUser") +"',idAdmin='"+ moneyObject.getString("idAdmin") +"',date= now()";
          rs = stmt.executeUpdate(sql);
          sql = "SELECT LAST_INSERT_ID() from expense";
          rs1 = stmt.executeQuery(sql);
          while(rs1.next()) {
              idexpense = rs1.getString("LAST_INSERT_ID()");
          }
          for(i=0;i<foodArray.size();i++) {
        	  sql = "insert into consumption values ('"+ idexpense +"','"+ foodArray.getJSONObject(i).getString("idFood") +"' , '"+ foodArray.getJSONObject(i).getString("numFood") +"' ,'"+ foodArray.getJSONObject(i).getString("idAdmin") +"' ,'"+ foodArray.getJSONObject(i).getString("idDesk") +"' ,'"+ foodArray.getJSONObject(i).getString("idUser") +"',now() )";
              rs = stmt.executeUpdate(sql);
          }
    	  response.setContentType("text/html;charset=UTF-8");       	    
    	  PrintWriter out = response.getWriter();
    	  if(rs!=0)out.write('1');
    	  else out.write('0');
          stmt.close();
          conn.close();
	  }catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}

  }
  }

