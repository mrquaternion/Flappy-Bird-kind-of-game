package character.item;

import character.physics.Collision;
import character.Enemy;
import javafx.animation.AnimationTimer;
import java.util.Random;

public class CoinGenerator {
    private final static double TIME = 1e9; // Time constant for calculations
    private final static double SPAWN_INTERVAL = 3; // Interval in seconds between spawns
    private long lastSpawnTime = 0; // Track the time since the last coin was spawned or reset

    public void spawnCoin(Coin[] coins, Enemy enemy) {
        new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                double deltaTime = (now - lastUpdate) / TIME;

                // Check if it's time to spawn or reset a coin
                if ((now - lastSpawnTime) / TIME >= SPAWN_INTERVAL) {
                    for (Coin coin : coins) {
                        // Spawn or reset coins that are off the screen
                        if (coin.getX() <= -Coin.image.getWidth() || coin.getX() == 640) { // Checks if it's a new or off-screen coin
                            resetCoinPosition(coin);
                            lastSpawnTime = now; // Update the last spawn time to now
                            break; // Only reset one coin per interval
                        }
                    }
                }

                // Update and move all coins
                for (Coin coin : coins) {
                    if (coin.getX() > -Coin.image.getWidth()) { // Keep updating if the coin is on the screen
                        coin.updateSpeed(deltaTime);

                        // Check for collision with the enemy
                        if (Collision.checkCollision(coin, enemy)) {
                            System.out.println("Collision detected");
                            enemy.increasePickupCoin();
                            resetCoinPosition(coin);
                        }

                        coin.setImageView();
                    }
                }
                lastUpdate = now;
            }

            // Helper method to reset a coin's position
            private void resetCoinPosition(Coin coin) {
                coin.setX(640); // Place the coin at the right edge
                // Randomly place the coin within the vertical bounds of the screen
                Random random = new Random();
                coin.setY(random.nextDouble() * (Coin.BACKGROUND_HEIGHT - ((Coin.image.getHeight() / 2) * 0.02)));
            }
        }.start();
    }
}


