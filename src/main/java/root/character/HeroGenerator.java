package character;

import java.util.List;
import java.util.Random;
import character.physics.Collision;


public class HeroGenerator {
    Random rand = new Random();

    // Spawn a hero if the time since the last spawn is greater than 3 seconds
    public boolean spawnHeroIfNeeded(List<Hero> heroes, long now, double lastSpawnTime) {
        if ((now - lastSpawnTime) >= (3 * 1e9)) {
            int index = rand.nextInt(heroes.size());
            if (!heroes.get(index).isActivated) {
                heroes.get(index).isActivated = true;
                Hero.setRandomRadius(heroes.get(index));
                heroes.get(index).resetHeroPosition();

                return true; // Indique qu'un héro doit spawm
            }
        }
        return false; // Inversement
    }

    // Update the position of the heroes and check for collisions
    public void updateHeroes(List<Hero> heroes, Enemy enemy, double dt) {
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
