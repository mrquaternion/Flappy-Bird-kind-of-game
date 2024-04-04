package character.physics;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

public class Gravity {
    private static final double GRAVITY = 0.4;
    private double velocity = 0;
    private boolean jumping = false;

    public void applyGravity(Scene scene, ImageView imageView) {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case SPACE:
                    if (!jumping) {
                        velocity = -12;
                        jumping = true;
                    }
                    break;
                default:
                    break;
            }
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                velocity += GRAVITY;
                imageView.setY(imageView.getY() + velocity);

                if (imageView.getY() > 315) {
                    velocity = 0;
                    imageView.setY(315);
                    jumping = false;
                }
            }
        };

        timer.start();
    }
}
