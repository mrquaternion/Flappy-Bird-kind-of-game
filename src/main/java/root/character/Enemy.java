package character;

import character.physics.Background;
import character.physics.Gravity;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import character.physics.Hitbox;

public class Enemy extends Character {

    ImageView imageViewEnemy;

    private int pickupCoin = 0;
    double vy = 0;
    double newY = 0;
    public static final double GRAVITY = 500;
    public static final double JUMP_VELOCITY = 300;
    public boolean jumpingStatus = false;

    public int jumpingInterval = 0;

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
        imageViewEnemy.setY(Background.HEIGHT -  imageViewEnemy.getFitHeight());
        imageViewEnemy.setX(50);
        this.imageViewCharacter = imageViewEnemy;
    }

    // -------------- Methods --------------


    public void increasePickupCoin() {
        this.pickupCoin += 1;
    }

    public void notJumping() {
        // L'ennemi ne saute pas alors il bounce
        vy = JUMP_VELOCITY;
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

    private  void borderTouch(){
        if (imageViewCharacter.getY() > Background.HEIGHT -(imageViewCharacter.getFitHeight())) {
            vy =  JUMP_VELOCITY;

        } else if (imageViewCharacter.getY() < 0) {
            vy =  -JUMP_VELOCITY;
        }
        }

    public void couldownJump(){
        if (jumpingStatus) {
            jumpingInterval++;
        }
        if (jumpingInterval == 20) {
            jumpingStatus = false;
            jumpingInterval = 0;
        }
    }
    }


