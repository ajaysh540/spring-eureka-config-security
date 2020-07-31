package com.manager.service.Service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MyUsers implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        if(s.equals("manager")){
            grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
            return new User("manager","manager",grantedAuthorityList);
        }
        else return null;
    }
}

