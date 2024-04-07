package character;

import character.physics.Background;
import character.physics.Hitbox;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public abstract class Hero extends Character {
    public Image image;
    public ImageView imageView = new ImageView();
    protected boolean isActivated = false;
    public static final int NUMBER_OF_HEROES = 6;
    public static final double vx = 120;
    public static final int frameRate = 60;
    public int nbOfCoins = 0;
    Enemy enemy;


    public void setHitbox() {
        hitbox.setX(imageView.getX());
        hitbox.setY(imageView.getY());
        hitbox.setWidth(imageView.getFitWidth());
        hitbox.setHeight(imageView.getFitHeight());
    }

    public ImageView getImageView() {
        setImageView();
        return imageView;
    }

    public void setImageView() {
        imageView.setFitWidth(image.getWidth() * ratio);
        imageView.setFitHeight(image.getHeight() * ratio);
        imageView.setPreserveRatio(true);
    }

    public Hitbox getHitbox() {
        Hitbox hitbox = new Hitbox();
        hitbox.setX(imageView.getX());
        hitbox.setY(imageView.getY());
        hitbox.setWidth(image.getWidth() * ratio);
        hitbox.setHeight(image.getHeight() * ratio);
        return hitbox;
    }

    @Override
    public void imageViewCharacterSet() {
        imageView.setY(Math.random() * (Background.HEIGHT - imageView.getFitHeight()));
        imageView.setX(Background.WIDTH - 20);
    }

    public abstract void interaction(Enemy enemy);

    @Override
    public void updatePosition(double dt) {
        // Calcul de la nouvelle vitesse et la nouvelle position du hero
        imageView.setX(imageView.getX() - ((vx / frameRate) + (nbOfCoins * 10) * dt));
    }
    public void updatePosition(int nbOfCoins, double dt) {
        imageView.setX(imageView.getX() - ((vx / frameRate) + (nbOfCoins * 10) * dt));
    }


    @Override
    public void borderTouch() {
        if (imageView.getX() < -imageView.getFitWidth()) {
            isActivated = false;
            imageView.setX(Background.WIDTH);
            imageView.setY(Math.random() * (Background.HEIGHT - imageView.getFitHeight()));
        }
    }
}
