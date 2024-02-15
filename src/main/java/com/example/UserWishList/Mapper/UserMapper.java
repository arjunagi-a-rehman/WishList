package com.example.UserWishList.Mapper;

import com.example.UserWishList.Dtos.UserDto;
import com.example.UserWishList.models.Users;

public class UserMapper {
    public static UserDto UsersToUserDto(Users user,UserDto userDto){
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setRole(userDto.getRole());
        return userDto;
    }

    public static Users UserDtoToUsers(UserDto userDto,Users user){
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setRole(userDto.getRole());
        user.setPassword(userDto.getPassword());
        return user;
    }
}
