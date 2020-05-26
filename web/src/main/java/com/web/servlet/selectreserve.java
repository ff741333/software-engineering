package com.web.servlet;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "selectreserve", urlPatterns = { "/selectreserve" })
public class selectreserve extends HttpServlet{
	@Value("${mysql.url}")
	private String url;
	@Value("${mysql.username}")
	private String username;
	@Value("${mysql.password}")
	private String password;
public void doPost(HttpServletRequest request, 
                    HttpServletResponse response)
                throws ServletException, IOException{
	String date = request.getParameter("date");
	String desk = request.getParameter("desk");
	  try{
		  Class.forName("com.mysql.cj.jdbc.Driver");
          String reserves = "[";

          Connection conn=DriverManager.getConnection(url,username,password);
          Statement stmt = conn.createStatement();
          String sql = null;
          ResultSet rs = null;		
    	  response.setContentType("text/html;charset=UTF-8");       	    
    	  PrintWriter out = response.getWriter();
    	  if(!date.isEmpty()&&!desk.isEmpty()) 
    		  sql = "select idreserve,reserve.idDesk,name,customername,customerphonenum,reserve.idAdmin,reservetime,desk.type from reserve,desk,admin "  
      		  		+ "where admin.idAdmin=reserve.idAdmin and reserve.idDesk = desk.idDesk and DATE_FORMAT(reservetime,'%Y-%m-%d') = '"+date+"' and reserve.idDesk = " +desk ;
    	  else if(date.isEmpty()&&!desk.isEmpty())
    		  sql = "select idreserve,reserve.idDesk,name,customername,customerphonenum,reserve.idAdmin,reservetime,desk.type from reserve,desk,admin "  
    		  		+ "where admin.idAdmin=reserve.idAdmin and reserve.idDesk = desk.idDesk and reserve.idDesk = " +desk ;
    	  else if(!date.isEmpty()&&desk.isEmpty())
    		  sql = "select idreserve,reserve.idDesk,name,customername,customerphonenum,reserve.idAdmin,reservetime,desk.type from reserve,desk,admin "
    		  		+ "where admin.idAdmin=reserve.idAdmin and reserve.idDesk = desk.idDesk and DATE_FORMAT(reservetime,'%Y-%m-%d') = '" + date + "' " ;
    	  else 
    		  sql = "select idreserve,reserve.idDesk,name,customername,customerphonenum,reserve.idAdmin,reservetime,desk.type from reserve,desk,admin "
      		  		+ "where admin.idAdmin=reserve.idAdmin and reserve.idDesk = desk.idDesk " ;
    	  rs = stmt.executeQuery(sql);
    	  
    	  	  while(rs.next()){
    	  		if(rs.isFirst())reserves+="{\"ReserveID\":\""+rs.getInt("idreserve")+
    	  				"\",\"DeskID\":\""+rs.getInt("idDesk")+
    	  				"\",\"ReserveTime\":\""+rs.getTimestamp("reservetime")+
    	  				"\",\"CustomerName\":\""+rs.getString("customername")+
    	  				"\",\"CustomerPhone\":\""+rs.getString("customerphonenum")+
    	  				"\",\"AdminName\":\""+rs.getString("name")+"\"}";
    	  		else 
    	  			reserves+=",{\"ReserveID\":\""+rs.getInt("idreserve")+
	  				"\",\"DeskID\":\""+rs.getInt("idDesk")+
	  				"\",\"ReserveTime\":\""+rs.getTimestamp("reservetime")+
	  				"\",\"CustomerName\":\""+rs.getString("customername")+
	  				"\",\"CustomerPhone\":\""+rs.getString("customerphonenum")+
	  				"\",\"AdminName\":\""+rs.getString("name")+"\"}";
    	  	  }
    	  	  reserves+="]";
    	  	  out.write(reserves);
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
  
