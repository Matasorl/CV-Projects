package com.example.gallery;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.example.gallery.Controller.GalleryController;
import com.example.gallery.Model.Customer;
import com.example.gallery.Model.Order;
import com.example.gallery.Model.Product;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GalleryControllerTest {
    private GalleryController galleryController;

    // Initialize JavaFX only once before all tests
    @BeforeAll
    public static void setUpOnce() {
        Platform.startup(() -> {});  // Ensures JavaFX toolkit is initialized only once
    }

    @BeforeEach
    public void setUp() {
        Platform.runLater(() -> {
            // Initialize the galleryController here
            galleryController = new GalleryController();
        });
    }

    @Test
    void testAddCustomer() {
        Platform.runLater(() -> {
            galleryController.addCustomer("John Doe", "123456789", "Gold", 500);
            assertEquals(1, galleryController.getCustomerArrayList().size());
            assertEquals("John Doe", galleryController.getCustomerArrayList().get(0).getName());
        });
    }

    @Test
    void testRemoveCustomer() {
        Platform.runLater(() -> {
            Customer customer = new Customer("Jane Doe", "987654321", "Silver", 300);
            galleryController.getCustomerArrayList().add(customer);
            galleryController.removeCustomer(customer);
            assertTrue(galleryController.getCustomerArrayList().isEmpty());
        });
    }

    @Test
    void testAddProduct() {
        Platform.runLater(() -> {
            galleryController.addProduct("Mona Lisa", "Painting", 2000);
            assertEquals(1, galleryController.getProductArray().size());
            assertEquals("Mona Lisa", galleryController.getProductArray().get(0).getTitle());
        });
    }

    @Test
    void testRemoveProduct() {
        Platform.runLater(() -> {
            Product product = new Product("Starry Night", "Painting", 3000);
            galleryController.getProductArray().add(product);
            galleryController.removeProduct(product);
            assertTrue(galleryController.getProductArray().isEmpty());
        });
    }

    @Test
    void testAddOrder() {
        Platform.runLater(() -> {
            Customer customer = new Customer("John Doe", "123456789", "Gold", 500);
            Product product = new Product("The Scream", "Painting", 250);
            Order order = new Order(customer, product, "2025-03-18");
            galleryController.addOrder(order);
            assertEquals(1, galleryController.getOrderArrayList().size());
            assertEquals("The Scream", galleryController.getOrderArrayList().get(0).getProduct().getTitle());
        });
    }

    // New Test: Integrity Check for Customer Data
    @Test
    void testCustomerIntegrity() {
        Platform.runLater(() -> {
            galleryController.addCustomer("Alice Wonderland", "111222333", "Gold", 1000);
            galleryController.addCustomer("Bob Builder", "444555666", "Silver", 500);

            ArrayList<Customer> customers = galleryController.getCustomerArrayList();

            // Check if names and contact numbers are stored correctly
            assertEquals("Alice Wonderland", customers.get(0).getName());
            assertEquals("111222333", customers.get(0).getContact());

            assertEquals("Bob Builder", customers.get(1).getName());
            assertEquals("444555666", customers.get(1).getContact());

            // Ensure that no duplicate customer ID exists
            assertNotEquals(customers.get(0).getContact(), customers.get(1).getContact());
        });
    }

    // New Test: Sorting Customers by Name
    @Test
    void testCustomerSorting() {
        Platform.runLater(() -> {
            galleryController.addCustomer("Charlie Brown", "999888777", "Bronze", 200);
            galleryController.addCustomer("Alice Wonderland", "111222333", "Gold", 1000);
            galleryController.addCustomer("Bob Builder", "444555666", "Silver", 500);

            ArrayList<Customer> customers = galleryController.getCustomerArrayList();

            // Sort customers alphabetically by name
            Collections.sort(customers, Comparator.comparing(Customer::getName));

            // Ensure correct sorting order: Alice -> Bob -> Charlie
            assertEquals("Alice Wonderland", customers.get(0).getName());
            assertEquals("Bob Builder", customers.get(1).getName());
            assertEquals("Charlie Brown", customers.get(2).getName());
        });
    }

    // New Test: Integrity Check for Product Data
    @Test
    void testProductIntegrity() {
        Platform.runLater(() -> {
            galleryController.addProduct("Mona Lisa", "Painting", 2000);
            galleryController.addProduct("The Starry Night", "Painting", 3000);

            ArrayList<Product> products = galleryController.getProductArray();

            // Check if product details are stored correctly
            assertEquals("Mona Lisa", products.get(0).getTitle());
            assertEquals("Painting", products.get(0).getType());
            assertEquals(2000, products.get(0).getPrice());

            assertEquals("The Starry Night", products.get(1).getTitle());
            assertEquals("Painting", products.get(1).getType());
            assertEquals(3000, products.get(1).getPrice());

            // Ensure that product titles are unique
            assertNotEquals(products.get(0).getTitle(), products.get(1).getTitle());
        });
    }

    // New Test: Sorting Products by Title
    @Test
    void testProductSorting() {
        Platform.runLater(() -> {
            galleryController.addProduct("The Starry Night", "Painting", 3000);
            galleryController.addProduct("Mona Lisa", "Painting", 2000);
            galleryController.addProduct("The Scream", "Painting", 250);

            ArrayList<Product> products = galleryController.getProductArray();

            // Sort products alphabetically by title
            Collections.sort(products, Comparator.comparing(Product::getTitle));

            // Ensure correct sorting order: Mona Lisa -> The Scream -> The Starry Night
            assertEquals("Mona Lisa", products.get(0).getTitle());
            assertEquals("The Scream", products.get(1).getTitle());
            assertEquals("The Starry Night", products.get(2).getTitle());
        });
    }
}
