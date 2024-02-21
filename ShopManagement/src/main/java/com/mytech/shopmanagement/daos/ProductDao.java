/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mytech.shopmanagement.daos;

import com.mytech.shopmanagement.db.DbConnector;
import com.mytech.shopmanagement.models.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author trungtruong
 */
public class ProductDao {
    
    public void addProduct(Product product) {
        EntityManager entityManager = DbConnector.getEntityManager();
        
        try{
            var transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(product);
            transaction.commit();
        }catch(Exception e){
        }
    }
    
    public void updateProduct(Product product) {
        EntityManager entityManager = DbConnector.getEntityManager();
        
        try{
            var transaction = entityManager.getTransaction();
            transaction.begin();
            Product dbProduct = entityManager.find(Product.class, product.getId());
            dbProduct.setName(product.getName());
            dbProduct.setPrice(product.getPrice());
            if (product.getImage() != null) {
                dbProduct.setImage(product.getImage());
            }
            entityManager.persist(dbProduct);
            transaction.commit();
        }catch(Exception e){
        }
    }
    
    public Product getProductById(Long id) {
        Product product = null;

        try{
            EntityManager entityManager = DbConnector.getEntityManager();
            product = entityManager.find(Product.class, product);

            System.out.println("Product By ID: " + product.getName());
        } catch (Exception e) {
        }

        return product;
    }

    public List<Product> getProducts() {
        List<Product> listProduct = new ArrayList<>();
        
        try {
            EntityManager entityManager = DbConnector.getEntityManager();
            Query query = entityManager.createNativeQuery("SELECT * FROM Products", Product.class);
            listProduct = query.getResultList();
            
            System.out.println("List products:");
            for (Product product : listProduct) {
                System.out.println("Product: " + product.getName());
            }
        } catch (Exception e) {
        }
        
        return listProduct;
    }
}