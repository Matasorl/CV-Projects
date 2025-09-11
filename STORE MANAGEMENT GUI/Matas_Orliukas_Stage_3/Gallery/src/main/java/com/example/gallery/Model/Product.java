package com.example.gallery.Model;

import java.io.Serializable;

public class Product implements Serializable {
    private String title;
    private String type;
    private int price;

    public Product() {
        this.title = "";
        this.type = "";
        this.price = 0;
    }

    public Product(String _title, String _type, int _price) {
        this.title = _title;
        this.type = _type;
        this.price = _price;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public String toString() {
        return title + "," + type + "," + price;
    }

    public static Product fromString(String line) {
        String[] parts = line.split(",");


        String title = parts[0];
        String type = parts[1];

        int price;
        try {
            price = Integer.parseInt(parts[2].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid price format. Price must be an integer.");
        }



        return new Product(title, type, price);
    }
}
