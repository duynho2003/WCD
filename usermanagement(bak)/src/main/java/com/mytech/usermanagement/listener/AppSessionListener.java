/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mytech.usermanagement.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

/**
 *
 * @author Admin
 */
@WebListener
public class AppSessionListener implements HttpSessionListener{

    public static int SESSION_COUNT = 0;
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSessionListener.super.sessionCreated(se);
        SESSION_COUNT++;
        System.out.println("SESSION_COUNT: " + SESSION_COUNT);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSessionListener.super.sessionDestroyed(se);
        SESSION_COUNT--;
        System.out.println("SESSION_COUNT: " + SESSION_COUNT);
    }
}
