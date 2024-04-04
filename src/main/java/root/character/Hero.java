package character;

import javafx.scene.image.Image;

public class Hero  extends Character {
    protected int x;
    protected int y;
    protected int width;
    protected int height;



    @Override
    public Image characterImage() {
        return new Image("file:src/main/resources/enemy.png");
    }
}
