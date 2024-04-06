package character.item;

import character.physics.Background;
import character.physics.Collision;
import character.Enemy;

import java.util.Random;

public class CoinGenerator {
    Random rand = new Random();

    public void spawnCoin(Coin[] coins, Enemy enemy, double dt) {

        // Check if it's time to spawn or reset a coin
        for (Coin coin : coins) {

             if (coin.isActive) {
                 coin.updatePosition(enemy.getPickupCoin(), dt);
                 if (Collision.checkCollisionCoin(coin, enemy)) {
                     enemy.increasePickupCoin();
                     coin.isActive = false;
                     resetCoinPosition(coin);
                 } else if (coin.getImageView().getX() < -coin.getImage().getWidth()) {
                     coin.isActive = false;
                     resetCoinPosition(coin);
                 }
             } else if (rand.nextInt(500) == 0) {
                 coin.isActive = true;
             }

             coin.setImageView();
        }
    }

    // Helper method to reset a coin's position
    private void resetCoinPosition(Coin coin) {
        System.out.println("x: " + coin.getImageView().getX() + ", y: "+ coin.getImageView().getY());
        coin.imageView.setX(640); // Place the coin at the right edge
        // Randomly place the coin within the vertical bounds of the screen
        coin.imageView.setY(rand.nextDouble() * (Background.HEIGHT - coin.imageView.getFitHeight()));
    }
}


