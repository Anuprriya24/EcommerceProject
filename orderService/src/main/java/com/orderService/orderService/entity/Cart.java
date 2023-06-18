package com.orderService.orderService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity ////// New class for mysql implementation
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();


    private Long userID;

    public Long getId() {
        return id;
    }

    public List<Item> getItems() {
        return items;
    }


}
