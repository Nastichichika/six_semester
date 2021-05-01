package com.nastichichika.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "block-servlet", value = "/block", urlPatterns = { "/deleteProduct" })
public class BlockUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("unpaidList.jsp").include(request, response);
        HttpSession session = request.getSession(false);
        if(session != null){
            out.print("Please login first");
            request.getRequestDispatcher("unpaidList.jsp").include(request, response);
        }
        else{
            out.print("Please login first");
            request.getRequestDispatcher("unpaidList.jsp").include(request, response);
        }
        out.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}