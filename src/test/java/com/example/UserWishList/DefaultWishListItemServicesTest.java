package com.example.UserWishList;

import com.example.UserWishList.Dtos.WishListItemDto;
import com.example.UserWishList.exceptions.ResourceNotFoundException;
import com.example.UserWishList.models.Users;
import com.example.UserWishList.models.WishListItem;
import com.example.UserWishList.repository.IWishListItemRepo;
import com.example.UserWishList.services.IUserServices;
import com.example.UserWishList.services.Imp.DefaultWishListItemServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DefaultWishListItemServicesTest {

    @Mock
    private IWishListItemRepo wishListItemRepo;

    @Mock
    private IUserServices userServices;

    @InjectMocks
    private DefaultWishListItemServices wishListItemServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createWishListItemTest() {
        String userEmail = "test@example.com";
        WishListItemDto wishListItemDto = new WishListItemDto();
        wishListItemDto.setProductId(145679); // Setting productId instead of name
        wishListItemDto.setTag("birthday Gifts");
        wishListItemDto.setNote("Only if she agrees to go to Manali");

        Users user = new Users();
        user.setEmail(userEmail);

        WishListItem wishListItem = new WishListItem();
        wishListItem.setProductId(wishListItemDto.getProductId());
        wishListItem.setTag(wishListItemDto.getTag());
        wishListItem.setNote(wishListItemDto.getNote());
        wishListItem.setUsers(user);

        when(userServices.getUserByEmail(userEmail)).thenReturn(user);
        when(wishListItemRepo.save(any(WishListItem.class))).thenReturn(wishListItem);

        WishListItemDto createdWishListItem = wishListItemServices.createWishListItem(wishListItemDto, userEmail);

        assertNotNull(createdWishListItem);
        assertEquals(wishListItemDto.getProductId(), createdWishListItem.getProductId());
        assertEquals(wishListItemDto.getTag(), createdWishListItem.getTag());
        assertEquals(wishListItemDto.getNote(), createdWishListItem.getNote());
        verify(userServices, times(1)).getUserByEmail(userEmail);
        verify(wishListItemRepo, times(1)).save(any(WishListItem.class));
    }


    @Test
    void getAllWishListItemsForUserTest() {
        String userEmail = "test@example.com";

        Users user = new Users();
        user.setEmail(userEmail);

        WishListItem wishListItem = new WishListItem();
        wishListItem.setProductId(12345);
        wishListItem.setUsers(user);

        when(userServices.getUserByEmail(userEmail)).thenReturn(user);
        when(wishListItemRepo.findByUsers(user)).thenReturn(Collections.singletonList(wishListItem));

        List<WishListItemDto> wishListItems = wishListItemServices.getAllWishListItemsForUser(userEmail);

        assertNotNull(wishListItems);
        assertFalse(wishListItems.isEmpty());
        assertEquals(1, wishListItems.size());
        assertEquals(12345, wishListItems.get(0).getProductId());
        verify(userServices, times(1)).getUserByEmail(userEmail);
        verify(wishListItemRepo, times(1)).findByUsers(user);
    }

    @Test
    void deleteWishListItemTest() {
        int itemId = 1;

        when(wishListItemRepo.findById(itemId)).thenReturn(Optional.of(new WishListItem()));

        assertTrue(wishListItemServices.deleteWishListItem(itemId));

        verify(wishListItemRepo, times(1)).findById(itemId);
        verify(wishListItemRepo, times(1)).delete(any(WishListItem.class));
    }

    @Test
    void deleteWishListItem_ItemNotFound() {
        int itemId = 1;

        when(wishListItemRepo.findById(itemId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> wishListItemServices.deleteWishListItem(itemId));

        verify(wishListItemRepo, times(1)).findById(itemId);
        verifyNoMoreInteractions(wishListItemRepo);
    }
}