package com.example.UserWishList.controller;

import com.example.UserWishList.Dtos.ResponseDto;
import com.example.UserWishList.Dtos.WishListItemDto;
import com.example.UserWishList.services.IWishListItemServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/vo/wishListItem",produces = {MediaType.APPLICATION_JSON_VALUE})
public class WishListItemController {
    private IWishListItemServices wishListItemServices;

    @PostMapping("/")
    private ResponseEntity<WishListItemDto> createWishList(@RequestBody WishListItemDto wishListItemDto){
        WishListItemDto wishListItemDto1=wishListItemServices.createWishListItem(wishListItemDto);
        return new ResponseEntity<>(wishListItemDto, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    private ResponseEntity<List<WishListItemDto>> getAllWishListForUser(){
        List<WishListItemDto> wishList=wishListItemServices.getAllWishListItemsForUser(null);
        return ResponseEntity.ok(wishList);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<ResponseDto> deleteWishListItem(@PathVariable Integer id){
        boolean isDeleted= wishListItemServices.deleteWishListItem(id);
        if(!isDeleted){
            return new ResponseEntity<>(new ResponseDto("500","something went wrong try later"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(new ResponseDto("200","Wish List Item deleted successfully"));
    }
}
