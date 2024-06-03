package com.example.jeancraft.Entity;

public class Product {
    private String id;
    private String image;
    private double price;
    private double size;
    private int quantity;
    public Product() {
    }

    public Product(String id, String image, double price, double size, int quantity) {
        this.id = id;
        this.image = image;
        this.price = price;
        this.size = size;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
