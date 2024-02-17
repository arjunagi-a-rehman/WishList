package com.example.UserWishList.Dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "WishListItem",
        description = "This schema holds the WishListItem data"
)
public class WishListItemDto {
    private Integer wishListItemId;
    @NotEmpty(message = "product id cannot be empty")
    @Schema(
            description = "the product you user want to include in wishlist",
            example = "145679"
    )
    private Integer productId;
    @Schema(
            description = "user can provide the tags to wish list items",
            example = "birthday Gifts"
    )
    private String tag;
    @Schema(
            description = "users can provide some note to each wishlist item",
            example = "Only if she agree to go to manali"
    )
    private String note;
}
