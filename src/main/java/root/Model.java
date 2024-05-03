import character.Enemy;
import character.Hero;
import character.HeroGenerator;
import character.hero.Melee;
import character.hero.Stealth;
import character.hero.Tank;
import character.item.Bullet;
import character.item.Coin;
import character.item.CoinGenerator;
import character.physics.Background;
import character.physics.Collision;
import javafx.scene.Node;


import java.util.List;
import java.util.ArrayList;

public class Model {
    // --------------------------------- Fields ---------------------------------
    private Background background;
    private Enemy enemy;
    private List<Hero> heroes = new ArrayList<>();
    private List<Coin> coins = new ArrayList<>();
    private CoinGenerator coinGenerator;
    private HeroGenerator heroGenerator;
    public boolean isPaused = false;
    private long lastUpdateTime = 0;
    private double lastHeroSpawnTime = 0;

    // --------------------------------- Constructor ---------------------------------
    public Model() {
        setHeroes();
        setCoins();
        setEnemy();
        setBackground();
        setCoinGenerator();
        setHeroGenerator();

        System.out.println("Model created. Here's the objects created: " + "Enemy: " + getEnemy() + ", Background: " + getBackground() + ", Heroes: " + getHeroes() + ", Coins: " + getCoins());
    }

    // --------------------------------- Getters ---------------------------------
    public Background getBackground() { return background; }
    public Enemy getEnemy() { return enemy; }
    public int getEnemyHealthStatus() { return enemy.getHealthStatus(); }
    public int getCoinCount() { return enemy.getAllCoin(); }
    public List<Hero> getHeroes() { return heroes; }
    public List<Coin> getCoins() { return coins; }
    public List<Bullet> getBullets() { return enemy.bullets; }


    // --------------------------------- Setters ---------------------------------
    private void setHeroGenerator() {
        this.heroGenerator = new HeroGenerator();
    }

    private void setCoinGenerator() {
        this.coinGenerator = new CoinGenerator();
    }

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
        for (int i = 0; i < Coin.NUMBER_OF_COINS; i++) {
            coins.add(new Coin());
        }
    }

    public void setEnemy() {
        this.enemy = new Enemy();
    }

    public void setBackground() {
        this.background = new Background();
    }

    // --------------------------------- Methods ---------------------------------
    public void updateGameState(long now) {
        if (lastUpdateTime == 0) {
            lastUpdateTime = now;
            lastHeroSpawnTime = now;
            return;
        }

        double deltaTime = (now - lastUpdateTime) / 1e9; // Convert nanoseconds to seconds

        updateBullets(deltaTime);
        updateBackground(deltaTime);
        updateEnemyPosition(deltaTime);
        updateHerosGeneration(deltaTime, now);
        updateCoinsGeneration(deltaTime);
        lastUpdateTime = now;
    }

    private void updateEnemyPosition(double dt) {
        enemy.gravityUnblock();
        enemy.jumpCooldown();
        if (enemy.isMoving) {
            enemy.updatePosition(dt);
            updateCoinsGeneration(dt);
        }
    }

    private void updateCoinsGeneration(double dt) {
        coinGenerator.spawn(coins, enemy, dt);
    }

    private void updateHerosGeneration(double dt, long now) {
        heroGenerator.updateHeroes(heroes, enemy, dt);
        if (heroGenerator.spawnHeroIfNeeded(heroes, now, lastHeroSpawnTime)) {
            lastHeroSpawnTime = now; // Update the last spawn time only when a hero is spawned
        }
    }


    private void updateBullets(double dt) {
        for (Bullet bullet : enemy.bullets) {
            bullet.updatePosition(dt);
        }
    }

    private void updateBackground(double dt) {
        background.scroll(enemy.getPickupCoin());
    }

    public void toggleShoot() {
        double startX = enemy.getX() + enemy.getFitWidth();
        double startY = enemy.getY() + (enemy.getFitHeight() / 2);

        enemy.lastBullet = new Bullet(startX, startY);
        enemy.bullets.add(enemy.lastBullet);
    }

    public void toggleJump() {
        if (!enemy.jumpingStatus) { enemy.isJumping(); }
    }
    public void togglePause() { this.isPaused = !isPaused;
        System.out.println("Pause toggled. Is paused: " + isPaused);
    }
}
