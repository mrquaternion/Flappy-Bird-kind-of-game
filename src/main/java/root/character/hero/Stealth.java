package character.hero;
import character.Enemy;
import character.Hero;
import character.physics.Background;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Stealth extends Hero {
    private double centerY;
    protected double sinCount = 0;

    // -------------- Constructor --------------

    public Stealth() {
        this.image = new Image("file:src/main/resources/BlackBeard.png");
        this.imageView = new ImageView(image);
        this.ratio = 0.15; // 0.15
        this.healthStatus = 100;
        this.imageView.setX(Background.WIDTH);
        this.imageView.setY(Math.random() * (Background.HEIGHT - imageView.getFitHeight()));
    }


    // Steal 10 coins from the enemy
    @Override
    public void interaction(Enemy enemy) {
        if (enemy.getAllCoin() >= 10){
            enemy.setAllCoin(enemy.getAllCoin() - 10);
        }
        else{
            enemy.setAllCoin(0);
            //test
        }
    }

    // -------------- Position --------------
    @Override
    public void resetHeroPosition(){
        imageView.setX(Background.WIDTH);
        imageView.setY(Math.random() * (Background.HEIGHT - imageView.getFitHeight()));
        centerY = imageView.getY();
    }

    // -------------- Update Position --------------

    @Override
    public void updatePosition(int nbOfCoins, double dt) {
        sinCount += dt;
        imageView.setX(imageView.getX() - ((vx / frameRate) + (nbOfCoins * 10) * dt));
        imageView.setY(centerY + Math.sin(sinCount*3)*25);
    }
}
