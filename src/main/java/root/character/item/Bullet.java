package character.item;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class Bullet {
    private final Image image;
    private final ImageView imageView;
    private double r;
    double ratio;
    boolean isActive = false;
    private double bulletCooldown = 0;


    public Bullet() {
        this.image = new Image("file:src/main/resources/chopper.png");
        this.imageView = new ImageView(image);
        imageView.setX(0);
        imageView.setY(0);
        ratio = 0.2; // 0.1
        isActive = false;
        setImageView();

    }

    public void setImageView() {
        imageView.setFitWidth(image.getWidth() * ratio);
        imageView.setFitHeight(image.getHeight() * ratio);
        imageView.setPreserveRatio(true);
        imageView.setVisible(false);
    }

    public void updatePosition(double dt) {
        // frame per second
        double speed = 3000;
        imageView.setX(imageView.getX() + speed * dt);
    }

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

    public void setActive() {
        imageView.setVisible(true);
        isActive = true;
    }
    public void setDesactive() {
        imageView.setVisible(false);
        isActive = false;
    }
    public boolean getActive() {
        return isActive;
    }
    public void updateBulletCooldown(double dt) {
        bulletCooldown += dt;
    }

    public void setBulletCooldown(double x){
        bulletCooldown = x;
    }
    public double getBulletCooldown(){
        return bulletCooldown;
    }
}

