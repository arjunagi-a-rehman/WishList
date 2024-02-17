package com.example.UserWishList.controller;

import com.example.UserWishList.Dtos.ErrorResponseDto;
import com.example.UserWishList.Dtos.ResponseDto;
import com.example.UserWishList.Dtos.WishListItemDto;
import com.example.UserWishList.services.IWishListItemServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;


import java.security.Principal;
import java.util.List;

@Tag(
        name = "CRD API's for WishListItems",
        description = "CRD REST API's in UserWishList to Create, Read and delete WishList Items "
)
@SecurityRequirement(name = "javainuseapi")
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/vo/wishListItem",produces = {MediaType.APPLICATION_JSON_VALUE})
public class WishListItemController {
    private IWishListItemServices wishListItemServices;

    @Operation(
            summary = "Create WishListItem REST API",
            description = "API to create new WishList Item for User"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED",
                    content = @Content(
                    schema = @Schema(implementation = WishListItemDto.class)
            )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PostMapping("/")
    private ResponseEntity<WishListItemDto> createWishList(@RequestBody WishListItemDto wishListItemDto,Principal principal){
        WishListItemDto wishListItemDto1=wishListItemServices.createWishListItem(wishListItemDto,principal.getName());
        return new ResponseEntity<>(wishListItemDto, HttpStatus.CREATED);
    }
//-----------------------------------------------------------------------------------------------------------

    @Operation(
            summary = "Fetch WishList For user",
            description = "API to Fetch All the WishList Items for the User"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/user")
    private ResponseEntity<List<WishListItemDto>> getAllWishListForUser(Principal principal){
        System.out.println(principal.toString());
        List<WishListItemDto> wishList=wishListItemServices.getAllWishListItemsForUser(principal.getName());
        return ResponseEntity.ok(wishList);
    }

//--------------------------------------------------------------------------------------------------

    @Operation(
            summary = "Delete Wish list item REST API",
            description = "API to Delete Wish list item based on wish list item id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @DeleteMapping("/{id}")
    private ResponseEntity<ResponseDto> deleteWishListItem(@PathVariable Integer id){
        boolean isDeleted= wishListItemServices.deleteWishListItem(id);
        if(!isDeleted){
            return new ResponseEntity<>(new ResponseDto("417","something went wrong try later"),HttpStatus.EXPECTATION_FAILED);
        }
        return ResponseEntity.ok(new ResponseDto("200","Wish List Item deleted successfully"));
    }
}
