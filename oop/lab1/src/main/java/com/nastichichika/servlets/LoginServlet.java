package com.nastichichika.servlets;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nastichichika.dao.AbonentDAO;

@WebServlet(name = "login-servlet", value = "/login")
public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        String n=request.getParameter("username");
        String p=request.getParameter("userpass");
        if(AbonentDAO.validate(n, p)){
            HttpSession session=request.getSession();
            session.setAttribute("name",n);
            if(n.equals("admin")) {
                RequestDispatcher rd=request.getRequestDispatcher("admin");
                rd.forward(request,response);
            } else {
                RequestDispatcher rd=request.getRequestDispatcher("userServlet");
                rd.forward(request,response);
            }
        }
        else{
            request.setAttribute("errorMessage","Sorry username or password error");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

}