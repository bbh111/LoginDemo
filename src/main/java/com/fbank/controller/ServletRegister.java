package com.fbank.controller;

import com.fbank.entities.Account;
import com.fbank.entities.AccountInformation;
import com.fbank.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletRegister extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        AccountService service = context.getBean(AccountService.class);
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String cfPassword = request.getParameter("cfpassword");
        String fullname = request.getParameter("cusName");
        Integer gender = Integer.parseInt(request.getParameter("gender"));
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String introduction = request.getParameter("introduction");
        AccountInformation accountInformation = new AccountInformation();
        if (!password.equals(cfPassword)) {
            request.setAttribute("errMess", "Mật khẩu không khớp !!!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            if (service.getAccountByUsername(username) == null) {
                Account account = new Account();
                account.setTimesLoginFailed(0);
                account.setStatus(Account.Status.ACTIVED.getValue());
                account.setPassword(password);
                account.setUsername(username);
                accountInformation.setAddress(address);
                accountInformation.setEmail(username);
                accountInformation.setFullName(fullname);
                accountInformation.setGender(gender);
                accountInformation.setPhone(phone);
                accountInformation.setIntroduction(introduction);
                account.setAccountInformation(accountInformation);
                service.createNewAccount(account);
                request.getSession().setAttribute("userSession",account);
            }
        }
        request.getRequestDispatcher("trang-chu").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
}
