package com.example.rupizzeria;

/**
 * Class managing orders and order history.
 * Handles the current order and the overall order history.
 *
 * @author Pranav Chundi
 * @version 1.0
 * @since 2024-11-17
 */
public class StoreManager {
    private static StoreManager instance = new StoreManager();
    private Order currentOrder;
    private OrderHistory orderHistory;

    /**
     * Constructor initializing the order history.
     */
    private StoreManager() {
        orderHistory = new OrderHistory();
    }

    /**
     * Retrieves the single instance of StoreManager.
     *
     * @return the instance of StoreManager
     */
    public static StoreManager getInstance() {
        return instance;
    }

    /**
     * Gets the current order. Creates a new one if it does not exist.
     *
     * @return the current order
     */
    public Order getCurrentOrder() {
        if (currentOrder == null) {
            currentOrder = new Order();
        }
        return currentOrder;
    }

    /**
     * Places the current order by adding it to the order history.
     * Resets the current order afterwards.
     */
    public void placeCurrentOrder() {
        if (currentOrder != null && !currentOrder.getPizzas().isEmpty()) {
            orderHistory.addOrder(currentOrder);
            currentOrder = null;
        }
    }

    /**
     * Retrieves the order history for managing past orders.
     *
     * @return the order history
     */
    public OrderHistory getOrderHistory() {
        return orderHistory;
    }
}
