package com.nastichichika.servlets;

import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.nastichichika.dao.AbonentDAO;
import com.nastichichika.model.Abonent;
import com.nastichichika.utils.Json_for_servlet;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    static final Logger logger = LogManager.getLogManager().getLogger(LoginServlet.class.getName());
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        String path = request.getServletPath();
        response.addHeader(path, "true");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setContentType("text/html");

        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        for(Cookie c: cookies) {
            if("LOGIN".equals(c.getName())) {
                cookie = c;
                break;
            }
        }
        assert cookie != null;
        String login = cookie.getValue();
        for(Cookie c: cookies) {
            if("PASSWORD".equals(c.getName())) {
                cookie = c;
                break;
            }
        }
        String password = cookie.getValue();
        Abonent val;
        if((val = AbonentDAO.validate(login, password)) != null){
            HttpSession session = request.getSession();
            session.setAttribute("login", login);
            cookie.setHttpOnly(false);
            response.addCookie(cookie);
            Json_for_servlet.makeJsonAnswer(val, response);
            if(login.equals("admin")) {
                new AdminServlet().doPost(request, response);
            }
            else {
                new UserServlet().doPost(request, response);
            }
        }

    }

}