package controllers;

import com.example.rupizzeria.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Controller class for the New York-style pizza view.
 * Handles user interactions for creating and customizing New York pizzas.
 *
 * @author  Pranav Chundi
 * @version 1.0
 * @since 2024-11-17
 */
public class NYPizzaController {
    @FXML private ComboBox<String> pizzaTypeComboBox;
    @FXML private RadioButton smallSize, mediumSize, largeSize;
    @FXML private TextField crustField, pizzaPriceField;
    @FXML private ListView<String> availableToppingsList, selectedToppingsList;
    @FXML private ToggleGroup sizeToggleGroup;
    @FXML private ImageView pizzaImage;

    private PizzaFactory pizzaFactory;
    private Pizza currentPizza;
    private StoreManager storeManager;

    /**
     * Initializes the controller with default settings and binds UI components.
     * Sets up pizza type and size selection listeners.
     */
    @FXML
    private void initialize() {
        storeManager = StoreManager.getInstance();
        pizzaFactory = new NYPizza();

        ObservableList<String> pizzaTypes = FXCollections.observableArrayList(
                "Build your own", "Deluxe", "BBQ Chicken", "Meatzza"
        );
        pizzaTypeComboBox.setItems(pizzaTypes);

        pizzaTypeComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                handlePizzaTypeSelection();
            }
        });

        sizeToggleGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                updatePizzaWithNewSize();
            }
        });

        smallSize.setSelected(true);
        pizzaPriceField.setText("0.00");
    }

    /**
     * Updates the current pizza object with the new size selected by the user.
     * Retains toppings if it's a "Build Your Own" pizza.
     */
    private void updatePizzaWithNewSize() {
        if (currentPizza == null || pizzaTypeComboBox.getValue() == null) return;

        ArrayList<Topping> currentToppings = new ArrayList<>();
        if (currentPizza instanceof BuildYourOwn) {
            currentToppings.addAll(currentPizza.getToppings());
        }

        String selectedType = pizzaTypeComboBox.getValue();
        Size newSize = getSelectedSize();

        switch (selectedType) {
            case "Build your own":
                currentPizza = pizzaFactory.createBuildYourOwn();
                currentPizza.setSize(newSize);
                for (Topping topping : currentToppings) {
                    currentPizza.addTopping(topping);
                }
                break;
            case "BBQ Chicken":
                currentPizza = pizzaFactory.createBBQChicken();
                currentPizza.setSize(newSize);
                break;
            case "Deluxe":
                currentPizza = pizzaFactory.createDeluxe();
                currentPizza.setSize(newSize);
                break;
            case "Meatzza":
                currentPizza = pizzaFactory.createMeatzza();
                currentPizza.setSize(newSize);
                break;
        }

        updateDisplay();
    }

    /**
     * Determines the size selected in the UI and returns it.
     * Defaults to SMALL if no size is selected.
     *
     * @return The selected pizza size.
     */
    private Size getSelectedSize() {
        if (mediumSize.isSelected()) return Size.MEDIUM;
        if (largeSize.isSelected()) return Size.LARGE;
        return Size.SMALL;
    }

    /**
     * Handles pizza type selection and creates the corresponding pizza object.
     * Clears or populates toppings based on the pizza type.
     */
    private void handlePizzaTypeSelection() {
        String selectedType = pizzaTypeComboBox.getValue();
        if (selectedType == null) return;

        clearToppingsLists();

        switch (selectedType) {
            case "Build your own":
                currentPizza = pizzaFactory.createBuildYourOwn();
                setImage(" ny_plain.png");
                populateAvailableToppings();
                break;
            case "BBQ Chicken":
                currentPizza = pizzaFactory.createBBQChicken();
                setImage("ny_bbq.png");
                populateSelectedToppingsOnly();
                break;
            case "Deluxe":
                currentPizza = pizzaFactory.createDeluxe();
                setImage(" ny_deluxe.png");
                populateSelectedToppingsOnly();
                break;
            case "Meatzza":
                currentPizza = pizzaFactory.createMeatzza();
                setImage(" ny_meatzza.png");
                populateSelectedToppingsOnly();
                break;
        }

        currentPizza.setSize(getSelectedSize());
        updateDisplay();
    }

    /**
     * Populates the available toppings list for "Build Your Own" pizzas.
     */
    private void populateAvailableToppings() {
        if (currentPizza instanceof BuildYourOwn) {
            BuildYourOwn byo = (BuildYourOwn) currentPizza;
            ObservableList<String> toppings = FXCollections.observableArrayList(
                    byo.getAvailableToppings().stream()
                            .map(Topping::toString)
                            .collect(Collectors.toList())
            );
            availableToppingsList.setItems(toppings);
            selectedToppingsList.setItems(FXCollections.observableArrayList());
        }
    }

    /**
     * Populates the selected toppings list for pre-defined pizza types.
     */
    private void populateSelectedToppingsOnly() {
        availableToppingsList.setItems(FXCollections.observableArrayList());
        ObservableList<String> selectedToppings = FXCollections.observableArrayList(
                currentPizza.getToppings().stream()
                        .map(Topping::toString)
                        .collect(Collectors.toList())
        );
        selectedToppingsList.setItems(selectedToppings);
    }

    /**
     * Clears both the available and selected toppings lists in the UI.
     */
    private void clearToppingsLists() {
        availableToppingsList.setItems(FXCollections.observableArrayList());
        selectedToppingsList.setItems(FXCollections.observableArrayList());
    }

    /**
     * Adds a selected topping from the available list to the current pizza.
     * Displays an alert if the maximum number of toppings (7) is exceeded.
     */
    @FXML
    private void addTopping() {
        if (!(currentPizza instanceof BuildYourOwn)) return;

        String selectedTopping = availableToppingsList.getSelectionModel().getSelectedItem();
        if (selectedTopping == null) return;

        if (currentPizza.getToppings().size() >= 7) {
            showAlert("Maximum 7 toppings allowed.");
            return;
        }

        Topping topping = Topping.valueOf(selectedTopping);
        currentPizza.addTopping(topping);

        availableToppingsList.getItems().remove(selectedTopping);
        selectedToppingsList.getItems().add(selectedTopping);

        updatePrice();
    }

    /**
     * Removes a selected topping from the current pizza and moves it back to the available list.
     */
    @FXML
    private void removeTopping() {
        if (!(currentPizza instanceof BuildYourOwn)) return;

        String selectedTopping = selectedToppingsList.getSelectionModel().getSelectedItem();
        if (selectedTopping == null) return;

        Topping topping = Topping.valueOf(selectedTopping);
        currentPizza.removeTopping(topping);

        selectedToppingsList.getItems().remove(selectedTopping);
        availableToppingsList.getItems().add(selectedTopping);

        updatePrice();
    }

    /**
     * Adds the current pizza to the order and resets the form for a new selection.
     * Displays a success message upon completion.
     */
    @FXML
    private void addToOrder() {
        if (currentPizza == null) {
            showAlert("Please select a pizza type.");
            return;
        }

        storeManager.getCurrentOrder().addPizza(currentPizza);
        showAlert("Pizza added to order successfully!");
        resetForm();
    }

    /**
     * Resets the UI form to its default state, clearing all selections and inputs.
     */
    private void resetForm() {
        pizzaTypeComboBox.setValue(null);
        smallSize.setSelected(true);
        currentPizza = null;
        crustField.clear();
        pizzaPriceField.setText("0.00");
        clearToppingsLists();
    }

    /**
     * Updates the display fields for the current pizza, including crust and price.
     */
    private void updateDisplay() {
        if (currentPizza == null) return;
        crustField.setText(currentPizza.getCrust().toString());
        updatePrice();
    }

    /**
     * Updates the pizza price field dynamically based on the current pizza configuration.
     */
    private void updatePrice() {
        if (currentPizza != null) {
            pizzaPriceField.setText(String.format("%.2f", currentPizza.price()));
        }
    }

    /**
     * Sets the pizza image in the UI based on the selected pizza type.
     * If the image is missing, handles gracefully without crashing.
     *
     * @param imageName The filename of the image to display.
     */
    private void setImage(String imageName) {
        try {
            pizzaImage.setImage(new Image(getClass().getResourceAsStream(
                    "/com/example/rupizzeria/images/" + imageName)));
        } catch (Exception e) {
            System.err.println("Error loading image: " + imageName);
        }
    }

    /**
     * Displays an informational alert to the user.
     *
     * @param message The message to show in the alert dialog.
     */
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}