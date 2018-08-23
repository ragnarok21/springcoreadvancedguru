package com.springcoreadvanced.service.impl;

import com.springcoreadvanced.domain.User;
import com.springcoreadvanced.domain.UserDetailsImpl;
import com.springcoreadvanced.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;


@Service(value = "userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    
    
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findUserByName(s);

        if(user == null) {
            throw new UsernameNotFoundException(String.format("The username %s doesn't exist", s));
        }

        UserDetailsImpl userDetails = new UserDetailsImpl();
        userDetails.setEnabled(user.getEnabled());
        userDetails.setPassword(user.getPassword());
        userDetails.setUsername(user.getUsername());

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        userDetails.setAuthorities(authorities);

        return userDetails;
    }
}
