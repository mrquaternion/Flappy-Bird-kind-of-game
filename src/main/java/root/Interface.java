import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;

import character.Enemy;
import character.collision.ImageBounds;

public class Interface extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create the root pane
        Pane root = new Pane();

        // Create an enemy object and an image view
        Enemy enemy = new Enemy();
        ImageView imageView = new ImageView(enemy.characterImage());

        // Create a rectangle object and add to pane
        Rectangle rectangle = ImageBounds.imageBoundsCalculator(enemy.characterImage());
        root.getChildren().add(rectangle);
        root.getChildren().add(imageView);

        // Create a scene with the root pane, specifying its width and height
        Scene scene = new Scene(root, 1000, 1000); // Width: 1000, Height: 1000

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
