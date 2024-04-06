package character.hero;
import character.Enemy;
import character.Hero;
import javafx.scene.image.Image;

public class Stealth extends Hero {
    public Stealth(){
        ratio = 0.1;
        healthStatus = 100;
        characterImage = new Image("file:src/main/resources/BlackBeard.png");
    }

    @Override
    public void interaction(Enemy enemy) {
        //hitbox
        enemy.setAllCoin(enemy.getAllCoin() - 10);
    }
}
