package com.orderService.orderService.repository;

import com.orderService.orderService.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository ////newly created
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByUserID(Long userId);

}
