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
import character.physics.ScrollingBackground;

public class Interface extends Application {
    private double backgroundWidth;
    private double backgroundWidth_2 = 640;
    @Override
    public void start(Stage primaryStage) {
        // Créer un root
        Pane root = new Pane();

        // Créer un background et ajuster sa taille et sa position
        Image background = new Image("file:src/main/resources/bg.png");
        ImageView imageViewBackground = new ImageView(background);
        imageViewBackground.setFitHeight(400);


        Image background_2 = new Image("file:src/main/resources/bg.png");
        ImageView imageViewBackground_1 = new ImageView(background);
        ImageView imageViewBackground_2 = new ImageView(background);
        imageViewBackground_2.setFitHeight(400);

        // Add the coin image to the root pane
        Image coin = new Image("file:/Users/tomstanic/IdeaProjects/TP2/src/main/resources/coin.png");
        ImageView imageViewCoin = new ImageView(coin);


        //creé un objet random pour afficher les pièces de manière aléatoire
        Random random = new Random();

        // Créer un ennemi et ajuster sa taille et sa position
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

        // Creér la scène
        Scene scene = new Scene(root, 640, 440);

        // Appliquer la gravité à l'ennemi
        Gravity gravity = new Gravity();
        gravity.applyGravity(scene, imageViewEnemy);

        // Paramétrer la scène et la fenêtre
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        // Afficher la fenêtre
        primaryStage.show();

        // Créer un objet ScrollingBackground
        ScrollingBackground scrollingBackground = new ScrollingBackground();
        scrollingBackground.scroll(imageViewBackground_1, imageViewBackground_2);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
