/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mytech.mvcshopdemo.db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUnit;

/**
 *
 * @author trungtruong
 */
public class DbConnector {
    static final String jdbcUrl = "jdbc:mysql://localhost:3306/sem4wcd2208A?useSSL=false";
    static final String jdbcUsername = "root";
    static final String jdbcPassword = "123456";
    
    //JPA
    @PersistenceUnit
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MVCShopDemo");
    
    public static EntityManager getEntityManager() {
        System.out.println("loading......");
        return DbConnector.entityManagerFactory.createEntityManager();
    }
}
