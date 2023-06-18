package com.orderService.orderService.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.orderService.orderService.entity.Cart;
import com.orderService.orderService.entity.Item;
import com.orderService.orderService.entity.Product;
import com.orderService.orderService.feignclient.ProductClient;
import com.orderService.orderService.repository.CartRepository;
import com.orderService.orderService.utilities.CartUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl  implements CartService{

    @Autowired
    private ProductClient productClient; //feign client

    @Autowired
    private CartRepository cartRepository;

    @Override
    public void addItemToCart(Long userID, Long productId, Integer quantity) throws JsonProcessingException {

        //Find cart
        Cart cart = cartRepository.findByUserID(userID);

        //Find the product which we want to add
        Product product = productClient.getProductById(productId);


        //Create an item
        Item item = new Item(quantity,productId, CartUtilities.getSubTotalForItem(product,quantity));
        //cartRedisRepository.addItemToCart(cartId, item);

       cart.getItems().add(item);

       cartRepository.save(cart);
    }

    @Override
    public List<Item> getCart(Long cartId) {
        //return (List<Item>)cartRepository.getCart(cartId, Item.class);

        Cart cart = cartRepository.findById(cartId).get();

        List<Item> itemList = cart.getItems();
        return itemList;
    }

    @Override
    public void changeItemQuantity(Long cartId, Long productId, Integer quantity) {


//        Cart cart = cartRepository.findById(cartId).get();
//
//        List<Item> itemList = cart.getItems();
//
//        for(Item item : itemList){
//            if((item.getProductID()).equals(productId)){
//                item.setQuantity(quantity);
//                item.setSubTotal(CartUtilities.getSubTotalForItem(item.getProduct(),quantity));
//            }
//        }
    }

    @Override
    public void deleteItemFromCart(Long cartId, Long productId) {

        Cart cart = cartRepository.findById(cartId).get();

        List<Item> itemList = cart.getItems();

       // List<Item> cart = (List) cartRedisRepository.getCart(cartId, Item.class);
        for(Item item : itemList){
            if((item.getProductID()).equals(productId)){
                //cartRedisRepository.deleteItemFromCart(cartId, item);

                itemList.remove(item);
            }
        }

        cart.setItems(itemList);
        cartRepository.save(cart);
    }

    @Override
    public boolean checkIfItemIsExist(Long cartId, Long productId) {

        Cart cart = cartRepository.findById(cartId).get();

        List<Item> itemList = cart.getItems();

       // List<Item> cart = (List) cartRedisRepository.getCart(cartId, Item.class);
        for(Item item : itemList){
            if((item.getProductID()).equals(productId)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Item> getAllItemsFromCart(Long cartId) {

        Cart cart = cartRepository.findById(cartId).get();

        List<Item> itemList = cart.getItems();

        //List<Item> items = (List)cartRedisRepository.getCart(cartId, Item.class);
        return itemList;
    }

    @Override
    public void deleteCart(Long cartId) {

       // cartRedisRepository.deleteCart(cartId);

        Cart cart = cartRepository.findById(cartId).get();

        cartRepository.delete(cart);
    }

    @Override
    public Cart getCartByUserID(Long userID) {
        return cartRepository.findByUserID(userID);
    }

    @Override
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }
}
