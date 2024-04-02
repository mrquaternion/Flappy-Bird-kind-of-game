import character.*;
import character.hero.*;

public class Main {
    public static void main(String[] args) {
        Hero hero = new Melee();
        Enemy enemy = new Enemy();
        hero.attack(enemy);
        enemy.attack(hero);
    }
}