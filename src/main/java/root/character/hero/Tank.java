package character.hero;
import character.Enemy;
import character.Hero;
import character.physics.Background;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tank extends Hero {
    private double timeSinceLastTeleport = 0;

    // -------------- Constructor --------------
    public Tank() {
        this.image = new Image("file:src/main/resources/kizaru.png");
        this.imageView = new ImageView(image);
        this.ratio = 0.9;
        this.healthStatus = 100;
        this.imageView.setX(Background.WIDTH);
        this.imageView.setY(Math.random() * (Background.HEIGHT - imageView.getFitHeight()));

    }

    @Override
    public void interaction(Enemy enemy) {
       //hitbox
        enemy.setHealthStatus(enemy.getHealthStatus() - 50);
    }

    @Override
    public void updatePosition(int nbOfCoins, double dt) {
        imageView.setX(imageView.getX() - ((vx / frameRate) + (nbOfCoins * 10) * dt));
        timeSinceLastTeleport += dt;
        if (timeSinceLastTeleport >= 0.5) {
            teleport();
            timeSinceLastTeleport = 0;

        }
    }
    public void teleport() {
        imageView.setX(imageView.getX() + getRandomDisplacement());
        imageView.setY(imageView.getY() + getRandomDisplacement());
    }

    private double getRandomDisplacement() {
        return (Math.random() - 0.5) * 60; // Produit un nombre entre -30 et 30
    }
}
