package manager;

import entity.Entity;
import entity.Player;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerManager {
    private final GamePanel gamePanel;
    private final Player player;

    public PlayerManager(GamePanel gamePanel, Player player) {
        this.gamePanel = gamePanel;
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void update() {
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
        Entity.MoveSprite moveSprite = player.getMoveSprite();
        switch (player.getDirection()) {
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
        graphics.drawImage(sprite, player.getCamera().getHorizontalCoordinate(), player.getCamera().getVerticalCoordinate(), player.getHeight(), player.getWidth(), null);
    }
}
