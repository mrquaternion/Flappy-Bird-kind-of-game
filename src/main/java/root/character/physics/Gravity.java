package character.physics;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class Gravity {
    private static final double GRAVITY = 500;
    private static final double JUMP_VELOCITY = -300;
    private double velocity = 0;
    private boolean jumping = false;
    private boolean isGravity = true;

    private AnimationTimer animationTimer;
    public void applyGravity(Scene scene, ImageView imageViewEnemy) {

        // Laisser le personnage sauter avec la touche espace
        scene.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.Z && !jumping) {
                velocity = JUMP_VELOCITY;
                jumping = true;
            }
        });

        animationTimer = new AnimationTimer()  {
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
                    System.out. println(velocity);
                    velocity = 300;
                }
                double newY = imageViewEnemy.getY() + velocity * deltaTime;

                // Regarder si le personnage est au sol (Y = 315)
                if (newY > 315) {
                    velocity = JUMP_VELOCITY;
                }

                if (jumping){
                    jumpingInterval++;
                }
                if (jumpingInterval == 20){
                    jumping = false;
                    jumpingInterval = 0;
                }

                if (newY < 0){
                    velocity = -JUMP_VELOCITY;
                }
                // Met à jour la position du personnage
                if (isGravity){
                    imageViewEnemy.setY(newY);
                }
                lastUpdateTime = now;

            }
        };
        animationTimer.start();
    }
    public void disableGravity() {
        isGravity = false;
        System.out.println(isGravity);

    }


    public void enableGravity(){
        isGravity = true;
        System.out.println(isGravity);
        /*
        if (animationTimer != null) {
            animationTimer.start();
        }*/
    }
}
