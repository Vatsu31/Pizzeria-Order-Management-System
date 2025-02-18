package com.example.rupizzeria;

/**
 * Represents a Meatzza pizza, known for its meat toppings and predefined crust.
 *
 * @author Pranav Chundi
 * @version 1.0
 * @since 2024-11-17
 */
public class Meatzza extends Pizza {

    /**
     * Constructor to create a Meatzza pizza with a specific crust and size.
     *
     * @param crust the type of crust for the Meatzza pizza
     * @param size the size of the Meatzza pizza
     */
    public Meatzza(Crust crust, Size size) {
        super(crust, size);
        getToppings().add(Topping.SAUSAGE);
        getToppings().add(Topping.PEPPERONI);
        getToppings().add(Topping.BEEF);
        getToppings().add(Topping.HAM);
    }

    /**
     * Calculates the price of the Meatzza pizza based on its size.
     *
     * @return the price of the Meatzza pizza
     */
    @Override
    public double price() {
        switch (getSize()) {
            case SMALL: return 17.99;
            case MEDIUM: return 19.99;
            case LARGE: return 21.99;
            default: return 0;
        }
    }
}
