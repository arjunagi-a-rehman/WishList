package com.example.UserWishList.Dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishListItemDto {
    private Integer wishListItemId;
    @NotEmpty
    private Integer productId;
    private String tag;
    private String note;
}
