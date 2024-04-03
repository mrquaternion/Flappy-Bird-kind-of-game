package character;

import javafx.scene.image.Image;

public interface Character {

    void attack(Character target);

    Image characterImage();
}
