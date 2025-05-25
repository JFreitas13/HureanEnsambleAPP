package com.svalero.hureanensamble.domain;

import java.io.Serializable;

public class Product implements Serializable {
    private long id;
    private String name;
    private String description;
    private float price;

    public Product(String name, String description, float price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public String getPriceString() {
        String priceString = String.valueOf(price);
        return  priceString;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
