package com.servlet;

import com.java.ConnectionDB;
import com.java.user;
import java.io.IOException;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author abraham ly
 */
public class Register extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/Inscription.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String adresse = request.getParameter("adresse");
        String telephone = request.getParameter("telephone");
        String password = request.getParameter("password");
        String cpassword = request.getParameter("cpassword");
        user u = new user(nom, prenom, email, password, telephone, adresse);
        new ConnectionDB().loadDatabase();
        boolean bool = new ConnectionDB().ajouterUtilisateur(u);
        if (password.equals(cpassword)) {
            if (bool == true) {
                this.getServletContext().getRequestDispatcher("/index.html").forward(request, response);
            } else {
                this.getServletContext().getRequestDispatcher("/WEB-INF/Inscription.jsp").forward(request, response);
            }
        }
    }

}
