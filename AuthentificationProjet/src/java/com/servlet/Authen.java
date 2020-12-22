/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet;

import com.java.ConnectionDB;
import static com.java.ConnectionDB.hashFunction;
import com.java.user;
import java.io.IOException;
import java.io.PrintWriter;
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
public class Authen extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.equals("/index.html")) {
           
        } else {
            this.getServletContext().getRequestDispatcher("/index.html").forward(request, response);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        new ConnectionDB().loadDatabase();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        user u = new user(email, password);
        if (request.getParameter("hdnbt").equals("register") ) {
            this.getServletContext().getRequestDispatcher("/WEB-INF/Inscription.jsp").forward(request, response);
        } else {
            boolean bool = new ConnectionDB().Connexion(u);
            if (bool == true) {
                new ConnectionDB().loadDatabase();
                List<user> users = new ConnectionDB().recupererUtilisateurs();
                session.setAttribute("users", users);
                this.getServletContext().getRequestDispatcher("/WEB-INF/Connection.jsp").forward(request, response);
            }
        }

    }

}
