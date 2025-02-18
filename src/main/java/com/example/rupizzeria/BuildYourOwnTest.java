package com.example.rupizzeria;

import org.junit.Test;
import static org.junit.Assert.*;

public class BuildYourOwnTest {


    @Test
    public void testAllThirteenToppings() {
        Pizza pizza = new BuildYourOwn(Crust.PAN, Size.LARGE);
        // Try adding all available toppings
        pizza.addTopping(Topping.SAUSAGE);
        pizza.addTopping(Topping.PEPPERONI);
        pizza.addTopping(Topping.GREEN_PEPPER);
        pizza.addTopping(Topping.ONION);
        pizza.addTopping(Topping.MUSHROOM);
        pizza.addTopping(Topping.BBQ_CHICKEN);
        pizza.addTopping(Topping.PROVOLONE);
        pizza.addTopping(Topping.CHEDDAR);
        pizza.addTopping(Topping.BEEF);
        pizza.addTopping(Topping.HAM);
        pizza.addTopping(Topping.PINEAPPLE);
        pizza.addTopping(Topping.OLIVES);
        pizza.addTopping(Topping.SPINACH);
        assertEquals("Should only count first 7 toppings",
                24.82, pizza.price(), 0.001); // Large base + 7 toppings
    }

    @Test
    public void testPriceSmallNoToppings() {
        Pizza pizza = new BuildYourOwn(Crust.PAN, Size.SMALL);
        assertEquals("Small pizza with no toppings should cost $8.99",
                8.99, pizza.price(), 0.001);
    }

    @Test
    public void testPriceMediumWithOneToppings() {
        Pizza pizza = new BuildYourOwn(Crust.PAN, Size.MEDIUM);
        pizza.addTopping(Topping.SAUSAGE);
        assertEquals("Medium pizza with one topping should cost $12.68",
                12.68, pizza.price(), 0.001);
    }

    @Test
    public void testPriceLargeWithMaxToppings() {
        Pizza pizza = new BuildYourOwn(Crust.PAN, Size.LARGE);
        pizza.addTopping(Topping.SAUSAGE);
        pizza.addTopping(Topping.PEPPERONI);
        pizza.addTopping(Topping.GREEN_PEPPER);
        pizza.addTopping(Topping.ONION);
        pizza.addTopping(Topping.MUSHROOM);
        pizza.addTopping(Topping.BBQ_CHICKEN);
        pizza.addTopping(Topping.PROVOLONE);
        assertEquals("Large pizza with seven toppings should cost $24.82",
                24.82, pizza.price(), 0.001);
    }

    @Test
    public void testPriceMediumWithThreeToppings() {
        Pizza pizza = new BuildYourOwn(Crust.PAN, Size.MEDIUM);
        pizza.addTopping(Topping.BEEF);
        pizza.addTopping(Topping.HAM);
        pizza.addTopping(Topping.PINEAPPLE);
        assertEquals("Medium pizza with three toppings should cost $16.06",
                16.06, pizza.price(), 0.001);
    }

    @Test
    public void testPriceRemovingToppings() {
        Pizza pizza = new BuildYourOwn(Crust.PAN, Size.SMALL);
        pizza.addTopping(Topping.SAUSAGE);
        pizza.addTopping(Topping.PEPPERONI);
        pizza.removeTopping(Topping.SAUSAGE);
        assertEquals("Small pizza after removing one topping should cost $10.68",
                10.68, pizza.price(), 0.001);
    }
    @Test
    public void testExceedMaxToppings() {
        Pizza pizza = new BuildYourOwn(Crust.PAN, Size.LARGE);
        // Add 8 toppings (exceeding max of 7)
        pizza.addTopping(Topping.SAUSAGE);
        pizza.addTopping(Topping.PEPPERONI);
        pizza.addTopping(Topping.GREEN_PEPPER);
        pizza.addTopping(Topping.ONION);
        pizza.addTopping(Topping.MUSHROOM);
        pizza.addTopping(Topping.BBQ_CHICKEN);
        pizza.addTopping(Topping.PROVOLONE);
        pizza.addTopping(Topping.CHEDDAR); // Should not be added
        assertEquals("Should not exceed 7 toppings",
                24.82, pizza.price(), 0.001); // Price for 7 toppings only
    }

    @Test
    public void testRemoveNonexistentTopping() {
        Pizza pizza = new BuildYourOwn(Crust.PAN, Size.SMALL);
        pizza.addTopping(Topping.SAUSAGE);
        pizza.removeTopping(Topping.PINEAPPLE); // Try removing topping that wasn't added
        assertEquals("Removing nonexistent topping should not affect price",
                10.68, pizza.price(), 0.001); // Base + 1 topping
    }


    @Test
    public void testSizeChange() {
        Pizza pizza = new BuildYourOwn(Crust.PAN, Size.SMALL);
        pizza.addTopping(Topping.SAUSAGE);
        pizza.setSize(Size.LARGE);
        assertEquals("Price should update when size changes",
                14.68, pizza.price(), 0.001); // Large base + 1 topping
    }



}