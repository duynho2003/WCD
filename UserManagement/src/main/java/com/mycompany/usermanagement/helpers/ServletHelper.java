/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.usermanagement.helpers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author Admin
 */
    public class ServletHelper {
        public static void forward(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
                request.getRequestDispatcher("/WEB-INF/" + page + ".jsp").forward(request, response);
        }
    }
