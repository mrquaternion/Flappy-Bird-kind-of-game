
import character.Hero;
import character.HeroGenerator;
import character.Scores;
import character.hero.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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
import character.item.*;
import character.physics.Background;
import javafx.scene.text.Text;
import character.physics.Collision;

import java.util.ArrayList;
import java.util.List;


public class Interface extends Application {
    private boolean isPaused = false;
    private Pane gamePane; // Declare gamePane at the class level
    private Enemy enemy; // Declare enemy at the class level
    private final List<Bullet> bullets = new ArrayList<>();
    private Bullet lastBullet = null;
    private long lastBulletTime = 0; // Track the last time a bullet was shot
    private Background background;
    private Hero[] heroes;
    private Coin[] coins;
    private AnimationTimer animationTimer;
    private Text playerLife;
    private Text nbOfCoin;
    private Scene scene;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        gamePane = new Pane();

        setupScene(primaryStage, root);
        setupGameComponents();
        setupStatusBar(root);

        background = new Background();
        gamePane.getChildren().addAll(background.getImageViewBackground_1(), background.getImageViewBackground_2());

        HeroGenerator heroGenerator = new HeroGenerator();
        CoinGenerator coinGenerator = new CoinGenerator();
        for (Coin coin : coins) { gamePane.getChildren().add(coin.getImageView()); }
        for (Hero hero : heroes) { gamePane.getChildren().add(hero.getImageView()); }
        gamePane.getChildren().add(enemy.getImageView());

        root.setCenter(gamePane);
        handleEvent();

        animationTimer = new AnimationTimer() {
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
                if (enemy.isMoving) {
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
                            hitHero.resetHeroPosition();
                            enemy.killReward(hitHero);
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
                System.out.println("This is the enemy health status before checking death: " + enemy.getHealthStatus());
                if (enemy.death()) {
                    stopTimer();
                    primaryStage.close();
                }
            }
        };
        animationTimer.start();

        primaryStage.show();
    }


    public void shoot(long now) {
        // Check if more than 1 second has passed since the last bullet was shot
        if (lastBullet == null || now - lastBulletTime > 1e9) {
            double startX = enemy.getImageView().getX() + enemy.getImageView().getFitWidth();
            double startY = enemy.getImageView().getY() + enemy.getImageView().getFitHeight() / 2;

            lastBullet = new Bullet(startX, startY); // Create a new bullet
            bullets.add(lastBullet); // Add it to your bullets list if you're tracking all bullets

            gamePane.getChildren().add(lastBullet.getImageView()); // Add the bullet to the scene
            lastBulletTime = now; // Update the time the last bullet was shot
        }
    }

    //----------------------------------------------
    //----------------------------------------------
    //----------------------------------------------
    //----------------------------------------------
    //----------------------------------------------
    //----------------------------------------------


    private void stopTimer() {
        animationTimer.stop();
        Scores.save(enemy.getAllCoin());
        System.out.println("The enemy just died.");
    }

    private void setupScene(Stage primaryStage, BorderPane root) {
        scene = new Scene(root, 640, 440);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
    }

    private void handleEvent() {
        scene.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.W && !enemy.jumpingStatus) {
                enemy.isJumping();
            } else if (event.getCode() == KeyCode.E) {
                shoot(System.nanoTime());
            }
        });
    }

    private void setupGameComponents() {
        enemy = new Enemy();
        enemy.setImageView();

        heroes = new Hero[Hero.NUMBER_OF_HEROES];
        for (int i = 0; i < Hero.NUMBER_OF_HEROES; i++) {
            if (i % 3 == 0) {
                heroes[i] = new Melee();
            } else if (i % 3 == 1) {
                heroes[i] = new Tank();
            } else {
                heroes[i] = new Stealth();
            }
        }

        coins = new Coin[50]; // NOMBRE ARBITRAIRE DE PIÈCES
        for (int i = 0; i < coins.length; i++) { coins[i] = new Coin(); } // Créer pièces
    }

    private void setupStatusBar(BorderPane root) {
        HBox statusBar = new HBox();
        statusBar.setAlignment(Pos.CENTER);
        statusBar.setPadding(new Insets(0, 0, 7, 0));
        statusBar.setSpacing(10);

        Button pauseButton = new Button("Pause");
        playerLife = new Text("Life: " + enemy.getHealthStatus());
        nbOfCoin = new Text("Coins: " + enemy.getAllCoin());
        Separator separator1 = new Separator(Orientation.VERTICAL);
        Separator separator2 = new Separator(Orientation.VERTICAL);

        pauseButton.setOnAction(event -> togglePause(pauseButton));

        statusBar.getChildren().addAll(pauseButton, separator1,  playerLife, separator2, nbOfCoin);
        root.setBottom(statusBar);
    }

    private void togglePause(Button pauseButton) {
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
    }

    public static void main(String[] args) {
        launch(args);
    }
}
