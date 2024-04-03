package character.collision;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.shape.Rectangle;

public class ImageBounds {

    public Rectangle imageBoundsCalculator(Image image) {
        PixelReader reader = image.getPixelReader();
        int minX = Integer.MAX_VALUE;
        int maxX = 0;
        int minY = Integer.MAX_VALUE;
        int maxY = 0;

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int argb = reader.getArgb(x, y);
                int alpha = (argb >> 24) & 0xff;
                if (alpha > 0) { // Assuming any non-transparent pixel is part of the sprite
                    if (x < minX) minX = x;
                    if (x > maxX) maxX = x;
                    if (y < minY) minY = y;
                    if (y > maxY) maxY = y;
                }
            }
        }

        if (minX < maxX && minY < maxY) {
            return new Rectangle(minX, minY, maxX - minX + 1, maxY - minY + 1);
        } else {
            // In case the sprite is completely transparent or an error occurred
            return null;
        }
    }
}

