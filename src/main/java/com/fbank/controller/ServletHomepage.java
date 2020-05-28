package com.fbank.controller;

import com.fbank.beans.I18N;
import com.fbank.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class ServletHomepage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language = request.getParameter("lang");
        if (language == null) {
            language = "en";
        }
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        I18N i18N = context.getBean(I18N.class);
        Map<String, String> map = new HashMap<>();
        String lang = i18N.messageSource().getMessage("dropdown_name", null, new Locale(language));
        String langOption1 = i18N.messageSource().getMessage("dropdow_option1", null, new Locale(language));
        String langOption2 = i18N.messageSource().getMessage("dropdown_option2", null, new Locale(language));
        String fullname = i18N.messageSource().getMessage("fullname", null, new Locale(language));
        String gender = i18N.messageSource().getMessage("gender", null, new Locale(language));
        String phone = i18N.messageSource().getMessage("phone", null, new Locale(language));
        String address = i18N.messageSource().getMessage("address", null, new Locale(language));
        String email = i18N.messageSource().getMessage("email", null, new Locale(language));
        String intro = i18N.messageSource().getMessage("intro", null, new Locale(language));
        String welcome = i18N.messageSource().getMessage("welcome", null, new Locale(language));
        map.put("lang", lang);
        map.put("langOption1", langOption1);
        map.put("langOption2",langOption2);
        map.put("fullname", fullname);
        map.put("gender", gender);
        map.put("phone", phone);
        map.put("address", address);
        map.put("email", email);
        map.put("intro", intro);
        map.put("welcome", welcome);
        request.setAttribute("mapLang", map);
        request.getRequestDispatcher("homepage.jsp").forward(request, response);
    }
}
