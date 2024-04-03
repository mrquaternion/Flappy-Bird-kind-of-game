package character.collision;

import javafx.scene.shape.Rectangle;

public class Hitbox {
    private Rectangle rectangle;

    // -------------- Constructor --------------
    public Hitbox() {
        rectangle = new Rectangle();
    }

    // -------------- Getters --------------
    public Rectangle getRectangle() { return rectangle; }

    public double getX() { return rectangle.getX(); }

    public double getY() { return rectangle.getY(); }

    public double getWidth() { return rectangle.getWidth(); }

    public double getHeight() { return rectangle.getHeight(); }

    // -------------- Setters --------------
    public void setRectangle(Rectangle rectangle) { this.rectangle = rectangle; }

    public void setX(double x) { rectangle.setX(x); }

    public void setY(double y) { rectangle.setY(y); }

    public void setWidth(double width) { rectangle.setWidth(width); }

    public void setHeight(double height) { rectangle.setHeight(height); }

    // -------------- Methods --------------
    public boolean intersects(Hitbox other) {
        return rectangle.getBoundsInParent().intersects(other.getRectangle().getBoundsInParent());
    }
}
