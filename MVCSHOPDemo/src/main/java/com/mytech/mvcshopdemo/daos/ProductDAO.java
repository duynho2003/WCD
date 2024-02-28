/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mytech.mvcshopdemo.daos;

import com.mytech.mvcshopdemo.db.DbConnector;
import com.mytech.mvcshopdemo.models.Productt;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author caova
 */
public class ProductDAO {
    public List<Productt> getProducts(){
        List<Productt> listProduct = new ArrayList<>();
        
        try {
            EntityManager entityManager = DbConnector.getEntityManager();
            Query query = entityManager.createNativeQuery("SELECT * FROM Productts",Productt.class);
            listProduct = query.getResultList();
            System.out.println("List Products:");
            for (Productt product : listProduct) {
                System.out.println("Product: " + product.getName());
            }
        } catch (Exception e) {
        }
        
        return listProduct;
    } 
    
    public Productt getProductById(String id){
        Productt product = null;
        
        try {
           EntityManager entityManager = DbConnector.getEntityManager();
           product = entityManager.find(Productt.class, id); 
        } catch (Exception e) {
        }
        return product;
    }
    
    public void addProduct(Productt product){
        EntityManager entityManager = DbConnector.getEntityManager();
        try {
            var transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(product);
            transaction.commit();
        } catch (Exception e) {
            
        }
    }
    
    public void updateProduct(Productt product) {
        EntityManager entityManager = DbConnector.getEntityManager();

        try {
            var transaction = entityManager.getTransaction();
            transaction.begin();
            Productt dbProduct = entityManager.find(Productt.class, product.getId());
            dbProduct.setName(product.getName());
            dbProduct.setPrice(product.getPrice());
            if (product.getImage() != null) {
                dbProduct.setImage(product.getImage());
            }
            entityManager.persist(dbProduct);
            transaction.commit();
        } catch (Exception e) {
        }
    }
    
    public void deleteProduct(String id){     
        try {
            EntityManager entityManager = DbConnector.getEntityManager();
            entityManager.getTransaction().begin();
            Productt product = entityManager.find(Productt.class, id);
            entityManager.remove(product);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
