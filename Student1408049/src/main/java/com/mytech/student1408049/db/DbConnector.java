/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mytech.student1408049.db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUnit;

/**
 *
 * @author caova
 */
public class DbConnector {
    static final String jdbcUrl = "jdbc:mysql://localhost:3306/sem4wcd2208a?useSSL=false";
    static final String jdbcUsername = "root";
    static final String jdbcPassword = "123456";
    
    //JPA
    @PersistenceUnit
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Student1408049");
    
    public static EntityManager getEntityManager(){
        return DbConnector.entityManagerFactory.createEntityManager();
    }
}
