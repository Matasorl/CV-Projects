package com.example.gallery.View;

import com.example.gallery.Controller.GalleryController;
import com.example.gallery.Model.Customer;
import com.example.gallery.Model.Order;
import com.example.gallery.Model.Product;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;


public class GalleryView {

    private final ArrayList<Customer> customers = new ArrayList<>();
    private final ArrayList<Product> products = new ArrayList<>();
    private final ArrayList<Order> orders = new ArrayList<>();
    private GalleryController controller;
    Label name_label_customer;
    public TextField name_text_field_customer;
    Label contact_label_customer;
    public TextField contact_text_field_customer;
    Label membership_label_customer;
    public TextField membership_text_field_customer;
    Label balance_label_customer;
    public TextField balance_text_field_customer;
    Button add_button_customer;
    Button remove_button_customer;
    Button list_button_customer;
    Button load_button_customer;
    Button save_button_customer;
    Button exit_button_customer;
    public TextArea display_info_text_area_customer;
    Label title_label_product;
    public TextField title_text_field_product;
    Label type_label_product;
    public TextField type_text_field_product;
    Label price_label_product;
    public TextField price_text_field_product;
    Button add_button_product;
    Button update_button_product;
    Button list_button_product;
    Button save_button_product;
    Button remove_button_product;
    public TextArea listing_text_area_product;
    public ComboBox<String> productComboBox_product;
    Label order_info_label_order;
    Label product_title_label_order;
    public TextField product_title_field_order;
    Label product_balance_label_order;
    public TextField product_balance_field_order;
    Label customer_name_label_order;
    public TextField customer_name_field_order;
    Label customer_member_label_order;
    public TextField customer_member_field_order;
    Label customer_balance_label_order;
    public TextField customer_balance_field_order;
    Label date_of_order_label_order;
    public TextField date_order_field_order;
    Button purchase_button_order;
    public Label purchaseStatus_order;
    public ComboBox<String> customerComboBox_order;
    public ComboBox<String> productComboBox_order;
    Button date_choice_button;
    Button product_name_choice_button;
    public TextArea order_display_text_area;





    public GalleryView(GalleryController _controller) {
        controller = _controller;
        this.name_label_customer = new Label("Name:");
        this.name_text_field_customer = new TextField();
        this.contact_label_customer = new Label("Contact:");
        this.contact_text_field_customer = new TextField();
        this.membership_label_customer = new Label("Membership:");
        this.membership_text_field_customer = new TextField();
        this.balance_label_customer = new Label("Balance:");
        this.balance_text_field_customer = new TextField();
        this.add_button_customer = new Button("Add");
        this.remove_button_customer = new Button("Remove");
        this.list_button_customer = new Button("List");
        this.load_button_customer = new Button("Load");
        this.save_button_customer = new Button("Save");
        this.exit_button_customer = new Button("Exit");
        this.display_info_text_area_customer = new TextArea();
        this.title_label_product = new Label("Title");
        this.title_text_field_product = new TextField();
        this.type_label_product = new Label("Type");
        this.type_text_field_product = new TextField();
        this.price_label_product = new Label("Price");
        this.price_text_field_product = new TextField();
        this.add_button_product = new Button("Add");
        this.update_button_product = new Button("Update");
        this.list_button_product = new Button("List");
        this.save_button_product = new Button("Save");
        this.remove_button_product = new Button("Remove");
        this.listing_text_area_product = new TextArea();
        this.productComboBox_product = new ComboBox<>();
        this.order_info_label_order = new Label("Order info:");
        this.product_title_label_order = new Label("Title");
        this.product_title_field_order = new TextField();
        this.product_balance_label_order = new Label("Balance");
        this.product_balance_field_order = new TextField();
        this.customer_name_label_order = new Label("Name");
        this.customer_name_field_order = new TextField();
        this.customer_member_label_order = new Label("Member Status");
        this.customer_member_field_order = new TextField();
        this.customer_balance_label_order = new Label("Balance");
        this.customer_balance_field_order = new TextField();
        this.date_of_order_label_order = new Label("Date");
        this.date_order_field_order = new TextField(controller.dateToday());
        this.purchase_button_order = new Button("Purchase");
        this.purchaseStatus_order = new Label();
        this.customerComboBox_order = new ComboBox<>();
        this.productComboBox_order = new ComboBox<>();
        this.date_choice_button = new Button("Order by date");
        this.product_name_choice_button = new Button("Order by product name");
        this.order_display_text_area = new TextArea();

    }

    private GridPane createCustomerTabContent() {


        add_button_customer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.addCustomerButton();
            }
        });

        remove_button_customer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.removeCustomerButton();
            }
        });

        load_button_customer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.loadCustomers();
            }
        });

        save_button_customer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.saveCustomers();
            }

        });

        list_button_customer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.customerListButton();
            }
        });

        exit_button_customer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.ExitButtonAlert();
            }
        });



        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(name_label_customer, 0, 0);
        gridPane.add(name_text_field_customer, 1, 0);

        gridPane.add(contact_label_customer, 0, 1);
        gridPane.add(contact_text_field_customer, 1, 1);

        gridPane.add(membership_label_customer, 0, 2);
        gridPane.add(membership_text_field_customer, 1, 2);

        gridPane.add(balance_label_customer, 0, 3);
        gridPane.add(balance_text_field_customer, 1, 3);

        GridPane buttonGroup1 = new GridPane();
        buttonGroup1.setHgap(10);
        buttonGroup1.add(add_button_customer, 0, 0);
        buttonGroup1.add(remove_button_customer, 1, 0);
        buttonGroup1.add(list_button_customer, 2, 0);
        gridPane.add(buttonGroup1, 0, 4, 3, 1);


        gridPane.add(display_info_text_area_customer, 0, 5, 3, 1);

        GridPane buttonGroup2 = new GridPane();
        buttonGroup2.setHgap(10);
        buttonGroup2.add(load_button_customer, 0, 0);
        buttonGroup2.add(save_button_customer, 1, 0);
        buttonGroup2.add(exit_button_customer, 2, 0);
        gridPane.add(buttonGroup2, 2, 6, 3, 1);


        return gridPane;
    }

    private GridPane createProductTabContent() {

        controller.productComboBox();

        productComboBox_product.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.displayProductComboBox();
            }
        });

        add_button_product.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.addProductButton();
            }
        });

        remove_button_product.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.removeProductButton();
            }
        });

        update_button_product.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.updateProductButton();
            }

        });

        list_button_product.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.listProductButton();
            }
        });

        save_button_product.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.saveProducts();
            }
        });


        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(title_label_product, 0, 0);
        gridPane.add(title_text_field_product, 1, 0);

        gridPane.add(type_label_product, 0, 1);
        gridPane.add(type_text_field_product, 1, 1);

        gridPane.add(price_label_product, 0, 2);
        gridPane.add(price_text_field_product, 1, 2);

        gridPane.add(productComboBox_product, 4, 0);


        GridPane buttonGroup1 = new GridPane();
        buttonGroup1.setHgap(10);
        buttonGroup1.add(add_button_product, 0, 0);
        buttonGroup1.add(update_button_product, 1, 0);
        buttonGroup1.add(remove_button_product,2,0);
        buttonGroup1.add(list_button_product, 3, 0);
        buttonGroup1.add(save_button_product, 4, 0);
        gridPane.add(buttonGroup1, 0, 3, 3, 1);

        gridPane.add(listing_text_area_product, 0, 4, 3, 1);

        return gridPane;



    }

    private GridPane createOrderTabContent() {


        controller.customerComboBoxOrder();

        controller.productComboBoxOrder();

        customerComboBox_order.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.displayCustomerContent();
            }
        });

        productComboBox_order.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.displayProductContent();
            }
        });

        purchase_button_order.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.purchaseButton();
            }
        });

        date_choice_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.dateChoice();
            }
        });

        product_name_choice_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.productChoice();
            }
        });

        customer_name_field_order.setEditable(false);
        customer_member_field_order.setEditable(false);
        customer_balance_field_order.setEditable(false);
        product_title_field_order.setEditable(false);
        product_balance_field_order.setEditable(false);
        date_order_field_order.setEditable(true);


        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(customerComboBox_order, 0, 0);
        gridPane.add(productComboBox_order, 1, 0);

        gridPane.add(order_info_label_order, 0, 1);

        gridPane.add(customer_name_label_order, 0, 2);
        gridPane.add(customer_name_field_order, 1, 2);
        gridPane.add(customer_member_label_order, 2, 2);
        gridPane.add(customer_member_field_order, 3, 2);
        gridPane.add(customer_balance_label_order,4, 2);
        gridPane.add(customer_balance_field_order, 5, 2);

        gridPane.add(product_title_label_order, 0, 3);
        gridPane.add(product_title_field_order, 1, 3);
        gridPane.add(product_balance_label_order, 2, 3);
        gridPane.add(product_balance_field_order, 3, 3);

        gridPane.add(date_of_order_label_order, 0, 4);
        gridPane.add(date_order_field_order, 1, 4);

        gridPane.add(purchase_button_order,0 ,5);
        gridPane.add(date_choice_button, 1, 5);
        gridPane.add(product_name_choice_button, 2, 5);
        gridPane.add(purchaseStatus_order, 3, 5);

        gridPane.add(order_display_text_area, 0, 6);

        return gridPane;
    }

    public Scene mainView() {
        controller.loadCustomers();
        controller.loadProducts();

        TabPane tabPane = new TabPane();

        Tab customerTab = new Tab("Customers");
        customerTab.setClosable(false);
        customerTab.setContent(createCustomerTabContent());

        Tab tab2 = new Tab("Order");
        tab2.setClosable(false);
        tab2.setContent(createOrderTabContent());

        Tab tab3 = new Tab("Maintenance");
        tab3.setClosable(false);
        tab3.setContent(createProductTabContent());

        tabPane.getTabs().addAll(customerTab, tab2, tab3);

        Scene scene = new Scene(tabPane, 450, 400);
        scene.getStylesheets().add(getClass().getResource("/com/example/gallery/styles.css").toExternalForm());

        return scene;

    }

}

