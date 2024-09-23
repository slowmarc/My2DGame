package manager;

import main.GamePanel;
import tile.GrassTile;
import tile.Tile;
import tile.WaterTile;

import java.awt.*;

public class TileManager {
    private GamePanel gamePanel;
    private final Tile[] tiles;
    private int map[][];

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[2];

        tiles[0] = new GrassTile(gamePanel.getTileSize(), gamePanel.getTileSize());
        tiles[1] = new WaterTile(gamePanel.getTileSize(), gamePanel.getTileSize());
        map = new int[gamePanel.getMaximumWorldWidth()][gamePanel.getMaximumWorldHeight()];
    }

    public void draw(Graphics graphics) {
        int worldWidthIndex = 0;
        int worldHeightIndex = 0;

        while (worldWidthIndex < gamePanel.getMaximumWorldWidth() && worldHeightIndex < gamePanel.getMaximumWorldHeight()) {
            int tileIndex = map[worldWidthIndex][worldHeightIndex];

            int worldHorizontalCoordinate = worldWidthIndex * gamePanel.getTileSize();
            int worldVerticalCoordinate = worldHeightIndex * gamePanel.getTileSize();

            int screenHorizontalCoordinate = worldHorizontalCoordinate - gamePanel.getPlayerManager().getPlayer().getLocation().getHorizontalCoordinate() + gamePanel.getPlayerManager().getPlayer().getCamera().getHorizontalCoordinate();
            int screenVerticalCoordinate = worldVerticalCoordinate - gamePanel.getPlayerManager().getPlayer().getLocation().getVerticalCoordinate() + gamePanel.getPlayerManager().getPlayer().getCamera().getVerticalCoordinate();

            if (worldHorizontalCoordinate + gamePanel.getTileSize() > gamePanel.getPlayerManager().getPlayer().getLocation().getHorizontalCoordinate() - gamePanel.getPlayerManager().getPlayer().getCamera().getHorizontalCoordinate()
                    && worldHorizontalCoordinate - gamePanel.getTileSize() < gamePanel.getPlayerManager().getPlayer().getLocation().getHorizontalCoordinate() + gamePanel.getPlayerManager().getPlayer().getCamera().getHorizontalCoordinate()
                    && worldVerticalCoordinate + gamePanel.getTileSize() > gamePanel.getPlayerManager().getPlayer().getLocation().getVerticalCoordinate() - gamePanel.getPlayerManager().getPlayer().getCamera().getVerticalCoordinate()
                    && worldVerticalCoordinate - gamePanel.getTileSize() < gamePanel.getPlayerManager().getPlayer().getLocation().getVerticalCoordinate() + gamePanel.getPlayerManager().getPlayer().getCamera().getVerticalCoordinate()) {
                graphics.drawImage(tiles[tileIndex].getImage(), screenHorizontalCoordinate, screenVerticalCoordinate, tiles[tileIndex].getHeight(), tiles[tileIndex].getWidth(), null);
            }
            worldWidthIndex++;

            if (worldWidthIndex == gamePanel.getMaximumWorldWidth()) {
                worldWidthIndex = 0;
                worldHeightIndex++;
            }
        }
    }
}
