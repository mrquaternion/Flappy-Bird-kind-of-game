package character;

import character.physics.Background;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import character.physics.Hitbox;

public class Enemy extends Character {
    Image[] characterImage = new Image[3];
    ImageView imageView;
    Hitbox hitbox = new Hitbox();
    private int pickupCoin = 0;
    double vy = 0;
    double newY = 0;
    public static final double GRAVITY = 500;
    public static final double JUMP_VELOCITY = 300;
    public boolean jumpingStatus = false;


    // -------------- Constructor --------------
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
    public ImageView getImageView() {
        return imageView;
    }
    public Image getImage() { return characterImage[0]; }
    public int getPickupCoin() { return pickupCoin; }
    public int getHealthStatus() { return healthStatus; }
    public Hitbox getHitbox() {
        setHitbox();
        return hitbox;
    }

    // -------------- Setters --------------
    public void setImageView(){
        ImageView imageView = new ImageView(characterImage[0]);
        imageView.setFitWidth(characterImage[0].getWidth() * 0.45);
        imageView.setFitHeight(characterImage[0].getHeight() * 0.45);
        imageView.setPreserveRatio(true);
        imageView.setY(y);
        imageView.setX(x);
        this.imageView = imageView;
    }

    // -------------- Methods --------------
    @Override
    public Image characterImage() {
        return characterImage[0];
    }

    public void increasePickupCoin() {
        this.pickupCoin += 1;
    }

    public void setHitbox() {
        hitbox.setX(imageView.getX());
        hitbox.setY(imageView.getY());
        hitbox.setWidth(imageView.getFitWidth());
        hitbox.setHeight(imageView.getFitHeight());
    }

    public void notJumping() {
        // L'ennemi ne saute pas alors il bounce
        vy = JUMP_VELOCITY;
        jumpingStatus = true;
    }

    public void updatePosition(double dt) {
        // Calcul de la nouvelle vitesse et la nouvelle position de l'ennemi
        vy += GRAVITY * dt;
        newY = imageView.getY() + (vy * dt);

        // On update la position de l'ennemi
        imageView.setY(newY);

        // On vérifie si l'ennemi est au sol
        if (imageView.getY() > 315) {
            vy = JUMP_VELOCITY;
        } else if (newY < 0) {
            vy = -JUMP_VELOCITY;
        }
    }
}

