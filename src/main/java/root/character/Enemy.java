package character;

import character.physics.Background;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Enemy extends Character {
    Image image;
    ImageView imageView;

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
        this.ratio = 0.40;
        this.healthStatus = 100;
        this.image = new Image("file:src/main/resources/luffysprite.png");
        this.imageView = new ImageView(image);
        r = 30;
    }

    // -------------- Setters --------------
    public void setImageView() {
        imageView.setFitWidth(image.getWidth() * ratio);
        imageView.setFitHeight(image.getHeight() * ratio);
        imageView.setPreserveRatio(true);
    }


    public void setAllCoin(int allCoin) {
        this.allCoin = allCoin;
    }


    // -------------- Getters --------------

    public int getPickupCoin() {
        return pickupCoin;
    }

    public int getHealthStatus() {
        return healthStatus;
    }

    public ImageView getImageView() {
        return imageView;
    }


    @Override
    public double getMidX(){
        return imageView.getX() + imageView.getFitWidth() / 2;
    }

    @Override
    public double getMidY(){
        return imageView.getY() + imageView.getFitHeight() / 2;
    }



    public int getAllCoin() {
        return allCoin;
    }



    //----------------- Coin -----------------

    public void increaseAllCoin() {
        this.allCoin += 1;
    }
    public void increasePickupCoin() {
        this.pickupCoin += 1;
        this.allCoin += 1;
    }

    // -------------- Physics --------------

    public void isJumping() {
        // L'ennemi ne saute pas alors il bounce
        vy = -JUMP_VELOCITY;
        jumpingStatus = true;
    }
    public void updatePosition(double dt) {
        // Calcul de la nouvelle vitesse et la nouvelle position de l'ennemi
        vy += GRAVITY * dt;
        newY = imageView.getY() + (vy * dt);

        // On update la position de l'ennemi
        imageView.setY(newY);

        // On vérifie si l'ennemi est au sol
        borderTouch();
    }
    @Override
    protected void borderTouch() {
        if (imageView.getY() > Background.HEIGHT - (imageView.getFitHeight())) {
            vy = -JUMP_VELOCITY;

        } else if (imageView.getY() < 0) {
            vy = JUMP_VELOCITY;
        }
    }

    public void jumpCooldown(){
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



