package com.manager.service.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;


@RestController
@RefreshScope
@RequestMapping("/manager")
public class ManagerController
{
    @Autowired
    private RestTemplate restTemplate;

    @Value("${message}")
    public String message;

    @Value("${employee.username}")
    public String employeeUsername;

    @Value("${employee.password}")
    public String employeePassword;

    @GetMapping("")
    public String welcomeMessage(){
        return "<h1>Welcome Manager "+ message +"</h1><br/>";
    }

    @GetMapping("/employee")
    public String callEmployee(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth(employeeUsername.trim(),employeePassword.trim());
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> result = restTemplate.exchange("http://EMPLOYEE-SERVICE/employee", HttpMethod.GET, entity, String.class);
        return result.getBody();
    }

    @GetMapping("/getemployees")
    public Object getEmployees(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth(employeeUsername.trim(),employeePassword.trim());
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> result = restTemplate.exchange("http://EMPLOYEE-SERVICE/employee/getemployees", HttpMethod.GET, entity, String.class);
        return result.getBody();
    }

    @PostMapping("/addemployee")
    public Object addNewEmployee(@RequestBody Object employee){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth(employeeUsername.trim(),employeePassword.trim());
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<>(employee,httpHeaders);
        ResponseEntity<String> result = restTemplate.exchange("http://EMPLOYEE-SERVICE/employee/addemployee", HttpMethod.POST, entity, String.class);
        return result.getBody();
    }

    @PutMapping("/updateemployee")
    public Object updateEmployee(@RequestBody Object employee){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth(employeeUsername.trim(),employeePassword.trim());
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<>(employee,httpHeaders);
        ResponseEntity<String> result = restTemplate.exchange("http://EMPLOYEE-SERVICE/employee/updateemployee", HttpMethod.PUT, entity, String.class);
        return result.getBody();
    }

    @DeleteMapping("/deleteemployee")
    public Object deleteEmployee(@RequestBody Object employee){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth(employeeUsername.trim(),employeePassword.trim());
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<>(employee,httpHeaders);
        ResponseEntity<String> result = restTemplate.exchange("http://EMPLOYEE-SERVICE/employee/deleteemployee", HttpMethod.DELETE, entity, String.class);
        return result.getBody();
    }

}
