/*  
 *  StarterPlainsMap.java
 *  
 *  Description: A strategy which implements MapStrategy.
 *               Builds a starter plains map.
 *
*/

package graphics.maps;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.Simulator;
import graphics.Tile;
import graphics.Camera;

public class StarterPlainsMap implements MapStrategy
{
    // Attributes
    private final int MAP_TILES = 55;
    private final int MAP_SIZE_TILE_X = 50;
    private final int MAP_SIZE_TILE_Y = 50;
    private Tile tileImages[];
    private int mapTileGrid[][];
    private MapLoader mapLoader;
    private Camera camera;

    // Default constructor
    public StarterPlainsMap(Camera camera)
    {
        this.camera = camera;
        this.tileImages = new Tile[MAP_TILES];
        this.mapLoader = new MapLoader();
        this.mapTileGrid = this.mapLoader.loadMap("/maps/starter_plains.txt", MAP_SIZE_TILE_X, MAP_SIZE_TILE_Y);
        getTileImages();
    }

    // Build map
    public void buildMap(Graphics2D graphics2D)
    {
        int mapColumn = 0;
        int mapRow = 0;

        while(mapColumn < MAP_SIZE_TILE_X && mapRow < MAP_SIZE_TILE_Y)
        {
            int tileIndex = mapTileGrid[mapColumn][mapRow];
            int mapPixelX = mapColumn * Simulator.TILE_SIZE;
            int mapPixelY = mapRow * Simulator.TILE_SIZE;
            int tileScreenPositionX = mapPixelX - camera.getPlayerScreenX();
            int tileScreenPositionY = mapPixelY - camera.getPlayerScreenY();

            graphics2D.drawImage(tileImages[tileIndex].getImage(), tileScreenPositionX, tileScreenPositionY, Simulator.TILE_SIZE, Simulator.TILE_SIZE, null);
            mapColumn++;

            if(mapColumn == MAP_SIZE_TILE_X)
            {
                mapColumn = 0;
                mapRow++;
            }
        }
    }

    // Get tile images and set consecutive indexs of tile image array to the loaded images
    private void getTileImages()
    {
        try {
            tileImages[0] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/grass_1.png")));
            tileImages[1] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/grass_2.png")));
            tileImages[2] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/grass_3.png")));
            tileImages[3] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/grass_4.png")));
            tileImages[4] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/grass_5.png")));
            tileImages[5] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/path.png")));
            tileImages[6] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/path_corner_1.png")));
            tileImages[7] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/path_corner_2.png")));
            tileImages[8] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/path_corner_3.png")));
            tileImages[9] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/path_corner_4.png")));
            tileImages[10] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/path_inner_1.png")));
            tileImages[11] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/path_inner_2.png")));
            tileImages[12] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/path_inner_3.png")));
            tileImages[13] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/path_inner_4.png")));
            tileImages[14] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/path_side_1.png")));
            tileImages[15] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/path_side_2.png")));
            tileImages[16] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/path_side_3.png")));
            tileImages[17] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/path_side_4.png")));
            tileImages[18] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/wall.png")), true);
            tileImages[19] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/wall_corner_1.png")), true);
            tileImages[20] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/wall_corner_2.png")), true);
            tileImages[21] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/wall_corner_3.png")), true);
            tileImages[22] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/wall_corner_4.png")), true);
            tileImages[23] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/wall_inner_1.png")), true);
            tileImages[24] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/wall_inner_2.png")), true);
            tileImages[25] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/wall_inner_3.png")), true);
            tileImages[26] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/wall_inner_4.png")), true);
            tileImages[27] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/wall_side_1.png")), true);
            tileImages[28] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/wall_side_2.png")), true);
            tileImages[29] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/wall_side_3.png")), true);
            tileImages[30] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/wall_side_4.png")), true);
            tileImages[31] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/water.png")), true);
            tileImages[32] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/water_corner_1.png")), true);
            tileImages[33] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/water_corner_2.png")), true);
            tileImages[34] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/water_corner_3.png")), true);
            tileImages[35] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/water_corner_4.png")), true);
            tileImages[36] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/water_inner_1.png")), true);
            tileImages[37] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/water_inner_2.png")), true);
            tileImages[38] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/water_inner_3.png")), true);
            tileImages[39] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/water_inner_4.png")), true);
            tileImages[40] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/water_side_1.png")), true);
            tileImages[41] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/water_side_2.png")), true);
            tileImages[42] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/water_side_3.png")), true);
            tileImages[43] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/water_side_4.png")), true);
            tileImages[44] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/flower_1.png")));
            tileImages[45] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/flower_2.png")));
            tileImages[46] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/flower_3.png")));
            tileImages[47] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/grass_6.png")));
            tileImages[48] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/grass_7.png")));
            tileImages[49] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/grass_8.png")));
            tileImages[50] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/grass_9.png")));
            tileImages[51] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/mushroom_1.png")));
            tileImages[52] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/mushroom_2.png")));
            tileImages[53] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/mushroom_3.png")));
            tileImages[54] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/mushroom_4.png")));
        }
        catch(IOException exception) {
            exception.printStackTrace();
        }
    }
}
