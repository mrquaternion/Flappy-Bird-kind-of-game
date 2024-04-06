package character.physics;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Background {
    public static final int HEIGHT = 400;
    public static final int WIDTH = 640;
    private final ImageView imageViewBackground_1;
    private final ImageView imageViewBackground_2;
    private double speed;
    private boolean isScrolling = true;
    static final int SPEED_BOOST = 10;

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



                //System.out.println(speed);
                speed = 2 + ((2 * ((double)SPEED_BOOST * numberOfCoin)) /120);

                if (isScrolling) {
                    imageViewBackground_1.setLayoutX(imageViewBackground_1.getLayoutX() - speed);
                    imageViewBackground_2.setLayoutX(imageViewBackground_2.getLayoutX() - speed);
                }

                if (imageViewBackground_1.getLayoutX() <= -640.0) {
                    imageViewBackground_1.setLayoutX(640);
                    imageViewBackground_2.setLayoutX(0);
                } else if (imageViewBackground_2.getLayoutX() <= -640.0) {
                    imageViewBackground_2.setLayoutX(640);
                    imageViewBackground_1.setLayoutX(0);
                }


    }

    public void stopScroll() {
        isScrolling = false;
    }

    public void startScroll() {
        isScrolling = true;
    }
}
