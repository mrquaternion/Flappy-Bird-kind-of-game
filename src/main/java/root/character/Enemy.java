package character;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Enemy extends Character {
    Image[] characterImage = new Image[3];
    private int pickupCoin = 0;
    public Enemy(){
        x = 50;
        y = 315;
        width = 394;
        height = 211;
        characterImage[0] = new Image("file:src/main/resources/luffysprite.png");
    }
    @Override
    public Image characterImage() {
        return characterImage[0];
    }

    public ImageView setImageView(){
        ImageView imageViewEnemy = new ImageView(characterImage[0]);
        imageViewEnemy.setFitWidth(characterImage[0].getWidth() * 0.45);
        imageViewEnemy.setFitHeight(characterImage[0].getHeight() * 0.45);
        imageViewEnemy.setPreserveRatio(true);
        imageViewEnemy.setY(y);
        imageViewEnemy.setX(x);
        return imageViewEnemy;
    }

    public int getPickupCoin() {
        return pickupCoin;
    }

    public void increasePickupCoin (int pickupCoin) {
        this.pickupCoin += 1;
    }
}

