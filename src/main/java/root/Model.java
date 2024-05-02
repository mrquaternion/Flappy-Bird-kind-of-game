import character.Enemy;
import character.Hero;
import character.HeroGenerator;
import character.hero.Melee;
import character.hero.Stealth;
import character.hero.Tank;
import character.item.Bullet;
import character.item.Coin;
import character.item.CoinGenerator;
import java.util.List;
import java.util.ArrayList;

public class Model {
    private Enemy enemy;
    private List<Hero> heroes = new ArrayList<>();
    private List<Coin> coins = new ArrayList<>();


    public Model() {
        HeroGenerator heroGenerator = new HeroGenerator();
        CoinGenerator coinGenerator = new CoinGenerator();
        this.enemy = new Enemy();
    }

    public int getEnemyHealthStatus() { return enemy.getHealthStatus(); }
    public int getCoinCount() { return enemy.getAllCoin(); }
    public List<Hero> getHeroes() { return heroes; }
    public List<Coin> getCoins() { return coins; }

    public void setHeroes() {
        for (int i = 0; i < Hero.NUMBER_OF_HEROES; i++) {
            switch (i % 3) {
                case 0:
                    heroes.add(new Melee());
                    break;
                case 1:
                    heroes.add(new Tank());
                    break;
                case 2:
                    heroes.add(new Stealth());
                    break;
            }
        }
    }

    public void setCoins() {
        for (int i = 0; i < Coin.NUMBER_OF_COINS; i++) { coins.add(new Coin()); }
    }


    public void toggleShoot(long now) {
        if (enemy.lastBullet == null || now - enemy.lastBulletTime > 1e9) {
            double startX = enemy.getImageView().getX() + enemy.getImageView().getFitWidth();
            double startY = enemy.getImageView().getY() + enemy.getImageView().getFitHeight() / 2;

            enemy.lastBullet = new Bullet(startX, startY);
            enemy.bullets.add(enemy.lastBullet);

            gamePane.getChildren().add(enemy.lastBullet.getImageView());
            enemy.lastBulletTime = now;
        }
    }

    public void toggleJump() {
        if (!enemy.jumpingStatus) { enemy.isJumping(); }
    }

    public void togglePause() {
        isPaused = !isPaused; // Inverse l'état de pause
        if (isPaused) {
            animationTimer.stop(); // Arrête le timer si en pause
            pauseButton.setText("Resume"); // Change le texte du bouton pour indiquer la prochaine action
            System.out.println("Game is paused");
            enemy.gravityBlock();

        } else {
            animationTimer.start(); // Redémarre le timer si le jeu reprend
            pauseButton.setText("Pause"); // Revenir au texte original
        }
    }
}
