package com.example.UserWishList.Mapper;

import org.springframework.security.core.GrantedAuthority;

public class RoleToAuthority implements GrantedAuthority {
    private String role;
    public RoleToAuthority(String role){
        this.role=role;
    }
    public void setRole(String role){
        this.role=role;
    }
    @Override
    public String getAuthority() {
        return role;
    }
}