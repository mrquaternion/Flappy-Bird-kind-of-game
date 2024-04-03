import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;

public class Interface extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create the root pane
        Pane root = new Pane();

        // Create a scene with the root pane, specifying its width and height
        Scene scene = new Scene(root, 1000, 1000); // Width: 300, Height: 200

        primaryStage.setScene(scene);
        //Empêche de rendimentionner la fenêtre
        primaryStage.setResizable(false);

        // Set the title of the window (optional)mes
        primaryStage.setTitle("Simple JavaFX Window");

        // Show the primary stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }
}
