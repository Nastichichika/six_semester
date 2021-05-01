package com.nastichichika.servlets;

import com.nastichichika.dao.AbonentDAO;
import com.nastichichika.model.Abonent;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "admin-servlet", value = "/admin")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("login.jsp").include(request, response);
        HttpSession session = request.getSession(false);
        if(session != null){
            List<Abonent> list = null;
            try {
                list = AbonentDAO.listUnpaidAccount();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            request.setAttribute("userList", list);

            /*List<Abonent> notlist = null;
            try {
                notlist = AbonentDAO.listUnwordAccount();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            request.setAttribute("notuserList", notlist);

            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("unpaidList.jsp");
            dispatcher.forward(request, response);*/
        }
        else{
            out.print("Please login first");
            request.getRequestDispatcher("login.jsp").include(request, response);
        }
        out.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}