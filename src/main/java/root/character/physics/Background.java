package character.physics;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Background {
    private ImageView imageViewBackground_1;
    private ImageView imageViewBackground_2;
    private  int speed;

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

    public void scroll(int speedBoostCoin) {

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                speed = 6 + 10*speedBoostCoin;
                imageViewBackground_1.setLayoutX(imageViewBackground_1.getLayoutX()-speed);
                imageViewBackground_2.setLayoutX(imageViewBackground_2.getLayoutX()-speed);
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
}