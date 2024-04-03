import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import character.Enemy;

public class Interface extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create the root pane
        Pane root = new Pane();

        // Create an enemy object and an image view
        Enemy enemy = new Enemy();
        ImageView imageViewEnemy = new ImageView(enemy.characterImage());

        // Add the background image to the root pane
        Image background = new Image("file:src/main/resources/bg.png"); // Replace with your image path
        ImageView imageViewBackground = new ImageView(background);

        // Setting the background image and the enemy image to the root pane
        root.getChildren().add(imageViewEnemy);
        root.getChildren().add(imageViewBackground);

        // Create a scene with the root pane, specifying its width and height
        Scene scene = new Scene(root, 640, 440); // Width: 1000, Height: 1000

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
