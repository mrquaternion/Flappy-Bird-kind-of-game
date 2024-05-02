package character.item;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import character.physics.Background;

public class Coin {
    protected double vx = 120;
    static final int frameRate = 60;
    protected boolean isActive = false;
    Image image;
    ImageView imageView;
    private double r;

    // -------------- Constructor --------------
    public Coin() {
        this.image = new Image("file:src/main/resources/coin.png");
        this.imageView = new ImageView(image);
        setImageView();
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

    public double getRadius() {
        return r;
    }

    public double getMidX(){
        return imageView.getX() + imageView.getFitWidth() / 2;
    }


    public double getMidY(){
        return imageView.getY() + imageView.getFitHeight() / 2;
    }

    // -------------- Setters --------------

    public void setImageView() {
        imageView.setFitWidth(image.getWidth() * 0.02);
        imageView.setFitHeight(image.getHeight() * 0.02);
        imageView.setPreserveRatio(true);
    }

    public void setRadius() {
        this.r = imageView.getFitHeight() / 2;
    }

    // -------------- Methods --------------
    public void updatePosition(int nbOfCoins, double dt) {
        imageView.setX(imageView.getX() - ((vx / frameRate) + (nbOfCoins * 10) * dt));
    }
}
