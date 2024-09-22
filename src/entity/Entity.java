package entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Entity {
    protected Location location;

    public class Location {
        protected int horizontalCoordinate;
        protected int verticalCoordinate;

        public Location(int horizontalCoordinate, int verticalCoordinate) {
            this.horizontalCoordinate = horizontalCoordinate;
            this.verticalCoordinate = verticalCoordinate;
        }

        public int getHorizontalCoordinate() {
            return horizontalCoordinate;
        }

        public int getVerticalCoordinate() {
            return verticalCoordinate;
        }
    }

    protected int height;
    protected int width;

    protected int speed;
    protected int health;
    protected int maximumHealth;

    protected Direction direction;

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    protected MoveSprite moveSprite;

    public class MoveSprite {
        protected int spriteCounter = 0;
        protected int spriteNumber = 0;

        protected BufferedImage up1;
        protected BufferedImage up2;
        protected BufferedImage down1;
        protected BufferedImage down2;
        protected BufferedImage left1;
        protected BufferedImage left2;
        protected BufferedImage right1;
        protected BufferedImage right2;

        public MoveSprite(String up1, String up2, String down1, String down2, String left1, String left2, String right1, String right2) {
            try {
                this.up1 = ImageIO.read(getClass().getResourceAsStream(up1));
                this.up2 = ImageIO.read(getClass().getResourceAsStream(up2));
                this.down1 = ImageIO.read(getClass().getResourceAsStream(down1));
                this.down2 = ImageIO.read(getClass().getResourceAsStream(down2));
                this.left1 = ImageIO.read(getClass().getResourceAsStream(left1));
                this.left2 = ImageIO.read(getClass().getResourceAsStream(left2));
                this.right1 = ImageIO.read(getClass().getResourceAsStream(right1));
                this.right2 = ImageIO.read(getClass().getResourceAsStream(right2));
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }

        public int getSpriteCounter() {
            return spriteCounter;
        }

        public int getSpriteNumber() {
            return spriteNumber;
        }

        public void setSpriteCounter(int spriteCounter) {
            this.spriteCounter = spriteCounter;
        }

        public void setSpriteNumber(int spriteNumber) {
            this.spriteNumber = spriteNumber;
        }

        public BufferedImage getUp1() {
            return up1;
        }

        public BufferedImage getUp2() {
            return up2;
        }

        public BufferedImage getDown1() {
            return down1;
        }

        public BufferedImage getDown2() {
            return down2;
        }

        public BufferedImage getLeft1() {
            return left1;
        }

        public BufferedImage getLeft2() {
            return left2;
        }

        public BufferedImage getRight1() {
            return right1;
        }

        public BufferedImage getRight2() {
            return right2;
        }
    }

    public Entity(int horizontalCoordinate,
                  int verticalCoordinate,
                  int height,
                  int width,
                  int speed,
                  int health,
                  int maximumHealth,
                  Direction direction,
                  String up1,
                  String up2,
                  String down1,
                  String down2,
                  String left1,
                  String left2,
                  String right1,
                  String right2) {
        location = new Location(horizontalCoordinate, verticalCoordinate);
        this.height = height;
        this.width = width;

        this.speed = speed;
        this.health = health;
        this.maximumHealth = maximumHealth;
        this.direction = direction;
        moveSprite = new MoveSprite(up1, up2, down1, down2, left1, left2, right1, right2);
    }

    public Location getLocation() {
        return location;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getSpeed() {
        return speed;
    }

    public int getHealth() {
        return health;
    }

    public Direction getDirection() {
        return direction;
    }

    public MoveSprite getMoveSprite() {
        return moveSprite;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void moveUp() {
        direction = Direction.UP;
        location.verticalCoordinate -= speed;
    }

    public void moveDown() {
        direction = Direction.DOWN;
        location.verticalCoordinate += speed;
    }

    public void moveLeft() {
        direction = Direction.LEFT;
        location.horizontalCoordinate -= speed;
    }

    public void moveRight() {
        direction = Direction.RIGHT;
        location.horizontalCoordinate += speed;
    }
}