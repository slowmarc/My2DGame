package main;

import entity.Player;
import manager.EntityManager;
import manager.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    private static int NANOSECONDS_PER_SECOND = 1000000000;
    private static int NANOSECONDS_PER_MILLISECOND = 1000000;

    private final int originalTileSize = 16; //16x16 tile
    private final int scale = 3; // 3 * 16px kind of

    private final int tileSize = originalTileSize * scale; //48x48 px tile
    private final int maximumScreenHorizontal = 16; //16x12 (4:3)
    private final int maximumScreenVertical = 12;

    private final int screenWidth = tileSize * maximumScreenHorizontal; //768 pixels
    private final int screenHeight = tileSize * maximumScreenVertical; //576 px

    private final int framesPerSecond = 60;

    private Thread thread;

    TileManager tileManager;
    EntityManager entityManager;

    public GamePanel() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.BLACK); //might remove
        setDoubleBuffered(true);

        setFocusable(true);

        tileManager = new TileManager(this);
        entityManager = new EntityManager(this);

        Player player = new Player(0, 0, tileSize, tileSize);
        addKeyListener(player.getKeyHandler());
        entityManager.add(player);
    }

    public int getMaximumScreenHorizontal() {
        return maximumScreenHorizontal;
    }

    public int getMaximumScreenVertical() {
        return maximumScreenVertical;
    }

    public int getTileSize() {
        return tileSize;
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
        entityManager.updatePlayer();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        tileManager.draw(graphics2D);
        entityManager.draw(graphics2D);

        graphics2D.dispose();
    }
}