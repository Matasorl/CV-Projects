module com.example.gallery {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gallery to javafx.fxml;
    exports com.example.gallery;
    exports com.example.gallery.Model;
    opens com.example.gallery.Model to javafx.fxml;
    exports com.example.gallery.Controller;
    opens com.example.gallery.Controller to javafx.fxml;
    exports com.example.gallery.View;
    opens com.example.gallery.View to javafx.fxml;
}