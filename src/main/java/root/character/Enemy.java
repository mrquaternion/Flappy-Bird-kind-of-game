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
    ImageView imageView = new ImageView();

    private int pickupCoin = 0;
    private int allCoin = 0;
    double vy = 0;
    double newY = 0;
    public static final double GRAVITY = 500;
    public static final double JUMP_VELOCITY = 300;
    public boolean jumpingStatus = false;

    public int jumpingInterval = 0;

    private Bullet bullet;



    public long lastBulletTime = 0;

    public Image[] frames;

    // -------------- Constructor --------------
    public Enemy() {
        this.ratio = 0.35; // 0.16
        this.healthStatus = 100;
        frames = new Image[] {
                new Image("file:src/main/resources/luffy_1.png"),
                new Image("file:src/main/resources/luffy_2.png"),
                new Image("file:src/main/resources/luffy_3.png"),
                new Image("file:src/main/resources/luffy_4.png")
        };
        this.imageView.setImage(frames[0]);
        System.out.println("lol" + imageView);
        r = 30;
        setImageView();
        bullet = new Bullet();
    }

    // -------------- Setters --------------
    public void setImageView() {
        imageView.setFitWidth(imageView.getImage().getWidth() * ratio);
        imageView.setFitHeight(imageView.getImage().getHeight() * ratio);
        imageView.setPreserveRatio(true);
    }

    public void setCurrentImageView(Image newImage) {
        //System.out.println(imageView);
        imageView.setImage(newImage);
    }

    public void setAllCoin(int allCoin) {
        this.allCoin = allCoin;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }
    public void setBullet() {
        bullet = new Bullet();
    }

    public void setBulletPosition(double x, double y) {
        bullet.getImageView().setX(x);
        bullet.getImageView().setY(y);
    }

    public void setBulletActive() {
        bullet.setActive();
    }


    // -------------- Getters --------------
    public int getPickupCoin() {
        return pickupCoin;
    }

    public int getHealthStatus() {
        return healthStatus;
    }

    public ImageView getImageView() {

        return this.imageView;
    }

    @Override
    public double getMidX() {
        return imageView.getX() + imageView.getFitWidth() / 2;
    }

    @Override
    public double getMidY(){
        return imageView.getY() + imageView.getFitHeight() / 2;
    }

    public int getAllCoin() {
        return allCoin;
    }

    public Bullet getBullet() {
        return bullet;
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
    public void updateBulletPosition(double dt) {
        if (bullet.getActive()) {
            bullet.updatePosition(dt);
        }
    }

    public void updateBulletCooldown(double dt) {
        if (bullet.getBulletCooldown() < 1 && bullet.getActive()){
            bullet.updateBulletCooldown(dt);
        }else if (bullet.getBulletCooldown() >= 1 && bullet.getActive()){
            bullet.setDesactive();
            bullet.setBulletCooldown(0);
        }
    }

}





