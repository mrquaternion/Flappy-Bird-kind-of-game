package character;

import character.physics.Background;
import javafx.scene.image.Image;

public abstract class Hero  extends Character {
    public final static int SPEED = 120;

@Override
    public  void imageViewCharacterSet() {
    double randomY = Math.random() * (Background.HEIGHT - imageViewCharacter.getFitHeight());
    imageViewCharacter.setY(randomY);
    imageViewCharacter.setY(randomY);
        imageViewCharacter.setX(Background.WIDTH);

}
public abstract void interaction(Enemy enemy, Hero hero);





}
