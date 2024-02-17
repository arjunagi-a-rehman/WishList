package com.example.UserWishList.Mapper;

import com.example.UserWishList.models.Users;
import com.example.UserWishList.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserNameToUserDetails implements UserDetailsService {
    @Autowired
    private UserRepo usersRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users=usersRepo.findByEmail(email).orElseThrow();
        return new UserToUserDetails(users);
    }
}