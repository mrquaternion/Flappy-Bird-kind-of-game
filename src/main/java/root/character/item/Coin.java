package character.item;

import java.util.Random;

public class Coin {
    protected double x;
    protected double y;
    protected double vx = 120;
    protected boolean activeStatus = true;
    public static final int BACKGROUND_HEIGHT = 400;

    // -------------- Constructor --------------
    public Coin() {
        this.x = 640;
        Random random = new Random();
        this.y = random.nextDouble() * BACKGROUND_HEIGHT;
    }

    // -------------- Getters --------------
    public double getX() { return x; }
    public double getY() { return y; }
    public boolean getActiveStatus() { return activeStatus; }

    // -------------- Setters --------------
    public void setX(double x) { this.x = x;}
    public void setY(double y) { this.y = y;}
    public void setActiveStatus(boolean activeStatus) { this.activeStatus = activeStatus; }

    // -------------- Methods --------------
    public void update(double dt) {
        x -= vx * dt;
    }
}
