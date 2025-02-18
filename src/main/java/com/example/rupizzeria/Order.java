package com.example.rupizzeria;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Represents an order, which contains a unique order number and a list of pizzas.
 * Handles calculations for the order's subtotal, tax, and total.
 *
 * @author  Pranav Chundi
 * @version 1.0
 * @since 2024-11-17
 */
public class Order {
    private static int orderCount = 0; // Static counter for unique order numbers
    private int orderNumber; // Instance variable to hold each order's unique number
    private ArrayList<Pizza> pizzas; // Changed to ArrayList<Pizza>

    /**
     * Constructor for the Order class. This initializes a new order with a
     * unique order number and an empty list of pizzas. Each order is automatically
     * assigned a sequential order number using a static counter, ensuring that every
     * order is uniquely identifiable.
     */
    public Order() {
        this.orderNumber = ++orderCount; // Increment and assign unique order number
        this.pizzas = new ArrayList<>();
    }

    /**
     * Retrieves the unique order number for this order. This number is automatically
     * assigned when the order is created and is used to distinguish it from other orders.
     *
     * @return The unique order number.
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * Retrieves the list of pizzas in this order. This allows access to all pizzas
     * currently added to the order for further operations like modifying, displaying,
     * or calculating totals.
     *
     * @return An ArrayList containing the pizzas in this order.
     */
    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    /**
     * Adds a pizza to the order.
     *
     * @param pizza The pizza to add.
     */
    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    /**
     * Removes a pizza from the order.
     *
     * @param pizza The pizza to remove.
     */
    public void removePizza(Pizza pizza) {
        pizzas.remove(pizza);
    }

    /**
     * Calculates the subtotal of the order by summing the prices of all pizzas.
     *
     * @return The subtotal of the order.
     */
    public double calculateSubtotal() {
        return pizzas.stream().mapToDouble(Pizza::price).sum();
    }

    /**
     * Calculates the tax for the order based on the subtotal.
     *
     * @return The tax amount for the order.
     */
    public double calculateTax() {
        return roundToTwoDecimals(calculateSubtotal() * 0.06625);
    }

    /**
     * Calculates the total cost of the order, including tax.
     *
     * @return The total cost of the order.
     */
    public double calculateTotal() {
        return roundToTwoDecimals(calculateSubtotal() + calculateTax());
    }

    /**
     * Rounds a given double value to two decimal places. This is particularly useful
     * for ensuring monetary values are displayed accurately, without unnecessary precision.
     *
     * @param value The value to be rounded.
     * @return The value rounded to two decimal places.
     */
    private double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    /**
     * Formats a given monetary value into a currency string. The output includes a
     * dollar sign and two decimal places for a clean and professional display.
     *
     * Example: A value of 12.5 will be formatted as "$12.50".
     *
     * @param amount The monetary value to be formatted.
     * @return A string representing the formatted currency value.
     */
    private String formatCurrency(double amount) {
        DecimalFormat df = new DecimalFormat("$#.00");
        return df.format(amount);
    }

    /**
     * Returns a string representation of the order, including all pizzas and the total cost.
     *
     * @return The order details as a string.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order #").append(orderNumber).append("\n");

        for (Pizza pizza : pizzas) {
            sb.append(pizza.toString()).append("\n");
        }

        sb.append("Subtotal: ").append(formatCurrency(calculateSubtotal())).append("\n");
        sb.append("Tax: ").append(formatCurrency(calculateTax())).append("\n");
        sb.append("Total: ").append(formatCurrency(calculateTotal())).append("\n");

        return sb.toString();
    }
}
