package com.employee.service.Controller;

import com.employee.service.Model.Employee;
import com.employee.service.Service.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeRepo employeeRepo;

    @GetMapping("")
    public String welcomeEmployee(){
        return "<h1>Welcome Employee</h1>";
    }

    @GetMapping("/getemployees")
    public List<Employee> getAllEmployees(){
        return employeeRepo.findAll();
    }

    @PostMapping("/addemployee")
    public Employee addNewEmployee(@RequestBody Employee employee){
        return employeeRepo.save(employee);
    }

    @PutMapping("/updateemployee")
    public Employee updateEmployee(@RequestBody Employee employee){
        return employeeRepo.save(employee);
    }

    @DeleteMapping("/deleteemployee")
    public void deleteEmployee(@RequestBody Employee employee){
        employeeRepo.delete(employee);
    }
}
