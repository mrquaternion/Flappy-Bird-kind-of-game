package character.item;

import javafx.animation.AnimationTimer;
import java.util.Random;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;

public class CoinSpawnRate {
    public void spawnCoin(ImageView imageViewCoin, Scene scene) {
        new AnimationTimer() {
            private long lastSpawnTime = 0;
            final private Random random = new Random();

            @Override
            public void handle(long now) {
                double spawnInterval = 3 * 1e9;
                if (lastSpawnTime == 0 || now - lastSpawnTime >= spawnInterval) {

                    // Y A PROBLEME PARFOIS CA RESPECTE PAS LA TAILLE DE L'ECRAN MAIS SI TU REGARDES LES COORDS DANS TERMINAL CEST ENTRE 0 ET 640 ET 0 ET 400
                    double coinX = random.nextDouble() * scene.getWidth();
                    double coinY = random.nextDouble() * (scene.getHeight() - 40); // On exclut la barre de menu

                    imageViewCoin.setX(coinX);
                    imageViewCoin.setY(coinY);
                    System.out.println("Coin spawned at: " + coinX + ", " + coinY);

                    lastSpawnTime = now;
                }
            }
        }.start();
    }
}

