/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.usermanagement.controllers;

import com.mycompany.usermanagement.helpers.ServletHelper;
import com.mycompany.usermanagement.models.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Date;

/**
 *
 * @author Admin
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletHelper.forward(request, response, "login");

    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println("email: " + email + "password: " + password);
        User loginUser = null;
        for (User user : User.USERDB) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                loginUser = user;
            }
            
            if (loginUser == null) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                request.setAttribute("error", "Invalid email or password");
                ServletHelper.forward(request, response, "login");
                return;
            }
            
            //loginUser.getLoginDates().add(new Date());
            loginUser.addLoginDate(new Date());
            
            HttpSession session = request.getSession();
            session.setAttribute("user", loginUser);
            
            response.sendRedirect("home");
        }
    }
    
}
