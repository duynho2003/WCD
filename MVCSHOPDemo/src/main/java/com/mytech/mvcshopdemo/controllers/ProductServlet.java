/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mytech.mvcshopdemo.controllers;

import com.mytech.mvcshopdemo.daos.ProductDAO;
import com.mytech.mvcshopdemo.helpers.Constants;
import com.mytech.mvcshopdemo.helpers.ServletHelper;
import com.mytech.mvcshopdemo.models.Productt;
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
 * @author caova
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/products/*"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,maxFileSize = 1024 * 1024 * 5)
public class ProductServlet extends HttpServlet {

    private ProductDAO productDao = new ProductDAO();
  
    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
        if (content.trim().startsWith("filename"))
        return content.substring(content.indexOf("=") + 2, content.length() - 1);
        }
        return Constants.DEFAULT_FILENAME;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        System.out.println("action: " + action);
        if(action != null && action.contains("/new")){
            ServletHelper.forward(request, response, "add_product");
            return;
        } else if (action != null && action.contains("/delete")) {
            String id = request.getParameter("id");
            productDao.deleteProduct(id);
            
            ServletHelper.redirect(request, response, "products");
            return;
        }else if(action != null && action.contains("/edit")){
            String id = request.getParameter("id");
            Productt product = productDao.getProductById(id);
            if(product != null){
                request.setAttribute("product", product);
                ServletHelper.forward(request, response, "update_product");
                return;
            }
        }
        List<Productt> listProducts = productDao.getProducts();
        request.setAttribute("listProducts", listProducts);
        ServletHelper.forward(request, response, "products");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        
        
        if(action != null && action.contains("/save")){
            
            //Upload file
            String uploadPath = getServletContext().getRealPath("") + Constants.UPLOAD_DIRECTORY;
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdirs();
            }

            String saveFilename = Constants.DEFAULT_FILENAME;
            try {
                for (Part part : request.getParts()) {
                    String filename = getFileName(part);
                    if(!Constants.DEFAULT_FILENAME.equals(filename)){
                        saveFilename = filename;
                        part.write(uploadPath + File.separator + filename);
                    }
                    
                }
            } catch (Exception e) {
                request.setAttribute("message", "Could not save file...");
            }
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String price = request.getParameter("price");
            String image = Constants.UPLOAD_DIRECTORY + File.separator + saveFilename ;

            System.out.println("name and price:"+id+"-" +name+"-"+price+"-"+image);

            
            Productt product = new Productt(id,name,Double.parseDouble(price));
            product.setImage(image);
            productDao.addProduct(product);
            
        }else if(action != null && action.contains("/edit")){
            //Upload file
            String uploadPath = getServletContext().getRealPath("") + Constants.UPLOAD_DIRECTORY;
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdirs();
            }

            String saveFilename = Constants.DEFAULT_FILENAME;
            try {
                for (Part part : request.getParts()) {
                    String filename = getFileName(part);
                    if(!Constants.DEFAULT_FILENAME.equals(filename)){
                        saveFilename = filename;
                        part.write(uploadPath + File.separator + filename);
                    }
                    
                }
            } catch (Exception e) {
                request.setAttribute("message", "Could not save file...");
            }
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String price = request.getParameter("price");
            String image = Constants.UPLOAD_DIRECTORY + File.separator + saveFilename ;

            Productt product = new Productt(id,name,Double.parseDouble(price));
                if(!Constants.DEFAULT_FILENAME.equals(saveFilename)){
                    product.setImage(image);   
                    productDao.updateProduct(product);
                }
            
        }
        
        ServletHelper.redirect(request, response, "products");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
