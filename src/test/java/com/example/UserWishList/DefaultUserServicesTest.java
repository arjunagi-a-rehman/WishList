package com.example.UserWishList;

import com.example.UserWishList.Dtos.UserDto;
import com.example.UserWishList.exceptions.ResourceNotFoundException;
import com.example.UserWishList.exceptions.UserAlreadyExistsException;
import com.example.UserWishList.models.Users;
import com.example.UserWishList.repository.UserRepo;
import com.example.UserWishList.services.Imp.DefaultUserServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Collections;
import java.util.Optional;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DefaultUserServicesTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private DefaultUserServices userServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createUser_Success() {
        // Mocking userDto
        UserDto userDto = new UserDto();
        userDto.setEmail("test@example.com");
        userDto.setPassword("password");

        // Mocking userRepo behavior
        when(userRepo.findByEmail(anyString())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        Users savedUser = new Users();
        savedUser.setEmail(userDto.getEmail());
        savedUser.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // Mocking userRepo.save() to return the populated Users object
        when(userRepo.save(any(Users.class))).thenReturn(savedUser);

        UserDto createdUser = userServices.createUser(userDto);

        // Assertions
        assertNotNull(createdUser);
        assertEquals(userDto.getEmail(), createdUser.getEmail());

    }


    @Test
    void createUser_UserAlreadyExists() {
        UserDto userDto = new UserDto();
        userDto.setEmail("test@example.com");
        userDto.setPassword("password");

        when(userRepo.findByEmail(anyString())).thenReturn(Optional.of(new Users()));

        assertThrows(UserAlreadyExistsException.class, () -> userServices.createUser(userDto));

        verify(userRepo, times(1)).findByEmail(anyString());

        verifyNoMoreInteractions(passwordEncoder);
        verifyNoMoreInteractions(userRepo); // Verifying no more interactions with userRepo
    }


    @Test
    void getAllUsers_Success() {
        when(userRepo.findAll()).thenReturn(Collections.singletonList(new Users()));

        assertEquals(1, userServices.getAllUsers().size());

        verify(userRepo, times(1)).findAll();
    }

    @Test
    void getUserByEmail_Success() {
        String email = "test@example.com";
        Users user = new Users();
        user.setEmail(email);

        when(userRepo.findByEmail(email)).thenReturn(Optional.of(user));

        Users foundUser = userServices.getUserByEmail(email);

        assertNotNull(foundUser);
        assertEquals(email, foundUser.getEmail());

        verify(userRepo, times(1)).findByEmail(email);
    }

    @Test
    void getUserByEmail_UserNotFound() {
        String email = "test@example.com";

        when(userRepo.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userServices.getUserByEmail(email));

        verify(userRepo, times(1)).findByEmail(email);
    }

    @Test
    void getUserById_Success() {
        int userId = 1;
        Users user = new Users();
        user.setId(userId);

        when(userRepo.findById(userId)).thenReturn(Optional.of(user));

        UserDto foundUser = userServices.getUserById(userId);

        assertNotNull(foundUser);
        assertEquals(userId, foundUser.getId());

        verify(userRepo, times(1)).findById(userId);
    }

    @Test
    void getUserById_UserNotFound() {
        int userId = 1;

        when(userRepo.findById(userId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userServices.getUserById(userId));

        verify(userRepo, times(1)).findById(userId);
    }

    @Test
    void updateUser_Success() {
        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setEmail("test@example.com");

        Users existingUser = new Users();
        existingUser.setId(1);

        when(userRepo.findById(1)).thenReturn(Optional.of(existingUser));
        when(userRepo.save(any(Users.class))).thenReturn(existingUser);

        assertTrue(userServices.updateUser(userDto));

        verify(userRepo, times(1)).findById(1);
        verify(userRepo, times(1)).save(any(Users.class));
    }

    @Test
    void updateUser_UserNotFound() {
        UserDto userDto = new UserDto();
        userDto.setId(1);

        when(userRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userServices.updateUser(userDto));

        verify(userRepo, times(1)).findById(1);

        verifyNoMoreInteractions(userRepo); // Verifying no more interactions with userRepo
    }


    @Test
    void deleteUser_Success() {
        int userId = 1;
        Users user = new Users();
        user.setId(userId);

        when(userRepo.findById(userId)).thenReturn(Optional.of(user));

        assertTrue(userServices.deleteUser(userId));

        verify(userRepo, times(1)).findById(userId);
        verify(userRepo, times(1)).delete(user);
    }

    @Test
    void deleteUser_UserNotFound() {
        int userId = 1;

        when(userRepo.findById(userId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userServices.deleteUser(userId));

        verify(userRepo, times(1)).findById(userId);
    }
}
