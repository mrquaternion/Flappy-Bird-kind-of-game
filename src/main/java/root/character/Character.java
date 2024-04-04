package character;
import javafx.scene.image.Image;
public abstract class Character {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean healthStatus = true;

    public void die() {
        System.out.println("The character dies.");
    }

    public abstract Image characterImage();
}







