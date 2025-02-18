package com.example.rupizzeria;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a customizable "Build Your Own" pizza.
 * Users can add up to 7 toppings from the available list.
 *
 * @author Pranav Chundi
 * @version 1.0
 * @since 2024-11-17
 */
public class BuildYourOwn extends Pizza {
    private static final List<Topping> AVAILABLE_TOPPINGS = Arrays.asList(
            Topping.SAUSAGE, Topping.PEPPERONI, Topping.GREEN_PEPPER, Topping.ONION, Topping.MUSHROOM,
            Topping.BBQ_CHICKEN, Topping.PROVOLONE, Topping.CHEDDAR, Topping.BEEF, Topping.HAM,
            Topping.PINEAPPLE, Topping.OLIVES, Topping.SPINACH // 13 toppings for requirement
    );

    /**
     * Constructor to create a "Build Your Own" pizza with a specific crust and size.
     *
     * @param crust the type of crust for the pizza
     * @param size the size of the pizza
     */
    public BuildYourOwn(Crust crust, Size size) {
        super(crust, size);
    }

    /**
     * Calculates the price of the "Build Your Own" pizza based on size and toppings.
     * Base price depends on size, and each topping adds $1.69.
     *
     * @return the price of the pizza
     */
    @Override
    public double price() {
        double basePrice;
        switch (getSize()) {
            case SMALL: basePrice = 8.99; break;
            case MEDIUM: basePrice = 10.99; break;
            case LARGE: basePrice = 12.99; break;
            default: return 0;
        }
        return basePrice + getToppings().size() * 1.69;
    }

    /**
     * Gets the list of available toppings for the "Build Your Own" pizza.
     * This ensures the staff can choose from the predefined set of toppings for customization.
     *
     * @return a list of all available toppings for "Build Your Own" pizzas
     */
    public List<Topping> getAvailableToppings() {
        return AVAILABLE_TOPPINGS;
    }
}