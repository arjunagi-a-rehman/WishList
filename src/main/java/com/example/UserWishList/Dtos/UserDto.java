package com.example.UserWishList.Dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "User",
        description = "This schema holds the User data"
)
public class UserDto {
    private Integer id;
    @NotEmpty(message = "user must have some name")
    @Schema(
            description = "users name",
            example = "Jon Targaryen"
    )
    private String name;
    @Email(message = "their must be proper email")
    @Schema(
            description = "users email",
            example = "jon123@crow.com"
    )
    private String email;

    @Schema(
            description = "users Password Their must be 1 Upper case,1 Lower case, 1 special char, 1 number and total length must be >=8",
            example = "Love1@Wildlings"
    )
    private String password;
    private String role;
}
