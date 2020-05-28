package com.fbank.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilterHomepage implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getSession().getAttribute("userSession") != null) {
            chain.doFilter(req, resp);
        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
