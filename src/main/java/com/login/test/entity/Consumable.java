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


    @ManyToMany
    @JoinTable(
            name = "consumable_itemsForSale",
            joinColumns = @JoinColumn(
                    name = "consumable_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "consumable_id"))

    private List<ItemsForSale> itemsForSales = new ArrayList<>();

    public Consumable() {
    }

    public List<ItemsForSale> getItemsForSales() {
        return itemsForSales;
    }

    public void setItemsForSales(List<ItemsForSale> ItemsForSales) {
        this.itemsForSales = itemsForSales;
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

}
