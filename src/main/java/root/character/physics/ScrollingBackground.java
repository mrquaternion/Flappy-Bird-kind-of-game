package character.physics;

import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;

public class ScrollingBackground {
    private double backgroundWidth_1 = 0;
    private double backgroundWidth_2 = 640;
    public void scroll(ImageView imageViewBackground_1, ImageView imageViewBackground_2) {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Mise à jour de la position de backgroundWidth et imageViewBackground
                backgroundWidth_1 = imageViewBackground_1.getLayoutX();
                backgroundWidth_1 -= 2;
                imageViewBackground_1.setLayoutX(backgroundWidth_1);
                if (backgroundWidth_1 == -640.0) {
                    imageViewBackground_1.setLayoutX(640);
                }

                // Mise à jour pour le deuxième background
                backgroundWidth_2 -= 2;
                imageViewBackground_2.setLayoutX(backgroundWidth_2);
                if (backgroundWidth_2 == -640.0) {
                    backgroundWidth_2 = 640;
                }
            }
        }.start();
    }
}
