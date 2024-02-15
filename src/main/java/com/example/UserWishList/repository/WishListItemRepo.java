package com.example.UserWishList.repository;

import com.example.UserWishList.models.WishListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListItemRepo extends JpaRepository<WishListItem,Integer> {
}
