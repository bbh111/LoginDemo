package com.fbank.controller;

import com.fbank.entities.Account;
import com.fbank.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ServletLogin extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        AccountService accountService = context.getBean(AccountService.class);
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Account account = accountService.getAccountByUsernameAndPassword(username, password);
        if (account != null) {
            if (account.getStatus() == Account.Status.DEACTIVED.getValue()) {
                if (account.getTimeBlocked().before(new Date(System.currentTimeMillis()))) {
                    account.setStatus(Account.Status.ACTIVED.getValue());
                    account.setTimesLoginFailed(0);
                    accountService.updateUser(account);
                    request.setAttribute("acc", account);
                    request.getSession().setAttribute("userSession", account);
                    request.getRequestDispatcher("trang-chu").forward(request, response);
                } else {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
                    LocalDateTime timeBlock = LocalDateTime.parse(account.getTimeBlocked().toString(), formatter);
                    LocalDateTime currentTime = LocalDateTime.now();
                    long timeRemain = java.time.Duration.between(currentTime, timeBlock).toMinutes();
                    request.setAttribute("errMess", "Tài khoản đang bị khóa vui lòng đợi trong " + timeRemain + " phút");
                    account.setStatus(Account.Status.DEACTIVED.getValue());
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("acc", account);
                request.getSession().setAttribute("userSession", account);
                request.getRequestDispatcher("trang-chu").forward(request, response);
            }
        } else {
            if (checkLoginFailed(username, accountService)) {
                request.setAttribute("lockMess", (accountService.getAccountByUsername(username).getTimeBlocked()).toString());
            }
            request.setAttribute("errMess", "Tài khoản hoặc mật khẩu không chính xác ");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    public boolean checkLoginFailed(String username, AccountService service) {
        Account account = service.getAccountByUsername(username);
        boolean flag = false;
        if (account != null) {
            int timeLoginFailed = account.getTimesLoginFailed();
            account.setTimesLoginFailed(timeLoginFailed += 1);
            if (timeLoginFailed == 5) {
                account.setStatus(Account.Status.DEACTIVED.getValue());
                LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(5);
                account.setTimeBlocked(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()));
                flag = true;
            }
            service.updateUser(account);
        }
        return flag;
    }
}
