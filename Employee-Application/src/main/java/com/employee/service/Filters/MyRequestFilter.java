package com.employee.service.Filters;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
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

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            System.out.println(request);
            String authHeader = request.getHeader("Authorization");
            System.out.println(authHeader);
            String base64Credentials = authHeader.substring("Basic".length()).trim();
            byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
            String credentials = new String(credDecoded, StandardCharsets.UTF_8);
            System.out.println(credentials);
            String[] credentialsArray = credentials.split(":");
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentialsArray[0], credentialsArray[1]));
            filterChain.doFilter(request, response);
        } catch (ServletException | IOException | InternalAuthenticationServiceException | BadCredentialsException e) {
            throw new BadCredentialsException("Authorization Failed");
        }
    }
}

