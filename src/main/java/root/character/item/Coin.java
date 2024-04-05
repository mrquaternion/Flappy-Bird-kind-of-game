package character.item;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.util.Random;

import character.physics.Hitbox;

public class Coin {
    protected double x;
    protected double y;
    protected double vx = 120;


    public static final int BACKGROUND_HEIGHT = 400;
    public final static Image image = new Image("file:src/main/resources/coin.png");
    ImageView imageView = new ImageView(image);

    // -------------- Constructor --------------
    public Coin() {
        this.x = 640;
        Random random = new Random();
        this.y = random.nextDouble() * (BACKGROUND_HEIGHT - imageView.getFitHeight());
    }

    // -------------- Getters --------------
    public double getX() { return x; }
    public double getY() { return y; }
    public static Image getImage() { return image; }
    public ImageView getImageView() {
        setImageView();
        return imageView;
    }

    // -------------- Setters --------------
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }

    public void setImageView(){
        imageView.setFitWidth(image.getWidth() * 0.02);
        imageView.setFitHeight(image.getHeight() * 0.02);
        imageView.setPreserveRatio(true);
        imageView.setY(y);
        imageView.setX(x);
    }

    // -------------- Methods --------------
    public void updateSpeed(double dt) {
        x -= vx * dt;
    }

    public Hitbox getHitbox() {
        Hitbox hitbox = new Hitbox();
        hitbox.setX(x);
        hitbox.setY(y);
        hitbox.setWidth(image.getWidth() * 0.02);
        hitbox.setHeight(image.getHeight() * 0.02);
        return hitbox;
    }
}
