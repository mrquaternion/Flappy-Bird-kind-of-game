import character.Hero;
import character.HeroGenerator;
import character.hero.*;
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
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;

import character.Enemy;
import character.physics.Gravity;
import character.item.*;
import character.physics.Background;
import javafx.scene.text.Text;
import character.physics.Collision;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;


public class Interface extends Application {
    private boolean isPaused = false;
    private Pane gamePane; // Declare gamePane at the class level
    private Enemy enemy; // Declare enemy at the class level
    private List<Bullet> bullets = new ArrayList<>();
    private Bullet lastBullet = null;

    @Override
    public void start(Stage primaryStage) {

        // ------------------------------------ ESPACE DE CRÉATION D'ÉLÉMENTS ------------------------------------
        // --------- CRÉATION ROOT ---------
        BorderPane root = new BorderPane();
        // --------- CRÉATION PANNEAU DE JEU ---------
        gamePane = new Pane();
        // --------- CRÉATION BARRE DE STATUT ---------
        HBox statusBar = new HBox();
        statusBar.setAlignment(Pos.CENTER); // Centrer les éléments de la HBOX
        statusBar.setPadding(new Insets(0, 0, 7, 0)); // Espacement de la HBOX
        statusBar.setSpacing(10); // Espacement entre les éléments de la HBOX
        // --------- CRÉATION & AJUSTER SA TAILLE D'UN ENNEMI ----------
        enemy = new Enemy();
        enemy.setImageView();

        // --------- CRÉATION DES HÉROS  ---------
        Hero[] heroes = new Hero[Hero.NUMBER_OF_HEROES];
        for (int i = 0; i < Hero.NUMBER_OF_HEROES; i++) {
            if (i % 3 == 0) {
                heroes[i] = new Melee();
            } else if (i % 3 == 1) {
                heroes[i] = new Tank();
            } else {
                heroes[i] = new Stealth();
            }

        }

        // --------- CRÉATION DE SÉPARATEURS ---------
        Separator separator1 = new Separator(Orientation.VERTICAL);
        Separator separator2 = new Separator(Orientation.VERTICAL);
        // --------- CRÉATION DE PIÈCES ----------
        Coin[] coins = new Coin[5]; // NOMBRE ARBITRAIRE DE PIÈCES
        for (int i = 0; i < coins.length; i++) { coins[i] = new Coin(); } // Créer pièces
        CoinGenerator coinGenerator = new CoinGenerator(); // Créer générateur de pièces

        // --------- CRÉATION SCÈNE ----------
        Scene scene = new Scene(root, 640, 440);
        // --------- CRÉATION DE LA GRAVITÉ ----------
        Gravity gravity = new Gravity();
        // --------- CRÉATION ARRIÈRE-PLAN ----------
        Background background = new Background();

        // --------- CRÉATION GÉNÉRATEUR DE HÉROS ----------
        HeroGenerator heroGenerator = new HeroGenerator();
        // --------- CONFIGURATION DE LA SCÈNE ----------
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);



        // ------------------------------------ ESPACE D'AJOUT AU JEU ------------------------------------
        // --------- AJOUT LES ÉLÉMENTS (BACKGROUND ET ENNEMI) AU PANNEAU DE JEU ----------
        gamePane.getChildren().addAll(background.getImageViewBackground_1(), background.getImageViewBackground_2(), enemy.getImageView());
        // --------- AJOUTER PIÈCES AU PANNEAU DE JEU ----------
        for (Coin coin : coins) {
            gamePane.getChildren().add(coin.getImageView());
        }
        // --------- AJOUTER HÉROS AU PANNEAU DE JEU ----------
        for (Hero hero : heroes) {
            gamePane.getChildren().add(hero.getImageView());
        }
        // --------- AJOUT BOUTON PAUSE ----------
        Button pauseButton = new Button("Pause");
        // --------- AJOUT BARRE DE STATUT ----------
        Text playerLife = new Text("Life: " + enemy.getHealthStatus());
        Text nbOfCoin = new Text("Coins: " + enemy.getAllCoin());
        // --------- AJOUT ÉLÉMENTS À LA BARRE DE STATUT ----------
        statusBar.getChildren().addAll(pauseButton, separator1,  playerLife, separator2, nbOfCoin);
        // --------- AJOUT PANNEAU DE JEU AU ROOT ---------
        root.setCenter(gamePane);
        // --------- AJOUT ÉLÉMENTS AU ROOT ----------
        root.setBottom(statusBar);



        // ------------------------------------ ESPACE DE GESTION DES ÉVÉNEMENTS ------------------------------------
        // --------- GESTIONNAIRE D'ÉVÉNEMENTS ----------
        // --------- APPLIQUER GRAVITÉ À LA SCÈNE ----------
        scene.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.W && !enemy.jumpingStatus) {
                enemy.isJumping();
            } else if (event.getCode() == KeyCode.R) {
                shoot();
            }
        });


        // --------- ANIMATION DE LA SCÈNE ---------
        AnimationTimer animationTimer = new AnimationTimer() {
            double lastTime = 0;
            double lastSpawnTime = 0;


            @Override
            public void handle(long now) {
                double deltaTime = (now - lastTime) * 1e-9;

                if (lastTime == 0) {
                    lastTime = now;
                    lastSpawnTime = now;
                    return;
                }


                enemy.jumpCooldown();
                if (enemy.go) {
                    enemy.updatePosition(deltaTime);
                    coinGenerator.spawn(coins, enemy, deltaTime); // Génération de pièces
                }
                if (!isPaused) {
                    enemy.gravityUnblock();
                    background.scroll(enemy.getPickupCoin());
                    heroGenerator.updateHeroes(heroes, enemy, deltaTime);


                    if (lastBullet != null) {
                        lastBullet.updatePosition(deltaTime);
                        if (lastBullet.getImageView().getX() > Background.WIDTH) {
                            gamePane.getChildren().remove(lastBullet.getImageView());
                        }
                        Hero hitHero = Collision.checkCollisionBullet(heroes, lastBullet);
                        if (hitHero != null) {
                            hitHero.interaction(enemy);
                            heroGenerator.resetHeroPosition(hitHero);
                            gamePane.getChildren().remove(lastBullet.getImageView());
                        }
                    }




                    // Spawn heroes as needed, every 3 seconds
                    if (heroGenerator.spawnHeroIfNeeded(heroes, now, lastSpawnTime)) {
                        lastSpawnTime = now; // Update the last spawn time only when a hero is spawned
                    }

                    // update text of nbOfCoins
                    nbOfCoin.setText("Coins: " + enemy.getAllCoin());
                    // update text of playerLife
                    playerLife.setText("Life: " + enemy.getHealthStatus());

                    lastTime = now;
                }
            }
        };
        animationTimer.start();

     pauseButton.setOnAction(event -> {
            isPaused = !isPaused; // Inverse l'état de pause
            if (isPaused) {
                animationTimer.stop(); // Arrête le timer si en pause
                pauseButton.setText("Resume"); // Change le texte du bouton pour indiquer la prochaine action
                System.out.println("Game is paused");
                enemy.gravityBlock();

            } else {
                animationTimer.start(); // Redémarre le timer si le jeu reprend
                pauseButton.setText("Pause"); // Revenir au texte original

            }
     });

        // --------- AFFICHAGE DE LA SCÈNE ----------
        primaryStage.show();

    }

    public void shoot() {
        if (lastBullet == null || lastBullet.getImageView().getX() > Background.WIDTH) {
            double startX = enemy.getImageView().getX() + enemy.getImageView().getFitWidth();
            double startY = enemy.getImageView().getY() + enemy.getImageView().getFitHeight() / 2;

            lastBullet = new Bullet(startX, startY); // Create a new bullet
            bullets.add(lastBullet); // Add it to your bullets list if you're tracking all bullets

            gamePane.getChildren().add(lastBullet.getImageView()); // Add the bullet to the scene
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

}
