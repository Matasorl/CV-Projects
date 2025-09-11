package com.example.gallery;

import com.example.gallery.Controller.GalleryController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.IOException;

public class GalleryApplication extends Application {
    private GalleryController controller;

    public GalleryApplication() {
        this.controller = new GalleryController();
    }

    public void start(Stage primaryStage) throws IOException {
        Platform.runLater(() ->
                controller.launch(primaryStage).show());

    }

    public static void main(String[] args) {
        launch();
    }

}
