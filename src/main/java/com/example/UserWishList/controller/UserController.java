package com.example.UserWishList.controller;

import com.example.UserWishList.Dtos.ResponseDto;
import com.example.UserWishList.Dtos.UserDto;
import com.example.UserWishList.services.IUserServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/vo/user",produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class UserController {
    private IUserServices userServices;
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
        UserDto userDto=userServices.createUser(user);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id){
        UserDto user=userServices.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users=userServices.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/")
    public ResponseEntity<ResponseDto> updateUser(@RequestBody UserDto userDto){
        boolean isUpdated= userServices.updateUser(userDto);
        if(!isUpdated){
            return new ResponseEntity<>(new ResponseDto("500","something went wrong please try later"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(new ResponseDto("200","updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteUser(@PathVariable Integer id){
        boolean isDeleted= userServices.deleteUser(id);
        if(!isDeleted){
            return new ResponseEntity<>(new ResponseDto("500","something went wrong please try later"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(new ResponseDto("200","deleted successfully"));
    }


}
