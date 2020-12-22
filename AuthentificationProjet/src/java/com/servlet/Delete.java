package com.servlet;

import com.java.ConnectionDB;
import com.java.user;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Delete extends HttpServlet {

    /* protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          TODO output your page here. You may use following sample code. 
           out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Delete</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Delete at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }*/
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/Connection.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            int id = Integer.parseInt(request.getParameter("id"));
            user u = new user(id);
            String delete = request.getParameter("delete");
            new ConnectionDB().loadDatabase();
            boolean b = new ConnectionDB().deleteUser(u);
            if (b == true) {
                new ConnectionDB().loadDatabase();
                List<user> users = new ConnectionDB().recupererUtilisateurs();
                session.setAttribute("users", users);
                this.getServletContext().getRequestDispatcher("/WEB-INF/Connection.jsp").forward(request, response);
            } else {
                this.getServletContext().getRequestDispatcher("/WEB-INF/Connection.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Delete.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
/* String action = request.getServletPath();
     response.setContentType("text/html;charset=UTF-8");
     try (PrintWriter out = response.getWriter()) {
     out.println("<!DOCTYPE html>");
     out.println("<html>");
     out.println("<head>");
     out.println("<title>Servlet Delete</title>");
     out.println("</head>");
     out.println("<body>");
     out.println("<h1> " + action + "</h1>");
     out.println("<h1> " + request.getParameter("id") + "</h1>");
     out.println("</body>");
     out.println("</html>");
     }
     private void deleteUser(HttpServletRequest request, HttpServletResponse response)
     throws SQLException, IOException, ServletException {
     int id = Integer.parseInt(request.getParameter("id"));
     user u = new user(id);
     new ConnectionDB().deleteUser(u);
     this.getServletContext().getRequestDispatcher("/WEB-INF/Connection.jsp").forward(request, response);
     }*/
