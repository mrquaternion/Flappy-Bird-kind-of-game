package character.physics;

import character.Enemy;
import character.Hero;
import character.item.Coin;
import character.item.Bullet;

import java.util.List;

public class Collision {
    public static boolean checkCollisionCoin(Coin coin, Enemy enemy) {
        double dx = coin.getMidX() - enemy.getMidX();
        double dy = coin.getMidY() - enemy.getMidY();
        double d2 = dx * dx + dy * dy;

        return d2 < (coin.getRadius() + enemy.getRadius()) * (coin.getRadius() + enemy.getRadius());
    }

    public static boolean checkCollisionHero(Hero hero, Enemy enemy) {
        double dx = hero.getMidX() - enemy.getMidX();
        double dy = hero.getMidY() - enemy.getMidY();
        double d2 = dx * dx + dy * dy;
        return d2 < (hero.getRadius() + enemy.getRadius()) * (hero.getRadius() + enemy.getRadius());
    }

    public static boolean checkCollisionBullet(Hero hero,Bullet bullet) {
        double dx = hero.getMidX() - bullet.getMidX();
        double dy = hero.getMidY() - bullet.getMidY();
        double d2 = dx * dx + dy * dy;
        return d2 < (hero.getRadius() + bullet.getRadius()) * (hero.getRadius() + bullet.getRadius());
    }

    public static Hero checkCollisionBullet(List<Hero> heroes, Bullet bullet) {
        for (Hero hero : heroes) {
            if (checkCollisionBullet(hero, bullet)) {
                hero.isActivated = false;
                return hero;
            }
        }
        return null;
    }
}
