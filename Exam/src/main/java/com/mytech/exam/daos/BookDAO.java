/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mytech.exam.daos;

import com.mytech.exam.db.DbConnector;
import com.mytech.exam.models.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tommy
 */
public class BookDAO {
    public List<Book> findAll(){
        List<Book> listBook = new ArrayList<>();
        
        try {
            EntityManager entityManager = DbConnector.getEntityManager();
            Query query = entityManager.createNativeQuery("SELECT * FROM tbBook",Book.class);
            listBook = query.getResultList();
            System.out.println("List Books:");
            for (Book book : listBook) {
                System.out.println("Book: " + book.getName()+ book.getAuthor());
            }
        } catch (Exception e) {
        }
        
        return listBook;
    }
    
    public void saveBook(Book book){
        EntityManager entityManager = DbConnector.getEntityManager();
        try {
            var transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(book);
            transaction.commit();
        } catch (Exception e) {
        }
    }
}
