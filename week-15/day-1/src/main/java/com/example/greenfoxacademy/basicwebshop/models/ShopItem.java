package com.example.greenfoxacademy.basicwebshop.models;

import java.text.DecimalFormat;

public class ShopItem {

    protected String name;
    protected String description;
    protected double price;
    protected int quantityOfStock;
    protected String type;


    public ShopItem(String name, String description, double price, int quantityOfStock, String type) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantityOfStock = quantityOfStock;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantityOfStock() {
        return quantityOfStock;
    }

    public void setQuantityOfStock(int quantityOfStock) {
        this.quantityOfStock = quantityOfStock;
    }

    public String getPriceInEuro() {

        DecimalFormat df = new DecimalFormat ("0.00");
        double exchangeRate = 27.23;
        double result = (price / exchangeRate);

        return  df.format(result);
    }
}
