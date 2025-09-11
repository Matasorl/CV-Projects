package com.example.gallery.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Serializable {
    public String name;
    public String contact;
    public String membership;
    public int balance;
    public ArrayList<Product> ownedProducts;

    // Constructor
    public Customer() {
        this.name = "";
        this.contact = "";
        this.membership = "";
        this.balance = 0;
        this.ownedProducts = new ArrayList<>();
    }

    public Customer(String _name, String _contact, String _membership, int _balance) {
        this.name = _name;
        this.contact = _contact;
        this.membership = _membership;
        this.balance = _balance;
        this.ownedProducts = new ArrayList<>();
    }

    public Customer(String _name, String _contact, String _membership, int _balance, ArrayList<Product> _ownedProducts) {
        this.name = _name;
        this.contact = _contact;
        this.membership = _membership;
        this.balance = _balance;
        this.ownedProducts = _ownedProducts;
    }

    // Getters for the properties
    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getMembership() {
        return membership;
    }

    public int getBalance() {
        return balance;
    }

    public ArrayList<Product> getOwnedProducts() {
        return ownedProducts;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setOwnedProducts(ArrayList<Product> ownedProducts) {
        this.ownedProducts = ownedProducts;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nContact Info: " + contact + "\nMembership Status: " + membership + "\nBalance: " + balance + "\nProduct: " + ownedProducts.toString() + "\n";
    }

    public static Customer fromString(String line) {

        line = line.replace("]", "");
        line = line.replace("[", "");
        line = line.replace("Title: ", "");
        line = line.replace("Type: ", "");
        line = line.replace("Price: ", "");
        String[] parts = line.split(",");

        String name = parts[0];
        String contact = parts[1];
        String membership = parts[2];
        int balance = Integer.parseInt(parts[3]);
        ArrayList<Product> ownedProducts = new ArrayList<>();
        if (parts.length > 4) {
            for (int i = 4; i < parts.length; i += 3) {
                if (i + 2 < parts.length) { // Ensure we have a full product (title, type, price)
                    String title = parts[i].trim();
                    String type = parts[i + 1].trim();
                    int price = Integer.parseInt(parts[i + 2].trim());
                    ownedProducts.add(new Product(title, type, price));
                }
            }
        }



        return new Customer(name, contact, membership, balance, ownedProducts);
    }


}
