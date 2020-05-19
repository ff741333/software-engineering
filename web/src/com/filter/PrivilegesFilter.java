package com.filter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.Filter;     
import javax.servlet.FilterChain;     
import javax.servlet.FilterConfig;     
import javax.servlet.ServletException;     
import javax.servlet.ServletRequest;     
import javax.servlet.ServletResponse;     
import javax.servlet.http.HttpServletRequest;     
import javax.servlet.http.HttpServletResponse;     
import javax.servlet.http.HttpSession;     

public class PrivilegesFilter implements Filter {     
    
    public void destroy() {     
        // TODO Auto-generated method stub     
             
    }     
    
    public void doFilter(ServletRequest request, ServletResponse response,     
            FilterChain chain) throws IOException, ServletException {     
        request.setCharacterEncoding("UTF-8");     
        response.setCharacterEncoding("UTF-8");     
        HttpServletRequest req = (HttpServletRequest) request;      
        HttpServletResponse res = (HttpServletResponse) response;        
        HttpSession session = req.getSession();     
        String user = (String) session.getAttribute("user");     
        String psw = (String) session.getAttribute("password");
        String url1 = req.getRequestURI(); 
        if(!url1.endsWith(".html"))
        	 chain.doFilter(request, response); 
        else if(user.isEmpty()||psw.isEmpty()) {
            chain.doFilter(request, response);     
            res.setHeader("Cache-Control","no-store");  
            res.setDateHeader("Expires",0);        
            res.setHeader("Pragma","no-cache");      
            res.flushBuffer(); 
    	}
    	else {
    		 try{
    			  Class.forName("com.mysql.cj.jdbc.Driver");
    			  String url="jdbc:mysql://localhost:3306/netcar?useSSL=false&serverTimezone=UTC";
    	          String username="root";
    	          String password=",.Fgh123.";
    	          Connection conn=DriverManager.getConnection(url,username,password);
    	          Statement stmt = conn.createStatement();
    	          String sql = "select * from user where idUser = '" + user + "' and psw='" + psw + "'";
    	          if(stmt.executeQuery(sql)!=null) {
    	          ResultSet rs = stmt.executeQuery(sql);   	    
    	    	  String status = null;
    	    	  while(rs.next()) {
    	    		  status = rs.getString("identity");
    	    	  }
    	    	  if((status.equals("admin")&&(url1.endsWith("indexadmin.html")
    	    			          ||(url1.contains("/pageforadmin/")&&url1.endsWith(".html"))))
    	    			  ||(status.equals("customer")&&(url1.endsWith("indexuser.html")
    	    					  ||(url1.contains("/page/")&&url1.endsWith(".html"))))
    	    			  ||(status.equals("supplier")&&(url1.endsWith("indexsupplier.html")
    	    	    			  ||(url1.contains("/pageforsupplier/")&&url1.endsWith(".html"))))
    	    			  ) {
    	            chain.doFilter(request, response);     
    	            res.setHeader("Cache-Control","no-store");  
    	            res.setDateHeader("Expires",0);        
    	            res.setHeader("Pragma","no-cache");      
    	            res.flushBuffer(); 
    	    	  }
    	    	  else
    	    		  res.sendRedirect("/web/testLogin.html");

    	    	  rs.close();
    	          }
    	          else 
    	      		res.sendRedirect("/web/testLogin.html");
    	  		
    	          stmt.close();
    	          conn.close(); 
    		  }catch (ClassNotFoundException e) {
    			e.printStackTrace();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}

  
    }     
    
    public void init(FilterConfig filterConfig) throws ServletException {     
        // TODO Auto-generated method stub     
             
    }     
    
}    