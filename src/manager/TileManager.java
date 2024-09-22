package manager;

import main.GamePanel;
import tile.GrassTile;
import tile.Tile;
import tile.WaterTile;

import java.awt.*;

public class TileManager {
    private GamePanel gamePanel;
    private final Tile[] tiles;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[2];

        tiles[0] = new GrassTile(gamePanel.getTileSize(), gamePanel.getTileSize());
        tiles[1] = new WaterTile(gamePanel.getTileSize(), gamePanel.getTileSize());
    }

    public void draw(Graphics graphics) {
        int columnIndex = 0;
        int rowIndex = 0;

        int horizontalCoordinate = 0;
        int verticalCoordinate = 0;

        while (columnIndex < gamePanel.getMaximumScreenHorizontal() && rowIndex < gamePanel.getMaximumScreenVertical()) {
            graphics.drawImage(tiles[0].getImage(), horizontalCoordinate, verticalCoordinate, tiles[0].getHeight(), tiles[0].getWidth(), null);
            columnIndex++;
            horizontalCoordinate += gamePanel.getTileSize();

            if (columnIndex == gamePanel.getMaximumScreenHorizontal()) {
                columnIndex = 0;
                horizontalCoordinate = 0;

                rowIndex++;
                verticalCoordinate += gamePanel.getTileSize();
            }
        }
    }
}
