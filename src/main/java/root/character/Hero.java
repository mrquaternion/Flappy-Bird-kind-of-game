package character;

import character.physics.Background;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public abstract class Hero extends Character {
    public Image image;
    public ImageView imageView = new ImageView();
    public boolean isActivated = false;
    public static final int NUMBER_OF_HEROES = 6;
    public static final double vx = 120;
    public static final int frameRate = 60;

    Enemy enemy;

    // -------------- Setters --------------

    public void setImageView() {
        imageView.setFitWidth(image.getWidth() * ratio);
        imageView.setFitHeight(image.getHeight() * ratio);
        imageView.setPreserveRatio(true);
    }

    public static void setRandomRadius(Hero hero){
        hero.r = 10 + Math.random() * 35;
        hero.setRatio(hero.r / hero.image.getHeight());
    }

// -------------- Getters --------------
    public ImageView getImageView() {
        setImageView();
        return imageView;
    }

    @Override
    public double getMidX(){
        return imageView.getX() + imageView.getFitWidth() / 2;
    }

    @Override
    public double getMidY(){
        return imageView.getY() + imageView.getFitHeight() / 2;
    }

    public abstract void interaction(Enemy enemy);

    public abstract void updatePosition(int nbOfCoins, double dt);


    @Override
    public void borderTouch() {
        if (imageView.getX() < -imageView.getFitWidth()) {
            isActivated = false;
            imageView.setX(Background.WIDTH);
            imageView.setY(Math.random() * (Background.HEIGHT - imageView.getFitHeight()));
        }
    }

    public void resetHeroPosition() {
        imageView.setX(Background.WIDTH);
        imageView.setY(Math.random() * (Background.HEIGHT - imageView.getFitHeight()));
    }



}
