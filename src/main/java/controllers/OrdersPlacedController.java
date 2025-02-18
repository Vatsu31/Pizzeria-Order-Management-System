package controllers;

import com.example.rupizzeria.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller class for managing the orders placed view.
 * Handles viewing, canceling, and exporting orders.
 *
 * @author  Pranav Chundi
 * @version 1.0
 * @since 2024-11-17
 */

public class OrdersPlacedController {
    /**
     * ComboBox to select and display order numbers
     */
    @FXML private ComboBox<Integer> orderNumberComboBox;
    /**
     * TextField displaying the total cost of selected order
     */
    @FXML private TextField orderTotalField;
    /**
     * ListView showing pizzas in the selected order
     */
    @FXML private ListView<String> ordersList;

    private StoreManager storeManager;

    /**
     * Initializes controller data and sets up order display.
     * Loads all orders from StoreManager and sets up order selection listeners.
     */
    @FXML
    private void initialize() {
        storeManager = StoreManager.getInstance();


        List<Order> allOrders = storeManager.getOrderHistory().getAllOrders();


        // Initialize the ComboBox with order numbers
        ObservableList<Integer> orderNumbers = FXCollections.observableArrayList();
        for (Order order : allOrders) {
            orderNumbers.add(order.getOrderNumber());
        }
        orderNumberComboBox.setItems(orderNumbers);

        // Add listener for order selection
        orderNumberComboBox.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        displayOrderDetails(newValue);
                    }
                });

        // Select first order if available
        if (!orderNumbers.isEmpty()) {
            orderNumberComboBox.getSelectionModel().select(0);
            displayOrderDetails(orderNumbers.get(0));
        }
    }

    /**
     * Displays the details of the selected order in the UI.
     *
     * @param orderNumber The order number to display.
     */
    private void displayOrderDetails(int orderNumber) {
        Order selectedOrder = findOrder(orderNumber);
        if (selectedOrder == null) {
            return;
        }

        // Update the total field
        double total = selectedOrder.calculateTotal();
        orderTotalField.setText(String.format("%.2f", total));


        // Create an observable list for the order details
        ObservableList<String> pizzaDetails = FXCollections.observableArrayList();

        // Add each pizza's details to the list
        for (Pizza pizza : selectedOrder.getPizzas()) {
            String details = formatPizzaDetails(pizza);

            pizzaDetails.add(details);
        }

        // Set the items to the ListView
        ordersList.setItems(pizzaDetails);
    }

    /**
     * Formats the details of a pizza for display in the orders placed list.
     *
     * @param pizza The pizza object to format.
     * @return A formatted string describing the pizza.
     */
    private String formatPizzaDetails(Pizza pizza) {
        StringBuilder details = new StringBuilder();

        String pizzaType = determinePizzaType(pizza);
        String style = determinePizzaStyle(pizza.getCrust());
        String crust = pizza.getCrust().toString();

        details.append(pizzaType)
                .append(" (")
                .append(style)
                .append(" - ")
                .append(crust)
                .append(") , ");

        // Add toppings
        if (!pizza.getToppings().isEmpty()) {
            details.append(String.join(", ",
                    pizza.getToppings().stream()
                            .map(Topping::toString)
                            .collect(Collectors.toList())));
        }

        // Add size and price
        details.append(", ")
                .append(pizza.getSize().toString().toLowerCase())
                .append(" $")
                .append(String.format("%.2f", pizza.price()));

        return details.toString();
    }

    /**
     * Determines the type of pizza based on its class.
     * This method checks the specific instance of the Pizza object
     * and returns a string representation of the type of pizza.
     *
     * @param pizza the Pizza object to check
     * @return a string representing the type of pizza ("Deluxe", "BBQ Chicken", "Meatzza", or "Build your own")
     */
    private String determinePizzaType(Pizza pizza) {
        if (pizza instanceof Deluxe) return "Deluxe";
        if (pizza instanceof BBQChicken) return "BBQ Chicken";
        if (pizza instanceof Meatzza) return "Meatzza";
        return "Build your own";
    }

    /**
     * Gets pizza style based on crust type.
     * @param crust Crust enum to check style
     * @return "Chicago Style" or "NY Style" based on crust
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
     * Finds order object by order number.
     * @param orderNumber order ID to find
     * @return matching Order or null if not found
     */
    private Order findOrder(int orderNumber) {
        return storeManager.getOrderHistory().getAllOrders()
                .stream()
                .filter(order -> order.getOrderNumber() == orderNumber)
                .findFirst()
                .orElse(null);
    }

    /**
     * Cancels selected order from system.
     * Removes order and updates display.
     */
    @FXML
    private void cancelOrder() {
        Integer selectedOrderNumber = orderNumberComboBox.getValue();
        if (selectedOrderNumber == null) {
            showAlert("Please select an order to cancel.");
            return;
        }

        storeManager.getOrderHistory().cancelOrder(selectedOrderNumber);

        // Update the ComboBox items
        updateOrderNumbers();

        // Clear display if no orders remain
        if (orderNumberComboBox.getItems().isEmpty()) {
            ordersList.setItems(FXCollections.observableArrayList());
            orderTotalField.clear();
        }

        showAlert("Order cancelled successfully.");
    }

    /**
     * Updates order numbers shown in ComboBox.
     * Gets fresh list from StoreManager.
     */
    private void updateOrderNumbers() {
        List<Integer> orderNumbers = storeManager.getOrderHistory().getAllOrders()
                .stream()
                .map(Order::getOrderNumber)
                .collect(Collectors.toList());

        orderNumberComboBox.setItems(FXCollections.observableArrayList(orderNumbers));

        if (!orderNumbers.isEmpty()) {
            orderNumberComboBox.setValue(orderNumbers.get(0));
        }
    }

    /**
     * Exports all orders to text file.
     * Opens file chooser and saves orders.
     */
    @FXML
    private void exportOrders() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export Store Orders");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );

        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            try {
                storeManager.getOrderHistory().exportOrdersToFile(file.getAbsolutePath());
                showAlert("Orders exported successfully.");
            } catch (IOException e) {
                showAlert("Error exporting orders: " + e.getMessage());
            }
        }
    }

    /**
     * Shows alert popup with message.
     * Used for user feedback on actions.
     * @param message text to show in alert
     */
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Store Orders");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}