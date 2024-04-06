package character;

import javafx.scene.image.Image;

import java.util.Random;
import character.hero.*;

public class Hero  extends Character {
    Random rand;


    @Override
    public Image characterImage() {
        return new Image("file:src/main/resources/enemy.png");
    }

    public void setImageView(){
        // Implement the method here
    }

    public Hero chooseType() {
        double x = rand.nextDouble(); // Generate a random double between 0.0 and 1.0

        if (x < 1.0 / 3.0) {
            return new Stealth();
        } else if (x < 2.0 / 3.0) {
            return new Tank();
        } else {
            return new Melee();
        }
    }

}
