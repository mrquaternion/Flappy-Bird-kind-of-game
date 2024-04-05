package character;

import javafx.scene.image.Image;

public class Hero  extends Character {



    @Override
    public Image characterImage() {
        return new Image("file:src/main/resources/enemy.png");
    }

    public void setImageView(){
        // Implement the method here
    }
}
