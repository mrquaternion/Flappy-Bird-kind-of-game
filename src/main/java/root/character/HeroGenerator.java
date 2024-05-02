package character;

import java.util.Random;
import character.physics.Collision;

public class HeroGenerator {
    Random rand = new Random();
    public boolean spawnHeroIfNeeded(Hero[] heroes, long now, double lastSpawnTime) {
        if ((now - lastSpawnTime) >= (3 * 1e9)) {
            int index = rand.nextInt(heroes.length);
            if (!heroes[index].isActivated) {
                heroes[index].isActivated = true;
                Hero.setRandomRadius(heroes[index]);
                heroes[index].resetHeroPosition();

                return true; // Indicate that a hero was spawned
            }
        }
        return false; // No hero was spawned
    }

    // New method for updating hero positions and checking collisions
    public void updateHeroes(Hero[] heroes, Enemy enemy, double dt) {
        for (Hero hero : heroes) {
            if (hero.isActivated) {
                hero.updatePosition(enemy.getPickupCoin(), dt);
                hero.borderTouch();
                if (Collision.checkCollisionHero(hero, enemy)) {
                    hero.isActivated = false;
                    hero.interaction(enemy);
                    hero.resetHeroPosition();
                }
            }
            hero.setImageView();
        }
    }



}
