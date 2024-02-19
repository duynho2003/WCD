/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mytech.shopmanagement.daos;

import com.mytech.shopmanagement.db.DbConnector;
import com.mytech.shopmanagement.models.Country;
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
 * @author trungtruong
 */
public class CountryDao {
    static final String SELECT_ALL_COUNTRY = "SELECT * FROM Countries";
    
   public List<Country> getCountries() {
       List<Country> listCountries = new ArrayList<>();
       Connection connection = null;
       try {
           connection =  DbConnector.getConnection();
           PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COUNTRY);
           ResultSet rs = preparedStatement.executeQuery();
           while(rs.next()) {
               String code = rs.getString("code");
               String name = rs.getString("name");
               Country country = new Country(code, name);
               listCountries.add(country);
               System.out.println("Country: " + country.toString());
           }
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           if (connection != null) {
               try {
                   connection.close();
               } catch (SQLException ex) {
                   Logger.getLogger(CountryDao.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
       }
       return listCountries;
   }
}
