/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author abraham ly
 */
public class Edit extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/Connection.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        new ConnectionDB().loadDatabase();
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String email = request.getParameter("email");
            String adresse = request.getParameter("adresse");
            String telephone = request.getParameter("telephone");
            String password = request.getParameter("password");
            String cpassword = request.getParameter("cpassword");
            user u = new user(id, nom, prenom, email, password, telephone, adresse);
            boolean bool = new ConnectionDB().updateUser(u);
            if (password.equals(cpassword)) {
                if (bool == true) {
                    new ConnectionDB().loadDatabase();
                    List<user> users = new ConnectionDB().recupererUtilisateurs();
                    session.setAttribute("users", users);
                    this.getServletContext().getRequestDispatcher("/WEB-INF/Connection.jsp").forward(request, response);
                } else {
                    response.setContentType("text/html;charset=UTF-8");
                    try (PrintWriter out = response.getWriter()) {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Servlet Delete</title>");
                        out.println("</head>");
                        out.println("<body>");
                       out.println("<h1> Non reussie</h1>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Edit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
