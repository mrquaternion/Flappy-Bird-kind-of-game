import character.Enemy;
import character.Hero;
import character.HeroGenerator;
import character.hero.Melee;
import character.hero.Stealth;
import character.hero.Tank;
import character.item.Coin;
import character.item.CoinGenerator;
import character.physics.Background;
import character.physics.Collision;
import java.util.List;
import java.util.ArrayList;

public class Model {
    // --------------------------------- Fields ---------------------------------
    private Background background;
    private Enemy enemy;
    private final List<Hero> heroes = new ArrayList<>();
    private final List<Coin> coins = new ArrayList<>();
    private CoinGenerator coinGenerator;
    private HeroGenerator heroGenerator;
    public boolean isPaused = false;
    private long lastUpdateTime = 0;
    private double lastHeroSpawnTime = 0;
    protected int frameCount = 0;
    private double timeGone = 0;

    // --------------------------------- Constructor ---------------------------------
    public Model() {
        setHeroes();
        setCoins();
        setEnemy();
        setBackground();
        setCoinGenerator();
        setHeroGenerator();
    }

    // --------------------------------- Getters ---------------------------------
    public Background getBackground() { return background; }
    public Enemy getEnemy() { return enemy; }
    public int getEnemyHealthStatus() { return enemy.getHealthStatus(); }
    public int getCoinCount() { return enemy.getAllCoin(); }
    public List<Hero> getHeroes() { return heroes; }
    public List<Coin> getCoins() { return coins; }


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
        double deltaTime = (now - lastUpdateTime) / 1e9;
        enemy.updateBulletCooldown(deltaTime);
        updateBullet(deltaTime);
        updateBackground();
        updateEnemy(deltaTime);
        updateHerosGeneration(deltaTime, now);
        updateCoinsGeneration(deltaTime);


        if (timeGone < 1) {
            timeGone += deltaTime;
        } else if (timeGone >= 0) {
            timeGone = 0;
            frameCount++;
        }

        enemy.setCurrentImageView(enemy.frames[frameCount % enemy.frames.length]);
        lastUpdateTime = now;
    }

    // --------------------------------- Update Methods ---------------------------------
    private void updateEnemy(double dt) {
        enemy.jumpCooldown();
        enemy.updatePosition(dt);
        Collision.checkCollisionsShoot(heroes, enemy);
    }

    private void updateCoinsGeneration(double dt) {
        coinGenerator.spawn(coins, enemy, dt);
    }

    private void updateHerosGeneration(double dt, long now) {
        heroGenerator.updateHeroes(heroes, enemy, dt);
        if (heroGenerator.spawnHeroIfNeeded(heroes, now, lastHeroSpawnTime)) {
            lastHeroSpawnTime = now;
        }
    }

    private void updateBullet(double dt) {
        if (enemy.getBullet().getActive()) {
            enemy.getBullet().updatePosition(dt);
        }
    }

    private void updateBackground() {
        background.scroll(enemy.getPickupCoin());
    }

    // --------------------------------- Toggle Methods ---------------------------------
    public void toggleShoot() {
        if (enemy.getBullet().getBulletCooldown() == 0) {
            double startX = enemy.getX() + enemy.getFitWidth();
            double startY = enemy.getY() + (enemy.getFitHeight() / 2);
            enemy.setBulletPosition(startX, startY);
            enemy.setBulletActive();
        }
    }

    public void toggleJump() {
        if (!enemy.jumpingStatus) { enemy.isJumping(); }
    }
    public void togglePause() {
        this.isPaused = !isPaused;
        if (!isPaused) {
            lastUpdateTime = 0;
        }
    }


}
