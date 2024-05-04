package character.item;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class Bullet {
    private final Image image;
    private final ImageView imageView;
    private double r;
    private double ratio;
    private boolean isActive = false;
    private double bulletCooldown = 0;

    // -------------- Constructor --------------
    public Bullet() {
        this.image = new Image("file:src/main/resources/chopper.png");
        this.imageView = new ImageView(image);
        imageView.setX(0);
        imageView.setY(0);
        ratio = 0.2; // 0.1
        isActive = false;
        setImageView();

    }

    // -------------- Setters --------------
    public void setImageView() {
        imageView.setFitWidth(image.getWidth() * ratio);
        imageView.setFitHeight(image.getHeight() * ratio);
        imageView.setPreserveRatio(true);
        imageView.setVisible(false);
    }

    public void setActive() {
        imageView.setVisible(true);
        isActive = true;
    }
    public void setDesactive() {
        imageView.setVisible(false);
        isActive = false;
    }

    public void setBulletCooldown(double x){
        bulletCooldown = x;
    }
    // -------------- Getters --------------
    public ImageView getImageView() {
        return imageView;
    }

    public double getMidX() {
        return imageView.getX() + imageView.getFitWidth() / 2;
    }

    public double getMidY() {
        return imageView.getY() + imageView.getFitHeight() / 2;
    }

    public double getRadius() {
        return r;
    }

    public boolean getActive() {
        return isActive;
    }

    // -------------- Update --------------
    public void updateBulletCooldown(double dt) {
        bulletCooldown += dt;
    }

    public void updatePosition(double dt) {
        // frame per second
        double speed = 3000;
        imageView.setX(imageView.getX() + speed * dt);
    }

    public double getBulletCooldown(){
        return bulletCooldown;
    }
}

