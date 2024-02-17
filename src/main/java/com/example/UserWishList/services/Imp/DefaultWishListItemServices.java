package com.example.UserWishList.services.Imp;

import com.example.UserWishList.Dtos.WishListItemDto;
import com.example.UserWishList.Mapper.WishListItemMapper;
import com.example.UserWishList.exceptions.ResourceNotFoundException;
import com.example.UserWishList.models.Users;
import com.example.UserWishList.models.WishListItem;
import com.example.UserWishList.repository.IWishListItemRepo;
import com.example.UserWishList.services.IUserServices;
import com.example.UserWishList.services.IWishListItemServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor // for constructor injection of repo
@Service
public class DefaultWishListItemServices implements IWishListItemServices {
    private IWishListItemRepo wishListItemRepo;
    private IUserServices userServices;
    @Override
    public WishListItemDto createWishListItem(WishListItemDto wishListItemDto,String email) {
        WishListItem wishListItem= WishListItemMapper.wishListItemDtoToWishListItem(wishListItemDto,new WishListItem());
        wishListItem.setUsers(userServices.getUserByEmail(email));
        wishListItem=wishListItemRepo.save(wishListItem);
        return WishListItemMapper.wishListIntemToWishListItemDto(wishListItem,wishListItemDto);
    }

    @Override
    public List<WishListItemDto> getAllWishListItemsForUser(String email) {
        Users user=userServices.getUserByEmail(email);
        return wishListItemRepo.findByUsers(user).stream().map(wishListItem -> WishListItemMapper.wishListIntemToWishListItemDto(wishListItem,new WishListItemDto())).collect(Collectors.toList());
    }

    @Override
    public Boolean deleteWishListItem(Integer id) {
        wishListItemRepo.delete(wishListItemRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("no wish list item found with ","id",id+"")));
        return true;
    }
}
