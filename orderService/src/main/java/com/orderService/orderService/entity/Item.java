package com.orderService.orderService.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private int quantity;
    private BigDecimal subTotal;

    private Long productID;

    public Item(int quantity, Long productID, BigDecimal subTotal) {
        this.quantity = quantity;
        this.productID = productID;
        this.subTotal = subTotal;
    }


}
