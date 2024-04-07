package character.item;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import character.physics.Background;


import character.physics.Hitbox;

public class Coin {
    protected double vx = 120;
    static final int frameRate = 60;
    protected boolean isActive = false;
    Image image;
    ImageView imageView;


    // -------------- Constructor --------------
    public Coin() {
        this.image = new Image("file:src/main/resources/coin.png");
        this.imageView = new ImageView(image);
        this.imageView.setX(Background.WIDTH);
        this.imageView.setY(Math.random() * (Background.HEIGHT - imageView.getFitHeight()));
    }

    // -------------- Getters --------------

    public Image getImage() {
        return image;
    }

    public ImageView getImageView() {
        setImageView();
        return imageView;
    }

    public Hitbox getHitbox() {
        Hitbox hitbox = new Hitbox();
        hitbox.setX(imageView.getX());
        hitbox.setY(imageView.getY());
        hitbox.setWidth(image.getWidth() * 0.02);
        hitbox.setHeight(image.getHeight() * 0.02);
        return hitbox;
    }

    // -------------- Setters --------------

    public void setImageView() {
        imageView.setFitWidth(image.getWidth() * 0.02);
        imageView.setFitHeight(image.getHeight() * 0.02);
        imageView.setPreserveRatio(true);
    }

    // -------------- Methods --------------
    public void updatePosition(int nbOfCoins, double dt) {
        imageView.setX(imageView.getX() - ((vx / frameRate) + (nbOfCoins * 10) * dt));
    }
}
