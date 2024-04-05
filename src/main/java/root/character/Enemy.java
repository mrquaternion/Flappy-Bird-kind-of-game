package character;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Enemy extends Character {
    Image[] characterImage = new Image[3];
    ImageView imageViewEnemy;
    private int pickupCoin = 0;
    public Enemy(){
        x = 50;
        y = 315;
        width = 394;
        height = 211;
        healthStatus = 100;
        characterImage[0] = new Image("file:/Users/tomstanic/IdeaProjects/TP2/src/main/resources/luffysprite.png");
    }
    @Override
    public Image characterImage() {
        return characterImage[0];
    }

    public void setImageView(){
        ImageView imageViewEnemy = new ImageView(characterImage[0]);
        imageViewEnemy.setFitWidth(characterImage[0].getWidth() * 0.45);
        imageViewEnemy.setFitHeight(characterImage[0].getHeight() * 0.45);
        imageViewEnemy.setPreserveRatio(true);
        imageViewEnemy.setY(y);
        imageViewEnemy.setX(x);
        this.imageViewEnemy = imageViewEnemy;
    }

    public ImageView getImageView(){
        return imageViewEnemy;
    }

    public int getPickupCoin() {
        return pickupCoin;
    }

    public void increasePickupCoin () {
        this.pickupCoin += 1;
    }

    public int getHealthStatus() {
        return healthStatus;
    }
}

