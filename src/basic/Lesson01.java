package basic;

// Import necessary JavaFX classes
import javafx.application.Application; // The base class for all JavaFX applications.
import javafx.geometry.Pos; // Used to control alignment of elements in a layout.
import javafx.scene.Scene; // Represents the container for all visual content in a window.
import javafx.scene.control.Button; // A JavaFX control representing a clickable button.
import javafx.scene.control.Label; // A JavaFX control used to display text.
import javafx.scene.layout.VBox; // A layout container that arranges its children vertically.
import javafx.stage.Stage; // Represents the top-level container (the application window).

/**
 * A basic JavaFX application that displays a label and a button.
 */
public class Lesson01 extends Application {

    /**
     * The `start` method is the entry point for a JavaFX application.
     * It is automatically called by the JavaFX runtime.
     * 
     * @param primaryStage The main application window.
     */
    @Override
    public void start(Stage primaryStage) {
        // Create a label that displays text.
        // `Label` is a simple control for showing non-editable text in the window.
        Label label = new Label("Hello, JavaFX!");

        // Create a button with a label ("Click Me").
        // `Button` is a clickable control that can perform an action when clicked.
        Button button = new Button("Click Me");

        // Create a vertical box layout (VBox) with 10px spacing between elements.
        // VBox arranges its children (here, the label and button) in a vertical column.
        VBox layout = new VBox(10, label, button);

        // Set the alignment of elements within the VBox to the center.
        // `Pos.CENTER` ensures all child elements are centered horizontally and vertically.
        layout.setAlignment(Pos.CENTER);

        // Create a scene to display the layout.
        // A scene is the container for all visual elements in the application.
        // The first parameter is the layout (root node), and the second and third
        // parameters are the width (300px) and height (200px) of the scene.
        Scene scene = new Scene(layout, 300, 200);

        // Set the title of the application window (stage).
        primaryStage.setTitle("Basic JavaFX App");

        // Attach the scene to the stage (application window).
        primaryStage.setScene(scene);

        // Show the stage, making the window visible to the user.
        primaryStage.show();
    }

    /**
     * The main method is the standard entry point for Java applications.
     * In JavaFX applications, it is used to launch the JavaFX runtime.
     * 
     * @param args Command-line arguments passed to the program (not used here).
     */
    public static void main(String[] args) {
        // Launch the JavaFX application. This will call the `start` method automatically.
        launch(args);
    }
}