package com.manager.service.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class CloudConfigurations {
    @Value("${message}")
    public String message;


    @Value("${employee.username}")
    public String employeeUsername;

    @Value("${employee.password}")
    public String employeePassword;

    public String getEncodedCredentials(){
        Base64.Encoder encoder = Base64.getEncoder();
        String credentials = employeeUsername+":"+employeePassword;
        String encodedCredentials = encoder.encodeToString(credentials.getBytes());
        return encodedCredentials;
    }
}

