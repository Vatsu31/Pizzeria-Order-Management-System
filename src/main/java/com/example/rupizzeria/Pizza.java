package com.example.rupizzeria;

import java.util.ArrayList;

/**
 * Abstract base class for representing a pizza.
 * This class defines the common properties for all pizzas, including toppings, crust, and size.
 * Subclasses like Deluxe, BBQChicken, Meatzza, and BuildYourOwn extend this class.
 *
 * @author Pranav Chundi
 * @version 1.0
 * @since 2024-11-17
 */
public abstract class Pizza {
    private ArrayList<Topping> toppings; // Changed to ArrayList<Topping>
    private Crust crust;
    private Size size;

    /**
     * Constructor to initialize a pizza with crust and size.
     *
     * @param crust the type of crust for the pizza
     * @param size the size of the pizza
     */
    public Pizza(Crust crust, Size size) {
        this.crust = crust;
        this.size = size;
        this.toppings = new ArrayList<>();
    }

    /**
     * Gets the crust type of the pizza.
     *
     * @return the crust type
     */
    public Crust getCrust() {
        return crust;
    }

    /**
     * Sets the size of the pizza.
     *
     * @param size the new size to set
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * Gets the size of the pizza.
     *
     * @return the size of the pizza
     */
    public Size getSize() {
        return size;
    }

    /**
     * Gets the list of toppings for the pizza.
     *
     * @return a list of toppings
     */
    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    /**
     * Adds a topping to the pizza, respecting the max toppings limit (if applicable).
     *
     * @param topping the topping to add
     */
    public void addTopping(Topping topping) {
        if (toppings.size() < 7) { // Maximum of 7 toppings for Build Your Own
            toppings.add(topping);
        }
    }

    /**
     * Removes a topping from the pizza.
     *
     * @param topping the topping to remove
     */
    public void removeTopping(Topping topping) {
        toppings.remove(topping);
    }

    /**
     * Abstract method for calculating the price of a pizza.
     * Must be implemented by subclasses.
     *
     * @return the price of the pizza
     */
    public abstract double price();

    /**
     * Converts the pizza details into a readable string.
     *
     * @return a string with pizza details (crust, size, toppings, and price)
     */
    @Override
    public String toString() {
        return "Pizza [crust=" + crust + ", size=" + size + ", toppings=" + toppings + ", price=$" + String.format("%.2f", price()) + "]";
    }
}
