/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mytech.eldemo;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class MyNumber {
    
    public static Double getAverage(ArrayList<Double> values) {
        if (values.size() == 0) return 0.0;
        
        Double total = 0.0;
        for (Double value : values) {
            total+= value;
        }
        return total / values.size();
    }
}
