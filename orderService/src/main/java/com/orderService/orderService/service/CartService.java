package com.orderService.orderService.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.orderService.orderService.entity.Cart;
import com.orderService.orderService.entity.Item;

import java.util.List;

public interface CartService {

    public void addItemToCart(Long cartId, Long productId, Integer quantity) throws JsonProcessingException;
    public List<Item> getCart(Long cartId);
    public void changeItemQuantity(Long cartId, Long productId, Integer quantity);
    public void deleteItemFromCart(Long cartId, Long productId);
    public boolean checkIfItemIsExist(Long cartId, Long productId);
    public List<Item> getAllItemsFromCart(Long cartId);
    public void deleteCart(Long cartId);

    public Cart getCartByUserID(Long userID);

    public Cart createCart(Cart cart);
}