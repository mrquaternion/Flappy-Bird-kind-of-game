import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import character.Enemy;
import character.physics.Gravity;
import character.item.*;
import character.physics.Background;
import javafx.scene.text.Text;

public class Interface extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Créer un root
        BorderPane root = new BorderPane();

        // Créer un Pane pour le jeu
        Pane gamePane = new Pane();

        // Ajouter le Pane au centre du root
        root.setCenter(gamePane);

        // Créer une barre de statut
        HBox statusBar = new HBox();
        statusBar.setAlignment(Pos.CENTER); // Centrer les éléments dans la HBox
        statusBar.setPadding(new Insets(0, 0, 7, 0)); // 7 pixels de marge en bas
        statusBar.setSpacing(10); // Espacer les éléments dans la HBox


        // Créer un arrière-plan
        Background background = new Background();

        // Créer une pièce de monnaie et ajuster sa taille et sa position

        ImageView imageViewCoin = new ImageView(Coin.getImage());
        imageViewCoin.setFitWidth(Coin.getImage().getWidth() * 0.025);
        imageViewCoin.setFitHeight(Coin.getImage().getHeight() * 0.025);

        // Créer un ennemi et ajuster sa taille et sa position
        Enemy enemy = new Enemy();
        enemy.setImageView();



        // Créer un bouton de pause
        Button pauseButton = new Button("Pause");
        // Créer un texte pour afficher la vie du joueur et le nombre de coins
        Text playerLife = new Text("Life "+ enemy.getHealthStatus());
        Text nbOfCoin = new Text("Coin "+ enemy.getPickupCoin());

        // Créer deux séparateur pour espacer les éléments de la barre de statut
        Separator separator1 = new Separator(Orientation.VERTICAL);
        Separator separator2 = new Separator(Orientation.VERTICAL);

        // Ajouter les images au root
        gamePane.getChildren().addAll(background.getImageViewBackground_1(), background.getImageViewBackground_2(), enemy.getImageView());
        // Ajouter les éléments à la barre de statut
        statusBar.getChildren().addAll(pauseButton, separator1,  playerLife, separator2, nbOfCoin);



        // Créer un tableau de pièces
        Coin[] coins = new Coin[10];
        for (int i = 0; i < coins.length; i++) {
            coins[i] = new Coin();
        }
        CoinGenerator coinGenerator = new CoinGenerator();
        coinGenerator.spawnCoin(coins, enemy);

        for (Coin coin : coins) {
            gamePane.getChildren().add(coin.getImageView());
        }

        // Ajouter la barre de statut au root en bas de la fenêtre
        root.setBottom(statusBar);

        // Creér la scène
        Scene scene = new Scene(root, 640, 440);

        // Appliquer la gravité à l'ennemi
        Gravity gravity = new Gravity();
        gravity.applyGravity(scene, enemy.getImageView());

        // Paramétrer la scène et la fenêtre
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        // Fais défiler l'arrière-plan
        background.scroll(enemy.getPickupCoin());

        // Afficher la fenêtre
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
