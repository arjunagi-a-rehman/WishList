package com.example.UserWishList.services.Imp;

import com.example.UserWishList.Dtos.UserDto;
import com.example.UserWishList.Mapper.UserMapper;
import com.example.UserWishList.exceptions.ResourceNotFoundException;
import com.example.UserWishList.exceptions.UserAlreadyExistsException;
import com.example.UserWishList.models.Users;
import com.example.UserWishList.repository.UserRepo;
import com.example.UserWishList.services.IUserServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DefaultUserServices implements IUserServices {
    private UserRepo userRepo;
    @Override
    public String createUser(UserDto userDto) {
        if(userRepo.findByEmail(userDto.getEmail()).isPresent()) // if User already exist with provided email then throw exception
            throw new UserAlreadyExistsException("user Already exist with email: "+userDto.getEmail());
        Users user= UserMapper.UserDtoToUsers(userDto,new Users());
        userRepo.save(user); // Don't Want to provide id to the user for security purposes
        return "Created the user";
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepo.findAll().stream().
                map(users -> UserMapper.UsersToUserDto(users,new UserDto())).
                collect(Collectors.toList()); // get List of users and convert to the userDto
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return UserMapper.UsersToUserDto(userRepo.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("no user Found ","email",email)),new UserDto());
    }

    @Override
    public Boolean updateUser(UserDto userDto) {
        Users user=userRepo.findByEmail(userDto.getEmail()).orElseThrow(()->// Throw Exception if no user Found with provided email
                new ResourceNotFoundException("no user found with ","email",userDto.getEmail()));
        userRepo.save(UserMapper.UserDtoToUsers(userDto,user));
        return true;
    }

    @Override
    public Boolean deleteUser(String email) {
        Users user=userRepo.findByEmail(email).orElseThrow(()->
                new ResourceNotFoundException("no user found with ","email",email));
        userRepo.delete(user);
        return true;
    }
}
