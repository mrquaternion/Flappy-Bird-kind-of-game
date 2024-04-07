package character.item;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class Bullet {

    private final Image image;
    private final ImageView imageView;

    private double r;


    public Bullet(double startX, double startY) {
        this.image = new Image("file:src/main/resources/chopper.png");
        this.imageView = new ImageView(image);
        imageView.setX(startX);
        imageView.setY(startY);
        setImageView();
    }

    public void setImageView() {
        imageView.setFitWidth(image.getWidth() * 0.3);
        imageView.setFitHeight(image.getHeight() * 0.3);
        imageView.setPreserveRatio(true);
    }

    public void updatePosition(double dt) {
        // frame per second
        double speed = 500;
        imageView.setX(imageView.getX() + speed * dt);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public double getMidX(){
        return imageView.getX() + imageView.getFitWidth() / 2;
    }

public double getMidY(){
        return imageView.getY() + imageView.getFitHeight() / 2;
    }

    public double getRadius() {
        return r;
    }

    public void setRadius() {
        this.r = imageView.getFitHeight() / 2;
    }
}
