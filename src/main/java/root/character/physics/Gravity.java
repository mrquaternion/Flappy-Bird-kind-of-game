package character.physics;

import character.Enemy;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class Gravity {
    private static final double GRAVITY = 500;
    protected static final double JUMP_VELOCITY = -300;
    private double velocity = 0;
    private boolean jumping = false;
    private boolean isGravity = true;

    public void applyGravity(Scene scene, ImageView imageViewEnemy) {

        // Laisser le personnage sauter avec la touche espace
        scene.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.W && !jumping) {
                velocity = JUMP_VELOCITY;
                jumping = true;
            }
        });

        // Calcul le temps entre le frame actuel et le frame précédent
        // Calcul de la nouvelle vitesse et la nouvelle position du personnage
        // Regarder si le personnage est au sol (Y = 315)
        // Met à jour la position du personnage
        AnimationTimer animationTimer = new AnimationTimer() {
            private long lastUpdateTime = 0;
            private int jumpingInterval = 0;

            @Override
            public void handle(long now) {

                if (lastUpdateTime == 0) {
                    lastUpdateTime = now;
                    return;
                }

                // Calcul le temps entre le frame actuel et le frame précédent
                double deltaTime = (now - lastUpdateTime) / 1e9;

                // Calcul de la nouvelle vitesse et la nouvelle position du personnage
                velocity += GRAVITY * deltaTime;
                if (velocity > 300) {
                    velocity = 300;
                }
                double newY = imageViewEnemy.getY() + velocity * deltaTime;

                // Regarder si le personnage est au sol (Y = 315)
                if (newY > 315) {
                    velocity = JUMP_VELOCITY;
                }

                if (jumping) {
                    jumpingInterval++;
                }
                if (jumpingInterval == 20) {
                    jumping = false;
                    jumpingInterval = 0;
                }

                if (newY < 0) {
                    velocity = -JUMP_VELOCITY;
                }
                // Met à jour la position du personnage
                if (isGravity) {
                    imageViewEnemy.setY(newY);
                }
                lastUpdateTime = now;

            }
        };
        animationTimer.start();
    }
    public void disableGravity() {
        isGravity = false;
       // System.out.println(isGravity);
    }
/*

    public void enableGravity(){
        isGravity = true;
        System.out.println(isGravity);
        /*
        if (animationTimer != null) {
            animationTimer.start();
        }*/
    }


    /*
    private void couldownJump(){
        if (jumping) {
            jumpingInterval++;
        }
        if (jumpingInterval == 20) {
            jumping = false;
            jumpingInterval = 0;
        }
    }

    private void coordonneesUpdate(ImageView imageViewEnemy, double newY){
        // Met à jour la position du personnage
        if (isGravity) {
            imageViewEnemy.setY(newY);
        }
    }
     */



