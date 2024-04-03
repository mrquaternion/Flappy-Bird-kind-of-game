package character;

import javafx.scene.image.Image;

public class Enemy implements Character {
    private int x;
    private int y;
    private int width;
    private int height;
    protected boolean healthStatus = true;
    @Override
    public void attack(Character target) {
        System.out.println("The enemy attacks the target.");
    }

    @Override
    public Image characterImage() {
        return new Image("file:src/main/resources/coin.png");
    }
}
