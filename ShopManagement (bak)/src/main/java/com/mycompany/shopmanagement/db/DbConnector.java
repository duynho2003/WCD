/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.shopmanagement.db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class DbConnector {
    static final String jdbcUrl = "jdbc:mysql://localhost:3306/sem4wcd2208a?SSL=false";
    static final String jdbcUsername = "root";
    static final String jdbcPassword = "123456";
    
    //JDBC
    public static Connection getConnection() {
        Connection connection = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl,jdbcUsername,jdbcPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    
    //JPA
    private static EntityManagerFactory entityManagerFactory = 
            Persistence.createEntityManagerFactory("ShopManagement");
    public static EntityManager getEntityManager() {
        return DbConnector.entityManagerFactory.createEntityManager();
    }
}
