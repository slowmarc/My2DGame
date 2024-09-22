package tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Tile {
    protected BufferedImage image;

    protected int height;
    protected int width;
    protected boolean isObstacle;

    public Tile(String name, int height, int width, boolean isObstacle) {
        try {
            image = ImageIO.read(getClass().getResourceAsStream(name));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.height = height;
        this.width = width;
        this.isObstacle = isObstacle;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean isObstacle() {
        return isObstacle;
    }
}
