/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mytech.shopmanagement.controllers;

import com.mytech.shopmanagement.daos.ProductDao;
import com.mytech.shopmanagement.helpers.ServletHelper;
import com.mytech.shopmanagement.models.Product;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author trungtruong
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/products/*"})
public class ProductServlet extends HttpServlet {
    
    private ProductDao productDao = new ProductDao();

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        System.out.println("action: " + action);
        
        if (action !=null && action.contains("/new")) {
            ServletHelper.forward(request, response, "add_product");
            return;
        }
        
        List<Product> listProducts = productDao.getProduct();
        request.setAttribute("listProducts", listProducts);
        ServletHelper.forward(request, response, "products");
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        System.out.println("action" + action);
        
        if (action !=null && action.contains("/save")) {
            String name = request.getParameter("name");
            String price = request.getParameter("price");
            Product product = new Product(name, Double.parseDouble(price));
            productDao.addProduct(product);
            ServletHelper.forward(request, response, "add_product");
            return;
        }
        
        ServletHelper.redirect(request, response, "products");
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