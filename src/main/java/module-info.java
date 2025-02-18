module com.example.rupizzeria {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    requires org.junit.jupiter.api;


    opens com.example.rupizzeria to javafx.fxml;
    exports com.example.rupizzeria;
    exports controllers;
    opens controllers to javafx.fxml;
}