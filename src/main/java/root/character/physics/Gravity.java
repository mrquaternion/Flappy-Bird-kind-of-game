package character.physics;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class Gravity {
    private static final double GRAVITY = 500; // Gravity acceleration pixels per second squared
    private double velocity = 0; // Y velocity of the character
    private boolean jumping = false;

    public void applyGravity(Scene scene, ImageView imageViewEnemy) {
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE && !jumping) {
                velocity = -300; // Set velocity to 300 pixels per second upwards
                jumping = true;
            }
        });

        new AnimationTimer() {
            private long lastUpdateTime = 0;

            @Override
            public void handle(long now) {
                if (lastUpdateTime == 0) {
                    lastUpdateTime = now;
                    return;
                }

                double elapsedTimeInSeconds = (now - lastUpdateTime) / 1e9; // Convert nanoseconds to seconds
                velocity += GRAVITY * elapsedTimeInSeconds; // Apply gravity
                double newY = imageViewEnemy.getY() + velocity * elapsedTimeInSeconds;

                // Check if the character has landed (assuming 315 is the ground position)
                if (newY > 315) {
                    newY = 315;
                    velocity = 0;
                    jumping = false;
                }

                imageViewEnemy.setY(newY);
                lastUpdateTime = now;
            }
        }.start();
    }
}
