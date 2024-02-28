/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mytech.pretest2.daos;

import com.mytech.pretest2.db.DbConnector;
import com.mytech.pretest2.models.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tommy
 */
public class StudentDAO {
    public List<Student> getStudents(){
    List<Student> listStudent = new ArrayList<>();
    try {
        // gọi EntityManager để gọi DbConnector (kết nối database)
            EntityManager entityManager = DbConnector.getEntityManager();
            //Mã query của sql để giải pháp
            Query query = entityManager.createNativeQuery("SELECT * FROM Students",Student.class);
            listStudent = query.getResultList();
            //hiển thị danh sách sinh viên ở dòng lệnh Apache Tomcat
            System.out.println("List Students:");
            for (Student student : listStudent) {
                System.out.println("Student: " + student.getName());
            }
        } catch (Exception e) {
        }
        
        return listStudent;
    }
    
    //lấy id của mỗi sinh viên để xem/cập nhật thông tin
    public Student getStudentById(String id){
        Student student = null;
        try {
           EntityManager entityManager = DbConnector.getEntityManager();
           student = entityManager.find(Student.class, id); 
        } catch (Exception e) {
        }
        return student;
    }
    //thêm sinh viên mới
    public void addStudent(Student student){
        EntityManager entityManager = DbConnector.getEntityManager();
        try {
            var transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(student);
            transaction.commit();
        } catch (Exception e) {
            
        }
    }
    //cập nhật sinh viên 
    public void updateStudent(Student student) {
        EntityManager entityManager = DbConnector.getEntityManager();

        try {
            var transaction = entityManager.getTransaction();
            transaction.begin();
            Student dbStudent = entityManager.find(Student.class, student.getId());
            dbStudent.setCode(student.getCode());
            dbStudent.setName(student.getName());
            dbStudent.setClassString(student.getClassString());
            dbStudent.setDob(student.getDob());
            entityManager.persist(dbStudent);
            transaction.commit();
        } catch (Exception e) {
        }
    }
    //xóa sinh viên
    public void deleteStudent(String id){     
        try {
            EntityManager entityManager = DbConnector.getEntityManager();
            entityManager.getTransaction().begin();
            Student student = entityManager.find(Student.class, id);
            entityManager.remove(student);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
