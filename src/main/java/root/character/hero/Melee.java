package character.hero;
import character.Enemy;
import character.Hero;
import javafx.scene.image.Image;

public class Melee extends Hero {
    public Melee(){
        ratio = 0.45;
        healthStatus = 100;
        characterImage = new Image("file:src/main/resources/kaido.png");
    }
    @Override
    public void interaction(Enemy enemy, Hero hero) {
        //hitbox
        enemy.setHealthStatus(0);
    }
}
