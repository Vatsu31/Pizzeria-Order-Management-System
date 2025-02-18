package controllers;

import com.example.rupizzeria.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.stream.Collectors;

/**
 * Controller class for managing the current order view.
 * Handles adding, removing, and placing pizzas in the current order.
 *
 * @author  Pranav Chundi
 * @version 1.0
 * @since 2024-11-17
 */

public class CurrentOrderController {
    @FXML private TextField orderNumberField;
    @FXML private TextField subtotalField;
    @FXML private TextField salesTaxField;
    @FXML private TextField orderTotalField;
    @FXML private ListView<String> currentOrderList;

    private StoreManager storeManager;
    private Order currentOrder;

    /**
     * Initializes the current order view and sets up data bindings.
     */
    @FXML
    private void initialize() {
        storeManager = StoreManager.getInstance();
        currentOrder = storeManager.getCurrentOrder();
        setupOrderDisplay();
    }

    /**
     * Sets up the display for the current order, including order number,
     * list of pizzas, and calculated totals.
     */
    private void setupOrderDisplay() {
        // Set order number
        orderNumberField.setText(String.valueOf(currentOrder.getOrderNumber()));

        // Setup ListView with current pizzas
        updateOrderList();

        // Update totals
        updateTotals();
    }

    /**
     * Updates the ListView with the details of all pizzas in the current order.
     */
    private void updateOrderList() {
        ObservableList<String> pizzaDetails = FXCollections.observableArrayList();
        for (Pizza pizza : currentOrder.getPizzas()) {
            pizzaDetails.add(formatPizzaDetails(pizza));
        }
        currentOrderList.setItems(pizzaDetails);
    }

    /**
     * Formats the details of a pizza into a readable string for display.
     *
     * @param pizza the Pizza object to format
     * @return a string representation of the pizza's details
     */
    private String formatPizzaDetails(Pizza pizza) {
        StringBuilder details = new StringBuilder();

        // Determine pizza type and style
        String pizzaType = determinePizzaType(pizza);
        String crust = pizza.getCrust().toString();

        details.append(pizzaType)
                .append(" (")
                .append(determinePizzaStyle(pizza.getCrust()))
                .append(" - ")
                .append(crust)
                .append(") , ");

        // Add toppings
        details.append(String.join(", ",
                pizza.getToppings().stream()
                        .map(Topping::toString)
                        .collect(Collectors.toList())));

        // Add size and price
        details.append(", ")
                .append(pizza.getSize().toString().toLowerCase())
                .append(", $")
                .append(String.format("%.2f", pizza.price()));

        return details.toString();
    }

    /**
     * Determines the style of the pizza based on its crust type.
     *
     * @param pizza the Crust object to evaluate
     * @return a string representing the pizza style ("Chicago Style" or "NY Style, etc ")
     */
    private String determinePizzaType(Pizza pizza) {
        if (pizza instanceof Deluxe) return "Deluxe";
        if (pizza instanceof BBQChicken) return "BBQ Chicken";
        if (pizza instanceof Meatzza) return "Meatzza";
        return "Build your own";
    }

    /**
     * Determines the style of the pizza based on its crust type.
     *
     * @param crust the Crust object to evaluate
     * @return a string representing the pizza style ("Chicago Style" or "NY Style")
     */
    private String determinePizzaStyle(Crust crust) {
        switch (crust) {
            case DEEP_DISH:
            case PAN:
            case STUFFED:
                return "Chicago Style";
            case BROOKLYN:
            case THIN:
            case HAND_TOSSED:
                return "NY Style";
            default:
                return "";
        }
    }

    /**
     * Updates the subtotal, sales tax, and total fields for the current order.
     */
    private void updateTotals() {
        double subtotal = currentOrder.calculateSubtotal();
        double tax = currentOrder.calculateTax();
        double total = currentOrder.calculateTotal();

        subtotalField.setText(String.format("%.2f", subtotal));
        salesTaxField.setText(String.format("%.2f", tax));
        orderTotalField.setText(String.format("%.2f", total));
    }

    /**
     * Removes the selected pizza from the current order.
     * If no pizza is selected, shows an alert.
     */
    @FXML
    private void removePizza() {
        int selectedIndex = currentOrderList.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < currentOrder.getPizzas().size()) {
            currentOrder.getPizzas().remove(selectedIndex);
            updateOrderList();
            updateTotals();
        } else {
            showAlert("Please select a pizza to remove.");
        }
    }

    /**
     * Clears all pizzas from the current order.
     */
    @FXML
    private void clearOrder() {
        currentOrder.getPizzas().clear();
        updateOrderList();
        updateTotals();
    }

    /**
     * Places the current order and resets the view for a new order.
     * If the order is empty, shows an alert.
     */
    @FXML
    private void placeOrder() {
        if (currentOrder.getPizzas().isEmpty()) {
            showAlert("Cannot place an empty order.");
            return;
        }

        storeManager.placeCurrentOrder();
        showAlert("Order placed successfully!");

        // Reset view with new order
        currentOrder = storeManager.getCurrentOrder();
        setupOrderDisplay();
    }

    /**
     * Displays an informational alert with the specified message.
     *
     * @param message the message to display in the alert
     */
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}