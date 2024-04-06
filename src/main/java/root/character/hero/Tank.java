package character.hero;
import character.Enemy;
import character.Hero;
import javafx.scene.image.Image;

public class Tank extends Hero {
    // -------------- Constructor --------------
    public Tank(){
        ratio = 0.75;
        healthStatus = 100;
        characterImage = new Image("file:src/main/resources/kizaru.png");
    }

    @Override
    public void interaction(Enemy enemy) {
       //hitbox
        enemy.setHealthStatus(enemy.getHealthStatus() - 50);
    }
}
