package com.orderService.orderService.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.orderService.orderService.entity.Cart;
import com.orderService.orderService.entity.Item;
import com.orderService.orderService.http.header.HeaderGenerator;
import com.orderService.orderService.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    private HeaderGenerator headerGenerator;

    @GetMapping (value = "/cart")
    public ResponseEntity<List<Item>> getCart(@RequestHeader(value = "userID") Long userID){
        Cart cart = cartService.getCartByUserID(userID);
        if (cart == null) {
            Cart cart1 = new Cart();
            cart1.setUserID(userID);
            cartService.createCart(cart1);
            cart = cart1;
        }

            return new ResponseEntity<List<Item>>(
                    cart.getItems(),
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.OK);
    }

    @PostMapping(value = "/cart", params = {"productId", "quantity"})
    public ResponseEntity<List<Item>> addItemToCart(
            @RequestParam("productId") Long productId,
            @RequestParam("quantity") Integer quantity,
            @RequestHeader(value = "userID") Long userID,
            HttpServletRequest request) throws JsonProcessingException {
        Cart cart = cartService.getCartByUserID(userID);
        if(cart != null) {
            if(cart.getItems().isEmpty()){
                cartService.addItemToCart(userID, productId, quantity);
            }else{

                    cartService.addItemToCart(cart.getId(), productId, quantity);

            }
            return new ResponseEntity<List<Item>>(
                    cart.getItems(),
                    //headerGenerator.getHeadersForSuccessPostMethod(request, Long.parseLong(cartId)),
                    headerGenerator.getHeadersForSuccessPostMethod(request,cart.getId()),
                    HttpStatus.CREATED);
        }
        return new ResponseEntity<List<Item>>(
                headerGenerator.getHeadersForError(),
                HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/cart", params = "productId")
    public ResponseEntity<Void> removeItemFromCart(
            @RequestParam("productId") Long productId,
            @RequestHeader(value = "Cookie") Long cartId){
        List<Item> cart = cartService.getCart(cartId);
        if(cart != null) {
            cartService.deleteItemFromCart(cartId, productId);
            return new ResponseEntity<Void>(
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<Void>(
                headerGenerator.getHeadersForError(),
                HttpStatus.NOT_FOUND);
    }

}
