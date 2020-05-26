package com.web.filter;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
    
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
@WebFilter(filterName = "UsersFilter", urlPatterns = "*.html")
public class UsersFilter implements Filter {
    
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
        Object o = session.getAttribute("user");     
        Object p =session.getAttribute("password");
        String url = req.getRequestURI();
        if(!url.endsWith("testRegister.html")) {
        if(url.endsWith("testLogin.html")&&o != null&&p!=null)
        	res.sendRedirect("logout");
        else if ((o == null||p==null)  && !url.endsWith("testLogin.html")    
            )   {   
            res.sendRedirect("testLogin.html");
        } else  {
            chain.doFilter(request, response);     
            res.setHeader("Cache-Control","no-store");  
            res.setDateHeader("Expires",0);        
            res.setHeader("Pragma","no-cache");      
            res.flushBuffer(); 
        	}
        }
        else
        {
        	  chain.doFilter(request, response);     
              res.setHeader("Cache-Control","no-store");  
              res.setDateHeader("Expires",0);        
              res.setHeader("Pragma","no-cache");      
              res.flushBuffer();  
        }
             
    }     
    
    public void init(FilterConfig filterConfig) throws ServletException {     
        // TODO Auto-generated method stub     
             
    }     
    
}    