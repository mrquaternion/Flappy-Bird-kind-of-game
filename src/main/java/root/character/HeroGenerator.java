package character;
import java.util.Random;

import character.physics.Background;
import character.physics.Collision;
public class HeroGenerator {
    Random rand = new Random();
    public boolean spawnHeroIfNeeded(Hero[] heroes, long now, double lastSpawnTime) {
        if ((now - lastSpawnTime) >= (3 * 1e9)) {
            int index = rand.nextInt(heroes.length);
            if (!heroes[index].isActivated) {
                heroes[index].isActivated = true;
                resetHeroPosition(heroes[index]);
                return true; // Indicate that a hero was spawned
            }
        }
        return false; // No hero was spawned
    }

    // New method for updating hero positions and checking collisions
    public void updateHeroes(Hero[] heroes, Enemy enemy, double dt) {
        for (Hero hero : heroes) {
            if (hero.isActivated) {
                Hero.setRandomRadius(hero);
                hero.updatePosition(enemy.getPickupCoin(), dt);
                hero.borderTouch();
                if (Collision.checkCollisionHero(hero, enemy)) {
                    hero.isActivated = false;
                    hero.interaction(enemy);
                    resetHeroPosition(hero);
                }
            }
            hero.setImageView();
        }
    }

    public void resetHeroPosition(Hero hero) {
        hero.getImageView().setX(Background.WIDTH);
        hero.getImageView().setY(rand.nextDouble() * (Background.HEIGHT - hero.getImageView().getFitHeight()));
    }

}
