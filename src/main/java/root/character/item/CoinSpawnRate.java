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
        }
        new AnimationTimer() {

            private long lastUpdate = 0;

            @Override
            public void handle(long now) {

                double deltaTime = (now - lastUpdate) / TIME;

                for (Coin coin : coins) {
                    if (!coin.activeStatus) {
                        coin.setX(640);
                        Random random = new Random();
                        coin.setY(random.nextDouble() * Coin.BACKGROUND_HEIGHT);
                        coin.setActiveStatus(true);
                    } else {
                        coin.update(deltaTime);
                    }

                    imageViewCoin.setX(coin.getX());
                    imageViewCoin.setY(coin.getY());
                }
                lastUpdate = now;
            }
        }.start();
    }
}

