/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.shopmanagement.daos;

import com.mycompany.shopmanagement.db.DbConnector;
import com.mycompany.shopmanagement.models.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
//JDBC
public class CustomerDAO {

    static final String SELECT_ALL_CUSTOMER = "SELECT * FROM Customers;";
    static final String INSERT_CUSTOMER = "INSERT INTO Customers (name, email, country) VALUES (?, ?, ?)";
    static final String FIND_CUSTOMER_BY_ID = "SELECT * FROM Customers WHERE id = ?";
    static final String UPDATE_CUSTOMER_BY_ID = "UPDATE Customers SET name = ?, email = ?, country = ? WHERE (id = ?)";
    static final String DELETE_CUSTOMER_BY_ID = "DELETE Customers WHERE (id = ?)";
    static final String FIND_CUSTOMER_BY_NAME_OR_EMAIL = "SELECT * FROM Customers WHERE name LIKE ? OR email LIKE ?";

    public Customer saveCustomer(Customer customer) {
        Connection connection = null;
        try {
            connection = DbConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getCountry());
            int result = preparedStatement.executeUpdate();
            System.out.println("Saved customer: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    public List<Customer> getCustomers() {
        List<Customer> listCustomers = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DbConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMER);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                Customer customer = new Customer(id, name, email, country);
                System.out.println("Customer:" + customer.toString());
                listCustomers.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return listCustomers;
    }

    public Customer getCustomer(int id) {
        Connection connection = null;
        try {
            connection = DbConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_CUSTOMER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idDb = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                Customer customer = new Customer(idDb, name, email, country);
                return customer;
            }
        } catch (Exception e) {

        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    
    public void updateCustomer(Customer customer) {
        Connection connection = null;
        try {
            connection = DbConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER_BY_ID);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getCountry());
            preparedStatement.setInt(4, customer.getId());
            int result = preparedStatement.executeUpdate();
            System.out.println("Updated customer: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    
    public List<Customer> getCustomers(String searchText) {
        List<Customer> listCustomers = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DbConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_CUSTOMER_BY_NAME_OR_EMAIL);
            preparedStatement.setString(1, "%" + searchText + "%");
            preparedStatement.setString(2, "%" + searchText + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idDb = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                Customer customer = new Customer(idDb, name, email, country);
                listCustomers.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return listCustomers;
    }
}
