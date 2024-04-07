package character.hero;
import character.Enemy;
import character.Hero;
import character.physics.Background;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tank extends Hero {
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
}
