package character;
import character.physics.Hitbox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Character {
    Hitbox hitbox = new Hitbox();
    protected int healthStatus;

    protected double ratio;



    // -------------- Setters --------------
    public abstract void setImageView ();

    protected abstract void imageViewCharacterSet();

    public abstract void setHitbox();

    public void setHealthStatus(int healthStatus) {
        this.healthStatus = healthStatus;
    }

    // -------------- Getters --------------
    public abstract ImageView getImageView();
    public Hitbox getHitbox() {
        setHitbox();
        return hitbox;
    }

    public abstract void updatePosition(double dt);

    protected abstract void borderTouch();

}







