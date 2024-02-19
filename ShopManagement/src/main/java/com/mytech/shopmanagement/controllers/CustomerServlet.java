/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mytech.shopmanagement.controllers;

import com.mytech.shopmanagement.daos.CountryDao;
import com.mytech.shopmanagement.daos.CustomerDao;
import com.mytech.shopmanagement.helpers.ServletHelper;
import com.mytech.shopmanagement.models.Country;
import com.mytech.shopmanagement.models.Customer;
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
@WebServlet(name = "CustomerServlet", urlPatterns = {"/customers/*"})
public class CustomerServlet extends HttpServlet {
    
    private CustomerDao customerDao = new CustomerDao();
    private CountryDao countryDao = new CountryDao();
    
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
            //ServletHelper.forward(request, response, "update_customer");
            String id = request.getParameter("id");
            customerDao.deleteCustomer(Integer.parseInt(id));
            ServletHelper.redirect(request, response, "customers");
            return;
        } else if (action != null && action.contains("/search")) {
            String searchText = request.getParameter("searchText");
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
            ServletHelper.redirect(request, response, "customers");
        }
    }
}
