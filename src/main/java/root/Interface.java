/*
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
        BorderPane root = new BorderPane(); // DONE
        gamePane = new Pane(); // DONE

        setupScene(primaryStage, root); // DONE
        setupGameComponents(); // DONE
        setupStatusBar(root); // DONE

        background = new Background(); // DONE
        gamePane.getChildren().addAll(background.getImageViewBackground_1(), background.getImageViewBackground_2()); // DONE

        HeroGenerator heroGenerator = new HeroGenerator();
        CoinGenerator coinGenerator = new CoinGenerator();
        for (Coin coin : coins) { gamePane.getChildren().add(coin.getImageView()); } // DONE
        for (Hero hero : heroes) { gamePane.getChildren().add(hero.getImageView()); } // DONE
        gamePane.getChildren().add(enemy.getImageView()); // DONE

        root.setCenter(gamePane); // DONE
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

                if (!isPaused) {
                    background.scroll(enemy.getPickupCoin());
                    //heroGenerator.updateHeroes(heroes, enemy, deltaTime);
/*
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
*/

                    // Spawn heroes as needed, every 3 seconds
                    //if (heroGenerator.spawnHeroIfNeeded(heroes, now, lastSpawnTime)) {
                        //lastSpawnTime = now; // Update the last spawn time only when a hero is spawned
                   // }

                    // update text of nbOfCoins
                    nbOfCoin.setText("Coins: " + enemy.getAllCoin());
                    // update text of playerLife
                    playerLife.setText("Life: " + enemy.getHealthStatus());

                    lastTime = now;
                }
                System.out.println("This is the enemy health status before checking death: " + enemy.getHealthStatus());
                if (enemy.death()) { // DONE
                    stopTimer(); // DONE
                    primaryStage.close();
                }
            }
        };
        animationTimer.start();

        primaryStage.show();
    }


    public void shoot(long now) { // DONE

        if (lastBullet == null || now - lastBulletTime > 1e9) { // DONE
            double startX = enemy.getImageView().getX() + enemy.getImageView().getFitWidth(); // DONE
            double startY = enemy.getImageView().getY() + enemy.getImageView().getFitHeight() / 2; // DONE

            //lastBullet = new Bullet(startX, startY); // DONE
            bullets.add(lastBullet); // DONE

            gamePane.getChildren().add(lastBullet.getImageView()); // DONE
            lastBulletTime = now; // DONE
        }
    }

    //----------------------------------------------
    //----------------------------------------------
    //----------------------------------------------
    //----------------------------------------------
    //----------------------------------------------
    //----------------------------------------------


    private void stopTimer() { // DONE
        animationTimer.stop(); // DONE
        Scores.save(enemy.getAllCoin()); // DONE
        System.out.println("The enemy just died.");
    }

    private void setupScene(Stage primaryStage, BorderPane root) { // DONE
        scene = new Scene(root, 640, 440); // DONE
        primaryStage.setScene(scene); // DONE
        primaryStage.setResizable(false); // DONE
    }

    private void handleEvent() { // DONE
        scene.setOnKeyPressed((event) -> { // DONE
            if (event.getCode() == KeyCode.W && !enemy.jumpingStatus) { // DONE
                enemy.isJumping(); // DONE
            } else if (event.getCode() == KeyCode.E) { // DONE
                shoot(System.nanoTime()); // DONE
            }
        }); // DONE
    }

    private void setupGameComponents() { // DONE
        enemy = new Enemy(); // DONE
        enemy.setImageView(); // DONE

        heroes = new Hero[Hero.NUMBER_OF_HEROES]; // DONE
        for (int i = 0; i < Hero.NUMBER_OF_HEROES; i++) { // DONE
            if (i % 3 == 0) { // DONE
                heroes[i] = new Melee(); // DONE
            } else if (i % 3 == 1) { // DONE
                heroes[i] = new Tank(); // DONE
            } else {
                heroes[i] = new Stealth(); // DONE
            }
        }

        coins = new Coin[50];// DONE
        for (int i = 0; i < coins.length; i++) { coins[i] = new Coin(); } // DONE
    }

    private void setupStatusBar(BorderPane root) { // DONE
        HBox statusBar = new HBox(); // DONE
        statusBar.setAlignment(Pos.CENTER); // DONE
        statusBar.setPadding(new Insets(0, 0, 7, 0)); // DONE
        statusBar.setSpacing(10); // DONE

        Button pauseButton = new Button("Pause"); // DONE
        playerLife = new Text("Life: " + enemy.getHealthStatus()); // DONE
        nbOfCoin = new Text("Coins: " + enemy.getAllCoin()); // DONE
        Separator separator1 = new Separator(Orientation.VERTICAL); // DONE
        Separator separator2 = new Separator(Orientation.VERTICAL); // DONE

        pauseButton.setOnAction(event -> togglePause(pauseButton));

        statusBar.getChildren().addAll(pauseButton, separator1,  playerLife, separator2, nbOfCoin); // DONE
        root.setBottom(statusBar); // DONE
    }

    private void togglePause(Button pauseButton) {
        isPaused = !isPaused;
        if (isPaused) {
            animationTimer.stop();
            pauseButton.setText("Resume");
            System.out.println("Game is paused");


        } else {
            animationTimer.start();
            pauseButton.setText("Pause");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}*/
