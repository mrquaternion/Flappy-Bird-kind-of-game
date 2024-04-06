package character.hero;
import character.Enemy;
import character.Hero;
import javafx.scene.image.Image;

public class Stealth extends Hero {
    public Stealth(){
        ratio = 0.45;
        healthStatus = 100;
        characterImage = new Image("file:src/main/resources/blackBeard.png");
    }

    @Override
    public void interaction(Enemy enemy, Hero hero) {
        //hitbox
        enemy.setAllCoin(enemy.getAllCoin() - 10);
    }
}
