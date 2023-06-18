package com.orderService.orderService.controller;

import com.orderService.orderService.feignclient.UserClient;
import com.orderService.orderService.utilities.OrderUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.orderService.orderService.http.header.HeaderGenerator;
import com.orderService.orderService.service.CartService;
import com.orderService.orderService.service.OrderService;
import com.orderService.orderService.entity.*;
@RestController
public class OrderController {

    @Autowired
    private UserClient userClient;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private HeaderGenerator headerGenerator;

    @PostMapping(value = "/order/{userId}")
    public ResponseEntity<Order> saveOrder(
            @PathVariable("userId") Long userId,
            HttpServletRequest request){

        Cart cart = cartService.getCartByUserID(userId);
        if(cart != null) {
            Order order = this.createOrder(cart.getItems(), userId);
            try{
                System.out.println("***** Created order "+order);
               Order order1  = orderService.saveOrder(order);
                System.out.println(order1);
                // cartService.deleteCart();
                return new ResponseEntity<Order>(
                        order,
                        headerGenerator.getHeadersForSuccessPostMethod(request, order.getId()),
                        HttpStatus.CREATED);
            }catch (Exception ex){
                ex.printStackTrace();
                return new ResponseEntity<Order>(
                        headerGenerator.getHeadersForError(),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Order>(
                headerGenerator.getHeadersForError(),
                HttpStatus.NOT_FOUND);
    }

    private Order createOrder(List<Item> items, Long userID) {
        Order order = new Order();
//        List<Item> items = new ArrayList<>();
//        for(int i = 0; i < cart.size(); i++){
//            items.add(cart.get(i));
//        }

        List<Item> itemsCopy = new ArrayList<>(items);

        order.setItems(itemsCopy);
        order.setUserID(userID);
        order.setTotal(OrderUtilities.countTotalPrice(itemsCopy));
        order.setOrderedDate(LocalDate.now());
        order.setStatus("PAYMENT_EXPECTED");
        return order;
    }
}
