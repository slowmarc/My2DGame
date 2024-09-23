package main;

import entity.Player;
import manager.EntityManager;
import manager.PlayerManager;
import manager.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    private static int NANOSECONDS_PER_SECOND = 1000000000;
    private static int NANOSECONDS_PER_MILLISECOND = 1000000;

    private final int originalTileSize = 16; //16x16 tile
    private final int scale = 3; // 3 * 16px kind of

    private final int tileSize = originalTileSize * scale; //48x48 px tile
    private final int maximumScreenWidth = 16; //16x12 (4:3)
    private final int maximumScreenHeight = 12;
    private final int screenWidth = tileSize * maximumScreenWidth; //768 pixels
    private final int screenHeight = tileSize * maximumScreenHeight; //576 px

    private final int framesPerSecond = 60;

    private final int maximumWorldWidth = 50;
    private final int maximumWorldHeight = 50;
    private final int worldWidth = tileSize * maximumWorldWidth;
    private final int worldHeight = tileSize * maximumWorldHeight;

    private Thread thread;

    TileManager tileManager;
    EntityManager entityManager;
    PlayerManager playerManager;

    public GamePanel() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.BLACK); //might remove
        setDoubleBuffered(true);

        setFocusable(true);

        tileManager = new TileManager(this);
        entityManager = new EntityManager(this);

        Player player = new Player(tileSize * 23, tileSize * 21, tileSize, tileSize, screenWidth / 2 - tileSize / 2, screenHeight / 2 - tileSize / 2);
        addKeyListener(player.getKeyHandler());
        playerManager = new PlayerManager(this, player);
    }

    public int getMaximumScreenWidth() {
        return maximumScreenWidth;
    }

    public int getMaximumScreenHeight() {
        return maximumScreenHeight;
    }

    public int getMaximumWorldWidth() {
        return maximumWorldWidth;
    }

    public int getMaximumWorldHeight() {
        return maximumWorldHeight;
    }

    public int getWorldWidth() {
        return worldWidth;
    }

    public int getWorldHeight() {
        return worldHeight;
    }

    public int getTileSize() {
        return tileSize;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public void startThread() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        final double drawInterval = NANOSECONDS_PER_SECOND / framesPerSecond;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (thread != null) {
            update();
            repaint();

            try {
                double remainingTime = (nextDrawTime - System.nanoTime()) / NANOSECONDS_PER_MILLISECOND;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException exception) {
                throw new RuntimeException(exception);
            }
        }
    }

    public void update() {
        playerManager.update();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        tileManager.draw(graphics2D);
        entityManager.draw(graphics2D);
        playerManager.draw(graphics2D);

        graphics2D.dispose();
    }
}