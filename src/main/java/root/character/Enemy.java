package character;

import character.hero.Melee;
import character.hero.Tank;
import character.item.Bullet;
import character.physics.Background;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

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

    public Bullet bullet;  // Assume enemy can have only one bullet at a time
    public boolean bulletAvailable = true;
    public double lastBulletSpawnTime = 0;



    // -------------- Constructor --------------
    public Enemy() {
        this.ratio = 0.35; // 0.16
        this.healthStatus = 100;
        this.image = new Image("file:src/main/resources/luffysprite.png");
        this.imageView = new ImageView(image);
        r = 30;
        setImageView();
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

    public void setVy(double vy) {
        this.vy = vy;
    }


    // -------------- Getters --------------
    public Bullet getBullet() { return bullet; }
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
        vy += (GRAVITY + 15 * pickupCoin) * dt;
        if (vy > 300) {
            vy = 300;
        }
        newY = imageView.getY() + (vy * dt) ;

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




    public boolean death() { return healthStatus <= 0; }

    public void killReward(Hero hero){
        if (hero instanceof Melee) {
            allCoin += 5;
        } else if(hero instanceof Tank){
            allCoin +=10;
        } else {
            allCoin += 7;
        }
    }

    public void setBullet(Object o) {
        this.bullet = (Bullet)o;
    }
}



