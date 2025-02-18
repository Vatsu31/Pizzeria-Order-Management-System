package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main controller class for navigating between views.
 * Handles opening the Chicago, New York, Current Order, and Orders Placed views.
 *
 * @author  Pranav Chundi
 * @version 1.0
 * @since 2024-11-17
 */

public class MainController {

    @FXML
    private ImageView chicagoImage, nyImage, ordersImage, cartImage;

    /**
     * Initializes the main view and loads images for the navigation buttons.
     */
    @FXML
    public void initialize() {
        // Make sure to use the correct paths
        chicagoImage.setImage(new Image(getClass().getResourceAsStream("/com/example/rupizzeria/images/chicago.png")));
        nyImage.setImage(new Image(getClass().getResourceAsStream("/com/example/rupizzeria/images/ny.png")));
        ordersImage.setImage(new Image(getClass().getResourceAsStream("/com/example/rupizzeria/images/orders.png")));
        cartImage.setImage(new Image(getClass().getResourceAsStream("/com/example/rupizzeria/images/cart.png")));
    }

    /**
     * Opens the Chicago-style pizza view in a new stage.
     */
    @FXML
    private void openChicagoPizzaView() throws IOException {
        openNewStage("/com/example/rupizzeria/ChicagoPizzaView.fxml", "Chicago Style Pizza");
    }

    /**
     * Opens the New York-style pizza view in a new stage.
     */
    @FXML
    private void openNYPizzaView() throws IOException {
        openNewStage("/com/example/rupizzeria/NYPizzaView.fxml", "NY Style Pizza");
    }

    /**
     * Opens the orders placed view in a new stage.
     */
    @FXML
    private void openOrdersPlacedView() throws IOException {
        openNewStage("/com/example/rupizzeria/OrdersPlacedView.fxml", "Orders Placed");
    }

    /**
     * Opens the current order view in a new stage.
     */
    @FXML
    private void openCurrentOrderView() throws IOException {
        openNewStage("/com/example/rupizzeria/CurrentOrderView.fxml", "Current Order");
    }

    /**
     * Opens a new stage with the specified FXML file and title.
     *
     * @param fxmlFile The path to the FXML file.
     * @param title    The title of the new stage.
     */
    private void openNewStage(String fxmlFile, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));

        if (fxmlLoader.getLocation() == null) {
            throw new IllegalStateException("FXML file location is not set or could not be found.");
        }

        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.setTitle(title);
        stage.show();
    }
}
