/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mytech.pretest4.controllers;

import com.mytech.pretest4.daos.BookDAO;
import com.mytech.pretest4.helpers.Constants;
import com.mytech.pretest4.helpers.ServletHelper;
import com.mytech.pretest4.models.Book;
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
            ServletHelper.forward(request, response, "add");
            return;
        } else if (action != null && action.contains("/delete")) {
            String id = request.getParameter("id");
            bookDao.deleteBook(id);

            ServletHelper.redirect(request, response, "books");
            return;
        } else if (action != null && action.contains("/edit")) {
            String id = request.getParameter("id");
            Book book = bookDao.getBookById(id);
            if (book != null) {
                request.setAttribute("book", book);
                ServletHelper.forward(request, response, "update");
                return;
            }
        } else if (action != null && action.contains("/search")) {
            String searchText = request.getParameter("searchText");
            List<Book> listBooks = bookDao.searchBooksByName(searchText);
            request.setAttribute("listBooks", listBooks);
            ServletHelper.forward(request, response, "books");
            return;
        } else if (action != null && action.contains("/sort")) {
            String min = request.getParameter("min");
            String max = request.getParameter("max");
            List<Book> listBooks = bookDao.getBookByPrice(Double.parseDouble(min), Double.parseDouble(max));
            System.out.println(">>>length list: ");
            request.setAttribute("listBooks", listBooks);
            ServletHelper.forward(request, response, "books");
            return;
        }
        //Hiển thị danh sách sách
        List<Book> listBooks = bookDao.getBooks();
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
            String price = request.getParameter("price");
            String image = Constants.UPLOAD_DIRECTORY + File.separator + saveFilename;

            System.out.println("name and price:" + id + "-" + name + "-" + author + "-" + price + "-" + image);

            Book book = new Book(id, name, author, Double.parseDouble(price));
            book.setImage(image);
            bookDao.addBook(book);
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
            String name = request.getParameter("name");
            String author = request.getParameter("author");
            String price = request.getParameter("price");
            String image = Constants.UPLOAD_DIRECTORY + File.separator + saveFilename;
            Book book = new Book(id, name, author, Double.parseDouble(price));
            if (!Constants.DEFAULT_FILENAME.equals(saveFilename)) {
                book.setImage(image);
                bookDao.updateBook(book);
            }
        }
        ServletHelper.redirect(request, response, "books");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
