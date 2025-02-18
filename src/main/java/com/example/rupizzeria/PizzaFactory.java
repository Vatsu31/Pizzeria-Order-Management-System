package com.example.rupizzeria;

/**
 * Interface for creating pizza objects using the Abstract Factory design pattern.
 * Defines methods for creating specific types of pizzas.
 *
 * @author  Pranav Chundi
 * @version 1.0
 * @since 2024-11-17
 */
public interface PizzaFactory {
    /**
     * Creates a Deluxe pizza.
     *
     * @return A new Deluxe pizza.
     */
    Pizza createDeluxe();

    /**
     * Creates a BBQ Chicken pizza.
     *
     * @return A new BBQ Chicken pizza.
     */
    Pizza createBBQChicken();

    /**
     * Creates a Meatzza pizza.
     *
     * @return A new Meatzza pizza.
     */
    Pizza createMeatzza();

    /**
     * Creates a customizable "Build Your Own" pizza.
     *
     * @return A new "Build Your Own" pizza.
     */
    Pizza createBuildYourOwn();
}