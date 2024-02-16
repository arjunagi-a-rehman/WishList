package com.example.UserWishList.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishListItemDto {
    private Integer wishListItemId;
    private Integer productId;
    private String tag;
    private String note;
}
