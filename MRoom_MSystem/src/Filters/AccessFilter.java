package Filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccessFilter implements Filter {
    //只有登录用户才可以查看除注册登录页面以外的其他页面
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain filterChain) throws IOException, ServletException{
        HttpServletRequest request = (HttpServletRequest)arg0;
        HttpServletResponse response = (HttpServletResponse)arg1;
        HttpSession session = request.getSession();
        //过滤器有错误，已经解决
        if(session.getAttribute("user_id")== null && request.getRequestURI().indexOf("login.jsp")==-1 && request.getRequestURI().indexOf("register.jsp")==-1){
            response.sendRedirect("login.jsp");
            return ;
        }
        filterChain.doFilter(arg0, arg1);
    }
}
