package com.ebay.inventory.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemId;
    private String title;
    private String itemCondition;
    private BigDecimal price;
    private int quantity;
    @ElementCollection
    @CollectionTable(name="listOfImageURL")
    private List<String> imageURL;
    private String itemSpecifics;
    private String description;
}
