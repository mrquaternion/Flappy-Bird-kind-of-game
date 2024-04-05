package character.physics;

import character.Enemy;
import character.item.Coin;

public class Collision {
    public static boolean checkCollision(Coin coin, Enemy enemy) {
        Hitbox coinHitbox = coin.getHitbox();

        Hitbox enemyHitbox = enemy.getHitbox();
        System.out.println("Enemy hitbox: " + enemyHitbox.getWidth() + ", " + enemyHitbox.getHeight());
        System.out.println("Coin hitbox: " + coinHitbox.getWidth() + ", " + coinHitbox.getHeight());
        return coinHitbox.intersects(enemyHitbox);
    }
}
