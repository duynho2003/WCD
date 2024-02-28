/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mytech.pretest2.controllers;

import com.mytech.pretest2.daos.StudentDAO;
import com.mytech.pretest2.helpers.ServletHelper;
import com.mytech.pretest2.models.Student;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author Tommy
 */
@WebServlet(name = "StudentServlet", urlPatterns = {"/students/*"})
public class StudentServlet extends HttpServlet {

    private StudentDAO studentDao = new StudentDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        System.out.println("action: " + action);
        if(action != null && action.contains("/new")){ //đặt tên hành động qua đường link (giống IAction bên ASP .Net)
            ServletHelper.forward(request, response, "add"); //gọi qua /WEB-INF/*.jsp (phải có ServletHelper)
            return;
        } else if (action != null && action.contains("/delete")) { //xóa sinh viên lập tức ko cần doPost
            String id = request.getParameter("id");
            studentDao.deleteStudent(id);
            //trở về trang students
            ServletHelper.redirect(request, response, "students");
            return;
        }else if(action != null && action.contains("/edit")){
            String id = request.getParameter("id");
            Student student = studentDao.getStudentById(id); //lấy thông tin sinh viên đã có theo id
            if(student != null){
                request.setAttribute("student", student);
                ServletHelper.forward(request, response, "update");
                return;
            }
        }
        //Hiển thị danh sách sinh viên
        List<Student> listStudents = studentDao.getStudents();
        request.setAttribute("listStudents", listStudents);
        //nhảy qua trang students
        ServletHelper.forward(request, response, "students");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        System.out.println("action: " + action);
        //gọi hành động qua submit
        if (action != null && action.equals("/save")) {
            String classString = request.getParameter("class");
            String name = request.getParameter("name");
            String code = request.getParameter("code");
            String dob = request.getParameter("dob");
            Student student = new Student(classString, name, code, dob);
            studentDao.addStudent(student);
        } else if (action != null && action.equals("/edit")) {
            String id = request.getParameter("id");
            String classString = request.getParameter("class");
            String name = request.getParameter("name");
            String code = request.getParameter("code");
            String dob = request.getParameter("dob");
            Student student = new Student(id, classString, name, code, dob);
            studentDao.updateStudent(student);
        }
        ServletHelper.redirect(request, response, "students");
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
