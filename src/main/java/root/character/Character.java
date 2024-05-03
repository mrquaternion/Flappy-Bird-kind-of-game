package character;
import javafx.scene.image.ImageView;

public abstract class Character {
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

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public double getX() { return getImageView().getX(); }
    public double getY() { return getImageView().getY(); }
    public double getFitWidth() { return getImageView().getFitWidth(); }
    public double getFitHeight() { return getImageView().getFitHeight(); }
}







