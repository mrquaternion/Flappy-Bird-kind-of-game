package character.item;

import character.Enemy;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import character.physics.Hitbox;

public class Bullet {
    Hitbox hitbox = new Hitbox();
    private final Image image;
    private final ImageView imageView;

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

    public Hitbox getHitbox() {
        hitbox.setX(imageView.getX());
        hitbox.setY(imageView.getY());
        hitbox.setWidth(imageView.getFitWidth());
        hitbox.setHeight(imageView.getFitHeight());
        return hitbox;
    }
}
