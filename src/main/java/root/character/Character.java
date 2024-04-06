package character;
import character.physics.Hitbox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Character {
    Hitbox hitbox = new Hitbox();

    protected Image characterImage;

    protected ImageView imageViewCharacter;
    protected int healthStatus;

    protected double ratio;



    // -------------- Setters --------------
    public void setImageView(){
        imageViewCharacter = new ImageView(characterImage);
        imageViewCharacter.setFitWidth(characterImage.getWidth() * ratio);
        imageViewCharacter.setFitHeight(characterImage.getHeight() * ratio);
        imageViewCharacterSet();
        imageViewCharacter.setPreserveRatio(true);
        System.out.println("Character image view set");
    }

    protected abstract  void imageViewCharacterSet();

    public void setHitbox() {
        hitbox.setX(imageViewCharacter.getX());
        hitbox.setY(imageViewCharacter.getY());
        hitbox.setWidth(imageViewCharacter.getFitWidth());
        hitbox.setHeight(imageViewCharacter.getFitHeight());
    }

    public void setHealthStatus(int healthStatus) {
        this.healthStatus = healthStatus;
    }

    // -------------- Getters --------------
    public ImageView getImageView() {
        return imageViewCharacter;
    }
    public Hitbox getHitbox() {
        setHitbox();
        return hitbox;
    }

    public abstract void updatePosition(double dt);

    protected abstract void borderTouch();

}







