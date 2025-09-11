package com.example.gallery.Controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

import com.example.gallery.Model.Customer;
import com.example.gallery.Model.Order;
import com.example.gallery.Model.Product;
import com.example.gallery.View.GalleryView;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class GalleryController {
    protected Stage stage;
    private GalleryView galleryView;
    private Customer customer;
    private Product product;
    private Order order;
    private ArrayList<Customer> customers;
    private ArrayList<Product> products;
    private final ArrayList<Order> orders;

    public GalleryController() {
        this.stage = new Stage();
        this.galleryView = new GalleryView(this);
        this.customer = new Customer();
        this.product = new Product();
        this.order = new Order();
        this.customers = new ArrayList<>();
        this.products = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    public GalleryController(ArrayList<Customer> _customers, ArrayList<Product> _products, ArrayList<Order> _orders) {
        this.stage = new Stage();
        this.galleryView = new GalleryView(this);
        this.customer = new Customer();
        this.product = new Product();
        this.order = new Order();
        this.customers = _customers;
        this.products = _products;
        this.orders = _orders;

    }

    public void addCustomer(String name, String contact, String membership, int balance) {
        Customer newCustomer = new Customer(name, contact, membership, balance);
        customers.add(newCustomer);
    }

    public void removeCustomer(Customer customer) {
        customers.remove(customer);
    }

    public ArrayList<Customer> getCustomerArrayList() {
        return this.customers;
    }

    public String getCustomerList() {
        return this.customers.toString();
    }

    public ArrayList<Order> getOrderArrayList() {
        return this.orders;
    }

    public void addProduct(String title, String type, int price) {
        Product product = new Product(title, type, price);
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public void removeProductButton() {
        String getTitle = galleryView.title_text_field_product.getText();
        String getPrice_string = galleryView.price_text_field_product.getText();
        int getPrice_int = Integer.parseInt(getPrice_string);

        for(int i=0; i<this.getProductArray().size();i++) {
            Product producttoremove = this.getProductArray().get(i);

            if((producttoremove.getTitle()).equals(getTitle) && (producttoremove.getPrice() == getPrice_int)) {
                this.removeProduct(producttoremove);
                i--;
            }
        }
        this.saveProducts();
    }


    public ArrayList<Product> getProductArray() {
        return this.products;
    }

    public String getProductList() {
        return this.products.toString();
    }

    public void addCustomerButton() {
        String getname = galleryView.name_text_field_customer.getText();
        String getcontact = galleryView.contact_text_field_customer.getText();
        String getmembership = galleryView.membership_text_field_customer.getText();
        String getbalance = galleryView.balance_text_field_customer.getText();

        if (getname.isEmpty() || getcontact.isEmpty() || getmembership.isEmpty() || getbalance.isEmpty()) {
            System.out.println("Error: All fields must be filled.");
            return;
        }

        int getinteger;
        try {
            getinteger = Integer.parseInt(getbalance);
            if (getinteger < 0) {
                System.out.println("Error: Balance cannot be negative.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Balance must be a valid number.");
            return;
        }

        this.addCustomer(getname, getcontact, getmembership, getinteger);
    }

    public void removeCustomerButton() {
        String getname = galleryView.name_text_field_customer.getText();
        String getbalance = galleryView.balance_text_field_customer.getText();
        int getinteger = Integer.parseInt(getbalance);

        for (int i = 0 ; i<this.getCustomerArrayList().size(); i++) {
            Customer customertoremove = this.getCustomerArrayList().get(i);

            if ((customertoremove.getName().equals(getname)) && (customertoremove.getBalance() == getinteger)) {
                this.removeCustomer(customertoremove);
                i--;
            }
        }
    }

    public void displayProductComboBox() {
        String selectedTitle = galleryView.productComboBox_product.getValue();

        for(Product product : this.getProductArray()) {
            if (product.getTitle().equals(selectedTitle)) {
                galleryView.title_text_field_product.setText(product.getTitle());
                galleryView.type_text_field_product.setText(product.getType());
                galleryView.price_text_field_product.setText(String.valueOf(product.getPrice()));
                break;
            }
        }
    }

    public void addProductButton() {
        String getTitle = galleryView.title_text_field_product.getText();
        String getType = galleryView.type_text_field_product.getText();
        String getprice_string = galleryView.price_text_field_product.getText();

        if (getTitle.isEmpty() || getType.isEmpty() || getprice_string.isEmpty()) {
            System.out.println("Error: All fields must be filled.");
            return;
        }

        int getprice_int;
        try {
            getprice_int = Integer.parseInt(getprice_string);
            if (getprice_int < 0) {
                System.out.println("Error: Price cannot be negative.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Price must be a valid number.");
            return;
        }



        this.addProduct(getTitle, getType, getprice_int);
        this.saveProducts();


        galleryView.productComboBox_product.getItems().clear();
        for (Product p : this.getProductArray()) {
            galleryView.productComboBox_product.getItems().add(p.getTitle());
        }
    }

    public void updateProductButton() {
        String updatedTitle = galleryView.title_text_field_product.getText();
        String updatedType = galleryView.type_text_field_product.getText();
        String updatePriceString = galleryView.price_text_field_product.getText();
        int updatedPriceInt = Integer.parseInt(updatePriceString);

        String selectedTitle = galleryView.productComboBox_product.getValue();
        for (Product product : this.getProductArray()) {
            if (product.getTitle().equals(selectedTitle)) {
                product.setTitle(updatedTitle);
                product.setType(updatedType);
                product.setPrice(updatedPriceInt);

                galleryView.productComboBox_product.getItems().clear();
                for (Product p : this.getProductArray()) {
                    galleryView.productComboBox_product.getItems().add(p.getTitle());
                }

            }
        }
        StringBuilder productDetails = new StringBuilder();
        for (Product p : this.getProductArray()) {
            productDetails.append("Title: ").append(p.getTitle())
                    .append(" Type: ").append(p.getType())
                    .append(" Price: ").append(p.getPrice())
                    .append("\n");
        }

        galleryView.listing_text_area_product.setText(productDetails.toString());    }

    public void listProductButton() {
        this.loadProducts();
    
        StringBuilder productDetails = new StringBuilder();
        for (Product p : this.getProductArray()) {
            productDetails.append("Title: ").append(p.getTitle())
                          .append(" Type: ").append(p.getType())
                          .append(" Price: ").append(p.getPrice())
                          .append("\n");
        }
    
        galleryView.listing_text_area_product.setText(productDetails.toString());
    
        galleryView.productComboBox_product.getItems().clear();
        for (Product p : this.getProductArray()) {
            galleryView.productComboBox_product.getItems().add(p.getTitle());
        }
    }
    

    public void productComboBox() {
        for (Product product : this.getProductArray()) {
            galleryView.productComboBox_product.getItems().add(product.getTitle());
        }
    }

    public void customerComboBoxOrder() {
        galleryView.customerComboBox_order.getItems().clear();
        for (Customer customer : this.getCustomerArrayList()) {
            galleryView.customerComboBox_order.getItems().add(customer.name);
        }
    }

    public void productComboBoxOrder() {
        galleryView.productComboBox_order.getItems().clear();
        for (Product product : this.getProductArray()) {
            galleryView.productComboBox_order.getItems().add(product.getTitle());
        }
    }

    public void displayCustomerContent() {
        String selected_customer = galleryView.customerComboBox_order.getValue();

        for(Customer customer : this.getCustomerArrayList()) {
            if(customer.getName().equals(selected_customer)) {
                galleryView.customer_name_field_order.setText(customer.getName());
                galleryView.customer_member_field_order.setText(customer.getMembership());
                galleryView.customer_balance_field_order.setText(String.valueOf(customer.getBalance()));

            }
        }
    }

    public void displayProductContent() {
        String selected_product = galleryView.productComboBox_order.getValue();

        for(Product product : this.getProductArray()) {
            if(product.getTitle().equals(selected_product)) {
                galleryView.product_title_field_order.setText(product.getTitle());
                galleryView.product_balance_field_order.setText(String.valueOf(product.getPrice()));
            }
        }
    }

    public void addOrder(Order order) {
        this.getOrderArrayList().add(order);
    }

    public void purchaseButton() {
        String selectedProductTitle = galleryView.productComboBox_order.getValue();
        String selectedCustomerName = galleryView.customerComboBox_order.getValue();
        String today_date = galleryView.date_order_field_order.getText();

        Product selectedProduct = new Product();
        Customer selectedCustomer = new Customer();

        for (Product product : this.getProductArray()) {
            if(product.getTitle().equals(selectedProductTitle)) {
                selectedProduct = product;
            }
        }

        for (Customer customer : this.getCustomerArrayList()) {
            if(customer.getName().equals(selectedCustomerName)) {
                selectedCustomer = customer;
            }
        }

        Order order = new Order(selectedCustomer, selectedProduct, today_date);
        boolean status = order.canBuy();
        if (status) {
            galleryView.purchaseStatus_order.setText("Purchase successful!");
            order.updateBalance();
            selectedCustomer.ownedProducts.add(selectedProduct);
            this.removeProduct(selectedProduct);
            this.saveProducts();
            this.saveCustomers();
            addOrder(order);
        }
        else {
            galleryView.purchaseStatus_order.setText("Insufficient funds for purchase!");
        }
    }

    public void dateChoice() {
        galleryView.order_display_text_area.clear();
        StringBuilder ordersString = new StringBuilder();
        this.orderByDate();

        for (Order order : this.getOrderArrayList()) {
            ordersString.append(order.toString()).append("\n");
        }
        galleryView.order_display_text_area.setText(ordersString.toString());
    }

    public void productChoice() {
        galleryView.order_display_text_area.clear();
        StringBuilder ordersString = new StringBuilder();
        this.orderByProductName();

        for (Order order : this.getOrderArrayList()) {
            ordersString.append(order.toString()).append("\n");
        }
        galleryView.order_display_text_area.setText(ordersString.toString());
    }

    public void orderByDate() {
        this.getOrderArrayList().sort(Comparator.comparing(Order::getDate)); 
    }

    public void orderByProductName() {
        this.getOrderArrayList().sort(Comparator.comparing(o -> o.getProduct().getTitle()));
    }

    public String dateToday() {
        return java.time.LocalDate.now().toString();
    }

    public void ExitButtonAlert() {
        Alert exit_alert = new Alert(Alert.AlertType.CONFIRMATION);
        exit_alert.setTitle("Exit Confirmation");
        exit_alert.setHeaderText("Do you want to save before exiting?");
        exit_alert.setContentText("Choose an option:");
        ButtonType yes_button = new ButtonType("Yes");
        ButtonType no_button = new ButtonType("No");

        exit_alert.getButtonTypes().setAll(yes_button, no_button);


        Optional<ButtonType> result = exit_alert.showAndWait();

        if (result.isPresent()) {
            if (result.get().equals(yes_button)) {
                this.saveCustomers();
                Platform.exit();
            }
            else if (result.get().equals(no_button)) {
                Platform.exit();
            }
        }
    }

    public void customerListButton() {
        StringBuilder customerInfo = new StringBuilder();

        for (Customer customer : this.getCustomerArrayList()) {
            customerInfo.append("Name: ").append(customer.getName()).append("\n")
                    .append("Contact Information: ").append(customer.getContact()).append("\n")
                    .append("Membership Status: ").append(customer.getMembership()).append("\n")
                    .append("Balance: ").append(customer.getBalance()).append("\n")
                    .append("Products Owned: ");

            // Display products owned
            if (!customer.getOwnedProducts().isEmpty()) {
                for (Product product : customer.getOwnedProducts()) {
                    customerInfo.append(product.getTitle()).append(", ");
                }
                // Remove the last comma and space
                customerInfo.setLength(customerInfo.length() - 2);
            } else {
                customerInfo.append("None");
            }

            customerInfo.append("\n\n"); // Space between customers
        }

        galleryView.display_info_text_area_customer.setText(customerInfo.toString());
    }

    public void loadProducts() {
        products.clear();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Products.ser"))) {
            products = (ArrayList<Product>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Load Error");
            alert.setContentText("Could not load products from file.");
            alert.showAndWait();
        }
    }

    public void saveProducts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Products.ser"))) {
            oos.writeObject(products);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Save Error");
            alert.setContentText("Could not save products to file.");
            alert.showAndWait();
        }
    }

    public void loadCustomers() {
        customers.clear();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Customers.ser"))) {
            customers = (ArrayList<Customer>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Load Error");
            alert.setContentText("Could not load customers from file.");
            alert.showAndWait();
        }
    }

    public void saveCustomers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Customers.ser"))) {
            oos.writeObject(customers);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Save Error");
            alert.setContentText("Could not save customers to file.");
            alert.showAndWait();
        }
    }


    public Stage launch(Stage stage) {
        stage.setTitle("Art Galley");
        stage.setScene(galleryView.mainView());
        return stage;
    }


}
