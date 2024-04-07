package character.physics;

import character.Enemy;
import character.Hero;
import character.item.Coin;

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
