package com.example.UserWishList.services;


import com.example.UserWishList.Dtos.UserDto;

import java.util.List;

public interface IUserServices {
    String createUser(UserDto userDto);
    List<UserDto> getAllUsers();
    UserDto getUserByEmail(String email);
    Boolean updateUser(UserDto userDto);
    Boolean deleteUser(String email);
}
