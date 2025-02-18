package com.example.rupizzeria;

/**
 * ChicagoPizza class implements the PizzaFactory interface to create various types
 * of Chicago-style pizzas. Each method in this class is responsible for creating a
 * specific type of pizza with predefined crusts and default sizes.
 *
 * @author Pranav Chundi
 * @version 1.0
 * @since 2024-11-17
 */
public class ChicagoPizza implements PizzaFactory {

    /**
     * Creates a Chicago-style Deluxe pizza.
     * The pizza is prepared with a deep-dish crust and a default medium size.
     *
     * @return a new Deluxe pizza instance with Chicago-style deep-dish crust
     */
    @Override
    public Pizza createDeluxe() {
        return new Deluxe(Crust.DEEP_DISH, Size.MEDIUM); // Default size or could be set in controller
    }

    /**
     * Creates a Chicago-style BBQ Chicken pizza.
     * The pizza is prepared with a pan crust and a default medium size.
     *
     * @return a new BBQChicken pizza instance with Chicago-style pan crust
     */
    @Override
    public Pizza createBBQChicken() {
        return new BBQChicken(Crust.PAN, Size.MEDIUM);
    }

    /**
     * Creates a Chicago-style Meatzza pizza.
     * The pizza is prepared with a stuffed crust and a default medium size.
     *
     * @return a new Meatzza pizza instance with Chicago-style stuffed crust
     */
    @Override
    public Pizza createMeatzza() {
        return new Meatzza(Crust.STUFFED, Size.MEDIUM);
    }

    /**
     * Creates a Chicago-style Build Your Own pizza.
     * The pizza is prepared with a pan crust and a default medium size,
     * allowing users to customize toppings later.
     *
     * @return a new BuildYourOwn pizza instance with Chicago-style pan crust
     */
    @Override
    public Pizza createBuildYourOwn() {
        return new BuildYourOwn(Crust.PAN, Size.MEDIUM);
    }
}
