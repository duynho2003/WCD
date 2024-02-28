/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mytech.pretest2.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author Tommy
 */
@Entity
@Table(name = "Students")
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    
    @Column(name = "class")
    private String classString;
    
    @Column(name = "code")
    private String code;
    
    @Column(name = "name", unique = true, length = 255,nullable = false)
    private String name;
    
    @Column(name = "dob")
    private String dob;
    
    public Student(){
    }

    public Student(String id, String classString, String name, String code, String dob) {
        this.id = id;
        this.classString = classString;
        this.name = name;
        this.code = code;
        this.dob = dob;
    }

    public Student(String classString, String name, String code, String dob) {
        this.classString = classString;
        this.name = name;
        this.code = code;
        this.dob = dob;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassString() {
        return classString;
    }

    public void setClassString(String classString) {
        this.classString = classString;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mytech.pretest2.models.Student[ id=" + id + " ]";
    }
    
}
