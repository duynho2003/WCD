/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.helloattributes;

import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 *
 * @author Admin
 */
@WebServlet(name = "NextServlet", urlPatterns = {"/next"})
public class NextServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Request info Method: " + request.getMethod());
        System.out.println("Request info ContentType: " + request.getContentType());
        System.out.println("Request info ContextType: " + request.getContextPath());
        System.out.println("Request info ServletPath: " + request.getServletPath());
        System.out.println("Request info ServertName: " + request.getServerName());
        System.out.println("Request info Method: " + request.getParameter("userid"));
        
        Enumeration<String> keys = request.getHeaderNames();
        
        while(keys.hasMoreElements()) {
            String key = keys.nextElement();
            System.out.println("Header key:" + key + " value:" + request.getHeader(key));
        }
        
        ServletContext context = getServletContext();
        String companyName = (String) context.getAttribute("company");
        
        String searchText = request.getParameter("search");
        if (searchText != null && !"".equals(searchText)) {
            response.sendRedirect("https://www.google.com/search?q=" + searchText);
            return;
        }
        
        response.addHeader("companyName", companyName);
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NextServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Company name: " + companyName + "</h1>");
            out.println("<h1>Servlet NextServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
