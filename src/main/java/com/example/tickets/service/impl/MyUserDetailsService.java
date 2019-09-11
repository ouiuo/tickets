package com.example.tickets.service.impl;

import com.example.tickets.entity.UserForAuth;
import com.example.tickets.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UsersService usersService;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserForAuth userForAuth = usersService.getUserByLogin(s);

        if (userForAuth == null) {
            throw new IllegalArgumentException("Wrong login");
        }

        return new org.springframework.security.core.userdetails.User(userForAuth.getLogin(), userForAuth.getPassword(), getGrantedAuthority(userForAuth));
    }

    private Collection<GrantedAuthority> getGrantedAuthority(UserForAuth user) {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        grantedAuthorities.add(user.getRoleEntity().getName().equals("ADMIN") ?
                new SimpleGrantedAuthority("ROLE_ADMIN") :
                new SimpleGrantedAuthority("ROLE_USER"));

        return grantedAuthorities;
    }

}
