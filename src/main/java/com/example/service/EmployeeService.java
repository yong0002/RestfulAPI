package com.example.service;

import com.example.entity.Employee;
import com.example.logging.LoggingFilter;
import com.example.repository.EmployeeRepository;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingFilter.class);
    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

//    public List<Employee> getListOfEmployees() {
//        return List.of(new Employee(1,"ONG",1, LocalDate.of(2022, Month.JULY,12),"023131"));
//    }

    public List<Employee> getListOfEmployees() {
        return employeeRepository.getListOfEmployees();
    }

    public boolean addEmployee(Employee employee) {
        return employeeRepository.addEmployee(employee);
    }

    public List<Employee> findEmployee(Long id) {
        return employeeRepository.findEmployee(id);
    }
    public Integer updateEmployeeTel(Long employeeId,String telephoneNumber) {
        return employeeRepository.updateEmployeeTel(employeeId,telephoneNumber);
    }

    public List<Employee>  getEmployeesbyAge(int age  ,int pageNo, int offset) {
        return employeeRepository.getEmployeesbyAge(age,pageNo,offset);
    }

    public boolean  nestedEmployeeAPI(Employee employeeRequestDto) {
        JSONObject request = new JSONObject();
        String urlString = "http://localhost:8080/api/v1/getEmployee";
        try{
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<JSONObject> entity = new HttpEntity<>(headers);

// send request and parse result
            ResponseEntity<ArrayList> loginResponse = restTemplate
                    .exchange(urlString, HttpMethod.GET, entity, ArrayList.class);
            if (loginResponse.getStatusCode() == HttpStatus.OK && (!loginResponse.getBody().equals(""))) {
                return true;
            }else{
                return false;
            }

        }catch (Exception ex){
            System.out.println("Exception when gettingList of Employees : " + ex);
            LOGGER.error("Exception when gettingList of Employees : " + ex);
        }
        return false;
    }

}
