package character;
import character.physics.Hitbox;
import javafx.scene.image.ImageView;

public abstract class Character {
    Hitbox hitbox = new Hitbox();
    protected int healthStatus;

    protected double ratio;

    protected double r;



    // -------------- Setters --------------
    public abstract void setImageView ();



    public void setHealthStatus(int healthStatus) {
        this.healthStatus = healthStatus;
    }

    // -------------- Getters --------------
    public abstract ImageView getImageView();


    protected abstract void borderTouch();

    public double getRadius(){
        return r;
    }

    public abstract double getMidX();

    public abstract double getMidY();


}







