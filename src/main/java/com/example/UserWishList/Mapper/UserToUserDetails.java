package com.example.UserWishList.Mapper;

import com.example.UserWishList.models.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserToUserDetails implements UserDetails {
    private String username;
    private String password;
    private List<GrantedAuthority> authorityList;
    public UserToUserDetails(Users users){
        username=users.getEmail();
        password=users.getPassword();
        String[] roles=users.getRole().split(",");
        authorityList=new ArrayList<>();
        for(String i:roles){
            authorityList.add(new RoleToAuthority(i));
        }
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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