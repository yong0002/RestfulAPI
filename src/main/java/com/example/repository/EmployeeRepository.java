package com.example.repository;

import ch.qos.logback.classic.util.StatusViaSLF4JLoggerFactory;
import com.example.entity.Employee;
import com.example.logging.LoggingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingFilter.class);
    @Autowired
    private EntityManager entityManager;

    public List<Employee> getListOfEmployees() {
        List employeeList = null;
        try {
            String sqlStatement = "SELECT * FROM employees";
            Query query = entityManager.createNativeQuery(sqlStatement, Employee.class);
            employeeList = query.getResultList();

        } catch (Exception ex) {
            System.out.println("Exception when gettingList of Employees : " + ex);
            LOGGER.error("Exception when gettingList of Employees : " + ex);
        }
        return employeeList;
    }

    @Transactional
    public boolean addEmployee(Employee employee) {
        try {
            entityManager.persist(employee);
            return true;
        }catch (Exception ex){
            System.out.println("Exception when inserting : " + ex);
            LOGGER.error("Exception when inserting : " + ex);
        }
        return false;
    }

    public List<Employee> findEmployee(Long id) {
        List employeeDetails = null;
        try {
            String sqlStatement = "SELECT * FROM employees WHERE id = ? ";
            Query query = entityManager.createNativeQuery(sqlStatement, Employee.class);
            query.setParameter(1, id);
            employeeDetails = query.getResultList();
        } catch (Exception ex) {
            System.out.println("Querying issue by ID: " + ex);
            LOGGER.error("Querying issue by ID : " + ex);
        }
        return employeeDetails;

    }
    @Transactional
    public Integer updateEmployeeTel(Long id,String telephoneNumber) {
        try {
            String sqlStatement = "UPDATE employees SET telephone_number = ? where id = ?";
            Query query = entityManager.createNativeQuery(sqlStatement, Employee.class);
            query.setParameter(1, telephoneNumber);
            query.setParameter(2, id);
            Integer rowsUpdated = query.executeUpdate();
            return rowsUpdated;
        }catch(Exception ex){
            System.out.println("Exception when updating : " + ex);
            LOGGER.error("Exception when updating : " + ex);
        }
        return null;
    }

    public List<Employee>  getEmployeesbyAge(int age  ,int pageNo, int offset) {
        List employeeDetails = null;
        try {
            String sqlStatement = "SELECT * FROM employees WHERE age = ? ORDER BY age OFFSET (? - 1) * ? ROWS FETCH NEXT ? ROWS ONLY";
            Query query = entityManager.createNativeQuery(sqlStatement, Employee.class);
            query.setParameter(1, age);
            query.setParameter(2, pageNo);
            query.setParameter(3, offset);
            query.setParameter(4, offset);
            employeeDetails = query.getResultList();
        }catch (Exception ex){
            System.out.println("Exception getting employees by age : " + ex);
            LOGGER.error("Exception getting employees by age : " + ex);
        }
        return employeeDetails;
    }

    public List<Employee>  createDummyEmployeeExist(Employee employeeRequestDto) {
        return new ArrayList<>();
    }
}

