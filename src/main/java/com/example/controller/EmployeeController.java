package com.example.controller;


import com.example.entity.Employee;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class EmployeeController implements CommandLineRunner {
    @Autowired
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(path = "/getEmployee", method = RequestMethod.GET, produces = "application/json")
    @GetMapping
    public List<Employee> getListOfEmployees() {
        return employeeService.getListOfEmployees();
    }

    @RequestMapping(path = "/createEmployee", method = RequestMethod.POST, produces = "application/json")
    @PostMapping
    public String addEmployee(@RequestBody Employee employeeRequestDto1) {
        boolean flagInsertion = employeeService.addEmployee(employeeRequestDto1);
        if (flagInsertion) {
            return "Successfully Inserted into Database";
        } else {
            return "Failed to insert into Database";
        }
    }

    @RequestMapping(path = "/findEmployee", method = RequestMethod.GET, produces = "application/json")
    @GetMapping
    public List<Employee> findEmployee(@RequestBody Employee employeeRequestDto) {
        return employeeService.findEmployee(employeeRequestDto.getId());
    }

    @RequestMapping(path = "/updateEmployeeTel", method = RequestMethod.PATCH, produces = "application/json")
    @PostMapping
    public String updateEmployeeTel(@RequestBody Employee employeeRequestDto) {
        Integer rowsUpdated = employeeService.updateEmployeeTel(employeeRequestDto.getId(), employeeRequestDto.getTelephoneNumber());
        if (rowsUpdated != 0) {
            return "Telephone Number has been updated for id " + employeeRequestDto.getId() + " with telephone number " + employeeRequestDto.getTelephoneNumber();
        } else {
            return "FAILED to update";
        }
    }


    @RequestMapping(path = "/getEmployeesbyAge", method = RequestMethod.GET, produces = "application/json")
    @GetMapping
    public List<Employee>  getEmployeesbyAge(@RequestBody Employee employeeRequestDto ,@RequestParam int pageNo,@RequestParam int offset) {
        return employeeService.getEmployeesbyAge(employeeRequestDto.getAge(),pageNo,offset);
    }


    //Call another api in an api
    @RequestMapping(path = "/nestedEmployeeAPI", method = RequestMethod.POST, produces = "application/json")
    @PostMapping
    public String  nestedEmployeeAPI(@RequestBody Employee employeeRequestDto) {
        boolean flag= employeeService.nestedEmployeeAPI(employeeRequestDto);
        if(flag){
            return "SUCESSFULLY NESTED API";
        }else{
            return "FAILED NESTED API";
        }
    }



    @Override
    public void run(String... args) throws Exception {
        System.out.println("");
    }
}
