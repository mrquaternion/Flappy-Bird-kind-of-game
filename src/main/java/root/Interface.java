import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import character.Enemy;
import character.physics.Gravity;
import javafx.animation.AnimationTimer;
import java.util.Random;
import java.util.random.RandomGenerator;

public class Interface extends Application {
    private double backgroundWidth;
    private double backgroundWidth_2 = 640;
    @Override
    public void start(Stage primaryStage) {
        // Create the root pane
        Pane root = new Pane();

        // Create a background object and an image view
        Image background = new Image("file:src/main/resources/bg.png");
        ImageView imageViewBackground = new ImageView(background);
        imageViewBackground.setFitHeight(400);


        Image background_2 = new Image("file:src/main/resources/bg.png");
        ImageView imageViewBackground_2 = new ImageView(background);
        imageViewBackground_2.setFitHeight(400);

        // Add the coin image to the root pane
        Image coin = new Image("file:/Users/tomstanic/IdeaProjects/TP2/src/main/resources/coin.png");
        ImageView imageViewCoin = new ImageView(coin);


        //creé un objet random pour afficher les pièces de manière aléatoire
        Random random = new Random();


        // Create an enemy object and an image view
        Enemy enemy = new Enemy();

        // Add the enemy image to the root pane
        Image imageEnemy = enemy.characterImage();
        ImageView imageViewEnemy = new ImageView(imageEnemy);
        imageViewEnemy.setFitWidth(imageEnemy.getWidth() * 0.45);
        imageViewEnemy.setFitHeight(imageEnemy.getHeight() * 0.45);
        imageViewEnemy.setPreserveRatio(true);
        imageViewEnemy.setY(315);
        imageViewEnemy.setX(50);

        // Setting the background image and the enemy image to the root pane
        root.getChildren().add(imageViewBackground);
        root.getChildren().add(imageViewBackground_2);
        root.getChildren().add(imageViewCoin);
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


        new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Mise à jour de la position de backgroundWidth et imageViewBackgroun
                backgroundWidth = imageViewBackground.getLayoutX();
                backgroundWidth -= 2;
                imageViewBackground.setLayoutX(backgroundWidth);
                if (backgroundWidth == -640.0) {
                    imageViewBackground.setLayoutX(640);
                }

                // Mise à jour pour le deuxième background
                backgroundWidth_2 -= 2;
                imageViewBackground_2.setLayoutX(backgroundWidth_2);
                if (backgroundWidth_2 == -640.0) {
                    backgroundWidth_2 = 640;
                }
            }
        }.start();
    }
    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }
}
