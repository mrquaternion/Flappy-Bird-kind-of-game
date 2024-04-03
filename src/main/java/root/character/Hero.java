package character;

import javafx.scene.image.Image;

public class Hero implements Character {
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    @Override
    public void attack(Character target) {
        System.out.println("The hero attacks the target.");
    }

    @Override
    public Image characterImage() {
        return new Image("file:src/main/resources/enemy.png");
    }
}
