package character;

import character.physics.Background;
import character.physics.Gravity;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import character.physics.Hitbox;

public class Enemy extends Character {


    private int pickupCoin = 0;
    private int allCoin = 0;
    double vy = 0;
    double newY = 0;
    public static final double GRAVITY = 500;
    public static final double JUMP_VELOCITY = 300;
    public boolean jumpingStatus = false;

    public int jumpingInterval = 0;
    public boolean go = true;


    // -------------- Constructor --------------
    public Enemy() {
        ratio = 0.45;
        healthStatus = 100;
        characterImage = new Image("file:src/main/resources/luffysprite.png");
    }

    // -------------- Getters --------------

    public int getPickupCoin() {
        return pickupCoin;
    }

    public int getHealthStatus() {
        return healthStatus;
    }


    @Override
    protected void imageViewCharacterSet() {
        imageViewCharacter.setY(Background.HEIGHT - imageViewCharacter.getFitHeight());
        imageViewCharacter.setX(50);
    }

    public void increasePickupCoin() {
        this.pickupCoin += 1;
        this.allCoin += 1;
    }


    public void increaseAllCoin() {
        this.allCoin += 1;
    }

    public int getAllCoin() {
        return allCoin;
    }

    public void setAllCoin(int allCoin) {
        this.allCoin = allCoin;
    }


    public void notJumping() {
        // L'ennemi ne saute pas alors il bounce
        vy = -JUMP_VELOCITY;
        jumpingStatus = true;
    }


    public void updatePosition(double dt) {
        // Calcul de la nouvelle vitesse et la nouvelle position de l'ennemi
        vy += GRAVITY * dt;
        newY = imageViewCharacter.getY() + (vy * dt);

        // On update la position de l'ennemi
        imageViewCharacter.setY(newY);

        // On vérifie si l'ennemi est au sol
        borderTouch();
    }

    private void borderTouch() {
        if (imageViewCharacter.getY() > Background.HEIGHT - (imageViewCharacter.getFitHeight())) {
            vy = -JUMP_VELOCITY;

        } else if (imageViewCharacter.getY() < 0) {
            vy = JUMP_VELOCITY;
        }
    }

    public void couldownJump() {
        if (jumpingStatus) {
            jumpingInterval++;
        }
        if (jumpingInterval == 20) {
            jumpingStatus = false;
            jumpingInterval = 0;
        }
    }
    public void gravityBlock () {
        go = false;
    }
    public void gravityUnblock () {
        go = true;
    }
}



