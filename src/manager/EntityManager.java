package manager;

import entity.Entity;
import entity.Player;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class EntityManager {
    private final GamePanel gamePanel;
    private final ArrayList<Entity> entities;

    public EntityManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        entities = new ArrayList<>();
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void add(Entity entity) {
        entities.add(entity);
    }

    public void updatePlayer() {
        Player player = null;
        for (Entity entity : entities) {
            if (entity instanceof Player) {
                player = (Player) entity;
            }
        }
        Player.KeyHandler keyHandler = player.getKeyHandler();

        if (!keyHandler.isUpPressed() && !keyHandler.isDownPressed() && !keyHandler.isLeftPressed() && !keyHandler.isRightPressed()) {
            return;
        }

        if (keyHandler.isUpPressed()) {
            player.moveUp();
        } else if (keyHandler.isDownPressed()) {
            player.moveDown();
        } else if (keyHandler.isLeftPressed()) {
            player.moveLeft();
        } else if (keyHandler.isRightPressed()) {
            player.moveRight();
        }

        Entity.MoveSprite moveSprite = player.getMoveSprite();
        moveSprite.setSpriteCounter(moveSprite.getSpriteCounter() + 1);
        if (moveSprite.getSpriteCounter() > 15) {
            moveSprite.setSpriteNumber(moveSprite.getSpriteNumber() == 1 ? 2 : 1);
            moveSprite.setSpriteCounter(0);
        }
    }

    public void draw(Graphics graphics) {
        BufferedImage sprite = null;

        for (Entity entity : entities) {
            Entity.MoveSprite moveSprite = entity.getMoveSprite();
            switch (entity.getDirection()) {
                case UP:
                    sprite = moveSprite.getSpriteNumber() == 1 ? moveSprite.getUp1() : moveSprite.getUp2();
                    break;
                case DOWN:
                    sprite = moveSprite.getSpriteNumber() == 1 ? moveSprite.getDown1() : moveSprite.getDown2();
                    break;
                case LEFT:
                    sprite = moveSprite.getSpriteNumber() == 1 ? moveSprite.getLeft1() : moveSprite.getLeft2();
                    break;
                case RIGHT:
                    sprite = moveSprite.getSpriteNumber() == 1 ? moveSprite.getRight1() : moveSprite.getRight2();
                    break;
            }
            graphics.drawImage(sprite, entity.getLocation().getHorizontalCoordinate(), entity.getLocation().getVerticalCoordinate(), entity.getHeight(), entity.getWidth(), null);
        }
    }
}
