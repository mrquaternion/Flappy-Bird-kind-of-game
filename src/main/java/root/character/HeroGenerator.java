package character;

import java.util.List;
import java.util.Random;
import character.physics.Collision;


public class HeroGenerator {
    Random rand = new Random();
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
