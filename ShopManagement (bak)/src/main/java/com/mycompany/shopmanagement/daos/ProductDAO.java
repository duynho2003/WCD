/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.shopmanagement.daos;

import com.mycompany.shopmanagement.db.DbConnector;
import com.mycompany.shopmanagement.models.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ProductDAO {
    
    public List<Product> getProduct() {
        List<Product> listProduct = new ArrayList<>();
        
        try {
            EntityManager entityManager = DbConnector.getEntityManager();
            Query query = entityManager.createNativeQuery("SELECT * FROM Products", Product.class);
            listProduct = query.getResultList();
            
            System.out.println("List products:");
            for (Product product : listProduct) {
                System.out.println("Product: " + product.getName());
            }
        } catch (Exception e ) {
        }
        return listProduct;
    }
}
