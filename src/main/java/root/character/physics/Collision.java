package character.physics;

import character.Enemy;
import character.Hero;
import character.item.Coin;
import character.item.Bullet;

public class Collision {
    public static boolean checkCollisionCoin(Coin coin, Enemy enemy) {
        Hitbox coinHitbox = coin.getHitbox();
        Hitbox enemyHitbox = enemy.getHitbox();
        return coinHitbox.intersects(enemyHitbox);
    }

    public static boolean checkCollisionHero(Hero hero, Enemy enemy) {
        Hitbox heroHitbox = hero.getHitbox();
        Hitbox enemyHitbox = enemy.getHitbox();
        return heroHitbox.intersects(enemyHitbox);
    }

    public static Hero checkCollisionBullet(Hero[] heroes, Bullet bullet) {
        for (Hero hero : heroes) {
            Hitbox heroHitbox = hero.getHitbox();
            Hitbox bulletHitbox = bullet.getHitbox();
            if (heroHitbox.intersects(bulletHitbox)) {
                return hero;
            }
        }
        return null;
    }
    protected static double borderTouch(Enemy enemy, double velocity){
        if (enemy.getImageView().getY() > Background.HEIGHT -(enemy.getImageView().getFitHeight())) {
            return  Gravity.JUMP_VELOCITY;

        } else if (enemy.getImageView().getY() < 0) {
            return  -Gravity.JUMP_VELOCITY;
        } else{
            return velocity;
        }
    }
}
