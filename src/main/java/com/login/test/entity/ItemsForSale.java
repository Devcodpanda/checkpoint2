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


    @ManyToMany(mappedBy = "itemsForSales")
    private List<Consumable> consumables = new ArrayList<>();

    public ItemsForSale() {
    }

    public List<Consumable> getConsumables() {
        return consumables;
    }

    public void setConsumables(List<Consumable> consumables) {
        this.consumables = consumables;
    }

    public ItemsForSale(String name) {
        this.name = name;
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


