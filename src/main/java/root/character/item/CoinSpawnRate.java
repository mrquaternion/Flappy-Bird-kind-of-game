package character.item;

import javafx.animation.AnimationTimer;
import java.util.Random;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;

public class CoinSpawnRate {
    private final static double TIME = 1e9;
    public void spawnCoin(ImageView imageViewCoin) {
        Coin[] coins = new Coin[10];

        for (int i = 0; i < coins.length; i++) {
            coins[i] = new Coin();
            System.out.println(coins[i].getX() + ", " + coins[i].getY());
        }

        new AnimationTimer() {
            private long lastUpdate = 0;
            @Override
            public void handle(long now) {
                double deltaTime = (now - lastUpdate) / TIME;

                for (Coin coin : coins) {
                    if (coin.getX() <= 0) {
                        coin.setX(640);
                        Random random = new Random();
                        coin.setY(random.nextDouble() * Coin.BACKGROUND_HEIGHT);
                        coin.setActiveStatus(true);
                    } else {
                        coin.update(deltaTime);
                    }
                    System.out.println(coin.getX() + ", " + coin.getY());
                    imageViewCoin.setX(coin.getX());
                    imageViewCoin.setY(coin.getY());
                }
                lastUpdate = now;
            }
        }.start();
    }
}

