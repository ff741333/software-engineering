package com.web.filter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

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
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;     
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "PrivilegesFilter", urlPatterns =
		{"/indexadmin.html","/indexuser.html","/indexsupplier.html","/pageforadmin/*","/pageforsupplier/*","/page/*"
		})

public class PrivilegesFilter implements Filter {
	@Value("${mysql.url}")
	private String url;
	@Value("${mysql.username}")
	private String username;
	@Value("${mysql.password}")
	private String password;

    
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
        else if("".equals(user)||"".equals(psw)) {
            chain.doFilter(request, response);     
            res.setHeader("Cache-Control","no-store");  
            res.setDateHeader("Expires",0);        
            res.setHeader("Pragma","no-cache");      
            res.flushBuffer(); 
    	}
    	else {
    		 try{
    			  Class.forName("com.mysql.cj.jdbc.Driver");
    	          Connection conn=DriverManager.getConnection(url,username,password);
    	          Statement stmt = conn.createStatement();
    	          String sql = "select * from user where idUser = '" + user + "' and psw='" + psw + "'";
				  ResultSet rs = stmt.executeQuery(sql);
				  if(rs.next()!=false) {
    	    	  String status = rs.getString("identity");

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
    	    		  res.sendRedirect("testLogin.html");

    	    	  rs.close();
    	          }
    	          else 
    	      		res.sendRedirect("testLogin.html");
    	  		
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