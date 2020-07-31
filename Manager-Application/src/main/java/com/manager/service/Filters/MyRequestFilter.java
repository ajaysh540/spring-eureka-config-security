package com.manager.service.Filters;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class MyRequestFilter extends OncePerRequestFilter {
    @Autowired
    private AuthenticationManager authenticationManager;

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println(request);
        String authHeader =request.getHeader("Authorization");
        System.out.println(authHeader);
        if(authHeader!=null && authHeader.startsWith("Basic ")){
            String base64Credentials = authHeader.substring("Basic".length()).trim();
            byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
            String credentials = new String(credDecoded, StandardCharsets.UTF_8);
            System.out.println(credentials);
            String[] credentialsArray = credentials.split(":");
            try {
                authenticationManager.authenticate
                        (new UsernamePasswordAuthenticationToken(credentialsArray[0], credentialsArray[1]));
            }
            catch( Exception e){
                response.sendError(HttpStatus.UNAUTHORIZED.value(),"Invalid Login Credentials");
            }
            filterChain.doFilter(request,response);
        }
        else{
            response.sendError(HttpStatus.BAD_REQUEST.value(),"Login Credentials Not Found");
        }
    }
}
