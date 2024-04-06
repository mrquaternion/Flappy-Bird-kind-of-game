package character.hero;
import character.Enemy;
import character.Hero;
import character.physics.Background;
import javafx.scene.image.Image;

public class Melee extends Hero {
    public Melee(){
        ratio = 0.45;
        healthStatus = 100;
        characterImage = new Image("file:src/main/resources/kaido.png");
        imageViewCharacter.setX((int) Background.WIDTH);
        imageViewCharacter.setY(Math.random() * (int) Background.HEIGHT - imageViewCharacter.getFitHeight());
    }
    @Override
    public void interaction(Enemy enemy, Hero hero) {
        //hitbox
        enemy.setHealthStatus(0);
    }
}
