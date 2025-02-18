package com.example.rupizzeria;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the history of all orders placed.
 * Supports adding new orders, canceling orders, and exporting orders to a file.
 *
 * @author  Pranav Chundi
 * @version 1.0
 * @since 2024-11-17
 */
public class OrderHistory {
    private List<Order> orders;

    /**
     * Constructor for the OrderHistory class.
     * Initializes the order history with an empty list of orders to keep track of placed orders.
     */
    public OrderHistory() {
        orders = new ArrayList<>();
    }

    /**
     * Adds an order to the history.
     *
     * @param order The order to add.
     */
    public void addOrder(Order order) {
        orders.add(order);
    }

    /**
     * Cancels an order by its unique order number.
     *
     * @param orderNumber The number of the order to cancel.
     */
    public void cancelOrder(int orderNumber) {
        orders.removeIf(order -> order.getOrderNumber() == orderNumber);
    }

    /**
     * Retrieves all the orders that have been placed so far.
     * This method provides access to the entire order history for review or management.
     *
     * @return a list of all orders in the order history
     */
    public List<Order> getAllOrders() {
        return orders;
    }

    /**
     * Exports all orders to a specified text file.
     *
     * @param fileName The name of the file to export to.
     * @throws IOException If an error occurs during file writing.
     */
    public void exportOrdersToFile(String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Order order : orders) {
                writer.write("Order Number: " + order.getOrderNumber() + "\n");
                writer.write("Pizzas:\n");
                for (Pizza pizza : order.getPizzas()) {
                    writer.write(" - " + pizza.toString() + "\n");
                }
                writer.write("Total: $" + String.format("%.2f", order.calculateTotal()) + "\n");
                writer.write("\n");
            }
        }
    }
}