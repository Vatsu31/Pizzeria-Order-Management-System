# Pizzeria Order Management System

## Overview
This project is a **JavaFX-based order management system** designed for **RU Pizzeria**, a fictional pizza restaurant. The system allows staff to manage pizza orders, including taking, placing, and canceling orders. It supports two pizza styles (**Chicago** and **New York**) and four pizza types (**Deluxe**, **BBQ Chicken**, **Meatzza**, and **Build Your Own**). The system also handles customization of toppings, calculates prices dynamically, and exports order details to a text file.

This project was developed as part of the **CS213 Fall 2024** course, demonstrating proficiency in **Java**, **JavaFX**, **object-oriented design**, and **software development best practices**.

---

## Key Features
- **Pizza Customization**:
  - Choose between **Chicago-style** and **New York-style** pizzas.
  - Select from **Deluxe**, **BBQ Chicken**, **Meatzza**, or **Build Your Own** pizza types.
  - Customize **Build Your Own** pizzas with up to **7 toppings** from a list of 13 available toppings.
  - Dynamically calculate and display the **subtotal**, **sales tax**, and **order total**.

- **Order Management**:
  - Add multiple pizzas to a single order.
  - Remove individual pizzas or clear the entire order before placing it.
  - View detailed order summaries, including pizza style, crust, toppings, and pricing.
  - Cancel existing orders and update the order list in real-time.

- **Order Export**:
  - Export all placed orders to a **text file** for record-keeping.
  - The exported file includes the **order number**, **list of pizzas**, and **order total**.

- **User-Friendly GUI**:
  - Intuitive navigation with **three distinct views**:
    1. **Ordering View**: Select pizza style, type, and size; customize toppings.
    2. **Order Summary View**: Review the current order, remove pizzas, or place the order.
    3. **Order History View**: Browse all placed orders, cancel orders, and export orders.
  - Built using **JavaFX** with **FXML** for clean separation of UI and logic.

- **Object-Oriented Design**:
  - Utilizes the **Abstract Factory** design pattern to create pizza objects based on style and type.
  - Implements an abstract `Pizza` class with subclasses for each pizza type (`Deluxe`, `BBQChicken`, `Meatzza`, `BuildYourOwn`).
  - Includes an `Order` class to manage orders with unique order numbers.

---

## Technologies Used
- **Programming Language**: Java
- **GUI Framework**: JavaFX
- **Build Tool**: Maven (or Gradle, if applicable)
- **Testing Framework**: JUnit (for unit testing the `BuildYourOwn` class)
- **Documentation**: Javadoc
- **Design Patterns**: Abstract Factory, Object-Oriented Programming (OOP)
