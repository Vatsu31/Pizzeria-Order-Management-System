package com.example.rupizzeria;

/**
 * Represents a Deluxe pizza, which comes with predefined crust and toppings.
 *
 * @author Pranav Chundi
 * @version 1.0
 * @since 2024-11-17
 */
public class Deluxe extends Pizza {

    /**
     * Constructor to create a Deluxe pizza with a specific crust and size.
     *
     * @param crust the type of crust for the Deluxe pizza
     * @param size the size of the Deluxe pizza
     */
    public Deluxe(Crust crust, Size size) {
        super(crust, size);
        getToppings().add(Topping.SAUSAGE);
        getToppings().add(Topping.PEPPERONI);
        getToppings().add(Topping.GREEN_PEPPER);
        getToppings().add(Topping.ONION);
        getToppings().add(Topping.MUSHROOM);
    }

    /**
     * Calculates the price of the Deluxe pizza based on its size.
     *
     * @return the price of the Deluxe pizza
     */
    @Override
    public double price() {
        switch (getSize()) {
            case SMALL: return 16.99;
            case MEDIUM: return 18.99;
            case LARGE: return 20.99;
            default: return 0;
        }
    }
}
