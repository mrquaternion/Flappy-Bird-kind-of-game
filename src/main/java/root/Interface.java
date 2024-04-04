import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import character.Enemy;
import character.physics.Gravity;
import character.item.*;
import character.physics.Background;


public class Interface extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Créer un root
        Pane root = new Pane();

        // Créer un background et ajuster sa taille et sa position
        Image imageBackground = new Image("file:src/main/resources/bg.png");
        ImageView imageViewBackground_1 = new ImageView(imageBackground);
        ImageView imageViewBackground_2 = new ImageView(imageBackground);

        // Créer une pièce de monnaie et ajuster sa taille et sa position
        Image imageCoin = new Image("file:src/main/resources/coin.png");
        ImageView imageViewCoin = new ImageView(imageCoin);
        imageViewCoin.setFitWidth(imageCoin.getWidth() * 0.025);
        imageViewCoin.setFitHeight(imageCoin.getHeight() * 0.025);

        // Créer un ennemi et ajuster sa taille et sa position
        Enemy enemy = new Enemy();
        Image imageEnemy = enemy.characterImage();

        ImageView imageViewEnemy = enemy.setImageView();

        // Ajouter les images au root
        root.getChildren().add(imageViewBackground_1);
        root.getChildren().add(imageViewBackground_2);
        root.getChildren().add(imageViewCoin);
        root.getChildren().add(imageViewEnemy);

        // Creér la scène
        Scene scene = new Scene(root, 640, 440);

        // Ajouter une pièce de monnaie toutes les 3 secondes
        CoinSpawnRate coinSpawnRate = new CoinSpawnRate();
        coinSpawnRate.spawnCoin(imageViewCoin, scene);

        // Appliquer la gravité à l'ennemi
        Gravity gravity = new Gravity();
        gravity.applyGravity(scene, imageViewEnemy);

        // Paramétrer la scène et la fenêtre
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        // Afficher la fenêtre
        primaryStage.show();

        // Créer un objet ScrollingBackground
        Background scrollingBackground = new Background();
        scrollingBackground.scroll(imageViewBackground_1, imageViewBackground_2);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
