/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mytech.pretest3.daos;

import com.mytech.pretest3.db.DbConnector;
import com.mytech.pretest3.models.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tommy
 */
public class EmployeeDAO {
    public List<Employee> getEmployees(){
        List<Employee> listEmployee = new ArrayList<>();
        
        try {
            EntityManager entityManager = DbConnector.getEntityManager();
            Query query = entityManager.createNativeQuery("SELECT * FROM Employees",Employee.class);
            listEmployee = query.getResultList();
            System.out.println("List Employees:");
            for (Employee employee : listEmployee) {
                System.out.println("Employee: " + employee.getFirstname() + employee.getLastname());
            }
        } catch (Exception e) {
        }
        
        return listEmployee;
    }
    
    //lấy id của mỗi nhân viên để xem/cập nhật thông tin
    public Employee getEmployeeById(String id){
        Employee employee = null;
        try {
           EntityManager entityManager = DbConnector.getEntityManager();
           employee = entityManager.find(Employee.class, id); 
        } catch (Exception e) {
        }
        return employee;
    }
    
    //thêm nhân viên mới
    public void addEmployee(Employee employee){
        EntityManager entityManager = DbConnector.getEntityManager();
        try {
            var transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(employee);
            transaction.commit();
        } catch (Exception e) {
        }
    }
    
    //cập nhật nhân viên 
    public void updateEmployee(Employee employee) {
        EntityManager entityManager = DbConnector.getEntityManager();

        try {
            var transaction = entityManager.getTransaction();
            transaction.begin();
            Employee dbEmployee = entityManager.find(Employee.class, employee.getId());
            dbEmployee.setFirstname(employee.getFirstname());
            dbEmployee.setLastname(employee.getLastname());
            dbEmployee.setDob(employee.getDob());
            if (employee.getImage() != null) {
                dbEmployee.setImage(employee.getImage());
            }
            entityManager.persist(dbEmployee);
            transaction.commit();
        } catch (Exception e) {
        }
    }
    //xóa nhân viên
    public void deleteEmployee(String id){     
        try {
            EntityManager entityManager = DbConnector.getEntityManager();
            entityManager.getTransaction().begin();
            Employee employee = entityManager.find(Employee.class, id);
            entityManager.remove(employee);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
