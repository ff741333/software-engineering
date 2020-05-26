package com.web.servlet;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "showpassword", urlPatterns = { "/showpassword" })
public class showpassword extends HttpServlet{
	@Value("${mysql.url}")
	private String url;
	@Value("${mysql.username}")
	private String username;
	@Value("${mysql.password}")
	private String password;
public void doPost(HttpServletRequest request, 
                    HttpServletResponse response)
                throws ServletException, IOException{
	String idUser = request.getParameter("idUser");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String psw = "";
			Connection conn = DriverManager.getConnection(url, username, password);
			Statement stmt = conn.createStatement();
			String sql = null;
			ResultSet rs = null;
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			int id = 0;
			sql = "select * from user where idUser='" + idUser + "'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				psw = rs.getString("psw");
			}
			out.write(psw);
			out.flush();
			out.close();
			rs.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

}
	  
  }
  
