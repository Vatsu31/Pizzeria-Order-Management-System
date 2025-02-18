package controllers;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main application class for RU Pizza.
 * Launches the main GUI and initializes the application.
 *
 * @author Pranav Chundi
 * @version 1.0
 * @since 2024-11-17
 */
public class PizzaMain extends Application {

    /**
     * Entry point for the JavaFX application.
     * Loads the main view (MainView.fxml) and sets up the primary stage.
     *
     * @param primaryStage the primary stage for this application
     * @throws Exception if there is an issue loading the FXML file
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/rupizzeria/MainView.fxml"));
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setTitle("RU Pizza");
        primaryStage.show();
    }

    /**
     * Main method to launch the application.
     *
     * @param args command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        launch(args);
    }
}
