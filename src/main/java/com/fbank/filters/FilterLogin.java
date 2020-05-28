package com.fbank.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilterLogin implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String loginURI = request.getContextPath()+"/dang-nhap";
        boolean loginRequest = request.getRequestURI().equals(loginURI);
        if(request.getSession().getAttribute("userSession")!=null||loginRequest){
            chain.doFilter(req, resp);
        }else{
            request.setAttribute("errMess","Bạn phải đăng nhập trước !!!");
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
