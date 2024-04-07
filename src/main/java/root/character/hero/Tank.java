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
    public void teleport(){
        if (Math.random() < 0.5)
            imageView.setX( imageView.getX() - 30 * Math.random());
        else{
            imageView.setX(imageView.getX() + 30 * Math.random());
        }
        if (Math.random() < 0.5)
            imageView.setY( imageView.getY() - 30 * Math.random());
        else{
            imageView.setY(imageView.getY() + 30 * Math.random());
        }


    }
}
