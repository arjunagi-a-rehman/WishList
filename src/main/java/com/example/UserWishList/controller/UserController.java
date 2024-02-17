package com.example.UserWishList.controller;

import com.example.UserWishList.Dtos.ErrorResponseDto;
import com.example.UserWishList.Dtos.ResponseDto;
import com.example.UserWishList.Dtos.UserDto;
import com.example.UserWishList.Dtos.WishListItemDto;
import com.example.UserWishList.services.IUserServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CURD API's for User",
        description = "CURD REST API's in Users to Create,Update, Read and delete Users here only user/register is open to all else other apis required admin role "
)
@SecurityRequirement(name = "javainuseapi")
@RestController
@RequestMapping(path = "/api/vo/user",produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class UserController {
    private IUserServices userServices;

    @Operation(
            summary = "Create User/register REST API",
            description = "API to create new User in system"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED",
                    content = @Content(
                            schema = @Schema(implementation = UserDto.class)
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
    @PostMapping("/register")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto user){
        UserDto userDto=userServices.createUser(user);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Fetch User by id",
            description = "API to Fetch A User based on id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = @Content(
                    schema = @Schema(implementation = UserDto.class)
            )
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
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id){
        UserDto user=userServices.getUserById(id);
        return ResponseEntity.ok(user);
    }
    @Operation(
            summary = "Fetch all  users",
            description = "API to Fetch All the Users"
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
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users=userServices.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @Operation(
            summary = "Update User Details REST API",
            description = "API to update User details User id "
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
    @PutMapping("/")
    public ResponseEntity<ResponseDto> updateUser(@RequestBody @Valid UserDto userDto){
        boolean isUpdated= userServices.updateUser(userDto);
        if(!isUpdated){
            return new ResponseEntity<>(new ResponseDto("417","something went wrong please try later"),HttpStatus.EXPECTATION_FAILED);
        }
        return ResponseEntity.ok(new ResponseDto("200","updated successfully"));
    }


    @Operation(
            summary = "delete User  REST API",
            description = "API to delete User based on User id "
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
    public ResponseEntity<ResponseDto> deleteUser(@PathVariable Integer id){
        boolean isDeleted= userServices.deleteUser(id);
        if(!isDeleted){
            return new ResponseEntity<>(new ResponseDto("417","something went wrong please try later"),HttpStatus.EXPECTATION_FAILED);
        }
        return ResponseEntity.ok(new ResponseDto("200","deleted successfully"));
    }


}
