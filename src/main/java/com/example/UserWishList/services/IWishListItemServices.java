package com.example.UserWishList.services;

import com.example.UserWishList.Dtos.WishListItemDto;
import com.example.UserWishList.models.Users;

import java.util.List;

public interface IWishListItemServices {
    WishListItemDto createWishListItem(WishListItemDto wishListItemDto,String email);
    List<WishListItemDto> getAllWishListItemsForUser(String email);
    Boolean deleteWishListItem(Integer id);
}
