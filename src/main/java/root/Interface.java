
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import character.Enemy;
import character.physics.Gravity;

public class Interface extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create the root pane
        Pane root = new Pane();

        // Create a background object and an image view
        Image background = new Image("file:src/main/resources/bg.png");
        ImageView imageViewBackground = new ImageView(background);

        // Create an enemy object and an image view
        Enemy enemy = new Enemy();
        Image imageEnemy = enemy.characterImage();
        ImageView imageViewEnemy = new ImageView(imageEnemy);
        imageViewEnemy.setFitWidth(imageEnemy.getWidth() * 0.45);
        imageViewEnemy.setFitHeight(imageEnemy.getHeight() * 0.45);
        imageViewEnemy.setPreserveRatio(true);
        imageViewEnemy.setY(315);
        imageViewEnemy.setX(50);

        // Setting the background image and the enemy image to the root pane
        root.getChildren().add(imageViewBackground);
        root.getChildren().add(imageViewEnemy);

        // Create a scene with the root pane, specifying its width and height
        Scene scene = new Scene(root, 640, 440); // Width: 1000, Height: 1000

        // Apply gravity to the enemy image view
        Gravity gravity = new Gravity();
        gravity.applyGravity(scene, imageViewEnemy);

        // Parameters of the primary stage (scene)
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        // Show the primary stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }
}
