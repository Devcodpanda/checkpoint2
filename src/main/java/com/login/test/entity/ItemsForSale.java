package com.login.test.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Entity
public class ItemsForSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String designation;
    private Double price;


    @ManyToMany(mappedBy = "itemsForSales")
    private List<Consumable> consumables = new ArrayList<>();

    public ItemsForSale() {
    }

    public List<Consumable> getConsumables() {
        return consumables;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

