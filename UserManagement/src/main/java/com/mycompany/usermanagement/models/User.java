/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.usermanagement.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author Admin
 */
public class User {
    
    public static HashSet<User> USERDB = new HashSet<>();
    static {
        USERDB.add(new User("admin@gmail.com", "123"));
        USERDB.add(new User("user@gmail.com", "123"));
    }
    
    private String email;
    private String password;
    
    private List<Date> loginDates = new ArrayList<>();
    
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Date> getLoginDates() {
        return loginDates;
    }

    public void setLoginDates(List<Date> loginDates) {
        this.loginDates = loginDates;
    }
    
    public void addLoginDate(Date date) {
        this.loginDates.add(date);
    }
}
