import javafx.animation.AnimationTimer;
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
import javafx.scene.image.Image;

import character.Enemy;
import character.physics.Gravity;
import character.item.*;
import character.physics.Background;
import javafx.scene.text.Text;

public class Interface extends Application {
    private boolean isPaused = false;


    @Override
    public void start(Stage primaryStage) {

        // ------------------------------------ ESPACE DE CRÉATION D'ÉLÉMENTS ------------------------------------
        // --------- CRÉATION ROOT ---------
        BorderPane root = new BorderPane();
        // --------- CRÉATION PANNEAU DE JEU ---------
        Pane gamePane = new Pane();
        // --------- CRÉATION BARRE DE STATUT ---------
        HBox statusBar = new HBox();
        statusBar.setAlignment(Pos.CENTER); // Centrer les éléments de la HBOX
        statusBar.setPadding(new Insets(0, 0, 7, 0)); // Espacement de la HBOX
        statusBar.setSpacing(10); // Espacement entre les éléments de la HBOX
        // --------- CRÉATION & AJUSTER SA TAILLE D'UN ENNEMI ----------
        Enemy enemy = new Enemy();
        enemy.setImageView();
        // --------- CRÉATION DE SÉPARATEURS ---------
        Separator separator1 = new Separator(Orientation.VERTICAL);
        Separator separator2 = new Separator(Orientation.VERTICAL);
        // --------- CRÉATION DE PIÈCES ----------
        Coin[] coins = new Coin[5]; // NOMBRE ARBITRAIRE DE PIÈCES
        for (int i = 0; i < coins.length; i++) { coins[i] = new Coin(); } // Créer pièces
        CoinGenerator coinGenerator = new CoinGenerator(); // Créer générateur de pièces
        coinGenerator.spawnCoin(coins, enemy); // Génération de pièces
        // --------- CRÉATION SCÈNE ----------
        Scene scene = new Scene(root, 640, 440);
        // --------- CRÉATION DE LA GRAVITÉ ----------
        Gravity gravity = new Gravity();
        // --------- CRÉATION ARRIÈRE-PLAN ----------
        Background background = new Background();
        // --------- CONFIGURATION DE LA SCÈNE ----------
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        // --------- APPLIQUER GRAVITÉ À LA SCÈNE ----------
        gravity.applyGravity(scene, enemy.getImageView());


        // ------------------------------------ ESPACE D'AJOUT AU JEU ------------------------------------
        // --------- AJOUT LES ÉLÉMENTS (BACKGROUND ET ENNEMI) AU PANNEAU DE JEU ----------
        gamePane.getChildren().addAll(background.getImageViewBackground_1(), background.getImageViewBackground_2(), enemy.getImageView());
        // --------- AJOUTER PIÈCES AU PANNEAU DE JEU ----------
        for (Coin coin : coins) { gamePane.getChildren().add(coin.getImageView()); } // Ajouter pièces au panneau de jeu
        // --------- AJOUT BOUTON PAUSE ----------
        Button pauseButton = new Button("Pause");
        // --------- AJOUT BARRE DE STATUT ----------
        Text playerLife = new Text("Life: " + enemy.getHealthStatus());
        Text nbOfCoin = new Text("Coins: " + enemy.getPickupCoin());
        // --------- AJOUT ÉLÉMENTS À LA BARRE DE STATUT ----------
        statusBar.getChildren().addAll(pauseButton, separator1,  playerLife, separator2, nbOfCoin);
        // --------- AJOUT PANNEAU DE JEU AU ROOT ---------
        root.setCenter(gamePane);
        // --------- AJOUT ÉLÉMENTS AU ROOT ----------
        root.setBottom(statusBar);
        // --------- DÉFILEMENT DE L'ARRIÈRE-PLAN ----------
        background.scroll(enemy.getPickupCoin());


        // ------------------------------------ ESPACE DE GESTION DES ÉVÉNEMENTS ------------------------------------
        // --------- GESTIONNAIRE D'ÉVÉNEMENTS ----------
        pauseButton.setOnAction(event -> {
            isPaused = !isPaused; // Inverser l'état de pause
            if (isPaused) {
                gravity.disableGravity();
                background.stopScroll();
            } else {
                gravity.enableGravity();
                background.startScroll();
            }
        });
        // --------- ANIMATION DE LA SCÈNE ---------
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
            }
        };


        // --------- AFFICHAGE DE LA SCÈNE ----------
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
