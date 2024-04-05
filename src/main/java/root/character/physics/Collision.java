package character.physics;

import character.Enemy;
import character.item.Coin;

public class Collision {
    public static boolean checkCollision(Coin coin, Enemy enemy) {
        Hitbox coinHitbox = coin.getHitbox();
        Hitbox enemyHitbox = enemy.getHitbox();
        return coinHitbox.intersects(enemyHitbox);
    }
}
