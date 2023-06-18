package com.orderService.orderService.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Entity
public class Product {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @JsonIgnore
    private Long productId;

//    @Transient
    private Long id;

    private String productName;

    private BigDecimal price;

//    @OneToMany (mappedBy = "product", cascade = CascadeType.ALL)
//    @JsonIgnore
    private Item item;

}
