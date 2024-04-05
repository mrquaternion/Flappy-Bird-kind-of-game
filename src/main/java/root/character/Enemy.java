package character;

import character.physics.Background;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import character.physics.Hitbox;

public class Enemy extends Character {
    Image[] characterImage = new Image[3];
    ImageView imageViewEnemy;
    Hitbox hitbox = new Hitbox();


    private int pickupCoin = 0;
    public Enemy(){
        this.x = 50;
        this.y = 315;
        width = 394;
        height = 211;
        healthStatus = 100;
        characterImage[0] = new Image("file:src/main/resources/luffysprite.png");
    }

    // -------------- Getters --------------
    public int getX() { return x; }
    public int getY() { return y; }
    public ImageView getImageView(){
        setImageView();
        return imageViewEnemy;
    }

    public Image getImage() { return characterImage[0]; }

    public int getPickupCoin() { return pickupCoin; }

    public int getHealthStatus() { return healthStatus; }

    // -------------- Setters --------------
    public void setImageView(){
        ImageView imageViewEnemy = new ImageView(characterImage[0]);
        imageViewEnemy.setFitWidth(characterImage[0].getWidth() * 0.45);
        imageViewEnemy.setFitHeight(characterImage[0].getHeight() * 0.45);
        imageViewEnemy.setPreserveRatio(true);
        imageViewEnemy.setY(y);
        imageViewEnemy.setX(x);
        this.imageViewEnemy = imageViewEnemy;
    }

    // -------------- Methods --------------
    @Override
    public Image characterImage() {
        return characterImage[0];
    }

    public void increasePickupCoin() {
        this.pickupCoin += 1;
        Background.speedUpdate(pickupCoin);
    }

    public Hitbox getHitbox() {
        setHitbox();
        return hitbox;
    }

    public void setHitbox() {
        hitbox.setX(x);
        hitbox.setY(y);
        hitbox.setWidth(imageViewEnemy.getFitWidth());
        hitbox.setHeight(imageViewEnemy.getFitHeight());
    }
}

