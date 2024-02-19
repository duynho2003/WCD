/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mytech.usermanagement.warppers;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.util.Locale;

/**
 *
 * @author Admin
 */
public class AppResponseWrapper extends HttpServletResponseWrapper{
    
    public AppResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public Locale getLocale() {
        Locale locale = super.getLocale();
        
        String country = locale.getCountry();
        String language = locale.getScript();
        
        if (!"US".equals(country)) {
            locale = locale.US;
        }
        
        System.out.println("AppResponseWrapper::getLocale" + language);
        
        return locale;
    }
}
