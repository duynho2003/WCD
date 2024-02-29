/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mytech.pretest4.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author Tommy
 */
@Entity
@Table(name = "Books")
@NamedQuery(name = "Book.findByName", query = "SELECT u FROM Book u WHERE u.name LIKE :name")
@NamedQuery(name = "Book.sortByPrice", query = "SELECT u FROM Book u WHERE u.price between :min and :max")
public class Book implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    
    @Column(name = "Name", unique = true, length = 255,nullable = false)
    private String name;
    
    @Column(name = "Author", unique = false, length = 50,nullable = false)
    private String author;
    
    @Column(name = "Price")
    private Double price;
    
    @Column(name = "Image")
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public Book(){}

    public Book(String id, String name, String author, Double price) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
    }

    public Book(String id, String name, String author, Double price, String image) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.image = image;
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
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mytech.pretest4.models.Book[ id=" + id + " ]";
    }
}
