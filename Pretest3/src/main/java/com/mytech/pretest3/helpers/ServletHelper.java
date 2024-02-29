/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mytech.pretest3.helpers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author caova
 */
public class ServletHelper {
    public static void forward(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/" + page + ".jsp").forward(request, response);
    }
    
    public static void redirect(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        System.out.println("Redirect context path: " + request.getContextPath());
        response.sendRedirect(request.getContextPath() + "/" + page);
    }
}
