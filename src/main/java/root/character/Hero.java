package character;

import character.physics.Background;
import javafx.scene.image.Image;

import java.util.Random;
import character.hero.*;

public abstract class Hero  extends Character {
    private int spawnTime;
    private boolean isActivated;
    public final static int SPEED = 120;
    Random rand;



    @Override
    public void imageViewCharacterSet() {
        double randomY = Math.random() * (Background.HEIGHT - imageViewCharacter.getFitHeight());
        imageViewCharacter.setY(randomY);
        imageViewCharacter.setY(randomY);
        imageViewCharacter.setX(Background.WIDTH);

    }

    public abstract void interaction(Enemy enemy, Hero hero);

    @Override
    public void updatePosition(double dt) {
        // Calcul de la nouvelle vitesse et la nouvelle position du hero
        imageViewCharacter.setX(imageViewCharacter.getX() - SPEED * dt);
    }

    protected void borderTouch() {
        if (imageViewCharacter.getX() < -imageViewCharacter.getFitWidth()) {
            isActivated = false;
            imageViewCharacter.setX(Background.WIDTH);
            imageViewCharacter.setY(Math.random() * (Background.HEIGHT - imageViewCharacter.getFitHeight()));
        }
    }


    public static boolean spawn(Hero[] heroes) {
        for (Hero hero : heroes) {
            if (!hero.isActivated) {
                hero.isActivated = true;
                return true;
            }
        }
        return false;
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
