package com.example.rupizzeria;

/**
 * The NYPizza class implements the PizzaFactory interface and is responsible
 * for creating New York-style pizzas. Each method in this class generates
 * a specific type of pizza with crusts that match the distinct New York-style
 * characteristics. The crust options include Brooklyn, thin, and hand-tossed,
 * delivering a variety of iconic New York pizza experiences.
 *
 * This factory encapsulates the creation logic for all New York pizzas, ensuring
 * that the right crust and toppings are pre-defined while still allowing size
 * customization in the controllers.
 *
 * @author Pranav Chundi
 * @version 1.0
 * @since 2024-11-17
 */
public class NYPizza implements PizzaFactory {

    /**
     * Creates a New York-style Deluxe pizza with a Brooklyn crust and medium size.
     * This pizza comes loaded with classic toppings that define the Deluxe variety.
     *
     * @return A Deluxe pizza with a Brooklyn-style crust.
     */
    @Override
    public Pizza createDeluxe() {
        return new Deluxe(Crust.BROOKLYN, Size.MEDIUM);
    }

    /**
     * Creates a New York-style BBQ Chicken pizza with a thin crust and medium size.
     * This pizza features BBQ-inspired flavors paired with a crispy thin crust.
     *
     * @return A BBQ Chicken pizza with a thin crust.
     */
    @Override
    public Pizza createBBQChicken() {
        return new BBQChicken(Crust.THIN, Size.MEDIUM);
    }

    /**
     * Creates a New York-style Meatzza pizza with a hand-tossed crust and medium size.
     * This pizza is loaded with a variety of meats and crafted on a soft yet sturdy crust.
     *
     * @return A Meatzza pizza with a hand-tossed crust.
     */
    @Override
    public Pizza createMeatzza() {
        return new Meatzza(Crust.HAND_TOSSED, Size.MEDIUM);
    }

    /**
     * Creates a New York-style Build Your Own pizza with a hand-tossed crust
     * and medium size. This pizza allows the user to customize their toppings
     * while enjoying the classic hand-tossed base.
     *
     * @return A Build Your Own pizza with a hand-tossed crust.
     */
    @Override
    public Pizza createBuildYourOwn() {
        return new BuildYourOwn(Crust.HAND_TOSSED, Size.MEDIUM);
    }
}
