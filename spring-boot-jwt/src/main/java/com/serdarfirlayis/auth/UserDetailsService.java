package com.serdarfirlayis.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final Map<String, String> users = new HashMap<>();

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
    public void init(){
        users.put("serdar", bCryptPasswordEncoder.encode("123"));
    }

    // SpringAuthManager calls this method for authentication
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (users.containsKey(username))
            return new User(username, users.get(username), new ArrayList<>());
        throw new UsernameNotFoundException(username);
    }
}