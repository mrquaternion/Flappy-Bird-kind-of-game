package character.physics;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Background {
    private final ImageView imageViewBackground_1;
    private final ImageView imageViewBackground_2;
    public static double speed;

    public Background() {
        Image imageBackground = new Image("file:src/main/resources/bg.png");
        this.imageViewBackground_1 = new ImageView(imageBackground);
        this.imageViewBackground_2 = new ImageView(imageBackground);
        imageViewBackground_2.setLayoutX(640);
    }

    public ImageView getImageViewBackground_1() {
        return imageViewBackground_1;
    }

    public ImageView getImageViewBackground_2() {
        return imageViewBackground_2;
    }

    public void scroll(int numberOfCoin) {

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                speedUpdate(numberOfCoin);

                imageViewBackground_1.setLayoutX(imageViewBackground_1.getLayoutX() - speed);
                imageViewBackground_2.setLayoutX(imageViewBackground_2.getLayoutX() - speed);
                if (imageViewBackground_1.getLayoutX() <= -640.0) {
                    imageViewBackground_1.setLayoutX(640);
                    imageViewBackground_2.setLayoutX(0);
                } else if (imageViewBackground_2.getLayoutX() <= -640.0) {
                    imageViewBackground_2.setLayoutX(640);
                    imageViewBackground_1.setLayoutX(0);
                }
            }
        }.start();
    }

    public static void speedUpdate(int numberOfCoin) {
        double SPEED_BOOST = 10;
        speed = 2 + numberOfCoin * (2 * (SPEED_BOOST / 120));
    }
}
