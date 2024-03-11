package com.agira.project.services;

import com.agira.project.ExceptionController.UserNotFoundException;
import com.agira.project.models.User;
import com.agira.project.repository.UserReposiotry;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserReposiotry userReposiotry;
    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String email) {
        User user=userReposiotry.findByEmail(email).orElseThrow(()->new UserNotFoundException(String.format("user with email: %s is not found",email)));
        Set<String > set=new HashSet<>();
        set.add("ROLE_ADMIN");
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),userAthority(set));
    }
    public Collection<? extends GrantedAuthority>userAthority(Set<String> roles){
        return  roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}

