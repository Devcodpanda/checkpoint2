package com.login.test.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Consumable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String designation;
    private Double price;


    @ManyToMany
    @JoinTable(
            name = "consumable_itemsForSale",
            joinColumns = @JoinColumn(
                    name = "consumable_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "itemsForSale_id"))

    private List<ItemsForSale> itemsForSales = new ArrayList<>();

    public Consumable() {
    }

    public List<ItemsForSale> getItemsForSales() {
        return itemsForSales;
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
