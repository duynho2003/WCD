/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mytech.pretest4.daos;

import com.mytech.pretest4.db.DbConnector;
import com.mytech.pretest4.models.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tommy
 */
public class BookDAO {
    public List<Book> getBooks(){
        List<Book> listBook = new ArrayList<>();
        
        try {
            EntityManager entityManager = DbConnector.getEntityManager();
            Query query = entityManager.createNativeQuery("SELECT * FROM Books",Book.class);
            listBook = query.getResultList();
            System.out.println("List Books:");
            for (Book book : listBook) {
                System.out.println("Book: " + book.getName()+ book.getAuthor());
            }
        } catch (Exception e) {
        }
        
        return listBook;
    }
    
    //lấy id của mỗi nhân viên để xem/cập nhật thông tin
    public Book getBookById(String id){
        Book book = null;
        try {
           EntityManager entityManager = DbConnector.getEntityManager();
           book = entityManager.find(Book.class, id); 
        } catch (Exception e) {
        }
        return book;
    }
    
    public void addBook(Book book){
        EntityManager entityManager = DbConnector.getEntityManager();
        try {
            var transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(book);
            transaction.commit();
        } catch (Exception e) {
        }
    }
    
    //cập nhật nhân viên 
    public void updateBook(Book book) {
        EntityManager entityManager = DbConnector.getEntityManager();

        try {
            var transaction = entityManager.getTransaction();
            transaction.begin();
            Book dbBook = entityManager.find(Book.class, book.getId());
            dbBook.setName(book.getName());
            dbBook.setAuthor(book.getAuthor());
            dbBook.setPrice(book.getPrice());
            if (book.getImage() != null) {
                dbBook.setImage(book.getImage());
            }
            entityManager.persist(dbBook);
            transaction.commit();
        } catch (Exception e) {
        }
    }
    
    //xóa nhân viên
    public void deleteBook(String id){     
        try {
            EntityManager entityManager = DbConnector.getEntityManager();
            entityManager.getTransaction().begin();
            Book book = entityManager.find(Book.class, id);
            entityManager.remove(book);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //tìm kiếm
    public List<Book> searchBooksByName(String name){
        List<Book> listBook = new ArrayList<>();
        try{
            EntityManager entityManager = DbConnector.getEntityManager();
            TypedQuery<Book> query = entityManager.createNamedQuery("Book.findByName", Book.class);
            query.setParameter("name", "%" + name + "%");
            listBook = query.getResultList();
        } catch (Exception e){
            System.out.println("searchBooksByName Exception: " + e.getMessage());
        }
        return listBook;
    }

    //sắp xếp giá tiền
    public List<Book> getBookByPrice(Double min, Double max) {
        List<Book> list = new ArrayList<>();
        try {
            EntityManager entityManager = DbConnector.getEntityManager();
            TypedQuery<Book> query = entityManager.createNamedQuery("Book.sortByPrice", Book.class);
            query.setParameter("min", min);
            query.setParameter("max", max);
            list = query.getResultList();
        } catch (Exception e) {
            System.out.println(">>>Loi: " + e.getMessage());
        }
        return list;
    }
}
