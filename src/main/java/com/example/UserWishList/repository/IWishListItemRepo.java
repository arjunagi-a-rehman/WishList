package com.example.UserWishList.repository;

import com.example.UserWishList.models.Users;
import com.example.UserWishList.models.WishListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IWishListItemRepo extends JpaRepository<WishListItem,Integer> {
    List<WishListItem> findByUsers(Users users);
}
