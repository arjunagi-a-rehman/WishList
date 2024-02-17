package com.example.UserWishList.services;


import com.example.UserWishList.Dtos.UserDto;
import com.example.UserWishList.models.Users;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface IUserServices {
    UserDto createUser(UserDto userDto);
    List<UserDto> getAllUsers();
    Users getUserByEmail(String email);
    UserDto getUserById(Integer id);
    Boolean updateUser(UserDto userDto);
    Boolean deleteUser(Integer id);
}
