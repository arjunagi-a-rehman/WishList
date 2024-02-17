package com.example.UserWishList.services.Imp;

import com.example.UserWishList.Dtos.UserDto;
import com.example.UserWishList.Mapper.UserMapper;
import com.example.UserWishList.exceptions.ResourceNotFoundException;
import com.example.UserWishList.exceptions.UserAlreadyExistsException;
import com.example.UserWishList.models.Users;
import com.example.UserWishList.repository.UserRepo;
import com.example.UserWishList.services.IUserServices;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DefaultUserServices implements IUserServices {
    private PasswordEncoder passwordEncoder;
    private UserRepo userRepo;
    @Override
    public UserDto createUser(UserDto userDto) {
        if(userRepo.findByEmail(userDto.getEmail()).isPresent()) // if User already exist with provided email then throw exception
            throw new UserAlreadyExistsException("user Already exist with email: "+userDto.getEmail());
        Users user= UserMapper.UserDtoToUsers(userDto,new Users());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user=userRepo.save(user);
        return UserMapper.UsersToUserDto(user,new UserDto());
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepo.findAll().stream().
                map(users -> UserMapper.UsersToUserDto(users,new UserDto())).
                collect(Collectors.toList()); // get List of users and convert to the userDto
    }

    @Override
    public Users getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("no user Found ","email",email));
    }

    @Override
    public UserDto getUserById(Integer id) {
        return UserMapper.UsersToUserDto(userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("no user found with","id",id+"")),new UserDto());
    }

    @Override
    public Boolean updateUser(UserDto userDto) {
        Users user=userRepo.findById(userDto.getId()).orElseThrow(()->// Throw Exception if no user Found with provided id
                new ResourceNotFoundException("no user found with ","id",userDto.getId()+""));
        userRepo.save(UserMapper.UserDtoToUsers(userDto,user));
        return true;
    }

    @Override
    public Boolean deleteUser(Integer id) {
        Users user=userRepo.findById(id).orElseThrow(()->
                new ResourceNotFoundException("no user found with ","id",id+""));
        userRepo.delete(user);
        return true;
    }
}
