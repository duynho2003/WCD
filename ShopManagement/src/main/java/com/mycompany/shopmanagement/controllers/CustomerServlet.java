/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.shopmanagement.controllers;

import com.mycompany.shopmanagement.daos.CountryDAO;
import com.mycompany.shopmanagement.daos.CustomerDAO;
import com.mycompany.shopmanagement.helpers.ServletHelper;
import com.mycompany.shopmanagement.models.Country;
import com.mycompany.shopmanagement.models.Customer;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CustomerServlet", urlPatterns = {"/customers/*"})
public class CustomerServlet extends HttpServlet {

    private CustomerDAO customerDao = new CustomerDAO();
    private CountryDAO countryDao = new CountryDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        System.out.println("action: " + action);
        
        List<Customer> listCustomers = customerDao.getCustomers();
        List<Country> listCountries = countryDao.getCountries();
        
        request.setAttribute("listCustomers", listCustomers);
        request.setAttribute("listCountries", listCountries);
        
        if (action != null && action.contains("/new")) {
            ServletHelper.forward(request, response, "add_customer");
            return;
        } else if (action != null && action.contains("/edit")) {
            String id = request.getParameter("id");
            System.out.println("Edit customer id: " + id);
            Customer customer = customerDao.getCustomer(Integer.parseInt(id));
            request.setAttribute("customer", customer);
            ServletHelper.forward(request, response, "update_customer");
            return;
        } else if (action != null && action.contains("/delete")) {
            ServletHelper.forward(request, response, "delete_customer");
            return;
        } else if (action != null && action.contains("/search")){
            String searchText= request.getParameter("searchText");
            System.out.println("SearchText: " + searchText);
            listCustomers = customerDao.getCustomers(searchText);
            
            request.setAttribute("listCustomers", listCustomers);
            ServletHelper.forward(request, response, "customers");
            return;
        }
        
        ServletHelper.forward(request, response, "customers");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        System.out.println("action: " + action);
        
        if (action != null && action.equals("/save")) {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String country = request.getParameter("country");
            Customer customer = new Customer(name, email, country);
            System.out.println("New customer: " + customer.toString());
            customerDao.saveCustomer(customer);
            //response.sendRedirect("/new");
            ServletHelper.redirect(request, response, "customers/new");
        } else if (action != null && action.equals("/update")) {
            String idString = request.getParameter("id");
            int id = Integer.parseInt(idString);
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String country = request.getParameter("country");
            Customer customer = new Customer(id, name, email, country);
            System.out.println("Update customer: " + customer.toString());
            customerDao.updateCustomer(customer);
            //response.sendRedirect("/new");
            ServletHelper.redirect(request, response, "customers/new");
        }
    }
}
