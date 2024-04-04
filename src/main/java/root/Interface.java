import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import character.Enemy;
import character.physics.Gravity;
import character.physics.ScrollingBackground;

public class Interface extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Créer un root
        Pane root = new Pane();

        // Créer un background et ajuster sa taille et sa position
        Image background = new Image("file:src/main/resources/bg.png");
        ImageView imageViewBackground_1 = new ImageView(background);
        ImageView imageViewBackground_2 = new ImageView(background);

        // Créer un ennemi et ajuster sa taille et sa position
        Enemy enemy = new Enemy();
        Image imageEnemy = enemy.characterImage();
        ImageView imageViewEnemy = new ImageView(imageEnemy);
        imageViewEnemy.setFitWidth(imageEnemy.getWidth() * 0.45);
        imageViewEnemy.setFitHeight(imageEnemy.getHeight() * 0.45);
        imageViewEnemy.setPreserveRatio(true);
        imageViewEnemy.setY(315);
        imageViewEnemy.setX(50);

        // Ajouter les images au root
        root.getChildren().add(imageViewBackground_1);
        root.getChildren().add(imageViewBackground_2);
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
