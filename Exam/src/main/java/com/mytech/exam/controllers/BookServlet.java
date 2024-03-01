/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mytech.exam.controllers;

import com.mytech.exam.daos.BookDAO;
import com.mytech.exam.helpers.Constants;
import com.mytech.exam.helpers.ServletHelper;
import com.mytech.exam.models.Book;
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
@WebServlet(name = "BookServlet", urlPatterns = {"/books/*"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5)
public class BookServlet extends HttpServlet {

    private BookDAO bookDao = new BookDAO();

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
            ServletHelper.forward(request, response, "create");
            return;
        }
        //Hiển thị danh sách sách
        List<Book> listBooks = bookDao.findAll();
        request.setAttribute("listBooks", listBooks);
        //nhảy qua trang books
        ServletHelper.forward(request, response, "books");
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
            String name = request.getParameter("name");
            String author = request.getParameter("author");
            String edition = request.getParameter("edition");
            String image = Constants.UPLOAD_DIRECTORY + File.separator + saveFilename;

            System.out.println("name and edition:" + id + "-" + name + "-" + author + "-" + edition + "-" + image);

            Book book = new Book(Long.parseLong(id), name, author, Double.parseDouble(edition));
            book.setImage(image);
            bookDao.saveBook(book);
        }
        ServletHelper.redirect(request, response, "books");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
