package com.example.UserWishList.services;


import com.example.UserWishList.Dtos.UserDto;

import java.util.List;

public interface IUserServices {
    UserDto createUser(UserDto userDto);
    List<UserDto> getAllUsers();
    UserDto getUserByEmail(String email);
    UserDto getUserById(Integer id);
    Boolean updateUser(UserDto userDto);
    Boolean deleteUser(Integer id);
}
