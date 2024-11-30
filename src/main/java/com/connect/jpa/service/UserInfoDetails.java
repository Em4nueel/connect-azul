package com.connect.jpa.service;

import org.springframework.security.core.GrantedAuthority; 
import org.springframework.security.core.authority.SimpleGrantedAuthority; 
import org.springframework.security.core.userdetails.UserDetails;

import com.connect.jpa.model.Usuario;

import java.util.Arrays; 
import java.util.Collection; 
import java.util.List; 
import java.util.stream.Collectors; 


public class UserInfoDetails implements UserDetails { 
    private static final long serialVersionUID = 1L;
    
	private String name; 
    private String password; 
    private List<GrantedAuthority> authorities; 
    public UserInfoDetails(Usuario userInfo) { 
        name = userInfo.getNome(); 
        password = userInfo.getSenha(); 
        authorities = Arrays.stream(userInfo.getRoles().split(",")) 
                .map(SimpleGrantedAuthority::new) 
                .collect(Collectors.toList()); 
    } 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { 
        return authorities; 
    } 
    @Override
    public String getPassword() { 
        return password; 
    } 
    @Override
    public String getUsername() { 
        return name; 
    } 
    @Override
    public boolean isAccountNonExpired() { 
        return true; 
    } 
    @Override
    public boolean isAccountNonLocked() { 
        return true; 
    } 
    @Override
    public boolean isCredentialsNonExpired() { 
        return true; 
    } 
    @Override
    public boolean isEnabled() { 
        return true; 
    } 
}