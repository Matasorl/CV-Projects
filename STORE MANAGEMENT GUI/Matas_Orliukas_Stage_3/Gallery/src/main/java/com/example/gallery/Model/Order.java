package com.example.gallery.Model;

import java.time.LocalDate;

public class Order {
    private Customer customer;
    private Product product;
    private String date;

    public Order() {
        this.customer = new Customer();
        this.product = new Product();
        this.date = LocalDate.now().toString();
    }

    public Order(Customer _customer, Product _product, String _date) {
        this.customer = _customer;
        this.product = _product;
        this.date = _date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Product getProduct() {
        return product;
    }

    public String getDate() {
        return date;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean canBuy() {
        boolean res = false;
        double difference = 0;

        String status = this.customer.getMembership();
        double percent = 0;

        switch(status.toLowerCase()) {
            case "gold":
                percent = 0.2;
                break;
            case "silver":
                percent = 0.1;
                break;
            case "bronze":
                percent = 0.05;
                break;
        }

        difference = this.customer.getBalance() - (this.product.getPrice() * (1 - percent));

        if (difference >= 0) {
            res = true;
        }


        return res;
    }

    public void updateBalance() {
        if (canBuy()) {
            this.customer.setBalance(this.customer.getBalance() - this.product.getPrice());
        }
    }

    public String toString() {
        return "Customer:\n" +
                "Name: " + getCustomer().getName() + "\n" +
                "Contact Info: " + getCustomer().getContact() + "\n" +
                "Membership Status: " + getCustomer().getMembership() + "\n" +
                "Balance: " + getCustomer().getBalance() + "\n" +
                "Product: " + getProduct().getTitle() + "\n" +
                "Date: " + getDate();
    }

}
