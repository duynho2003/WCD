/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mytech.pretest3.controllers;

import com.mytech.pretest3.daos.EmployeeDAO;
import com.mytech.pretest3.helpers.Constants;
import com.mytech.pretest3.helpers.ServletHelper;
import com.mytech.pretest3.models.Employee;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.util.List;

/**
 *
 * @author Tommy
 */
@WebServlet(name = "EmployeeServlet", urlPatterns = {"/employees/*"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5)
public class EmployeeServlet extends HttpServlet {

    private EmployeeDAO employeeDao = new EmployeeDAO();

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        }
        return Constants.DEFAULT_FILENAME;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        System.out.println("action: " + action);
        if (action != null && action.contains("/create")) {
            ServletHelper.forward(request, response, "add");
            return;
        } else if (action != null && action.contains("/delete")) {
            String id = request.getParameter("id");
            employeeDao.deleteEmployee(id);

            ServletHelper.redirect(request, response, "employees");
            return;
        } else if (action != null && action.contains("/edit")) {
            String id = request.getParameter("id");
            Employee employee = employeeDao.getEmployeeById(id);
            if (employee != null) {
                request.setAttribute("employee", employee);
                ServletHelper.forward(request, response, "update");
                return;
            }
        }
        //Hiển thị danh sách nhân viên
        List<Employee> listEmployees = employeeDao.getEmployees();
        request.setAttribute("listEmployees", listEmployees);
        //nhảy qua trang students
        ServletHelper.forward(request, response, "employees");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action != null && action.contains("/save")) {

            //Upload file
            String uploadPath = getServletContext().getRealPath("") + Constants.UPLOAD_DIRECTORY;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String saveFilename = Constants.DEFAULT_FILENAME;
            try {
                for (Part part : request.getParts()) {
                    String filename = getFileName(part);
                    if (!Constants.DEFAULT_FILENAME.equals(filename)) {
                        saveFilename = filename;
                        part.write(uploadPath + File.separator + filename);
                    }
                }
            } catch (Exception e) {
                request.setAttribute("message", "Could not save file...");
            }
            String id = request.getParameter("id");
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String dob = request.getParameter("dob");
            String image = Constants.UPLOAD_DIRECTORY + File.separator + saveFilename;

            System.out.println("name and price:" + id + "-" + firstname + "-" + lastname + "-" + dob + "-" + image);

            Employee employee = new Employee(id, firstname, lastname, dob);
            employee.setImage(image);
            employeeDao.addEmployee(employee);
        } else if (action != null && action.contains("/edit")) {
            //Upload file
            String uploadPath = getServletContext().getRealPath("") + Constants.UPLOAD_DIRECTORY;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String saveFilename = Constants.DEFAULT_FILENAME;
            try {
                for (Part part : request.getParts()) {
                    String filename = getFileName(part);
                    if (!Constants.DEFAULT_FILENAME.equals(filename)) {
                        saveFilename = filename;
                        part.write(uploadPath + File.separator + filename);
                    }
                }
            } catch (Exception e) {
                request.setAttribute("message", "Could not save file...");
            }
            String id = request.getParameter("id");
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String dob = request.getParameter("dob");
            String image = Constants.UPLOAD_DIRECTORY + File.separator + saveFilename;

            Employee employee = new Employee(id, firstname, lastname, dob);
            if (!Constants.DEFAULT_FILENAME.equals(saveFilename)) {
                employee.setImage(image);
                employeeDao.updateEmployee(employee);
            }
        }
        ServletHelper.redirect(request, response, "employees");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
