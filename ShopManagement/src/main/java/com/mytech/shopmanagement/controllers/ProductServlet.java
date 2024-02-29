/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mytech.shopmanagement.controllers;

import com.mytech.shopmanagement.daos.ProductDao;
import com.mytech.shopmanagement.helpers.Constants;
import com.mytech.shopmanagement.helpers.ServletHelper;
import com.mytech.shopmanagement.models.Product;
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
 * @author trungtruong
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/products/*"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5)
public class ProductServlet extends HttpServlet {

    private ProductDao productDao = new ProductDao();

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            System.out.println("content part: " + content);
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

        if (action != null && action.contains("/new")) {
            ServletHelper.forward(request, response, "add_product");
            return;
        } else if (action != null && action.contains("/edit")) {
            String id = request.getParameter("id");
            Product product = productDao.getProductById(Long.parseLong(id));
            if (product != null) {
                request.setAttribute("product", product);
                ServletHelper.forward(request, response, "update_product");
                return;
            }
        } else if (action != null && action.contains("/delete")) {
            String id = request.getParameter("id");
            productDao.deleteProduct(Long.parseLong(id));
            ServletHelper.redirect(request, response, "products");
            return;
        } else if (action != null && action.contains("/search")) {
            String searchText = request.getParameter("searchText");
            List<Product> listProducts = productDao.searchProductsByName(searchText);
            request.setAttribute("listProducts", listProducts);
            ServletHelper.forward(request, response, "products");
            return;
        } else if (action != null && action.contains("/sort")) {
            String min = request.getParameter("min");
            String max = request.getParameter("max");
            List<Product> listProducts = productDao.getProductByPrice(Double.parseDouble(min), Double.parseDouble(max));
            System.out.println(">>>length list: ");
            request.setAttribute("listProducts", listProducts);
            ServletHelper.forward(request, response, "products");
            return;
        }

        List<Product> listProducts = productDao.getProducts();
        request.setAttribute("listProducts", listProducts);
        ServletHelper.forward(request, response, "products");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        System.out.println("action: " + action);
        String name = request.getParameter("name");
        String price = request.getParameter("price");

        String id = request.getParameter("id");

        boolean isUpdate = id != null;

        if (action != null && action.contains("/save")) {
            // Upload File
            String uploadPath = getServletContext().getRealPath("") + Constants.UPLOAD_DIRECTORY;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            System.out.println("Upload path: " + uploadPath);

            String savefilename = Constants.DEFAULT_FILENAME;
            try {
                for (Part part : request.getParts()) {
                    String filename = getFileName(part);
                    if (!Constants.DEFAULT_FILENAME.equals(filename)) {
                        savefilename = filename;
                        part.write(uploadPath + File.separator + filename);
                    }
                }
            } catch (Exception e) {
                request.setAttribute("message", "Could not save file...");
            }
            String image = Constants.UPLOAD_DIRECTORY + File.separator + savefilename;
            // String name = request.getParameter("name");
            // String price = request.getParameter("price");
            if (isUpdate) {
                Product product = new Product(Long.parseLong(id), name, Double.parseDouble(price));
                if (!Constants.DEFAULT_FILENAME.equals(savefilename)) {
                    product.setImage(image);
                    productDao.updateProduct(product);
                }
            } else {
                Product product = new Product(name, Double.parseDouble(price));
                product.setImage(image);
                productDao.addProduct(product);
            }
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
