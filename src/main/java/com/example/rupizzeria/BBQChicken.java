package com.example.rupizzeria;

/**
 * Represents a BBQ Chicken pizza, which is a specialty pizza with predefined toppings and crust.
 * The crust type is set based on the style, and the price varies depending on the size.
 *
 * @author  Pranav Chundi
 * @version 1.0
 * @since 2024-11-17
 */
public class BBQChicken extends Pizza {

    /**
     * Constructor to create a BBQ Chicken pizza with a specific crust and size.
     *
     * @param crust the type of crust for the BBQ Chicken pizza
     * @param size the size of the BBQ Chicken pizza
     */
    public BBQChicken(Crust crust, Size size) {
        super(crust, size);
        getToppings().add(Topping.BBQ_CHICKEN);
        getToppings().add(Topping.GREEN_PEPPER);
        getToppings().add(Topping.PROVOLONE);
        getToppings().add(Topping.CHEDDAR);
    }

    /**
     * Calculates the price of the BBQ Chicken pizza based on its size.
     *
     * @return the price of the BBQ Chicken pizza
     */
    @Override
    public double price() {
        switch (getSize()) {
            case SMALL: return 14.99;
            case MEDIUM: return 16.99;
            case LARGE: return 19.99;
            default: return 0;
        }
    }
}