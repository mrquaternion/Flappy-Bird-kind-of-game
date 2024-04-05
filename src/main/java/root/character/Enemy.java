package character;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import character.physics.Hitbox;

public class Enemy extends Character {

    private int pickupCoin = 0;
    private double ratio;

    // -------------- Constructor --------------
    public Enemy(){
        ratio = 0.45;
        healthStatus = 100;
        characterImage = new Image("file:src/main/resources/luffysprite.png");
    }

    // -------------- Getters --------------
    public ImageView getImageView() {
        return imageViewCharacter;
    }
    public Image getImage() { return characterImage; }
    public int getPickupCoin() { return pickupCoin; }
    public int getHealthStatus() { return healthStatus; }
    public Hitbox getHitbox() {
        setHitbox();
        return hitbox;
    }

    // -------------- Setters --------------
    @Override
    public void setImageView(){
        ImageView imageViewEnemy = new ImageView(characterImage);
        imageViewEnemy.setFitWidth(characterImage.getWidth() * ratio);
        imageViewEnemy.setFitHeight(characterImage.getHeight() * ratio);
        imageViewEnemy.setPreserveRatio(true);
        imageViewEnemy.setY(400 -  imageViewEnemy.getFitHeight());
        imageViewEnemy.setX(50);
        this.imageViewCharacter = imageViewEnemy;
    }

    // -------------- Methods --------------


    public void increasePickupCoin() {
        this.pickupCoin += 1;
    }


}

