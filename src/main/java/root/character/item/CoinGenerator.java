package character.item;

import character.physics.Background;
import character.physics.Collision;
import character.Enemy;

import java.util.List;
import java.util.Random;


public class CoinGenerator {
    Random rand = new Random();

    private double timeToSpawn = 0;

    public void spawn(List<Coin> coins, Enemy enemy, double dt) {
        timeToSpawn += dt;
        for (Coin coin : coins) {
            if (coin.isActive) {
                coin.updatePosition(enemy.getPickupCoin(), dt);
                if (Collision.checkCollisionCoin(coin, enemy)) {
                    enemy.increasePickupCoin();
                    coin.isActive = false;
                    resetCoinPosition(coin);
                } else if (coin.getImageView().getX() < -coin.getImageView().getFitWidth()) {
                    coin.isActive = false;
                    resetCoinPosition(coin);
                }
            } else if (timeToSpawn >= 2) {
                coin.isActive = true;
                timeToSpawn = 0;
            }

            coin.setImageView();
        }
    }

    private void resetCoinPosition(Coin coin) {
        coin.imageView.setX(640);
        coin.imageView.setY(rand.nextDouble() * (Background.HEIGHT - coin.imageView.getFitHeight()));
    }
}


