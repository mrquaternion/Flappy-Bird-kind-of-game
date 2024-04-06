package character;

import character.physics.Background;
import character.physics.Collision;
import javafx.scene.image.Image;

import java.util.Random;
import character.hero.*;

public abstract class Hero  extends Character {
    private int spawnTime;
    private boolean isActivated;
    public final static int SPEED = 120;
    private static Random rand = new Random();
    public static final int NUMBER_OF_HEROES = 2;


    @Override
    public void imageViewCharacterSet() {
        double randomY = Math.random() * (Background.HEIGHT - imageViewCharacter.getFitHeight());
        imageViewCharacter.setY(randomY);
        imageViewCharacter.setY(randomY);
        imageViewCharacter.setX(Background.WIDTH);

    }

    public abstract void interaction(Enemy enemy);

    @Override
    public void updatePosition(double dt) {
        // Calcul de la nouvelle vitesse et la nouvelle position du hero
        if (isActivated) {
            imageViewCharacter.setX(imageViewCharacter.getX() - SPEED * dt);
        }
    }

    public void borderTouch() {
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

    public static void chooseType(Melee[] melee, Tank[] tank, Stealth[] stealths) {
        double x =rand.nextDouble(); // Generate a random double between 0.0 and 1.0

            if (x < 1.0 / 3.0) {
                spawn(melee);
            } else if (x < 2.0 / 3.0) {
               spawn(tank);
            } else {
               spawn(stealths);
            }

    }

    public void  Collision(Enemy enemy) {
        if (Collision.checkCollisionHero(this, enemy)) {
            isActivated = false;
            this.interaction(enemy);
            imageViewCharacter.setX(Background.WIDTH);
            imageViewCharacter.setY(Math.random() * (Background.HEIGHT - imageViewCharacter.getFitHeight()));
        }
    }



}
