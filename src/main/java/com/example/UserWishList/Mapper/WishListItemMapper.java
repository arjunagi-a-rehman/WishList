package com.example.UserWishList.Mapper;

import com.example.UserWishList.Dtos.WishListItemDto;
import com.example.UserWishList.models.WishListItem;

public class WishListItemMapper {
    public static WishListItemDto wishListIntemToWishListItemDto(WishListItem wishListItem,WishListItemDto wishListItemDto){
        wishListItemDto.setNote(wishListItem.getNote());
        wishListItemDto.setTag(wishListItem.getTag());
        wishListItemDto.setProductId(wishListItem.getProductId());
        return wishListItemDto;
    }

    public static WishListItem wishListItemStoToWishListItem(WishListItemDto wishListItemDto,WishListItem wishListItem){
        wishListItem.setNote(wishListItemDto.getNote());
        wishListItem.setTag(wishListItemDto.getTag());
        wishListItem.setProductId(wishListItemDto.getProductId());
        return wishListItem;
    }
}
