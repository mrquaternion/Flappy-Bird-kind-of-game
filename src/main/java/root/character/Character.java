package character;
import character.physics.Hitbox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Character {
    Hitbox hitbox = new Hitbox();

    protected Image characterImage;

    protected ImageView imageViewCharacter;
    protected int healthStatus;

    public void die() {
        System.out.println("The character dies.");
    }

    public Image characterImage(){
        return characterImage;
    }
    public abstract void setImageView();

    public void setHitbox() {
        hitbox.setX(imageViewCharacter.getX());
        hitbox.setY(imageViewCharacter.getY());
        hitbox.setWidth(imageViewCharacter.getFitWidth());
        hitbox.setHeight(imageViewCharacter.getFitHeight());
    }
}







