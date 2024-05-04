package character.physics;

import character.Enemy;
import character.Hero;
import character.item.Coin;
import character.item.Bullet;

import java.util.List;
public class Collision {

    // Check if the coin and enemy collide
    public static boolean checkCollisionCoin(Coin coin, Enemy enemy) {
        double dx = coin.getMidX() - enemy.getMidX();
        double dy = coin.getMidY() - enemy.getMidY();
        double d2 = dx * dx + dy * dy;

        return d2 < (coin.getRadius() + enemy.getRadius()) * (coin.getRadius() + enemy.getRadius());
    }

    // Check if the hero and enemy collide
    public static boolean checkCollisionHero(Hero hero, Enemy enemy) {
        double dx = hero.getMidX() - enemy.getMidX();
        double dy = hero.getMidY() - enemy.getMidY();
        double d2 = dx * dx + dy * dy;
        return d2 < (hero.getRadius() + enemy.getRadius()) * (hero.getRadius() + enemy.getRadius());
    }


    // Check if the hero and bullet collide
    public static boolean checkCollisionBullet(Hero hero, Bullet bullet) {
        if (bullet.getActive()){
            double dx = hero.getMidX() - bullet.getMidX();
            double dy = hero.getMidY() - bullet.getMidY();
            double d2 = dx * dx + dy * dy;
            return d2 < (hero.getRadius() + bullet.getRadius()) * (hero.getRadius() + bullet.getRadius());
        }else {
            return false;
        }
    }
    public static void checkCollisionsShoot(List<Hero> heroes, Enemy enemy) {
        for (Hero hero : heroes) {
            if (checkCollisionBullet(hero, enemy.getBullet())) {
                hero.isActivated = false;
                hero.resetHeroPosition();
                enemy.killReward(hero);
            }
        }
    }
}
